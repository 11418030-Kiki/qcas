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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import qcas.backEnd.DatabaseManager;
import qcas.backEnd.User;

/**
 *
 * @author aayush
 */
public class FXMLHomeController extends AnchorPane implements Initializable {

    @FXML
    private Text actiontarget;
    @FXML
    private TextField usernamefield;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> cmbLoginType;

    private QCAS application;
    User userObject = new User();

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {

        String username = usernamefield.getText();
        String password = passwordField.getText();
        DatabaseManager dbManager = new DatabaseManager();
        User user = null;
        if (!username.isEmpty() && !password.isEmpty() && !cmbLoginType.getValue().isEmpty()) {
            user = dbManager.login(username, password, cmbLoginType.getValue());
        } else {
            usernamefield.clear();
            passwordField.clear();
            actiontarget.setText("Please enter a valid username and password !");
            return;
        }
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        if (user != null && user.getUserID() > 0) {
            System.out.println("RRB-->"+cmbLoginType.getValue());
            try {
                if (cmbLoginType.getValue().equalsIgnoreCase("student")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    StudentDashboardController controller = fxmlLoader.getController();
                    controller.initData(user);
                    stage.setTitle("Welcome to Student Dashboard");
                    stage.setScene(new Scene(root, 630, 510));
                    stage.show();
                }else {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InstructorDashboard.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    stage.setTitle("Welcome to Instructor Dashboard");
                    stage.setScene(new Scene(root, 630, 510));
                    stage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            passwordField.clear();
            actiontarget.setText("Invalid Credentials!");
        }
    }

    public void setApp(QCAS application) {
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actiontarget.setText("");
        usernamefield.setPromptText("Username");
        passwordField.setPromptText("Password");
    }

    @FXML
    public void initData(User user) {
        userObject = user;
    }
}
