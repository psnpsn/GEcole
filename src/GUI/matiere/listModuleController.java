package GUI.matiere;

import DAO.ModuleDAO;
import GUI.LoginController;
import Models.Module;
import Models.Salle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main_pack.Main_class;

public class listModuleController implements Initializable {

    @FXML
    private TableView<Module> tableView;
    @FXML
    private TableColumn<Module, String> modCol;
    @FXML
    private TableColumn<Module, String> nom;
    @FXML
    private TableColumn<Module, String> id;
    @FXML
    private TableColumn<Module, String> niveau;
    @FXML
    private TableColumn<Module, String> nbr_matieres;
    @FXML
    private TableColumn<Module, String> total_coef;
    @FXML
    private TableColumn<Module, String> cocher;
    
    private ObservableList<Module> data = null;
    private ArrayList<Integer> selected_ids = new ArrayList<Integer>();


    private void refresh() {
            ModuleDAO dao = new ModuleDAO();
            tableView.getItems().clear();
            data =  dao.getAll();
            tableView.setItems(data);
    }
    @Override public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        tableView.getSelectionModel().setCellSelectionEnabled(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        id.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(""+cellData.getValue().getId());
        });
        nom.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getNom());
        });
        niveau.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(""+String.valueOf(cellData.getValue().getRef_niv()).charAt(0));
        });
        nbr_matieres.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(""+String.valueOf(ModuleDAO.nbr_matiere(cellData.getValue().getId())));
        });
        total_coef.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(""+String.valueOf(ModuleDAO.total_coefs(cellData.getValue().getId())));
        });
        cocher.setCellFactory(callback_fn_select_module);
        modCol.setCellFactory(callback_fn_editer_salle);
        refresh();
    }    
Callback<TableColumn<Module, String>, TableCell<Module, String>> callback_fn_editer_salle = new Callback<TableColumn<Module, String>, TableCell<Module, String>>() {
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
                        final JFXButton editer = new JFXButton("<Modifier>");
                        editer.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                param.getTableView().getSelectionModel().select(getIndex());
                                Module item = tableView.getSelectionModel().getSelectedItem();
                                if (item != null) {
                                    Node source = (Node) event.getSource();
                                    Scene scene = (Scene) source.getScene();
                                    BorderPane border = (BorderPane) scene.getRoot();
                                    try {//
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("ajoutModule.fxml"));
                                        Parent root = loader.load();
                                        GUI.matiere.ajoutModuleController controller = loader.getController();
                                        border.setCenter((AnchorPane) root);
                                        if (controller != null) {
                                            controller.edit_module(item.getId());
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
    @FXML
    private void goto_admin_main(ActionEvent event) {
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
    private void listMod(ActionEvent event) {
         try {
            URL loader = getClass().getResource("listModule.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ajoutMod(ActionEvent event) {
         try {
            URL loader = getClass().getResource("ajoutModule.fxml");
            AnchorPane middle = FXMLLoader.load(loader);
            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private boolean user_is_sure() {
        Alert conf;
        conf = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES, ButtonType.NO);
        conf.setTitle("Confirmer suppression de/des Module(s)!");
        conf.setHeaderText("Etes Vous sure de vouloir supprimer ce/ces Module(s) ?");
        conf.setContentText("La suppression d'un module supprimera tous les matieres qui lui sont associer");
        conf.showAndWait();
        if (conf.getResult() == ButtonType.YES){
            return true;
        }
        else return false;
    }
    
    
    @FXML
    private void supprimer(ActionEvent event) {
        if (user_is_sure()) {
            if (tableView.getSelectionModel().getSelectedItems().size() > 0) {
                ModuleDAO dao = new ModuleDAO();
                ObservableList<Module> liste = tableView.getSelectionModel().getSelectedItems();
                liste.forEach((l) -> {
                    dao.delete(l.getId());
                });
                listMod(event);
            }
        }
    }

    @FXML
    private void supprimerTout(ActionEvent event) {
        if (user_is_sure()) {
            ModuleDAO dao = new ModuleDAO();
            dao.delAll();
            listMod(event);
        }
    }

    @FXML
    private void user_selection(MouseEvent event) {
        tableView.getSelectionModel().clearSelection();
        for (int i = 0; i < selected_ids.size(); i++) {
            tableView.getSelectionModel().select(selected_ids.get(i));
        }
    }
        
    private void update_selection() {
        tableView.getSelectionModel().clearSelection();
        for (int i = 0; i < selected_ids.size(); i++) {
            tableView.getSelectionModel().select(selected_ids.get(i));
        }
    }
    
    Callback<TableColumn<Module, String>, TableCell<Module, String>> callback_fn_select_module = new Callback<TableColumn<Module, String>, TableCell<Module, String>>() {
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
}
