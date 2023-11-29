/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.AdminController;
import controller.TransactionController;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import model.AdminModel;
import model.TransactionModel;
import service.AdminService;

/**
 *
 * @author HP
 */
public class NasabahPage_Transfer extends javax.swing.JFrame {
    TransactionModel TM = new TransactionModel();
    TransactionController TC = new TransactionController();
    AdminModel AM = new AdminModel();
    AdminService AS = new AdminService();
    AdminController AC = new AdminController(AS);
    private String notif, notif1;
    NumberFormat formatRp = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
    
    /**
     * Creates new form NasabahPage_Transfer
     */
    public NasabahPage_Transfer() {
        if ("Active".equals(TM.getStatusLogin())) {
            initComponents();
            display();
        } else {
            dispose();
            LoginPage LP = new LoginPage();
            LP.setVisible(true);
        }
    }
    
    public void display() {
        lbl_noRek.setText(TM.getNoRek());
        lbl_saldo.setText(formatRp.format(TM.getSaldo()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tf_destinationAccount = new javax.swing.JTextField();
        tf_nom = new javax.swing.JTextField();
        lbl_noRek = new javax.swing.JLabel();
        lbl_saldo = new javax.swing.JLabel();
        btn_transfer = new javax.swing.JButton();
        lbl_exit = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tf_destinationAccount.setBackground(new java.awt.Color(171, 237, 216));
        tf_destinationAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_destinationAccountActionPerformed(evt);
            }
        });
        getContentPane().add(tf_destinationAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 253, 150, 23));

        tf_nom.setBackground(new java.awt.Color(171, 237, 216));
        getContentPane().add(tf_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 285, 150, 23));

        lbl_noRek.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_noRek.setForeground(new java.awt.Color(255, 255, 255));
        lbl_noRek.setText("#################");
        getContentPane().add(lbl_noRek, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 370, -1, -1));

        lbl_saldo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_saldo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_saldo.setText("#################");
        getContentPane().add(lbl_saldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 390, -1, -1));

        btn_transfer.setBackground(new java.awt.Color(72, 70, 109));
        btn_transfer.setForeground(new java.awt.Color(171, 237, 216));
        btn_transfer.setText("TRANSFER");
        btn_transfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transferActionPerformed(evt);
            }
        });
        getContentPane().add(btn_transfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 428, 100, 27));

        lbl_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_exitMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 24, 25, 25));

        lbl_background.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Documents\\SystemBank-StructureData\\images\\background\\Nasabah Page - Transfer.png")); // NOI18N
        lbl_background.setText("jLabel1");
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tf_destinationAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_destinationAccountActionPerformed
        if (tf_destinationAccount.getText().trim().equals(TM.getNoRek())) {
            JOptionPane.showMessageDialog(null, "Maaf Tidak Bisa Transfer ke Rekening Sendiri");
            tf_nom.setEditable(false);
        } else {
            String namaLengkap = null;
            AC.viewAllNasabah();
            ArrayList<String[]> dataNasabah = AM.getDataNasabah();
            for (String[] rowData : dataNasabah) {
                if (rowData[0].equals(tf_destinationAccount.getText())) {
                    namaLengkap = rowData[1];
                    break;
                }
            }
            if (namaLengkap == null) {
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
                tf_nom.setEditable(false);
                tf_destinationAccount.requestFocus();
            } else {
                tf_nom.setEditable(true);
                int pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda transfer ke atas nama\n" + namaLengkap, "Trasnfer", JOptionPane.YES_NO_OPTION);
                if (pilihan == JOptionPane.YES_OPTION) {
                    tf_nom.requestFocus();
                } else {
                    tf_destinationAccount.setText("");
                }
            }
        }
    }//GEN-LAST:event_tf_destinationAccountActionPerformed

    private void btn_transferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transferActionPerformed
        if (tf_destinationAccount.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, No.Rekening Tujuan belum diisi !");
            tf_destinationAccount.requestFocus();
        } else if (tf_nom.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Nominal belum diisi !");
            tf_nom.requestFocus();
        } else if (Double.parseDouble(tf_nom.getText().trim().replace(".", "")) < 10000) {
            JOptionPane.showMessageDialog(null, "Maaf, Minimal Transfer Rp10.000");
            tf_nom.requestFocus();
        } else if (tf_destinationAccount.getText().trim().equals(TM.getNoRek())) {
            JOptionPane.showMessageDialog(null, "Maaf Tidak Bisa Transfer ke Rekening Sendiri");
            tf_destinationAccount.requestFocus();
        } else {
            try {
                String nom = tf_nom.getText().replace(".", "");
                notif = TC.minusSaldo(TM.getNoRek(), nom, "Transfer Keluar", ("ke " + tf_destinationAccount.getText()));
                notif1 = TC.plusSaldo(tf_destinationAccount.getText(), nom, "Transfer Masuk", "dari " + TM.getNoRek());
                JOptionPane.showMessageDialog(null, notif, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                TC.setSaldoNew(TM.getNoRek());
                dispose();
                NasabahPage_LandingPage LP = new NasabahPage_LandingPage();
                LP.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Anda gagal transfer", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_transferActionPerformed

    private void lbl_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_exitMouseClicked
        dispose();
        NasabahPage_LandingPage LP = new NasabahPage_LandingPage();
        LP.setVisible(true);
    }//GEN-LAST:event_lbl_exitMouseClicked

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
            java.util.logging.Logger.getLogger(NasabahPage_Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NasabahPage_Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NasabahPage_Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NasabahPage_Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NasabahPage_Transfer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_transfer;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_exit;
    private javax.swing.JLabel lbl_noRek;
    private javax.swing.JLabel lbl_saldo;
    private javax.swing.JTextField tf_destinationAccount;
    private javax.swing.JTextField tf_nom;
    // End of variables declaration//GEN-END:variables
}