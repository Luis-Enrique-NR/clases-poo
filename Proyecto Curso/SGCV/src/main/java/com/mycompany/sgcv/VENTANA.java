package com.mycompany.sgcv;

public class VENTANA extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VENTANA.class.getName());
    
    public VENTANA() {
        initComponents();
        this.setLocationRelativeTo(null); // Centrar la ventana en pantalla

        // 1. Asignar las manos (Cursor) a las etiquetas
        txtNUEVOCLIENTE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtSIMULAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtVENCIMIENTOS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtREGISTRARPAGO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtHISTORIAL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCERRARSESION.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // 2. Registrar las pantallas en el CardLayout
        jPanel3.setLayout(new java.awt.CardLayout());
        jPanel3.add(new PanelBienvenida(), "pnlBienvenida");
        jPanel3.add(new PnlNuevoCliente(), "pnlRegistrar");
        jPanel3.add(new PnlCredito(), "pnlCredito");
        jPanel3.add(new PnlVencimientos(), "pnlCronograma");
        jPanel3.add(new PnlRegistrarPago(), "pnlRegistrarPago");
        jPanel3.add(new PnlHistorial(), "pnlHistorial");

        // Mostrar pantalla inicial
        cambiarPantalla("pnlBienvenida");
    }
    
    private void cambiarPantalla(String nombrePanel) {
        java.awt.CardLayout card = (java.awt.CardLayout) jPanel3.getLayout();
        card.show(jPanel3, nombrePanel);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtNUEVOCLIENTE = new javax.swing.JLabel();
        txtSIMULAR = new javax.swing.JLabel();
        txtVENCIMIENTOS = new javax.swing.JLabel();
        txtREGISTRARPAGO = new javax.swing.JLabel();
        txtHISTORIAL = new javax.swing.JLabel();
        txtCERRARSESION = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1157, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        txtNUEVOCLIENTE.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtNUEVOCLIENTE.setForeground(new java.awt.Color(255, 255, 255));
        txtNUEVOCLIENTE.setText("Nuevo cliente");
        txtNUEVOCLIENTE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNUEVOCLIENTEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtNUEVOCLIENTEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtNUEVOCLIENTEMouseExited(evt);
            }
        });

        txtSIMULAR.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtSIMULAR.setForeground(new java.awt.Color(255, 255, 255));
        txtSIMULAR.setText("Simular / Crédito");
        txtSIMULAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSIMULARMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtSIMULARMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtSIMULARMouseExited(evt);
            }
        });

        txtVENCIMIENTOS.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtVENCIMIENTOS.setForeground(new java.awt.Color(255, 255, 255));
        txtVENCIMIENTOS.setText("Próximos vencimientos");
        txtVENCIMIENTOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVENCIMIENTOSMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtVENCIMIENTOSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtVENCIMIENTOSMouseExited(evt);
            }
        });

        txtREGISTRARPAGO.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtREGISTRARPAGO.setForeground(new java.awt.Color(255, 255, 255));
        txtREGISTRARPAGO.setText("Registrar pago");
        txtREGISTRARPAGO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtREGISTRARPAGOMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtREGISTRARPAGOMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtREGISTRARPAGOMouseExited(evt);
            }
        });

        txtHISTORIAL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtHISTORIAL.setForeground(new java.awt.Color(255, 255, 255));
        txtHISTORIAL.setText("Historial de pagos");
        txtHISTORIAL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHISTORIALMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtHISTORIALMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtHISTORIALMouseExited(evt);
            }
        });

        txtCERRARSESION.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtCERRARSESION.setForeground(new java.awt.Color(255, 255, 255));
        txtCERRARSESION.setText("Cerrar sesión");
        txtCERRARSESION.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCERRARSESIONMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCERRARSESIONMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCERRARSESIONMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1157, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txtCERRARSESION)
                .addGap(343, 343, 343)
                .addComponent(txtNUEVOCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSIMULAR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVENCIMIENTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtREGISTRARPAGO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHISTORIAL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNUEVOCLIENTE)
                    .addComponent(txtSIMULAR)
                    .addComponent(txtVENCIMIENTOS)
                    .addComponent(txtREGISTRARPAGO)
                    .addComponent(txtHISTORIAL)
                    .addComponent(txtCERRARSESION))
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNUEVOCLIENTEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNUEVOCLIENTEMouseClicked
        cambiarPantalla("PnlNuevoCliente");
    }//GEN-LAST:event_txtNUEVOCLIENTEMouseClicked

    private void txtSIMULARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSIMULARMouseClicked
        cambiarPantalla("PnlCredito");
    }//GEN-LAST:event_txtSIMULARMouseClicked

    private void txtVENCIMIENTOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVENCIMIENTOSMouseClicked
        cambiarPantalla("PnlVencimientos");
    }//GEN-LAST:event_txtVENCIMIENTOSMouseClicked

    private void txtREGISTRARPAGOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtREGISTRARPAGOMouseClicked
        cambiarPantalla("PnlRegistrarPago");
    }//GEN-LAST:event_txtREGISTRARPAGOMouseClicked

    private void txtHISTORIALMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHISTORIALMouseClicked
        cambiarPantalla("PnlHistorial");
    }//GEN-LAST:event_txtHISTORIALMouseClicked

    private void txtNUEVOCLIENTEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNUEVOCLIENTEMouseEntered
        txtNUEVOCLIENTE.setForeground(new java.awt.Color(255, 235, 120));
    }//GEN-LAST:event_txtNUEVOCLIENTEMouseEntered

    private void txtNUEVOCLIENTEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNUEVOCLIENTEMouseExited
        txtNUEVOCLIENTE.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_txtNUEVOCLIENTEMouseExited

    private void txtSIMULARMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSIMULARMouseEntered
        txtSIMULAR.setForeground(new java.awt.Color(255, 235, 120));
    }//GEN-LAST:event_txtSIMULARMouseEntered

    private void txtSIMULARMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSIMULARMouseExited
        txtSIMULAR.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_txtSIMULARMouseExited

    private void txtVENCIMIENTOSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVENCIMIENTOSMouseEntered
        txtVENCIMIENTOS.setForeground(new java.awt.Color(255, 235, 120));
    }//GEN-LAST:event_txtVENCIMIENTOSMouseEntered

    private void txtVENCIMIENTOSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVENCIMIENTOSMouseExited
        txtVENCIMIENTOS.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_txtVENCIMIENTOSMouseExited

    private void txtREGISTRARPAGOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtREGISTRARPAGOMouseEntered
        txtREGISTRARPAGO.setForeground(new java.awt.Color(255, 235, 120));
    }//GEN-LAST:event_txtREGISTRARPAGOMouseEntered

    private void txtREGISTRARPAGOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtREGISTRARPAGOMouseExited
        txtREGISTRARPAGO.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_txtREGISTRARPAGOMouseExited

    private void txtHISTORIALMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHISTORIALMouseEntered
        txtHISTORIAL.setForeground(new java.awt.Color(255, 235, 120));
    }//GEN-LAST:event_txtHISTORIALMouseEntered

    private void txtHISTORIALMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHISTORIALMouseExited
        txtHISTORIAL.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_txtHISTORIALMouseExited

    private void txtCERRARSESIONMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCERRARSESIONMouseClicked
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea cerrar la sesión?",
            "Confirmación",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            InicioSesion login = new InicioSesion();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
            this.dispose(); // Descarga de memoria la ventana principal
        }
    }//GEN-LAST:event_txtCERRARSESIONMouseClicked

    private void txtCERRARSESIONMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCERRARSESIONMouseEntered
        txtCERRARSESION.setForeground(new java.awt.Color(255, 120, 120));
    }//GEN-LAST:event_txtCERRARSESIONMouseEntered

    private void txtCERRARSESIONMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCERRARSESIONMouseExited
        txtCERRARSESION.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_txtCERRARSESIONMouseExited

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VENTANA().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel txtCERRARSESION;
    private javax.swing.JLabel txtHISTORIAL;
    private javax.swing.JLabel txtNUEVOCLIENTE;
    private javax.swing.JLabel txtREGISTRARPAGO;
    private javax.swing.JLabel txtSIMULAR;
    private javax.swing.JLabel txtVENCIMIENTOS;
    // End of variables declaration//GEN-END:variables
}
