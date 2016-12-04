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
import javafx.stage.Stage;
import qcas.backEnd.Test;

/**
 * FXML Controller class
 *
 * @author aayush
 */
public class TrueFalseQController implements Initializable {

    Test testobject = new Test();
    @FXML
    private Label lblQuestion;
    @FXML
    private RadioButton rbTrue;
    @FXML
    private RadioButton rbFalse;
    @FXML
    private ToggleGroup tgAnswers;
    private String answer;
    @FXML
    //private Button btnNext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rbTrue.setToggleGroup(tgAnswers);
        rbFalse.setToggleGroup(tgAnswers);

        if (testobject.getCurrentQuestionNumber() == testobject.getNumberOfQuestions()) {
            // btnNext.
        }

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

    /*
    @FXML
    protected void handleNextButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException {
       
    }

    @FXML
    protected void handleEndTestButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException {
       }
     */
/*
    @FXML
    protected void handleNextButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException {
    }

    @FXML
    protected void handleEndTestButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException {
    }
*/
    @FXML
    public void initData(Test test) {
        testobject = test;
        lblQuestion.setText(((testobject.getQuestionList()).get(testobject.getCurrentQuestionNumber())).getQuestion());
    }

}
