package GUI.inst;

import DAO.InstituteurDAO;
import Models.Instituteur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class gestionInstController implements Initializable {

    @FXML
    private TableView<Instituteur> table_instituteur;
    @FXML
    private TableColumn<Instituteur, String> colonne_nom;
    @FXML
    private TableColumn<Instituteur, String> colonne_date_embauchement;
    @FXML
    private TableColumn<Instituteur, String> colonne_sex;
    @FXML
    private TableColumn<Instituteur, String> colonne_matricule;
    @FXML
    private TableColumn<Instituteur, String> colonne_grade;
    @FXML
    private TableColumn<Instituteur, String> colonne_modifier;
    @FXML
    private TableColumn<Instituteur, String> colonne_cocher;
    @FXML
    private TableColumn<Instituteur, String> colonne_identifiant;
    @FXML
    private JFXTextField identifiant;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXDatePicker date_embauchement;
    @FXML
    private JFXComboBox<String> grade;
    @FXML
    private JFXTextField numero_matricule;

    private ObservableList<Instituteur> data = FXCollections.observableArrayList();
    private ArrayList<Integer> selected_ids = new ArrayList<Integer>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table_instituteur.getSelectionModel().setCellSelectionEnabled(false);
        table_instituteur.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colonne_nom.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getNom());
        });
        colonne_date_embauchement.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDateEmb().toString());
        });
        colonne_identifiant.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(""+cellData.getValue().getId_i());
        });
        colonne_sex.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getSex());
        });
        colonne_grade.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getGrade());
        });
        colonne_matricule.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(""+cellData.getValue().getImmatricul());
        });
        colonne_modifier.setCellFactory(callback_fn_editer_instituteur);
        colonne_cocher.setCellFactory(callback_fn_select_instituteur);
        
        refresh();init_filters();
    }

    Callback<TableColumn<Instituteur, String>, TableCell<Instituteur, String>> callback_fn_select_instituteur = new Callback<TableColumn<Instituteur, String>, TableCell<Instituteur, String>>() {
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

     Callback<TableColumn<Instituteur, String>, TableCell<Instituteur, String>> callback_fn_editer_instituteur = new Callback<TableColumn<Instituteur, String>, TableCell<Instituteur, String>>() {
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
                                Instituteur item = table_instituteur.getSelectionModel().getSelectedItem();
                                if (item != null) {
                                    Node source = (Node) event.getSource();
                                    Scene scene = (Scene) source.getScene();
                                    BorderPane border = (BorderPane) scene.getRoot();
                                    try {//
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("ajoutInst.fxml"));
                                        Parent root = loader.load();
                                        GUI.inst.ajoutInstController controller = loader.getController();
                                        border.setCenter((AnchorPane) root);
                                        if (controller != null) {
                                            controller.edit_instituteur(item.getId_i());
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
    @FXML private void goto_admin_main(ActionEvent event) {
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
    private void goto_ajouter_instituteur(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("ajoutInst.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void chercher_instituteur(ActionEvent event) {}
    private void init_filters(){
         InstituteurDAO sdao = new InstituteurDAO();
        data = sdao.getAll();
        table_instituteur.setItems(data);
        nom.setText("");
        identifiant.setText("");
        //date_embauchement.setText("");
        nom.setText("");
        
        FilteredList<Instituteur> filteredData = new FilteredList<>(data, p -> true);
        
        identifiant.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(inst -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String fullnameFilter = newValue.toLowerCase();
                if (String.valueOf(inst.getId_i()).toLowerCase().contains(fullnameFilter)) {
                    return true;
                } 
                return false;
            });
        });
        nom.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(salle -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String fullnameFilter = newValue.toLowerCase();
                if (salle.getNom().toLowerCase().contains(fullnameFilter.toLowerCase()) || salle.getPrenom().toLowerCase().contains(fullnameFilter.toLowerCase())) {
                    return true;
                } 
                return false;
            });
        });
       /* capacite.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(salle -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String fullnameFilter = newValue.toLowerCase();
                if (String.valueOf(salle.getCapacite()).toLowerCase().contains(fullnameFilter)) {
                    return true;
                } 
                return false;
            });
        });
         dateS.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(salle -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String fullnameFilter = newValue.toLowerCase();
                if (String.valueOf(salle.getDate_creation()).toLowerCase().contains(fullnameFilter)) {
                    return true;
                } 
                return false;
            });
        });*/
        SortedList<Instituteur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_instituteur.comparatorProperty());
        table_instituteur.setItems(sortedData);
    }

    @FXML
    private void supprimer_instituteur(ActionEvent event) {
        if (table_instituteur.getSelectionModel().getSelectedItems().size() > 0) {
            InstituteurDAO dao = new InstituteurDAO();
            ObservableList<Instituteur> liste = table_instituteur.getSelectionModel().getSelectedItems();
            for (Instituteur l : liste) {
                dao.delete(l.getId_i());
                try {
                    Path path = FileSystems.getDefault().getPath("data/instituteur/" + l.getId_i() + ".png");
                    Files.deleteIfExists(path);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
            refresh();
        }
    }

    @FXML
    private void supprimer_instituteurs(ActionEvent event) {
        InstituteurDAO dao = new InstituteurDAO();
        dao.delAll();
        refresh();
        try {
            Files.walk(Paths.get("data/instituteur/"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
    @FXML
    private void user_selection(MouseEvent event) {
        update_selection();
    }

    private void update_selection() {
        table_instituteur.getSelectionModel().clearSelection();
        for (int i = 0; i < selected_ids.size(); i++) {
            table_instituteur.getSelectionModel().select(selected_ids.get(i));
        }
    }
    private void refresh() {
        InstituteurDAO dao = new InstituteurDAO();
        table_instituteur.getItems().clear();
        data = (ObservableList<Instituteur>) dao.getAll();
        table_instituteur.setItems(data);
    }
}
