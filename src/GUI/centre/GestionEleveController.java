/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.centre;

import GUI.LoginController;
import Models.Eleve;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GestionEleveController implements Initializable {

    @FXML
    private TableView<Eleve> tableView;
    @FXML
    private TableColumn<Eleve, String> nomCol;
    @FXML
    private TableColumn<Eleve, String> dateCol;
    @FXML
    private TableColumn<Eleve, String> sexCol;
    @FXML
    private TableColumn<Eleve, String> dateinsCol;
    @FXML
    private TableColumn<Eleve, String> classeCol;
    @FXML
    private TableColumn<?, ?> modifCol;
    @FXML
    private TableColumn<?, ?> cochCol;

    private ObservableList<Eleve> masterData = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
    }

    @FXML
    private void click_ajouter(ActionEvent event) {
        try {
            URL loader = getClass().getResource("ajoutEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void click_voir(ActionEvent event) {
               try {
            URL loader = getClass().getResource("voirEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void click_retour(ActionEvent event) {
        try {
            URL loader = getClass().getResource("../mainwindow.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void click_chercher(ActionEvent event) {
        
    }
/*
    private void click_modifier(ActionEvent event) {
                try {
            URL loader = getClass().getResource("modEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
*/

    @FXML
    private void click_supp(ActionEvent event) {
    }

    @FXML
    private void click_supptout(ActionEvent event) {
    }

    private void initCol() {
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"+"prenom"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateNaiss"));
        sexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        dateinsCol.setCellValueFactory(new PropertyValueFactory<>("dateIns"));
        classeCol.setCellValueFactory(new PropertyValueFactory<>("ref_c"+"ref_n"));
    }


}
