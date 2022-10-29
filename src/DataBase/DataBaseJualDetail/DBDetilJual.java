/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.penjualan.DataBase.DataBaseJualDetail;

import project.penjualan.DataBase.Connector.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.penjualan.Detil_Jual_Models;

/**
 *
 * @author LIKMI
 */
public class DBDetilJual {
    private Detil_Jual_Models dt=new Detil_Jual_Models();    
    public Detil_Jual_Models getDetilJualModel(){ return(dt);}
    public void setDetilJualModel(Detil_Jual_Models s){ dt=s;}
    public double Total=0;
    
    public ObservableList<Detil_Jual_Models>  Load(String kode) {
        try {
            Total=0;
            ObservableList<Detil_Jual_Models> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select j.nojual,b.kodebrg,b.namabrg,j.jumlah,b.tarif " + 
                    "from detil_jual j join barang b on(j.kodebrg=b.kodebrg) WHERE j.nojual LIKE '" + kode + "%'");
int i = 1;
            while (rs.next()) {
                Detil_Jual_Models d=new Detil_Jual_Models();
                d.setNojual(rs.getString("nojual"));                
                d.setKodebrg(rs.getString("kodebrg"));
                d.setNamabrg(rs.getString("namabrg"));
                d.setJumlah(rs.getInt("jumlah"));  
                d.setTarif(rs.getDouble("tarif"));
                             
                float total=0;
                int jumlah = rs.getInt("jumlah");
                double tarif = rs.getDouble("tarif");
                
                total=jumlah*(float)tarif;
                d.setTotal(total);
                Total+=total;
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
    
     public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from jual_detil where nojual = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual_detil (nojual, kodebrg,jumlah) values (?,?,?)");
            con.preparedStatement.setString(1, getDetilJualModel().getNojual());           
            con.preparedStatement.setString(2, getDetilJualModel().getKodebrg());
            con.preparedStatement.setInt(3, getDetilJualModel().getJumlah());            
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
    
    public boolean delete(String nomor, String kode) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jual_detil where nojual  = ? and kodebrg = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean deleteall(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jual_detil where nojual  = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update jual_detil set kodebrg = ?, jumlah = ?  where  nojual= ? ");
            con.preparedStatement.setString(1, getDetilJualModel().getKodebrg());
            con.preparedStatement.setInt(2, getDetilJualModel().getJumlah());
            con.preparedStatement.setString(3, getDetilJualModel().getNojual());
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
    
    public ObservableList<Detil_Jual_Models>  CariDetil(String kode) {
        try {    
            Total=0;
            ObservableList<Detil_Jual_Models> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select j.nojual,b.kodebrg,b.namabrg,j.jumlah,b.tarif " + 
                    "from detil_jual j join barang b on(j.kodebrg=b.kodebrg) WHERE nojual LIKE '" + kode + "'");
        int i = 1;
        while(rs.next()){
            Detil_Jual_Models d=new Detil_Jual_Models();
                d.setNojual(rs.getString("nojual"));                
                d.setKodebrg(rs.getString("kodebrg"));
                d.setKodebrg(rs.getString("namabrg"));
                d.setJumlah(rs.getInt("jumlah"));  
                d.setTarif(rs.getDouble("tarif"));
                             
                float total=0;
                int jumlah = rs.getInt("jumlah");
                double tarif = rs.getDouble("tarif");
                
                total=jumlah*(float)tarif;
                d.setTotal(total);
                Total += total;
            tableData.add(d);
            i++;
        }
        con.tutupKoneksi();
        return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}