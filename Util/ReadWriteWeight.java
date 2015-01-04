/*
 * Copyright 2015 Patrick Osgood
 */

package weightdiary.Util;

import java.io.IOException;
import java.io.RandomAccessFile;
import weightdiary.Domain.WeightEntry;
import weightdiary.Interfaces.IWeightRecord;

/**
 * ReadWriteWeight class reads and writes WeightEntry objects to 
 * binary data file. It writes date as a long integer (milliseconds)
 * and double number (weight in pounds) pairing.
 * 
 * @author patrick
 */
public class ReadWriteWeight {
    
    //Appends new record at end of file
    public static void write(RandomAccessFile raf, WeightEntry weightEntry) throws IOException{
        raf.seek(raf.length());
        raf.writeLong(weightEntry.getDate());
        raf.writeDouble(weightEntry.getWeight());
    }
    
    //Reads weight from record. WeightDiary project does not need date weight was
    //recorded, so it is not read.
    public static double read(RandomAccessFile raf, int location) throws IOException{
        raf.seek(location + IWeightRecord.LENGTH_OF_DATE);
        double weightEntry = raf.readDouble();
        
        return weightEntry;
    }

}
