/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.Form;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import pembeliantiketkereta.Form.NavigatorFormInterface;

/**
 *
 * @author Candi-PC
 */
public class FormUtama extends javax.swing.JFrame {

    /**
     * Creates new form FormUtama
     */
    public FormUtama() {
        initComponents();
        setLocationRelativeTo(null);
        TombolOff();
    }
    
    public void TombolOn(){
        btNew.setEnabled(true);
        btDelete.setEnabled(true);
        btFind.setEnabled(true);
        btSave.setEnabled(true);
    }
    
    public void TombolOff(){
        btNew.setEnabled(false);
        btDelete.setEnabled(false);
        btFind.setEnabled(false);
        btSave.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jToolBar1 = new javax.swing.JToolBar();
        btNew = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btFind = new javax.swing.JButton();
        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        masterUser = new javax.swing.JMenuItem();
        masterKereta = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        bukaBayar = new javax.swing.JMenuItem();
        menuBooking = new javax.swing.JMenu();
        bukaCustomer = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuLaporan = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Utama");

        jToolBar1.setRollover(true);

        btNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/New.png"))); // NOI18N
        btNew.setText("New");
        btNew.setFocusable(false);
        btNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btNew);

        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save Blue 32 n p8.png"))); // NOI18N
        btSave.setText("Save");
        btSave.setFocusable(false);
        btSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btSave);

        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        btDelete.setText("Delete");
        btDelete.setFocusable(false);
        btDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btDelete);

        btFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search 32 n p8.png"))); // NOI18N
        btFind.setText("Find");
        btFind.setFocusable(false);
        btFind.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btFind.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFindActionPerformed(evt);
            }
        });
        jToolBar1.add(btFind);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jMenu3.setText("Master");

        masterUser.setText("Master User");
        masterUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterUserActionPerformed(evt);
            }
        });
        jMenu3.add(masterUser);

        masterKereta.setText("Master Kereta");
        masterKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterKeretaActionPerformed(evt);
            }
        });
        jMenu3.add(masterKereta);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Form Bayar");

        bukaBayar.setText("Buka Form Bayar");
        bukaBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukaBayarActionPerformed(evt);
            }
        });
        jMenu4.add(bukaBayar);

        jMenuBar1.add(jMenu4);

        menuBooking.setText("Form Customer");

        bukaCustomer.setText("Buka Form Customer");
        bukaCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukaCustomerActionPerformed(evt);
            }
        });
        menuBooking.add(bukaCustomer);

        jMenuBar1.add(menuBooking);

        jMenu5.setText("Laporan");

        menuLaporan.setText("Buka Form Laporan");
        menuLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLaporanActionPerformed(evt);
            }
        });
        jMenu5.add(menuLaporan);

        jMenuBar1.add(jMenu5);

        menuLogout.setText("Log Out");
        menuLogout.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuLogoutMenuSelected(evt);
            }
        });
        jMenuBar1.add(menuLogout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(desktopPane)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFindActionPerformed
        nav=(NavigatorFormInterface) desktopPane.getSelectedFrame();
        nav.cari();
        
    }//GEN-LAST:event_btFindActionPerformed

    private void masterKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterKeretaActionPerformed
        FormKereta FK=new FormKereta();
        showForm(FK);
        TombolOff();
        btNew.setEnabled(true);
    }//GEN-LAST:event_masterKeretaActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        nav=(NavigatorFormInterface) desktopPane.getSelectedFrame();
        nav.bersih();
        nav.aktif();
    }//GEN-LAST:event_btNewActionPerformed

    private void bukaCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bukaCustomerActionPerformed
        this.dispose();
        FormCustomer FC=new FormCustomer();
        FC.setLocationRelativeTo(null);
        FC.setVisible(true);
        
    }//GEN-LAST:event_bukaCustomerActionPerformed

    private void bukaBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bukaBayarActionPerformed
        FormBayar FB=new FormBayar();
        showForm(FB);
        TombolOff();
        btNew.setEnabled(true);
    }//GEN-LAST:event_bukaBayarActionPerformed

    private void masterUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterUserActionPerformed
       FormUser FU=new FormUser();
       showForm(FU);
       TombolOn();
    }//GEN-LAST:event_masterUserActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        nav=(NavigatorFormInterface) desktopPane.getSelectedFrame();
        nav.simpan();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        if(JOptionPane.showConfirmDialog(null, "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            nav=(NavigatorFormInterface) desktopPane.getSelectedFrame();
            nav.hapus();
        } 
    }//GEN-LAST:event_btDeleteActionPerformed

    private void menuLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLaporanActionPerformed
        FormLaporan Flap=new FormLaporan();
        showForm(Flap);
        TombolOff();
    }//GEN-LAST:event_menuLaporanActionPerformed

    private void menuLogoutMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuLogoutMenuSelected
        this.dispose();
        FormLogin Flog=new FormLogin();
        Flog.setVisible(true);
    }//GEN-LAST:event_menuLogoutMenuSelected

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
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btFind;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.JMenuItem bukaBayar;
    private javax.swing.JMenuItem bukaCustomer;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem masterKereta;
    private javax.swing.JMenuItem masterUser;
    private javax.swing.JMenu menuBooking;
    private javax.swing.JMenuItem menuLaporan;
    private javax.swing.JMenu menuLogout;
    // End of variables declaration//GEN-END:variables

    NavigatorFormInterface nav;
    private void showForm(Object obj){
        JInternalFrame jf=null;
        jf=(JInternalFrame) obj;
        desktopPane.add(jf);
        jf.setVisible(true);
        try{
            jf.setMaximum(true);
            jf.setSelected(true);
        }catch(java.beans.PropertyVetoException e){}
    }
}
