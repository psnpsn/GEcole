
package GUI.emploi;

import DAO.ClasseDAO;
import DAO.SalleDAO;
import Models.Classe;
import Models.Emploi;
import Models.Salle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class add_emploi implements Initializable {

    @FXML
    private JFXButton action;
    @FXML
    private Text matiere;
    @FXML
    private Text instituteur;
    @FXML
    private Text salle;
    @FXML
    private ComboBox<String> classe;
    @FXML
    private ComboBox<String> niveau;
    @FXML
    private JFXListView<String> list_des_salles;
    Pane targetPane = null;
    @FXML
    private JFXListView<String> list_matieres;
    @FXML
    private Rectangle lundi_1;
    @FXML
    private Rectangle lundi_0;
    @FXML
    private Rectangle h8h10;
    @FXML
    private Rectangle h10h12;
    @FXML
    private Rectangle h14h16;
    @FXML
    private Rectangle h16h18;
    @FXML
    private Rectangle mardi_0;
    @FXML
    private Rectangle mercredi_0;
    @FXML
    private Rectangle jeudi_0;
    @FXML
    private Rectangle vendredi_0;
    @FXML
    private Rectangle samedi_0;
    @FXML
    private Rectangle lundi_2;
    @FXML
    private Rectangle lundi_3;
    @FXML
    private Rectangle lundi_4;
    @FXML
    private Rectangle mardi_1;
    @FXML
    private Rectangle mardi_2;
    @FXML
    private Rectangle mardi_3;
    @FXML
    private Rectangle mardi_4;
    @FXML
    private Rectangle mercredi_1;
    @FXML
    private Rectangle mercredi_2;
    @FXML
    private Rectangle mercredi_3;
    @FXML
    private Rectangle mercredi_4;
    @FXML
    private Rectangle jeudi_1;
    @FXML
    private Rectangle jeudi_2;
    @FXML
    private Rectangle jeudi_3;
    @FXML
    private Rectangle jeudi_4;
    @FXML
    private Rectangle vendredi_1;
    @FXML
    private Rectangle vendredi_2;
    @FXML
    private Rectangle vendredi_3;
    @FXML
    private Rectangle vendredi_4;
    @FXML
    private Rectangle samedi_1;
    @FXML
    private Rectangle samedi_2;

    HashMap emplois = new HashMap();
    ArrayList<Salle> salles = new ArrayList<Salle>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        niveau.getItems().addAll("1","2","3","4","5","6");
        emplois.put(lundi_0,new Emploi());

        SalleDAO dao = new SalleDAO();
        ObservableList<Salle> s = (ObservableList<Salle>) dao.getAll();
        for (int i = 0;i<s.size();i++){
            salles.add(s.get(i));
            list_des_salles.getItems().add(s.get(i).getType_salle());
         }
        init_emploi();
    }

    private void init_emploi(){
        emplois.clear();
       // emplois.put(lundi_0,new emp_entry());
        emplois.put(lundi_1,new Emploi());
        emplois.put(lundi_2,new Emploi());
        emplois.put(lundi_3,new Emploi());
        emplois.put(lundi_4,new Emploi());
       // emplois.put(mardi_0,new emp_entry());
        emplois.put(mardi_1,new Emploi());
        emplois.put(mardi_2,new Emploi());
        emplois.put(mardi_3,new Emploi());
        emplois.put(mardi_4,new Emploi());
       // emplois.put(mercredi_0,new emp_entry());
        emplois.put(mercredi_1,new Emploi());
        emplois.put(mercredi_2,new Emploi());
        emplois.put(mercredi_3,new Emploi());
        emplois.put(mercredi_4,new Emploi());
      //  emplois.put(jeudi_0,new emp_entry());
        emplois.put(jeudi_1,new Emploi());
        emplois.put(jeudi_2,new Emploi());
        emplois.put(jeudi_3,new Emploi());
        emplois.put(jeudi_4,new Emploi());
      //  emplois.put(vendredi_0,new emp_entry());
        emplois.put(vendredi_1,new Emploi());
        emplois.put(vendredi_2,new Emploi());
        emplois.put(vendredi_3,new Emploi());
        emplois.put(vendredi_4,new Emploi());
       // emplois.put(samedi_0,new emp_entry());
        emplois.put(samedi_1,new Emploi());
        emplois.put(samedi_2,new Emploi());
    }

    private void rerender_emploi() {
        Iterator<Map.Entry<Rectangle, Emploi>> iterator = emplois.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Rectangle, Emploi> entry = iterator.next();
            Pane p = (Pane) entry.getKey().getParent();
            if (p != null && entry.getValue() != null) {
                Text txtsalle = (Text) p.getChildren().get(3);
                if (entry.getValue().getId_salle()!= -1) {
                    SalleDAO daos = new SalleDAO();
                    Salle s = daos.find(entry.getValue().getId_salle());
                    if (s != null) {
                        txtsalle.setText(s.getType_salle()+"("+s.getCapacite()+")");
                    }
                } else {
                    txtsalle.setText("-----------------");
                }
            }
        }
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
    private void goto_list(ActionEvent event) {
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
    private void ajouter_emploi(ActionEvent event) {
    }


    @FXML
    private void entered(DragEvent event) { // target
        if (event.getDragboard().hasString()) {
            if (event.getSource() == list_des_salles) {
                if (targetPane != null) {
                    Text txt = (Text) targetPane.getChildren().get(3);
                    txt.setUnderline(true);
                }
            }
        }
        event.consume();
    }



    @FXML private void dropped(DragEvent event) {
            if ((JFXListView) event.getGestureSource() == list_des_salles) {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Rectangle r = (Rectangle) event.getGestureTarget();

                Pane p = (Pane) r.getParent();
                if (p!=null){
                    Text txt = (Text) p.getChildren().get(3);
                    int id_salle_in_db = Integer.parseInt(db.getString());
                    SalleDAO daos = new SalleDAO();
                    Salle s = daos.find(id_salle_in_db);
                    ((Emploi)emplois.get(r)).setId_salle(s.getIdentifiant());
                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
            rerender_emploi();
    }
    }

    @FXML
    private void detected(MouseEvent event) { // source
        if (list_des_salles.getSelectionModel().getSelectedIndex() != -1) {
            Dragboard db = list_des_salles.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            //content.putString(list_des_salles.getSelectionModel().getSelectedItem());
            //String txt = list_des_salles.getSelectionModel().getSelectedItem();


            for (int i=0;i<salles.size();i++){
                if (list_des_salles.getSelectionModel().getSelectedItem().equals(salles.get(i).getType_salle())){
                    content.putString(""+salles.get(i).getIdentifiant());
                    //System.out.println(""+salles.get(i).getIdentifiant());
                }

            }


            db.setContent(content);
            event.consume();
        }
    }

    @FXML
    private void done(DragEvent event) { // source
        if (event.getTransferMode() == TransferMode.MOVE) {
            if (event.getSource() == list_des_salles) {
                System.out.println("ajout 1 salle");
            }
        }
        event.consume();
    }

    @FXML
    private void niveau_selection(ActionEvent event) {
        classe.getItems().clear();
        if (niveau.getSelectionModel().getSelectedIndex()==-1)
            classe.setDisable(true);
        else {
            classe.setDisable(false);
            SalleDAO dao = new SalleDAO();
            ClasseDAO daoc = new ClasseDAO();
            ObservableList<Classe> classes = (ObservableList<Classe>) daoc.getAll();
            {
                for (int i = 0 ; i < classes.size() ; i++){
                    String c = ""+classes.get(i).getRef_niv();
                    if (c.equalsIgnoreCase(niveau.getSelectionModel().getSelectedItem()))
                        classe.getItems().add(classes.get(i).getNom());
                }
            }
        }
    }


    @FXML
    private void re_init(Event event) {
        init_emploi();
        rerender_emploi();
    }

   @FXML private void exited(DragEvent event) {
        Rectangle rec = (Rectangle) event.getGestureTarget();
        targetPane = null;
        if (rec != null) {
            Pane p = (Pane) rec.getParent();
            if (p != null) {
                Text txt = (Text) p.getChildren().get(3);
                txt.setUnderline(false);
            }
        }
        event.consume();
    }
   @FXML private void over(DragEvent event) {
        if (event.getDragboard().hasString()) {
            Rectangle targetRect  = (Rectangle) event.getGestureTarget();
            if (targetRect != null) {
                targetPane = (Pane) targetRect.getParent();
            } else {
                targetPane = null;
            }
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }
}