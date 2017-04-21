package Models;
public class Emploi {
    private int id;
    private int id_salle;
    private int id_assiste;
    private int id_heure;
    private int id_day;

    public Emploi() {
        id = -1;
        id_salle = -1;
        id_assiste = -1;
        id_heure = -1;
        id_day = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public int getId_assiste() {
        return id_assiste;
    }

    public void setId_assiste(int id_assiste) {
        this.id_assiste = id_assiste;
    }

    public int getId_heure() {
        return id_heure;
    }

    public void setId_heure(int id_heure) {
        this.id_heure = id_heure;
    }

    public int getId_day() {
        return id_day;
    }

    public void setId_day(int id_day) {
        this.id_day = id_day;
    }

}
