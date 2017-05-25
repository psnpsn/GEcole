package GUI.note;

import DAO.ClasseDAO;
import DAO.EleveDAO;
import DAO.MatiereDAO;
import DAO.NoteDAO;
import Models.Classe;
import Models.Eleve;
import Models.Matiere;
import Models.Note;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;


public class ajoutNoteC implements Initializable {

    @FXML private JFXButton action;
    @FXML private TableColumn<Note, String> col_nom;
    @FXML private TableColumn<Note, Object> col_note;
    @FXML private TableView<Note> tableview;
    
    
    EleveDAO ElvData = new EleveDAO();
    ObservableList<Note> NotesData;
    
    ClasseDAO daoclass = new ClasseDAO();
    MatiereDAO daomat = new MatiereDAO();
    @FXML private JFXComboBox<String> cmb_classe,cmb_mats;
    ArrayList<Integer> class_ids = new ArrayList();
    ArrayList<Integer> mats_ids = new ArrayList();
    
    
    private void init_combos(){
        for (Classe c:daoclass.getAll()){
            cmb_classe.getItems().add(c.getNom());
            class_ids.add(c.getId_c());
        }        
        if (cmb_classe.getItems().size()!=0)
            cmb_classe.getSelectionModel().select(0);
        for (Matiere m:daomat.getAll()){
            cmb_mats.getItems().add(m.getNom());
            mats_ids.add(m.getId_m());
        }
        if (cmb_mats.getItems().size()!=0){
            cmb_mats.getSelectionModel().select(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_note.setEditable(true);
        NotesData = FXCollections.observableArrayList();
        
        col_nom.setCellValueFactory(cellData -> {
            Eleve e = ElvData.find(cellData.getValue().getRef_e());
            if (e == null) {
                return new SimpleStringProperty("-");
            } else {
                return new SimpleStringProperty(e.getNom() + " " + e.getPrenom());
            }
        });
        
        col_note.setCellValueFactory(cellData -> {
            cellData.getValue().txt_note.setText(""+cellData.getValue().getNote());
            return new SimpleObjectProperty(cellData.getValue().txt_note);
        });
        
        NotesData.addAll(new NoteDAO().getAll());
        tableview.setItems(NotesData);
        init_combos();
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
    private void listNotes(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("listNote.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void ajoutNotes(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("ajoutNote.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void ajouterNotes(ActionEvent event) {
    }

    @FXML
    private void reinit(ActionEvent event) {
    }

}
