package GUI;


import GUI.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main_pack.Main_class;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MainwindowController implements Initializable  {
    
    	
        
       
        
    @FXML
    private AnchorPane anchor;

    
    @FXML
    void click_geleves(ActionEvent event) {
        try {
            URL loader = getClass().getResource("centre/gestionEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);
            
            BorderPane border = Main_class.getRoot();   
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_gclasses(ActionEvent event) {
        try {
            URL loader = getClass().getResource("classe/gestionClasse.fxml");
            AnchorPane middle = FXMLLoader.load(loader);
            
            BorderPane border = Main_class.getRoot();   
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void click_ginst(ActionEvent event) {
        try {
            URL loader = getClass().getResource("inst/gestionInst.fxml");
            AnchorPane middle = FXMLLoader.load(loader);
            
            BorderPane border = Main_class.getRoot();   
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

   

    
    
}
