/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Shikha
 */
public class InstructorPageController implements Initializable {
  @FXML
    private BarChart<?, ?> StudentsPerformance;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      displayChart();
    }    
    public void displayChart(){
        double[]fromData=new double[8];
        /*
        if(timePeriod=="M"){
            fromData=instructorDAO.getMonth();
        }else if(timePeriod=="Q"){
            fromData=instructorDAO.getQuater();
        }else if(timePeriod=="Y"){
            fromData=instructorDAO.getYear();
        }*/
        
        //DatabaseManager dbManager = new DatabaseManager();
        //dbManager.getDateDetails();
        
        fromData[0]=3.6;fromData[1]=5.7;fromData[2]=4.3;
        
        XYChart.Series set1= new XYChart.Series<>();
        XYChart.Series set2= new XYChart.Series<>();
        XYChart.Series set3= new XYChart.Series<>();
        XYChart.Series set4= new XYChart.Series<>();
        //for(int i =0;i<fromData.length;i++){
        //    if(i == 0){
                set1.getData().add(new XYChart.Data("Number of test",fromData[0]));
        //    }else if(i==1){
                set2.getData().add(new XYChart.Data("Average Score",fromData[1]));
        //    }else if(i==2){
                set3.getData().add(new XYChart.Data("Hard level Ave Score",fromData[2]));
        /*    }else if(i==3){
                set3.getData().add(new XYChart.Data("Medium level Ave Score",fromData[i]));
            }else if(i==4){
                set3.getData().add(new XYChart.Data("Easy level Ave Score",fromData[i]));
            }else if(i==5){
                set3.getData().add(new XYChart.Data("Mixed level Ave Score",fromData[i]));
            }else if(i==6){
                set4.getData().add(new XYChart.Data("Pass Student",fromData[i]));
            }else if(i==7){
                set4.getData().add(new XYChart.Data("Fail Student",fromData[i]));
            }
        }*/
        StudentsPerformance.getData().addAll(set1);
        StudentsPerformance.getData().addAll(set2);
        StudentsPerformance.getData().addAll(set3);
        //dashboardChart.getData().addAll(set4);
        
    }
    
    
    
}
