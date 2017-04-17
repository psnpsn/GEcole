package Models;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Salle {

    private SimpleIntegerProperty identifiant;
    private SimpleStringProperty type_salle;
    private SimpleIntegerProperty capacite;
    private SimpleObjectProperty date_creation;


    public Salle(int identifiant, String type_salle, int capacite , Date date_creation) {
        this.date_creation = new SimpleObjectProperty(date_creation);
        this.identifiant = new SimpleIntegerProperty(identifiant);
        this.type_salle = new SimpleStringProperty(type_salle);
        this.capacite = new SimpleIntegerProperty(capacite);
    }


    public Salle() {
        date_creation = new SimpleObjectProperty(new Date());
        identifiant = new SimpleIntegerProperty(0);
        type_salle  = new SimpleStringProperty("");
        capacite = new SimpleIntegerProperty(0);
    }

    public int getIdentifiant() {
        return identifiant.getValue();
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant.setValue(identifiant);
    }

    public String getType_salle() {
        return type_salle.getValue();
    }

    public void setType_salle(String type_salle) {
        this.type_salle.setValue(type_salle);
    }

    public int getCapacite() {
        return capacite.getValue();
    }

    public void setCapacite(int capacite) {
        this.capacite.setValue(capacite);
    }

    public Date getDate_creation() {
        return (Date) date_creation.getValue();
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation.setValue(date_creation);
    }

}
