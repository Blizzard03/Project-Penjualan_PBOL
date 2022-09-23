/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.penjualan;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LIKMI
 */
public class DBBrg {
    private BrgModel dt=new BrgModel();    
    public BrgModel getBrgModel(){ return(dt);}
    public void setBrgModel(BrgModel s){ dt=s;}
    
    
    
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from barang where kodebrg = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from barang where kodebrg  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }

    
    public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update barang set namabrg = ?, tarif = ?, gambar = ?  where  kodebrg = ?");
            con.preparedStatement.setString(1, getBrgModel().getNamabrg());           
            con.preparedStatement.setDouble(2, getBrgModel().getTarif()); 
            con.preparedStatement.setString(3, getBrgModel().getGambar());
            con.preparedStatement.setString(4, getBrgModel().getKodebrg());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into barang (kodebrg,namabrg,tarif,gambar) values (?,?,?,?)");
            con.preparedStatement.setString(1, getBrgModel().getKodebrg());
            con.preparedStatement.setString(2, getBrgModel().getNamabrg());           
            con.preparedStatement.setDouble(3, getBrgModel().getTarif()); 
            con.preparedStatement.setString(4, getBrgModel().getGambar());           
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
     }
    
    
    public ObservableList<BrgModel>  CariBrg(String kode, String nama) {
        return Show("select * from barang WHERE kodebrg LIKE '" + kode + "%' OR namabrg LIKE '" + nama + "%'");
    }

    public ObservableList<BrgModel>  Load() {
        return Show("Select * from barang");
    }
    
    public ObservableList<BrgModel> Show(String a){
        try {
            ObservableList<BrgModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(a);
            int i = 1;
            while (rs.next()) {
                BrgModel d=new BrgModel();
                d.setKodebrg(rs.getString("kodebrg"));                
                d.setNamabrg(rs.getString("namabrg"));
                d.setTarif(rs.getDouble("tarif"));
                d.setGambar(rs.getString("gambar"));
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    
    }
    
    
}
