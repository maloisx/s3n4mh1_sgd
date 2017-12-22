package com.senamhi.sis.controller;

import com.senamhi.sis.connection.ConeccionDB;
import com.senamhi.sis.functions.Util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class SisdadController {

	@RequestMapping(value = { "/sisdad/resum_datos"}, method = RequestMethod.GET)
	public String SisdadResumDatos(HttpServletRequest request, HttpServletResponse response,ModelMap model) {    
            
            String p = request.getParameter("p");
            HttpSession session = request.getSession();
            session.setAttribute("p",p);
            
            Util util = new Util();
            request.setAttribute("title_pag","RESUMEN DE DATOS DE ESTACIONES AUTOMATICAS");  
            
            //tabla de datos!            
            Vector datos = new ConeccionDB().EjecutarProcedureOracle("pkg_decode.sp_obt_resumdatos", new String[0]);
            Vector tmp_datos = new Vector();
                for(int i=0;i<datos.size();i++){
                    Vector vx = (Vector) datos.get(i);
                    Vector vx_t = new Vector();
                    vx_t.add(vx.get(0)); //cod_esta
                    vx_t.add(vx.get(1)); //cod_goes
                    vx_t.add(vx.get(10).toString().replace("REGIONAL","ZONAL")); //DZ
                    vx_t.add(vx.get(2)); //nom_esta
                    vx_t.add(vx.get(11)); //Estado
                    vx_t.add(vx.get(3)); //tipo
                    vx_t.add(vx.get(4)); //hora
                    vx_t.add("<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all' title='Descargar datos de "+vx.get(2)+"' onclick='popup_editar_estacion_sisdad(\\\""+vx.get(0)+"\\\")'><span class='ui-icon ui-icon-gear'></span></li></ul></div>"); //icon
                    vx_t.add("<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all' title='Descargar datos de "+vx.get(2)+"' onclick='popup_datos(\\\""+vx.get(0)+"\\\",\\\""+vx.get(1)+"\\\",\\\""+vx.get(2)+"\\\",\\\""+((vx.get(4).toString().equals(""))?"":vx.get(4).toString().substring(0,10))+"\\\")'><span class='ui-icon ui-icon-circle-arrow-s'></span></li></ul></div>"); //icon
                    vx_t.add("<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all' title='buscar datos de "+vx.get(2)+"' onclick='buscar_maker_map(\\\""+vx.get(5)+"\\\",\\\""+vx.get(6)+"\\\")'><span class='ui-icon ui-icon-search'></span></li></ul></div>"); //icon
                    vx_t.add(vx.get(9)); //horas de diferencia
                    tmp_datos.add(vx_t);
                }
                
            String json = util.vector2json(tmp_datos);                 
                        
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bFilter");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("bLengthChange");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
            sv.add("bInfo");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
            sv.add("bPaginate");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
            sv.add("iDisplayLength");sv.add(datos.size());vc_tbl.add(sv);sv =  new Vector();
//            sv.add("bJQueryUI");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumns");sv.add("["
                                    + "{\"sTitle\":\"COD ESTA\",\"sWidth\":\"100px\" } , "
                                    + "{\"sTitle\":\"COD GOES\",\"sWidth\":\"100px\" } , "
                                    + "{\"sTitle\":\"DZ\",\"sWidth\":\"300px\" } , "
                                    + "{\"sTitle\":\"ESTACION\",\"sWidth\":\"300px\" } , "
                                    + "{\"sTitle\":\"EST.\",\"sWidth\":\"80px\" } , "
                                    + "{\"sTitle\":\"TRAMA\",\"sWidth\":\"70px\" } , "
                                    + "{\"sTitle\":\"ULT. DATO\",\"sWidth\":\"140px\" } , "
                                    + "{\"sTitle\":\"E\",\"sWidth\":\"40px\" } ,"
                                    + "{\"sTitle\":\"-\",\"sWidth\":\"40px\" } ,"
                                    + "{\"sTitle\":\"HD\",\"sWidth\":\"10px\" } , "
                                    + "{\"sTitle\":\"HORAS RETRASO\",\"sWidth\":\"10px\" } "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumnDefs");sv.add("[{\"sClass\":\"center\",\"aTargets\":[0,1,4,5,6]},{\"aTargets\":[ 10 ],\"bVisible\": false,\"bSearchable\": false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' },{text:'NUEVA ESTACION',action:function( e, dt, node, config ) {sisdad_popup_mant_nueva_estacion()},className:'btn btn-info btn-sm'}]");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no tiene datos
            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                                " if (rtrim(aData[10]) >= 3 && rtrim(aData[10]) <= 4){$('td', nRow).addClass('ui-state-highlight' );} " + 
                                " if (rtrim(aData[10]) > 4){$('td', nRow).addClass('ui-state-error' );} " +                     
                          "}";
            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
            ///////////////////////////////////////////////////////
            
            String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive '  id='c_table_resumen_datos'  ></table>";
            String tbl = util.datatable("c_table_resumen_datos",vc_tbl);            
            request.setAttribute("c_table_resumen_datos", tbl_html + tbl); 
            
            //mapa
            String c_mapa = "<script>pintarmapa('div_map');";
                for(int i=0;i<datos.size();i++){
                    Vector vx = (Vector) datos.get(i);
                    c_mapa += "aniadirmarker('"+vx.get(0)+"','"+vx.get(1)+"','"+vx.get(2)+"','"+( "("+vx.get(1)+")"+ vx.get(2)+"<br>"+vx.get(4))+"','"+((vx.get(4).toString().equals(""))?"":vx.get(4).toString().substring(0,10))+"','"+vx.get(5)+"','"+vx.get(6)+"','"+vx.get(8)+"','');";
                }
            c_mapa += "centrarmapa();";
            c_mapa += "</script>";
            request.setAttribute("c_mapa", c_mapa);
            return "sisdad/resum_datos";
	}
        
        @RequestMapping(value = { "/sisdad/datos"}, method = RequestMethod.GET)
	public String SisdadDatos(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            
            String cod_esta = request.getParameter("cod_esta");
            String cod_goes = request.getParameter("cod_goes");
            String nom_esta = request.getParameter("nom_esta");
            String fec_ini = request.getParameter("fec_ini");            
            
            String fec_act = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//dd/MM/yyyy
            
            if(fec_ini.equals("")){
                fec_ini = fec_act;
            }
            
            request.setAttribute("cod_esta", cod_esta); 
            request.setAttribute("cod_goes", cod_goes); 
            request.setAttribute("nom_esta", nom_esta);             
            request.setAttribute("fecha_ini", fec_ini); 
            request.setAttribute("fecha_fin", fec_act);
            
            return "sisdad/datos";
	}
        
        @RequestMapping(value = { "/sisdad/popup_editar_estacion"}, method = RequestMethod.GET)
	public String SisdadPopupEditarEstacion(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            request.setAttribute("title_pag","x1");
            String cod_esta = request.getParameter("cod_esta");
            
            request.setAttribute("cod_esta", cod_esta); 
            return "sisdad/popup_editar_estacion";
	}
        
        @RequestMapping(value = { "/sisdad/popup_editar_estacion2"}, method = RequestMethod.GET)
	public String SisdadPopupEditarEstacion2(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            request.setAttribute("title_pag","y2");
            String cod_esta = request.getParameter("cod_esta");
            
            request.setAttribute("cod_esta", cod_esta); 
            return "sisdad/popup_editar_estacion2";
	}
        
        @RequestMapping(value = { "/sisdad/tbl_datos"}, method = RequestMethod.GET)
	public String SisdadTblDatos(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
        
            HttpSession session = request.getSession();
            String p = (String) session.getAttribute("p");
            if(p == null){
                p = "";
            }
            
            String cod_esta = request.getParameter("cod_esta");
            String cod_goes = request.getParameter("cod_goes");
            String fecha_ini = request.getParameter("fecha_ini");
            String fecha_fin = request.getParameter("fecha_fin");
            
            Vector v_rawdata = new Util().obt_rawdata(cod_goes);
            
            String param_variables[] = new String[1];
            param_variables[0] = cod_esta;
            Vector v_param = new ConeccionDB().EjecutarProcedureOracle("pkg_decode.sp_obt_variable_estacion", param_variables);
            String col_tbl = "";
            String cb_param = "<option value='0'>Seleccionar...</option>";
            String col_center = "0,";
            for(int i=0;i<v_param.size();i++){
                    Vector vx = (Vector) v_param.get(i);                    
//                    col_tbl += "{\"sTitle\":\""+vx.get(0)+"\",\"sWidth\":\"140px\" } , ";
//                    col_tbl    += "{\"sTitle\":\""+vx.get(0)+"<input type='checkbox' class='cb_data' id='cb_"+vx.get(0)+"' /><label for='id_"+vx.get(0)+"'></label>"+"\" , \"orderable\": false  } , ";                    
                    col_tbl    += "{\"sTitle\":\""+vx.get(0)+"<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all' cb_checked='0' onclick='sisdad_change_cb_selected(this,\\\""+vx.get(1)+"\\\")'><span class='ui-icon ui-icon-check'></span></li></ul></div>"+"\" , \"orderable\": false  } , ";                    
                    col_center += (i+1)+",";
                    
                    cb_param +="<option value='"+(i+1)+"'>"+vx.get(0)+"</option>";
                }
            col_center = col_center.substring(0, col_center.length()-1);
                    
            request.setAttribute("cb_param", cb_param);
            
            String param_datos[] = new String[3];
            param_datos[0] = cod_esta;
            param_datos[1] = fecha_ini;
            param_datos[2] = fecha_fin;
            Vector datos = new ConeccionDB().EjecutarProcedureOracle("pkg_decode.sp_obt_datos", param_datos);
            request.setAttribute("json_datos", new Util().vector2json(datos));
            
            
            Vector datos_tmp = new Vector();
            int ult_col = 0;
            for(int i=0;i<datos.size();i++){
                    Vector vx = (Vector) datos.get(i);   
                    Vector vx_t =  new Vector();
                    String fecha_trama = (String) vx.get(0);
                    vx_t.add(fecha_trama); // fecha
                    ult_col = vx.size();
                    String btn_flag = "";
                        for(int j=1;j<vx.size()-1;j++){
                            
                            Vector vxx = (Vector) v_param.get(j-1);   
                            String nom_param =vxx.get(1).toString();                            
                            String n = (String) vx.get(j);
                            String e = "";
                            if(n.equals("")){
                                vx_t.add("");
                            }else{
                                  if(n.matches("[-+]?\\d*\\.?\\d+")){
                                    n =  String.format("%.3f",Double.parseDouble(n)).replace(",", "."); 
//                                    vx_t.add(n);
                                    btn_flag =  "<ul class='flg' style='float:right;display: none;cursor:pointer'><li style='width:18px' class='ui-state-error ui-corner-all' title='DATO MALO' onclick=\"cambiar_estado_reg('"+cod_esta+"','"+fecha_trama+"','"+nom_param+"','M')\"><img style='width:14px' src='/sis/static/img/E.png'></li></ul>"
                                              + "<ul class='flg' style='float:right;display: none;cursor:pointer'><li style='width:18px' class='ui-state-highlight ui-corner-all' title='DATO DUDOSO' onclick=\"cambiar_estado_reg('"+cod_esta+"','"+fecha_trama+"','"+nom_param+"','D')\"><img style='width:14px' src='/sis/static/img/D.png'></li></ul>";
                                  }else {
                                     e = n.substring(n.length()-1, n.length());
                                     if(e.equalsIgnoreCase("D")){
                                         //e = "ui-state-highlight";
                                         btn_flag = "<ul class='flg' style='float:right;display: none;cursor:pointer'><li style='width:18px' class='ui-state-error ui-corner-all' title='DATO MALO' onclick=\"cambiar_estado_reg('"+cod_esta+"','"+fecha_trama+"','"+nom_param+"','M')\"><img style='width:14px' src='/sis/static/img/E.png'></li></ul>"
                                                    + "<ul class='flg' style='float:right;display: none;cursor:pointer'><li style='width:18px' class='ui-state-default ui-corner-all' title='DATO BUENO' onclick=\"cambiar_estado_reg('"+cod_esta+"','"+fecha_trama+"','"+nom_param+"','')\"><img style='width:14px' src='/sis/static/img/B.png'></li></ul>" ;
                                     }if(e.equalsIgnoreCase("M")){
                                         //e = "ui-state-error";
                                         btn_flag = "<ul class='flg' style='float:right;display: none;cursor:pointer'><li style='width:18px' class='ui-state-highlight ui-corner-all' title='DATO DUDOSO' onclick=\"cambiar_estado_reg('"+cod_esta+"','"+fecha_trama+"','"+nom_param+"','D')\"><img style='width:14px' src='/sis/static/img/D.png'></li></ul>"
                                                    + "<ul class='flg' style='float:right;display: none;cursor:pointer'><li style='width:18px' class='ui-state-default ui-corner-all' title='DATO BUENO' onclick=\"cambiar_estado_reg('"+cod_esta+"','"+fecha_trama+"','"+nom_param+"','')\"><img style='width:14px' src='/sis/static/img/B.png'></li></ul>" ;
                                     }       
                                  }                                
                                  
                                  String id_tmp = cod_esta+"_"+fecha_trama.replace("/","").replace(":","").replace(" ","")+"_"+nom_param;
//                                  String divdato =  "<div align='center' style='width: 100% ;height: 100%' d_codesta='"+cod_esta+"' d_fectrama='"+fecha_trama+"' d_var='"+nom_param+"' class='"+e+"' onmouseover=\\\"$(this).children(\\'ul\\').show()\\\"  onmouseout=\\\"$(this).children(\\'ul\\').hide()\\\">" 
                                  String divdato =  //"<div align='center' style='width: 100% ;height: 100%' d_codesta='"+cod_esta+"' d_fectrama='"+fecha_trama+"' d_var='"+nom_param+"' class='"+e+"'>" 
                                                    //+ 
                                                      "<span>"+ "<input type='checkbox' class='cb_var cb_var_"+nom_param+"' id='"+id_tmp+"' value='"+id_tmp+"'  idesta='"+cod_esta+"' fecha='"+fecha_trama+"' codvar='"+nom_param+"'  /><label for='"+id_tmp+"'></label>" + n + "</span>"
                                                    + "<div style='display: none' id='div_reg_"+id_tmp+"'></div> "
                                                    //+"<br>"+btn_flag;
                                                    + (p.equalsIgnoreCase("ADMIN")?"<br>"+btn_flag:"") ;
                                                  //+ "</div>";
                                                  
                                                  
                                  vx_t.add(divdato); 
                            }                            
                        }
                    
                    
                    
                    String exist = (String) vx.get(vx.size() - 1);
                            // btn para bajar trama
                            String btn_bajartrama = "";//(String) vx.get(vx.size() - 1);
                            String btn_mostrartrama = "";
                            for(int x = 0 ; x<v_rawdata.size();x++){
                                Vector v_rawdata_sub =  (Vector) v_rawdata.get(x);
                                String fec_rawdata = (String) v_rawdata_sub.get(1);
                                String t_rawdata = (String) v_rawdata_sub.get(2);
                                t_rawdata = t_rawdata.replace("\"", " ").replace("'", "\\\\\\'").replace("\\", "\\\\\\\\");
                                //System.out.println(fec_rawdata +" -> " +fecha_trama);
                                //System.out.println(t_rawdata);
                                if(fec_rawdata.equalsIgnoreCase(fecha_trama)){
                                    //System.out.println(fec_rawdata +" -> " +fecha_trama + " ok");                                    
                                    String id_div = fecha_trama.replace(" ","").replace("/","").replace(":","");
                                    btn_bajartrama   = "<div align='center' style='float:left' id='div_fecha_"+id_div+"'><ul><li style='width:18px' class='ui-state-default ui-corner-all btn_reg_rawdata btn_reg_rawdata_"+exist+"' title='Descargar datos "+fecha_trama+"' onclick='registrar_rawdata(\""+id_div+"\",\""+t_rawdata+"\")'><span class='ui-icon ui-icon-circle-arrow-s'></span></li></ul></div>";
                                    btn_mostrartrama = "<div align='center' style='float:right'><ul><li style='width:18px' class='ui-state-default ui-corner-all title='mostrar rawdata "+fecha_trama+"' onclick='$.dialog({title: \"RAWDATA del "+fecha_trama+"\" , content:\""+t_rawdata+"\"})'><span class='ui-icon ui-icon-info'></span></li></ul></div>";
                                    break;
                                }
                            }
                    if(p.equalsIgnoreCase("ADMIN"))
                       vx_t.add(btn_bajartrama + btn_mostrartrama); 
                    else
                        vx_t.add("");
                    
                    vx_t.add(exist); 
                    
                    datos_tmp.add(vx_t);
            }
            
            Util util = new Util();            
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bFilter");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("bLengthChange");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
            sv.add("bInfo");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
            sv.add("bPaginate");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
            sv.add("iDisplayLength");sv.add(datos_tmp.size());vc_tbl.add(sv);sv =  new Vector();
//            sv.add("bJQueryUI");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            
            sv.add("fixedHeader");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("oTableTools");sv.add("{\"aButtons\": [{\"sExtends\": \"collection\",\"sButtonText\": \"Guardar\",\"aButtons\":[ \"xls\"]}]}");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm'},{text:'CAMBIO DE ESTADO MASIVO',action:function( e, dt, node, config ) {sisdad_confirm_cambio_estado_masivo()},className:'btn btn-info btn-sm'},{text:'CAMBIO DE FACTOR MASIVO',action:function( e, dt, node, config ) {sisdad_confirm_cambio_factor_masivo()},className:'btn btn-info btn-sm'}]");vc_tbl.add(sv);sv =  new Vector();
            //////////////////////////////////////////////////////////
            
            sv.add("aoColumns");sv.add("["
                                    + "{\"sTitle\":\"FECHA\",\"sWidth\":\"140px\" } , "
                                    + col_tbl    
                                    + "{\"sTitle\":\""+"<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all' title='Descargar todos los datos' onclick='registrar_rawdata_all()'><span class='ui-icon ui-icon-circle-arrow-s'></span></li></ul></div>"+"\",\"sWidth\":\"40px\" }, " //boton masivo para descargar todos los datos
                                    + "{\"sTitle\":\"-\",\"sWidth\":\"40px\" } "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(new Util().vector2json_tbl(datos_tmp));vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumnDefs");sv.add("[{\"sClass\":\"center\",\"aTargets\":["+col_center+"]},{\"aTargets\":["+ult_col+"],\"bVisible\": false,\"bSearchable\": false}]");vc_tbl.add(sv);sv =  new Vector();
            ///////////////////////////////////////////////////////
           
            String fnc =    "function( nRow, aData, iDisplayIndex ){ "
                              ////Pintar de rojo el registro si no tiene datos
                              + " if(rtrim(aData["+ult_col+"]) == '0'){$('td', nRow).addClass('ui-state-error');} "
                              //buscar datos dudosos y malos
                              
                              + " for(var i = 1 ; i< aData.length - 2 ; i++){ "
                              + " var d = $('td:eq('+i+')', nRow).children('span').html(); " 
                              //+ " console.log(d); "
                              + " if( d != undefined ) {"
                              + " var e = d.substring(d.length-1, d.length); "
                              + " var v = d.substring(0,d.length-1); "
                              //+ " console.log(e); "
                                + " if( e == 'D' ){ "
                                //+ "   console.log(e); "
                                + "   $('td:eq('+i+')', nRow).children('span').html( v ); "
                                + "   $('td:eq('+i+')', nRow).addClass('ui-state-highlight' ); "
                                + " }if( e == 'M' ){ "
                                + "   $('td:eq('+i+')', nRow).children('span').html( v ); "
                                + "   $('td:eq('+i+')', nRow).addClass('ui-state-error' ); "
                                + " }"
                              ///////+ " console.log($('td:eq("+"0"+")', nRow).html());  "
                    
//                                    //mostrar botones de malo y dudoso
//                                    + " $('td:eq('+i+')', nRow).mouseover(function() {" 
//                                    +  "  $(this).children('ul').show(); " 
//                                    +  " }); "
//                                    + " $('td:eq('+i+')', nRow).mouseout(function() { " 
//                                    +  "  $(this).children('ul').hide(); " 
//                                    +  " }); "
                              + " } "
                              + " }"
                          + "}";
            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
            ///////////////////////////////////////////////////////
            
            String tbl_html = "<table border='1' class='table table-striped table-bordered '  id='c_table_datos'></table>";
            String tbl = util.datatable("c_table_datos",vc_tbl);            
            request.setAttribute("c_table_datos", tbl_html + tbl );
            
            return "sisdad/tbl_datos";
	}
        
        @RequestMapping(value = { "/sisdad/registrar_rawdata"}, method = RequestMethod.GET)
	public String SisdadRegistrarRawData(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            
            String raw_data = request.getParameter("raw_data");
            String array[] = new String[2];
            array[0] = raw_data;
            array[1] = "1";
            Vector v_datos = new ConeccionDB().EjecutarProcedureOracle("pkg_decode.sp_reg_trama_goes", array);

//            Vector sv = (Vector) v_datos.get(0);
            
            String json = new Util().vector2json(v_datos);
            request.setAttribute("response", json);        
//            String cod_esta = (String) sv.get(0);
//            String fecha = (String) sv.get(1);            
            
            return "sisdad/registrar_rawdata";
	}
        
        @RequestMapping(value = { "/sisdad/act_estado_reg"}, method = RequestMethod.GET)
	public String SisdadActEstadoReg(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
                        
            String codesta = request.getParameter("codesta");
            String fecha = request.getParameter("fecha");
            String param = request.getParameter("param");
            String estado = request.getParameter("estado");
            String array[] = new String[4];
            array[0] = codesta;
            array[1] = fecha;
            array[2] = param;
            array[3] = estado;
            Vector v_datos = new ConeccionDB().EjecutarProcedureOracle("pkg_decode.sp_act_flag", array);

            Vector sv = (Vector) v_datos.get(0);
            
//            String json = new Util().vector2json(v_datos);
//            request.setAttribute("response", json);        
            String rpta = (String) sv.get(0);
//            String fecha = (String) sv.get(1);            
            
            request.setAttribute("response", rpta);

            return "sisdad/act_estado_reg";
	}
        
        @RequestMapping(value = { "/sisdad/act_factconver_reg"}, method = RequestMethod.GET)
	public String SisdadActFactConverReg(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
                        
            String codesta = request.getParameter("codesta");
            String fecha = request.getParameter("fecha");
            String param = request.getParameter("param");
            String fact = request.getParameter("fact");
            String array[] = new String[4];
            array[0] = codesta;
            array[1] = fecha;
            array[2] = param;
            array[3] = fact;
            Vector v_datos = new ConeccionDB().EjecutarProcedureOracle("pkg_decode.sp_act_factconver", array);

            Vector sv = (Vector) v_datos.get(0);
            
//            String json = new Util().vector2json(v_datos);
//            request.setAttribute("response", json);        
            String rpta = (String) sv.get(0);
//            String fecha = (String) sv.get(1);            
            
            request.setAttribute("response", rpta);

            return "sisdad/act_factconver_reg";
	}
        
        
        @RequestMapping(value = { "/sisdad/mant_esta_goes"}, method = RequestMethod.GET)
	public String SisdadMantEstaGoes(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            request.setAttribute("title_pag","AKEEEEEEE VA EL TETOLOOOO!! PAPAYYYY");             
            request.setAttribute("btn_nuevo_reg","alert('aki toy')");
            return "sisdad/mant_esta_goes";
	}
        
        @RequestMapping(value = { "/sisdad/mant_esta_goes_tbl"}, method = RequestMethod.GET)
	public String SisdadMantEstaGoesTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();
            cn.oracle_login("SISDAD", "SISDAD");
            
            Vector v_datos =  cn.EjecutarSelectOracle(  " select e.v_cod_esta , e.v_nom_esta  , d.v_nom_dreg, g.v_cod_goes , gm.v_nom_goesmarca " +
                                                        " from dadtbp_goes g , semap_esta e ,dadtbp_goesmarca gm , sentbp_dirreg d " +
                                                        " where e.v_cod_esta = g.v_cod_esta  " +
                                                        " and e.v_cod_dre = d.c_cod_dreg " +
                                                        " and g.v_cod_goesmarca = gm.v_cod_goesmarca");
            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String codesta = vss.get(0).toString();
                String nomesta = vss.get(1).toString();
                String dz = vss.get(2).toString();
                String goes = vss.get(3).toString();
                String trama = vss.get(4).toString();
                String btn = "<button type=\"button\" class=\"btn btn-info\" onclick=\"sisdad_popup_mant_esta_goes('"+codesta+"')\"><span class=\"glyphicon glyphicon-edit\"></span></button>";
                
                Vector vv = new Vector();
                vv.add(codesta);
                vv.add(nomesta);
                vv.add(dz);
                vv.add(goes);
                vv.add(trama);
                vv.add(btn);
                v_temp.add(vv);                
            }
            
            
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. ESTACION");
            v_tbl_cab.add("NOM. ESTACION");
            //v_tbl_cab.add("COD DZ");
            v_tbl_cab.add("DIR. ZONAL");
            v_tbl_cab.add("COD. GOES");
            v_tbl_cab.add("TIPO TRAMA");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sisdad/mant_esta_goes_tbl";
	}
        
        
    @RequestMapping(value = { "/sisdad/mant_nueva_estacion"}, method = RequestMethod.GET)
    public String SisdadNuevaEstacion(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        request.setAttribute("title_pag","REGISTRAR NUEVA ESTACION GOES");
        
        return "sisdad/mant_nueva_estacion";
    } 
    
    
    @RequestMapping(value = { "/sisdad/mant_ptoobs_subirfile"}, method = RequestMethod.GET)
    public String SisdadPtoObsSubirFile(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        request.setAttribute("title_pag","Subir File");
        
        return "sisdad/mant_ptoobs_subirfile";
    }
    
     @RequestMapping(value = { "/sisdad/mant_ptoobs_reporte"}, method = RequestMethod.GET)
    public String SisdadPtoObsReporte(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        request.setAttribute("title_pag","Reporte");
        
        return "sisdad/mant_ptoobs_reporte";
    } 
    
    
        
        
}