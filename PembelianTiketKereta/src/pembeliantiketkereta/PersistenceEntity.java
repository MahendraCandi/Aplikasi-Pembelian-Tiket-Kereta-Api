/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pembeliantiketkereta.Form.FormUtama;
/**
 *
 * @author Candi-PC
 */
public class PersistenceEntity {
    public static EntityManagerFactory emf=Persistence.createEntityManagerFactory("PembelianTiketKeretaPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new FormUtama().setVisible(true);
    }
    
}
