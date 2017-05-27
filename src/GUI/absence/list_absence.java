
package GUI.absence;

import DAO.AbsenceDAO;
import DAO.EleveDAO;
import Models.Absence;
import Models.Eleve;
import Models.Salle;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class list_absence implements Initializable {

    @FXML private TableColumn<Absence, String> colonne_identifiant;
    @FXML private TableColumn<Absence, String> colonne_nom;
    @FXML private TableColumn<Absence, String> colonne_dateabs;
    @FXML private TableColumn<Absence, String> colonne_seance;
    @FXML private TableColumn<Absence, String> colonne_cocher;
    @FXML private JFXTextField nom;
    @FXML private JFXTextField dateabs;
    @FXML private TableView<Absence> table_abs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {      
        
        table_abs.getSelectionModel().setCellSelectionEnabled(false);
        table_abs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colonne_cocher.setCellFactory(callback_fn_select);
        colonne_identifiant.setCellValueFactory(cellData -> {
           return new SimpleStringProperty(String.valueOf(cellData.getValue().getId_absence()));
       });
        colonne_nom.setCellValueFactory(value -> {
            Eleve e = new EleveDAO().find(value.getValue().getRef_e());
            if (e != null) {
                return new SimpleStringProperty(e.getNom() + " " + e.getPrenom());
            } else {
                return new SimpleStringProperty("");
            }
        });        
        colonne_dateabs.setCellValueFactory(value -> {
            String rs = value.getValue().getDate_abs().toString();
            return new SimpleStringProperty(rs.substring(0, rs.indexOf(" ")));
        });
        colonne_seance.setCellValueFactory(value -> {
            int id = value.getValue().getRef_sc();
            switch (id) {
                case  0: return new SimpleStringProperty("8h a 10h");             
                case  1: return new SimpleStringProperty("10h a 12h");
                case  2: return new SimpleStringProperty("14h a 16h");
                case  3: return new SimpleStringProperty("16h a 18h");
             
                default:
                    return new SimpleStringProperty(" . ");
            }
        });
        table_abs.getItems().setAll(new AbsenceDAO().getAll());
        
        
        FilteredList<Absence> filteredData = new FilteredList<>(table_abs.getItems(), p -> true);
        
        nom.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(x -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String f = newValue.toLowerCase();
                Eleve e = new EleveDAO().find(x.getRef_e());
                if (e != null && (e.getNom().contains(f) || e.getPrenom().contains(f))) {
                    return true;
                }
                return false;
            });
        });      
         dateabs.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(salle -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String f = newValue.toLowerCase();
                if (String.valueOf(salle.getDate_abs()).toLowerCase().contains(f)) {
                    return true;
                } 
                return false;
            });
        });
        SortedList<Absence> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_abs.comparatorProperty());
        table_abs.setItems(sortedData);
        
    }

    @FXML
    private void goto_admin_main(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("../mainwindow.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void goto_lister_abs(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("list_absence.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void goto_ajouter_abs(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("add_absence.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void supprimer_abs(ActionEvent event) {
            if (table_abs.getSelectionModel().getSelectedItems().size() > 0) {
            ObservableList<Absence> liste = table_abs.getSelectionModel().getSelectedItems();
            liste.forEach((l) -> {
                new AbsenceDAO().delete(l.getId_absence());
            });
            goto_lister_abs(event);
        }
    }

    @FXML
    private void supprimer_abses(ActionEvent event) {
        new AbsenceDAO().delAll();
        goto_lister_abs(event);
    }

    @FXML
    private void user_selection(MouseEvent event) {
        update_selection();
    }

    private void update_selection() {
        table_abs.getSelectionModel().clearSelection();
        for (int i = 0; i < selected_ids.size(); i++) {
            table_abs.getSelectionModel().select(selected_ids.get(i));
        }
    }

    private void refresh() {
            table_abs.getItems().clear();
            table_abs.setItems(new AbsenceDAO().getAll());
    }
    
    private ArrayList<Integer> selected_ids = new ArrayList<Integer>();

    Callback<TableColumn<Absence, String>, TableCell<Absence, String>> callback_fn_select = new Callback<TableColumn<Absence, String>, TableCell<Absence, String>>() {
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