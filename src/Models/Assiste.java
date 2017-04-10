/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author DELL
 */
public class Assiste {
    private int id_assiste;
    private int ref_i;
    private int ref_c;
    private String ref_m;
    
    public Assiste() {
    }

    public Assiste(int id_assiste, int ref_c, String ref_m) {
        this.id_assiste = id_assiste;
        this.ref_c = ref_c;
        this.ref_m = ref_m;
    }

    public int getId_assiste() {
        return id_assiste;
    }

    public void setId_assiste(int id_assiste) {
        this.id_assiste = id_assiste;
    }

    public int getRef_i() {
        return ref_i;
    }

    public void setRef_i(int ref_i) {
        this.ref_i = ref_i;
    }

    public int getRef_c() {
        return ref_c;
    }

    public void setRef_c(int ref_c) {
        this.ref_c = ref_c;
    }

    public String getRef_m() {
        return ref_m;
    }

    public void setRef_m(String ref_m) {
        this.ref_m = ref_m;
    }

   


    
}
