package View;

import Controller.HomeController;
import Model.Conexion;
import com.toedter.calendar.JDateChooser; // Añadido para soporte de fecha
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JFHome extends javax.swing.JFrame {

    private HomeController homecontroller;

    public JFHome() {
        initComponents();
        this.setLocationRelativeTo(null);
        

        try {
            mostrarDatos();
        } catch (SQLException ex) {
            Logger.getLogger(JFHome.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
    }
    
    public int getclienteId() {
        return Integer.parseInt(lblCliente.getText());
    }
    
    public JButton getBtnGuardar() {
        return btnBuscar;
    }

    public void limpiarCampos() {
        lblCliente.setText("");
        
    }

    public void mostrarDatos_por_ID() throws SQLException{
        
        Conexion conection = new Conexion();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID CITA");
        dtm.addColumn("ID CLIENTE");
        dtm.addColumn("FECHA");
        dtm.addColumn("ID SUCURSAL");
        
        String sql = "SELECT APPOINTMENT_ID, CLIENTE_ID, FECHA, SUCURSAL_ID "
                + "FROM TB_APPOINTMENTS "
                + "WHERE CLIENTE_ID = " + lblCliente.getText() + " ";

        String datos[] = new String[4];

        Statement st = conection.getConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);//Aqui ejecuta la consulta
        
        while(rs.next()){//Se hace el llenado de la tabla con los datos que se obtienen  de la consulta
            datos[0] = String.valueOf(rs.getInt(1));
            datos[1] = String.valueOf(rs.getInt(2));
            datos[2] = rs.getString(3);
            datos[3] = String.valueOf(rs.getInt(4));
            dtm.addRow(datos);
        }
        tblHome.setModel(dtm);
}
    
    
    
    public void mostrarDatos() throws SQLException {
        Conexion conection = new Conexion();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID");
        dtm.addColumn("NOMBRE");
        dtm.addColumn("ESPECILIDAD");
        dtm.addColumn("ESTADO");

        String sql = "SELECT APPOINTMENT_ID, CLIENTE_ID, FECHA, SUCURSAL_ID  "
                + "FROM TB_APPOINTMENTS";

        String datos[] = new String[4];

        Statement st = conection.getConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);//Aqui ejecuta la consulta

        while (rs.next()) {//Se hace el llenado de la tabla con los datos que se obtienen  de la consulta
            datos[0] = String.valueOf(rs.getInt(1));
            datos[1] = String.valueOf(rs.getInt(2));
            datos[2] = rs.getString(3);
            datos[3] = String.valueOf(rs.getInt(4));
            dtm.addRow(datos);
        }
        tblHome.setModel(dtm);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        txtCliente = new javax.swing.JLabel();
        lblCliente = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        bntCita = new javax.swing.JButton();
        btnEspecialistas = new javax.swing.JButton();
        btnAuditoria = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHome = new javax.swing.JTable();
        btnHome1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(java.awt.Color.white);
        setPreferredSize(new java.awt.Dimension(808, 650));

        btnBuscar.setText("BUSCAR");
        btnBuscar.setName("bntGuardar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCliente.setText("Cliente");

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setForeground(new java.awt.Color(51, 51, 0));

        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("HOME");
        btnHome.setBorder(null);
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        bntCita.setForeground(new java.awt.Color(255, 255, 255));
        bntCita.setText("CITAS");
        bntCita.setBorder(null);
        bntCita.setContentAreaFilled(false);
        bntCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCitaActionPerformed(evt);
            }
        });

        btnEspecialistas.setForeground(new java.awt.Color(255, 255, 255));
        btnEspecialistas.setText("ESPECIALISTAS");
        btnEspecialistas.setBorder(null);
        btnEspecialistas.setContentAreaFilled(false);
        btnEspecialistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEspecialistasActionPerformed(evt);
            }
        });

        btnAuditoria.setForeground(new java.awt.Color(255, 255, 255));
        btnAuditoria.setText("AUDITORIA");
        btnAuditoria.setBorder(null);
        btnAuditoria.setContentAreaFilled(false);
        btnAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuditoriaActionPerformed(evt);
            }
        });

        btnUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnUsuarios.setText("USUARIOS");
        btnUsuarios.setBorder(null);
        btnUsuarios.setContentAreaFilled(false);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setText("CLIENTES");
        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.setBorder(null);
        btnSalir.setContentAreaFilled(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnHome)
                .addGap(18, 18, 18)
                .addComponent(bntCita)
                .addGap(18, 18, 18)
                .addComponent(btnAuditoria)
                .addGap(18, 18, 18)
                .addComponent(btnClientes)
                .addGap(18, 18, 18)
                .addComponent(btnEspecialistas)
                .addGap(18, 18, 18)
                .addComponent(btnUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHome)
                    .addComponent(bntCita)
                    .addComponent(btnEspecialistas)
                    .addComponent(btnAuditoria)
                    .addComponent(btnUsuarios)
                    .addComponent(btnClientes)
                    .addComponent(btnSalir))
                .addGap(16, 16, 16))
        );

        tblHome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblHome);

        btnHome1.setBackground(new java.awt.Color(0, 0, 102));
        btnHome1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome1.setForeground(new java.awt.Color(0, 0, 102));
        btnHome1.setText("VISUALIZACIÓN DE CITAS");
        btnHome1.setToolTipText("");
        btnHome1.setBorder(null);
        btnHome1.setContentAreaFilled(false);
        btnHome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome1ActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHome1)
                    .addComponent(txtCliente)
                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void bntCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCitaActionPerformed
        JFCitas abrir = new JFCitas();
        abrir.setVisible(true);
        this.setVisible(false);        
    }//GEN-LAST:event_bntCitaActionPerformed

    private void btnAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaActionPerformed
        JFAuditoria abrir;
        try {
            abrir = new JFAuditoria();
            abrir.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JFHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);  
    }//GEN-LAST:event_btnAuditoriaActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
           JFUsuarios abrir = new JFUsuarios();
           abrir.setVisible(true);
           this.setVisible(false);   // TODO add your handling code here:
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
           JFClientes abrir = new JFClientes();
           abrir.setVisible(true);
           this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
         System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHome1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        try {
            mostrarDatos_por_ID();
        } catch (SQLException ex) {
            Logger.getLogger(JFHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEspecialistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEspecialistasActionPerformed
           JFEspecialistas abrir = new JFEspecialistas();
           abrir.setVisible(true);
           this.setVisible(false);                          // TODO add your handling code here:
    }//GEN-LAST:event_btnEspecialistasActionPerformed

   
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCita;
    private javax.swing.JButton btnAuditoria;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnEspecialistas;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHome1;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblCliente;
    private javax.swing.JTable tblHome;
    private javax.swing.JLabel txtCliente;
    // End of variables declaration//GEN-END:variables

    

}
