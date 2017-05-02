/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.matiere;

import DAO.MatiereDAO;
import GUI.LoginController;
import GUI.Tests;
import Models.Matiere;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ajoutMatiereController implements Initializable {

    @FXML
    private JFXButton action;
    @FXML
    private JFXTextField coef;
    @FXML
    private JFXTextField nom;
    @FXML
    private Label lnom;
    @FXML
    private Label lcoef;
    @FXML
    private JFXTextArea desc;
    private int id_mat=-1;
    @FXML
    private Label idLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
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
        if (val()){
            Matiere mat=new Matiere();
            mat.setNom(nom.getText());
            mat.setCoef(Float.parseFloat(coef.getText()));
            mat.setDesc(desc.getText());
            MatiereDAO matdao= new MatiereDAO();
            id_mat = matdao.create(mat);
        }
        if (id_mat!=-1) {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Success!");
                conf.setHeaderText("L'operation de l'ajout de la matière est effectuée avec succés");
                conf.setContentText("La matière est ajoutée avec l'id "+id_mat+".\n");
                conf.showAndWait();
                
                
               
            } else if(!val()) {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Erreur!");
                conf.setHeaderText("Une erreur s'est produite lors de l'ajout de la matière.");
                conf.setContentText("Aucune matière n'a été ajouté.");
                conf.showAndWait();
            }

    }

    @FXML
    private void reinit(ActionEvent event) {
        nom.setText("");
        coef.setText("");
        desc.setText("");
        init();
    }

    private void init() {
        lcoef.setVisible(false);
        lcoef.setText("");
        lnom.setVisible(false);
        lnom.setText("");
    }

    private boolean val() {
        boolean v=false;
        v=Tests.txt_field(nom, lnom, 30, false, false)
                
                ;
        return v;
    }
    
    public void edit_matiere(int x){
        action.setText("Modifier Matiere");
        idLabel.setVisible(true);
        idLabel.setText(idLabel.getText()+x);
        id_mat = x;
        action.setOnAction((e) -> {
            update_matiere(x);
        });
        MatiereDAO dao = new MatiereDAO();
        Matiere mat = (Matiere) dao.find(x);
        nom.setText(mat.getNom());
        desc.setText(mat.getDesc());
        coef.setText(mat.getCoef()+"");
        
    }

    private void update_matiere(int x) {
        if (val()){
            MatiereDAO dao = new MatiereDAO();
            Matiere mat = new Matiere();
            mat.setId_m(x);
            mat.setNom(nom.getText());
            mat.setCoef(Float.parseFloat(coef.getText()));
            mat.setDesc(desc.getText());
            if (dao.update(mat)) {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Success!");
                conf.setHeaderText("L'operation de la modification de la matière est effectuée avec succés");
                conf.setContentText("La matière est modifiée .\n");
                conf.showAndWait();
                
                
               
            } else {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Erreur!");
                conf.setHeaderText("Une erreur s'est produite lors de la modification de la matière.");
                conf.setContentText("Aucune matière n'a été modifié.");
                conf.showAndWait();
            }
        }
    }
    
    
    
}
