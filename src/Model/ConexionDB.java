package Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexionDB {

    public static void main(String[] args)  {

    
        try {
            // Cargar el controlador JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url= "jdbc:oracle:thin:@localhost:1521:orcl"; // Reemplaza con tu configuración
            String uname = "admin";
            String upass = "admin1234";
            // Establecer la conexión
            Connection conn = DriverManager.getConnection(url, uname, upass);
           
            String sql = "SELECT USER_ID FROM TB_USERS";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.print("USER_ID");
                   System.out.println(rs.getString(1));
                 }

        } catch (Exception e) {
            // Manejar la excepción de carga del controlador
            JOptionPane.showMessageDialog(null, e);
        }
    }
}