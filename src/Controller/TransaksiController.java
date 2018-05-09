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
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ACER Z3-451
 */
public class TransaksiController extends Controller {

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
//                System.out.println(idTransaksi.getStringCellValue());
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
        DateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
        String d;
        
        if (this.list.isEmpty()) {
            this.getAllData();
        }
        int size = this.list.size();

        System.out.println("ID Transaksi\t\tTgl Transaksi\t\t\tTotal\n");
        for (int i = 0; i < size; i++) {
            d = sdf.format(this.list.get(i).getTglTransaksi());
            System.out.print(this.list.get(i).getIdTransaksi() + "\t\t\t");
            System.out.print(d + "\t");
            System.out.print("Rp "+super.number_format((int)this.list.get(i).getTotal())+ "\n");
        }
    }

    @Override
    public void filterList(int filter, String nilai) {

    }

    public Transaksi getElement(String id) {
        Transaksi tr = new Transaksi();
        tr.setIdTransaksi("000");

//        if (this.list.isEmpty()) {
//            getAllData();
//        }

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
        DateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String d;
        Date date;
        if (this.list.isEmpty()) {
            this.getAllData();
        }

        System.out.println("ID Transaksi\t\tTgl Transaksi\t\t\tTotal\n");
        for (int i = start - 1; i < end; i++) {
            System.out.print(this.list.get(i).getIdTransaksi() + "\t\t\t");
            d = sdf.format(this.list.get(i).getTglTransaksi());
            date = this.list.get(i).getTglTransaksi();
            System.out.print(d+"\t");
            System.out.print(this.list.get(i).getTotal() + "\n");
        }
    }

    @Override
    public void printList(int limit) {
//        if (this.list.isEmpty()) {
//            this.getAllData();
//        }

        System.out.println("ID Transaksi\t\tTgl Transaksi\t\t\tTotal\n");
        for (int i = 0; i < limit; i++) {
            System.out.print(this.list.get(i).getIdTransaksi() + "\t\t\t");
            System.out.print(this.list.get(i).getTglTransaksi() + "\t");
            System.out.print(this.list.get(i).getTotal() + "\n");
        }
    }

    public String generateCode() {
        String id = "";
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(5);
            int size = sheet.getPhysicalNumberOfRows();

            Row currentRow = sheet.getRow(size - 1);

            Cell idTrans = currentRow.getCell(0);
            id = idTrans.getStringCellValue().substring(1);
            int code = Integer.parseInt(id);
            code += 1;

            id = String.valueOf(code);
            while (id.length() < 3) {
                id = "0" + id;
            }

            id = "T" + id;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public void setData(int Total, KeranjangController cart) {
        int size = this.list.size(), row;
        DetailTransaksiController dtc = new DetailTransaksiController();
        
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(5);
//            Iterator<Row> iterator = sheet.iterator();
//            Row currentRow = iterator.next();
            row = sheet.getLastRowNum()+1;
            Row currentRow = sheet.createRow(row);

//            while (iterator.hasNext()) {
//                currentRow = iterator.next();
//            }
//            SimpleDateFormat sdf  = new SimpleDateFormat("MM/dd/YYYY");
            Date today = new Date();
//            String d = sdf.format(today);
            
            Transaksi transaksi = new Transaksi();
            
            transaksi.setIdTransaksi(generateCode());
            transaksi.setTglTransaksi(today);
            transaksi.setTotal(Total);
            
            Cell idTr = currentRow.createCell(0);
            idTr.setCellValue(transaksi.getIdTransaksi());
            
            Cell tglTr = currentRow.createCell(1);
            tglTr.setCellValue(today);
            
            Cell totalTr = currentRow.createCell(2);
            totalTr.setCellValue(transaksi.getTotal());

            try {
                FileOutputStream output = new FileOutputStream(new File(FILE_NAME));
                workbook.write(output);
                workbook.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
            }
            dtc.setData(transaksi, cart);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RekeningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
