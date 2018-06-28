package com.senamhi.sis.controller;

import com.senamhi.sis.connection.ConeccionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class SisbienController {

    @RequestMapping(value = { "/sisbien/prueba"}, method = RequestMethod.GET)
    public String SisbienPrueba(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{

        return "sisbien/prueba";
    } 
    
    @RequestMapping(value = { "/sisbien/preregistro"}, method = RequestMethod.GET)
    public String SisbienPreRegistro(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        return "sisbien/preregistro";
    } 
    
    @RequestMapping(value = { "/sisbien/sigamef_bienes"}, method = RequestMethod.GET)
    public String SisbienSigaMefBienes(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        return "sisbien/sigamef_bienes";
    } 
    
    
    @RequestMapping(value = { "/sisbien/sigamef_bienes_etiquetas"}, method = RequestMethod.GET)
    public String SisbienSigaMefBienesEtiquetas(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        //String esquema = request.getParameter("s");
        String arch_report = request.getParameter("r");
        String param = request.getParameter("p");
        
        if(arch_report == null){
            arch_report = "sisbien_sigamef_bienes_etiquetas";
        }        
                
        System.setProperty("java.awt.headless", "true"); 
        
            try{
        
        String host= "172.25.100.100";//con.ip;
        String sid = "SIGAMEF1";
        String user= "SIGAMEF1";
        String pass= "mef";
        String puerto="1521";

        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+puerto+":"+sid, user, pass);
        String cn_error = "";
                
        String cad_url = "http://sgd.senamhi.gob.pe/sis/static/report_jasper/"+arch_report+".jasper";
        URL url = new URL(cad_url);        
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url);
        
        Map parameters = new HashMap();
        StringTokenizer str = new StringTokenizer(param,"|");
        int cant_str = str.countTokens();
        for(int i = 0 ; i < cant_str ; i++){
            StringTokenizer sub_str = new StringTokenizer(str.nextToken(),"$");
            int cant_sub_str = sub_str.countTokens();
            if(cant_sub_str == 2){
                String nom_param = sub_str.nextToken().toUpperCase();
                String val_param = sub_str.nextToken();
                parameters.put(nom_param, val_param);
            }if(cant_sub_str == 1){
                String nom_param = sub_str.nextToken().toUpperCase();
                parameters.put(nom_param,"");
            }
        }         
//        parameters.put("P_COD_EMP", cod_emp); 
//        parameters.put("P_FECHA_INI", fecha_ini); 
//        parameters.put("P_FECHA_FIN", fecha_fin); 
        
//        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, cn);

        
        
        byte[] bytes = JasperRunManager.runReportToPdf(reporte,parameters,cn);
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream ouputStream = response.getOutputStream();        
        ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
    
        }
        catch(Exception e){
        //cn_error = "ERROR: "+e.getMessage();
        request.setAttribute("response",  "ERROR: "+e.getMessage());
        }
        
        return "sisbien/sigamef_bienes_etiquetas";
    } 
    
}