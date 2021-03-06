/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Interface.iDataAccess.FILE_NAME;
import Model.SubKategori;
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
 * @author ACER Z3-451
 */
public class SubKategoriController extends Controller{
    
    List<SubKategori> list = new ArrayList<>();

    public SubKategoriController() {
        init();
    }
    
    @Override
    public void getAllData() {
        
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(4);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            
            while(iterator.hasNext()){
                Row currentRow = iterator.next();
                
                Cell idSubKategori = currentRow.getCell(0);
                Cell kat = currentRow.getCell(1);
                Cell subKat = currentRow.getCell(2);
                
                SubKategori subKategori = new SubKategori();
                subKategori.setIdSubkategori(idSubKategori.getStringCellValue());
                
                KategoriController kategori1 = new KategoriController();          
                subKategori.setKategori(kategori1.getElement(kat.getStringCellValue()));
                subKategori.setSubKategori(subKat.getStringCellValue());
                
                this.list.add(subKategori);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SubKategoriController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SubKategoriController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    @Override
    public void printList() {
        if (this.list.isEmpty()) {
            this.getAllData();
        }
        
        int size = this.list.size();
        
        System.out.println("ID SubKategor\t Kategori\t SubKategori\n");
        for (int i = 0; i < size; i++) {
            System.out.print(this.list.get(i).getIdSubkategori()+ "\t");
            System.out.print(this.list.get(i).getKategori().getKategori()+ "\t");
            System.out.print(this.list.get(i).getSubKategori()+ "\n");
        }
    }
    
    @Override
    public void printList(int start, int end) {
        if (this.list.isEmpty()) {
            this.getAllData();
        }
        
        int size = this.list.size();
        
        System.out.println("ID SubKategor\t Kategori\t SubKategori\n");
        for (int i = start-1; i < end; i++) {
            System.out.print(this.list.get(i).getIdSubkategori()+ "\t");
            System.out.print(this.list.get(i).getKategori().getKategori()+ "\t");
            System.out.print(this.list.get(i).getSubKategori()+ "\n");
        }
    }
    
    @Override
    public void printList(int limit) {
        if (this.list.isEmpty()) {
            this.getAllData();
        }
        
        int size = this.list.size();
        
        System.out.println("ID SubKategor\t Kategori\t SubKategori\n");
        for (int i = 0; i < limit; i++) {
            System.out.print(this.list.get(i).getIdSubkategori()+ "\t");
            System.out.print(this.list.get(i).getKategori().getKategori()+ "\t");
            System.out.print(this.list.get(i).getSubKategori()+ "\n");
        }
    }

    @Override
    public void filterList(int filter, String nilai) {
        
    }
    
    public SubKategori getElement(String id){
        SubKategori sub = new SubKategori();
        
        if(this.list.isEmpty()){
            getAllData();
        }
        
        int i = 0;
        boolean ketemu = false;
        while(i<list.size() && !ketemu){
            String ID = list.get(i).getIdSubkategori();

            if(ID.equals(id)){
                sub = list.get(i);
                ketemu = true;
            }

            i += 1;
        
        }
        
        return sub;
    }

    @Override
    final void init() {
        getAllData();
    }
}