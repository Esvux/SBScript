package org.esvux.sbscript.ventanas;

import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.Interprete;

/**
 *
 * @author esvux
 */
public class Inicio extends javax.swing.JFrame {

    public Inicio() {
        initComponents();
        TextLineNumber tln = new TextLineNumber(jTextPane_Programa);
        jScrollPane_Programa.setRowHeaderView(tln);
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
            System.err.println("LookAndFeel no encontrado.");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane_Principal = new javax.swing.JSplitPane();
        jScrollPane_Programa = new javax.swing.JScrollPane();
        jTextPane_Programa = new javax.swing.JTextPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane_Principal.setDividerLocation(500);

        jTextPane_Programa.setFont(new java.awt.Font("Andale Mono", 0, 14)); // NOI18N
        jTextPane_Programa.setText("Principal(){\n\tMostrar(\"Hola\", \"Mundo\");\n}");
        jTextPane_Programa.setCaretPosition(0);
        jTextPane_Programa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextPane_ProgramaKeyReleased(evt);
            }
        });
        jScrollPane_Programa.setViewportView(jTextPane_Programa);

        jSplitPane_Principal.setLeftComponent(jScrollPane_Programa);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(null);
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane1.addTab("   Salida   ", jScrollPane1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Descripción", "Lin : Col"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        jTabbedPane1.addTab("   Errores   ", jScrollPane2);

        jSplitPane_Principal.setRightComponent(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextPane_ProgramaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane_ProgramaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            Interprete i = new Interprete(this.jTextPane_Programa.getText());
            i.analizar();
            mostrarErrores();
        }
    }//GEN-LAST:event_jTextPane_ProgramaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane_Programa;
    private javax.swing.JSplitPane jSplitPane_Principal;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane_Programa;
    // End of variables declaration//GEN-END:variables

    private void mostrarErrores() {
        Errores errs = Errores.getInstance();
        String encabezado[] = {"Descripción", "Ubicación"};
        String data[][] = errs.getReporteErrores();
        DefaultTableModel dtm = new DefaultTableModel(data, encabezado);
        this.jTable1.setModel(dtm);
        if(errs.cuentaErrores()>0)
            jTabbedPane1.setSelectedIndex(1);
    }

}
