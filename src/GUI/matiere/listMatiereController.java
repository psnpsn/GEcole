package GUI.matiere;

import DAO.MatiereDAO;
import GUI.LoginController;
import Models.Matiere;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class listMatiereController implements Initializable {

    @FXML
    private TableView<Matiere> tableView;
    @FXML
    private TableColumn<Matiere, String> idCol;
    @FXML
    private TableColumn<Matiere, String> nomCol;
    @FXML
    private TableColumn<Matiere, String> coefCol;
    @FXML
    private TableColumn<Matiere, String> descCol,modCol;
    @FXML
    private TableColumn<Matiere, String> cochCol;
    @FXML
    private JFXTextField identifiant;
    @FXML
    private JFXTextField nom;
    
    public static int id_matiere_a_editer = -1 ;
    
     private ObservableList<Matiere> masterData = FXCollections.observableArrayList();
     
     private ArrayList<Integer> selected_ids = new ArrayList<Integer>();

     Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>> callback_fn_editer_matiere = new Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>>() {
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
                        final Button editer = new Button("<Modifier>");
                        editer.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                param.getTableView().getSelectionModel().select(getIndex());
                                Matiere item = tableView.getSelectionModel().getSelectedItem();
                                if (item != null) {
                                    Node source = (Node) event.getSource();
                                    Scene scene = (Scene) source.getScene();
                                    BorderPane border = (BorderPane) scene.getRoot();
                                    try {//
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("ajoutMatiere.fxml"));
                                        Parent root = loader.load();
                                        GUI.matiere.ajoutMatiereController controller = loader.getController();
                                        border.setCenter((AnchorPane) root);
                                        if (controller != null) {
                                            controller.edit_matiere(item.getId_m());
                                        }
                                        else System.out.println("nul: ");
                                    } catch (Exception exception) {
                                        System.out.println("erreur i/o: " + exception);
                                    }
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
    @FXML
    private TableColumn<?, ?> moduleCol;
    @FXML
    private TableColumn<?, ?> modifol;
    @FXML
    private JFXComboBox<?> module;
     
     private void update_selection() {
        tableView.getSelectionModel().clearSelection();
        for (int i = 0; i < selected_ids.size(); i++) {
            tableView.getSelectionModel().select(selected_ids.get(i));
        }
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
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
    private void user_selection(MouseEvent event) {
    }

    @FXML
    private void listMat(ActionEvent event) {
        try {
            URL loader = getClass().getResource("listMatiere.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajoutMat(ActionEvent event) {
        try {
            URL loader = getClass().getResource("ajoutMatiere.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chercher(ActionEvent event) {
        refresh();
        FilteredList<Matiere> filteredData = new FilteredList<>(masterData, p -> true);

         nom.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eleve -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String fullnameFilter = newValue.toLowerCase();

                if (eleve.getNom().toLowerCase().contains(fullnameFilter)) {
                    return true;
                } 
                return false;
            });
        });
         identifiant.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eleve -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String idFilter = newValue;
                String ids = eleve.getId_m()+"";

                if (ids.contains(idFilter)){
                    return true;
                }
                return false;
            });
        });

         // 3. Wrap the FilteredList in a SortedList.
        SortedList<Matiere> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItems().size() > 0) {
            MatiereDAO dao = new MatiereDAO();
            ObservableList<Matiere> liste = tableView.getSelectionModel().getSelectedItems();
            liste.forEach((l) -> {
                dao.delete(l.getId_m());
            });
            refresh();
        }
    }

    @FXML
    private void supprimerTout(ActionEvent event) {
        MatiereDAO dao = new MatiereDAO();
        dao.delAll();
        tableView.getItems().clear();
        masterData = (ObservableList<Matiere>) dao.getAll();
        tableView.setItems(masterData);
    }

    private void initCol() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
         coefCol.setCellValueFactory(cellData -> cellData.getValue().coefProperty().asString());
         descCol.setCellValueFactory(cellData -> cellData.getValue().descProperty());
         modCol.setCellFactory(callback_fn_editer_matiere);
      //  cochCol.setCellFactory(callback_fn_select_matiere);
    }
    
    private void refresh() {
        MatiereDAO dao = new MatiereDAO();
        tableView.getItems().clear();
        masterData = (ObservableList<Matiere>) dao.getAll();
        tableView.setItems(masterData);
    }

    @FXML
    private void listMod(ActionEvent event) {
    }

    @FXML
    private void ajoutMod(ActionEvent event) {
    }
    
}
