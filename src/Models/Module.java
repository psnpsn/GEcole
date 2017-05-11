package Models;

public class Module {
    
    private int id;
    private int ref_niv;
    private String nom;


    
    public Module() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRef_niv() {
        return ref_niv;
    }

    public void setRef_niv(int ref_niv) {
        this.ref_niv = ref_niv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
