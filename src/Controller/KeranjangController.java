/*
 * Copyright May - 2018
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package Controller;

import Model.Buku;
import Model.Keranjang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KeranjangController extends Controller{
    private List<Keranjang> daftarBelanja = new ArrayList<>();
    private double Total;
    
    @Override
    void init() {
        /* Do nothing */
    }

    @Override
    public void getAllData() {
        /* Do nothing */
    }

    @Override
    public void printList() {
        int size = this.daftarBelanja.size();
        
        System.out.println("\nID Buku\t\tJudul\t\t\t\tJumlah\t\tHarga\t\tTotal\n");
        for(int i = 0; i < size; i++){
            System.out.print(this.daftarBelanja.get(i).getBuku().getIdBuku()+"\t\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku()+ "\t");
            System.out.print(this.daftarBelanja.get(i).getJumlah()+ "\t\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getHarga()+"\t\t");
            System.out.print(this.daftarBelanja.get(i).getTotal()+"\n");
        }
        hitungTotal();
        System.out.print("\nTotal yang harus dibayar : "+this.Total+"\n");
    }

    @Override
    public void printList(int start, int end) {
        
    }
    
    @Override
    public void printList(int limit) {
        
    }

    @Override
    public void filterList(int filter, String nilai) {
        /*Do nothing*/
    }
    
    public void tambahBarang(String ID_Barang, int Jumlah){
        boolean ketemu=false;
        int i=0;
        BukuController bc = new BukuController();
        Buku b = bc.cariBuku(ID_Barang);
        
        if(this.daftarBelanja.isEmpty() && Jumlah != 0){
            Keranjang k = new Keranjang();
            k.setBuku(b);
            k.setJumlah(Jumlah);
            k.setTotal(b.getHarga()*Jumlah);
            this.daftarBelanja.add(k);
            ketemu = true;
        }
        while(!ketemu && i<this.daftarBelanja.size() && !this.daftarBelanja.isEmpty() && Jumlah != 0){
            if(this.daftarBelanja.get(i).getBuku().getIdBuku().equals(b.getIdBuku())){
                ketemu=true;
                Keranjang k = new Keranjang();
                k.setBuku(b);
                k.setJumlah(this.daftarBelanja.get(i).getJumlah()+Jumlah);
                k.setTotal(b.getHarga()*Jumlah);
                this.daftarBelanja.set(i, k);
            }
            i++;
        }
        if(!ketemu && !this.daftarBelanja.isEmpty() && Jumlah != 0){
            Keranjang k = new Keranjang();
            k.setBuku(b);
            k.setJumlah(Jumlah);
            k.setTotal(b.getHarga()*Jumlah);
            this.daftarBelanja.add(k);
        }
        
    }
    
    public void hitungTotal(){
        double temp=0;
        for(int i=0; i<this.daftarBelanja.size();i++){
            temp = temp + this.daftarBelanja.get(i).getTotal();
        }
        this.Total += temp;
    }
}
