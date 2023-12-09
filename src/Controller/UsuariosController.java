package Controller;

import Model.Conexion;
import Model.Usuarios;
import View.JFUsuarios;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuariosController {

    private JFUsuarios jfusuarios;
    private Usuarios usuarios;
    private Conexion conection = new Conexion();

    public UsuariosController() {
    }

    public UsuariosController(JFUsuarios jfusuarios) {
        this.jfusuarios = jfusuarios;
        this.usuarios = new Usuarios();

        // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfusuarios.getbtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPCrearUsuario();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfusuarios.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPEliminarUsuario();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void SPCrearUsuario() throws SQLException {
        int idCed = jfusuarios.getCedula();
        String pNombre = jfusuarios.getNombre();
        String pCorre = jfusuarios.getCorreo();
        String pContrasena = jfusuarios.getContrasenna();

        try {
            String sql = "{call Create_User(?, ?, ?, ?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, idCed);
                stmt.setString(2, pNombre);
                stmt.setString(3, pCorre);
                stmt.setString(4, pContrasena);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se agregó el usuario correctamente");
                // Limpiar los campos en la Vista
                jfusuarios.limpiarCampos();
                jfusuarios.mostrarDatos();
            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

    public void SPEliminarUsuario() throws SQLException {
        int idCed = jfusuarios.getCedula();

        try {
            String sql = "{call Delete_User(?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, idCed);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se eliminó el usuario correctamente");
                // Limpiar los campos en la Vista
                jfusuarios.limpiarCampos();
                jfusuarios.mostrarDatos();
            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }
}
