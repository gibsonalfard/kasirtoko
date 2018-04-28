/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.iDataAccess;
import static Interface.iDataAccess.FILE_NAME;
import Model.DetailTransaksi;
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
public class DetailTransaksiController extends Controller{
    List<DetailTransaksi> list = new ArrayList<>();

    public DetailTransaksiController() {
        init();
    }
    
    @Override
    public void getAllData() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(3);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                
                Cell idDetail = currentRow.getCell(0);
                Cell idTransaksi = currentRow.getCell(1);
                Cell buku = currentRow.getCell(2);
                Cell qty = currentRow.getCell(3);
                Cell total = currentRow.getCell(4);
                
                DetailTransaksi dtlTransaksi = new DetailTransaksi();
                dtlTransaksi.setIdDetail(idDetail.getStringCellValue());
                
                TransaksiController transc = new TransaksiController();
                dtlTransaksi.setTransaksi(transc.getElement(idTransaksi.getStringCellValue()));
                
                BukuController buku1 = new BukuController();
                dtlTransaksi.setBarang(buku1.getElements(buku.getStringCellValue()));
                
                dtlTransaksi.setQty((int) qty.getNumericCellValue());
                dtlTransaksi.setTotal(total.getNumericCellValue());
                
                this.list.add(dtlTransaksi);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailTransaksiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailTransaksiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printList(int start, int end) {
        if(this.list.isEmpty()){
            this.getAllData();
        }
        int size = this.list.size();
        
        System.out.println("ID Detail\t Transaksi\t\tJudul Buku\t\t\tQuantity\t\t Total\n");
        for(int i = start-1; i < end; i++){
            System.out.print(this.list.get(i).getIdDetail()+ "\t\t");
            System.out.print(this.list.get(i).getTransaksi().getIdTransaksi()+ "\t\t\t");
            System.out.print (this.list.get(i).getBarang().getJudulBuku()+ "\t");
            System.out.print (this.list.get(i).getQty()+"\t\t");
            System.out.print(this.list.get(i).getTotal()+ "\n");
        }
    }
    
    @Override
    public void printList(int limit) {
        if(this.list.isEmpty()){
            this.getAllData();
        }
        int size = this.list.size();
        
        System.out.println("ID Detail\t Transaksi\t\tJudul Buku\t\t\tQuantity\t\t Total\n");
        for(int i = 0; i < limit; i++){
            System.out.print(this.list.get(i).getIdDetail()+ "\t\t");
            System.out.print(this.list.get(i).getTransaksi().getIdTransaksi()+ "\t\t\t");
            System.out.print (this.list.get(i).getBarang().getJudulBuku()+ "\t");
            System.out.print (this.list.get(i).getQty()+"\t\t");
            System.out.print(this.list.get(i).getTotal()+ "\n");
        }
    }
    
    @Override
    public void printList() {
        if(this.list.isEmpty()){
            this.getAllData();
        }
        int size = this.list.size();
        
        System.out.println("ID Detail\t Transaksi\t\tJudul Buku\t\t\tQuantity\t\t Total\n");
        for(int i = 0; i < size; i++){
            System.out.print(this.list.get(i).getIdDetail()+ "\t\t");
            System.out.print(this.list.get(i).getTransaksi().getIdTransaksi()+ "\t\t\t");
            System.out.print (this.list.get(i).getBarang().getJudulBuku()+ "\t");
            System.out.print (this.list.get(i).getQty()+"\t\t");
            System.out.print(this.list.get(i).getTotal()+ "\n");
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