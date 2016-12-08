/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    @FXML
    private Label lblHeading;
    @FXML
    private TextArea txtQuestion;

    @FXML
    // private Button btnNext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (testobject.getCurrentQuestionNumber() == (testobject.getNumberOfQuestions() - 2)) {
        }
    }

    /**
     * 
     * @param event
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws SQLException
     * @throws IllegalAccessException
     */
    @FXML
    protected void handlePreviousButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
    }

    /**
     * on next button handle
     * @param event
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws ParseException
     */
    @FXML
    protected void handleNextButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException, ParseException {
        String userAnswer = ((TextField) txtAnswer).getText();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        int currentQuestion = testobject.getCurrentQuestionNumber();
        String correctAnswer = testobject.getQuestionList().get(currentQuestion).getAnswerString();
        testobject.setCurrentQuestionNumber(currentQuestion + 1);
        Parent root1 = null;
        FXMLLoader fxmlLoader = null;
        if (userAnswer.equals("")) {
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + 1);
        } else if (correctAnswer.equalsIgnoreCase(userAnswer)) {
            testobject.setCorrectQuestions(testobject.getCorrectQuestions() + 1);
        } else if (!correctAnswer.equals(userAnswer)) {
            testobject.setIncorrectQuestions(testobject.getIncorrectQuestions() + 1);
        }

        if (currentQuestion == (testobject.getNumberOfQuestions() - 1)) {
            //if on last question end test
            currentQuestion++;
            //createTestResult();
            stage.setTitle("Test Report");
            fxmlLoader = new FXMLLoader(getClass().getResource("EndTest.fxml"));
            root1 = (Parent) fxmlLoader.load();
            // createTestResult();
            EndTestController controller = fxmlLoader.<EndTestController>getController();
            controller.initData(testobject);
        } else {
            //go to next question
            currentQuestion++;
            stage.setTitle("Test");
            String nextQuestionType = testobject.getQuestionList().get(currentQuestion).getQuestionType();

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
                fxmlLoader = new FXMLLoader(getClass().getResource("TFNew.fxml"));
                root1 = (Parent) fxmlLoader.load();
                TFNewController controller = fxmlLoader.<TFNewController>getController();
                controller.initData(testobject);
            } else if (nextQuestionType.equals("FIB")) {
                fxmlLoader = new FXMLLoader(getClass().getResource("FIBQuestion.fxml"));
                root1 = (Parent) fxmlLoader.load();
                FIBQuestionController controller = fxmlLoader.<FIBQuestionController>getController();
                controller.initData(testobject);
            }

        }

        stage.setScene(new Scene(root1, 630, 510));
        stage.show();
    }

    /**
     * end test button
     * @param event
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws ParseException
     */
    @FXML
    protected void handleEndTestButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException, ParseException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //createTestResult();
        Parent root1 = null;
        FXMLLoader fxmlLoader = null;
        fxmlLoader = new FXMLLoader(getClass().getResource("EndTest.fxml"));
        root1 = (Parent) fxmlLoader.load();
        stage.setTitle("Test Report");
        EndTestController controller = fxmlLoader.<EndTestController>getController();
        controller.initData(testobject);
        stage.setScene(new Scene(root1, 630, 510));
        stage.show();
    }

    /**
     * initalize resources
     * @param test
     */
    @FXML
    public void initData(Test test) {
        testobject = test;

        String difficulty = (testobject.getQuestionList().get(testobject.getCurrentQuestionNumber())).getDifficulty();
        lblHeading.setText(lblHeading.getText() + " - " + difficulty);
        //  txtQuestion.setText("Q " + (testobject.getCurrentQuestionNumber() + 1) + ". " + (testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber()).getQuestion());
        lblQuestion.setText("Q " + (testobject.getCurrentQuestionNumber() + 1) + ". " + (testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber()).getQuestion());
    }

}
