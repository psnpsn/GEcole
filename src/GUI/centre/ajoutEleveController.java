/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.centre;

import DAO.DAO;
import DAO.EleveDAO;
import DAO.ParentDAO;
import GUI.LoginController;
import GUI.Tests;
import Models.Eleve;
import Models.Parent;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
 * @author Chazzone
 */
public class ajoutEleveController implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private JFXComboBox<String> ville;

    @FXML
    private JFXTextField telP;

    @FXML
    private JFXRadioButton garcon;

    @FXML
    private JFXTextField profP;

    @FXML
    private JFXTextField addresse;

    @FXML
    private JFXTextField profM;

    @FXML
    private JFXRadioButton fille;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField nomM;

    @FXML
    private JFXTextField emailP;

    @FXML
    private JFXDatePicker dnaissance;

    @FXML
    private JFXTextField codepostal;

    @FXML
    private JFXTextField nomP;

    @FXML
    private JFXTextField lnaissance;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;
    @FXML
    private JFXComboBox<Integer> niveau;
    @FXML
    private Label lnom;
    @FXML
    private Label lprenom;
    @FXML
    private Label ldate;
    @FXML
    private Label llieu;
    @FXML
    private Label laddresse;
    @FXML
    private Label lville;
    @FXML
    private Label lniv;
    @FXML
    private Label lemail;
    @FXML
    private Label lpere;
    @FXML
    private Label lppere;
    @FXML
    private Label lmere;
    @FXML
    private Label lpmere;
    @FXML
    private Label ltel;
    @FXML
    private Label lemailp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        garcon.setToggleGroup(group);
        fille.setToggleGroup(group);
        fille.setSelected(true);
        ville.setItems(FXCollections.observableArrayList(
                "Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tatatouine", "Tozeur", "Tunis", "Zaghouan"
        ));
        niveau.setItems(FXCollections.observableArrayList(1,2,3,4,5,6));
    }

    @FXML
    void click_image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image pour l'eleve");
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

    @FXML private void click_ajouter(ActionEvent event) {
        boolean erreur;
        erreur=((Tests.vtel(telP, ltel))&(Tests.vchaine(nom,lnom,20,false))&(Tests.vchaine(prenom,lprenom,20,false))
                &(Tests.vchaine(lnaissance,llieu,20,false))&(Tests.vchaine(addresse,laddresse,40,false))
                &(Tests.vemail(email,lemail))&(Tests.vchaine(nomP,lpere,20,false))&(Tests.vchaine(profP,lppere,20,false))
                &(Tests.vchaine(nomM,lmere,20,false))&(Tests.vchaine(profM,lpmere,20,false))&(Tests.vemail(emailP,lemailp))
                &(Tests.vcodep(codepostal,lville))&(Tests.vdate(dnaissance,ldate))
                );
        
        if (erreur) {
            DAO elevedao = new EleveDAO();
            Eleve eleve = new Eleve();
            // ajout parent
            Parent p = new Parent(-1,nomP.getText(),profP.getText(),nomM.getText(),profM.getText(),telP.getText(),emailP.getText());
            ParentDAO daop = new ParentDAO();
            int id_parent=daop.create(p);
            if (id_parent == -1)
                return;
            eleve.setNom(nom.getText());
            eleve.setPrenom(prenom.getText());
            eleve.setAdresse(addresse.getText());
            eleve.setVille(ville.getSelectionModel().getSelectedItem());
            eleve.setCodeP(Integer.parseInt(codepostal.getText()));
            eleve.setDateNaiss(Date.from(dnaissance.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            eleve.setLieuNaiss(lnaissance.getText());
            if (garcon.isSelected()) {
                eleve.setSex("M");
            } else {
                eleve.setSex("F");
            }
            eleve.setEmail(email.getText());
            eleve.setRef_niv(0);
            eleve.setRef_c(0);
            eleve.setRef_p(id_parent);
            String niv= niveau.getSelectionModel().getSelectedItem()+"2016";
            eleve.setRef_niv(Integer.parseInt(niv));
            int id=elevedao.create(eleve);
            if (id!=-1) {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Success!");
                conf.setHeaderText("L'operation de l'ajout dde l'élève est effectuée avec succés");
                conf.setContentText("L'élève est ajouté"+id+".\n");
                conf.showAndWait();
                reinit();

            } else {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Erreur!");
                conf.setHeaderText("Une erreur s'est produite lors de l'ajout");
                conf.setContentText("Aucun élève n'a été ajouté.");
                conf.showAndWait();
            }
    
        } else {
            System.out.println("Erreur");
            
        }
}


    @FXML private void click_reinitialiser(ActionEvent event) {
        reinit();
    }

    private void reinit() {
        garcon.setSelected(true);
        nom.setText("");
        prenom.setText("");
        ville.getSelectionModel().clearSelection();
        ville.setValue("");
        ville.setPromptText("Ville Naissance");
        niveau.getSelectionModel().clearSelection();
        niveau.setValue(-1);
        niveau.setPromptText("Niveau");
        addresse.setText("");
        codepostal.setText("");
        nomP.setText("");
        profP.setText("");
        nomM.setText("");
        profM.setText("");
        telP.setText("");
        email.setText("");
        lnaissance.setText("");
        emailP.setText("");
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource("../image/default-eleve.jpg"));
            Image i = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(i);
        } catch (IOException x) {
            System.out.println("Erreur : " + x);
        }

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
            URL loader = getClass().getResource("gestionEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
