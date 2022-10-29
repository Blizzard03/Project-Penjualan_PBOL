/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.DataBaseCustomers;

//Models & Connector Import
import DataBase.Connector.Koneksi;
import Models.Costumers.Custumers_Models;

//Java SQL Import
import java.sql.ResultSet;
import java.sql.SQLException;

//Javafx Collection Import
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LIKMI
 */
public class DataBaseCustumers {

    private Custumers_Models dt = new Custumers_Models();

    public Custumers_Models getCustModel() {
        return (dt);
    }

    public void setCustModel(Custumers_Models s) {
        dt = s;
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from customer where idmember = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into customer (idmember,nama, alamat, total) values (?,?,?,?)");
            con.preparedStatement.setString(1, getCustModel().getIdmember());
            con.preparedStatement.setString(2, getCustModel().getNama());
            con.preparedStatement.setString(3, getCustModel().getAlamat());
            con.preparedStatement.setDouble(4, getCustModel().getTotal());
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

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from customer where idmember  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update customer set nama = ?, alamat = ?, total = ?  where  idmember = ? ");
            con.preparedStatement.setString(1, getCustModel().getNama());
            con.preparedStatement.setString(2, getCustModel().getAlamat());
            con.preparedStatement.setDouble(3, getCustModel().getTotal());
            con.preparedStatement.setString(4, getCustModel().getIdmember());
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

    public ObservableList<Custumers_Models> CariCust(String kode, String nama) {
        return Show("select * from customer WHERE idmember LIKE '" + kode + "%' OR nama LIKE '" + nama + "%'");
    }

    public ObservableList<Custumers_Models> Load() {
        return Show("Select idmember, nama, alamat, total from customer");
    }

    public ObservableList<Custumers_Models> Show(String a) {
        try {
            ObservableList<Custumers_Models> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(a);
            int i = 1;
            while (rs.next()) {
                Custumers_Models d = new Custumers_Models();
                d.setIdmember(rs.getString("idmember"));
                d.setNama(rs.getString("nama"));
                d.setAlamat(rs.getString("alamat"));
                d.setTotal(rs.getDouble("total"));
                double total = rs.getDouble("total");
                String status;

                if (total >= 1000000) {
                    status = "gold";
                } else if (total >= 500000) {
                    status = "silver";
                } else {
                    status = "reguler";
                }
                d.setStatus(status);
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
