/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weightdiary.Util;

import java.io.IOException;
import java.io.RandomAccessFile;
import weightdiary.Domain.WeightEntry;
import weightdiary.Interfaces.IWeightRecord;

/**
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
    
    //Can read any weight record from anywhere in file
    public static double read(RandomAccessFile raf, int location) throws IOException{
        raf.seek(location + IWeightRecord.LENGTH_OF_DATE);
        double weightEntry = raf.readDouble();
        
        return weightEntry;
    }
    
    
}
