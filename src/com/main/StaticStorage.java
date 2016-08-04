
package com.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StaticStorage {
    private static List<DataSet> dataSets = new ArrayList<>(); 
    private static List<RandomForest> randomForests = new ArrayList<>();
    private static int index=-1;
    
    public static void newData(File file){
        DataSet ds = ImportData.importData(file);
        RandomForest forest = new RandomForest(ds, 1, 1);
        
        //System.out.println(ds);
        //System.out.println(forest);
        index++;
        dataSets.add(ds);
        randomForests.add(forest);
    }
    
    public static void setIndex(int i){
        index = i;
    }
    
    public static DataSet getCurrentDataSet(){
        if(dataSets.isEmpty()) return null;
        return dataSets.get(index);
    }
    
    public static RandomForest getCurrentRandomForest(){
        if(dataSets.isEmpty()) return null;
        return randomForests.get(index);
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
}
