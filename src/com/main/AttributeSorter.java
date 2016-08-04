package com.main;

import java.util.Comparator;

public class AttributeSorter implements Comparator<Record> {

    private Attribute att;

    public AttributeSorter(Attribute att) {
        this.att = att;

    }

    /**
     * compares the records by the value of a specified attribute that must be continuous
     * can only be used before this attribute has been bucketed and swapped for a ContinuousAttribute
     * Object
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Record o1, Record o2) {
        try {
            return (int)(Double.parseDouble(o1.getValue(att))-Double.parseDouble(o2.getValue(att)));
        }
        catch(NumberFormatException e) {
            throw new Error("cannot compare values of a discrete attribute");
        }
    }
}
