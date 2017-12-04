	
var map, manager;
var centerLatitude = -9.49282, centerLongitude = -75.3689, startZoom = 6;
var markersArray = [];
var arrayestaciones;
var popup = new google.maps.InfoWindow();

var optionsmaps = {
        zoom: startZoom
        , center: new google.maps.LatLng(centerLatitude, centerLongitude)
//        , mapTypeId: google.maps.MapTypeId.SATELLITE
        , mapTypeId: google.maps.MapTypeId.HYBRID
 
        , backgroundColor: '#ffffff'
        , noClear: true
        , disableDefaultUI: true
        , keyboardShortcuts: false
        , disableDoubleClickZoom: true
        , draggable: true
//        , scrollwheel: true
        , draggableCursor: 'move'
        , draggingCursor: 'move'
 
        , mapTypeControl: true
        , mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR
            , position: google.maps.ControlPosition.TOP_RIGHT
            , mapTypeIds: [
                google.maps.MapTypeId.ROADMAP
                ,google.maps.MapTypeId.SATELLITE
                ,google.maps.MapTypeId.HYBRID
                ,google.maps.MapTypeId.TERRAIN
            ]
        }
//        , navigationControl: true
        , streetViewControl: false
        , navigationControlOptions: {
            position: google.maps.ControlPosition.TOP_LEFT
//            , style: google.maps.NavigationControlStyle.ANDROID
            , style: google.maps.NavigationControlStyle.ZOOM_PAN
        }	       
//        , scaleControl: true
        , scaleControlOptions: {
            position: google.maps.ControlPosition.BOTTOM_LEFT
            , style: google.maps.ScaleControlStyle.DEFAULT
        }
    };
    
    function pintarmapa(divmapa){	 
    map = new google.maps.Map(document.getElementById(divmapa), optionsmaps);
};

function createTooltip(marker, txt) {
    //create a tooltip 
    var tooltipOptions = {
        marker: marker,
        content: txt,
        cssClass: 'tooltip_googlemaps' // Nombre de la clase ha aplicar tooltip
    };
    var tooltip = new Tooltip(tooltipOptions);
}


function aniadirmarker(codestacion,codgoes,estacion,txt,fech_ini,lng,lat,pathicon,css){

	if ( pathicon == '' || pathicon == undefined ){
            pathicon = path +'static/img/localizacion_verde.png';
	}else{
            pathicon = path +'static/img/'+pathicon+'';
        }
        
    
        if ( css == '' || css == undefined ){
		css = 'labelsgooglemaps';
	}
	
//	var marker = new google.maps.Marker({
//	        position: new google.maps.LatLng(lat, lng)
//	        , map: map
//	        , title: estacion
//	        , icon: 'http://gmaps-samples.googlecode.com/svn/trunk/markers/red/marker10.png'
////	        , icon: path +'img/localizacion_' + ((trim(alerta) == '')?'verde':'rojo') + '.png'
//	    });		
	
	
	var marker = new MarkerWithLabel({
	       position: new google.maps.LatLng(lat, lng),
	       draggable: false,
	       map: map,
               //raiseOnDrag: true,
	       icon: pathicon,
               zoomControl: true,
	       //labelContent: estacion,               
//	       labelAnchor: new google.maps.Point(100, 0),
	       labelClass: css,//"labelsgooglemaps", // the CSS class for the label
	       labelStyle: {opacity: 0},
	       labelInBackground: true
	     });
	//a??adiendo la ventana del evento hover
        createTooltip(marker, txt);

	
		    google.maps.event.addListener(marker, 'click', function(){
                        
                        popup_datos(codestacion,codgoes,estacion,fech_ini);
                        
//			        var note = '<div id="contentInfoWindow' + codestacion + '" class="ui-corner-all" style="width: 550px; height: 550px; border: 1px solid #000;"></div>';
//			    	popup.setContent(note);
//			        popup.open(map, this);
//			    	
//			    	var note = '';
//			    	$.ajax({
//					 	dataType: "html",
//					 	type: "GET",
//					 	url: path + "index.php/sophi/mapdatagraf/", 
//					 	data: 	"codestacion="+codestacion,	 	 
//						beforeSend: function(data){ 	 	
//							$('#contentInfoWindow' + codestacion).html("Cargando...");
//						},
//						success: function(requestData){
//							$('#contentInfoWindow' + codestacion).html(requestData);
//						},		
//						error: function(requestData, strError, strTipoError){											
//							$('#contentInfoWindow' + codestacion).html("Error " + strTipoError +": " + strError);
//						},
//						complete: function(requestData, exito){ 
//						
//						}
//					});    	
		       
			    });
    
	markersArray.push(marker);
}

function removermarkers(markersArray){
	 for (var i = 0; i < markersArray.length; i++) {
		  markersArray[i].setMap(null);
     }
	  markersArray.length=0;
}

function centrarmapa(){
			
	if(markersArray.length>0){
		var limits = new google.maps.LatLngBounds();
		for(var marker=0;marker<markersArray.length;marker++){
			limits.extend(markersArray[marker].getPosition());
		}
		map.fitBounds(limits);	
	}else{
		map.setOptions({zoom: startZoom, center: new google.maps.LatLng(centerLatitude, centerLongitude)});
	}
	
} 

function popup_datos(cod_esta,cod_goes,nom_esta,fec_ini){
    //console.log(cod_goes + '->' +nom_esta);
    var url = encodeURI(path + "sisdad/datos/?cod_esta="+cod_esta+"&cod_goes="+cod_goes+"&nom_esta="+nom_esta+"&fec_ini="+fec_ini);
    //console.log(url);
    $.colorbox({
        "href": url
       ,"width"  : 1500
       ,"height" : 800 
    });
}

function popup_editar_estacion_sisdad(cod_esta){
    //console.log(cod_goes + '->' +nom_esta);
    var url = encodeURI(path + "sisdad/popup_editar_estacion/?cod_esta="+cod_esta);
    //console.log(url);
    $.colorbox({
        "href": url
       ,"width"  : 1500
       ,"height" : 800 
    });
}

function popup_editar_estacion_sisdad2(cod_esta){
    //console.log(cod_goes + '->' +nom_esta);
    var url = encodeURI(path + "sisdad/popup_editar_estacion2/?cod_esta="+cod_esta);
    //console.log(url);
    $.colorbox2({
        "href": url
       ,"width"  : 1500
       ,"height" : 800 
    });
}


function buscar_maker_map(lon,lat){
    console.log(lon + " -> " + lat);
    map.setOptions({zoom: 12, center: new google.maps.LatLng(lat, lon)});
}

function mostrar_tbl_datos(){
    var cod_esta = $('#hd_cod_esta').val();
    var cod_goes = $('#hd_cod_goes').val();
    var fecha_ini = $('#fecha_ini').val();
    var fecha_fin = $('#fecha_fin').val();
    //console.log(fecha_ini + "->" + fecha_fin);
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sisdad/tbl_datos/", 
            data:     "cod_esta="+cod_esta+"&cod_goes="+cod_goes+"&fecha_ini="+fecha_ini+"&fecha_fin="+fecha_fin ,	 	 
            beforeSend: function(data){ 	 	
                    $('#div_table_datos').html("Cargando...");
            },
            success: function(requestData){
                    $('#div_table_datos').html(requestData);
            },		
            error: function(requestData, strError, strTipoError){											
                    $('#div_table_datos').html("Error " + strTipoError +": " + strError);
            }
    });
}

//google.charts.setOnLoadCallback(graf_datos);
function graf_datos(){
    
    var fecha_ini = $('#fecha_ini').val();
    var fecha_fin = $('#fecha_fin').val();
    var nom_esta = $('#div_nom_esta').html();
    
    var nro_col = $('#cb_param').val();
    var col = $("#cb_param :selected").text();
    var json = $('#json_datos').val();
    
    var show_data_hour = $('#chb_datos_horarios').is(':checked');
    var show_data_obs = $('#chb_datos_observador').is(':checked');
    
    var val_min = 999999;
    var val_max = -99999;
    //console.log(nro_col + "->" + col);
    //console.log(json);
     arrayobj = jQuery.parseJSON(json);	
     arrayobj.reverse();
     //console.log(arrayobj);
     var fech = "";
     var val = "";
     var dat_cat =new Array();
     var dat_ser =new Array();
//    var data = new google.visualization.DataTable();
//      data.addColumn('date', 'FECHA');
//      data.addColumn('number', 'valor');
     
     for(i=0;i<arrayobj.length;i++){
         fech = arrayobj[i][0];
         val = arrayobj[i][nro_col];
//         console.log(val);
            var d = fech.substring(0,2);
            var m = fech.substring(3,5);
            var y = fech.substring(6,10);
            var h = fech.substring(11,13);
            var mi = fech.substring(14,17);
         
         //console.log(fech + " : " + val);
//         tmp_array.push([Date(y,(m-1),d,h,mi),parseFloat(val)]);
        if(show_data_hour == true){
            if(mi == '00'){
                
                if(show_data_obs == false){                    
                    if( val.indexOf("M") == -1 && val.indexOf("D") == -1 ){
                        dat_cat.push(fech);
                        dat_ser.push(parseFloat(val));
                        if(val != ''){
                            if(val<val_min) val_min = val;
                            if(val>val_max) val_max = val;
                        }
                    }else{
                        dat_cat.push(fech);
                        dat_ser.push('');
                    }                     
                }else{
                    dat_cat.push(fech);
                    dat_ser.push(parseFloat(val));
                        if(val != ''){
                            if(val<val_min) val_min = val;
                            if(val>val_max) val_max = val;
                        }
                }
                
            }
        }else{
            if(show_data_obs == false){                    
                if( val.indexOf("M") == -1 && val.indexOf("D") == -1 ){
                    //console.log(fech + '->' +val);
                    dat_cat.push(fech);
                    dat_ser.push(parseFloat(val));
                        if(val != ''){
                            if(val<val_min) val_min = val;
                            if(val>val_max) val_max = val;
                        }   
                }else{
                    dat_cat.push(fech);
                    dat_ser.push('');
                }                    
            }else{
                dat_cat.push(fech);
                dat_ser.push(parseFloat(val));
                    if(val != ''){
                        if(val<val_min) val_min = val;
                        if(val>val_max) val_max = val;
                    }
            }
        }
        
     }
     val_max = val_max.replace("M", "").replace("D", "");
     console.log('max: '+val_max + ' // min: '+val_min);
     $( "#sp_val_min" ).spinner( "value", val_min );
     $( "#sp_val_max" ).spinner( "value", val_max );    
     
     /*
     data.addRows(tmp_array);
     var options = {
            title: "GRAF DE " + nom_esta + " DEL "+fecha_ini + ' AL '+fecha_fin + ' DE LA VARIABLE '+col
          , legend: { position: 'none' }
         ,hAxis: {
                format: 'dd/MM/yyyy HH:mm'
            }
            
        };
     var chart = new google.charts.Line(document.getElementById('chart_div'));
//     var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
     chart.draw(data, options);
     */
    
        Highcharts.chart('chart_div', {
                chart: {
                    zoomType: 'x'
                },
                title: {
                    text: "GRAF DE " + nom_esta + " DEL "+fecha_ini + ' AL '+fecha_fin + ' DE LA VARIABLE '+col
                },
                xAxis: {
                    categories: dat_cat
                },
                legend: {
                    enabled: false
                },
                series: [{
                    type: 'line',
                    name: 'VALOR',
                    data: dat_ser
                }]
            });

    
    
}

function registrar_rawdata(id,raw_data){
    console.log(raw_data)
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sisdad/registrar_rawdata/", 
            data:     "raw_data="+raw_data,	 	 
            beforeSend: function(data){ 	 	
                $('#div_fecha_'+id).html("Cargando...");
            },
            success: function(requestData){
                console.log(requestData);
                arrayobj = jQuery.parseJSON(requestData);	
                console.log(arrayobj);
                var cod_esta = arrayobj[0][0];
                var msj    = arrayobj[0][1];
                
                if(cod_esta != ''){
                    $('#div_fecha_'+id).html("<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all'><span class='ui-icon ui-icon-circle-check'></span></li></ul></div>");
                    $('#div_fecha_'+id).parent().removeClass("ui-state-error").addClass("ui-state-highlight");
                }
                else{
                    $('#div_fecha_'+id).html(msj);
                    console.log(msj);
                }
            },		
            error: function(requestData, strError, strTipoError){											
                $('#div_fecha_'+id).html("Error " + strTipoError +": " + strError);
            }
    });
}

function registrar_rawdata_all(){
    
//    jQuery(".btn_reg_rawdata").trigger('click');
//    return false;

        $.confirm({
            title: '',
            content: 'DESCARGA DE DATOS MASIVA',
            theme: 'supervan',
            buttons: {
//                confirm: function () {
//                    $.alert('Confirmed!');
//                },                    
                    buttonFaltamtes: {
                        text: 'DATOS FALTANTES',
                        action: function () {
                            //$.alert('FALTANTES!');
                            jQuery(".btn_reg_rawdata_0").trigger('click');
                        }
                    },
                    buttonTodo: {
                        text: 'TODOS LOS DATOS',
                        action: function () {
                            //$.alert('TODO!');
                            jQuery(".btn_reg_rawdata").trigger('click');
                        }
                    },
                    buttonCancelar: {
                        text: 'CANCELAR'
                        //$.alert('Canceled!');
                    }
            }
        });
}

function cambiar_estado_reg(codesta,fecha,param,estado){
    console.log(codesta+"->"+fecha+"->"+param+"->"+estado);
    var id= codesta+"_"+fecha.replace(/[/]/g,"").replace(":","").replace(" ","")+"_"+param;
    console.log(id +"->"+estado);
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sisdad/act_estado_reg/", 
            data:     "codesta="+codesta+"&fecha="+fecha+"&param="+param+"&estado="+estado,	 	 
            beforeSend: function(data){ 
                $('#div_reg_'+id).show();
                $('#div_reg_'+id).html("Cargando...");
            },
            success: function(requestData){
                //console.log(requestData);
                                
                if(requestData == 1){
                    $('#div_reg_'+id).html("<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all'><span class='ui-icon ui-icon-alert'></span></li></ul></div>");
                    //$('#div_reg_'+id).parent().removeClass("ui-state-error").addClass("ui-state-highlight");
                }
                else{
                    console.log(requestData);
                    $('#div_reg_'+id).html(requestData);
                }
            },		
            error: function(requestData, strError, strTipoError){											
                $('#div_reg_'+id).html("Error " + strTipoError +": " + strError);
            }
    });
}


function mostrar_mant_esta_goes_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sisdad/mant_esta_goes_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_esta_goes_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_esta_goes_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_esta_goes_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sisdad_popup_mant_nueva_estacion(){
    $.alert("nueva estacion");   
}

function sisdad_popup_mant_esta_goes(codesta){
    console.log(codesta);    
}