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
public class Kategori {

    /**
     * @return the idKategori
     */
    public String getIdKategori() {
        return idKategori;
    }

    /**
     * @param idKategori the idKategori to set
     */
    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    /**
     * @return the kategori
     */
    public String getKategori() {
        return kategori;
    }

    /**
     * @param kategori the kategori to set
     */
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    private String idKategori;
    private String kategori;
}
