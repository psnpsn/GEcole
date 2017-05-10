
package GUI.salle;

import DAO.SalleDAO;
import GUI.Tests;
import Models.Salle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class add_salle implements Initializable {

    @FXML
    private JFXButton action;
    @FXML
    private JFXTextField capacite;
    @FXML
    private Label lcapacite;
    @FXML
    private JFXTextField type_salle;
    @FXML
    private Label ltype_salle;
    @FXML
    private DatePicker date_salle;
    @FXML
    private Label ldate_salle;
    @FXML
    private JFXTextField nom;
    @FXML
    private Label lnom;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }
    private void init(){
      type_salle.setText("");
      capacite.setText("");
      ltype_salle.setVisible(false);
      lcapacite.setVisible(false);
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
    private void goto_lister_salle(ActionEvent event) {
         Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("list_salle.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void ajouter_salle(ActionEvent event) {
        if(val()){
            SalleDAO dao = new SalleDAO();
            LocalDate ds = date_salle.getValue();
            Salle s = new Salle();
            s.setIdentifiant(-1);
            s.setCapacite(Integer.parseInt(capacite.getText()));
            s.setDate_creation(Date.from(ds.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            s.setNom(nom.getText());
            s.setType_salle(type_salle.getText());
            dao.create(s);
            goto_lister_salle(new ActionEvent(action, action));
        }
    }

    @FXML
    private void re_init(ActionEvent event) {
        init();
    }
    private boolean val() {
        boolean success = true;
        success=(Tests.txt_field(nom, lnom, 10, true,false)&
                Tests.txt_field(type_salle,ltype_salle,20,false,false)&
                Tests.capacite_field(capacite,lcapacite)
                );
        return success;
    }
private int id_salle = -1;

    void edit_salle(int x) {
        action.setText("Modifier Salle");
        id_salle = x;
        action.setOnAction((e) -> {
            update_salle(x);
        });
        SalleDAO dao = new SalleDAO();
        Salle salle = (Salle) dao.find(x);
        if (salle != null) {
            nom.setText(salle.getNom());
            type_salle.setText(salle.getType_salle());
            capacite.setText(""+salle.getCapacite());
            Instant instant = Instant.ofEpochMilli(salle.getDate_creation().getTime());
            date_salle.setValue(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());

    }
    }

    private void update_salle(int x) {
         if (val()) {
            SalleDAO dao = new SalleDAO();
            Salle salle = new Salle();
            
            salle.setCapacite(Integer.parseInt(capacite.getText()));
            salle.setIdentifiant(x);
            salle.setNom(nom.getText());
            salle.setType_salle(type_salle.getText());
            LocalDate ds = date_salle.getValue();
            salle.setDate_creation(Date.from(ds.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            
            dao.update(salle);
            goto_lister_salle(new ActionEvent(action, action));
        }
    }
}
