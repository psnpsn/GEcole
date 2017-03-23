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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class listInstController implements Initializable {

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
    private JFXTextField idEleveF;
    @FXML
    private JFXTextField nomEleveF;
    @FXML
    private JFXDatePicker dateNaissF;
    @FXML
    private JFXComboBox<?> classeF;
    @FXML
    private JFXTextField imm;
    @FXML
    private JFXTextField idEleveF1;

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
    
}
