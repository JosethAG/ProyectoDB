package Controller;
import Model.Conexion;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspecialistasController {
    public static void main(String[]args) throws IOException, SQLException{
        Conexion conexion = Conexion.obtenerConexion();
      if (conexion != null){
          try {
              String insercion = "INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, USER_ID, ESPECIALIDAD) VALUES (?,?,?)";
          
          } catch(Exception e){
            e.printStackTrace();
       
              }
           
        }
      } 
                          
    }
    

