
package Models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Matiere {
    
    private SimpleIntegerProperty id_m;
    private SimpleStringProperty nom;
    private SimpleFloatProperty coef;
    private SimpleStringProperty desc;
    private int ref_module = -1;

    public Matiere(int id_m, String nom, float coef, String desc) {
        this.id_m = new SimpleIntegerProperty(id_m);
        this.nom = new SimpleStringProperty(nom);
        this.coef = new SimpleFloatProperty(coef);
        this.desc = new SimpleStringProperty(desc);
    }
    
    public Matiere() {
        this.id_m = new SimpleIntegerProperty();
        this.nom = new SimpleStringProperty("");
        this.coef = new SimpleFloatProperty();
        this.desc = new SimpleStringProperty("");
    }

    public void setRef_module(int ref){
        ref_module = ref;
    }
    public int getRef_module(){
        return ref_module;
    }
    public int getId_m() {
        return id_m.get();
    }

    public void setId_m(int id_m) {
        this.id_m.set(id_m);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public float getCoef() {
        return coef.get();
    }

    public void setCoef(float coef) {
        this.coef.set(coef);
    }

    public String getDesc() {
        return desc.get();
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }
    
    public SimpleIntegerProperty idProperty(){
        return new SimpleIntegerProperty(id_m.get());
    }
    
    public SimpleStringProperty nomProperty(){
        return new SimpleStringProperty(nom.get());
    }
    
    public SimpleFloatProperty coefProperty(){
        return new SimpleFloatProperty(coef.get());
    }
    
    public SimpleStringProperty descProperty(){
        return new SimpleStringProperty(desc.get());
    }
}
