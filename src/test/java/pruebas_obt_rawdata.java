
import com.senamhi.sis.functions.Util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class pruebas_obt_rawdata {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Vector v =  new Util().obt_rawdata("");
     
    String cod_goes = "472147C4";
    
            Vector v_rawdata = new Vector();        
//            String json_rawdata = "";
            String trama = "";
            String cad_url = "http://eddn.usgs.gov/cgi-bin/retrieveData.pl?DRS_SINCE=now%20-1000%20hours&DRS_UNTIL=now&NETWORKLIST=&DCP_ADDRESS="+cod_goes+"&CHANNEL=&BEFORE=//SOURCE%20GOESTR%20DCP%20DAPS\\n&AFTER=\\n//END\\n&SPACECRAFT=Any&BAUD=Any&ELECTRONIC_MAIL=&DCP_BUL=&GLOB_BUL=&TIMING=&RETRANSMITTED=Y&DAPS_STATUS=Y&";
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
                            String fecha = String.format("%02d", cal.get(Calendar.DATE)) + "/" + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + "/" + cal.get(Calendar.YEAR);
                            String hora = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + ":"+String.format("%02d", cal.get(Calendar.MINUTE));
                            String fecha_full = fecha + " " + hora;
                            v_tmp.add(cod_goes_t);
                            v_tmp.add(fecha_full);
                            v_tmp.add(trama);
                            v_rawdata.add(v_tmp);
//                            System.out.println(cod_goes_t+"\t"+fecha_full +"\t"+ trama);
//                            System.out.println(inputLine);
                        }
                    }                     
                }
            }catch(Exception e){
                System.err.println(e.getMessage());
//                json_rawdata = "ERROR EN DATOS DAWDATA WEB.";
            }

    }
    
}
