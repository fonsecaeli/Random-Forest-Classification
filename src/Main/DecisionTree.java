package Main;

// Author: EliFo
// Description: Class that represents a decision tree, provides functionality to grow one
// 7/26/2016

import java.util.List;

public class DecisionTree {

    private DataSet dataSet;

    /*
     * used to hold the Attributes that can still be selected to split the data
     * i needed some way to keep track of what options where still left
     */
    private List<Attribute> toSelect;


    //head node for the decision tree
    private Node head;

    public DecisionTree(DataSet data) {
        this.dataSet = data;
    }

    //this will be the recursive method we need not sure exactly how this works yet
    public void grow(DataSet data, Node node) {
        //base case, once we reach maximum entropy
        if(Entropy.entropy(data) == 0) { //TODO: configure entropy to work with DataSet
            node.setDecision(data.get(0).getClassification()); //setting the Decision of the node to the
            //class shared between all the Records in the DataSet data
        }
        Attribute att = bestSplit(data);
        List<DataSet> newDataSets = splitDataSet(data, att);
        node.setCondition(att); //TODO: need setCondition() in Node
        List<Node> children = getChildren(node, newDataSets);
        //not sure if this for loop is a good idea
        for(int i = 0; i < newDataSets.size(); i++) {
            grow(newDataSets.get(i), children.get(i));
        }


    }

    //will create the children for the node based of the new data sets
    public List<Node> getChildren(Node node, List<DataSet> newDataSets) {{

    }

    //splits a data set into n different sets, splits on the specified attribute
    public List<DataSet> splitDataSet(DataSet data, Attribute att) {
        //TODO: needs to be finished off later
    }


    //helper method for the growTree method, will split the data on the best attribute and return that attribute so
    //that the tree can be grown correctly
    public Attribute bestSplit(DataSet data) {
            return null;
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

}
