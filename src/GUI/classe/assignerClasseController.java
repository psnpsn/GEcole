package GUI.classe;

import DAO.AppartientDAO;
import DAO.ClasseDAO;
import DAO.DAO;
import DAO.EleveDAO;
import Models.Classe;
import Models.Eleve;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class assignerClasseController implements Initializable {

    @FXML
    private JFXButton assigner;
    @FXML
    private TableView<Eleve> tableView;
    @FXML
    private TableColumn<Eleve, String> nomCol;
    @FXML
    private TableColumn<Eleve, String> idCol;
    @FXML
    private TableColumn<Eleve, String> dateCol;
    @FXML
    private TableColumn<Eleve, String> adresseCol;
    @FXML
    private TableColumn<Eleve, String> cochCol;
    @FXML
    private Text txt_classe;
    @FXML
    private Text txt_capacite;
    @FXML
    private Text txt_dispo;
    @FXML
    private Text txt_nb_eleve;

    private int id_classe = -1;

    private EleveDAO elevedao = new EleveDAO();

    private ObservableList<Eleve> eleves;

    private ArrayList<Integer> ids = new ArrayList<Integer>();

    public assignerClasseController() {
        this.eleves =  FXCollections.observableArrayList();
    }

    @FXML
    private void user_selection(MouseEvent event) {
        update_selection();
    }

    private void update_selection() {
        tableView.getSelectionModel().clearSelection();
        for (int i = 0; i < ids.size(); i++) {
            tableView.getSelectionModel().select(ids.get(i));
        }
    }

    private void initCol() {
        nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        idCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getId_e()));
        });
        dateCol.setCellValueFactory(cellData -> {
            Date d = cellData.getValue().getDateNaiss();
          return new SimpleStringProperty(String.valueOf(d).substring(0,String.valueOf(d).indexOf(" ")));  
        });
        adresseCol.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
        cochCol.setCellFactory(callback_fn_select_classe);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        tableView.getSelectionModel().setCellSelectionEnabled(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       maj_info_text();
    }

    private void maj_info_text() {
        eleves = elevedao.getAll();

        AppartientDAO appdao = new AppartientDAO();

        // re pas afficher les eleves assigner a une classe
        ObservableList<Eleve> neweleves = FXCollections.observableArrayList();
        for (Eleve e : eleves) {
            if (!appdao.appartient(e.getId_e())) {
                neweleves.add(e);
            }
        }
        tableView.getItems().setAll(neweleves);
        if (id_classe != -1) {
            txt_classe.setText("classe de id " + id_classe);
            ClasseDAO classdao = new ClasseDAO();
            Classe c = classdao.find(id_classe);
            if (c != null) {
                txt_classe.setText(c.getNom());
                txt_capacite.setText(String.valueOf(c.getCapacite()));
                txt_nb_eleve.setText(String.valueOf(c.getNbE()));
                txt_dispo.setText(String.valueOf(c.getCapacite() - c.getNbE()));
            }
        }

    }

    public void open_for_assign(int id) {
        id_classe = id;
        maj_info_text();
    }

    @FXML
    private void click_retour(ActionEvent event) {
    }

    @FXML
    private void click_trouver(ActionEvent event) {
    }

    @FXML
    private void click_assigner(ActionEvent event) {
        AppartientDAO appdao = new AppartientDAO();
        for (int i = 0 ; i < ids.size() ; i++){
            Eleve e = tableView.getItems().get(ids.get(i));
            if (e!=null)
                appdao.assigner(id_classe, e.getId_e());
        };
        appdao.mettre_a_jour_nb_eleves();
        maj_info_text();
    }
    
    Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>> callback_fn_select_classe = new Callback<TableColumn<Eleve, String>, TableCell<Eleve, String>>() {
        @Override
        public TableCell call(final TableColumn param) {
            final TableCell cell = new TableCell() {

                @Override
                public void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        final JFXCheckBox check_box = new JFXCheckBox();
                        check_box.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (check_box.isSelected()) {
                                    ids.add(getIndex());
                                    param.getTableView().getSelectionModel().select(getIndex());
                                    update_selection();
                                } else {
                                    ids.remove(ids.indexOf(getIndex()));
                                }
                                param.getTableView().getSelectionModel().clearSelection(getIndex());
                                update_selection();
                            }
                        });
                        setGraphic(check_box);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    }
                }
            };
            return cell;
        }
    };

}
