package com.main;

// Author: EliFo
// Description: Class that represents a decision tree, provides functionality to grow one
// 7/26/2016

import java.util.List;
import java.util.Map;

public class DecisionTree {


    /*
     * used to hold the Attributes that can still be selected to split the data
     * i needed some way to keep track of what options where still left
     */
    private List<Attribute> toSelect;


    //head node for the decision tree
    private Node head;

    public DecisionTree(DataSet data) {
        head = new Node();
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

    public boolean query(Record r) {
        return false;
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

    /*public void print() {
        head.PrintPretty("", "", "", true);

    }*/

    public String toString() {
        return head.toString();
    }

}
