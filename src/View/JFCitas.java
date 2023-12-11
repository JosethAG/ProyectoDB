package View;

import Controller.CitasController;
import Model.Conexion;
import com.toedter.calendar.JDateChooser; // Añadido para soporte de fecha
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class JFCitas extends javax.swing.JFrame {

    private CitasController citasController;

    public JFCitas() {
        initComponents();
        this.setLocationRelativeTo(null);
        citasController = new CitasController(this);

        try {
            mostrarDatos();
        } catch (SQLException ex) {
            Logger.getLogger(JFUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public int getLblCitas() {
        return Integer.parseInt( lblCitas.getText());
    }
    
    public int getLblCliente() {
        return Integer.parseInt( lblCliente.getText());
    }
    
    
    public String getJfecha() {
        String fecha =((JTextField)jfecha.getDateEditor().getUiComponent()).getText();
        return fecha;
    }
    
    public String getLblhora() {
        return lblhora.getText();
    }
    
    public int getLblSucursal() {
        return Integer.parseInt(lblSucursal.getText());
    }
    
    public int getTipocita() {
        return  tipocita.getSelectedIndex();
    }
    
     public String getCbxEstado() {
        return  cbxEstado.getSelectedItem().toString();
    }

    public void setLblCitas(JTextField lblCitas) {
        this.lblCitas = lblCitas;
    }


    public void setJfecha(JDateChooser jfecha) {
        this.jfecha = jfecha;
    }

    public int getLblEspecialista() {
        return Integer.parseInt(lblEspecialista.getText());
    }

    public void setLblEspecialista(JTextField lblEspecialista) {
        this.lblEspecialista = lblEspecialista;
    }

    

    public void setLblCliente(JTextField lblCliente) {
        this.lblCliente = lblCliente;
    }

   

    public void setCbxEstado(JComboBox<String> cbxEstado) {
        this.cbxEstado = cbxEstado;
    }

    

    public void setLblSucursal(JTextField lblSucursal) {
        this.lblSucursal = lblSucursal;
    }

    

    public void setLblhora(JTextField lblhora) {
        this.lblhora = lblhora;
    }

    

    public void setTipocita(JComboBox<String> tipocita) {
        this.tipocita = tipocita;
    }

    public void limpiarCampos() {
        lblCitas.setText("");
        lblCliente.setText("");
        lblEspecialista.setText("");
        jfecha.cleanup();
        lblhora.setText("");
        lblSucursal.setText("");
        tipocita.setSelectedItem(0);
        cbxEstado.setSelectedItem(0);

    }

    public void mostrarDatos() throws SQLException {
        Conexion conection = new Conexion();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID CITA");
        dtm.addColumn("ID CLIENTE");
        dtm.addColumn("FECHA");
        dtm.addColumn("ID ESPECIALISTA");
        
        String sql = "SELECT APPOINTMENT_ID, CLIENTE_ID, FECHA, ESPECIALISTA_ID "
                + "FROM TB_APPOINTMENTS";

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
        tblCitas.setModel(dtm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtCodigoCita = new javax.swing.JLabel();
        lblCitas = new javax.swing.JTextField();
        txtCliente = new javax.swing.JLabel();
        lblCliente = new javax.swing.JTextField();
        txtEspecialista = new javax.swing.JLabel();
        lblEspecialista = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        bntCita = new javax.swing.JButton();
        btnEspecialistas = new javax.swing.JButton();
        btnAuditoria = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCitas = new javax.swing.JTable();
        txtProvincia1 = new javax.swing.JLabel();
        jfecha = new com.toedter.calendar.JDateChooser();
        txtHora = new javax.swing.JLabel();
        lblhora = new javax.swing.JTextField();
        txtSucursall = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JTextField();
        txtTipoCita = new javax.swing.JLabel();
        tipocita = new javax.swing.JComboBox<>();
        txtEstado = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(java.awt.Color.white);

        btnModificar.setText("Modificar");
        btnModificar.setName("bntEliminar"); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setName("bntGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtCodigoCita.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCodigoCita.setText("Código Cita");

        lblCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblCitasActionPerformed(evt);
            }
        });

        txtCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCliente.setText("Cliente");

        txtEspecialista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEspecialista.setText("Especialista");

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setForeground(new java.awt.Color(51, 51, 0));

        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("HOME");
        btnHome.setBorder(null);
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        bntCita.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(16, 16, 16))
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

        tblCitas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCitas);

        txtProvincia1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtProvincia1.setText("Fecha");

        jfecha.setDateFormatString("dd-MMM-yy");

        txtHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHora.setText("Hora");

        lblhora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblhoraActionPerformed(evt);
            }
        });

        txtSucursall.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSucursall.setText("Sucursal");

        txtTipoCita.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTipoCita.setText("Tipo de Cita");

        tipocita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Virtual", "Presencial" }));

        txtEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEstado.setText("Estado");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Programada", "Realizada", "Cancelada", "" }));
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEstado)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSucursal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblhora, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jfecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addComponent(lblCitas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEspecialista)
                            .addComponent(tipocita, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEspecialista)
                                    .addComponent(txtCliente)
                                    .addComponent(txtCodigoCita)
                                    .addComponent(txtProvincia1)
                                    .addComponent(txtHora)
                                    .addComponent(txtSucursall)
                                    .addComponent(txtTipoCita))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(16, 16, 16))))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCodigoCita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEspecialista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtProvincia1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblhora, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSucursall)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTipoCita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tipocita, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCitasActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
         JFHome abrir = new JFHome();
        abrir.setVisible(true);
        this.setVisible(false);  
    }//GEN-LAST:event_btnHomeActionPerformed

    private void bntCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bntCitaActionPerformed

    private void btnAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaActionPerformed
         JFAuditoria abrir;
        try {
            abrir = new JFAuditoria();
            abrir.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JFCitas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);  
    }//GEN-LAST:event_btnAuditoriaActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
         JFUsuarios abrir = new JFUsuarios();
        abrir.setVisible(true);
        this.setVisible(false);  
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        JFClientes abrir = new JFClientes();
        abrir.setVisible(true);
        this.setVisible(false);  
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
         System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void lblhoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblhoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblhoraActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEspecialistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEspecialistasActionPerformed
         JFEspecialistas abrir = new JFEspecialistas();
        abrir.setVisible(true);
        this.setVisible(false);  
    }//GEN-LAST:event_btnEspecialistasActionPerformed

    private void tblCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitasMouseClicked
        // TODO add your handling code here:
        lblCitas.setText(tblCitas.getValueAt(tblCitas.getSelectedRow(), 0).toString());
        lblCliente.setText(tblCitas.getValueAt(tblCitas.getSelectedRow(), 1).toString());
        lblEspecialista.setText(tblCitas.getValueAt(tblCitas.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_tblCitasMouseClicked
                                     
 
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
            java.util.logging.Logger.getLogger(JFCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFCitas().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCita;
    private javax.swing.JButton btnAuditoria;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnEspecialistas;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jfecha;
    private javax.swing.JTextField lblCitas;
    private javax.swing.JTextField lblCliente;
    private javax.swing.JTextField lblEspecialista;
    private javax.swing.JTextField lblSucursal;
    private javax.swing.JTextField lblhora;
    private javax.swing.JTable tblCitas;
    private javax.swing.JComboBox<String> tipocita;
    private javax.swing.JLabel txtCliente;
    private javax.swing.JLabel txtCodigoCita;
    private javax.swing.JLabel txtEspecialista;
    private javax.swing.JLabel txtEstado;
    private javax.swing.JLabel txtHora;
    private javax.swing.JLabel txtProvincia1;
    private javax.swing.JLabel txtSucursall;
    private javax.swing.JLabel txtTipoCita;
    // End of variables declaration//GEN-END:variables

}
