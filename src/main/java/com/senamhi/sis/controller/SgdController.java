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
 * @author Kori
**/
@Controller
@RequestMapping("/")
public class SgdController {

    @RequestMapping(value = {"/sgd/"}, method = RequestMethod.GET)
    public String Index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        return "sgd/index";
    }

//  ASIGNACIÓN DE BALDAS, CUERPOS, ESTANTES Y ARCHIVOS ----------------------------------------------------------------------------------------------------------------
        
    @RequestMapping(value = {"/sgd/mant_asigna_balda"}, method = RequestMethod.GET)
	public String MantAsignaBalda(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","GESTIÓN DE ASIGNACIÓN DE BALDAS");             
            request.setAttribute("btn_nuevo_reg","sgd_mant_asigna_balda_popup()");
            request.setAttribute("tit_btn","ASIGNAR");
            return "sgd/mant_asigna_balda";
	}
        
    
//INICIO MANTENIMIENTO TRAMITE         
    @RequestMapping(value = {"/sgd/mant_tramite"}, method = RequestMethod.GET)
	public String MantTramite(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
            request.setAttribute("title_pag","GESTIÓN DE TRAMITE");             
            request.setAttribute("btn_nuevo_reg","sgd_mant_tramite_popup()");
            request.setAttribute("tit_btn","NUEVO REGISTRO");
            return "sgd/mant_tramite";
	}
//FIN MANTENIMIENTO TRAMITE          
//
//INICIO MANTENIMIENTO TRÁMITE TABLA        
    @RequestMapping(value = {"/sgd/mant_tramite_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryTramite(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_tramite_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_trami = vss.get(0).toString();
                String c_des_trami = vss.get(1).toString();
                String i_plazo_trami = vss.get(2).toString();
                String c_est_reg = vss.get(3).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_tramite_popup(\\\""+i_id_trami+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id_trami);
                vv.add(c_des_trami);
                vv.add(i_plazo_trami);
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
                                    + "{'sTitle':'TRAMITE'} , "
                                    + "{'sTitle':'PLAZO (DIAS)'} , "
                                    + "{'sTitle':'ESTADO'} , "
                                    + "{'sTitle':'-'}  "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
        //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
            ///////////////////////////////////////////////////////

            String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_buscar_expediente'></table>";
            String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
            request.setAttribute("response", tbl_html + tbl);

            return "sgd/mant_tramite_tbl";
	}
//FIN MANTENIMIENTO TRÁMITE TABLA    
//
//INICIO MANTENIMIENTO TRÁMITE POPUP            
    @RequestMapping(value = {"/sgd/mant_tramite_popup"}, method = RequestMethod.GET)
    public String MantTramitePopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","REGISTRO DE TRAMITE");     

        try {
            String id = request.getParameter("id");
            
            ConeccionDB cn = new ConeccionDB(); 
            
            String np = "sgd.fn_tramite_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id_trami = "";
            String c_des_trami = "";
            String i_plazo_trami = "";
            String c_est_reg = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){                
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id_trami = datos_v.get(0).toString();
                c_des_trami = datos_v.get(1).toString();
                i_plazo_trami = datos_v.get(2).toString();
                c_est_reg = datos_v.get(3).toString();
            }            
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id_trami);
            request.setAttribute("des", c_des_trami);
            request.setAttribute("plazo", i_plazo_trami);
            
//          información para el combo Estado
            String nt = "sgd.fn_estado_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
            String cb_desc_estado = util.contenido_combo(datos_cbo, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);
          
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_tramite_popup";
    }   
//FIN MANTENIMIENTO TRÁMITE POPUP            
//
//INICIO MANTENIMIENTO TRÁMITE GUARDAR    
@RequestMapping(value = {"/sgd/mant_tramite_guardar"}, method = RequestMethod.GET)
    public String MantTramiteGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String plazo = request.getParameter("plazo");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_tramite_mant";
            String array[] = new String[4];
            array[0] = id;
            array[1] = des;
            array[2] = plazo;
            array[3] = estado;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_tramite_guardar";
    }
//FIN MANTENIMIENTO TRÁMITE GUARDAR     
//        
//INICIO PROCEDIMIENTO MANTENIMIENTO        
    @RequestMapping(value = {"/sgd/mant_procedimiento"}, method = RequestMethod.GET)
    public String MantProcedimiento(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        request.setAttribute("title_pag","GESTIÓN DE PROCEDIMIENTO");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_procedimiento_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_procedimiento";
    }
//FIN PROCEDIMIENTO MANTENIMIENTO         
//
//INICIO PROCEDIMIENTO MANTENIMIENTO TABLA 
    @RequestMapping(value = {"/sgd/mant_procedimiento_tbl"}, method = RequestMethod.GET)
    public String MantProcedimientoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        ConeccionDB cn =  new ConeccionDB();            

        String np = "sgd.fn_procedimiento_consulta";
        String array[] = new String[1];
        array[0] = "";
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector vss =  (Vector) v_datos.get(i);
            String i_id_proc = vss.get(0).toString();
            String c_des_proc = vss.get(1).toString();
            String c_des_trami = vss.get(3).toString();
            String c_est_reg = vss.get(4).toString();
            if ("1".equals(c_est_reg)) {
                c_est_reg = "Activo";
            } else {
                c_est_reg = "Inactivo";
            }    
            String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_procedimiento_popup(\\\""+i_id_proc+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";

            Vector vv = new Vector();
            vv.add(i_id_proc);
            vv.add(c_des_proc);
            vv.add(c_des_trami);
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
                                + "{'sTitle':'PROCEDIMIENTO'} , "
                                + "{'sTitle':'TRAMITE'} , "
                                + "{'sTitle':'ESTADO'} , "
                                + "{'sTitle':'-'}  "
                                + "]");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
    //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
        //boton de excel
        sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
        ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
        ///////////////////////////////////////////////////////

        String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_buscar_expediente'></table>";
        String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
        request.setAttribute("response", tbl_html + tbl);
        
        return "sgd/mant_procedimiento_tbl";
    }        
//FIN PROCEDIMIENTO MANTENIMIENTO TABLA
//
//INICIO PROCEDIMIENTO MANTENIMIENTO POPUP    
    @RequestMapping(value = {"/sgd/mant_procedimiento_popup"}, method = RequestMethod.GET)
    public String MantProcedimientoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","REGISTRO DE PROCEDIMIENTO"); 
        try {
            String id = request.getParameter("id");          
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_procedimiento_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id_proc = "";
            String c_des_proc = "";
            String i_id_trami = "";
            String c_est_reg = "";
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id_proc = datos_v.get(0).toString();
                c_des_proc = datos_v.get(1).toString();
                i_id_trami = datos_v.get(2).toString();
                c_est_reg = datos_v.get(4).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id_proc);
            request.setAttribute("des", c_des_proc);
            request.setAttribute("idt", i_id_trami);
            
//            información para el combo trámite
            String nt = "sgd.fn_tramite_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
            String cb_desc_trami = util.contenido_combo(datos_cbo, i_id_trami);
            request.setAttribute("cb_desc_trami", cb_desc_trami);
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);            
   
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_procedimiento_popup";
    }   
//FIN PROCEDIMIENTO MANTENIMIENTO POPUP
//
//INICIO PROCEDIMIENTO MANTENIMIENTO GUARDAR   
    @RequestMapping(value = {"/sgd/mant_procedimiento_guardar"}, method = RequestMethod.GET)
    public String MantProcedimientoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String id_trami = request.getParameter("id_trami");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {                
        ConeccionDB cdb = new ConeccionDB(); 
        String np = "sgd.fn_procedimiento_mant";
        String array[] = new String[4];
        array[0] = id;
        array[1] = des;
        array[2] = id_trami;
        array[3] = estado;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_procedimiento_guardar";
    }
//FIN PROCEDIMIENTO MANTENIMIENTO GUARDAR     
//
//INICIO CARCAR COMBO PROCEDIMIENTO
    @RequestMapping(value = {"/sgd/mant_procedimiento_cargar_cbo"}, method = RequestMethod.GET)
    public String MantProcedimientoCargarCbo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
//            request.setAttribute("title_pag","NUEVO PROCEDIMIENTO"); 
        String var_request = "";
        try {
            String id_tramite = request.getParameter("id_tramite"); 
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_tramite_proced_consulta";
            String array[] = new String[1];
            array[0] = id_tramite;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            var_request = new Util().contenido_combo(datos,"1");
            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("response", var_request);
        
        return "sgd/mant_procedimiento_cargar_cbo";
    } 
//FIN CARCAR COMBO PROCEDIMIENTO    
    
////    ORIGEN ----------------------------------------------------------------------------------------------------------------
        
    @RequestMapping(value = {"/sgd/mant_origen"}, method = RequestMethod.GET)
    public String MantOrigen(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE ORIGEN");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_origen_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_origen";
    }  
        
    @RequestMapping(value = {"/sgd/mant_origen_tbl"}, method = RequestMethod.GET)
	public String MantOrigenTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_origen_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_origen = vss.get(0).toString();
                String c_des_origen = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_origen_popup('"+i_id_origen+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id_origen);
                vv.add(c_des_origen);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. ORIGEN");
            v_tbl_cab.add("DES. ORIGEN");
            v_tbl_cab.add("ESTADO");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_origen_tbl";
	}
        
    @RequestMapping(value = {"/sgd/mant_origen_popup"}, method = RequestMethod.GET)
    public String MantOrigenPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVO ORIGEN"); 
        try {
            String id = request.getParameter("id");          
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_origen_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id_origen = "";
            String c_des_origen = "";
            String c_est_reg = "";
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id_origen = datos_v.get(0).toString();
                c_des_origen = datos_v.get(1).toString();
                c_est_reg = datos_v.get(2).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id_origen);
            request.setAttribute("des", c_des_origen);
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);                    
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_origen_popup";
    }       
    
@RequestMapping(value = {"/sgd/mant_origen_guardar"}, method = RequestMethod.GET)
    public String MantOrigenGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {        
        ConeccionDB cdb = new ConeccionDB(); 
        String np = "sgd.fn_origen_mant";
        String array[] = new String[3];
        array[0] = id;
        array[1] = des;
        array[2] = estado;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_origen_guardar";
    }


////    TEMA ----------------------------------------------------------------------------------------------------------------
        
    @RequestMapping(value = {"/sgd/mant_tema"}, method = RequestMethod.GET)
    public String MantTema(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE TEMA");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_tema_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_tema";
    }  

    @RequestMapping(value = {"/sgd/mant_tema_tbl"}, method = RequestMethod.GET)
	public String MantTemaTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_tema_consulta";
            String array[] = new String[2];
            array[0] = "";
            array[1] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_tema = vss.get(0).toString();
                String c_des_tema = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_tema_popup('"+i_id_tema+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id_tema);
                vv.add(c_des_tema);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. TEMA");
            v_tbl_cab.add("DES. TEMA");
            v_tbl_cab.add("ESTADO");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_tema_tbl";
	}
    
    @RequestMapping(value = {"/sgd/mant_tema_popup"}, method = RequestMethod.GET)
    public String MantTemaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVO TEMA"); 
        try {
            String id = request.getParameter("id");          
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_tema_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id_tema = "";
            String c_des_tema = "";
            String c_est_reg = "";
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id_tema = datos_v.get(0).toString();
                c_des_tema = datos_v.get(1).toString();
                c_est_reg = datos_v.get(2).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id_tema);
            request.setAttribute("des", c_des_tema);
//            request.setAttribute("estado", c_est_reg);
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);                    
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_tema_popup";
    }        
        
    @RequestMapping(value = {"/sgd/mant_tema_guardar"}, method = RequestMethod.GET)
    public String MantTemaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {                
        ConeccionDB cdb = new ConeccionDB(); 
        String np = "sgd.fn_tema_mant";
        String array[] = new String[3];
        array[0] = id;
        array[1] = des;
        array[2] = estado;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_tema_guardar";
    }
    
    
////    ALCANCE ----------------------------------------------------------------------------------------------------------------
        
    @RequestMapping(value = {"/sgd/mant_alcance"}, method = RequestMethod.GET)
    public String MantAlcance(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE ALCANCE");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_alcance_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_alcance";
    }  
    
    @RequestMapping(value = {"/sgd/mant_alcance_tbl"}, method = RequestMethod.GET)
	public String MantAlcanceTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_alcance_consulta";
            String array[] = new String[2];
            array[0] = "";
            array[1] = "";            
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_alcan = vss.get(0).toString();
                String c_des_alcan = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_alcance_popup('"+i_id_alcan+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id_alcan);
                vv.add(c_des_alcan);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. ALCANCE");
            v_tbl_cab.add("DES. ALCANCE");
            v_tbl_cab.add("ESTADO");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_alcance_tbl";
	}
        
    @RequestMapping(value = {"/sgd/mant_alcance_popup"}, method = RequestMethod.GET)
    public String MantAlcancePopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVO ALCANCE"); 
        try {
            String id = request.getParameter("id");          
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_alcance_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id_alcan = "";
            String c_des_alcan = "";
            String c_est_reg = "";
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id_alcan = datos_v.get(0).toString();
                c_des_alcan = datos_v.get(1).toString();
                c_est_reg = datos_v.get(2).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id_alcan);
            request.setAttribute("des", c_des_alcan);
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);   
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_alcance_popup";
    }          
        
    @RequestMapping(value = {"/sgd/mant_alcance_guardar"}, method = RequestMethod.GET)
    public String MantAlcanceGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {
        ConeccionDB cdb = new ConeccionDB(); 
        String np = "sgd.fn_alcance_mant";
        String array[] = new String[3];
        array[0] = id;
        array[1] = des;
        array[2] = estado;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
        
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_alcance_guardar";
    }

////    CONDICION ----------------------------------------------------------------------------------------------------------------
        
    @RequestMapping(value = {"/sgd/mant_condicion"}, method = RequestMethod.GET)
    public String MantCondicion(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE CONDICION");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_condicion_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_condicion";
    }  

    @RequestMapping(value = {"/sgd/mant_condicion_tbl"}, method = RequestMethod.GET)
	public String MantCondicionTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_condicion_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_cond = vss.get(0).toString();
                String c_des_cond = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_condicion_popup('"+i_id_cond+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id_cond);
                vv.add(c_des_cond);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. CONDICION");
            v_tbl_cab.add("DES. CONDICION");
            v_tbl_cab.add("ESTADO");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_condicion_tbl";
	}

    @RequestMapping(value = {"/sgd/mant_condicion_popup"}, method = RequestMethod.GET)
    public String MantCondicionPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVA CONDICIÓN"); 
        try {
            String id = request.getParameter("id");          
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_condicion_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id_cond = "";
            String c_des_cond = "";
            String c_est_reg = "";
            
            for(int i = 0 ; i<datos.size() ; i++){                
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id_cond = datos_v.get(0).toString();
                c_des_cond = datos_v.get(1).toString();
                c_est_reg = datos_v.get(2).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id_cond);
            request.setAttribute("des", c_des_cond);
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);   
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_condicion_popup";
    }          

    @RequestMapping(value = {"/sgd/mant_condicion_guardar"}, method = RequestMethod.GET)
    public String MantCondicionGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {
        ConeccionDB cdb = new ConeccionDB(); 
        String np = "sgd.fn_condicion_mant";
        String array[] = new String[3];
        array[0] = id;
        array[1] = des;
        array[2] = estado;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
        
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_condicion_guardar";
    }
    

////    TIPO DE FLUJO ----------------------------------------------------------------------------------------------------------------
        
    
    @RequestMapping(value = {"/sgd/mant_tipflujo"}, method = RequestMethod.GET)
    public String MantTipflujo(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE TIPO DE FLUJO");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_tipflujo_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_tipflujo";
    }  
    
    @RequestMapping(value = {"/sgd/mant_tipflujo_tbl"}, method = RequestMethod.GET)
	public String MantTipflujoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_tipflujo_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id = vss.get(0).toString();
                String c_des = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_tipflujo_popup('"+i_id+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id);
                vv.add(c_des);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. TIPO DE FLUJO");
            v_tbl_cab.add("DES. TIPO DE FLUJO");
            v_tbl_cab.add("ESTADO");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_tipflujo_tbl";
	}

    @RequestMapping(value = {"/sgd/mant_tipflujo_popup"}, method = RequestMethod.GET)
    public String MantTipflujoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVO TIPO DE FLUJO"); 
        try {
            String id = request.getParameter("id");          
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_tipflujo_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_des = "";
            String c_est_reg = "";
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_des = datos_v.get(1).toString();
                c_est_reg = datos_v.get(2).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("des", c_des);
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);               
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_tipflujo_popup";    
    }    
        
    @RequestMapping(value = {"/sgd/mant_tipflujo_guardar"}, method = RequestMethod.GET)
    public String MantTipflujoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {
        ConeccionDB cdb = new ConeccionDB(); 
        String np = "sgd.fn_tipflujo_mant";
        String array[] = new String[3];
        array[0] = id;
        array[1] = des;
        array[2] = estado;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
        
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_tipflujo_guardar";
    }    
        

////    ACCION ----------------------------------------------------------------------------------------------------------------       
    
    @RequestMapping(value = {"/sgd/mant_accion"}, method = RequestMethod.GET)
    public String MantAccion(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE ACCIÓN");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_accion_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_accion";
    }  
        
    @RequestMapping(value = {"/sgd/mant_accion_tbl"}, method = RequestMethod.GET)
	public String MantAccionTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_accion_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id = vss.get(0).toString();
                String c_des = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_accion_popup('"+i_id+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id);
                vv.add(c_des);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. ACCION");
            v_tbl_cab.add("DES. ACCION");
            v_tbl_cab.add("ESTADO");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_accion_tbl";
	}
        
    @RequestMapping(value = {"/sgd/mant_accion_popup"}, method = RequestMethod.GET)
    public String MantAccionPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVA ACCION"); 
        try {
            String id = request.getParameter("id");          
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_accion_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_des = "";
            String c_est_reg = "";
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_des = datos_v.get(1).toString();
                c_est_reg = datos_v.get(2).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("des", c_des);
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);            
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_accion_popup";    
    }        
        
    @RequestMapping(value = {"/sgd/mant_accion_guardar"}, method = RequestMethod.GET)
    public String MantAccionGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {
        ConeccionDB cdb = new ConeccionDB(); 
        String np = "sgd.fn_accion_mant";
        String array[] = new String[3];
        array[0] = id;
        array[1] = des;
        array[2] = estado;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
        
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_accion_guardar";
    }        
        
        
////    TIPO DE ESTADO DEL FLUJO ----------------------------------------------------------------------------------------------------------------
            
    @RequestMapping(value = {"/sgd/mant_tipestadoflujo"}, method = RequestMethod.GET)
    public String MantTipestadoflujo(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE TIPO DE ESTADO DEL FLUJO");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_tipestadoflujo_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_tipestadoflujo";        
    }   
    
    @RequestMapping(value = {"/sgd/mant_tipestadoflujo_tbl"}, method = RequestMethod.GET)
	public String MantTipestadoflujoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_tipestadoflujo_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id = vss.get(0).toString();
                String c_des = vss.get(1).toString();
                String c_est_reg = vss.get(2).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_tipestadoflujo_popup('"+i_id+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id);
                vv.add(c_des);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. TIP.ESTADO DE FLUJO");
            v_tbl_cab.add("DES. TIP.ESTADO DE FLUJO");
            v_tbl_cab.add("ESTADO");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_tipestadoflujo_tbl";
	}
    
    @RequestMapping(value = {"/sgd/mant_tipestadoflujo_popup"}, method = RequestMethod.GET)
    public String MantTipestadoflujoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVO TIPO DE ESTADO DE FLUJO"); 
        try {
            String id = request.getParameter("id");          
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_tipestadoflujo_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_des = "";
            String c_est_reg = "";
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_des = datos_v.get(1).toString();
                c_est_reg = datos_v.get(2).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("des", c_des);
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);                
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_tipestadoflujo_popup";    
    }        
    
    @RequestMapping(value = {"/sgd/mant_tipestadoflujo_guardar"}, method = RequestMethod.GET)
    public String MantTipestadoflujoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {
        ConeccionDB cdb = new ConeccionDB(); 
        String np = "sgd.fn_tipestadoflujo_mant";
        String array[] = new String[3];
        array[0] = id;
        array[1] = des;
        array[2] = estado;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
        
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_tipestadoflujo_guardar";
    }        
    
    
////    PRIORIDAD ----------------------------------------------------------------------------------------------------------------
            
    @RequestMapping(value = {"/sgd/mant_prioridad"}, method = RequestMethod.GET)
    public String MantPrioridad(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE PRIORIDAD");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_prioridad_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_prioridad";        
    }   
        
   @RequestMapping(value = {"/sgd/mant_prioridad_tbl"}, method = RequestMethod.GET)
	public String MantPrioridadTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "sgd.fn_prioridad_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id = vss.get(0).toString();
                String c_des = vss.get(1).toString();
                String i_plazo = vss.get(2).toString();
                String c_est_reg = vss.get(3).toString();
                if ("1".equals(c_est_reg)) {
                    c_est_reg = "Activo";
                } else {
                    c_est_reg = "Inactivo";
                }       
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_prioridad_popup('"+i_id+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id);
                vv.add(c_des);
                vv.add(i_plazo);
                vv.add(c_est_reg);
                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("COD. PRIORIDAD");
            v_tbl_cab.add("DES. PRIORIDAD");
            v_tbl_cab.add("PLAZO (DIAS)");
            v_tbl_cab.add("ESTADO");
            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_prioridad_tbl";
	}
    
    @RequestMapping(value = {"/sgd/mant_prioridad_popup"}, method = RequestMethod.GET)
    public String MantPrioridadPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVA PRIORIDAD"); 
        try {
            String id = request.getParameter("id");
            
            ConeccionDB cn = new ConeccionDB(); 
            
            String np = "sgd.fn_prioridad_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_des = "";
            String i_plazo = "";
            String c_est_reg = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_des = datos_v.get(1).toString();
                i_plazo = datos_v.get(2).toString();
                c_est_reg = datos_v.get(3).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("des", c_des);
            request.setAttribute("plazo", i_plazo);

//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);     
             
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_prioridad_popup";
    }  
        
    @RequestMapping(value = {"/sgd/mant_prioridad_guardar"}, method = RequestMethod.GET)
    public String MantPrioridadGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String des = request.getParameter("des");
        String plazo = request.getParameter("plazo");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_prioridad_mant";
            String array[] = new String[4];
            array[0] = id;
            array[1] = des;
            array[2] = plazo;
            array[3] = estado;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_prioridad_guardar";
    }
      
    
//    ESTANTE ----------------------------------------------------------------------------------------------------------------
            
    @RequestMapping(value = {"/sgd/mant_estante"}, method = RequestMethod.GET)
    public String MantEstante(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE ESTANTES");
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        
        ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
            String ne = "sgd.fn_almacen_uo_consulta";
            String array_almacen[] = new String[1];
            array_almacen[0] = codUser;
            Vector datos_cbo_alm = cn.EjecutarProcedurePostgres(ne, array_almacen);
            String cb_almacen = util.contenido_combo(datos_cbo_alm, "");
            request.setAttribute("cb_almacen", cb_almacen);  
            
            String cod_alm = "";
            for(int i = 0 ; i<datos_cbo_alm.size() ; i++){
                Vector vss =  (Vector) datos_cbo_alm.get(i);
                cod_alm = vss.get(0).toString();
            }
            request.setAttribute("hd_alma", cod_alm);    
        
        return "sgd/mant_estante";        
    }   
             
    @RequestMapping(value = {"/sgd/mant_estante_tbl"}, method = RequestMethod.GET)
    public String MantEstanteTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        String cod_almacen = request.getParameter("cod_almacen");
        ConeccionDB cn =  new ConeccionDB();            

        String np = "sgd.fn_almacen_estante_consulta";
        String array[] = new String[1];
        array[0] = cod_almacen;
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector vss =  (Vector) v_datos.get(i);
            String i_id = vss.get(0).toString();
            String c_des = vss.get(1).toString();
            String c_mat = vss.get(2).toString();
            String c_dim = vss.get(3).toString();
            String c_est_reg = vss.get(4).toString();
            String cuerpos = vss.get(5).toString();
            if ("1".equals(c_est_reg)) {
                c_est_reg = "Activo";
            } else {
                c_est_reg = "Inactivo";
            }       
            String btn = "<div class='text-center'>"
                       + "<button type='button' class='btn btn-info' onclick='sgd_mant_estante_popup(\\\""+i_id+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>"
                       + "</div>";

            Vector vv = new Vector();
            vv.add(i_id);
            vv.add(c_des);
            vv.add(c_mat);
            vv.add(c_dim);
            vv.add(cuerpos);
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
                                + "{'sTitle':'ESTANTE'} , "
                                + "{'sTitle':'MATERIAL'} , "
                                + "{'sTitle':'DIMENSIONES'} , "
                                + "{'sTitle':'N° CUERPOS'} , "
                                + "{'sTitle':'ESTADO'} , "
                                + "{'sTitle':'-'}  "
                                + "]");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
    //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
        //boton de excel
        sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
        ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

        String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_buscar_expediente'></table>";
        String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
        request.setAttribute("response", tbl_html + tbl);

        return "sgd/mant_estante_tbl";
    }  
    
    @RequestMapping(value = {"/sgd/mant_estante_popup"}, method = RequestMethod.GET)
    public String MantEstantePopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        request.setAttribute("title_pag","GESTIÓN DE ESTANTES"); 
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        try {
            String id = request.getParameter("id");
            String almacen = request.getParameter("almacen");       
            
            ConeccionDB cn = new ConeccionDB();             
            String np = "sgd.fn_estante_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            String obj_habilita_form = "";
            
            String i_id = "";
            String c_cod = "";
            String c_des = "";
            String c_mat = "";
            String i_dim = "";
            String c_est_reg = "";            
//            String c_almacen = "";            
            String c_cpos = "";            
            String c_bld = "";            
            String c_lado = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                obj_habilita_form = "disabled";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_cod = datos_v.get(1).toString();
                c_des = datos_v.get(2).toString();
                c_mat = datos_v.get(3).toString();
                i_dim = datos_v.get(4).toString();
                c_est_reg = datos_v.get(5).toString();             
//                c_almacen = datos_v.get(6).toString();             
                c_cpos = datos_v.get(7).toString();             
                c_bld = datos_v.get(8).toString();             
                c_lado = datos_v.get(9).toString();             
            }           
            
            request.setAttribute("obj_active_form", obj_active_form);
            request.setAttribute("obj_habilita_form", obj_habilita_form);            
            request.setAttribute("id", i_id);
            request.setAttribute("cod", c_cod);
            request.setAttribute("des", c_des);
            request.setAttribute("material", c_mat);
            request.setAttribute("dimension", i_dim);
            request.setAttribute("c_cpos", c_cpos);
            request.setAttribute("c_bld", c_bld);
            request.setAttribute("almacen", almacen);
            request.setAttribute("c_lado", c_lado);            
           
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);                
            
            if (!id.equals("undefined")){ 
                String codest = "sgd.fn_codestt_consulta";
                String array_cbo_codest[] = new String[2];
                array_cbo_codest[0] = "";
                array_cbo_codest[1] = "";
                Vector datos_cbo_codest = cn.EjecutarProcedurePostgres(codest, array_cbo_codest);
                String cb_desc_est = util.contenido_combo(datos_cbo_codest, c_cod);
                request.setAttribute("cb_codest", cb_desc_est); 
            }else{
                String codest = "sgd.fn_codestt_consulta";
                String array_cbo_codest[] = new String[2];
                array_cbo_codest[0] = "";
                array_cbo_codest[1] = almacen;
                Vector datos_cbo_codest = cn.EjecutarProcedurePostgres(codest, array_cbo_codest);
                String cb_desc_est = util.contenido_combo(datos_cbo_codest, c_cod);
                request.setAttribute("cb_codest", cb_desc_est); 
            }
//          llenar combo lado
            String lado = "1,2";  
            String cb_lado = "";  
            String[] array_lado = lado.split(",");  
            String cod_lado = "";
             for (int i = 0; i<array_lado.length; i++){
                cod_lado = array_lado[i]; 
                
                cb_lado += "<option value='"+cod_lado+"' >"+cod_lado+"</option>";
            }                   
            request.setAttribute("cb_lado", cb_lado); 
                        
//            Llenar combo cuerpo
            String cb_cpo ="";
            for(int i = 1 ; i<20 ; i++){                    
                cb_cpo += "<option value='"+i+"'>"+i+"</option>";
            }   
            request.setAttribute("cb_cpo", cb_cpo); 
            
//            Llenar combo balda            
            String cb_balda ="";
            for(int i = 1 ; i<100 ; i++)
            {                    
                cb_balda += "<option value='"+i+"'>"+i+"</option>";
            }   
            request.setAttribute("cb_balda", cb_balda);
             
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_estante_popup";
    }  
    
   @RequestMapping(value = {"/sgd/mant_estante_guardar"}, method = RequestMethod.GET)
    public String MantEstanteGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String cod = request.getParameter("cod");
        String des = request.getParameter("des");
        String material = request.getParameter("material");
        String dimension = request.getParameter("dimension");
        String almacen = request.getParameter("almacen");
        String estado = request.getParameter("cb_estado");
        String cuerpo = request.getParameter("cb_cuerpo");
        String balda = request.getParameter("cb_balda");
        String lado = request.getParameter("cb_lado");

        String var_request = "";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_estante_mant";
            String array[] = new String[10];
            array[0] = id; 
            array[1] = cod;
            array[2] = des;
            array[3] = material;
            array[4] = dimension;
            array[5] = almacen;
            array[6] = estado;
            array[7] = cuerpo;
            array[8] = balda;
            array[9] = lado;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_estante_guardar";
    }
    
    
    
////    CUERPO ----------------------------------------------------------------------------------------------------------------
            
    @RequestMapping(value = {"/sgd/mant_cuerpo"}, method = RequestMethod.GET)
    public String MantCuerpo(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE CUERPO");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_cuerpo_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_cuerpo";        
    } 

    @RequestMapping(value = {"/sgd/mant_cuerpo_tbl"}, method = RequestMethod.GET)
    public String MantCuerpoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        ConeccionDB cn =  new ConeccionDB();            

        String np = "sgd.fn_cuerpo_consulta";
        String array[] = new String[1];
        array[0] = "";
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector vss =  (Vector) v_datos.get(i);
            String i_id = vss.get(0).toString();
            String c_cod = vss.get(1).toString();
            String c_des = vss.get(2).toString();
            String c_estt = vss.get(4).toString();
            String c_est_reg = vss.get(5).toString();
            if ("1".equals(c_est_reg)) {
                c_est_reg = "Activo";
            } else {
                c_est_reg = "Inactivo";
            }       
            String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_cuerpo_popup('"+i_id+"')'><span class='glyphicon glyphicon-edit'></span></button>";

            Vector vv = new Vector();
            vv.add(i_id);
            vv.add(c_cod);
            vv.add(c_des);
            vv.add(c_estt);
            vv.add(c_est_reg);
            vv.add(btn);
            v_temp.add(vv);                
        }

        Vector v_tbl_cab =  new Vector();
        v_tbl_cab.add("ID. CUERPO");
        v_tbl_cab.add("COD. CUERPO");
        v_tbl_cab.add("DES. CUERPO");
        v_tbl_cab.add("ESTANTE");
        v_tbl_cab.add("ESTADO");
        v_tbl_cab.add("EDITAR");

        Util u = new Util();
        String tbl =  u.tbl(v_tbl_cab, v_temp);
        request.setAttribute("response",tbl);

        return "sgd/mant_cuerpo_tbl";
    }  

    @RequestMapping(value = {"/sgd/mant_cuerpo_popup"}, method = RequestMethod.GET)
    public String MantCuerpoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVO CUERPO"); 
        try {
            String id = request.getParameter("id");
            
            ConeccionDB cn = new ConeccionDB(); 
            
            String np = "sgd.fn_cuerpo_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_des = "";
            String c_cod = "";
            String i_id_estt = "";
            String c_est_reg = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_cod = datos_v.get(1).toString();
                c_des = datos_v.get(2).toString();
                i_id_estt = datos_v.get(3).toString();
                c_est_reg = datos_v.get(5).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("cod", c_cod);
            request.setAttribute("des", c_des);
            request.setAttribute("id_estt", i_id_estt);          
            
//            información para el combo Estante
            String nt = "sgd.fn_estante_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
            String cb_desc_estt = util.contenido_combo(datos_cbo, i_id_estt);
            request.setAttribute("cb_desc_estt", cb_desc_estt);            
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);                            
             
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_cuerpo_popup";
    }  

    @RequestMapping(value = {"/sgd/mant_cuerpo_guardar"}, method = RequestMethod.GET)
    public String MantCuerpoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String cod = request.getParameter("cod");
        String des = request.getParameter("des");
        String id_estt = request.getParameter("id_estt");
        String estado = request.getParameter("cb_estado");

        String var_request = "";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_cuerpo_mant";
            String array[] = new String[5];
            array[0] = id;
            array[1] = cod;
            array[2] = des;
            array[3] = id_estt;
            array[4] = estado;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_cuerpo_guardar";
    } 
    
    ////    BALDA ----------------------------------------------------------------------------------------------------------------
            
    @RequestMapping(value = {"/sgd/mant_balda"}, method = RequestMethod.GET)
    public String MantBalda(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE BALDA");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_balda_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_balda";        
    } 
    
    @RequestMapping(value = {"/sgd/mant_balda_tbl"}, method = RequestMethod.GET)
    public String MantBaldaTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        ConeccionDB cn =  new ConeccionDB();            

        String np = "sgd.fn_balda_consulta";
        String array[] = new String[1];
        array[0] = "";
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector vss =  (Vector) v_datos.get(i);
            String i_id = vss.get(0).toString();
            String c_cod = vss.get(1).toString();
            String c_des = vss.get(2).toString();
            String c_cpo = vss.get(4).toString();
            String c_estt = vss.get(6).toString();
            String c_est_reg = vss.get(5).toString();
            if ("1".equals(c_est_reg)) {
                c_est_reg = "Activo";
            } else {
                c_est_reg = "Inactivo";
            }       
            String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_balda_popup('"+i_id+"')'><span class='glyphicon glyphicon-edit'></span></button>";

            Vector vv = new Vector();
            vv.add(i_id);
            vv.add(c_cod);
            vv.add(c_des);
            vv.add(c_cpo);
            vv.add(c_estt);
            vv.add(c_est_reg);
            vv.add(btn);
            v_temp.add(vv);                
        }

        Vector v_tbl_cab =  new Vector();
        v_tbl_cab.add("ID. BALDA");
        v_tbl_cab.add("COD. BALDA");
        v_tbl_cab.add("DES. BALDA");
        v_tbl_cab.add("CUERPO");
        v_tbl_cab.add("ESTANTE");
        v_tbl_cab.add("ESTADO");
        v_tbl_cab.add("EDITAR");

        Util u = new Util();
        String tbl =  u.tbl(v_tbl_cab, v_temp);
        request.setAttribute("response",tbl);

        return "sgd/mant_balda_tbl";
    }  
    
    @RequestMapping(value = {"/sgd/mant_balda_popup"}, method = RequestMethod.GET)
    public String MantBaldaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVA BALDA"); 
        try {
            String id = request.getParameter("id");
            
            ConeccionDB cn = new ConeccionDB(); 
            
            String np = "sgd.fn_balda_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_des = "";
            String c_cod = "";
            String i_id_cpo = "";
            String c_est_reg = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_cod = datos_v.get(1).toString();
                c_des = datos_v.get(2).toString();
                i_id_cpo = datos_v.get(3).toString();
                c_est_reg = datos_v.get(5).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("cod", c_cod);
            request.setAttribute("des", c_des);
            request.setAttribute("id_cpo", i_id_cpo);
//            request.setAttribute("estado", c_est_reg);
            
//            información para el combo Cuerpo
            String nt = "sgd.fn_cuerpo_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
            String cb_desc_estt = util.contenido_combo(datos_cbo, i_id_cpo);
            request.setAttribute("cb_desc_cpo", cb_desc_estt);   
            
//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);  
          
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_balda_popup";
    }  
    
    @RequestMapping(value = {"/sgd/mant_balda_guardar"}, method = RequestMethod.GET)
    public String MantBaldaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String cod = request.getParameter("cod");
        String des = request.getParameter("des");
        String id_cpo = request.getParameter("id_cpo");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_balda_mant";
            String array[] = new String[5];
            array[0] = id;
            array[1] = cod;
            array[2] = des;
            array[3] = id_cpo;
            array[4] = estado;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_balda_guardar";    
    }
    

//INICIO TIPO DE UNIDAD DE CONSERVACIÓN 
    @RequestMapping(value = {"/sgd/mant_tipunidcons"}, method = RequestMethod.GET)
    public String MantTipunidcons(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE TIPO DE UNIDAD DE CONSERVACIÓN");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_tipunidcons_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_tipunidcons";        
    }     
    
    @RequestMapping(value = {"/sgd/mant_tipunidcons_tbl"}, method = RequestMethod.GET)
    public String MantTipunidconsTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        ConeccionDB cn =  new ConeccionDB();            

        String np = "sgd.fn_tipunidcons_consulta";
        String array[] = new String[1];
        array[0] = "";
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector vss =  (Vector) v_datos.get(i);
            String i_id = vss.get(0).toString();
            String c_cod = vss.get(2).toString();
            String c_des = vss.get(1).toString();
            String c_est_reg = vss.get(3).toString();
            if ("1".equals(c_est_reg)) {
                c_est_reg = "Activo";
            } else {
                c_est_reg = "Inactivo";
            }       //\""+i_id+"\"
            String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_tipunidcons_popup(\\\""+i_id+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";

            Vector vv = new Vector();
            vv.add(i_id);
            vv.add(c_cod);
            vv.add(c_des);
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
                                + "{'sTitle':'ID'} , "
                                + "{'sTitle':'CÓDIGO TIPO UNIDAD DE CONSERVACIÓN'} , "
                                + "{'sTitle':'TIPO UNIDAD DE CONSERVACIÓN'} , "
                                + "{'sTitle':'ESTADO'} , "
                                + "{'sTitle':'-'}  "
                                + "]");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
    //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
        //boton de excel
        sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
        ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

        String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_buscar_expediente'></table>";
        String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
        request.setAttribute("response", tbl_html + tbl);
        
        return "sgd/mant_tipunidcons_tbl";
    }  
//FIN TIPO DE UNIDAD DE CONSERVACIÓN     
//
//
//INICIO TIPO DE UNIDAD DE CONSERVACIÓN POPUP    
    @RequestMapping(value = {"/sgd/mant_tipunidcons_popup"}, method = RequestMethod.GET)
    public String MantTipunidconsPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVO TIPO DE UNIDAD DE CONSERVACIÓN"); 
        try {
            String id = request.getParameter("id");
            
            ConeccionDB cn = new ConeccionDB();             
            
            String np = "sgd.fn_tipunidcons_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_cod = "";
            String c_des = "";
            String c_est_reg = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_cod = datos_v.get(2).toString();
                c_des = datos_v.get(1).toString();
                c_est_reg = datos_v.get(3).toString();
            }            
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("cod", c_cod);
            request.setAttribute("des", c_des);

//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);              
            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_tipunidcons_popup";
    }  
//FIN TIPO DE UNIDAD DE CONSERVACIÓN POPUP       
//
//INICIO TIPO DE UNIDAD DE CONSERVACIÓN GUARDAR
    @RequestMapping(value = {"/sgd/mant_tipunidcons_guardar"}, method = RequestMethod.GET)
    public String MantTipunidconsGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String cod = request.getParameter("cod");
        String des = request.getParameter("des");
        String estado = request.getParameter("cb_estado");

        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_tipunidcons_mant";
            String array[] = new String[4];
            array[0] = id;
            array[1] = cod;
            array[2] = des;
            array[3] = estado;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_tipunidcons_guardar";    
    }
//FIN TIPO DE UNIDAD DE CONSERVACIÓN GUARDAR
//
//INICIO UNIDAD DE CONSERVACIÓN 
    @RequestMapping(value = {"/sgd/mant_unidcons"}, method = RequestMethod.GET)
    public String MantUnidcons(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE UNIDAD DE CONSERVACIÓN");             
//        request.setAttribute("btn_nuevo_reg","sgd_mant_unidcons_popup()");
//        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_unidcons";        
    }     
//INICIO UNIDAD DE CONSERVACIÓN 
//
//INICIO UNIDAD DE CONSERVACIÓN TABLA    
    @RequestMapping(value = {"/sgd/mant_unidcons_tbl"}, method = RequestMethod.GET)
    public String MantUnidconsTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        
        ConeccionDB cn =  new ConeccionDB();    
        String np = "sgd.fn_unidcons_ubicar_conslta";
        String array[] = new String[3];
        array[0] = "";
        array[1] = codUser;
        array[2] = "";        
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){            
            Vector vss =  (Vector) v_datos.get(i);
            String i_id = vss.get(0).toString();
            String i_id_tip_unidcons = vss.get(8).toString();
            String c_cod = vss.get(2).toString();
            String c_des = vss.get(3).toString();
            String c_obs = vss.get(4).toString();
            String d_fec_extini = vss.get(5).toString();
            String d_fec_extfin = vss.get(6).toString();
            String c_est_reg = vss.get(7).toString();
            String c_balda = vss.get(10).toString();
            if ("1".equals(c_est_reg)) {
                c_est_reg = "Activo";
            } else {
                c_est_reg = "Inactivo";
            } 
            String chkb = "<div class='form-group dropdown text-center'>"
                            + "<input type='checkbox' value='' id='cb_exp_"+i_id+"' />"
                            + "<label for='cb_exp_"+i_id+"'></label>"
                            + "</div>";
            
            String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_unidcons_popup(\\\""+i_id+"\\\")'><span class='glyphicon glyphicon-edit'></span></button>";

            Vector vv = new Vector();
            vv.add(chkb);
            vv.add(i_id);
            vv.add(i_id_tip_unidcons);
            vv.add(c_cod);
            vv.add(c_des);
            vv.add(c_obs);
            vv.add(d_fec_extini);
            vv.add(d_fec_extfin);
            vv.add(c_balda);
            vv.add(c_est_reg);
            vv.add(btn);
            v_temp.add(vv);                
        }

//        Vector v_tbl_cab =  new Vector();
//        v_tbl_cab.add("");
//        v_tbl_cab.add("ID. UNID.CONS");
//        v_tbl_cab.add("TIPO UNID.CONS");
//        v_tbl_cab.add("COD. UNID.CONS");
//        v_tbl_cab.add("DES. UNID.CONS");
//        v_tbl_cab.add("OBS. UNID.CONS");
//        v_tbl_cab.add("FECHA EXT.INICIAL");
//        v_tbl_cab.add("FECHA EXT.FINAL");
//        v_tbl_cab.add("UBICACIÓN");
//        v_tbl_cab.add("ESTADO");
//        v_tbl_cab.add("EDITAR");
//
//        Util u = new Util();
//        String tbl =  u.tbl(v_tbl_cab, v_temp);
//        request.setAttribute("response",tbl);

        Util util = new Util();
        String json = util.vector2json(v_temp);   
        Vector vc_tbl = new Vector();
        Vector sv =  new Vector();
        sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
        sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aoColumns");sv.add("["                                    
                                + "{'sTitle':'-'} , "
                                + "{'sTitle':'ID'} , "
                                + "{'sTitle':'TIPO'} , "
                                + "{'sTitle':'CODIGO'} , "
                                + "{'sTitle':'DESCRIPCIÓN'} , "
                                + "{'sTitle':'OBSERVACIONES'} , "
                                + "{'sTitle':'FECHA EXT.INICIAL'} , "
                                + "{'sTitle':'FECHA EXT.FINAL'} , "
                                + "{'sTitle':'UBICACIÓN'} , "
                                + "{'sTitle':'ESTADO'} , "
                                + "{'sTitle':'-'}  "
                                + "]");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
    //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
        //boton de excel
        sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
        ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

        String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_buscar_expediente'></table>";
        String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
        request.setAttribute("response", tbl_html + tbl);

        return "sgd/mant_unidcons_tbl";
    }  
//FIN UNIDAD DE CONSERVACIÓN TABLA        
//
//INICIO UNIDAD DE CONSERVACIÓN POPUP    
    @RequestMapping(value = {"/sgd/mant_unidcons_popup"}, method = RequestMethod.GET)
    public String MantUnidconsPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","GESTIÓN DE UNIDAD DE CONSERVACIÓN"); 
//        sesión de usuario
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        String codUO = (String) session.getAttribute("codUO");

        ConeccionDB cn =  new ConeccionDB();
        try {
            String id = request.getParameter("id");
            
            String np = "sgd.fn_unidcons_ubicar_conslta";
            String array[] = new String[3];
            array[0] = id;
            array[1] = codUser;
            array[2] = "";
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String i_id_tip_unidcons = "";
            String c_cod = "";
            String c_des = "";
            String c_obs = "";
            String d_fec_extini = "";
            String d_fec_extfin = "";
            String c_est_reg = ""; 
            String i_id_clasifdoc = ""; 
            String i_id_balda = ""; 
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                i_id_tip_unidcons = datos_v.get(1).toString();
                c_cod = datos_v.get(2).toString();
                c_des = datos_v.get(3).toString();
                c_obs = datos_v.get(4).toString();
                d_fec_extini = datos_v.get(5).toString();
                d_fec_extfin = datos_v.get(6).toString();
                c_est_reg = datos_v.get(7).toString();
                i_id_clasifdoc = datos_v.get(9).toString();
                i_id_balda = datos_v.get(11).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("id_tip_unidcons", i_id_tip_unidcons);
            request.setAttribute("cod", c_cod);
            request.setAttribute("des", c_des);
            request.setAttribute("obs", c_obs);
            request.setAttribute("fec_extini", d_fec_extini);
            request.setAttribute("fec_extfin", d_fec_extfin);      
            request.setAttribute("cod_unid_org", codUO);      
            
//          información para el combo Tipo Unidad de Conservación
            String nt = "sgd.fn_tipunidcons_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
            String cb_desc_estt = util.contenido_combo(datos_cbo, i_id_tip_unidcons);
            request.setAttribute("cb_desc_tip_unidcons", cb_desc_estt);   
            
//          información para el combo Clasificación Documental
            String nt_cd = "sgd.fn_clasifdoc_consulta";
            String array_cbo_cd[] = new String[1];
            array_cbo_cd[0] = "";
            Vector datos_cbo_cd = cn.EjecutarProcedurePostgres(nt_cd, array_cbo_cd);
            String cb_desc_cd = util.contenido_combo(datos_cbo_cd, i_id_clasifdoc);
            request.setAttribute("cb_desc_cd", cb_desc_cd); 

//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);   
            
            String cons_almac = "sgd.fn_almacen_consulta";
            String array_almacen[] = new String[1];
            array_almacen[0] = codUO;
            Vector datos_cbo_almacen = cn.EjecutarProcedurePostgres(cons_almac, array_almacen);
            String cb_almacen = util.contenido_combo(datos_cbo_almacen, "");
            request.setAttribute("cb_almacen", cb_almacen); 
            
            String cons_balda = "sgd.fn_unidcons_balda_consulta";
            String array_balda[] = new String[1];
            array_balda[0] = i_id_balda;
            Vector datos_cbo_balda = cn.EjecutarProcedurePostgres(cons_balda, array_balda);
//            String cb_balda = util.contenido_combo(datos_cbo_balda, i_id_balda);
//             
            String id_cuerpo = "";
            String id_estante = "";
            for(int i = 0 ; i<datos_cbo_balda.size() ; i++){
                Vector vss =  (Vector) datos_cbo_balda.get(i);
                id_cuerpo = vss.get(2).toString();
                id_estante = vss.get(4).toString();                
            }
            request.setAttribute("id_estante", id_estante);
            request.setAttribute("id_cuerpo", id_cuerpo);
            request.setAttribute("id_balda", i_id_balda);   
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_unidcons_popup";
    }  
//FIN UNIDAD DE CONSERVACIÓN POPUP    
//
//INICIO UNIDAD DE CONSERVACIÓN GUARDAR    
    @RequestMapping(value = {"/sgd/mant_unidcons_guardar"}, method = RequestMethod.GET)
    public String MantUnidconsGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {

        String id = request.getParameter("id");
        String id_tip_unidcons = request.getParameter("id_tip_unidcons");
        String cod = request.getParameter("cod");
        String des = request.getParameter("des");
        String obs = request.getParameter("obs");
        String fec_ini = request.getParameter("fec_extini");
        String fec_fin = request.getParameter("fec_extfin");
        String estado = request.getParameter("cb_estado");
        String cd = request.getParameter("cd");
        String iduo = request.getParameter("iduo");
        String idbalda = request.getParameter("idbalda");

        String var_request = "";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_unidcons_mant";
            String array[] = new String[11];
            array[0] = id;
            array[1] = id_tip_unidcons;
            array[2] = cod;
            array[3] = des;
            array[4] = obs;
            array[5] = fec_ini;
            array[6] = fec_fin;
            array[7] = cd;
            array[8] = estado;
            array[9] = iduo;
            array[10] = idbalda;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);            
            var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_unidcons_guardar";    
    }
//FIN UNIDAD DE CONSERVACIÓN GUARDAR 
//
//INICIO SERIE DOCUMENTAL            
    @RequestMapping(value = {"/sgd/mant_seriedoc"}, method = RequestMethod.GET)
    public String MantSeriedoc(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE SERIE DOCUMENTAL");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_seriedoc_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_seriedoc";        
    } 
//FIN SERIE DOCUMENTAL            
//
//INICIO SERIE DOCUMENTAL TABLA               
    @RequestMapping(value = {"/sgd/mant_seriedoc_tbl"}, method = RequestMethod.GET)
    public String MantSeriedocTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        ConeccionDB cn =  new ConeccionDB();            

        String np = "sgd.fn_seriedoc_consulta";
        String array[] = new String[1];
        array[0] = "";
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector vss =  (Vector) v_datos.get(i);
            String i_id = vss.get(0).toString();
            String c_cod = vss.get(2).toString();
            String c_des = vss.get(1).toString();
            String i_periodo = vss.get(3).toString();
            String i_pag = vss.get(4).toString();
            String i_pap = vss.get(5).toString();
            String i_poa = vss.get(6).toString();
            String c_est_reg = vss.get(7).toString();
            if ("1".equals(c_est_reg)) {
                c_est_reg = "Activo";
            } else {
                c_est_reg = "Inactivo";
            }       
            String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_seriedoc_popup(\""+i_id+"\")'><span class='glyphicon glyphicon-edit'></span></button>";

            Vector vv = new Vector();
            vv.add(i_id);
            vv.add(c_cod);
            vv.add(c_des);
            vv.add(i_periodo);
            vv.add(i_pag);
            vv.add(i_pap);
            vv.add(i_poa);
            vv.add(c_est_reg);
            vv.add(btn);
            v_temp.add(vv);
        }

//        Vector v_tbl_cab =  new Vector();
//        v_tbl_cab.add("ID");
//        v_tbl_cab.add("CODIGO");
//        v_tbl_cab.add("DESCRIPCIÓN");
//        v_tbl_cab.add("PER.RETENCIÓN (AÑOS)");
//        v_tbl_cab.add("PER.ARCH.GESTIÓN (AÑOS)");
//        v_tbl_cab.add("PER.ARCH.PERIFERICO (AÑOS)");
//        v_tbl_cab.add("PER.ORG.ADM.ARCH (AÑOS)");
//        v_tbl_cab.add("ESTADO");
//        v_tbl_cab.add("EDITAR");
//
//        Util u = new Util();
//        String tbl =  u.tbl(v_tbl_cab, v_temp);
//        request.setAttribute("response",tbl);

        Util util = new Util();
        String json = util.vector2json_tbl(v_temp);   
        Vector vc_tbl = new Vector();
        Vector sv =  new Vector();
        sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
        sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aoColumns");sv.add("[" 
                                + "{'sTitle':'ID'} , "
                                + "{'sTitle':'CODIGO'} , "
                                + "{'sTitle':'DESCRIPCIÓN'} , "
                                + "{'sTitle':'PER.RETENCIÓN (AÑOS)'} , "
                                + "{'sTitle':'PER.ARCH.GESTIÓN (AÑOS)'} , "
                                + "{'sTitle':'PER.ARCH.PERIFERICO (AÑOS)'} , "
                                + "{'sTitle':'PER.ORG.ADM.ARCH (AÑOS)'} , "
                                + "{'sTitle':'ESTADO'} , "
                                + "{'sTitle':'EDITAR'} "

                                + "]");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
    //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
        //boton de excel
        //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
    //    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
        sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
        ////Pintar de rojo el registro si no t.iene datos
    //    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
    //                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
    //                  "}";
    //    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
        ///////////////////////////////////////////////////////

        String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
        String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
        request.setAttribute("response", tbl_html + tbl);
        
        return "sgd/mant_seriedoc_tbl";
    }  
//FIN SERIE DOCUMENTAL TABLA  

    
    @RequestMapping(value = {"/sgd/mant_seriedoc_popup"}, method = RequestMethod.GET)
    public String MantSeriedocPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","NUEVA SERIE DOCUMENTAL"); 
        try {
            String id = request.getParameter("id");
            
            ConeccionDB cn = new ConeccionDB(); 
            
            String np = "sgd.fn_seriedoc_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_cod = "";
            String c_des = "";
            String i_periodo = "";
            String i_pag = "";
            String i_pap = "";
            String i_poa = "";
            String c_est_reg = "";  
            String i_id_uo = ""; 
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_cod = datos_v.get(2).toString();
                c_des = datos_v.get(1).toString();
                i_periodo = datos_v.get(3).toString();
                i_pag = datos_v.get(4).toString();
                i_pap = datos_v.get(5).toString();
                i_poa = datos_v.get(6).toString();
                c_est_reg = datos_v.get(7).toString();
                i_id_uo = datos_v.get(8).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("cod", c_cod);
            request.setAttribute("des", c_des);            
            request.setAttribute("per", i_periodo);
            request.setAttribute("pag", i_pag);
            request.setAttribute("pap", i_pap);
            request.setAttribute("poa", i_poa);            
            
//          información para el combo Unidad Orgánica
            //String i_id_uo = "";
            String nt_uo = "senamhi.fn_uo_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(nt_uo, array_cbo_uo);
            String cb_desc_uo = util.contenido_combo(datos_cbo_uo, i_id_uo);
            request.setAttribute("cb_desc_uo", cb_desc_uo); 

//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);    
            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_seriedoc_popup";
    }  
    
    @RequestMapping(value = {"/sgd/mant_seriedoc_guardar"}, method = RequestMethod.GET)
    public String MantSeriedocGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String cod = request.getParameter("cod");
        String des = request.getParameter("des");
        String per = request.getParameter("per");
        String pag = request.getParameter("pag");
        String pap = request.getParameter("pap");
        String poa = request.getParameter("poa");
        String estado = request.getParameter("cb_estado");
        String uo = request.getParameter("uo");
        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_seriedoc_mant";
            String array[] = new String[9];
            array[0] = id;
            array[1] = cod;
            array[2] = des;
            array[3] = per;
            array[4] = pag;
            array[5] = pap;
            array[6] = poa; 
            array[7] = uo;
            array[8] = estado;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_seriedoc_guardar";    
    }
    
    
////CLASIFICACIÓN DOCUMENTAL ----------------------------------------------------------------------------------------------------------------
            
    @RequestMapping(value = {"/sgd/mant_clasifdoc"}, method = RequestMethod.GET)
    public String MantClasifdoc(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE CLASIFICACIÓN DOCUMENTAL");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_clasifdoc_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_clasifdoc";        
    } 

    @RequestMapping(value = {"/sgd/mant_clasifdoc_tbl"}, method = RequestMethod.GET)
    public String MantClasifdocTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        ConeccionDB cn =  new ConeccionDB();            

        String np = "sgd.fn_clasifdoc_consulta";
        String array[] = new String[1];
        array[0] = "";
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector vss =  (Vector) v_datos.get(i);
            String i_id = vss.get(0).toString();
            String c_cod = vss.get(2).toString();
            String c_des = vss.get(1).toString();
            String i_id_sd = vss.get(5).toString();
            String c_est_reg = vss.get(4).toString();
            if ("1".equals(c_est_reg)) {
                c_est_reg = "Activo";
            } else {
                c_est_reg = "Inactivo";
            }       
            String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_clasifdoc_popup(\""+i_id+"\")'><span class='glyphicon glyphicon-edit'></span></button>";

            Vector vv = new Vector();
            vv.add(i_id);
            vv.add(c_cod);
            vv.add(c_des);
            vv.add(i_id_sd);
            vv.add(c_est_reg);
            vv.add(btn);
            v_temp.add(vv);
        }

//        Vector v_tbl_cab =  new Vector();
//        v_tbl_cab.add("ID");
//        v_tbl_cab.add("CODIGO");
//        v_tbl_cab.add("DESCRIPCIÓN");
//        v_tbl_cab.add("SERIE DOC");
//        v_tbl_cab.add("ESTADO");
//        v_tbl_cab.add("EDITAR");
//
//        Util u = new Util();
//        String tbl =  u.tbl(v_tbl_cab, v_temp);
//        request.setAttribute("response",tbl);

        Util util = new Util();
        String json = util.vector2json_tbl(v_temp);   
        Vector vc_tbl = new Vector();
        Vector sv =  new Vector();
        sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
        sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aoColumns");sv.add("[" 
                                + "{'sTitle':'ID'} , "
                                + "{'sTitle':'CODIGO'} , "
                                + "{'sTitle':'DESCRIPCIÓN'} , "
                                + "{'sTitle':'SERIE DOC'} , "
                                + "{'sTitle':'ESTADO'} , "
                                + "{'sTitle':'EDITAR'} "

                                + "]");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
    //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
        //boton de excel
        //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
    //    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
        sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
        ////Pintar de rojo el registro si no t.iene datos
    //    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
    //                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
    //                  "}";
    //    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
        ///////////////////////////////////////////////////////

        String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
        String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
        request.setAttribute("response", tbl_html + tbl);

        return "sgd/mant_clasifdoc_tbl";
    }  
    
    @RequestMapping(value = {"/sgd/mant_clasifdoc_popup"}, method = RequestMethod.GET)
    public String MantClasifdocPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","REGISTRO DE CLASIFICACIÓN DOCUMENTAL"); 

        try {
            String id = request.getParameter("id");
            
            ConeccionDB cn = new ConeccionDB(); 
            
            String np = "sgd.fn_clasifdoc_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
            
            String i_id = "";
            String c_cod = "";
            String c_des = "";
            String i_id_sd = "";
            String c_est_reg = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id = datos_v.get(0).toString();
                c_cod = datos_v.get(2).toString();
                c_des = datos_v.get(1).toString();
                i_id_sd = datos_v.get(3).toString();
                c_est_reg = datos_v.get(4).toString();
            }
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id);
            request.setAttribute("cod", c_cod);
            request.setAttribute("des", c_des);            
            request.setAttribute("id_sd", i_id_sd); 
            
//          información para el combo Serie Documental
            String nt = "sgd.fn_seriedoc_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
            String cb_desc_sd = util.contenido_combo(datos_cbo, i_id_sd);
            request.setAttribute("cb_desc_sd", cb_desc_sd);        

//          información para el combo Estado
            String ne = "sgd.fn_estado_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_desc_estado = util.contenido_combo(datos_cbo_est, c_est_reg);
            request.setAttribute("cb_estado", cb_desc_estado);                         
            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_clasifdoc_popup";
    }  
    
    @RequestMapping(value = {"/sgd/mant_clasifdoc_guardar"}, method = RequestMethod.GET)
    public String MantClasifdocGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String cod = request.getParameter("cod");
        String des = request.getParameter("des");
        String seriedoc = request.getParameter("id_sd");
        String estado = request.getParameter("cb_estado");
        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_clasifdoc_mant";
            String array[] = new String[5];
            array[0] = id;
            array[1] = cod;
            array[2] = des;
            array[3] = seriedoc;
            array[4] = estado;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_clasifdoc_guardar";    
    }
    
    
////UNIDAD ORGÁNICA ----------------------------------------------------------------------------------------------------------------
            
    @RequestMapping(value = {"/sgd/mant_unidorg"}, method = RequestMethod.GET)
    public String MantUnidorg(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE UNIDAD ORGÁNICA");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_clasifdoc_popup()"); 
        request.setAttribute("tit_btn","NUEVO REGISTRO");        
        return "sgd/mant_unidorg";        
    } 
    
    @RequestMapping(value = {"/sgd/mant_unidorg_tbl"}, method = RequestMethod.GET)
	public String MantUnidorgTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();            

            String np = "senamhi.fn_uo_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id = vss.get(0).toString();
                String c_des = vss.get(1).toString();
                
//                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_origen_popup('"+i_id+"')'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id);
                vv.add(c_des);
//                vv.add(btn);
                v_temp.add(vv);                
            }
     
            Vector v_tbl_cab =  new Vector();
//            v_tbl_cab.add("COD.UNID.ORG");
//            v_tbl_cab.add("DESCRIPCIÓN");
//            v_tbl_cab.add("EDITAR");
            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_unidorg_tbl";
	}     
//INICIO UNIDAD ORGANICA TABLA 
        
////INICIO BANDEJA ENTRADA ////***********************************************************************************************************
    @RequestMapping(value = {"/sgd/bandeja_entrada"}, method = RequestMethod.GET)
    public String BandejaEntrada(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","BANDEJA DE ENTRADA");
        return "sgd/bandeja_entrada";        
    } 
//FIN BANDEJA ENTRADA 
//
//    
//INICIO BANDEJA ENTRADA POR PERFIL
    @RequestMapping(value = {"/sgd/bandeja_perfil_tbl"}, method = RequestMethod.GET)
    public String BandejaPerfil(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","BANDEJA DE ENTRADA");
        
        String cod_perfil_selected = request.getParameter("cod_perfil");
        
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");        
        String idPers = (String) session.getAttribute("idPers");        
        String uoPers = (String) session.getAttribute("codUO");        
        String nomUser = (String) session.getAttribute("nomPers");        
        String appUser = (String) session.getAttribute("appPers");        
        String apmUser = (String) session.getAttribute("apmPers");  
        String nombreUser =  appUser.trim() +" "+ apmUser.trim() +" "+ nomUser.trim();      
        
        String origen = "1";//1=interno, 2=externo(ventanilla); depende del perfil
        String cod_pfl = "";
        String des_pfl = "";
        String obj_active_tab = "";
        
        Date date = new Date();//Fecha de registro del documento (por referencia)
        DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
        String fecha_acuse = formatofec.format(date);
        
        ConeccionDB cn =  new ConeccionDB();
        Util u = new Util();            
//        consulta perfiles de usuario: dircción, profesional y ventanilla
//        id de persona, unidad orgánica a la que pertenece y nivel en el organigrama
        String np = "seguridad.fn_perfiluser_consulta";
        String array[] = new String[2];
        array[0] = codUser;
        array[1] = "1"; //id sistema ... 1 = sgd
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
//        String nom_pers = "";
//        String id_pers = "";
//        String id_uo = "";
        String nivel = "";
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector datos_v =  (Vector) v_datos.get(i);
//            nom_pers = datos_v.get(3).toString();
//            id_pers = datos_v.get(4).toString();
//            id_uo = datos_v.get(5).toString();
            nivel = datos_v.get(7).toString();
        }
//            String tab_bandeja = "";
        String tab_bandeja_button = "";
        String tab_bandeja_content = "";
        int j = 0;
        String cod_perfil="";
        String perfil="";
        String prfl_ventanilla="5";
        String id_vt = "";
        String user_agrupa = "";
        
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector t_v =  (Vector) v_datos.get(i);
            cod_perfil = t_v.get(0).toString();
            perfil = t_v.get(1).toString();
            
            if (cod_perfil.equals(prfl_ventanilla)){//ventanilla consulta
                String cons_vt = "senamhi.fn_ventanilla_consulta";    
                String array_vt[] = new String[1];
                array_vt[0] = uoPers;
                Vector v_datos_vt = cn.EjecutarProcedurePostgres(cons_vt, array_vt);                    
                for(int v = 0 ; v<v_datos_vt.size() ; v++){
                    Vector datos_vt =  (Vector) v_datos_vt.get(v);
                    id_vt = datos_vt.get(0).toString();
                }                
            }            
            String np_bandeja = "sgd.fn_bandeja_consulta";
            String array_bandeja[] = new String[4];
            array_bandeja[0] = codUser;
            array_bandeja[1] = cod_perfil;
            array_bandeja[2] = "2";
            array_bandeja[3] = uoPers;
            
            Vector v_tbl_data_bandeja = cn.EjecutarProcedurePostgres(np_bandeja, array_bandeja);

            Vector v_temp = new Vector();
            for(j = 0 ; j<v_tbl_data_bandeja.size() ; j++){
                Vector vss =  (Vector) v_tbl_data_bandeja.get(j);
                String i_id = vss.get(0).toString();
                String i_cut = vss.get(1).toString();
                String d_fec_reg = vss.get(2).toString();
                String d_fec_envio = vss.get(3).toString();
                String i_nro_doc = vss.get(4).toString();
                String c_asu_doc = vss.get(5).toString();
                String c_des_accion = vss.get(6).toString(); 
                String c_des_uo = vss.get(7).toString(); 
                String c_des_persona = vss.get(8).toString();
                String c_des_perfil = vss.get(9).toString(); 
                String i_id_doc = vss.get(10).toString();
//                    String i_id_cond = vss.get(11).toString();
                String i_id_flujo = vss.get(12).toString();
                String i_per_exp = vss.get(13).toString();
                String c_fisico = vss.get(14).toString();    
                String confima_fisico = vss.get(15).toString(); 
                String accion = vss.get(16).toString(); 
                String matriz = vss.get(17).toString(); 
                String adjuntos = vss.get(18).toString(); 
                user_agrupa = vss.get(19).toString();
                String user_reg = vss.get(20).toString();
                String prioridad = vss.get(21).toString();
                String fec_modif = vss.get(22).toString();
                String fec_acuse = vss.get(23).toString();
                String fis= "1";
                String conf_fis = "1";
                String deshabilita = "modal";
                String deshabilita_deriva = "enabled";
                if (c_fisico.equals(fis)){
                    c_fisico = "Pendiente";
                    deshabilita = "active";
                    deshabilita_deriva = "modal";
                }else{
                    c_fisico = "";
                }                
                if (confima_fisico.equals(conf_fis)){
                   c_fisico = "Recibido";
                    deshabilita = "modal"; 
                    deshabilita_deriva = "enabled";
                }
                String acc = "1";
                String deshabilita_acc = "enabled";
                if (accion.equals(acc)){
                    deshabilita_acc = "modal";
                } 
                String deshabilita_modifica = "modal";
                if(user_reg.equals(codUser)){//deshabilita la opción para modificar un documento
                    deshabilita_modifica = "enabled";
                }                
                String chkb = "<div class='form-group dropdown text-center'>"
                            + "<input type='checkbox' value='"+i_id+"_"+i_cut+"_"+i_per_exp+"_"+d_fec_reg+"' id='cb_exp_"+i_id+'_'+i_id_doc+"' class='cb_agrupa_exp' onchange='sgd_lista_cut_agrupa_tmp(this)' />"
                            + "<label for='cb_exp_"+i_id+'_'+i_id_doc+"'></label>"
                            + "</div>";

                String btn = "<div class='form-group dropdown text-center'>"
                            + "<button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown' id='herramientas'>"
                            + "<span class='glyphicon glyphicon-wrench'>"
                            + "</span>"
                            + "</button>"
                            + "<ul class='dropdown-menu dropdown-menu-left'>" 
                            + "<li class='divider'></li>";
                if (fec_acuse.equals("")){
                    btn += "<li><a onclick='sgd_mant_expediente_consulta_popup(\\\""+i_id+"\\\",\\\""+i_id_doc+"\\\",\\\""+i_id_flujo+"\\\",\\\""+codUser+"\\\"); sgd_mant_acuse_guardar(\\\""+i_id_flujo+"\\\",\\\""+fecha_acuse+"\\\",\\\""+cod_perfil+"\\\")'>Consultar Expediente</a></li>";
                }else{
                    btn += "<li><a onclick='sgd_mant_expediente_consulta_popup(\\\""+i_id+"\\\",\\\""+i_id_doc+"\\\",\\\""+i_id_flujo+"\\\",\\\""+codUser+"\\\")'>Consultar Expediente</a></li>";
                }
                    btn += "<li><a onclick='sgd_mant_expediente_añadirdoc_popup(\\\""+i_id+"\\\",\\\""+codUser+"\\\",\\\""+idPers+"\\\",\\\""+uoPers+"\\\",\\\""+nombreUser.trim()+"\\\",\\\""+id_vt+"\\\")'>Añadir documento</a></li>"
                            + "<li class=''><a onclick='sgd_mant_expediente_modificadoc_popup(\\\""+i_id+"\\\",\\\""+i_id_doc+"\\\",\\\""+i_id_flujo+"\\\",\\\""+codUser+"\\\",\\\""+user_agrupa+"\\\",\\\""+uoPers+"\\\")'>Modificar documento</a></li>"
                            + "<li class='divider'></li>"
                            + "<li  class=\\\""+"\\\"><a onclick='sgd_mant_expediente_deriva_cut_popup(\\\""+i_id+"\\\",\\\""+i_cut+"\\\",\\\""+i_per_exp+"\\\",\\\""+cod_perfil+"\\\",\\\""+idPers+"\\\",\\\""+uoPers+"\\\",\\\""+i_id_flujo+"\\\",\\\""+nivel+"\\\")'>Derivar</a></li>";
                
                        if (uoPers.equals("90000003") && cod_perfil.equals("3")){
                            btn += "<li><a onclick='sgd_mant_expediente_deriva_popup(\\\""+i_id+"\\\",\\\""+i_cut+"\\\",\\\""+i_per_exp+"\\\",\\\""+cod_perfil+"\\\",\\\""+idPers+"\\\",\\\""+uoPers+"\\\",\\\""+i_id_flujo+"\\\",\\\""+nivel+"\\\")'>Derivar Docs</a></li>";
                        }                        
                            btn += "<li class=\\\""+deshabilita_acc+"\\\"><a onclick='sgd_mant_archiva_flujo_popup(\\\""+i_id_flujo+"\\\",\\\""+i_cut+"\\\",\\\""+i_per_exp+"\\\",\\\""+c_des_persona+"\\\",\\\""+c_des_uo+"\\\")'>Archivar</a></li>" 
//                            + "<li><a onclick='sgd_mant_rechaza_flujo_popup('"+i_cut+"','"+i_id_flujo+"','"+c_des_persona+"','"+c_des_uo+"')'>Rechazar</a></li>" 
                            + "<li class='divider'></li>" 
                            
                            + "<li class=\\\""+deshabilita+"\\\"><a onclick='sgd_mant_fisico_popup(\\\""+i_id_doc+"\\\",\\\""+i_id_flujo+"\\\")'>Confirma físico</a></li>"  
                            + "</ul>"
                            + "</div>";
                           
                Vector vv = new Vector();
                vv.add(chkb);
                vv.add(btn);                
                vv.add(i_cut);
                vv.add(d_fec_reg);
                vv.add(i_nro_doc);               
                vv.add(c_asu_doc);
                vv.add(d_fec_envio);
                vv.add(c_des_uo);
                vv.add(c_des_persona + " ("+c_des_perfil+")");
//                vv.add(c_des_perfil);
                vv.add(c_des_accion);                
                vv.add(c_fisico);
                vv.add(adjuntos);
                vv.add(fec_modif);
                vv.add(prioridad);
                vv.add(fec_acuse);
                v_temp.add(vv);   
                
            }
//        String tbl_bandeja = u.tbl(v_tbl_cab, v_temp); 
        Util util = new Util();
        String json = util.vector2json(v_temp);   
        Vector vc_tbl = new Vector();
        Vector sv =  new Vector();
    //            sv.add("language");sv.add("{'url':'"+request.getContextPath()+"/static/datatables/Spanish.json'}");vc_tbl.add(sv);sv =  new Vector();
    //            sv.add("bFilter");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    //            sv.add("bLengthChange");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
    //            sv.add("bInfo");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
    //            sv.add("bPaginate");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
    //            sv.add("iDisplayLength");sv.add(v_temp.size());vc_tbl.add(sv);sv =  new Vector();
    //            sv.add("bJQueryUI");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
        sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
        sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
                                
        sv.add("aoColumns");sv.add("["                                    
                                + "{'sTitle':'-'} , "
                                + "{'sTitle':'HERR'},  "
                                + "{'sTitle':'CUT'} , "
                                + "{'sTitle':'FEC.REG'} , "
                                + "{'sTitle':'DOC'} , "
                                + "{'sTitle':'ASUNTO'} , "
                                + "{'sTitle':'FEC.ENV'} , "                                 
                                + "{'sTitle':'U.ORG. ENVIA'} , "
                                + "{'sTitle':'PROF. ENVIA'} , "
                                 + "{'sTitle':'ACCIÓN'} , "
                                + "{'sTitle':'FISICO'} , "
                                + "{'sTitle':'EXP. AGRUP'} , "
                                + "{'sTitle':'FEC. MOD.'} , "
                                + "{'sTitle':'PRIOR'},  "
                                + "{'sTitle':'VISTO'}  "
                                + "]");vc_tbl.add(sv);sv =  new Vector();
                                
        
//          sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,2,3,6,7,9,10,12,13]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
        sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,2,3,6,7,9,10,12,13]}]");vc_tbl.add(sv);sv =  new Vector();
        //boton de excel
//        sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//        sv.add("dom");sv.add("'<\"top\"fp<\"clear\">>Bir<\"bottom\"<\"clear\">>'");vc_tbl.add(sv);sv =  new Vector();
        sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//        sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
        sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
        ////Pintar de rojo el registro si no t.iene datos
        String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                        " if (rtrim(aData[13]) == 'ALTA'){$('td', nRow).addClass('ui-state-error' );} " +       
                        " if (rtrim(aData[13]) == 'MEDIA'){$('td', nRow).addClass('ui-state-highbaja' );} " +
                        " if (rtrim(aData[9]) == 'PENDIENTE'){$('td', nRow).addClass('ui-state-highnuevo' );} " +
                        " if (rtrim(aData[9]) != 'PENDIENTE' && rtrim(aData[14]) == ''){$('td', nRow).addClass('ui-state-highpendiente' );} " +
                      "}";
        sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
        ///////////////////////////////////////////////////////
        
        sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
        

        String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive ' id='c_tbl_expediente_"+cod_perfil+"'></table>";
        String tbl = util.datatable("c_tbl_expediente_"+cod_perfil+"",vc_tbl);            
//        request.setAttribute("response", tbl_html + tbl);   

        if(cod_perfil_selected.equals("")){
                if(i == 0){  
                        obj_active_tab = "active";                    
                        tab_bandeja_button += "<li class='topnav "+obj_active_tab+"' id = '"+cod_perfil+"'>"
                                            + "<a class'btn btn-info' href='#tab_"+cod_perfil+"' data-toggle='tab' onclick='func_perfil(\""+cod_perfil+"\",\""+perfil+"\")' >BANDEJA: "+perfil+"</a>"
                                            + "<span class='badge social-counter-bandeja'>"+j+"</span>"
                                            + "</li>";  
                        cod_pfl = cod_perfil; 
                        des_pfl = perfil;                    
                }else{                
                        tab_bandeja_button += "<li class='topnav' id = '"+cod_perfil+"'>"
                                            + "<a href='#tab_"+cod_perfil+"' data-toggle='tab' onclick='func_perfil(\""+cod_perfil+"\",\""+perfil+"\")'>BANDEJA: "+perfil+"</a>"
                                            + "<span class='badge social-counter-bandeja'>"+j+"</span></li>";

                }            
                if(i == 0){
                    tab_bandeja_content += "<div class='tab-pane active' id='tab_"+cod_perfil+"'>"+tbl_html + tbl+"</div>" ; 
                }else{
                    tab_bandeja_content += "<div class='tab-pane' id='tab_"+cod_perfil+"'>"+tbl_html + tbl+"</div>";
                }
        }else{
                if(cod_perfil_selected.equals(cod_perfil)){  
                        obj_active_tab = "active";                    
                        tab_bandeja_button += "<li class='topnav "+obj_active_tab+"' id = '"+cod_perfil+"'>"
                                            + "<a class'btn btn-info' href='#tab_"+cod_perfil+"' data-toggle='tab' onclick='func_perfil(\""+cod_perfil+"\",\""+perfil+"\")' >BANDEJA: "+perfil+"</a>"
                                            + "<span class='badge social-counter-bandeja'>"+j+"</span>"
                                            + "</li>";  
                        cod_pfl = cod_perfil; 
                        des_pfl = perfil;                    
                }else{                
                        tab_bandeja_button += "<li class='topnav' id = '"+cod_perfil+"'>"
                                            + "<a href='#tab_"+cod_perfil+"' data-toggle='tab' onclick='func_perfil(\""+cod_perfil+"\",\""+perfil+"\")'>BANDEJA: "+perfil+"</a>"
                                            + "<span class='badge social-counter-bandeja'>"+j+"</span></li>";

                }            
                if(cod_perfil_selected.equals(cod_perfil)){
                    tab_bandeja_content += "<div class='tab-pane active' id='tab_"+cod_perfil+"'>"+tbl_html + tbl+"</div>" ; 
                }else{
                    tab_bandeja_content += "<div class='tab-pane' id='tab_"+cod_perfil+"'>"+tbl_html + tbl+"</div>";
                }   
            }        
        }  
        request.setAttribute("perfil",cod_pfl);
        request.setAttribute("des_pfl",des_pfl);
        request.setAttribute("codUser",codUser);
        request.setAttribute("nom_pers",nombreUser.trim());
        request.setAttribute("id_pers",idPers);
        request.setAttribute("id_uo",uoPers);        
        request.setAttribute("id_vt",id_vt);
        request.setAttribute("user_agrupa",user_agrupa);
        request.setAttribute("tab_bandeja_button",tab_bandeja_button);
        request.setAttribute("tab_bandeja_content",tab_bandeja_content);
        
        return "sgd/bandeja_perfil_tbl";        
    } 
//FIN BANDEJA ENTRADA POR PERFIL  
//        
//INICIO EXPEDIENTE AÑADIR DOCUMENTO POPUP            
    @RequestMapping(value = {"/sgd/mant_expediente_añadirdoc_popup"}, method = RequestMethod.GET)
    public String MantExpedienteAñadirdocPopup_1(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        String des_pfl = request.getParameter("des_pfl");//nombre perfil de usuario   
        request.setAttribute("title_pag","EXPEDIENTE - ADICIONAR DOCUMENTO - PERFIL " + des_pfl);           
        int j = 0;
        try {            
            ConeccionDB cn = new ConeccionDB();     
            Util util =  new Util();
            
            String codUser = request.getParameter("codUser");//id del expediente
            String nom_pers = request.getParameter("nom_pers");//id del expediente
            String id_pers = request.getParameter("id_pers");//id del expediente
            String id_uo = request.getParameter("id_uo");//id unidad orgánica del usuario
            String id_exp = request.getParameter("id_exp");//id del expediente
            String perfil = request.getParameter("perfil");//id del perfil del usuario   
            
            String id_vt = request.getParameter("id_vt");//ventanilla del usuario EXPEDIENTES NUEVOS  *************************************************            
            
            Calendar c = Calendar.getInstance();//Anio actual para el registro del documento (por referencia)           
            String annio = Integer.toString(c.get(Calendar.YEAR));
            
            Date date = new Date();//Fecha de registro del documento (por referencia)
            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String fecha = formatofec.format(date);
            //Fecha del documeto, por defecto el día actual            
            DateFormat fecDoc = new SimpleDateFormat("dd/MM/yyyy");
            String FecDoc = fecDoc.format(date); 
            
            String i_id_exp = "";//id del expediente
            String i_cut = "";//id del cut, numeración anual
            String i_per = "";//año del expediente
            String d_fec_reg = "";//fecha del registro del expediente
            String i_vtn = "";//ventanilla desde donde se generó el expediente
            String c_nro_cutext = "";//nro de cut externo, de otras entidades
            String i_cond = "";//condición del expediente
            String i_priori = "2";//prioridad
            String i_plazo = "";//plazo de atención a partir de día de su creación
            String i_alcan = "1";//alcance del expediente
            String i_tema = "4";//tema del expediente            
            String i_origen = "";//variable para el origen: externo o interno            
            String i_proc = "1";//id del procedimiento
                       
            String np = "sgd.fn_exp_nvodoc_consulta";//consulta para añadir documento a un cut existente
            String array[] = new String[1];
            array[0] = id_exp;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);             
            for(int i = 0 ; i<datos.size() ; i++){
                Vector datos_v =  (Vector) datos.get(j);
                i_id_exp = datos_v.get(0).toString();
                i_cut = datos_v.get(1).toString();
                i_per = datos_v.get(2).toString();
                i_origen = datos_v.get(3).toString();
                i_tema = datos_v.get(4).toString();
                i_proc = datos_v.get(5).toString();
                i_alcan = datos_v.get(6).toString();
                i_vtn = datos_v.get(7).toString();
                c_nro_cutext = datos_v.get(8).toString();
                i_priori = datos_v.get(9).toString();
                i_plazo = datos_v.get(10).toString(); 
                i_cond = datos_v.get(11).toString();
                d_fec_reg = datos_v.get(12).toString();
                }                     
            request.setAttribute("codUser", codUser); 
            request.setAttribute("perfil", perfil); 
            request.setAttribute("id_uo", id_uo);  
            request.setAttribute("id", i_id_exp); 
            request.setAttribute("cut", i_cut);         
            request.setAttribute("per", i_per);         
            request.setAttribute("fecreg", d_fec_reg);
            request.setAttribute("i_vtn", i_vtn);
            request.setAttribute("cutext", c_nro_cutext);
            request.setAttribute("i_plazo", i_plazo);    
            request.setAttribute("id_remite", id_pers);
            request.setAttribute("fec_doc", FecDoc);
            
            String nc = "sgd.fn_condicion_consulta";//combo condición               
            String array_cbo_condicion[] = new String[1];
            array_cbo_condicion[0] = "";
            Vector datos_cbo_condicion = cn.EjecutarProcedurePostgres(nc, array_cbo_condicion);
            String cb_desc_condicion = util.contenido_combo(datos_cbo_condicion, i_cond);
            request.setAttribute("cb_condicion", cb_desc_condicion);                        
       
            String npr = "sgd.fn_prioridad_consulta";//combo Prioridad
            String array_cbo_prioridad[] = new String[1];
            array_cbo_prioridad[0] = "";            
            Vector datos_cbo_prioridad = cn.EjecutarProcedurePostgres(npr, array_cbo_prioridad);
            String cb_desc_prioridad = util.contenido_combo(datos_cbo_prioridad, i_priori);
            request.setAttribute("cb_priori", cb_desc_prioridad);            
            
            String nacc = "sgd.fn_alcance_consulta";//combo Alcance
            String array_cbo_alcance[] = new String[2];
            array_cbo_alcance[0] = "";
            array_cbo_alcance[1] = "1";
            Vector datos_cbo_alcance = cn.EjecutarProcedurePostgres(nacc, array_cbo_alcance);
            String cb_desc_alcance = util.contenido_combo(datos_cbo_alcance, i_alcan);
            request.setAttribute("cb_alcance", cb_desc_alcance);
            
            String nt = "sgd.fn_tema_consulta";//combo Tema
            String array_cbo_tema[] = new String[2];
            array_cbo_tema[0] = "";
            array_cbo_tema[1] = "1";
            Vector datos_cbo_tema = cn.EjecutarProcedurePostgres(nt, array_cbo_tema);
            String cb_desc_tema = util.contenido_combo(datos_cbo_tema, i_tema);
            request.setAttribute("cb_tema", cb_desc_tema);    
            
            String ntr = "sgd.fn_tramite_consulta";//Trámite por procedimiento
            String array_cbo_tramite[] = new String[1];
            array_cbo_tramite[0] = i_proc;
            Vector datos_cbo_tramite = cn.EjecutarProcedurePostgres(ntr, array_cbo_tramite);
            String cb_desc_tramite = util.contenido_combo(datos_cbo_tramite, i_proc);
            request.setAttribute("cb_tramite", cb_desc_tramite);  
            
            String proc = "sgd.fn_procedimiento_consulta";//combo Origen
            String array_cbo_proc[] = new String[1];
            array_cbo_proc[0] = "";
            Vector datos_proc = cn.EjecutarProcedurePostgres(proc, array_cbo_proc);
            String cb_desc_proc = util.contenido_combo(datos_proc, i_proc);
            request.setAttribute("cb_procedimiento", cb_desc_proc); 
                
            String no = "sgd.fn_origen_consulta";//combo Origen
            String array_cbo_origen[] = new String[1];
            array_cbo_origen[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(no, array_cbo_origen);
            String cb_desc_orig = util.contenido_combo(datos_cbo, i_origen);
            request.setAttribute("cb_origen", cb_desc_orig);               
            
            String cons_destino_uo = "senamhi.fn_destino_consulta";//combo destino                  
            String array_destino_uo[] = new String[1];
            array_destino_uo[0] = "";
            Vector datos_cbo_uo_destino = cn.EjecutarProcedurePostgres(cons_destino_uo, array_destino_uo); 
            String cb_uo_dest = util.contenido_combo(datos_cbo_uo_destino, "");//variable de selección para combo destino
            request.setAttribute("cb_destino", cb_uo_dest);          
            
            String obj_enabled = "";
            String obj_readonly = "";
            String uo_remite = "";
            String nom_remite = "";
            String prfl_ventanilla = "5";
            if (perfil.equals(prfl_ventanilla)){// si el perfil es 5 - ventanilla                
                obj_enabled = "enabled";
                obj_readonly = "";
                uo_remite = "";
                nom_remite = "";
            }else{                
                obj_enabled = "disabled";
                obj_readonly = "readonly";
                uo_remite = id_uo;
                nom_remite = nom_pers;
            }                        
            request.setAttribute("txt_remite", nom_remite);
            String cons_rmte_uo = "senamhi.fn_uo_entidad_consulta";//consulta combo remite
            String array_rmte_uo[] = new String[1];
            array_rmte_uo[0] = "";//id persona
            Vector datos_cbo_uo_rmte = cn.EjecutarProcedurePostgres(cons_rmte_uo, array_rmte_uo);
            String cb_uo_rmte = util.contenido_combo(datos_cbo_uo_rmte, uo_remite); 
            request.setAttribute("cb_remite", cb_uo_rmte);      
            
            if (perfil.equals(prfl_ventanilla)){
                String ntdoc = "sgd.fn_clasificadoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
                String array_cbo_tdoc[] = new String[1];
                array_cbo_tdoc[0] = "";
                Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
                String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, "");
                request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc);
            }else{
                String ntdoc = "sgd.fn_clasifdoc_seriedoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
                String array_cbo_tdoc[] = new String[1];
                array_cbo_tdoc[0] = id_uo;
                Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
                String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, "");
                request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc);
            }    
            
            request.setAttribute("obj_enabled", obj_enabled);      
            request.setAttribute("obj_readonly", obj_readonly);      
                                
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_expediente_añadirdoc_popup";
    }  
//INICIO EXPEDIENTE AÑADIR DOCUMENTO POPUP
//    
//INICIO EXPEDIENTE CONSULTA DOCUMENTO POPUP            
    @RequestMapping(value = {"/sgd/mant_expediente_consulta_popup"}, method = RequestMethod.GET)
    public String MantExpedienteConsultadocPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        
        String des_pfl = request.getParameter("des_pfl");//id perfil de usuario         
        request.setAttribute("title_pag","CONSULTA DE EXPEDIENTE - PERFIL " + des_pfl);           
        int j = 0;
        try {            
            ConeccionDB cn = new ConeccionDB();     
            Util util =  new Util();
            
            String codUser = request.getParameter("codUser");//id usuario
            String nom_pers = request.getParameter("nom_pers");//nombre persona (usuario)
            String id_pers = request.getParameter("id_pers");//id persona
            String id_uo = request.getParameter("id_uo");//id unidad orgánica del usuario
            String perfil = request.getParameter("perfil");//id perfil de usuario 
            String user_agrupa = request.getParameter("user_agrupa");//id usuario que agrupa
            String id_exp = request.getParameter("id_exp");//id del expediente
            String doc = request.getParameter("doc"); //ID documento
            String flujo = request.getParameter("flujo"); //ID flujo  
            
            String id_vt = request.getParameter("id_vt");//ventanilla del usuario EXPEDIENTES NUEVOS  *************************************************
                        
            Calendar c = Calendar.getInstance();//Anio actual para el registro del expediente o documento (por referencia)           
            String annio = Integer.toString(c.get(Calendar.YEAR));
            
            Date date = new Date();//Fecha de registro del documento (por referencia)
            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String fecha = formatofec.format(date);
            //Fecha del documeto, por defecto el día actual            
            DateFormat fecDoc = new SimpleDateFormat("dd/MM/yyyy");
            String FecDoc = fecDoc.format(date); 
            
            String i_id_exp = "";//id del expediente
            String i_cut = "";//id del cut, numeración anual
            String i_per = "";//año del expediente
            String d_fec_reg = "";//fecha del registro del expediente
            String i_vtn = "";//ventanilla desde donde se generó el expediente
            String c_nro_cutext = "";//nro de cut externo, de otras entidades
            String i_cond = "";//condición del expediente
            String i_priori = "";//prioridad
            String i_plazo = "";//plazo de atención a partir de día de su creación
            String i_alcan = "1";//alcance del expediente
            String i_tema = "4";//tema del expediente            
            String i_origen = "";//variable para el origen: externo o interno            
            String i_proc = "1";//id del procedimiento
            String i_id_doc = ""; 
            String i_clsfdoc = "";
            String i_nrodoc = "";
            String d_fec_doc = "";
            String i_folio = "";
            String i_rmte = "";
            String c_destino = "";
            String c_asunto = ""; 
            String c_observacion = ""; 
            String i_id_flujo = "";
            String nom_dest = "";
            String uo_dest = "";            
            
            String np = "sgd.fn_exp_doc_consulta";//consulta para añadir documento a un cut existente
            String array[] = new String[3];
                array[0] = id_exp;
                array[1] = doc;
                array[2] = flujo;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);              
            for(int i = 0 ; i<datos.size() ; i++){
                Vector datos_v =  (Vector) datos.get(i);
                i_id_exp = datos_v.get(0).toString();
                i_cut = datos_v.get(1).toString();
                i_per = datos_v.get(2).toString();
                i_id_doc = datos_v.get(3).toString();
                d_fec_reg = datos_v.get(4).toString();
                i_cond = datos_v.get(5).toString();
                i_priori = datos_v.get(6).toString();
                i_plazo = datos_v.get(7).toString();
                i_alcan = datos_v.get(8).toString();
                i_tema = datos_v.get(9).toString();
                i_origen = datos_v.get(10).toString();
                i_proc = datos_v.get(11).toString();
                c_nro_cutext = datos_v.get(12).toString();
                i_clsfdoc = datos_v.get(13).toString();
                i_nrodoc = datos_v.get(14).toString();
                d_fec_doc = datos_v.get(15).toString();
                i_folio = datos_v.get(16).toString();
                i_rmte = datos_v.get(17).toString();
                c_destino = datos_v.get(18).toString();
                c_asunto = datos_v.get(19).toString();
                c_observacion = datos_v.get(20).toString();
                i_id_flujo = datos_v.get(21).toString();         
                i_vtn = datos_v.get(22).toString();         
                }                     
            request.setAttribute("codUser", codUser); 
            request.setAttribute("perfil", perfil); 
            request.setAttribute("user_agrupa", user_agrupa); 
            request.setAttribute("id", i_id_exp); 
            request.setAttribute("cut", i_cut);         
            request.setAttribute("per", i_per);         
            request.setAttribute("fecreg", d_fec_reg);
            request.setAttribute("doc", i_id_doc);
            request.setAttribute("i_vtn", i_vtn);
            request.setAttribute("cutext", c_nro_cutext);
            request.setAttribute("plazo", i_plazo);   
            request.setAttribute("id_remite", i_rmte);
            request.setAttribute("nrodoc", i_nrodoc);
            request.setAttribute("fec_doc", d_fec_doc);
            request.setAttribute("folio", i_folio);
            request.setAttribute("asunto", c_asunto);
            request.setAttribute("observacion", c_observacion);
            
            String nc = "sgd.fn_condicion_consulta";//combo condición               
            String array_cbo_condicion[] = new String[1];
            array_cbo_condicion[0] = "";
            Vector datos_cbo_condicion = cn.EjecutarProcedurePostgres(nc, array_cbo_condicion);
            String cb_desc_condicion = util.contenido_combo(datos_cbo_condicion, i_cond);
            request.setAttribute("cb_condicion", cb_desc_condicion);    
     
            String npr = "sgd.fn_prioridad_consulta";//combo Prioridad
            String array_cbo_prioridad[] = new String[1];
            array_cbo_prioridad[0] = "";            
            Vector datos_cbo_prioridad = cn.EjecutarProcedurePostgres(npr, array_cbo_prioridad);
            String cb_desc_prioridad = util.contenido_combo(datos_cbo_prioridad, i_priori);
            request.setAttribute("cb_priori", cb_desc_prioridad);            
            
            String nacc = "sgd.fn_alcance_consulta";//combo Alcance
            String array_cbo_alcance[] = new String[2];
            array_cbo_alcance[0] = "";
            array_cbo_alcance[1] = "1";
            Vector datos_cbo_alcance = cn.EjecutarProcedurePostgres(nacc, array_cbo_alcance);
            String cb_desc_alcance = util.contenido_combo(datos_cbo_alcance, i_alcan);
            request.setAttribute("cb_alcance", cb_desc_alcance);
            
            String nt = "sgd.fn_tema_consulta";//combo Tema
            String array_cbo_tema[] = new String[2];
            array_cbo_tema[0] = "";
            array_cbo_tema[1] = "1";
            Vector datos_cbo_tema = cn.EjecutarProcedurePostgres(nt, array_cbo_tema);
            String cb_desc_tema = util.contenido_combo(datos_cbo_tema, i_tema);
            request.setAttribute("cb_tema", cb_desc_tema);    
            
            String ntr = "sgd.fn_tramite_consulta";//Trámite por procedimiento
            String array_cbo_tramite[] = new String[1];
            array_cbo_tramite[0] = i_proc;
            Vector datos_cbo_tramite = cn.EjecutarProcedurePostgres(ntr, array_cbo_tramite);
            String cb_desc_tramite = util.contenido_combo(datos_cbo_tramite, i_proc);
            request.setAttribute("cb_tramite", cb_desc_tramite);  
            
            String proc = "sgd.fn_procedimiento_consulta";//combo Origen
            String array_cbo_proc[] = new String[1];
            array_cbo_proc[0] = "";
            Vector datos_proc = cn.EjecutarProcedurePostgres(proc, array_cbo_proc);
            String cb_desc_proc = util.contenido_combo(datos_proc, i_proc);
            request.setAttribute("cb_procedimiento", cb_desc_proc); 
                
            String no = "sgd.fn_origen_consulta";//combo Origen
            String array_cbo_origen[] = new String[1];
            array_cbo_origen[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(no, array_cbo_origen);
            String cb_desc_orig = util.contenido_combo(datos_cbo, i_origen);
            request.setAttribute("cb_origen", cb_desc_orig);               
            
            String np_pu = "senamhi.fn_uo_entidad_consulta";//consulta´para txt remite
            String array_pu[] = new String[1];
            array_pu[0] = i_rmte;
            Vector v_datos = cn.EjecutarProcedurePostgres(np_pu, array_pu);
            for(int u = 0 ; u<v_datos.size() ; u++){
                Vector datos_v =  (Vector) v_datos.get(u);
                nom_pers = datos_v.get(1).toString();
                id_pers = datos_v.get(0).toString();
                id_uo = datos_v.get(3).toString();
            }
            request.setAttribute("txt_remite", nom_pers); 
                        
            String cons_rmte_uo = "senamhi.fn_uo_entidad_consulta";//consulta combo remite
            String array_rmte_uo[] = new String[1];
            array_rmte_uo[0] = "";//id persona
            Vector datos_cbo_uo_rmte = cn.EjecutarProcedurePostgres(cons_rmte_uo, array_rmte_uo);
            String cb_uo_rmte = util.contenido_combo(datos_cbo_uo_rmte, id_uo); 
            request.setAttribute("cb_remite", cb_uo_rmte); 
            
            String cons_dest = "senamhi.fn_uo_entidad_consulta";//consulta para txt destino
            String array_dest[] = new String[1];
            array_dest[0] = c_destino;
            Vector v_dest = cn.EjecutarProcedurePostgres(cons_dest, array_dest);
            for(int u = 0 ; u<v_dest.size() ; u++){
                Vector datos_v =  (Vector) v_dest.get(u);
                nom_dest = datos_v.get(1).toString();
//                id_pers = datos_v.get(0).toString();
                uo_dest = datos_v.get(3).toString();
            }
            request.setAttribute("txt_destino", nom_dest); 
            
            String cons_destino_uo = "senamhi.fn_uo_entidad_consulta";//consulta combo destino                  
            String array_destino_uo[] = new String[1];
            array_destino_uo[0] = "";
            Vector datos_cbo_uo_destino = cn.EjecutarProcedurePostgres(cons_destino_uo, array_destino_uo); 
            String cb_uo_dest = util.contenido_combo(datos_cbo_uo_destino, uo_dest);//variable de selección para combo destino
            request.setAttribute("cb_destino", cb_uo_dest);          
            
            if (i_vtn.equals("")){
                String ntdoc = "sgd.fn_clasifdoc_seriedoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
                String array_cbo_tdoc[] = new String[1];
                array_cbo_tdoc[0] = id_uo;
                Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
                String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, i_clsfdoc);
                request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc);
            }else{
                String ntdoc = "sgd.fn_clasifdoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
                String array_cbo_tdoc[] = new String[1];
                array_cbo_tdoc[0] = "";
                Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
                String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, i_clsfdoc);
                request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc);
            }   
            
            String obj_disabled = "";
            if (user_agrupa.equals(codUser)){
                obj_disabled = "enabled";
            }else{
                obj_disabled = "disabled";
            }
            request.setAttribute("obj_disabled", obj_disabled);
            
            String cta_agrup = "";
            String cons_cta_agrupados = "sgd.fn_agrupacuenta_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cta[] = new String[1];
            array_cta[0] = id_exp;
            Vector datos_cta = cn.EjecutarProcedurePostgres(cons_cta_agrupados, array_cta); 
            for(int u = 0 ; u<datos_cta.size() ; u++){
                Vector datos_v =  (Vector) datos_cta.get(u);
                cta_agrup = datos_v.get(0).toString();                
            }
            request.setAttribute("cta_agrup", cta_agrup);   
                                
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_expediente_consulta_popup";
    }  
//FIN EXPEDIENTE CONSULTA DOCUMENTO POPUP   
//
//INICIO EXPEDIENTE MODIFICA DOCUMENTO POPUP            
    @RequestMapping(value = {"/sgd/mant_expediente_modifica_popup"}, method = RequestMethod.GET)
    public String MantExpedienteModificadocPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        String des_pfl = request.getParameter("des_pfl");//nombre perfil de usuario 
        request.setAttribute("title_pag","MODIFICANDO DOCUMENTO - PERFIL " + des_pfl);           
        int j = 0;
        try {            
            ConeccionDB cn = new ConeccionDB();     
            Util util =  new Util();
            
            String codUser = request.getParameter("codUser");//id usuario
            String id_uo = request.getParameter("id_uo");//id usuario
            
            String perfil = request.getParameter("perfil");//id perfil de usuario 
            String user_agrupa = request.getParameter("user_agrupa");//id usuario que agrupa
            String id_exp = request.getParameter("id_exp");//id del expediente
            String doc = request.getParameter("doc"); //ID documento
            String flujo = request.getParameter("flujo"); //ID flujo  
              
            String i_id_exp = "";//id del expediente
            String i_cut = "";//id del cut, numeración anual
            String i_per = "";//año del expediente
            String d_fec_reg = "";//fecha del registro del expediente
            String i_vtn = "";//ventanilla desde donde se generó el expediente
            String c_nro_cutext = "";//nro de cut externo, de otras entidades
            String i_cond = "";//condición del expediente
            String i_priori = "";//prioridad
            String i_plazo = "";//plazo de atención a partir de día de su creación
            String i_alcan = "1";//alcance del expediente
            String i_tema = "4";//tema del expediente            
            String i_origen = "";//variable para el origen: externo o interno            
            String i_proc = "1";//id del procedimiento
            String i_id_doc = ""; 
            String i_clsfdoc = "";
            String i_nrodoc = "";
            String d_fec_doc = "";
            String i_folio = "";
            String i_rmte = "";
            String c_destino = "";
            String c_asunto = ""; 
            String c_observacion = ""; 
            String i_id_flujo = "";
            String nom_dest = "";
            String uo_dest = "";            
            String c_obs_modifica = "";            
            String user_crea = "";            
//            String user_agrupa = "";            
            
            String np = "sgd.fn_exp_doc_consulta";//consulta para añadir documento a un cut existente
            String array[] = new String[3];
                array[0] = id_exp;
                array[1] = doc;
                array[2] = flujo;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);              
            for(int i = 0 ; i<datos.size() ; i++){
                Vector datos_v =  (Vector) datos.get(i);
                i_id_exp = datos_v.get(0).toString();
                i_cut = datos_v.get(1).toString();
                i_per = datos_v.get(2).toString();
                i_id_doc = datos_v.get(3).toString();
                d_fec_reg = datos_v.get(4).toString();
                i_cond = datos_v.get(5).toString();
                i_priori = datos_v.get(6).toString();
                i_plazo = datos_v.get(7).toString();
                i_alcan = datos_v.get(8).toString();
                i_tema = datos_v.get(9).toString();
                i_origen = datos_v.get(10).toString();
                i_proc = datos_v.get(11).toString();
                c_nro_cutext = datos_v.get(12).toString();
                i_clsfdoc = datos_v.get(13).toString();
                i_nrodoc = datos_v.get(14).toString();
                d_fec_doc = datos_v.get(15).toString();
                i_folio = datos_v.get(16).toString();
                i_rmte = datos_v.get(17).toString();
                c_destino = datos_v.get(18).toString();
                c_asunto = datos_v.get(19).toString();
                c_observacion = datos_v.get(20).toString();
                i_id_flujo = datos_v.get(21).toString();         
                i_vtn = datos_v.get(22).toString();         
                c_obs_modifica = datos_v.get(23).toString();         
                user_crea = datos_v.get(24).toString();         
//                user_agrupa = datos_v.get(24).toString();         
                }                     
            request.setAttribute("codUser", codUser); 
            request.setAttribute("perfil", perfil); 
            request.setAttribute("id_uo", id_uo); 
//            request.setAttribute("user_agrupa", user_agrupa); 
            request.setAttribute("id", i_id_exp); 
            request.setAttribute("cut", i_cut);         
            request.setAttribute("per", i_per);         
            request.setAttribute("fecreg", d_fec_reg);
            request.setAttribute("doc", i_id_doc);
            request.setAttribute("id_vt", i_vtn);
            request.setAttribute("cutext", c_nro_cutext);
            request.setAttribute("plazo", i_plazo);   
            request.setAttribute("id_remite", i_rmte);
            request.setAttribute("nrodoc", i_nrodoc);
            request.setAttribute("fec_doc", d_fec_doc);
            request.setAttribute("folio", i_folio);
            request.setAttribute("asunto", c_asunto);
            request.setAttribute("observacion", c_observacion);
            request.setAttribute("obs_modifica", c_obs_modifica);
            
            String nc = "sgd.fn_condicion_consulta";//combo condición               
            String array_cbo_condicion[] = new String[1];
            array_cbo_condicion[0] = "";
            Vector datos_cbo_condicion = cn.EjecutarProcedurePostgres(nc, array_cbo_condicion);
            String cb_desc_condicion = util.contenido_combo(datos_cbo_condicion, i_cond);
            request.setAttribute("cb_condicion", cb_desc_condicion);    
     
            String npr = "sgd.fn_prioridad_consulta";//combo Prioridad
            String array_cbo_prioridad[] = new String[1];
            array_cbo_prioridad[0] = "";            
            Vector datos_cbo_prioridad = cn.EjecutarProcedurePostgres(npr, array_cbo_prioridad);
            String cb_desc_prioridad = util.contenido_combo(datos_cbo_prioridad, i_priori);
            request.setAttribute("cb_priori", cb_desc_prioridad);            
            
            String nacc = "sgd.fn_alcance_consulta";//combo Alcance
            String array_cbo_alcance[] = new String[2];
            array_cbo_alcance[0] = "";
            array_cbo_alcance[1] = "1";
            Vector datos_cbo_alcance = cn.EjecutarProcedurePostgres(nacc, array_cbo_alcance);
            String cb_desc_alcance = util.contenido_combo(datos_cbo_alcance, i_alcan);
            request.setAttribute("cb_alcance", cb_desc_alcance);
            
            String nt = "sgd.fn_tema_consulta";//combo Tema
            String array_cbo_tema[] = new String[2];
            array_cbo_tema[0] = "";
            array_cbo_tema[1] = "1";
            Vector datos_cbo_tema = cn.EjecutarProcedurePostgres(nt, array_cbo_tema);
            String cb_desc_tema = util.contenido_combo(datos_cbo_tema, i_tema);
            request.setAttribute("cb_tema", cb_desc_tema);    
            
            String ntr = "sgd.fn_tramite_consulta";//Trámite por procedimiento
            String array_cbo_tramite[] = new String[1];
            array_cbo_tramite[0] = i_proc;
            Vector datos_cbo_tramite = cn.EjecutarProcedurePostgres(ntr, array_cbo_tramite);
            String cb_desc_tramite = util.contenido_combo(datos_cbo_tramite, i_proc);
            request.setAttribute("cb_tramite", cb_desc_tramite);  
            
            String proc = "sgd.fn_procedimiento_consulta";//combo Origen
            String array_cbo_proc[] = new String[1];
            array_cbo_proc[0] = "";
            Vector datos_proc = cn.EjecutarProcedurePostgres(proc, array_cbo_proc);
            String cb_desc_proc = util.contenido_combo(datos_proc, i_proc);
            request.setAttribute("cb_procedimiento", cb_desc_proc); 
                
            String no = "sgd.fn_origen_consulta";//combo Origen
            String array_cbo_origen[] = new String[1];
            array_cbo_origen[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(no, array_cbo_origen);
            String cb_desc_orig = util.contenido_combo(datos_cbo, i_origen);
            request.setAttribute("cb_origen", cb_desc_orig);               
            
            String nom_pers = "";//nombre persona (usuario)
            String id_pers = "";//id persona
//            String id_uo = "";//id unidad orgánica del usuario
            String np_pu = "senamhi.fn_uo_entidad_consulta";//consulta´para txt remite
            String array_pu[] = new String[1];
            array_pu[0] = i_rmte;
            Vector v_datos = cn.EjecutarProcedurePostgres(np_pu, array_pu);
            for(int u = 0 ; u<v_datos.size() ; u++){
                Vector datos_v =  (Vector) v_datos.get(u);
                nom_pers = datos_v.get(1).toString();
                id_pers = datos_v.get(0).toString();
//                id_uo = datos_v.get(3).toString();
            }
            request.setAttribute("txt_remite", nom_pers); 
                        
            String cons_rmte_uo = "senamhi.fn_uo_entidad_consulta";//consulta combo remite
            String array_rmte_uo[] = new String[1];
            array_rmte_uo[0] = "";//id persona
            Vector datos_cbo_uo_rmte = cn.EjecutarProcedurePostgres(cons_rmte_uo, array_rmte_uo);
            String cb_uo_rmte = util.contenido_combo(datos_cbo_uo_rmte, id_uo); 
            request.setAttribute("cb_remite", cb_uo_rmte); 
            
            String cons_dest = "senamhi.fn_uo_entidad_consulta";//consulta para txt destino
            String array_dest[] = new String[1];
            array_dest[0] = c_destino;
            Vector v_dest = cn.EjecutarProcedurePostgres(cons_dest, array_dest);
            for(int u = 0 ; u<v_dest.size() ; u++){
                Vector datos_v =  (Vector) v_dest.get(u);
                nom_dest = datos_v.get(1).toString();
//                id_pers = datos_v.get(0).toString();
                uo_dest = datos_v.get(3).toString();
            }
            request.setAttribute("txt_destino", nom_dest); 
            request.setAttribute("hd_id_des", c_destino); 
            
            String cons_destino_uo = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_destino_uo[] = new String[1];
            array_destino_uo[0] = "";
            Vector datos_cbo_uo_destino = cn.EjecutarProcedurePostgres(cons_destino_uo, array_destino_uo); 
            String cb_uo_dest = util.contenido_combo(datos_cbo_uo_destino, uo_dest);//variable de selección para combo destino
            request.setAttribute("cb_destino", cb_uo_dest);          
            
            String vtn_enabled_form = "";
//            if (i_vtn.equals("")){
            if (i_vtn == null || i_vtn.equals("")){
                String ntdoc = "sgd.fn_clasifdoc_seriedoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
                String array_cbo_tdoc[] = new String[1];
                array_cbo_tdoc[0] = id_uo;
                Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
                String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, i_clsfdoc);
                request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc);      
                vtn_enabled_form = "disabled";
                request.setAttribute("vtn_enabled_form", vtn_enabled_form);  
            }else{
                String ntdoc = "sgd.fn_clasifdoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
                String array_cbo_tdoc[] = new String[1];
                array_cbo_tdoc[0] = "";
                Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
                String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, i_clsfdoc);
                request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc);  
                if (user_crea.equals(codUser)){
                    vtn_enabled_form = "enabled";
                    request.setAttribute("vtn_enabled_form", vtn_enabled_form);   
                }else{
                    vtn_enabled_form = "disabled";
                    request.setAttribute("vtn_enabled_form", vtn_enabled_form);  
                }
            }            
            
            String obj_disabled = "";
            if (user_agrupa.equals(codUser)){
                obj_disabled = "enabled";
            }else{
                obj_disabled = "disabled";
            }
            request.setAttribute("obj_disabled", obj_disabled);
            request.setAttribute("user_agrupa", user_agrupa);
            
            String cta_agrup = "";
            String cons_cta_agrupados = "sgd.fn_agrupacuenta_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cta[] = new String[1];
            array_cta[0] = id_exp;
            Vector datos_cta = cn.EjecutarProcedurePostgres(cons_cta_agrupados, array_cta); 
            for(int u = 0 ; u<datos_cta.size() ; u++){
                Vector datos_v =  (Vector) datos_cta.get(u);
                cta_agrup = datos_v.get(0).toString();                
            }
            request.setAttribute("cta_agrup", cta_agrup);  
            
            String obj_disabled_form = "disabled";
            String obj_readonly_form = "readonly";
            if (user_crea.equals(codUser)){
                obj_disabled_form = "enabled";
                obj_readonly_form = "";
            } 
            request.setAttribute("obj_disabled_form", obj_disabled_form);  
            request.setAttribute("obj_readonly_form", obj_readonly_form);  
            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_expediente_modifica_popup";
    }  
//FIN EXPEDIENTE MODIFICA DOCUMENTO POPUP  
//    
//INICIO EXPEDIENTE NUEVO POPUP            
    @RequestMapping(value = {"/sgd/mant_expediente_nuevo_popup"}, method = RequestMethod.GET)
    public String MantExpedienteNuevoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {        
        
        String des_pfl = request.getParameter("des_pfl");//descripción del perfil del usuario        
        request.setAttribute("title_pag","NUEVO EXPEDIENTE - PERFIL " + des_pfl);           
        int j = 0;
        try {            
            ConeccionDB cn = new ConeccionDB();     
            Util util =  new Util();
            String codUser = request.getParameter("codUser");//id del expediente
            String nom_pers = request.getParameter("nom_pers");//id del expediente
            String id_pers = request.getParameter("id_pers");//id del expediente
            String id_uo = request.getParameter("id_uo");//id unidad orgánica del usuario
            String perfil = request.getParameter("perfil");//id del perfil del usuario  
            String id_vt = request.getParameter("id_vt");//ventanilla del usuario EXPEDIENTES NUEVOS
            String id_exp = request.getParameter("id_exp");//id del expediente
            String doc = request.getParameter("doc"); //ID del documento
            String flujo = request.getParameter("flujo"); //ID del flujo  
            
            Calendar c = Calendar.getInstance();//Anio actual para el registro del expediente o documento (por referencia)           
            String annio = Integer.toString(c.get(Calendar.YEAR));
            
            Date date = new Date();//Fecha de registro del documento (por referencia)
            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String fecha = formatofec.format(date);
            //Fecha del documeto, por defecto el día actual            
            DateFormat fecDoc = new SimpleDateFormat("dd/MM/yyyy");
            String fecdoc = fecDoc.format(date);                           
            
            request.setAttribute("codUser", codUser); 
            request.setAttribute("id_remite", id_pers);    
            request.setAttribute("id_uo", id_uo);    
            request.setAttribute("perfil", perfil);
            request.setAttribute("des_pfl", des_pfl);
            request.setAttribute("per", annio);         
            request.setAttribute("fecreg", fecha);            
            request.setAttribute("fec_doc", fecdoc);
            
            String id_orig = "";
            String uo_remite = "";
            String nom_remite = "";
            String obj_enabled = "";
            String obj_readonly = "";            
            String prfl_ventanilla = "5";
            if (perfil.equals(prfl_ventanilla)){// si el perfil es 5 - ventanilla
                request.setAttribute("id_vt", id_vt);  
                id_orig = "2";
                uo_remite = "";
                nom_remite = "";
                obj_enabled = "enabled";
                obj_readonly = "";
            }else{
                request.setAttribute("id_vt", "");  
                id_orig = "1";
                uo_remite = id_uo;
                nom_remite = nom_pers;
                obj_enabled = "disabled";
                obj_readonly = "readonly";
            } 
            request.setAttribute("txt_remite", nom_remite); 
            request.setAttribute("obj_enabled", obj_enabled);      
            request.setAttribute("obj_readonly", obj_readonly);      
            
            String nc = "sgd.fn_condicion_consulta";//combo condición               
            String array_cbo_condicion[] = new String[1];
            array_cbo_condicion[0] = "";
            Vector datos_cbo_condicion = cn.EjecutarProcedurePostgres(nc, array_cbo_condicion);
            String cb_desc_condicion = util.contenido_combo(datos_cbo_condicion, "2");
            request.setAttribute("cb_condicion", cb_desc_condicion);    
     
            String npr = "sgd.fn_prioridad_consulta";//combo Prioridad
            String array_cbo_prioridad[] = new String[1];
            array_cbo_prioridad[0] = "";            
            Vector datos_cbo_prioridad = cn.EjecutarProcedurePostgres(npr, array_cbo_prioridad);
            String cb_desc_prioridad = util.contenido_combo(datos_cbo_prioridad, "");
            request.setAttribute("cb_priori", cb_desc_prioridad);            
            
            String nacc = "sgd.fn_alcance_consulta";//combo Alcance
            String array_cbo_alcance[] = new String[2];
            array_cbo_alcance[0] = "";
            array_cbo_alcance[1] = "1";
            Vector datos_cbo_alcance = cn.EjecutarProcedurePostgres(nacc, array_cbo_alcance);
            String cb_desc_alcance = util.contenido_combo(datos_cbo_alcance, "");
            request.setAttribute("cb_alcance", cb_desc_alcance);
            
            String nt = "sgd.fn_tema_consulta";//combo Tema
            String array_cbo_tema[] = new String[2];
            array_cbo_tema[0] = "";
            array_cbo_tema[1] = "1";
            Vector datos_cbo_tema = cn.EjecutarProcedurePostgres(nt, array_cbo_tema);
            String cb_desc_tema = util.contenido_combo(datos_cbo_tema, "");
            request.setAttribute("cb_tema", cb_desc_tema);    
            
            String ntr = "sgd.fn_tramite_consulta";//Trámite por procedimiento
            String array_cbo_tramite[] = new String[1];
            array_cbo_tramite[0] = "";
            Vector datos_cbo_tramite = cn.EjecutarProcedurePostgres(ntr, array_cbo_tramite);
            String cb_desc_tramite = util.contenido_combo(datos_cbo_tramite, "1");
            request.setAttribute("cb_tramite", cb_desc_tramite);  
            
            String proc = "sgd.fn_procedimiento_consulta";//combo Origen
            String array_cbo_proc[] = new String[1];
            array_cbo_proc[0] = "";
            Vector datos_proc = cn.EjecutarProcedurePostgres(proc, array_cbo_proc);
            String cb_desc_proc = util.contenido_combo(datos_proc, "");
            request.setAttribute("cb_procedimiento", cb_desc_proc);             
            
            String no = "sgd.fn_origen_consulta";//combo Origen
            String array_cbo_origen[] = new String[1];
            array_cbo_origen[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(no, array_cbo_origen);
            String cb_desc_orig = util.contenido_combo(datos_cbo, id_orig);
            request.setAttribute("cb_origen", cb_desc_orig); 
                        
            String cons_rmte_uo = "senamhi.fn_uo_entidad_consulta";//consulta combo remite
            String array_rmte_uo[] = new String[1];
            array_rmte_uo[0] = "";//id persona
            Vector datos_cbo_uo_rmte = cn.EjecutarProcedurePostgres(cons_rmte_uo, array_rmte_uo);
            String cb_uo_rmte = util.contenido_combo(datos_cbo_uo_rmte, uo_remite); 
            request.setAttribute("cb_remite", cb_uo_rmte); 
                      
            String cons_destino_uo = "senamhi.fn_destino_consulta";//consulta combo destino                  
            String array_destino_uo[] = new String[1];
            array_destino_uo[0] = "";
            Vector datos_cbo_uo_destino = cn.EjecutarProcedurePostgres(cons_destino_uo, array_destino_uo); 
            String cb_uo_dest = util.contenido_combo(datos_cbo_uo_destino, "");
            request.setAttribute("cb_destino", cb_uo_dest);          
            
            if (perfil.equals(prfl_ventanilla)){
                String ntdoc = "sgd.fn_clasifdoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
                String array_cbo_tdoc[] = new String[1];
                array_cbo_tdoc[0] = "";
                Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
                String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, "");
                request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc); 
            }else{
                String ntdoc = "sgd.fn_clasifdoc_seriedoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
                String array_cbo_tdoc[] = new String[1];
                array_cbo_tdoc[0] = id_uo;
                Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
                String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, "");
                request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc); 
            }       
                                
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_expediente_nuevo_popup";
    }  
//FIN EXPEDIENTE NUEVO POPUP
//    
//INICIO LISTA DE DOCUMENTOS ADJUNTOS EN UN EXPEDIENTE    
@RequestMapping(value = {"/sgd/mant_lista_docs_cargar"}, method = RequestMethod.GET)
    public String MantListaDocsCargar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String idexp = request.getParameter("idexp");
        String codUser = request.getParameter("codUser");
        String perfil = request.getParameter("perfil");
        String operacion = request.getParameter("operacion");
                
        String var_request = "";
        String estado_mod = "0";
        String btn_ver = "";
        String btn_elim = "";
        String cuentadocs = "";
        String consulta = "1";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            
            String cons_cuentadocs = "sgd.fn_documentoscuenta_consulta";//cuenta los documentos de un expediente
            String array_cuentadocs[] = new String[1];
            array_cuentadocs[0] = idexp;
            Vector v_cuentadocs = cdb.EjecutarProcedurePostgres(cons_cuentadocs, array_cuentadocs);            
            for(int i = 0 ; i<v_cuentadocs.size() ; i++){
                Vector vss =  (Vector) v_cuentadocs.get(i);
                cuentadocs = vss.get(0).toString();//
            }
            
            String np_bandeja = "sgd.fn_exp_documentos_consulta";
            String array_bandeja[] = new String[3];
            array_bandeja[0] = idexp;
            array_bandeja[1] = codUser;
            array_bandeja[2] = perfil;
            Vector v_tbl_data_bandeja = cdb.EjecutarProcedurePostgres(np_bandeja, array_bandeja);

            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("ID");
            v_tbl_cab.add("UNID. ORG. ENVIA");
            v_tbl_cab.add("PERIODO");
            v_tbl_cab.add("ORIGEN");
            v_tbl_cab.add("DOCUMENTO");
            v_tbl_cab.add("FECHA ENVIO");
            v_tbl_cab.add("ASUNTO");
            v_tbl_cab.add("N° FOLIOS");
            
            if (operacion.equals(consulta)){                
                v_tbl_cab.add("VISUALIZAR");
            }else{
                v_tbl_cab.add("VER/MOD.");
                v_tbl_cab.add("ELIMINAR");
            }
            String btn_mod = "";             
            Util ut = new Util();
            Vector v_temp = new Vector();
        
            for(int k = 0 ; k<v_tbl_data_bandeja.size() ; k++){
                Vector vss =  (Vector) v_tbl_data_bandeja.get(k);
                String id_doc = vss.get(0).toString();//id del documento
                String des_uo = vss.get(1).toString();
                String per_exp = vss.get(2).toString();
                String orig_exp = vss.get(3).toString();
                String des_clsfdoc = vss.get(4).toString();
                String fec_doc = vss.get(5).toString();
                String asu_doc = vss.get(6).toString();
                String folio = vss.get(7).toString();
                String idn_doc = vss.get(8).toString();
                String dir = vss.get(9).toString();
                String us_reg = vss.get(10).toString(); //usuario que registra documento
                String est_doc = vss.get(11).toString();
                if (operacion.equals(consulta)){
                   btn_ver = "<div class='form-group dropdown text-center' >"
                                + "<button type='button' class='btn btn-info' id='ver' onclick='sgd_mant_adjuntos_cargar(\""+id_doc+"\"), sgd_mant_doc_detalle(\""+id_doc+"\")'>"
                                + "<span class='glyphicon glyphicon-search'>"
                                + "</span>"
                                + "</button>"
                                + "</div>";  
                }else{
//                    if (est_doc.equals(estado_mod) || us_reg.equals(codUser)){
                    if (us_reg.equals(codUser)){
                        btn_ver = "<div class='form-group dropdown text-center' >"
                                    + "<button type='button' class='btn btn-info' id='ver' onclick='sgd_mant_adjuntos_cargar(\""+id_doc+"\"), sgd_mant_doc_detalle(\""+id_doc+"\",\""+codUser+"\")'>"
                                    + "<span class='glyphicon glyphicon-pencil'>"
                                    + "</span>"
                                    + "</button>"
                                    + "</div>";  
                        if (Integer.parseInt(cuentadocs) > 1 && est_doc.equals(estado_mod)){//el botón eliminar se habilita sólo si el expediente tiene más de un documento y el documento aún no ha sido derivado                   
                            btn_elim = "<div class='form-group dropdown text-center' >"
                                        + "<button type='button' class='btn btn-info' id='ver' onclick='sgd_mant_documento_elim(\""+id_doc+"\")'>"
                                        + "<span class='glyphicon glyphicon-remove'>"
                                        + "</span>"
                                        + "</button>"
                                        + "</div>";                             
                        }else{
                            btn_elim = "<div class='form-group dropdown text-center' >"
                                    + "<button type='button' class='btn btn-info' id='ver' onclick='sgd_mant_documento_elim(\""+id_doc+"\")' disabled>"
                                    + "<span class='glyphicon glyphicon-remove'>"
                                    + "</span>"
                                    + "</button>"
                                    + "</div>"; 
                        }                                      
                    }else{
                        btn_ver = "<div class='form-group dropdown text-center' >"
                                    + "<button type='button' class='btn btn-info' id='ver' onclick='sgd_mant_adjuntos_cargar(\""+id_doc+"\"), sgd_mant_doc_detalle(\""+id_doc+"\")'>"
                                    + "<span class='glyphicon glyphicon-search'>"
                                    + "</span>"
                                    + "</button>"
                                    + "</div>";  
                        btn_elim = "<div class='form-group dropdown text-center' >"
                                    + "<button type='button' class='btn btn-info' id='ver' onclick='sgd_mant_documento_elim(\""+id_doc+"\")' disabled>"
                                    + "<span class='glyphicon glyphicon-remove'>"
                                    + "</span>"
                                    + "</button>"
                                    + "</div>"; 
                    }  
                }
                Vector vv = new Vector();
                vv.add(idn_doc);
                vv.add(des_uo);
                vv.add(per_exp);
                vv.add(orig_exp);
                vv.add(des_clsfdoc);
                vv.add(fec_doc);
                vv.add(asu_doc);
                vv.add(folio);
                vv.add(btn_ver);
                if (!operacion.equals(consulta)){
                    vv.add(btn_elim);
                }
                v_temp.add(vv);
            }
            String tbl_docs = ut.tbl(v_tbl_cab, v_temp);  
            request.setAttribute("response",tbl_docs);   
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "sgd/mant_lista_docs_cargar";
    }    
//FIN LISTA DE DOCUMENTOS ADJUNTOS EN UN EXPEDIENTE  
//        
//INICIO LISTA DE DERIVACIONES EN UN EXPEDIENTE  
@RequestMapping(value = {"/sgd/mant_lista_deriva_cargar"}, method = RequestMethod.GET)
    public String MantListaDerivaCargar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String idexp = request.getParameter("idexp");
//        String codUser = request.getParameter("codUser");
//        String perfil = request.getParameter("perfil");                
        String var_request = "";
        Util util = new Util();
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
             String np_deriva = "sgd.fn_exp_derivacion_consulta";
            String array_deriva[] = new String[2];
            array_deriva[0] = idexp;
            array_deriva[1] = "";
            Vector v_tbl_data_deriva = cdb.EjecutarProcedurePostgres(np_deriva, array_deriva);

            Vector v_tbl_cab_der =  new Vector();
    //        v_tbl_cab_der.add("ID");
    //        v_tbl_cab_der.add("DOCUMENTO");
            v_tbl_cab_der.add("UNID. ORG. ENVIA");
            v_tbl_cab_der.add("FECHA ENVIO");
            v_tbl_cab_der.add("OBSERVACIONES");
            v_tbl_cab_der.add("ESTADO");
            v_tbl_cab_der.add("DESTINO");
            v_tbl_cab_der.add("ENV.FISICO");

            Vector v_temp_der = new Vector();
            int k;
            for(k = 0 ; k<v_tbl_data_deriva.size() ; k++){
                Vector vss_d =  (Vector) v_tbl_data_deriva.get(k);
                String id_doc = vss_d.get(0).toString();
                String nro_doc = vss_d.get(1).toString();
                String obs_flj = vss_d.get(2).toString(); 
                String fec_envio = vss_d.get(3).toString();
                String des_uo = vss_d.get(4).toString();
                String c_fisico = vss_d.get(7).toString();
                String conf_fisico = vss_d.get(8).toString();
                String fis = "1";
                String conf_fis = "1";
                if (c_fisico.equals(fis)){
                    c_fisico = "PENDIENTE";
                }else{
                    c_fisico = "";
                }                
                if (conf_fisico.equals(conf_fis)){
                    c_fisico = "RECIBIDO";
                }
                Vector vv_d = new Vector();
                vv_d.add(id_doc);
                vv_d.add(nro_doc);
                vv_d.add(obs_flj);                
                vv_d.add(fec_envio);
                vv_d.add(des_uo);
                vv_d.add(c_fisico);
                v_temp_der.add(vv_d);                
                }                
//        String visible = "show";
        String tbl_deriva = util.tbl(v_tbl_cab_der, v_temp_der);              
        request.setAttribute("response",tbl_deriva);           
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "sgd/mant_lista_deriva_cargar";
    }   

    
@RequestMapping(value = {"/sgd/mant_expediente_guardar"}, method = RequestMethod.GET)
    public String MantExpedienteGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String per = request.getParameter("per");
        String orig = request.getParameter("orig");
        String tema = request.getParameter("tema");
        String proc = request.getParameter("proc");
        String alcan = request.getParameter("alcan");
        String userreg = request.getParameter("userreg");   
        String fecreg = request.getParameter("fecreg"); 
        
        String cond = request.getParameter("cond");
        String cutext = request.getParameter("cutext");
        
        String priori = request.getParameter("priori");
        String plazo = request.getParameter("plazo");
        
        String id_doc= request.getParameter("id_doc");
        String clsdoc = request.getParameter("clsdoc");
        String numd = request.getParameter("numd");
        String fecd = request.getParameter("fecd");
        String fecreg_doc = request.getParameter("fecreg_doc");
//        String asu_doc = request.getParameter("asu_doc");
//        String asu_doc = new String(request.getParameter("asu_doc").getBytes("ISO-8859-1"),"UTF-8");
        String asu_doc =  request.getParameter("asu_doc").trim();
        asu_doc = asu_doc.replaceAll("\"","'");
        asu_doc = asu_doc.replaceAll("\n", "");
        
        String folio = request.getParameter("folio");
        String uoreg_doc = request.getParameter("uoreg_doc");
        String prflreg_doc = request.getParameter("prflreg_doc");        
        String userreg_doc = request.getParameter("userreg_doc");
        String id_vt = request.getParameter("id_vt");
//        String obs = request.getParameter("obs");
//        String obs = new String(request.getParameter("obs").getBytes("ISO-8859-1"),"UTF-8");
        String obs =  request.getParameter("obs").trim();
        obs = obs.replace("\"","'");
        obs = obs.replace("\n", "");
        
        String idrem = request.getParameter("idrem");
        String iddes = request.getParameter("iddes");
//        String obsmodifica = request.getParameter("obsmodifica");
//        String obsmodifica = new String(request.getParameter("obsmodifica").getBytes("ISO-8859-1"),"UTF-8");
        String obsmodifica = request.getParameter("obs").trim();
        obsmodifica = obsmodifica.replace("\"","'");
        obsmodifica = obsmodifica.replace("\n", "");
        
        String var_request = "";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_expediente_mant";
            String array[] = new String[27];
            array[0] = id;
            array[1] = per;
            array[2] = orig;
            array[3] = tema;
            array[4] = proc;
            array[5] = alcan;
            array[6] = userreg;
            array[7] = fecreg;
            
            array[8] = cond;
            array[9] = cutext;
            
            array[10] = priori;
            array[11] = plazo;
            
            array[12] = id_doc;
            array[13] = clsdoc;
            array[14] = numd; 
            array[15] = fecd; 
            array[16] = fecreg_doc;
            array[17] = asu_doc;
            array[18] = folio;
            array[19] = uoreg_doc;
            array[20] = prflreg_doc;
            array[21] = userreg_doc;
            array[22] = obs;
            array[23] = idrem;
            array[24] = iddes;
            array[25] = id_vt;
            array[26] = obsmodifica;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_expediente_guardar";
    }
    
//INICIO DERIVAR EXPEDIENTE COMPLETO /////////////////////////////////////////////////////////////////////////////////////////////////////////    
    @RequestMapping(value = {"/sgd/mant_expediente_deriva_cut_popup"}, method = RequestMethod.GET)
    public String MantExpedienteDerivaCutPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        String des_pfl = request.getParameter("des_pfl");//nombre perfil de usuario
        request.setAttribute("title_pag","DERIVAR EXPEDIENTE - PERFIL " + des_pfl);
        
            String id = request.getParameter("id");
            String cut = request.getParameter("cut");
            String per = request.getParameter("per");
            String perfil = request.getParameter("perfil");
            String id_pers = request.getParameter("id_pers");
            String id_uo = request.getParameter("id_uo");
            String uo_pdr = request.getParameter("uo_pdr");
            String uo_pdr_pdr = request.getParameter("uo_pdr_pdr");
            String id_flujo = request.getParameter("id_flujo");
            String nivel = request.getParameter("nivel"); 
                
            String uo = "";
            Util util = new Util();   
            
        try {
            HttpSession session = request.getSession();
            String codUser = (String) session.getAttribute("codUser");
            
            ConeccionDB cn = new ConeccionDB();   
            
        //Lista de Documentos del Expediente 
        String np_bandeja = "sgd.fn_exp_documentos_consulta";
        String array_bandeja[] = new String[3];
        array_bandeja[0] = id;
        array_bandeja[1] = codUser;
        array_bandeja[2] = perfil;        
        Vector v_tbl_data_bandeja = cn.EjecutarProcedurePostgres(np_bandeja, array_bandeja);
        
        int j;
        String id_doc = "";
//        contar adjuntos en el documento
        for(j = 0 ; j<v_tbl_data_bandeja.size() ; j++){
            Vector vss =  (Vector) v_tbl_data_bandeja.get(j);
            String id_exp = vss.get(0).toString();//id del documento 
            id_doc += id_exp + ",";
        }  
        if (id_doc.length() != 0){
            id_doc = id_doc.substring(0, id_doc.length() - 1);
          
            String altdir = "2";
            String dir = "3";
            String prof = "4";
            String vent = "5";          
            
            if (perfil.equals(altdir) || perfil.equals(vent) || id_uo.equals("90000003") || id_uo.equals("90000044") || id_uo.equals("90000048") || id_uo.equals("90000052")){    
//                String np = "senamhi.fn_uo_flujo_consulta";
                String np = "senamhi.fn_altdir_destino_consulta";
                String array_acc[] = new String[1];
                array_acc[0] = codUser;
                Vector datos_tmp = cn.EjecutarProcedurePostgres(np, array_acc);
                Vector datos = new Vector();
                    for(int k=0; k<datos_tmp.size();k++){
                        Vector vt = (Vector) datos_tmp.get(k);                        
                        String c1 = vt.get(0).toString();
                        String c2 = vt.get(1).toString();
                        
                        Vector v = new Vector();
                        v.add(c1);
                        v.add(c2);
                        datos.add(v);                        
                    }                
                
                String cb_desc_uo = util.contenido_combo(datos, uo);
                request.setAttribute("cb_uo", cb_desc_uo);
            }              
            else if (perfil.equals(dir)){
//                String np = "senamhi.fn_uo_flujo_consulta";
                String np = "senamhi.fn_emis_recep_expediente_dirlinea";
                String array_acc[] = new String[1];
                array_acc[0] = codUser;
                Vector datos_tmp = cn.EjecutarProcedurePostgres(np, array_acc);
                Vector datos = new Vector();
                    for(int k=0; k<datos_tmp.size();k++){
                        Vector vt = (Vector) datos_tmp.get(k);                        
                        String c1 = vt.get(0).toString();
                        String c2 = vt.get(1).toString();
                        
                        Vector v = new Vector();
                        v.add(c1);
                        v.add(c2);
                        datos.add(v);                        
                    }                
                
                String cb_desc_uo = util.contenido_combo(datos, uo);
                request.setAttribute("cb_uo", cb_desc_uo);
            }  
            else if (perfil.equals(prof)){
                String np = "senamhi.fn_emis_recep_expediente_profes";
                String array_acc[] = new String[1];
                array_acc[0] = codUser;
                Vector datos = cn.EjecutarProcedurePostgres(np, array_acc);
                String cb_desc_uo = util.contenido_combo(datos, uo);
                request.setAttribute("cb_uo", cb_desc_uo);        
            }
            
            request.setAttribute("id",id);  
            request.setAttribute("cut",cut);      
            request.setAttribute("per",per);    
            
            request.setAttribute("codUser",codUser);    
            request.setAttribute("perfil",perfil);
            request.setAttribute("id_pers",id_pers);            
            request.setAttribute("id_uo",id_uo);
            request.setAttribute("uo_pdr",uo_pdr);
            request.setAttribute("uo_pdr_pdr",uo_pdr_pdr);   
            request.setAttribute("id_flujo",id_flujo); 
            request.setAttribute("id_doc",id_doc);  
         }else{
            String msje = "¡No puede derivar un expediente sin documentos adjuntos!";
            String alerta = "alert alert-danger";  
            request.setAttribute("msje",msje);  
            request.setAttribute("alerta",alerta);              
        }            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_expediente_deriva_cut_popup";
    }  
//FIN DERIVAR EXPEDIENTE COMPLETO /////////////////////////////////////////////////////////////////////////////////////////////////////////         
    
// INICIO DERIVACIONES PARA SELECCIONAR DOCUMENTOS///////////////////////////////////////////////////////////////////////////////////////////////////////// 
    @RequestMapping(value = {"/sgd/mant_expediente_deriva_popup"}, method = RequestMethod.GET)
    public String MantExpedienteDerivaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
            String id = request.getParameter("id");
            String cut = request.getParameter("cut");
            String per = request.getParameter("per"); 
            String perfil = request.getParameter("perfil");
            String id_pers = request.getParameter("id_pers");            
            String id_uo = request.getParameter("id_uo"); 
            String uo_pdr = request.getParameter("uo_pdr");
            String uo_pdr_pdr = request.getParameter("uo_pdr_pdr");
            String id_flujo = request.getParameter("id_flujo");           
            String nivel = request.getParameter("nivel"); 
            
            String uo = "";
            Util util = new Util();
            
        request.setAttribute("title_pag","Derivar Expediente"); 
        
        try {
            HttpSession session = request.getSession();
            String codUser = (String) session.getAttribute("codUser");
            
            ConeccionDB cn = new ConeccionDB(); 

//Lista de Documentos del Expediente 
        String np_bandeja = "sgd.fn_exp_documentos_consulta";
        String array_bandeja[] = new String[3];
        array_bandeja[0] = id;
        array_bandeja[1] = codUser;
        array_bandeja[2] = perfil;
        Vector v_tbl_data_bandeja = cn.EjecutarProcedurePostgres(np_bandeja, array_bandeja);

        Vector v_tbl_cab =  new Vector();
        v_tbl_cab.add("-");
        v_tbl_cab.add("ID");
        v_tbl_cab.add("UNID. ORG. ENVIA");
        v_tbl_cab.add("PERIODO");
        v_tbl_cab.add("ORIGEN");
        v_tbl_cab.add("DOCUMENTO");
        v_tbl_cab.add("ASUNTO");
        v_tbl_cab.add("N° FOLIOS");

        Util ut = new Util();
        Vector v_temp = new Vector();
        int j;
        for(j = 0 ; j<v_tbl_data_bandeja.size() ; j++){
            Vector vss =  (Vector) v_tbl_data_bandeja.get(j);
            String id_exp = vss.get(0).toString();//id del documento
            String des_uo = vss.get(1).toString();
            String per_exp = vss.get(2).toString();
            String orig_exp = vss.get(3).toString();
            String des_clsfdoc = vss.get(4).toString();
            String asu_doc = vss.get(6).toString();
            String folio = vss.get(7).toString();
            
            String chkb = "<input type='checkbox' value='"+id_exp+"' class='cb_doc_exp' id='cb_doc_"+id_exp+"'/>"
                        + "<label for='cb_doc_"+id_exp+"'></label>";
            
            Vector vv = new Vector();
            vv.add(chkb);
            vv.add(id_exp);
            vv.add(des_uo);
            vv.add(per_exp);
            vv.add(orig_exp);
            vv.add(des_clsfdoc);           
            vv.add(asu_doc);
            vv.add(folio);
            v_temp.add(vv);                
        }
        String tbl_docs = ut.tbl(v_tbl_cab, v_temp);     
        //tab_exp_content = "<div class='tab-pane waves-effect waves-light' id='lista_doc'>"+tbl_docs+"</div>";
        request.setAttribute("tab_exp_content",tbl_docs);
        
        String np = "senamhi.fn_altdir_destino_consulta";
        String array_acc[] = new String[1];
        array_acc[0] = "";
        Vector datos = cn.EjecutarProcedurePostgres(np, array_acc);
        String cb_desc_uo = util.contenido_combo(datos, "");
        request.setAttribute("cb_uo", cb_desc_uo);

        request.setAttribute("id",id);  
        request.setAttribute("cut",cut);      
        request.setAttribute("per",per);    

        request.setAttribute("codUser",codUser);    
        request.setAttribute("perfil",perfil);
        request.setAttribute("id_pers",id_pers);            
        request.setAttribute("id_uo",id_uo);
        request.setAttribute("uo_pdr",uo_pdr);
        request.setAttribute("uo_pdr_pdr",uo_pdr_pdr);   
        request.setAttribute("id_flujo",id_flujo); 
        request.setAttribute("nivel",nivel); 
            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_expediente_deriva_popup";
    }  
//FIN DERIVACIONES PARA SELECCIONAR DOCUMENTOS/////////////////////////////////////////////////////////////////////////////////////////////////////////     
//    
//INICIO DE DERIVACIÓN GUARDAR
@RequestMapping(value = {"/sgd/mant_deriva_guardar"}, method = RequestMethod.GET)
    public String MantDerivaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String codUser = request.getParameter("codUser");
        String hd_perfil = request.getParameter("perfil");
        String id_uo = request.getParameter("id_uo");
        String cb_destino = request.getParameter("cd_fin");  
        String id_doc = request.getParameter("id_doc");  
        String id_flujo_ant = request.getParameter("id_flujo_ant");
        String copia = request.getParameter("copia");
//        String fisico = request.getParameter("fisico");
        String fisico = request.getParameter("cb_fisico");
        String acc_rpta = request.getParameter("rpta_r"); //acción respuesta
        String acc_conoc = request.getParameter("rpta_c"); //acción conocimiento
        String acc_accion = request.getParameter("rpta_a"); //acción 
//        String observa = request.getParameter("observa"); //acción conocimiento
//        String observa = java.net.URLEncoder.encode(request.getParameter("observa"), "UTF-8").replace("+", "%20");
//        String observa = new String(request.getParameter("observa").getBytes("ISO-8859-1"),"UTF-8");
//        String observa = request.getParameter("observa").replace("\"", "'").trim();
        String observa = request.getParameter("observa").trim();
//        observa = request.getParameter("observa").replace("%20", " ").trim();        
        observa = observa.replaceAll("\"", "'");
        observa = observa.replaceAll("\n", "");
//        observa = observa.replace("\n", "");
        
        String[] array_fisico = fisico.split(",");
        String[] array_acc_rpta = acc_rpta.split(",");
        String[] array_acc_conoc = acc_conoc.split(",");
        String[] array_acc_accion = acc_accion.split(",");
        
//        cb_destino = cb_destino.substring(0, cb_destino.length() - 1);
        String[] array_destino = cb_destino.split(",");
        
        String accion = "";
        String c_fisico = "";
        
        String c_persona = "";
        String c_perfil = "";
        String c_usuario = "";
        String c_unidorg = "";
        String c_acciones = "";
        
        String cod_persona = "";
        String destino_rpta = "";
        String destino_conoc = "";
        String destino_accion = "";
        String destino_fisico = "";
        
        for (int x = 0; x<array_destino.length; x++){   
            cod_persona = array_destino[x];
            String[] array_cod_persona = cod_persona.split("_");
            c_persona = array_cod_persona [0]; 
            
            for (int y = 0; y<array_acc_rpta.length; y++){ 
                String c_per_rpta = array_acc_rpta[y];
                if(c_persona.equals(c_per_rpta)){
                    String cad_destino_rpta = "";
                    cad_destino_rpta += cod_persona + "_1,";     
                    destino_rpta += cad_destino_rpta; 
                    }                
            }            
            for (int y = 0; y<array_acc_conoc.length; y++){
                String c_per_conoc = array_acc_conoc[y];    
                if(c_persona.equals(c_per_conoc)){
                    String cad_destino_conoc = "";
                    cad_destino_conoc += cod_persona + "_2,";     
                    destino_conoc += cad_destino_conoc;
                }
            }
            for (int y = 0; y<array_acc_accion.length; y++){
                String c_per_accion = array_acc_accion[y];    
                if(c_persona.equals(c_per_accion)){
                    String cad_destino_accion = "";
                    cad_destino_accion += cod_persona + "_4,";     
                    destino_accion += cad_destino_accion;
                }
            }
        }
        String destino = destino_rpta + destino_conoc + destino_accion;
        String[] array_dest_fis = destino.split(",");  
        String cod_pers = "";
        String c_pers_fis = "";        
        for (int x = 0; x<array_dest_fis.length; x++){ 
            cod_pers = array_dest_fis[x];
            String[] array_cod_persona = cod_pers.split("_");
            c_pers_fis = array_cod_persona [0];             
            for (int y = 0; y<array_fisico.length; y++){
                String c_fis = array_fisico[y];
                if(c_pers_fis.equals(c_fis)){
                    String cad_fisico = "";
                    cad_fisico += cod_pers + "_1,";
                    destino_fisico += cad_fisico;
                }else{
                    String cad_fisico = "";
                    cad_fisico += cod_pers + "_0,";
                    destino_fisico += cad_fisico;
                }
            }      
        }
        destino_fisico  = destino_fisico.substring(0, destino_fisico.length() - 1);
        String c_pers = "";
        String cod_pers_fis = "";        
        String[] array_dest_fisico = destino_fisico.split(","); 
        for (int x = 0; x<array_dest_fisico.length; x++){ 
            cod_pers_fis = array_dest_fisico[x];
            String[] array_cod_persona = cod_pers_fis.split("_");            
            c_pers += array_cod_persona [0] + ","; 
            c_perfil += array_cod_persona [1] + ","; 
            c_usuario += array_cod_persona [2] + ",";
            c_unidorg += array_cod_persona [3] + ",";
            c_acciones += array_cod_persona [4] + ",";     
            c_fisico += array_cod_persona [5] + ",";     
        }        
        
        c_pers = c_pers.substring(0, c_pers.length() - 1);
        c_perfil = c_perfil.substring(0, c_perfil.length() - 1);
        c_usuario = c_usuario.substring(0, c_usuario.length() - 1);
        c_unidorg = c_unidorg.substring(0, c_unidorg.length() - 1);  
        c_acciones = c_acciones.substring(0, c_acciones.length() - 1);              
        c_fisico = c_fisico.substring(0, c_fisico.length() - 1);              
                
        String id_tpestflj = "";
        String tpflj_ant = "";
        String accion_ant = "";
        if (copia == "") {
            id_tpestflj = "2";
            tpflj_ant = "1";
            accion_ant = "3";
        }else{
            id_tpestflj = "1";
            tpflj_ant = "2";
            accion_ant = "2";
        }
        String tpflj = "2";   
        accion = c_acciones;
        String tpest_flj = "1";   
        
        Date date = new Date();
        DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
        String fecha = formatofec.format(date);
        
        String var_request = "";        
        try {
            ConeccionDB cdb = new ConeccionDB();
//            String np = "sgd.fn_derivar_mant";
            String np = "sgd.fn_flujo_mant";            
            String array[] = new String[19];
            array[0] = id;
            array[1] = tpflj;
            array[2] = accion;
            array[3] = codUser;
            array[4] = hd_perfil;
            array[5] = id_uo;
            array[6] = c_pers;
            array[7] = id_doc;
            array[8] = c_perfil;
            array[9] = c_usuario;
            array[10] = c_unidorg;
            array[11] = tpest_flj;
            array[12] = id_flujo_ant; 
            array[13] = id_tpestflj; 
            array[14] = fecha;
            array[15] = tpflj_ant;
            array[16] = c_fisico;
            array[17] = observa;
            array[18] = accion_ant;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);            
            var_request = new Util().vector2json(datos);
        } 
        catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_deriva_guardar";    
    }
//FIN DE DERIVACIÓN GUARDAR    
//
//INICIO SUBIR DOCUMENTOS ----------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = {"/sgd/uploadfile"}, method = RequestMethod.POST)
    public String uploadfile(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
        ConeccionDB cdb = new ConeccionDB();
        
//        String UPLOAD_DIRECTORY = "/opt/glassfish4/glassfish/domains/domain1/applications/files/sgd"; 
        String UPLOAD_DIRECTORY = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/sgd"; 
        String msj = "";
                
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                
                if (isMultipart) {
                    // Create a factory for disk-based file items
                    FileItemFactory factory = new DiskFileItemFactory();
                    
                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    
                    try {
                        // Parse the request                        
                        List items = upload.parseRequest(request);
                        Iterator iterator = items.iterator();
                        while (iterator.hasNext()) {
                            FileItem item = (FileItem) iterator.next();
                            if (!item.isFormField()) {
                                String fileName = item.getName();  
                                String field = item.getFieldName();
                                    StringTokenizer str =  new StringTokenizer(field,"|");
                                    String anio = str.nextToken();
                                    String nrocut = str.nextToken();
                                    String iddoc = str.nextToken();
                                    String in = str.nextToken();
                                    String dir = ""+anio+"_"+nrocut+"_"+iddoc+"";
                                    dir = DigestUtils.md5Hex(dir);                               
                                
                                fileName = DigestUtils.md5Hex(fileName);
                                File path = new File(UPLOAD_DIRECTORY+"/"+anio+"/"+"/"+dir);
                                if (!path.exists()) {
                                    boolean status = path.mkdirs();
                                }
                                
                                File uploadedFile = new File(path +"/"+ fileName+".pdf"); 
                                msj += uploadedFile.getAbsolutePath()+"";
                                item.write(uploadedFile);
                                
                                String np = "sgd.fn_docadjunto_mant_insert";            
                                String array[] = new String[3];
                                array[0] = iddoc;
                                array[1] = fileName+".pdf";
                                array[2] = dir;
                                
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
//        request.setAttribute("request",msj);

        return "sgd/uploadfile";
    }     
//FIN SUBIR DOCUMENTOS ----------------------------------------------------------------------------------------------------------------    
//    
//INICIO CARGAR PRIORIDAD TXT    
//**************************************************************************************************************************
    @RequestMapping(value = {"/sgd/mant_prioridad_cargar_txt"}, method = RequestMethod.GET)
    public String MantPrioridadCargarTxt(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
//            request.setAttribute("title_pag","NUEVO PROCEDIMIENTO"); 
        String var_request = "";
        String i_plazo = "";
        
        try {
            String id_priori = request.getParameter("id_priori"); 
            
            ConeccionDB cn = new ConeccionDB();   
            
            String np = "sgd.fn_prioridad_consulta";
            String array[] = new String[1];
            array[0] = id_priori;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);         
            
            for(int i = 0 ; i<datos.size() ; i++){
                Vector datos_v =  (Vector) datos.get(i);
                i_plazo = datos_v.get(2).toString();
//                c_est_reg = datos_v.get(3).toString();
            }            
            request.setAttribute("response", i_plazo);
        
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "sgd/mant_prioridad_cargar_txt";
    }
//**************************************************************************************************************************
//FIN CARGAR PRIORIDAD TXT       
//
//    DOCUMENTOS DETALLE    
    @RequestMapping(value = {"/sgd/mant_doc_detalle"}, method = RequestMethod.GET)
    public String MantDocDetalle(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String id_doc = request.getParameter("id_doc"); 
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB();   
            Util util =  new Util();
                                   
            String np = "sgd.fn_documento_consulta";
            String array[] = new String[1];
            array[0] = id_doc;// id del documento
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
                        
            var_request = new Util().vector2json(datos); 
            
            String obj_disabled_form = "enabled";
            String obj_readonly_form = "";
            request.setAttribute("obj_disabled_form", obj_disabled_form);  
            request.setAttribute("obj_readonly_form", obj_readonly_form);  
                   
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        return "sgd/mant_doc_detalle";
    }     
//    FIN DOCUMENTOS DETALLE

//INICIO CARGAR TEXT DESDE COMBO DESTINO - EXPEDIENTE 
@RequestMapping(value = {"/sgd/mant_destino_cargar_txt"}, method = RequestMethod.GET)
    public String MantRemiteCargarTxt(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String id_dest = request.getParameter("id_dest"); 
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB(); 
            String np = "senamhi.fn_destino_consulta";
            String array[] = new String[1];
            array[0] = id_dest;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);                        
            var_request = new Util().vector2json(datos);                
                   
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        
        return "sgd/mant_destino_cargar_txt";
    }         
//FIN CARGAR TEXT DESDE COMBO DESTINO - EXPEDIENTE
//    
//    LISTA ADJUNTOS
    @RequestMapping(value = {"/sgd/mant_adjunto_cargar"}, method = RequestMethod.GET)
    public String MantAdjuntoCargar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {        
        try {
            String id_doc = request.getParameter("id_doc"); 
            ConeccionDB cn = new ConeccionDB();   
                        
            String np = "sgd.fn_docadjunto_consulta";
            String array[] = new String[1];
            array[0] = id_doc;
            Vector datos = cn.EjecutarProcedurePostgres(np, array); 
            
            Vector v_tbl_cab_adj =  new Vector();
//            v_tbl_cab_adj.add("ID");
            v_tbl_cab_adj.add("Adjuntos");
            v_tbl_cab_adj.add("_");
//            v_tbl_cab_adj.add("Eliminar");
            v_tbl_cab_adj.add("");
            
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
                String anio = datos_v.get(4).toString();    
                String cargo = datos_v.get(7).toString();    
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
                    v_adj.add("Doc_"+ d);
                if (cargo.equals("1")){
                    v_adj.add("Cargo");
                }else{
                    v_adj.add("");
                }                
//                v_adj.add(nom_doc);
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
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "sgd/mant_adjunto_cargar";
    }     
//   FIN LISTA ADJUNTOS
//    
//    DOCUMENTOS DETALLE    
    @RequestMapping(value = {"/sgd/mant_docadj_elim"}, method = RequestMethod.GET)
    public String MantDocAdjElim(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {  
            String var_request = "";
        try {
            String id_doc = request.getParameter("id_doc"); 
            String nom_doc = request.getParameter("nom_doc"); 
            ConeccionDB cn = new ConeccionDB();   
                        
            String np = "sgd.fn_docadjunto_mant_elim";
            String array[] = new String[2];
            array[0] = id_doc;
            array[1] = nom_doc;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);  
            var_request = new Util().vector2json(datos);    
                                            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        request.setAttribute("request",var_request);
        
        return "sgd/mant_docadj_elim";
    }     
//    FIN DOCUMENTOS DETALLE    
    
//    ELIMINAR DOCUMENTOS     
    @RequestMapping(value = {"/sgd/mant_documento_elim"}, method = RequestMethod.GET)
    public String MantDocumentoElim(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {  
            String var_request = "";
        try {
            String id_doc = request.getParameter("id_doc"); 
            ConeccionDB cn = new ConeccionDB();   
                        
            String np = "sgd.fn_documento_mant_elim";
            String array[] = new String[1];
            array[0] = id_doc;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);  
            var_request = new Util().vector2json(datos);    
                                            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        request.setAttribute("request",var_request);
        
        return "sgd/mant_documento_elim";
    }     
//    FIN ELIMINAR DOCUMENTOS
    
// CONFIRMA RECEPCIÓN DE FISICO
@RequestMapping(value = {"/sgd/mant_fisico_popup"}, method = RequestMethod.GET)
    public String MantFisicoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","CONFIRMAR FISICO"); 
        try {
            String id_doc = request.getParameter("id_doc");          
            String id_flujo = request.getParameter("id_flujo");  
            
            Date date = new Date();
            DateFormat formatofec_acuse = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String fecha_acuse = formatofec_acuse.format(date);
          
            request.setAttribute("id_doc", id_doc);
            request.setAttribute("id_flujo", id_flujo); 
            request.setAttribute("fecha_acuse", fecha_acuse);           
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_fisico_popup";
    }
// FIN CONFIRMA RECEPCIÓN DE FISICO 
       
// GUARDAR CONFIRMA RECEPCIÓN DE FISICO   
@RequestMapping(value = {"/sgd/mant_fisico_guardar"}, method = RequestMethod.GET)
    public String MantFisicoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id_doc = request.getParameter("id_doc");
        String id_flujo = request.getParameter("id_flujo");
        String fec_fisico = request.getParameter("fecfisico");
        String observacion = request.getParameter("observacion");

        String var_request = "";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_fisico_mant";
            String array[] = new String[4];
            array[0] = id_doc;
            array[1] = id_flujo;
            array[2] = fec_fisico;
            array[3] = observacion;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_fisico_guardar";
    }
// FIN DE GUARDAR CONFIRMA RECEPCIÓN DE FISICO   
    
//CONFIRMA ARCHIVO DE DERIVACIÓN (FLUJO)
@RequestMapping(value = {"/sgd/mant_archiva_flujo_popup"}, method = RequestMethod.GET)
    public String MantAchivaFlujoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","CONFIRMAR ARCHIVAMIENTO"); 
        try {         
            String id_flujo = request.getParameter("id_flujo");
            String i_cut = request.getParameter("i_cut");
            String i_per_exp = request.getParameter("i_per_exp");
            String c_des_persona = request.getParameter("c_des_persona");
            String c_des_uo = request.getParameter("c_des_uo");            
            
            Date date = new Date();
            DateFormat formatofec_archi = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String fecha_archi = formatofec_archi.format(date);
          
            request.setAttribute("id_flujo", id_flujo); 
            request.setAttribute("fecha_archi", fecha_archi);           
            request.setAttribute("i_cut", i_cut);           
            request.setAttribute("i_per_exp", i_per_exp);           
            request.setAttribute("c_des_persona", c_des_persona);           
            request.setAttribute("c_des_uo", c_des_uo);           
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_archiva_flujo_popup";
    }
//FIN ARCHIVO DE DERIVACIÓN (FLUJO)    
    
//GUARDAR ARCHIVO DE DERIVACIÓN (FLUJO)
@RequestMapping(value = {"/sgd/mant_archiva_flujo_guardar"}, method = RequestMethod.GET)
    public String MantArchivaFlujoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id_flujo = request.getParameter("id_flujo");
        String fecarchiva = request.getParameter("fecarchiva");
        String obs = request.getParameter("obs");  
//        String obs = new String(request.getParameter("obs").getBytes("ISO-8859-1"),"UTF-8");

        String var_request = "";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_archiva_flujo_mant";
            String array[] = new String[3];
            array[0] = id_flujo;
            array[1] = fecarchiva;
            array[2] = obs;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_archiva_flujo_guardar";
    }
        
//FIN DE GUARDAR ARCHIVO DE DERIVACIÓN (FLUJO)    
//    
// CONFIRMA DERIVACIÓN ACCIÓN
@RequestMapping(value = {"/sgd/mant_expediente_deriva_accion_popup"}, method = RequestMethod.GET)
    public String MantDerivaAccionPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","ACCIÓN PARA DERIVACIÓN"); 
        try {
            String id_doc = request.getParameter("id_doc");   
            String cd_fin = request.getParameter("cd_fin");  
            String cd_fin_pers = request.getParameter("cd_fin_pers");              
//            String observa = request.getParameter("observa").trim();    
            
            String observa = new String(request.getParameter("observa").getBytes("ISO-8859-1"),"UTF-8").replace("\"", "'").trim();
//            observa = observa.replace("\"", "'");
            observa = observa.replace("\n", "");
            
//            Fecha de derivación 
            Date date = new Date();
            DateFormat formatofec = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String fecha = formatofec.format(date);
            
            request.setAttribute("id_doc", id_doc);
            request.setAttribute("observa", observa);           
            
            StringTokenizer st = new StringTokenizer(cd_fin, "_,");
            String cod_persona = "";
            String perfil = "";
            String usuario = "";
            String des_uo = "";
            int cta = 0;
            while(st.hasMoreTokens()) {
    //            código de la persona
                cod_persona += st.nextToken() + ",";
    //            código de perfil
                perfil += st.nextToken() + ","; 
    //            código de usuario
                usuario += st.nextToken() + ",";
    //            código de unidad orgánica
                des_uo += st.nextToken() + ",";
                cta = cta + 1;
                }
                        
            cod_persona = cod_persona.substring(0, cod_persona.length() - 1);
            perfil = perfil.substring(0, perfil.length() - 1);
            usuario = usuario.substring(0, usuario.length() - 1);
            des_uo = des_uo.substring(0, des_uo.length() - 1);
                         
            String[] persona = cod_persona.split(","); 
            String[] cod_perfil = perfil.split(","); 
           
            
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "senamhi.fn_destino_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector datos_pers = cdb.EjecutarProcedurePostgres(np, array);
            
            String tabla = "<table class='table table-bordered table-hover'>"
                            + "<tr>"
                            + "<th>Adj.Físico</th>"
                            + "<th>Persona</th>"
                            + "<th>Para Respuesta</th>"
                            + "<th>Para Conocimiento</th>"
                            + "<th>Tomar Acción</th>"
                            + "</tr>";
        
            for (int x = 0; x<persona.length; x++){
                
                for(int y=0; y<datos_pers.size(); y++){
                    
                    Vector datos_pers_sub =  (Vector) datos_pers.get(y);
                    
                    String cod_pers_db = datos_pers_sub.get(0).toString();
                    String nom_pers_db = datos_pers_sub.get(1).toString();
                    String perf_pers_db = datos_pers_sub.get(4).toString();
                    
                    if(cod_pers_db.equals(persona[x]) && perf_pers_db.equals(cod_perfil[x])){
                         tabla += "<tr>"
                                 + "<td>"
                                 + "<input type='checkbox' value='"+cod_pers_db+"' class='cb_fisico' id='cb_fisico_"+cod_pers_db+"'/>"
                                 + "<label for='cb_fisico_"+cod_pers_db+"'></label>"
                                 + "</td>"
                                 + "<td>"+nom_pers_db+"</td>"
                                 + "<td><input name='group_"+cod_pers_db+"' cod='"+cod_pers_db+"' class='rd_accion_r' type='radio' id='radio_"+cod_pers_db+"_r' checked=''><label for='radio_"+cod_pers_db+"_r'></label></td>"                                 
                                 + "<td><input name='group_"+cod_pers_db+"' cod='"+cod_pers_db+"' class='rd_accion_c' type='radio' id='radio_"+cod_pers_db+"_c' checked=''><label for='radio_"+cod_pers_db+"_c'></label></td>"
                                 + "<td><input name='group_"+cod_pers_db+"' cod='"+cod_pers_db+"' class='rd_accion_a' type='radio' id='radio_"+cod_pers_db+"_a' checked=''><label for='radio_"+cod_pers_db+"_a'></label></td>"
                                 + "</tr>";
                    }
                }
            }
//            String chkb = "<input type='checkbox' value='"+id_exp+"' class='cb_doc_exp' id='cb_doc_"+id_exp+"'/><label for='cb_doc_"+id_exp+"'></label>";
            
            tabla += "</table>";
                            
         request.setAttribute("tab_table",tabla);                    
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return "sgd/mant_expediente_deriva_accion_popup";
    }
// FIN CONFIRMA DERIVACIÓN ACCIÓN  
//    
//INICIO CONSULTA DERIVACIÓN (FLUJO) ARCHIVADA POPUP
@RequestMapping(value = {"/sgd/mant_archiva_desarch_flujo_popup"}, method = RequestMethod.GET)
    public String MantArchivaDesarchFlujoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","DESARCHIVAR DERIVACIÓN"); 
            HttpSession session = request.getSession();
            String codUser = (String) session.getAttribute("codUser");
            
        try {                    
            String c_per = "";
            ConeccionDB cn = new ConeccionDB();   
            
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            
            String cb_periodo = util.contenido_combo(datos_cbo_per, c_per);
            request.setAttribute("cb_periodo", cb_periodo);          
            request.setAttribute("codUser", codUser);   
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("cb_estado", ex.getMessage());  
        }
        return "sgd/mant_archiva_desarch_flujo_popup";
    }       
//FIN CONSULTA DERIVACIÓN (FLUJO) ARCHIVADA POPUP  
//        
//INICIO CONSULTA DERIVACIÓN (FLUJO) ARCHIVADA TABLA
  @RequestMapping(value = {"/sgd/mant_exp_archivados_consulta"}, method = RequestMethod.GET)
	public String AjaxQueryArchivadosConsulta(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();   
            int i;
            String p_cut = request.getParameter("id_cut");
            String p_periodo = request.getParameter("periodo");
            String p_codUser = request.getParameter("codUser");            

            String np = "sgd.fn_exp_archivados_consulta";
            String array[] = new String[3];
            array[0] = p_cut;
            array[1] = p_periodo; 
            array[2] = p_codUser; 
            
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_exp = vss.get(0).toString();
                String i_cut_exp = vss.get(1).toString();
                String d_fec_reg = vss.get(2).toString();
                String i_id_flujo = vss.get(3).toString();
                String d_fec_archivo = vss.get(4).toString();
                String c_obs_archivo = vss.get(5).toString();
                String i_id_prflrec = vss.get(6).toString();
                
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_desarchivar(\""+i_id_flujo+"\")'><span class='glyphicon glyphicon-edit'></span></button>";
                
                Vector vv = new Vector();
                vv.add(i_cut_exp);
                vv.add(d_fec_reg);
                vv.add(d_fec_archivo);
                vv.add(c_obs_archivo);
                vv.add(i_id_prflrec);
                vv.add(btn);
                v_temp.add(vv); 
            }     
//            Vector v_tbl_cab =  new Vector();
//            v_tbl_cab.add("N° CUT");
//            v_tbl_cab.add("FECHA REGISTRO");
//            v_tbl_cab.add("FECHA ARCHIVO");
//            v_tbl_cab.add("OBSERVACIONES");
//            v_tbl_cab.add("DESARCHIVAR");
//            
//            Util u = new Util();
//            String tbl =  u.tbl(v_tbl_cab, v_temp);
//            if (i == 0 ){
//                tbl = "¡No se encontraron resultados!";
//            }            
//            request.setAttribute("response",tbl);
        
            Util util = new Util();
            String json = util.vector2json_tbl(v_temp);   
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
            sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aoColumns");sv.add("[" 
                                    + "{'sTitle':'N° CUT'} , "
                                    + "{'sTitle':'FECHA REG.CUT'} , "
                                    + "{'sTitle':'FECHA ARCHIVO'} , "
                                    + "{'sTitle':'OBSERVACIONES'} , "
                                    + "{'sTitle':'DESDE PERFIL'} , "
                                    + "{'sTitle':'DESARCHIVAR'} "

                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
        //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
        //    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
            ///////////////////////////////////////////////////////

            String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
            String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
//    request.setAttribute("response", tbl_html + tbl); 
    
            request.setAttribute("response", tbl_html + tbl);
            
            if (i == 0 ){
                tbl = "¡No se encontraron resultados!";
            }    
            return "sgd/mant_exp_archivados_consulta";
	}    
//FIN CONSULTA DERIVACIÓN (FLUJO) ARCHIVADA TABLA
//        
//GUARDAR DESARCHIVAR DERIVACIÓN (FLUJO)
@RequestMapping(value = {"/sgd/mant_exp_desarchiva_guardar"}, method = RequestMethod.GET)
    public String MantExpArchivaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id_flujo = request.getParameter("id_flujo");

        String var_request = "";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_desarchiva_flujo_mant";
            String array[] = new String[1];
            array[0] = id_flujo;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);            
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_exp_desarchiva_guardar";
    }
        
//FIN DE GUARDAR ARCHIVO DE DERIVACIÓN (FLUJO)        
    
//INICIO RECHAZAR DERIVACIÓN (FLUJO) POPUP
@RequestMapping(value = {"/sgd/mant_rechaza_flujo_popup"}, method = RequestMethod.GET)
    public String MantRechazaFlujoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","RECHAZAR DERIVACIÓN"); 
            HttpSession session = request.getSession();
            String codUser = (String) session.getAttribute("codUser");
            
            String id_cut = request.getParameter("id_cut");
            String id_flujo = request.getParameter("id_flujo");
            String persona = request.getParameter("persona");
            String unid_org = request.getParameter("unid_org");
        try {               
            Date date = new Date();
            DateFormat formatofec_rechazo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String fecha_rechaza = formatofec_rechazo.format(date);

            request.setAttribute("id_cut", id_cut);
            request.setAttribute("id_flujo", id_flujo);
            request.setAttribute("fecha_rechaza", fecha_rechaza);
            request.setAttribute("persona", persona);
            request.setAttribute("unid_org", unid_org);
            

        } catch (Exception ex) {
            
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "sgd/mant_rechaza_flujo_popup";
    }       
//FIN RECHAZAR DERIVACIÓN (FLUJO) POPUP
//           
//GUARDAR RECHAZAR DERIVACIÓN (FLUJO)
@RequestMapping(value = {"/sgd/mant_exp_rechaza_guardar"}, method = RequestMethod.GET)
    public String MantExpRechazaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id_flujo = request.getParameter("id_flujo");

        String var_request = "";
        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_rechaza_flujo_mant";
            String array[] = new String[1];
            array[0] = id_flujo;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_exp_rechaza_guardar";
    }        
//FIN DE RECHAZAR DERIVACIÓN (FLUJO)  
//
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL    
@RequestMapping(value = {"/sgd/mant_archcentral"}, method = RequestMethod.GET)
    public String MantArchCentral(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","TRANSFERENCIA DOCUMENTAL AL ARCHIVO CENTRAL");
//        request.setAttribute("tit_btn","ENVIAR A ARCHIVO"); 
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
                
        return "sgd/mant_archcentral";        
    }
//FIN ENVIO DOCUMENTOS ARCHIVO CENTRAL
//    
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL TABLA
    @RequestMapping(value = {"/sgd/mant_archcentral_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryArchcentral(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
//        sesión de usuario
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        String unid_org = "";
            ConeccionDB cn =  new ConeccionDB();
            String np_uo = "senamhi.fn_uo_useremisor_consulta";
            String array_uo[] = new String[1];
            array_uo[0] = codUser;
            Vector v_datos_uo = cn.EjecutarProcedurePostgres(np_uo, array_uo);
            for(int i = 0 ; i<v_datos_uo.size() ; i++){
                Vector vss =  (Vector) v_datos_uo.get(i);
                unid_org = vss.get(1).toString();
            }
            request.setAttribute("unid_org",unid_org); 
            request.setAttribute("codUser",codUser);             
            
            String np = "sgd.fn_exp_archcentral_consulta";
            String array[] = new String[2];
            array[0] = "";
            array[1] = codUser;
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String id_exp = vss.get(0).toString();
                String cut_exp = vss.get(1).toString();
                String per_exp = vss.get(2).toString();
                String fec_reg = vss.get(3).toString();
                String id_alcan = vss.get(4).toString();
                String id_tema = vss.get(5).toString();
                String id_cond = vss.get(6).toString();
                String des_uo = vss.get(8).toString();
                               
                String chkb = "<input type='checkbox' value='"+id_exp+"' class='cb_exp' id='cb_"+id_exp+"'/>"
                        + "<label for='cb_"+id_exp+"'></label>";                    
//                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_expediente_popup('"+id_exp+"','1','1','1','0')'>"
//                        + "<span class='glyphicon glyphicon-search'>"
//                        + "</span>"
//                        + "</button>";
                Vector vv = new Vector();
                vv.add(chkb);                
                vv.add(cut_exp);
                vv.add(per_exp);
                vv.add(fec_reg);
                vv.add(id_alcan);
                vv.add(id_tema);
                vv.add(id_cond);
                vv.add(des_uo);
//                vv.add(btn);
                v_temp.add(vv);                
            }     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("SELECCIONE");
            v_tbl_cab.add("N° EXPEDIENTE");
            v_tbl_cab.add("PERIODO");
            v_tbl_cab.add("FECHA");
            v_tbl_cab.add("ALCANCE");
            v_tbl_cab.add("TEMA");
            v_tbl_cab.add("CONDICIÓN");
            v_tbl_cab.add("UNIDAD ORGÁNICA");
//            v_tbl_cab.add("CONSULTAR");            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
            return "sgd/mant_archcentral_tbl";
	}    
//FIN ENVIO DOCUMENTOS ARCHIVO CENTRAL TABLA
//        
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL POPUP    
    @RequestMapping(value = {"/sgd/mant_archcentral_envia_popup"}, method = RequestMethod.GET)
        public String MantArchcentralEnviaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","ENVIAR AL ARCHIVO CENTRAL");            
        try {
            String id_exp = request.getParameter("id_exp");          
            String unid_org = request.getParameter("unid_org");          
            String coduser = request.getParameter("coduser");          
            
            ConeccionDB cn = new ConeccionDB();            
            Date date = new Date();
            DateFormat formatofec_archi = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String fecha_archi = formatofec_archi.format(date);
            
//          información para el combo Serie Documental
            Util util =  new Util();
            String nt = "sgd.fn_seriedoc_consulta";
            String array_cbo[] = new String[1];
            array_cbo[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(nt, array_cbo);
            String cb_sd = util.contenido_combo(datos_cbo, "");
            request.setAttribute("cb_sd", cb_sd);  
                       
            request.setAttribute("id_exp", id_exp);
            request.setAttribute("fecha_archi", fecha_archi);
            request.setAttribute("unid_org", unid_org);
            request.setAttribute("coduser", coduser);
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_archcentral_envia_popup";
    }       
//FIN ENVIO DOCUMENTOS ARCHIVO CENTRAL POPUP    
//
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL GUARDAR       
@RequestMapping(value = {"/sgd/mant_archcentral_envia_guardar"}, method = RequestMethod.GET)
    public String MantArchcentralEnviaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String idexp = request.getParameter("idexp");        
        String coduser = request.getParameter("coduser");        
        String id_sd = request.getParameter("id_sd");        
        String obs = request.getParameter("obs");        
        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_exp_archcentral_envia_mant";
            String array[] = new String[4];
            array[0] = idexp;
            array[1] = coduser;
            array[2] = id_sd;
            array[3] = obs;            
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_archcentral_envia_guardar";
    }    
//FIN ENVIO DOCUMENTOS ARCHIVO CENTRAL GUARDAR     
//    
//INICIO UBICACIÓN DE EXPEDIENTES EN UNIDADES DE CONSERVACIÓN
@RequestMapping(value = {"/sgd/mant_ubicaciontopo"}, method = RequestMethod.GET)
    public String MantUbicaciontopo(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","GESTIÓN DE EXPEDIENTES");
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");        
                
        return "sgd/mant_ubicaciontopo";        
    }
//FIN UBICACIÓN EXPEDIENTES EN UNIDADES DE CONSERVACIÓN
//    
//INICIO UBICACIÓN EXPEDIENTES EN UNIDADES DE CONSERVACIÓN TABLA
    @RequestMapping(value = {"/sgd/mant_ubicaciontopo_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryUbicaciontopo(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
//        sesión de usuario
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        
        ConeccionDB cn = new ConeccionDB(); 
        String np = "seguridad.fn_perfiluser_consulta";
        String array[] = new String[1];
        array[0] = codUser;
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
        String nom_pers = "";
        String id_pers = "";
        String id_uo = "";
        String nivel = "";
        for(int i = 0 ; i<v_datos.size() ; i++){
            Vector datos_v =  (Vector) v_datos.get(i);
            nom_pers = datos_v.get(3).toString();
            id_pers = datos_v.get(4).toString();
            id_uo = datos_v.get(5).toString();
            nivel = datos_v.get(7).toString();
        }       
        request.setAttribute("id_uo",id_uo);
        request.setAttribute("codUser",codUser);
        request.setAttribute("id_pers",id_pers);
        request.setAttribute("nom_pers",nom_pers);        
        
        String cons_exp = "sgd.fn_expediente_ubicaciontopo_consulta";
        String array_exp[] = new String[1];
        array_exp[0] = id_uo;
        Vector v_exp = cn.EjecutarProcedurePostgres(cons_exp, array_exp);
        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_exp.size() ; i++){
            Vector vss =  (Vector) v_exp.get(i);
            String id_exp = vss.get(0).toString();
            String cut_exp = vss.get(1).toString();
            String per_exp = vss.get(2).toString();
            String fec_reg = vss.get(3).toString();
            String id_alcan = vss.get(4).toString();
            String id_tema = vss.get(5).toString();
//                String id_cond = vss.get(6).toString();
            String des_uo = vss.get(8).toString();
//                String sdoc = vss.get(9).toString();

            String chkb = "<input type='checkbox' value='"+id_exp+"' class='cb_exp' id='cb_"+id_exp+"'/>"
                    + "<label for='cb_"+id_exp+"'></label>";                    
//                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_expediente_popup('"+id_exp+"','1','1','1','0')'>"
//                        + "<span class='glyphicon glyphicon-search'>"
//                        + "</span>"
//                        + "</button>";
            Vector vv = new Vector();
            vv.add(chkb);                
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(fec_reg);
            vv.add(id_alcan);
            vv.add(id_tema);
//                vv.add(id_cond);
            vv.add(des_uo);
//                vv.add(sdoc);
//                vv.add(btn);
            v_temp.add(vv);                
        }     
            Vector v_tbl_cab =  new Vector();
            v_tbl_cab.add("");
            v_tbl_cab.add("N° EXPEDIENTE");
            v_tbl_cab.add("PERIODO");
            v_tbl_cab.add("FECHA");
            v_tbl_cab.add("ALCANCE");
            v_tbl_cab.add("TEMA");
//            v_tbl_cab.add("CONDICIÓN");
            v_tbl_cab.add("UNIDAD ORGÁNICA");
//            v_tbl_cab.add("SERIE DOC.");            
            Util u = new Util();
            String tbl =  u.tbl(v_tbl_cab, v_temp);
            request.setAttribute("response",tbl);
            
        return "sgd/mant_ubicaciontopo_tbl";
	}    
//FIN UBICACIÓN EXPEDIENTES EN UNIDADES DE CONSERVACIÓN TABLA
//        
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL POPUP    
    @RequestMapping(value = {"/sgd/mant_ubicaciontopo_popup"}, method = RequestMethod.GET)
        public String MantUbicaciontopoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","UBICACIÓN DE EXPEDIENTES");            
        try {
            String id_exp = request.getParameter("id_exp");          
            String coduser = request.getParameter("coduser");          
            String cod_unid_org = request.getParameter("cod_unid_org");   
            String cod_uo = "";
            
            ConeccionDB cn = new ConeccionDB();            
            
            Util util =  new Util();
            String ne = "sgd.fn_almacen_consulta";
            String array_almacen[] = new String[1];
            array_almacen[0] = cod_unid_org;
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_almacen);
            String cb_almacen = util.contenido_combo(datos_cbo_est, cod_uo);
            request.setAttribute("cb_almacen", cb_almacen);    
                       
            request.setAttribute("id_exp", id_exp);
            request.setAttribute("coduser", coduser);
            
            
            if (id_exp.length() == 0){
                request.setAttribute("msj", "¡Debe seleccionar por lo menos 1 expediente!"); 
                request.setAttribute("danger", "alert alert-danger");  
                request.setAttribute("obj_disable_form", "disabled");  
            } 
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_ubicaciontopo_popup";
    }       
//FIN ENVIO DOCUMENTOS ARCHIVO CENTRAL POPUP    
//             
//INICIO UBICACIÓN DE EXPEDIENTES EN UNIDADES DE CONSERVACIÓN
@RequestMapping(value = {"/sgd/mant_ubicaciontopo_treeview"}, method = RequestMethod.GET)
    public String MantUbicaciontopoTreeview(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","UBICACIÓN TOPOGRÁFICA TREEVIEW");
//        request.setAttribute("tit_btn","ENVIAR A ARCHIVO"); 
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        String unid_org = "";
        String cod_unid_org = "";
        
        ConeccionDB cn =  new ConeccionDB();
        String np_uo = "senamhi.fn_uo_useremisor_consulta";
        String array_uo[] = new String[1];
        array_uo[0] = codUser;
        Vector v_datos_uo = cn.EjecutarProcedurePostgres(np_uo, array_uo);
        for(int i = 0 ; i<v_datos_uo.size() ; i++){
            Vector vss =  (Vector) v_datos_uo.get(i);
            unid_org = vss.get(1).toString();            
            cod_unid_org = vss.get(2).toString();            
        }
            request.setAttribute("codUser",codUser);
            request.setAttribute("cod_unid_org",cod_unid_org);               
              
        return "sgd/mant_ubicaciontopo_treeview";        
    }
//FIN UBICACIÓN EXPEDIENTES EN UNIDADES DE CONSERVACIÓN     
//    
//INICIO UBICACIÓN DE EXPEDIENTES EN UNIDADES DE CONSERVACIÓN
@RequestMapping(value = {"/sgd/mant_ubicaciontopo_treeview_data"}, method = RequestMethod.GET)
    public String MantUbicaciontopoTreeviewData(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
//        request.setAttribute("title_pag","UBICACIÓN TOPOGRÁFICA TREEVIEW");
//        request.setAttribute("tit_btn","ENVIAR A ARCHIVO"); 
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        ConeccionDB cn = new ConeccionDB();   

        String np = "sgd.fn_ubicaciontopo_treeview_data_consulta";
        String array[] = new String[1];
        array[0] = codUser;
        Vector datos = cn.EjecutarProcedurePostgres(np, array);
        String var_response = new Util().vector2json(datos);
        request.setAttribute("response", var_response);
        return "sgd/mant_ubicaciontopo_treeview_data";        
    }
//FIN UBICACIÓN EXPEDIENTES EN UNIDADES DE CONSERVACIÓN   
//    
//INICIO CARGAR COMBO ESTANTE
    @RequestMapping(value = {"/sgd/mant_estante_cargar_cbo"}, method = RequestMethod.GET)
public String MantEstanteCargarCbo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
    
    String var_request = "";
    try {
        String id_almacen = request.getParameter("id_almacen"); 
        String id_estante = request.getParameter("id_estante"); 
        String vacio="";

        ConeccionDB cn = new ConeccionDB();   

        String np = "sgd.fn_almacen_estante_consulta";
        String array[] = new String[1];
        array[0] = id_almacen;
        Vector datos = cn.EjecutarProcedurePostgres(np, array);
        
        if (id_estante.equals(vacio)){
            var_request = new Util().contenido_combo(datos, "");
        }else{
            var_request = new Util().contenido_combo(datos, id_estante);
        }

    } catch (Exception ex) {
        Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
    }

    request.setAttribute("response", var_request);

    return "sgd/mant_estante_cargar_cbo";
} 
//FIN CARGAR COMBO ESTANTE    
//
//INICIO CARGAR COMBO CUERPO
    @RequestMapping(value = {"/sgd/mant_cuerpo_cargar_cbo"}, method = RequestMethod.GET)
public String MantCuerpoCargarCbo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
    
    String var_request = "";
    try {
        String id_estante = request.getParameter("id_estante"); 
        String id_cuerpo = request.getParameter("id_cuerpo"); 
        String vacio="";
        
        ConeccionDB cn = new ConeccionDB();   
        String np = "sgd.fn_almacen_cuerpo_consulta";
        String array[] = new String[1];
        array[0] = id_estante;
        Vector datos = cn.EjecutarProcedurePostgres(np, array);
        if (id_estante.equals(vacio)){
            var_request = new Util().contenido_combo(datos, "");
        }else{        
            var_request = new Util().contenido_combo(datos, id_cuerpo);
        }

    } catch (Exception ex) {
        Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
    }
    request.setAttribute("response", var_request);

    return "sgd/mant_cuerpo_cargar_cbo";
} 
//FIN CARGAR COMBO CUERPO  
//
//INICIO CARGAR COMBO BALDA
    @RequestMapping(value = {"/sgd/mant_balda_cargar_cbo"}, method = RequestMethod.GET)
public String MantBaldaCargarCbo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
    
    String var_request = "";
    try {
        String id_cuerpo = request.getParameter("id_cuerpo"); 
        String id_balda = request.getParameter("id_balda"); 
        String vacio="";
        
        ConeccionDB cn = new ConeccionDB();  
        String np = "sgd.fn_almacen_balda_consulta";
        String array[] = new String[1];
        array[0] = id_cuerpo;
        Vector datos = cn.EjecutarProcedurePostgres(np, array);
        if (id_balda.equals(vacio)){
            var_request = new Util().contenido_combo(datos, "");
        }else{        
            var_request = new Util().contenido_combo(datos, id_balda);
        }

    } catch (Exception ex) {
        Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
    }
    request.setAttribute("response", var_request);

    return "sgd/mant_balda_cargar_cbo";
} 
//FIN CARGAR COMBO BALDA  
//
//INICIO CARGAR COMBO UNIDAD DE CONSERVACIÓN
    @RequestMapping(value = {"/sgd/mant_unidcons_cargar_cbo"}, method = RequestMethod.GET)
public String MantUnidconsCargarCbo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
    
    String var_request = "";
    try {
        String id_balda = request.getParameter("id_balda"); 

        ConeccionDB cn = new ConeccionDB();   

        String np = "sgd.fn_almacen_unidcons_consulta";
        String array[] = new String[1];
        array[0] = id_balda;
        Vector datos = cn.EjecutarProcedurePostgres(np, array);
        var_request = new Util().contenido_combo(datos,"");

    } catch (Exception ex) {
        Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
    }
    request.setAttribute("response", var_request);

    return "sgd/mant_unidcons_cargar_cbo";
} 
//FIN CARGAR COMBO UNIDAD DE CONSERVACIÓN  
//
//INICIO UBICACIÓN TOPOGRÁFICA GUARDAR       
@RequestMapping(value = {"/sgd/mant_ubicaciontopo_guardar"}, method = RequestMethod.GET)
    public String MantUbicaciontopoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String idexp = request.getParameter("idexp");        
//        String coduser = request.getParameter("coduser"); 
        String unidcons = request.getParameter("unidcons");         
        String var_request = "";
        
        try {
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_exp_ubicaciontopo_mant";
            String array[] = new String[2];
            array[0] = idexp;
            array[1] = unidcons;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_ubicaciontopo_guardar";
    }    
//FIN UBICACIÓN TOPOGRÁFICA GUARDAR     
//    
//INICIO MOVIMIENTO DE UNIDADES DE CONSERVACIÓN
@RequestMapping(value = {"/sgd/mant_mov_unidcons"}, method = RequestMethod.GET)
    public String MantMovUnidcons(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        request.setAttribute("title_pag","UNIDADES DE CONSERVACIÓN");       
        
        return "sgd/mant_mov_unidcons";        
    }
//FIN MOVIMIENTO DE UNIDADES DE CONSERVACIÓN    
//    
//INICIO MOVIMIENTO DE UNIDADES DE CONSERVACIÓN TABLA
    @RequestMapping(value = {"/sgd/mant_mov_unidcons_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryMovUnidcons(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
//        sesión de usuario
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        String unid_org = "";
        String cod_unid_org = "";
        
        ConeccionDB cn =  new ConeccionDB();
        String np_uo = "senamhi.fn_uo_useremisor_consulta";
        String array_uo[] = new String[1];
        array_uo[0] = codUser;
        Vector v_datos_uo = cn.EjecutarProcedurePostgres(np_uo, array_uo);
        for(int i = 0 ; i<v_datos_uo.size() ; i++){
            Vector vss =  (Vector) v_datos_uo.get(i);
            unid_org = vss.get(1).toString();            
            cod_unid_org = vss.get(2).toString();            
        }
            request.setAttribute("cod_unid_org",cod_unid_org); 
            request.setAttribute("codUser",codUser);             
            
        String np = "sgd.fn_unidcons_consulta";
        String array[] = new String[3];
        array[0] = "";
        array[1] = codUser;
        array[2] = "1";
        Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

        Vector v_temp = new Vector();
        for(int i = 0 ; i<v_datos.size() ; i++){
            
            Vector vss =  (Vector) v_datos.get(i);
            String i_id = vss.get(0).toString();
            String i_id_tip_unidcons = vss.get(8).toString();
            String c_cod = vss.get(2).toString();
            String c_des = vss.get(3).toString();
            String c_obs = vss.get(4).toString();
            String d_fec_extini = vss.get(5).toString();
            String d_fec_extfin = vss.get(6).toString();
            String id_bld = vss.get(10).toString();
            String des_bld = vss.get(11).toString();
            String id_cpo = vss.get(12).toString();
            String des_cpo = vss.get(13).toString();
            String id_estt = vss.get(14).toString();
            String des_estt = vss.get(15).toString();
            
            String chkb = "<div class='text-center'>"
                    + "<input type='checkbox' value='"+i_id+"_"+id_bld+"_"+id_cpo+"_"+id_estt+"' class='cb_unidcons' id='cb_"+i_id+"_"+id_bld+"_"+id_cpo+"_"+id_estt+"'/>"
                    + "<label for='cb_"+i_id+"_"+id_bld+"_"+id_cpo+"_"+id_estt+"'></label>"
                    + "</div>";             
//            String btn = "<div class='text-center'>"
//                    + "<button type='button' class='btn btn-info' onclick='sgd_mant_unidcons_popup('"+i_id+"')'>"
//                    + "<span class='glyphicon glyphicon-edit'></span>"
//                    + "</button>"
//                    + "</div>";
            Vector vv = new Vector();
            vv.add(chkb);
            vv.add(i_id);
            vv.add(i_id_tip_unidcons);
            vv.add(c_cod);
            vv.add(c_des);
            vv.add(c_obs);
            vv.add(d_fec_extini);
            vv.add(d_fec_extfin);
            vv.add(des_bld);
            vv.add(des_cpo);
            vv.add(des_estt);
            v_temp.add(vv);                
        }
        Vector v_tbl_cab =  new Vector();
        v_tbl_cab.add("");
        v_tbl_cab.add("ID. UNID.CONS");
        v_tbl_cab.add("TIPO UNID.CONS");
        v_tbl_cab.add("COD. UNID.CONS");
        v_tbl_cab.add("DES. UNID.CONS");
        v_tbl_cab.add("OBS. UNID.CONS");
        v_tbl_cab.add("FECHA EXT.INICIAL");
        v_tbl_cab.add("FECHA EXT.FINAL");
        v_tbl_cab.add("BALDA");
        v_tbl_cab.add("CUERPO");
        v_tbl_cab.add("ESTANTE");

        Util u = new Util();
        String tbl =  u.tbl(v_tbl_cab, v_temp);
        request.setAttribute("response",tbl);
            
        return "sgd/mant_mov_unidcons_tbl";
    }    
//FIN MOVIMIENTO DE UNIDADES DE CONSERVACIÓN TABLA            
//        
//INICIO MOVIMIENTO DE UNIDADES DE CONSERVACIÓN POPUP    
    @RequestMapping(value = {"/sgd/mant_mov_unidcons_popup"}, method = RequestMethod.GET)
        public String MantMovUnidconsPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","MOVER UNIDAD DE CONSERVACIÓN");            
        try {
            String id_unidcons = request.getParameter("id_unidcons");          
            String codUser = request.getParameter("coduser");          
            String cod_unid_org = request.getParameter("cod_unid_org");   
            String cod_uo = "";
            
            ConeccionDB cn =  new ConeccionDB();
            String np = "sgd.fn_unidcons_consulta";
            String array[] = new String[3];
            array[0] = "";
            array[1] = codUser;
            array[2] = "1";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){            
                Vector vss =  (Vector) v_datos.get(i);
                String i_id = vss.get(0).toString();
                String c_des = vss.get(3).toString();
                String id_bld = vss.get(10).toString();
                String des_bld = vss.get(11).toString();
                String id_cpo = vss.get(12).toString();
                String des_cpo = vss.get(13).toString();
                String id_estt = vss.get(14).toString();
                String des_estt = vss.get(15).toString();

                Vector vv = new Vector();
    //            vv.add(i_id);
                vv.add(c_des);
                vv.add(des_bld);
                vv.add(des_cpo);
                vv.add(des_estt);
                v_temp.add(vv);   

                id_unidcons = i_id + '_' + c_des + '_' + des_bld + '_' + des_cpo + '_' + des_estt;
            }
                      
            Util util =  new Util();
            String ne = "sgd.fn_almacen_consulta";
            String array_almacen[] = new String[1];
            array_almacen[0] = cod_unid_org;
            Vector datos_cbo_est = cn.EjecutarProcedurePostgres(ne, array_almacen);
            String cb_almacen = util.contenido_combo(datos_cbo_est, cod_uo);
            request.setAttribute("cb_almacen", cb_almacen);    

            request.setAttribute("id_unidcons", id_unidcons);
            request.setAttribute("coduser", codUser);
                       
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_mov_unidcons_popup";
    }       
//FIN MOVIMIENTO DE UNIDADES DE CONSERVACIÓN POPUP    
//             
//INICIO LISTA UNIDADES DE CONSERVACIÓN - POPUP MOVIMIENTO
    @RequestMapping(value = {"/sgd/mant_unidcons_lista"}, method = RequestMethod.GET)
    public String MantUnidconsLista(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {        
        try {        
            String cod_unidcons = "";
            String cod_uc = "";
            String des_uc = "";
            String balda = "";
            String cuerpo = "";
            String estante = "";

            String id_unidcons = request.getParameter("id_unidcons");             
            String[] array_unidcons = id_unidcons.split(",");  

            String tbl_unidc = "<table class='table'>" +
                                    "<tr>" +
                                    "  <td><strong>CÓDIGO</strong></td>" +
                                    "  <td><strong>UNID.CONS.</strong></td>" +
                                    "  <td><strong>BALDA</strong></td>" +
                                    "  <td><strong>CUERPO</strong></td>" +
                                    "  <td><strong>ESTANTE</strong></td>" +
                                    "</tr>";        

            for (int x = 0; x<array_unidcons.length; x++){
                cod_unidcons = array_unidcons[x];
                String[] array_cod_unidcons = cod_unidcons.split("_");
                cod_uc = array_cod_unidcons [0];
                des_uc = array_cod_unidcons [1];
                balda = array_cod_unidcons [2];
                cuerpo = array_cod_unidcons [3];
                estante = array_cod_unidcons [4];

                tbl_unidc += "<tr>" +
                            "  <td>"+cod_uc+"</td>" +
                            "  <td>"+des_uc+"</td>" +
                            "  <td>"+balda+"</td>" +
                            "  <td>"+cuerpo+"</td>" +
                            "  <td>"+estante+"</td>" +
                            "</tr>";
            }     
            tbl_unidc += "</table>";        

            request.setAttribute("response",tbl_unidc);

        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "sgd/mant_unidcons_lista";
    }     
//FIN LISTA UNIDADES DE CONSERVACIÓN - POPUP MOVIMIENTO      
//
//INICIO MOVIMIENTO DE UNIDADES DE CONSERVACIÓN GUARDAR     
@RequestMapping(value = {"/sgd/mant_mov_unidcons_guardar"}, method = RequestMethod.GET)
    public String MantMovUnidconsGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        String idunidcons = request.getParameter("idunidcons");        
        String idbalda = request.getParameter("idbalda"); 
        String obs = request.getParameter("obs");         
        String var_request = "";        
        try {
            
            String[] array_cod_unidcons = idunidcons.split("_");
            String cod_uc = array_cod_unidcons [0];
            
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_unidcons_mov_mant";
            String array[] = new String[3];
            array[0] = cod_uc;
            array[1] = idbalda;
            array[2] = obs;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_mov_unidcons_guardar";
    }    
//FIN MOVIMIENTO DE UNIDADES DE CONSERVACIÓN GUARDAR         
//  
//INICIO AGRUPAR EXPEDIENTES POPUP    
@RequestMapping(value = {"/sgd/mant_agrupa_popup"}, method = RequestMethod.GET)
    public String MantAgrupaPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        request.setAttribute("title_pag","AGRUPAR EXPEDIENTES");
        int x = 0;
        try {                   
            String codUser = request.getParameter("codUser");          
//            String cod_unid_org = request.getParameter("cod_unid_org");   
//            String cod_uo = "";
            String id_exp_agr = "";
            String id_exp = "";
            String cut = "";
            String periodo = "";
            String fec_registro = "";
            String cad_id_exp = "";
            
            String id_expediente = request.getParameter("id_expediente");   
            String[] array_exp = id_expediente.split(","); 
            
            SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date f_menor = new Date();
            for (x = 0; x < array_exp.length; x++){
                String[] array_s = array_exp[x].split("_");
                fec_registro = array_s [3];
                
                Date nf = sdf.parse(fec_registro);
                if(nf.before(f_menor)) {
                    f_menor= nf;
                }                
            }
            
//            String fs_menor = (1900+f_menor.getYear())+""+(1+f_menor.getMonth())+""+f_menor.getDate()+""+f_menor.getHours()+""+f_menor.getMinutes()+""+f_menor.getSeconds();            
            String fs_menor = sdf.format(f_menor);
            String tbl_agrupa = "<table class='table table-striped'>" +
                                    "<tr class='success'>" +
                                    "  <td>MATRIZ</td>" +
                                    "  <td>ITEM</td>" +
                                    "  <td>N° EXPEDIENTE</td>" +
                                    "  <td>PERIODO(AÑO)</td>" +
                                    "  <td>FECHA REGISTRO</td>" +
                                    "</tr>";  
            String id_exp_matriz = "";
            for (x = 0; x < array_exp.length; x++){
                id_exp_agr = array_exp[x];
                String[] array_cod_unidcons = id_exp_agr.split("_");
                id_exp = array_cod_unidcons [0];
                cut = array_cod_unidcons [1];
                periodo = array_cod_unidcons [2];
                fec_registro = array_cod_unidcons [3];
                
                tbl_agrupa += "<tr>" +
                                "<td class='text-left'>"; 
                if (fec_registro.equals(fs_menor)){
                    id_exp_matriz = id_exp+",";
                    tbl_agrupa += "<button type='button' class='btn btn-info' id='matriz'>" + 
                                    "<span class='glyphicon glyphicon-star'>" +
                                    "</span>" + 
                                  "</button>";
                }
                tbl_agrupa += "  </td>" +
                                "  <td class='text-center'>"+x+"</td>" +
                                "  <td>"+cut+"</td>" +
                                "  <td>"+periodo+"</td>" +
                                "  <td>"+fec_registro+ "</td>" +
                                "</tr>";  
                cad_id_exp += id_exp + ",";
            }
            
            cad_id_exp = cad_id_exp.replace(id_exp_matriz, "");
            cad_id_exp = id_exp_matriz + cad_id_exp;
            tbl_agrupa += "</table>";             
            
            if (x > 1){
                request.setAttribute("tbl_agrupa", tbl_agrupa); 
                request.setAttribute("cad_id_exp", cad_id_exp);               
                request.setAttribute("codUser", codUser);              
            }else{
                request.setAttribute("msj", "¡Debe seleccionar por lo menos 2 expedientes!"); 
                request.setAttribute("danger", "alert alert-danger"); 
            } 
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
            if (x < 2){
                request.setAttribute("msj", "¡Debe seleccionar por lo menos 2 expedientes!"); 
                request.setAttribute("danger", "alert alert-danger");  
                request.setAttribute("obj_disable_form", "disabled");  
            } 
        }
    return "sgd/mant_agrupa_popup";  
    }
//FIN AGRUPAR EXPEDIENTES POPUP
//
//INICIO AGRUPA GUARDAR     
@RequestMapping(value = {"/sgd/mant_agrupa_guardar"}, method = RequestMethod.GET)
    public String MantAgrupaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        
            String var_request = "";    
            String cad_id_exp = request.getParameter("cad_id_exp"); 
            String codUser = request.getParameter("codUser"); 
            cad_id_exp = cad_id_exp.substring(0, cad_id_exp.length() - 1);
        try {
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_agrupa_mant";
            String array[] = new String[2];
            array[0] = cad_id_exp;
            array[1] = codUser;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_agrupa_guardar";
    }    
//FIN AGRUPA GUARDAR       
//  
//INICIO AGRUPA LISTA
@RequestMapping(value = {"/sgd/mant_lista_agrupa_cargar"}, method = RequestMethod.GET)
    public String MantListaAgrupaCargar(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        
        String id_exp = request.getParameter("idexp"); 
        
        ConeccionDB cn = new ConeccionDB();   

        String np = "sgd.fn_agrupa_consulta";
        String array[] = new String[1];
        array[0] = id_exp;
        Vector datos = cn.EjecutarProcedurePostgres(np, array);
        String var_response = new Util().vector2json(datos);
        request.setAttribute("response", var_response);
        return "sgd/mant_lista_agrupa_cargar";        
    }
//FIN AGRUPA LISTA
//       
//INICIO DESAGRUPA GUARDAR     
@RequestMapping(value = {"/sgd/mant_desagrupa_guardar"}, method = RequestMethod.GET)
    public String MantDesagrupaGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        
            String var_request = "";    
            String idexp_desagr = request.getParameter("idexp_desagr"); 
//            String codUser = request.getParameter("codUser"); 
            
            idexp_desagr = idexp_desagr.substring(0, idexp_desagr.length() - 1);  
        try {
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_desagrupa_mant";
            String array[] = new String[1];
            array[0] = idexp_desagr;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);
            var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_desagrupa_guardar";
    }    
//FIN DESAGRUPA GUARDAR       
// 
//INICIO BUSCAR EXPEDIENTE POR DIRECCIÓN    
@RequestMapping(value = {"/sgd/mant_buscarexp"}, method = RequestMethod.GET)
    public String MantBuscarexp(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
        request.setAttribute("title_pag","BUSCAR EXPEDIENTE");
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");  
        request.setAttribute("codUser", codUser);               
        return "sgd/mant_buscarexp";
    }  
//FIN BUSCAR EXPEDIENTE POR DIRECCIÓN           
//
//INICIO BUSCAR EXPEDIENTE POR DIRECCIÓN TABLA
//@RequestMapping(value = {"/sgd/mant_buscarexp_tbl"}, method = RequestMethod.GET)
//    public String AjaxQueryBuscarexpTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
//
//    String codUser = request.getParameter("codUser");  
//
//    ConeccionDB cn =  new ConeccionDB();        
//    String np = "sgd.fn_expedientegeneral_consulta";
//    String array[] = new String[1];
//    array[0] = codUser;
//    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
//    Vector v_temp = new Vector();
//    for(int i = 0 ; i<v_datos.size() ; i++){            
//        Vector vss =  (Vector) v_datos.get(i);
//        String i_id = vss.get(0).toString();//id expediente
//        String cut_exp = vss.get(1).toString();
//        String per_exp = vss.get(2).toString();
//        String fec_reg = vss.get(3).toString();
//        String des_origen = vss.get(4).toString();
//        String des_tema = vss.get(5).toString();
//        String des_proc = vss.get(6).toString();
//        String des_uo = vss.get(7).toString();
//        String documento = vss.get(8).toString();
//        String exp_agrupados = vss.get(9).toString();
//        String matriz = vss.get(10).toString();
//        String asunto = vss.get(12).toString();
//        String des_alcance = vss.get(14).toString();
//        String id_doc = vss.get(15).toString();
////        String remite = vss.get(16).toString();
////        String destino = vss.get(17).toString();
//        String f_envio = vss.get(17).toString();
//        String cut_per = cut_exp +'-'+ per_exp;
//
//        String btn = "<div class='text-center'>"
//                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\",\""+codUser+"\")'>"
//                + "<span class='glyphicon glyphicon-search'></span>"
//                + "</button>"
//                + "</div>";
//        Vector vv = new Vector();
//            vv.add(btn);
//            vv.add(cut_exp);
//            vv.add(per_exp);
//            vv.add(des_alcance);
//            vv.add(fec_reg);
//            vv.add(documento);
//            vv.add(asunto);
//            vv.add(des_origen);
//            vv.add(des_tema);
//            vv.add(des_proc);
//            vv.add(des_uo);
//            vv.add(matriz);
//            vv.add(exp_agrupados);
////            vv.add(remite);
//////            vv.add(destino);
//            vv.add(f_envio);            
//            v_temp.add(vv); 
//    }
//    Util util = new Util();
//    String json = util.vector2json_tbl(v_temp);   
//    Vector vc_tbl = new Vector();
//    Vector sv =  new Vector();
//    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("aoColumns");sv.add("["     
//                            + "{'sTitle':'-'} , "
//                            + "{'sTitle':'N CUT'} , "
//                            + "{'sTitle':'PERIODO'} , "
//                            + "{'sTitle':'ALCANCE'} , "
//                            + "{'sTitle':'FECHA REG.'} , "
//                            + "{'sTitle':'DOCUMENTO'} , "
//                            + "{'sTitle':'ASUNTO'} , "
//                            + "{'sTitle':'ORIGEN'} , "
//                            + "{'sTitle':'TEMA'} , "
//                            + "{'sTitle':'PROCEDIMIENTO'} , "
//                            + "{'sTitle':'UNIDAD ORGÁNICA CREA'} , "
//                            + "{'sTitle':'EXP. MATRIZ'} , "
//                            + "{'sTitle':'EXP. AGRUPADOS'},  "
////                            + "{'sTitle':'REMITE'},  "
////                            + "{'sTitle':'DESTINO'},  "
//                            + "{'sTitle':'FEC.ENVIO'}  "                          
//                            + "]");
//    vc_tbl.add(sv);
//    sv =  new Vector();
//    sv.add("aaData");
//    sv.add(json);
//    vc_tbl.add(sv);
//    sv =  new Vector();
//    sv.add("dom");
//    sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");
//    vc_tbl.add(sv);
//    sv =  new Vector();
//    sv.add("buttons");
//    sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");
//    vc_tbl.add(sv);
//    sv =  new Vector();
//    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                  "}";
//    sv.add("fnRowCallback");
//    sv.add(fnc);
//    vc_tbl.add(sv);
//    sv =  new Vector();
//    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
//    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
//    request.setAttribute("response", tbl_html + tbl);
//
//    return "sgd/mant_buscarexp_tbl";
//    }   
        
@RequestMapping(value = {"/sgd/mant_buscarexp_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarExpTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");    
           
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_consulta";
    String array[] = new String[1];
    array[0] = codUser;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscarexp_tbl";
    }        
    
//FIN BUSCAR EXPEDIENTE POR DIRECCIÓN TABLA
//        
//INICIO BUSCAR EXPEDIENTES POPUP    
@RequestMapping(value = {"/sgd/mant_expediente_buscar_popup"}, method = RequestMethod.GET)
    public String MantBuscarexpPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws ServletException, IOException {
        request.setAttribute("title_pag","CONSULTANDO EXPEDIENTE");
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");
        String id_exp = request.getParameter("id_exp"); 
        String id_doc = request.getParameter("id_doc"); 
        
        String i_id_exp = "";//id del expediente
        String i_cut = "";//id del cut, numeración anual
        String i_per = "";//año del expediente
        String d_fec_reg = "";//fecha del registro del expediente
        String i_vtn = "";//ventanilla desde donde se generó el expediente
        String c_nro_cutext = "";//nro de cut externo, de otras entidades
        String i_cond = "";//condición del expediente
        String i_priori = "";//prioridad
        String i_plazo = "";//plazo de atención a partir de día de su creación
        String i_alcan = "";//alcance del expediente
        String i_tema = "";//tema del expediente            
        String i_origen = "";//variable para el origen: externo o interno            
        String i_proc = "";//id del procedimiento
        String i_id_doc = ""; 
        String i_clsfdoc = "";
        String i_nrodoc = "";
        String d_fec_doc = "";
        String i_folio = "";
        String i_rmte = "";
        String c_destino = "";
        String c_asunto = ""; 
        String c_observacion = ""; 
//        String i_id_flujo = "";
        String nom_dest = "";
        String uo_dest = ""; 
//        String user_agrupa = ""; 
        
        try {
            Util util =  new Util();
            ConeccionDB cn = new ConeccionDB(); 
            
            String nc = "sgd.fn_expedientebuscar_consulta";//consulta de documento             
            String array[] = new String[2];
            array[0] = id_exp;
            array[1] = id_doc;
            Vector v_datos = cn.EjecutarProcedurePostgres(nc, array);
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector datos_v =  (Vector) v_datos.get(i);
                i_id_exp = datos_v.get(0).toString();
                i_cut = datos_v.get(1).toString();
                i_per = datos_v.get(2).toString();
                i_id_doc = datos_v.get(3).toString();
                d_fec_reg = datos_v.get(4).toString();
                i_cond = datos_v.get(5).toString();
                i_priori = datos_v.get(6).toString();
                i_plazo = datos_v.get(7).toString();
                i_alcan = datos_v.get(8).toString();
                i_tema = datos_v.get(9).toString();
                i_origen = datos_v.get(10).toString();
                i_proc = datos_v.get(11).toString();
                c_nro_cutext = datos_v.get(12).toString();
                i_clsfdoc = datos_v.get(13).toString();
                i_nrodoc = datos_v.get(14).toString();
                d_fec_doc = datos_v.get(15).toString();
                i_folio = datos_v.get(16).toString();
                i_rmte = datos_v.get(17).toString();
                c_destino = datos_v.get(18).toString();
                c_asunto = datos_v.get(19).toString();
                c_observacion = datos_v.get(20).toString();
//                i_id_flujo = datos_v.get(21).toString();         
//                user_agrupa = datos_v.get(22).toString();         
                }  
            request.setAttribute("codUser", codUser);
//            request.setAttribute("user_agrupa", user_agrupa); 
            request.setAttribute("id", i_id_exp); 
            request.setAttribute("cut", i_cut);         
            request.setAttribute("per", i_per);         
            request.setAttribute("fecreg", d_fec_reg);
            request.setAttribute("doc", i_id_doc);
            request.setAttribute("i_vtn", i_vtn);
            request.setAttribute("cutext", c_nro_cutext);
            request.setAttribute("plazo", i_plazo);   
            request.setAttribute("id_remite", i_rmte);
            request.setAttribute("nrodoc", i_nrodoc);
            request.setAttribute("fec_doc", d_fec_doc);
            request.setAttribute("folio", i_folio);
            request.setAttribute("asunto", c_asunto);
            request.setAttribute("observacion", c_observacion);
            
            String cons_cond = "sgd.fn_condicion_consulta";//combo condición               
            String array_cbo_condicion[] = new String[1];
            array_cbo_condicion[0] = "";
            Vector datos_cbo_condicion = cn.EjecutarProcedurePostgres(cons_cond, array_cbo_condicion);
            String cb_desc_condicion = util.contenido_combo(datos_cbo_condicion, i_cond);
            request.setAttribute("cb_condicion", cb_desc_condicion);    
            
            String cons_prio = "sgd.fn_prioridad_consulta";//combo Prioridad
            String array_cbo_prioridad[] = new String[1];
            array_cbo_prioridad[0] = "";            
            Vector datos_cbo_prioridad = cn.EjecutarProcedurePostgres(cons_prio, array_cbo_prioridad);
            String cb_desc_prioridad = util.contenido_combo(datos_cbo_prioridad, i_priori);
            request.setAttribute("cb_priori", cb_desc_prioridad);   
            
            String cons_alc = "sgd.fn_alcance_consulta";//combo Alcance
            String array_cbo_alcance[] = new String[2];
            array_cbo_alcance[0] = "";
            array_cbo_alcance[1] = "1";
            Vector datos_cbo_alcance = cn.EjecutarProcedurePostgres(cons_alc, array_cbo_alcance);
            String cb_desc_alcance = util.contenido_combo(datos_cbo_alcance, i_alcan);
            request.setAttribute("cb_alcance", cb_desc_alcance);
            
            String cons_tema = "sgd.fn_tema_consulta";//combo Tema
            String array_cbo_tema[] = new String[2];
            array_cbo_tema[0] = "";
            array_cbo_tema[1] = "1";
            Vector datos_cbo_tema = cn.EjecutarProcedurePostgres(cons_tema, array_cbo_tema);
            String cb_desc_tema = util.contenido_combo(datos_cbo_tema, i_tema);
            request.setAttribute("cb_tema", cb_desc_tema);    
            
            String cons_tram = "sgd.fn_tramite_consulta";//Trámite por procedimiento
            String array_cbo_tramite[] = new String[1];
            array_cbo_tramite[0] = i_proc;
            Vector datos_cbo_tramite = cn.EjecutarProcedurePostgres(cons_tram, array_cbo_tramite);
            String cb_desc_tramite = util.contenido_combo(datos_cbo_tramite, i_proc);
            request.setAttribute("cb_tramite", cb_desc_tramite);  
            
            String cons_proc = "sgd.fn_procedimiento_consulta";//combo Origen
            String array_cbo_proc[] = new String[1];
            array_cbo_proc[0] = "";
            Vector datos_proc = cn.EjecutarProcedurePostgres(cons_proc, array_cbo_proc);
            String cb_desc_proc = util.contenido_combo(datos_proc, i_proc);
            request.setAttribute("cb_procedimiento", cb_desc_proc);  
            
            String cons_orig = "sgd.fn_origen_consulta";//combo Origen
            String array_cbo_origen[] = new String[1];
            array_cbo_origen[0] = "";
            Vector datos_cbo = cn.EjecutarProcedurePostgres(cons_orig, array_cbo_origen);
            String cb_desc_orig = util.contenido_combo(datos_cbo, i_origen);
            request.setAttribute("cb_origen", cb_desc_orig);          
                        
            String nom_pers = "";
            String id_pers = "";
            String id_uo = "";
            String np_pu = "senamhi.fn_uo_entidad_consulta";//consulta´para txt remite
            String array_pu[] = new String[1];
            array_pu[0] = i_rmte;
            Vector v_remite = cn.EjecutarProcedurePostgres(np_pu, array_pu);
            for(int u = 0 ; u<v_remite.size() ; u++){
                Vector datos_v =  (Vector) v_remite.get(u);
                nom_pers = datos_v.get(1).toString();
                id_pers = datos_v.get(0).toString();
                id_uo = datos_v.get(3).toString();
            }
            request.setAttribute("txt_remite", nom_pers); 
            
            String cons_rmte_uo = "senamhi.fn_uo_entidad_consulta";//consulta combo remite
            String array_rmte_uo[] = new String[1];
            array_rmte_uo[0] = "";//id persona
            Vector datos_cbo_uo_rmte = cn.EjecutarProcedurePostgres(cons_rmte_uo, array_rmte_uo);
            String cb_uo_rmte = util.contenido_combo(datos_cbo_uo_rmte, id_uo); 
            request.setAttribute("cb_remite", cb_uo_rmte); 
            
            String cons_dest = "senamhi.fn_uo_entidad_consulta";//consulta para txt destino
            String array_dest[] = new String[1];
            array_dest[0] = c_destino;
            Vector v_dest = cn.EjecutarProcedurePostgres(cons_dest, array_dest);
            for(int u = 0 ; u<v_dest.size() ; u++){
                Vector datos_v =  (Vector) v_dest.get(u);
                nom_dest = datos_v.get(1).toString();
                uo_dest = datos_v.get(3).toString();
            }
            request.setAttribute("txt_destino", nom_dest); 
            
            String cons_destino_uo = "senamhi.fn_uo_entidad_consulta";//consulta combo destino                  
            String array_destino_uo[] = new String[1];
            array_destino_uo[0] = "";
            Vector datos_cbo_uo_destino = cn.EjecutarProcedurePostgres(cons_destino_uo, array_destino_uo); 
            String cb_uo_dest = util.contenido_combo(datos_cbo_uo_destino, uo_dest);//variable de selección para combo destino
            request.setAttribute("cb_destino", cb_uo_dest);    
            
            String ntdoc = "sgd.fn_clasificadoc_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cbo_tdoc[] = new String[1];
            array_cbo_tdoc[0] = "";
            Vector datos_cbo_tdoc = cn.EjecutarProcedurePostgres(ntdoc, array_cbo_tdoc);
            String cb_desc_clsfdoc = util.contenido_combo(datos_cbo_tdoc, i_clsfdoc);
            request.setAttribute("cb_clsfdoc", cb_desc_clsfdoc);
            
            String cta_agrup = "";
            String cons_cta_agrupados = "sgd.fn_agrupacuenta_consulta";//combo Tipo de Documentos por Unidad Orgánica
            String array_cta[] = new String[1];
            array_cta[0] = id_exp;
            Vector datos_cta = cn.EjecutarProcedurePostgres(cons_cta_agrupados, array_cta); 
            for(int u = 0 ; u<datos_cta.size() ; u++){
                Vector datos_v =  (Vector) datos_cta.get(u);
                cta_agrup = datos_v.get(0).toString();                
            }
            request.setAttribute("cta_agrup", cta_agrup);       
            
            
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);            
        }
    return "sgd/mant_expediente_buscar_popup";  
    }
//FIN BUSCAR EXPEDIENTES POPUP     
//        
//INICIO BUSCAR EXPEDIENTE PROFESIONAL   
@RequestMapping(value = {"/sgd/mant_buscarexp_prof"}, method = RequestMethod.GET)
    public String MantBuscarexpProf(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
        request.setAttribute("title_pag","BUSCAR EXPEDIENTE");
        HttpSession session = request.getSession();
        String codUser = (String) session.getAttribute("codUser");           
        request.setAttribute("codUser", codUser);         
        
        return "sgd/mant_buscarexp_prof";
    }  
//FIN BUSCAR EXPEDIENTE PROFESIONAL    
//        
//INICIO BUSCAR EXPEDIENTE PROFESIONAL TABLA
@RequestMapping(value = {"/sgd/mant_buscarexp_prof_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarexpProfTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

    String codUser = request.getParameter("codUser");  

    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_prof_consulta";
    String array[] = new String[1];
    array[0] = codUser;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();
        String des_uo = vss.get(7).toString();
        String documento = vss.get(8).toString();
        String exp_agrupados = vss.get(9).toString();
        String matriz = vss.get(10).toString();
        String user_agrupa = vss.get(11).toString();
        String asunto = vss.get(12).toString();
        String id_alcance = vss.get(13).toString();
        String des_alcance = vss.get(14).toString();
        String id_doc = vss.get(15).toString();
        String cut_per = cut_exp +'-'+ per_exp;

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\",\""+codUser+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(des_uo);
            vv.add(matriz);
            vv.add(exp_agrupados);
            
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
//            sv.add("language");sv.add("{'url':'"+request.getContextPath()+"/static/datatables/Spanish.json'}");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("bFilter");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("bLengthChange");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("bInfo");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("bPaginate");sv.add("false");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("iDisplayLength");sv.add(v_temp.size());vc_tbl.add(sv);sv =  new Vector();
//            sv.add("bJQueryUI");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("["
                            + "{'sTitle':'-'},  "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'UNIDAD ORGÁNICA'} , "
                            + "{'sTitle':'EXP. MATRIZ'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
//    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscarexp_prof_tbl";
    }
//FIN BUSCAR EXPEDIENTE PROFESIONAL TABLA
//
//INICIO BUSCAR EXPEDIENTE ALTA DIRECCION TABLA
@RequestMapping(value = {"/sgd/mant_buscar_altdir"}, method = RequestMethod.GET)
    public String MantBuscareAltdir(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
        request.setAttribute("title_pag","BUSQUEDA GLOBAL DE EXPEDIENTES");
                    
        String c_per = "";
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, c_per);
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorgsgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);
            
        return "sgd/mant_buscar_altdir";
    }          
//FIN BUSCAR EXPEDIENTE ALTA DIRECCION TABLA
//    
//INICIO BUSCAR EXPEDIENTE ALTA DIRECCION TABLA
@RequestMapping(value = {"/sgd/mant_buscar_altdir_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarAltdirTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
    
    String cut = request.getParameter("cut").trim();      
    String anio = request.getParameter("anio");      
    String asun = request.getParameter("asunto").trim();      
    String cd = request.getParameter("cd");      
    String nro = request.getParameter("nro");      
    String uo = request.getParameter("uo");      
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_altdir_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = uo;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_altdir_tbl";
    }    
//FIN BUSCAR EXPEDIENTE ALTA DIRECCION TABLA   
//
//INICIO GESTION ADMINISTRADO        
@RequestMapping(value = {"/sgd/mant_administrado"}, method = RequestMethod.GET)
    public String MantAdministrado(HttpServletRequest request, HttpServletResponse response,ModelMap model) {            
        request.setAttribute("title_pag","GESTIÓN DE ADMINISTRADO");             
        request.setAttribute("btn_nuevo_reg","sgd_mant_administrado_popup()");
        request.setAttribute("tit_btn","NUEVO REGISTRO");
        return "sgd/mant_administrado";
    }        
//FIN GESTION ADMINISTRADO    
//
//INICIO GESTION ADMINISTRADO TABLA 
@RequestMapping(value = {"/sgd/mant_administrado_tbl"}, method = RequestMethod.GET)
	public String AjaxQueryAdministradoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
            
            ConeccionDB cn =  new ConeccionDB();
            String np = "senamhi.fn_administrado_consulta";
            String array[] = new String[1];
            array[0] = "";
            Vector v_datos = cn.EjecutarProcedurePostgres(np, array);

            Vector v_temp = new Vector();
            for(int i = 0 ; i<v_datos.size() ; i++){
                Vector vss =  (Vector) v_datos.get(i);
                String i_id_adm = vss.get(0).toString();
                String i_tpdident = vss.get(1).toString();
                String c_docident = vss.get(2).toString();
                String c_des_adm = vss.get(3).toString();
                String c_repres_adm = vss.get(4).toString();
                String c_dir_adm = vss.get(5).toString();
                String c_email_adm = vss.get(6).toString();
                String c_tlf_adm = vss.get(7).toString();
                     
                String btn = "<button type='button' class='btn btn-info' onclick='sgd_mant_administrado_popup(\\\""+i_id_adm+"\\\")'><span class='glyphicon glyphicon-edit'>"
                            + "</span></button>";
                
                Vector vv = new Vector();
                vv.add(i_id_adm);
                vv.add(i_tpdident);
                vv.add(c_docident);
                vv.add(c_des_adm);
                vv.add(c_repres_adm);
                vv.add(c_dir_adm);
                vv.add(c_email_adm);
                vv.add(c_tlf_adm);
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
                                    + "{'sTitle':'TIPO DOC.IDENT.'} , "
                                    + "{'sTitle':'N° DOC. IDENT.'} , "
                                    + "{'sTitle':'ADMINISTRADO'} , "
                                    + "{'sTitle':'REPRESENTANTE'} , "
                                    + "{'sTitle':'DIRECCIÓN'} , "
                                    + "{'sTitle':'CORREO ELECTRÓNICO'} , "
                                    + "{'sTitle':'TELÉFONO'} , "
                                    + "{'sTitle':'-'}  "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
        //      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
            ////Pintar de rojo el registro si no t.iene datos
//            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
//                            " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
//                          "}";
//            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
            ///////////////////////////////////////////////////////

            String tbl_html = "<table border='1' class='table table-striped table-bordered' id='c_tbl_buscar_expediente'></table>";
            String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
            request.setAttribute("response", tbl_html + tbl);
            
            return "sgd/mant_administrado_tbl";
	}
//FIN GESTION ADMINISTRADO TABLA 
//        
//INICIO GESTION ADMINISTRADO POPUP         
@RequestMapping(value = {"/sgd/mant_administrado_popup"}, method = RequestMethod.GET)
    public String MantAdministradoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
        request.setAttribute("title_pag","REGISTRO DE ADMINISTRADO");   
                
        try {
            String id = request.getParameter("id");
            
            ConeccionDB cn = new ConeccionDB();             
            String np = "senamhi.fn_administrado_consulta";
            String array[] = new String[1];
            array[0] = id;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);
            
            Util util =  new Util();
            String obj_active_form = "";
                        
            String i_id_adm = "";
            String c_docident = "";
            String c_des_adm = "";
            String c_dir_adm = "";
            String c_email_adm = "";            
            String c_tlf_adm = "";            
            String c_repres_adm = "";            
            String i_tpdident = "";            
            
            for(int i = 0 ; i<datos.size() ; i++){                
                obj_active_form = "active";
                
                Vector datos_v =  (Vector) datos.get(i);
                i_id_adm = datos_v.get(0).toString();
                c_docident = datos_v.get(2).toString();
                c_des_adm = datos_v.get(3).toString();
                c_repres_adm = datos_v.get(4).toString();
                c_dir_adm = datos_v.get(5).toString();
                c_email_adm = datos_v.get(6).toString();
                c_tlf_adm = datos_v.get(7).toString();
                i_tpdident = datos_v.get(8).toString();
            }            
            request.setAttribute("obj_active_form", obj_active_form);
            
            request.setAttribute("id", i_id_adm);
            request.setAttribute("docident", c_docident);
            request.setAttribute("descripcion", c_des_adm);
            request.setAttribute("representante", c_repres_adm);
            request.setAttribute("direccion", c_dir_adm);
            request.setAttribute("email", c_email_adm);
            request.setAttribute("telefono", c_tlf_adm);
//            request.setAttribute("tpdident", i_tpdident);
            
//          información para el combo tipo documento
            String ne = "senamhi.fn_tipodocident_consulta";
            String array_cbo_tipodoc[] = new String[1];
            array_cbo_tipodoc[0] = "";
            Vector datos_cbo_tipodoc = cn.EjecutarProcedurePostgres(ne, array_cbo_tipodoc);
            String cbo_tipodoc = util.contenido_combo(datos_cbo_tipodoc, i_tpdident);
            request.setAttribute("cb_tipodoc", cbo_tipodoc);     
          
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_administrado_popup";
    }           
//FIN GESTION ADMINISTRADO POPUP    
//
//INICIO GESTION ADMINISTRADO GUARDAR    
@RequestMapping(value = {"/sgd/mant_administrado_guardar"}, method = RequestMethod.GET)
    public String MantAdministradoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {  
       
        String var_request = "";
        try {             
            String id = request.getParameter("id");
//          String des = request.getParameter("des");
            String des =  request.getParameter("des").replace("\"", "'");
            des = des.replace("\n", "");
//            String repr = request.getParameter("repr");
//            String repr = new String(request.getParameter("repr").getBytes("ISO-8859-1"),"UTF-8");
            String re = request.getParameter("repr");
            String repr =  request.getParameter("repr").replace("\"", "'");
            
            String dir = request.getParameter("dir");
            String telef = request.getParameter("telef");
            String email = request.getParameter("email");
            String tipodoc = request.getParameter("tipodoc");
            String nrodoc = request.getParameter("nrodoc");

            ConeccionDB cdb = new ConeccionDB(); 
            String np = "senamhi.fn_administrado_mant";
            String array[] = new String[8];
            array[0] = id;
            array[1] = des;
            array[2] = repr;
            array[3] = dir;
            array[4] = email;
            array[5] = telef;
            array[6] = tipodoc;
            array[7] = nrodoc;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);            
        } 
        
        request.setAttribute("request", var_request);
        return "sgd/mant_administrado_guardar";
    }    
//FIN GESTION ADMINISTRADO GUARDAR     
//
//INICIO BUSQUEDA DE EXPEDIENTES DERIVADOS ALTA DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_derivado"}, method = RequestMethod.GET)
    public String MantBuscareDerivado(HttpServletRequest request, HttpServletResponse response,ModelMap model) {  
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","BUSQUEDA DE EXPEDIENTES DERIVADOS POR "+ sglUO);
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_derivado";
    }              
//FIN BUSQUEDA DE EXPEDIENTES DERIVADOS ALTA DIRECCIÓN   
//
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA ALTA DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_derivado_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarDerivadoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    
    String cut = request.getParameter("cut");
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto");
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_derivado_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUser;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_derivado_tbl";
    }    
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA ALTA DIRECCIÓN   
//
//INICIO BUSQUEDA DE EXPEDIENTES RECIBIDOS ALTA DIRECCIÓN 
@RequestMapping(value = {"/sgd/mant_buscar_recibido"}, method = RequestMethod.GET)
    public String MantBuscareRecibido(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","BUSQUEDA DE EXPEDIENTES RECIBIDOS POR PREJ");
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_recibido";
    }              
//FIN BUSQUEDA DE EXPEDIENTES RECIBIDOS ALTA DIRECCIÓN    
//    
//INICIO BUSCAR EXPEDIENTE RECIBIDOS TABLA ALTA DIRECCIÓN 
@RequestMapping(value = {"/sgd/mant_buscar_recibido_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarRecibidoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    
    String cut = request.getParameter("cut");
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto");
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_recibido_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUser;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_recibido_tbl";
    }    
//FIN BUSCAR EXPEDIENTE RECIBIDOS TABLA ALTA DIRECCIÓN   
//    
//INICIO BUSQUEDA DE EXPEDIENTES DERIVADOS POR DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_derivado_dir"}, method = RequestMethod.GET)
    public String MantBuscareDerivadoDir(HttpServletRequest request, HttpServletResponse response,ModelMap model) {  
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","BUSQUEDA DE EXPEDIENTES DERIVADOS POR " + sglUO);
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_derivado_dir";
    }              
//FIN BUSQUEDA DE EXPEDIENTES DERIVADOS POR DIRECCIÓN
//
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA POR DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_derivado_dir_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarDerivadoDirTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut");
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto");
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_derivado_dir_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUO;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_derivado_dir_tbl";
    }    
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA POR DIRECCIÓN      
//
//INICIO BUSQUEDA DE EXPEDIENTES RECIBIDOS POR DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_recibido_dir"}, method = RequestMethod.GET)
    public String MantBuscareRecibidoDir(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","BUSQUEDA DE EXPEDIENTES RECIBIDOS POR " + sglUO);
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_recibido_dir";
    }              
//FIN BUSQUEDA DE EXPEDIENTES RECIBIDOS POR DIRECCIÓN   
//    
//INICIO BUSCAR EXPEDIENTE RECIBIDOS TABLA POR DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_recibido_dir_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarRecibidoDirTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut");
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto");
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_recibido_dir_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUO;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_recibido_dir_tbl";
    }    
//FIN BUSCAR EXPEDIENTE RECIBIDOS TABLA POR DIRECCIÓN
//    
//INICIO BUSQUEDA DE EXPEDIENTES DERIVADOS POR PROFESIONAL
@RequestMapping(value = {"/sgd/mant_buscar_derivado_prof"}, method = RequestMethod.GET)
    public String MantBuscareDerivadoProf(HttpServletRequest request, HttpServletResponse response,ModelMap model) {  
        HttpSession session = request.getSession();
//        String appPers = (String) session.getAttribute("appPers");        
        request.setAttribute("title_pag","BUSQUEDA DE EXPEDIENTES DERIVADOS - PERFIL PROFESIONAL");
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_derivado_prof";
    }              
//FIN BUSQUEDA DE EXPEDIENTES DERIVADOS POR PROFESIONAL
//         
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA POR PROFESIONAL
@RequestMapping(value = {"/sgd/mant_buscar_derivado_prof_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarDerivadoProfTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut").trim();
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto").trim();
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_derivado_prof_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUser;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_derivado_prof_tbl";
    }    
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA POR PROFESIONAL      
//    
//INICIO BUSQUEDA DE EXPEDIENTES RECIBIDOS POR PROFESIONAL
@RequestMapping(value = {"/sgd/mant_buscar_recibido_prof"}, method = RequestMethod.GET)
    public String MantBuscareRecibidoProf(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
//        String appPers = (String) session.getAttribute("appPers");
        request.setAttribute("title_pag","BUSQUEDA DE EXPEDIENTES RECIBIDOS - PERFIL PROFESIONAL");
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_recibido_prof";
    }              
//FIN BUSQUEDA DE EXPEDIENTES RECIBIDOS POR PROFESIONAL
//    
//INICIO BUSCAR EXPEDIENTE RECIBIDOS TABLA POR PROFESIONAL
@RequestMapping(value = {"/sgd/mant_buscar_recibido_prof_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarRecibidoProfTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut").trim();
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto").trim();
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_recibido_prof_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUser;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_recibido_prof_tbl";
    }    
//FIN BUSCAR EXPEDIENTE RECIBIDOS TABLA POR PROFESIONAL    
//   
//INICIO BUSQUEDA DE EXPEDIENTES CREADOS POR DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_creado_dir"}, method = RequestMethod.GET)
    public String MantBuscareCreadoDir(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","BUSQUEDA DE EXPEDIENTES CREADOS POR " + sglUO);
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_creado_dir";
    }              
//FIN BUSQUEDA DE EXPEDIENTES CREADOS POR DIRECCIÓN
//    
//INICIO BUSCAR EXPEDIENTE CREADOS TABLA POR DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_creado_dir_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarCreadoDirTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut");
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto");
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_creado_dir_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUO;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_creado_dir_tbl";
    }    
//FIN BUSCAR EXPEDIENTE CREADOS TABLA POR DIRECCIÓN    
//   
//INICIO BUSQUEDA DE EXPEDIENTES CREADOS POR PROFESIONAL
@RequestMapping(value = {"/sgd/mant_buscar_creado_prof"}, method = RequestMethod.GET)
    public String MantBuscareCreadoProf(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
//        String appPers = (String) session.getAttribute("appPers");
        request.setAttribute("title_pag","EXPEDIENTES CREADOS - PERFIL PROFESIONAL");
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_creado_prof";
    }              
//FIN BUSQUEDA DE EXPEDIENTES CREADOS POR PROFESIONAL
//    
//INICIO BUSCAR EXPEDIENTE CREADOS TABLA POR PROFESIONAL
@RequestMapping(value = {"/sgd/mant_buscar_creado_prof_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarCreadoProfTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut").trim();
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto").trim();
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_creado_prof_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUser;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_creado_prof_tbl";
    }    
//FIN BUSCAR EXPEDIENTE CREADOS TABLA POR PROFESIONAL    
//   
//INICIO BUSQUEDA DE EXPEDIENTES CREADOS POR ALTA DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_creado"}, method = RequestMethod.GET)
    public String MantBuscarCreado(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","EXPEDIENTES CREADOS POR " + sglUO);
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorg_sgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_creado";
    }              
//FIN BUSQUEDA DE EXPEDIENTES CREADOS POR ALTA DIRECCIÓN
//      
//INICIO BUSCAR EXPEDIENTE CREADOS TABLA POR ALTA DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_buscar_creado_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarCreadoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut");
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto");
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_creado_consulta";
    String array[] = new String[5];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();
    ///////////////////////////////////////////////////////

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_creado_tbl";
    }    
//FIN BUSCAR EXPEDIENTE CREADOS TABLA POR ALTA DIRECCIÓN    
//       
//INICIO GUARDA FECHA ACUSE (expedientes consultados)
@RequestMapping(value = {"/sgd/mant_acuse_guardar"}, method = RequestMethod.GET)
    public String MantAcuseGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id_flujo = request.getParameter("id_flujo");
        String fecha_acuse = request.getParameter("fecha_acuse");
        String var_request = "";

        try {                    
            ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_acuse_mant";
            String array[] = new String[2];
            array[0] = id_flujo;
            array[1] = fecha_acuse;
            
            Vector datos = cdb.EjecutarProcedurePostgres(np, array);

            var_request = new Util().vector2json(datos);
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("request", var_request);
        return "sgd/mant_acuse_guardar";    
    }    
//FIN GUARDA FECHA ACUSE (expedientes consultados)   
//        
//INICIO BUSQUEDA DOCUMENTOS X OFICINA/UF DIRECCIÓN (PERFIL: 2/3)
@RequestMapping(value = {"/sgd/mant_rep_uo_pendiente"}, method = RequestMethod.GET)
    public String MantExpUoPendiente(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","EXPEDIENTES POR DIRECCIÓN/UNID.FUNCIONAL/OFICINA");
            
            ConeccionDB cn = new ConeccionDB();
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);
            
//            información para el combo unidad orgánica
            String uo = "senamhi.fn_unidorgsgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);
            
        return "sgd/mant_rep_uo_pendiente";
    }              
//FIN BUSQUEDA DOCUMENTOS X OFICINA/UF DIRECCIÓN (PERFIL: 2/3)
//      
//INICIO BUSQUEDA DOCUMENTOS X OFICINA/UF DIRECCIÓN (PERFIL: 2/3) TABLA
@RequestMapping(value = {"/sgd/mant_rep_uo_pendiente_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryRepUoPendienteTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
//    HttpSession session = request.getSession();
//    String codUser = (String) session.getAttribute("codUser");
//    String codUO = (String) session.getAttribute("codUO");
    
    String uo = request.getParameter("uo");    
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_rep_uo_pendiente_consulta";
    String array[] = new String[1];
    array[0] = uo;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String cut_exp = vss.get(1).toString();//id expediente
        String cant = vss.get(4).toString();                          
        String fec_envio = vss.get(3).toString();                          
        
        Vector vv = new Vector();
            vv.add(i+1);
            vv.add(cut_exp);            
            vv.add(fec_envio);            
            vv.add(cant);            
            v_temp.add(vv); 
    }
    Vector v_tbl_cab =  new Vector();
    v_tbl_cab.add("ITEM");
    v_tbl_cab.add("N° CUT");
    v_tbl_cab.add("FECHA DE RECEPCIÓN");
    v_tbl_cab.add("CONSULTADO A LOS (DIAS)");

    Util u = new Util();
    String tbl =  u.tbl(v_tbl_cab, v_temp);
    request.setAttribute("response",tbl);
    
    //cuenta los consultados
//    String cc = "sgd.fn_rep_uo_cuenta_consultado";
//    String array_cc[] = new String[1];
//    array_cc[0] = uo;
//    String cuenta_c = "";
//    Vector v_datos_cc = cn.EjecutarProcedurePostgres(cc, array_cc);
//     for(int i = 0 ; i<v_datos_cc.size() ; i++){            
//        Vector vss =  (Vector) v_datos_cc.get(i);
//        cuenta_c = vss.get(0).toString();//cuenta        
//    }
//    request.setAttribute("cuenta_c",cuenta_c); 
    
    return "sgd/mant_rep_uo_pendiente_tbl";
    }    
//FIN BUSQUEDA DOCUMENTOS X OFICINA/UF DIRECCIÓN (PERFIL: 2/3) TABLA
// 
//INICIO CUENTA PENDIENTE
@RequestMapping(value = {"/sgd/mant_cuenta_pendiente"}, method = RequestMethod.GET)
    public String MantRemiteCuentaPendiente(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String uo = request.getParameter("uo");   
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB(); 
            String np = "sgd.fn_rep_uo_cuenta_pendiente";
            String array[] = new String[1];
            array[0] = uo;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);                        
            var_request = new Util().vector2json(datos); 
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        
        return "sgd/mant_cuenta_pendiente";
    }         
//FIN CUENTA PENDIENTE 
// 
//INICIO CUENTA CONSULTADOS
@RequestMapping(value = {"/sgd/mant_cuenta_consultado"}, method = RequestMethod.GET)
    public String MantRemiteCuentaConsultado(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String uo = request.getParameter("uo");   
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB(); 
            String np = "sgd.fn_rep_uo_cuenta_consultado";
            String array[] = new String[1];
            array[0] = uo;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);                        
            var_request = new Util().vector2json(datos); 
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        
        return "sgd/mant_cuenta_consultado";
    }         
//FIN CUENTA CONSULTADOS 
// 
//INICIO CUENTA NO CONSULTADOS
@RequestMapping(value = {"/sgd/mant_cuenta_noconsultado"}, method = RequestMethod.GET)
    public String MantRemiteCuentaNoConsultado(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String uo = request.getParameter("uo");   
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB();
            String np = "sgd.fn_rep_uo_cuenta_noconsultado";
            String array[] = new String[1];
            array[0] = uo;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);                        
            var_request = new Util().vector2json(datos);                
                   
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        
        return "sgd/mant_cuenta_noconsultado";
    }
//FIN CUENTA NO CONSULTADOS
//
//INICIO REPORTE GRAFICO POR OFICINA / PERIODO
@RequestMapping(value = {"/sgd/mant_rep_uo_periodo"}, method = RequestMethod.GET)
    public String MantExpUoPeriodo(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","REPORTE ESTADÍSTICO POR DIRECCIÓN/UNID.FUNCIONAL/OFICINA POR ESTADO");
                    
            ConeccionDB cn = new ConeccionDB();
            Util util =  new Util();
            
//            información para el combo unidad orgánica
            String uo = "senamhi.fn_unidorgsgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);
            
            Date date = new Date();//Fecha actual
            DateFormat fechoy = new SimpleDateFormat("dd/mm/aaaa");
            String hoy = fechoy.format(date);
            
            request.setAttribute("hoy", hoy);
                        
        return "sgd/mant_rep_uo_periodo";
    }
//FIN REPORTE GRAFICO POR OFICINA / PERIODO
//          
//INICIO REPORTE UO POR PERIODO GRÁFICO
@RequestMapping(value = {"/sgd/mant_rep_uo_periodo_grf"}, method = RequestMethod.GET)
    public String MantRepUoPeriodoGrf(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String uo = request.getParameter("uo");   
        String f_ini = request.getParameter("f_ini");   
        String f_fin = request.getParameter("f_fin");   
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB();
            String np = "sgd.fn_rep_uo_periodo_grf";
            String array[] = new String[3];
            array[0] = uo;
            array[1] = f_ini;
            array[2] = f_fin;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);                        
            var_request = new Util().vector2json(datos); 
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        
        return "sgd/mant_rep_uo_periodo_grf";
    }
//FIN REPORTE UO POR PERIODO GRÁFICO
//
//INICIO REPORTE GRAFICO POR OFICINA / PROFESIONALES
@RequestMapping(value = {"/sgd/mant_rep_uo_prof"}, method = RequestMethod.GET)
    public String MantExpUoProf(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","REPORTE ESTADÍSTICO POR DIRECCIÓN/UNID.FUNCIONAL/OFICINA Y PROFESIONAL");
                    
            ConeccionDB cn = new ConeccionDB();
            Util util =  new Util();
            
//            información para el combo unidad orgánica
            String uo = "senamhi.fn_unidorgsgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);
                        
        return "sgd/mant_rep_uo_prof";
    }
//FIN REPORTE GRAFICO POR OFICINA / PROFESIONALES
//              
//INICIO REPORTE UO PROFESIONAL GRÁFICO
@RequestMapping(value = {"/sgd/mant_rep_uo_prof_grf"}, method = RequestMethod.GET)
    public String MantRepUoProfGrf(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {   
        
        String uo = request.getParameter("uo"); 
        String var_request = "";
        try {
            ConeccionDB cn = new ConeccionDB();
            String np = "sgd.fn_rep_uo_prof_grf";
            String array[] = new String[1];
            array[0] = uo;
            Vector datos = cn.EjecutarProcedurePostgres(np, array);                        
            var_request = new Util().vector2json(datos); 
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        request.setAttribute("response", var_request);
        
        return "sgd/mant_rep_uo_prof_grf";
    }
//FIN REPORTE UO PROFESIONAL GRÁFICO
//
//INICIO BUSQUEDA DE EXPEDIENTES UTF
@RequestMapping(value = {"/sgd/mant_buscar_exp_utf"}, method = RequestMethod.GET)
    public String MantBuscarExpUtf(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","EXPEDIENTES");
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
            String uo = "senamhi.fn_unidorgsgl_consulta";
            String array_cbo_uo[] = new String[1];
            array_cbo_uo[0] = "";
            Vector datos_cbo_uo = cn.EjecutarProcedurePostgres(uo, array_cbo_uo);            
            String cb_uo = util.contenido_combo(datos_cbo_uo, "");
            request.setAttribute("cb_uo", cb_uo);   
                        
        return "sgd/mant_buscar_exp_utf";
    }              
//FIN BUSQUEDA DE EXPEDIENTES UTF
//      
//INICIO BUSQUEDA DE EXPEDIENTES UTF TABLA
@RequestMapping(value = {"/sgd/mant_buscar_exp_utf_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryBuscarExpUtfTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUser = (String) session.getAttribute("codUser");
//    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut").trim();      
    String anio = request.getParameter("anio");      
    String asun = request.getParameter("asunto").trim();      
    String cd = request.getParameter("cd");      
    String nro = request.getParameter("nro");      
    String uo = request.getParameter("uo");      
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_buscar_exp_uft_consulta";
    String array[] = new String[6];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = uo;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();
        String ruta = vss.get(15).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_mant_cargo_popup(\""+cut_exp+"\",\""+id_doc+"\",\""+documento+"\",\""+ruta+"\",\""+per_exp+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'REMITE'} , "
                            + "{'sTitle':'DESTINO'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_buscar_exp_utf_tbl";
    }    
//FIN BUSQUEDA DE EXPEDIENTES UTF TABLA
//      
//INICIO PROCEDIMIENTO MANTENIMIENTO POPUP    
    @RequestMapping(value = {"/sgd/mant_cargo_popup"}, method = RequestMethod.GET)
    public String MantCargoPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {
            request.setAttribute("title_pag","REGISTRO DE CARGOS"); 
        try {
            String anio = request.getParameter("anio");          
            String cut_exp = request.getParameter("cut_exp");          
            String id_doc = request.getParameter("id_doc");          
            String documento = request.getParameter("documento");          
            String ruta = request.getParameter("ruta");          
                        
            request.setAttribute("anio", anio);
            request.setAttribute("cut_exp", cut_exp);
            request.setAttribute("id_doc", id_doc);
            request.setAttribute("documento", documento);
            request.setAttribute("ruta", ruta);
            
   
        } catch (Exception ex) {
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sgd/mant_cargo_popup";
    }   
//FIN PROCEDIMIENTO MANTENIMIENTO POPUP
//
//INICIO CARGO GUARDA
    @RequestMapping(value = {"/sgd/mant_cargo_consulta"}, method = RequestMethod.GET)
    public String MantCargoGuardar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ServletException, IOException {

        String id_doc = request.getParameter("id_doc");
        String var_request = "";

        try {        
        ConeccionDB cdb = new ConeccionDB(); 
            String np = "sgd.fn_docadjunto_consulta";
            String array[] = new String[1];
            array[0] = id_doc;

        Vector datos = cdb.EjecutarProcedurePostgres(np, array);

        var_request = new Util().vector2json(datos);
            
        } catch (Exception ex) {
            var_request = ex.getMessage();
            Logger.getLogger(SgdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("response", var_request);
        return "sgd/mant_cargo_consulta";
    }
//FIN CARGO GUARDAR 
//
//INICIO SUBIR CARGOS 
    @RequestMapping(value = {"/sgd/uploadfile_cargo"}, method = RequestMethod.POST)
    public String uploadfileCargo(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
//        String anio = request.getParameter("anio");
//        String id_doc = request.getParameter("id_doc");
        String ruta = request.getParameter("ruta");        
        
        ConeccionDB cdb = new ConeccionDB();
        
//        String UPLOAD_DIRECTORY = "/opt/glassfish4/glassfish/domains/domain1/applications/files/sgd"; 
        String UPLOAD_DIRECTORY = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/sgd"; 
        String msj = "";
                
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                
                if (isMultipart) {
                    // Create a factory for disk-based file items
                    FileItemFactory factory = new DiskFileItemFactory();
                    
                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    
                    try {
                        // Parse the request                        
                        List items = upload.parseRequest(request);
                        Iterator iterator = items.iterator();
                        while (iterator.hasNext()) {
                            FileItem item = (FileItem) iterator.next();
                            if (!item.isFormField()) {
                                String fileName = item.getName();  
                                String field = item.getFieldName();
                                    StringTokenizer str =  new StringTokenizer(field,"|");
                                    String anio = str.nextToken();
                                    String nrocut = str.nextToken();
                                    String iddoc = str.nextToken();
                                    String idn = str.nextToken();
                                    String dir = ""+anio+"_"+nrocut+"_"+iddoc+"";
                                    dir = DigestUtils.md5Hex(dir);                               
                                
                                fileName = DigestUtils.md5Hex(fileName);
                                File path = new File(UPLOAD_DIRECTORY+"/"+anio+"/"+"/"+ruta);
                                if (!path.exists()) {
                                    boolean status = path.mkdirs();
                                }
                                
                                File uploadedFile = new File(path +"/"+ fileName+".pdf"); 
                                msj += uploadedFile.getAbsolutePath()+"";
                                item.write(uploadedFile);
                                
                                String np = "sgd.fn_docadjunto_cargo_mant_insert";            
                                String array[] = new String[3];
                                array[0] = iddoc;
                                array[1] = fileName+".pdf";
                                array[2] = ruta;
                                
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
//        request.setAttribute("request",msj);

        return "sgd/uploadfile_cargo";
    }       
//FIN SUBIR CARGOS 
//        
//INICIO BUSQUEDA DE EXPEDIENTE POR DIRECCIÓN 
@RequestMapping(value = {"/sgd/mant_expediente_dir"}, method = RequestMethod.GET)
    public String MantExpedienteDir(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        HttpSession session = request.getSession();
        String sglUO = (String) session.getAttribute("sglUO");
        request.setAttribute("title_pag","EXPEDIENTES POR DIRECCIÓN: " + sglUO);
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
            
            String clsdoc = "sgd.fn_clasifdoc_consulta";
            String array_cbo_clsdoc[] = new String[1];
            array_cbo_clsdoc[0] = "";
            Vector datos_cbo_clsdoc = cn.EjecutarProcedurePostgres(clsdoc, array_cbo_clsdoc);            
            String cb_clsdoc = util.contenido_combo(datos_cbo_clsdoc, "");
            request.setAttribute("cb_clsdoc", cb_clsdoc);   
            
//            información combo envia (el que realiza la derivación)
            String pers = "senamhi.fn_personal_consulta";//PERSONAL CONSULTADO ID_USER, NOMBRE DEL EMPLEADO
            String array_cbo_personal[] = new String[1];
            array_cbo_personal[0] = "";
            Vector datos_cbo_personal = cn.EjecutarProcedurePostgres(pers, array_cbo_personal);            
            String cb_personal = util.contenido_combo(datos_cbo_personal, "");
            request.setAttribute("cb_personal", cb_personal);   
                        
        return "sgd/mant_expediente_dir";
    }              
//FIN BUSQUEDA DE EXPEDIENTE POR DIRECCIÓN
//
//INICIO BUSCAR EXPEDIENTE POR DIRECCIÓN
@RequestMapping(value = {"/sgd/mant_expediente_dir_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryExpedienteDirTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        
    HttpSession session = request.getSession();
    String codUO = (String) session.getAttribute("codUO");
    
    String cut = request.getParameter("cut");
    String anio = request.getParameter("anio");
    String asun = request.getParameter("asunto");
    String cd = request.getParameter("cd");
    String nro = request.getParameter("nro");
    String envia = request.getParameter("envia");
    String recibe = request.getParameter("recibe");
        
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_expedientegeneral_dir_consulta";
    String array[] = new String[8];
    array[0] = cut;
    array[1] = anio;
    array[2] = asun;
    array[3] = cd;
    array[4] = nro;
    array[5] = codUO;
    array[6] = envia;
    array[7] = recibe;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i);
        String i_id = vss.get(0).toString();//id expediente
        String cut_exp = vss.get(1).toString();
        String per_exp = vss.get(2).toString();
        String fec_reg = vss.get(3).toString();
        String des_origen = vss.get(4).toString();
        String des_tema = vss.get(5).toString();
        String des_proc = vss.get(6).toString();        
        String documento = vss.get(7).toString();
        String exp_agrupados = vss.get(8).toString();
        String asunto = vss.get(9).toString();
        String id_alcance = vss.get(10).toString();
        String des_alcance = vss.get(11).toString();
        String id_doc = vss.get(12).toString();
        String origen = vss.get(13).toString();
        String destino = vss.get(14).toString();
        String fec_envio = vss.get(15).toString();

        String btn = "<div class='text-center'>"
                + "<button type='button' class='btn btn-info' onclick='sgd_expediente_buscar_popup(\""+i_id+"\",\""+id_doc+"\")'>"
                + "<span class='glyphicon glyphicon-search'></span>"
                + "</button>"
                + "</div>";
        Vector vv = new Vector();
            vv.add(btn);
            vv.add(cut_exp);
            vv.add(per_exp);
            vv.add(des_alcance);
            vv.add(fec_reg);
            vv.add(documento);
            vv.add(asunto);
            vv.add(des_origen);
            vv.add(des_tema);
            vv.add(des_proc);
            vv.add(origen);
            vv.add(destino);
            vv.add(exp_agrupados);
            vv.add(fec_envio);
            v_temp.add(vv); 
    }
    Util util = new Util();
    String json = util.vector2json_tbl(v_temp);   
    Vector vc_tbl = new Vector();
    Vector sv =  new Vector();
    sv.add("bScrollCollapse");sv.add("true");vc_tbl.add(sv);sv =  new Vector();
    sv.add("sScrollY");sv.add("'93%'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aoColumns");sv.add("[" 
                            + "{'sTitle':'-'} , "
                            + "{'sTitle':'N° CUT'} , "
                            + "{'sTitle':'PERIODO'} , "
                            + "{'sTitle':'ALCANCE'} , "
                            + "{'sTitle':'FECHA REG.'} , "
                            + "{'sTitle':'DOCUMENTO'} , "
                            + "{'sTitle':'ASUNTO'} , "
                            + "{'sTitle':'ORIGEN'} , "
                            + "{'sTitle':'TEMA'} , "
                            + "{'sTitle':'PROCEDIMIENTO'} , "
                            + "{'sTitle':'ENVÍA'} , "
                            + "{'sTitle':'RECIBE'} , "
                            + "{'sTitle':'EXP. AGRUPADOS'},  "
                            + "{'sTitle':'FEC.ENVIO'}  "
                            
                            + "]");vc_tbl.add(sv);sv =  new Vector();
    sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//      sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1,4,5,6]},{'aTargets':[ 10 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
    //boton de excel
    //    sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
    sv.add("dom");sv.add("'<\"row\"<\"col-xs-6\"B><\"col-xs-6\"f>><\"row\"<\"col-xs-12 \"p>>rt<\"bottom\"><\"clear\">'");vc_tbl.add(sv);sv =  new Vector();
//    sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
    sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }]");vc_tbl.add(sv);sv =  new Vector();
    ////Pintar de rojo el registro si no t.iene datos
    String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                    " if (rtrim(aData[2]) == 'CONFIDENCIAL'){$('td', nRow).addClass('ui-state-error' );} " +                     
                  "}";
    sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

    String tbl_html = "<table border='1' class='table table-striped table-bordered dt-responsive' id='c_tbl_buscar_expediente'></table>";
    String tbl = util.datatable("c_tbl_buscar_expediente",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_expediente_dir_tbl";
    }    
//FIN BUSCAR EXPEDIENTE POR DIRECCIÓN    
//    
//INICIO BUSQUEDA DE EXPEDIENTE ATENCIÓN AL CIUDADANO
@RequestMapping(value = {"/sgd/mant_atencion_ciudadano"}, method = RequestMethod.GET)
    public String MantAtencionCiudadano(HttpServletRequest request, HttpServletResponse response,ModelMap model) {        
        request.setAttribute("title_pag","ATENCIÓN AL CIUDADANO");
                    
            ConeccionDB cn = new ConeccionDB();               
            Util util =  new Util();
//          información para el combo periodo (año)
            String ne = "sgd.fn_exp_anio_consulta";
            String array_cbo_est[] = new String[1];
            array_cbo_est[0] = "";
            Vector datos_cbo_per = cn.EjecutarProcedurePostgres(ne, array_cbo_est);            
            String cb_periodo = util.contenido_combo(datos_cbo_per, "");
            request.setAttribute("cb_periodo", cb_periodo);   
                                                
        return "sgd/mant_atencion_ciudadano";
    }
//FIN BUSQUEDA DE EXPEDIENTE ATENCIÓN AL CIUDADANO
//       
//INICIO BUSCAR EXPEDIENTE ATENCIÓN AL CIUDADANO TABLA
@RequestMapping(value = {"/sgd/mant_atencion_ciudadano_tbl"}, method = RequestMethod.GET)
    public String AjaxQueryExpedienteAtencionCiudadanoTbl(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
         
    String exp = request.getParameter("exp");
    String anio = request.getParameter("anio");
    
    ConeccionDB cn =  new ConeccionDB();        
    String np = "sgd.fn_atencion_ciudadano_consulta";
    String array[] = new String[2];
    array[0] = exp;
    array[1] = anio;
    Vector v_datos = cn.EjecutarProcedurePostgres(np, array);
    Vector v_temp = new Vector();
    for(int i = 0 ; i<v_datos.size() ; i++){            
        Vector vss =  (Vector) v_datos.get(i); 
        String origen = vss.get(0).toString();  
        String fec_envio = vss.get(1).toString(); 
        String observaciones = vss.get(2).toString();     
        String destino = vss.get(3).toString();
        String estado = vss.get(4).toString();
        
        Vector vv = new Vector();
            vv.add(fec_envio);
            vv.add(origen);
            vv.add(observaciones);
            vv.add(destino);
            vv.add(estado);
            v_temp.add(vv); 
    }
     Util util = new Util();
            String json = util.vector2json(v_temp);   
            Vector vc_tbl = new Vector();
            Vector sv =  new Vector();
            sv.add("bScrollCollapse");
            sv.add("true");
            vc_tbl.add(sv);
            sv =  new Vector();
            sv.add("sScrollY");
            sv.add("'80%'");
            vc_tbl.add(sv);
            sv =  new Vector();
            sv.add("aoColumns");sv.add("["                                    
                                    + "{'sTitle':'FECHA ENVÍO'} , "
                                    + "{'sTitle':'OF.ENVÍA'} , "
                                    + "{'sTitle':'OBSERVACIONES'} , "
                                    + "{'sTitle':'OF.RECIBE'} , "
                                    + "{'sTitle':'ESTADO'}  "
                                    + "]");vc_tbl.add(sv);sv =  new Vector();
            sv.add("aaData");sv.add(json);vc_tbl.add(sv);sv =  new Vector();
//            sv.add("aoColumnDefs");sv.add("[{'sClass':'center','aTargets':[0,1]},{'aTargets':[ 2 ],'bVisible': false,'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("aoColumnDefs");sv.add("[{'className':'dt-head-center','aTargets':[2]},{'bSearchable': false}]");vc_tbl.add(sv);sv =  new Vector();
            //boton de excel
            sv.add("dom");sv.add("'Bfrtip'");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("buttons");sv.add("['excel']");vc_tbl.add(sv);sv =  new Vector();
//            sv.add("columns");sv.add("[{ name:'EXPEDIENTE',name:'FECHA RECEP.ORH' }]");
            sv.add("buttons");sv.add("[{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm',exportOptions:{columns:[1,2,3,4,5]} },"
//                                    + "{ extend:'pdf',text:'Exportar a PDF',className:'btn btn-info btn-sm',title:'Secretaría Técnica del Procedimiento Administrativo Disciplinario - PAD',exportOptions:{columns:[1,2,3,4,5,6,7]},orientation:'landscape',pageSize:'A4' },"
//                                    + "{ extend:'print',text:'imprimir',className:'btn btn-info btn-sm',title:'Secretaría Técnica del Procedimiento Administrativo Disciplinario - PAD',messageTop:'REPORTE DE EXPEDIENTES',exportOptions:{columns:[1,2,3,4,5,6,7]} }"
                                    + " ]");
            vc_tbl.add(sv);
            sv =  new Vector();
            ////Pintar de rojo si está como pendiente
            String fnc = "function( nRow, aData, iDisplayIndex ){ "+
                            " if (rtrim(aData[4]) == 'PENDIENTE'){$('td', nRow).addClass('ui-state-error' );} " +                     
                          "}";
            sv.add("fnRowCallback");sv.add(fnc);vc_tbl.add(sv);sv =  new Vector();

            String tbl_html = "<table border='1' class='table table-striped table-bordered table-responsive-sm' id='c_tbl_atencion_ciudadano'></table>";
    String tbl = util.datatable("c_tbl_atencion_ciudadano",vc_tbl);            
    request.setAttribute("response", tbl_html + tbl);

    return "sgd/mant_atencion_ciudadano_tbl";
    }    
//FIN BUSCAR EXPEDIENTE ATENCIÓN AL CIUDADANO TABLA
//               
}

