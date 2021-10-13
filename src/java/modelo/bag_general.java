package modelo;

public class bag_general {
    private int codus;
    private String codpack;

    public bag_general(int codus, String codpack) {
        this.codus = codus;
        this.codpack = codpack;
    }

    public String getCodpack() {
        return codpack;
    }

    public void setCodpack(String codpack) {
        this.codpack = codpack;
    }

    public int getCodus() {
        return codus;
    }

    public void setCodus(int codus) {
        this.codus = codus;
    }
    
}
