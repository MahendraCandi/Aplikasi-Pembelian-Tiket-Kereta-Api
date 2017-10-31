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
@Table(name = "detailtransaksi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailtransaksi.findAll", query = "SELECT d FROM Detailtransaksi d"),
    @NamedQuery(name = "Detailtransaksi.findById", query = "SELECT d FROM Detailtransaksi d WHERE d.id = :id"),
    @NamedQuery(name = "Detailtransaksi.findByNoTransaksi", query = "SELECT d FROM Detailtransaksi d WHERE d.noTransaksi = :noTransaksi"),
    @NamedQuery(name = "Detailtransaksi.findByKodeKereta", query = "SELECT d FROM Detailtransaksi d WHERE d.kodeKereta = :kodeKereta"),
    @NamedQuery(name = "Detailtransaksi.findByNamaKereta", query = "SELECT d FROM Detailtransaksi d WHERE d.namaKereta = :namaKereta"),
    @NamedQuery(name = "Detailtransaksi.findByKotaAsal", query = "SELECT d FROM Detailtransaksi d WHERE d.kotaAsal = :kotaAsal"),
    @NamedQuery(name = "Detailtransaksi.findByStasiunAsal", query = "SELECT d FROM Detailtransaksi d WHERE d.stasiunAsal = :stasiunAsal"),
    @NamedQuery(name = "Detailtransaksi.findByKotaTujuan", query = "SELECT d FROM Detailtransaksi d WHERE d.kotaTujuan = :kotaTujuan"),
    @NamedQuery(name = "Detailtransaksi.findByStasiunTujuan", query = "SELECT d FROM Detailtransaksi d WHERE d.stasiunTujuan = :stasiunTujuan"),
    @NamedQuery(name = "Detailtransaksi.findByKelas", query = "SELECT d FROM Detailtransaksi d WHERE d.kelas = :kelas"),
    @NamedQuery(name = "Detailtransaksi.findBySubkelas", query = "SELECT d FROM Detailtransaksi d WHERE d.subkelas = :subkelas"),
    @NamedQuery(name = "Detailtransaksi.findByTanggalBerangkat", query = "SELECT d FROM Detailtransaksi d WHERE d.tanggalBerangkat = :tanggalBerangkat"),
    @NamedQuery(name = "Detailtransaksi.findByBerangkat", query = "SELECT d FROM Detailtransaksi d WHERE d.berangkat = :berangkat"),
    @NamedQuery(name = "Detailtransaksi.findByHarga", query = "SELECT d FROM Detailtransaksi d WHERE d.harga = :harga"),
    @NamedQuery(name = "Detailtransaksi.findByJumlahPenumpang", query = "SELECT d FROM Detailtransaksi d WHERE d.jumlahPenumpang = :jumlahPenumpang"),
    @NamedQuery(name = "Detailtransaksi.findByStatus", query = "SELECT d FROM Detailtransaksi d WHERE d.status = :status")})
public class Detailtransaksi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NoTransaksi")
    private String noTransaksi;
    @Basic(optional = false)
    @Column(name = "KodeKereta")
    private String kodeKereta;
    @Column(name = "NamaKereta")
    private String namaKereta;
    @Column(name = "KotaAsal")
    private String kotaAsal;
    @Column(name = "StasiunAsal")
    private String stasiunAsal;
    @Column(name = "KotaTujuan")
    private String kotaTujuan;
    @Column(name = "StasiunTujuan")
    private String stasiunTujuan;
    @Column(name = "Kelas")
    private String kelas;
    @Column(name = "Subkelas")
    private String subkelas;
    @Column(name = "TanggalBerangkat")
    @Temporal(TemporalType.DATE)
    private Date tanggalBerangkat;
    @Column(name = "Berangkat")
    @Temporal(TemporalType.TIME)
    private Date berangkat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Harga")
    private Double harga;
    @Column(name = "JumlahPenumpang")
    private Integer jumlahPenumpang;
    @Column(name = "Status")
    private String status;

    public Detailtransaksi() {
    }

    public Detailtransaksi(Integer id) {
        this.id = id;
    }

    public Detailtransaksi(Integer id, String noTransaksi, String kodeKereta) {
        this.id = id;
        this.noTransaksi = noTransaksi;
        this.kodeKereta = kodeKereta;
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

    public String getKodeKereta() {
        return kodeKereta;
    }

    public void setKodeKereta(String kodeKereta) {
        this.kodeKereta = kodeKereta;
    }

    public String getNamaKereta() {
        return namaKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
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

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getSubkelas() {
        return subkelas;
    }

    public void setSubkelas(String subkelas) {
        this.subkelas = subkelas;
    }

    public Date getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(Date tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public Date getBerangkat() {
        return berangkat;
    }

    public void setBerangkat(Date berangkat) {
        this.berangkat = berangkat;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getJumlahPenumpang() {
        return jumlahPenumpang;
    }

    public void setJumlahPenumpang(Integer jumlahPenumpang) {
        this.jumlahPenumpang = jumlahPenumpang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Detailtransaksi)) {
            return false;
        }
        Detailtransaksi other = (Detailtransaksi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pembeliantiketkereta.data.Detailtransaksi[ id=" + id + " ]";
    }
    
}
