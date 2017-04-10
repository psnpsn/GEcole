/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.inst;

import DAO.DAO;
import DAO.InstituteurDAO;
import GUI.LoginController;
import GUI.Tests;
import Models.Instituteur;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ajoutInstController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXDatePicker dnaissance;
    @FXML
    private JFXTextField addresse;
    @FXML
    private JFXRadioButton fille;
    @FXML
    private JFXRadioButton garcon;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField tel;
    @FXML
    private ImageView image;
    @FXML
    private JFXTextField codepostal;
    @FXML
    private JFXComboBox<String> ville;
    @FXML
    private JFXTextField ncin;
    @FXML
    private JFXTextField tel2;
    @FXML
    private JFXTextField grade;
    @FXML
    private JFXDatePicker demb;
    @FXML
    private JFXTextField imm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        garcon.setToggleGroup(group);
        fille.setToggleGroup(group);
        garcon.setSelected(true);
        ville.setItems(FXCollections.observableArrayList(
                "Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tatatouine", "Tozeur", "Tunis", "Zaghouan"
        ));
        
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
    private void click_trouver(ActionEvent event) {
        try {
            URL loader = getClass().getResource("gestionInst.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void click_ajouter(ActionEvent event) {
        String erreur = "";
        if (!Tests.email(email.getText())) {
            erreur += "Erreur Email\n";
            email.clear();
            email.setPromptText("Email (*@*.*)");
            email.setStyle("-fx-prompt-text-fill:red");
        }
        if (!Tests.telephone(tel.getText())) {
            erreur += "Erreur Numero Telephone\n";
            tel.clear();
            tel.setPromptText("Téléphone (8 Chiffre)");
            tel.setStyle("-fx-prompt-text-fill:red");
        }
        if (!Tests.telephone(tel2.getText())) {
            erreur += "Erreur Numero Telephone\n";
            tel2.clear();
            tel2.setPromptText("Téléphone (8 Chiffre)");
            tel2.setStyle("-fx-prompt-text-fill:red");
        }
        if (!Tests.chaine(nom.getText(), 20, false)) {
            erreur += "Erreur Nom Eleve\n";
            nom.clear();
            nom.setPromptText("Nom (Que des lettres)");
            nom.setStyle("-fx-prompt-text-fill:red");
        }
        if (!Tests.chaine(prenom.getText(), 20, false)) {
            erreur += "Erreur Prenom Eleve\n";
            prenom.clear();
            prenom.setPromptText("Prenom (Que des lettres)");
            prenom.setStyle("-fx-prompt-text-fill:red");
        }
        if (!Tests.chaine(addresse.getText(), 20, true)) {
            erreur += "Erreur addresse\n";
            addresse.clear();
            addresse.setPromptText("Adresse (Trop long)");
            addresse.setStyle("-fx-prompt-text-fill:red");
        }
        if (!Tests.code_postal(codepostal.getText())) {
            erreur += "Erreur Code Postal\n";
            codepostal.clear();
            codepostal.setPromptText("Code Postal (4 Chiffre seulement)");
            codepostal.setStyle("-fx-prompt-text-fill:red");
        }
        LocalDate d = dnaissance.getValue();
        if (d == null) {
            erreur += "Erreur Date , Selectionner une\n";
            dnaissance.setValue(null);
            dnaissance.setPromptText("Date de Naissance");
            dnaissance.setStyle("-fx-prompt-text-fill:red");
        }
        if (d != null && !Tests.date_naissance(Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
            erreur += "Erreur Date Naissance invalide\n";
            dnaissance.setValue(null);
            dnaissance.setPromptText("Date de Naissance");
            dnaissance.setStyle("-fx-prompt-text-fill:red");
        }
        LocalDate de = demb.getValue();
        if (d == null) {
            erreur += "Erreur Date , Selectionner une\n";
            dnaissance.setValue(null);
            dnaissance.setPromptText("Date de Naissance");
            dnaissance.setStyle("-fx-prompt-text-fill:red");
        }
        if (de != null && !Tests.date_naissance(Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
            erreur += "Erreur Date Naissance invalide\n";
            dnaissance.setValue(null);
            dnaissance.setPromptText("Date de Naissance");
            dnaissance.setStyle("-fx-prompt-text-fill:red");
        }
        if (ville.getSelectionModel().getSelectedIndex() == -1) {
            erreur += "Erreur Ville : selectionner une svp\n";
            ville.setValue("");
            ville.setPromptText("Ville");
            ville.setStyle("-fx-prompt-text-fill:red");
        }
        
        if (!Tests.chaine(grade.getText(), 20, false)) {
            erreur += "Saisir que des lettres";
            grade.clear();
            grade.setPromptText("Grade (Saisir que des lettres)");
            grade.setStyle("-fx-prompt-text-fill:red");
        }
     
        if (!Tests.telephone(imm.getText())) {
            erreur += "Erreur Numero Telephone\n";
            imm.clear();
            imm.setPromptText("Téléphone (8 Chiffre)");
            imm.setStyle("-fx-prompt-text-fill:red");
        }
        if (!Tests.telephone(ncin.getText())) {
            erreur += "Erreur Numero Telephone\n";
            ncin.clear();
            ncin.setPromptText("Téléphone (8 Chiffre)");
            ncin.setStyle("-fx-prompt-text-fill:red");
        }
        if (erreur.isEmpty()) {
            DAO instdao = new InstituteurDAO();
            Instituteur inst = new Instituteur();
            
            inst.setNom(nom.getText());
            inst.setPrenom(prenom.getText());
            inst.setAdresse(addresse.getText());
            inst.setVille(ville.getSelectionModel().getSelectedItem());
            inst.setCodeP(Integer.parseInt(codepostal.getText()));
            inst.setDateNaiss(Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            inst.setDateEmb(Date.from(de.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            if (garcon.isSelected()) {
                inst.setSex("M");
            } else {
                inst.setSex("F");
            }
            inst.setEmail(email.getText());
            inst.setNCIN(Integer.parseInt(ncin.getText()));
            inst.setTel1(Integer.parseInt(tel.getText()));
            inst.setTel2(Integer.parseInt(tel2.getText()));
            inst.setImmatricul(Integer.parseInt(imm.getText()));
            inst.setGrade(grade.getText());
            instdao.create(inst);
            reinit();
            
    }
    }

    @FXML
    private void click_reinitialiser(ActionEvent event) {
        reinit();
        
    }

    @FXML
    private void click_image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image pour l'instituteur");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.png", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(new javafx.stage.Stage());
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image i = SwingFXUtils.toFXImage(bufferedImage, null);
                image.setImage(i);
            } catch (IOException x) {
            }
        }
    }

    private void reinit() {
        garcon.setSelected(true);
        nom.setText("");
        prenom.setText("");
        ville.getSelectionModel().clearSelection();
        ville.setValue("");
        ville.setPromptText("Ville Naissance");
        addresse.setText("");
        codepostal.setText("");
        imm.setText("");
        grade.setText("");
        ncin.setText("");
        tel2.setText("");
        tel.setText("");
        email.setText("");
     
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource("../image/default-inst.jpg"));
            Image i = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(i);
        } catch (IOException x) {
            System.out.println("Erreur : " + x);
        }

    }
    
}
