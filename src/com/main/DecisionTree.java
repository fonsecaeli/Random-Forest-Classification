package com.main;

import java.util.*;

public class DecisionTree {

    private Node head;
    private final int ATTRIBUTE_SAMPLE_SIZE;
    public static final String ERROR_MESSAGE = "Error, Record cannot be classified";


    /*public DecisionTree(DataSet data) {
        head = new Node("HEAD_NODE");
        grow(data, head);
    }
    */

    public DecisionTree(DataSet data, int attributeSampleSize) {
        ATTRIBUTE_SAMPLE_SIZE = attributeSampleSize;
        head = new Node("HEAD_NODE");
        grow(data, head);
    }

    //TODO: make sure we don't check attributes that have already been split on
    private void grow(DataSet data, Node node) {
        Attribute toSplitOn = bestSplit(data);
        List<Record> records = data.getRecords();

        //IF ONLY 1 RECORD IS IN DATA, toSplitOn WILL BE NULL BECAUSE THERE WOULD BE NO INFORMATION GAIN FROM SPLITTING
        if(toSplitOn == null) {
            if(!records.isEmpty()) {
                node.setDecision(records.get(0).getClassificationValue(data));
            }
        } else {

            Map<String, DataSet> dataSets = DataSet.splitData(data, toSplitOn);

            node.setAttribute(data, toSplitOn);
            List<String> keys = toSplitOn.getValues();

            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                Node nextNode = node.getChild(key);
                DataSet dataSetForNextNode = dataSets.get(key);
                grow(dataSetForNextNode, nextNode);
            }

        }
    }

    //helper method for the growTree method, will split the data on the best attribute and return that attribute so
    //that the tree can be grown correctly
    private Attribute bestSplit(DataSet data) {
        List<Attribute> tempAttributes = data.getAttributes();
        List<Attribute> randomAttributes = randomSample(tempAttributes);
        Attribute bestAtt = randomAttributes.get(0);
        double bestGain = 0.0;

        for (int i = 0; i < randomAttributes.size(); i++) {
            Attribute currentAttribute = randomAttributes.get(i);
            //System.out.println(currentAttribute);
            double currentGain = Entropy.gain(data, currentAttribute);
            if (currentGain > bestGain) {
                bestAtt = currentAttribute;
                bestGain = currentGain;
            }
        }

        if (bestGain == 0.0) {
            return null;
        } else {
            return bestAtt;
        }
    }

    private List<Attribute> randomSample(List<Attribute> atts) {
        Random randomGenerator = new Random();
        List<Attribute> randomSample = new ArrayList<>();
        for (int i = 0; i < ATTRIBUTE_SAMPLE_SIZE; i++) {
            /*need the -1 because we dont want to count the classificaiton value for
            this random splitting*/
            Attribute toBeAdded = atts.get(randomGenerator.nextInt(atts.size() - 1));
            //makes sure we don't get any duplicates
            if (!randomSample.contains(toBeAdded)) {
                randomSample.add(toBeAdded);
            } else {
                i--;
            }
        }
        return randomSample;
    }

    /*
    TODO interesting thing happens in the method when combine with RandomForest Algorithm
    it is possible to have a valid record but reach a place in a tree where
    you can no longer progress down the tree.  need to figure out how we want to handle
    this issue
    */
    public String query(Record r) {

        Node currentNode = head;
        while (true) {
            String classification = currentNode.getDecision();
            if (classification != null) {
                return classification;
            }
            Attribute currentAtt = currentNode.getAttribute();
            String value = r.getValue(currentAtt);
            Set<String> keys = currentNode.getKeys();
            if (keys.contains(value)) {
                currentNode = currentNode.getChild(value);
            } else {
                //System.out.println(keys);
                //System.out.println(value);
                //System.out.println();
                return ERROR_MESSAGE;//"Error test record: " + r + "cannot be classified because it does not match the training data";
            }
        }
    }

    //TODO: we really should change how we get the classification of a record, it is just ugly right now
    public boolean testRecord(Record r, DataSet data) {
        String guess = this.query(r);
        //System.out.println(guess);
        //System.out.println(r.getClassificationValue(data));
        if (guess.equals(r.getClassificationValue(data))) {
            return true;
        }
        return false;
    }
    
    public Node getHeadNode(){
        return head;
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
