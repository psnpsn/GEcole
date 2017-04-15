package GUI.salle;

import DAO.SalleDAO;
import Models.Salle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class list_salle implements Initializable {

    @FXML
    private TableView<Salle> table_salle;
    @FXML
    private TableColumn<Salle,String> colonne_identifiant;
    @FXML
    private TableColumn<Salle,String> colonne_nom;
    @FXML
    private TableColumn<Salle,String> colonne_capacite;
    @FXML
    private TableColumn<Salle,String> colonne_modifier;
    @FXML
    private TableColumn<Salle,String> colonne_cocher;
    private ObservableList<Salle> data = FXCollections.observableArrayList();
    private ArrayList<Integer> selected_ids = new ArrayList<Integer>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table_salle.getSelectionModel().setCellSelectionEnabled(false);
        table_salle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colonne_cocher.setCellFactory(callback_fn_select_salle);
        colonne_modifier.setCellFactory(callback_fn_editer_salle);
        colonne_identifiant.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(""+cellData.getValue().getIdentifiant());
        });
        colonne_nom.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getType_salle());
        });
        colonne_capacite.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(""+cellData.getValue().getCapacite());
        });
        refresh();
    }
Callback<TableColumn<Salle, String>, TableCell<Salle, String>> callback_fn_editer_salle = new Callback<TableColumn<Salle, String>, TableCell<Salle, String>>() {
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
                                Salle item = table_salle.getSelectionModel().getSelectedItem();
                                if (item != null) {
                                    Node source = (Node) event.getSource();
                                    Scene scene = (Scene) source.getScene();
                                    BorderPane border = (BorderPane) scene.getRoot();
                                    try {//
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("add.fxml"));
                                        Parent root = loader.load();
                                        GUI.salle.add_salle controller = loader.getController();
                                        border.setCenter((AnchorPane) root);
                                        if (controller != null) {
                                            controller.edit_salle(item.getIdentifiant());
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
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("../main_screen/admin_main.fxml")));
        }catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

     Callback<TableColumn<Salle, String>, TableCell<Salle, String>> callback_fn_select_salle = new Callback<TableColumn<Salle, String>, TableCell<Salle, String>>() {
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
    private void goto_ajouter_salle(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("add.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void chercher_salle(ActionEvent event) {
    }

    @FXML
    private void supprimer_salle(ActionEvent event) {
        if (table_salle.getSelectionModel().getSelectedItems().size() > 0) {
            SalleDAO dao = new SalleDAO();
            ObservableList<Salle> liste = table_salle.getSelectionModel().getSelectedItems();
            liste.forEach((l) -> {
                dao.delete(l.getIdentifiant());
            });
            refresh();
        }
    }

    @FXML
    private void supprimer_salles(ActionEvent event) {
        SalleDAO dao = new SalleDAO();
        dao.delAll();
        table_salle.getItems().clear();
        data = (ObservableList<Salle>) dao.getAll();
        table_salle.setItems(data);
    }

    @FXML
    private void user_selection(MouseEvent event) {
        update_selection();
    }

    private void update_selection() {
        table_salle.getSelectionModel().clearSelection();
        for (int i = 0; i < selected_ids.size(); i++) {
            table_salle.getSelectionModel().select(selected_ids.get(i));
        }
    }

    private void refresh() {
        SalleDAO dao = new SalleDAO();
        table_salle.getItems().clear();
        data = (ObservableList<Salle>) dao.getAll();
        table_salle.setItems(data);
    }
}
