package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



public class GestionEleveController implements Initializable ,ControlledScreen{
    
    ScreensController myController;

        @FXML
    private void click_modifier(ActionEvent event) throws Exception{
        
    }
        @FXML
    private void click_supprimer(ActionEvent event) throws Exception{
        
    }
        @FXML
    private void click_recherche(ActionEvent event) throws Exception{
        
    }
        @FXML
    private void click_lister(ActionEvent event) throws Exception{
        System.out.println("click");
        
        
    }
    @FXML
        private void click_ajouter(ActionEvent event) throws Exception{
        System.out.println("click");
        
        
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
