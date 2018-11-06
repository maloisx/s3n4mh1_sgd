package com.senamhi.sis.controller;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class DemoController {

    @RequestMapping(value = { "/demo/tiff2jpg"}, method = RequestMethod.GET)
    public String DemoTiff2jpg(HttpServletRequest request, HttpServletResponse response,ModelMap model) 
    throws ServletException, IOException{
     
        String url_tiff = request.getParameter("i");
        URL url = new URL(url_tiff); 

        ImageDecoder dec = ImageCodec.createImageDecoder("tiff", url.openStream() ,null);                
        RenderedImage tif =   dec.decodeAsRenderedImage(0);

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
        
        return "demo/tiff2jpg";
    } 
    
       
    
}