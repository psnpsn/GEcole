package GUI;

public class Tests {

    public static boolean email(String s) {
        // non vide
        if (s.isEmpty()) {
            return false;
        }
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
        // pa vide
        if (s.isEmpty()) {
            return false;
        }
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
        // pa vide
        if (s.isEmpty()) {
            return false;
        }
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
        // pa vide
        if (s.isEmpty()) {
            return false;
        }
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
