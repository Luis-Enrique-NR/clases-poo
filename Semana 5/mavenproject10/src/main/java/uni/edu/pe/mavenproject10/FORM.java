
package uni.edu.pe.mavenproject10;

import java.awt.Color;
import javax.swing.ImageIcon;


public class FORM extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FORM.class.getName());

    private javax.swing.ImageIcon icon(String name) {
        
        
        java.net.URL url = getClass().getResource("/image/" + name);
        if (url == null) {
            logger.warning("Image resource not found: /image/" + name);
            return null;
        }
        return new javax.swing.ImageIcon(url);
    }

  
    public FORM() {
        initComponents();
        
        jPanel3.add(imgLOGO, "HOME");
        
        jPanel3.add(new ClientePanel(), "CLIENTES");
        
        setLocationRelativeTo(null);
        //LoadImages();
    }

    
    private void mostrar(String nombreCard) {
        java.awt.CardLayout layout = (java.awt.CardLayout) jPanel3.getLayout();
        layout.show(jPanel3, nombreCard);
    }
    
    
    
 public void LoadImages(){

/*imgCLIENTE.setLocation(H, V);
imgSOPORTE.setLocation(H, V+Z);
imgSEDES.setLocation(H, V+2*Z);
imgEMPLEADOS.setLocation(H, V+3*Z);
imgADMINISTRACION.setLocation(H, V+4*Z);
lblCLIENTE.setLocation(H+E, V);
lblSOPORTE.setLocation(H+E, V+Z);
lblSEDES.setLocation(H+E, V+2*Z);
lblEMPLEADOS.setLocation(H+E, V+3*Z);
lblUSUARIOS.setLocation(H+E, V+4*Z);*/
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PANEL1 = new javax.swing.JPanel();
        imgCLIENTE = new javax.swing.JLabel();
        imgEMPLEADOS = new javax.swing.JLabel();
        imgSEDES = new javax.swing.JLabel();
        imgADMINISTRACION = new javax.swing.JLabel();
        lblCLIENTE = new javax.swing.JLabel();
        lblSOPORTE = new javax.swing.JLabel();
        lblADMINISTRACION = new javax.swing.JLabel();
        lblSEDES = new javax.swing.JLabel();
        lblEMPLEADOS = new javax.swing.JLabel();
        imgSOPORTE = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        imgLOGO = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1213, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        PANEL1.setBackground(new java.awt.Color(255, 255, 255));

        imgCLIENTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/usuario.png"))); // NOI18N
        imgCLIENTE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                imgCLIENTEMouseMoved(evt);
            }
        });
        imgCLIENTE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgCLIENTEMouseExited(evt);
            }
        });

        imgEMPLEADOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/empleados.png"))); // NOI18N
        imgEMPLEADOS.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                imgEMPLEADOSMouseMoved(evt);
            }
        });
        imgEMPLEADOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgEMPLEADOSMouseExited(evt);
            }
        });

        imgSEDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sedes.png"))); // NOI18N
        imgSEDES.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                imgSEDESMouseMoved(evt);
            }
        });
        imgSEDES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgSEDESMouseExited(evt);
            }
        });

        imgADMINISTRACION.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/administracion.jpg"))); // NOI18N
        imgADMINISTRACION.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                imgADMINISTRACIONMouseMoved(evt);
            }
        });
        imgADMINISTRACION.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgADMINISTRACIONMouseExited(evt);
            }
        });

        lblCLIENTE.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        lblCLIENTE.setText("Registro de Clientes");
        lblCLIENTE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCLIENTEMouseMoved(evt);
            }
        });
        lblCLIENTE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCLIENTEMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCLIENTEMouseExited(evt);
            }
        });

        lblSOPORTE.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        lblSOPORTE.setText("Soporte y asistencia tecnica");
        lblSOPORTE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblSOPORTEMouseMoved(evt);
            }
        });
        lblSOPORTE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSOPORTEMouseExited(evt);
            }
        });

        lblADMINISTRACION.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        lblADMINISTRACION.setText("Gestion de Usuarios");
        lblADMINISTRACION.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblADMINISTRACIONMouseMoved(evt);
            }
        });
        lblADMINISTRACION.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblADMINISTRACIONMouseExited(evt);
            }
        });

        lblSEDES.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        lblSEDES.setText("Administracion de sedes");
        lblSEDES.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblSEDESMouseMoved(evt);
            }
        });
        lblSEDES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSEDESMouseExited(evt);
            }
        });

        lblEMPLEADOS.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        lblEMPLEADOS.setText("Registro de empleados");
        lblEMPLEADOS.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblEMPLEADOSMouseMoved(evt);
            }
        });
        lblEMPLEADOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEMPLEADOSMouseExited(evt);
            }
        });

        imgSOPORTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/soporte.png"))); // NOI18N
        imgSOPORTE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                imgSOPORTEMouseMoved(evt);
            }
        });
        imgSOPORTE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgSOPORTEMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PANEL1Layout = new javax.swing.GroupLayout(PANEL1);
        PANEL1.setLayout(PANEL1Layout);
        PANEL1Layout.setHorizontalGroup(
            PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANEL1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PANEL1Layout.createSequentialGroup()
                        .addGroup(PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgEMPLEADOS, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgSEDES, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgSOPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PANEL1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addGroup(PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSOPORTE)
                                    .addComponent(lblSEDES, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCLIENTE))
                                .addGap(16, 16, 16))
                            .addGroup(PANEL1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblEMPLEADOS)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addComponent(imgADMINISTRACION, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblADMINISTRACION)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PANEL1Layout.setVerticalGroup(
            PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANEL1Layout.createSequentialGroup()
                .addGroup(PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(imgCLIENTE))
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblCLIENTE)))
                .addGroup(PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblSOPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(lblSEDES))
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(imgSOPORTE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imgSEDES)))
                .addGroup(PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(imgEMPLEADOS))
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lblEMPLEADOS)))
                .addGroup(PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(imgADMINISTRACION))
                    .addGroup(PANEL1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(lblADMINISTRACION)))
                .addContainerGap(390, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.CardLayout());

        imgLOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Img0.png"))); // NOI18N
        imgLOGO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgLOGOMouseExited(evt);
            }
        });
        jPanel3.add(imgLOGO, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PANEL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PANEL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Configurar(boolean Sw, int nn) {
        if(nn==1) {
           lblCLIENTE.setForeground((Sw==true?Color.RED:Color.BLACK));
           imgCLIENTE.setIcon(icon(Sw==true?"usuario2.png":"usuario.png"));
           imgLOGO.setIcon(icon(Sw==true?"Img1.png":"Img0.png"));
           
        }
        else if(nn==2) {
           lblSOPORTE.setForeground((Sw==true?Color.RED:Color.BLACK));
           imgSOPORTE.setIcon(icon(Sw==true?"soporte2.png":"soporte.png"));
           imgLOGO.setIcon(icon(Sw==true?"Img2.png":"Img0.png"));
        }
        else if(nn==3) {
           lblSEDES.setForeground((Sw==true?Color.RED:Color.BLACK));
           imgSEDES.setIcon(icon(Sw==true?"sedes2.png":"sedes.png"));
           imgLOGO.setIcon(icon(Sw==true?"Img3.png":"Img0.png"));
        }
        else if(nn==4) {
           lblEMPLEADOS.setForeground((Sw==true?Color.RED:Color.BLACK));
           imgEMPLEADOS.setIcon(icon(Sw==true?"empleados2.png":"empleados.png"));
           imgLOGO.setIcon(icon(Sw==true?"Img4.png":"Img0.png"));
        }
        else if(nn==5) {
           lblADMINISTRACION.setForeground((Sw==true?Color.RED:Color.BLACK));
           imgADMINISTRACION.setIcon(icon(Sw==true?"administracion2.jpg":"administracion.jpg"));
           imgLOGO.setIcon(icon(Sw==true?"Img5.png":"Img0.png"));
        }
        else {
        }
    }
    
    
    private void lblCLIENTEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCLIENTEMouseMoved
       Configurar(true,1);
    }//GEN-LAST:event_lblCLIENTEMouseMoved

    private void imgCLIENTEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCLIENTEMouseMoved
       Configurar(true,1);
    }//GEN-LAST:event_imgCLIENTEMouseMoved

    private void imgCLIENTEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCLIENTEMouseExited
        Configurar(false,1);    
    }//GEN-LAST:event_imgCLIENTEMouseExited

    private void imgSOPORTEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSOPORTEMouseMoved
        Configurar(true,2);

    }//GEN-LAST:event_imgSOPORTEMouseMoved

    private void imgSEDESMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSEDESMouseMoved
        Configurar(true,3);
    }//GEN-LAST:event_imgSEDESMouseMoved

    private void imgEMPLEADOSMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgEMPLEADOSMouseMoved
        Configurar(true,4);
    }//GEN-LAST:event_imgEMPLEADOSMouseMoved

    private void imgADMINISTRACIONMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgADMINISTRACIONMouseMoved
        Configurar(true,5);
    }//GEN-LAST:event_imgADMINISTRACIONMouseMoved

    private void imgSOPORTEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSOPORTEMouseExited
       Configurar(false,2);
    }//GEN-LAST:event_imgSOPORTEMouseExited

    private void imgSEDESMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSEDESMouseExited
       Configurar(false,3);
    }//GEN-LAST:event_imgSEDESMouseExited

    private void imgEMPLEADOSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgEMPLEADOSMouseExited
        Configurar(false,4);
    }//GEN-LAST:event_imgEMPLEADOSMouseExited

    private void imgADMINISTRACIONMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgADMINISTRACIONMouseExited
        Configurar(false,5);
    }//GEN-LAST:event_imgADMINISTRACIONMouseExited

    private void lblSOPORTEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSOPORTEMouseMoved
         Configurar(true,2);
    }//GEN-LAST:event_lblSOPORTEMouseMoved

    private void lblSEDESMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSEDESMouseMoved
        Configurar(true,3);
    }//GEN-LAST:event_lblSEDESMouseMoved

    private void lblEMPLEADOSMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEMPLEADOSMouseMoved
        Configurar(true,4);
    }//GEN-LAST:event_lblEMPLEADOSMouseMoved

    private void lblADMINISTRACIONMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblADMINISTRACIONMouseMoved
        Configurar(true,5);
    }//GEN-LAST:event_lblADMINISTRACIONMouseMoved

    private void lblSEDESMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSEDESMouseExited
        Configurar(false,3);
    }//GEN-LAST:event_lblSEDESMouseExited

    private void lblCLIENTEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCLIENTEMouseExited
       Configurar(false,1);
    }//GEN-LAST:event_lblCLIENTEMouseExited

    private void lblSOPORTEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSOPORTEMouseExited
        Configurar(false,2);
    }//GEN-LAST:event_lblSOPORTEMouseExited

    private void lblEMPLEADOSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEMPLEADOSMouseExited
        Configurar(false,4);
    }//GEN-LAST:event_lblEMPLEADOSMouseExited

    private void lblADMINISTRACIONMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblADMINISTRACIONMouseExited
        Configurar(false,5);
    }//GEN-LAST:event_lblADMINISTRACIONMouseExited

    private void imgLOGOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgLOGOMouseExited

    }//GEN-LAST:event_imgLOGOMouseExited

    private void lblCLIENTEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCLIENTEMouseClicked
        mostrar("CLIENTES");
    }//GEN-LAST:event_lblCLIENTEMouseClicked

 
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
        java.awt.EventQueue.invokeLater(() -> new FORM().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PANEL1;
    private javax.swing.JLabel imgADMINISTRACION;
    private javax.swing.JLabel imgCLIENTE;
    private javax.swing.JLabel imgEMPLEADOS;
    private javax.swing.JLabel imgLOGO;
    private javax.swing.JLabel imgSEDES;
    private javax.swing.JLabel imgSOPORTE;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblADMINISTRACION;
    private javax.swing.JLabel lblCLIENTE;
    private javax.swing.JLabel lblEMPLEADOS;
    private javax.swing.JLabel lblSEDES;
    private javax.swing.JLabel lblSOPORTE;
    // End of variables declaration//GEN-END:variables
}
