package com.senamhi.sis.controller;

import com.senamhi.sis.connection.ConeccionDB;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
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
import javax.imageio.ImageIO;



@Controller
@RequestMapping("/")
public class SisgemController {

    @RequestMapping(value = { "/sisgem/mant_registro_planilla"}, method = RequestMethod.GET)
    public String SisgemRegistroPlanilla(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        request.setAttribute("title_pag","Registro de Planillas digitalizadas"); 
        return "sisgem/mant_registro_planilla";
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
//                            File path = new File(ruta_archi + dz +"/"+ anio +"/"+ estacion +"/"+ tplanilla);
                            
//                            File path = new File(UPLOAD_DIRECTORY+"/"+anio);
                            File path = new File(UPLOAD_DIRECTORY+"/"+ dz +"/"+ anio +"/"+ estacion +"/"+ tplanilla);
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
        request.setAttribute("title_pag","Planillas con códigos errados");        
        
        return "sisgem/mant_lista_errada";
    } 
    //FIN Lista nomenclatura errada 
    //
    //INICIO COPIAR ARCHIVO
    @RequestMapping(value = { "/sisgem/mant_copyfile"}, method = RequestMethod.GET)
    public String SisgemCopyFile(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        
        String fileName_errado = request.getParameter("p_errado");
        String fileName = request.getParameter("nom_archi");
        String tinterf = request.getParameter("p_tinterf");
        String ruta_archi = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/sisgem/";
        String ruta_tmp = "";
        if (tinterf.equals("E")){        
            ruta_tmp = "/home/glassfish/glassfish4/glassfish/domains/domain1/applications/files/sisgem/DirTem/"; 
        }else if(tinterf.equals("V")){
            String dz_e = fileName_errado.substring(0,2);
            String estacion_e = fileName_errado.substring(2,8);
            String tplanilla_e = fileName_errado.substring(8,10);
            String anio_e = fileName_errado.substring(12,16);
            
            ruta_tmp = ruta_archi + dz_e +"/"+ anio_e +"/"+ estacion_e +"/"+ tplanilla_e + "/";
        }        
        
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
    //
    //INICIO BUSCAR PLANILLA
    @RequestMapping(value = { "/sisgem/mant_buscar_planilla"}, method = RequestMethod.GET)
    public String SisgemBuscarPlanilla(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        request.setAttribute("title_pag","Buscar Planilla");     
        
        return "sisgem/mant_buscar_planilla";
    }     
    //FIN BUSCAR PLANILLA
    //
    //INICIO CONVIERTE TIFF A JPG
    @RequestMapping(value = { "/sisgem/mant_convtiff"}, method = RequestMethod.GET)
    public String SisgemConvTiff(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
            String url_tiff = request.getParameter("url");
            URL url = new URL(url_tiff); 

            ImageDecoder dec = ImageCodec.createImageDecoder("tiff", url.openStream() ,null);                
            RenderedImage tif = dec.decodeAsRenderedImage(0);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(tif, "jpg", baos);
            
            byte[] bytes = baos.toByteArray();            
            response.setContentType("image/jpg");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();        
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
            baos.close();
        return "sisgem/mant_convtiff";
    }
    //FIN CONVIERTE TIFF A JPG
    //
    //INICIO BUSCAR PLANILLA EN MAPA
    @RequestMapping(value = { "/sisgem/mant_buscar_planilla_mapa"}, method = RequestMethod.GET)
    public String SisgemBuscarPlanillaMapa(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        request.setAttribute("title_pag","Buscar Planilla con Mapa");     
        
        return "sisgem/mant_buscar_planilla_mapa";
    }     
    //FIN BUSCAR PLANILLA  EN MAPA
    //
    //INICIO MOSTRAR MAPA
    @RequestMapping(value = {"/sisgem/mant_mapa_mostrar"}, method = RequestMethod.GET)
        public String MantMapaMostrar(HttpServletRequest request, HttpServletResponse response,ModelMap model) { 

//            String cod_estacion = request.getParameter("cod_estacion");          
            String cod_dpto = request.getParameter("cod_dz");
            
                //tabla de datos
                String array[] = new String[1];
//                array[0] = cod_estacion;
                array[0] = cod_dpto;
                Vector datos = new ConeccionDB().EjecutarProcedureOracle("PKG_GEM.SP_OBT_ESTA", array);

                //mapa
                String c_mapa = "<script>pintarmapa('div_map');";
                    for(int i=0;i<datos.size();i++){
                        Vector vx = (Vector) datos.get(i);
                        if (!cod_dpto.equals("")){
                            c_mapa += "aniadirmarker('"+vx.get(0)+"','"+vx.get(8)+"','"+vx.get(1)+"','"+( "("+vx.get(1)+")"+ vx.get(9)+"<br>"+vx.get(10))+"','"+vx.get(4)+"','"+vx.get(3)+"','"+vx.get(2)+"','"+vx.get(11)+"','','"+vx.get(9)+"','"+vx.get(5)+"','"+vx.get(6)+"','"+vx.get(7)+"');";
                        }
                    }
                c_mapa += "centrarmapa();";
                c_mapa += "</script>";
                request.setAttribute("response", c_mapa);

            return "sisgem/mant_mapa_mostrar";
    }
    //FIN MOSTRAR MAPA
    //
    //INICIO BUSCAR PLANILLA
    @RequestMapping(value = { "/sisgem/mant_buscar_planilla_dz"}, method = RequestMethod.GET)
    public String SisgemBuscarPlanillaDz(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        request.setAttribute("title_pag","Buscar Planilla por DZ");
        
        return "sisgem/mant_buscar_planilla_dz";
    }     
    //FIN BUSCAR PLANILLA
    //  
    //INICIO EDITAR PLANILLA
    @RequestMapping(value = { "/sisgem/mant_edita_planilla"}, method = RequestMethod.GET)
    public String SisgemEditaPlanilla(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        request.setAttribute("title_pag","Planillas Registradas");
        
        return "sisgem/mant_edita_planilla";
    }
    //FIN EDITAR PLANILLA
    //
    ////INICIO LISTA DE USUARIOS
    @RequestMapping(value = { "/sisgem/mant_lista_usuarios"}, method = RequestMethod.GET)
    public String SisgemListaUsuarios(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
        request.setAttribute("title_pag","Lista de Usuarios");
        
        return "sisgem/mant_lista_usuarios";
    } 
    //FIN LISTA DE USUARIOS 
    

    
}