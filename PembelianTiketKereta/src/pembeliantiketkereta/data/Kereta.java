/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "kereta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kereta.findAll", query = "SELECT k FROM Kereta k"),
    @NamedQuery(name = "Kereta.findById", query = "SELECT k FROM Kereta k WHERE k.id = :id"),
    @NamedQuery(name = "Kereta.findByKodeKereta", query = "SELECT k FROM Kereta k WHERE k.kodeKereta = :kodeKereta"),
    @NamedQuery(name = "Kereta.findByKotaAsal", query = "SELECT k FROM Kereta k WHERE k.kotaAsal = :kotaAsal"),
    @NamedQuery(name = "Kereta.findByStasiunAsal", query = "SELECT k FROM Kereta k WHERE k.stasiunAsal = :stasiunAsal"),
    @NamedQuery(name = "Kereta.findByKotaTujuan", query = "SELECT k FROM Kereta k WHERE k.kotaTujuan = :kotaTujuan"),
    @NamedQuery(name = "Kereta.findByStasiunTujuan", query = "SELECT k FROM Kereta k WHERE k.stasiunTujuan = :stasiunTujuan"),
    @NamedQuery(name = "Kereta.findByNamaKereta", query = "SELECT k FROM Kereta k WHERE k.namaKereta = :namaKereta"),
    @NamedQuery(name = "Kereta.findByBerangkat", query = "SELECT k FROM Kereta k WHERE k.berangkat = :berangkat"),
    @NamedQuery(name = "Kereta.findByTiba", query = "SELECT k FROM Kereta k WHERE k.tiba = :tiba"),
    @NamedQuery(name = "Kereta.findByKelas", query = "SELECT k FROM Kereta k WHERE k.kelas = :kelas"),
    @NamedQuery(name = "Kereta.findBySubKelas", query = "SELECT k FROM Kereta k WHERE k.subKelas = :subKelas"),
    @NamedQuery(name = "Kereta.findByHarga", query = "SELECT k FROM Kereta k WHERE k.harga = :harga")})
public class Kereta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "KodeKereta")
    private String kodeKereta;
    @Column(name = "KotaAsal")
    private String kotaAsal;
    @Column(name = "StasiunAsal")
    private String stasiunAsal;
    @Column(name = "KotaTujuan")
    private String kotaTujuan;
    @Column(name = "StasiunTujuan")
    private String stasiunTujuan;
    @Column(name = "NamaKereta")
    private String namaKereta;
    @Column(name = "Berangkat")
    @Temporal(TemporalType.TIME)
    private Date berangkat;
    @Column(name = "Tiba")
    @Temporal(TemporalType.TIME)
    private Date tiba;
    @Column(name = "Kelas")
    private String kelas;
    @Column(name = "SubKelas")
    private String subKelas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Harga")
    private Double harga;

    public Kereta() {
    }

    public Kereta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKodeKereta() {
        return kodeKereta;
    }

    public void setKodeKereta(String kodeKereta) {
        this.kodeKereta = kodeKereta;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getStasiunAsal() {
        return stasiunAsal;
    }

    public void setStasiunAsal(String stasiunAsal) {
        this.stasiunAsal = stasiunAsal;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getStasiunTujuan() {
        return stasiunTujuan;
    }

    public void setStasiunTujuan(String stasiunTujuan) {
        this.stasiunTujuan = stasiunTujuan;
    }

    public String getNamaKereta() {
        return namaKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }

    public Date getBerangkat() {
        return berangkat;
    }

    public void setBerangkat(Date berangkat) {
        this.berangkat = berangkat;
    }

    public Date getTiba() {
        return tiba;
    }

    public void setTiba(Date tiba) {
        this.tiba = tiba;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getSubKelas() {
        return subKelas;
    }

    public void setSubKelas(String subKelas) {
        this.subKelas = subKelas;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
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
        if (!(object instanceof Kereta)) {
            return false;
        }
        Kereta other = (Kereta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pembeliantiketkereta.data.Kereta[ id=" + id + " ]";
    }
    
}
