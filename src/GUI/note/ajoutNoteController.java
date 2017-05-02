/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.note;

import GUI.LoginController;
import Models.Eleve;
import Models.Matiere;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class ajoutNoteController implements Initializable {

    @FXML
    private JFXButton action;
    @FXML
    private Tab tabClasse;
    @FXML
    private JFXComboBox<String> niv;
    @FXML
    private JFXComboBox<String> classe;
    @FXML
    private Tab tabNote;
    @FXML
    private TableView<Matiere> tableViewM;
    @FXML
    private TableColumn<Matiere, String> nomCol;
    @FXML
    private TableColumn<Matiere, String> cochCol;
    @FXML
    private TableView<Eleve> tableViewE;
    @FXML
    private TableColumn<Eleve, String> idCol;
    @FXML
    private TableColumn<Eleve, String> dateCol;
    @FXML
    private TableColumn<Eleve, String> noteCol;
    @FXML
    private TableColumn<Eleve, String> nompreCol;
    
    public static int id_matiere_a_editer = -1 ;
    
     private ObservableList<Matiere> masterDataM = FXCollections.observableArrayList();
     
     private ObservableList<Eleve> masterDataE = FXCollections.observableArrayList();
     
     private ArrayList<Integer> selected_ids = new ArrayList<Integer>();
     
     Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>> callback_fn_select_matiere = new Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>>() {
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
                        final JFXCheckBox check_box = new JFXCheckBox();
                        check_box.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (check_box.isSelected()) {
                                    selected_ids.add(getIndex());
                                    param.getTableView().getSelectionModel().select(getIndex());
                                    update_selection();
                                } else {
                                    selected_ids.remove(selected_ids.indexOf(getIndex()));
                                }
                                param.getTableView().getSelectionModel().clearSelection(getIndex());
                                update_selection();
                            }
                        });
                        setGraphic(check_box);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    }
                }
            };
            return cell;
        }
    };
     
     
     private void update_selection() {
        tableViewM.getSelectionModel().clearSelection();
        for (int i = 0; i < selected_ids.size(); i++) {
            tableViewM.getSelectionModel().select(selected_ids.get(i));
        }
    }
     
     Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>> callback_fn_ajouter_note = new Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>>() {
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
                        final TextField check_box = new TextField();
                        setGraphic(check_box);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    }
                }
            };
            return cell;
        }
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }    

    @FXML
    private void goto_admin_main(ActionEvent event) {
        try {
            URL loader = getClass().getResource("../GUI/mainwindow.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void reinit(ActionEvent event) {
    }

    @FXML
    private void listNote(ActionEvent event) {
        try {
            URL loader = getClass().getResource("listNote.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajoutNote(ActionEvent event) {
        try {
            URL loader = getClass().getResource("ajoutNote.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter_note(ActionEvent event) {
    }

    @FXML
    private void versNote(ActionEvent event) {
    }

    @FXML
    private void selection_mat(MouseEvent event) {
    }

    private void init() {
        //table matieres
        nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        cochCol.setCellFactory(callback_fn_select_matiere);
        //table eleves
        idCol.setCellValueFactory(cellData -> cellData.getValue().id_eProperty().asString());
        nompreCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateNaissProperty().asString());
        noteCol.setCellFactory(callback_fn_ajouter_note);
        
    }
    
}
