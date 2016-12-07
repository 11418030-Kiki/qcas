/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Shikha
 */
public class InstructorPageController implements Initializable {

    @FXML
    private BarChart<String, Number> StudentsPerformance;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //displayChart();
        
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));

        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);

    }

    public void displayChart() {
        double[] fromData = new double[8];
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
        fromData[0] = 3.6;
        fromData[1] = 5.7;
        fromData[2] = 4.3;

         XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
        series1.setName("Number of test");
        XYChart.Series<String,Number> series2 = new XYChart.Series<String,Number>();
        series2.setName("Average Score");
        XYChart.Series<String,Number> series3 = new XYChart.Series<String,Number>();
        series3.setName("Hard level Ave Score");
//        XYChart.Series set2 = new XYChart.Series<>();
//        XYChart.Series set3 = new XYChart.Series<>();
//        XYChart.Series set4 = new XYChart.Series<>();
        //for(int i =0;i<fromData.length;i++){
        //    if(i == 0){
        series1.getData().add(new XYChart.Data("", fromData[0]));
        //    }else if(i==1){
        series2.getData().add(new XYChart.Data("", fromData[1]));
        //    }else if(i==2){
        series3.getData().add(new XYChart.Data("", fromData[2]));
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
        StudentsPerformance.getData().addAll(series1);
        StudentsPerformance.getData().addAll(series2);
        StudentsPerformance.getData().addAll(series3);
        //dashboardChart.getData().addAll(set4);

    }
    
        public void setPersonData() {
        // Count the number of people having their birthday in a specific month.
        
        List<String> persons = new ArrayList<>();
        persons.add("abc");
        int[] monthCounter = new int[12];
        for (String p : persons) {
            int month = 5 - 1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }

}
