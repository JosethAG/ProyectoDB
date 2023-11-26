package Controller;

import Model.Usuarios;
import View.JFUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class UsuariosController {
     private JFUsuarios jfusuarios;
    private Usuarios usuarios;

    public UsuariosController(JFUsuarios jfusuarios) {
        this.jfusuarios = jfusuarios;
        this.usuarios = new Usuarios("jdbc:oracle:thin:@localhost:1521:orcl","admin","admin1234");

        // Agregar el manejador de eventos al botón
        jfusuarios.getbtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarProcedimiento();
            }
        });
    }

    public void ejecutarProcedimiento() {
        String parametro1 = jfusuarios.getCedula();
        String parametro2 = jfusuarios.getCedula();
        String parametro3 = jfusuarios.getCorreo();
        String parametro4 = jfusuarios.getContrasenna();

        // Llamar al método del Usuarios para ejecutar el procedimiento almacenado
        usuarios.ejecutarProcedimiento(parametro1, parametro2,parametro3,parametro4);

        // Limpiar los campos en la JFUsuarios
        jfusuarios.limpiarCampos();
    }
    
}
