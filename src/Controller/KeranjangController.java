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
            if (this.daftarBelanja.get(i).getBuku().getJudulBuku().length() > 39) {
                String text = this.daftarBelanja.get(i).getBuku().getJudulBuku();
                text = text.substring(0, 37);
                text += "...";
                System.out.print(text + "\t");
            } else {
                System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku()
                        + (this.daftarBelanja.get(i).getBuku().getJudulBuku().length() > 39 ? "\t"
                        : this.daftarBelanja.get(i).getBuku().getJudulBuku().length() < 30 ? "\t\t"
                        : this.daftarBelanja.get(i).getBuku().getJudulBuku().length() < 24 ? "\t\t\t\t" : "\t\t\t"));
            }
            System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku()+ "\t");
            System.out.print(this.daftarBelanja.get(i).getJumlah()+ "\t\t");
            System.out.print(this.daftarBelanja.get(i).getBuku().getHarga()+"\t\t");
            int harga = (int) this.daftarBelanja.get(i).getTotal();
            System.out.print("Rp " + super.number_format(harga) + "\n");
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
    
    public List<Keranjang> getList(){
        return this.daftarBelanja;
    }
    
    public void delAllList () {
        this.daftarBelanja.clear();
    }
    
    public void setTotalHarga (double total) {
        this.Total = total;
    }
    
    public double getTotalHarga () {
        return Total;
    }
    
    public short getJumlahBuku () {
        short jumlahBuku = 0;
        for (int i = 0; i < this.daftarBelanja.size(); i++) {
            jumlahBuku += this.daftarBelanja.get(i).getJumlah();
        }
        return jumlahBuku;
    }
}
