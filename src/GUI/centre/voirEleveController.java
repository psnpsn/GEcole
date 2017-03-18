/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.centre;

import GUI.LoginController;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private JFXTextField tel1;

    @FXML
    private JFXTextField tel2;
    @FXML
    private ImageView image;

    @FXML
    private JFXTextField ville;

    @FXML
    private JFXRadioButton garcon;

    @FXML
    private JFXTextField nomPere;

    @FXML
    private JFXTextField addresse;

    @FXML
    private JFXRadioButton fille;

    @FXML
    private JFXTextField nomMere;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField profMere;

    @FXML
    private JFXTextField dnaissance;

    @FXML
    private JFXTextField codepostal;

    @FXML
    private JFXTextField profPere;

    @FXML
    private JFXTextField prenomMere;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField prenomPere;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    private void click_retour(ActionEvent event) {
        try {
            URL loader = getClass().getResource("gestionEleve.fxml");
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
                    + " <td>"+nom.getText() + " "+ prenom.getText()+"</td>  </tr>  <tr>  "
                    + "  <td>Addresse :</td>    <td>"+addresse.getText()+"</td>"
                    + "  </tr>  <tr>    <td>Date et Ville Naissance :</td>    <td>"+dnaissance.getText()+" a "+ville.getText()+"</td>  </tr>  <tr>"
                    + "    <td>Nom et Prenom du Pere :</td>    <td>nomprenompere</td>  </tr>  <tr>    <td>Nom et Prenom de la mere :</td>    <td>nomprenommere</td>  </tr>"
                    + "  <tr>    <td>Profession Pere</td>    <td>profpere</td>  </tr>    <tr>    <td>Profession Mere</td>    <td>profmere</td>  </tr>"
                    + "    </tr>    <tr>    <td>Telephone :</td>    <td>tel1 , tel2</td>  </tr>    </tr>    <tr>    <td>Email</td>    <td>email</td>"
                    + "  </tr></table></body></html>\n");
            w.close();
            xx.close();
        } catch (Exception ex) {
           System.out.println("Erreur d'entree/Sortie dans la methode sauvegarder() : " +ex);
}


        try {
            String[] params = { "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe", "output.html" };
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
    @FXML private void click_voir(ActionEvent event) {

    }
}
