/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public void initData(Test test) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        testobject = test;
        int currentQuestion = testobject.getCurrentQuestionNumber();
        int totalQuestion = testobject.getNumberOfQuestions();
        if (currentQuestion < totalQuestion - 1) {
            int questionsLeft = totalQuestion - currentQuestion;
            testobject.setUnansweredQuestions(testobject.getUnansweredQuestions() + questionsLeft);
        }

        int Score = testobject.getCorrectQuestions();
        testobject.setScore(testobject.getCorrectQuestions());
        double scaledScore = testobject.getCorrectQuestions() * 100.0 / testobject.getNumberOfQuestions();
        testobject.setScaledScore(scaledScore);
        DateFormat df = new SimpleDateFormat("DD/MM/YYYY");
        Date dateobj = new Date();
        //System.out.println(df.format(dateobj));

        /*getting current date time using calendar class 
        * An Alternative of above*/
        Calendar calobj = Calendar.getInstance();
        String strDate = df.format(calobj.getTime());
        testobject.setTestDate((Date) df.parse(strDate));
        Test testResult = new Test();
        testResult.saveTestDetails(testobject);

        System.out.println(df.format(calobj.getTime()));

        //DatabaseManager dbManager = new DatabaseManager();
        //dbManager.saveTestDetails(testobject);
    }

}
