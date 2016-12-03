/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.io.IOException;
import java.util.EventObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Pansul47
 */
public class ViewChanger {
    
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
