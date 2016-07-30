package com.main;

import java.util.*;

public class DecisionTree {

    //head node for the decision tree
    private Node head;
    private final int ATTRIBUTE_SAMPLE_SIZE;


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
            List<Attribute> tempAttributes = data.getAttributes();
            List<Attribute> randomAttributes = randomSample(tempAttributes);
            Attribute bestAtt=randomAttributes.get(0);
            double bestGain=0.0;
            
            for(int i=0; i<randomAttributes.size()-1; i++){
                Attribute currentAttribute = randomAttributes.get(i);
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

    public List<Attribute> randomSample(List<Attribute> atts) {
        Random randomGenerator = new Random();
        List<Attribute> randomSample = new ArrayList<>();
        for(int i = 0; i < ATTRIBUTE_SAMPLE_SIZE; i++) {
            /*need the -1 because we dont want to count the classificaiton value for
            this random splitting*/
            Attribute toBeAdded = atts.get(randomGenerator.nextInt(atts.size()-1));
            //makes sure we don't get any duplicates
            if(!randomSample.contains(toBeAdded)) {
                randomSample.add(toBeAdded);
            }
            else {
                i--;
            }
        }
        return randomSample;
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

    public String toString(){
	String s="";
	toStringRecursive(s, 0, head, true);
        return s;
    }

    public String toStringRecursive(String s, int deep, Node n, boolean atEnd){
	    s+=getTabs(deep, atEnd)+n.toString()+"\n";
        System.out.print(getTabs(deep,atEnd)+n.toString()+"\n");
	    if(n.getAttribute()!=null){
		    Set<String> keys = n.getKeys();
            List<String> listOfKeys = new ArrayList<>();
		for(String key : keys){
                    listOfKeys.add(key);
		}
        for(int i=0; i<listOfKeys.size(); i++){
            String key = listOfKeys.get(i);
            toStringRecursive(s, deep+1, n.getChild(key), i==listOfKeys.size()-1);
        }
                        /*or this, doesn't really work because the node doesn't necessarily have a child for each value in attributes
                int i=0;
                for(String key : keys){
			toStringRecursive(s, deep+1, n.getChild(key), i==n.getAttribute().getValues().size()-1);
                        i++;
		}
                        
                */
	}	
        return s;
    }


    public String getTabs(int deep, boolean atEnd){
	String s="\u001B[1m";
	for(int i=0;i<deep;i++)
		s+=".\t";
        if(atEnd){
            s+="\\";
        } else {
            s+="|";
        }
        s+="\u001B[0m";
	return s;
    }

}
