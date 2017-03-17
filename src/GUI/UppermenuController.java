/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UppermenuController implements Initializable {

    @FXML
    private HBox box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void click_reduire(ActionEvent event) {
        Main_class.stage.setIconified(true);
    }

    @FXML
    private void dragged(MouseEvent event) {
        Main_class.stage.setX(event.getScreenX() - Main_class.xOffset);
                Main_class.stage.setY(event.getScreenY() - Main_class.yOffset);
    }

    @FXML
    private void pressed(MouseEvent event) {
         Main_class.xOffset = event.getSceneX();
                Main_class.yOffset = event.getSceneY();
    }
    
}
