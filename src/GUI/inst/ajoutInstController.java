package GUI.inst;


import DAO.InstituteurDAO;
import GUI.Tests;
import Models.Instituteur;
import com.jfoenix.controls.JFXButton;
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
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class ajoutInstController implements Initializable {

    @FXML
    private JFXButton action;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXDatePicker date_naissance;
    @FXML
    private JFXTextField addresse;
    @FXML
    private JFXRadioButton fille;
    @FXML
    private JFXRadioButton garcon;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField num_cin;
    @FXML
    private JFXDatePicker date_embauchement;
    @FXML
    private JFXTextField telephone_1;
    @FXML
    private JFXTextField telephone_2;
    @FXML
    private ImageView image;
    @FXML
    private JFXTextField nom_image;
    @FXML
    private JFXTextField code_postal;
    @FXML
    private JFXComboBox<String> ville;
    @FXML
    private JFXTextField numero_matricule;
    @FXML
    private JFXComboBox<String> grade;
    @FXML
    private Label lnom;
    @FXML
    private Label lprenom;
    @FXML
    private Label ldate_naissance;
    @FXML
    private Label lnumero_matricule;
    @FXML
    private Label laddresse;
    @FXML
    private Label lgrade;
    @FXML
    private Label lemail;
    @FXML
    private Label lnumero_cin;
    @FXML
    private Label ldate_embauchement;
    @FXML
    private Label ltelephone_1;
    @FXML
    private Label ltelephone_2;

    @FXML
    private Label lville;
    @FXML
    private Label lcode_postal;

    private int id_instituteur;
    @FXML
    private Label ltelephone_parent;
    @FXML
    private Label lemail_parent;
    @FXML
    private Label idLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        garcon.setToggleGroup(group);
        fille.setToggleGroup(group);
        new File("data/instituteur").mkdirs();
        init();   
    }

    private void init() {
        date_naissance.getEditor().setEditable(false);
        date_naissance.setEditable(false);
         date_embauchement.getEditor().setEditable(false);
        date_embauchement.setEditable(false);
        garcon.setSelected(true);
        nom.setText("");
        lnom.setVisible(false);
        prenom.setText("");
        lprenom.setVisible(false);
        ville.getSelectionModel().clearSelection();
        ville.setItems(FXCollections.observableArrayList(
                "Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tatatouine", "Tozeur", "Tunis", "Zaghouan"
        ));
        ville.setValue("");
        ville.setPromptText("Ville Naissance");
        lville.setVisible(false);
        grade.getSelectionModel().clearSelection();
        grade.setItems(FXCollections.observableArrayList("Professeur", "Remplacant", "Stagiaire"));
         grade.setValue("");
        grade.setPromptText("Grade");
        lgrade.setVisible(false);
        addresse.setText("");
        laddresse.setVisible(false);
        numero_matricule.setText("");
        lnumero_matricule.setVisible(false);
        code_postal.setText("");
        num_cin.setText("");
        numero_matricule.setText("");
        telephone_1.setText("");
        telephone_2.setText("");
        nom_image.setText("");
        nom_image.setEditable(false);
        ltelephone_1.setVisible(false);
        ldate_embauchement.setVisible(false);
        email.setText("");
        lemail.setVisible(false);
        try {
            File f = new File(getClass().getResource("default.jpg").toURI());
            if (Tests.image(f)) {
                set_image(f);
                nom_image.setText(f.getName());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void goto_admin_main(ActionEvent event) {
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
    private void goto_list(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("gestionInst.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void ajouter_instituteur(ActionEvent event) {
        if (val()) {
            InstituteurDAO dao = new InstituteurDAO();
            LocalDate dn = date_naissance.getValue();
            LocalDate de = date_embauchement.getValue();
            Instituteur instituteur = new Instituteur(-1,
                    nom.getText(),
                    prenom.getText(),
                    Date.from(dn.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                    Integer.parseInt(num_cin.getText()),
                    Date.from(de.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                    garcon.isSelected() ? "H" : "F",
                    Integer.parseInt(numero_matricule.getText()),
                    grade.getSelectionModel().getSelectedItem(),
                    addresse.getText(),
                    ville.getSelectionModel().getSelectedItem(),
                     email.getText(),
                    Integer.parseInt(code_postal.getText()),
                            Integer.parseInt(telephone_1.getText()),
                            Integer.parseInt(telephone_2.getText())
                           );
            int id_instituteur = dao.create(instituteur);
            // sauvegarde de l'image
            try {
                File outputFile = new File("data/instituteur/" + id_instituteur + ".png");
                BufferedImage bImage = SwingFXUtils.fromFXImage(image.getImage(), null);
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (id_instituteur!=-1)
                 goto_list(new ActionEvent(action, action));
        }
    }



    @FXML
    private void re_init(ActionEvent event) {
        init();
    }

    @FXML
    private void ouvrir_image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image pour l'eleve");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.png", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(new javafx.stage.Stage());
        if (Tests.image(file))
            set_image(file);
    }
        private void set_image(File path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(path);
            Image i = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(i);
        } catch (IOException x) {
            System.out.println(x);
        }
    }

    private boolean val() {
        return (Tests.email_field(email, lemail)
                & Tests.txt_field(prenom, lprenom, 20, false, false)
                & Tests.txt_field(nom, lnom, 20, false, false)
                & Tests.date_naissance_field(date_naissance, ldate_naissance)
                & Tests.telephone_field(numero_matricule, lnumero_matricule)
                & Tests.txt_field(addresse, laddresse, 20, true, false)
                & Tests.code_postal_field(code_postal, lcode_postal)
                & Tests.ville_field(ville, lville)
                & Tests.niveau_field(grade, lgrade)
                & Tests.telephone_field(num_cin, lnumero_cin)
                & Tests.date_naissance_field(date_embauchement, ldate_embauchement)
                & Tests.telephone_field(telephone_1, ltelephone_1)
                & Tests.telephone_field(telephone_2, ltelephone_2));
    }
    public void update_instituteur(int x) {
        if (val()) {
            LocalDate dn = date_naissance.getValue();
            LocalDate de = date_embauchement.getValue();
            InstituteurDAO dao = new InstituteurDAO();
            Instituteur instituteur = new Instituteur(x,
                    nom.getText(),
                    prenom.getText(),
                    Date.from(dn.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                    Integer.parseInt(num_cin.getText()),
                    Date.from(de.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                    garcon.isSelected() ? "H" : "F",
                    Integer.parseInt(numero_matricule.getText()),
                    grade.getSelectionModel().getSelectedItem(),
                    addresse.getText(),
                    ville.getSelectionModel().getSelectedItem(),
                     email.getText(),
                    Integer.parseInt(code_postal.getText()),
                            Integer.parseInt(telephone_1.getText()),
                            Integer.parseInt(telephone_2.getText())
                           );
            dao.update(instituteur);
            try {
                File outputFile = new File("data/instituteur/" + x + ".png");
                BufferedImage bImage = SwingFXUtils.fromFXImage(image.getImage(), null);
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
             goto_list(new ActionEvent(action, action));
        }
    }
    public void edit_instituteur(int x) {

        action.setText("Modifier Instituteur");
        idLabel.setVisible(true);
        idLabel.setText(idLabel.getText()+x);
        id_instituteur = x;
        action.setOnAction((e) -> {
            update_instituteur(x);
        });
        InstituteurDAO dao = new InstituteurDAO();
        Instituteur instituteur = (Instituteur) dao.find(x);
        if (instituteur != null) {
            nom.setText(instituteur.getNom());
            prenom.setText(instituteur.getPrenom());
            Instant instant = Instant.ofEpochMilli(instituteur.getDateNaiss().getTime());
            date_naissance.setValue(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
            num_cin.setText("" + instituteur.getNCIN());
            instant = Instant.ofEpochMilli(instituteur.getDateNaiss().getTime());
            date_embauchement.setValue(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
            if (instituteur.getSex().equalsIgnoreCase("F")) {
                fille.setSelected(true);
            } else {
                garcon.setSelected(true);
            }
            numero_matricule.setText("" + instituteur.getImmatricul());
            grade.setValue(instituteur.getGrade());
            addresse.setText(instituteur.getAdresse());
            ville.setValue(instituteur.getVille());
            code_postal.setText("" + instituteur.getCodeP());
            telephone_1.setText("" + instituteur.getTel1());
            telephone_2.setText("" + instituteur.getTel2());
            email.setText(instituteur.getEmail());
            try {
                File fichier = new File("data/instituteur/" + x + ".png");
                nom_image.setText(fichier.getName());
                if (Tests.image(fichier)) {
                    BufferedImage bm = ImageIO.read(fichier);
                    if (bm != null) {
                        Image i = SwingFXUtils.toFXImage(bm, null);
                        if (i != null) {
                            image.setImage(i);
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
