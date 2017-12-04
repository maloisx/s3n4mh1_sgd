package com.senamhi.sis.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.senamhi.sis.connection.ConeccionDB;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = { "/login"}, method = RequestMethod.GET)
    public String LoginIndex(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{

        return "login";
    } 
    
    @RequestMapping(value = { "/login/logout"}, method = RequestMethod.GET)
    public String Loginlogout(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.removeAttribute("codUser");
        session.removeAttribute("idPers");
        session.removeAttribute("appPers");
        session.removeAttribute("apmPers");
        session.removeAttribute("nomPers");
        session.removeAttribute("dni");
        session.removeAttribute("desUO");
        session.removeAttribute("codUser");
        session.removeAttribute("sglUO");
        response.sendRedirect(request.getContextPath());
        return "login/logout";
    } 
    
    @RequestMapping(value = { "/login/validarlogin"}, method = RequestMethod.POST)
    public String LoginValidarLogin(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        ConeccionDB cn = new ConeccionDB();   
            
        String np = "seguridad.fn_login_token";
        String array[] = new String[2];
        array[0] = user;
        array[1] = pass;
        
        String var_response = "";
        
        String datos_json = cn.EjecutarProcedurePostgres_JSON(np, array);
        JsonObject datos_Object = new Gson().fromJson(datos_json,JsonObject.class);
        JsonArray obj_data = (JsonArray) datos_Object.get("data");
        JsonArray obj_request = (JsonArray) datos_Object.get("request");
        
        if(obj_data.size()>0){
            JsonObject subdata = (JsonObject) obj_data.get(0);
            var_response = datos_json;
            HttpSession session = request.getSession();
            session.setAttribute("codUser",subdata.get("id_user").getAsString()); 
            session.setAttribute("idPers",subdata.get("id_pers").getAsString()); 
            session.setAttribute("appPers",subdata.get("app_pers").getAsString()); 
            session.setAttribute("apmPers",subdata.get("apm_pers").getAsString()); 
            session.setAttribute("nomPers",subdata.get("nom_pers").getAsString()); 
            session.setAttribute("dni",subdata.get("dni").getAsString()); 
            session.setAttribute("codUO",subdata.get("cod_uo").getAsString()); 
            session.setAttribute("desUO",subdata.get("des_uo").getAsString()); 
            session.setAttribute("sglUO",subdata.get("sgl_uo").getAsString());               
        }
        
        
        
//        Vector datos = cn.EjecutarProcedurePostgres(np, array);
//        
//        String var_response = "0";
//        if(datos.size() > 0 ){
//            var_response = "1";
//            HttpSession session = request.getSession();
//            Vector vs = (Vector) datos.get(0);
//            session.setAttribute("codUser",vs.get(0)); 
//            session.setAttribute("idPers",vs.get(1)); 
//            session.setAttribute("appPers",vs.get(2)); 
//            session.setAttribute("apmPers",vs.get(3)); 
//            session.setAttribute("nomPers",vs.get(4)); 
//            session.setAttribute("dni",vs.get(5)); 
//            session.setAttribute("codUO",vs.get(6)); 
//            session.setAttribute("desUO",vs.get(7)); 
//            session.setAttribute("sglUO",vs.get(8));            
//        }        
        request.setAttribute("response",var_response);
        return "login/validarlogin";
    }  
    
    
    @RequestMapping(value = { "/login/solicitud_reset_pass"}, method = RequestMethod.POST)
    public String LoginSolicitudResetPass(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
    String mail = request.getParameter("mail");
    boolean valid = EmailValidator.getInstance().isValid(mail); 
    String msj = "";
    if(!valid){
        msj = "Formato de mail incorrecto.";
    }else{
        msj = "1";
        ConeccionDB cn = new ConeccionDB();   
            
        String np = "seguridad.fn_datos_mail";
        String array[] = new String[1];
        array[0] = mail;
        Vector datos = cn.EjecutarProcedurePostgres(np, array);
        
        if(datos.size() > 0 ){
            Vector vs = (Vector) datos.get(0);
            String cod = vs.get(0).toString();
            String app = vs.get(1).toString();
            String apm = vs.get(2).toString();
            String nom = vs.get(3).toString();
            String estado = vs.get(4).toString();
            String user = vs.get(5).toString();
            String user_md5 = vs.get(6).toString();
            String clv_md5 = vs.get(7).toString();
            
            if(estado.equals("0")){
                msj ="Usuario desactivado, contactar al administrador."; 
            }else{
                try{
                        // Propiedades de la conexión
                        Properties props = new Properties();
//                        props.setProperty("mail.smtp.host", "smtp.gmail.com");
//                        props.setProperty("mail.smtp.starttls.enable", "true");
//                        props.setProperty("mail.smtp.port","587");
//                        props.setProperty("mail.smtp.user", "maloisx@gmail.com");
//                        props.setProperty("mail.smtp.auth", "true");

                        props.setProperty("mail.smtp.host", "mail.senamhi.gob.pe");
                        props.setProperty("mail.smtp.starttls.enable", "true");
                        props.setProperty("mail.smtp.port", "25");
                        props.setProperty("mail.smtp.user", "dba@senamhi.gob.pe");
                        props.setProperty("mail.smtp.auth", "true");

                        // Preparamos la sesion
                        Session session = Session.getDefaultInstance(props);

                        // Construimos el mensaje
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("app@senamhi.gob.pe"));
                        message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail));
                        message.setSubject("reset de clave");
                        message.setText("Estimado(a) "+nom.toUpperCase()+" :" +
                                        "\n\n" +
                                        "El usuario para acceder a la cuenta es: "+user+
                                        "\n\n" +
                                        "Accediendo al siguiente link adjunto, se procedera al cambio de su contraseña para el ingreso a los sistemas, esta nueva contraseña no debe de exceder de los 20 caracteres y unicamente debe de conocerla el propietario de la cuenta.\n" +
                                        "\n\n" +
                                        "Link : " + "http://sgd.senamhi.gob.pe/sis/login/reset_pass?m="+mail+"&u="+user_md5+"&p="+clv_md5+
//                                        "Link : " + "http://localhost:8085/sis/login/reset_pass?m="+mail+"&u="+user_md5+"&p="+clv_md5+
                                        "\n\n" +
                                        "Atte: OTI.");

                        // Lo enviamos.
                        Transport t = session.getTransport("smtp");
//                        t.connect("maloisx@gmail.com", "**R@dm1n**"); 
                        t.connect("dba@senamhi.gob.pe", "*R@dm1n*");
                        t.sendMessage(message, message.getAllRecipients());
                        // Cierre.
                        t.close();
                    msj = "1";
                }catch(Exception e){
                    msj = e.getMessage();
                }
            }
            
        }else{ 
            msj = "No se encuentran datos con dicho email, contactar al administrador.";
        }
    }
        
        
        request.setAttribute("response",msj);
        return "login/solicitud_reset_pass";
    }  
    
    
    @RequestMapping(value = { "/login/reset_pass"}, method = RequestMethod.GET)
    public String LoginResetPass(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{

        String mail = request.getParameter("m");
        String user_md5 = request.getParameter("u");
        String pass_md5 = request.getParameter("p");
        
         ConeccionDB cn = new ConeccionDB();   
            
        String np = "seguridad.fn_datos_mail";
        String array[] = new String[1];
        array[0] = mail;
        Vector datos = cn.EjecutarProcedurePostgres(np, array);
        if(datos.size() > 0 ){
            Vector vs = (Vector) datos.get(0);
            String bd_cod = vs.get(0).toString();
            String bd_app = vs.get(1).toString();
            String bd_apm = vs.get(2).toString();
            String bd_nom = vs.get(3).toString();
            String bd_estado = vs.get(4).toString();
            String bd_user = vs.get(5).toString();
            String bd_user_md5 = vs.get(6).toString();
            String bd_clv_md5 = vs.get(7).toString();
            
            if(!user_md5.equalsIgnoreCase(bd_user_md5) || !pass_md5.equalsIgnoreCase(bd_clv_md5)){
                request.setAttribute("show_error","");
                request.setAttribute("show_form","display:none");
            }else{
                 request.setAttribute("show_error","display:none");
                request.setAttribute("show_form","");                
                request.setAttribute("nombre",bd_nom);
                request.setAttribute("cod",bd_cod);
                request.setAttribute("hd_u",bd_user_md5);
                request.setAttribute("hd_p",bd_clv_md5);
            }            
        }else{
            request.setAttribute("show_error","");
            request.setAttribute("show_form","display:none");
        }
        
        return "login/reset_pass";
    }
    
    @RequestMapping(value = { "/login/reset_pass_confirm"}, method = RequestMethod.POST)
    public String LoginResetPassConfirm(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{

        String cod = request.getParameter("cod");
        String user_md5 = request.getParameter("u");
        String pass_md5 = request.getParameter("p");
        
        String np = request.getParameter("np");
        
         ConeccionDB cn = new ConeccionDB();   
            
        String p = "seguridad.fn_cambiar_clave";
        String array[] = new String[4];
        array[0] = cod;
        array[1] = user_md5;
        array[2] = pass_md5;
        array[3] = np;
        Vector datos = cn.EjecutarProcedurePostgres(p, array);

        Vector vs = (Vector) datos.get(0);
        String r = vs.get(0).toString();
        request.setAttribute("response",r);

        
        return "login/reset_pass_confirm";
    }    
    
}