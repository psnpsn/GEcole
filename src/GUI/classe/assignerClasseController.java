/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.classe;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author h2oo2
 */
public class assignerClasseController implements Initializable {

    @FXML
    private JFXButton action;
    @FXML
    private JFXButton reinitbtn;
    @FXML
    private JFXTextField nomClasse;
    @FXML
    private Label lnomClasse;
    @FXML
    private JFXComboBox<?> capacite;
    @FXML
    private JFXComboBox<?> niveau;
    @FXML
    private Label lcapacite;
    @FXML
    private Label lniveau;
    @FXML
    private Label nomLabel;
    @FXML
    private Label nbLabel;
    @FXML
    private Label nivLabel;
    @FXML
    private Label nbLabel1;
    @FXML
    private JFXTabPane tabs;
    @FXML
    private Tab tabE;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> nomCol;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> adresseCol;
    @FXML
    private TableColumn<?, ?> cochCol;
    @FXML
    private Tab tabI;
    @FXML
    private TableView<?> tableViewI;
    @FXML
    private TableColumn<?, ?> nomICol;
    @FXML
    private TableColumn<?, ?> immCol;
    @FXML
    private TableColumn<?, ?> gradeCol;
    @FXML
    private TableColumn<?, ?> matiereCol;
    @FXML
    private TableColumn<?, ?> cochColI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_retour(ActionEvent event) {
    }

    @FXML
    private void click_trouver(ActionEvent event) {
    }

    @FXML
    private void click_ajouter(ActionEvent event) {
    }

    @FXML
    private void click_reinitialiserC(ActionEvent event) {
    }

    @FXML
    private void click_reinitialiserE(ActionEvent event) {
    }

    @FXML
    private void user_selection(MouseEvent event) {
    }

    @FXML
    private void tab_eleve(Event event) {
    }

    @FXML
    private void user_selectionI(MouseEvent event) {
    }

    @FXML
    private void tab_inst(Event event) {
    }
    
}
