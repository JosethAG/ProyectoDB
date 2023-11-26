package Controller;

import Model.Usuarios;
import View.JFUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;



public class UsuariosController {
     private JFUsuarios jfusuarios;
    private Usuarios usuarios;

//    public UsuariosController(JFUsuarios jfusuarios) {
//        this.jfusuarios = jfusuarios;
//        this.usuarios = new Usuarios("jdbc:oracle:thin:@localhost:1521:orcl","admin","admin1234");
//
//        // Agregar el manejador de eventos al botón
//        jfusuarios.getbtnGuardar().addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                ejecutarProcedimiento();
//            }
//        });
//    }
    
    
    public UsuariosController(JFUsuarios jfusuarios) {
           this.jfusuarios = jfusuarios;
        try {
           this.usuarios = new Usuarios("jdbc:oracle:thin:@localhost:1521:orcl","admin","admin1234");
        } catch (SQLException | ClassNotFoundException e) {
            // Manejo de excepciones: Imprimir el error y cerrar la aplicación (puedes considerar un manejo más elegante)
            e.printStackTrace();
            System.exit(1);
        }

        // Agregar el manejador de eventos al botón
         jfusuarios.getbtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarProcedimiento();
            }
        });
    }

//    public void ejecutarProcedimiento() {
//        String parametro1 = jfusuarios.getCedula();
//        String parametro2 = jfusuarios.getCedula();
//        String parametro3 = jfusuarios.getCorreo();
//        String parametro4 = jfusuarios.getContrasenna();
//
//        // Llamar al método del Usuarios para ejecutar el procedimiento almacenado
//        usuarios.ejecutarProcedimiento(parametro1, parametro2,parametro3,parametro4);
//
//        // Limpiar los campos en la JFUsuarios
//        jfusuarios.limpiarCampos();
//    }
    
    
     public void ejecutarProcedimiento() {
        try {
        int parametro1 = jfusuarios.getCedula();
        String parametro2 = jfusuarios.getNombre();
        String parametro3 = jfusuarios.getCorreo();
        String parametro4 = jfusuarios.getContrasenna();

            // Llamar al método del Modelo para ejecutar el procedimiento almacenado
            usuarios.ejecutarProcedimiento(parametro1, parametro2,parametro3,parametro4);

            // Limpiar los campos en la Vista
            jfusuarios.limpiarCampos();
            
         
            jfusuarios.limpiarCampos();
        } catch (SQLException e) {
            // Manejo de excepciones: Mostrar un mensaje al usuario, log, etc.
            e.printStackTrace();
        }
    }
    
}
