package org.esvux.sbscript.ventanas;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
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

    public SBScript() {
        initComponents();
        configurarEditor();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelSalidas = new javax.swing.JPanel();
        jButtonCompilar = new javax.swing.JButton();
        jLabelUbicacion = new javax.swing.JLabel();
        jButtonEjemplo1 = new javax.swing.JButton();
        jButtonEjemplo2 = new javax.swing.JButton();
        jButtonEjemplo3 = new javax.swing.JButton();
        jScrollPaneErrores = new javax.swing.JScrollPane();
        jListErrores = new javax.swing.JList<>();
        jScrollPaneSalida = new javax.swing.JScrollPane();
        jTextAreaSalida = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SBScript - By Esvux");
        setBackground(java.awt.Color.white);

        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelSalidas.setBackground(java.awt.Color.white);

        jButtonCompilar.setText("Ejecutar");
        jButtonCompilar.setToolTipText("Para ejecutar presione F5");
        jButtonCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompilarActionPerformed(evt);
            }
        });

        jLabelUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUbicacion.setText("Columna: 1");

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

        javax.swing.GroupLayout jPanelSalidasLayout = new javax.swing.GroupLayout(jPanelSalidas);
        jPanelSalidas.setLayout(jPanelSalidasLayout);
        jPanelSalidasLayout.setHorizontalGroup(
            jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCompilar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEjemplo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEjemplo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEjemplo3)
                .addContainerGap())
        );
        jPanelSalidasLayout.setVerticalGroup(
            jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonEjemplo1)
                        .addComponent(jButtonEjemplo2)
                        .addComponent(jButtonEjemplo3))
                    .addComponent(jButtonCompilar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jListErrores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "No existen errores en el programa." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneErrores.setViewportView(jListErrores);

        jTextAreaSalida.setColumns(20);
        jTextAreaSalida.setRows(5);
        jScrollPaneSalida.setViewportView(jTextAreaSalida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelSalidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPaneSalida)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneErrores)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompilarActionPerformed
        ejecutar();
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

    private void jTextEntradaKeyReleased(java.awt.event.KeyEvent evt) {
        actualizarNumeroColumna();
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            ejecutar();
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
            @Override
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
    private javax.swing.JTextArea jTextAreaSalida;
    // End of variables declaration//GEN-END:variables
    private RSyntaxTextArea rsta;

    private void ejecutar() {
        Interprete i = new Interprete(rsta.getText());
        i.analizar();
        i.ejecutar();
        mostrarSalida(i.getSalida());
        mostrarErrores();
    }

    private void mostrarErrores() {
        Errores errs = Errores.getInstance();
        int cuentaErrores = errs.cuentaErrores();
        DefaultListModel<String> modelo = new DefaultListModel<>();
        if (cuentaErrores > 0) {
            JOptionPane.showMessageDialog(this, "Existen algunos errores en la entrada de SBScript.", "SBSript by Esvux", JOptionPane.ERROR_MESSAGE);
            for (String err : errs.getReporteErrores()) {
                modelo.addElement(err);
            }
        } else {
            modelo.addElement("No existen errores en el programa.");
        }
        jListErrores.setModel(modelo);
    }

    private void mostrarSalida(String salida) {
        jTextAreaSalida.setText(salida);
    }

    private void configurarEditor() {
        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/SBScript", "org.esvux.sbscript.ventanas.SBScriptSyntax");
        rsta = new RSyntaxTextArea(20, 60);
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
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.decode("#0d47a1");
        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.decode("#e65100");
        scheme.getStyle(Token.IDENTIFIER).foreground = Color.decode("#1b5e20");
        scheme.getStyle(Token.DATA_TYPE).foreground = Color.decode("#42a5f5");
        scheme.getStyle(Token.COMMENT_EOL).foreground = Color.decode("#827717");
        scheme.getStyle(Token.COMMENT_MULTILINE).foreground = Color.decode("#827717");
        scheme.getStyle(Token.SEPARATOR).foreground = Color.black;
    }

    private void actualizarNumeroColumna() {
        try {
            int caretPos = rsta.getCaretPosition();
            int offset = Utilities.getRowStart(rsta, caretPos);
            int colNum = caretPos - offset + 1;
            jLabelUbicacion.setText("Columna: " + colNum);
        } catch (BadLocationException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
