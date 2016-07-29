package com.main;

// Author: EliFo
// Description: Class that represents a decision tree, provides functionality to grow one
// 7/26/2016

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
        head = new Node();
        grow(data, head);

    }

    //this will be the recursive method we need not sure exactly how this works yet
    //TODO: make sure we don't check attributes that have already been split on
    public void grow(DataSet data, Node node) {
        //base case, once we reach maximum entropy
        if(Entropy.entropy(data) == 0) { //TODO: configure entropy to work with DataSet
            node.setDecision(data.getData().get(0).getClassificationValue(data)); //setting the Decision of the node to the
            //class shared between all the Records in the DataSet data
            return;
        }
        Attribute att = bestSplit(data);
        Map<String, DataSet> newDataSets = DataSet.splitData(data, att);
        node.setAttribute(att); //TODO: need setCondition() in Node

        Set<String> keys = node.getKeys();
        for(String str: keys) {
            DataSet d = newDataSets.get(str);
            if(d != null) {
                grow(d, node.getChild(str));
            }
        }


    }

    //helper method for the growTree method, will split the data on the best attribute and return that attribute so
    //that the tree can be grown correctly
    public Attribute bestSplit(DataSet data) {
        List<Attribute> attributes = data.getAttributes();
        List<Record> records = data.getData();
        double bestGain = 0;
        Attribute bestAtt = attributes.get(0);
        for(Attribute att: attributes) {
            if(att!=data.getClassification()) {
                double gain = Entropy.gain(data, att);
                if (gain > bestGain) {
                    bestAtt = att;
                    bestGain = gain;
                }
            }
        }
        if(bestAtt == null) {
            throw new IllegalArgumentException("could not find best attribute error");
        }
        return bestAtt;
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

    public String toString() {
        return head.toString();
    }

}
