package pembeliantiketkereta.data;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-31T18:51:09")
@StaticMetamodel(Kereta.class)
public class Kereta_ { 

    public static volatile SingularAttribute<Kereta, Date> tiba;
    public static volatile SingularAttribute<Kereta, Double> harga;
    public static volatile SingularAttribute<Kereta, String> kodeKereta;
    public static volatile SingularAttribute<Kereta, String> kotaTujuan;
    public static volatile SingularAttribute<Kereta, Date> berangkat;
    public static volatile SingularAttribute<Kereta, String> kotaAsal;
    public static volatile SingularAttribute<Kereta, String> stasiunAsal;
    public static volatile SingularAttribute<Kereta, String> kelas;
    public static volatile SingularAttribute<Kereta, String> namaKereta;
    public static volatile SingularAttribute<Kereta, String> stasiunTujuan;
    public static volatile SingularAttribute<Kereta, Integer> id;
    public static volatile SingularAttribute<Kereta, String> subKelas;

}