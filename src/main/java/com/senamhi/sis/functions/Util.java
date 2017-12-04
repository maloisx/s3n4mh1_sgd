/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senamhi.sis.functions;

import com.google.gson.Gson;
import com.senamhi.sis.configuration.AppConfig;
import com.senamhi.sis.connection.ConeccionDB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import org.springframework.http.converter.json.GsonBuilderUtils;

/**
 *
 * @author Usuario
 */
public class Util {
    
    public String GetPath(){
        return new AppConfig().Path();
    }
    
    public String datatable(String id, Vector opciones){
        
        int n = (int) (Math.random()*999999+1);
        String tbl = "";
        tbl =  "<script>";
        tbl += "var oTable"+n+" = $('#"+id+"').dataTable( {";	
        	for(int i=0;i<opciones.size();i++){
                    Vector sub_opc = (Vector) opciones.get(i);
        		tbl += "\"" + sub_opc.get(0) + "\":"+sub_opc.get(1)+",";        	
        	}
                //opciones por defecto
                tbl += "\"language\":{'url':'"+new AppConfig().Path()+"static/datatables/Spanish.json'}";     
		tbl += "});";
		tbl += "oTable"+n+".$('tr').hover( function() {$(this).addClass('highlighted');}, function() {oTable"+n+".$('tr.highlighted').removeClass('highlighted'); } );";
//		if($estaticolumn){
//		tbl += "new FixedColumns(oTable"+n+");";
//		}
		tbl += "</script>";
        
        return tbl;
    }
    
    public String vector2json(Vector vc){
        
//        List<String> lista_datos = new ArrayList<String>(vc);
        
        String json = ""; 
        
        if(vc.size() > 0){
            json = "[";
            for(int i=0;i<vc.size();i++){
                    Vector vx = (Vector) vc.get(i);
                    json += "["; 
                    for(int j=0;j<vx.size();j++){
                        json += "\""+vx.get(j).toString()+"\",";
                    }
                    json = json.substring(0, json.length()-1);
                    json += "],"; 
//                    
             }
                json = json.substring(0, json.length()-1);
             
             json += "]";

//         Gson gson = new Gson();
//         json = gson.toJson(lista_datos);   

        }else{
            json = "[]";
        }       
        return json;
    }
    
        public String vector2json_tbl(Vector vc){
        
        List<String> lista_datos = new ArrayList<String>(vc);
        
        String json = ""; 
        
        if(vc.size() > 0){
            
         Gson gson = new Gson();
         json = gson.toJson(lista_datos);   

        }else{
            json = "[]";
        }       
        return json;
    }
    
    public Vector obt_rawdata(String cod_goes){
            
            Vector v_rawdata = new Vector();        
//            String json_rawdata = "";
            String trama = "";
            String cad_url = "http://eddn.usgs.gov/cgi-bin/retrieveData.pl?DRS_SINCE=now%20-1000%20hours&DRS_UNTIL=now&NETWORKLIST=&DCP_ADDRESS="+cod_goes+"&CHANNEL=&BEFORE=//SOURCE%20GOESTR%20DCP%20DAPS\\n&AFTER=\\n//END\\n&SPACECRAFT=Any&BAUD=Any&ELECTRONIC_MAIL=&DCP_BUL=&GLOB_BUL=&TIMING=&RETRANSMITTED=Y&DAPS_STATUS=Y&";
            System.out.println(cad_url);
            String fecha_full = "";        
            try{
                URL url = new URL(cad_url);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    
                    if(inputLine.length()>8){
                        if(inputLine.substring(0,8).equalsIgnoreCase(cod_goes)){
                            
                            Vector v_tmp =  new Vector();
                            
                            trama = inputLine.trim();
                            String cod_goes_t = trama.substring(0,8);
                            String anio = trama.substring(8,10);
                            String dia_juliano = trama.substring(10,13);                                  

                            String h = trama.substring(13,15);
                            String m = "00";//trama.substring(15,17);
                            String s = "00";//trama.substring(17,19);

                                Calendar calendar_t = Calendar.getInstance();
                                calendar_t.set(Calendar.DAY_OF_YEAR, Integer.parseInt(dia_juliano));
                                calendar_t.set(Calendar.YEAR, Integer.parseInt("20"+anio));

                                Calendar cal = Calendar.getInstance();
                                cal.set(calendar_t.get(Calendar.YEAR), calendar_t.get(Calendar.MONTH), calendar_t.get(Calendar.DATE), Integer.parseInt(h), Integer.parseInt(m), Integer.parseInt(s));                                       
                                cal.add(Calendar.HOUR, -5);
                            String fecha = cal.get(Calendar.YEAR)+ "/" + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + "/" +String.format("%02d", cal.get(Calendar.DATE));
                            String hora = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + ":"+String.format("%02d", cal.get(Calendar.MINUTE));
                            fecha_full = fecha + " " + hora;
                            v_tmp.add(cod_goes_t);
                            v_tmp.add(fecha_full);
                            v_tmp.add(trama);
                            v_rawdata.add(v_tmp);
                            //System.out.println(cod_goes_t+"\t"+fecha_full +"\t"+ trama);
//                            System.out.println(inputLine);
                        }
                    }                     
                }
                
                /*llenar con datos de la cabecera de datos los rawdata*/
                
                String param_datos[] = new String[2];
                param_datos[0] = cod_goes;
                param_datos[1] = fecha_full;
                Vector datos = new ConeccionDB().EjecutarProcedureOracle("pkg_decode.SP_OBT_RAWDATA_OLD", param_datos);
                for(int x = 0 ; x<datos.size();x++){
                    Vector v_datos_sub =  (Vector) datos.get(x);
                    String fec_rawdata = (String) v_datos_sub.get(1);
                    String t_rawdata = (String) v_datos_sub.get(2);
                        Vector v_tmp =  new Vector();
                        v_tmp.add(cod_goes);
                        v_tmp.add(fec_rawdata);
                        v_tmp.add(t_rawdata);
                        v_rawdata.add(v_tmp);
                    //System.out.println(fec_rawdata +"  ->  "+t_rawdata);                    
                }
                
            }catch(Exception e){
                System.err.println(e.getMessage());
//                json_rawdata = "ERROR EN DATOS DAWDATA WEB.";
            }
            
            return v_rawdata;            
    }
    
   public String contenido_combo(Vector v , String txt_id){
   
//   String cb_obj = "<option value=''>"+"SELECCIONAR OPCION..."+"</option>";
    //txt_id += ",";
    String array_id[] =  txt_id.split(",");
    String id = "";

   String cb_obj = "";
   boolean b = false;
             for(int i = 0 ; i<v.size() ; i++)
             {
                b = false;  
                Vector datos_v =  (Vector) v.get(i);
                String vect_id = datos_v.get(0).toString();
                String vect_des = datos_v.get(1).toString();
                for(int y = 0 ; y <array_id.length ; y++){
                    if(vect_id.equalsIgnoreCase(array_id[y])){
                     b = true; 
                     break;                      
                    }
                                         
                }
                if (b == true)    
                    cb_obj += "<option value='"+vect_id+"' selected='selected' >"+vect_des+"</option>";
                else
                   cb_obj += "<option value='"+vect_id+"'  >"+vect_des+"</option>";
            }   
   return cb_obj;
   }  
   
    public String contenido_combo_columna(Vector v , String txt_id, Integer col){
   
//   String cb_obj = "<option value=''>"+"SELECCIONAR OPCION..."+"</option>";
    //txt_id += ",";
    String array_id[] =  txt_id.split(",");
    String id = "";

   String cb_obj = "";
   boolean b = false;
             for(int i = 0 ; i<v.size() ; i++)
             {
                b = false;  
                Vector datos_v =  (Vector) v.get(i);
                String vect_id = datos_v.get(0).toString();
                String vect_des = datos_v.get(col).toString();
                for(int y = 0 ; y <array_id.length ; y++){
                    if(vect_id.equalsIgnoreCase(array_id[y])){
                     b = true; 
                     break;                      
                    }
                                         
                }
                if (b == true)    
                    cb_obj += "<option value='"+vect_id+"' selected='selected' >"+vect_des+"</option>";
                else
                   cb_obj += "<option value='"+vect_id+"'  >"+vect_des+"</option>";
            }   
   return cb_obj;
   }  
   
   public String tbl(Vector v_tbl_cab , Vector v_datos){
       
//   String  var_cantcombo = (String) (p_cantcombo.length > 0 ? p_cantcombo[0] : "5");    
       
   Random r = new Random();
            int Low = 1000000;
            int High = 9999999;
            int n = r.nextInt(High-Low) + Low;
            String css = "<style> "+
                            " .results_"+n+" tr[visible='false'], " +
                            ".no-result_"+n+"{" +
                            "  display:none; " +
                            "}" +
                            ".results_"+n+" tr[visible='true']{ " +
                            "  display:table-row;" +
                            "}" +
                            ".counter_tbl_"+n+"{ " +
                            "  padding:8px; " +
                            "  color:#ccc; " +
                            "}" + 
                            "table th {\n" +
                            "  text-align: center;\n" +
                            "}"+
                         "</style>";
            
            String js = "<script>"+
                        "$(document).ready(function() {\n" +
                        "  $(\".search_"+n+"\").keyup(function () {" +
                        "    var searchTerm_"+n+" = $(\".search_"+n+"\").val();"+
                        "    var listItem_"+n+" = $('.results_"+n+" tbody').children('tr');" +
                        "    var searchSplit_"+n+" = searchTerm_"+n+".replace(/ /g, \"'):containsi('\");" +
                        "    " +
                        "  $.extend($.expr[':'], {'containsi': function(elem, i, match, array){" +
                        "        return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || \"\").toLowerCase()) >= 0;" +
                        "    }" +
                        "  });" +
                        "    " +
                        "  $(\".results_"+n+" tbody tr\").not(\":containsi('\" + searchSplit_"+n+" + \"')\").each(function(e){" +
                        "    $(this).attr('visible','false');" +
                        "  });" +
                        "" +
                        "  $(\".results_"+n+" tbody tr:containsi('\" + searchSplit_"+n+" + \"')\").each(function(e){" +
                        "    $(this).attr('visible','true');" +
                        "  });" +
                        
                        " if(searchTerm_"+n+" == ''){"+
                        "$(\".results_"+n+" tbody tr\").each(function(e){$(this).attr('visible','true');});"+
                        "} " +
                    
                        "  var jobCount_"+n+" = $('.results_"+n+" tbody tr[visible=\"true\"]').length;" +
                        "    $('.counter_tbl_"+n+"').text(jobCount_"+n+" + ' registros');" +
                        "" +
                        "  if(jobCount_"+n+" == '0') {$('.no-result_"+n+"').show();}" +
                        "    else {$('.no-result_"+n+"').hide();}" +
                        " "+                        
                        "});" +
                        "});" +
                        " function pagination_tbl_"+n+"(){"+    
                        " $('#tblbody_"+n+"').pageMe({pagerSelector:'#myPager_"+n+"',showPrevNext:true,hidePageNumbers:false,perPage:parseInt($('#maxRows_"+n+"').val())}); " +
//                        " $('#tblbody_"+n+"').pageMe({pagerSelector:'#myPager_"+n+"',showPrevNext:true,hidePageNumbers:false,perPage:3}); " +
                        "}"+
                        "pagination_tbl_"+n+"();"+
                        "</script>";            
           
//            String tbl = " <div class='form-group pull-left' style='float: left!important;'> " +
//                         "    <input type='text' class='search_"+n+" form-control' placeholder='Buscar...'> " +
//                         " </div> ";
            
               String  tbl = " <div class='form-group pull-right col-md-1' style='float: right!important;' > ";      
                      tbl +=      " <label for=\"maxRows_"+n+"\" class=\"form-group active\">Ver </label>" +
                      " <select class=\"form-control selectpicker\" id=\"maxRows_"+n+"\" onchange=\"pagination_tbl_"+n+"()\">\n" +
                            "    <option value=\"99999\">TODO</option>" +
                            "    <option value=\"5\" >5</option>" +
                            "    <option value=\"10\" selected>10</option>\n" +
                            "    <option value=\"15\">15</option>\n" +
                            "    <option value=\"20\">20</option>\n" +
                            "    <option value=\"50\">50</option>\n" +
                            "    <option value=\"70\">70</option>\n" +
                            "    <option value=\"100\">100</option>\n" +
                            " </select>";         
                 tbl +=     " </div> ";
           
                         //" <span class='counter_tbl_"+n+" pull-right' style='float: right!important;'></span> "+
                 tbl += " <br>"+
                        " <div class=\"\">"+
                                " <table class='table table-striped table-condensed table-bordered table-hover results_"+n+"'>";
                           //cabecera
                           tbl += "<thead>"
                                + "<tr>";
                                for(int i=0;i<v_tbl_cab.size();i++){
                                    tbl += "<th>"+v_tbl_cab.get(i).toString().toUpperCase().trim()+""
                                         + "</th>";
                                }
                                tbl += "<tr class='warning no-result_"+n+"'> " +
                                       "   <td colspan='"+v_datos.size()+"'><i class='fa fa-warning'></i>SIN RESULTADOS</td>" +
                                       "</tr>";
                           tbl += "</tr>"
                                + "</thead>";
                           //fin cabecera
                           //cuerpo
                           tbl += "<tbody id=\"tblbody_"+n+"\">";
                           for(int i=0;i<v_datos.size();i++){
                               tbl += "<tr>";
                               Vector v_datos_row = (Vector) v_datos.get(i);
                               for(int j=0;j<v_datos_row.size();j++){
                                   tbl += "<td>"+v_datos_row.get(j).toString().trim()+"</td>";
                               }
                               tbl += "</tr>";
                           }
                           tbl += "</tbody>";
                           //fincuerpo
                           tbl += "</table>" +
                        "</div>";
                        if (v_datos.size()>10){
                            tbl += " <div class=\"col-md-12 text-center \">\n" +
                                " <ul class=\"pagination pagination-lg pager\" id=\"myPager_"+n+"\"></ul>\n" +
                           " </div>";                            
                        }                           
   return css+tbl+js;
   }    
   
       public String menu_buscar_hijos(Vector datos_menu,String codmenu, String Path_url){
        String menu = "";
        
            menu += "<ul " +((codmenu =="0")?" id=\"menu\" ":"")+">";

            for(int i=0;i<datos_menu.size();i++){	
                Vector v_menu = (Vector) datos_menu.get(i);
                String cod_rol = v_menu.get(0).toString();
                String desc_rol = v_menu.get(1).toString();

                String cod_obj = v_menu.get(2).toString();
                String desc_obj = v_menu.get(3).toString();
                String cod_obj_padre = v_menu.get(4).toString();
                String url_obj = v_menu.get(5).toString();

                String orden = v_menu.get(6).toString();
                int cant_hijos = Integer.parseInt(v_menu.get(7).toString());

                    if(codmenu.equalsIgnoreCase(cod_obj_padre)){
                        menu += "<li>";
                        menu += "<a href=\""+((cant_hijos>0)?"javascript: void(0)":Path_url+url_obj)+"\">"+desc_obj+"</a>";
                                if(cant_hijos>0){
                                    menu += this.menu_buscar_hijos(datos_menu, cod_obj, Path_url);
                                }
                        menu += "</li>";				
                    }

            }
            menu += ((codmenu =="0")?" <li><a href=\""+Path_url+"/login/logout\">SALIR</a></li> ":"")+"</ul>";
        return menu;
    }
}


