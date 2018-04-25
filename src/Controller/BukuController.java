/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.iDataAccess;
import static Interface.iDataAccess.FILE_NAME;
import Model.Buku;
import Model.Penulis;
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
public class BukuController extends Buku implements iDataAccess {
    List<Buku> list = new ArrayList<>();

    @Override
    public void getAllData() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(3);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            
            while(iterator.hasNext()){
                Row currentRow = iterator.next();
                
                Cell idBuku = currentRow.getCell(0);
                Cell judul = currentRow.getCell(1);
                Cell subKat = currentRow.getCell(2);
                Cell write = currentRow.getCell(3);
                Cell pub = currentRow.getCell(4);
                Cell thn = currentRow.getCell(5);
                Cell sinopsis = currentRow.getCell(6);
                Cell harga = currentRow.getCell(7);
                
                Buku book = new Buku();
                book.setIdBuku(idBuku.getStringCellValue());
                book.setJudulBuku(judul.getStringCellValue());
                
                
                book.setSubKategori(super.getSubKategori());
                
                PenulisController wri = new PenulisController();
                book.setPenulis(wri.getElement(write.getStringCellValue()));
                
                PenerbitController publ = new PenerbitController();
                book.setPenerbit(publ.getElement(pub.getStringCellValue()));
                
                book.setTahunTerbit((int) thn.getNumericCellValue());
                book.setSinopsis(sinopsis.getStringCellValue());
                book.setHarga(harga.getNumericCellValue());
                
                this.list.add(book);
            }
        } catch (IOException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void filterList(int filter, String nilai) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
