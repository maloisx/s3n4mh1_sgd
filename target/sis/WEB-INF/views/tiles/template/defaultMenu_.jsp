<%            
    String codUser = (String) request.getSession().getAttribute("codUser");
    String ap =  (String) request.getSession().getAttribute("appPers");
    String am =  (String) request.getSession().getAttribute("apmPers");
    String nom =  (String) request.getSession().getAttribute("nomPers");
    String sglUO =  (String) request.getSession().getAttribute("sglUO");
%>
<div id="fondo" style="height: 100%">
    <div style="height: 67px" align="center">
       <img src="${pageContext.request.contextPath}/static/img/logo.png" height="67px">      
    </div>	
     <div style="height: 67px" align="center">
        <!--<img src="http://172.25.0.13/senamhi_web/public/img/logo.png" height="67px">-->
            <div class="row">
                <!--Image column-->
                <div class="col-sm-3 col-12" style="vertical-align: middle;">
                    <img src="${pageContext.request.contextPath}/static/img/00000000.jpg" style="border-radius: 50%" height="50px">
                </div>
                <!--/.Image column-->
                <!--Content column-->
                <div class="col-sm-9 col-12">
                    <p style="font:bold 12px Arial, Helvetica;color: #999;"><%=ap+" "+am%><br><%=nom%></p>  
                    <ul class="list-unstyled">
                        <li class="comment-date"><%=sglUO%> x</li>
                    </ul>
                </div>
                <!--/.Content column-->
            </div>
        
        
    </div>
    <nav id="menu-wrap">  				
        <ul id="menu">
            <li>
                <a href="javascript: void(0)">SISDAD</a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/sisdad/resum_datos">RESUMEN DE DATOS</a>
                    </li>
<!--                    <li>
                        <a href="javascript: void(0)">CONFIGURACION DE TRAMAS</a>
                            <ul>
                                <li>
                                    <a href="">ESTACIONES GOES</a>
                                </li>
                                <li>
                                    <a href="">VARIABLES</a>
                                </li>
                            </ul>
                    </li>-->
                </ul>  
            </li>    
            <li>
                <a href="javascript: void(0)">SGD</a>                
                <ul>
                    <li><a href="${pageContext.request.contextPath}/sgd/bandeja">BANDEJA DE ENTRADA</a></li>
                    <li><a href="${pageContext.request.contextPath}/sgd/mant_unidcons">GEST.UNID.CONSERVACIÓN</a></li>
                    <li><a href="${pageContext.request.contextPath}/sgd/mant_ubicaciontopo_treeview">UBICACIÓN TOPOGRÁFICA</a></li> 
                    <li><a href="${pageContext.request.contextPath}/sgd/mant_archcentral">TRANSFERENCIA DOC.</a></li>
                    <li><a href="${pageContext.request.contextPath}/sgd/mant_mov_unidcons">MOV.UNID.CONSERVACIÓN</a></li>
                   
                    <li><a href="javascript: void(0)">MANTENIMIENTOS</a>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_tramite">TRAMITE</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_procedimiento">PROCEDIMIENTO</a></li>
                            <!--<li><a href="${pageContext.request.contextPath}/sgd/mant_origen">ORIGEN</a></li>-->
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_tema">TEMA</a></li>
                            <!--<li><a href="${pageContext.request.contextPath}/sgd/mant_alcance">ALCANCE</a></li>-->
<!--                            <li><a href="${pageContext.request.contextPath}/sgd/mant_condicion">CONDICION</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_tipflujo">TIP.FLUJO</a></li>-->
                            <!--<li><a href="${pageContext.request.contextPath}/sgd/mant_accion">ACCION</a></li>-->
                            <!--<li><a href="${pageContext.request.contextPath}/sgd/mant_tipestadoflujo">TIP.ESTADO DE FLUJO</a></li>-->
                            <!--<li><a href="${pageContext.request.contextPath}/sgd/mant_prioridad">PRIORIDAD</a></li>-->
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_estante">ESTANTE</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_cuerpo">CUERPO</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_balda">BALDA</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_tipunidcons">TIPO UNID.CONSERVACIÓN</a></li>                            
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_seriedoc">SERIE DOCUMENTAL</a></li>
                            <li><a href="${pageContext.request.contextPath}/sgd/mant_clasifdoc">CLASIF.DOCUMENTAL</a></li>
                        </ul>                    
                    </li>
                </ul>                 
            </li>    
            <li><a href="${pageContext.request.contextPath}/login/logout">SALIR</a>
            </li>
        </ul>	
    </nav>
</div>