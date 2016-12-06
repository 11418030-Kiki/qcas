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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;
import qcas.backEnd.Question;
import qcas.backEnd.Test;
import qcas.backEnd.User;
/**
 * FXML Controller class
 *
 * @author Shikha
 */
public class StudentSignUpController implements Initializable {
  @FXML
    private ComboBox<String> cmbSignupType;
//    @FXML
//    private Text actiontarget;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField Course;
    @FXML
    private TextField usernamefield;
    @FXML
    private PasswordField passwordField;
    /**
     * Initializes the controller class.
     */
    @Override  
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        usernamefield.setPromptText("Username");
//        passwordField.setPromptText("Password");
//        Course.setPromptText("course");
//        firstName.setPromptText("firstname");
//        lastName.setPromptText("lastname");    
    }    
    
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException {

        String username = usernamefield.getText();
        String password = passwordField.getText();
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String course = Course.getText();
        int userID=0;
        DatabaseManager dbManager = new DatabaseManager();
        
        //actiontarget.setText("invalid credentials");
        User user = new User(userID, username, password,  cmbSignupType.getValue(), firstname, lastname, course);
        if(!username.isEmpty() && !password.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty()&& !course.isEmpty() && !cmbSignupType.getValue().isEmpty()){
            dbManager.saveUserDetails(user);
        }else{
            usernamefield.clear();
            passwordField.clear();
//            actiontarget.setText("Please enter a valid username and password !");
            return;
        }
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GenTest.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                GenTestController controller = fxmlLoader.<GenTestController>getController();
                controller.initData(user);
                stage.setTitle("Take Test");
                stage.setScene(new Scene(root1));
                stage.show();

    }
}
