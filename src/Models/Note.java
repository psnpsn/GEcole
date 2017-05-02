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
 *St
 * @author DELL
 */
public class Note {
    private SimpleIntegerProperty id_note;
    private SimpleFloatProperty note;
    private SimpleStringProperty type;
    private SimpleIntegerProperty ref_e;
    private SimpleIntegerProperty ref_ass;
    
    public Note(int id_note, float note, String type, int ref_e, int ref_ass) {
        this.id_note = new SimpleIntegerProperty(id_note);
        this.type = new SimpleStringProperty(type);
        this.note = new SimpleFloatProperty(note);
        this.ref_e = new SimpleIntegerProperty(ref_e);
        this.ref_ass = new SimpleIntegerProperty(ref_ass);
    }
    
    public Note() {
        this.id_note = new SimpleIntegerProperty();
        this.type= new SimpleStringProperty("");
        this.note = new SimpleFloatProperty();
        this.ref_e = new SimpleIntegerProperty();
        this.ref_ass = new SimpleIntegerProperty();
    }

    public int getId_note() {
        return id_note.get();
    }

    public void setId_note(int id_note) {
        this.id_note.set(id_note);
    }

    public float getNote() {
        return note.get();
    }

    public void setNote(float note) {
        this.note.set(note);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getRef_e() {
        return ref_e.get();
    }

    public void setRef_e(int ref_e) {
        this.ref_e.set(ref_e);
    }

    public int getRef_ass() {
        return ref_ass.get();
    }

    public void setRef_ass(int ref_ass) {
        this.ref_ass.set(ref_ass);
    }
    
    
}
