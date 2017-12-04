<%@page import="com.senamhi.sis.functions.Util"%>
<%@page import="java.util.Vector"%>
<%@page import="com.senamhi.sis.connection.ConeccionDB"%>
<%            
    String codUser = (String) request.getSession().getAttribute("codUser");
    String ap =  (String) request.getSession().getAttribute("appPers");
    String am =  (String) request.getSession().getAttribute("apmPers");
    String nom =  (String) request.getSession().getAttribute("nomPers");
    String sglUO =  (String) request.getSession().getAttribute("sglUO");
    String dni =  (String) request.getSession().getAttribute("dni");
%>
<div id="fondo" style="height: 100%">
    <div style="height: 67px" align="center">
       <img src="${pageContext.request.contextPath}/static/img/logo.png" height="67px">      
       
        <!--<div style="width:20px;float:right;"><button id="btn_menu_slider">...</button></div>-->
        <div style="width:20px;float:right;">
            <a class="btn-floating btn-sm waves-effect waves-light blue glyphicon glyphicon-menu-hamburger" id="btn_menu_slider"><span class=""></span></a>
        </div>
        
    </div>	
     <div style="" align="center">
         
            <div class="row" align="center">                
                <div class="col-12" style="vertical-align: middle;margin-bottom: 10px">
                    <img src="${pageContext.request.contextPath}/static/img/00000000.jpg" style="border-radius: 50%" height="50px">
                    <!--<img src="http://sgd.senamhi.gob.pe/files/personal/fotos/<%=dni%>.jpg" style="border-radius:15%" height="70px">-->
                </div>
            </div>
            <div class="row">    
                <div class="col-12">
                    <p style="font:bold 12px Arial, Helvetica;color: #999;"><%=ap+" "+am%><br><%=nom%></p>  
                    <ul class="list-unstyled">
                        <li class="comment-date"><%=sglUO%></li>
                    </ul>
                </div>
            </div>        
    </div>
    <nav id="menu-wrap">  	
        <%
            String menu = "";
           if (codUser != null){  
           ConeccionDB cn =  new ConeccionDB();            

            String np = "seguridad.fn_menu";
            String array[] = new String[1];
            array[0] = codUser;
            Vector datos_menu = cn.EjecutarProcedurePostgres(np, array); 
            
           Util u = new Util();
            
           menu = u.menu_buscar_hijos(datos_menu, "0", u.GetPath());
           }
        %>
        <%=menu%>
<!--        <ul id="menu">
            <li>
                <a href="javascript: void(0)">SISDAD</a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/sisdad/resum_datos">RESUMEN DE DATOS</a>
                    </li>
                </ul>  
            </li>    
            <li>
                <a href="javascript: void(0)">SGD</a>                
                <ul>
                    <li><a href="${pageContext.request.contextPath}/sgd/bandeja_entrada">BANDEJA DE ENTRADA</a></li>
                    <li><a href="${pageContext.request.contextPath}/sgd/mant_estante">GESTIÓN ESTANTES</a></li>
                    <li><a href="${pageContext.request.contextPath}/sgd/mant_unidcons">GESTIÓN UNID.CONSERV.UO</a></li>
                    <li><a href="${pageContext.request.contextPath}/sgd/mant_ubicaciontopo_treeview">UBICACIÓN TOPOGRÁFICA</a></li>                 
                   
                    <li><a href="javascript: void(0)">MANTENIMIENTOS</a>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_tramite">TRAMITE</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_procedimiento">PROCEDIMIENTO</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_tema">TEMA</a></li>            
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_cuerpo">CUERPO</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_balda">BALDA</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_tipunidcons">TIPO UNID.CONSERVACIÓN</a></li>                            
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_seriedoc">SERIE DOCUMENTAL</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_clasifdoc">CLASIF.DOCUMENTAL</a></li>
                        </ul>                    
                    </li>
                </ul>                 
            </li>    
            <li><a href="${pageContext.request.contextPath}/login/logout">SALIR</a></li>
        </ul>	-->
    </nav>
</div>