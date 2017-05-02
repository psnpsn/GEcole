package GUI.emploi;

import DAO.AssisteDAO;
import DAO.ClasseDAO;
import DAO.EmploiDAO;
import DAO.InstituteurDAO;
import DAO.SalleDAO;
import Models.Assiste;
import Models.Classe;
import Models.Emploi;
import Models.Instituteur;
import Models.Salle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class add_emploi implements Initializable {
    
    @FXML private JFXButton action;
   // @FXML private Text matiere,instituteur, salle;
    @FXML private ComboBox<String> combobox_classe,combobox_niveau;
    @FXML private JFXListView<String> listview_salles,listview_matieres;
    
    @FXML private Rectangle h8h10, h10h12,h14h16,h16h18;
          private Pane      targetPane = null;
    @FXML private Rectangle lundi_0,mardi_0,mercredi_0,jeudi_0,vendredi_0,samedi_0;
    @FXML private Rectangle lundi_1,lundi_2,lundi_3,lundi_4;
    @FXML private Rectangle mardi_1,mardi_2,mardi_3, mardi_4;
    @FXML private Rectangle mercredi_1,mercredi_2,mercredi_3,mercredi_4;
    @FXML private Rectangle jeudi_1,jeudi_2,jeudi_3,jeudi_4;
    @FXML private Rectangle vendredi_1,vendredi_2,vendredi_3,vendredi_4;
    @FXML private Rectangle samedi_1,samedi_2;

    private EmploiDAO    dao_emploi = null;
    private SalleDAO     dao_salles = null;
    private InstituteurDAO dao_inst = null;
    private ClasseDAO   dao_classes = null;
    private AssisteDAO  dao_assiste = null;
    private HashMap<String,Emploi> emp = new HashMap<>();
    @FXML
    private Text matiere;
    @FXML
    private Text instituteur;
    @FXML
    private Text salle;

    
    //----- init events
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        action.setDisable(true);
        init_daos();
        init_label_events();
        init_emp();
        init_listview_salle();
        init_listview_matiere();
        
        init_combobox_niveaux();


    }
    
    private void init_combobox_niveaux(){
        combobox_niveau.getItems().clear();
        combobox_niveau.getItems().addAll("1","2","3","4","5","6");
        combobox_niveau.setOnAction(value-> {
            combobox_classe.setDisable(false);
            init_combobox_classe();
        });
    }
    private void init_combobox_classe(){
        combobox_classe.getItems().clear();
        for (Classe c : dao_classes.getAll()){
            if (Integer.toString(c.getRef_niv()).charAt(0) == combobox_niveau.getSelectionModel().getSelectedItem().charAt(0))
                combobox_classe.getItems().add(c.getNom());
        }
    }
    private void init_daos(){
        dao_emploi  = new EmploiDAO();
        dao_salles  = new SalleDAO();
        dao_inst    = new InstituteurDAO();
        dao_classes = new ClasseDAO();
        dao_assiste = new AssisteDAO();
        
    }
    private void init_emp(){
        emp.put("lundi_1"   ,new Emploi(1,1)) ;emp.put("lundi_2",new Emploi(1,2))   ;emp.put("lundi_3",new Emploi(1,3))   ;emp.put("lundi_4",new Emploi(1,4));
        emp.put("mardi_1"   ,new Emploi(2,1)) ;emp.put("mardi_2",new Emploi(2,2))   ;emp.put("mardi_3",new Emploi(2,3))   ;emp.put("mardi_4",new Emploi(2,4));
        emp.put("mercredi_1",new Emploi(3,1)) ;emp.put("mercredi_2",new Emploi(3,2));emp.put("mercredi_3",new Emploi(3,3));emp.put("mercredi_4",new Emploi(3,4));
        emp.put("jeudi_1"   ,new Emploi(4,1)) ;emp.put("jeudi_2",new Emploi(4,2))   ;emp.put("jeudi_3",new Emploi(4,3))   ;emp.put("jeudi_4",new Emploi(4,4));
        emp.put("vendredi_1",new Emploi(5,1)) ;emp.put("vendredi_2",new Emploi(5,2));emp.put("vendredi_3",new Emploi(5,3));emp.put("vendredi_4",new Emploi(5,4));
        emp.put("samedi_1"  ,new Emploi(6,1)) ;emp.put("samedi_2",new Emploi(6,2))   ;
    }
    
    private void init_listview_matiere(){
        listview_matieres.getItems().clear();
       for (Assiste s:dao_assiste.getAll()){
           Instituteur i = dao_inst.find(s.getRef_i());
           if (i != null) {
               listview_matieres.getItems().add(i.getNom() + i.getPrenom() + " - " + dao_assiste.getNomMatiere(Integer.parseInt(s.getRef_m())));
           }
        }
    }
    private void init_listview_salle(){
        listview_salles.getItems().clear();
        for (Salle s:dao_salles.getAll()){
            listview_salles.getItems().add(s.getType_salle());
        }
    }
    
    private void init_label_events(){
        //time labels
        h8h10.setOnMouseClicked (event -> {reset_h8h10(event);});
        h10h12.setOnMouseClicked(event -> {reset_h10h12(event);});
        h14h16.setOnMouseClicked(event -> {reset_h14h16(event);});
        h16h18.setOnMouseClicked(event -> {reset_h16h18(event);});
        //day labels
        lundi_0.setOnMouseClicked(event    -> {reset_lundi(event);});
        mardi_0.setOnMouseClicked(event    -> {reset_mardi(event);});
        mercredi_0.setOnMouseClicked(event -> {reset_mercredi(event);});
        jeudi_0.setOnMouseClicked(event    -> {reset_jeudi(event);});
        vendredi_0.setOnMouseClicked(event -> {reset_vendredi(event);});
        samedi_0.setOnMouseClicked(event   -> {reset_samedi(event);});
    }

    @FXML
    private void ajouter_emploi(ActionEvent event) {
        
        if (combobox_niveau.getSelectionModel().getSelectedIndex()==-1 || combobox_classe.getSelectionModel().getSelectedIndex()==-1){
            Alert a = new Alert(AlertType.CONFIRMATION, "Veuillez Selectionner un niveau et une classe avant d'ajouter l'emploi", ButtonType.OK);
            a.showAndWait();
            return ;
        }

        
        boolean ok = true;
        int flash = 5 ;
        
        try {
            ODB.OracleDBSingleton.getSession().setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(add_emploi.class.getName()).log(Level.SEVERE, null, ex);
        }
        Savepoint save = dao_emploi.getSave();
        
       if(dao_emploi.conflict(emp.get("lundi_1")) )  
       {ok = false; blink_node(lundi_1.getParent(),flash);} else dao_emploi.create(emp.get("lundi_1"));
        
        /*
        if(dao_emploi.Existe(emp.get("lundi_1")) || dao_emploi.create(emp.get("lundi_1"))!=-2) 
        if(dao_emploi.Existe(emp.get("lundi_2")) || dao_emploi.create(emp.get("lundi_2"))!=-2) {ok = false;blink_node(lundi_2.getParent(),flash);} 
        if(dao_emploi.Existe(emp.get("lundi_3")))  {ok = false; blink_node(lundi_3.getParent(),flash);} else dao_emploi.create(emp.get("lundi_3"));
        if(dao_emploi.Existe(emp.get("lundi_4")))  {ok = false; blink_node(lundi_4.getParent(),flash);} else dao_emploi.create(emp.get("lundi_4"));

        if(dao_emploi.Existe(emp.get("mardi_1"))) {ok = false;blink_node(mardi_1.getParent(),flash);} else dao_emploi.create(emp.get("mardi_1"));
        if(dao_emploi.Existe(emp.get("mardi_2"))) {ok = false;blink_node(mardi_2.getParent(),flash);} else dao_emploi.create(emp.get("mardi_2"));
        if(dao_emploi.Existe(emp.get("mardi_3"))) {ok = false;blink_node(mardi_3.getParent(),flash);} else dao_emploi.create(emp.get("mardi_3"));
        if(dao_emploi.Existe(emp.get("mardi_4"))) {ok = false;blink_node(mardi_4.getParent(),flash);} else dao_emploi.create(emp.get("mardi_4"));
        
        if(dao_emploi.Existe(emp.get("mercredi_1"))) {ok = false;blink_node(mercredi_1.getParent(),flash);} else dao_emploi.create(emp.get("mercredi_1"));
        if(dao_emploi.Existe(emp.get("mercredi_2"))) {ok = false;blink_node(mercredi_2.getParent(),flash);} else dao_emploi.create(emp.get("mercredi_2"));
        if(dao_emploi.Existe(emp.get("mercredi_3"))) {ok = false;blink_node(mercredi_3.getParent(),flash);} else dao_emploi.create(emp.get("mercredi_3"));
        if(dao_emploi.Existe(emp.get("mercredi_4"))) {ok = false;blink_node(mercredi_4.getParent(),flash);} else dao_emploi.create(emp.get("mercredi_4"));
        
        if(dao_emploi.Existe(emp.get("jeudi_1"))) {ok = false;blink_node(jeudi_1.getParent(),flash);} else dao_emploi.create(emp.get("jeudi_1"));
        if(dao_emploi.Existe(emp.get("jeudi_2"))) {ok = false;blink_node(jeudi_2.getParent(),flash);} else dao_emploi.create(emp.get("jeudi_2"));
        if(dao_emploi.Existe(emp.get("jeudi_3"))) {ok = false;blink_node(jeudi_3.getParent(),flash);} else dao_emploi.create(emp.get("jeudi_3"));
        if(dao_emploi.Existe(emp.get("jeudi_4"))) {ok = false;blink_node(jeudi_4.getParent(),flash);} else dao_emploi.create(emp.get("jeudi_4"));
        
        if(dao_emploi.Existe(emp.get("vendredi_1"))) {ok = false;blink_node(vendredi_1.getParent(),flash);} else dao_emploi.create(emp.get("vendredi_1"));
        if(dao_emploi.Existe(emp.get("vendredi_2"))) {ok = false;blink_node(vendredi_2.getParent(),flash);} else dao_emploi.create(emp.get("vendredi_2"));
        if(dao_emploi.Existe(emp.get("vendredi_3"))) {ok = false;blink_node(vendredi_3.getParent(),flash);} else dao_emploi.create(emp.get("vendredi_3"));
        if(dao_emploi.Existe(emp.get("vendredi_4"))) {ok = false;blink_node(vendredi_4.getParent(),flash);} else dao_emploi.create(emp.get("vendredi_4"));
        
        if(dao_emploi.Existe(emp.get("samedi_1"))) {ok = false;blink_node(samedi_1.getParent(),flash);} else dao_emploi.create(emp.get("samedi_1"));
        if(dao_emploi.Existe(emp.get("samedi_2"))) {ok = false;blink_node(samedi_2.getParent(),flash);} else dao_emploi.create(emp.get("samedi_2"));
        */
        if (!ok){
            //dao_emploi.loadSave(save);
        }
        
        try {
            ODB.OracleDBSingleton.getSession().commit();
            ODB.OracleDBSingleton.getSession().setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(add_emploi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //-- resets methods
    public void reset_salle(Node node){
        Pane panel = (Pane) node;
        Text txt_salle = (Text) panel.getChildren().get(3);
        txt_salle.setText("-----------------");
        txt_salle.setStrikethrough(false);
        txt_salle.setStyle("-fx-fill:black;");
        
        //
        Emploi e = emp.get(panel.getChildren().get(0).getId());
        if (e != null) {
            e.setId_assiste(-1);
            emp.put(panel.getChildren().get(0).getId(), e);
        }
    }
    
    
    public void reset_salles(Node ...node){
        for (Node n : node) {
            reset_salle(n);
        }     
    }
    
    public void reset_assiste(Node node){
        Pane panel = (Pane) node;
        // matiere
        Text txt = (Text) panel.getChildren().get(1);
        txt.setText("---------------------------------------");
        txt.setStrikethrough(false);
        txt.setStyle("-fx-fill:black;");
        // instituteur
        txt = (Text) panel.getChildren().get(2);
        txt.setText("---------------------------------------");
        txt.setStrikethrough(false);
        txt.setStyle("-fx-fill:black;");
        //
        Emploi e = emp.get(panel.getChildren().get(0).getId());
        if (e != null) {
            e.setId_assiste(-1);
            emp.put(panel.getChildren().get(0).getId(), e);
        }
    }
    
    
    public void reset_assistes(Node ...node){
        for (Node n : node) {
            reset_assiste(n);
        }     
    }
    ///---- errors
    private void blink_node(Node node, int count){
        FadeTransition ft = new FadeTransition(Duration.millis(1000), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(count);
        ft.setAutoReverse(false);
        ft.play();
    }
    private void erreur_assiste(Node node){
      Pane panel = (Pane) node;
      
      //matiere
      Text txt = (Text) panel.getChildren().get(1);
      txt.setStrikethrough(true);
      txt.setStyle("-fx-fill:red;");
      blink_node(txt,10);
      //professeur
      txt = (Text) panel.getChildren().get(2);
      txt.setStrikethrough(true);
      txt.setStyle("-fx-fill:red;");
      blink_node(txt,10);
    }
    private void erreur_salle(Node node){
      Pane panel = (Pane) node;
      Text txt_salle = (Text) panel.getChildren().get(3);
      txt_salle.setStrikethrough(true);
      txt_salle.setStyle("-fx-fill:red;");
      blink_node(txt_salle,8);
    }
    
    
    ///---- reset events
    private void reset_h8h10(MouseEvent event) {
        reset_salles(lundi_1.getParent(),mardi_1.getParent(),mercredi_1.getParent(),jeudi_1.getParent(),vendredi_1.getParent(),samedi_1.getParent());
        reset_assistes(lundi_1.getParent(),mardi_1.getParent(),mercredi_1.getParent(),jeudi_1.getParent(),vendredi_1.getParent(),samedi_1.getParent());
    }
    private void reset_h10h12(MouseEvent event) {
        reset_salles(lundi_2.getParent(),mardi_2.getParent(),mercredi_2.getParent(),jeudi_2.getParent(),vendredi_2.getParent(),samedi_2.getParent());
        reset_assistes(lundi_2.getParent(),mardi_2.getParent(),mercredi_2.getParent(),jeudi_2.getParent(),vendredi_2.getParent(),samedi_2.getParent());
}
    private void reset_h14h16(MouseEvent event) {
        reset_salles(lundi_3.getParent(),mardi_3.getParent(),mercredi_3.getParent(),jeudi_3.getParent(),vendredi_3.getParent());
        reset_assistes(lundi_3.getParent(),mardi_3.getParent(),mercredi_3.getParent(),jeudi_3.getParent(),vendredi_3.getParent());
    }
    private void reset_h16h18(MouseEvent event) {
        reset_salles(lundi_4.getParent(),mardi_4.getParent(),mercredi_4.getParent(),jeudi_4.getParent(),vendredi_4.getParent());
        reset_assistes(lundi_4.getParent(),mardi_4.getParent(),mercredi_4.getParent(),jeudi_4.getParent(),vendredi_4.getParent());

    }
    private void reset_lundi(MouseEvent event) {
        reset_salles(lundi_1.getParent(),lundi_2.getParent(),lundi_3.getParent(),lundi_4.getParent());
        reset_assistes(lundi_1.getParent(),lundi_2.getParent(),lundi_3.getParent(),lundi_4.getParent());

    }
    private void reset_mardi(MouseEvent event) {
        reset_salles(mardi_1.getParent(),mardi_2.getParent(),mardi_3.getParent(),mardi_4.getParent());
        reset_assistes(mardi_1.getParent(),mardi_2.getParent(),mardi_3.getParent(),mardi_4.getParent());

    }
    private void reset_mercredi(MouseEvent event) {
        reset_salles(mercredi_1.getParent(),mercredi_2.getParent(),mercredi_3.getParent(),mercredi_4.getParent());
        reset_assistes(mercredi_1.getParent(),mercredi_2.getParent(),mercredi_3.getParent(),mercredi_4.getParent());
    }
    private void reset_jeudi(MouseEvent event) {
        reset_salles(jeudi_1.getParent(),jeudi_2.getParent(),jeudi_3.getParent(),jeudi_4.getParent());
        reset_assistes(jeudi_1.getParent(),jeudi_2.getParent(),jeudi_3.getParent(),jeudi_4.getParent());

    }
    private void reset_vendredi(MouseEvent event) {
        reset_salles(vendredi_1.getParent(),vendredi_2.getParent(),vendredi_3.getParent(),vendredi_4.getParent());
        reset_assistes(vendredi_1.getParent(),vendredi_2.getParent(),vendredi_3.getParent(),vendredi_4.getParent());

    }
    private void reset_samedi(MouseEvent event) {
        reset_salles(samedi_1.getParent(),samedi_2.getParent());
        reset_assistes(samedi_1.getParent(),samedi_2.getParent());

    }
    @FXML private void reset_cell(ActionEvent event) {
        JFXButton button = (JFXButton) event.getSource();
        Pane parent = (Pane) button.getParent();
          reset_salle(parent);
          reset_assiste(parent);
          
        Emploi e = emp.get(parent.getChildren().get(0).getId());
        if (e != null) {
            e.setId(-1);
            e.setId_assiste(-1);
            e.setId_salle(-1);
            emp.put(parent.getChildren().get(0).getId(), e);
        }
          
    }
    @FXML private void reset_all_cells(MouseEvent event) {
        reset_h8h10(event);
        reset_h10h12(event);
        reset_h14h16(event);
        reset_h16h18(event);
    }
    //----- drag events
       @FXML
    private void matiere_drag_detected(MouseEvent event) {
        if (listview_matieres.getSelectionModel().getSelectedIndex() != -1) {
            ObservableList<Assiste> all = dao_assiste.getAll();
            Dragboard db = listview_matieres.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            for (int i=0;i<all.size();i++){
                Instituteur inst = dao_inst.find(all.get(i).getRef_i());
                String item_selected = listview_matieres.getSelectionModel().getSelectedItem().substring(0, listview_matieres.getSelectionModel().getSelectedItem().indexOf(" -"));
                if (inst!=null && item_selected.equals(inst.getNom()+inst.getPrenom())){
                    content.putString(""+all.get(i).getId_assiste());
                }
            }
            db.setContent(content);
            event.consume();
        }
    }
    
    @FXML private void salle_drag_detected(MouseEvent event) {
        if (listview_salles.getSelectionModel().getSelectedIndex() != -1) {
            ObservableList<Salle> all = dao_salles.getAll();
            Dragboard db = listview_salles.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            for (int i=0;i<all.size();i++){
                if (listview_salles.getSelectionModel().getSelectedItem().equals(all.get(i).getType_salle())){
                    content.putString(""+all.get(i).getIdentifiant());
                }
            }
            db.setContent(content);
            event.consume();
        }
    }

    @FXML private void done(DragEvent event) {
    }

    @FXML private void entered(DragEvent event) {
    }

    @FXML private void exited(DragEvent event) {
    }

    @FXML private void salle_dragged_over(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML private void salle_dropped(DragEvent event) {
        // matieres
        if ((JFXListView) event.getGestureSource() == listview_matieres) {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Rectangle r = (Rectangle) event.getGestureTarget();
                Pane p = (Pane) r.getParent();
                if (p != null) {
                    int id_assiste = Integer.parseInt(db.getString());
                    Assiste assiste = dao_assiste.find(id_assiste);
                    if (assiste != null) {
                        set_assiste(p, assiste);
                        Emploi e = emp.get(p.getChildren().get(0).getId());
                        if (e != null && !dao_emploi.conflict(e)) {
                            e.setId_assiste(id_assiste);
                            emp.put(p.getChildren().get(0).getId(), e);
                        }else
                        System.out.println("emploi null out emploi existe");
                    }else
                    System.out.println("assiste null");
                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        }

        //
        if ((JFXListView) event.getGestureSource() == listview_salles) {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Rectangle r = (Rectangle) event.getGestureTarget();
                Pane p = (Pane) r.getParent();
                if (p != null) {
                    int id_salle_in_db = Integer.parseInt(db.getString());
                    Salle s = dao_salles.find(id_salle_in_db);
                    if (s != null) {
                        set_salle(p, s);
                        Emploi e = emp.get(p.getChildren().get(0).getId());
                        if (e != null && !dao_emploi.conflict(e)) {
                            e.setId_salle(s.getIdentifiant());
                            emp.put(p.getChildren().get(0).getId(), e);
                        } else
                            System.out.println("emploi null out emploi existe");
                    } else
                        System.out.println("assiste null");
                    
                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        }
    }
    
    // emploi methods
    private void set_salle(Node n, Salle s) {
        Pane panel = (Pane) n;
        reset_salle(n);
        Text txt_salle = (Text) panel.getChildren().get(3);
        txt_salle.setText(s.getType_salle());   
        action.setDisable(true);
    }
    private void set_assiste(Node n, Assiste s) {
        Pane panel = (Pane) n;
        reset_assiste(n);
        
        Instituteur prof = dao_inst.find(s.getRef_i());
        Text txt_prof = (Text) panel.getChildren().get(2);
        txt_prof.setText(""+prof.getNom() + " " + prof.getPrenom());
        
        Text txt_mat = (Text) panel.getChildren().get(1);
        txt_mat.setText(dao_assiste.getNomMatiere(Integer.parseInt(s.getRef_m())));
        action.setDisable(true);
    }

    // navigation methods
    @FXML private void goto_admin_main(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("../mainwindow.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML private void goto_list(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("list_emploi.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

 
    @FXML
    private void check_conflicts(ActionEvent event) {
        action.setDisable(false);
    }

 

}
