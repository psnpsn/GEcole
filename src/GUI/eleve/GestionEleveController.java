/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.eleve;

import DAO.DAO;
import DAO.EleveDAO;
import GUI.LoginController;
import Models.Eleve;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
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
import main_pack.Main_class;

public class GestionEleveController implements Initializable {

    @FXML
    private TableView<Eleve> tableView;
    @FXML
    private TableColumn<Eleve, String> nomCol;
    @FXML
    private TableColumn<Eleve, String> dateCol;
    @FXML
    private TableColumn<Eleve, String> sexCol;
    @FXML
    private TableColumn<Eleve, String> dateinsCol;
    @FXML
    private TableColumn<Eleve, String> modifCol;
    @FXML
    private TableColumn<Eleve, String> cochCol,idCol;

    private ObservableList<Eleve> masterData = FXCollections.observableArrayList();
    @FXML
    private JFXTextField idEleveF;
    @FXML
    private JFXTextField nomEleveF;
    @FXML
    private JFXTextField dateNaissF;
    @FXML
    private JFXTextField dateInsF;

private ArrayList<Integer> selected_ids = new ArrayList<Integer>();

Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>> callback_fn_editer_eleve = new Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>>() {
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
                                Eleve item = tableView.getSelectionModel().getSelectedItem();
                                if (item != null) {
                                    Node source = (Node) event.getSource();
                                    Scene scene = (Scene) source.getScene();
                                    BorderPane border = (BorderPane) scene.getRoot();
                                    try {//
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("ajoutEleve.fxml"));
                                        Parent root = loader.load();
                                        ajoutEleveController controller = loader.getController();
                                        border.setCenter((AnchorPane) root);
                                        if (controller != null) {
                                            controller.edit_eleve(item.getId_e());
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
Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>> callback_fn_select_eleve = new Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>>() {
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        
    }

        @FXML
    private void user_selection(MouseEvent event) {
        update_selection();
    }

    private void update_selection() {
        tableView.getSelectionModel().clearSelection();
        for (int i = 0; i < selected_ids.size(); i++) {
            tableView.getSelectionModel().select(selected_ids.get(i));
        }
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


    private void refresh(){
            click_chercher(new ActionEvent(nomCol,nomCol));
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
        tableView.setItems(masterData);
        
        nomEleveF.setText("");
        idEleveF.setText("");
        dateNaissF.setText("");
        dateInsF.setText("");
        
        
        

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

         dateNaissF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eleve -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String idFilter = newValue;

                if (eleve.dateNaissProperty().toString().contains(idFilter)){
                    return true;
                }
                return false;
            });
        });
         
         dateInsF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(eleve -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String idFilter = newValue;

                if (eleve.dateInsProperty().toString().contains(idFilter)){
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
        if (tableView.getSelectionModel().getSelectedItems().size() > 0) {
            EleveDAO dao = new EleveDAO();
            ObservableList<Eleve> liste = tableView.getSelectionModel().getSelectedItems();
            for (Eleve l : liste) {
                System.out.println("suppression de "+l.getId_e()+" ..");
                dao.delete(l.getId_e());
                System.out.println("suppression ok");
                try {
                    Path path = FileSystems.getDefault().getPath("data/eleve/" + l.getId_e() + ".png");
                    Files.deleteIfExists(path);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
            refresh();
        }

    }

    @FXML
    private void click_supptout(ActionEvent event) {
        EleveDAO dao = new EleveDAO();
        dao.delAll();
        refresh();
        try {
            Files.walk(Paths.get("data/eleve/"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    private void initCol() {
        tableView.getSelectionModel().setCellSelectionEnabled(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nomCol.setCellValueFactory(cellData -> cellData.getValue().fullnomProperty());
        dateCol.setCellValueFactory(cellData -> {
            Date d = cellData.getValue().getDateNaiss();
          return new SimpleStringProperty(String.valueOf(d).substring(0,String.valueOf(d).indexOf(" ")));  
        });
        sexCol.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        
        dateinsCol.setCellValueFactory(cellData -> {
          return new SimpleStringProperty(cellData.getValue().getSDateIns().substring(0,cellData.getValue().getSDateIns().indexOf(" ")));  
        });
        
        modifCol.setCellFactory(callback_fn_editer_eleve);
        cochCol.setCellFactory(callback_fn_select_eleve);
        
        idCol.setCellValueFactory( cellData -> {
            return new SimpleStringProperty(""+cellData.getValue().getId_e());
        });


    }



    }





