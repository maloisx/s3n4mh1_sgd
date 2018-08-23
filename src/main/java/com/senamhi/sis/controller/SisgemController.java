package com.senamhi.sis.controller;

import com.senamhi.sis.connection.ConeccionDB;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class SisgemController {

    @RequestMapping(value = { "/sisgem/mant_registro_planilla"}, method = RequestMethod.GET)
    public String SisbienPrueba(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{

        return "sisgem/mant_registro_planilla";
    } 
    
    @RequestMapping(value = { "/sisgem/mant_buscar_planilla"}, method = RequestMethod.GET)
    public String SisgemBuscarPlanilla(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{

        return "sisgem/mant_buscar_planilla";
    } 
    
    //INICIO SUBIR DOCUMENTOS
    @RequestMapping(value = {"/sisgem/uploadfile"}, method = RequestMethod.POST)
    public String uploadfile(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        ConeccionDB cdb = new ConeccionDB();

        String UPLOAD_DIRECTORY = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/sisgem"; 
        String msj = "";

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//            String dz = "";
//            String estacion = "";
//            String tplanilla = "";
//            String periodo = "";
//            String cara = "";
//            String fecha = "";

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
                            
//                            String dz = fileName.substring(0,2);
//                            String estacion = fileName.substring(2,8);
//                            String tplanilla = fileName.substring(8,10);
//                            String periodo = fileName.substring(10,11);
//                            String cara = fileName.substring(11,12);
//                            String fecha = fileName.substring(12,20);
                            String anio = fileName.substring(12,16);

//                            String anio = "cutervo";                                    
//
//                            fileName = DigestUtils.md5Hex(fileName);
                            File path = new File(UPLOAD_DIRECTORY+"/"+anio);
                            if (!path.exists()) {
                                boolean status = path.mkdirs();
                            }

                            File uploadedFile = new File(path +"/"+ fileName); 
                            msj += uploadedFile.getAbsolutePath()+"";
                            item.write(uploadedFile);

//                            String np = "pad.fn_docadjunto_mant";            
//                            String array[] = new String[4];
//                            array[0] = iddoc;
//                            array[1] = fileName+".pdf";
//                            array[2] = dir;
//                            array[3] = fileName_1;
//                            Vector datos = cdb.EjecutarProcedurePostgres(np, array); 
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
        return "sisgem/uploadfile";
    }  
    //FIN SUBIR DOCUMENTOS   
    
    
}