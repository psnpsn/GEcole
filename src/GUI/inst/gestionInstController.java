/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.inst;

import DAO.DAO;
import DAO.InstituteurDAO;
import GUI.LoginController;
import Models.Instituteur;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class gestionInstController implements Initializable {

    @FXML
    private TableView<Instituteur> tableView;
    @FXML
    private TableColumn<Instituteur, String> nomCol;
    @FXML
    private TableColumn<Instituteur, Date> dateCol;
    @FXML
    private TableColumn<Instituteur, String> sexCol;
    @FXML
    private TableColumn<Instituteur, String> immCol;
    @FXML
    private TableColumn<Instituteur, String> gradeCol;
    @FXML
    private TableColumn<?, ?> modifCol;
    @FXML
    private TableColumn<?, ?> cochCol;
    @FXML
    private JFXTextField idInstituteurF;
    @FXML
    private JFXTextField nomInstituteurF;
    @FXML
    private JFXDatePicker dateNaissF;
    @FXML
    private JFXComboBox<?> classeF;
    @FXML
    private JFXTextField imm;
    @FXML
    private JFXTextField idInstituteurF1;
    
    public static int id_inst_a_editer = -1 ;


    Callback<TableColumn<Instituteur, String>, TableCell<Instituteur, String>> callback_fn_editer_eleve
            = //
            new Callback<TableColumn<Instituteur, String>, TableCell<Instituteur, String>>() {
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
                                Instituteur item = tableView.getSelectionModel().getSelectedItem();
                                if (item != null) {
                                    id_inst_a_editer = item.getId_i();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void click_chercher(ActionEvent event) {
        DAO instdao = new InstituteurDAO();
        ObservableList<Instituteur> masterData = instdao.getAll();
        tableView.getItems().setAll(masterData);
    }

    @FXML
    private void click_supp(ActionEvent event) {
    }

    @FXML
    private void click_supptout(ActionEvent event) {
    }

    @FXML
    private void click_ajouter(ActionEvent event) {
        try {
            URL loader = getClass().getResource("ajoutInst.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {
        nomCol.setCellValueFactory(cellData -> cellData.getValue().fullnomProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateNaissProperty());
        sexCol.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        immCol.setCellValueFactory(cellData -> cellData.getValue().immProperty().asString());
        gradeCol.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
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
    
}
