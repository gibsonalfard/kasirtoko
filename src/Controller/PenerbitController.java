/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.iDataAccess;
import static Interface.iDataAccess.FILE_NAME;
import Model.Penerbit;
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
public class PenerbitController extends Penerbit implements iDataAccess {

    List<Penerbit> list = new ArrayList<>();

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
                
                Cell idPenerbit = currentRow.getCell(0);
                Cell nama = currentRow.getCell(1);
                
                Penerbit publish = new Penerbit();
                publish.setIdPenerbit(idPenerbit.getStringCellValue());
                publish.setNamaPenerbit(nama.getStringCellValue());
                
                this.list.add(publish);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PenerbitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PenerbitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printList() {
        if(this.list.isEmpty()){
            this.getAllData();
        }
        int size = this.list.size();
        
        System.out.println("ID Penerbit\tNama Penerbit");
        for(int i = 0; i < size; i++){
            System.out.print(this.list.get(i).getIdPenerbit()+ "\t");
            System.out.print(this.list.get(i).getNamaPenerbit()+ "\n");
        }
    }

    @Override
    public void filterList(int filter, String nilai) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Penerbit getElement(String id){
        Penerbit pub = new Penerbit();
        
        if(this.list.isEmpty()){
            getAllData();
        }
        
        int i = 0;
        boolean ketemu = false;
        while(i<list.size() && !ketemu){
            String ID = list.get(i).getIdPenerbit();

            pub = list.get(i);

            if(ID.equals(id)){
                ketemu = true;
            }

            i += 1;
        
        }
        
        return pub;
    }

}
