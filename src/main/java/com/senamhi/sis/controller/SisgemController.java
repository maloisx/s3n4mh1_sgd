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

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/")
public class SisgemController {

    @RequestMapping(value = { "/sisgem/mant_registro_planilla"}, method = RequestMethod.GET)
    public String SisbienPrueba(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        request.setAttribute("title_pag","Registro de Planillas");  

        return "sisgem/mant_registro_planilla";
    } 
    
    @RequestMapping(value = { "/sisgem/mant_buscar_planilla"}, method = RequestMethod.GET)
    public String SisgemBuscarPlanilla(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{

        return "sisgem/mant_buscar_planilla";
    } 
    
    //INICIO SUBIR PLANILLAS
    @RequestMapping(value = {"/sisgem/uploadfile"}, method = RequestMethod.POST)
    public String uploadfile(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
                
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
                            String dz = fileName.substring(0,2);
                            String estacion = fileName.substring(2,8);
                            String tplanilla = fileName.substring(8,10);
//                            String periodo = fileName.substring(10,11);
//                            String cara = fileName.substring(11,12);
//                            String fecha = fileName.substring(12,20);
                            String anio = fileName.substring(12,16);

                            File path = new File(UPLOAD_DIRECTORY+"/"+dz+"/"+anio+"/"+estacion+"/"+tplanilla);
                            if (!path.exists()) {
                                boolean status = path.mkdirs();
                            }
                            File uploadedFile = new File(path +"/"+ fileName); 
                            msj += uploadedFile.getAbsolutePath()+" ";
                            item.write(uploadedFile);
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
    //FIN SUBIR PLANILLAS
    
    //INICIO SUBIR PLANILLAS AL DIRECTORIO TEMPORAL
    @RequestMapping(value = {"/sisgem/uploadfiletmp"}, method = RequestMethod.POST)
    public String uploadfiletmp(HttpServletRequest request, HttpServletResponse response,ModelMap model) {

        ConeccionDB cdb = new ConeccionDB();
                
        String UPLOAD_DIRECTORY = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/sisgem"; 
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
                            String tmp = "DirTem";
                            File path = new File(UPLOAD_DIRECTORY+"/"+tmp);
                            if (!path.exists()) {
                                boolean status = path.mkdirs();
                            }
                            File uploadedFile = new File(path +"/"+ fileName); 
                            msj += uploadedFile.getAbsolutePath()+"";
                            item.write(uploadedFile);                       
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
        return "sisgem/uploadfiletmp";
    }  
    //FIN SUBIR PLANILLAS AL DIRECTORIO TEMPORAL  
    //
    //INICIO Lista nomenclatura errada 
    @RequestMapping(value = { "/sisgem/mant_lista_errada"}, method = RequestMethod.GET)
    public String SisgemListaErrada(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{

        return "sisgem/mant_lista_errada";
    } 
    //FIN Lista nomenclatura errada 
    
     //INICIO COPIAR ARCHIVO
    @RequestMapping(value = { "/sisgem/mant_copyfile"}, method = RequestMethod.GET)
    public String SisgemCopyFile(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        String fileName_errado = request.getParameter("p_errado");
        String fileName = request.getParameter("nom_archi");
        String ruta_tmp = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/sisgem/DirTem/"; 
        String ruta_archi = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/sisgem/";
        
        try {
            //Renombrar archivo
            File archivo = new File(ruta_tmp + fileName_errado);
            archivo.renameTo(new File(ruta_tmp + fileName));
            //Códigos para carpetas
            String dz = fileName.substring(0,2);
            String estacion = fileName.substring(2,8);
            String tplanilla = fileName.substring(8,10);
            String anio = fileName.substring(12,16);
            //Mover archivo
            File rutaOriginalFichero = new File(ruta_tmp + fileName);
            Path origenPath = FileSystems.getDefault().getPath(ruta_tmp + fileName);
            //Ruta destino: DZ/AÑO/ESTACION/TIPO_PLANILLA
            File path = new File(ruta_archi + dz +"/"+ anio +"/"+ estacion +"/"+ tplanilla);
            if (!path.exists()) {
                boolean status = path.mkdirs();
            }
//            File uploadedFile = new File(path +"/"+ fileName);
            Path destinoPath = FileSystems.getDefault().getPath(path +"/"+ fileName);
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sisgem/mant_copyfile";
    }
    //FIN OPIAR ARCHIVO
}