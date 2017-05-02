package Models;

import java.util.Date;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

//type des donnes doit correspondre a la table eleve
public class Eleve {

     // Attributs

    private SimpleIntegerProperty id_e;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty adresse;
    private SimpleStringProperty ville;
    private SimpleIntegerProperty codeP;
    private SimpleObjectProperty<Date> dateNaiss;
    private SimpleStringProperty lieuNaiss;
    private SimpleStringProperty sex;
    private SimpleStringProperty email;
    private SimpleIntegerProperty ref_niv;
    private SimpleIntegerProperty ref_c;
    private SimpleIntegerProperty ref_p;
    private SimpleObjectProperty<Date> dateIns;

    private BooleanProperty cocher = new SimpleBooleanProperty(false);


// constructeurs;
        public Eleve() {
        this.id_e = new SimpleIntegerProperty();
        this.nom = new SimpleStringProperty();
        this.prenom = new SimpleStringProperty();
        this.adresse = new SimpleStringProperty();
        this.ville = new SimpleStringProperty();
        this.codeP = new SimpleIntegerProperty(-1);
        this.dateNaiss = new SimpleObjectProperty<Date>();
        this.lieuNaiss = new SimpleStringProperty();
        this.sex = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.ref_niv = new SimpleIntegerProperty();
        this.ref_c = new SimpleIntegerProperty();
        this.ref_p = new SimpleIntegerProperty();
        this.dateIns = new SimpleObjectProperty<Date>();


    }


    public Eleve(int id_e, String nom, String prenom, String adresse, String ville, int codeP, Date dateNaiss, String lieuNaiss, String sex, String email, int ref_niv, int ref_c, int ref_p, Date dateIns) {
        this.id_e = new SimpleIntegerProperty(id_e);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.adresse = new SimpleStringProperty(adresse);
        this.ville = new SimpleStringProperty(ville);
        this.codeP = new SimpleIntegerProperty(codeP);
        this.dateNaiss = new SimpleObjectProperty<Date>(dateNaiss);
        this.lieuNaiss = new SimpleStringProperty(lieuNaiss);
        this.sex = new SimpleStringProperty(sex);
        this.email = new SimpleStringProperty(email);
        this.ref_niv = new SimpleIntegerProperty(ref_niv);
        this.ref_c = new SimpleIntegerProperty(ref_c);
        this.ref_p = new SimpleIntegerProperty(ref_p);
        this.dateIns = new SimpleObjectProperty<Date>(dateIns);

    }

    // getters

    public int getId_e() {
        return id_e.get();
    }



    public String getNom() {
        return nom.get();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public String getAdresse() {
        return adresse.get();
    }

    public String getVille() {
        return ville.get();
    }

    public int getCodeP() {
        return codeP.get();
    }

    public Date getDateNaiss() {
        return dateNaiss.get();
    }

    public String getLieuNaiss() {
        return lieuNaiss.get();
    }

    public String getSex() {
        return sex.get();
    }


    public String getEmail() {
        return email.get();
    }

    public int getRef_niv() {
        return ref_niv.get();
    }
    

    public int getRef_c() {
        return ref_c.get();
    }

    public int getRef_p() {
        return ref_p.get();
    }

    public Date getDateIns() {
        return dateIns.get();
    }



    // Setters

    public void setId_e(int id_e) {
        this.id_e.set(id_e);
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }

    public void setCodeP(int codeP) {
        this.codeP.set(codeP);
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss.set(dateNaiss);
    }

    public void setLieuNaiss(String lieuNaiss) {
        this.lieuNaiss.set(lieuNaiss);
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }


    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setRef_niv(int ref_n) {
        this.ref_niv.set(ref_n);
    }

    public void setRef_c(int ref_c) {
        this.ref_c.set(ref_c);
    }

    public void setRef_p(int ref_p) {
        this.ref_p.set(ref_p);
    }

    public void setDateIns(Date dateIns) {
        this.dateIns.set(dateIns);
    }

    //Property
    public SimpleIntegerProperty id_eProperty(){
        return id_e;
    }
    public SimpleStringProperty nomProperty(){
        return nom;
    }
    public SimpleStringProperty fullnomProperty(){
        return new SimpleStringProperty(nom.get()+" "+prenom.get());
    }
    public SimpleStringProperty prenomProperty(){
        return prenom;
    }
    public SimpleStringProperty adresseProperty(){
        SimpleStringProperty fulladresse= new SimpleStringProperty();
        fulladresse.set(adresse.get()+", "+ville.get());
        return fulladresse;
    }
    public SimpleStringProperty villeProperty(){
        return ville;
    }
    public SimpleIntegerProperty codePProperty(){
        return codeP;
    }
    public SimpleObjectProperty<Date> dateNaissProperty(){
        return dateNaiss;
    }
    public SimpleStringProperty lieuNaissProperty(){
        return lieuNaiss;
    }
    public SimpleStringProperty sexProperty(){
        return sex;
    }
    public SimpleStringProperty emailProperty(){
        return email;
    }
    public SimpleIntegerProperty ref_nivProperty(){
        return ref_niv;
    }
    public SimpleIntegerProperty ref_nProperty(){
        return new SimpleIntegerProperty(ref_niv.get()/10000);
    }
    public SimpleIntegerProperty ref_cProperty(){
        return ref_c;
    }
    public SimpleIntegerProperty ref_pProperty(){
        return ref_p;
    }
    public SimpleObjectProperty<Date> dateInsProperty(){
        return dateIns;
    }

    //cocher property
     public BooleanProperty cocherProperty() {
            return cocher;
        }

        public void setCocher(boolean cocher) {
            this.cocher.set(cocher);
        }

        public boolean isCocher() {
            return cocher.get();
        }




    // methodes utiles...
    public boolean equals(Eleve other) {
        if (other == null) {
            return false;
        }
        if (this.codeP != other.codeP) {
            return false;
        }
        if (this.sex != other.sex) {
            return false;
        }

        if (this.ref_niv != other.ref_niv) {
            return false;
        }
        if (this.ref_c != other.ref_c) {
            return false;
        }
        if (this.ref_p != other.ref_p) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        if (!Objects.equals(this.lieuNaiss, other.lieuNaiss)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.dateNaiss, other.dateNaiss)) {
            return false;
        }
        if (!Objects.equals(this.dateIns, other.dateIns)) {
            return false;
        }
        return true;
}

    @Override
    public String toString() {
        return "Eleve{" + "id_e=" + id_e.toString() + ", nom=" + nom.toString() + ", prenom=" + prenom + ", adresse=" + adresse + ", ville=" + ville + ", codeP=" + codeP + ", dateNaiss=" + dateNaiss + ", lieuNaiss=" + lieuNaiss + ", sex=" + sex + ", email=" + email + ", ref_niv=" + ref_niv + ", ref_c=" + ref_c + ", ref_p=" + ref_p + ", dateIns=" + dateIns + '}';
    }

}
