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
public class Buku {

    /**
     * @return the idBuku
     */
    public String getIdBuku() {
        return idBuku;
    }

    /**
     * @param idBuku the idBuku to set
     */
    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    /**
     * @return the judulBuku
     */
    public String getJudulBuku() {
        return judulBuku;
    }

    /**
     * @param judulBuku the judulBuku to set
     */
    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    /**
     * @return the subKategori
     */
    public SubKategori getSubKategori() {
        return subKategori;
    }

    /**
     * @param subKategori the subKategori to set
     */
    public void setSubKategori(SubKategori subKategori) {
        this.subKategori = subKategori;
    }

    /**
     * @return the penulis
     */
    public Penulis getPenulis() {
        return penulis;
    }

    /**
     * @param penulis the penulis to set
     */
    public void setPenulis(Penulis penulis) {
        this.penulis = penulis;
    }

    /**
     * @return the penerbit
     */
    public Penerbit getPenerbit() {
        return penerbit;
    }

    /**
     * @param penerbit the penerbit to set
     */
    public void setPenerbit(Penerbit penerbit) {
        this.penerbit = penerbit;
    }

    /**
     * @return the tahunTerbit
     */
    public int getTahunTerbit() {
        return tahunTerbit;
    }

    /**
     * @param tahunTerbit the tahunTerbit to set
     */
    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    /**
     * @return the sinopsis
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * @param sinopsis the sinopsis to set
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    /**
     * @return the harga
     */
    public double getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(double harga) {
        this.harga = harga;
    }
    private String idBuku;
    private String judulBuku;
    private SubKategori subKategori;
    private Penulis penulis;
    private Penerbit penerbit;
    private int tahunTerbit;
    private String sinopsis;
    private double harga;
}
