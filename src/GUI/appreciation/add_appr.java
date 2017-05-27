
package GUI.appreciation;

import DAO.AppartientDAO;
import DAO.AppreciationDAO;
import DAO.ClasseDAO;
import DAO.EleveDAO;
import DAO.InstituteurDAO;
import DAO.ModuleDAO;
import Models.Appreciation;
import Models.Classe;
import Models.Eleve;
import Models.Instituteur;
import Models.Module;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class add_appr implements Initializable {

    @FXML  private JFXButton action;
    @FXML  private JFXComboBox<String> cmb_classe, cmb_eleve,cmb_inst,cmb_module;
    @FXML  private JFXTextArea txt_contenu;
    private ArrayList<Integer> id_insts = new ArrayList<>();
    private ArrayList<Integer> id_cls = new ArrayList<>();
    private ArrayList<Integer> id_elves = new ArrayList<>();
    private ArrayList<Integer> id_mods = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Instituteur> all_insts = new InstituteurDAO().getAll();
        for (Instituteur e:all_insts){
            cmb_inst.getItems().add(e.getNom() + " " + e.getPrenom());
            id_insts.add(e.getId_i());
        }
        ObservableList<Classe> all_cls = new ClasseDAO().getAll();
        for (Classe e:all_cls){
            cmb_classe.getItems().add(e.getNom());
            id_cls.add(e.getId_c());
        }                
        ObservableList<Module> all_mods = new ModuleDAO().getAll();
        for (Module m:all_mods){
            cmb_module.getItems().add(m.getNom());
            id_mods.add(m.getId());
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
    private void goto_lister_appr(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("list_appr.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }

    @FXML
    private void goto_ajout_appr(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = (Scene) source.getScene();
        BorderPane border = (BorderPane) scene.getRoot();
        try {
            border.setCenter(FXMLLoader.load(getClass().getResource("add_appr.fxml")));
        } catch (IOException exception) {
            System.out.println("erreur i/o: " + exception);
        }
    }
    @FXML
    private void ajout_appr(ActionEvent event) {
        Appreciation a = new Appreciation();
        a.setContenu(txt_contenu.getText());
        a.setRef_e(id_elves.get(cmb_eleve.getSelectionModel().getSelectedIndex()));
        a.setRef_inst(id_insts.get(cmb_inst.getSelectionModel().getSelectedIndex()));
        a.setRef_module(id_mods.get(cmb_module.getSelectionModel().getSelectedIndex()));
        if (new AppreciationDAO().create(a)!=-1){
            goto_lister_appr(event);
        }
        
    }

    @FXML
    private void update_classe(ActionEvent event) {
        init_combo_eleves();
    }

    private void init_combo_eleves() {
        id_elves.clear();
        cmb_eleve.getItems().clear();
        EleveDAO daoelv = new EleveDAO();
        AppartientDAO appdao = new AppartientDAO();
        ObservableList<Eleve> all = daoelv.getAll();
        for (Eleve e : all) {
            cmb_eleve.getSelectionModel().select(0);
            if (appdao.appartient(id_cls.get(cmb_classe.getSelectionModel().getSelectedIndex()), e.getId_e())) {
                cmb_eleve.getItems().add(e.getNom() + " " + e.getPrenom());
                id_elves.add(e.getId_e());
            }
        }
    }
}
