package GUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main_pack.Main_class;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MainwindowController implements Initializable ,ControlledScreen {
    
    	ScreensController myController; 
        
       
        
    @FXML
    private BorderPane content;

    
    @FXML
    void click_geleves(ActionEvent event) throws IOException {
        myController.setScreen(Main_class.GEST_ELEVE);
    }

    @FXML
    void click_exit(ActionEvent event) {
        System.exit(0);

    }
    
    @FXML
    void click_reduire(ActionEvent event) {
        
        Main_class.stg.setIconified(true);
    }
    
    @FXML
    void dragged(MouseEvent event) {
    Main_class.stg.setX(event.getScreenX() - Main_class.xOffset);
                Main_class.stg.setY(event.getScreenY() - Main_class.yOffset);
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

    @Override
    public void setScreenParent(ScreensController screenParent) {
       myController=screenParent;
    }

   

    
    
}
