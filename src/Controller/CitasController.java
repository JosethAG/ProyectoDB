package Controller;

import Model.Citas;
import Model.Conexion;
import View.JFCitas;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CitasController {

    private JFCitas jfCitas;
    private Conexion conection = new Conexion();

    public CitasController(JFCitas jfCitas) {
           this.jfCitas = jfCitas;
           
           jfCitas.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPCrearCita();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfCitas.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPEliminarcita();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
           
      
}
       
       public void SPCrearCita() throws SQLException {
        int idcita = jfCitas.getLblCitas();
        int idcliente = jfCitas.getLblCliente();
        int especialista = jfCitas.getLblEspecialista();
        String fecha = jfCitas.getJfecha();
        String hora = jfCitas.getLblhora();
        int sucursal = jfCitas.getLblSucursal();
        int tipocita = jfCitas.getTipocita();
        int estado = jfCitas.getCbxEstado();

        try {
            String sql = "{call Crear_Cita(?, ?, ?, ?, ?, ?,?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, idcita);
                stmt.setInt(2, idcliente);
                stmt.setInt(3, especialista);
                stmt.setString(4, fecha);
                stmt.setString(5, hora);
                stmt.setInt(6, sucursal);
                stmt.setInt(7, tipocita);
                stmt.setInt(8, estado);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se agregó el cliente correctamente");
                // Limpiar los campos en la Vista
                jfCitas.limpiarCampos();
                jfCitas.mostrarDatos();

            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

    public void SPEliminarcita() throws SQLException {
        int idcita = jfCitas.getLblCitas();
        int idcliente = jfCitas.getLblCliente();
        int especialista = jfCitas.getLblEspecialista();
        String fecha = jfCitas.getJfecha();
        String hora = jfCitas.getLblhora();
        int sucursal = jfCitas.getLblSucursal();
        int tipocita = jfCitas.getTipocita();
        int estado = jfCitas.getCbxEstado();

        try {
            String sql = "{call update_cita(?, ?, ?, ?, ?, ?,?,?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, idcita);
                stmt.setInt(2, idcliente);
                stmt.setInt(3, especialista);
                stmt.setString(4, fecha);
                stmt.setString(5, hora);
                stmt.setInt(6, sucursal);
                stmt.setInt(7, tipocita);
                stmt.setInt(8, estado);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se agregó el cliente correctamente");
                // Limpiar los campos en la Vista
                jfCitas.limpiarCampos();
                jfCitas.mostrarDatos();

            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }
        
        

        

}
