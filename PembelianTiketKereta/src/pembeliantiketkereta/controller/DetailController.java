/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.controller;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import pembeliantiketkereta.data.Detailtransaksi;

/**
 *
 * @author Candi-PC
 */
public class DetailController implements Serializable{
    private EntityManagerFactory emf=null;
    public DetailController(EntityManagerFactory emf){
        this.emf=emf;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void saveDetail(Detailtransaksi detail)throws Exception{
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(detail);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    //menampilkan table detail dengan defaulttablemodel
    public DefaultTableModel showTableDetail(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT d.noTransaksi, d.namaKereta, d.stasiunAsal, d.stasiunTujuan, "
                    + "d.kelas, d.jumlahPenumpang, d.status from Detailtransaksi d");
            
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //membuat method return DefaultTableModel dengan dua parameter model dan kode 
    public DefaultTableModel showTablePenumpang(DefaultTableModel model, String kode){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("select p.namaPenumpang, p.noKtp from Penumpang p where p.noTransaksi like '%"+kode+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr=hasil.iterator();
            while(itr.hasNext()){
                Object[] obj=(Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //membuat method untuk mengubah status setelah membayar
    public void updateStatus(String noTrans) {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Detailtransaksi d SET d.status = 'Sudah bayar' where d.noTransaksi = '"+noTrans+"'");
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
    }
    
    //mengambil query / data dari database untuk text area pada form bayar
    public Object[] textArea(String kode){
        EntityManager em=getEntityManager();
        try{
            Query q=em.createQuery("SELECT d.noTransaksi, d.kodeKereta, d.namaKereta, d.kotaAsal, "
                    + "d.stasiunAsal, d.kotaTujuan, d.stasiunTujuan, d.kelas, d.subkelas, "
                    + "d.tanggalBerangkat, d.berangkat, d.harga, d.jumlahPenumpang, d.status "
                    + "from Detailtransaksi d where d.noTransaksi like '%"+kode+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            itr.hasNext();
                Object[] obj=(Object[]) itr.next();
            return obj;
        }finally{}
    }
    
    public void cetak(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportKeretaDetail.jasper";
            JasperPrint jprint=JasperFillManager.fillReport(namafile, new HashMap(), connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                connect.close();
        }catch(Exception e){e.printStackTrace();
        }
    }
    
    /* tidak digunakan
    //menampilkan table detail dengan abstracttablemodel
    public List showDetail(){
        EntityManager em=getEntityManager();
        try{
            @SuppressWarnings("unchecked")
            List list=em.createQuery("select d from Detailtransaksi d order by d.noTransaksi").getResultList();
            return list;
        }finally{
            em.close();
        }
    }
    */
}
