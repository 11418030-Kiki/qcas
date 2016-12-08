/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;

/**
 * FXML Controller class
 *
 * @author RRB
 */
public class InstructorDashboardController implements Initializable {

    @FXML
    private ImageView imgHome;
    @FXML
    private BarChart<?, ?> StudentsPerformance;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayChart();
        /*
        imgHome.setOnMouseClicked(event -> {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                FXMLHomeController controller = fxmlLoader.<FXMLHomeController>getController();
                stage.setTitle("Welcome to QCAS");
                stage.setScene(new Scene(root, 630, 510));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //imgLogout .setStyle("-fx-image: url(\""+ IMAGE2 + "\");");
        });
*/
    }
    /**
     * import question
     * @param event 
     */
    @FXML
    private void importQuestions(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuestionUploader.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage.setTitle("Upload New Questions");
            stage.setScene(new Scene(root, 630, 510));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * home button
     * @param event 
     */
    @FXML
    private void homeApp(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage.setTitle("Welcome to QCAS");
            stage.setScene(new Scene(root, 630, 510));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/**
 * logout button functionality
 * @param event 
 */
    @FXML
    private void logoutApp(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            FXMLHomeController controller = fxmlLoader.<FXMLHomeController>getController();
            stage.setTitle("Welcome to QCAS");
            stage.setScene(new Scene(root, 630, 510));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void loadStudentReports(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentReports.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage.setTitle("Student Performance Reports");
            stage.setScene(new Scene(root, 720, 690));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void displayChart() {
        try {
            double[] fromData = new double[8];

            DatabaseManager dbManager = new DatabaseManager();
            double result[]=dbManager.getInstructorGraph();
            
            fromData[0]=result[0];fromData[1]=result[1];fromData[2]=result[2];
            
            XYChart.Series set1= new XYChart.Series<>();
            set1.setName("Number of test");
            XYChart.Series set2= new XYChart.Series<>();
            set2.setName("Average Score");
            XYChart.Series set3= new XYChart.Series<>();
            set3.setName("Hard level Avg Score");
            set1.getData().add(new XYChart.Data("",fromData[0]));
            set2.getData().add(new XYChart.Data("",fromData[1]));
            set3.getData().add(new XYChart.Data("",fromData[2]));

            StudentsPerformance.getData().addAll(set1);
            StudentsPerformance.getData().addAll(set2);
            StudentsPerformance.getData().addAll(set3);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InstructorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(InstructorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InstructorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
