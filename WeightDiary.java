package weightdiary;

/*
 * Copyright 2015 Patrick Osgood
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weightdiary.Domain.WeightEntry;
import weightdiary.Interfaces.IWeightRecord;
import weightdiary.Util.ReadWriteWeight;

/**
 *
 * @author patrick
 */
public class WeightDiary extends Application {
    RandomAccessFile raf;
    //Constructor
   public WeightDiary() throws FileNotFoundException, IOException{
        raf = new RandomAccessFile("data.dat", "rw");       
        }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();    
    }
    
    public RandomAccessFile getRAFFile(){
        return raf;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}
