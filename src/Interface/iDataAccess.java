/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author Gibran
 */
public interface iDataAccess {
    String FILE_NAME = "*/../src/Excel/data.xls";
    
    void getAllData();
    void printList();
    void filterList(int filter, String nilai);
}
