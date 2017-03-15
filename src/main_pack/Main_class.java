/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_pack;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import GUI.ScreensController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 *
 * @author DELL
 */
public class Main_class extends Application {
    
    public static double xOffset = 0;
    public static double yOffset = 0;
    
     public static final String MAIN_SCREEN = "MainWindow";
     public static final String MAIN_SCREEN_FXML = "../GUI/mainwindow.fxml";
     public static final String GEST_ELEVE = "GestionEleve";
     public static final String GEST_ELEVE_FXML ="../GUI/gestionEleve.fxml";
     public static  Stage stg ;
     @Override
     public void start(Stage primaryStage) {
         

       ScreensController mainContainer = new ScreensController();
       mainContainer.loadScreen(Main_class.MAIN_SCREEN,
                            Main_class.MAIN_SCREEN_FXML);
       mainContainer.loadScreen(Main_class.GEST_ELEVE,
                            Main_class.GEST_ELEVE_FXML);

       mainContainer.setScreen(Main_class.MAIN_SCREEN);

       primaryStage.initStyle(StageStyle.UNDECORATED);
       stg=primaryStage;
       
       
       Group root = new Group();
       /*
       root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
       */
       
       
       root.getChildren().addAll(mainContainer);
       Scene scene = new Scene(root);
       primaryStage.setScene(scene);
       primaryStage.show(); 
     }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("test");
        launch(args);
    }
    
}
