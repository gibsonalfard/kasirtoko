/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirtoko;

import Controller.Controller;
import Controller.PenerbitController;
import Controller.PenulisController;

/**
 *
 * @author Gibran
 */
public class KasirToko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller kc = new PenerbitController();
        
        kc.getAllData();
        kc.printList();
    }
    
}
