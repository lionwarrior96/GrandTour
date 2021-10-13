package modelo;

public class bag_specific {
    private int codbs;
    private int codus;
    private String bname;

    public bag_specific(int codbs, int codus, String bname) {
        this.codbs = codbs;
        this.codus = codus;
        this.bname = bname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getCodbs() {
        return codbs;
    }

    public void setCodbs(int codbs) {
        this.codbs = codbs;
    }

    public int getCodus() {
        return codus;
    }

    public void setCodus(int codus) {
        this.codus = codus;
    }
    
}
