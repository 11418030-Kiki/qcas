/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RRB
 */
public class StudentReportsController implements Initializable {
    
    @FXML
    private BarChart<?, ?> numberTestsBar;
    
    @FXML
    private BarChart<?, ?> difficultyLevelBar;
    
    @FXML
    private ComboBox<String> comboReportPeriod;
    @FXML
    private ImageView imgHome;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /*imgHome.setOnMouseClicked(event -> {

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

            //imgLogout .setStyle("-fx-image: url(\""+ IMAGE2 + "\");");
        });
       */
    
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
    
}
