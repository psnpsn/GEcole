/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author DELL
 */
public class ScreensController extends AnchorPane {
    
    	private HashMap<String, Node> screens = new HashMap<>(); 
        
        public void addScreen(String name, Node screen) {
       screens.put(name, screen);
        } 
        
        public boolean loadScreen(String name, String res){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(res));
                    Parent loadScreen = (Parent) loader.load();
                    ControlledScreen myScreenControler =
                            ((ControlledScreen) loader.getController());
                    myScreenControler.setScreenParent(this);
                    addScreen(name, loadScreen); 
                    return true;
                } catch (IOException ex) {
                    Logger.getLogger(ScreensController.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
        }
        
    public boolean setScreen(final String name) {

     if(screens.get(name) != null) { //screen loaded
       final DoubleProperty opacity = opacityProperty();

       //Is there is more than one screen
       if(!this.getChildren().isEmpty()){
         Timeline fade = new Timeline(
           new KeyFrame(Duration.ZERO,
                        new KeyValue(opacity,1.0)),
           new KeyFrame(new Duration(1000), new EventHandler() {

                 @Override
                 public void handle(Event t) {
                   //remove displayed screen
                   getChildren().remove(0);
                   //add new screen
                   getChildren().add(0, screens.get(name));
                   Timeline fadeIn = new Timeline(
                       new KeyFrame(Duration.ZERO,
                              new KeyValue(opacity, 0.0)),
                       new KeyFrame(new Duration(800),
                              new KeyValue(opacity, 1.0)));
                   fadeIn.play();
                 }

             
             
               }, new KeyValue(opacity, 0.0)));
         fade.play();
       } else {
         //no one else been displayed, then just show
         setOpacity(0.0);
         getChildren().add(screens.get(name));
         Timeline fadeIn = new Timeline(
             new KeyFrame(Duration.ZERO,
                          new KeyValue(opacity, 0.0)),
             new KeyFrame(new Duration(2500),
                          new KeyValue(opacity, 1.0)));
         fadeIn.play();
       }
       return true;
     } else {
         System.out.println("screen hasn't been loaded!\n");
         return false;
   } 
   }
     public boolean unloadScreen(String name) {
     if(screens.remove(name) == null) {
       System.out.println("Screen didn't exist");
       return false;
     } else {
       return true;
     }
   } 
}