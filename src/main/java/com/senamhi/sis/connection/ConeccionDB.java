package com.senamhi.sis.connection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConeccionDB {
    
    
    String oracle_user = "SISDAD";
    String oracle_pass = "SISDAD";
    
    private  String postgres_usuario;
    private  String postgres_password;
    private  String postgres_url;
    private  String postgres_driver;
    private  Integer postgres_initialSize;
    private  Integer postgres_maxTotal;
    
    public ConeccionDB() {
            this.postgres_usuario = "";
            this.postgres_password = "";
            this.postgres_url = "";
            this.postgres_driver = "";
            this.postgres_initialSize = 0;
            this.postgres_maxTotal = 0;
        
        try {            
            ResourceBundle rb = ResourceBundle.getBundle("prop");
            this.postgres_usuario = rb.getString("postgres_usuario");
            this.postgres_password = rb.getString("postgres_password");
            this.postgres_url = rb.getString("postgres_url");
            this.postgres_driver = rb.getString("postgres_driver");
            this.postgres_initialSize = Integer.valueOf(rb.getString("postgres_initialSize"));
            this.postgres_maxTotal = Integer.valueOf(rb.getString("postgres_maxTotal"));
            
//            propiedades.load(new FileInputStream(rb.));
//            this.usuario = propiedades.getProperty("usuario");
//            this.password = propiedades.getProperty("password");
//            this.url = propiedades.getProperty("url");
//            this.driver = propiedades.getProperty("driver");
//            this.initialSize = Integer.valueOf(propiedades.getProperty("initialSize"));
//            this.maxTotal = Integer.valueOf(propiedades.getProperty("maxTotal"));
        } catch (Exception ex) {
            Logger.getLogger(ConeccionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void oracle_login( String v_user, String v_pass ){
        oracle_user = v_user;
        oracle_pass = v_pass;
    }
    
    public Connection CnOracle()
    {
        Connection cn = null;    
        try{        
//        String host= "172.32.0.4";
        String host= "172.25.13.188";
        String sid = "ogeip";
        String user= oracle_user; 
        String pass= oracle_pass;
        String puerto="1521";

        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        cn = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+puerto+":"+sid, user, pass);
//        cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
        }
        catch(Exception e){
           // JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println(e.getMessage());
        }
        return cn;
    }
    
    public Connection CnSQLServer()
    {
        Connection cn = null;    
        try{

        String userName = "senamhi";
            String password = "senamhi";
            String url = "jdbc:sqlserver://172.25.0.48";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url, userName, password);
        }
        catch(Exception e){
//            JOptionPane.showMessageDialog(null, e.getMessage());
             System.err.println("error sqlserver "+e.getMessage());
        }
        return cn;
    }
   
//    public Connection CnPostgres()
//    {
//        Connection cn = null;    
//        try{
//        String userName = "postgres";
//            String password = "s3n4mh1*pg";
//            String url = "jdbc:postgresql://172.25.0.125:5432/senamhi"; 
//            Class.forName("org.postgresql.Driver");
//            cn = DriverManager.getConnection(url,userName, password);
//        }catch (SQLException se) {
//            System.err.println("Threw a SQLException creating the list of blogs.");
//            System.err.println(se.getMessage());
//          }
//        catch(Exception e){
//             System.err.println("error Postgres server "+e.getMessage());
//        }
//        return cn;
//    }
    
    public Connection CnPostgres()
    {
        BasicDataSource ds = new BasicDataSource();
        
//        ds.setDriverClassName("org.postgresql.Driver");
//        ds.setUsername("postgres");
//        ds.setPassword("s3n4mh1*pg");
//        ds.setUrl("jdbc:postgresql://172.25.0.125:5432/senamhi");
//        ds.setInitialSize(5);
//        ds.setMaxTotal(1000);

        ds.setDriverClassName(postgres_driver);
        ds.setUsername(postgres_usuario);
        ds.setPassword(postgres_password);
        ds.setUrl(postgres_url);
        ds.setInitialSize(postgres_initialSize);
        ds.setMaxTotal(postgres_maxTotal);
        
        Connection cn = null;    
        try{
            cn = ds.getConnection();
        }catch (SQLException se) {
            System.err.println("Threw a SQLException creating the list of blogs.");
            System.err.println(se.getMessage());
          }
        catch(Exception e){
             System.err.println("error Postgres server "+e.getMessage());
        }
        return cn;
    }
    
    public Vector EjecutarProcedureOracle(String np ,String array[]){
        
    Vector vr = new Vector();    
     Connection con = this.CnOracle();      
      String cadvar = "";
    try{       
            for(int i = 0 ; i< array.length ; i++){
                cadvar += "?,";
            }            
            
            CallableStatement pstmt = con.prepareCall("{call "+np+"("+cadvar+"?)}");
             for(int i = 0 ; i< array.length ; i++){
                  pstmt.setObject(i+1, array[i]);                
            }  
            pstmt.registerOutParameter(array.length + 1 ,OracleTypes.CURSOR);
            
            pstmt.execute();
            
            ResultSet rs = (ResultSet) pstmt.getObject(array.length + 1);
            ResultSetMetaData rsmeta = rs.getMetaData();
            
            int cantcol = rsmeta.getColumnCount();

            while (rs.next())
            {
                Vector vtemp = new Vector();
                for(int i = 1 ; i<= cantcol ; i++){
                    vtemp.add((rs.getString(i)==null)?"":rs.getString(i)); 
                }
                vr.add(vtemp);
            }
            pstmt.close();
            con.close();
        }
        catch(Exception e){
        try {
            con.close();
//            JOptionPane.showMessageDialog(null,);
            System.err.println("ERROR EN : {call "+np+"("+cadvar+"?)}");
            System.err.println(e.getMessage());
            vr = EjecutarProcedureOracle(np ,array);
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
        return vr;
    }
    
//   public Vector EjecutarProcedureSQLServer(String np){
   public Vector EjecutarProcedureSQLServer(String np ,String array[]){   
    Vector vr = new Vector();    
    Connection con = this.CnSQLServer();       
    try{
         
        String cadvar = "";
//            for(int i = 0 ; i< array.length ; i++){
//                cadvar += "?,";
//            }            
            
            
             for(int i = 0 ; i< array.length ; i++){
                 cadvar += " '"+array[i].toString()+"',";           
            }  
            if(cadvar.length() > 0) 
                cadvar = cadvar.substring(0, cadvar.length()-1);
             
            String ins = "exec "+np + " "+cadvar;
            CallableStatement pstmt = con.prepareCall(ins);
//            pstmt.registerOutParameter(array.length + 1 ,OracleTypes.CURSOR);                        
            pstmt.execute();            
//            ResultSet rs = (ResultSet) pstmt.getObject(array.length + 1);

            ResultSet rs = pstmt.getResultSet();
            ResultSetMetaData rsmeta = rs.getMetaData();
            
            int cantcol = rsmeta.getColumnCount();

            while (rs.next())
            {
                Vector vtemp = new Vector();
                for(int i = 1 ; i<= cantcol ; i++){
                    vtemp.add(rs.getString(i)); 
                }
                vr.add(vtemp);
            }
            pstmt.close();
            con.close();
        }
        catch(Exception e){
        try {
            con.close();
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println(e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
        return vr;
    }

   public Vector EjecutarProcedurePostgres(String np ,String array[]){
        
    Vector vr = new Vector();    
        
    try{
        Connection con =  this.CnPostgres();   
        
        String variables = "";
        int ca = array.length;
        for(int i = 0 ;i<ca;i++){
//            variables += "'"+array[i]+"',";
            variables += "?,";
        }
        variables = variables.substring(0,variables.length()-1);            
        
            con.setAutoCommit(false);
            String q = "{ ? = call "+np+"("+variables+") }";
            CallableStatement pstmt = con.prepareCall(q);   
            
            for(int i = 0 ;i<ca;i++){
                pstmt.setString(i+1,array[i]);
            }            
            pstmt.registerOutParameter((array.length + 1) ,Types.OTHER); 
            pstmt.execute();
            ResultSet rs = (ResultSet) pstmt.getObject((array.length + 1));
            
//            String q = "BEGIN;select "+np+"("+variables+"'r_cursor');FETCH ALL IN r_cursor;COMMIT;";
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(q);
           
            ResultSetMetaData rsmeta = rs.getMetaData();
            
            int cantcol = rsmeta.getColumnCount();
//            System.out.println("cantidad de columnas: " + cantcol);            
            
            while (rs.next())
            {
               //System.out.println(rs.getString(1) + " => " + rs.getString(2));
                Vector vtemp = new Vector();
                String v = "";
                for(int i = 1 ; i<= cantcol ; i++){
                    String tv = rs.getString(i);
                    if(tv == null)
                        vtemp.add(""); 
                    else
                        vtemp.add(tv); 
                                
                        
                }
                vr.add(vtemp);
            }
            con.commit();
            con.setAutoCommit(true);
            con.close();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }    
        return vr;
    } 
   
    public String EjecutarProcedurePostgres_JSON(String np, String array[]) {

        String result_json = "{\"data\":\"[]\",\"request\":\"\"}"; //"[]";
        int cant_resultset = 1;
        String result_json_data = "";
        String result_json_msg = "";
        try {
            Connection con = this.CnPostgres();

            String variables = "";
            
            int ca = array.length ;
            for (int i = 0; i < ca; i++) {
                variables += "?,";
            }
            variables = variables.substring(0, variables.length() - 1);
            
            con.setAutoCommit(false);
//            String q = "{ ? = call " + np + "(" + variables + ") }";
            String q = "SELECT * from " + np + "(" + variables + ")";
            CallableStatement pstmt = con.prepareCall(q);

            for (int i = 0; i < ca; i++) {
                pstmt.setString(i + 1, array[i]);                
            }
//            pstmt.set
//            pstmt.registerOutParameter((array.length + 1), Types.OTHER);
            pstmt.execute();
            ResultSet rs = (ResultSet) pstmt.getResultSet(); 
            while (rs.next()) {
                String nomcursor = rs.getString(1);
                Statement st = con.createStatement();
                ResultSet rs_cur = st.executeQuery("FETCH ALL IN \""+nomcursor+"\"");
                ResultSetMetaData rsmeta = rs_cur.getMetaData();
                int cantcol = rsmeta.getColumnCount();
                    JsonArray json = new JsonArray();
                    while (rs_cur.next()) {
                        JsonObject obj = new JsonObject();
                        for (int i = 1; i <= cantcol; i++) {
                            String column_name = rsmeta.getColumnName(i);
                            String column_val = (rs_cur.getString(i) == null) ? "" : rs_cur.getString(i);
                            obj.addProperty(column_name, column_val);
                        }
                        json.add(obj);
                    }
                    if(cant_resultset == 1){
                        result_json_data = json.toString();                        
                    }if(cant_resultset == 2){
                       result_json_msg = json.toString();
                    }                     
                    cant_resultset++;  
                    rs_cur.close();
                    st.close();    
            }
            
              pstmt.close();
                       
            result_json = "{\"data\":"+result_json_data+",\"request\":"+result_json_msg+"}";
            
            con.commit();
            con.setAutoCommit(true);
            con.close();
        } catch (Exception e) {
            result_json = "{\"data\":\"[]\",\"request\":[{\"status\":\"ERROR\",\""+e.getMessage()+"\":\"\"}]}"; //"[]";
            System.err.println(e.getMessage());
        }
        return result_json;
    }
   
    public Vector EjecutarSelectSQLServer(String queryString){
        
    Vector vr = new Vector();    
    Connection con = this.CnSQLServer();        
        try{            
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            ResultSetMetaData rsmeta = rs.getMetaData();

            int cantcol = rsmeta.getColumnCount();

            while (rs.next())
            {
                Vector vtemp = new Vector();
                for(int i = 1 ; i<= cantcol ; i++){
                    vtemp.add(rs.getString(i)); 
                }
                vr.add(vtemp);
            }
            rs.close();
            con.close();
        }
        catch(Exception e){
        try {            
            con.close();
            EjecutarSelectSQLServer(queryString);
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println(e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
        return vr;
    }
    
    public Vector EjecutarSelectOracle(String queryString){
        
    Vector vr = new Vector();    
        Connection con = this.CnOracle();   
        try{
             
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            ResultSetMetaData rsmeta = rs.getMetaData();

            int cantcol = rsmeta.getColumnCount();

            while (rs.next())
            {
                Vector vtemp = new Vector();
                for(int i = 1 ; i<= cantcol ; i++){
                    vtemp.add(rs.getString(i)); 
                }
                vr.add(vtemp);
            }
            rs.close();
            con.close();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            
//            try {
                System.err.println("volviendo a ejecutar consulta: " + queryString);
                vr = EjecutarSelectOracle(queryString);
//                con.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(ConeccionDB.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }    
        return vr;
    }
    
    public Vector EjecutarSelectPostgres(String queryString){
        
    Vector vr = new Vector();    
    Connection con = this.CnPostgres();        
        try{            
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            ResultSetMetaData rsmeta = rs.getMetaData();

            int cantcol = rsmeta.getColumnCount();

            while (rs.next())
            {
                Vector vtemp = new Vector();
                for(int i = 1 ; i<= cantcol ; i++){
                    vtemp.add(rs.getString(i)); 
                }
                vr.add(vtemp);
            }
            rs.close();
            con.close();
        }
        catch(Exception e){
        try {            
            con.close();
            EjecutarSelectSQLServer(queryString);
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println(e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
        return vr;
    }
    
    public boolean EjecutarInstruccionOracle(String ins){
        
        boolean b  = false;    
        Connection con = this.CnOracle();   
        try{             
            Statement statement = con.createStatement();
            b = statement.execute(ins);
            con.commit();
            con.close();
        }
        catch(Exception e){
            try {                
                con.rollback();
                con.close();
                EjecutarInstruccionOracle(ins);
            } catch (SQLException ex) {
                Logger.getLogger(ConeccionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
//            JOptionPane.showMessageDialog(null, e.getMessage());
             System.err.println(e.getMessage());
        }   
        
        return b;
    }
    
        public boolean EjecutarInstruccionSqlServer(String ins){
        
        boolean b  = false;    
        Connection con = this.CnSQLServer();   
        try{             
            Statement statement = con.createStatement();
            b = statement.execute(ins);
            //con.commit();
            con.close();
        }
        catch(Exception e){
            try {                
                //con.rollback();
                con.close();
                //System.out.println(ins);
                EjecutarInstruccionSqlServer(ins);
            } catch (SQLException ex) {
                Logger.getLogger(ConeccionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
//            JOptionPane.showMessageDialog(null, e.getMessage());
             System.err.println(e.getMessage());
        }   
        
        return b;
    }

    public CallableStatement prepareCall(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
