/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Interface.iDataAccess.FILE_NAME;
import Model.Rekening;
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
 * @author ACER Z3-451
 */
public class RekeningController extends Controller {
    List<Rekening> list = new ArrayList<>();
    
    public RekeningController () {
        init();
    }
    
    @Override
    final void init() {
        getAllData();
    }

    @Override
    public void getAllData() {
        try {
            FileInputStream excelFile = new FileInputStream (new File (FILE_NAME));
            Workbook workbook = new XSSFWorkbook (excelFile);
            Sheet sheet = workbook.getSheetAt(7);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
         
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                
                Cell noRek = currentRow.getCell(0);
                Cell nama = currentRow.getCell(1);
                Cell saldo = currentRow.getCell(2);
                
                Rekening rekening = new Rekening();
                rekening.setNoRekening(noRek.getStringCellValue());
                rekening.setNamaPemilik(nama.getStringCellValue());
                rekening.setSaldo((int) saldo.getNumericCellValue());
                
                this.list.add(rekening);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printList() {
//        if (this.list.isEmpty())
        int size = this.list.size();
        
        System.out.println ("ID Rekening\t\t Nama Pemilik\t\t Saldo");
        for (int i = 0; i < size; i++) {
            System.out.print (this.list.get(i).getNoRekening()+"\t\t");
            System.out.print (this.list.get(i).getNamaPemilik()+"\t\t");
            System.out.print(this.list.get(i).getSaldo()+"\n");
        }
    }

    @Override
    public void printList(int start, int end) {
        int size = this.list.size();
        
        if (start > size || end > size) {
            System.out.println("Ukuran list hanya sebanyak : " + size);
        } else {
            System.out.println ("ID Rekening\t\t Nama Pemilik\t\t Saldo");
            for (int i = 0; i < size; i++) {
                System.out.print (this.list.get(i).getNoRekening()+"\t\t");
                System.out.print (this.list.get(i).getNamaPemilik()+"\t\t");
                System.out.print(this.list.get(i).getSaldo()+"\n");
            }
        }
    }

    @Override
    public void printList(int limit) {
        int size = this.list.size();
        
        if (limit > size) {
            System.out.println("Ukuran list hanya sebanyak : " + size);
        } else {
            System.out.println ("ID Rekening\t\t Nama Pemilik\t\t Saldo");
            for (int i = 0; i < size; i++) {
                System.out.print (this.list.get(i).getNoRekening()+"\t\t");
                System.out.print (this.list.get(i).getNamaPemilik()+"\t\t");
                System.out.print(this.list.get(i).getSaldo()+"\n");
            }
        }
    }

    @Override
    public void filterList(int filter, String nilai) {
        boolean kondisi = false;
        int size = this.list.size();
        nilai = nilai.toLowerCase();
        
        for (int i = 0; i < size; i++) {
            switch (filter) {
                case 1: //No Rekening
                    kondisi = this.list.get(i).getNoRekening().toLowerCase().contains(nilai);
                    break;
                case 2: //Nama Pemilik
                    kondisi = this.list.get(i).getNamaPemilik().toLowerCase().contains(nilai);
                    break;
                case 3: //Saldo
                    kondisi = String.valueOf(this.list.get(i).getSaldo()).toLowerCase().contains(nilai);
                    break;
            }
            
            if(kondisi){
                System.out.print(this.list.get(i).getNoRekening() + "\t");
                System.out.print(this.list.get(i).getNamaPemilik() + "\t\t");
                System.out.print(this.list.get(i).getSaldo() + "\n");
            }
        }
    }
    
    public Rekening getElements(String noRekening) {
        Rekening rekening = new Rekening();

//        if (this.list.isEmpty()) {
//            getAllData();
//        }

        int i = 0;
        boolean ketemu = false;
        while (i < list.size() && !ketemu) {
            String noRek = list.get(i).getNoRekening();

            if (noRek.equals(noRekening)) {
                ketemu = true;
                rekening = list.get(i);
            }
            i += 1;
        }
        return rekening;
    }
    
    public void setData (Rekening rekening) {
        int size = this.list.size();
        
        try {
            FileInputStream excelFile = new FileInputStream (new File (FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(7);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                
                Cell noRek = currentRow.getCell(0);
                Cell nama = currentRow.getCell(1);
                Cell saldo = currentRow.getCell(2);
                
                if (rekening.getNoRekening().equals(noRek.getStringCellValue())) {
                    saldo.setCellValue(rekening.getSaldo());                    
                    try {
                        FileOutputStream output = new FileOutputStream (new File (FILE_NAME));
                        workbook.write(output);
                        workbook.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
