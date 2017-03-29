/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.centre;

import DAO.DAO;
import DAO.EleveDAO;
import GUI.LoginController;
import Models.Eleve;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
public class GestionEleveController implements Initializable {

    @FXML
    private TableView<Eleve> tableView;
    @FXML
    private TableColumn<Eleve, String> nomCol;
    @FXML
    private TableColumn<Eleve, Date> dateCol;
    @FXML
    private TableColumn<Eleve, String> sexCol;
    @FXML
    private TableColumn<Eleve, Date> dateinsCol;
    @FXML
    private TableColumn<Eleve, String> classeCol;
    @FXML
    private TableColumn<Eleve, String> modifCol;
    @FXML
    private TableColumn<Eleve, Boolean> cochCol;
    
    private ObservableList<Eleve> masterData = FXCollections.observableArrayList();
    @FXML
    private JFXTextField idEleveF;
    @FXML
    private JFXTextField nomEleveF;
    @FXML
    private JFXDatePicker dateNaissF;
    @FXML
    private JFXDatePicker dateInsF;
    @FXML
    private JFXComboBox<?> nivF;
    @FXML
    private JFXComboBox<?> classeF;

    public static int id_eleve_a_editer = -1 ;


    /**
     * Initializes the controller class.
     */

    Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>> callback_fn_editer_eleve
            = //
            new Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>>() {
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
                                Eleve item = tableView.getSelectionModel().getSelectedItem();
                                if (item != null) {
                                    id_eleve_a_editer = item.getId_e();
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
    private void click_chercher(ActionEvent event) {
        DAO elevedao = new EleveDAO();
        masterData = elevedao.getAll();
        tableView.getItems().setAll(masterData);

        FilteredList<Eleve> filteredData = new FilteredList<>(masterData, p -> true);
        
         nomEleveF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eleve -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String fullnameFilter = newValue.toLowerCase();

                if (eleve.getNom().toLowerCase().contains(fullnameFilter)) {
                    return true; 
                } else if (eleve.getPrenom().toLowerCase().contains(fullnameFilter)) {
                    return true; 
                }
                return false;
            });
        });
         idEleveF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eleve -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String idFilter = newValue;

                if (eleve.id_eProperty().toString().contains(idFilter)){
                    return true;
                } 
                return false;
            });
        });
         
         dateNaissF.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eleve -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String dateFilter = newValue.replace("/","-");
                System.out.println("dateFilter");
                

                if (eleve.dateNaissProperty().toString().equals(dateFilter)){
                    return true;
                } 
                return false;
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Eleve> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
        

    }

    private void click_modifier() {




                try {
            URL loader = getClass().getResource("modEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    @FXML
    private void click_supp(ActionEvent event) {

    }

    @FXML
    private void click_supptout(ActionEvent event) {
    }

    private void initCol() {
        nomCol.setCellValueFactory(cellData -> cellData.getValue().fullnomProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateNaissProperty());
        sexCol.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        dateinsCol.setCellValueFactory(cellData -> cellData.getValue().dateInsProperty());
        classeCol.setCellValueFactory(cellData -> cellData.getValue().ref_nProperty().asString());
        cochCol.setCellFactory(CheckBoxTableCell.forTableColumn(cochCol));
        cochCol.setCellValueFactory(cellData -> cellData.getValue().cocherProperty());
        modifCol.setCellFactory(callback_fn_editer_eleve);

        
    }


         
    }
        




