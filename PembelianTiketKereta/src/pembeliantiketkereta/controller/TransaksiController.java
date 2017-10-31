/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.controller;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import pembeliantiketkereta.data.Transaksi;

/**
 *
 * @author Candi-PC
 */
public class TransaksiController implements Serializable {
    private EntityManagerFactory emf=null;
    public TransaksiController(EntityManagerFactory emf){
        this.emf=emf;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Transaksi trans)throws Exception{
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(trans);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Transaksi findTransaksi(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(Transaksi.class, kode);
        }finally{}
    }

    public String nomorTrans(){
        String kode="TR-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select count (t.noTransaksi) from Transaksi t");
            q.setMaxResults(1);
            Long hasil=(Long) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor=new DecimalFormat("TR-000");
                kode=formatnomor.format(hasil+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    public void cetak(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportKeretaTransaksi.jasper";
            JasperPrint jprint=JasperFillManager.fillReport(namafile, new HashMap(), connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                connect.close();
        }catch(Exception e){e.printStackTrace();
        }
    }
}
