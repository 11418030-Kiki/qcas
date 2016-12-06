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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import qcas.backEnd.DatabaseManager;
import qcas.backEnd.Login;
import qcas.backEnd.Question;
import qcas.backEnd.Test;
import qcas.backEnd.User;

/**
 * FXML Controller class
 *
 * @author aayush
 */
public class GenTestController implements Initializable {

    @FXML
    private ComboBox<String> cmbNoOfQuestions;
    @FXML
    private ComboBox<String> cmbDifficultyLevel;
    @FXML
    private Text actiontarget;
    User userObject = new User();

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private ArrayList<Question> getQList() {
        ArrayList<Question> questionList = new ArrayList<Question>();
        questionList.add(new Question(1, "MC", "E", "ABCDE1", "A", true, "B", false, "C", false, "D", false, "A"));
        questionList.add(new Question(2, "MA", "E", "ABCDE2", "A", true, "B", true, "C", false, "D", false, "A,B"));
        questionList.add(new Question(3, "FIB", "E", "ABCDE4 __________", "aayushb1", false, "", false, "", false, "", false, "aayushb1"));
        questionList.add(new Question(4, "TF", "E", "ABCDE3", "True", false, "", false, "", false, "", false, "True"));
        questionList.add(new Question(5, "MA", "M", "ABCDE5", "A", true, "B", true, "C", true, "D", false, "A,B,C"));
        return questionList;
    }

    @FXML
    protected void handleTakeTestButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        //read the combobox selected value
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        String numberOfQues = (String) cmbNoOfQuestions.getValue();
        String difficultyLevel = (String) cmbDifficultyLevel.getValue();
        int numberOfQuestions;
        ArrayList<Question> questionList = null;
        //proceed only if both the value from both the combobox have been selected
        if (numberOfQues != null && difficultyLevel != null && !(numberOfQues.equals(cmbNoOfQuestions.getPromptText())) && !(difficultyLevel.equals(cmbDifficultyLevel.getPromptText()))) {
            //convert number of question value to int
            numberOfQuestions = Integer.parseInt((String) cmbNoOfQuestions.getValue());

            questionList = new ArrayList<Question>();
            //create a test object
            Test test = new Test(userObject, numberOfQuestions, difficultyLevel);

            questionList = test.generateTest(numberOfQuestions, difficultyLevel);
            //questionList = getQList();
            test.setQuestionList(questionList);
            /*
            //test.setNumberOfQuestions(5);
            String[] answers = new String[5];
            answers[0] = "A";
            answers[1] = "A,B";
            answers[2] = "aayushb1";
            answers[3] = "True";
            answers[4] = "A,B,C";
            //answers[5] = "A,B,C,D";
             */

//            test.setAnswerArrayList(answers);
            test.setCurrentQuestionNumber(0);
            //procedd only if number of questions returned is equal to the number of questions selected by the user
            if (questionList != null && questionList.size() == numberOfQuestions) {
                try {
                    Parent root1 = null;
                    //here
                    //String nextQuestionType = questionList.get(0).getQuestionType();
                    String nextQuestionType = questionList.get(0).getQuestionType();
                    FXMLLoader fxmlLoader = null;
                    if (nextQuestionType.equals("MC")) {
                        fxmlLoader = new FXMLLoader(getClass().getResource("MCQuestion.fxml"));
                        root1 = (Parent) fxmlLoader.load();
                        MCQuestionController controller = fxmlLoader.<MCQuestionController>getController();
                        controller.initData(test);
                    } else if (nextQuestionType.equals("MA")) {
                        fxmlLoader = new FXMLLoader(getClass().getResource("MAQuestion.fxml"));
                        root1 = (Parent) fxmlLoader.load();
                        MAQuestionController controller = fxmlLoader.<MAQuestionController>getController();
                        controller.initData(test);
                    } else if (nextQuestionType.equals("TF")) {
                        fxmlLoader = new FXMLLoader(getClass().getResource("TFNew.fxml"));
                        root1 = (Parent) fxmlLoader.load();
                        TFNewController controller = fxmlLoader.<TFNewController>getController();
                        controller.initData(test);
                    } else if (nextQuestionType.equals("FIB")) {
                        fxmlLoader = new FXMLLoader(getClass().getResource("FIBQuestion.fxml"));
                        root1 = (Parent) fxmlLoader.load();
                        FIBQuestionController controller = fxmlLoader.<FIBQuestionController>getController();
                        controller.initData(test);
                    }

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

        // GenTestController controller = fxmlLoader.<GenTestController>getController();
        //controller.initData(user);
        //stage.setTitle("Take Test");
        //stage.setScene(new Scene(root1));
        //stage.show();
    }

    @FXML
    public void initData(User user) {
        userObject = user;
    }
}
