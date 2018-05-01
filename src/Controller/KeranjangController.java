/*
 * Copyright May - 2018
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package Controller;

import Model.Keranjang;
import Model.Buku;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KeranjangController extends Controller{
    List<Keranjang> daftarBelanja = new ArrayList<>();

    @Override
    void init() {
        
    }

    @Override
    public void getAllData() {
        // sambungkn ke excel
    }

    @Override
    public void printList() {
        if(this.daftarBelanja.isEmpty()){
            this.getAllData();
        }
        int size = this.daftarBelanja.size();
        
        System.out.println("ID Buku\t\tJudul\t\t\tJumlah\n");
        for(int i = 0; i < size; i++){
            System.out.print(this.daftarBelanja.get(i).getBuku().getIdBuku()+"\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku()+ "\t\t\t");
            System.out.print(this.daftarBelanja.get(i).getJumlah()+ "\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getHarga()*this.daftarBelanja.get(i).getJumlah());
        }
    }

    @Override
    public void printList(int start, int end) {
        if(this.daftarBelanja.isEmpty()){
            this.getAllData();
        }
        
        System.out.println("ID Transaksi\t\tTgl Transaksi\t\t\tTotal\n");
        for(int i = start-1; i < end; i++){
            System.out.print(this.daftarBelanja.get(i).getBuku().getIdBuku()+"\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku()+ "\t\t\t");
            System.out.print(this.daftarBelanja.get(i).getJumlah()+ "\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getHarga()*this.daftarBelanja.get(i).getJumlah());
        }
    }
    
    @Override
    public void printList(int limit) {
        if(this.daftarBelanja.isEmpty()){
            this.getAllData();
        }
        
        System.out.println("ID Transaksi\t\tTgl Transaksi\t\t\tTotal\n");
        for(int i = 0; i < limit; i++){
            System.out.print(this.daftarBelanja.get(i).getBuku().getIdBuku()+"\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku()+ "\t\t\t");
            System.out.print(this.daftarBelanja.get(i).getJumlah()+ "\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getHarga()*this.daftarBelanja.get(i).getJumlah());
        }
    }

    @Override
    public void filterList(int filter, String nilai) {
        
    }
    
    
    
}
