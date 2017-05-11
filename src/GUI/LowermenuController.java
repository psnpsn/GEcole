package GUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class LowermenuController implements Initializable {

    @FXML
    private HBox box;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goto_config(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("prefs.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o 2: " + exception);
        }
    }
        @FXML
    private void goto_login(ActionEvent event) {
        ODB.OracleDBSingleton.seDeconnecter();
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("login.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o 2: " + exception);
        }
    }


}