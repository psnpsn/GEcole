/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.classe;

import DAO.AssisteDAO;
import DAO.ClasseDAO;
import DAO.DAO;
import DAO.EleveDAO;
import DAO.InstituteurDAO;
import GUI.LoginController;
import GUI.Tests;
import Models.Assiste;
import Models.Classe;
import Models.Eleve;
import Models.Instituteur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
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
    private TableView<Eleve> tableView;
    @FXML
    private TableColumn<Eleve, String> nomCol;
    @FXML
    private TableColumn<Eleve, String> idCol;
    @FXML
    private TableColumn<Eleve, Date> dateCol;
    @FXML
    private TableColumn<Eleve, String> adresseCol;
    @FXML
    private TableColumn<Eleve, Boolean> cochCol;
    
    private ObservableList<Eleve> masterData = FXCollections.observableArrayList();
    
    @FXML
    private Label lnomClasse;
    @FXML
    private Label lniveau;
    @FXML
    private Label lcapacite;
    @FXML
    private TableView<Instituteur> tableViewI;
    @FXML
    private TableColumn<Instituteur, String> nomICol;
    @FXML
    private TableColumn<Instituteur, String> immCol;
    @FXML
    private TableColumn<Instituteur, String> gradeCol;
    @FXML
    private TableColumn<Instituteur, String> matiereCol;
    @FXML
    private TableColumn<Instituteur, Boolean> cochColI;
    
     private ObservableList<Instituteur> masterDataI = FXCollections.observableArrayList();
    @FXML
    private JFXButton reinitbtn;
    @FXML
    private JFXTabPane tabs;
    @FXML
    private Tab tabE;
    @FXML
    private Tab tabI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        niveau.setItems(FXCollections.observableArrayList(1,2,3,4,5,6));
        capacite.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,
                16,17,18,20,21,22,23,24,25,26,27,28,29,30)
        );
        initCol();
        //init eleves
        DAO elevedao = new EleveDAO();
        masterData = elevedao.getAll();
        tableView.getItems().setAll(masterData);
        //init inst
        DAO instdao = new InstituteurDAO();
        masterDataI = instdao.getAll();
        tableViewI.getItems().setAll(masterDataI);
        
    }    
    
    @FXML private void selection_eleve(MouseEvent event) {
        ObservableList<Eleve> selected = tableView.getSelectionModel().getSelectedItems();
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).setCocher(!selected.get(i).isCocher());
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
    private void click_trouver(ActionEvent event) {
        try {
            URL loader = getClass().getResource("gestionClasse.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void click_ajouter(ActionEvent event) {
        boolean erreur;
        erreur=((Tests.vcombo(capacite,lcapacite))&(Tests.vchaine(nomClasse,lnomClasse, 20, true))
                &(Tests.vcombo(niveau, lniveau))
                );
        if(erreur){
        DAO classedao =new ClasseDAO();
        Classe classe=new Classe();
        classe.setNom(nomClasse.getText());
        classe.setRef_niv(Integer.parseInt(niveau.getSelectionModel().getSelectedItem()+"2016"));
        classe.setCapacite(capacite.getSelectionModel().getSelectedItem());
        int id=classedao.create(classe);
        if (id!=-1) {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Success!");
                conf.setHeaderText("L'operation de l'ajout de la classe est effectuée avec succés");
                conf.setContentText("La classe est ajouté avec l'id "+id+".\n");
                conf.showAndWait();
                
                tableView.getItems().clear();
               
            } else {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Erreur!");
                conf.setHeaderText("Une erreur s'est produite lors de l'ajout de la classe.");
                conf.setContentText("Aucune classe n'a été ajoutée.");
                conf.showAndWait();
            }
        
        if (ajoutEleves(id)) {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Success!");
                conf.setHeaderText("L'operation de l'ajout des élèves est effectuée avec succés");
                conf.setContentText("Les élèves sont ajoutés à la classe.\n");
                conf.showAndWait();
                reinitC();
                tableView.getItems().clear();
               
            } else {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Erreur!");
                conf.setHeaderText("Une erreur s'est produite lors de l'ajout des élèves.");
                conf.setContentText("Aucune élève n'a été ajouté à la classe.");
                conf.showAndWait();
            }

            
        }
        
        
        
    }

    @FXML
    private void click_reinitialiserC(ActionEvent event) {
        reinitC();
        
    }

    @FXML
    private void click_reinitialiserE(ActionEvent event) {
        if(reinitbtn.getText()=="Réinitialiser les élèves"){
            System.out.println("If élèves");
            tableView.getItems().forEach(item -> item.cocherProperty().set(false));
            
        }
         if(reinitbtn.getText()=="Réinitialiser les instituteurs"){
            System.out.println("If Instituteurs");
            tableViewI.getItems().forEach(item -> item.cocherProperty().set(false));
            
        }
        
    }
    
    @FXML
    private void selection_instituteur(MouseEvent event) {
        ObservableList<Instituteur> selected = tableViewI.getSelectionModel().getSelectedItems();
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).setCocher(!selected.get(i).isCocher());
                }
    }

    @FXML
    private void tab_eleve(Event event) {
        System.out.println("Tab Eleve Selected");
            reinitbtn.setText("Réinitialiser les élèves");
    }

    @FXML
    private void tab_inst(Event event) {
        System.out.println("Tab Instituteurs Selected");
            reinitbtn.setText("Réinitialiser les instituteurs");
    }
    
    private void initCol() {
        //eleve
        nomCol.setCellValueFactory(cellData -> cellData.getValue().fullnomProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateNaissProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        adresseCol.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
        cochCol.setCellFactory(CheckBoxTableCell.forTableColumn(cochCol));
        cochCol.setCellValueFactory(cellData -> cellData.getValue().cocherProperty());
        //instituteur
        nomICol.setCellValueFactory(cellData -> cellData.getValue().fullnomProperty());
        immCol.setCellValueFactory(cellData -> cellData.getValue().immProperty().asString());
        gradeCol.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
        cochColI.setCellFactory(CheckBoxTableCell.forTableColumn(cochColI));
        cochColI.setCellValueFactory(cellData -> cellData.getValue().cocherProperty());
        
    }
    
    private void reinitC() {
        nomClasse.setText("");
        capacite.getSelectionModel().clearSelection();
        capacite.setValue(0);
        capacite.setPromptText("Capacité");
        niveau.getSelectionModel().clearSelection();
        niveau.setValue(0);
        niveau.setPromptText("Capacité");
        
    }
    
    private boolean ajoutEleves(int id){
        int i=0;
        boolean valide=false;
        Eleve eleve=new Eleve();
        EleveDAO elevedao=new EleveDAO();
        
        while(i<masterData.size()){
            eleve = masterData.get(i);
            if (eleve.isCocher()){
            eleve.setRef_c(id);
            if(elevedao.updateRef_c(eleve))
                valide=true;
            else valide=false;
            }
            i++;
        }
        
        
        return valide;
    }
    
    private boolean ajoutInst(int id){
        int i=0;
        boolean valide=false;
        Assiste assiste=new Assiste();
        AssisteDAO assistedao=new AssisteDAO();
        Instituteur inst=new Instituteur();
        
        while(i<masterDataI.size()){
            inst = masterDataI.get(i);
            if (inst.isCocher()){
            assiste.setRef_c(id);
            if(assistedao.create(assiste)!=-1)
                valide=true;
            else valide=false;
            }
            i++;
        }
        
        
        return valide;
    }

    
    
}
