package controlador.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import modelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.Interface_SQLProcessData;

public class SQLProcessData implements Interface_SQLProcessData{
    
    //Empty constructor=========================================================================================================
    public SQLProcessData() {}
    //==========================================================================================================================
    //SQL Connection============================================================================================================
    @Override
    public Connection getConnection(){
        Connection connection = null;
        while (true) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionURL=""
                        + "jdbc:sqlserver://GUSTAVASHO:1433;"
                        + "databaseName=PRODUCTS;"
                        + "user=usuarioSQL;"
                        + "password=321";
                connection=DriverManager.getConnection(connectionURL);
                break;
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SQLProcessData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return connection;
    }
    //==========================================================================================================================
    //SQL USUARIO Queries=======================================================================================================
    @Override
    public void addUsuario(usuario u){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO USUARIO (EMAIL, NOMBRE, PASSW) VALUES(?,?,?);");
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getPassword());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public usuario selectSomethingWhereFromUsuario(String thing, String condition, String parametro){
        usuario u = null;
        try {
            String statement ="SELECT "+thing.toUpperCase()+" FROM USUARIO WHERE "+condition.toUpperCase()+" = ?;";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setString(1, parametro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codigo=rs.getInt(1);
                String email=rs.getString(2);
                String nombre=rs.getString(3);
                String password=rs.getString(4);
                u = new usuario(codigo, email, nombre, password);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return u;
    }
    //==========================================================================================================================
    //SQL COMENTARIO Queries====================================================================================================
    @Override
    public void addComentario(comentario c){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO COMENTARIO (CODPACK, NOMUS, COMENT, DATECOM, CODANSWER, VOTO) VALUES(?,?,?,?,?,?);");
            ps.setString(1, c.getCodpack());
            ps.setString(2, c.getNomus());
            ps.setString(3, c.getComent());
            ps.setString(4, c.getFecha());
            ps.setInt(5, c.getCodanswer());
            ps.setInt(6, c.getVoto());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public void updateVoteFromComentario(int codcoment, int valor){
        try {
            PreparedStatement ps = getConnection().prepareStatement("UPDATE COMENTARIO SET VOTO = ? WHERE CODCOM = ?;");
            ps.setInt(1, valor);
            ps.setInt(2, codcoment);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public List<comentario> selectWhereFromMSComentario(String seleccionar, String condition, String c, int parametro, String codepack){
        List<comentario> lista = new ArrayList<>();
        try {
            String statement = "SELECT "+seleccionar.toUpperCase()+" FROM COMENTARIO WHERE ("+condition.toUpperCase()+" "+c+" ? AND CODPACK = ?);";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setInt(1, parametro);
            ps.setString(2, codepack);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codcom=rs.getInt(1);
                String codpack=rs.getString(2);
                String nomus=rs.getString(3);
                String coment=rs.getString(4);
                String datecom=rs.getString(5);
                int codanswer=rs.getInt(6);
                int voto=rs.getInt(7);
                lista.add(new comentario(codcom,codpack,nomus,coment,datecom,codanswer,voto));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    //==========================================================================================================================
    //SQL VOTO Queries==========================================================================================================
    @Override
    public void addVoto(voto v){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO VOTOS VALUES(?,?,?);");
            ps.setInt(1, v.getCodcom());
            ps.setInt(2, v.getCodus());
            ps.setInt(3, v.getValor());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public List<voto> selectAllFromVoto(){
        List<voto> lista = new ArrayList<>();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM VOTOS;");
            while (rs.next()){
                int codcom=rs.getInt(1);
                int codus=rs.getInt(2);
                int valor=rs.getInt(3);
                lista.add(new voto(codcom,codus,valor));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public List<voto> selectWhereFromVoto(String seleccionar, String condition, int parametro){
        List<voto> lista = new ArrayList<>();
        try {
            String statement = "SELECT "+seleccionar.toUpperCase()+" FROM VOTOS WHERE "+condition.toUpperCase()+" = ?;";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setInt(1, parametro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codcom=rs.getInt(1);
                int codus=rs.getInt(2);
                int valor=rs.getInt(3);
                lista.add(new voto(codcom,codus,valor));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    //==========================================================================================================================
    //SQL PAQUETE Queries=======================================================================================================
    @Override
    public void addPaquete(paquete p){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO PAQUETE VALUES(?,?,?,?,?,?);");
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getImg());
            ps.setString(3, p.getName());
            ps.setString(4, p.getDescription());
            ps.setDouble(5, p.getCost());
            ps.setString(6, p.getLink());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public List<paquete> selectSomethingFromPaquete(String thing){
        List<paquete> lista = new ArrayList<>();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT "+thing.toUpperCase()+" FROM PAQUETE;");
            while (rs.next()){
                String codigo=rs.getString(1);
                String img=rs.getString(2);
                String name=rs.getString(3);
                String description=rs.getString(4);
                double cost=rs.getDouble(5);
                String link=rs.getString(6);
                lista.add(new paquete(codigo,img,name,description,cost,link));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
           System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public List<paquete> selectSubstringFromPaquete(String seleccionar, String condition, String parametro){
        List<paquete> lista = new ArrayList<>();
        try {
            String statement = "SELECT "+seleccionar.toUpperCase()+" FROM PAQUETE WHERE SUBSTRING("+condition.toUpperCase()+", 2, 5) = ?;";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setString(1, parametro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo=rs.getString(1);
                String img=rs.getString(2);
                String name=rs.getString(3);
                String description=rs.getString(4);
                double cost=rs.getDouble(5);
                String link=rs.getString(6);
                lista.add(new paquete(codigo,img,name,description,cost,link));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public List<paquete> selectTwoSubstringsFromPaquete(String seleccionar, String condition, int a, int b, String parametro, int x, int y){
        List<paquete> lista = new ArrayList<>();
        seleccionar = seleccionar.toUpperCase();
        condition = condition.toUpperCase();
        try {
            String statement = "SELECT "+seleccionar+" FROM PAQUETE WHERE SUBSTRING("+condition+", "+a+", "+b+") = SUBSTRING(?, "+x+", "+y+");";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setString(1, parametro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo=rs.getString(1);
                String img=rs.getString(2);
                String name=rs.getString(3);
                String description=rs.getString(4);
                double cost=rs.getDouble(5);
                String link=rs.getString(6);
                lista.add(new paquete(codigo,img,name,description,cost,link));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public List<paquete> selectMultiFromPaquete(List<String> codigos){
        List<paquete> lista = new ArrayList<>();
        try {
            String statement = "SELECT * FROM PAQUETE WHERE (";
            for (int i = 0; i < codigos.size(); i++) {
                if (i > 0) statement += " OR ";
                statement += "CODE = ?";
            }
            statement += ");";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            for (int i = 0; i < codigos.size(); i++) {
                ps.setString(i + 1, codigos.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo=rs.getString(1);
                String img=rs.getString(2);
                String name=rs.getString(3);
                String description=rs.getString(4);
                double cost=rs.getDouble(5);
                String link=rs.getString(6);
                lista.add(new paquete(codigo,img,name,description,cost,link));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    //==========================================================================================================================
    //SQL MOCHILA [General] Queries=============================================================================================
    @Override
    public void addGbag(bag_general bg){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO BAG_GENERAL VALUES(?,?);");
            ps.setInt(1, bg.getCodus());
            ps.setString(2, bg.getCodpack());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public void deleteFromGbag(bag_general bg){
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM BAG_GENERAL WHERE (CODUS = ? AND CODPACK = ?);");
            ps.setInt(1, bg.getCodus());
            ps.setString(2, bg.getCodpack());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public List<bag_general> selectSomethingWhereMcGbag(String thing, int codus){
        List<bag_general> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT "+thing.toUpperCase()+" FROM BAG_GENERAL WHERE (CODUS = ?);");
            ps.setInt(1, codus);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int coduser = rs.getInt(1);
                String codpack = rs.getString(2);
                lista.add(new bag_general(coduser, codpack));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public List<bag_general> selectSomethingWhereBcGbag(String thing, bag_general bg){
        List<bag_general> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT "+thing.toUpperCase()+" FROM BAG_GENERAL WHERE (CODUS = ? AND CODPACK = ?);");
            ps.setInt(1, bg.getCodus());
            ps.setString(2, bg.getCodpack());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int codus = rs.getInt(1);
                String codpack = rs.getString(2);
                lista.add(new bag_general(codus, codpack));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
           System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    //==========================================================================================================================
    //SQL MOCHILA [Specific] Queries============================================================================================
    @Override
    public void addSbag(bag_specific bs){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO BAG_SPECIFIC (CODUS, BNAME) VALUES(?,?);");
            ps.setInt(1, bs.getCodus());
            ps.setString(2, bs.getBname());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public void updateSbag(bag_specific bs, String bname){
        try {
            PreparedStatement ps = getConnection().prepareStatement("UPDATE BAG_SPECIFIC SET BNAME = ? WHERE CODBS = ?;");
            ps.setString(1, bname);
            ps.setInt(2, bs.getCodbs());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public void deleteFromSbag(int codbs){
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM BAG_SPECIFIC WHERE CODBS = ?;");
            ps.setInt(1, codbs);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public List<bag_specific> selectAllWhereSbag(int codus){
        List<bag_specific> lista = new ArrayList<>();
        try {
            String statement = "SELECT * FROM BAG_SPECIFIC WHERE CODUS = ?;";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setInt(1, codus);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codbs = rs.getInt(1);
                int coduser = rs.getInt(2);
                String bname = rs.getString(3);
                lista.add(new bag_specific(codbs, coduser, bname));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    //==========================================================================================================================
    //SQL MOCHILA [Specific-Items] Queries======================================================================================
    @Override
    public void addSBItem(bag_specific_item bsi){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO BAG_SPECIFIC_ITEMS VALUES(?,?);");
            ps.setInt(1, bsi.getCodbs());
            ps.setString(2, bsi.getCodpack());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public void deleteFromSbag(bag_specific_item bsi){
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM BAG_SPECIFIC_ITEMS WHERE CODPACK = ?;");
            ps.setString(1, bsi.getCodpack());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    //==========================================================================================================================
    
    //SQL LUGARES Queries=======================================================================================================
    @Override
    public void addLugar(geografia g){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO LUGARES VALUES(?,?,?,?);");
            ps.setString(1, g.getCodigo());
            ps.setString(2, g.getContinente());
            ps.setString(3, g.getPais());
            ps.setString(4, g.getLugar());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
    }
    @Override
    public List<String> selectSomethingFromLugares(String thing){
        List<String> lista = new ArrayList<>();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT "+thing.toUpperCase()+" FROM LUGARES;");
            while (rs.next()){
                lista.add(rs.getString(thing.toUpperCase()));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
           System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public List<String> simpleWhereSelectLugares(String seleccionar, String consulta, String parametro) {
        List<String> lista = new ArrayList<>();
        try {
            String statement = "SELECT "+seleccionar.toUpperCase()+" FROM LUGARES WHERE "+consulta.toUpperCase()+" = ?;";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setString(1, parametro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString(seleccionar.toUpperCase()));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public List<String> simpleDistinctSelectLugares(String seleccionar) {
        List<String> lista = new ArrayList<>();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT "+seleccionar.toUpperCase()+" FROM LUGARES;");
            while (rs.next()) {
                lista.add(rs.getString(seleccionar.toUpperCase()));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public List<String> simpleDistinctWhereSelectLugares(String seleccionar, String condicion, String parametro) {
        List<String> lista = new ArrayList<>();
        try {
            String statement = "SELECT DISTINCT "+seleccionar.toUpperCase()+" FROM LUGARES WHERE "+condicion.toUpperCase()+" = ?;";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setString(1, parametro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString(seleccionar.toUpperCase()));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        }
        return lista;
    }
    @Override
    public String getIndexCodeFromLugares(String tipo, String place){
        tipo = tipo.toUpperCase();
        try {
            String statement = "SELECT CODE FROM LUGARES WHERE "+tipo.toUpperCase()+" = ?;";
            PreparedStatement ps = getConnection().prepareStatement(statement);
            ps.setString(1, place);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getString("CODE");
            }
        } catch (SQLException ex) {
            System.out.println("Error with DataBase | Exception: "+ex);
        } 
        return "---";
    }
    //==========================================================================================================================
}