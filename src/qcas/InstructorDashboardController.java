/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RRB
 */
public class InstructorDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void importQuestions(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuestionUploader.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage.setTitle("Welcome to QCAS");
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
    
    protected BarChart<String, Number> createChart() {
        final String[] years = {"2007", "2008", "2009"};
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis,"$",null));
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        // setup chart
        bc.setTitle("Advanced Bar Chart");
        xAxis.setLabel("Year");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(years)));
        yAxis.setLabel("Price");
        // add starting data
        XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
        series1.setName("Data Series 1");
        XYChart.Series<String,Number> series2 = new XYChart.Series<String,Number>();
        series2.setName("Data Series 2");
        XYChart.Series<String,Number> series3 = new XYChart.Series<String,Number>();
        series3.setName("Data Series 3");
        // create sample data
        series1.getData().add(new XYChart.Data<String,Number>(years[0], 567));
        series1.getData().add(new XYChart.Data<String,Number>(years[1], 1292));
        series1.getData().add(new XYChart.Data<String,Number>(years[2], 2180));
        series2.getData().add(new XYChart.Data<String,Number>(years[0], 956));
        series2.getData().add(new XYChart.Data<String,Number>(years[1], 1665));
        series2.getData().add(new XYChart.Data<String,Number>(years[2], 2450));
        series3.getData().add(new XYChart.Data<String,Number>(years[0], 800));
        series3.getData().add(new XYChart.Data<String,Number>(years[1], 1000));
        series3.getData().add(new XYChart.Data<String,Number>(years[2], 2800));
        bc.getData().add(series1);
        bc.getData().add(series2);
        bc.getData().add(series3);
        return bc;
    }

}
