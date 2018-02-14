
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
                    , google.maps.MapTypeId.SATELLITE
                    , google.maps.MapTypeId.HYBRID
                    , google.maps.MapTypeId.TERRAIN
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

function pintarmapa(divmapa) {
    map = new google.maps.Map(document.getElementById(divmapa), optionsmaps);
}
;

function createTooltip(marker, txt) {
    //create a tooltip 
    var tooltipOptions = {
        marker: marker,
        content: txt,
        cssClass: 'tooltip_googlemaps' // Nombre de la clase ha aplicar tooltip
    };
    var tooltip = new Tooltip(tooltipOptions);
}


function aniadirmarker(codestacion, codgoes, estacion, txt, fech_ini, lng, lat, pathicon, css) {

    if (pathicon == '' || pathicon == undefined) {
        pathicon = path + 'static/img/localizacion_verde.png';
    } else {
        pathicon = path + 'static/img/' + pathicon + '';
    }


    if (css == '' || css == undefined) {
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
        labelClass: css, //"labelsgooglemaps", // the CSS class for the label
        labelStyle: {opacity: 0},
        labelInBackground: true
    });
    //a??adiendo la ventana del evento hover
    createTooltip(marker, txt);


    google.maps.event.addListener(marker, 'click', function () {

        popup_datos(codestacion, codgoes, estacion, fech_ini);

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

function removermarkers(markersArray) {
    for (var i = 0; i < markersArray.length; i++) {
        markersArray[i].setMap(null);
    }
    markersArray.length = 0;
}

function centrarmapa() {

    if (markersArray.length > 0) {
        var limits = new google.maps.LatLngBounds();
        for (var marker = 0; marker < markersArray.length; marker++) {
            limits.extend(markersArray[marker].getPosition());
        }
        map.fitBounds(limits);
    } else {
        map.setOptions({zoom: startZoom, center: new google.maps.LatLng(centerLatitude, centerLongitude)});
    }

}

function popup_datos(cod_esta, cod_goes, nom_esta, fec_ini) {
    //console.log(cod_goes + '->' +nom_esta);
    var url = encodeURI(path + "sisdad/datos/?cod_esta=" + cod_esta + "&cod_goes=" + cod_goes + "&nom_esta=" + nom_esta + "&fec_ini=" + fec_ini);
    //window.open(url,"_blank");
    $.colorbox({
        "href": url
        , "width": 1500
        , "height": 800
    });
}

function popup_editar_estacion_sisdad(cod_esta) {
    //console.log(cod_goes + '->' +nom_esta);
    var url = encodeURI(path + "sisdad/popup_editar_estacion/?cod_esta=" + cod_esta);
    //console.log(url);
    $.colorbox({
        "href": url
        , "width": 1500
        , "height": 800
    });
}

function sisdad_js_popup_editar_estacion(){
    var cod_esta = $('#hd_cod_esta').val();
    var obj_estaciones = ws('sisdad', ' pkg_ws.sp_obt_estaciones', '["'+cod_esta+'",""]');
    //console.log(cod_esta);
    //console.log(obj_estaciones);
    
    var nom_esta = obj_estaciones.data[0].NOM_ESTA;
    var cod_goes = obj_estaciones.data[0].COD_GOES;
    var cod_goesmarca = obj_estaciones.data[0].COD_GOESMARCA;
    var estado_goes = obj_estaciones.data[0].ESTADO_GOES;
        
    var obj_goesmarca = ws('sisdad', 'pkg_ws.sp_obt_goesmarca', '');
    
    $('#txt_estacion').val(cod_esta + ' - '+ nom_esta);
    $('#txt_cod_goes').val(cod_goes);
    ws_contenido_combo("cb_tipogoes", obj_goesmarca.data, cod_goesmarca);
    
    //estado goes
    var obj_estado_goes = [];
    obj_estado_goes.push(["0","Off-LINE"]);
    obj_estado_goes.push(["1","On-LINE"]);
    
    ws_contenido_combo("cb_estado_esta", obj_estado_goes, estado_goes);
        
    var tbl_cab = [
                //{'sTitle': 'Nro','sWidth':'30px', 'sClass':'text-center'}, 
                {'sTitle': 'VARIABLE', 'sClass':'text-center'}, 
                {'sTitle': 'POSICION', 'sClass':'text-center'},
                {'sTitle': 'LONGITUD', 'sClass':'text-center'},
                {'sTitle': 'FAT. CONV.', 'sClass':'text-center'},
                {'sTitle': 'DECIMALES', 'sClass':'text-center'},
                {'sTitle': 'OFFSET', 'sClass':'text-center'},
                {'sTitle': 'VAL. CONV. ADICIONAL', 'sClass':'text-center'},
                {'sTitle': '-','sWidth':'30px', 'sClass':'text-center'}
            ];       
//            for(var i= 0 ; i<obj_items.data.length ; i++){
//                obj_items.data[i].UNIDAD = obj_items.data[i].UNIDAD + 'xxxxx';
//                obj_items.data[i].btn1 = tbl_ext_btn('glyphicon-edit',"$.alert('XXX')") ;
//                obj_items.data[i].btn2 = tbl_ext_btn('glyphicon-trash',"$.alert('"+obj_items.data[i].PREC_UNIT+"')");
//                obj_items.data[i].btn3 = tbl_ext_btn('glyphicon-print');
//            }
            //console.log(obj_items.data);
            
            var opciones_tbl= {
                responsive: false
                , bFilter: true
                , bLengthChange: false
                , bInfo: false
                , bPaginate: false
                , dom: "Blfrtip"
                , buttons: [
                            {text:'NUEVA VARIABLE',action:function( e, dt, node, config ) {sisdad_js_popup_editar_estacion_aniadir_var('','','','','','','')},className:'btn btn-info btn-sm'},
                            {text:'GUARDAR CONFIGURACION',action:function( e, dt, node, config ) {sisdad_js_popup_editar_estacion_guardar_config()},className:'btn btn-info btn-sm'}
                            //{extend: 'excel', text: 'Exportar a Excel', className: 'btn btn-info btn-sm'}
                        ]
            };
            
            ws_datatable("sisdad_js_popup_editar_estacion_config_trama_tbl",[], tbl_cab,opciones_tbl); 
            
            
            var obj_lista_estacion_variables = ws('sisdad', 'pkg_ws.sp_obt_estacion_variable', '["'+cod_esta+'"]');
            //console.log(obj_lista_estacion_variables);
            for(var i = 0;i<obj_lista_estacion_variables.data.length ; i++){
                sisdad_js_popup_editar_estacion_aniadir_var(
                                                            obj_lista_estacion_variables.data[i].COD_VAR,
                                                            obj_lista_estacion_variables.data[i].POS,
                                                            obj_lista_estacion_variables.data[i].LONGITUD,
                                                            obj_lista_estacion_variables.data[i].FATCONV,
                                                            obj_lista_estacion_variables.data[i].NUMDEC,
                                                            obj_lista_estacion_variables.data[i].OFFSET_TIME,
                                                            obj_lista_estacion_variables.data[i].OFFSET_CONV
                                                            );
            }
    
}

function sisdad_js_popup_editar_estacion_aniadir_var(COD_VAR,POS , LONGITUD ,FATCONV ,NUMDEC ,OFFSET_TIME ,OFFSET_CONV  ){
   
    var tbl = $('#tbl_dt_sisdad_js_popup_editar_estacion_config_trama_tbl').DataTable();
    
    var nn = parseInt(Math.random() * 99999 + 1); //tbl.rows().count();
    
    tbl.row.add( [
            //tbl_row_count,
            '<input type="hidden" class="tbl_var_row" value="'+nn+'"/><select id="tbl_cb_variable_'+nn+'" class="selectpicker" data-width="100%" data-live-search="true" data-size="10"></select>',
            '<input id="tbl_txt_posicion_'+nn+'" type="text" value="'+POS+'" />',
            '<input id="tbl_txt_longitud_'+nn+'" type="text" value="'+LONGITUD+'" />',
            '<input id="tbl_txt_fact_conv_'+nn+'" type="text" value="'+FATCONV+'" />',
            '<input id="tbl_txt_decimales_'+nn+'" type="text" value="'+NUMDEC+'" />',
            '<input id="tbl_txt_offset_'+nn+'" type="text" value="'+OFFSET_TIME+'" />',
            '<input id="tbl_txt_val_conv_adicional_'+nn+'" type="text"  value="'+OFFSET_CONV+'" />',
            '<button id="tbl_btn_delete_' + nn + '" type="button" class="btn btn-info" onclick="sisdad_js_popup_editar_estacion_borrar_var(this)"><span class="glyphicon glyphicon-trash"></span></button>'
        ] ).draw( false );
        
    var obj_lista_variables = ws('sisdad', 'pkg_ws.sp_obt_lista_variable', '');    
    //console.log(obj_lista_variables);
    ws_contenido_combo("tbl_cb_variable_"+nn, obj_lista_variables.data, COD_VAR);    
}

function sisdad_js_popup_editar_estacion_borrar_var(row) {
    var tbl = $('#tbl_dt_sisdad_js_popup_editar_estacion_config_trama_tbl').DataTable();
    
    tbl.row( $(row).parents('tr') )
        .remove()
        .draw();
}

function sisdad_js_popup_editar_estacion_guardar_config(){
    
    var cod_esta = $("#hd_cod_esta").val();
    var cod_goes = $("#txt_cod_goes").val();
    var cod_goesmarca = $("#cb_tipogoes").val();
    var estado_goes = $("#cb_estado_esta").val();
    
    var response_status = null; 
    var error_reg = null;
    
    console.log(cod_esta + '->' + cod_goes + '->' + cod_goesmarca + '->' + estado_goes );
    
    var obj_reg = ws('sisdad', ' pkg_ws.SP_REG_ESTA_GOES', '["' + cod_esta + '","' + cod_goes + '","' + cod_goesmarca + '","' + estado_goes + '"]');
    console.log("registro cab: " +obj_reg.request.STATUS);
    if (obj_reg.request.STATUS == 'ERROR') {  error_reg = 1; }
    
    var obj_del_var = ws('sisdad', ' pkg_ws.SP_DEL_ESTA_GOES_VAR', '["' + cod_esta + '"]');
    console.log(obj_del_var);
    console.log("del det: " +obj_del_var.request.STATUS);
    if (obj_del_var.request.STATUS == 'ERROR') {  error_reg = 1; }    
    
    
//    if (response_status == 'OK') {
//        response_msn = obj_reg.data[0].MSG;
//        response_type = "green"
//    } else {
//        response_msn = obj_reg.request.MSG;
//        response_type = "red";
//    }
//
    
    $(".tbl_var_row").each(function (index) {       
        var nn = $(this).val();
        var cod_var = $("#tbl_cb_variable_"+nn).val();
        var posicion = $("#tbl_txt_posicion_"+nn).val();
        var longitud = $("#tbl_txt_longitud_"+nn).val();
        var fact_conv = $("#tbl_txt_fact_conv_"+nn).val();
        var decimales = $("#tbl_txt_decimales_"+nn).val();
        var offset = $("#tbl_txt_offset_"+nn).val();
        var val_conv_adicional = $("#tbl_txt_val_conv_adicional_"+nn).val();
        console.log(cod_var + '->' + posicion + '->' + longitud + '->' +trim(fact_conv) + '->' + decimales + '->' + offset + '->' + val_conv_adicional);
        var obj_reg_var = ws('sisdad', ' pkg_ws.SP_REG_ESTA_GOES_VAR', '["' + cod_esta +  '","'+ cod_var + '","' + posicion + '","' + longitud + '","' + trim(fact_conv) + '","' + decimales + '","' + offset + '","' + val_conv_adicional + '"]'); 
        console.log(obj_reg_var);
        if (obj_reg_var.request.STATUS == 'ERROR') {  error_reg = 1; } 
    });
    
    var response_msn = "";
    var response_type = "";
    if(error_reg == null){
        response_msn = "GUARDADO EXITOSO";
        response_type = "green"
    }else{
        response_msn = "ERROR EN EL PROCESO DE GUARDADO, COMUNICAR A LA OTI.";
        response_type = "red";
    }
    
    
        $.confirm({
            title: 'Mensaje:',
            content: response_msn,
            type: response_type,
            typeAnimated: true,
            buttons: {
                close: function () {
//                    location.reload();
                }
            }
        });
    
}

//function popup_editar_estacion_sisdad2(cod_esta) {
//    //console.log(cod_goes + '->' +nom_esta);
//    var url = encodeURI(path + "sisdad/popup_editar_estacion2/?cod_esta=" + cod_esta);
//    //console.log(url);
//    $.colorbox2({
//        "href": url
//        , "width": 1500
//        , "height": 800
//    });
//}


function buscar_maker_map(lon, lat) {
    //console.log(lon + " -> " + lat);
    map.setOptions({zoom: 12, center: new google.maps.LatLng(lat, lon)});
}

function mostrar_tbl_datos() {
    var cod_esta = $('#hd_cod_esta').val();
    var cod_goes = $('#hd_cod_goes').val();
    var fecha_ini = $('#fecha_ini').val();
    var fecha_fin = $('#fecha_fin').val();
    //console.log(fecha_ini + "->" + fecha_fin);

    $.ajax({
        dataType: "html",
        type: "GET",
        url: path + "sisdad/tbl_datos/",
        data: "cod_esta=" + cod_esta + "&cod_goes=" + cod_goes + "&fecha_ini=" + fecha_ini + "&fecha_fin=" + fecha_fin,
        beforeSend: function (data) {
            $('#div_table_datos').html("Cargando...");
        },
        success: function (requestData) {
            $('#div_table_datos').html(requestData);
        },
        error: function (requestData, strError, strTipoError) {
            $('#div_table_datos').html("Error " + strTipoError + ": " + strError);
        }
    });
}

//google.charts.setOnLoadCallback(graf_datos);
function graf_datos() {

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
    var dat_cat = new Array();
    var dat_ser = new Array();
//    var data = new google.visualization.DataTable();
//      data.addColumn('date', 'FECHA');
//      data.addColumn('number', 'valor');

    for (i = 0; i < arrayobj.length; i++) {
        fech = arrayobj[i][0];
        val = arrayobj[i][nro_col];
//         console.log(val);
        var d = fech.substring(0, 2);
        var m = fech.substring(3, 5);
        var y = fech.substring(6, 10);
        var h = fech.substring(11, 13);
        var mi = fech.substring(14, 17);

        //console.log(fech + " : " + val);
//         tmp_array.push([Date(y,(m-1),d,h,mi),parseFloat(val)]);
        if (show_data_hour == true) {
            if (mi == '00') {

                if (show_data_obs == false) {
                    if (val.indexOf("M") == -1 && val.indexOf("D") == -1) {
                        dat_cat.push(fech);
                        dat_ser.push(parseFloat(val));
                        if (val != '') {
                            if (val < val_min)
                                val_min = val;
                            if (val > val_max)
                                val_max = val;
                        }
                    } else {
                        dat_cat.push(fech);
                        dat_ser.push('');
                    }
                } else {
                    dat_cat.push(fech);
                    dat_ser.push(parseFloat(val));
                    if (val != '') {
                        if (val < val_min)
                            val_min = val;
                        if (val > val_max)
                            val_max = val;
                    }
                }

            }
        } else {
            if (show_data_obs == false) {
                if (val.indexOf("M") == -1 && val.indexOf("D") == -1) {
                    //console.log(fech + '->' +val);
                    dat_cat.push(fech);
                    dat_ser.push(parseFloat(val));
                    if (val != '') {
                        if (val < val_min)
                            val_min = val;
                        if (val > val_max)
                            val_max = val;
                    }
                } else {
                    dat_cat.push(fech);
                    dat_ser.push('');
                }
            } else {
                dat_cat.push(fech);
                dat_ser.push(parseFloat(val));
                if (val != '') {
                    if (val < val_min)
                        val_min = val;
                    if (val > val_max)
                        val_max = val;
                }
            }
        }

    }
    val_max = val_max.replace("M", "").replace("D", "");
    console.log('max: ' + val_max + ' // min: ' + val_min);
    $("#sp_val_min").spinner("value", val_min);
    $("#sp_val_max").spinner("value", val_max);

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

//     console.log([{
//                type: 'line',
//                name: 'VALOR',
//                data: dat_ser
//            }]);


    Highcharts.chart('chart_div', {
        chart: {
            zoomType: 'x'
        },
        title: {
            text: "GRAF DE " + nom_esta + " DEL " + fecha_ini + ' AL ' + fecha_fin + ' DE LA VARIABLE ' + col
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

function registrar_rawdata(id, raw_data) {
    console.log(raw_data)
    $.ajax({
        dataType: "html",
        type: "GET",
        url: path + "sisdad/registrar_rawdata/",
        data: "raw_data=" + raw_data,
        beforeSend: function (data) {
            $('#div_fecha_' + id).html("Cargando...");
        },
        success: function (requestData) {
            console.log(requestData);
            arrayobj = jQuery.parseJSON(requestData);
            console.log(arrayobj);
            var cod_esta = arrayobj[0][0];
            var msj = arrayobj[0][1];

            if (cod_esta != '') {
                $('#div_fecha_' + id).html("<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all'><span class='ui-icon ui-icon-circle-check'></span></li></ul></div>");
                $('#div_fecha_' + id).parent().removeClass("ui-state-error").addClass("ui-state-highlight");
            } else {
                $('#div_fecha_' + id).html(msj);
                console.log(msj);
            }
        },
        error: function (requestData, strError, strTipoError) {
            $('#div_fecha_' + id).html("Error " + strTipoError + ": " + strError);
        }
    });
}

function registrar_rawdata_all() {

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

function cambiar_estado_reg(codesta, fecha, param, estado) {
    //console.log(codesta+"->"+fecha+"->"+param+"->"+estado);
    var id = codesta + "_" + fecha.replace(/[/]/g, "").replace(":", "").replace(" ", "") + "_" + param;
    //console.log(id +"->"+estado);
    $.ajax({
        dataType: "html",
        type: "GET",
        url: path + "sisdad/act_estado_reg/",
        data: "codesta=" + codesta + "&fecha=" + fecha + "&param=" + param + "&estado=" + estado,
        beforeSend: function (data) {
            $('#div_reg_' + id).show();
            $('#div_reg_' + id).html("Cargando...");
        },
        success: function (requestData) {
            console.log(requestData);

            if (requestData == 1) {
                $('#div_reg_' + id).html("<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all'><span class='ui-icon ui-icon-check'></span></li></ul></div>");
                //$('#div_reg_'+id).parent().removeClass("ui-state-error").addClass("ui-state-highlight");
            } else {
                console.log(requestData);
                $('#div_reg_' + id).html(requestData);
            }
        },
        error: function (requestData, strError, strTipoError) {
            $('#div_reg_' + id).html("Error " + strTipoError + ": " + strError);
        }
    });
}

function cambiar_factor_reg(codesta, fecha, param, fact) {
    console.log(codesta + "->" + fecha + "->" + param + "->" + fact);
    var id = codesta + "_" + fecha.replace(/[/]/g, "").replace(":", "").replace(" ", "") + "_" + param;
    //console.log(id +"->"+estado);
    $.ajax({
        dataType: "html",
        type: "GET",
        url: path + "sisdad/act_factconver_reg/",
        data: "codesta=" + codesta + "&fecha=" + fecha + "&param=" + param + "&fact=" + fact,
        beforeSend: function (data) {
            $('#div_reg_' + id).show();
            $('#div_reg_' + id).html("Cargando...");
        },
        success: function (requestData) {
            //console.log(requestData);

            if (requestData == 1) {
                $('#div_reg_' + id).html("<div align='center'><ul><li style='width:18px' class='ui-state-default ui-corner-all'><span class='ui-icon ui-icon-check'></span></li></ul></div>");
                //$('#div_reg_'+id).parent().removeClass("ui-state-error").addClass("ui-state-highlight");
            } else {
                console.log(requestData);
                $('#div_reg_' + id).html(requestData);
            }
        },
        error: function (requestData, strError, strTipoError) {
            $('#div_reg_' + id).html("Error " + strTipoError + ": " + strError);
        }
    });
}


function mostrar_mant_esta_goes_tbl() {

    $.ajax({
        dataType: "html",
        type: "GET",
        url: path + "sisdad/mant_esta_goes_tbl/",
        data: "",
        beforeSend: function (data) {
            $('#div_mant_esta_goes_tbl').html("Cargando...");
        },
        success: function (requestData) {
            $('#div_mant_esta_goes_tbl').html(requestData);
        },
        error: function (requestData, strError, strTipoError) {
            $('#div_mant_esta_goes_tbl').html("Error " + strTipoError + ": " + strError);
        }
    });
}


function sisdad_change_cb_selected(btn, codvar) {

    var check = $(btn).attr('cb_checked');
    if (check == 0) {
        $(".cb_var_" + codvar).prop('checked', true);
        $(btn).attr('cb_checked', '1');
    } else {
        $(".cb_var_" + codvar).prop('checked', false);
        $(btn).attr('cb_checked', '0');
    }
}

function sisdad_confirm_cambio_estado_masivo() {
    $.confirm({
        title: '',
        content: 'CAMBIO DE ESTADO MASIVO',
        theme: 'supervan',
        buttons: {
            buttonBuenos: {
                text: 'TODOS LOS DATOS BUENOS',
                action: function () {
                    cambio_estado_masivo('');
                }
            },
            buttonDudosos: {
                text: 'TODOS LOS DATOS DUDOSOS',
                action: function () {
                    cambio_estado_masivo('D');
                }
            }, buttonMalos: {
                text: 'TODOS LOS DATOS MALOS',
                action: function () {
                    cambio_estado_masivo('M');
                }
            },
            buttonCancelar: {
                text: 'CANCELAR'
                        //$.alert('Canceled!');
            }
        }
    });
}

function cambio_estado_masivo(estado) {
    $(".cb_var").each(function (index) {
        var check = $(this).prop('checked');
        if (check) {
            var idesta = $(this).attr('idesta');
            var fecha = $(this).attr('fecha');
            var codvar = $(this).attr('codvar');
            //console.log(idesta + "->" +fecha + "->" +codvar + '->' + estado );
            cambiar_estado_reg(idesta, fecha, +codvar, estado);
        }
    });
}

function sisdad_confirm_cambio_factor_masivo() {
    $.confirm({
        title: 'Prompt!',
        content: '' +
                '<form action="" class="formFactor">' +
                '<div class="form-group">' +
                '<label>Ingrese el factor de conversion para ser aplicado:</label>' +
                '<input type="number" step="0.1" value="1" placeholder="Factor de conversion" class="factor form-control" required />' +
                '</div>' +
                '</form>',
        buttons: {
            formSubmit: {
                text: 'Aplicar Factor',
                btnClass: 'btn-blue',
                action: function () {
                    var factor = this.$content.find('.factor').val();
                    if (!factor) {
                        $.alert('ingresar un factor valido');
                        return false;
                    }
                    cambio_factor_masivo(factor);
                    //$.alert('Your name is ' + factor);
                }
            },
            cancel: function () {
                //close
            },
        },
        onContentReady: function () {
            // bind to events
            var jc = this;
            this.$content.find('form').on('submit', function (e) {
                // if the user submits the form by pressing enter in the field.
                e.preventDefault();
                jc.$$formSubmit.trigger('click'); // reference the button and click it
            });
        }
    });
}


function cambio_factor_masivo(factor) {
    $(".cb_var").each(function (index) {
        var check = $(this).prop('checked');
        if (check) {
            var idesta = $(this).attr('idesta');
            var fecha = $(this).attr('fecha');
            var codvar = $(this).attr('codvar');
            //console.log(idesta + "->" +fecha + "->" +codvar + '->' + factor );
            cambiar_factor_reg(idesta, fecha, +codvar, factor);
        }
    });
}

function sisdad_popup_mant_nueva_estacion() {

    var url = encodeURI(path + "sisdad/mant_nueva_estacion/");
    //console.log(url);
    $.colorbox2({
        "href": url
        , "width": 400
        , "height": 500
    });

}

function sisdad_js_mant_nueva_estacion() {
    var obj_estaciones = ws('sisdad', ' pkg_ws.sp_obt_estaciones', '["",""]');
    //console.log(obj_estaciones);
    var tbl_data = [];
    for (var item = 0; item < obj_estaciones.data.length; item++) {

        var tbl_row = [];
        tbl_row.push(obj_estaciones.data[item].COD_ESTA);
        tbl_row.push(obj_estaciones.data[item].NOM_ESTA + " (" + obj_estaciones.data[item].NOM_CATE + ")");
        tbl_data.push(tbl_row);
    }

    ws_contenido_combo("cb_new_esta_goes", tbl_data, "");
    //---------------------------
    var obj_goesmarca = ws('sisdad', 'pkg_ws.sp_obt_goesmarca', '');
    ws_contenido_combo("cb_tipogoes", obj_goesmarca.data, "");
    //---------------------------
    var data_estado = [];
    var tbl_row = [];
    tbl_row.push("1");
    tbl_row.push("ACTIVO");
    data_estado.push(tbl_row);
    var tbl_row = [];
    tbl_row.push("0");
    tbl_row.push("INACTIVO");
    data_estado.push(tbl_row);
    ws_contenido_combo("cb_estado", data_estado, "");

}

function sisdad_js_mant_nueva_estacion_btn_guardar() {
    var cod_esta = $("#cb_new_esta_goes").val();
    var cod_goes = $("#txt_cod_goes").val();
    var tipogoes = $("#cb_tipogoes").val();
    var estado = $("#cb_estado").val();

    var obj_reg = ws('sisdad', ' pkg_ws.SP_REG_ESTA_GOES', '["' + cod_esta + '","' + cod_goes + '","' + tipogoes + '","' + estado + '"]');
    //console.log(obj_reg);
    var response_status = obj_reg.request.STATUS;
    var response_msn = "";
    var response_type = "";
    if (response_status == 'OK') {
        response_msn = obj_reg.data[0].MSG;
        response_type = "green"
    } else {
        response_msn = obj_reg.request.MSG;
        response_type = "red";
    }

    $.confirm({
        title: 'Mensaje:',
        content: response_msn,
        type: response_type,
        typeAnimated: true,
        buttons: {
            close: function () {
                location.reload();
            }
        }
    });


}

function sisdad_popup_mant_esta_goes(codesta) {
    console.log(codesta);
}


function sisdad_js_mant_ptoobs_subirfile_btn(){
    $.confirm({
        title: '',
        content: '<h3>Subir Archivo?</h3>',
        theme: 'supervan',
        buttons: {
            buttonBuenos: {
                text: 'CONTINUAR',
                action: function () {
                    sisdad_js_mant_ptoobs_subirfile_btn_procesar();
                }
            },
            buttonCancelar: {
                text: 'CANCELAR'
                        //$.alert('Canceled!');
            }
        }
    });
}


function sisdad_js_mant_ptoobs_subirfile_btn_procesar() {

    var x = document.getElementById("ptoobs_file");
    var txt = "";
    var cant_ok = 0;
    var cant_error = 0;
    var error_log = "";
    if ('files' in x) {
        
        if (x.files.length == 0) {
            txt = "Seleccione uno o mas archivos.<br>";
        } else {
            console.log('x1');
            for (var i = 0; i < x.files.length; i++) {
                txt += (i + 1) + ". ";
                var file = x.files[i];
//                if ('name' in file) {
//                    txt += " " + file.name + "<br>";
//                }
                /*
                 if ('size' in file) {
                 txt += "size: " + file.size + " bytes ";
                 }*/
                var reader = new FileReader();
                // Closure to capture the file information.
                reader.onload = (function (theFile) {
                    return function (e) {
                        //jQuery( '#ms_word_filtered_html' ).val( e.target.result );
                        var result = e.target.result;
                        var txt = result.split("\n");
                        var serial = "";
                        var datos = new Array();
                        for (i = 0; i < txt.length; i++) {
                            cad = txt[i];
                            //console.log(cad);
                            if (cad.trim() != '') {
                                if (i == 1) {
                                    serial = cad.substring(85, 110).trim();
                                } else if (i > 1) {
                                    //console.log(cad);									
                                    fechafull = cad.substring(9, 34).trim();

                                    /*corregir fechafull*/

                                    dia = fechafull.substring(0, 2);
                                    mes = fechafull.substring(3, 5);
                                    anio = fechafull.substring(6, 10);

                                    dt = new Date(mes + '/' + dia + '/' + anio);

                                    abrev = fechafull.substring(20, 24);


                                    hor = parseInt(fechafull.substring(11, 13));
                                    if (abrev == 'p.m.' && (hor >= 1 && hor <= 11))
                                        hor = hor + 12;
                                    if (abrev == 'a.m.' && hor == 12)
                                        hor = 0;

                                    min = parseInt(fechafull.substring(14, 16));

                                    hor = min > 52 ? (hor === 23 ? 0 : ++hor) : hor;
                                    cambio_fecha = min > 52 ? (hor === 0 ? 1 : 0) : 0;
                                    min = (Math.round(min / 10) * 10) % 60;

                                    if (cambio_fecha == 1)
                                        dt.setDate(dt.getDate() + 1);

                                    var dd = dt.getDate();
                                    var mm = dt.getMonth() + 1; //January is 0!
                                    var yyyy = dt.getFullYear();


                                    fec = ('00' + dd).slice(-2) + '/' + ('00' + mm).slice(-2) + '/' + yyyy + ' ' + ('00' + hor).slice(-2) + ':' + ('00' + min).slice(-2) + ':00';


                                    d1 = cad.substring(34, 51).trim();
                                    d2 = cad.substring(51, 66).trim();
                                    d3 = cad.substring(66, 81).trim();
                                    d = new Array(serial, fec, d1, d2, d3);
                                    datos.push(d);
                                }
                            }

                        }
//                        var row = new Array();
//                        row['name'] = theFile.name;
//                        row['data'] = datos;
//                        ptoobs_bd_tmp[ptoobs_bd_tmp.length] = row;
                        console.log(serial);
                        console.log(datos);
                        cant_reg = datos.length;
                        for (i = 0; i < datos.length; i++) {
                            serial = datos[i][0];
                            fecha = datos[i][1];
                            val_temp = replaceAll(datos[i][2], '.', ',');
                            val_rh = replaceAll(datos[i][3], '.', ',');
                            val_pr = replaceAll(datos[i][4], '.', ',');

                            cad_json = '["' + serial + '","' + fecha + '","' + val_temp + '","' + val_rh + '","' + val_pr + '"]';
                            console.log(cad_json);
                            var obj_reg = ws('sisdad', 'pkg_ws.sp_reg_ptoobs_dat_codext', cad_json);
                            console.log(obj_reg);
                            var response_status = obj_reg.request.STATUS;
                            var response_msn = "";
                            var response_type = "";
                            if (response_status == 'OK') {
                                response_msn = obj_reg.data[0].MSG;
                                //response_type = "green"
                                cant_ok++;
                                porc = (cant_ok * 100) / cant_reg;
                                $('#pb_subirfile').css({'width': porc + '%'});
                                //$('#id_div_log').append(cad_json + " " + "OK" + "<br>" ); 
                            } else {
                                response_msn = obj_reg.request.MSG;
//                                    //response_type = "red";
                                cant_error++;
                                //$('#id_div_log').append(cad_json + " -> " + response_msn + "<br>"); 
                                error_log += cad_json + " -> " + response_msn + "<br>";
                            }
                        }

                        $('#id_div_log').append("<br>Archivo: " + theFile.name);

                        $('#id_div_log').append("<br>Se procesaron con exito: " + cant_ok + " registros.");
                        $('#id_div_log').append("<br>Se procesaron con error: " + cant_error + " registros." + ((error_log == "") ? "" : "<br> ERRORES:<BR>" + error_log));
                        $('#id_div_log').append("<br>");

                    };
                })(file);

                reader.readAsText(file);

            }
        }
    } else {
        if (x.value == "") {
            txt += "Seleccione uno o mas archivos.";
        } else {
            txt += "The files property is not supported by your browser!";
            txt += "The path of the selected file: " + x.value; // If the browser does not support the files property, it will return the path of the selected file instead. 
        }
    }


//    console.log(ptoobs_bd_tmp);

    if (cant_error > 0) {
        $.alert("Se encontraron error en el proceso, verificar el log!");
    } else {
        $('#ptoobs_file').fileinput('clear');
    }
}

function sisdad_js_mant_ptoobs_reporte(){
    var obj_ptoobs = ws('sisdad', 'pkg_ws.sp_obt_ptoobs', '[""]');
//    console.log(obj_reg);
    var tbl_data = [];
    for (var item = 0; item < obj_ptoobs.data.length; item++) {
        var tbl_row = [];
        tbl_row.push(obj_ptoobs.data[item].COD_PTOOBS);
        tbl_row.push(obj_ptoobs.data[item].NOM_PTOOBS + " (" + obj_ptoobs.data[item].COD_EXTERNO + ")");
        tbl_data.push(tbl_row);
    }
    ws_contenido_combo("cb_ptoobs", tbl_data, "");
}

function sisdad_js_mant_ptoobs_reporte_procesar(){
    
    var error_log = '';
    var ptoobs = $("#cb_ptoobs").val();    
    var fecha_ini = $('#txt_fec_ini').val();
    var fecha_fin = $('#txt_fec_fin').val();
    
    if(ptoobs == null){
        error_log += 'Seleccionar almenos un punto de Observacion.<br>';
    }if(fecha_ini == ''){
        error_log += 'Seleccionar Fecha de inicio.<br>';
    }if(fecha_fin == ''){
        error_log += 'Seleccionar Fecha de fin.<br>';
    }
    
    if(error_log != ''){
        $.alert(error_log,"ERROR");
    }else{
     var cad_ptoobs = ptoobs.toString();     
     //console.log(cad_ptoobs);
     
    var obj_data = ws('sisdad', 'pkg_ws.sp_obt_ptoobs_reportedata', '["'+cad_ptoobs+'","'+fecha_ini+'","'+fecha_fin+'"]');
    console.log(obj_data);
    $('#sisdad_js_mant_ptoobs_data_hd').val(JSON.stringify(obj_data.data));
     
    var obj_data_ptoobs = new Array();
    for(var i= 0 ; i<obj_data.data.length ; i++){
        obj_data_ptoobs.push(obj_data.data[i].COD_EXTERNO);
    }
    var obj_data_ptoobs = Array.from(new Set(obj_data_ptoobs));
    //console.log(obj_data_ptoobs);
    ws_contenido_combo("cb_ptoobs_graf",obj_data_ptoobs,""); 
    
    
      var tbl_cab = [
                {'sTitle': 'ID PTOOBS', 'sClass':'text-center'}, 
                {'sTitle': 'SERIE', 'sClass':'text-center'},
                {'sTitle': 'FECHA', 'sClass':'text-center'},
                {'sTitle': 'TM', 'sClass':'text-center'},
                {'sTitle': 'RH', 'sClass':'text-center'},
                {'sTitle': 'PR', 'sClass':'text-center'},
                {'sTitle': 'TM MAX', 'sClass':'text-center'},
                {'sTitle': 'TM MIN', 'sClass':'text-center'},
                {'sTitle': 'RH MAX', 'sClass':'text-center'},
                {'sTitle': 'RH MIN', 'sClass':'text-center'},
                {'sTitle': 'PR MAX', 'sClass':'text-center'},
                {'sTitle': 'PR MIN', 'sClass':'text-center'}
            ];       
//            for(var i= 0 ; i<obj_data.data.length ; i++){
//                obj_data.data[i].UNIDAD = obj_items.data[i].UNIDAD + 'xxxxx';
//                obj_data.data[i].btn1 = tbl_ext_btn('glyphicon-edit',"$.alert('"+obj_items.data[i].NOMBRE_ITEM+"')") ;
//                obj_data.data[i].btn2 = tbl_ext_btn('glyphicon-trash',"$.alert('"+obj_items.data[i].PREC_UNIT+"')");
//                obj_data.data[i].btn3 = tbl_ext_btn('glyphicon-print');
//            }
            //console.log(obj_items.data);
            
            var opciones = {
                responsive: false
                , bLengthChange: true
                , bInfo: true
                , bPaginate: true
                , buttons: [{extend: 'excel', text: 'Exportar a Excel', className: 'btn btn-info btn-sm'},{extend: 'csv', text: 'Exportar a CSV', className: 'btn btn-info btn-sm'}]
            };
            
            ws_datatable("sisdad_js_mant_ptoobs_reporte_tbl", obj_data.data, tbl_cab, opciones);   
            
    }
}

function sisdad_js_mant_ptoobs_reporte_btn_graf(){
    
    var json_data= $('#sisdad_js_mant_ptoobs_data_hd').val();
    
    var ptoobs = $('#cb_ptoobs_graf').val();
    var ptoobs_var = $('#cb_ptoobs_var').val();
    
    var fecha_ini = $('#txt_fec_ini').val();
    var fecha_fin = $('#txt_fec_fin').val();
    
    var error_log = '';
    if(ptoobs == ''){
        error_log = 'Seleccione un punto de observacion.<br>';
    }
    if(ptoobs_var.length  == 0){
        error_log = 'Seleccione almenos una variable a graficar.<br>';
    }
    
    if(error_log != ''){
        $.alert(error_log,"ERROR");
    }else{
        
        var data = jQuery.parseJSON(json_data);
            var dat_cat = []; //fechas   

            for(var i= 0 ; i<data.length ; i++){        
                if(data[i].COD_EXTERNO == ptoobs){
                   dat_cat.push(data[i].FECHA);
                }        
            }
            var dat_series = []; //por serie  
            for(var x= 0 ; x<ptoobs_var.length ; x++){  
                var dat_series_t = []; //por serie  
                var var_name = ptoobs_var[x];
                dat_series_t["type"] = "line";
                dat_series_t["name"] = var_name;

                var dat_series_datos = [];
                for(var i= 0 ; i<data.length ; i++){        
                    if(data[i].COD_EXTERNO == ptoobs){                
                       if(var_name == 'PR'){
                           dat_series_datos.push(parseFloat(data[i].PR));
                       } 
                       if(var_name == 'RH'){
                           dat_series_datos.push(parseFloat(data[i].RH));
                       } 
                       if(var_name == 'TM'){
                           dat_series_datos.push(parseFloat(data[i].TM));
                       } 
                    }        
                }
                dat_series_t["data"] = dat_series_datos;      

                dat_series.push(dat_series_t);
            }

        //    console.log(dat_series);

            Highcharts.chart('sisdad_js_mant_ptoobs_reporte_graf', {
                chart: {
                    zoomType: 'x'
                },
                title: {
                    text: "Grafico del Punto de Observacion "+ ptoobs + " entre las fechas "+fecha_ini+" al "+fecha_fin
                },
                xAxis: {
                    categories: dat_cat
                },
                legend: {
                    enabled: true
                },
                series: dat_series
            });
        
    }
    
}


function sisdad_js_mant_ptoobs_reporte_resum_procesar(){
    
    var error_log = '';
    var ptoobs = $("#cb_ptoobs").val();    
    var fecha_ini = $('#txt_fec_ini').val();
    var fecha_fin = $('#txt_fec_fin').val();
    
    if(ptoobs == null){
        error_log += 'Seleccionar almenos un punto de Observacion.<br>';
    }if(fecha_ini == ''){
        error_log += 'Seleccionar Fecha de inicio.<br>';
    }if(fecha_fin == ''){
        error_log += 'Seleccionar Fecha de fin.<br>';
    }
    
    if(error_log != ''){
        $.alert(error_log,"ERROR");
    }else{
     var cad_ptoobs = ptoobs.toString();     
     console.log(cad_ptoobs);
     
     var obj_data = ws('sisdad', 'pkg_ws.sp_obt_ptoobs_reportedata_r', '["'+cad_ptoobs+'","'+fecha_ini+'","'+fecha_fin+'"]');
     
      var tbl_cab = [
                {'sTitle': 'ID PTOOBS', 'sClass':'text-center'}, 
                {'sTitle': 'SERIE', 'sClass':'text-center'},
                {'sTitle': 'FECHA', 'sClass':'text-center'},
//                {'sTitle': 'TM', 'sClass':'text-center'},
//                {'sTitle': 'RH', 'sClass':'text-center'},
//                {'sTitle': 'PR', 'sClass':'text-center'},
                {'sTitle': 'TM MAX', 'sClass':'text-center'},
                {'sTitle': 'TM MIN', 'sClass':'text-center'},
                {'sTitle': 'RH MAX', 'sClass':'text-center'},
                {'sTitle': 'RH MIN', 'sClass':'text-center'},
                {'sTitle': 'PR MAX', 'sClass':'text-center'},
                {'sTitle': 'PR MIN', 'sClass':'text-center'}
            ];       
//            for(var i= 0 ; i<obj_data.data.length ; i++){
//                obj_data.data[i].UNIDAD = obj_items.data[i].UNIDAD + 'xxxxx';
//                obj_data.data[i].btn1 = tbl_ext_btn('glyphicon-edit',"$.alert('"+obj_items.data[i].NOMBRE_ITEM+"')") ;
//                obj_data.data[i].btn2 = tbl_ext_btn('glyphicon-trash',"$.alert('"+obj_items.data[i].PREC_UNIT+"')");
//                obj_data.data[i].btn3 = tbl_ext_btn('glyphicon-print');
//            }
            //console.log(obj_items.data);
            
            var opciones = {
                responsive: false
                , bLengthChange: true
                , bInfo: true
                , bPaginate: true
                , buttons: [{extend: 'excel', text: 'Exportar a Excel', className: 'btn btn-info btn-sm'},{extend: 'csv', text: 'Exportar a CSV', className: 'btn btn-info btn-sm'}]
            };
            
            ws_datatable("sisdad_js_mant_ptoobs_reporte_tbl", obj_data.data, tbl_cab, opciones);   
            
     
    }
}