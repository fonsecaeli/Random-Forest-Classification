package com.main;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The basic node for the Decision tree class
 * Has a list of children nodes and children keys (pointer Strings) as well as an Attribute for the node
 */
public class Node {
    private Map<String,Node> children;
    private Attribute att;
    private String decision;
    
    /**
     * Initializes children and childrenKeys
     */
    public Node(){
        children = new LinkedHashMap<>();
    }
    
    //The initial setup for a node; Sets the Attribute and populates the map of children with the possible values in the Attribute as the keys
    public void setAttribute(Attribute a){
        att=a;
        List<String> values = att.getValues();
        
        for(int i=0;i<values.size();i++){
            children.put(values.get(i),new Node());
        }
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
	
	public String toString(){
		if (decision == null)
			return stringMaker(0, "Base Node");
		else throw new Error("You can't print an unpopulated tree!");
	}
	
	public String stringMaker(int tabs, String str){
		String toReturn = tabs(tabs)+">>> [Option: "+str+"]";
		if (decision == null){
			toReturn+=", [Attribute: "+att.getName()+"]\n";
			for (String a: children.keySet())
				toReturn+=children.get(a).stringMaker(tabs+1, a);
		} else toReturn+="\n"+tabs(tabs+1)+"[Classification: "+decision+"]\n";
		return toReturn;
	}
	
	public String tabs(int tabs){
		String str = "";
		for (int i=0; i<tabs; i++)
			str+="\t";
		return str;
	}
}
