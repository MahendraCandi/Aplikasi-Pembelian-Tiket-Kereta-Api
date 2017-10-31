/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "penumpang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Penumpang.findAll", query = "SELECT p FROM Penumpang p"),
    @NamedQuery(name = "Penumpang.findById", query = "SELECT p FROM Penumpang p WHERE p.id = :id"),
    @NamedQuery(name = "Penumpang.findByNoTransaksi", query = "SELECT p FROM Penumpang p WHERE p.noTransaksi = :noTransaksi"),
    @NamedQuery(name = "Penumpang.findByNamaPenumpang", query = "SELECT p FROM Penumpang p WHERE p.namaPenumpang = :namaPenumpang"),
    @NamedQuery(name = "Penumpang.findByNoKtp", query = "SELECT p FROM Penumpang p WHERE p.noKtp = :noKtp")})
public class Penumpang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NoTransaksi")
    private String noTransaksi;
    @Column(name = "NamaPenumpang")
    private String namaPenumpang;
    @Column(name = "NoKtp")
    private String noKtp;

    public Penumpang() {
    }

    public Penumpang(Integer id) {
        this.id = id;
    }

    public Penumpang(Integer id, String noTransaksi) {
        this.id = id;
        this.noTransaksi = noTransaksi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getNamaPenumpang() {
        return namaPenumpang;
    }

    public void setNamaPenumpang(String namaPenumpang) {
        this.namaPenumpang = namaPenumpang;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penumpang)) {
            return false;
        }
        Penumpang other = (Penumpang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pembeliantiketkereta.data.Penumpang[ id=" + id + " ]";
    }
    
}
