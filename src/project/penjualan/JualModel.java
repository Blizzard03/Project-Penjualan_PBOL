/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.penjualan;

import java.sql.Date;

/**
 *
 * @author LIKMI
 */
public class JualModel {
    private String nojual, idmember, nama;
    private Date tanggal;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    
    public String getNojual() {
        return nojual;
    }

    public void setNojual(String nojual) {
        this.nojual = nojual;
    }

    public String getIdmember() {
        return idmember;
    }

    public void setIdmember(String idmember) {
        this.idmember = idmember;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
    
}
