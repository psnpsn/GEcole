
package GUI.salle;

import DAO.SalleDAO;
import GUI.Tests;
import Models.Salle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class add_salle implements Initializable {

    @FXML private JFXButton action;
    @FXML private JFXTextField nom,type_salle,capacite;
    @FXML private JFXDatePicker date_salle;
    @FXML private Label lnom,ltype_salle,lcapacite;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }
    private void init(){
      type_salle.setText("");
      capacite.setText("");
      ltype_salle.setVisible(false);
      lcapacite.setVisible(false);
      date_salle.setValue(LocalDate.now());
      date_salle.getEditor().setEditable(false);
      date_salle.setEditable(false);
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
    
    private void set_e(JFXTextField n, Label l, String s) {
        n.setUnFocusColor(Color.RED);
        n.getStyleClass().add("fielderror");
        l.setText(s);
        l.setVisible(true);
    }
    private void unset_e(JFXTextField n, Label l) {
        n.setUnFocusColor(Color.GREEN);
        n.getStyleClass().add("txtfield");
        n.getStyleClass().remove("fielderror");
        l.setText("");
        l.setVisible(false);
    }
    private boolean val() {
        unset_e(nom, lnom);
        unset_e(type_salle, ltype_salle);
        unset_e(capacite, lcapacite);
        boolean success = true;
        // nom salle
        if (nom.getText().isEmpty() || nom.getText().matches("^\\s*$"))
           {success = false;set_e(nom, lnom , "Nom de Salle ne doit pa etre vide");}
        if (!nom.getText().matches("[a-zA-Z0-9]*"))
            {success = false;set_e(nom, lnom , "Nom de Salle ne doit contenir que des lettres et des chiffres seulement");}
        if (nom.getText().length()  > 19)
            {success = false;set_e(nom, lnom , "Nom de Salle trop long!");}
        // type salle
        if (type_salle.getText().isEmpty() || type_salle.getText().matches("^\\s*$"))
           {success = false;set_e(type_salle, ltype_salle , "Type de Salle ne doit pas etre vide.");}
        
        if (type_salle.getText().length() > 19)
           {success = false;set_e(type_salle, ltype_salle , "Type de Salle trop long.");}
        
        if (!type_salle.getText().matches(".*[a-zA-Z]*") || type_salle.getText().matches("[0-9]*") )
            {success = false;set_e(type_salle, ltype_salle , "Type de Salle ne doit contenir que des lettres.");}
        
        // capacite salle
        if (capacite.getText().isEmpty() || capacite.getText().matches("^\\s*$"))
            {success = false;set_e(capacite, lcapacite , "Capacite de Salle ne doit contenir que des chiffres.");}
        else {
            try {
                int x = Integer.parseInt(capacite.getText());
                if (x<1 || x > 99) 
                    {success = false;set_e(capacite, lcapacite , "une Salle doit avoir une capacite dans l'intervalle  1 - 99 .");}

                        
            }catch(Exception ex){
                {success = false;set_e(capacite, lcapacite , "Capacite de Salle ne doit contenir que des chiffres.");}
            }
        }
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
