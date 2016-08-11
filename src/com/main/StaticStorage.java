
package com.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StaticStorage {
    public static final int NUM_TREES=50;
    public static final double TUNING_FACTOR=0.7;
    
    private static List<DataSet> dataSets = new ArrayList<>(); 
    private static List<RandomForest> randomForests = new ArrayList<>();
    private static int indexOfCurrentDataSet=0;
    private static int indexOfCurrentTree=0;
    
    public static void newData(File file){
        DataSet ds = ImportData.importData(file);
        RandomForest forest = new RandomForest(ds, NUM_TREES, TUNING_FACTOR);
        
        dataSets.add(ds);
        randomForests.add(forest);
        setIndex(dataSets.size()-1);
    }
    
    public static int getIndexOfCurrentTree(){
        return indexOfCurrentTree;
    }
    
    public static int getIndexOfCurrentDataSet(){
        return indexOfCurrentDataSet;
    }
    
    public static int getIndexOfCurrentRandomForest(){
        return getIndexOfCurrentDataSet();
    }
    
    public static void setIndex(int i){
        if(i<0)i=0;
        else if(i>=dataSets.size())i=dataSets.size()-1;
        indexOfCurrentDataSet = i;
        indexOfCurrentTree=0;
    }
    
    public static DataSet getCurrentDataSet(){
        if(dataSets.isEmpty()) return null;
        return dataSets.get(indexOfCurrentDataSet);
    }
    
    public static RandomForest getCurrentRandomForest(){
        if(randomForests.isEmpty()) return null;
        return randomForests.get(indexOfCurrentDataSet);
    }
    
    public static DecisionTree getCurrentTree(){
        if(randomForests.isEmpty()) return null;
        return randomForests.get(indexOfCurrentDataSet).getTrees()[indexOfCurrentTree];
    }
    
    public static int numDataSets(){
        return dataSets.size();
    }
    
    public static int numTrees(){
        return getCurrentRandomForest().getTrees().length;
    }
    
    public static List<DataSet> getDataSets(){
        return dataSets;
    }
    
    public static List<RandomForest> getRandomForests(){
        return randomForests;
    }
    
    public static void incrementCurrentTree(){
        if (getCurrentRandomForest()!=null){
            indexOfCurrentTree++;
            int currentMaxIndex=getCurrentRandomForest().getTrees().length-1;
            if(indexOfCurrentTree>currentMaxIndex)indexOfCurrentTree=0;
        }
    }

    public static void decrementCurrentTree(){
        if (getCurrentRandomForest()!=null){
            indexOfCurrentTree--;
            if (indexOfCurrentTree<0) indexOfCurrentTree=getCurrentRandomForest().getTrees().length-1;
        }
    }
}
