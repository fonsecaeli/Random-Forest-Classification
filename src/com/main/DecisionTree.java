package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DecisionTree {

    /*
     * used to hold the Attributes that can still be selected to split the data
     * i needed some way to keep track of what options where still left
     */
    private List<Attribute> toSelect;

    //head node for the decision tree
    private Node head;

    public DecisionTree(DataSet data) {
        head = new Node("HEAD_NODE");
        grow(data, head);

    }

    //this will be the recursive method we need not sure exactly how this works yet
    //TODO: make sure we don't check attributes that have already been split on
    public void grow(DataSet data, Node node) {
        Attribute toSplitOn = bestSplit(data);
        List<Record> records = data.getData();
        
        //IF ONLY 1 RECORD IS IN DATA, toSplitOn WILL BE NULL BECAUSE THERE WOULD BE NO INFORMATION GAIN FROM SPLITTING
        if(toSplitOn==null){
            if(!records.isEmpty()){
                node.setDecision(records.get(0).getClassificationValue(data));
            }
        } else {
            
            Map<String,DataSet> dataSets = DataSet.splitData(data, toSplitOn);
            
            node.setAttribute(data, toSplitOn);
            List<String> keys = toSplitOn.getValues();
            
            for(int i=0; i<keys.size(); i++){
                String key = keys.get(i);
                Node nextNode = node.getChild(key);
                DataSet dataSetForNextNode = dataSets.get(key);
                grow(dataSetForNextNode, nextNode);
            }
            
        }
    }

    //helper method for the growTree method, will split the data on the best attribute and return that attribute so
    //that the tree can be grown correctly
    public Attribute bestSplit(DataSet data) {
            List<Attribute> attributes = data.getAttributes();
            Attribute bestAtt=attributes.get(0);
            double bestGain=0.0;
            
            for(int i=0; i<attributes.size()-1; i++){
                Attribute currentAttribute = attributes.get(i);
                double currentGain = Entropy.gain(data, currentAttribute);
                if(currentGain>bestGain){
                    bestAtt=currentAttribute;
                    bestGain=currentGain;
                }
            }
            
            if(bestGain==0.0){
                return null;
            } else {
                return bestAtt;
            }
    }

    public String query(Record r) {

        Node currentNode = head;
        while(true) {
            String classification = currentNode.getDecision();
            if(classification != null) {
                return classification;
            }
            Attribute currentAtt = currentNode.getAttribute();
            String value = r.getValue(currentAtt);
            Set<String> keys = currentNode.getKeys();
            if (keys.contains(value)) {
                currentNode = currentNode.getChild(value);
            } else {
                return "Error test record: " + r + "cannot be classified because it does not match the training data";
            }
        }
    }

    //could just be like ascii art shown on the console to start and then a gui later
    //if we want to do that
    public void display() {

    }

    //saves tree so that it can be reconstructed easily without starting again from the data
    public void saveCurrentTree() {

    }

    //loads a saved tree from a file of some kind
    public void loadTree(String fileName) {

    }

    @Override
    public String toString(){
	return toString(false);
    }

    public String toString(boolean doColor){
	MutableString s = new MutableString();
	toStringRecursive(s, 0, head, true, doColor);
        return s.get();
    }

    private void toStringRecursive(MutableString s, int deep, Node n, boolean atEnd, boolean doColor){
        s.add(getTabs(deep, atEnd, doColor));
        if(doColor){
            s.add(n.toStringColor()+"\n");
        }else{
            s.add(n.toString()+"\n");
        }
        if(n.getAttribute()!=null){
            Set<String> keys = n.getKeys();
            List<String> listOfKeys = new ArrayList<>();
            
            for(String key : keys){
                listOfKeys.add(key);
            }
            
            for(int i=0; i<listOfKeys.size(); i++){
                String key = listOfKeys.get(i);
                toStringRecursive(s, deep+1, n.getChild(key), i==listOfKeys.size()-1, doColor);
            }
        }
    }


    public String getTabs(int deep, boolean atEnd, boolean doColor){
        String s="";
	if(doColor)s+="\u001B[1m";
	for(int i=0;i<deep;i++)
		s+=".\t";
        if(atEnd){
            s+="\\";
        } else {
            s+="|";
        }
        if(doColor)s+="\u001B[0m";
	return s;
    }
    
    private class MutableString {
        private String s;
        
        public MutableString(){
            s="";
        }
        
        public void add(String string){
            s+=string;
        }
        
        public String get(){
            return s;
        }
        
    }

}
