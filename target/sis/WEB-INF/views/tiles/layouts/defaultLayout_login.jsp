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
		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCi3uGbWaukJjhm6WjuStB19XoIslLtvbk" type="text/javascript"></script>
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script>
                    google.charts.load('current', {packages: ['corechart', 'line']});
                                        
                </script>
		<script type="text/javascript" src="<c:url value='/static/js/common/00_jquery_1.9.1.min.js'/>"></script>
                <!--<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>-->
                <script type="text/javascript" src="<c:url value='/static/js/common/01_jquery-ui_1.12.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/static/js/common/01_jquery-ui_1.12.1.js'/>"></script>
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
			//var path="${pageContext.request.contextPath}";
                        var path="/sis/";
                        //var path="<c:url value='/'/>";
			$(function(){
				$("input:submit, input:button, input:reset, button").button();
			});
		</script>

            <!--<link href="<c:url value='/static/datatables/css/demo_page.css'/>" rel="stylesheet">-->
            <!--<link href="<c:url value='/static/datatables/css/demo_table.css'/>" rel="stylesheet">-->
            <link href="<c:url value='/static/datatables/css/TableTools.css'/>" rel="stylesheet">
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/jquery.dataTables.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/ZeroClipboard.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/TableTools.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/FixedColumns.js'/>"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/FixedHeader.js'/>"></script>

            <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
            <script type="text/javascript" language="javascript" src="<c:url value='/static/datatables/js/dataTables.buttons.js'/>"></script>
            <script type="text/javascript" language="javascript" src="//cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
            <script type="text/javascript" language="javascript" src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.24/build/pdfmake.min.js"></script>
            <script type="text/javascript" language="javascript" src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.24/build/vfs_fonts.js"></script>
            <script type="text/javascript" language="javascript" src="//cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js"></script>
            <!--<link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet">-->
            <link href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css" rel="stylesheet">
            
            <link href="<c:url value='/static/kendo/css/kendo.common.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/static/kendo/css/kendo.default.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/static/kendo/css/kendo.dataviz.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/static/kendo/css/kendo.bootstrap.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/static/kendo/css/examples.css'/>" rel="stylesheet">   
	    <script src="<c:url value='/static/kendo/js/kendo.all.js'/>"></script> 
	    
	    <script src="<c:url value='/static/kendo/js/console.js'/>"></script>
	    <script src="<c:url value='/static/kendo/js/prettify.js'/>"></script>
	        
            <script src="<c:url value='/static/kendo/js/kendo.culture.es-PE.js'/>"></script>
            <script>kendo.culture('es-PE');</script>	
            <script src="<c:url value='/static/kendo/js/kendo_fn.js'/>"></script>
            
            <script src="https://code.highcharts.com/highcharts.js"></script>
            <script src="https://code.highcharts.com/highcharts-more.js"></script>
            <script src="https://code.highcharts.com/modules/data.js"></script>
            <script src="https://code.highcharts.com/modules/heatmap.js"></script>
            <script src="https://code.highcharts.com/modules/exporting.js"></script>
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
            
<!--            <link rel="stylesheet" href="<c:url value='/static/bootstrap/css/bootstrap.css'/>" /> 
            <script src="<c:url value='/static/bootstrap/js/bootstrap.js'/>"></script>-->
              
            <!-- Font Awesome -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">            
            <link href="<c:url value='/static/MDB/css/bootstrap.min.css'/>" rel="stylesheet">            
            <link href="<c:url value='/static/MDB/css/mdb.css'/>" rel="stylesheet">
            <script type="text/javascript" src="<c:url value='/static/MDB/js/bootstrap.min.js'/>"></script>
            <script type="text/javascript" src="<c:url value='/static/MDB/js/mdb.js'/>"></script>
            
            <link rel="stylesheet" href="<c:url value='/static/bootstrap-select/css/bootstrap-select.css'/>" /> 
            <script src="<c:url value='/static/bootstrap-select/js/bootstrap-select.js'/>"></script>
            
            <link rel="stylesheet" href="<c:url value='/static/bootstrap-vertical/bootstrap.vertical-tabs.css'/>" /> 
            
                <link href="<c:url value='/static/bootstrap-fileinput/css/fileinput.css'/>" media="all" rel="stylesheet" type="text/css"/>
    <!--<link href="../themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css"/>-->
    <script src="<c:url value='/static/bootstrap-fileinput/js/plugins/sortable.js" type="text/javascript'/>"></script>
    <script src="<c:url value='/static/bootstrap-fileinput/js/fileinput.js" type="text/javascript'/>"></script>
    <script src="<c:url value='/static/bootstrap-fileinput/js/locales/es.js" type="text/javascript'/>"></script>
    <!--<script src="../themes/explorer/theme.js" type="text/javascript"></script>-->
            
            
            
            <script src="<c:url value='/static/js/js_generales.js'/>"></script>
            <script src="<c:url value='/static/js/js_login.js'/>"></script>
            
</head>
 
<body> 

            <tiles:insertAttribute name="body" />
 
</body>
</html>