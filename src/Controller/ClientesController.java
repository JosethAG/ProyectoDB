package Controller;

import Model.Clientes;
import Model.Conexion;
import View.JFClientes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClientesController {
    
    private JFClientes jfClientes;
    private Clientes clientes;
    private Conexion conection = new Conexion();
    
    public ClientesController(JFClientes jfClientes) {
           this.jfClientes = jfClientes;
           
         // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfClientes.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPCrearCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfClientes.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPEliminarCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void SPCrearCliente() throws SQLException {
        int idCed = jfClientes.getTxtCedula();
        String pNombre = jfClientes.getTxtNombre();
        String pApellidos = jfClientes.getTxtApellidos();
        String pCorreo = jfClientes.getTxtCorreo();
        int pProvincia = jfClientes.getCdProvincia();
        String pFechaNacimiento = jfClientes.getDcNacimiento();

        try {
            String sql = "{call Create_Client(?, ?, ?, ?, ?, ?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, idCed);
                stmt.setString(2, pNombre);
                stmt.setString(3, pApellidos);
                stmt.setString(4, pCorreo);
                stmt.setString(5, pFechaNacimiento );
                stmt.setInt(6,  pProvincia);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se agregó el cliente correctamente");
                // Limpiar los campos en la Vista
                jfClientes.limpiarCampos();
                jfClientes.mostrarDatos();
                
            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

    public void SPEliminarCliente() throws SQLException {
        int idCed = jfClientes.getTxtCedula();
        String pNombre = jfClientes.getTxtNombre();
        String pApellidos = jfClientes.getTxtApellidos();
        String pCorreo = jfClientes.getTxtCorreo();
        int pProvincia = jfClientes.getCdProvincia();
        String pFechaNacimiento = jfClientes.getDcNacimiento();
        int pEstado = jfClientes.getCbEstado();

        try {
            String sql = "{call UPATE_CLIENT(?, ?, ?, ?, ?, ?, ?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, idCed);
                stmt.setString(2, pNombre);
                stmt.setString(3, pApellidos);
                stmt.setString(4, pCorreo);
                stmt.setString(5, pFechaNacimiento );
                stmt.setInt(6,  pProvincia);
                stmt.setInt(7, pEstado);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se inactivo el cliente correctamente");
                // Limpiar los campos en la Vista
                jfClientes.limpiarCampos();
                jfClientes.mostrarDatos();
            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

}
