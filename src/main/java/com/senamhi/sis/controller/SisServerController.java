package com.senamhi.sis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class SisServerController {

    @RequestMapping(value = { "/sisserver/listserver"}, method = RequestMethod.GET)
    public String SisServerListServer(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        request.setAttribute("title_pag","Lista de Servidores");   
        return "sisserver/listserver";
    } 
    
    @RequestMapping(value = { "/sisserver/detalleservidor"}, method = RequestMethod.GET)
    public String SisServerDetalleServidor(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        String cod = request.getParameter("cod");
        String host = request.getParameter("host");
        String nomserver = request.getParameter("nomserver");
        
        request.setAttribute("title_pag","Detalle del Servidor "+nomserver + "("+host+")");  
        request.setAttribute("cod",cod);   
        request.setAttribute("host",host); 
        return "sisserver/detalleservidor";
    } 
    
}