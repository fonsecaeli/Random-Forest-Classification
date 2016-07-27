package Main;

// Author: EliFo
// Description: Class that represents a decision tree, provides functionality to grow one
// 7/26/2016

import java.util.List;

public class DecisionTree {

    //this will be the recursive method we need
    public void grow(List<Record> data) {

    }

    //helper method for the growTree method, will split the data on the best attribute and return that attribute so
    //that the tree can be grown correctly
    public Attribute bestSplit(List<Record> data) {
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
