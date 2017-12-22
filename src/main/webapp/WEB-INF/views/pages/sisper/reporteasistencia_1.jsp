<%-- 
    Document   : index
    Created on : 13/08/2015, 11:29:38 AM
    Author     : usuario
--%>

<%@page import="java.util.StringTokenizer"%>
<%--<%@page import="conn.conexion"%>--%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="com.lowagie.text.pdf.codec.Base64.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporter"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    
    String esquema = request.getParameter("s") ;
    String arch_report = request.getParameter("r") ;
    String param   = request.getParameter("p") ;
        
    System.setProperty("java.awt.headless", "true");    
    Connection cn;
    String cn_error="";
    try{

//        conexion con = new conexion();
//        con.cn(esquema);
        
        String host= "172.25.13.187";//con.ip;
        String sid = "OGEIP";
        String user= "SISPER";
        String pass= "SISOPE2";
        String puerto="1521";

        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        cn = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+puerto+":"+sid, user, pass);
        cn_error = "";
        
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
        cn_error = "ERROR: "+e.getMessage();
        }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=cn_error%></h1>
    </body>
</html>
