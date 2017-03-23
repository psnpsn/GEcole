
package Models;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
//type de données doivent correspondre a la table INST

public class Instituteur {
    //attributs
    private SimpleIntegerProperty id_i;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleObjectProperty<Date> dateNaiss;
    private SimpleIntegerProperty NCIN;
    private SimpleObjectProperty<Date> dateEmb;
    private SimpleStringProperty sex;
    private SimpleIntegerProperty immatricul;
    private SimpleStringProperty grade;
    private SimpleStringProperty adresse;
    private SimpleStringProperty ville;
    private SimpleStringProperty email;
    private SimpleIntegerProperty codeP;
    private SimpleIntegerProperty tel1;
    private SimpleIntegerProperty tel2;
    //constructeurs 
 public Instituteur(){
     this.id_i =new SimpleIntegerProperty();
     this.nom=new SimpleStringProperty();
     this.prenom=new SimpleStringProperty();
     this.dateNaiss=new SimpleObjectProperty<Date>();
     this.NCIN=new SimpleIntegerProperty();
     this.dateEmb=new SimpleObjectProperty<Date>();
     this.sex=new SimpleStringProperty();
     this.grade=new SimpleStringProperty();
     this.adresse=new SimpleStringProperty();
     this.ville=new SimpleStringProperty();
     this.email=new SimpleStringProperty();
     this.codeP= new SimpleIntegerProperty();
     this.tel1=new SimpleIntegerProperty();
     this.tel2=new SimpleIntegerProperty();
     this.immatricul=new SimpleIntegerProperty();
 }
    
    public Instituteur(int id_i, String nom, String prenom, Date dateNaiss, int NCIN, Date dateEmb, String sex, int immatricul, String grade, String adresse, String ville, String email, int codeP, int tel1, int tel2)
 {
     this.id_i =new SimpleIntegerProperty(id_i);
     this.nom=new SimpleStringProperty(nom);
     this.prenom=new SimpleStringProperty(prenom);
     this.dateNaiss=new SimpleObjectProperty<Date>(dateNaiss);
     this.NCIN=new SimpleIntegerProperty(NCIN);
     this.dateEmb=new SimpleObjectProperty<Date>(dateEmb);
     this.sex=new SimpleStringProperty(sex);
     this.grade=new SimpleStringProperty(grade);
     this.adresse=new SimpleStringProperty(adresse);
     this.ville=new SimpleStringProperty(ville);
     this.email=new SimpleStringProperty(email);
     this.codeP=new SimpleIntegerProperty(codeP);
     this.tel1=new SimpleIntegerProperty(tel1);
     this.tel2=new SimpleIntegerProperty(tel2);
     this.immatricul=new SimpleIntegerProperty(immatricul);          
 }
//getters
    public int getId_i() {
        return id_i.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public Date getDateNaiss() {
        return dateNaiss.get();
    }

    public int getNCIN() {
        return NCIN.get();
    }

    public Date getDateEmb() {
        return dateEmb.get();
    }

    public String getSex() {
        return sex.get();
    }

    public int getImmatricul() {
        return immatricul.get();
    }

    public String getGrade() {
        return grade.get();
    }

    public String getAdresse() {
        return adresse.get();
    }

    public String getVille() {
        return ville.get();
    }

    public String getEmail() {
        return email.get();
    }

    public int getCodeP() {
        return codeP.get();
    }

    public int getTel1() {
        return tel1.get();
    }

    public int getTel2() {
        return tel2.get();
    }
//setters

    public void setId_i(int id_i) {
        this.id_i.set(id_i);
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss.set(dateNaiss);
    }

    public void setNCIN(int NCIN) {
        this.NCIN.set(NCIN);
    }

    public void setDateEmb(Date dateEmb) {
        this.dateEmb.set(dateEmb);
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public void setImmatricul(int immatricul) {
        this.immatricul.set(immatricul);
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setCodeP(int codeP) {
        this.codeP.set(codeP);
    }

    public void setTel1(int tel1) {
        this.tel1.set(tel1);
    }

    public void setTel2(int tel2) {
        this.tel2.set(tel2);
    }
    
    //Property
    public SimpleIntegerProperty id_iProperty(){
        return id_i;
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
        return adresse;
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
    public SimpleStringProperty gradeProperty(){
        return grade;
    }
    public SimpleStringProperty sexProperty(){
        return sex;
    }
    public SimpleStringProperty emailProperty(){
        return email;
    }
    public SimpleIntegerProperty tel1Property(){
        return tel1;
    }
    public SimpleIntegerProperty immProperty(){
        return immatricul;
    }
    public SimpleIntegerProperty tel2Property(){
        return tel2;
    }

    public SimpleObjectProperty<Date> dateEmbProperty(){
        return dateEmb;
    }
    //methodes utils ...
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Instituteur other = (Instituteur) obj;
        if (!Objects.equals(this.id_i, other.id_i)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.dateNaiss, other.dateNaiss)) {
            return false;
        }
        if (!Objects.equals(this.NCIN, other.NCIN)) {
            return false;
        }
        if (!Objects.equals(this.dateEmb, other.dateEmb)) {
            return false;
        }
        if (!Objects.equals(this.sex, other.sex)) {
            return false;
        }
        if (!Objects.equals(this.immatricul, other.immatricul)) {
            return false;
        }
        if (!Objects.equals(this.grade, other.grade)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.codeP, other.codeP)) {
            return false;
        }
        if (!Objects.equals(this.tel1, other.tel1)) {
            return false;
        }
        if (!Objects.equals(this.tel2, other.tel2)) {
            return false;
        }
        return true;
    }


}
