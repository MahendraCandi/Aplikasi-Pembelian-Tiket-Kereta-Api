/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembeliantiketkereta.Form;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pembeliantiketkereta.data.Kereta;
/**
 *
 * @author Candi-PC
 */
public class TableModel extends AbstractTableModel{
    private static final long serialVersionUID  = 1L;
    private List<Kereta> list= new ArrayList<Kereta>();
    public void updateAll(Collection<Kereta>krt){
        list.clear();
        list.addAll(krt);
        fireTableDataChanged();
    }
    
    public Kereta get(int index){
        return list.get(index);
    } 
    
    public void setData(List list){
        this.list=list;
        fireTableDataChanged();
    }
    
    public void insert(Kereta krt){
        list.add(krt);
        fireTableRowsInserted(getRowCount() -1, getRowCount() -1);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index,index);
    }
    
    public void update(int index, Kereta krt){
        list.set(index, krt);
        fireTableRowsUpdated(index,index);
    }
    
    public Kereta select(int index){
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getKodeKereta();
            case 1:
                return list.get(rowIndex).getNamaKereta();
            case 2:
                return list.get(rowIndex).getKotaAsal();
            case 3:
                return list.get(rowIndex).getStasiunAsal();
            case 4:
                return list.get(rowIndex).getKotaTujuan();
            case 5:
                return list.get(rowIndex).getStasiunTujuan();
            case 6:
                return list.get(rowIndex).getBerangkat();
            case 7:
                return list.get(rowIndex).getTiba();
            case 8:
                return list.get(rowIndex).getKelas();
            case 9:
                return list.get(rowIndex).getSubKelas();
            case 10:
                return list.get(rowIndex).getHarga();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "Kode";
            case 1:
                return "Nama Kereta";
            case 2:
                return "Kota Asal";
            case 3:
                return "Stasiun Asal";
            case 4:
                return "Kota Tujuan";
            case 5:
                return "Stasiun Tujuan";
            case 6:
                return "Berangkat";
            case 7:
                return "Tiba";
            case 8:
                return "Kelas";
            case 9:
                return "Sub";
            case 10:
                return "Harga";
            
            default:
                return null;
        }
    }
    
    
}
