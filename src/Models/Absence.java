package Models;

import java.time.LocalDate;
import java.util.Date;

public class Absence {

    private int id_absence;
    private Date date_abs;
    private int ref_sc;
    private int ref_e;

    public Absence() {
        date_abs = new Date();
        id_absence=-1;
        ref_sc = -1;
        ref_e = -1;
    }

    public int getId_absence() {
        return id_absence;
    }

    public void setId_absence(int id_absence) {
        this.id_absence = id_absence;
    }

    public Date getDate_abs() {
        return date_abs;
    }

    public void setDate_abs(Date date_abs) {
        this.date_abs = date_abs;
    }

    public int getRef_sc() {
        return ref_sc;
    }

    public void setRef_sc(int ref_sc) {
        this.ref_sc = ref_sc;
    }

    public int getRef_e() {
        return ref_e;
    }

    public void setRef_e(int ref_e) {
        this.ref_e = ref_e;
    }

}
