/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;
import qcas.backEnd.Question;

/**
 * FXML Controller class
 *
 * @author rajeevraibhatia
 */
public class QuestionUploaderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    ArrayList<Question> questionList = new ArrayList<>();
    
    @FXML
    private Label label;
    
    @FXML
    private Label actionMessage;
    
    public File chosenFile;
    
    @FXML
    private File chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = null;
        try {
            file = fileChooser.showOpenDialog(new Stage());
            chosenFile = file;
        } catch (Exception e) {
            System.out.println("");
        }
        label.setText(file.getName());
        return file;
    }
    
    @FXML
    private void uploadFile(ActionEvent event) throws FileNotFoundException, IOException {

        try {
            System.out.println("Computing");
            Question ques;
            
            BufferedReader br = null;
            //String filePath = System.getProperty("user.dir") + "/src/importques/question.csv";
            br = new BufferedReader(new FileReader(chosenFile));

            String line;

            while ((line = br.readLine()) != null) {
                ques = new Question();
                String[] fields = parseCSVLine(line);
                if(fields[1].equals("MC") || fields[1].equals("MA")){
                    ques.setQuestionType(fields[1]);
                    ques.setDifficulty(fields[2]);
                    ques.setQuestion(fields[3]);
                    ques.setOptionA(fields[4]);
                    ques.setOptionACorrect(fields[5].equals("correct"));
                    ques.setOptionB(fields[6]);
                    ques.setOptionBCorrect(fields[7].equals("correct"));
                    ques.setOptionC(fields[8]);
                    ques.setOptionCCorrect(fields[9].equals("correct"));
                    ques.setOptionD(fields[10]);
                    ques.setOptionDCorrect(fields[11].equals("correct"));
                }else if(fields[1].equals("TF")){
                    ques.setQuestionType(fields[1]);
                    ques.setDifficulty(fields[2]);
                    ques.setQuestion(fields[3]);
                    ques.setOptionA(fields[4]);
                }else if(fields[1].equals("FIB")){
                    ques.setQuestionType(fields[1]);
                    ques.setDifficulty(fields[2]);
                    ques.setQuestion(fields[3]);
                    ques.setOptionA(fields[4]);
                }
                questionList.add(ques);
            }
            DatabaseManager dbManager = new DatabaseManager();
            dbManager.insertQuestionInDatabase(questionList);
            actionMessage.setText("Questions uploaded successfully!");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionUploaderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(QuestionUploaderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionUploaderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuestionUploaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public static String[] parseCSVLine(String line) {
        // Create a pattern to match breaks
        Pattern p = Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        // Split input with the pattern
        String[] fields = p.split(line);
        for (int i = 0; i < fields.length; i++) {
            // Get rid of residual double quotes
            fields[i] = fields[i].replace("\"", "");
        }
        return fields;
    }
    
}
