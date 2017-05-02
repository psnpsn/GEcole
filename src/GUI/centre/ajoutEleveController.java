package GUI.centre;


import DAO.ClasseDAO;
import DAO.EleveDAO;
import DAO.ParentDAO;
import GUI.Tests;
import Models.Classe;
import Models.Eleve;
import Models.Parent;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.print.PrintColor.COLOR;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class ajoutEleveController implements Initializable {

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
    private JFXTextField nom_pere;
    @FXML
    private JFXTextField profession_pere;
    @FXML
    private JFXTextField nom_mere;
    @FXML
    private JFXTextField profession_mere;
    @FXML
    private JFXTextField telephone_parents;
    @FXML
    private JFXTextField email_parents;
    @FXML JFXTextField nom_image;
    @FXML
    private ImageView image;
    @FXML
    private JFXTextField code_postal;
    @FXML
    private JFXComboBox<String> ville;
    @FXML
    private JFXComboBox<String> niveau;
    @FXML
    private Label lnom;
    @FXML
    private Label lprenom;
    @FXML
    private Label ldate_naissance;
    @FXML
    private Label llieu_naissance;
    @FXML
    private Label laddresse;
    @FXML
    private Label lville;
    @FXML
    private Label lcode_postal;
    @FXML
    private Label lniveau;
    @FXML
    private Label lemail;
    @FXML
    private Label lnom_pere;
    @FXML
    private Label lprofession_pere;
    @FXML
    private Label lnom_mere;
    @FXML
    private Label lprofession_mere;
    @FXML
    private Label ltelephone_parent;
    @FXML
    private Label lemail_parent;
    @FXML
    private JFXTextField lieu_naissance;
    @FXML JFXButton action;

    private int id_el = -1,id_pa=-1;
    private ArrayList<Classe> liste_des_classes;
    @FXML
    private JFXComboBox<String> classe;
    private ObservableList<Classe> ob_class_list;
    private ObservableList<Eleve> ob_eleve_list;
    @FXML
    private JFXButton pressedbtn;
    @FXML
    private Label idLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        garcon.setToggleGroup(group);
        fille.setToggleGroup(group);
        init();
    }

    private void set_class(){
        classe.getItems().clear();

        ClasseDAO daoc = new ClasseDAO();
        ob_class_list = daoc.getAll();
        EleveDAO daoe = new EleveDAO();
        ob_eleve_list = daoe.getAll();

        for (int i = 0; i < ob_class_list.size(); i++) {
            int count = 0;
            for (int ei = 0; ei < ob_eleve_list.size(); ei++) {
                if (ob_eleve_list.get(ei).getRef_c() == ob_class_list.get(i).getId_c()) {
                    count++;
                }
            }
            if (count < ob_class_list.get(i).getCapacite()
                    && ob_class_list.get(i).getRef_niv()
                    == Integer.parseInt(niveau.getSelectionModel().getSelectedItem())){
                classe.getItems().add(ob_class_list.get(i).getNom()+" - "+count + "/"+ob_class_list.get(i).getCapacite());
            }

        }
    }
    private void init(){
        date_naissance.getEditor().setEditable(false);
        date_naissance.setEditable(false);
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
        niveau.getSelectionModel().clearSelection();
        niveau.setItems(FXCollections.observableArrayList("1","2","3","4","5","6"));
        niveau.setValue("");
        niveau.setPromptText("Niveau");
        lniveau.setVisible(false);
        addresse.setText("");
        laddresse.setVisible(false);
        lieu_naissance.setText("");
        llieu_naissance.setVisible(false);
        code_postal.setText("");
        nom_pere.setText("");
        lnom_mere.setVisible(false);
        profession_pere.setText("");
        lprofession_pere.setVisible(false);
        nom_mere.setText("");
        lnom_mere.setVisible(false);
        profession_mere.setText("");
        lprofession_mere.setVisible(false);
        telephone_parents.setText("");
        ltelephone_parent.setVisible(false);
        email.setText("");
        lemail.setVisible(false);
        email_parents.setText("");
        lemail_parent.setVisible(false);
        try {
            File f = new File(getClass().getResource("default-eleve.jpg").toURI());
            if (Tests.image(f))
                set_image(f);
        } catch (Exception ex) {
        }

    }
    public void edit_eleve(int x) {

        action.setText("Modifier");
        pressedbtn.setStyle("maintbn");
        idLabel.setVisible(true);
        idLabel.setText(idLabel.getText()+x);
        id_el = x;
        action.setOnAction((e) -> {
            update_eleve(x);
        });
        EleveDAO dao = new EleveDAO();
        Eleve eleve = (Eleve) dao.find(x);
        id_pa = eleve.getRef_p();
        if (eleve != null) {
            nom.setText(eleve.getNom());
            prenom.setText(eleve.getPrenom());
            Instant instant = Instant.ofEpochMilli(eleve.getDateNaiss().getTime());
            date_naissance.setValue(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
            addresse.setText(eleve.getAdresse());
            if (eleve.getSex().equalsIgnoreCase("F")) {
                fille.setSelected(true);
            } else {
                garcon.setSelected(true);
            }
            email.setText(eleve.getEmail());
            ville.setValue(eleve.getVille());
            niveau.setValue(Integer.toString(eleve.getRef_niv()));
            code_postal.setText("" + eleve.getCodeP());
            lieu_naissance.setText(eleve.getLieuNaiss());
            ParentDAO daop = new ParentDAO();
            Parent p = daop.find(eleve.getRef_p());
            if (p != null) {
                nom_pere.setText(p.getNOMP());
                nom_mere.setText(p.getNOMM());
                profession_mere.setText(p.getPROFM());
                profession_pere.setText(p.getPROFP());
                email_parents.setText(p.getEMAILP());
                telephone_parents.setText("" + p.getTELP());
                id_pa = eleve.getRef_p();
            }
            try {
                File fichier = new File("data/eleve/" + x + ".png");
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
    @FXML
    private void goto_list(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("gestionEleve.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    private void update_eleve(int x){
        if (val()) {
            ParentDAO daop = new ParentDAO();
            Parent parents = new Parent(id_pa, nom_pere.getText(), nom_mere.getText(), profession_pere.getText(), profession_mere.getText(), telephone_parents.getText(), email_parents.getText());
            EleveDAO daoe = new EleveDAO();
            if (id_pa != -1) {
                daop.update(parents);
            } else {
                id_pa = daop.create(parents);
            }
            if (id_el != -1) {
                LocalDate d = date_naissance.getValue();
                String sex = garcon.isSelected() ? "H" : "F";
                Eleve eleve = new Eleve(id_el, nom.getText(), prenom.getText(), addresse.getText(),ville.getSelectionModel().getSelectedItem(),Integer.parseInt(code_postal.getText()) , Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),lieu_naissance.getText(), sex,  email.getText(), Integer.parseInt(niveau.getSelectionModel().getSelectedItem()),-1,id_pa , null);

                daoe.update(eleve);
                try {
                    File outputFile = new File("data/eleve/" + x + ".png");
                    BufferedImage bImage = SwingFXUtils.fromFXImage(image.getImage(), null);
                    ImageIO.write(bImage, "png", outputFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                 goto_list(new ActionEvent(action, action));
            }
        }
    }

    @FXML
    private void ajouter_eleve(ActionEvent event) {
        if (val()) {
            ParentDAO dao = new ParentDAO();
            Parent parents = new Parent(0, nom_pere.getText(), nom_mere.getText(), profession_pere.getText(), profession_mere.getText(), telephone_parents.getText(), email_parents.getText());
            int id_parents = dao.create(parents);
            if (id_parents != -1) {
                EleveDAO daoe = new EleveDAO();
                LocalDate d = date_naissance.getValue();
                Eleve eleve = new Eleve(id_el, nom.getText(), prenom.getText(), addresse.getText(),ville.getSelectionModel().getSelectedItem(),Integer.parseInt(code_postal.getText()) , Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),lieu_naissance.getText(), (garcon.isSelected() ? "H" : "F"),  email.getText(), Integer.parseInt(niveau.getSelectionModel().getSelectedItem()),-1,id_parents , null);
                if (classe.getSelectionModel().getSelectedIndex()!=-1){
                    for (int i = 0;i < ob_class_list.size();i++)
                    {
                        if (ob_class_list.get(i).getNom().equals(classe.getSelectionModel().getSelectedItem()))
                               {
                            eleve.setRef_c(ob_class_list.get(i).getId_c());
                        }
                    }
                }
                int id_eleve = daoe.create(eleve);
                if (id_eleve != -1) {
                    eleve.setRef_p(id_parents);
                    boolean e = daoe.update(eleve);
                    // sauvegarde de l'image
                    try {
                        File outputFile = new File("data/eleve/" + id_eleve + ".png");
                        BufferedImage bImage = SwingFXUtils.fromFXImage(image.getImage(), null);
                        ImageIO.write(bImage, "png", outputFile);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                     goto_list(new ActionEvent(action, action));
                } else {
                    // delete parent
                }
            }
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
    private void set_image(File path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(path);
            Image i = SwingFXUtils.toFXImage(bufferedImage, null);
            if (Tests.image(path))
            image.setImage(i);
        } catch (IOException x) {
            System.out.println(x);
        }
    }

    private boolean val(){
        boolean success = true;
        success=(Tests.email_field(email, lemail)&
                Tests.txt_field(prenom, lprenom, 20, false,false)&
                Tests.txt_field(nom,lnom, 20, false,false)&
                Tests.date_naissance_field(date_naissance, ldate_naissance)&
                Tests.txt_field(lieu_naissance,llieu_naissance,20,false,false)&
                Tests.txt_field(addresse,laddresse,20,true,false)&
                Tests.code_postal_field(code_postal,lcode_postal)&
                Tests.ville_field(ville, lville)&
                Tests.niveau_field(niveau,lniveau)&
                Tests.txt_field(nom_pere,lnom_pere,20,false,false)&
                Tests.txt_field(nom_mere,lnom_mere,20,false,false)&
                Tests.txt_field(profession_pere,lprofession_pere,20,false,false)&
                Tests.txt_field(profession_mere,lprofession_mere,20,false,false)&
                Tests.email_field(email_parents,lemail_parent)&
                Tests.telephone_field(telephone_parents,ltelephone_parent)
                );
        return success;
    }

    @FXML
    private void niv_selected(ActionEvent event) {
        classe.setDisable(false);
        this.set_class();
    }
}
