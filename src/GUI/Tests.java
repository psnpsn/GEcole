package GUI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Tests {
    
    //interraction des nodes
    
    public static boolean vemail(TextField field,Label label){
        if (field.getText().isEmpty()){ 
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        } 
        if (!email(field.getText())){
            field.getStyleClass().add("fielderror");
            label.setText("L'email est incorrecte.");
            label.setVisible(true);
            return false;    
        }else
        {
            field.getStyleClass().add("txtfield");
            label.setVisible(false);
            return true;
        }
        
    }
    
    public static boolean vtel(TextField field,Label label){
        if (field.getText().isEmpty()){ 
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        } 
        if (!telephone(field.getText())){
            field.getStyleClass().add("fielderror");
            label.setText("Numéro à 8 Chiffres.");
            label.setVisible(true);
            return false;    
        }else
        {
            field.getStyleClass().add("txtfield");
            label.setVisible(false);
            return true;
        }
        
    }
    
    public static boolean vchaine(TextField field,Label label,int max,boolean chiffre){
        if (field.getText().isEmpty()){
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        } 
        if (!chaine(field.getText(), max, chiffre)){
            field.getStyleClass().add("fielderror");
            if (!chiffre){
            label.setText("Que des lettres, de longueur maximale "+max+".");
            }else label.setText("Longueur maximale "+max+"?");
            label.setVisible(true);
            return false;    
        }else
        {
            field.getStyleClass().add("txtfield");
            label.setVisible(false);
            return true;
        }
        
    }
    
    public static boolean vcodep(TextField field,Label label){
        if (field.getText().isEmpty()){
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        } 
        if (!code_postal(field.getText())){
            field.getStyleClass().add("fielderror");
            label.setText("4 Chiffres.");
            label.setVisible(true);
            return false;    
        }else
        {
            field.getStyleClass().add("txtfield");
            label.setVisible(false);
            return true;
        }
        
    }
    
    public static boolean vdate(JFXDatePicker field,Label label){
        LocalDate d = field.getValue();
        if (d==null){
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        } 
        if (!date_naissance(Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))){
            field.getStyleClass().add("fielderror");
            label.setText("Choisir une date antérieur.");
            label.setVisible(true);
            return false;    
        }else
        {
            field.getStyleClass().add("txtfield");
            label.setVisible(false);
            return true;
        }
        
    }
    
    public static boolean vcombo(JFXComboBox field, Label label){
        if (field.getSelectionModel().getSelectedIndex() == -1 ){
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }else
        {
            field.getStyleClass().add("txtfield");
            label.setVisible(false);
            return true;
        }
    }
    
    //tests sur les champs

    public static boolean email(String s) {
        // doit contenir un @
        if (!s.contains("@")) {
            return false;
        }
        // pa d'espace
        if (s.contains(" ")) {
            return false;
        }
        //ne commence pa par @
        if (s.indexOf('@') == 0) {
            return false;
        }
        // ne se termine pa par .
        if (s.charAt(s.length()-1)=='.') {
            return false;
        }
        // ne se termine pa par un point
        if (s.length() == s.indexOf('.')) {
            return false;
        }
        //pa plus que 20 chars
        if (s.length() >=20) {
            return false;
        }
        //doit contenir un . apres le @
        // exemple : @gmail.com
        if (!s.substring(s.indexOf("@")).contains(".")) {
            return false;
        }
        return true;
    }

    public static boolean telephone(String s) {
        // pa de lettre dans un tel
        if (!s.chars().allMatch(Character::isDigit)) {
            return false;
        }
        // n'es pa composer de 8 chiffres.
        if (s.length() != 8) {
            return false;
        }
        return true;
    }

    public static boolean chaine(String s, int max, boolean accepter_chiffre) {
        // depasse la limite
        if (s.length() > max) {
            return false;
        }
        // pa de chiffre
        if ((!accepter_chiffre) && !s.chars().allMatch(Character::isLetter)) {
            return false;
        }
        return true;
    }

    public static boolean code_postal(String s) {
        // 4 chiffre , a verifier...
        if (s.length() != 4) {
            return false;
        }
        // que des chiffres..
        if (!s.chars().allMatch(Character::isDigit)) {
            return false;
        }
        return true;
    }

    public static boolean date_naissance(java.util.Date d) {
        // l'eleve n'est pa encore née
        if (d.after(new java.util.Date())) {
            return false;
        }
        return true;
    }

}
