/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.iDataAccess;
import static Interface.iDataAccess.FILE_NAME;
import Model.Penulis;
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
public class PenulisController extends Controller implements iDataAccess{
    List<Penulis> list = new ArrayList<>();

    public PenulisController() {
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
                
                Cell idPenulis = currentRow.getCell(0);
                Cell nama = currentRow.getCell(1);
                
                Penulis writer = new Penulis();
                writer.setIdPenulis(idPenulis.getStringCellValue());
                writer.setNamaPenulis(nama.getStringCellValue());
                
                this.list.add(writer);
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
        
        System.out.println("ID Penulis\tNama Penulis");
        for(int i = 0; i < size; i++){
            System.out.print(this.list.get(i).getIdPenulis()+ "\t");
            System.out.print(this.list.get(i).getNamaPenulis()+ "\n");
        }
    }

    @Override
    public void filterList(int filter, String nilai) {
        
    }
    
    public Penulis getElement(String id){
        Penulis writer = new Penulis();
        
        if(this.list.isEmpty()){
            getAllData();
        }
        
        int i = 0;
        boolean ketemu = false;
        while(i<list.size() && !ketemu){
            String ID = list.get(i).getIdPenulis();

            writer = list.get(i);

            if(ID.equals(id)){
                ketemu = true;
            }

            i += 1;
        
        }
        
        return writer;
    }

    @Override
    final void init() {
        getAllData();
    }
}
