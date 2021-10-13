package controlador;

import controlador.SQL.SQLProcessData;
import java.util.ArrayList;
import java.util.List;

public class text {

    public text() {
    }
    public static void main(String[] args) {
        SQLProcessData sql = new SQLProcessData();
        text t=new text();
        
        
        String statement = "SELECT * FROM PAQUETE WHERE (";
            for (int i = 0; i < 5; i++) {
                if (i > 0) statement += " OR ";
                statement += "CODE = ?";
            }
            statement += ");";
            System.out.println(statement);
    } 
}
