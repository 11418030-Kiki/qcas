/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;
import qcas.backEnd.Test;

/**
 * FXML Controller class
 *
 * @author aayush
 */
public class EndTestController implements Initializable {

    Test testobject = new Test();

    @FXML
    private BarChart<?, ?> StudentResult;

    @FXML
    private Label lblResult;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String s = "here";

    }

    /**
     * go to student dashboard page
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void handleTakeTestButtonAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GenTest.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        GenTestController controller = fxmlLoader.<GenTestController>getController();

        controller.initData(testobject.getUserData());
        stage.setTitle("Take Test");
        stage.setScene(new Scene(root, 630, 510));
        stage.show();
    }

    /**
     * initialize end test page calculate results
     *
     * @param test
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ParseException
     */
    @FXML
    public void initData(Test test) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        testobject = test;
        int currentQuestion = testobject.getCurrentQuestionNumber();
        int totalQuestion = testobject.getNumberOfQuestions();

        //  int questionsLeft = totalQuestion - testobject.getCorrectQuestions() - testobject.getIncorrectQuestions() - testobject.getUnansweredQuestions();
        int totalAttempted = testobject.getCorrectQuestions() + testobject.getIncorrectQuestions() + testobject.getUnansweredQuestions();
        int totalLeft = totalQuestion - totalAttempted;
        testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + totalLeft);
        /*
        if (currentQuestion < totalQuestion) {
            int questionsLeft = totalQuestion - testobject.getCorrectQuestions() - testobject.getIncorrectQuestions() - testobject.getUnansweredQuestions();
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + questionsLeft);
        } else if (currentQuestion == totalQuestion) {
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + 1);
        }
         */
        int Score = testobject.getCorrectQuestions();
        testobject.setScore(testobject.getCorrectQuestions());
        double scaledScore = testobject.getCorrectQuestions() * 100.0 / testobject.getNumberOfQuestions();
        testobject.setScaledScore(scaledScore);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        //System.out.println(dtf.format(localDate));
        testobject.setTestDate(dtf.format(localDate).toString());

        if (testobject.getScaledScore() >= 60.00) {
            testobject.setResult("Pass");
        } else {
            testobject.setResult("Fail");
        }
        testobject.saveTestDetails(testobject);
        this.displayChart();
        String resultAnalysis = "Result Analysis\nTotal Questions: " + testobject.getNumberOfQuestions() + "\nYou got " + testobject.getCorrectQuestions() + " questions right.\nYou " + testobject.getResult() + "ed the test!!";
        lblResult.setText(resultAnalysis);
    }

    /**
     * display charts according to score result
     */
    public void displayChart() {
        double[] fromData = new double[8];
        //  testobject.getCorrectQuestions();

        fromData[0] = testobject.getCorrectQuestions();
        fromData[1] = testobject.getIncorrectQuestions();
        fromData[2] = testobject.getUnansweredQuestions();

        XYChart.Series set1 = new XYChart.Series<>();
        XYChart.Series set2 = new XYChart.Series<>();
        XYChart.Series set3 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Correct", fromData[0]));
        set2.getData().add(new XYChart.Data("Incorrect", fromData[1]));
        set3.getData().add(new XYChart.Data("Unanswered", fromData[2]));

        StudentResult.getData().addAll(set1);
        StudentResult.getData().addAll(set2);
        StudentResult.getData().addAll(set3);

    }

    /*
    logout button
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

}
