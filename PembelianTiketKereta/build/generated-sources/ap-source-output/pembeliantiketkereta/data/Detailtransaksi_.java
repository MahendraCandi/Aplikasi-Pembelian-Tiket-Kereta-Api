package pembeliantiketkereta.data;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-31T18:51:08")
@StaticMetamodel(Detailtransaksi.class)
public class Detailtransaksi_ { 

    public static volatile SingularAttribute<Detailtransaksi, Date> tanggalBerangkat;
    public static volatile SingularAttribute<Detailtransaksi, Date> berangkat;
    public static volatile SingularAttribute<Detailtransaksi, String> stasiunTujuan;
    public static volatile SingularAttribute<Detailtransaksi, String> subkelas;
    public static volatile SingularAttribute<Detailtransaksi, String> noTransaksi;
    public static volatile SingularAttribute<Detailtransaksi, Double> harga;
    public static volatile SingularAttribute<Detailtransaksi, String> kodeKereta;
    public static volatile SingularAttribute<Detailtransaksi, String> kotaTujuan;
    public static volatile SingularAttribute<Detailtransaksi, String> kotaAsal;
    public static volatile SingularAttribute<Detailtransaksi, String> stasiunAsal;
    public static volatile SingularAttribute<Detailtransaksi, String> kelas;
    public static volatile SingularAttribute<Detailtransaksi, String> namaKereta;
    public static volatile SingularAttribute<Detailtransaksi, Integer> jumlahPenumpang;
    public static volatile SingularAttribute<Detailtransaksi, Integer> id;
    public static volatile SingularAttribute<Detailtransaksi, String> status;

}