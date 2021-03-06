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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import qcas.backEnd.Test;

/**
 * FXML Controller class
 *
 * @author aayush
 */
public class MAQuestionController implements Initializable {

    Test testobject = new Test();
    @FXML
    private Label lblQuestion;
    @FXML
    private CheckBox cbA;
    @FXML
    private CheckBox cbB;
    @FXML
    private CheckBox cbC;
    @FXML
    private CheckBox cbD;
    @FXML
    private Label lblHeading;
    @FXML
    private TextArea txtQuestion;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * prev button
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
     * next button click
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
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        int currentQuestion = testobject.getCurrentQuestionNumber();
        String correctAnswer = testobject.getQuestionList().get(currentQuestion).getAnswerString();
        testobject.setCurrentQuestionNumber(currentQuestion + 1);
        String selectedAnswers = "";
        if (cbA.isSelected()) {
            selectedAnswers += "A"; //cbA.getText();
        }
        if (cbB.isSelected()) {
            if (selectedAnswers.length() > 0) {
                selectedAnswers += ",";
            }
            selectedAnswers += "B";//cbB.getText();
        }
        if (cbC.isSelected()) {
            if (selectedAnswers.length() > 0) {
                selectedAnswers += ",";
            }
            selectedAnswers += "C";//cbC.getText();
        }
        if (cbD.isSelected()) {
            if (selectedAnswers.length() > 0) {
                selectedAnswers += ",";
            }
            selectedAnswers += "D";//cbD.getText();
        }

        Parent root1 = null;
        if (selectedAnswers.equals("")) {
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + 1);
        } else if (correctAnswer.equals(selectedAnswers)) {
            testobject.setCorrectQuestions(testobject.getCorrectQuestions() + 1);
        } else if (!correctAnswer.equals(selectedAnswers)) {
            testobject.setIncorrectQuestions(testobject.getIncorrectQuestions() + 1);
        }
        FXMLLoader fxmlLoader = null;
        //if (currentQuestion == (testobject.getNumberOfQuestions() - 1)) {
        if (currentQuestion == (testobject.getNumberOfQuestions() - 1)) {
            //end test page
            currentQuestion++;
            stage.setTitle("Test Report");
            fxmlLoader = new FXMLLoader(getClass().getResource("EndTest.fxml"));
            root1 = (Parent) fxmlLoader.load();
            //createTestResult();
            EndTestController controller = fxmlLoader.<EndTestController>getController();
            controller.initData(testobject);
        } else {
            //next question page
            currentQuestion++;
            String nextQuestionType = testobject.getQuestionList().get(currentQuestion).getQuestionType();

            stage.setTitle("Test");
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
        // createTestResult();
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
     * initialize resources
     * @param test
     */
    @FXML
    public void initData(Test test) {
        testobject = test;
        String difficulty = (testobject.getQuestionList().get(testobject.getCurrentQuestionNumber())).getDifficulty();
        lblHeading.setText(lblHeading.getText() + " - " + difficulty);

       // txtQuestion.setText("Q " + (testobject.getCurrentQuestionNumber() + 1) + ". " + (testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber()).getQuestion());
        lblQuestion.setText("Q " + (testobject.getCurrentQuestionNumber() + 1) + ". " + (testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber()).getQuestion());
        cbA.setText(((testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber())).getOptionA());
        cbB.setText(((testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber())).getOptionB());
        cbC.setText(((testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber())).getOptionC());
        cbD.setText(((testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber())).getOptionD());
    }

    /**
     * split answer
     * @param answer
     * @return
     */
    public List<String> splitAnswer(String answer) {
        List<String> finAnswers = Arrays.asList(answer.split(","));
        return finAnswers;
    }
}
