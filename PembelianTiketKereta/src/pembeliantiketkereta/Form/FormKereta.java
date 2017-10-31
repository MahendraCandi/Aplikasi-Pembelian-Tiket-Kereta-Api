/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.Form;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import pembeliantiketkereta.PersistenceEntity;
import pembeliantiketkereta.controller.KeretaController;
import pembeliantiketkereta.data.Kereta;
/**
 *
 * @author Candi-PC
 */
public class FormKereta extends javax.swing.JInternalFrame implements NavigatorFormInterface{
    Kereta krt=new Kereta();
    KeretaController Kcont=new KeretaController(PersistenceEntity.emf);
    TableModel tableModel=new TableModel();  //memanggil kelas AbstractTableModel pada kelas Table Model untuk di masukan ke table kereta
    DaftarStasiun daftarSt=new DaftarStasiun(); //memanggil kelas untuk dimasukan ke combo box
    
    /**
     * Creates new form FormKereta
     */
    public FormKereta() {
        initComponents();
        tableModel=new TableModel();
        table.setModel(tableModel); //memasulkan AbstractTableModel ke table kereta
        
        comboKotaStasiunAsal(); //memanggil method untuk membuat combo box Asal dan Tujuan saling terhubung
        comboKotaStasiunTujuan();
        
        cmbKelas.addItem("Eksekutif"); //memasukan item ke combo box kelas
        cmbKelas.addItem("Ekonomi");
        comboKelas(); //memanggil method untuk membuat combo kelas terhubung dengan subkelas
        
        setLebarKolomTable(table); //method setting lebar kolom table kereta
        renderTable(); //method untuk membuat kolom Berankat dan Tiba pada table kereta menjadi waktu
        seleksiBarisTable(); //method table kereta dapat mengeluarkan data ketika di selelsi baris
    }
    
    //setting ukuran lebar column table kereta
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
    
     //membuat table kereta menampilkan data ketika baris terseleksi
    private void seleksiBarisTable(){
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=table.getSelectedRow();       //dapatkan baris yang terseleksi
                if(baris != -1){                        //jika baris bukan sama dengan -1 atau tidak ada
                    Kereta krt=tableModel.get(baris);
                    txtkode.setText(krt.getKodeKereta());
                    txtNama.setText(krt.getNamaKereta());
                    cmbKotaAsal.setSelectedItem(krt.getKotaAsal());
                    cmbStasiunAsal.setSelectedItem(krt.getStasiunAsal());
                    cmbKotaTujuan.setSelectedItem(krt.getKotaTujuan());
                    cmbStasiunTujuan.setSelectedItem(krt.getStasiunTujuan());
                    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");     
                    txtBerangkat.setText(sdf.format(krt.getBerangkat()));
                    txtTiba.setText(sdf.format(krt.getTiba()));
                    cmbKelas.setSelectedItem(krt.getKelas());
                    cmbSub.setSelectedItem(krt.getSubKelas());
                    txtHarga.setText(krt.getHarga().toString());
                }
            }
        });
    }
    
    //memberikan table kereta, format date HH:mm pada kolom Berangkat dan Tiba
    public void renderTable(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Date){
                    value = sdf.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        table.getColumnModel().getColumn(6).setCellRenderer(tbr);
        table.getColumnModel().getColumn(7).setCellRenderer(tbr);
    }
    
    //membuat data combo box Kota Asal dan Stasiun Asal saling berhubungan
    private void comboKotaStasiunAsal(){
        //memberikan data nama stasiun pada kota jakarta
        final DefaultComboBoxModel Jakarta=new DefaultComboBoxModel(daftarSt.jakarta());
        final DefaultComboBoxModel Bandung=new DefaultComboBoxModel(daftarSt.bandung());
        final DefaultComboBoxModel Yogyakarta=new DefaultComboBoxModel(daftarSt.yogyakarta());
        final DefaultComboBoxModel Bogor=new DefaultComboBoxModel(daftarSt.bogor());
        final DefaultComboBoxModel Brebes=new DefaultComboBoxModel(daftarSt.brebes());
        final DefaultComboBoxModel Cirebon=new DefaultComboBoxModel(daftarSt.cirebon());
        final DefaultComboBoxModel Malang=new DefaultComboBoxModel(daftarSt.malang());
        final DefaultComboBoxModel Solo=new DefaultComboBoxModel(daftarSt.solo());
        final DefaultComboBoxModel Sukabumi=new DefaultComboBoxModel(daftarSt.sukabumi());
        final DefaultComboBoxModel Surabaya=new DefaultComboBoxModel(daftarSt.surabaya());
        cmbKotaAsal.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Jakarta".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Jakarta);
                }else if("Bandung".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Bandung);
                }else if("Yogyakarta".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Yogyakarta);
                }else if("Bogor".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Bogor);
                }else if("Brebes".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Brebes);
                }else if("Cirebon".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Cirebon);
                }else if("Malang".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Malang);
                }else if("Solo".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Solo);
                }else if("Sukabumi".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Sukabumi);
                }else if("Surabaya".equals(cmbKotaAsal.getSelectedItem())){
                    cmbStasiunAsal.setModel(Surabaya);
                }
            }
        });
    }
    
    //membuat Combo box Kota Tujuan dan Stasiun Tujuan saling berhubungan
    private void comboKotaStasiunTujuan(){
        final DefaultComboBoxModel Jakarta=new DefaultComboBoxModel(daftarSt.jakarta());
        final DefaultComboBoxModel Bandung=new DefaultComboBoxModel(daftarSt.bandung());
        final DefaultComboBoxModel Yogyakarta=new DefaultComboBoxModel(daftarSt.yogyakarta());
        final DefaultComboBoxModel Bogor=new DefaultComboBoxModel(daftarSt.bogor());
        final DefaultComboBoxModel Brebes=new DefaultComboBoxModel(daftarSt.brebes());
        final DefaultComboBoxModel Cirebon=new DefaultComboBoxModel(daftarSt.cirebon());
        final DefaultComboBoxModel Malang=new DefaultComboBoxModel(daftarSt.malang());
        final DefaultComboBoxModel Solo=new DefaultComboBoxModel(daftarSt.solo());
        final DefaultComboBoxModel Sukabumi=new DefaultComboBoxModel(daftarSt.sukabumi());
        final DefaultComboBoxModel Surabaya=new DefaultComboBoxModel(daftarSt.surabaya());
        cmbKotaTujuan.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Jakarta".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Jakarta);
                }else if("Bandung".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Bandung);
                }else if("Yogyakarta".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Yogyakarta);
                }else if("Bogor".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Bogor);
                }else if("Brebes".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Brebes);
                }else if("Cirebon".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Cirebon);
                }else if("Malang".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Malang);
                }else if("Solo".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Solo);
                }else if("Sukabumi".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Sukabumi);
                }else if("Surabaya".equals(cmbKotaTujuan.getSelectedItem())){
                    cmbStasiunTujuan.setModel(Surabaya);
                }
            }
        });
    }
    
    //membuat Combo box Kelas dan SubKelas saling berhubungan
    private void comboKelas(){
        final DefaultComboBoxModel Eksekutif=new DefaultComboBoxModel(new String[]{"A","H","I","J"});
        final DefaultComboBoxModel EKonomi=new DefaultComboBoxModel(new String[]{"C","P","Q","S"});
        
        cmbKelas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Eksekutif".equals(cmbKelas.getSelectedItem())){
                    cmbSub.setModel(Eksekutif);
                }else{
                    cmbSub.setModel(EKonomi);
                }
            }
        });
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
        table = new javax.swing.JTable();
        btTambah = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtkode = new javax.swing.JTextField();
        cmbKotaAsal = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbStasiunAsal = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbKotaTujuan = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbStasiunTujuan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbKelas = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbSub = new javax.swing.JComboBox<>();
        txtTiba = new javax.swing.JFormattedTextField();
        txtHarga = new javax.swing.JTextField();
        txtBerangkat = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox<>();
        txtCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        btRefresh = new javax.swing.JButton();

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

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama Kereta", "Kota Asal", "Stasiun Asal", "Kota Tujuan", "Stasiun Tujuan", "Berangkat", "Tiba", "Kelas", "Sub-Kelas", "Harga"
            }
        ));
        jScrollPane1.setViewportView(table);

        btTambah.setText("Tambah");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });

        btEdit.setText("Edit");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Kereta"));

        txtkode.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbKotaAsal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jakarta", "Bandung", "Yogyakarta", "Bogor", "Brebes", "Cirebon", "Malang", "Solo", "Sukabumi", "Surabaya" }));
        cmbKotaAsal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Kota Asal");

        cmbStasiunAsal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Stasiun Asal");

        cmbKotaTujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jakarta", "Bandung", "Yogyakarta", "Bogor", "Brebes", "Cirebon", "Malang", "Solo", "Sukabumi", "Surabaya" }));
        cmbKotaTujuan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Kota Tujuan");

        cmbStasiunTujuan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Stasiun Tujuan");

        jLabel6.setText("Nama Kereta");

        txtNama.setText("Argo Parahyangan");
        txtNama.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Kode Kereta");

        jLabel10.setText("Harga");

        jLabel7.setText("Berangkat");

        jLabel8.setText("Kelas");

        cmbKelas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Sub-Kelas");

        jLabel11.setText("Tiba");

        cmbSub.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTiba.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTiba.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));

        txtHarga.setText("999999");
        txtHarga.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBerangkat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbKotaAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStasiunAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(34, 34, 34)
                                .addComponent(cmbKotaTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cmbStasiunTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSub, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtBerangkat, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTiba, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKotaAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStasiunAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txtBerangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtTiba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKotaTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cmbSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbStasiunTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Cari berdasarkan");

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kodeKereta", "namaKereta", "kelas", "kotaAsal", "stasiunAsal", "kotaTujuan", "stasiunTujuan", "" }));
        cmbCari.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCari.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btCari.setText("Cari");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });

        btRefresh.setText("Refresh");
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btEdit, btHapus, btTambah});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btEdit)
                        .addComponent(btHapus)
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btCari)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btEdit, btHapus, btTambah});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        txtkode.setEnabled(false);
        cmbKotaAsal.setEnabled(false);
        cmbStasiunAsal.setEnabled(false);
        cmbKotaTujuan.setEnabled(false);
        cmbStasiunTujuan.setEnabled(false);
        txtNama.setEnabled(false);
        txtBerangkat.setEnabled(false);
        txtTiba.setEnabled(false);
        cmbKelas.setEnabled(false);
        cmbSub.setEnabled(false);
        txtHarga.setEnabled(false);
        btTambah.setEnabled(false);
        btHapus.setEnabled(false);
        btEdit.setEnabled(false);
        btRefresh.setEnabled(false);
        cmbCari.setEnabled(false);
        txtCari.setEnabled(false);
        btCari.setEnabled(false);
        table.setEnabled(false);
        
        bersih();
    }//GEN-LAST:event_formInternalFrameActivated

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        //Jika data tidak lengkap dan combo box stasiun sama
        if(txtNama.getText().equals("")||txtBerangkat.getText().equals("")||
                txtTiba.getText().equals("")||txtHarga.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data harus diisi dengan lengkap!");
            return;
        }else if(cmbStasiunAsal.getSelectedItem()==cmbStasiunTujuan.getSelectedItem()){
            JOptionPane.showMessageDialog(null, "Stasiun asal dan tujuan harus berbeda!");
        }
        simpan();
        
        //combo box subkelas pindah dengan otomatis
        if(cmbSub.getSelectedIndex()==0){
            cmbSub.setSelectedIndex(1);
        }else if(cmbSub.getSelectedIndex()==1){
            cmbSub.setSelectedIndex(2);
        }else if(cmbSub.getSelectedIndex()==2){
            cmbSub.setSelectedIndex(3);
        }else{
            JOptionPane.showMessageDialog(null, "Subkelas terakhir!");
        }
    }//GEN-LAST:event_btTambahActionPerformed

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        //menampilkan table
        tableModel.updateAll(Kcont.showTable());
        txtCari.setText("");
        bersih();
    }//GEN-LAST:event_btRefreshActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus data?", 
                "Hapus Data", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION){
            return;
        }
        hapus();
        bersih();
    }//GEN-LAST:event_btHapusActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
       if(txtNama.getText().equals("")||txtBerangkat.getText().equals("")||
                txtTiba.getText().equals("")||txtHarga.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data harus diisi dengan lengkap!");
            return;
        }
       edit();
    }//GEN-LAST:event_btEditActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cari();    
    }//GEN-LAST:event_btCariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btRefresh;
    private javax.swing.JButton btTambah;
    private javax.swing.JComboBox<String> cmbCari;
    private javax.swing.JComboBox<String> cmbKelas;
    private javax.swing.JComboBox<String> cmbKotaAsal;
    private javax.swing.JComboBox<String> cmbKotaTujuan;
    private javax.swing.JComboBox<String> cmbStasiunAsal;
    private javax.swing.JComboBox<String> cmbStasiunTujuan;
    private javax.swing.JComboBox<String> cmbSub;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JFormattedTextField txtBerangkat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
    private javax.swing.JFormattedTextField txtTiba;
    private javax.swing.JTextField txtkode;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        cmbKotaAsal.setEnabled(true);
        cmbStasiunAsal.setEnabled(true);
        cmbKotaTujuan.setEnabled(true);
        cmbStasiunTujuan.setEnabled(true);
        txtNama.setEnabled(true);
        txtBerangkat.setEnabled(true);
        txtTiba.setEnabled(true);
        cmbKelas.setEnabled(true);
        cmbSub.setEnabled(true);
        txtHarga.setEnabled(true);
        btTambah.setEnabled(true);
        btHapus.setEnabled(true);
        btEdit.setEnabled(true);
        btRefresh.setEnabled(true);
        cmbCari.setEnabled(true);
        txtCari.setEnabled(true);
        btCari.setEnabled(true);
        table.setEnabled(true);
        //menampilkan table
        btRefreshActionPerformed(null);
    }

    @Override
    public void bersih() {
        cmbKotaAsal.setSelectedIndex(0);
        cmbKotaTujuan.setSelectedIndex(0);
        cmbStasiunAsal.setSelectedIndex(0);
        cmbStasiunTujuan.setSelectedIndex(0);
        txtNama.setText("");
        txtBerangkat.setText("");
        txtTiba.setText("");
        cmbKelas.setSelectedIndex(0);
        cmbSub.setSelectedIndex(0);
        txtHarga.setText("");
        txtkode.setText(Kcont.nomorOto());
    }

    @Override
    public void simpan() {
            Kereta krt=new Kereta();
            krt.setKodeKereta(txtkode.getText());
            krt.setNamaKereta(txtNama.getText());
            krt.setKotaAsal(cmbKotaAsal.getSelectedItem().toString());
            krt.setStasiunAsal(cmbStasiunAsal.getSelectedItem().toString());
            krt.setKotaTujuan(cmbKotaTujuan.getSelectedItem().toString());
            krt.setStasiunTujuan(cmbStasiunTujuan.getSelectedItem().toString());
            krt.setKotaAsal(cmbKotaAsal.getSelectedItem().toString());
            try{
                SimpleDateFormat df=new SimpleDateFormat("HH:mm");
                Date jamBerangkat=(Date)df.parse(txtBerangkat.getText());
                Date jamTiba=(Date) df.parse(txtTiba.getText());
                krt.setBerangkat(jamBerangkat);
                krt.setTiba(jamTiba);
            } catch (Exception ex) {}
            krt.setKelas(cmbKelas.getSelectedItem().toString());
            krt.setSubKelas(cmbSub.getSelectedItem().toString());
            krt.setHarga(Double.parseDouble(txtHarga.getText()));
            //simpan ke databse
            try{
                Kcont.save(krt);
            }catch(Exception ex){}
            //simpan ke table
            tableModel.insert(krt);
    }
    
    @Override
    public void edit() {
        //mendapatkan baris yang terseleksi pada table kereta
        int index=table.getSelectedRow();
        if(index==-1){
            JOptionPane.showMessageDialog(null, "Pilih baris yang akan diupdate!");
            return;
        }
        Kereta krt=tableModel.select(index);
            krt.setKodeKereta(txtkode.getText());
            krt.setNamaKereta(txtNama.getText());
            krt.setKotaAsal(cmbKotaAsal.getSelectedItem().toString());
            krt.setStasiunAsal(cmbStasiunAsal.getSelectedItem().toString());
            krt.setKotaTujuan(cmbKotaTujuan.getSelectedItem().toString());
            krt.setStasiunTujuan(cmbStasiunTujuan.getSelectedItem().toString());
            krt.setKotaAsal(cmbKotaAsal.getSelectedItem().toString());
            try {
                SimpleDateFormat df=new SimpleDateFormat("hh:mm");
                Date jamBerangkat=(Date)df.parse(txtBerangkat.getText());
                Date jamTiba=(Date) df.parse(txtTiba.getText());
                krt.setBerangkat(jamBerangkat);
                krt.setTiba(jamTiba);
             } catch (Exception ex) {}
            krt.setKelas(cmbKelas.getSelectedItem().toString());
            krt.setSubKelas(cmbSub.getSelectedItem().toString());
            krt.setHarga(Double.parseDouble(txtHarga.getText()));
            //simpan ke databse
            try{
                Kcont.update(krt);
            }catch(Exception ex){}
            //simpan ke table
            tableModel.update(index, krt);
    }

    @Override
    public void hapus() {
        int index=table.getSelectedRow();
        if(index==-1){
            JOptionPane.showMessageDialog(null, "Pilih baris yang akan dihapus!");
            return;
        }
        try {
            Kereta krt=tableModel.select(index); //mendapatkan index urutan data pada list
            Kcont.delete(krt);
            tableModel.delete(index);
        } catch (Exception ex) {}
    }

    @Override
    public void cari() {
        try{
            tableModel.setData((List) Kcont.setCari(cmbCari.getSelectedItem().toString(), txtCari.getText()));
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    @Override
    public void tampil() {
        
    }

    
}
