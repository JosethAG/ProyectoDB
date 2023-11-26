package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuarios {
    
//    private int UsuarioID;
//    private String NombreUsuario;
//    private String Correo;
//    private String Contrasenna;
//
//    public Usuarios(int UsuarioID, String NombreUsuario, String Correo, String Contrasenna) {
//        this.UsuarioID = UsuarioID;
//        this.NombreUsuario = NombreUsuario;
//        this.Correo = Correo;
//        this.Contrasenna = Contrasenna;
//    }
//
//    public int getUsuarioID() {
//        return UsuarioID;
//    }
//
//    public void setUsuarioID(int UsuarioID) {
//        this.UsuarioID = UsuarioID;
//    }
//
//    public String getNombreUsuario() {
//        return NombreUsuario;
//    }
//
//    public void setNombreUsuario(String NombreUsuario) {
//        this.NombreUsuario = NombreUsuario;
//    }
//
//    public String getCorreo() {
//        return Correo;
//    }
//
//    public void setCorreo(String Correo) {
//        this.Correo = Correo;
//    }
//
//    public String getContrasenna() {
//        return Contrasenna;
//    }
//
//    public void setContrasenna(String Contrasenna) {
//        this.Contrasenna = Contrasenna;
//    }
//    
    
    private Connection conn;

    public Usuarios(String url, String usuario, String contraseña) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, usuario, contraseña);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void ejecutarProcedimiento(String parametro1, String parametro2,String parametro3,String parametro4) {
        try {
            String sql = "{call Create_User(?, ?, ?, ?)}";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, parametro1);
                stmt.setString(2, parametro2);
                stmt.setString(3, parametro3);
                stmt.setString(4, parametro4);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

