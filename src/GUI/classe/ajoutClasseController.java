/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.classe;

import DAO.ClasseDAO;
import DAO.DAO;
import Models.Classe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author h2oo2
 */
public class ajoutClasseController implements Initializable {

    @FXML
    private JFXButton action;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField capacite;
    @FXML
    private JFXComboBox<String> niveau;
    @FXML
    private Label lnom;
    @FXML
    private Label lcapacite;
    @FXML
    private Label lniveau;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        niveau.getItems().addAll("1","2","3","4","5","6");
        niveau.getSelectionModel().select(0);
        // TODO
    }    

    @FXML
    private void click_retour(ActionEvent event) {
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
    private void click_trouver(ActionEvent event) {
                Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("gestionClasse.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    private boolean saisie_valide(){
        if (nom.getText().isEmpty()){
            lnom.setText("nom de classe ne doit pas etre vide");
            lnom.setVisible(true);
            return false;
        }
        try {
            int n = Integer.parseInt(capacite.getText());
            if (n<1 || n > 99){
            lcapacite.setVisible(true);
            lcapacite.setText("la capacite d'une classe doit etre dans l'intervalle 1 .. 99");
            return false;
            }
            
        }catch(Exception ex){
            lcapacite.setVisible(true);
            lcapacite.setText("la capacite d'une classe doit etre un nombre");
            return false;
        }
        return true;
    }
    @FXML
    private void click_ajouter_classe(ActionEvent event) {
        init_labls();
        DAO dao = new ClasseDAO();
        Classe c = new Classe();
        c.setCapacite(Integer.parseInt(capacite.getText()));
        c.setNom(nom.getText());
        c.setRef_niv(Integer.parseInt(niveau.getSelectionModel().getSelectedItem()+"2017"));
        
        if (saisie_valide()) {
            int rs = dao.create(c);
            System.out.println("rs = " + rs);
            if (rs != -1) {
                click_trouver(event);
            }
        }
    }

    void edit_classe(int id_c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init_labls(){
        lnom.setVisible(false);
        lcapacite.setVisible(false);
        lniveau.setVisible(false);
    }
    @FXML
    private void click_reinitialiser(ActionEvent event) {
        nom.setText("");
        capacite.setText("");
        niveau.getSelectionModel().select(0);
        init_labls();
    }
    
}
