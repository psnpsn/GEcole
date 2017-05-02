/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author DELL
 */
public class Matiere {
    
    private SimpleIntegerProperty id_m;
    private SimpleStringProperty nom;
    private SimpleFloatProperty coef;
    private SimpleStringProperty desc;

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
