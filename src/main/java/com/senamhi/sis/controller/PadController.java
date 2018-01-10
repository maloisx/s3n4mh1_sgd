package com.senamhi.sis.controller;

import com.senamhi.sis.connection.ConeccionDB;
import com.senamhi.sis.functions.Util;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Kori
 */
@Controller
@RequestMapping("/")
public class PadController {

//INICIO LISTA DE EXPEDIENTE DEL PAD BASE        
    @RequestMapping(value = {"/pad/mant_expedientes_pad"}, method = RequestMethod.GET)
	public String MantExpedientesPad(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","EXPEDIENTES DEL PAD");
            
            ConeccionDB cn =  new ConeccionDB();   
            Util util = new Util();
//          información para el combo Abogado
            String abogado = "pad.fn_abogado_consulta";
            String array_cbo_abogado[] = new String[1];
            array_cbo_abogado[0] = "";
            Vector datos_cbo_abogado = cn.EjecutarProcedurePostgres(abogado, array_cbo_abogado);
            String cb_abogado = util.contenido_combo(datos_cbo_abogado, "");
            request.setAttribute("abogado", cb_abogado);
                        
            
            return "pad/mant_expedientes_pad";
	}
//FIN LISTA DE EXPEDIENTE DEL PAD BASE
//
//INICIO LISTA DE EXPEDIENTE DEL PAD TABLA        
    @RequestMapping(value = {"/pad/mant_expedientes_pad_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryExpedientePadTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            String abogado = request.getParameter("abogado");  
            
            if (abogado.equals("undefined")){
                abogado = "";
            }
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "pad.fn_expediente_pad_consulta";
            String array[] = new String[1];
            array[0] = abogado;
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String v_id_exp = vss.get(0).toString();
                String d_fec_exp = vss.get(1).toString();
                String v_descripcion_etapa = vss.get(2).toString();                
                String v_id_denunciante = vss.get(3).toString();                
                String v_abogado = vss.get(5).toString();                
                String d_fec_presc_ipad = vss.get(7).toString();                
                String investigado = vss.get(15).toString();                
                
                String chkb = "<div class='form-group dropdown text-center'>"
                            + "<input type='checkbox' value='"+v_id_exp+'_'+d_fec_exp+'_'+v_descripcion_etapa+"' id='cb_exp_"+v_id_exp+"' class='cb_exp' onchange='pad_lista_exp_asigna_abogado_tmp(this)' />"
                            + "<label for='cb_exp_"+v_id_exp+"'></label>"
                            + "</div>";
                
                String boton = "<div class='form-group dropdown'>"
                            + "<button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown' id='herramientas'>"
                            + "<span class='glyphicon glyphicon-wrench'>"
                            + "</span>"
                            + "</button>"
                            + "<ul class='dropdown-menu dropdown-menu-left'>" 
                            + "<li class='divider'></li>"
                            + "<li><a onclick='pad_mant_expedientes_pad_consulta_popup(\\\""+v_id_exp+"\\\")'>Consultar expediente</a></li>"
                            + "<li><a onclick='pad_mant_expedientes_pad_modifica_popup(\\\""+v_id_exp+"\\\")'>Adicionar documento</a></li>"
                            + "<li class='divider'></li>"
                            + "<li  class=''><a onclick='pad_mant_investigado_consulta_popup(\\\""+v_id_exp+"\\\")'>Consultar investigados</a></li>"
                            + "<li  class=''><a onclick='pad_mant_investigado_popup(\\\""+v_id_exp+"\\\")'>Adicionar investigado</a></li>"
                            + "<li class='divider'></li>"  
                            + "</ul>"
                            + "</div>";                          
                
                Vector vv = new Vector();
                vv.add(chkb);
                vv.add(v_id_exp);
                vv.add(d_fec_exp);
                vv.add(d_fec_presc_ipad);
                vv.add(v_descripcion_etapa);
                vv.add(v_id_denunciante);
                vv.add(investigado);
                vv.add(v_abogado);
                vv.add(boton);
                v_temp.add(vv);                
            }    
            
            Util util = new Util();
            String json = util.vector2json(v_temp);   
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("sScrollY");sv.add("'80%'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumns");sv.add("["                                    
                                    + "{'sTitle':'_'} , "
                                    + "{'sTitle':'EXPEDIENTE'} , "
                                    + "{'sTitle':'FECHA RECEP.ORH'} , "
                                    + "{'sTitle':'FECHA PRESCR.INICIO PAD'} , "
                                    + "{'sTitle':'ETAPA'} , "
                                    + "{'sTitle':'DENUNCIANTE'} , "
                                    + "{'sTitle':'DENUNCIADO(S)'} , "
                                    + "{'sTitle':'ABOGADO'} , "
                                    + "{'sTitle':'HERRAMIENTAS'}  "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
            //sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1]},{'aTargets':[ 2 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' },{ extend:'print',text:'imprimir',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

            String tbl_html = "<table border='1' class='table table-striped table-bordered table-responsive-sm text-center' id='c_tbl_exp_pad'></table>";
            String tbl = util.datatable("c_tbl_exp_pad",vc_tbl);            
            request.setAttribute("response", tbl_html + tbl);

            return "pad/mant_expedientes_pad_tbl";
	}
//FIN LISTA DE EXPEDIENTE DEL PAD TABLA            
//
//INICIO MANTENIMIENTO NUEVO EXPEDIENTE DEL PAD POPUP            
    @RequestMapping(value = {"/pad/mant_expedientes_pad_popup"}, method = RequestMethod.GET)
    public String MantExpedientePadPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","REGISTRO DE EXPEDIENTE");     

        try {
            String id = request.getParameter("id");
            
            Calendar c = Calendar.getInstance();//Anio actual para el registro del expediente           
            String anio = Integer.toString(c.get(Calendar.YEAR));
            
            Date date = new Date();//Fecha de registro del documento (por referencia)
            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy");
            String fecharecep = formatofec.format(date);        
                        
            //Fecha del documeto, por defecto el día actual            
            DateFormat fecDoc = new SimpleDateFormat("dd/MM/yyyy");
            String fec_doc = fecDoc.format(date); 
            
            //Fecha de prescripción del inicio del PAD
//            Date nuevaFecha = new Date();
//            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.DAY_OF_YEAR, 365);
//            nuevaFecha = cal.getTime();
//            String fecpresc_iniPAD = formatofec.format(nuevaFecha); 
            
            ConeccionDB cn = new ConeccionDB(); 
            Util util =  new Util();

//          información para el combo periodo (año)
            String ne = "pad.fn_anio_consulta";
            String array_cbo_anio[] = new String[1];
            array_cbo_anio[0] = "";
            Vector datos_cbo_anio = cn.EjecutarProcedurePostgres(ne, array_cbo_anio);            
            String cb_anio = util.contenido_combo(datos_cbo_anio, anio);
            request.setAttribute("anio", cb_anio);  
            
            request.setAttribute("fecharecep", fecharecep);
            request.setAttribute("fecdoc", fec_doc);
            
//          información para el combo Etapa
            String etapa = "pad.fn_etapa_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(etapa, array_cbo);
            String cb_etapa = util.contenido_combo(datos_cbo, "");
            request.setAttribute("etapa", cb_etapa);
            
//          información para el combo Abogado
            String abogado = "pad.fn_abogado_consulta";
            String array_cbo_abogado[] = new String[1];
            array_cbo_abogado[0] = "";
            Vector datos_cbo_abogado = cn.EjecutarProcedurePostgres(abogado, array_cbo_abogado);
            String cb_abogado = util.contenido_combo(datos_cbo_abogado, "");
            request.setAttribute("abogado", cb_abogado);
            
//          información para el combo Dependencia
            String dependencia = "pad.fn_dependencia_consulta";
            String array_cbo_dependencia[] = new String[1];
            array_cbo_dependencia[0] = "";
            Vector datos_cbo_dependencia = cn.EjecutarProcedurePostgres(dependencia, array_cbo_dependencia);
            String cb_dependencia = util.contenido_combo(datos_cbo_dependencia, "");
            request.setAttribute("dependencia", cb_dependencia);
            
//            información combos: denunciante, remite y destino
            String cons_remite_uo = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_remite_uo[] = new String[1];
            array_remite_uo[0] = "";
            Vector datos_cbo_uo_remite = cn.EjecutarProcedurePostgres(cons_remite_uo, array_remite_uo); 
            String cb_uo_rmte = util.contenido_combo(datos_cbo_uo_remite, "");
            request.setAttribute("remite", cb_uo_rmte); 
            
//            información combos: denunciante, remite y destino
            String cons_destino_uo = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_destino_uo[] = new String[1];
            array_destino_uo[0] = "";
            Vector datos_cbo_uo_destino = cn.EjecutarProcedurePostgres(cons_destino_uo, array_destino_uo); 
            String cb_uo_dest = util.contenido_combo(datos_cbo_uo_destino, "00122760");//UFS por defecto
            request.setAttribute("destino", cb_uo_dest); 
            
//            información tipo de documento
            String ntdoc = "sgd.fn_clasifdoc_seriedoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cbo_tdoc[] = new String[1];
            array_cbo_tdoc[0] = "90000048";
            Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
            String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, "118");
            request.setAttribute("clsfdoc", cb_desc_clsfdoc);            
          
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "pad/mant_expedientes_pad_popup";
    }   
//FIN MANTENIMIENTO NUEVO EXPEDIENTE DEL PAD POPUP
//    
//INICIO MANTENIMIENTO EXPEDIENTE GUARDAR    
@RequestMapping(value = {"/pad/mant_expedientes_pad_guardar"}, method = RequestMethod.GET)
    public String MantExpedientePadGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");//id del usuario

        String id = request.getParameter("id");
        String fecharecep = request.getParameter("fecharecep");
        String fecpresc_iniPAD = request.getParameter("fecpresc_iniPAD");
        String etapa = request.getParameter("etapa");
        String denunciante = request.getParameter("denunciante");
        String dependencia = request.getParameter("dependencia");
        String abogado = request.getParameter("abogado");
        String documento = request.getParameter("documento");
        String nrodoc = request.getParameter("nrodoc");        
        String fechadoc = request.getParameter("fechadoc");        
        String folio = request.getParameter("folio");        
        String plazo = request.getParameter("plazo");        
        String remite = request.getParameter("remite");        
        String destino = request.getParameter("destino");        
        String asunto =  request.getParameter("asunto").trim();
        asunto = asunto.replace("\"","'");
        asunto = asunto.replace("\n", "");
        String observacion =  request.getParameter("observacion").trim();      
        String iddoc = request.getParameter("iddoc");  
        String instructor = request.getParameter("instructor");  
        String sancionador = request.getParameter("sancionador");  
        String fecnotif_iniPAD = request.getParameter("fecnotif_iniPAD");  
        String fecpres_PAD = request.getParameter("fecpres_PAD");  
//        
        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "pad.fn_expediente_pad_mant";
            String array[] = new String[22];
            array[0] = id;
            array[1] = fecharecep;
            array[2] = fecpresc_iniPAD;
            array[3] = etapa;
            array[4] = denunciante;
            array[5] = dependencia;
            array[6] = abogado;
            array[7] = codUser;
            array[8] = documento;
            array[9] = nrodoc;
            array[10] = fechadoc;
            array[11] = folio;
            array[12] = plazo;
            array[13] = remite;
            array[14] = destino;
            array[15] = asunto;
            array[16] = observacion;
            array[17] = iddoc;
            array[18] = instructor;
            array[19] = sancionador;
            array[20] = fecnotif_iniPAD;
            array[21] = fecpres_PAD;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "pad/mant_expedientes_pad_guardar";
    }
//FIN MANTENIMIENTO EXPEDIENTE GUARDAR     
//
//    
//INICIO MANTENIMIENTO EXPEDIENTE NUEVO GUARDAR    
@RequestMapping(value = {"/pad/mant_expedientes_pad_nuevo_guardar"}, method = RequestMethod.GET)
    public String MantExpedientePadNuevoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");//id del usuario

        String id = request.getParameter("id");
        String fecharecep = request.getParameter("fecharecep");
        String fecpresc_iniPAD = request.getParameter("fecpresc_iniPAD");
        String etapa = request.getParameter("etapa");
        String denunciante = request.getParameter("denunciante");
        String dependencia = request.getParameter("dependencia");
        String abogado = request.getParameter("abogado");
        String documento = request.getParameter("documento");
        String nrodoc = request.getParameter("nrodoc");        
        String fechadoc = request.getParameter("fechadoc");        
        String folio = request.getParameter("folio");        
        String plazo = request.getParameter("plazo");        
        String remite = request.getParameter("remite");        
        String destino = request.getParameter("destino");        
        String asunto =  request.getParameter("asunto").trim();
        asunto = asunto.replace("\"","'");
        asunto = asunto.replace("\n", "");
        String observacion =  request.getParameter("observacion").trim();      
        String iddoc = request.getParameter("iddoc");  
        String instructor = request.getParameter("instructor");  
        String sancionador = request.getParameter("sancionador");  
        String fecnotif_iniPAD = request.getParameter("fecnotif_iniPAD");  
        String fecpres_PAD = request.getParameter("fecpres_PAD");  
        String anio = request.getParameter("anio");  
//        
        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "pad.fn_expediente_pad_mant_nuevo";
            String array[] = new String[23];
            array[0] = id;
            array[1] = fecharecep;
            array[2] = fecpresc_iniPAD;
            array[3] = etapa;
            array[4] = denunciante;
            array[5] = dependencia;
            array[6] = abogado;
            array[7] = codUser;
            array[8] = documento;
            array[9] = nrodoc;
            array[10] = fechadoc;
            array[11] = folio;
            array[12] = plazo;
            array[13] = remite;
            array[14] = destino;
            array[15] = asunto;
            array[16] = observacion;
            array[17] = iddoc;
            array[18] = instructor;
            array[19] = sancionador;
            array[20] = fecnotif_iniPAD;
            array[21] = fecpres_PAD;
            array[22] = anio;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "pad/mant_expedientes_pad_nuevo_guardar";
    }
//FIN MANTENIMIENTO EXPEDIENTE NUEVO GUARDAR     
//
//INICIO SUBIR DOCUMENTOS
@RequestMapping(value = {"/pad/uploadfile"}, method = RequestMethod.POST)
    public String uploadfile(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
        ConeccionDB cdb = new ConeccionDB();
        
        String UPLOAD_DIRECTORY = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/pad"; 
        String msj = "";
                
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                
                if (isMultipart) {
                    // Create a factory for disk-based file items
                    FileItemFactory factory = new DiskFileItemFactory();
                    
                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    
                    try {                       
                        List items = upload.parseRequest(request);
                        Iterator iterator = items.iterator();
                        while (iterator.hasNext()) {
                            FileItem item = (FileItem) iterator.next();
                            if (!item.isFormField()) {
                                String fileName = item.getName();  
                                String fileName_1 = item.getName();  
                                String field = item.getFieldName();
                                    StringTokenizer str =  new StringTokenizer(field,"|");
                                    String anio = str.nextToken();
                                    String exp = str.nextToken();
                                    String iddoc = str.nextToken();
                                    String in = str.nextToken();
                                    String dir = ""+anio+"_"+exp+"_"+iddoc+"";
                                    dir = DigestUtils.md5Hex(dir);                               
                                
                                fileName = DigestUtils.md5Hex(fileName);
                                File path = new File(UPLOAD_DIRECTORY+"/"+anio+"/"+"/"+dir);
                                if (!path.exists()) {
                                    boolean status = path.mkdirs();
                                }
                                
                                File uploadedFile = new File(path +"/"+ fileName+".pdf"); 
                                msj += uploadedFile.getAbsolutePath()+"";
                                item.write(uploadedFile);
                                
                                String np = "pad.fn_docadjunto_mant";            
                                String array[] = new String[4];
                                array[0] = iddoc;
                                array[1] = fileName+".pdf";
                                array[2] = dir;
                                array[3] = fileName_1;
                                
                                Vector datos = cdb.EjecutarProcedurePostgres(np, array); 
                            }
                        }
                        msj += "File Uploaded Successfully";
                        request.setAttribute("request", msj);
                        
                    } catch (FileUploadException e) {
                        request.setAttribute("request", "x1 File Upload Failed due to " + e.getMessage());
                    } catch (Exception e) {
                        request.setAttribute("request", "x2 File Upload Failed due to " + e.getMessage());
                    }
                }
        return "pad/uploadfile";
    }  
//FIN SUBIR DOCUMENTOS   
//
//INICIO LISTA ADJUNTOS
    @RequestMapping(value = {"/pad/mant_adjunto_cargar"}, method = RequestMethod.GET)
    public String MantAdjuntoCargar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {        
        try {
            String id_doc = request.getParameter("id_doc"); 
            ConeccionDB cn = new ConeccionDB();
                        
            String np = "pad.fn_docadjunto_consulta";
            String array[] = new String[1];
            array[0] = id_doc;
            Vector datos = cn.EjecutarProcedurePostgres(np, array); 
            
            Vector v_tbl_cab_adj =  new Vector();
            v_tbl_cab_adj.add("Item");
            v_tbl_cab_adj.add("Doc. Adjunto");
//            v_tbl_cab_adj.add("Eliminar");
            v_tbl_cab_adj.add("Ver");
            
            Util util_adj = new Util();
            Vector v_temp_adj = new Vector();
            int d = 0; 
            int i = 0 ;
            for(i = 0 ; i<datos.size() ; i++){
                d = d+1;
                Vector datos_v =  (Vector) datos.get(i);
                String id = datos_v.get(0).toString();//id del documento
                String nom_doc = datos_v.get(1).toString();
                String dir = datos_v.get(2).toString();
                String anio = datos_v.get(3).toString();    
                String idn = datos_v.get(4).toString();    
                String nombre = datos_v.get(5).toString();    
//                String btn_elim = "<div class='form-group dropdown'>"
//                                    + "<button type='button' class='btn btn-info' id='elim' onclick='sgd_mant_adjuntos_elim('"+id_doc+"','"+nom_doc+"')'>"
//                                    + "<span class='glyphicon glyphicon-remove'>"
//                                    + "</span>"
//                                    + "</button>"
//                                    + "</div>";
                
                String btn_ver = "<div class='form-group dropdown text-center'>"
                                    + "<button type='button' class='btn btn-info' onclick='window.open(\"http://sgd.senamhi.gob.pe/files/sgd/"+anio+"/"+dir+"/"+nom_doc+"\")'>"
                                    + "<span class='glyphicon glyphicon-search'>"
                                    + "</span>"
                                    + "</button>"
                                    + "</div>";
                
                Vector v_adj = new Vector();
                v_adj.add(idn);
                v_adj.add(nombre);
//                v_adj.add(btn_elim);
                v_adj.add(btn_ver);
                v_temp_adj.add(v_adj);
            }
            String tbl_doc_adj = util_adj.tbl(v_tbl_cab_adj, v_temp_adj); 
            if (i == 0){
                request.setAttribute("response","El documento no tiene adjuntos.");
            }else{
                request.setAttribute("response",tbl_doc_adj);
            }        
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "pad/mant_adjunto_cargar";
    }     
//FIN LISTA ADJUNTOS
//    
//INICIO MANTENIMIENTO MODIFICAR EXPEDIENTE DEL PAD POPUP            
    @RequestMapping(value = {"/pad/mant_expedientes_pad_modifica_popup"}, method = RequestMethod.GET)
    public String MantExpedientePadModificaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","ADICIONAR DOCUMENTO");     

        try {
            
            Date date = new Date();//Fecha de registro del documento (por referencia)
            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy");
            String fec_doc = formatofec.format(date); 
            
            ConeccionDB cn = new ConeccionDB(); 
            Util util =  new Util();
            
            String id_exp = request.getParameter("id");
            
//            String cn_doc = "pad.fn_expediente_doc_pad_consulta";
//            String cn_doc = "pad.fn_expediente_doc_pad_consulta";
//                String array_doc[] = new String[1];
//                array_doc[0] = iddoc;
//                Vector datos_doc = cn.EjecutarProcedurePostgres(cn_doc, array_doc);
//                String v_id_exp = "";          
//                String i_id_doc = "";          
//                String i_id_clsfdoc = "";          
//                String v_num_doc = "";    
//                String i_folio_doc = "";    
//                String d_fec_doc = "";    
//                String i_plazo_rpta = "";    
//                String v_remite_doc = "";    
//                String v_destino_doc = "";    
//                String v_asunto_doc = "";    
//                String v_observacion_doc = "";    

//                for(int i = 0 ; i<datos_doc.size() ; i++){                 
//                    Vector datos_v =  (Vector) datos_doc.get(i);
//                    v_id_exp = datos_v.get(0).toString();
//                    i_id_doc = datos_v.get(0).toString();
//                    i_id_clsfdoc = datos_v.get(2).toString();
//                    v_num_doc = datos_v.get(3).toString();
//                    i_folio_doc = datos_v.get(4).toString();
//                    d_fec_doc = datos_v.get(5).toString();
//                    i_plazo_rpta = datos_v.get(6).toString();
//                    v_remite_doc = datos_v.get(7).toString();
//                    v_destino_doc = datos_v.get(8).toString();
//                    v_asunto_doc = datos_v.get(9).toString();                    
//                    v_observacion_doc = datos_v.get(10).toString();                    
//                } 
//            request.setAttribute("doc", i_id_doc);
//            request.setAttribute("nrodoc", v_num_doc);
//            request.setAttribute("folio", i_folio_doc);
//            request.setAttribute("fecdoc", d_fec_doc);
//            request.setAttribute("plazo", i_plazo_rpta);
//            request.setAttribute("asunto", v_asunto_doc);
//            request.setAttribute("observacion", v_observacion_doc);
            ////////////////////////////////////////////////////////////////////////////////////////            
            
//            String id = request.getParameter("id");                                  
            
            String np = "pad.fn_expediente_pad_consulta";
                String array[] = new String[1];
                array[0] = id_exp;
                Vector datos = cn.EjecutarProcedurePostgres(np, array);

                String v_id_exp = "";
                String d_fec_recep = "";          
                String d_fecpresc_iniPAD = "";          
                String i_id_etapa = "";        
                String v_id_denunciante = "";      
                String i_id_dependencia = "";      
                String v_id_abogado = "";      
                String v_organo_instr = "";      
                String v_organo_sanc = "";      
                String fecnotif_iniPAD = "";      
                String fecpres_PAD = "";      

                for(int i = 0 ; i<datos.size() ; i++){                 
                    Vector datos_v =  (Vector) datos.get(i);
                    v_id_exp = datos_v.get(0).toString();
                    d_fec_recep = datos_v.get(1).toString();
                    d_fecpresc_iniPAD = datos_v.get(7).toString();
                    i_id_etapa = datos_v.get(6).toString();
                    v_id_denunciante = datos_v.get(8).toString();
                    i_id_dependencia = datos_v.get(9).toString();
                    v_id_abogado = datos_v.get(10).toString();
                    v_organo_instr = datos_v.get(11).toString();
                    v_organo_sanc = datos_v.get(12).toString();
                    fecnotif_iniPAD = datos_v.get(13).toString();
                    fecpres_PAD = datos_v.get(14).toString();
                }  
            
            request.setAttribute("nroexp", v_id_exp);
            request.setAttribute("fecharecep", d_fec_recep);
            request.setAttribute("fecpresc_iniPAD", d_fecpresc_iniPAD);
            request.setAttribute("fecnotif_iniPAD", fecnotif_iniPAD);
            request.setAttribute("fecpres_PAD", fecpres_PAD);
            request.setAttribute("fecdoc", fec_doc);
            
//          información para el combo Etapa
            String etapa = "pad.fn_etapa_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(etapa, array_cbo);
            String cb_etapa = util.contenido_combo(datos_cbo, i_id_etapa);
            request.setAttribute("etapa", cb_etapa);
                        
//            información combos: denunciante
            String cn_denunciante = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_denunciante[] = new String[1];
            array_denunciante[0] = "";
            Vector datos_cbo_denunciante = cn.EjecutarProcedurePostgres(cn_denunciante, array_denunciante); 
            String cb_denunciante = util.contenido_combo(datos_cbo_denunciante, v_id_denunciante);
            request.setAttribute("denunciante", cb_denunciante); 
            
//            información para el combo Dependencia
            String dependencia = "pad.fn_dependencia_consulta";
            String array_cbo_dependencia[] = new String[1];
            array_cbo_dependencia[0] = "";
            Vector datos_cbo_dependencia = cn.EjecutarProcedurePostgres(dependencia, array_cbo_dependencia);
            String cb_dependencia = util.contenido_combo(datos_cbo_dependencia, i_id_dependencia);
            request.setAttribute("dependencia", cb_dependencia); 
            
//          información para el combo Abogado
            String abogado = "pad.fn_abogado_consulta";
            String array_cbo_abogado[] = new String[1];
            array_cbo_abogado[0] = "";
            Vector datos_cbo_abogado = cn.EjecutarProcedurePostgres(abogado, array_cbo_abogado);
            String cb_abogado = util.contenido_combo(datos_cbo_abogado, v_id_abogado);
            request.setAttribute("abogado", cb_abogado);
            
//          información para el combo Órgano instructor / Órgano sancionador
            String organo_ins = "pad.fn_organo_consulta";
            String array_cbo_organo_ins[] = new String[1];
            array_cbo_organo_ins[0] = "";
            Vector datos_cbo_organo_ins = cn.EjecutarProcedurePostgres(organo_ins, array_cbo_organo_ins);
            String cb_organo_ins = util.contenido_combo(datos_cbo_organo_ins, v_organo_instr);
            request.setAttribute("organo_ins", cb_organo_ins);
                        
//          información para el combo Órgano sancionador
            String organo_san = "pad.fn_organo_consulta";
            String array_cbo_organo_san[] = new String[1];
            array_cbo_organo_san[0] = "";
            Vector datos_cbo_organo_san = cn.EjecutarProcedurePostgres(organo_san, array_cbo_organo_san);
            String cb_organo_san = util.contenido_combo(datos_cbo_organo_san, v_organo_sanc);
            request.setAttribute("organo_san", cb_organo_san);
                        
            /////////////////////////////////////////////////////////////////////////////////////////////////////////            
            
//            información tipo de documento
            String ntdoc = "sgd.fn_clasifdoc_seriedoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cbo_tdoc[] = new String[1];
            array_cbo_tdoc[0] = "90000048";
            Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
            String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, "");
            request.setAttribute("clsfdoc", cb_desc_clsfdoc);
          
//            información combos: remite
            String cons_rmte = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_rmte[] = new String[1];
            array_rmte[0] = "";
            Vector datos_cbo_rmte = cn.EjecutarProcedurePostgres(cons_rmte, array_rmte); 
            String cb_rmte = util.contenido_combo(datos_cbo_rmte, "");
            request.setAttribute("remite", cb_rmte); 
            
//            información combos: destino
            String cons_destino = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_destino[] = new String[1];
            array_destino[0] = "";
            Vector datos_cbo_destino = cn.EjecutarProcedurePostgres(cons_destino, array_destino); 
            String cb_destino = util.contenido_combo(datos_cbo_destino, "");
            request.setAttribute("destino", cb_destino); 
            
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "pad/mant_expedientes_pad_modifica_popup";
    }   
//FIN MANTENIMIENTO MODIFICAR EXPEDIENTE DEL PAD POPUP
// 
//INICIO CONSULTA EXPEDIENTE DEL PAD POPUP            
    @RequestMapping(value = {"/pad/mant_expedientes_pad_consulta_popup"}, method = RequestMethod.GET)
    public String MantExpedientePadConsultaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","CONSULTA DE EXPEDIENTE");     

        try {
            String nroexp = request.getParameter("id");
                        
            ConeccionDB cn = new ConeccionDB(); 
            Util util =  new Util();
            String np = "pad.fn_expediente_pad_consulta";
                String array[] = new String[1];
                array[0] = nroexp;
                Vector datos = cn.EjecutarProcedurePostgres(np, array);

                String obj_active_form = "";

                String v_id_exp = "";
                String d_fec_recep = "";          
                String d_fecpresc_iniPAD = "";          
                String i_id_etapa = "";                        
                String fecnotif_iniPAD = "";      
                String fecpres_PAD = "";      

                for(int i = 0 ; i<datos.size() ; i++){                 
                    Vector datos_v =  (Vector) datos.get(i);
                    v_id_exp = datos_v.get(0).toString();
                    d_fec_recep = datos_v.get(1).toString();
                    d_fecpresc_iniPAD = datos_v.get(7).toString();
                    i_id_etapa = datos_v.get(6).toString();
                    fecnotif_iniPAD = datos_v.get(13).toString();
                    fecpres_PAD = datos_v.get(14).toString();
                }  
                request.setAttribute("nroexp", nroexp); 
                request.setAttribute("fecharecep", d_fec_recep); 
                request.setAttribute("fecpresc_iniPAD", d_fecpresc_iniPAD); 
                
//          información para el combo Etapa
            String etapa = "pad.fn_etapa_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(etapa, array_cbo);
            String cb_etapa = util.contenido_combo(datos_cbo, i_id_etapa);
            request.setAttribute("etapa", cb_etapa);  
            
//            información combos: denunciante
            String cn_denunciante = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_denunciante[] = new String[1];
            array_denunciante[0] = "";
            Vector datos_cbo_denunciante = cn.EjecutarProcedurePostgres(cn_denunciante, array_denunciante); 
            String cb_denunciante = util.contenido_combo(datos_cbo_denunciante, "");
            request.setAttribute("denunciante", cb_denunciante); 
            
//            información para el combo Dependencia
            String dependencia = "pad.fn_dependencia_consulta";
            String array_cbo_dependencia[] = new String[1];
            array_cbo_dependencia[0] = "";
            Vector datos_cbo_dependencia = cn.EjecutarProcedurePostgres(dependencia, array_cbo_dependencia);
            String cb_dependencia = util.contenido_combo(datos_cbo_dependencia, "");
            request.setAttribute("dependencia", cb_dependencia);      
            
//          información para el combo Abogado
            String abogado = "pad.fn_abogado_consulta";
            String array_cbo_abogado[] = new String[1];
            array_cbo_abogado[0] = "";
            Vector datos_cbo_abogado = cn.EjecutarProcedurePostgres(abogado, array_cbo_abogado);
            String cb_abogado = util.contenido_combo(datos_cbo_abogado, "");
            request.setAttribute("abogado", cb_abogado);
                        
//          información para el combo Órgano instructor / Órgano sancionador
            String organo_ins = "pad.fn_organo_consulta";
            String array_cbo_organo_ins[] = new String[1];
            array_cbo_organo_ins[0] = "";
            Vector datos_cbo_organo_ins = cn.EjecutarProcedurePostgres(organo_ins, array_cbo_organo_ins);
            String cb_organo_ins = util.contenido_combo(datos_cbo_organo_ins, "");
            request.setAttribute("organo", cb_organo_ins);    
            
//            información tipo de documento
            String ntdoc = "sgd.fn_clasifdoc_seriedoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cbo_tdoc[] = new String[1];
            array_cbo_tdoc[0] = "90000048";
            Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
            String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, "");
            request.setAttribute("clsfdoc", cb_desc_clsfdoc);            
            
//            información combos: remite
            String cons_rmte = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_rmte[] = new String[1];
            array_rmte[0] = "";
            Vector datos_cbo_rmte = cn.EjecutarProcedurePostgres(cons_rmte, array_rmte); 
            String cb_rmte = util.contenido_combo(datos_cbo_rmte, "");
            request.setAttribute("persona", cb_rmte);             
          
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "pad/mant_expedientes_pad_consulta_popup";
    }   
//FIN CONSULTA EXPEDIENTE DEL PAD POPUP
// 
//INICIO LISTA DOCUMENTOS POR EXPEDIENTE TABLA        
    @RequestMapping(value = {"/pad/mant_expedientes_pad_docs_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryExpedientesPadDocsTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws ParseException {
            
            String id = request.getParameter("nroexp");
                       
//            ConeccionDB cn =  new ConeccionDB();            

//            String np = "pad.fn_expediente_pad_docs_consulta";
//            String array[] = new String[1];
//            array[0] = id;
//            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
//
//            Vector v_temp = new Vector();
//            for(int i = 0 ; i<v_datos.size() ; i++){
//                Vector vss =  (Vector) v_datos.get(i);
//                String iddoc = vss.get(0).toString();
//                String tipodoc = vss.get(1).toString();
//                String fechadoc = vss.get(3).toString();
//                String asunto = vss.get(7).toString();                
//                String remite = vss.get(5).toString();                
//                String destino = vss.get(6).toString();                
//                
//                String btn = "<button type='button' class='btn btn-info' onclick='pad_mant_expedientes_pad_modifica_popup(\\\""+iddoc+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";
//                
//                Vector vv = new Vector();
//                vv.add(tipodoc);
//                vv.add(fechadoc);
//                vv.add(asunto);
//                vv.add(remite);
//                vv.add(destino);
//                vv.add(btn);
//                v_temp.add(vv);
//            }
//            
//            Util util = new Util();
//            String json = util.vector2json(v_temp);
//            Vector vc_tbl = new Vector();
//            Vector sv =  new Vector();
//            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("sScrollY");sv.add("'90%'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("aoColumns");sv.add("["
//                                    + "{'sTitle':'DOCUMENTO'} , "
//                                    + "{'sTitle':'FECHA DOC.'} , "
//                                    + "{'sTitle':'ASUNTO'} , "
//                                    + "{'sTitle':'REMITE'} , "
//                                    + "{'sTitle':'DESTINO'} , "
////                                    + "{'sTitle':'F.RESPUESTA'} , "
//                                    + "{'sTitle':'EDITAR'}  "
//                                    + "]");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//        //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
//            //boton de excel
//            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
////            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
//            ////Pintar de rojo el registro si no t.iene datos
////            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
////                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
////                          "}";
////            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
//
//            String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_exp_docs'></table>";
//            String tbl = util.datatable("c_tbl_exp_docs",vc_tbl);            
//            request.setAttribute("response", tbl_html + tbl);

        String btn_ver = "";
        String var_request = "";
        Util util = new Util();
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
             String np = "pad.fn_expediente_pad_docs_consulta";
            String array_docs[] = new String[1];
            array_docs[0] = id;
            Vector v_tbl_data_docs = cdb.EjecutarProcedurePostgres(np, array_docs);

            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("N");
            v_tbl_cab.add("DOCUMENTO");
            v_tbl_cab.add("FECHA DOC.");
            v_tbl_cab.add("ASUNTO");
            v_tbl_cab.add("FECHA RPTA.");
            v_tbl_cab.add("VER");

            Vector v_temp = new Vector();
            int k;
            for(k = 0 ; k<v_tbl_data_docs.size() ; k++){
                Vector vss =  (Vector) v_tbl_data_docs.get(k);
                String iddoc = vss.get(0).toString();
                String tipodoc = vss.get(1).toString();
                String fechadoc = vss.get(3).toString();
                String asunto = vss.get(7).toString();                
                String fec_rpta = vss.get(11).toString();                
                String idn_doc = vss.get(12).toString();

                btn_ver = "<div class='form-group dropdown text-center' >"
                                + "<button type='button' class='btn btn-info' id='ver' onclick='pad_mant_adjuntos_cargar(\""+iddoc+"\"), pad_mant_doc_detalle(\""+iddoc+"\")'>"
                                + "<span class='glyphicon glyphicon-search'>"
                                + "</span>"
                                + "</button>"
                                + "</div>";  
                
                Vector vv_d = new Vector();
                vv_d.add(idn_doc);
                vv_d.add(tipodoc);
                vv_d.add(fechadoc);                
                vv_d.add(asunto);
                vv_d.add(fec_rpta);
                vv_d.add(btn_ver);
                v_temp.add(vv_d);                
                }
            
        String tbl_deriva = util.tbl(v_tbl_cab, v_temp);              
        request.setAttribute("response",tbl_deriva);           
        
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "pad/mant_expedientes_pad_docs_tbl";
	}
//FIN LISTA DOCUMENTOS POR EXPEDIENTE TABLA         
//  
//INICIO DOCUMENTOS DETALLE    
    @RequestMapping(value = {"/pad/mant_doc_detalle"}, method = RequestMethod.GET)
    public String MantDocDetalle(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String id_doc = request.getParameter("id_doc"); 
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB();   
            Util util =  new Util();
                                   
            String np = "pad.fn_expediente_doc_pad_consulta";
            String array[] = new String[1];
            array[0] = id_doc;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            var_request = new Util().vector2json(datos);
                   
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        return "pad/mant_doc_detalle";
    }     
//FIN DOCUMENTOS DETALLE
//
//INICIO MANTENIMIENTO NUEVO INVESTIGADO POPUP            
    @RequestMapping(value = {"/pad/mant_investigado_popup"}, method = RequestMethod.GET)
    public String MantInvestigadoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","ADICIONAR INVESTIGADO");     

        try {
            String id = request.getParameter("id");
            request.setAttribute("nroexp", id);
            
            ConeccionDB cn = new ConeccionDB(); 
            Util util =  new Util();
            
//          información para el combo Investigado
            String etapa = "pad.fn_organo_consulta";
            String array_cbo_inv[] = new String[1];
            array_cbo_inv[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(etapa, array_cbo_inv);
            String cb_investigado = util.contenido_combo(datos_cbo, "");
            request.setAttribute("investigado", cb_investigado);
            
//          información para el combo Cargo
            String abogado = "pad.fn_cargo_consulta";
            String array_cbo_cargo[] = new String[1];
            array_cbo_cargo[0] = "";
            Vector datos_cbo_cargo = cn.EjecutarProcedurePostgres(abogado, array_cbo_cargo);
            String cb_cargo = util.contenido_combo(datos_cbo_cargo, "");
            request.setAttribute("cargo", cb_cargo);
            
//          información para el combo Falta
            String falta = "pad.fn_falta_consulta";
            String array_cbo_falta[] = new String[1];
            array_cbo_falta[0] = "";
            Vector datos_cbo_falta = cn.EjecutarProcedurePostgres(falta, array_cbo_falta);
            String cb_falta = util.contenido_combo(datos_cbo_falta, "");
            request.setAttribute("falta", cb_falta);
                      
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "pad/mant_investigado_popup";
    }   
//FIN MANTENIMIENTO NUEVO INVESTIGADO POPUP
// 
//INICIO CARGAR COMBO FALTAS
    @RequestMapping(value = {"/pad/mant_falta_cargar_cbo"}, method = RequestMethod.GET)
    public String MantFaltaCargarCbo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException { 
        String var_request = "";
        try {
            String id_normajur = request.getParameter("id_normajur"); 
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "pad.fn_falta_normajur_consulta";
            String array[] = new String[1];
            array[0] = id_normajur;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            var_request = new Util().contenido_combo(datos,"1");
            
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);        
        return "pad/mant_falta_cargar_cbo";
    } 
//FIN CARGAR COMBO FALTAS   
//    
//INICIO NUEVO INVESTIGADO GUARDAR    
@RequestMapping(value = {"/pad/mant_investigado_guardar"}, method = RequestMethod.GET)
    public String MantInvestigadoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {        

        String id_exp = request.getParameter("idexp");        
        String investigado = request.getParameter("investigado");        
        String cargo = request.getParameter("cargo");        
        String observacion = request.getParameter("observacion");        
        String faltas = request.getParameter("faltas");        
        
        String var_request = "";
        
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "pad.fn_investigado_mant";
            String array[] = new String[5];
            array[0] = investigado;
            array[1] = cargo;            
            array[2] = id_exp;            
            array[3] = observacion;            
            array[4] = faltas;     
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "pad/mant_investigado_guardar";
    }
//FIN NUEVO INVESTIGADO GUARDAR     
//
//INICIO CONSULTA INVESTIGADO POPUP            
    @RequestMapping(value = {"/pad/mant_investigado_consulta_popup"}, method = RequestMethod.GET)
    public String MantInvestigadoConsultaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","CONSULTA DE INVESTIGADOS");     

        try {
            String id = request.getParameter("id");
            request.setAttribute("nroexp", id);
            
//            Calendar c = Calendar.getInstance();//Anio actual para el registro del expediente           
//            String anio = Integer.toString(c.get(Calendar.YEAR));
//            
//            Date date = new Date();//Fecha de registro del documento (por referencia)
//            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy");
//            String fecharecep = formatofec.format(date);        
//                        
//            //Fecha del documeto, por defecto el día actual            
//            DateFormat fecDoc = new SimpleDateFormat("dd/MM/yyyy");
//            String fec_doc = fecDoc.format(date); 
            
            //Fecha de prescripción del inicio del PAD
//            Date nuevaFecha = new Date();
//            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.DAY_OF_YEAR, 365);
//            nuevaFecha = cal.getTime();
//            String fecpresc_iniPAD = formatofec.format(nuevaFecha); 
            
            ConeccionDB cn = new ConeccionDB(); 
            Util util =  new Util();
            
//          información para el combo Investigado
            String etapa = "pad.fn_organo_consulta";
            String array_cbo_inv[] = new String[1];
            array_cbo_inv[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(etapa, array_cbo_inv);
            String cb_investigado = util.contenido_combo(datos_cbo, "");
            request.setAttribute("investigado", cb_investigado);
            
//          información para el combo Cargo
            String abogado = "pad.fn_cargo_consulta";
            String array_cbo_cargo[] = new String[1];
            array_cbo_cargo[0] = "";
            Vector datos_cbo_cargo = cn.EjecutarProcedurePostgres(abogado, array_cbo_cargo);
            String cb_cargo = util.contenido_combo(datos_cbo_cargo, "");
            request.setAttribute("cargo", cb_cargo);       
            
//          información para el combo Falta
            String falta = "pad.fn_falta_consulta";
            String array_cbo_falta[] = new String[1];
            array_cbo_falta[0] = "";
            Vector datos_cbo_falta = cn.EjecutarProcedurePostgres(falta, array_cbo_falta);
            String cb_falta = util.contenido_combo(datos_cbo_falta, "");
            request.setAttribute("falta", cb_falta);
            
//            información combo Sanción
            String sancion = "pad.fn_sancion_consulta";//consulta combo destino                  
            String array_sancion[] = new String[1];
            array_sancion[0] = "";
            Vector datos_cbo_sancion = cn.EjecutarProcedurePostgres(sancion, array_sancion); 
            String cb_sancion = util.contenido_combo(datos_cbo_sancion, "");
            request.setAttribute("sancion", cb_sancion); 
            
//            información combo medida cautelar
            String medcaut = "pad.fn_medida_caut_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cbo_medcaut[] = new String[1];
            array_cbo_medcaut[0] = "";
            Vector datos_cbo_medcaut = cn.EjecutarProcedurePostgres(medcaut, array_cbo_medcaut);
            String cb_desc_medcaut = util.contenido_combo(datos_cbo_medcaut, "");
            request.setAttribute("medcaut", cb_desc_medcaut);
          
//            información combo recurso
            String recurso = "pad.fn_recurso_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cbo_recurso[] = new String[1];
            array_cbo_recurso[0] = "";
            Vector datos_cbo_recurso = cn.EjecutarProcedurePostgres(recurso, array_cbo_recurso);
            String cb_desc_recurso = util.contenido_combo(datos_cbo_recurso, "");
            request.setAttribute("recurso", cb_desc_recurso);
          
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "pad/mant_investigado_consulta_popup";
    }   
//FIN CONSULTA INVESTIGADO POPUP
//  
//INICIO LISTA DOCUMENTOS POR EXPEDIENTE TABLA        
    @RequestMapping(value = {"/pad/mant_investigados_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryInvestigadosTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws ParseException {
            
        String id = request.getParameter("nroexp");
                       
        String btn_ver = "";
        String var_request = "";
        Util util = new Util();
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
             String np = "pad.fn_investigados_consulta";
            String array_docs[] = new String[1];
            array_docs[0] = id;
            Vector v_tbl_data_docs = cdb.EjecutarProcedurePostgres(np, array_docs);

            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD.EMPL.");
            v_tbl_cab.add("INVESTIGADO");
            v_tbl_cab.add("CARGO");
            v_tbl_cab.add("VER");

            Vector v_temp = new Vector();
            int k;
            for(k = 0 ; k<v_tbl_data_docs.size() ; k++){
                Vector vss =  (Vector) v_tbl_data_docs.get(k);
                String idinv = vss.get(0).toString();
                String investigado = vss.get(1).toString();
                String cargo = vss.get(2).toString();

                btn_ver = "<div class='form-group dropdown text-center' >"
                                + "<button type='button' class='btn btn-info' id='ver' onclick='pad_mant_investigado_detalle(\""+idinv+"\");'>"
                                + "<span class='glyphicon glyphicon-search'>"
                                + "</span>"
                                + "</button>"
                                + "</div>";  
                
                Vector vv_d = new Vector();
                vv_d.add(idinv);
                vv_d.add(investigado);
                vv_d.add(cargo);
                vv_d.add(btn_ver);
                v_temp.add(vv_d);                
                }
            
        String tbl_investigado = util.tbl(v_tbl_cab, v_temp);              
        request.setAttribute("response",tbl_investigado);           
        
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "pad/mant_investigados_tbl";
	}
//FIN LISTA DOCUMENTOS POR EXPEDIENTE TABLA         
//
//INICIO DOCUMENTOS DETALLE    
    @RequestMapping(value = {"/pad/mant_investigado_detalle"}, method = RequestMethod.GET)
    public String MantInvestigadoDetalle(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String id_inv = request.getParameter("investigado"); 
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB();   
            Util util =  new Util();
                                   
            String np = "pad.fn_investigados_exp_consulta";
            String array[] = new String[1];
            array[0] = id_inv;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            var_request = new Util().vector2json(datos);
                   
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        return "pad/mant_investigado_detalle";
    }     
//FIN DOCUMENTOS DETALLE
//   
//INICIO NUEVO INVESTIGADO GUARDAR    
@RequestMapping(value = {"/pad/mant_investigado_modificar_guardar"}, method = RequestMethod.GET)
    public String MantInvestigadoModificaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {        

        String id_exp = request.getParameter("idexp");        
        String investigado = request.getParameter("investigado");        
        String cargo = request.getParameter("cargo");        
        String observacion = request.getParameter("observacion");        
        String faltas = request.getParameter("faltas");        
        
        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "pad.fn_investigado_modifica";
            String array[] = new String[5];
            array[0] = investigado;
            array[1] = cargo;            
            array[2] = id_exp;            
            array[3] = observacion;            
            array[4] = faltas;     
            
            //Modificacombo cargo y denunciado
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "pad/mant_investigado_modificar_guardar";
    }
//FIN NUEVO INVESTIGADO GUARDAR     
//
//INICIO ASIGNA ABOGADO POPUP            
    @RequestMapping(value = {"/pad/mant_asigna_abogado_popup"}, method = RequestMethod.GET)
    public String MantAsignaAbogadoConsultaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","ASIGNAR ABOGADO");   
        String array_expediente = request.getParameter("array_expediente");  
        int x = 0;   
        try {
            String exp = "";
            String nro_exp = "";
            String fec_rec = "";
            String etapa = "";
            String cad_id_exp = "";
            
            
            String[] array_exp = array_expediente.split(","); 
            
            String tbl_exp = "<table class='table table-striped'>" +
                                    "<tr class='success'>" +
                                    "  <td>ITEM</td>" +
                                    "  <td>N° EXPEDIENTE</td>" +
                                    "  <td>FECHA RECEP.(ORH)</td>" +
                                    "  <td>ETAPA</td>" +
                                    "</tr>";  
            String id_exp_array = "";
            for (x = 0; x < array_exp.length; x++){
                exp = array_exp[x];
                String[] array_elem = exp.split("_");
                nro_exp = array_elem [0];
                fec_rec = array_elem [1];                
                etapa = array_elem [2];
                
                tbl_exp += "<tr>" +
                                "  <td class='text-center'>"+x+"</td>" +
                                "  <td>"+nro_exp+"</td>" +
                                "  <td>"+fec_rec+"</td>" +
                                "  <td>"+etapa+ "</td>" +
                                "</tr>";  
                cad_id_exp += nro_exp + ",";
            }            
            cad_id_exp = cad_id_exp.replace(id_exp_array, "");
            cad_id_exp = id_exp_array + cad_id_exp;
            tbl_exp += "</table>";            
            request.setAttribute("tbl_exp", tbl_exp);
            request.setAttribute("cad_id_exp", cad_id_exp);            
            
            ConeccionDB cn = new ConeccionDB();   
            Util util =  new Util();
//          información para el combo Abogado
            String abogado = "pad.fn_abogado_consulta";
            String array_cbo_abogado[] = new String[1];
            array_cbo_abogado[0] = "";
            Vector datos_cbo_abogado = cn.EjecutarProcedurePostgres(abogado, array_cbo_abogado);
            String cb_abogado = util.contenido_combo(datos_cbo_abogado, "");
            request.setAttribute("abogado", cb_abogado);            
          
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "pad/mant_asigna_abogado_popup";
    }   
//FIN ASIGNA ABOGADO POPUP
//  
//INICIO ASIGNA ABOGADO GUARDAR    
@RequestMapping(value = {"/pad/mant_asigna_abogado_modificar_guardar"}, method = RequestMethod.GET)
    public String MantAsignaAbogadoModificaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {        

        String cadidexp = request.getParameter("cadidexp");        
        String abogado = request.getParameter("abogado");   
        String var_request = "";

        try {
            ConeccionDB cdb = new ConeccionDB();                
            String np = "pad.fn_asigna_abogado_modifica";
            String array[] = new String[2];
            array[0] = cadidexp;
            array[1] = abogado; 
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "pad/mant_asigna_abogado_modificar_guardar";
    }
//FIN ASIGNA ABOGADO GUARDAR     
// 
//INICIO LISTA DE EXPEDIENTE DEL PAD BASE        
    @RequestMapping(value = {"/pad/mant_buscar"}, method = RequestMethod.GET)
	public String MantBuscar(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","BUSCAR EXPEDIENTES");
        
            try {
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "pad.fn_anio_exp_consulta";
            String array_cbo_anio[] = new String[1];
            array_cbo_anio[0] = "";
            Vector datos_cbo_anio = cn.EjecutarProcedurePostgres(ne, array_cbo_anio);            
            String cb_anio = util.contenido_combo(datos_cbo_anio, "");
            request.setAttribute("anio", cb_anio);  
            
            String clsdoc = "sgd.fn_clasifdoc_seriedoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "90000048";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);
            
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }            
            return "pad/mant_buscar";
	}
//FIN LISTA DE EXPEDIENTE DEL PAD BASE
//
//INICIO BUSCAR TABLA        
    @RequestMapping(value = {"/pad/mant_buscar_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryBuscarTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            String nroexp = request.getParameter("nroexp");    
            String anio = request.getParameter("anio");    
            String fecini = request.getParameter("fecini");    
            String fecfin = request.getParameter("fecfin");    
            String fecinipad = request.getParameter("fecinipad");    
            String fecfinpad = request.getParameter("fecfinpad");    
            
            ConeccionDB cn =  new ConeccionDB();  
            String np = "pad.fn_buscar_consulta";
            String array[] = new String[6];
            array[0] = nroexp;
            array[1] = anio;
            array[2] = fecini;
            array[3] = fecfin;
            array[4] = fecinipad;
            array[5] = fecfinpad;
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String nro_exp = vss.get(0).toString();
                String fecharecep = vss.get(1).toString();
                String etapa = vss.get(2).toString();
                String estado = vss.get(4).toString();
                 
                String btn = "<button type='button' class='btn btn-info' onclick='pad_mant_expedientes_pad_consulta_popup(\\\""+nro_exp+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i);
                vv.add(nro_exp);
                vv.add(fecharecep);
                vv.add(etapa);
                vv.add(estado);
                vv.add(btn);
                v_temp.add(vv);
            }
            
            Util util = new Util();
            String json = util.vector2json(v_temp);   
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumns");sv.add("["                                    
                                    + "{'sTitle':'ITEM'} , "
                                    + "{'sTitle':'N° EXPEDIENTE'} , "
                                    + "{'sTitle':'FECHA RECEP. ORH'} , "
                                    + "{'sTitle':'ETAPA'} , "
                                    + "{'sTitle':'ESTADO'} , "
                                    + "{'sTitle':'-'}  "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
        //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm ' }]");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

            String tbl_html = "<table border='1' class='table table-striped table-bordered  text-center' id='c_tbl_medida_caut'></table>";
            String tbl = util.datatable("c_tbl_medida_caut",vc_tbl);            
            request.setAttribute("response", tbl_html + tbl);

            return "pad/mant_buscar_tbl";
	}
//FIN BUSCAR TABLA
// 
//INICIO LISTA DE EXPEDIENTE DEL PAD BASE        
    @RequestMapping(value = {"/pad/mant_reporte1"}, method = RequestMethod.GET)
	public String MantReporte1(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","REPORTE DE EXPEDIENTES POR");
        
            try {
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
            
//          información para el combo Etapa
            String etapa = "pad.fn_etapa_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(etapa, array_cbo);
            String cb_etapa = util.contenido_combo(datos_cbo, "");
            request.setAttribute("etapa", cb_etapa);
            
//          información para el combo Estado
            String estado = "pad.fn_estado_consulta";
            String array_estado[] = new String[1];
            array_estado[0] = "";
            Vector datos_estado = cn.EjecutarProcedurePostgres(estado, array_estado);
            String cb_estado = util.contenido_combo(datos_estado, "");
            request.setAttribute("estado", cb_estado);
            
//          información para el combo Abogado
            String abogado = "pad.fn_abogado_consulta";
            String array_cbo_abogado[] = new String[1];
            array_cbo_abogado[0] = "";
            Vector datos_cbo_abogado = cn.EjecutarProcedurePostgres(abogado, array_cbo_abogado);
            String cb_abogado = util.contenido_combo(datos_cbo_abogado, "");
            request.setAttribute("abogado", cb_abogado);
            
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }            
            return "pad/mant_reporte1";
	}
//FIN LISTA DE EXPEDIENTE DEL PAD BASE
//
//INICIO REPORTE POR TABLA        
    @RequestMapping(value = {"/pad/mant_rep1_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryRep1Tbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            String etapa = request.getParameter("etapa");    
            String estado = request.getParameter("estado");    
            String abogado = request.getParameter("abogado");   
            String fecini = request.getParameter("fecini");    
            String fecfin = request.getParameter("fecfin");    
            String fecinipad = request.getParameter("fecinipad");    
            String fecfinpad = request.getParameter("fecfinpad");    
            
            ConeccionDB cn =  new ConeccionDB();  
            String np = "pad.fn_rep1_consulta";
            String array[] = new String[7];
            array[0] = etapa;
            array[1] = estado;
            array[2] = abogado;
            array[3] = fecini;
            array[4] = fecfin;
            array[5] = fecinipad;
            array[6] = fecfinpad;
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String nro_exp = vss.get(0).toString();
                String fecharecep = vss.get(1).toString();
                String v_etapa = vss.get(2).toString();
                String v_estado = vss.get(4).toString();
                String v_abogado = vss.get(5).toString();
                 
                String btn = "<button type='button' class='btn btn-info' onclick='pad_mant_expedientes_pad_consulta_popup(\\\""+nro_exp+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i);
                vv.add(nro_exp);
                vv.add(fecharecep);
                vv.add(v_etapa);
                vv.add(v_estado);
                vv.add(v_abogado);
                vv.add(btn);
                v_temp.add(vv);
            }
            
            Util util = new Util();
            String json = util.vector2json(v_temp);   
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumns");sv.add("["                                    
                                    + "{'sTitle':'ITEM'} , "
                                    + "{'sTitle':'N° EXPEDIENTE'} , "
                                    + "{'sTitle':'FECHA RECEP. ORH'} , "
                                    + "{'sTitle':'ETAPA'} , "
                                    + "{'sTitle':'ESTADO'} , "
                                    + "{'sTitle':'ABOGADO'} , "
                                    + "{'sTitle':'-'}  "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
        //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

            String tbl_html = "<table border='1' class='table table-striped table-bordered  text-center' id='c_tbl_medida_caut'></table>";
            String tbl = util.datatable("c_tbl_medida_caut",vc_tbl);            
            request.setAttribute("response", tbl_html + tbl);

            return "pad/mant_rep1_tbl";
	}
//FIN REPORTE POR TABLA
// 
//INICIO LISTA DE EXPEDIENTE DEL PAD BASE        
    @RequestMapping(value = {"/pad/mant_reporte_graf1"}, method = RequestMethod.GET)
	public String MantReporteGraf1(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","REPORTE GRAFICO DE EXPEDIENTES POR ETAPA");
        
        try {
            Calendar c = Calendar.getInstance();//Anio actual para el registro del expediente
            
            Date date = new Date();//Fecha de registro del documento (por referencia)
            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy");
            String hoy = formatofec.format(date);
            
            request.setAttribute("hoy", hoy);
            
//Fecha de un mes antes
            Date nuevaFecha = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, -30);
            nuevaFecha = cal.getTime();
            String fec_mes = formatofec.format(nuevaFecha); 
            
            request.setAttribute("fec_mes", fec_mes);
            
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }            
            return "pad/mant_reporte_graf1";
	}
//FIN LISTA DE EXPEDIENTE DEL PAD BASE
//  
//INICIO REPORTE POR PERIODO GRÁFICO
@RequestMapping(value = {"/pad/mant_reporte_grafico1"}, method = RequestMethod.GET)
    public String MantReporteGrafico1(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
          
        String f_ini = request.getParameter("f_ini");   
        String f_fin = request.getParameter("f_fin");   
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB();
            String np = "pad.fn_rep_graf1_consulta";
            String array[] = new String[2];
            array[0] = f_ini;
            array[1] = f_fin;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);                        
            var_request = new Util().vector2json(datos); 
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        
        return "pad/mant_reporte_grafico1";
    }
//FIN REPORTE POR PERIODO GRÁFICO 
// 
//INICIO LISTA DE EXPEDIENTE DEL PAD BASE        
    @RequestMapping(value = {"/pad/mant_reporte_abogado_graf"}, method = RequestMethod.GET)
	public String MantReporteAbogadoGraf(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","REPORTE GRAFICO DE EXPEDIENTES POR ABOGADO");
        
        try {
            Calendar c = Calendar.getInstance();//Anio actual para el registro del expediente
            
            Date date = new Date();//Fecha de registro del documento (por referencia)
            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy");
            String hoy = formatofec.format(date);
            
            request.setAttribute("hoy", hoy);
            
//Fecha de un mes antes
            Date nuevaFecha = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, -30);
            nuevaFecha = cal.getTime();
            String fec_mes = formatofec.format(nuevaFecha); 
            
            request.setAttribute("fec_mes", fec_mes);
            
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }            
            return "pad/mant_reporte_abogado_graf";
	}
//FIN LISTA DE EXPEDIENTE DEL PAD BASE
// 
//INICIO REPORTE POR ABOGADO GRÁFICO
@RequestMapping(value = {"/pad/mant_reporte_abogado_grafico"}, method = RequestMethod.GET)
    public String MantReporteAbogadoGrafico(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
          
        String f_ini = request.getParameter("f_ini");   
        String f_fin = request.getParameter("f_fin");   
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB();
            String np = "pad.fn_rep_abogado_graf_consulta";
            String array[] = new String[2];
            array[0] = f_ini;
            array[1] = f_fin;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);                        
            var_request = new Util().vector2json(datos); 
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        
        return "pad/mant_reporte_abogado_grafico";
    }
//FIN REPORTE POR ABOGADO GRÁFICO 
//      
//INICIO LISTA MEDIDA CAUTELAR BASE        
    @RequestMapping(value = {"/pad/mant_medida_caut"}, method = RequestMethod.GET)
	public String MantMedidaCaut(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","GESTIÓN DE MEDIDAS CAUTELARES");             
            request.setAttribute("btn_nuevo_reg","pad_mant_medida_caut_popup()");
            request.setAttribute("tit_btn","NUEVO REGISTRO");
            return "pad/mant_medida_caut";
	}
//FIN LISTA MEDIDA CAUTELAR BASE
//
//INICIO LISTA MEDIDA CAUTELAR TABLA        
    @RequestMapping(value = {"/pad/mant_medida_caut_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryMedidaCautTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "pad.fn_medida_caut_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_medida_caut = vss.get(0).toString();
                String c_des_medida_caut = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                 
                String btn = "<button type='button' class='btn btn-info' onclick='pad_mant_medida_caut_popup(\\\""+i_id_medida_caut+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id_medida_caut);
                vv.add(c_des_medida_caut);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }     
            
            Util util = new Util();
            String json = util.vector2json(v_temp);   
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumns");sv.add("["                                    
                                    + "{'sTitle':'CÓDIGO'} , "
                                    + "{'sTitle':'MEDIDA CAUTELAR'} , "
                                    + "{'sTitle':'ESTADO'} , "
                                    + "{'sTitle':'-'}  "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
        //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

            String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_medida_caut'></table>";
            String tbl = util.datatable("c_tbl_medida_caut",vc_tbl);            
            request.setAttribute("response", tbl_html + tbl);

            return "pad/mant_medida_caut_tbl";
	}
//FIN LISTA MEDIDA CAUTELAR TABLA
//    
//INICIO MEDIDA CAUTELAR POPUP            
    @RequestMapping(value = {"/pad/mant_medida_caut_popup"}, method = RequestMethod.GET)
    public String MantMedidaCautPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","GESTIÓN DE MEDIDA CAUTELAR");
        String id = request.getParameter("id");
           
        try {            
            ConeccionDB cn = new ConeccionDB(); 
            
            String np = "pad.fn_medida_caut_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            
            String i_id_med = "";
            String c_des_med = "";
            String c_est_reg = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){
                Vector datos_v =  (Vector) datos.get(i);
                i_id_med = datos_v.get(0).toString();
                c_des_med = datos_v.get(1).toString();
                c_est_reg = datos_v.get(3).toString();
            }            
            request.setAttribute("id", i_id_med);  
            request.setAttribute("descripcion", c_des_med);  
            
//          información para el combo Estado
            String nt = "sgd.fn_estado_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
            String cb_desc_estado = util.contenido_combo(datos_cbo, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);
            
          
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "pad/mant_medida_caut_popup";
    }   
//FIN MEDIDA CAUTELAR POPUP
// 
//INICIO ASIGNA ABOGADO GUARDAR    
@RequestMapping(value = {"/pad/mant_medida_caut_guardar"}, method = RequestMethod.GET)
    public String MantMedidaCautGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {        

        String id = request.getParameter("id");        
        String descripcion = request.getParameter("descripcion");   
        String estado = request.getParameter("estado");   
        String var_request = "";

        try {
            ConeccionDB cdb = new ConeccionDB();                
            String np = "pad.fn_medida_caut_mant";
            String array[] = new String[3];
            array[0] = id;
            array[1] = descripcion; 
            array[2] = estado; 
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "pad/mant_medida_caut_guardar";
    }
//FIN ASIGNA ABOGADO GUARDAR     
//      
//INICIO LISTA FALTAS BASE        
    @RequestMapping(value = {"/pad/mant_falta"}, method = RequestMethod.GET)
	public String MantFalta(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","GESTIÓN DE FALTAS");             
            request.setAttribute("btn_nuevo_reg","pad_mant_falta_popup()");
            request.setAttribute("tit_btn","NUEVO REGISTRO");
            return "pad/mant_falta";
	}
//FIN LISTA FALTAS BASE
//         
//INICIO LISTA FALTAS TABLA        
    @RequestMapping(value = {"/pad/mant_falta_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryMedidaFaltaTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "pad.fn_falta_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_medida_caut = vss.get(0).toString();
                String c_des_medida_caut = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                 
                String btn = "<button type='button' class='btn btn-info' onclick='pad_mant_falta_popup(\\\""+i_id_medida_caut+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id_medida_caut);
                vv.add(c_des_medida_caut);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }     
            
            Util util = new Util();
            String json = util.vector2json(v_temp);   
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumns");sv.add("["                                    
                                    + "{'sTitle':'CÓDIGO'} , "
                                    + "{'sTitle':'FALTA'} , "
                                    + "{'sTitle':'ESTADO'} , "
                                    + "{'sTitle':'-'}  "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
        //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

            String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_falta'></table>";
            String tbl = util.datatable("c_tbl_falta",vc_tbl);            
            request.setAttribute("response", tbl_html + tbl);

            return "pad/mant_falta_tbl";
	}
//FIN LISTA FALTAS TABLA
//    
//INICIO FALTAS POPUP            
    @RequestMapping(value = {"/pad/mant_falta_popup"}, method = RequestMethod.GET)
    public String MantFaltaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","GESTIÓN DE FALTAS");
        String id = request.getParameter("id");
           
        try {            
            ConeccionDB cn = new ConeccionDB(); 
            
            String np = "pad.fn_falta_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            
            String i_id_falta = "";
            String c_des_falta = "";
            String c_est_reg = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){
                Vector datos_v =  (Vector) datos.get(i);
                i_id_falta = datos_v.get(0).toString();
                c_des_falta = datos_v.get(1).toString();
                c_est_reg = datos_v.get(3).toString();
            }            
            request.setAttribute("id", i_id_falta);  
            request.setAttribute("descripcion", c_des_falta);  
            
            String cb_desc_estado = "";
//          información para el combo Estado
            
                String nt = "sgd.fn_estado_consulta";
                String array_cbo[] = new String[1];
                array_cbo[0] = "";
                Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
                if (i_id_falta.length() != 0){
                    cb_desc_estado = util.contenido_combo(datos_cbo, c_est_reg);
                }else{
                    cb_desc_estado = util.contenido_combo(datos_cbo, "1");    
                }  
                request.setAttribute("cb_estado", cb_desc_estado);                               
          
        } catch (Exception ex) {
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "pad/mant_falta_popup";
    }   
//FIN FALTAS POPUP
// 
//INICIO FALTA GUARDAR    
@RequestMapping(value = {"/pad/mant_falta_guardar"}, method = RequestMethod.GET)
    public String MantFaltaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {        

        String id = request.getParameter("id");        
        String descripcion = request.getParameter("descripcion");   
        String estado = request.getParameter("estado");   
        String var_request = "";

        try {
            ConeccionDB cdb = new ConeccionDB();                
            String np = "pad.fn_falta_mant";
            String array[] = new String[3];
            array[0] = id;
            array[1] = descripcion; 
            array[2] = estado; 
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(PadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "pad/mant_falta_guardar";
    }
//FIN FALTA GUARDAR     
         
//              
}

