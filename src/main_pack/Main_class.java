/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_pack;


import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

/**
 *
 * @author DELL
 */
public class Main_class extends Application {
    
    public static double xOffset = 0;
    public static double yOffset = 0;
    
     private static BorderPane root = new BorderPane();

    
     public static final String MENU_UP = "../GUI/uppermenu.fxml";
     public static final String MENU_LOW = "../GUI/lowermenu.fxml";
     public static final String LOGIN = "../GUI/login.fxml";
     public static  Stage stage ;
     
      public static BorderPane getRoot() {
    return root;
  }
      
     @Override
     public void start(Stage primaryStage) throws IOException {
         
         stage=primaryStage;
         
             URL upper = getClass().getResource(MENU_UP);
             HBox barup = FXMLLoader.load(upper);

            URL lower = getClass().getResource(MENU_LOW);
            HBox barlow = FXMLLoader.load(lower);
            
            URL content = getClass().getResource(LOGIN);
            AnchorPane middle = FXMLLoader.load(content);

            primaryStage.initStyle(StageStyle.UNDECORATED);
    // constructing our scene using the static root

    root.setTop(barup);
    root.setCenter(middle);
    root.setBottom(barlow);
    
    Scene scene = new Scene(root, 1200, 650);
   
    
    primaryStage.setScene(scene);
    primaryStage.show();
         

     }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("test");
        ODB.OracleDBSingleton.seConnecter("jdbc:oracle:thin:@localhost:1521:XE","oracle.jdbc.driver.OracleDriver","tester","tester");
        launch(args);
    }
    
}
