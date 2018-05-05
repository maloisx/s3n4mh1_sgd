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
public class SisperController {

    @RequestMapping(value = { "/sisper/index"}, method = RequestMethod.GET)
    public String SisperIndex(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
            
        return "sisper/index";
    } 
    
    @RequestMapping(value = { "/sisper/reporteasistencia"}, method = RequestMethod.GET)
    public String SisperReporteAsistencia(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        String iframe = "<iframe height=\"100%\" width=\"100%\" src=\"reporteasistenciapdf\">";
        
        request.setAttribute("response",iframe);
        
        return "sisper/reporteasistencia";
    }
    
    @RequestMapping(value = { "/sisper/reporteasistenciapdf"}, method = RequestMethod.GET)
    public String SisperReporteAsistenciaPDF(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        //String esquema = request.getParameter("s");
        String arch_report = request.getParameter("r");
        String param = request.getParameter("p");
        
        if(arch_report == null){
            arch_report = "asistenciaempleado";
        }
        if(param == null){
            HttpSession session = request.getSession();
            String idPers = session.getAttribute("idPers").toString(); 
           
            SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
            Date fechai =  new Date();
            fechai.setMonth(fechai.getMonth() - 1);
            fechai.setDate(1);
            String fecha_ini =  sm.format(fechai);            
            
            Date fechaf =  new Date();
            String fecha_fin =  sm.format(fechaf);
            
            param = "p_cod_emp$"+idPers+"|p_fecha_ini$"+fecha_ini+"|p_fecha_fin$"+fecha_fin;
            System.out.println(param);
            
        }
                
        System.setProperty("java.awt.headless", "true"); 
        
            try{

//        conexion con = new conexion();
//        con.cn(esquema);
        
        String host= "172.25.13.187";//con.ip;
        String sid = "OGEIP";
        String user= "SISPER";
        String pass= "SISOPE2";
        String puerto="1521";

        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+puerto+":"+sid, user, pass);
        String cn_error = "";
                
        String cad_url = "http://sgd.senamhi.gob.pe/sis/static/report_jasper/"+arch_report+".jasper";
        URL url = new URL(cad_url);        
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url);
        
        Map parameters = new HashMap();// parametros
//        parameters.put("P_COD_EMP", "00111891"); 
//        parameters.put("P_FECHA_INI", "01/05/2015"); 
//        parameters.put("P_FECHA_FIN", "31/05/2015"); 
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
        
        return "sisper/reporteasistenciapdf";
    } 
    
}