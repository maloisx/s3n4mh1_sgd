package com.senamhi.sis.controller;

import com.senamhi.sis.connection.ConeccionDB;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.*;
import org.icepdf.ri.common.SwingController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pe.ds.org.main.MySwingController;



@Controller
@RequestMapping("/")
public class SisbienController {

    @RequestMapping(value = { "/sisbien/prueba"}, method = RequestMethod.GET)
    public String SisbienPrueba(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
            
//        Locale peruvianLocale = new Locale("es", "PE");
//        ResourceBundle messageBundle = ResourceBundle.getBundle("MessageBundle", peruvianLocale);
//        MySwingController msc = new MySwingController(messageBundle);
//        msc.o
        return "sisbien/prueba";
    } 
    
    @RequestMapping(value = { "/sisbien/preregistro"}, method = RequestMethod.GET)
    public String SisbienPreRegistro(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        return "sisbien/preregistro";
    } 
    
}