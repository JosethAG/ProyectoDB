package Controller;

import Model.Clientes;
import View.JFClientes;
import java.sql.SQLException;

public class ClientesController {
    
    private JFClientes jfClientes;
    private Clientes clientes;
    
    public ClientesController(JFClientes jfClientes) {
           this.jfClientes = jfClientes;
        try {
           this.clientes = new Clientes("jdbc:oracle:thin:@localhost:1521:orcl","admin","admin1234");
        } catch (SQLException | ClassNotFoundException e) {
            // Manejo de excepciones: Imprimir el error y cerrar la aplicación (puedes considerar un manejo más elegante)
            e.printStackTrace();
            System.exit(1);
        }

        // Agregar el manejador de eventos al botón
         jfClientes.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarProcedimiento();
            } 
        });
    }
    
     public void ejecutarProcedimiento() {
        try {
        int id = jfClientes.getTxtCedula();
        String firstName = jfClientes.getTxtNombre();
        String lastName = jfClientes.getTxtApellidos();
        String email = jfClientes.getTxtCorreo();
        String fechaNacimiento = jfClientes.getDcNacimiento();
        int provincia = jfClientes.getTxtProvincia();

            // Llamar al método del Modelo para ejecutar el procedimiento almacenado
            clientes.ejecutarProcedimiento(id, firstName,lastName,email,fechaNacimiento,provincia);
            
            // Limpiar los campos en la Vista
            jfClientes.limpiarCampos();
            
         
            jfClientes.limpiarCampos();
        } catch (SQLException e) {
            // Manejo de excepciones: Mostrar un mensaje al usuario, log, etc.
            e.printStackTrace();
        }
    }

}
