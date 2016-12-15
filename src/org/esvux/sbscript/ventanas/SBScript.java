package org.esvux.sbscript.ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.Interprete;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author esvux
 */
public class SBScript extends javax.swing.JFrame {

    private RSyntaxTextArea rsta = new RSyntaxTextArea(20, 60);
    
    public SBScript() {
        initComponents();
        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/SBScript", "org.esvux.sbscript.ventanas.SBScriptSyntax");
        rsta.setSyntaxEditingStyle("text/SBScript");
        rsta.setCodeFoldingEnabled(true);
        rsta.setCurrentLineHighlightColor(new Color(227, 242, 253, 200));
        rsta.setFadeCurrentLineHighlight(true);
        rsta.setBorder(BorderFactory.createEmptyBorder());
        rsta.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextEntradaKeyReleased(evt);
            }
        });
        RTextScrollPane rtsp = new RTextScrollPane(rsta);
        rtsp.setViewportBorder(BorderFactory.createEmptyBorder());
        jPanelPrincipal.add(rtsp);

        SyntaxScheme scheme = rsta.getSyntaxScheme();
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.blue;
        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.orange;
        scheme.getStyle(Token.IDENTIFIER).foreground = Color.green;
        scheme.getStyle(Token.DATA_TYPE).foreground = Color.MAGENTA;
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jTabInformacion = new javax.swing.JTabbedPane();
        jPanelSalidas = new javax.swing.JPanel();
        jButtonCompilar = new javax.swing.JButton();
        jButtonEjemplo1 = new javax.swing.JButton();
        jButtonEjemplo2 = new javax.swing.JButton();
        jButtonEjemplo3 = new javax.swing.JButton();
        jScrollPaneSalida = new javax.swing.JScrollPane();
        jTextAreaSalida = new javax.swing.JTextArea();
        jLabelUbicacion = new javax.swing.JLabel();
        jScrollPaneErrores = new javax.swing.JScrollPane();
        jListErrores = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jTabInformacion.setBackground(java.awt.Color.white);

        jPanelSalidas.setBackground(java.awt.Color.white);

        jButtonCompilar.setText("Ejecutar");
        jButtonCompilar.setToolTipText("Para ejecutar presione F5");
        jButtonCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompilarActionPerformed(evt);
            }
        });

        jButtonEjemplo1.setText("Ejemplo 1");
        jButtonEjemplo1.setToolTipText("Ejemplo sencillo");
        jButtonEjemplo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjemplo1ActionPerformed(evt);
            }
        });

        jButtonEjemplo2.setText("Ejemplo 2");
        jButtonEjemplo2.setToolTipText("Ejemplo con llamadas a metodos");
        jButtonEjemplo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjemplo2ActionPerformed(evt);
            }
        });

        jButtonEjemplo3.setText("Ejemplo 3");
        jButtonEjemplo3.setToolTipText("Ejemplo con llamadas recursivas");
        jButtonEjemplo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjemplo3ActionPerformed(evt);
            }
        });

        jTextAreaSalida.setColumns(20);
        jTextAreaSalida.setRows(5);
        jTextAreaSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaSalidaKeyReleased(evt);
            }
        });
        jScrollPaneSalida.setViewportView(jTextAreaSalida);

        jLabelUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelSalidasLayout = new javax.swing.GroupLayout(jPanelSalidas);
        jPanelSalidas.setLayout(jPanelSalidasLayout);
        jPanelSalidasLayout.setHorizontalGroup(
            jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneSalida)
                    .addGroup(jPanelSalidasLayout.createSequentialGroup()
                        .addComponent(jButtonCompilar, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEjemplo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEjemplo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEjemplo3)))
                .addContainerGap())
        );
        jPanelSalidasLayout.setVerticalGroup(
            jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCompilar)
                    .addComponent(jButtonEjemplo1)
                    .addComponent(jButtonEjemplo2)
                    .addComponent(jButtonEjemplo3)
                    .addComponent(jLabelUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabInformacion.addTab("   Salida   ", jPanelSalidas);

        jListErrores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "No existen errores en el programa." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneErrores.setViewportView(jListErrores);

        jTabInformacion.addTab("   Errores   ", jScrollPaneErrores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabInformacion)
                .addContainerGap())
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompilarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCompilarActionPerformed

    private void jButtonEjemplo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjemplo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEjemplo1ActionPerformed

    private void jButtonEjemplo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjemplo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEjemplo2ActionPerformed

    private void jButtonEjemplo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjemplo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEjemplo3ActionPerformed

    private void jTextAreaSalidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaSalidaKeyReleased
        
    }//GEN-LAST:event_jTextAreaSalidaKeyReleased

    private void jTextEntradaKeyReleased(java.awt.event.KeyEvent evt) {                                            
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            Interprete i = new Interprete(rsta.getText());
            i.analizar();
            i.ejecutar();
            mostrarSalida(i.getSalida());
            mostrarErrores();
        }
        try {
            int caretPos = rsta.getCaretPosition();
            int offset = Utilities.getRowStart(rsta, caretPos);
            int colNum = caretPos - offset + 1;
            jLabelUbicacion.setText("Columna: " + colNum);
        } catch (BadLocationException ex) {
            System.err.println(ex.getMessage());
        }        
    }                                           
    
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SBScript.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SBScript sbs = new SBScript();
                sbs.getContentPane().setBackground(Color.white);
                sbs.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCompilar;
    private javax.swing.JButton jButtonEjemplo1;
    private javax.swing.JButton jButtonEjemplo2;
    private javax.swing.JButton jButtonEjemplo3;
    private javax.swing.JLabel jLabelUbicacion;
    private javax.swing.JList<String> jListErrores;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelSalidas;
    private javax.swing.JScrollPane jScrollPaneErrores;
    private javax.swing.JScrollPane jScrollPaneSalida;
    private javax.swing.JTabbedPane jTabInformacion;
    private javax.swing.JTextArea jTextAreaSalida;
    // End of variables declaration//GEN-END:variables

    private void mostrarErrores() {
        Errores errs = Errores.getInstance();        
        int cuentaErrores = errs.cuentaErrores();
        DefaultListModel<String> modelo = new DefaultListModel<>();        
        if (cuentaErrores > 0) {
            JOptionPane.showMessageDialog(this, "Existen algunos errores en la entrada de SBScript.", "SBSript by Esvux", JOptionPane.ERROR_MESSAGE);            
            for(String err : errs.getReporteErrores())
                modelo.addElement(err);
            jTabInformacion.setSelectedIndex(1);
        } else {
            modelo.addElement("No existen errores en el programa.");
            jTabInformacion.setSelectedIndex(0);
        }
        jListErrores.setModel(modelo);
    }

    private void mostrarSalida(String salida) {
        jTextAreaSalida.setText(salida);
    }



}
