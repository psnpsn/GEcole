/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.matiere;

import DAO.ModuleDAO;
import GUI.LoginController;
import Models.Module;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main_pack.Main_class;

public class ajoutModuleController implements Initializable {

    @FXML
    private JFXButton action;
    @FXML
    private Label lcoef;
    @FXML
    private JFXTextField nom;
    @FXML
    private Label lnom;
    @FXML
    private JFXComboBox<String> niveau;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        niveau.getItems().addAll("1","2","3","4","5","6");
        niveau.getSelectionModel().select(0);
    }

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
    private void ajouter_matiere(ActionEvent event) {
        Module m = new Module();
        m.setNom(nom.getText());
        m.setRef_niv(Integer.parseInt(niveau.getSelectionModel().getSelectedItem() + "2017"));
        ModuleDAO dao = new ModuleDAO();
        if (dao.create(m) != 1) {
            listMod(event);
        }
    }

    @FXML
    private void reinit(ActionEvent event) {
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
    
    void edit_module(int id) {
        action.setText("Modifier Module");
        action.setOnAction((e) -> {
            update_module(id);
        });
        ModuleDAO dao = new ModuleDAO();
        Module mod = dao.find(id);
        if (mod != null) {
            nom.setText(mod.getNom());
            niveau.setValue("" + String.valueOf(mod.getRef_niv()).charAt(0));
        }
    }
    private void update_module(int x) {
        if (x != -1) {
            ModuleDAO dao = new ModuleDAO();
            Module mod = new Module();
            mod.setRef_niv(Integer.parseInt(niveau.getSelectionModel().getSelectedItem() + "2017"));
            mod.setId(x);
            mod.setNom(nom.getText());
            dao.update(mod);
            listMod(new ActionEvent(action, action));
        }
    }
}
