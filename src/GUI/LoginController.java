package GUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main_pack.Main_class;



/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LoginController implements Initializable  {
    
   
    @FXML
    private AnchorPane anchor;

  
    @FXML
    void click_exit(ActionEvent event) {
        System.exit(0);

    }
    
    @FXML
    void click_reduire(ActionEvent event) {
        
        Main_class.stage.setIconified(true);
    }
    
    @FXML
    void dragged(MouseEvent event) {
    Main_class.stage.setX(event.getScreenX() - Main_class.xOffset);
                Main_class.stage.setY(event.getScreenY() - Main_class.yOffset);
    }

    @FXML
    void pressed(MouseEvent event) {
        Main_class.xOffset = event.getSceneX();
                Main_class.yOffset = event.getSceneY();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    @FXML
    private void lunchapp(ActionEvent event) {
        try {
            URL loader = getClass().getResource("centre/mainwindow.fxml");
            AnchorPane middle = FXMLLoader.load(loader);
            
            BorderPane border = Main_class.getRoot();   
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    
}
