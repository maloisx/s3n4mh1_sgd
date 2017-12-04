/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senamhi.sis.test;

import com.senamhi.sis.functions.Util;
import java.util.Vector;

/**
 *
 * @author Usuario
 */
public class test_function_Util {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vector v_rawdata = new Util().obt_rawdata("47208020");
        for(int x = 0 ; x<v_rawdata.size();x++){
            Vector v_rawdata_sub =  (Vector) v_rawdata.get(x);
            String fec_rawdata = (String) v_rawdata_sub.get(1);
            String t_rawdata = (String) v_rawdata_sub.get(2);
            System.out.println(fec_rawdata +"  ->  "+t_rawdata);                    
        }
    }
    
}
