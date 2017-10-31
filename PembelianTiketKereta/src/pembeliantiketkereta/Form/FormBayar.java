/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.Form;
import com.sun.glass.events.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pembeliantiketkereta.PersistenceEntity;
import pembeliantiketkereta.controller.DetailController;
import pembeliantiketkereta.data.Penumpang;

/**
 *
 * @author Candi-PC
 */
public class FormBayar extends javax.swing.JInternalFrame  implements NavigatorFormInterface{
    DetailController Dcont=new DetailController(PersistenceEntity.emf);
    Penumpang pp=new Penumpang();
    
    DefaultTableModel tblDet;  //membuat DefaultTableModel untuk table detail
    DefaultTableModel tblPen;  //membuat DefaultTableModel untuk table penumpang
    
    //membuat variabel untuk menyimpan data dari objek array
    String pilih, noTr, kd, nama, ktAsal, stAsal, ktTujuan, stTujuan,
           kelas, subkelas, status, tglBer, berangkat;
    double harga;
    int jumPe;
    double total=harga*jumPe;
     
    /**
     * Creates new form FormBayar
     */
    public FormBayar(){
        initComponents();
        
        //setting nama kolom DefaultTableModel detail
        tblDet = new DefaultTableModel();
        tblDet.addColumn("No. Trans");
        tblDet.addColumn("Nama Kereta");
        tblDet.addColumn("Stasiun Asal");
        tblDet.addColumn("Stasiun Tujuan");
        tblDet.addColumn("Kelas");
        tblDet.addColumn("Jumlah Penumpang");
        tblDet.addColumn("Status");
        
        //setting nama kolom DefaultTableModel penumpang
        tblPen =new DefaultTableModel();
        tblPen.addColumn("No. Identitas");
        tblPen.addColumn("Nama Penumpang");
        
    }
    
    private void tampilTableDetail(){
        //setting data table detail dengan
        //memasukan DefaultTableModel detail ke method showTableDetail
        tableDetail.setModel(Dcont.showTableDetail(tblDet));
    }
    
    private void tampilTablePenumpang(String pil){
        //membuat data penumpang muncul ketika table detail terseleksi
        tablePenumpang.setModel(Dcont.showTablePenumpang(tblPen, pil));
    }
    
    //mengambil data dari database
    private void dataDetail(String pil){
        try{
            Object[] obj= Dcont.textArea(pil); //menampilkan nilai obj array sesuai table detail yang terseleksi
            SimpleDateFormat sdf=new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
            SimpleDateFormat df=new SimpleDateFormat("HH:mm"); 
            noTr = String.valueOf(obj[0]);
            kd = String.valueOf(obj[1]);
            nama = String.valueOf(obj[2]);
            ktAsal = String.valueOf(obj[3]);
            stAsal = String.valueOf(obj[4]);
            ktTujuan = String.valueOf(obj[5]);
            stTujuan = String.valueOf(obj[6]);
            kelas = String.valueOf(obj[7]);
            subkelas = String.valueOf(obj[8]);
            tglBer = sdf.format((obj[9]));
            berangkat = df.format(obj[10]);
            harga = Double.parseDouble(String.valueOf(obj[11]));
            jumPe = Integer.parseInt(String.valueOf(obj[12]));
            status = String.valueOf(obj[13]);
        }catch(Exception ex){}
    }
    
    //membuat teks untuk txtArea
    public void keterangan(){
        total = harga*jumPe;
        String ket=noTr
                + "\n=========="
                + "\nKode kereta : " + kd
                + "\nNama kereta : " + nama
                + "\nStasiun/kota asal : " + stAsal + ", " + ktAsal
                + "\nKelas : " + kelas
                + "\nSubkelas : " + subkelas
                + "\nHarga : " + harga
                + "\n=========="
                + "\nStasiun/kota tujuan : " + stTujuan + ", " + ktTujuan
                + "\nTanggal berangkat : " + tglBer
                + "\nWaktu berangkat : " + berangkat
                + "\n\nJumlah penumpang : " + jumPe
                + "\nTotal harga : " + total
                + "\n\nStatus : " + status;
        txtArea.setText(ket);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDetail = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePenumpang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtUbay = new javax.swing.JTextField();
        txtUkem = new javax.swing.JTextField();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        txtArea.setText("TR-000\n==========\nKode kereta : \nNama kereta : \nStasiun/kota asal : \nKelas : \nSubkelas : \nHarga : \n==========\nStasiun/kota tujuan : \nTanggal berangkat : \nWaktu berangkat : \n\nJumlah penumpang : \nTotal harga : \n\nStatus : ");
        jScrollPane1.setViewportView(txtArea);

        tableDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Transaksi", "Nama Kereta", "Stasiun Asal", "Stasiun Tujuan"
            }
        ));
        tableDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableDetail);

        tablePenumpang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Identitas", "Nama Penumpang"
            }
        ));
        jScrollPane3.setViewportView(tablePenumpang);

        jLabel1.setText("Total harga : ");

        jLabel2.setText("Uang bayar :");

        jLabel3.setText("Uang kembali :");

        txtTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUbay.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtUbay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUbayKeyPressed(evt);
            }
        });

        txtUkem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUbay, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUkem, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtUbay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtUkem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailMouseClicked
        //mengambil data dari table detail yang terseleksi
        int index=tableDetail.getSelectedRow(); //mengambil nilai int baris yang terseleksi
        String pil = (String) tableDetail.getValueAt(index, 0); //mengambil string data dari kolom no transaksi
        dataDetail(pil); //method untuk memproses string yang terseleksi untuk dicari datanya
        keterangan(); // method untuk menampilkan data yang terseleksi
        
        //tampil table penumpang
        tampilTablePenumpang(pil); //menampilkan table penumpang sesuai transaksi
        txtTotal.setText(String.valueOf(total));
        txtUbay.requestFocus();
    }//GEN-LAST:event_tableDetailMouseClicked

    private void txtUbayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbayKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            total=harga*jumPe;
            double ubay, ukem;
            ubay = Double.parseDouble(txtUbay.getText());
            ukem=ubay-total;
            
            //meruubah status dari 'Belum bayar' menjadi 'Sudah bayar' pada table detail
            int index=tableDetail.getSelectedRow(); //selesksi baris yang ingin di proses
            String stat = (String) tableDetail.getValueAt(index, 6); //mendapatkan baris pada kolom status 
            if(stat.equalsIgnoreCase("Sudah Bayar")){                //jika transaksi sudah dibayar
                JOptionPane.showMessageDialog(null, "Transaksi sudah dibayar!");
                txtUbay.setText("");
            }else if(ubay<total){           //jika uang bayar kurang
                JOptionPane.showMessageDialog(null, "Pembayaran kurang!");
                txtUbay.setText("");
            }else{                          //jika pembayaran berhasil
                JOptionPane.showMessageDialog(null, "Pembayaran berhasil!");
                txtUkem.setText(String.valueOf(ukem));
                simpan();
            } 
        }
    }//GEN-LAST:event_txtUbayKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtArea.setEnabled(false);
        tableDetail.setEnabled(false);
        tablePenumpang.setEnabled(false);
        txtTotal.setEnabled(false);
        txtUbay.setEnabled(false);
        txtUkem.setEnabled(false);
        bersih();
    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableDetail;
    private javax.swing.JTable tablePenumpang;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUbay;
    private javax.swing.JTextField txtUkem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtArea.setEnabled(true);
        tableDetail.setEnabled(true);
        tablePenumpang.setEnabled(true);
        txtTotal.setEnabled(true);
        txtTotal.setEditable(false);
        txtUbay.setEnabled(true);
        txtUkem.setEnabled(true);
        tampilTableDetail();
    }

    @Override
    public void simpan() {
        //memilih baris pada kolom no. transaksi yang terseleksi di table detail
        int index=tableDetail.getSelectedRow();
        String pil = (String) tableDetail.getValueAt(index, 0);
        try{
            Dcont.updateStatus(pil); //memasukan string pil yang telah diberi nilai string no transaksi
            JOptionPane.showMessageDialog(null, "No. Transaksi " +pil+" berhasil terbayar!");
        }catch(Exception ex){}
        tampilTableDetail();
        
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void bersih() {
        
    }

    @Override
    public void hapus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cari() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tampil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}