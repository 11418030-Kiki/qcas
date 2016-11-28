/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aayush
 */
public class QCAS extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("Home Screen");
        //scenetitle.setId("welcome-text");
        //actiontarget.setId("actiontarget");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
