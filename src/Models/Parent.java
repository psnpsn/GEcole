package Models;

public class Parent {
    private int ID_PARENT;
    private String NOMP;
    private String PROFP;
    private String NOMM;
    private String PROFM;
    private String TELP;
    private String EMAILP;

    public Parent() {
    }

    public Parent(int ID_PARENT, String NOMP, String PROFP, String NOMM, String PROFM, String TELP, String EMAILP) {
        this.ID_PARENT = ID_PARENT;
        this.NOMP = NOMP;
        this.PROFP = PROFP;
        this.NOMM = NOMM;
        this.PROFM = PROFM;
        this.TELP = TELP;
        this.EMAILP = EMAILP;
    }

    public int getID_PARENT() {
        return ID_PARENT;
    }

    public void setID_PARENT(int ID_PARENT) {
        this.ID_PARENT = ID_PARENT;
    }

    public String getNOMP() {
        return NOMP;
    }

    public void setNOMP(String NOMP) {
        this.NOMP = NOMP;
    }

    public String getPROFP() {
        return PROFP;
    }

    public void setPROFP(String PROFP) {
        this.PROFP = PROFP;
    }

    public String getNOMM() {
        return NOMM;
    }

    public void setNOMM(String NOMM) {
        this.NOMM = NOMM;
    }

    public String getPROFM() {
        return PROFM;
    }

    public void setPROFM(String PROFM) {
        this.PROFM = PROFM;
    }

    public String getTELP() {
        return TELP;
    }

    public void setTELP(String TELP) {
        this.TELP = TELP;
    }

    public String getEMAILP() {
        return EMAILP;
    }

    public void setEMAILP(String EMAILP) {
        this.EMAILP = EMAILP;
    }
}
