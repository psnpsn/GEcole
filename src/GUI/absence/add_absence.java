
package GUI.absence;

import DAO.AbsenceDAO;
import DAO.AppartientDAO;
import DAO.ClasseDAO;
import DAO.EleveDAO;
import Models.Absence;
import Models.Classe;
import Models.Eleve;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.awt.Robot;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import static javafx.scene.AccessibleRole.NODE;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class add_absence implements Initializable {

    @FXML private JFXButton action;
    @FXML private Label ltype_salle,lcapacit,lnom;
    @FXML private JFXComboBox<String> heure_absence,cmb_eleve,cmb_classe;
    @FXML private DatePicker date_absence;
    
    private ArrayList<Integer> id_class = new ArrayList<>();
    private ArrayList<Integer> id_elves = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        heure_absence.getItems().addAll("8..10","10..12","14..16","16..18");
        date_absence.setValue(LocalDate.now());
        heure_absence.getSelectionModel().select(0);        
        init_combo_classes();
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
    private void goto_lister_absence(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("list_absence.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void goto_ajout_absence(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("add_absence.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void marquer_absence(ActionEvent event) {
        if (cmb_eleve.getSelectionModel().getSelectedIndex()==-1 )
            return;
        AbsenceDAO absdao = new AbsenceDAO();
        Absence abs = new Absence();
        LocalDate d = date_absence.getValue();
        abs.setDate_abs(Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        System.out.println(id_elves.get(cmb_eleve.getSelectionModel().getSelectedIndex()));
        abs.setRef_e(id_elves.get(cmb_eleve.getSelectionModel().getSelectedIndex()));
        abs.setRef_sc(heure_absence.getSelectionModel().getSelectedIndex());                
        if (absdao.create(abs)!=-1)
            goto_lister_absence(event);
    }

    private void init_combo_eleves(){
        id_elves.clear();
        cmb_eleve.getItems().clear();
        EleveDAO daoelv = new EleveDAO();
        AppartientDAO appdao = new AppartientDAO();
        ObservableList<Eleve> all = daoelv.getAll();        
        for (Eleve e : all){
            cmb_eleve.getSelectionModel().select(0);
            if (appdao.appartient(id_class.get(cmb_classe.getSelectionModel().getSelectedIndex()), e.getId_e())){
                cmb_eleve.getItems().add(e.getNom() + " " + e.getPrenom());
                id_elves.add(e.getId_e());
            }
        }                                        
    }
    private void init_combo_classes(){
        cmb_classe.getItems().clear();        
        ClasseDAO cdao = new ClasseDAO();
        ObservableList<Classe> all = cdao.getAll();
        for (Classe c : all){
            cmb_classe.getItems().add(c.getNom());
            id_class.add(c.getId_c());            
        }        
    }
    
    @FXML private void update_classe(ActionEvent event) {
        init_combo_eleves();        
    }    
}
