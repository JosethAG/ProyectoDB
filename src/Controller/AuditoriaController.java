package Controller;

import Model.Conexion;
import View.JFAuditoria;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AuditoriaController {

    private JFAuditoria jfAuditoria;
    private Conexion conection = new Conexion();

    public AuditoriaController(JFAuditoria jfAuditoria) throws SQLException {
        this.jfAuditoria = jfAuditoria;
        
        jfAuditoria.getExportar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporte();
            }
        });
    }
    
    public void reporte(){
        try {
            String query = "select * from Audit_Log";
            PreparedStatement stmt = conection.getConexion().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            String outputFile = "D:\\OracleResult.csv";
            resultToCsv(rs, outputFile);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /*
    public static void resultToCsv(ResultSet rs, String outputFile) throws SQLException, FileNotFoundException {

        PrintWriter csvWriter = new PrintWriter(new File(outputFile));
        ResultSetMetaData meta = rs.getMetaData();
        int numberOfColumns = meta.getColumnCount();
        String dataHeaders = """ + meta.getColumnName(1) + "
        "";
    for (int i = 2; i < numberOfColumns + 1; i++) {
            dataHeaders += ","
        
        " + meta.getColumnName(i).replaceAll(""", "\"") + """;
    }
    csvWriter.println(dataHeaders);
        while (rs.next()) {
            String row = """ + rs.getString(1).trim().replaceAll("
            "", "\"") + """;
      for (int i = 2; i < numberOfColumns + 1; i++) {
                row += ","
            
            " + rs.getString(i).trim().replaceAll(""", "\"") + """;
      }
      csvWriter.println(row);
        }
        csvWriter.close();
    }
*/
    public static void resultToCsv(ResultSet rs, String outputFile) throws SQLException, FileNotFoundException {
        PrintWriter csvWriter = new PrintWriter(new File(outputFile));
        ResultSetMetaData meta = rs.getMetaData();
        int numberOfColumns = meta.getColumnCount();

        // Escribir encabezados
        StringBuilder dataHeaders = new StringBuilder(meta.getColumnName(1));
        for (int i = 2; i < numberOfColumns + 1; i++) {
            dataHeaders.append(",").append(meta.getColumnName(i).replaceAll("\"", "\"\""));
        }
        csvWriter.println(dataHeaders.toString());

        // Escribir datos
        while (rs.next()) {
            StringBuilder row = new StringBuilder(rs.getString(1).trim().replaceAll("\n", "\"\""));
            for (int i = 2; i < numberOfColumns + 1; i++) {
                row.append(",").append(rs.getString(i).trim().replaceAll("\"", "\"\""));
            }
            csvWriter.println(row.toString());
        }

        csvWriter.close();
    }
}
