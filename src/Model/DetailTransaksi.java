/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Gibran
 */
public class DetailTransaksi {

    /**
     * @return the idDetail
     */
    public String getIdDetail() {
        return idDetail;
    }

    /**
     * @param idDetail the idDetail to set
     */
    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    /**
     * @return the transaksi
     */
    public Transaksi getTransaksi() {
        return transaksi;
    }

    /**
     * @param transaksi the transaksi to set
     */
    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    /**
     * @return the barang
     */
    public Buku getBarang() {
        return barang;
    }

    /**
     * @param barang the barang to set
     */
    public void setBarang(Buku barang) {
        this.barang = barang;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
    private String idDetail;
    private Transaksi transaksi;
    private Buku barang;
    private int qty;
    private double total;
}
