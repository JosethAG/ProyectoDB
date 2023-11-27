package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import oracle.sql.DATE;

public class Clientes {
    /*
    private int clienteID;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaNacimiento;
    private int provinciaID;

    public Clientes() {
    }

    public Clientes(int clienteID, String nombre, String apellido, String email, Date fechaNacimiento, int provinciaID) {
        this.clienteID = clienteID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.provinciaID = provinciaID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getProvinciaID() {
        return provinciaID;
    }

    public void setProvinciaID(int provinciaID) {
        this.provinciaID = provinciaID;
    }*/
    
    
    private Connection conn;
    
        public Clientes(String url, String usuario, String contraseña) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, usuario, contraseña);
        } catch (ClassNotFoundException | SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

    public void ejecutarProcedimiento(int id, String firstName,String lastName,
            String email, String fechaNacimiento, int provincia) throws SQLException {
        try {
            String sql = "{call Create_Client(?, ?, ?, ?)}";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, firstName);
                stmt.setString(3, lastName);
                stmt.setString(4, email);
                stmt.setString(5, fechaNacimiento);
                stmt.setInt(5, provincia);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se agregó el cliente correctamente");
            }
        } catch (SQLException e) {
         // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
             throw e;
             
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
}
