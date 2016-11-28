/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import qcas.backEnd.DatabaseManager;
import qcas.backEnd.Login;
import qcas.backEnd.User;

/*
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
 */
/**
 *
 * @author aayush
 */
public class FXMLHomeController {

    @FXML
    private Text actiontarget;
    @FXML
    private TextField usernamefield;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        Login login = new Login();

        String username = usernamefield.getText();
        String passsword = passwordField.getText();
        DatabaseManager dbManager = new DatabaseManager();
        //actiontarget.setText("invalid credentials");
        User user = dbManager.login(username, passsword, "student");

        if (user != null && user.getUserID() > 0) {
            actiontarget.setText("login successful");
        } else {
            actiontarget.setText("invalid credentials");
        }

    }
}
