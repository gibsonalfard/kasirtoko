/*
 * Copyright May - 2018
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package Controller;

import static Interface.iDataAccess.FILE_NAME;
import Model.Buku;
import Model.Keranjang;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class KeranjangController extends Controller {

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

        System.out.println("\nID\tJudul\t\t\t\t\t\tJumlah\t\tHarga\t\tTotal\n");
//        for (int i = 0; i < size; i++) {
//            System.out.print(this.daftarBelanja.get(i).getBuku().getIdBuku() + "\t");
//            if (this.daftarBelanja.get(i).getBuku().getJudulBuku().length() > 39) {
//                String text = this.daftarBelanja.get(i).getBuku().getJudulBuku();
//                text = text.substring(0, 37);
//                text += "...";
//                System.out.print(text + "\t");
//            } else {
//                System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku()
//                        + (this.daftarBelanja.get(i).getBuku().getJudulBuku().length() > 39 ? "\t"
//                        : this.daftarBelanja.get(i).getBuku().getJudulBuku().length() < 30 ? "\t\t"
//                        : this.daftarBelanja.get(i).getBuku().getJudulBuku().length() < 24 ? "\t\t\t\t" : "\t\t\t\t\t\t"));
//            }
////            System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku() + "\t");
//            System.out.print(this.daftarBelanja.get(i).getJumlah() + "\t\t");
//            System.out.print(this.daftarBelanja.get(i).getBuku().getHarga() + "\t\t");
//            int harga = (int) this.daftarBelanja.get(i).getTotal();
//            System.out.print("Rp " + super.number_format(harga) + "\n");
//        }
        for (int i = 0; i < size; i++) {
            System.out.print(this.daftarBelanja.get(i).getBuku().getIdBuku() + "\t");
            if(this.daftarBelanja.get(i).getBuku().getJudulBuku().length()<8){
                System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku() + "\t\t\t\t\t\t");
            }
            else if(this.daftarBelanja.get(i).getBuku().getJudulBuku().length()<16){
                System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku() + "\t\t\t\t\t");
            }
            else if(this.daftarBelanja.get(i).getBuku().getJudulBuku().length()<24){
                System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku() + "\t\t\t\t");
            }
            else if(this.daftarBelanja.get(i).getBuku().getJudulBuku().length()<32){
                System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku() + "\t\t\t");
            }
            else if(this.daftarBelanja.get(i).getBuku().getJudulBuku().length()<40){
                System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku() + "\t\t");
            }
            else
                System.out.print(this.daftarBelanja.get(i).getBuku().getJudulBuku().substring(0, 40)+"...\t");
            
            
            System.out.print(this.daftarBelanja.get(i).getJumlah() + "\t\t");
            System.out.print("Rp" + super.number_format((int) this.daftarBelanja.get(i).getBuku().getHarga()) + "\t");
            System.out.print("Rp " + super.number_format((int) this.daftarBelanja.get(i).getTotal()) + "\n");
        }
        System.out.print("\nTotal yang harus dibayar : "+super.number_format((int) this.getTotalHarga())+"\n");
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

    public void tambahBarang(String ID_Barang, int Jumlah) {
        boolean ketemu = false;
        int i = 0;
        BukuController bc = new BukuController();
        Keranjang k = new Keranjang();
        Buku b = bc.cariBuku(ID_Barang);

        if (this.daftarBelanja.isEmpty() && Jumlah != 0) {

            b.setStok(b.getStok() - Jumlah);
            k.setJumlah(Jumlah);
            k.setTotal(b.getHarga() * Jumlah);
            k.setBuku(b);
            this.daftarBelanja.add(k);
            ketemu = true;
        }
        while (!ketemu && i < this.daftarBelanja.size() && !this.daftarBelanja.isEmpty() && Jumlah != 0) {
            if (this.daftarBelanja.get(i).getBuku().getIdBuku().equals(b.getIdBuku())) {
                ketemu = true;
                b.setStok(b.getStok() - Jumlah);
                k.setBuku(b);
                k.setJumlah(this.daftarBelanja.get(i).getJumlah() + Jumlah);
                k.setTotal(b.getHarga() * Jumlah);
                this.daftarBelanja.set(i, k);
            }
            i++;
        }
        if (!ketemu && !this.daftarBelanja.isEmpty() && Jumlah != 0) {
            b.setStok(b.getStok() - Jumlah);
            k.setBuku(b);
            k.setJumlah(Jumlah);
            k.setTotal(b.getHarga() * Jumlah);
            this.daftarBelanja.add(k);
        }
        hitungTotal(k.getTotal());
    }

    public void hitungTotal(double harga) {
//        double temp = 0;
//        for (int i = 0; i < this.daftarBelanja.size(); i++) {
//            temp = temp + this.daftarBelanja.get(i).getTotal();
//        }
//        this.Total += temp;
          this.Total += harga;
    }

    public List<Keranjang> getList() {
        return this.daftarBelanja;
    }

    public void delAllList() {
        this.daftarBelanja.clear();
    }

    public void setTotalHarga(double total) {
        this.Total = total;
    }

    public double getTotalHarga() {
        return Total;
    }

    public short getJumlahBuku() {
        short jumlahBuku = 0;
        for (int i = 0; i < this.daftarBelanja.size(); i++) {
            jumlahBuku += this.daftarBelanja.get(i).getJumlah();
        }
        return jumlahBuku;
    }

    public void setStock(Buku buku) {
        BukuController bc = new BukuController();
        //System.out.println ("\nMasuk modul..\n");
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            for (int i = 0; i < this.daftarBelanja.size(); i++) {
                buku = bc.getElements(this.daftarBelanja.get(i).getBuku().getIdBuku());
//                System.out.println("\n" + buku.getIdBuku());

//                System.out.println("\nMasuk FOR\n");
                while (iterator.hasNext() && !(buku.getIdBuku()).equals("000")) {
                    Row currentRow = iterator.next();

                    Cell idBuku = currentRow.getCell(0);
//                    System.out.println("\n Nilai Stok List Keranjang : " + this.daftarBelanja.get(i).getBuku().getStok()
//                            + "\n Nilai Stok List Buku : " + buku.getStok());
//                    System.out.println("\n ID Buku List Keranjang : " + this.daftarBelanja.get(i).getBuku().getIdBuku()
//                            + "\n ID Buku List Buku : " + buku.getIdBuku()
//                            + "\n ID Buku dari Cell : " + idBuku.getStringCellValue());

                    if (buku.getIdBuku().equals(idBuku.getStringCellValue())) {
                        currentRow.getCell(8).setCellValue(this.daftarBelanja.get(i).getBuku().getStok());
//                        bc.getList().get(i).setStok(this.daftarBelanja.get(i).getBuku().getStok());
//                        System.out.println("\nIf masukk");
                        try {
                            FileOutputStream output = new FileOutputStream(new File(FILE_NAME));
                            workbook.write(output);
                            workbook.close();
//                            System.out.println("\nOutput masukk");
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KeranjangController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KeranjangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
