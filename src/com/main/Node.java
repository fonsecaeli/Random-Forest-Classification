package com.main;

import java.util.*;

/**
 * The basic node for the Decision tree class
 * Has a list of children nodes and children keys (pointer Strings) as well as an Attribute for the node
 */
public class Node {
    private Map<String,Node> children;
    private Attribute att;
    private String decision;
    private String keyString;
    
    /**
     * Initializes children and childrenKeys
     */
    public Node(String from){
        children = new LinkedHashMap<>();
        keyString=from;
    }

    /**
     * The initial setup for a node; Sets the Attribute and populates the map of children
     * with the possible values in the Attribute as the keys
     *
     * @param ds the data set
     * @param a the attribute
     */
    public void setAttribute(DataSet ds, Attribute a){
        att=a;
        List<String> values = att.getValues();
        List<Record> records = ds.getRecords();
        
        for(int i=0;i<values.size();i++){
            for(int j=0; j<records.size(); j++){
                if(records.get(j).getValue(att).equals(values.get(i))){
                    createChild(values.get(i));
                    break;
                }
            }
        }
    }

    /**
     * gets the attribute this node splits on
     *
     * @return a List of Attributes
     */
    public Attribute getAttribute(){
        return att;
    }

    /**
     * Creates a child node for this node
     *
     * @param str the key to assign to the new child
     */
    private void createChild(String str){
        children.put(str, new Node(str));
    }

    /**
     * Returns a Node based on a given key
     *
     * @param str the key for the child
     * @return the child that corresponds to the given key
     */
    public Node getChild(String str){
        return children.get(str);
    }

    /**
     * use for(String key : object.getKeys())
     * returns the Set of all Keys (which can be used to access all the mapped Nodes)
     *
     * @return the list of keys
     */
    public Set<String> getKeys(){
        return children.keySet();
    }
    
    //Sets the decision for a node; only applicable for leaf nodes
    public void setDecision(String value){
        decision=value;
    }

    /**
     * getter for the decision field
     *
     * @return the decision of this node
     */
    public String getDecision(){
        return decision;
    }

    /**
     * getter for the keyString field
     *
     * @return the keyString of this node
     */
	public String getKeyString(){
		return keyString;
	}

    /**
     * returns a String representation of this object with color
     *
     * @return String representation of this object
     */
    public String toStringColor(){
        String toReturn = "[Option: \u001B[31m"+keyString+"\u001B[0m";
            if (att != null){
                toReturn+=" | Attribute: \u001B[47m"+att.getName()+"\u001B[0m]";
            } else if(decision!=null && !decision.equals(""))
                toReturn+="][Classification: \u001B[34m"+decision+"]\u001B[0m";
            return toReturn;
    }
	
    @Override
    /**
     * toString for this Object
     *
     * @return a folder representation of the tree bellow
     * this node
     */
    public String toString(){
	String toReturn = "[Option: "+keyString;
		if (att != null){
			toReturn+=" | Attribute: "+att.getName()+"]";
		} else if(decision!=null && !decision.equals(""))
			toReturn+="][Classification: "+decision+"]";
		return toReturn;
    }

}

