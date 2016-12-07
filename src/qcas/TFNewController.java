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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import qcas.backEnd.Test;

/**
 * FXML Controller class
 *
 * @author aayush
 */
public class TFNewController implements Initializable {

    Test testobject = new Test();
    @FXML
    private Label lblQuestion;
    @FXML
    private RadioButton rbTrue;
    @FXML
    private RadioButton rbFalse;
    @FXML
    private Label lblHeading;

    @FXML
    private ToggleGroup tgAnswers;
    private String answer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rbTrue.setToggleGroup(tgAnswers);
        rbFalse.setToggleGroup(tgAnswers);

        tgAnswers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (tgAnswers.getSelectedToggle() != null) {
                    answer = ((RadioButton) tgAnswers.getSelectedToggle()).getText(); // ; .getUserData().toString();
                    //System.out.println(tgAnswers.getSelectedToggle().getUserData().toString());
                }
            }
        });
    }

    @FXML
    protected void handleNextButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException, ParseException {
        String a;
        if (answer == null) {
            a = null;
        } else if (answer.equals("True")) {
            a = "T";
        } else {
            a = "F";
        }

        //String a = answer; //.g;
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        int currentQuestion = testobject.getCurrentQuestionNumber();
        String correctAnswer = testobject.getQuestionList().get(currentQuestion).getAnswerString();
        testobject.setCurrentQuestionNumber(currentQuestion + 1); //.getQuestionList().get(currectQuestion).getAnswer();
        Parent root1 = null;
        FXMLLoader fxmlLoader = null;
        if (a == null) {
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + 1);
        } else if (correctAnswer.equals(answer)) {
            testobject.setCorrectQuestions(testobject.getCorrectQuestions() + 1);
        } else if (!correctAnswer.equals(answer)) {
            testobject.setIncorrectQuestions(testobject.getIncorrectQuestions() + 1);
        }
        if (currentQuestion == (testobject.getNumberOfQuestions() - 1)) {
            stage.setTitle("Test Report");
            fxmlLoader = new FXMLLoader(getClass().getResource("EndTest.fxml"));
            root1 = (Parent) fxmlLoader.load();
            // createTestResult();
            EndTestController controller = fxmlLoader.<EndTestController>getController();
            controller.initData(testobject);
        } else {
            stage.setTitle("Test");
            String nextQuestionType = testobject.getQuestionList().get(currentQuestion + 1).getQuestionType();

            if (nextQuestionType.equals("MC")) {
                fxmlLoader = new FXMLLoader(getClass().getResource("MCQuestion.fxml"));
                //Parent root = (Parent) fxmlLoader.load();
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

            //Stage stage = new Stage();
            stage.setScene(new Scene(root1, 630, 510));
            stage.show();

        }
    }

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

    @FXML
    public void initData(Test test) {
       // @FXML
      //  private Label lblHeading;
        lblHeading.getText();
      //  test.getQuestionList(test.)()
       // lblHeading.setText(lblHeading.getText()+", Difficulty Level: " );//(test.getQuestionList(test.getCurrentQuestionNumber())). );
        testobject = test;
        lblQuestion.setText("Q " + (testobject.getCurrentQuestionNumber() + 1) + ". " + (testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber()).getQuestion());
    }
}
