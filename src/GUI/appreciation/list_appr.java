
package GUI.appreciation;

import Models.Appreciation;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class list_appr implements Initializable {

    @FXML  private TableView<Appreciation> table_appr;
    @FXML  private TableColumn<Appreciation, String> colonne_id;
    @FXML  private TableColumn<Appreciation, String> colonne_eleve,colonne_inst,colonne_module,colonne_contenu,colonne_modifier,colonne_cocher;
    @FXML  private JFXTextField id_inst,id_eleve;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goto_admin_main(ActionEvent event) {
    }

    @FXML
    private void goto_lister_appr(ActionEvent event) {
    }

    @FXML
    private void goto_ajouter_apps(ActionEvent event) {
    }

    @FXML
    private void supprimer_appr(ActionEvent event) {
    }

    @FXML
    private void supprimer_apprs(ActionEvent event) {
    }

    @FXML
    private void user_selection(MouseEvent event) {
    }
    
}
