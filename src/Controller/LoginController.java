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
    private Conexion conection = new Conexion();

    public LoginController(JFLogin jfLogin) {
        this.jfLogin = jfLogin;

        // Escucha cuando se realiza click en el boton y ejecuta un proceso
        jfLogin.getBtnIngresar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
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

        try {
            String sql = "SELECT USER_ID, PASSWORD from TB_USERS WHERE USER_ID = " +pNombre + "";

            Statement st = conection.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);//Aqui ejecuta la consulta

            String datos[] = new String[2];

            while (rs.next()) {//Se hace el llenado de la tabla con los datos que se obtienen  de la consulta
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                if (datos[1].equals(pContra)) {
                    jfLogin.dispose();
                    JFHome abrir = new JFHome();
                    abrir.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales Erroneas");
                }
            }

        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        }
    }

}
