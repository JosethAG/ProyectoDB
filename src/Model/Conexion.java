package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexion {
    
    private Connection conn;
    private Statement sentencia;
    private static Conexion instancia;
    
    public Conexion(){
        try{
             Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","admin","admin1234"); //Ajustar parametros
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error 
            e.printStackTrace();
        }
    }
    
    public static Conexion getInstancia(){
        if(instancia==null){
            instancia = new Conexion();
        }
        return instancia;
    } 

    public Connection getConexion() {
        return conn;
    }

    public void setConexion(Connection conexion) {
        this.conn = conexion;
    }

    public CallableStatement prepareCall(String call) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
