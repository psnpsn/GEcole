/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.centre;

import DAO.EleveDAO;
import DAO.ParentDAO;
import GUI.LoginController;
import Models.Eleve;
import Models.Parent;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author Chazzone
 */
public class voirEleveController implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Label ville;

    @FXML
    private Label telp;

    @FXML
    private JFXRadioButton garcon;

    @FXML
    private JFXTextField identifiant;

    @FXML
    private Label profp;

    @FXML
    private Label addresse;

    @FXML
    private Label profm;

    @FXML
    private JFXRadioButton fille;

    @FXML
    private Label nom;

    @FXML
    private Label nomm;

    @FXML
    private Label emailp;

    @FXML
    private Label dnaissance;

    @FXML
    private Label codepostal;

    @FXML
    private Label nomp;

    @FXML
    private Label lnaissance;

    @FXML
    private Label prenom;

    @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        garcon.setToggleGroup(group);
        fille.setToggleGroup(group);
        garcon.setSelected(true);

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

    @FXML private void click_imprimer(ActionEvent event) {

        try {
            FileWriter xx = new FileWriter("output.html", false);
            PrintWriter w = new PrintWriter(xx);
            //-
            w.printf("<html><head><title>GESTION ECOLE</title><style>table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%%;}td, th {border: 1px solid #dddddd;text-align: left;padding: 8px;tr:nth-child(even) {background-color: #dddddd;}</style></head>\n<html><body>"
                    + "<p>x y</p><table>  <tr>    <td>Nom et Prenom :</td>   "
                    + " <td>" + nom.getText() + " " + prenom.getText() + "</td>  </tr>  <tr>  "
                    + "  <td>Addresse :</td>    <td>" + addresse.getText() + "</td>"
                    + "  </tr>  <tr>    <td>Date et Ville Naissance :</td>    <td>" + dnaissance.getText() + " a " + ville.getText() + "</td>  </tr>  <tr>"
                    + "    <td>Nom et Prenom du Pere :</td>    <td>nomprenompere</td>  </tr>  <tr>    <td>Nom et Prenom de la mere :</td>    <td>nomprenommere</td>  </tr>"
                    + "  <tr>    <td>Profession Pere</td>    <td>profpere</td>  </tr>    <tr>    <td>Profession Mere</td>    <td>profmere</td>  </tr>"
                    + "    </tr>    <tr>    <td>Telephone :</td>    <td>tel1 , tel2</td>  </tr>    </tr>    <tr>    <td>Email</td>    <td>email</td>"
                    + "  </tr></table></body></html>\n");
            w.close();
            xx.close();
        } catch (Exception ex) {
            System.out.println("Erreur d'entree/Sortie dans la methode sauvegarder() : " + ex);
        }

        try {
            String[] params = {"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe", "output.html"};
            Process p = Runtime.getRuntime().exec(params);

            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(voirEleveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reinit() {
        nom.setText("Nom : ");
        prenom.setText("Prenom : ");
        addresse.setText("Adresse : ");
        codepostal.setText("Code Postal : ");
        email.setText("Email : ");
        lnaissance.setText("Lieu Naissance : ");
        ville.setText("Ville : ");
        garcon.setSelected(true);
        dnaissance.setText("Date Naissance :");
        // parent
        nomp.setText("Nom et Prenom Pere :");
        profp.setText("Profession Pere :");
        nomm.setText("Nom et Prenom Mere :");
        profm.setText("Profession Mere :");
        telp.setText("Telephone Parent :");
        emailp.setText("Email Parent:");

    }

    @FXML private void click_voir(ActionEvent event) {
        reinit();
        if (identifiant.getText().isEmpty()) {
            return;
        }

        EleveDAO dao = new EleveDAO();
        Eleve eleve = dao.find(Integer.parseInt(identifiant.getText()));
        if (eleve != null) {
            nom.setText("Nom : " + eleve.getNom());
            prenom.setText("Prenom : " + eleve.getPrenom());
            addresse.setText("Adresse : " + eleve.getAdresse());
            codepostal.setText("Code Postal : " + eleve.getCodeP());
            email.setText("Email : " + eleve.getEmail());
            lnaissance.setText("Lieu Naissance : " + eleve.getLieuNaiss());
            ville.setText("Ville : " + eleve.getVille());
            if (eleve.getSex().equalsIgnoreCase("M")) {
                garcon.setSelected(true);
            } else {
                fille.setSelected(true);
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(eleve.getDateNaiss());
            dnaissance.setText("Née le  " + new SimpleDateFormat("DD").format(cal.getTime()) + " " + new SimpleDateFormat("MMMM").format(cal.getTime()) + " " + new SimpleDateFormat("YYYY").format(cal.getTime()));
            // parent
            ParentDAO daop = new ParentDAO();
            Parent p = daop.find(eleve.getRef_p());
            if (p != null) {
                nomp.setText("Nom et Prenom Pere :" + p.getNOMP());
                profp.setText("Profession du Pere :" + p.getPROFP());
                nomm.setText("Nom et Prenom Mere :" + p.getNOMM());
                profm.setText("Profession de la Mere :" + p.getPROFM());
                telp.setText("Telephone parent :" + p.getTELP());
                emailp.setText("Email Parent :" + p.getEMAILP());
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

    @FXML private void click_trouver(ActionEvent event) {

    }
}
