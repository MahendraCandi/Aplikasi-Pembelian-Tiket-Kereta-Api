/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.controller;
import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import pembeliantiketkereta.data.Kereta;
/**
 *
 * @author Candi-PC
 */
public class KeretaController implements Serializable{
    private EntityManagerFactory emf=null;
   
    public KeretaController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Kereta kereta) throws Exception{
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(kereta);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Kereta update(Kereta krt){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(krt);
            em.getTransaction().commit();
        }catch(Exception ex){}
        return krt;
    }
    
    public Kereta delete(Kereta krt){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            em.remove(em.merge(krt));
            em.getTransaction().commit();
        }catch(Exception ex){}
        return krt;
    }
        
    //method pencarian data dengan nilai return list 
    public List setCari(String cmbCari,String txtCari)throws Exception{
        EntityManager em=getEntityManager();
        try{
            List cari=em.createQuery("select k from Kereta k where " + "k." + cmbCari + " like '%" + txtCari + "%'").getResultList();
            return cari;
        }finally{
            em.close();
        }
    }
    
    //method untuk button cari
    public Object[] setComboCari(String cmbCari)throws Exception{
        EntityManager em=getEntityManager();
        try{
            List list=em.createQuery("SELECT k." + cmbCari + " FROM Kereta k GROUP by k." + cmbCari).getResultList();
            Object[] item=list.toArray();
            return item;
        }finally{
            em.close();
        }        
    }
    
    //menampilkan hasil query dalam bentuk list untuk abstracttablemodel
    public List showTable(){        
        EntityManager em=getEntityManager();        
        try{
            @SuppressWarnings("unchecked")
            List list=em.createQuery("select k from Kereta k order by k.id").getResultList();
            return list;
        }finally{
            em.close();
        }
    }
    
    //membuat nmor otomatis kereta dalam bentuk list
    public String nomorOto(){
        String kode="KRT-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select k from Kereta k order by k.kodeKereta");
            List list = q.getResultList();
            int row=list.size();
            row--;
            Kereta k=(Kereta) list.get(row);
            if(q!=null){
                
                DecimalFormat formatnomor=new DecimalFormat("KRT-000");
                String nomorurut=k.getKodeKereta().substring(4);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
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
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportKereta.jasper";
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
