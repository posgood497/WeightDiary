/*
 *Copyright 2015 Patrick Osgood
 */

package weightdiary;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import weightdiary.Domain.WeightEntry;
import weightdiary.Interfaces.IWeightRecord;
import weightdiary.Util.ReadWriteWeight;


/**
 * FXML Controller class
 *
 * @author patrick
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private Text feedbackTxt;
    @FXML
    private TextField weightTF;
    private WeightDiary weightdiary;
    private RandomAccessFile raf;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            raf = new RandomAccessFile("data.dat", "rw");
            raf.seek(0);
            setReadout(raf);
        }    
        catch(IOException e){
            System.out.println("Can not access file!");
                }
        
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try{
        ReadWriteWeight.write(raf, new WeightEntry(Double.parseDouble(weightTF.getText())));
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter decimal number only.", "Input Error", JOptionPane.ERROR_MESSAGE);
            weightTF.setText("");
        }
        setReadout(raf);
        weightTF.setText("");
    }
    
    public void setReadout(RandomAccessFile raf) throws IOException{
        if ((int)raf.length() / IWeightRecord.LENGTH_OF_RECORD > 1){
            WeightEntry firstWeight = new WeightEntry(ReadWriteWeight.read(raf, IWeightRecord.START_OF_FILE));
            WeightEntry lastWeight = new WeightEntry(ReadWriteWeight.read(raf, (int)raf.length() - IWeightRecord.LENGTH_OF_RECORD));
                
            if (lastWeight.getWeight() - firstWeight.getWeight() == 0.0){
                feedbackTxt.setText("You have not lost or gained weight!");
            }
            else if (lastWeight.getWeight() - firstWeight.getWeight() > 0.0){
                double temp = lastWeight.getWeight() - firstWeight.getWeight();
                feedbackTxt.setText("You gained  " + String.format("%.1f", temp) + "  pounds so far!");
            }
            else if (lastWeight.getWeight() - firstWeight.getWeight() < 0.0){
                double temp = lastWeight.getWeight() - firstWeight.getWeight();
                feedbackTxt.setText("You lost  " + String.format("%.1f", Math.abs(temp)) + "  pounds so far!");
            }
        }
    }
    
}
