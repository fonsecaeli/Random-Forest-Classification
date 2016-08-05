
package com.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StaticStorage {
    private static List<DataSet> dataSets = new ArrayList<>(); 
    private static List<RandomForest> randomForests = new ArrayList<>();
    private static int indexOfCurrentDataSet=-1;
    private static int indexOfCurrentTree=-1;
    
    public static void newData(File file){
        DataSet ds = ImportData.importData(file);
        RandomForest forest = new RandomForest(ds, 2, 0.7);
        
        //System.out.println(ds);
        //System.out.println(forest);
        dataSets.add(ds);
        randomForests.add(forest);
        setIndex(indexOfCurrentDataSet+1);
    }
    
    public static void setIndex(int i){
        indexOfCurrentDataSet = i;
        indexOfCurrentTree=0;
    }
    
    public static DataSet getCurrentDataSet(){
        if(dataSets.isEmpty()) return null;
        return dataSets.get(indexOfCurrentDataSet);
    }
    
    public static RandomForest getCurrentRandomForest(){
        if(dataSets.isEmpty()) return null;
        return randomForests.get(indexOfCurrentDataSet);
    }
    
    public static DecisionTree getCurrentTree(){
        if(dataSets.isEmpty()) return null;
        return randomForests.get(indexOfCurrentDataSet).getTrees()[indexOfCurrentTree];
    }
    
    public static int numDataSets(){
        return dataSets.size();
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
            indexOfCurrentTree%=getCurrentRandomForest().getTrees().length;
        }
    }

    public static void decrementCurrentTree(){
        if (getCurrentRandomForest()!=null){
            indexOfCurrentTree--;
            if (indexOfCurrentTree<0) indexOfCurrentTree=getCurrentRandomForest().getTrees().length-1;
        }
    }
}
