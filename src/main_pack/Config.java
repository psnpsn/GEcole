package main_pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Config {

    private String fichier_de_preference = "data/preferences.cfg";
    private static HashMap prefs;

    public Config() {
        // creation fichier de configuration si non existant
        Path out;

        try {
            out = Files.createFile(Paths.get(fichier_de_preference));
        } catch (Exception faee) {
            
         }
        this.prefs = new HashMap<>();
        //preference oracle
        prefs.put("dbusername", "user");
        prefs.put("dbpassword", "pass");
        prefs.put("dbdriver", "driver");
        prefs.put("dburl", "url");
        prefs.put("nom_ecole", "non ecole non configuree");
        prefs.put("adresse_ecole", "adresse ecole non configuree");
        prefs.put("siteweb_ecole", "siteweb ecole non configuree");
        prefs.put("tel_ecole", "numero telephone de l'ecole non configuree");
        prefs.put("email_ecole", "email de l'administration de l'ecole non configuree");
    }

    public boolean save() {
        boolean valide = false;
        try {
            FileWriter file_writer = new FileWriter(fichier_de_preference, false);
            PrintWriter print_writer = new PrintWriter(file_writer);
            Iterator it = prefs.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                print_writer.println(pair.getKey() + " = " + pair.getValue());
            }
            print_writer.close();
            file_writer.close();
            valide = true;
        } catch (IOException exception) {
            System.out.println("Erreur lors de la sauvegarde des preferences\nException : " + exception);
        } finally {
            return valide;
        }
    }

    public boolean load() {
        boolean valide = false;

        try {
            FileReader file_reader = new FileReader(fichier_de_preference);
            BufferedReader buffer = new BufferedReader(file_reader);
            String ligne;
            while ((ligne = buffer.readLine()) != null) {
                if (ligne.charAt(0) != '#') { // ligne commencant par # sont des commentaire
                    if (ligne.contains("=")) {
                        String cle = ligne.substring(0, ligne.indexOf("=")).replaceAll("\\s", "");
                        String valeur = ligne.substring(ligne.indexOf("=") + 1);
                        if (prefs.containsKey(cle.toLowerCase())) {
                            prefs.put(cle.toLowerCase(), valeur);
                        }
                    } // else : ligne ne contient pa = , donc pa une ligne de parametre
                } // else : ligne est un commentaire ,car elle ,la ligne,commence par #
            }
            buffer.close();
            file_reader.close();
            valide = true;
        } catch (IOException exception) {
            System.out.println("Erreur lors du chargement du fichier des preferences\nException : "+exception);
        } finally {
            return valide;
        }
    }

    public String get_data(String key) {
        String resultat ="";
        if (prefs.containsKey(key.toLowerCase())) {
            resultat = prefs.get(key.toLowerCase()).toString();
        }
        return resultat;
    }
        public void set_data(String key,String value) {
           if (prefs.containsKey(key.toLowerCase())) {
                prefs.put(key.toLowerCase(), value);
            }
    }

    private static String color_error = (char)27 + "[43;31m " ;
    private static String color_info = (char)27 + "[47;30m " ;
    private static String color_norm =  (char)27 + "[0m" ;

    public static String error(String msg){
        return Config.color_error + msg + color_norm;
    }
    public static String info(String msg){
        return Config.color_info + msg +color_norm;
    }
}
