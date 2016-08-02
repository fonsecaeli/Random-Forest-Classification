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
    
    //The initial setup for a node; Sets the Attribute and populates the map of children with the possible values in the Attribute as the keys
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
    
    public Attribute getAttribute(){
        return att;
    }
    
    private void createChild(String str){
        children.put(str, new Node(str));
    }

    //returns a Node based on a given key
    public Node getChild(String str){
        return children.get(str);
    }

    //use for(String key : object.getKeys())
    //returns the Set of all Keys (which can be used to access all the mapped Nodes)
    public Set<String> getKeys(){
        return children.keySet();
    }
    
    //Sets the decision for a node; only applicable for leaf nodes
    public void setDecision(String value){
        decision=value;
    }
    
    //returns the decisions
    public String getDecision(){
        return decision;
    }
	
    public String toStringColor(){
	String toReturn = "[Option: \u001B[31m"+keyString+"\u001B[0m";
		if (att != null){
			toReturn+=" | Attribute: \u001B[47m"+att.getName()+"\u001B[0m]";
		} else if(decision!=null && !decision.equals(""))
			toReturn+="][Classification: \u001B[34m"+decision+"]\u001B[0m";
		return toReturn;
    }
	
    @Override
    public String toString(){
	String toReturn = "[Option: "+keyString;
		if (att != null){
			toReturn+=" | Attribute: "+att.getName()+"]";
		} else if(decision!=null && !decision.equals(""))
			toReturn+="][Classification: "+decision+"]";
		return toReturn;
    }

}

