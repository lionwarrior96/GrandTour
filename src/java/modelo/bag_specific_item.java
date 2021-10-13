package modelo;

public class bag_specific_item {
    private int codbs;
    private String codpack;

    public bag_specific_item(int codbs, String codpack) {
        this.codbs = codbs;
        this.codpack = codpack;
    }

    public String getCodpack() {
        return codpack;
    }

    public void setCodpack(String codpack) {
        this.codpack = codpack;
    }

    public int getCodbs() {
        return codbs;
    }

    public void setCodbs(int codbs) {
        this.codbs = codbs;
    }
    
}
