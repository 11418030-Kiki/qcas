/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import qcas.backEnd.Test;
import qcas.backEnd.User;

/**
 * FXML Controller class
 *
 * @author RRB
 */
public class StudentDashboardController implements Initializable {

    User userObject = new User();
    @FXML
    private Label lblName;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * take test button
     *
     * @param event
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws SQLException
     * @throws IllegalAccessException
     */
    @FXML
    protected void handleTakeTestAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GenTest.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            GenTestController controller = fxmlLoader.<GenTestController>getController();
            stage.setTitle("Take a Test");
            controller.initData(userObject);
            //stage.setTitle("Welcome to Student Dashboard");
            stage.setScene(new Scene(root, 630, 510));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * logout button
     *
     * @param event
     */
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

    /**
     *
     * @param user
     */
    @FXML
    public void initData(User user) {
        userObject = user;
        lblName.setText("Hi " + userObject.getFirstName() + " " + userObject.getLastName());

    }
}
