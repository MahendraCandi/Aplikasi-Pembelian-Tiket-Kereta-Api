/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.Form;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import pembeliantiketkereta.PersistenceEntity;
import pembeliantiketkereta.controller.*;
import pembeliantiketkereta.data.*;

/**
 *
 * @author Candi-PC
 */
public class FormTransaksi extends javax.swing.JInternalFrame implements NavigatorFormInterface{
    TableModel tableModel=new TableModel(); //memanggil kelas AbstractTableModel pada kelas Table Model untuk di masukan ke table kereta
    
    KeretaController Kcont=new KeretaController(PersistenceEntity.emf);
    TransaksiController Tcont=new TransaksiController(PersistenceEntity.emf);
    DetailController Dcont=new DetailController(PersistenceEntity.emf);
    PenumpangController Pcont=new PenumpangController(PersistenceEntity.emf);
    
    Transaksi trans=new Transaksi();
    Detailtransaksi det=new Detailtransaksi();
    Penumpang penum=new Penumpang();
    
    DefaultTableModel tblPenum;         //membuat DefaultTableModel untuk table penumpang
    String no, identitas, namaPenumpang; //String untuk menyimpan data penumpang 
    double harga=0, total=0;             //double untuk menghitung harga tiket dan total banyak tiket
    int jumlahPenumpang=0;               //menyimpan banyak penumpang
    /**
     * Creates new form FormTransaksi
     */
    public FormTransaksi() {
        initComponents();
        
        setWaktu(); // method untuk pengaturan waktu 
        getTanggalBerangkat(); //method untuk pengaturan JDateChooser tanggal berangkat
        
        tableModel=new TableModel();
        tableKereta.setModel(tableModel); //memasulkan AbstractTableModel ke table kereta
        setLebarKolomTable(tableKereta); // method setting lebar kolom kereta
        renderTable(); //method untuk membuat kolom Berankat dan Tiba pada table kereta menjadi waktu
        seleksiBarisTable();    //method table kereta dapat mengeluarkan data ketika di selelsi baris
        
        tblPenum=(DefaultTableModel) tablePenumpang.getModel(); //memasukan DefaultTableModel ke table penumpang
        tablePenumpang.getColumnModel().getColumn(0).setPreferredWidth(50); //membuat lebar kolom nomor pada table penumpang menjadi 50 
    }
    
    private void setWaktu(){
        //membuat format waktu jam, hari bulan dan tahun dengan locale indonesia
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm EEEE, dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        Calendar cal=Calendar.getInstance();
        txtTanggal.setText(sdf.format(cal.getTime()));
    }
    
    //setting JDateChooser tanggal berangkat 
    private void getTanggalBerangkat(){
        txtTanggalBerangkat.setLocale(Locale.forLanguageTag("in-ID")); //membuat locale indonesia
        txtTanggalBerangkat.setMinSelectableDate(new Date());   //menyembunyikan tanggal yang sudah lewat
        txtTanggalBerangkat.setDateFormatString("EEEE, dd MMMM yyyy");
        txtTanggalBerangkat.setDate(new Date());
        
    }
    
    //menampilkan table dan setting lebar kolom pada table kereta
    public void setLebarKolomTable(JTable table){
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(175);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(75);
        table.getColumnModel().getColumn(7).setPreferredWidth(75);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setPreferredWidth(50);
        table.getColumnModel().getColumn(10).setPreferredWidth(100);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }
    
    //menampilkan format date HH:mm pada table di kolom Berangkat dan Tiba pada table kereta
    public void renderTable(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Date){
                    value = sdf.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, 
                        hasFocus, row, column);
            }
        };
        tableKereta.getColumnModel().getColumn(6).setCellRenderer(tbr);
        tableKereta.getColumnModel().getColumn(7).setCellRenderer(tbr);
    }
    
    //membuat table menampilkan data ketika  baris terseleksi
    private void seleksiBarisTable(){
        tableKereta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableKereta.getSelectedRow(); //dapatkan baris yang terseleksi
                if(baris != -1){
                    Kereta krt=tableModel.get(baris);
                    txtKode.setText(krt.getKodeKereta());
                    txtNama.setText(krt.getNamaKereta());
                    txtKotaAsal.setText(krt.getKotaAsal());
                    txtStasiunAsal.setText(krt.getStasiunAsal());
                    txtKotaTujuan.setText(krt.getKotaTujuan());
                    txtStasiunTujuan.setText(krt.getStasiunTujuan());
                    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
                    txtBerangkat.setText(sdf.format(krt.getBerangkat()));
                    txtTiba.setText(sdf.format(krt.getTiba()));
                    txtKelas.setText(krt.getKelas());
                    txtSub.setText(krt.getSubKelas());
                    txtHarga.setText(krt.getHarga().toString());
                    txtIdentitas.requestFocus();
                }
            }
        });
    }
    
    //menampilkan table penumpang
    private void tampilTablePenumpang(){
        Object[] obj=new Object[3]; //membuat object array 
        obj[1]=txtIdentitas.getText(); //isi data object array urutan ke dua
        obj[2]=txtPenumpang.getText(); //isi data object array urutan ke tiga
        tblPenum.addRow(obj); // memasukan object ke table penumpang
        noTable();//membuat nomor otomatis pada table kolom nomor, pada urutan ke satu
    }
    
    //membuat penomoran otomatis  table penumpang
    private void noTable(){
        int row=tblPenum.getRowCount(); //mengisi objek row dengan total seluruh baris table penumpang 
        for(int a=0; a<row ;a++){
            String no=String.valueOf(a+1);
            tablePenumpang.setValueAt(no, a, 0); 
        }
    }
    
    //menghapus data penumpang yang terseleksi
    private void deleteTablePenumpang(){
        int pilih=tablePenumpang.getSelectedRow();
        if(pilih==-1){
            JOptionPane.showMessageDialog(null, "Pilih penumpang yang mau dihapus!");
        }else{
            tblPenum.removeRow(pilih);
        }
        noTable();  //merefresh /mengulang nomor
    }
    
    //memberihkan / batal pada table penumpang
    private void clearTablePenumpang(){
        int row=tblPenum.getRowCount();
        while(row>0){
            row--;
            tblPenum.removeRow(row);
        }
    }
    
    //menghitung banyak penumpang dengan harga tiket
    private void hitung(){
        jumlahPenumpang=tablePenumpang.getRowCount();
        harga = Double.parseDouble(txtHarga.getText());
        total = jumlahPenumpang*harga;
    }
    
    //membuat rekap transaksi untuk txtArea
    private String textKeterangan(){
        SimpleDateFormat sdf=new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        String ket="No. Transaksi \t: " + txtNoTrans.getText()
                + "\n========================="
                + "\nNama Kereta \t: " + txtNama.getText()
                + "\nStasiun Asal \t: " +txtStasiunAsal.getText()+", "+txtKotaAsal.getText()
                + "\nStasiun Tujuan \t: " +txtStasiunTujuan.getText()+", "+txtKotaTujuan.getText()
                + "\nKelas \t: " + txtKelas.getText()
                + "\nSubkelas \t: " + txtSub.getText()
                + "\nTanggal berangkat : " + sdf.format(txtTanggalBerangkat.getDate())
                + "\nBerangkat pukul : " + txtBerangkat.getText()
                + "\nHarga \t: " + txtHarga.getText()
                + "\n\nJumlah Penumpang : " + jumlahPenumpang
                + "\nTotal harga \t: " + total
                + "\n=========================" 
                + "\nAPAKAH DATA RESERVASI ANDA SUDAH BENAR?"
                + "\nJIKA IYA, TEKAN SIMPAN.";
        return ket;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNoTrans = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalBerangkat = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKereta = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtKotaAsal = new javax.swing.JTextField();
        txtStasiunAsal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtKotaTujuan = new javax.swing.JTextField();
        txtStasiunTujuan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTiba = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtKelas = new javax.swing.JTextField();
        txtBerangkat = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtSub = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox<>();
        btCari = new javax.swing.JButton();
        btRefresh = new javax.swing.JButton();
        cmbHasil = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIdentitas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPenumpang = new javax.swing.JTextField();
        btTambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePenumpang = new javax.swing.JTable();
        btBatal = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btBatalTrans = new javax.swing.JButton();
        btSimpanTrans = new javax.swing.JButton();

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

        jLabel1.setText("No Transaksi");

        txtNoTrans.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Reservasi Kereta"));

        jLabel2.setText("Tanggal berangkat");

        tableKereta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableKereta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKereta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKeretaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKereta);

        jLabel9.setText("Kode Kereta");

        txtKode.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Kota Asal");

        txtKotaAsal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtStasiunAsal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Stasiun Asal");

        jLabel12.setText("Kota Tujuan");

        jLabel13.setText("Stasiun Tujuan");

        txtKotaTujuan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtStasiunTujuan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Harga");

        txtTiba.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtHarga.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Nama Kereta");

        txtNama.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Kelas");

        txtKelas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBerangkat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("Berangkat");

        jLabel19.setText("Tiba");

        txtSub.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setText("Sub");

        jLabel3.setText("Cari berdasarkan");

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kotaTujuan", "stasiunTujuan", "namaKereta", "kelas", "kotaAsal", "stasiunAsal", "kodeKereta" }));
        cmbCari.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cmbCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCariActionPerformed(evt);
            }
        });

        btCari.setText("Cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });

        btRefresh.setText("Refresh Pencarian");
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });

        cmbHasil.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbHasil, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCari)
                        .addGap(18, 18, 18)
                        .addComponent(btRefresh)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTanggalBerangkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtKotaAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtStasiunAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel16))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNama)
                                    .addComponent(txtKotaTujuan)
                                    .addComponent(txtStasiunTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtKelas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSub, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtHarga, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtBerangkat, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTiba, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 105, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtKotaAsal, txtStasiunAsal});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtBerangkat, txtTiba});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTanggalBerangkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtBerangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel19)
                                        .addComponent(txtTiba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14)
                                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtKotaAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtStasiunAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtKotaTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtStasiunTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCari)
                    .addComponent(btRefresh)
                    .addComponent(cmbHasil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Data Penumpang"));

        jLabel5.setText("No. Identitas");

        txtIdentitas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Nama Penumpang");

        txtPenumpang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btTambah.setText("TAMBAH");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        tablePenumpang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tablePenumpang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nama", "No. KTP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablePenumpang);
        if (tablePenumpang.getColumnModel().getColumnCount() > 0) {
            tablePenumpang.getColumnModel().getColumn(0).setResizable(false);
            tablePenumpang.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablePenumpang.getColumnModel().getColumn(1).setResizable(false);
            tablePenumpang.getColumnModel().getColumn(2).setResizable(false);
        }

        btBatal.setText("BATAL");
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });

        btDelete.setText("DELETE");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdentitas, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPenumpang, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(136, 136, 136))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIdentitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPenumpang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btDelete)
                            .addComponent(btBatal))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel15.setText("Waktu");

        txtTanggal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane4.setViewportView(txtArea);

        btBatalTrans.setText("BATAL TRANSAKSI");
        btBatalTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalTransActionPerformed(evt);
            }
        });

        btSimpanTrans.setText("SIMPAN RESERVASI");
        btSimpanTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanTransActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNoTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btBatalTrans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btSimpanTrans, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNoTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btSimpanTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btBatalTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNoTrans.setEditable(false);
        txtTanggal.setEditable(false);
        txtTanggalBerangkat.setEnabled(false);
        txtKode.setEnabled(false);
        txtKotaAsal.setEnabled(false);
        txtStasiunAsal.setEnabled(false);
        txtKotaTujuan.setEnabled(false);
        txtStasiunTujuan.setEnabled(false);
        txtNama.setEnabled(false);
        txtBerangkat.setEnabled(false);
        txtTiba.setEnabled(false);
        txtKelas.setEnabled(false);
        txtSub.setEnabled(false);
        txtHarga.setEnabled(false);
        cmbCari.setEnabled(false);
        cmbHasil.setEnabled(false);
        btCari.setEnabled(false);
        btRefresh.setEnabled(false);
        txtIdentitas.setEnabled(false);
        txtPenumpang.setEnabled(false);
        btTambah.setEnabled(false);
        btDelete.setEnabled(false);
        btBatal.setEnabled(false);
        txtArea.setEnabled(false);
        btSimpanTrans.setEnabled(false);
        btBatalTrans.setEnabled(false);
        tableKereta.setEnabled(false);
        tablePenumpang.setEnabled(false);
        bersih();
    }//GEN-LAST:event_formInternalFrameActivated

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        tableModel.updateAll(Kcont.showTable()); //menampilkan database kereta ke table kereta
        bersih();
    }//GEN-LAST:event_btRefreshActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cari(); //menampilkan hasil pencarian database pada table kereta
    }//GEN-LAST:event_btCariActionPerformed

    private void cmbCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCariActionPerformed
        try{
            String x=cmbCari.getSelectedItem().toString();
            Object[] item=Kcont.setComboCari(x); //memasukan string x ke dalama parameter setComboCari, dan memberi nilai hasil return combocari ke objek array item
            cmbHasil.setModel(new DefaultComboBoxModel(item)); //membuat combo box cmbHasil mendapatkan data dari objek array item
        }catch(Exception ex){}
    }//GEN-LAST:event_cmbCariActionPerformed

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        if(txtIdentitas.getText().equals("")||txtPenumpang.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Isi identitas dan nama penumpang!");
        }else{
            tampilTablePenumpang();
        }
        hitung();
        txtArea.setText(textKeterangan());
        txtIdentitas.setText("");
        txtPenumpang.setText("");
        txtIdentitas.requestFocus();
    }//GEN-LAST:event_btTambahActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        deleteTablePenumpang();
        hitung();
        txtArea.setText(textKeterangan());
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        clearTablePenumpang();
        hitung();
        txtArea.setText(textKeterangan());
    }//GEN-LAST:event_btBatalActionPerformed

    private void btSimpanTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanTransActionPerformed
        int row=tblPenum.getRowCount();
        if(txtTanggalBerangkat.getDate()==null||txtKode.getText().equals("")
                ||row==0){
            JOptionPane.showMessageDialog(null, "Data Reservasi belum lengkap!");
        }else{
            simpan();
            btBatalTransActionPerformed(null);
            formInternalFrameActivated(null);
            txtNoTrans.setText(Tcont.nomorTrans());
        }
        
    }//GEN-LAST:event_btSimpanTransActionPerformed

    private void btBatalTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalTransActionPerformed
        btRefreshActionPerformed(null); //memanggil method refresh table kereta
        txtTanggalBerangkat.setDate(new Date());
        txtIdentitas.setText("");
        txtPenumpang.setText("");
        clearTablePenumpang();
        
    }//GEN-LAST:event_btBatalTransActionPerformed

    private void tableKeretaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKeretaMouseClicked
        hitung();
        txtArea.setText(textKeterangan()); //menampilakan rekap pada txtArea
    }//GEN-LAST:event_tableKeretaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBatal;
    private javax.swing.JButton btBatalTrans;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btRefresh;
    private javax.swing.JButton btSimpanTrans;
    private javax.swing.JButton btTambah;
    private javax.swing.JComboBox<String> cmbCari;
    private javax.swing.JComboBox<String> cmbHasil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tableKereta;
    private javax.swing.JTable tablePenumpang;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtBerangkat;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdentitas;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtKotaAsal;
    private javax.swing.JTextField txtKotaTujuan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoTrans;
    private javax.swing.JTextField txtPenumpang;
    private javax.swing.JTextField txtStasiunAsal;
    private javax.swing.JTextField txtStasiunTujuan;
    private javax.swing.JTextField txtSub;
    private javax.swing.JTextField txtTanggal;
    private com.toedter.calendar.JDateChooser txtTanggalBerangkat;
    private javax.swing.JTextField txtTiba;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        //menampilkan table ketika form aktif
        btRefreshActionPerformed(null);
        
        //mengambil data dari database ke combo box
        cmbCariActionPerformed(null);
        
        txtNoTrans.setText(Tcont.nomorTrans());
        txtTanggalBerangkat.setEnabled(true);
        cmbCari.setEnabled(true);
        cmbHasil.setEnabled(true);
        btCari.setEnabled(true);
        btRefresh.setEnabled(true);
        tableKereta.setEnabled(true);
        
        txtKode.setEnabled(true);
        txtKotaAsal.setEnabled(true);
        txtStasiunAsal.setEnabled(true);
        txtKotaTujuan.setEnabled(true);
        txtStasiunTujuan.setEnabled(true);
        txtNama.setEnabled(true);
        txtBerangkat.setEnabled(true);
        txtTiba.setEnabled(true);
        txtKelas.setEnabled(true);
        txtSub.setEnabled(true);
        txtHarga.setEnabled(true);
        
        //membuat text field tidak bisa di edit
        txtKode.setEditable(false);
        txtKotaAsal.setEditable(false);
        txtStasiunAsal.setEditable(false);
        txtKotaTujuan.setEditable(false);
        txtStasiunTujuan.setEditable(false);
        txtNama.setEditable(false);
        txtBerangkat.setEditable(false);
        txtTiba.setEditable(false);
        txtKelas.setEditable(false);
        txtSub.setEditable(false);
        txtHarga.setEditable(false);
   
        txtIdentitas.setEnabled(true);
        txtPenumpang.setEnabled(true);
        btTambah.setEnabled(true);
        btDelete.setEnabled(true);
        btBatal.setEnabled(true);
        tablePenumpang.setEnabled(true);
        
        txtArea.setEnabled(true);
        btSimpanTrans.setEnabled(true);
        btBatalTrans.setEnabled(true);
        
        txtArea.setText(textKeterangan()); 
    }

    @Override
    public void simpan() {
        Transaksi trans=new Transaksi();
        Detailtransaksi det=new Detailtransaksi();
        Penumpang penum=new Penumpang();
        
        //simpan ke database transaksi
        trans.setNoTransaksi(txtNoTrans.getText());
        trans.setTanggal(new Date());
        trans.setTotal(total);
        try{
            Tcont.save(trans);
            System.out.println("Transaksi");
        }catch(Exception ex){}
        
        //simpan ke database detail transaksi
        det.setNoTransaksi(txtNoTrans.getText());
        det.setKodeKereta(txtKode.getText());
        det.setNamaKereta(txtNama.getText());
        det.setKotaAsal(txtKotaAsal.getText());
        det.setStasiunAsal(txtStasiunAsal.getText());
        det.setKotaTujuan(txtKotaTujuan.getText());
        det.setStasiunTujuan(txtStasiunTujuan.getText());
        det.setKelas(txtKelas.getText());
        det.setSubkelas(txtSub.getText());
        det.setTanggalBerangkat(txtTanggalBerangkat.getDate());
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
            Date jamBerangkat=(Date) sdf.parse(txtBerangkat.getText());
            det.setBerangkat(jamBerangkat);
        }catch(Exception ex){}
        det.setHarga(harga);
        det.setJumlahPenumpang(jumlahPenumpang);
        det.setStatus("Belum bayar"); //membuat status belum bayar
        try{
            Dcont.saveDetail(det);
            System.out.println("detail");
        }catch(Exception ex){}
        
        //simpan dari table penumpang ke database penumpang
        int row=tblPenum.getRowCount();
        for(int i=0; i<row; i++){
            penum.setNamaPenumpang(tblPenum.getValueAt(i, 1).toString());
            penum.setNoKtp(tblPenum.getValueAt(i, 2).toString());
            penum.setNoTransaksi(txtNoTrans.getText());
            try{
                Pcont.savePenumpang(penum);
                System.out.println("penumpang");
            }catch(Exception ex){}
        }
        
        JOptionPane.showMessageDialog(null, "Data telah tersimpan!");
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void bersih() {
        
        txtKode.setText("");
        txtKotaAsal.setText("");
        txtStasiunAsal.setText("");
        txtKotaTujuan.setText("");
        txtStasiunTujuan.setText("");
        txtNama.setText("");
        txtBerangkat.setText("");
        txtTiba.setText("");
        txtKelas.setText("");
        txtSub.setText("");
        txtHarga.setText("");
        cmbCari.setSelectedIndex(0);
        
    }

    @Override
    public void hapus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cari() {
        //membuat AbstractTableModel mendapatkan data pencarian untuk ditampilan di table kereta dengan
        //mengambil String item dari combo box cari dan combo box hasil menjadi parameter pada method setCari di kelas controller
        try{
            tableModel.setData((List) Kcont.setCari(cmbCari.getSelectedItem().toString(), cmbHasil.getSelectedItem().toString()));
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    @Override
    public void tampil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
