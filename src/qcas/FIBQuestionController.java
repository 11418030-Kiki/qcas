/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import qcas.backEnd.Test;

/**
 * FXML Controller class
 *
 * @author aayush
 */
public class FIBQuestionController implements Initializable {

    Test testobject = new Test();
    @FXML
    private Label lblQuestion;
    @FXML
    private TextField txtAnswer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void handlePreviousButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
    }

    @FXML
    protected void handleNextButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException {
        String userAnswer = txtAnswer.toString(); //.g;
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        int currentQuestion = testobject.getCurrentQuestionNumber();
        String correctAnswer = testobject.getQuestionList().get(currentQuestion).getAnswerString();
        testobject.setCurrentQuestionNumber(currentQuestion + 1); //.getQuestionList().get(currectQuestion).getAnswer();
        Parent root1 = null;
        FXMLLoader fxmlLoader = null;
        if (userAnswer == "") {
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + 1);
        } else if (correctAnswer.equals(userAnswer)) {
            testobject.setCorrectQuestions(testobject.getCorrectQuestions() + 1);
        } else if (!correctAnswer.equals(userAnswer)) {
            testobject.setIncorrectQuestions(testobject.getIncorrectQuestions() + 1);
        }

        if (currentQuestion == (testobject.getNumberOfQuestions() - 1)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("EndTest.fxml"));
            root1 = (Parent) fxmlLoader.load();
            EndTestController controller = fxmlLoader.<EndTestController>getController();
            controller.initData(testobject);
        } else {

            String nextQuestionType = testobject.getQuestionList().get(currentQuestion + 1).getQuestionType();

            if (nextQuestionType.equals("MC")) {
                fxmlLoader = new FXMLLoader(getClass().getResource("MCQuestion.fxml"));
                root1 = (Parent) fxmlLoader.load();
                MCQuestionController controller = fxmlLoader.<MCQuestionController>getController();
                controller.initData(testobject);
            } else if (nextQuestionType.equals("MA")) {
                fxmlLoader = new FXMLLoader(getClass().getResource("MAQuestion.fxml"));
                root1 = (Parent) fxmlLoader.load();
                MAQuestionController controller = fxmlLoader.<MAQuestionController>getController();
                controller.initData(testobject);
            } else if (nextQuestionType.equals("TF")) {
                fxmlLoader = new FXMLLoader(getClass().getResource("TFQuestion.fxml"));
                root1 = (Parent) fxmlLoader.load();
                TFQuestionController controller = fxmlLoader.<TFQuestionController>getController();
                controller.initData(testobject);
            } else if (nextQuestionType.equals("FIB")) {
                fxmlLoader = new FXMLLoader(getClass().getResource("FIBQuestion.fxml"));
                root1 = (Parent) fxmlLoader.load();
                FIBQuestionController controller = fxmlLoader.<FIBQuestionController>getController();
                controller.initData(testobject);
            }

        }
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    protected void handleEndTestButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root1 = null;
        FXMLLoader fxmlLoader = null;
        fxmlLoader = new FXMLLoader(getClass().getResource("EndTest.fxml"));
        root1 = (Parent) fxmlLoader.load();
        EndTestController controller = fxmlLoader.<EndTestController>getController();
        controller.initData(testobject);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    public void initData(Test test) {
        testobject = test;
        lblQuestion.setText(((testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber())).getQuestion());
    }

}
