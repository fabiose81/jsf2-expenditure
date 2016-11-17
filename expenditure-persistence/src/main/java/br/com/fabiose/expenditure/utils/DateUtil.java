/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.expenditure.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fabioestrela
 */
public class DateUtil {
    
    public static String formatDate(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
