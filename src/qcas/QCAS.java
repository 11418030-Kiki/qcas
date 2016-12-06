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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aayush
 */
public class QCAS extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        //stage.getIcons().add(new Image("https://example.com/javaicon.png"));
        Scene scene = new Scene(root, 630, 510);
        stage.setTitle("Home Screen");
        // stage.initStyle(StageStyle.UNDECORATED);
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
