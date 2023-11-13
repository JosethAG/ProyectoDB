package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.ConexionDB;
import java.sql.Connection;

public class LoginController {

    public static void main(String[] args) {
     //   ConexionDB conexion = new ConexionDB();
      //  Connection  con = conexion.obtenerConexion();
        /*
        // Nombre del procedimiento almacenado
        String nombreProcedimiento = "nombre_del_procedimiento";

        // Valores a pasar al procedimiento
        String parametro1 = "valor1";
        int parametro2 = 123;

       try {
            // Establecer la conexión
            Connection  con = conexion.obtenerConexion();

            // Consulta SQL
            String consulta = "SELECT * FROM TB_USERS";

            // Preparar la declaración
            try (PreparedStatement statement = con.prepareStatement(consulta)) {
                // Configurar parámetros, si es necesario
                statement.setString(1, "valorCondicion");

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Procesar los resultados
                    while (resultSet.next()) {
                        // Acceder a los valores de las columnas
                        String valorColumna1 = resultSet.getString("columna1");
                        int valorColumna2 = resultSet.getInt("columna2");

                        // Hacer algo con los valores, por ejemplo, imprimirlos
                        System.out.println("Columna1: " + valorColumna1 + ", Columna2: " + valorColumna2);
                    }
                }
            }

            // Cerrar la conexión
            con.close();

        } catch (SQLException e) {
            // Manejar excepciones de SQL
            e.printStackTrace();
        }
*/
    }

}
