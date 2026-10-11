package com.mycompany.sgcv;

import javax.swing.JOptionPane;

public class InicioSesion extends javax.swing.JFrame {
    
    private final GestionUsuarios gestionUsuarios = new GestionUsuarios();
    private final GestionArchivos gestionArchivos = new GestionArchivos();

    public InicioSesion() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPSW = new javax.swing.JPasswordField();
        btnACCEDER = new javax.swing.JButton();
        btnCANCELAR = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONTROL DE ACCESO");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Usuario");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Contraseña");

        txtPSW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPSWActionPerformed(evt);
            }
        });

        btnACCEDER.setBackground(new java.awt.Color(113, 238, 113));
        btnACCEDER.setText("ACCEDER");
        btnACCEDER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACCEDERActionPerformed(evt);
            }
        });

        btnCANCELAR.setBackground(new java.awt.Color(249, 94, 94));
        btnCANCELAR.setText("CANCELAR");
        btnCANCELAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELARActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\frank\\Documents\\NetBeansProjects\\SGCV\\Candado2.png")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario)
                            .addComponent(txtPSW, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnACCEDER)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCANCELAR)
                        .addGap(16, 16, 16)))
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPSW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnACCEDER)
                            .addComponent(btnCANCELAR))
                        .addGap(65, 65, 65))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnACCEDERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACCEDERActionPerformed
        String usr = txtUsuario.getText().trim();
        String psw = new String(txtPSW.getPassword()).trim();

        // 1. Validar que no estén vacíos
        if (usr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ERROR: No ha ingresado el usuario");
            txtUsuario.requestFocus();
            return;
        }

        if (psw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ERROR: No ha ingresado la contraseña");
            txtPSW.requestFocus();
            return;
        }

        int intentos = gestionArchivos.leerIntentos();
        if(intentos >= 3){
            JOptionPane.showMessageDialog(this, "Acceso denegado. Este equipo ha superado el límite de 3 intentos fallidos.");
            btnACCEDER.setEnabled(false);
            return;
        }
        
        // 3. Buscar al usuario en el archivo USUARIOS.txt
        String linea = gestionUsuarios.obtenerLineaUsuario(usr);

        if (linea == null) {
            intentos++;
            gestionArchivos.guardarIntentos(intentos);
            
            if(intentos >= 3){
                String host = GetHostName_GetAddressIP.GetHostName();
                String ip = GetHostName_GetAddressIP.GetAddressIP();
                gestionArchivos.registrarHost(host, ip);
                JOptionPane.showMessageDialog(this, "Ha superado los 3 intentos fallidos.\nSu dispositivo ha sido registrado:\nHost: " + host + "\nIP: " + ip);
                btnACCEDER.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "ERROR: El usuario no existe. Intento " + intentos + " de 3.");
            }
            return;
        }

        // 3. Extraer contraseña (caracteres 20 a 27) y estado (caracter 29)
        String pswArchivo = linea.substring(20, 28).trim();
        char estado = linea.charAt(29);

        // 4. Validar contraseña
        if (!pswArchivo.equals(psw)) {
            intentos++;
            gestionArchivos.guardarIntentos(intentos);
            if (intentos >= 3) {
                String host = GetHostName_GetAddressIP.GetHostName();
                String ip = GetHostName_GetAddressIP.GetAddressIP();
                gestionArchivos.registrarHost(host, ip);
                JOptionPane.showMessageDialog(this, "Ha superado los 3 intentos fallidos.\nSu dispositivo ha sido registrado:\nHost: " + host + "\nIP: " + ip);
                btnACCEDER.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "ERROR: Contraseña incorrecta. Intento " + intentos + " de 3.");
            }
            return;
        }

        // 5. Validar estado/habilitación ('1' activo, '0' inactivo)
        if (estado != '1') {
            JOptionPane.showMessageDialog(this, "ERROR: El usuario no está activo");
            return;
        }

        // 6. Acceso concedido
        gestionArchivos.guardarIntentos(0);
        JOptionPane.showMessageDialog(this, "Bienvenido al sistema, " + usr);
        VENTANA ventanaPrincipal = new VENTANA();
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);

        // Cerrar la ventana actual de Login
        this.dispose();
    }//GEN-LAST:event_btnACCEDERActionPerformed

    private void btnCANCELARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELARActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCANCELARActionPerformed

    private void txtPSWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPSWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSWActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new InicioSesion().setVisible(true));
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnACCEDER;
    private javax.swing.JButton btnCANCELAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtPSW;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
