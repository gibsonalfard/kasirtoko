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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String opsi, loop = "";
        /* Updated */
        try {
//            TransaksiController trs = new TransaksiController();
//            
//            String id = trs.generateCode();
//            

//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date = new Date();
//            System.out.println(dateFormat.format(date));
            
            KeranjangController belanjaan = new KeranjangController();
            BukuController book = new BukuController();
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("====================================================");
                System.out.println("=======              MENU                   ========");
                System.out.println("====================================================");
                System.out.println("Jumlah Buku dalam Keranjang :" + belanjaan.getJumlahBuku() + " \n");
                System.out.println("1. Lihat Data Buku");
                System.out.println("2. Beli Buku");
                System.out.println("3. Liat Keranjang");
                System.out.println("4. Bayar");
                System.out.print("Masukkan Pilihan Anda : ");
                opsi = dataIn.readLine();

                switch (Integer.parseInt(opsi)) {
                    case 1:
                        lihatBuku(belanjaan, book, dataIn);
                        break;
                    case 2:
                        do {
                            beliBuku(belanjaan, dataIn);
                            System.out.print("Ulangi (Y/N) : ");
                            loop = dataIn.readLine();
                        } while (loop.equals("Y") || loop.equals("y"));
                        break;
                    case 3:
                        belanjaan.printList();
                        break;
                    case 4:
                        /* Pembayaran */
                        pembayaran(belanjaan);
                        break;
                }
            } while (Integer.parseInt(opsi) < 5);
        } catch (IOException ex) {
            Logger.getLogger(KasirToko.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Pembelian  */
//          System.out.println("List Belanjaan");
//            KeranjangController belanjaan = new KeranjangController();
//            belanjaan.tambahBarang("ID001", 2);
//            belanjaan.tambahBarang("ID002", 9);
//            belanjaan.tambahBarang("ID001", 3); //menambah jumlah dari yang sebelumnya
//            belanjaan.printList();
    }

    public static void lihatBuku(KeranjangController cart, BukuController book, BufferedReader dataIn) {
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
        String opsi = null;
        String filter = null;

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

                if (!opsi.equals("5")) {
                    System.out.print("Filter : ");
                    filter = dataIn.readLine();
                }

                book.filterList(Integer.parseInt(opsi), filter);

                if (opsi.equals("5")) {
                    beliBuku(cart, dataIn);
                }

                System.out.print("Ulangi (Y/N) : ");
                opsi = dataIn.readLine();
            } catch (IOException ex) {
                Logger.getLogger(KasirToko.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (opsi.equals("Y") || opsi.equals("y"));
    }

    public static void beliBuku(KeranjangController cart, BufferedReader dataIn) {
        try {
            String opsi = "";
            String jml = "";

            System.out.print("\nMasukan Kode Buku : ");
            opsi = dataIn.readLine();
            System.out.print("Jumlah Buku : ");
            jml = dataIn.readLine();
            cart.tambahBarang(opsi, Integer.parseInt(jml));

        } catch (IOException ex) {
            Logger.getLogger(KasirToko.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pembayaran(KeranjangController cart) {
//        Buku buku = new Buku();
        TransaksiController tc = new TransaksiController();
        Scanner input = new Scanner(System.in);
        int harga = (int) cart.getTotalHarga();

        System.out.print("\nJumlah yang harus dibayar : "
                + harga
                + "\nMenu Pembayaran yang bisa dipilih :\n"
                + "1.Tunai\n"
                + "2.Kartu Kredit\n"
                + "3.Voucher\n"
                + "4.Transfer\n"
                + "Pilih menu pembayaran : ");
        short i = input.nextShort();

        switch (i) {
            case 1:
                System.out.print("\nSilahkan masukkan nominal uang Anda : ");
                int bayar = input.nextInt();
                int sisa = (int) (bayar - harga);
                if (sisa < 0) {
                    System.out.println("\nUang Anda kurang");
                } else {
                    System.out.println("\nKembalian Anda : " + sisa);
                    cart.setStock();
                    cart.delAllList();
                    cart.setTotalHarga(0);
//                    tc.
                }
                break;
            case 2:
                System.out.print("\nMasukkan No Kartu Kredit Anda : ");
                String creditCard = input.next();
                System.out.println("Kredit Anda bertambah sejumlah : " + harga);
                cart.setStock();
                cart.delAllList();
                cart.setTotalHarga(0);
                break;
            case 3:
                System.out.print("\nSilahkan masukkan No Voucher Anda : ");
                String noVoucher = input.next();
                System.out.println("\nTerima kasih sudah menggunakan Voucher kami");
                cart.setStock();
                cart.delAllList();
                cart.setTotalHarga(0);
                break;
            case 4:
                RekeningController rc = new RekeningController();

                System.out.print("Masukkan No Rekening : ");
                String noRekening = input.next();

                Rekening rekening = rc.getElements(noRekening);
                if (rekening.getNoRekening().equals("0")) {
                    System.out.println("Rekening Anda tidak terdaftar");
                } else {
                    System.out.println("\nData Anda : "
                            + "\nNo Rekening : " + rekening.getNoRekening()
                            + "\nNama Pemilik : " + rekening.getNamaPemilik()
                            + "\nSaldo : " + (int) rekening.getSaldo()
                            + "\nSaldo Anda akan dikurangi sebanyak " + harga);

                    sisa = (int) rekening.getSaldo() - harga;
                    if (sisa < 0) {
                        System.out.println("\nSaldo Anda kurang");
                    } else {
                        rekening.setSaldo(sisa);
                        System.out.println("\nData Anda Sekarang : "
                                + "\nNo Rekening : " + rekening.getNoRekening()
                                + "\nNama Pemilik : " + rekening.getNamaPemilik()
                                + "\nSaldo : " + (int) rekening.getSaldo());
                        cart.setStock();
                        cart.delAllList();
                        cart.setTotalHarga(0);
                    }
                    rc.setData(rekening);
                }
                break;
        }
    }
}
