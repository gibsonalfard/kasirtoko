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
public class Penerbit {

    /**
     * @return the idPenerbit
     */
    public String getIdPenerbit() {
        return idPenerbit;
    }

    /**
     * @param idPenerbit the idPenerbit to set
     */
    public void setIdPenerbit(String idPenerbit) {
        this.idPenerbit = idPenerbit;
    }

    /**
     * @return the namaPenerbit
     */
    public String getNamaPenerbit() {
        return namaPenerbit;
    }

    /**
     * @param namaPenerbit the namaPenerbit to set
     */
    public void setNamaPenerbit(String namaPenerbit) {
        this.namaPenerbit = namaPenerbit;
    }
    private String idPenerbit;
    private String namaPenerbit;
}
