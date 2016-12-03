/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;
//import qcas.backEnd.DatabaseManager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;

/**
 * FXML Controller class
 *
 * @author Pansul47
 */
public class ProfController implements Initializable {

    @FXML
    private BarChart<?, ?> StudentsPerformance;
    @FXML
    private PieChart NumberOfStudents;
    @FXML
    private Button ImportTest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            displayChart();
        }    

    @FXML
    private void ImportAskPage(ActionEvent event) throws IOException {
            Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("graphs/askImportfile.fxml"));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
                    
        }
        catch(IOException e){
            e.printStackTrace();
        }
        //graphs.goToWindow(event, "askImportfile");
    }
    
    
    
    public void displayChart() {
        try {
            int fromData[] = new int[3];
            /*
            if(timePeriod=="M"){
            fromData=instructorDAO.getMonth();
            }else if(timePeriod=="Q"){
            fromData=instructorDAO.getQuater();
            }else if(timePeriod=="Y"){
            fromData=instructorDAO.getYear();
            }*/
            
            DatabaseManager dbManager = new DatabaseManager();
            fromData  = dbManager.getDateDetails();
            
            XYChart.Series set1= new XYChart.Series<>();
            XYChart.Series set2= new XYChart.Series<>();
            XYChart.Series set3= new XYChart.Series<>();
            //XYChart.Series set4= new XYChart.Series<>();
            //for(int i =0;i<fromData.length;i++){
            //    if(i == 0){
            set1.getData().add(new XYChart.Data("Last Month",fromData[0]));
            //    }else if(i==1){
            set2.getData().add(new XYChart.Data("Last Quater",fromData[1]));
            //    }else if(i==2){
            set3.getData().add(new XYChart.Data("Last Year",fromData[2]));
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProfController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProfController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProfController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        //Parent root = FXMLLoader.load(Graphs.class.getResource("askImportfile.fxml"));
        //gotosecondpage(event,"askImportfile");
    }
    
//    @FXML
//    public void gotosecondpage(ActionEvent actioning, String s){
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(Graphs.class.getResource(s));
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//    }

