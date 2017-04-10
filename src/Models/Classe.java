/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author DELL
 */
public class Classe {
    // Attributs

    private SimpleIntegerProperty id_c;
    private SimpleStringProperty nom;
    private SimpleIntegerProperty capacite;
    private SimpleIntegerProperty ref_niv;
    
    private BooleanProperty cocher = new SimpleBooleanProperty(false);

    //Constructeurs
    public Classe(){
        this.id_c = new SimpleIntegerProperty();
        this.nom = new SimpleStringProperty("");
        this.capacite = new SimpleIntegerProperty();
        this.ref_niv = new SimpleIntegerProperty();
    }
    public Classe(int id_c, String nom, int capacite, int ref_niv) {
        this.id_c = new SimpleIntegerProperty(id_c);
        this.nom = new SimpleStringProperty(nom);
        this.capacite = new SimpleIntegerProperty(capacite);
        this.ref_niv = new SimpleIntegerProperty(ref_niv);
    }

    //Setters & Getters
    public int getId_c() {
        return id_c.get();
    }

    public void setId_c(int id_c) {
        this.id_c.set(id_c);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public int getCapacite() {
        return capacite.get();
    }

    public void setCapacite(int capacite) {
        this.capacite.set(capacite);
    }

    public int getRef_niv() {
        return ref_niv.get();
    }

    public void setRef_niv(int ref_niv) {
        this.ref_niv.set(ref_niv);
    }
    //Property return
    public SimpleIntegerProperty id_cProperty(){
        return id_c;
    }
    
    public SimpleStringProperty nomProperty(){
        return nom;
    }
    
    public SimpleIntegerProperty capaciteProperty(){
        return capacite;
    }
    
    public SimpleIntegerProperty ref_nivProperty(){
        return ref_niv;
    }
    
    public SimpleIntegerProperty nivProperty(){
        return new SimpleIntegerProperty(ref_niv.get()/10000);
    }
    
    public SimpleIntegerProperty anneeProperty(){
        return new SimpleIntegerProperty(ref_niv.get()%10000);
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
    
}
