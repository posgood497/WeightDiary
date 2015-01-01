/*
 * Copyright 2015 Patrick Osgood
 */

package weightdiary.Domain;

/**
 * 
 * @author patrick
 */
public class WeightEntry {
    /*
    * THIS CLASS IS IMMUTABLE!
    */
    private final long date;
    private final double weight;
    
   public WeightEntry(){
        date = System.currentTimeMillis();
        weight = 0.0;
    }
   
   public WeightEntry(double weight){
        date = System.currentTimeMillis();
        this.weight = weight;
    }
   
   public double getWeight(){
       return weight;
   }
   
   public long getDate(){
       return date;
   }
}
