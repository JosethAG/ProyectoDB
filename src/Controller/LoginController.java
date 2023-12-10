package Controller;

import Model.Conexion;
import View.JFHome;
import View.JFLogin;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginController {

    private JFLogin jfLogin;
    private JFHome jfHome;
    private Conexion conection = new Conexion();
    
     public LoginController(JFLogin jfLogin) {
           this.jfLogin = jfLogin;
           
         // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfLogin.getBtnIngresar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    System.out.println("salto");
                    SPValidarLogin();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
     }
 
    public void SPValidarLogin() throws SQLException {
        String pNombre = jfLogin.getTxtUser();
        String pContra = jfLogin.getTxtContra();
        String respuesta = "0";

        try {
            /*
            String sql = "{call VALIDAR_USUARIO_CONTRASEÑA(?, ?, ?)}";
            try (PreparedStatement stmt = conection.getConexion().prepareStatement(sql)) {
                stmt.setString(1, pNombre);
                stmt.setString(2, pContra);
                stmt.executeUpdate();
                
                System.out.println(respuesta);
                if (respuesta == 1) {
                    jfLogin.dispose();
                    jfHome.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Se agregó el cliente correctamente");
                }
                
            }*/
        //String sql = "EXEC VALIDAR_USUARIO_CONTRASEÑA( "+ pNombre + "," + pContra + " );";
        String sql = "EXEC VALIDAR_USUARIO_CONTRASEÑA('101', 'pass1');";

        Statement st = conection.getConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);//Aqui ejecuta la consulta
        respuesta = rs.getString(1);
            System.out.println(respuesta);
        if (respuesta == "1") {
                    jfLogin.dispose();
                    jfHome.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Se agregó el cliente correctamente");
                }
        
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
        System.out.println("salte");
    }
    
}


