/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
    private SimpleStringProperty ref_niv;

    //Constructeurs
    public Classe(){
        this.id_c = new SimpleIntegerProperty(-1);
        this.nom = new SimpleStringProperty("");
        this.capacite = new SimpleIntegerProperty(-1);
        this.ref_niv = new SimpleStringProperty("");
    }
    public Classe(int id_c, String nom, int capacite, String ref_niv) {
        this.id_c = new SimpleIntegerProperty(id_c);
        this.nom = new SimpleStringProperty(nom);
        this.capacite = new SimpleIntegerProperty(capacite);
        this.ref_niv = new SimpleStringProperty(ref_niv);
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

    public String getRef_niv() {
        return ref_niv.get();
    }

    public void setRef_niv(String ref_niv) {
        this.ref_niv.set(ref_niv);
    }
    
    
    
}
