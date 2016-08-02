package com.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContinuousAttribute extends Attribute {

    private double cutOff;
    private static final String HIGH = "High";
    private static final String LOW = "Low";
    private static final ArrayList<String> values = new ArrayList<>(Arrays.asList(HIGH, LOW));

    public ContinuousAttribute(String name, double cutOff) {
        super(name);
        this.cutOff = cutOff;
    }

    public double getCutOff() {
        return cutOff;
    }

    @Override
    public List<String> getValues() {
        return values;
    }

    public String getBucket(String str) {
        double n = Double.parseDouble(str);
        //returns low if the value is below or equal to the cutOff
        //and returns high if the value is higher than the cutOff
        if(n <= this.cutOff) {
            return LOW;
        }
        else {
            return HIGH;
        }
    }

}