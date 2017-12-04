/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senamhi.sis.test;

import com.senamhi.sis.connection.ConeccionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class prueba_psotgres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ConeccionDB cdb = new ConeccionDB();
            Connection cn = cdb.CnPostgres();
            Statement st = cn.createStatement();
            ResultSet rs =  st.executeQuery("select now()");
            String fecha_db = "";
            while(rs.next()){
                fecha_db = rs.getString(1);
            }
            System.out.println(fecha_db);
        } catch (SQLException ex) {
            Logger.getLogger(prueba_psotgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
