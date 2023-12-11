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
    
    
    

    public int getcodigoCita() {
        return Integer.parseInt(lblCitas.getText());
    }

    public int getclienteId() {
        return Integer.parseInt(lblCliente.getText());
    }
    

    public String getFechadesde() {
        String fecha =((JTextField)fechadesde.getDateEditor().getUiComponent()).getText();
        return fecha;
    }

    public String getFechahasta() {
        String fecha =((JTextField)fechahasta.getDateEditor().getUiComponent()).getText();
        return fecha;
    }
    
    
    public int getSucursal() {
        return Integer.parseInt(lblSucursal.getText());
    }
    
    public int gettipocita() {
        return tipocita.getSelectedIndex();
    }
    
    

    public String getEstado() {
        return cbxEstado.getSelectedItem().toString();
    }

    public JButton getBtnGuardar() {
        return btnBuscar;
    }

    public void limpiarCampos() {
        lblCitas.setText("");
        lblCliente.setText("");
        fechadesde.cleanup();
        fechahasta.cleanup();
        lblSucursal.setText("");
        tipocita.setSelectedIndex(0); 
        cbxEstado.setSelectedItem(0); 
        
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
    
//    public void mostrarDatos() throws SQLException{
//        Conexion conection = new Conexion();
//        DefaultTableModel dtm = new DefaultTableModel();
//        dtm.addColumn("ID CITA");
//        dtm.addColumn("ID CLIENTE");
//        dtm.addColumn("FECHA");
//        dtm.addColumn("ID SUCURSAL");
//        dtm.addColumn("ID TIPO CITA");
//        dtm.addColumn("ID ESTADO");
//        
//        String sql = "select APPOINTMENT_ID, CLIENTE_ID, FECHA, SUCURSAL_ID,TIPOCITA_ID,ESTADO from TB_APPOINTMENTS";
////        String sql = "SELECT APPOINTMENT_ID, CLIENTE_ID, FECHA, SUCURSAL_ID  FROM TB_APPOINTMENTS";
//
//        String datos[] = new String[6];
////        String datos[] = new String[3];
//
//        Statement st = conection.getConexion().createStatement();
//        ResultSet rs = st.executeQuery(sql);//Aqui ejecuta la consulta
//        
//        while(rs.next()){//Se hace el llenado de la tabla con los datos que se obtienen  de la consulta
//            datos[0] = String.valueOf(rs.getInt(1));
//            datos[1] = String.valueOf(rs.getInt(2));
//            datos[2] = rs.getString(3);
//            datos[3] = String.valueOf(rs.getInt(4));
//            datos[4] = String.valueOf(rs.getInt(5));
//            datos[5] = String.valueOf(rs.getInt(6));
//            dtm.addRow(datos);
//            
//    }
//        tblHome.setModel(dtm);
//}

    
    
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
        txtCodigoCita = new javax.swing.JLabel();
        lblCitas = new javax.swing.JTextField();
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
        txtProvincia1 = new javax.swing.JLabel();
        txtSucursall = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JTextField();
        txtTipoCita = new javax.swing.JLabel();
        tipocita = new javax.swing.JComboBox<>();
        txtEstado = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        txtProvincia2 = new javax.swing.JLabel();
        fechadesde = new com.toedter.calendar.JDateChooser();
        btnHome1 = new javax.swing.JButton();
        fechahasta = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(java.awt.Color.white);

        btnBuscar.setText("BUSCAR");
        btnBuscar.setName("bntGuardar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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

        txtProvincia1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtProvincia1.setText("Fecha desde");

        txtSucursall.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSucursall.setText("Sucursal");

        lblSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblSucursalActionPerformed(evt);
            }
        });

        txtTipoCita.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTipoCita.setText("Tipo de Cita");

        tipocita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Virtual", "Presencial" }));
        tipocita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipocitaActionPerformed(evt);
            }
        });

        txtEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEstado.setText("Estado");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Programada  ", "Realizada", "Cancelada", " " }));
        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });

        txtProvincia2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtProvincia2.setText("Fecha hasta");

        fechadesde.setDateFormatString("dd-MMM-yy");

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

        fechahasta.setDateFormatString("dd-mm-yyyy");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigoCita)
                                    .addComponent(lblCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliente)
                                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtProvincia1)
                                        .addGap(133, 133, 133))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(fechadesde, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProvincia2)
                                    .addComponent(fechahasta, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSucursall)
                                    .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tipocita, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTipoCita))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtEstado))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnHome1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHome1)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCliente)
                                .addComponent(txtProvincia1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fechadesde, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtCodigoCita)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtProvincia2)
                        .addGap(40, 40, 40))
                    .addComponent(fechahasta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTipoCita)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tipocita, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSucursall)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void lblCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCitasActionPerformed
        
       
    }//GEN-LAST:event_lblCitasActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void bntCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCitaActionPerformed
        JFCitas abrir = new JFCitas();
        abrir.setVisible(true);
        this.setVisible(false);        
    }//GEN-LAST:event_bntCitaActionPerformed

    private void btnAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaActionPerformed
        JFAuditoria abrir = new JFAuditoria();
        abrir.setVisible(true);
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

    private void lblSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSucursalActionPerformed

    private void tipocitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipocitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipocitaActionPerformed

    private void cbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoActionPerformed

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
    private javax.swing.JComboBox<String> cbxEstado;
    private com.toedter.calendar.JDateChooser fechadesde;
    private com.toedter.calendar.JDateChooser fechahasta;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblCitas;
    private javax.swing.JTextField lblCliente;
    private javax.swing.JTextField lblSucursal;
    private javax.swing.JTable tblHome;
    private javax.swing.JComboBox<String> tipocita;
    private javax.swing.JLabel txtCliente;
    private javax.swing.JLabel txtCodigoCita;
    private javax.swing.JLabel txtEstado;
    private javax.swing.JLabel txtProvincia1;
    private javax.swing.JLabel txtProvincia2;
    private javax.swing.JLabel txtSucursall;
    private javax.swing.JLabel txtTipoCita;
    // End of variables declaration//GEN-END:variables

    

}
