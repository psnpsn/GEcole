/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.centre;

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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class modEleveController implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private JFXComboBox<String> ville;

    @FXML
    private JFXRadioButton garcon;

    @FXML
    private JFXTextField nomP;

    @FXML
    private JFXTextField addresse;

    @FXML
    private JFXRadioButton fille;

    @FXML
    private JFXTextField nomM;

    @FXML
    private JFXTextField nom, telP;

    @FXML
    private JFXTextField profM, lnaissance;

    @FXML
    private JFXDatePicker dnaissance;

    @FXML
    private JFXTextField codepostal;
    @FXML
    private JFXTextField identifiant;
    @FXML
    private JFXTextField profP;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField emailP, email;

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
                "Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "tatatouine", "Tozeur", "Tunis", "Zaghouan"
        ));
    }

    @FXML
    void click_image(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image pour l'eleve");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Images", "*.*"),
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
        if (event.isSecondaryButtonDown()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(getClass().getResource("../image/default-eleve.jpg"));
                Image i = SwingFXUtils.toFXImage(bufferedImage, null);
                image.setImage(i);
            } catch (IOException x) {
                System.out.println(x);
            }
        }

    }

    @FXML private void click_voir(ActionEvent event) {
        reinit();
        EleveDAO dao = new EleveDAO();
        if (identifiant.getText().isEmpty()) {
            return;
        }
        Eleve eleve = dao.find(Integer.parseInt(identifiant.getText()));
        if (eleve != null) {
            if (eleve.getSex().equalsIgnoreCase("M")) {
                garcon.setSelected(true);
            } else {
                fille.setSelected(true);
            }
            nom.setText(eleve.getNom());
            prenom.setText(eleve.getPrenom());
            ville.setValue(eleve.getVille());
            addresse.setText(eleve.getAdresse());
            codepostal.setText("" + eleve.getCodeP());
            email.setText(eleve.getEmail());
            lnaissance.setText(eleve.getLieuNaiss());
            Instant instant = Instant.ofEpochMilli(eleve.getDateNaiss().getTime());
            dnaissance.setValue(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
            // parent
            ParentDAO daop = new ParentDAO();
            Parent p = daop.find(eleve.getRef_p());
            if (p != null) {
                nomP.setText(p.getNOMP());
                profP.setText(p.getPROFP());
                nomM.setText(p.getNOMM());
                profM.setText(p.getPROFM());
                telP.setText(p.getTELP());
                emailP.setText(p.getEMAILP());
            }
        } else {
            Alert conf = new Alert(Alert.AlertType.INFORMATION);
            conf.setTitle("Erreur!");
            conf.setHeaderText("L'identifiant n'a pa ete touve");
            conf.setContentText("aucun eleve n'existe avec ce identifiant :(\n"
                    + "Verifier l'identifiant et reessayer");
            conf.showAndWait();
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
        nomP.setText("");
        profP.setText("");
        nomM.setText("");
        profM.setText("");
        email.setText("");
        emailP.setText("");
        telP.setText("");
        lnaissance.setText("");
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource("../image/default-eleve.jpg"));
            Image i = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(i);
        } catch (IOException x) {
            System.out.println(x);
        }

    }

    @FXML private void click_reinitialiser(ActionEvent event) {
        identifiant.setText("");
        reinit();
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
    private void click_modifier(ActionEvent event) {
        // controle de saisie
        String erreur = "";
        if (!Tests.email(email.getText())) {
            erreur += "Erreur Email Eleve\n";
        }
        if (!Tests.telephone(telP.getText())) {
            erreur += "Erreur Numero Telephone\n";
        }
        if (!Tests.chaine(nom.getText(), 20, false)) {
            erreur += "Erreur Nom Eleve\n";
        }
        if (!Tests.chaine(prenom.getText(), 20, false)) {
            erreur += "Erreur Prenom Eleve\n";
        }
        if (!Tests.chaine(addresse.getText(), 20, true)) {
            erreur += "Erreur addresse\n";
        }
        if (!Tests.code_postal(codepostal.getText())) {
            erreur += "Erreur Code Postal\n";
        }
        LocalDate d = dnaissance.getValue();
        if (d == null) {
            erreur += "Erreur Date , Selectionner une\n";
        }
        if (d != null && !Tests.date_naissance(Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
            erreur += "Erreur Date Naissance invalide\n";
        }
        if (!Tests.chaine(lnaissance.getText(), 20, false)) {
            erreur += "Erreur Addresse\n";
        }
        if (ville.getSelectionModel().getSelectedIndex() == -1) {
            erreur += "Erreur Ville : selectionner une svp\n";
        }
        // donnee des parents
        if (!Tests.chaine(nomP.getText(), 20, false)) {
            erreur += "Erreur Nom Pere\n";
        }
        if (!Tests.chaine(nomM.getText(), 20, false)) {
            erreur += "Erreur Nom Mere\n";
        }
        if (!Tests.chaine(profM.getText(), 20, false)) {
            erreur += "Erreur Profession Mere\n";
        }
        if (!Tests.chaine(profP.getText(), 20, false)) {
            erreur += "Erreur Profession Pere\n";
        }
        if (!Tests.email(emailP.getText())) {
            erreur += "Erreur Email parent\n";
        }
        if (!Tests.telephone(telP.getText())) {
            erreur += "Erreur telephone Parent\n";
        }
        if (erreur.isEmpty()) {
            EleveDAO dao = new EleveDAO();
            if (identifiant.getText().isEmpty()) {
                return;
            }
            Eleve ancien = dao.find(Integer.parseInt(identifiant.getText()));
            Eleve eleve = new Eleve();
            eleve.setId_e(Integer.parseInt(identifiant.getText()));
            eleve.setNom(nom.getText());
            eleve.setPrenom(prenom.getText());
            eleve.setAdresse(addresse.getText());
            eleve.setVille(ville.getSelectionModel().getSelectedItem().toString());
            eleve.setCodeP(Integer.parseInt(codepostal.getText()));
            eleve.setDateNaiss(new java.util.Date());
            eleve.setLieuNaiss(lnaissance.getText());
            if (garcon.isSelected()) {
                eleve.setSex("M");
            } else {
                eleve.setSex("F");
            }
            eleve.setEmail(email.getText());
            eleve.setRef_niv(0);
            eleve.setRef_c(0);
            if (ancien != null) {
                ParentDAO daop = new ParentDAO();
                Parent parent = daop.find(ancien.getRef_p());
                // suppression ancien parent
                if (parent != null) {
                    daop.delete(ancien.getRef_p());
                }
                parent = new Parent();
                parent.setNOMP(nomP.getText());
                parent.setNOMM(nomM.getText());
                parent.setPROFM(profM.getText());
                parent.setPROFP(profP.getText());
                parent.setEMAILP(emailP.getText());
                parent.setTELP(telP.getText());
                daop.create(parent);
                eleve.setRef_p(daop.dernier());
                //
                if (dao.update(eleve)) {
                    Alert conf = new Alert(Alert.AlertType.INFORMATION);
                    conf.setTitle("Success!");
                    conf.setHeaderText("l'operation de mise a jour eleve est effectuer sans erreur");
                    conf.setContentText("1 tuple eleve modifier =)");
                    conf.showAndWait();
                    reinit();
                } else {
                    Alert conf = new Alert(Alert.AlertType.INFORMATION);
                    conf.setTitle("Erreur!");
                    conf.setHeaderText("une erreur s'est produite lors de l'edition");
                    conf.setContentText("aucun eleve modifier =(");
                    conf.showAndWait();
                }

            }} else {
                System.out.println(erreur);
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Erreur!");
                conf.setHeaderText("des erreur sont produite lors de l'ajout");
                conf.setContentText(erreur + "\n\n\n\n\n\n\nVerifiez les donnes et reessayer ");
                conf.showAndWait();

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
}
