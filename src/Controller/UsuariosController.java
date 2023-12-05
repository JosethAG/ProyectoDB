package Controller;

import Model.Conexion;
import Model.Usuarios;
import View.JFUsuarios;import java.sql.CallableStatement;
;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuariosController {

    private JFUsuarios jfusuarios;
    private Usuarios usuarios;
    private Conexion conection = new Conexion();

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
    }

    public UsuariosController() {
        
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
                JOptionPane.showMessageDialog(null, "Se agregó el cliente correctamente");
                // Limpiar los campos en la Vista
                jfusuarios.limpiarCampos();
            
            }
        } catch (SQLException e) {
            // Manejo de excepciones: Imprimir el error y relanzar la excepción
            e.printStackTrace();
            throw e;
        } 
    }
    
    private static final String FINDALL_USERS = "?=call findAllUsers()"; //Variable que guarda la cadena de llamado en la DB
    
    public Collection findAllusuarios(){ //Procedimiento para almacenar toda la informacion
        
        try {
            conection.getConexion();
        } catch (Exception e) {
        }
        
        ResultSet rs = null;
        ArrayList collection = new ArrayList();
        Usuarios user = null;
        CallableStatement pstmt = null;
        
        try {
            pstmt = conection.getConexion().prepareCall(FINDALL_USERS); //preparmos el sql 
            pstmt.registerOutParameter(1,OracleTypes.CURSOR); //Obtenemos el cursor
            pstmt.execute(); 
            //Ejecutamos el procedimiento 
            
            rs = (ResultSet) pstmt.getObject(1); //Obtenemos el objeto
            //Ciclo para almacenar los datos de cada registro/
            while(rs.next()){ 
                user = new Usuarios(
                        rs.getInt("USER_ID"),
                        rs.getString("NAME_USERS"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD")
                );
                
                collection.add(user); //agregamos el objeto a la coleccion
            }
        } catch (Exception e) {
        } finally{ //procedimiento para cerrar los datos de conexion
            try {
                if(rs!= null){
                    rs.close();
                }
                
                if(pstmt != null){
                    pstmt.close();
                }
                
                conection.cerrarConexion();
            } catch (Exception e) {
            }
        }
        
        return collection;
    }
            
}
