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
    String FILE_NAME = "*/../src/Excel/dataProject.xlsx";
    
    void getAllData();
    void printList();
    void printList(int start, int end);
    void printList(int limit);
    void filterList(int filter, String nilai);
}
