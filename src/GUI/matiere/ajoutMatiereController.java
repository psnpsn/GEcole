/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.matiere;

import DAO.MatiereDAO;
import DAO.ModuleDAO;
import Models.Matiere;
import Models.Module;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main_pack.Main_class;

public class ajoutMatiereController implements Initializable {

    @FXML private JFXButton action;
    @FXML private JFXTextField coef,nom;
    @FXML private JFXTextArea desc;
    @FXML private Label lnom,lcoef;
    @FXML private JFXComboBox<String> module;
    
    ArrayList<Integer> ids_mods = new ArrayList<Integer>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
       init_mods();
       module.getSelectionModel().select(0);
    }    

    @FXML
    private void goto_admin_main(ActionEvent event) {
        try {
            URL loader = getClass().getResource("../mainwindow.fxml");
            AnchorPane middle = FXMLLoader.load(loader);
            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(ajoutMatiereController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ajoutMatiereController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ajoutMatiereController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML private void listMat(ActionEvent event) {
        try {
            URL loader = getClass().getResource("listMatiere.fxml");
            AnchorPane middle = FXMLLoader.load(loader);
            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(ajoutMatiereController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML private void ajoutMat(ActionEvent event) {
        try {
            URL loader = getClass().getResource("ajoutMatiere.fxml");
            AnchorPane middle = FXMLLoader.load(loader);
            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(ajoutMatiereController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML private void ajouter_matiere(ActionEvent event) {
        Matiere mat = new Matiere();
        mat.setNom(nom.getText());
        mat.setCoef(Float.parseFloat(coef.getText()));
        mat.setDesc(desc.getText());
        mat.setRef_module(ids_mods.get(module.getSelectionModel().getSelectedIndex()));
        MatiereDAO matdao = new MatiereDAO();
       int id_mat = matdao.create(mat);
       if (id_mat!=-1){
        System.out.println("Identifiant matiere = " + id_mat);
           listMat(event);
       }else
            System.out.println("Erreur Lors de l'ajout matiere");
    }

    @FXML
    private void reinit(ActionEvent event) {
        nom.setText("");
        coef.setText("");
        desc.setText("");
        init();
    }

    void edit_matiere(int id_m) {
        action.setText("Modifier Matiere");
        action.setOnAction((e) -> {
            update_matiere(id_m);
        });
        MatiereDAO dao = new MatiereDAO();
        Matiere mat = dao.find(id_m);
        if (mat != null) {
            nom.setText(mat.getNom());
            desc.setText(mat.getDesc());
            coef.setText(mat.getCoef() + "");
        }
    }
    private void update_matiere(int x) {
        MatiereDAO dao = new MatiereDAO();
        Matiere mat = new Matiere();
        mat.setId_m(x);
        mat.setNom(nom.getText());
        mat.setCoef(Float.parseFloat(coef.getText()));
        mat.setDesc(desc.getText());
        if (dao.update(mat)) {

        }
    }
    private void init() {
        lcoef.setVisible(false);
        lcoef.setText("");
        lnom.setVisible(false);
        lnom.setText("");
    }

    private void init_mods() {
        ModuleDAO dao = new ModuleDAO();
        ObservableList<Module> all = dao.getAll();
        if (all.size() != 0) {
            for (Module m : all) {
                module.getItems().add(m.getNom());
                ids_mods.add(m.getId());
            }
        } else {
            Alert conf = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            conf.setTitle("Erreur ajout matiere");
            conf.setHeaderText("Aucun Module trouve pour associer des matieres");
            conf.setContentText("Chaque matiere doit appartenir a un module"
                    + "\nL'ajout sera desactive jusqu'a au mois un Module est Crees.");
            conf.showAndWait();
            action.setStyle("-fx-strikethrough: true;");
            action.setDisable(true);
        }
    }
}
