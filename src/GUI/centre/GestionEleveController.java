/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.centre;

import DAO.DAO;
import DAO.EleveDAO;
import GUI.LoginController;
import Models.Eleve;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main_pack.Main_class;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GestionEleveController implements Initializable {

    @FXML
    private TableView<Eleve> tableView;
    @FXML
    private TableColumn<Eleve, String> nomCol;
    @FXML
    private TableColumn<Eleve, Date> dateCol;
    @FXML
    private TableColumn<Eleve, String> sexCol;
    @FXML
    private TableColumn<Eleve, Date> dateinsCol;
    @FXML
    private TableColumn<Eleve, String> classeCol;
    @FXML
    private TableColumn<Eleve, ?> modifCol;
    @FXML
    private TableColumn<Eleve, Boolean> cochCol;

    
    /**
     * Initializes the controller class.
     */
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
      
    }

    @FXML
    private void click_ajouter(ActionEvent event) {
        try {
            URL loader = getClass().getResource("ajoutEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void click_voir(ActionEvent event) {
               try {
            URL loader = getClass().getResource("voirEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void click_retour(ActionEvent event) {
        try {
            URL loader = getClass().getResource("../mainwindow.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void click_chercher(ActionEvent event) {
        DAO elevedao = new EleveDAO();
        ObservableList<Eleve> masterData = elevedao.getAll();
        tableView.getItems().setAll(masterData);
        
    }
/*
    private void click_modifier(ActionEvent event) {
                try {
            URL loader = getClass().getResource("modEleve.fxml");
            AnchorPane middle = FXMLLoader.load(loader);

            BorderPane border = Main_class.getRoot();
            border.setCenter(middle);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
*/

    @FXML
    private void click_supp(ActionEvent event) {
    }

    @FXML
    private void click_supptout(ActionEvent event) {
    }

    private void initCol() {
        nomCol.setCellValueFactory(cellData -> cellData.getValue().fullnomProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateNaissProperty());
        sexCol.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        dateinsCol.setCellValueFactory(cellData -> cellData.getValue().dateInsProperty());
        classeCol.setCellValueFactory(cellData -> cellData.getValue().ref_nProperty().asString());
        cochCol.setCellFactory(CheckBoxTableCell.forTableColumn(cochCol));
        cochCol.setCellValueFactory(cellData -> cellData.getValue().cocherProperty());
        
        /* 
        modifCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Eleve, Boolean>, ObservableValue<Boolean>>() {
 
                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Eleve, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });
        modifCol.setCellFactory(
                new Callback<TableColumn<Eleve, Boolean>, TableCell<ELeve, Boolean>>() {
 
                    @Override
                    public TableCell<Eleve, Boolean> call(TableColumn<Eleve, Boolean> p) {
                        return new ButtonCell();
                    }
 
                });
 
    }
 
    //Define the button cell
    private class ButtonCell extends TableCell<Eleve, Boolean> {
 
        final Button cellButton = new Button("Action");
 
        ButtonCell() {
 
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                public void handle(ActionEvent t) {
                    // do something when button clicked
                    //...
                }
            });
        }
 
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }

        }
        */
    }


}
