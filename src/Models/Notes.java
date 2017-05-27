package Models;

import java.util.ArrayList;

public class Notes {    
    private Eleve eleve;
    private ArrayList<Note> notes;
    private ArrayList<Matiere> matieres;

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
    public int getIndexNote(Note note){
        return this.notes.indexOf(note);
    }
    
    public int getIndexMatiere(Matiere matiere){
        return this.matieres.indexOf(matiere);
    }
    public void rmNote(Note note){
        this.notes.remove(note);
    }

    public void rmMatiere(Matiere matiere){
        this.matieres.remove(matiere);
    }
    public void addNote(Note note) {
        this.notes.add(note);
    }

    public ArrayList<Matiere> getMatieres() {
        return matieres;
    }

    public void addMatiere(Matiere matiere) {
        this.matieres.add(matiere);
    }



   public float moyenne(){
       return -1f;
   }
    
    
}
