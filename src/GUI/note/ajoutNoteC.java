package GUI.note;

import DAO.AppartientDAO;
import DAO.ClasseDAO;
import DAO.EleveDAO;
import DAO.InstituteurDAO;
import DAO.MatiereDAO;
import DAO.NoteDAO;
import Models.Classe;
import Models.Eleve;
import Models.Instituteur;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;


public class ajoutNoteC implements Initializable {

    @FXML private JFXButton action;
    @FXML private TableColumn<Note, String> col_nom;
    @FXML private TableColumn<Note, String> col_note;
    @FXML private TableView<Note> tableview;
    
    
    
    ObservableList<Note> data;
    
    ClasseDAO daoclass = new ClasseDAO();
    MatiereDAO daomat = new MatiereDAO();
    InstituteurDAO daoinst = new InstituteurDAO();
    EleveDAO ElvData = new EleveDAO();
    NoteDAO daonote = new NoteDAO();
    
    
    @FXML private JFXComboBox<String> cmb_classe,cmb_mats,cmb_inst;
    ArrayList<Integer> class_ids = new ArrayList();
    ArrayList<Integer> mats_ids = new ArrayList();
    ArrayList<Integer> inst_ids = new ArrayList();
    
    
    private void init_combos(){
        for (Classe c:daoclass.getAll()){
            cmb_classe.getItems().add(c.getNom());
            class_ids.add(c.getId_c());
        }                
        for (Matiere m:daomat.getAll()){
            cmb_mats.getItems().add(m.getNom());
            mats_ids.add(m.getId_m());
        }
       for (Instituteur i:daoinst.getAll()){
            cmb_inst.getItems().add(i.getNom());
            inst_ids.add(i.getId_i());
        }        
        if (cmb_mats.getItems().size()!=0){
            cmb_mats.getSelectionModel().select(0);
        }
        if (cmb_classe.getItems().size()!=0)
            cmb_classe.getSelectionModel().select(0);
        if (cmb_inst.getItems().size()!=0)
            cmb_inst.getSelectionModel().select(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_note.setEditable(true);
        data = FXCollections.observableArrayList();
        
        col_nom.setCellValueFactory(cellData -> {
            Eleve e = ElvData.find(cellData.getValue().getRef_e());
            if (e == null) {
                return new SimpleStringProperty("-");
            } else {
                return new SimpleStringProperty(e.getNom() + " " + e.getPrenom());
            }
        });

        col_note.setCellFactory(TextFieldTableCell.forTableColumn()) ;
        for (Eleve e:ElvData.getAll()){
            AppartientDAO dao = new AppartientDAO();
            Note n = new Note();
            n.setNote(0);
            n.setRef_e(e.getId_e());
            data.add(n);
        }
        
        col_note.setOnEditCommit(
                new EventHandler<CellEditEvent<Note, String>>() {
            public void handle(CellEditEvent<Note, String> t) {
                ((Note) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setNote(Float.parseFloat(t.getNewValue()));
            }
        }
        );
        
        
        tableview.setItems(data);
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
        for (int i = 0;i<tableview.getItems().size();i++){            
            Note no = new Note();
            no.setNote(tableview.getItems().get(i).getNote());
            no.setRef_e(tableview.getItems().get(i).getRef_e());
            no.setRef_inst(inst_ids.get(cmb_inst.getSelectionModel().getSelectedIndex()));
            no.setRef_mat(mats_ids.get(cmb_mats.getSelectionModel().getSelectedIndex()));            
            //System.out.println(tableview.getItems().get(i).getId_note()+" - " + tableview.getItems().get(i).getNote() + cmb_mats.getSelectionModel().getSelectedItem());
            System.out.println(daonote.create(no));
        }

    }

    @FXML
    private void reinit(ActionEvent event) {
    }

}
