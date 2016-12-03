/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.io.IOException;
import java.util.EventObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Pansul47
 */
public class Graphs extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("prof.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

                
    }
    
        public void goToWindow (EventObject eventObject, String fxmlName) {
    Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlName));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        }
}
