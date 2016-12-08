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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;
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
    @FXML
    private Text actiontarget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usernamefield.setPromptText("Username");
        passwordField.setPromptText("Password");
        Course.setPromptText("Course");
        firstName.setPromptText("First Name");
        lastName.setPromptText("Last Name");
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException, IOException {

        String username = usernamefield.getText();
        String password = passwordField.getText();
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String course = Course.getText();
        int userID = 0;
        DatabaseManager dbManager = new DatabaseManager();
        User user;
        if (!username.isEmpty() && !password.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !course.isEmpty() && !cmbSignupType.getValue().isEmpty()) {
            user = new User(userID, username, password, cmbSignupType.getValue().toLowerCase(), firstname, lastname, course);

            boolean flag = dbManager.checkUserDetails(user);
            if (flag) {
                dbManager.saveUserDetails(user);
                actiontarget.setText("Signed up Successfully! Please Login from Home");
                return;
            } else {
                actiontarget.setText("User already exists! Please try again.");
                return;
            }
        } else {
            actiontarget.setText("Please enter all the required fields!");
            return;
        }
    }

    @FXML
    private void logoutApp(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            FXMLHomeController controller = fxmlLoader.<FXMLHomeController>getController();
            stage.setTitle("Welcome to QCAS");
            stage.setScene(new Scene(root, 630, 510));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void resetAllFields(ActionEvent event) {
        usernamefield.clear();
        passwordField.clear();
        firstName.clear();
        lastName.clear();
        Course.clear();
    }
}
