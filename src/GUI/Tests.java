package GUI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

public class Tests {

    //interraction des nodes

    public static boolean vemail(JFXTextField field,Label label){
        if (field.getText().isEmpty()){
            System.out.println("Erreur empty "+field.getId());
            field.setUnFocusColor(Color.RED);
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!email(field.getText())){
            System.out.println("Erreur "+field.getId());
            field.setUnFocusColor(Color.RED);
            label.setText("L'email est incorrecte.");
            label.setVisible(true);
            return false;
        }else
        {
            System.out.println("Champ Valide "+field.getId());
            field.setUnFocusColor(Color.GREEN);
            label.setVisible(false);
            return true;
        }

    }

    public static boolean vtel(JFXTextField field,Label label){
        if (field.getText().isEmpty()){
            System.out.println("Erreur empty "+field.getId());
            field.setUnFocusColor(Color.RED);
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!telephone(field.getText())){
            System.out.println("Erreur "+field.getId());
            field.setUnFocusColor(Color.RED);
            label.setText("Numéro à 8 Chiffres.");
            label.setVisible(true);
            return false;
        }else
        {
            System.out.println("Champ Valide "+field.getId());
            field.setUnFocusColor(Color.GREEN);
            label.setVisible(false);
            return true;
        }

    }

    public static boolean vchaine(JFXTextField field,Label label,int max,boolean chiffre){
        if (field.getText().isEmpty()){
            System.out.println("Erreur empty "+field.getId());
            field.setUnFocusColor(Color.RED);
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!chaine(field.getText(), max, chiffre)){
            System.out.println("Erreur "+field.getId());
            field.setUnFocusColor(Color.RED);
            if (!chiffre){
            label.setText("Que des lettres, de longueur maximale "+max+".");
            }else label.setText("Longueur maximale "+max+"?");
            label.setVisible(true);
            return false;
        }else
        {
            System.out.println("Champ Valide "+field.getId());
            field.setUnFocusColor(Color.GREEN);
            label.setVisible(false);
            return true;
        }

    }

    public static boolean vcodep(JFXTextField field,Label label){
        if (field.getText().isEmpty()){
            System.out.println("Erreur empty "+field.getId());
            field.setUnFocusColor(Color.RED);
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!code_postal(field.getText())){
            System.out.println("Erreur "+field.getId());
            field.setUnFocusColor(Color.RED);
            label.setText("4 Chiffres.");
            label.setVisible(true);
            return false;
        }else
        {
            System.out.println("Champ Valide "+field.getId());
            field.setUnFocusColor(Color.GREEN);
            label.setVisible(false);
            return true;
        }

    }

    public static boolean vdate(JFXDatePicker field,Label label){
        LocalDate d = field.getValue();
        if (d==null){
            System.out.println("Erreur empty "+field.getId());
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!date_naissance(Date.from(d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))){
            System.out.println("Erreur "+field.getId());
            field.getStyleClass().add("fielderror");
            label.setText("Choisir une date antérieur.");
            label.setVisible(true);
            return false;
        }else
        {
            System.out.println("Champ Valide "+field.getId());
            field.getStyleClass().add("txtfield");
            label.setVisible(false);
            return true;
        }

    }

    public static boolean vcombo(JFXComboBox field, Label label){
        if (field.getSelectionModel().isEmpty() ){
            System.out.println("Erreur empty "+field.getId());
            field.setUnFocusColor(Color.RED);
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }else
        {
            System.out.println("Champ Valide "+field.getId());
            field.getStyleClass().add("txtfield");
            field.setUnFocusColor(Color.GREEN);
            label.setVisible(false);
            return true;
        }
    }

    //tests sur les champs


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




    /****
     *
     */
    public static boolean email_field(JFXTextField field,Label label){
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
            field.getStyleClass().remove("fielderror");
            label.setVisible(false);
            return true;
        }
    }
    public static boolean email(String email) {
        Pattern pattern_valide = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern_valide.matcher(email);
        return matcher.find();
    }
    public static boolean txt_field(JFXTextField field,Label label,int max,boolean chiffre,boolean empty){
        if (!empty && field.getText().isEmpty()){
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!txt(field.getText(), max, chiffre,empty)){
            field.getStyleClass().add("fielderror");
            if (!chiffre){
            label.setText("Que des lettres, de longueur maximale "+max+".");
            }else label.setText("Longueur maximale "+max+"?");
            label.setVisible(true);
            return false;
        }else
        {
            field.getStyleClass().add("txtfield");
            field.getStyleClass().remove("fielderror");
            label.setVisible(false);
            return true;
        }

    }
        public static boolean telephone_field(JFXTextField field,Label label){
        if (field.getText().isEmpty()){
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!telephone(field.getText())){
            field.getStyleClass().add("fielderror");
            label.setText("Numéro de 8 Chiffres.");
            label.setVisible(true);
            return false;
        }else
        {
            field.getStyleClass().add("txtfield");
            field.getStyleClass().remove("fielderror");
            label.setVisible(false);
            return true;
        }
    }
    public static boolean telephone(String s) {
        return s.matches("^[0-9]{8}$");
    }
    public static boolean txt(String s,int max,boolean accepter_chiffres,boolean accepter_vide){
        if (accepter_vide && s.isEmpty())
            return false;
        if(s.length()>max)
            return false;
        if (!accepter_chiffres&& s.matches(".*\\d+.*"))
            return false;
        return true;

    }

        public static boolean date_naissance_field(JFXDatePicker field,Label label){
        java.time.LocalDate d = field.getValue();
        if (d==null){
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!date_naissance(java.util.Date.from(d.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()))){
            field.getStyleClass().add("fielderror");
            label.setText("Choisir une date antérieur.");
            label.setVisible(true);
            return false;
        }else
        {
            field.getStyleClass().add("txtfield");
            field.getStyleClass().remove("fielderror");
            label.setVisible(false);
            return true;
        }

    }
    public static boolean date_naissance(java.util.Date d) {
        return !d.after(new java.util.Date());
    }

    public static boolean code_postal_field(JFXTextField field, Label label) {
        if (field.getText().isEmpty()) {
            field.getStyleClass().add("fielderror");
            label.setText("Code Postal obligatoire.");
            label.setVisible(true);
            return false;
        }
        if (!code_postal(field.getText())) {
            field.getStyleClass().add("fielderror");
            label.setText("4 Chiffres.");
            label.setVisible(true);
            return false;
        } else {
            field.getStyleClass().add("txtfield");
            field.getStyleClass().remove("fielderror");
            label.setVisible(false);
            return true;
        }
    }

    public static boolean ville_field(JFXComboBox field, Label label) {
        if (field.getSelectionModel().getSelectedIndex() == -1) {
            field.getStyleClass().add("fielderror");
            label.setText("Ville obligatoire.");
            label.setVisible(true);
            return false;
        } else {
            field.getStyleClass().add("txtfield");
            field.getStyleClass().remove("fielderror");
            label.setVisible(false);
            return true;
        }
    }
    public static boolean niveau_field(JFXComboBox field, Label label) {
        if (field.getSelectionModel().getSelectedIndex() == -1) {
            field.getStyleClass().add("fielderror");
            label.setText("Champ obligatoire.");
            label.setVisible(true);
            return false;
        } else if (field.getSelectionModel().getSelectedIndex() > field.getItems().size()) {
            return false;
        } else {
                field.getStyleClass().add("txtfield");
                field.getStyleClass().remove("fielderror");
                label.setVisible(false);
                return true;
            }
    }
    public static boolean code_postal(String s) {
        return s.matches("^[0-9]{4}$");
    }

        public static String toSHA1(String data) {
        String SHA1 = "" ;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(data.getBytes("UTF-8"));
            SHA1 = new  BigInteger(1, crypt.digest()).toString(16);
        } catch (Exception exception) {
            System.out.println("Methode :toSHA1() \n"
                             + "Exception : " + exception);
        }
        return SHA1;
    }

          public static boolean image(File fichier){
        if (fichier==null)
            return false;
        long taille_en_KB = fichier.length() / 1024;
        // taille fichier entre 0+kb et 500kb
        if (taille_en_KB < 0 || taille_en_KB > (500 * 1024 * 1024))
            return false;

        // verification si fichier est de type image
        try {
            ImageIO.read(fichier);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
