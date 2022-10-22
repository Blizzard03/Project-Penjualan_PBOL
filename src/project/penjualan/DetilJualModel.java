/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.penjualan;

/**
 *
 * @author LIKMI
 */
public class DetilJualModel {
    private String nojual, kodebrg, namabrg; 
    private int jumlah;
    private double tarif;
    private float total;

    public String getNamabrg() {
        return namabrg;
    }

    public void setNamabrg(String namabrg) {
        this.namabrg = namabrg;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    

    public String getNojual() {
        return nojual;
    }

    public void setNojual(String nojual) {
        this.nojual = nojual;
    }

    public String getKodebrg() {
        return kodebrg;
    }

    public void setKodebrg(String kodebrg) {
        this.kodebrg = kodebrg;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    
}
