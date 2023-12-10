package Controller;
import Model.Conexion;
import View.JFEspecialistas;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EspecialistasController {
    private JFEspecialistas jfEspecialitas;
    private Conexion conection = new Conexion();
    
    public EspecialistasController(JFEspecialistas jfEspecialitas) {
           this.jfEspecialitas = jfEspecialitas;
           
         // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfEspecialitas.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPCrearCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfEspecialitas.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPModificar();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void SPCrearCliente() throws SQLException {
        int id = jfEspecialitas.getTxtID();
        String pNombre = jfEspecialitas.getTxtNombre();
        String pEspecialista = jfEspecialitas.getTxtEspecialidad();

        try {
            String sql = "{call Create_Especialista(?, ?, ?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, pNombre);
                stmt.setString(3, pEspecialista);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se agregó el especialista correctamente");
                // Limpiar los campos en la Vista
                jfEspecialitas.limpiarCampos();
                jfEspecialitas.mostrarDatos();
                
            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

    public void SPModificar() throws SQLException {
        int id = jfEspecialitas.getTxtID();
        String pNombre = jfEspecialitas.getTxtNombre();
        String pEspecialista = jfEspecialitas.getTxtEspecialidad();
        int pEstado = jfEspecialitas.getCbEstado();

        try {
            String sql = "{call UPATE_CLIENT(?, ?, ?, ?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, pNombre);
                stmt.setString(3, pEspecialista);
                stmt.setInt(4, pEstado);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se inactivo el especialista correctamente");
                // Limpiar los campos en la Vista
                jfEspecialitas.limpiarCampos();
                jfEspecialitas.mostrarDatos();
            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

}
