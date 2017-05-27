package GUI;
import main_pack.Config;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class LoginController implements Initializable {

    @FXML private JFXTextField username;
    @FXML private JFXTextField password;
    
    @FXML private JFXButton se_connecter;
    @FXML private Label lconnexion;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setPromptText("Username/CIN");
        password.setPromptText("Password/CIN");
        
    }

    private void evaluer(){
        if (!username.getText().isEmpty()
                && !password.getText().isEmpty()) {
            se_connecter.setDisable(false);
        } else {
            se_connecter.setDisable(true);
        }
    }

    @FXML
    private void is_typing(KeyEvent event) {
        evaluer();
    }

    @FXML
    private void item_selected(ActionEvent event) {
        evaluer();
    }

    @FXML private void quitter(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void connect(ActionEvent event) {
        lconnexion.setVisible(false);
        Config cfg = new Config();
        if (cfg.load()) {
            if(!ODB.OracleDBSingleton.seConnecter(cfg.get_data("dburl").trim(), cfg.get_data("dbdriver").trim(), cfg.get_data("dbusername").trim(), cfg.get_data("dbpassword").trim())){
                lconnexion.setVisible(true);
                lconnexion.setText("Connexion Oracle Echoue,Verifier les parametres de connexion.");
                lconnexion.setStyle("-fx-background-color:red");
                return;
            }
        }
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
         
            if (password.getText().equalsIgnoreCase("GECOLE")) {
                try {
                    border.setCenter(FXMLLoader.load(getClass().getResource("mainwindow.fxml")));
                    main_pack.Main_class.user_id = 0;
                } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            } else {
                lconnexion.setVisible(true);
                lconnexion.setText("Combinaison username/mot de passe incorrectes.");
                lconnexion.setStyle("-fx-background-color:red");
                username.setStyle("-fx-background-color:#00CC00");
                password.setStyle("-fx-background-color:#00CC00");
            }
        
        
        
    }
}
