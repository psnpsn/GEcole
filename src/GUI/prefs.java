package GUI;

import main_pack.Config;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class prefs implements Initializable {

    @FXML
    private JFXTextField url_oracle;
    @FXML
    private JFXTextField driver_oracle;
    @FXML
    private JFXTextField password_oracle;
    @FXML
    private JFXTextField nom_utilisateur_oracle;
    @FXML
    private Label test_result;
    @FXML
    private JFXTextField nom_ecole;
    @FXML
    private JFXTextField adresse_ecole;
    @FXML
    private JFXTextField siteweb_ecole;
    @FXML
    private JFXTextField telephone_ecole;
    @FXML
    private JFXTextField email_ecole;
    private Config cfg = new Config();
    @FXML
    private Label lnom;
    @FXML
    private Label ladresse;
    @FXML
    private Label lsiteweb;
    @FXML
    private Label ltelephone;
    @FXML
    private Label lemail;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // load_settings_from_file();
    }

    @FXML
    private void test_session(ActionEvent event) {

        try {
                Class.forName(driver_oracle.getText()).newInstance();
                Connection session = java.sql.DriverManager.getConnection(url_oracle.getText(),nom_utilisateur_oracle.getText(),
                        password_oracle.getText());
                java.sql.PreparedStatement statement = session.prepareStatement("SELECT * FROM DUAL");
                java.sql.ResultSet r = statement.executeQuery();
                if (r.next()) {
                    System.out.println(Config.info("Connection a Oracle reussie."));
                    test_result.setStyle("-fx-background-color:#00CC00");
                    test_result.setText("Connexion OK :)");
                }
                else
                    test_result.setText("ECHEC Connexion  :(");
                if (session!=null)
                    session.close();
            } catch (Exception exception) {
                test_result.setStyle("-fx-background-color:red");
                test_result.setText("Connexion ECHEC  :(");
                System.out.println(Config.error("Erreur Connection a Oracle. :")+exception.getMessage());
            }
    }

    private void load_settings_from_file(){
        Config cfg = new Config();
        if (cfg.load()) {
            url_oracle.setText(cfg.get_data("dburl").trim());
            nom_utilisateur_oracle.setText(cfg.get_data("dbusername").trim());
            driver_oracle.setText(cfg.get_data("dbdriver").trim());
            password_oracle.setText(cfg.get_data("dbpassword").trim());
            //--
            nom_ecole.setText(cfg.get_data("nom_ecole").trim());
            adresse_ecole.setText(cfg.get_data("adresse_ecole").trim());
            siteweb_ecole.setText(cfg.get_data("siteweb_ecole").trim());
            telephone_ecole.setText(cfg.get_data("tel_ecole").trim());
            email_ecole.setText(cfg.get_data("email_ecole").trim());
            test_result.setStyle("-fx-background-color:#00CC00");
            test_result.setText("Chargement OK!");
        } else{
            test_result.setStyle("-fx-background-color:red");
            test_result.setText("Chargement ECHEC!");
        }
    }
    private void save_settings_to_file() {
        Config cfg = new Config();
        if (!nom_ecole.getText().isEmpty()){
            cfg.set_data("nom_ecole",nom_ecole.getText());
        }
        if (!adresse_ecole.getText().isEmpty()){
            cfg.set_data("adresse_ecole",adresse_ecole.getText());
        }
        if (!telephone_ecole.getText().isEmpty()){
            cfg.set_data("tel_ecole", telephone_ecole.getText());
        }
        if (!email_ecole.getText().isEmpty()){
            cfg.set_data("email_ecole", email_ecole.getText());
        }
        cfg.set_data("dburl", url_oracle.getText());
        cfg.set_data("dbusername", nom_utilisateur_oracle.getText());
        cfg.set_data("dbdriver", driver_oracle.getText());
        cfg.set_data("dbpassword", password_oracle.getText());
        if (cfg.save()){
            test_result.setStyle("-fx-background-color:#00CC00");
            test_result.setText("Enregistrement OK!");
        } else {
            test_result.setStyle("-fx-background-color:red");
            test_result.setText("Enregistrement ECHEC!");
        }
    }

    @FXML
    private void load_settings(ActionEvent event) {
        load_settings_from_file();
    }

    @FXML
    private void save_settings(ActionEvent event) {
        save_settings_to_file();
    }

    @FXML
    private void factory_settings(ActionEvent event) {
        url_oracle.setText("jdbc:oracle:thin:@localhost:1521:XE");
        driver_oracle.setText("oracle.jdbc.driver.OracleDriver");
        nom_ecole.setText("");
        adresse_ecole.setText("");
        siteweb_ecole.setText("");
        telephone_ecole.setText("");
        email_ecole.setText("");
    }
     @FXML
    private void goto_login(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("login.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }
}
