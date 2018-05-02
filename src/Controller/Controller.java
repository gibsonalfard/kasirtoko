/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.iDataAccess;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author Gibran
 */
public abstract class Controller implements iDataAccess {
    abstract void init();
    
    String number_format(int number) {
        DecimalFormat df = new DecimalFormat("#,##0",new DecimalFormatSymbols(new Locale("pt", "ID")));
        BigDecimal value = new BigDecimal(number);

        return String.valueOf(df.format(value.floatValue()));
    }
}