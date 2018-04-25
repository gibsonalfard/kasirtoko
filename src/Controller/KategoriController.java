/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.iDataAccess;
import Model.Kategori;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class KategoriController extends Controller{

    List<Kategori> list = new ArrayList<>();

    public KategoriController() {
        this.init();
    }
    
    @Override
    public void getAllData(){
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(3);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            
            while(iterator.hasNext()){
                Row currentRow = iterator.next();
                
                Cell idKategori = currentRow.getCell(0);
                Cell kat = currentRow.getCell(1);
                
                Kategori kategori = new Kategori();
                kategori.setIdKategori(idKategori.getStringCellValue());
                kategori.setKategori(kat.getStringCellValue());
                
                this.list.add(kategori);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KategoriController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KategoriController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printList() {
        if(this.list.isEmpty()){
            this.getAllData();
        }
        int size = this.list.size();
        
        System.out.println("ID Kategori\tKategori");
        for(int i = 0; i < size; i++){
            System.out.print(this.list.get(i).getIdKategori() + "\t");
            System.out.print(this.list.get(i).getKategori()+ "\n");
        }
    }

    @Override
    public void filterList(int filter, String nilai) {
        
    }

    @Override
    final void init() {
        getAllData();
    }
}
