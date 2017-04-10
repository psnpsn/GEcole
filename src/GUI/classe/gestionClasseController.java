/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.classe;

import DAO.ClasseDAO;
import DAO.DAO;
import GUI.LoginController;
import Models.Classe;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class gestionClasseController implements Initializable {

    @FXML
    private TableView<Classe> tableView;
    @FXML
    private TableColumn<Classe, String> nomCol;
    @FXML
    private TableColumn<Classe, String> nivCol;
    @FXML
    private TableColumn<Classe, String> nbCol;
    @FXML
    private TableColumn<Classe, String> capaciteCol;
    @FXML
    private TableColumn<Classe, String> anneeCol;
    @FXML
    private TableColumn<Classe, String> modifCol;
    @FXML
    private TableColumn<Classe, Boolean> cochCol;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXComboBox<Integer> niveau;
    @FXML
    private JFXComboBox<Integer> capacite;
    @FXML
    private JFXRadioButton vide;
    @FXML
    private JFXRadioButton complet;
    @FXML
    private JFXRadioButton incomplet;
    
     public static int id_classe_a_editer = -1 ;
     
     ToggleGroup group = new ToggleGroup();
     
     private ObservableList<Classe> masterData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
     
     Callback<TableColumn<Classe, String>, TableCell<Classe, String>> callback_fn_editer_classe
            = //
            new Callback<TableColumn<Classe, String>, TableCell<Classe, String>>() {
        @Override
        public TableCell call(final TableColumn param) {
            final TableCell cell = new TableCell() {

                @Override
                public void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        final Button editer = new Button("Modifier");
                        editer.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                param.getTableView().getSelectionModel().select(getIndex());
                                Classe item = tableView.getSelectionModel().getSelectedItem();
                                if (item != null) {
                                    id_classe_a_editer = item.getId_c();
                                    click_modifier();

                                }
                            }
                        });
                        setGraphic(editer);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    }
                }
            };
            return cell;
        }
    };
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //niveau.setItems(FXCollections.observableArrayList(1,2,3,4,5,6));
        capacite.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,
                16,17,18,20,21,22,23,24,25,26,27,28,29,30)
        );
        
        vide.setToggleGroup(group);
        incomplet.setToggleGroup(group);
        complet.setToggleGroup(group);
        initCol();
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
    private void click_ajouter(ActionEvent event) {
        try {
            URL loader = getClass().getResource("ajoutClasse.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void selection_classe(MouseEvent event) {
        ObservableList<Classe> selected = tableView.getSelectionModel().getSelectedItems();
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).setCocher(!selected.get(i).isCocher());
                }
    }

    @FXML
    private void click_chercher(ActionEvent event) {
        DAO classedao = new ClasseDAO();
        masterData = classedao.getAll();
        tableView.getItems().setAll(masterData);

        FilteredList<Classe> filteredData = new FilteredList<>(masterData, p -> true);
        
         nom.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(classe -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String fullnameFilter = newValue.toLowerCase();

                if (classe.getNom().toLowerCase().contains(fullnameFilter)) {
                    return true; 
                } 
                return false;
            });
        });
         
         niveau.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(classe -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String nivFilter = newValue;

                if (classe.nivProperty().toString().equals(nivFilter)) {
                    return true; 
                } 
                return false;
            });
        });
         
         capacite.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(classe -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String capaciteFilter = newValue.toLowerCase();

                if (classe.capaciteProperty().toString().equals(capaciteFilter)) {
                    return true; 
                } 
                return false;
            });
        });
         
         // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Classe> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }

    @FXML
    private void click_supprimer(ActionEvent event) {
    }

    @FXML
    private void click_supptout(ActionEvent event) {
    }
    
    private void initCol() {
        nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        //nbCol.setCellValueFactory(cellData -> cellData.getValue().dateNaissProperty());
        nivCol.setCellValueFactory(cellData -> cellData.getValue().nivProperty().asString());
        capaciteCol.setCellValueFactory(cellData -> cellData.getValue().capaciteProperty().asString());
        anneeCol.setCellValueFactory(cellData -> cellData.getValue().anneeProperty().asString());
        cochCol.setCellFactory(CheckBoxTableCell.forTableColumn(cochCol));
        cochCol.setCellValueFactory(cellData -> cellData.getValue().cocherProperty());
        modifCol.setCellFactory(callback_fn_editer_classe);

        
    }
    
    private void click_modifier() {
                try {
            URL loader = getClass().getResource("");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
    
}
