
package Models;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Note {
    private SimpleIntegerProperty id_note;
    private SimpleFloatProperty   note;
    private SimpleStringProperty  type;
    private SimpleIntegerProperty ref_e;
    private SimpleIntegerProperty ref_inst;
    private SimpleIntegerProperty ref_mat;
    public JFXTextField           txt_note;
       
    public Note() {
       
        this.id_note = new SimpleIntegerProperty();
        this.type= new SimpleStringProperty("");
        this.note = new SimpleFloatProperty(-1);
        this.ref_e = new SimpleIntegerProperty();
        this.ref_inst = new SimpleIntegerProperty();
        this.ref_mat = new SimpleIntegerProperty();
        this.txt_note = new JFXTextField(""+this.note.getValue());
    }

    public int getRef_mat() {
        return ref_mat.getValue();
    }

    public void setRef_mat(int ref_mat) {
        this.ref_mat.setValue(ref_mat);
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

    public int getRef_inst() {
        return ref_inst.get();
    }

    public void setRef_inst(int inst) {
        this.ref_inst.set(inst);
    }
    
    
}
