/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Interface.iDataAccess.FILE_NAME;
import Model.Buku;
import java.io.File;
import java.io.FileInputStream;
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
 * @author Gibran
 */
public class BukuController extends Controller {

    List<Buku> list = new ArrayList<>();

    public BukuController() {
        this.init();
    }
    
    public List<Buku> getList(){
        return this.list;
    }
    
    @Override
    public void getAllData() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                Cell idBuku = currentRow.getCell(0);
                Cell judul = currentRow.getCell(1);
                Cell subKat = currentRow.getCell(2);
                Cell write = currentRow.getCell(3);
                Cell pub = currentRow.getCell(4);
                Cell thn = currentRow.getCell(5);
                Cell sinopsis = currentRow.getCell(6);
                Cell harga = currentRow.getCell(7);
                Cell stok = currentRow.getCell(8);

                Buku book = new Buku();
                book.setIdBuku(idBuku.getStringCellValue());
                book.setJudulBuku(judul.getStringCellValue());

                SubKategoriController sub = new SubKategoriController();
                book.setSubKategori(sub.getElement(subKat.getStringCellValue()));

                PenulisController wri = new PenulisController();
                book.setPenulis(wri.getElement(write.getStringCellValue()));

                PenerbitController publ = new PenerbitController();
                book.setPenerbit(publ.getElement(pub.getStringCellValue()));

                book.setTahunTerbit((int) thn.getNumericCellValue());
                book.setSinopsis(sinopsis.getStringCellValue());
                book.setHarga(harga.getNumericCellValue());
                book.setStok((int) stok.getNumericCellValue());
                
                book.setStok((int)stokBuku.getNumericCellValue());

                this.list.add(book);
            }
        } catch (IOException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printList() {
        if (this.list.isEmpty()) {
            this.getAllData();
        }
        int size = this.list.size();

        System.out.println("ID Buku\tJudul Buku\t\t\tSub Kategori\tPenulis\t\tPenerbit\tTahun Terbit\tSinopsis\tHarga\n");
        for (int i = 0; i < size; i++) {
            System.out.print(this.list.get(i).getIdBuku() + "\t");
            System.out.print(this.list.get(i).getJudulBuku() + "\t\t");
            System.out.print(this.list.get(i).getSubKategori().getSubKategori() + "\t");
            System.out.print(this.list.get(i).getPenulis().getNamaPenulis() + "\t");
            System.out.print(this.list.get(i).getPenerbit().getNamaPenerbit() + "\t");
            System.out.print(this.list.get(i).getTahunTerbit() + "\t");
            System.out.print(this.list.get(i).getSinopsis() + "\t");
            int harga = (int) this.list.get(i).getHarga();
            System.out.print("Rp " + super.number_format(harga) + "\n");
        }
    }

    @Override
    public void printList(int start, int end) {
        if (this.list.isEmpty()) {
            this.getAllData();
        }
        int size = this.list.size();

        System.out.println("ID Buku\tJudul Buku\t\t\t\t\tSub Kategori\tPenulis\t\t\t\tHarga\t\tStok Barang");
        for (int i = start - 1; i < end; i++) {
            System.out.print(this.list.get(i).getIdBuku() + "\t");
            if(this.list.get(i).getJudulBuku().length() > 39){
                String text = this.list.get(i).getJudulBuku();
                text = text.substring(0,37);
                text = text + "...";
                System.out.print(text + "\t");
            }else{
                System.out.print(this.list.get(i).getJudulBuku() 
                    + (this.list.get(i).getJudulBuku().length() > 39 ? "\t" 
                      : (this.list.get(i).getJudulBuku().length() > 30) ? "\t\t" 
                      : (this.list.get(i).getJudulBuku().length() < 24) ? "\t\t\t\t" : "\t\t\t"));
            }
            System.out.print(this.list.get(i).getSubKategori().getSubKategori() + "\t");
            System.out.print(this.list.get(i).getPenulis().getNamaPenulis()
                    + (this.list.get(i).getPenulis().getNamaPenulis().length() > 23 ? "\t" 
                    : (this.list.get(i).getPenulis().getNamaPenulis().length() < 15) ? "\t\t\t" : "\t\t"));
//            System.out.print(this.list.get(i).getPenerbit().getNamaPenerbit() + "\t");
//            System.out.print(this.list.get(i).getTahunTerbit() + "\t");
//            System.out.print(this.list.get(i).getSinopsis() + "\t");
            int harga = (int) this.list.get(i).getHarga();
            System.out.print("Rp " + super.number_format(harga) + "\t\t");
            System.out.print(this.list.get(i).getStok() + "\n");
        }
    }

    @Override
    public void printList(int limit) {
        if (this.list.isEmpty()) {
            this.getAllData();
        }
        int size = this.list.size();

        System.out.println("ID Buku\tJudul Buku\t\t\tSub Kategori\tPenulis\t\tPenerbit\tTahun Terbit\tSinopsis\tHarga\n");
        for (int i = 0; i < limit; i++) {
            System.out.print(this.list.get(i).getIdBuku() + "\t");
            System.out.print(this.list.get(i).getJudulBuku() + "\t\t");
            System.out.print(this.list.get(i).getSubKategori().getSubKategori() + "\t");
            System.out.print(this.list.get(i).getPenulis().getNamaPenulis() + "\t");
            System.out.print(this.list.get(i).getPenerbit().getNamaPenerbit() + "\t");
            System.out.print(this.list.get(i).getTahunTerbit() + "\t");
            System.out.print(this.list.get(i).getSinopsis() + "\t");
            int harga = (int) this.list.get(i).getHarga();
            System.out.print("Rp " + super.number_format(harga) + "\n");
        }
    }

    @Override
    public void filterList(int filter, String nilai) {
        boolean kondisi = false;
        int size = this.list.size();
        nilai = nilai.toLowerCase();

        for (int i = 0; i < size; i++) {
            switch (filter) {
                case 1: //Penulis
                    kondisi = this.list.get(i).getPenulis().getNamaPenulis().toLowerCase().contains(nilai);
                    break;
                case 2: //Penerbit
                    kondisi = this.list.get(i).getPenerbit().getNamaPenerbit().toLowerCase().contains(nilai);
                    break;
                case 3: //Judul
                    kondisi = this.list.get(i).getJudulBuku().toLowerCase().contains(nilai);
                    break;
                case 4: //ID
                    kondisi = this.list.get(i).getIdBuku().toLowerCase().contains(nilai);
                    break;
            }

            if (kondisi) {
                System.out.print(this.list.get(i).getIdBuku() + "\t");
                System.out.print(this.list.get(i).getJudulBuku() + "\t\t");
                System.out.print(this.list.get(i).getSubKategori().getSubKategori() + "\t");
                System.out.print(this.list.get(i).getPenulis().getNamaPenulis() + "\t");
                System.out.print(this.list.get(i).getPenerbit().getNamaPenerbit() + "\t");
                System.out.print(this.list.get(i).getTahunTerbit() + "\t");
                System.out.print(this.list.get(i).getSinopsis() + "\t");
                int harga = (int) this.list.get(i).getHarga();
                System.out.print("Rp " + super.number_format(harga) + "\n");
            }
        }
    }

    public Buku getElements(String id) {
        Buku buku = new Buku();

        if (this.list.isEmpty()) {
            getAllData();
        }

        int i = 0;
        boolean ketemu = false;
        while (i < list.size() && !ketemu) {
            String ID = list.get(i).getIdBuku();

            if (ID.equals(id)) {
                ketemu = true;
                buku = list.get(i);
            }
            i += 1;
        }
        return buku;
    }

    @Override
    final void init() {
        getAllData();
    }

    public Buku cariBuku(String ID_Buku) {
        boolean kondisi = false;
        int i = 0;
        ID_Buku = ID_Buku.toLowerCase();

        while (!kondisi && i < this.list.size()) {
            kondisi = this.list.get(i).getIdBuku().toLowerCase().contains(ID_Buku);
            i++;
        }

        if (!kondisi) {
            return null;
        } else {
            return this.list.get(i - 1);
        }
    }

}
