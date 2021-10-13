package interfaces;

import java.sql.Connection;
import java.util.List;
import modelo.*;

public interface Interface_SQLProcessData {
    //SQL Connection============================================================================================================
    public Connection getConnection();
    //==========================================================================================================================
    //SQL USUARIO Queries=======================================================================================================
    public void addUsuario(usuario u);
    public usuario selectSomethingWhereFromUsuario(String thing, String condition, String parametro);
    //==========================================================================================================================
    //SQL COMENTARIO Queries====================================================================================================
    public void addComentario(comentario c);
    public void updateVoteFromComentario(int codcoment, int valor);
    public List<comentario> selectWhereFromMSComentario(String seleccionar, String condition, String c, int parametro, String codepack);
    //==========================================================================================================================
    //SQL VOTO Queries====================================================================================================
    public void addVoto(voto v);
    public List<voto> selectAllFromVoto();
    public List<voto> selectWhereFromVoto(String seleccionar, String condition, int parametro);
    //==========================================================================================================================
    //SQL PAQUETE Queries=======================================================================================================
    public void addPaquete(paquete p);
    public List<paquete> selectSomethingFromPaquete(String thing);
    public List<paquete> selectSubstringFromPaquete(String seleccionar, String condition, String parametro);
    public List<paquete> selectTwoSubstringsFromPaquete(String seleccionar, String condition, int a, int b, String parametro, int x, int y);
    public List<paquete> selectMultiFromPaquete(List<String> codigos);
    //==========================================================================================================================
    //SQL MOCHILA [General] Queries=============================================================================================
    public void addGbag(bag_general bg);
    public void deleteFromGbag(bag_general bg);
    public List<bag_general> selectSomethingWhereMcGbag(String thing, int codus);
    public List<bag_general> selectSomethingWhereBcGbag(String thing, bag_general bg);
    //==========================================================================================================================
    //SQL MOCHILA [Specific] Queries============================================================================================
    public void addSbag(bag_specific bs);
    public void updateSbag(bag_specific bs, String bname);
    public void deleteFromSbag(int codbs);
    public List<bag_specific> selectAllWhereSbag(int codus);
    //==========================================================================================================================
    //SQL MOCHILA [Specific-Items] Queries======================================================================================
    public void addSBItem(bag_specific_item bsi);
    public void deleteFromSbag(bag_specific_item bsi);
    //==========================================================================================================================
    
    //SQL LUGARES Queries=======================================================================================================
    public void addLugar(geografia g);
    public List<String> selectSomethingFromLugares(String thing);
    public List<String> simpleWhereSelectLugares(String seleccionar, String consulta, String parametro);
    public List<String> simpleDistinctSelectLugares(String seleccionar);
    public List<String> simpleDistinctWhereSelectLugares(String seleccionar, String condicion, String parametro);
    public String getIndexCodeFromLugares(String tipo, String place);
    //==========================================================================================================================
}
