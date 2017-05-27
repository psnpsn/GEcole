
package GUI.note;

import DAO.AppartientDAO;
import DAO.ClasseDAO;
import DAO.EleveDAO;
import DAO.MatiereDAO;
import DAO.NoteDAO;
import Models.Classe;
import Models.Eleve;
import Models.Matiere;
import Models.Note;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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

public class listNoteC implements Initializable {

    @FXML private JFXButton action;
    @FXML private JFXComboBox<String> cmb_classe;
    private ArrayList<Integer> id_class = new ArrayList<>();
    private ArrayList<Integer> id_elves = new ArrayList<>();
    @FXML private TableView<Note> tableview;
    @FXML private TableColumn<Note, String> col_nom;
    
    private MatiereDAO matdao = new MatiereDAO();
    private EleveDAO   elvdao = new EleveDAO();
    private NoteDAO    notdao = new NoteDAO();
    private AppartientDAO appdao = new AppartientDAO();
    private ClasseDAO  classdao = new ClasseDAO();            

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_data();
        deduplication();
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
    private void imprimerNotes(ActionEvent event) {
        for (int i=0;i < tableview.getItems().size();i++){
            tableview.getColumns().get(i).getText();
            ObservableList<TableColumn<Note, ?>> columns = tableview.getColumns();
            for (int j=0;j<columns.size();j++){
                System.out.println(columns.get(j).getText() +": " +columns.get(j).getCellObservableValue(i));
            }
        }
    }

    private void init_data() {

        tableview.getItems().clear();
        while(tableview.getColumns().size()>1){
            tableview.getColumns().remove(1);
        }
        id_class.clear();
        id_elves.clear();
                
        col_nom.setCellValueFactory(cellData -> {
            Eleve e = elvdao.find(cellData.getValue().getRef_e());
            if (e != null) {
                return new SimpleStringProperty(e.getNom() + " " + e.getPrenom());
            } else {
                return new SimpleStringProperty("");
            }
        });

        for (Matiere m : matdao.getAll()) {
            TableColumn<Note, String> col = new TableColumn<>();
            col.setText(m.getNom());
            col.setPrefWidth(m.getNom().length() + 20 * 6);
            col.setCellValueFactory(cellData -> {
                Note e = cellData.getValue();
                if (notdao.getNote(e.getRef_e(), m.getId_m()) == -1) {
                    return new SimpleStringProperty("  .  ");
                }
                return new SimpleStringProperty("" + notdao.getNote(e.getRef_e(), m.getId_m()));
            });
            tableview.getColumns().add(col);            
        }

        ObservableList<Classe> allc = classdao.getAll();
        cmb_classe.getItems().clear();
        id_class.clear();
        for (Classe c : allc) {
            cmb_classe.getItems().add(c.getNom());
            id_class.add(c.getId_c());
        }
        if (allc.size() != 0) {
            cmb_classe.getSelectionModel().select(0);
        }
        ObservableList<Eleve> all1 = elvdao.getAll();
        ObservableList<Note> nts = notdao.getAll();
        for (Eleve e : all1) {
            if (appdao.appartient(id_class.get(cmb_classe.getSelectionModel().getSelectedIndex()), e.getId_e())) {
                id_elves.add(e.getId_e());
                for (Note n : nts) {
                    if (n.getRef_e() == e.getId_e() && !tableview.getItems().contains(e)) {
                        tableview.getItems().add(n);
                    }
                }
            }
        }
        deduplication();
    }

    @FXML
    private void action_supprimer_notes(ActionEvent event) {
        for (int i = 0; i < id_elves.size(); i++) {
            notdao.delete(id_elves.get(i));
        }
        tableview.getItems().clear();
    }

    
    private void deduplication(){
         TableColumn e  = tableview.getColumns().get(0);
         for (int i=0;i<tableview.getItems().size();i++){
             if (e.getCellData(i).equals(e.getCellData(i+1)))
                 tableview.getItems().remove(i);
         }
                          
    }
    

    private void fill_rows(){
        tableview.getItems().clear();
         ObservableList<Eleve> all1 = elvdao.getAll();
        ObservableList<Note> nts = notdao.getAll();
        for (Eleve e : all1) {
            if (appdao.appartient(id_class.get(cmb_classe.getSelectionModel().getSelectedIndex()), e.getId_e())) {
                id_elves.add(e.getId_e());
                for (Note n : nts) {
                    if (n.getRef_e() == e.getId_e() && !tableview.getItems().contains(e)) {
                        tableview.getItems().add(n);
                    }
                }
            }
        }
        
    }
    @FXML
    private void class_changed(ActionEvent event) {
     //   init_data();
     
     fill_rows();
        deduplication();
    }
}
