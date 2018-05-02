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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gibran
 */
public class KasirToko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
<<<<<<< Updated upstream
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
=======

        try {
            //        BukuController buku = new BukuController();
//        buku.filterList(3, "Islam");
//        buku.filterList(4, "ID001");
//        System.out.println("\n\n\n");
//        
//        // untuk testing list keranjang //
//        //System.out.println("List Belanjaan");
//        
//        KeranjangController belanjaan = new KeranjangController();
//        belanjaan.tambahBarang("ID001", 2);
//        belanjaan.tambahBarang("ID002", 9);
//        belanjaan.tambahBarang("ID001", 3); //menambah jumlah dari yang sebelumnya
//        belanjaan.printList();
            KeranjangController belanjaan = new KeranjangController();
            BukuController book = new BukuController();
            String opsi,loop = "";
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("====================================================");
                System.out.println("=======              MENU                   ========");
                System.out.println("====================================================");
                System.out.println("Jumlah Buku dalam Keranjang :" + belanjaan.getList().size() + " \n");
                System.out.println("1. Lihat Data Buku");
                System.out.println("2. Beli Buku");
                System.out.println("3. Liat Keranjang");
                System.out.println("4. Bayar");
                System.out.print("Masukkan Pilihan Anda : ");
                opsi = dataIn.readLine();

                switch (Integer.parseInt(opsi)) {
                    case 1:
                        lihatBuku(belanjaan,book, dataIn);
                    break;
                    case 2:
                        do{
                            beliBuku(belanjaan, dataIn);
                            System.out.print("Ulangi (Y/N) : "); loop = dataIn.readLine();
                        }while(loop.equals("Y") || loop.equals("y"));
                    break;
                    case 3:
                        belanjaan.printList();
                    break;
                    case 4:
                        //DI AFIF
                    break;
                }
            } while (Integer.parseInt(opsi) < 5);
        } catch (IOException ex) {
            Logger.getLogger(KasirToko.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void lihatBuku(KeranjangController cart,BukuController book, BufferedReader dataIn) {
        String opsi = "";
        int halaman = 1;

        do {
            try {
                book.printList(((halaman - 1) * 10) + 1, (halaman * 10));
                System.out.print("\n1. Prev \t\t");
                System.out.print("2. Filter Book \t\t");
                System.out.print("3. Next \t\t\n");
                System.out.println("4. Beli Buku");
                System.out.println("5. Kembali Menu Utama");
                System.out.print("Masukkan Pilihan Anda : ");
                opsi = dataIn.readLine();

                switch (Integer.parseInt(opsi)) {
                    case 1:
                        if (halaman > 1) {
                            halaman -= 1;
                        }
                        break;
                    case 2:
                        filterBuku(cart, book, dataIn);
                        break;
                    case 3:
                        if (halaman * 10 < book.getList().size()) {
                            halaman += 1;
                        }
                        break;
                    case 4:
                        beliBuku(cart, dataIn);
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(KasirToko.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!opsi.equals("5"));
    }

    public static void filterBuku(KeranjangController cart, BukuController book, BufferedReader dataIn) {
        String opsi = "";
        String filter = "";

        do {
            try {
                System.out.println("Filter buku berdasarkan");
                System.out.print("1. Penulis \t\t");
                System.out.print("2. Penerbit \t\t");
                System.out.print("3. Judul \t\t\n");
                System.out.print("4. ID Buku \t\t\n");
                System.out.println("5. Beli Buku");
                System.out.println("6. Kembali Menu Utama");
                System.out.print("Masukkan Pilihan Anda : ");
                opsi = dataIn.readLine();
                
                if(!opsi.equals("5")){
                    System.out.print("Filter : ");
                    filter = dataIn.readLine();
                }

                book.filterList(Integer.parseInt(opsi), filter);
                
                if(opsi.equals("5")){
                    beliBuku(cart, dataIn);
                }
                
                System.out.print("Ulangi (Y/N) : ");
                opsi = dataIn.readLine();
            } catch (IOException ex) {
                Logger.getLogger(KasirToko.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (opsi.equals("Y") || opsi.equals("y"));
    }
    
    public static void beliBuku(KeranjangController cart, BufferedReader dataIn){
        try {
            String opsi = "";
            String jml = "";
            
            System.out.print("\nMasukan Kode Buku : ");   opsi = dataIn.readLine();
            System.out.print("Jumlah Buku : ");   jml = dataIn.readLine();
            cart.tambahBarang(opsi, Integer.parseInt(jml));
            
        } catch (IOException ex) {
            Logger.getLogger(KasirToko.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
>>>>>>> Stashed changes
}
