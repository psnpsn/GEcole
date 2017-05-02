/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.emploi;

import DAO.ClasseDAO;
import DAO.EmploiDAO;
import DAO.InstituteurDAO;
import DAO.SalleDAO;
import Models.Classe;
import Models.Emploi;
import Models.Instituteur;
import Models.Salle;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class list_emploi implements Initializable {

    @FXML
    private TableView<Emploi> tableview_emploi;
    @FXML private TableColumn<Emploi, String> colonne_total_heures,colonne_nom_classe,colonne_niveau_classe,colonne_nom_salle;
    @FXML private TableColumn<Emploi, String> colonne_capacite,colonne_capacite_classe,colonne_capacite_salle,colonne_actions;
    @FXML private JFXComboBox<String> combobox_classe,combobox_niveau,combobox_instituteur,combobox_salle;
    private EmploiDAO    dao_emploi = null;
    private SalleDAO     dao_salles = null;
    private InstituteurDAO dao_inst = null;
    private ClasseDAO   dao_classes = null;
    

    /**
     * Initializes the controller class.
     */
    //-- init methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_daos();
        init_combobox_niveau();
        init_combobox_salles();
        init_combobox_instituteurs();
        init_combobox_classes();
        init_table();
    }    
    
    private void init_daos(){
        dao_emploi  = new EmploiDAO();
        dao_salles  = new SalleDAO();
        dao_inst    = new InstituteurDAO();
        dao_classes = new ClasseDAO();
     }
    private void init_table(){
        tableview_emploi.getSelectionModel().setCellSelectionEnabled(false);
        tableview_emploi.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colonne_capacite_classe.setCellValueFactory(cellData -> {
            return new SimpleStringProperty("not implemented");
        });
        colonne_capacite_salle.setCellValueFactory(cellData -> {
            Salle s = dao_salles.find(cellData.getValue().getId_salle());
            if (s != null) {
                return new SimpleStringProperty("" + s.getCapacite());
            } else {
                return new SimpleStringProperty("");
            }
        });
        ObservableList<Emploi> data = dao_emploi.getAll();
        if (data != null) {
            tableview_emploi.setItems(data);
        }
    }
    private void init_combobox_niveau(){
        combobox_niveau.setTooltip(new Tooltip("Filtrer par Annee Scolaire\n - Choisir la valeur '--' pour afficher les emplois de tous les niveaux."));
        combobox_niveau.getItems().clear();
        combobox_niveau.getItems().addAll("--","1","2","3","4","5","6");
    }
    private void init_combobox_salles(){
        combobox_salle.setTooltip(new Tooltip("Filtrer par Salle\n - Choisir la valeur '--' pour afficher les emplois de n'importe quelle Salles."));
        combobox_salle.getItems().clear();
        combobox_salle.getItems().add("--");
        for (Salle s:dao_salles.getAll()){
            combobox_salle.getItems().add(s.getType_salle());
        }
    }
    private void init_combobox_instituteurs(){
        combobox_instituteur.setTooltip(new Tooltip("Filtrer par Instituteur\n - Choisir la valeur '--' pour afficher les emplois de n'importe quelle Instituteurs."));
        combobox_instituteur.getItems().clear();
        combobox_instituteur.getItems().add("--");
        for (Instituteur i:dao_inst.getAll()){
            combobox_instituteur.getItems().add(i.getNom() + " " + i.getPrenom());
        }
    }
    private void init_combobox_classes(){
        combobox_classe.setTooltip(new Tooltip("Filtrer par Classe\n - Choisir la valeur '--' pour afficher les emplois de n'importe quelle Classes."));
        combobox_classe.getItems().clear();
        combobox_classe.getItems().add("--");
        for (Classe c:dao_classes.getAll()){
            combobox_classe.getItems().add(c.getNom());
        }
    }
    //navigations
    @FXML
    private void goto_admin_main(ActionEvent event) {
    }

    @FXML
    private void goto_ajouter_emploi(ActionEvent event) {
    }

    @FXML
    private void chercher_emploi(ActionEvent event) {
    }

    @FXML
    private void supprimer_emploi(ActionEvent event) {
    }

    @FXML
    private void supprimer_emplois(ActionEvent event) {
      
    }

    @FXML
    private void user_selection(MouseEvent event) {
    }
    
}
