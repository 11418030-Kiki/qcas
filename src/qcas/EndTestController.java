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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void handleTakeTestButtonAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GenTest.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        GenTestController controller = fxmlLoader.<GenTestController>getController();

        controller.initData(testobject.getUserData());
        stage.setTitle("Welcome to Student Dashboard");
        stage.setScene(new Scene(root, 630, 510));
        stage.show();

    }

    @FXML
    public void initData(Test test) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        testobject = test;
        int currentQuestion = testobject.getCurrentQuestionNumber();
        int totalQuestion = testobject.getNumberOfQuestions();
        if (currentQuestion < totalQuestion) {
            int questionsLeft = totalQuestion - testobject.getCorrectQuestions() - testobject.getIncorrectQuestions() - testobject.getUnansweredQuestions();
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + questionsLeft);
        } else if (currentQuestion == totalQuestion) {
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + 1);
        }

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
    }

}
