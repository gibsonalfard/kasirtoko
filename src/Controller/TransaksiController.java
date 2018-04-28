/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Interface.iDataAccess.FILE_NAME;
import Model.Transaksi;
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
public class TransaksiController extends Controller{
    List<Transaksi> list = new ArrayList<>();

    public TransaksiController() {
        init();
    }
       
    
    @Override
    public void getAllData() {
        
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(5);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                
                Cell idTransaksi = currentRow.getCell(0);
                Cell tglTransaksi = currentRow.getCell(1);
                Cell total = currentRow.getCell(2);
                
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(idTransaksi.getStringCellValue());
                transaksi.setTglTransaksi(tglTransaksi.getDateCellValue());
                transaksi.setTotal(total.getNumericCellValue());
                
                this.list.add(transaksi);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printList() {
        if(this.list.isEmpty()){
            this.getAllData();
        }
        int size = this.list.size();
        
        System.out.println("ID Transaksi\t\tTgl Transaksi\t\t\tTotal\n");
        for(int i = 0; i < size; i++){
            System.out.print(this.list.get(i).getIdTransaksi()+ "\t\t\t");
            System.out.print(this.list.get(i).getTglTransaksi()+ "\t");
            System.out.print(this.list.get(i).getTotal()+ "\n");
        }
    }
    

    

    @Override
    public void filterList(int filter, String nilai) {
        
    }
    
    public Transaksi getElement (String id) {
        Transaksi tr = new Transaksi();
        
        if (this.list.isEmpty()) {
            getAllData();
        }
        
        int i = 0;
        boolean ketemu = false;
        while (i < list.size() && !ketemu) {
            String ID = list.get(i).getIdTransaksi();
            
            if (ID.equals(id)) {
                ketemu = true;
                tr = list.get(i);
            }
            i += 1;        
        }
        return tr;
    }    

    @Override
    final void init() {
        getAllData();
    }

    @Override
    public void printList(int start, int end) {
        if(this.list.isEmpty()){
            this.getAllData();
        }
        
        System.out.println("ID Transaksi\t\tTgl Transaksi\t\t\tTotal\n");
        for(int i = start-1; i < end; i++){
            System.out.print(this.list.get(i).getIdTransaksi()+ "\t\t\t");
            System.out.print(this.list.get(i).getTglTransaksi()+ "\t");
            System.out.print(this.list.get(i).getTotal()+ "\n");
        }
    }
    
    @Override
    public void printList(int limit) {
        if(this.list.isEmpty()){
            this.getAllData();
        }
        
        System.out.println("ID Transaksi\t\tTgl Transaksi\t\t\tTotal\n");
        for(int i = 0; i < limit; i++){
            System.out.print(this.list.get(i).getIdTransaksi()+ "\t\t\t");
            System.out.print(this.list.get(i).getTglTransaksi()+ "\t");
            System.out.print(this.list.get(i).getTotal()+ "\n");
        }
    }
    
}
