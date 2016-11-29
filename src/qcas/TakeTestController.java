/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;
import qcas.backEnd.Login;
import qcas.backEnd.Question;
import qcas.backEnd.Test;

/**
 * FXML Controller class
 *
 * @author aayush
 */
public class TakeTestController implements Initializable {

    @FXML
    private ComboBox<String> cmbNoOfQuestions;
    @FXML
    private ComboBox<String> cmbDifficultyLevel;
    @FXML
    private Text actiontarget;

    // @FXML
    //private Combobox usernamefield;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void handleTakeTestButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        //read the combobox selected value
        String numberOfQues = (String) cmbNoOfQuestions.getValue();
        String difficultyLevel = (String) cmbDifficultyLevel.getValue();
        int numberOfQuestions;
        ArrayList<Question> questionList = null;
        //procedd only if both the value from both the combobox have been selected
        if (!(numberOfQues.equals(cmbNoOfQuestions.getPromptText())) && !(difficultyLevel.equals(cmbDifficultyLevel.getPromptText()))) {
            //convert number of question value to int
            numberOfQuestions = Integer.parseInt((String) cmbNoOfQuestions.getValue());
            DatabaseManager dbManager = new DatabaseManager();
            questionList = new ArrayList<Question>();
            //create a test object
            Test test = new Test(1, numberOfQuestions, difficultyLevel);
            questionList = test.generateTest(1, numberOfQuestions, difficultyLevel);
            test.setQuestionList(questionList);
            //procedd only if number of questions returned is equal to the number of questions selected by the user
            if (questionList != null && questionList.size() == numberOfQuestions) {
                try {
                    String nextQuestionType = questionList.get(0).getQuestionType();
                    FXMLLoader fxmlLoader = null;
                    if (nextQuestionType.equals("MC")) {
                        fxmlLoader = new FXMLLoader(getClass().getResource("MCQuestion.fxml"));
                    } else if (nextQuestionType.equals("MA")) {
                        fxmlLoader = new FXMLLoader(getClass().getResource("MAQuestion.fxml"));
                    } else if (nextQuestionType.equals("TF")) {
                        fxmlLoader = new FXMLLoader(getClass().getResource("TFQuestion.fxml"));
                    } else if (nextQuestionType.equals("FIB")) {
                        fxmlLoader = new FXMLLoader(getClass().getResource("FIBQuestion.fxml"));
                    }

                    Parent root1 = (Parent) fxmlLoader.load();
                    root1.setUserData(test);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            actiontarget.setText("Please select a value in both of the combo box.");
        }
    }

    @FXML
    protected void handleCancelTestButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
