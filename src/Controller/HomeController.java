package Controller;

import Model.Conexion;
import Model.Home;
import View.JFHome;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class HomeController {

    private JFHome jfHome;
    private Home home;
    private Conexion conection = new Conexion();

    public HomeController(JFHome jfHome) {
        this.jfHome = jfHome;

        jfHome.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SPBuscarCita();
                } catch (SQLException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        

    }

    public void SPBuscarCita() throws SQLException {
        int idcita = jfHome.getcodigoCita();
        int idcliente = jfHome.getclienteId();
        String fechadesde = jfHome.getFechadesde();
        String fechahasta = jfHome.getFechahasta();
        int sucursal = jfHome.getSucursal();
        int tipocita = jfHome.gettipocita();
        int estado = jfHome.getEstado();

        try {
            String sql = "{call Buscar_Cita(?, ?, ?, ?, ?, ?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setInt(1, idcita);
                stmt.setInt(2, idcliente);
                stmt.setString(3, fechadesde);
                stmt.setString(4, fechahasta);
                stmt.setInt(5, sucursal);
                stmt.setInt(6, tipocita);
                stmt.setInt(7, estado);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se agregó el cliente correctamente");
                // Limpiar los campos en la Vista
                jfHome.limpiarCampos();
                jfHome.mostrarDatos();

            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

    

}
