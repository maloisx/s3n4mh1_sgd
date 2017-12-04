package com.senamhi.sis.controller;

import com.senamhi.sis.configuration.AppConfig;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class AppController {

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
        throws ServletException, IOException{
            
//            String p = request.getParameter("p");
//                                   
            HttpSession session = request.getSession();
            
//            session.setAttribute("codUser","2");
//            session.setAttribute("p",p);

            String codUser = (String) session.getAttribute("codUser");
            if(codUser == null){
                response.sendRedirect(new AppConfig().Path()+"login/");
            }
            else{
                String ap =  (String) session.getAttribute("appPers");
                String am =  (String) session.getAttribute("apmPers");
                String nom =  (String) session.getAttribute("nomPers");
                request.setAttribute("nom_emp",ap + " "+ am + ", "+nom);
            }
            return "home";
	}       
        
        
        
        @RequestMapping(value = { "/sgd_monitor"}, method = RequestMethod.GET)
	public String SgdMonitorPage(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
        throws ServletException, IOException{
            
            String t_response = "";
            String t = request.getParameter("t");
                if(t == null || t.equals("")){
                    t = "0";
                }
                int tr = Integer.parseInt(t);
                request.setAttribute("response",tr);
            return "sgd_monitor";
	}
        
        @RequestMapping(value = { "/sgd_monitor_ayax"}, method = RequestMethod.GET)
	public String SgdMonitorAyaxPage(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
        throws ServletException, IOException{
            
            String t_response = "";
            String urlString = "http://sgd.senamhi.gob.pe/sgd-senamhi_web_v2/";
            try {
                Date date = new Date();
                DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
                String fecha = formatofec.format(date);
                
                URL url = new URL(urlString);
                HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                huc.setRequestMethod("GET");
                StopWatch pageLoad = new StopWatch();
                pageLoad.start();
                huc.connect(); 
                pageLoad.stop();
                long pageLoadTime_ms = pageLoad.getTime();
                long pageLoadTime_Seconds = pageLoadTime_ms / 1000;
                long pageLoadTime_SecondsRemainder = (pageLoadTime_ms % 1000);
                t_response += "web: " + urlString + "<br>";
                t_response += "fecha: " + fecha + "<br><br>";
                t_response += "Tiempo de Carga: " + pageLoadTime_ms + " milisegundos" + "<br>";          
                t_response += "Tiempo de Carga: " + "Segundos:" + pageLoadTime_Seconds + ", Recordatorio de cache:"+ pageLoadTime_SecondsRemainder + "<br>";
                t_response += "Codigo de carga: " + huc.getResponseCode();
                System.out.println(t_response);
            } catch (Exception e) {
                t_response = e.getMessage();
            }            
            request.setAttribute("response","<h3>"+t_response+"</h3>");
            
            return "sgd_monitor_ayax";
	}
        
        
}