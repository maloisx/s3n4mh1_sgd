<%@page import="com.senamhi.sis.functions.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<head>
    
    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link type="image/x-icon" href="<c:url value='/static/img/favicon.png'/>" rel="icon">
        <link type="image/x-icon" href="<c:url value='/static/img/favicon.png'/>" rel="shortcut icon">
        
        <style type="text/css">
                .printable {
                        border: 1px dotted #CCCCCC ;
                        padding: 10px 10px 10px 10px ;
                }
        </style>

            <link href="<c:url value='/static/css/content.css'/>" rel="stylesheet" type="text/css">
            <link href="<c:url value='/static/css/fg.menu.css'/>" rel="stylesheet" type="text/css">
            <!--<link href="<c:url value='/static/css/index.css'/>" rel="stylesheet" type="text/css">-->
            <link href="<c:url value='/static/css/jquery.jqplot.css'/>" rel="stylesheet" type="text/css">
            <link href="<c:url value='/static/css/menu_lateral.css'/>" rel="stylesheet" type="text/css">
            <!--<link href="<c:url value='/static/css/ui.jqgrid.css'/>" rel="stylesheet" type="text/css">-->
            <link href="<c:url value='/static/css/ui.multiselect.css'/>" rel="stylesheet" type="text/css">
            <link href="<c:url value='/static/css/jquery-confirm.css'/>" rel="stylesheet" type="text/css">

		<link href="<c:url value='/static/theme/blue_explora/jquery-ui.css'/>" rel="stylesheet" type="text/css" id="jquery_theme_link">
		<!--<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCi3uGbWaukJjhm6WjuStB19XoIslLtvbk" type="text/javascript"></script>-->
		<script src="http://maps.googleapis.com/maps/api/js?key=" type="text/javascript"></script>
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script>
                    google.charts.load('current', {packages: ['corechart', 'line']});
                                        
                </script>
		<script type="text/javascript" src="<c:url value='/static/js/common/00_jquery_1.9.1.min.js'/>"></script>
                <!--<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>-->
                <script type="text/javascript" src="<c:url value='/static/js/common/01_jquery-ui_1.12.1.js'/>"></script>
		<!--<script type="text/javascript" src="<c:url value='/static/js/common/01_jquery-ui_1.12.1.js'/>"></script>-->
		<script type="text/javascript" src="<c:url value='/static/js/common/02_jquery.subscribe.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/03_jquery.struts2-3.1.0.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/04_jquery.print.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/05_fg.menu.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/06_common.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/07_validate.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/08_jquery.pagination.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/09_autoNumeric-1.6.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/10_swfobject.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/11_functions.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/12_jquery.numeric.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/13_js_validaciones.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/14_markerwithlabel.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/15_jquery-confirm.js'/>"></script>
                <script type="text/javascript" src="<c:url value='/static/js/common/16_tooltip_googlemaps.js'/>"></script>
                <style>
                    .tooltip_googlemaps{
                        border: #000 solid 1px;
                        border-radius: 5px;
                        background-color:#FFFBF0;
                        padding:5px;
                    }                    
                </style>
                
		<script type="text/javascript">
			var path="${pageContext.request.contextPath}/";
                        var path_ws="http://sgd.senamhi.gob.pe/ws/rest";
                        //var path_ws="http://localhost:8085/ws/rest";
			$(function(){
				$("input:submit, input:button, input:reset, button").button();
			});
		</script>

            <!--<link href="<c:url value='/static/datatables/css/demo_page.css'/>" rel="stylesheet">-->
            <!--<link href="<c:url value='/static/datatables/css/demo_table.css'/>" rel="stylesheet">-->
            <link href="<c:url value='/static/datatables/css/TableTools.css'/>" rel="stylesheet">
            <link href="<c:url value='/static/datatables/css/dataTables.bootstrap.css'/>" rel="stylesheet">
            
            <!--<link href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.bootstrap.min.css" rel="stylesheet">-->
            <link href="<c:url value='/static/datatables/css/responsive.bootstrap.min.css'/>" rel="stylesheet">
            <link href="<c:url value='/static/datatables/ext/responsive.bootstrap.min.css'/>" rel="stylesheet">
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/jquery.dataTables.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/dataTables.bootstrap.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/ZeroClipboard.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/TableTools.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/FixedColumns.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/FixedHeader.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/dataTables.responsive.min.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/responsive.bootstrap.min.js'/>"></script>
            

            <!--xxxx<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>-->
        <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/dataTables.buttons.js'/>"></script>
           
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/ext/jszip.min.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/ext/pdfmake.min.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/ext/vfs_fonts.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/ext/buttons.html5.min.js'/>"></script>
               
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/ext/buttons.print.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/ext/buttons.colVis.js'/>"></script>
            
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/ext/dataTables.responsive.min.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/ext/responsive.bootstrap.min.js'/>"></script>
            
            <!--xxxx<link href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css" rel="stylesheet">-->
            
            <!--<link href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.bootstrap.css" rel="stylesheet">-->
            <link href="<c:url value='/static/datatables/ext/buttons.bootstrap.css'/>" rel="stylesheet">
                
<!--            <link href="<c:url value='/static/kendo/css/kendo.common.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/static/kendo/css/kendo.default.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/static/kendo/css/kendo.dataviz.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/static/kendo/css/kendo.bootstrap.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/static/kendo/css/examples.css'/>" rel="stylesheet">   
	    <script src="<c:url value='/static/kendo/js/kendo.all.js'/>"></script> -->
	    
<!--	    <script src="<c:url value='/static/kendo/js/console.js'/>"></script>
	    <script src="<c:url value='/static/kendo/js/prettify.js'/>"></script>-->
	        
<!--            <script src="<c:url value='/static/kendo/js/kendo.culture.es-PE.js'/>"></script>
            <script>kendo.culture('es-PE');</script>	
            <script src="<c:url value='/static/kendo/js/kendo_fn.js'/>"></script>-->
            
<!--            <script src="https://code.highcharts.com/highcharts.js"></script>
            <script src="https://code.highcharts.com/highcharts-more.js"></script>
            <script src="https://code.highcharts.com/modules/data.js"></script>
            <script src="https://code.highcharts.com/modules/heatmap.js"></script>
            <script src="https://code.highcharts.com/modules/exporting.js"></script>-->

            <script src="<c:url value='/static/highcharts/highcharts.js'/>"></script>
            <script src="<c:url value='/static/highcharts/highcharts-more.js'/>"></script>
            <script src="<c:url value='/static/highcharts/data.js'/>"></script>
            <script src="<c:url value='/static/highcharts/heatmap.js'/>"></script>
            <script src="<c:url value='/static/highcharts/exporting.js'/>"></script>

            <script>
            Highcharts.setOptions({
                    lang: {
                            months: ["ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"],
                            weekdays: ["DOMINGO","LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO"],
                            downloadJPEG: "Descargar JPEG",
                            downloadPDF: "Descargar PDF",
                            downloadPNG: "Descargar PNG",
                            downloadSVG: "Descargar SVG vector",
                            drillUpText: "Retornar a {series.name}",
                            loading: "Cargando...",
                            noData: "No se encuentra datos para mostrar",
                            printChart: "Imprimir Gráfico",
                            resetZoom: "Resetear zoom",
                            resetZoomTitle: "Resetear nivel de zoom 1:1",
                            shortMonths: ["ENE","FEB","MAR","ABR","MAY","JUN","JUL","AGO","SEP","OCT","NOV","DIC"]
                    }
            });
            </script>
             
            <link rel="stylesheet" href="<c:url value='/static/js/popup/colorbox.css'/>" />
	    <script src="<c:url value='/static/js/popup/jquery.colorbox.js'/>"></script>
            <link rel="stylesheet" href="<c:url value='/static/js/popup/colorbox2.css'/>" />
	    <script src="<c:url value='/static/js/popup/jquery.colorbox2.js'/>"></script>
            
<!--            <link rel="stylesheet" href="<c:url value='/static/bootstrap/css/bootstrap.css'/>" /> 
            <script src="<c:url value='/static/bootstrap/js/bootstrap.js'/>"></script>-->
              
            <!-- Font Awesome -->
            <link rel="stylesheet" href="<c:url value='/static/css/font-awesome.min.css'/> ">   
            
            <link href="<c:url value='/static/MDB/css/bootstrap.min.css'/>" rel="stylesheet">            
            <link href="<c:url value='/static/MDB/css/mdb.css'/>" rel="stylesheet">
            <script type="text/javascript" src="<c:url value='/static/MDB/js/bootstrap.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/MDB/js/mdb.js'/>"></script>
            
            <link rel="stylesheet" href="<c:url value='/static/bootstrap-select/css/bootstrap-select.css'/>" /> 
            <script src="<c:url value='/static/bootstrap-select/js/bootstrap-select.js'/>"></script>
            
            <link rel="stylesheet" href="<c:url value='/static/bootstrap-vertical/bootstrap.vertical-tabs.css'/>" /> 
            
            <link href="<c:url value='/static/bootstrap-fileinput/css/fileinput.css'/>" media="all" rel="stylesheet" type="text/css"/>
            <!--<link href="../themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>-->
            <script src="<c:url value='/static/bootstrap-fileinput/js/plugins/sortable.js'/>" type="text/javascript"></script>
            <script src="<c:url value='/static/bootstrap-fileinput/js/fileinput.js'/>" type="text/javascript"></script>
            <script src="<c:url value='/static/bootstrap-fileinput/js/locales/es.js'/>" type="text/javascript"></script>
            <!--<script src="../themes/explorer/theme.js" type="text/javascript"></script>-->
            
            
            <!--***********LightGallery-->
            <link type="text/css" rel="stylesheet" href="<c:url value='/static/lightGallery-master/dist/css/lightgallery.min.css'/>">
            <link type="text/css" rel="stylesheet" href="<c:url value='/static/lightGallery-master/dist/css/lightgallery.css'/>">
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/dist/js/lightgallery.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/dist/js/lightgallery.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/lib/jquery.mousewheel.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/modules/lg-thumbnail.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/modules/lg-zoom.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/modules/lg-share.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/modules/lg-fullscreen.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/modules/lg-hash.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/lightGallery-master/modules/lg-pager.min.js'/>"></script>
            <!--***********LightGallery-->
            
            
            <link rel="stylesheet" type="text/css" href="<c:url value='/static/SlidePushMenus/css/menu_component.css'/>" />
            <script src="<c:url value='/static/SlidePushMenus/js/modernizr.custom.js'/>"></script>
            
            <script src="<c:url value='/static/bootstrap-treeview/bootstrap-treeview.js" type="text/javascript'/>"></script>
            <script src="<c:url value='/static/bootstrap-table/bootstrap-table.js" type="text/javascript'/>"></script>
            <script src="<c:url value='/static/js/js_ws.js'/>"></script>
            <script src="<c:url value='/static/js/js_generales.js'/>"></script>
            <script src="<c:url value='/static/js/js_sisdad.js'/>"></script>
            <script src="<c:url value='/static/js/js_sgd.js'/>"></script>
            <script src="<c:url value='/static/js/js_sisbien.js'/>"></script>
            <script src="<c:url value='/static/js/js_pad.js'/>"></script>
            <script src="<c:url value='/static/js/js_sisserver.js'/>"></script>
            <script src="<c:url value='/static/js/js_sisper.js'/>"></script>
            <script src="<c:url value='/static/js/js_sisgem.js'/>"></script>
            <script src="<c:url value='/static/js/js_sisproj.js'/>"></script>
</head>
 
<body>    
     
    <% 
            String codUser = (String) request.getSession().getAttribute("codUser");
            if(codUser == "" || codUser == null){
                response.sendRedirect(new Util().GetPath()+"/login/");
            }
            //else{
                    %>					
  
                                
                              <div style="overflow-x: hidden;"> 
                                <div style="width:222px;" class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left cbp-spmenu-open" id="cbp-spmenu-s1">
                                    <tiles:insertAttribute name="menu" />   
                                </div>
                                <div id="container_x" class="container_x" style="height: 100%;">  
                                    
<!--                                    <div style="width:20px;height: 100%; z-index: 9999; background-image: -webkit-gradient(linear, left top, left bottom, from(#444), to(#111));">
                                        <button id="btn_menu_slider">...</button>
                                    </div>-->
                                        <div style="height: 20px;background: url('<c:url value='/static/img/footer_bg_cen.jpg' />');" >
                                            <tiles:insertAttribute name="header" />
                                        </div>
                                        <tiles:insertAttribute name="cab" />
                                        <tiles:insertAttribute name="body" />

                                </div>
                               </div> 
                                
                    <%
            //}
    %>
    <tiles:insertAttribute name="javascript_final_general" />
    <script src="<c:url value='/static/SlidePushMenus/js/classie.js'/>"></script>
    <script>
            var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),					
                showLeftPush = document.getElementById( 'btn_menu_slider' ),
                container_x = document.getElementById( 'container_x' ),
                body = document.body;
            classie.toggle( body, 'cbp-spmenu-push-toright' );
            showLeftPush.onclick = function() {   
                    classie.toggle( this, 'active' );
                    classie.toggle( body, 'cbp-spmenu-push-toright' );
                    classie.toggle( menuLeft, 'cbp-spmenu-open' ); 
                    var t = $($.fn.dataTable.tables(true)).DataTable(); 
                    t.responsive.rebuild();
                    t.responsive.recalc();
                    t.columns.adjust();
            };
            
            container_x.onclick = function() {                
                var b = $(body).hasClass( "cbp-spmenu-push-toright");
                //console.log(b);
                
                if(b){                
                    classie.toggle( this, 'active' );
                    classie.toggle( body, 'cbp-spmenu-push-toright' );
                    classie.toggle( menuLeft, 'cbp-spmenu-open' );                    
                    var t = $($.fn.dataTable.tables(true)).DataTable(); 
                    t.responsive.rebuild();
                    t.responsive.recalc();
                    t.columns.adjust();                    
                } 
            };
            
            
            
//            showLeftPush.onmouseover = function() {
//                    classie.toggle( this, 'active' );
//                    classie.toggle( body, 'cbp-spmenu-push-toright' );
//                    classie.toggle( menuLeft, 'cbp-spmenu-open' );
//                    
//                    var t = $($.fn.dataTable.tables(true)).DataTable(); 
//                    t.responsive.rebuild();
//                    t.responsive.recalc();
//                    t.columns.adjust();
//            };
            
            var t = $($.fn.dataTable.tables(true)).DataTable(); 
            if(t != null){
                    t.responsive.rebuild();
                    t.responsive.recalc();
                    t.columns.adjust();
                }
            
    </script>
</body>
</html>