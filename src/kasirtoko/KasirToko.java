/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirtoko;

import Controller.BukuController;
import Controller.DetailTransaksiController;
import Controller.KategoriController;
import Controller.KeranjangController;
import Controller.PenerbitController;
import Controller.PenulisController;
import Controller.RekeningController;
import Controller.SubKategoriController;
import Controller.TransaksiController;
import Model.Rekening;
import java.util.Scanner;

/**
 *
 * @author Gibran
 */
public class KasirToko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
//        BukuController buku = new BukuController();
//        buku.printList();
//        System.out.println("\n");
//        
        PenulisController penulis = new PenulisController();
        penulis.printList();
        System.out.println("\n");
//        
//        PenerbitController penerbit = new PenerbitController();
//        penerbit.printList();
//        System.out.println("\n");
//        
//        KategoriController kc = new KategoriController();
//        kc.printList();
//        System.out.println("\n");
//         
//        SubKategoriController subKat = new SubKategoriController();
//        subKat.printList();
//        System.out.println("\n");
//          
//        TransaksiController trk = new TransaksiController();
//        trk.printList();
//        System.out.println("\n");
//        
//        DetailTransaksiController detailTransaksi = new DetailTransaksiController();
//        detailTransaksi.printList();
//        System.out.println("\n");

          /* Pembelian  */
          System.out.println("List Belanjaan");
            KeranjangController belanjaan = new KeranjangController();
            belanjaan.tambahBarang("ID001", 2);
            belanjaan.tambahBarang("ID002", 9);
            belanjaan.tambahBarang("ID001", 3); //menambah jumlah dari yang sebelumnya
            belanjaan.printList();
        
          /* Pembayaran */
          System.out.print("Menu Pembayaran yang bisa dipilih :\n"
                + "1.Tunai\n"
                + "2.Kartu Kredit\n"
                + "3.Voucher\n"
                + "4.Transfer\n"
                + "Pilih menu pembayaran : ");
        short i = input.nextShort();
        int harga = 10000000;
        
        switch (i) {
            case 1 : 
                System.out.print("\nSilahkan masukkan nominal uang Anda : ");
                int bayar = input.nextInt();
                int sisa = (int) (bayar - harga);
                if (sisa < 0) {
                    System.out.println("\nUang Anda kurang");
                } else {
                    System.out.println("\nKembalian Anda : " + sisa);
                }
                break;
            case 2 : 
                System.out.print("\nMasukkan No Kartu Kredit Anda : ");
                String creditCard = input.next();
                System.out.println("Kredit Anda bertambah sejumlah : "+harga);
                break;
            case 3 : 
                System.out.print("\nSilahkan masukkan No Voucher Anda : ");
                String noVoucher = input.next();
                System.out.println("Terima kasih sudah menggunakan Voucher kami");
                break;
            case 4 :
                RekeningController rc = new RekeningController();
                
                System.out.print("Masukkan No Rekening : ");
                String noRekening = input.next();
                
                Rekening rekening = rc.getElements(noRekening);                
                System.out.println("\nData Anda : "
                        + "\nNo Rekening : " + rekening.getNoRekening()
                        + "\nNama Pemilik : " + rekening.getNamaPemilik()
                        + "\nSaldo : " + (int) rekening.getSaldo()
                        + "\nSaldo Anda akan dikurangi sebanyak " + harga);
                
                sisa = (int) rekening.getSaldo() - harga;
                if (sisa < 0) {
                    System.out.println ("\nSaldo Anda kurang");
                } else {
                    rekening.setSaldo(sisa);
                    System.out.println("\nData Anda Sekarang : "
                        + "\nNo Rekening : " + rekening.getNoRekening()
                        + "\nNama Pemilik : " + rekening.getNamaPemilik()
                        + "\nSaldo : " + (int) rekening.getSaldo());
                }                
                rc.setData(rekening);
                break;
        }
    }
}
