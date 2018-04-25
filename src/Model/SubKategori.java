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
public class SubKategori {

    /**
     * @return the idSubkategori
     */
    public String getIdSubkategori() {
        return idSubkategori;
    }

    /**
     * @param idSubkategori the idSubkategori to set
     */
    public void setIdSubkategori(String idSubkategori) {
        this.idSubkategori = idSubkategori;
    }

    /**
     * @return the kategori
     */
    public Kategori getKategori() {
        return kategori;
    }

    /**
     * @param kategori the kategori to set
     */
    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    /**
     * @return the subKategori
     */
    public String getSubKategori() {
        return subKategori;
    }

    /**
     * @param subKategori the subKategori to set
     */
    public void setSubKategori(String subKategori) {
        this.subKategori = subKategori;
    }
    private String idSubkategori;
    private Kategori kategori;
    private String subKategori;
}
