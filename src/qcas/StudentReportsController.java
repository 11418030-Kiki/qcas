/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;

/**
 * FXML Controller class
 *
 * @author RRB
 */
public class StudentReportsController implements Initializable {

    @FXML
    private BarChart numberTestsBar;

    @FXML
    private BarChart difficultyLevelBar;

    @FXML
    private PieChart averageScorePie;

    @FXML
    private PieChart passFailPie;

    @FXML
    private ComboBox<String> comboReportPeriod;

    @FXML
    private Text difficulty1;

    @FXML
    private Text difficulty2;

    @FXML
    private Text difficulty3;

    @FXML
    private Text difficulty4;

    @FXML
    private Text passText;

    @FXML
    private Text failText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void displayChart1(String period) {
        try {
            double[] fromData = new double[8];

            DatabaseManager dbManager = new DatabaseManager();
            double result[] = dbManager.getStudentNumberGraph(period);

            if (period.equals("LastQuarter")) {
                fromData[0] = result[0];
            } else if (period.equals("LastMonth")) {
                fromData[0] = result[0];
            } else if (period.equals("LastYear")) {
                fromData[0] = result[0];
            }
            numberTestsBar.getData().clear();
            XYChart.Series set1 = new XYChart.Series<>();
            set1.getData().add(new XYChart.Data("Number of Tests = " + fromData[0], fromData[0]));
            numberTestsBar.getData().addAll(set1);

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

    public void displayChart2(String period) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            double result[] = dbManager.getStudentAvgGraph(period);

            difficultyLevelBar.getData().clear();
            XYChart.Series set1 = new XYChart.Series<>();
            set1.setName("Average Score by Period");
            set1.getData().add(new XYChart.Data("Average Score = " + result[0], result[0]));
            difficultyLevelBar.getData().addAll(set1);

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

    public void displayChart3(String period) {
        try {

            DatabaseManager dbManager = new DatabaseManager();
            double result[] = dbManager.getStudentDifficultyGraph(period);

            PieChart.Data slice1 = new PieChart.Data("Easy", result[0]);
            difficulty1.setText("Easy = " + result[0]);
            PieChart.Data slice2 = new PieChart.Data("Medium", result[1]);
            difficulty2.setText("Medium = " + result[1]);
            PieChart.Data slice3 = new PieChart.Data("Hard", result[2]);
            difficulty3.setText("Hard = " + result[2]);
            PieChart.Data slice4 = new PieChart.Data("Mixed", result[3]);
            difficulty4.setText("Mixed = " + result[3]);
            averageScorePie.getData().clear();
            averageScorePie.getData().add(slice1);
            averageScorePie.getData().add(slice2);
            averageScorePie.getData().add(slice3);
            averageScorePie.getData().add(slice4);

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

    public void displayChart4(String period) {
        try {

            DatabaseManager dbManager = new DatabaseManager();
            double result[] = dbManager.getPassFailPiechart(period);

            PieChart.Data slice1 = new PieChart.Data("Passed=" + result[0], result[0]);
            //passText.setText("Passed = "+result[0]);
            //passText.setText("Failed = "+result[1]);
            PieChart.Data slice2 = new PieChart.Data("Failed=" + result[1], result[1]);
            passFailPie.getData().clear();
            passFailPie.getData().add(slice1);
            passFailPie.getData().add(slice2);

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

    @FXML
    private void displayGraphAction(ActionEvent event) {
        displayChart1(comboReportPeriod.getValue());
        displayChart2(comboReportPeriod.getValue());
        displayChart3(comboReportPeriod.getValue());
        displayChart4(comboReportPeriod.getValue());
    }

    @FXML
    private void homeApp(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InstructorDashboard.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage.setTitle("Welcome Instructor Dashboard");
            stage.setScene(new Scene(root, 630, 510));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

}
