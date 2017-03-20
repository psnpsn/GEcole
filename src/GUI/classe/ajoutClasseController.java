/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.classe;

import GUI.LoginController;
import Models.Classe;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ajoutClasseController implements Initializable {

    @FXML
    private JFXComboBox<Integer> capacite;
    @FXML
    private JFXComboBox<Integer> niveau;
    @FXML
    private JFXTextField nomClasse;
    @FXML
    private TableView<Classe> tableView;
    @FXML
    private TableColumn<Classe, String> nomCol;
    @FXML
    private TableColumn<Classe, String> idCol;
    @FXML
    private TableColumn<Classe, String> dateCol;
    @FXML
    private TableColumn<Classe, String> adresseCol;
    @FXML
    private TableColumn<?, ?> cochCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void click_trouver(ActionEvent event) {
    }

    @FXML
    private void click_ajouter(ActionEvent event) {
    }

    @FXML
    private void click_reinitialiserC(ActionEvent event) {
        nomClasse.setText("");
        capacite.getSelectionModel().clearSelection();
        capacite.setValue(0);
        capacite.setPromptText("Capacité");
        niveau.getSelectionModel().clearSelection();
        niveau.setValue(0);
        niveau.setPromptText("Capacité");
        
    }

    @FXML
    private void click_reinitialiserE(ActionEvent event) {
    }
    
}
