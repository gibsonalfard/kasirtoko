/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirtoko;

import Controller.BukuController;
import Controller.Controller;
import Controller.DetailTransaksiController;
import Controller.KategoriController;
import Controller.PenerbitController;
import Controller.PenulisController;
import Controller.SubKategoriController;
import Controller.TransaksiController;

/**
 *
 * @author Gibran
 */
public class KasirToko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller kc = new PenerbitController();
        
        kc.getAllData();
        kc.printList();
        System.out.println("\n");
        
        BukuController buku = new BukuController();
        buku.printList();
        System.out.println("\n");
        
        PenulisController penulis = new PenulisController();
        penulis.printList();
        System.out.println("\n");
        
        PenerbitController penerbit = new PenerbitController();
        penerbit.printList();
        System.out.println("\n");
        
        KategoriController kategori = new KategoriController();
        kategori.printList();
        System.out.println("\n");
         
        SubKategoriController subKat = new SubKategoriController();
        subKat.printList();
        System.out.println("\n");
          
        TransaksiController trk = new TransaksiController();
        trk.printList();
        System.out.println("\n");
        
        DetailTransaksiController detailTransaksi = new DetailTransaksiController();
        detailTransaksi.printList();
        System.out.println("\n");
    }
    
}
