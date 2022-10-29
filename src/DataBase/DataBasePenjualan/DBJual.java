/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.DataBasePenjualan;

import DataBase.Connector.Koneksi;
import Models.Jual.Jual_Models;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LIKMI
 */
public class DBJual {

    private Jual_Models dt = new Jual_Models();

    public Jual_Models getJualModel() {
        return (dt);
    }

    public void setJualModel(Jual_Models s) {
        dt = s;
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from jual where nojual = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jual where nojual  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update jual tanggal = ?, idmember = ?  where  nojual = ?");

            con.preparedStatement.setDate(1, getJualModel().getTanggal());
            con.preparedStatement.setString(2, getJualModel().getIdmember());
            con.preparedStatement.setString(3, getJualModel().getNojual());

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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual (nojual,tanggal,idmember) values (?,?,?)");
            con.preparedStatement.setString(1, getJualModel().getNojual());
            con.preparedStatement.setDate(2, getJualModel().getTanggal());
            con.preparedStatement.setString(3, getJualModel().getIdmember());
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

    public ObservableList<Jual_Models> CariJual(String kode) {
        return Show("select j.nojual, tanggal, c.idmember, c.nama from jual j "
                + "join customer c on(c.idmember = j.idmember) WHERE nojual LIKE '" + kode + "%'");
    }

    public ObservableList<Jual_Models> Load() {
        return Show("Select j.nojual, tanggal, c.idmember, c.nama from jual j "
                + "join customer c on(c.idmember = j.idmember)");
    }

    public ObservableList<Jual_Models> Show(String a) {
        try {
            ObservableList<Jual_Models> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(a);
            int i = 1;
            while (rs.next()) {
                Jual_Models d = new Jual_Models();
                d.setNojual(rs.getString("nojual"));
                d.setIdmember(rs.getString("idmember"));
                d.setTanggal(rs.getDate("tanggal"));
                d.setNama(rs.getString("nama"));
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

    public void CetakReportJual() {
        Koneksi con = new Koneksi();
        String is = "D:\\Kezia\\PBOL\\Penjualan_Kezia\\src\\penjualan_kezia\\reportJual.jrxml";
        Map map = new HashMap();
        map.put("judul", "Laporan Data Barang");
        con.bukaKoneksi();
        try {
            //JasperReport jasperReport = JasperCompileManager.compileReport(is);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,con.dbKoneksi);
            //JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        con.tutupKoneksi();
    }

}
