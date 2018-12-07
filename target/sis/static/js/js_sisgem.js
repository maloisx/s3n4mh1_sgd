//buscar tipo planilla
function sisgem_buscar_tipoplantilla_js() {
    var ws_tipo_planilla = ws('sismethaweb','pkg_gem.SP_OBT_TIPOPLANILLA', '');
    /*llenando un combo box*/
    ws_contenido_combo("cb_tipo_planilla", ws_tipo_planilla.data, "");
    //ws para DZ
    var ws_lista_dz = ws('sismethaweb','pkg_gem.SP_OBT_DZ', '[""]');    
    /*llenando un combo box*/
    ws_contenido_combo("cb_dz", ws_lista_dz.data, "");   
}

//cargar estaciones
function sisgem_buscar_plantilla_cargar_cbestaciones(){
    var coddz = $('#cb_dz').val();
    if(coddz != ''){
//        var ws_lista_estacion = ws('sismethaweb','pkg_gem.sp_obt_esta', '["'+coddz+'"]');
//        ws_contenido_combo("cb_estacion", ws_lista_estacion.data, ""); 

//        var ws_data = ws('sisdad','PKG_KORI.SP_OBT_ESTACIONES', '["","'+coddz+'"]');
        var ws_data = ws('sismethaweb','pkg_gem.SP_OBT_ESTACION', '["'+coddz+'"]');
        var data = ws_data.data;
//        console.log(data);
        var list = [];
        for(var i = 0 ; i<data.length ; i++){
            var cod = data[i].COD_ESTA;
            var val = data[i].COD_ESTA + " - " + data[i].NOM_ESTA + " ("+data[i].NOM_CATE+")";
            
            var item = [];
            item[0] = cod ;
            item[1] = val;
            list.push(item);            
        }
        ws_contenido_combo("cb_estacion", list, ""); 
    }else{
        ws_contenido_combo("cb_estacion", "", ""); 
    }
}

//Registro de planilla
function sisgem_registro_planilla(){
    
    var input = document.querySelector('input[type="file"]');   
    
    var file;
    var temporal = '';
    var cta_ok = 0;
    var cta_tmp = 0;
    var cta_existe = 0;
    var cta_nuevo = 0;    
    var msg_reg = '';
    for (var i = 0 ; i < input.files.length ; i++) {
        file = input.files[i];
        
        var nom_archi = file.name;
        var cod_plaimg = nom_archi.substring(0,nom_archi.lastIndexOf("."));
        var dz = nom_archi.substring(0,2);
        var estacion = nom_archi.substring(8,2);
        var tplanilla = nom_archi.substring(10,8);
        var periodo = nom_archi.substring(11,10);
        var cara = nom_archi.substring(12,11);
//        var fecha = nom_archi.substring(20,12);
        var anio = nom_archi.substring(16,12);
        var mes = nom_archi.substring(18,16);
        var dia = nom_archi.substring(20,18);
        var fecha = anio +'/'+ mes +'/'+ dia;        
        var ws_val = ws('sismethaweb','pkg_gem.SP_VALIDA_PLA', '["'+nom_archi+'"]');
        var val_rpta = ws_val.data[0].RPTA_VALIDAR;
        var val_msj = ws_val.data[0].MSJ_VALIDAR; 
        var val_estado = 1; 
        if(val_rpta == 'OK'){
            
            val_estado = 1;
            
            var fdata = new FormData();    
            fdata.append('file', file);
            var xhr = new XMLHttpRequest();            
            xhr.open("POST",path + "sisgem/uploadfile/", true);
            xhr.addEventListener("load", function (e) {
//                console.log(xhr.responseText);                    
            });
            xhr.send(fdata);
            cta_ok +=1;
        }else{            
            val_estado = 0;
            
            temporal +=  nom_archi + ',';  
            var fdata = new FormData();
            fdata.append(0, file);
            //Inicio subir archivos con nomenclatura errada                         
            var xhr = new XMLHttpRequest();
            xhr.open("POST",path + "sisgem/uploadfiletmp/", true);
            xhr.addEventListener("load", function (e) {
            //        pad_mant_adjuntos_cargar(id_doc);//Carga lista de adjuntos                      
            });
            xhr.send(fdata);            
            //fin subir archivos con nomenclatura errada 
            dz = "";
            estacion = "";
            tplanilla = "";
            periodo = "";
            cara = "";
            fecha = "";
            cta_tmp +=1;  
        }
//        var ws_data = ws('sismethaweb','pkg_gem.sp_guardar_plaimg', '["'+nom_archi+'","'+val_estado+'","'+dz+'","'+estacion+'","'+tplanilla+'","'+periodo+'","'+cara+'","'+fecha+'","'+val_msj+'"]');
        var ws_data = ws('sismethaweb','pkg_gem.SP_REGISTRAR_PLAIMG', '["'+nom_archi+'","'+val_estado+'","'+dz+'","'+estacion+'","'+tplanilla+'","'+periodo+'","'+cara+'","'+fecha+'","'+val_msj+'"]');
        var rpta_reg = ws_data.data[0].RPTA;
        var msg_reg = ws_data.data[0].MSG + '<br>' + msg_reg;
        if(rpta_reg == 1){
            cta_nuevo +=1;
        }else{
            cta_existe +=1;
        } 
    }
    
    if (input.files.length > 0){
        var msj = "Registro exitoso: <br>" + cta_ok + " Planilla(s) validadas(s).<br>\n\
                  " + cta_tmp + " Planilla(s) mal nombrada(s).";
        if (cta_nuevo > 0){
            msj += "<br>" + cta_nuevo + " Planilla(s) nueva(s).";
        }
        if (cta_existe > 0){
            msj += "<br>" + cta_existe + " Planilla(s) existente(s):<br>" + msg_reg;
        }        
    }
    $('#file-sisgem').fileinput('clear');
    $.alert('<h6>' + msj + '</h6>');
}
//
//
//INICIO VALIDA SELECCIÓN DE COMBOS EN BUSCAR PLANILLA
function sisgem_validacbo_busarpl_js(){
    var p_dz = $('#hd_dz').val();
    var p_codesta = $('#hd_esta').val();
    var p_codbp = $('#cb_tipo_planilla').val();
   if (p_codbp === '' && p_dz === '' && p_codesta === ''){
//        alert('Debe seleccionar el tipo de planilla / DZ / ESTACIÓN');
        $('#alerta').show();
    }else{ 
        $('#alerta').hide();
        sisgem_buscar_data_js();
    }
}
//FIN VALIDA SELECCIÓN DE COMBOS EN BUSCAR PLANILLA
//
//VALIDA SELECCIÓN DE COMBOS EN BUSCAR POR MAPA
function sisgem_validacbo_busarmapa_js(){
    var p_dz = $('#hd_dz').val();
//    var p_codesta = $('#hd_esta').val();
    var p_codbp = $('#cb_tipo_planilla').val();
   if (p_codbp === '' && p_dz === ''){
//        alert('Debe seleccionar el tipo de planilla / DZ / ESTACIÓN');
        $('#alerta').show();
    }else{ 
        $('#alerta').hide();
        sisgem_mant_mapa_mostrar();
    }
}
//FIN SELECCIÓN DE COMBOS EN BUSCAR POR MAPA
//
//INICIO BUSCAR DATA: ccmvd_dinum_c1 - sismethaweb
function sisgem_buscar_data_js() {
    var p_dz = $('#hd_dz').val();
    var p_codesta = $('#hd_esta').val();
    var p_codbp = $('#cb_tipo_planilla').val();
    
//    if (p_codbp === '' && p_dz === '' && p_codesta === ''){
////        alert('Debe seleccionar el tipo de planilla / DZ / ESTACIÓN');
//        $('#alerta').show();
//    }else{
//        $('#alerta').hide();
    var ws_data = ws('sismethaweb','pkg_gem.SP_DATA_PLANILLA', '["'+p_codesta+'","'+p_codbp+'"]');
//    var dz = $('select[name=cb_dz] option:selected').text();
//    var estacion = $('select[name=cb_estacion] option:selected').text();
//    dz = dz.substr(dz.length-5, dz.length);
//    estacion = dz.substr(estacion.length-5,estacion.length);
//    estacion = estacion.substr(estacion.indexOf('-')+2, estacion.length);
//    var p_dz = $('#cb_dz').val();    
//    var p_codesta = $('#cb_estacion').val();    
//    var p_codbp = $('#cb_tipo_planilla').val();  
//    var ws_data = ws('sismethaweb','pkg_gem.SP_OBT_DATA', '["'+p_codesta+'","'+p_codbp+'"]');
    var tbl_cab = [
                        //{'sTitle': 'ESTACION' , "sClass" : "text-center"}
                        {'sTitle': 'AÑO', "sClass" : "text-center"}
                       ,{'sTitle': 'ENE', "sClass" : "text-center"}
                       ,{'sTitle': 'FEB', "sClass" : "text-center"}
                       ,{'sTitle': 'MAR', "sClass" : "text-center"}
                       ,{'sTitle': 'ABR', "sClass" : "text-center"}
                       ,{'sTitle': 'MAY', "sClass" : "text-center"}
                       ,{'sTitle': 'JUN', "sClass" : "text-center"}
                       ,{'sTitle': 'JUL', "sClass" : "text-center"}
                       ,{'sTitle': 'AGO', "sClass" : "text-center"}
                       ,{'sTitle': 'SET', "sClass" : "text-center"}
                       ,{'sTitle': 'OCT', "sClass" : "text-center"}
                       ,{'sTitle': 'NOV', "sClass" : "text-center"}
                       ,{'sTitle': 'DIC', "sClass" : "text-center"}
                      ];
                      
        var tbl_opc = {
                        responsive: false
                        , bFilter: false
                        , bLengthChange: false
                        , bInfo: true
                        , bPaginate: true
                        , aoColumnDefs : [{ "visible": false, "targets": [] }]
                        , buttons: []
                    };
        
        for(var i= 0 ; i<ws_data.data.length ; i++){
            var anio = ws_data.data[i].V_ANO;            
            ws_data.data[i].M01 = color_tbl_datos_planilla(ws_data.data[i].M01,anio,'01','ENERO');            
            ws_data.data[i].M02 = color_tbl_datos_planilla(ws_data.data[i].M02,anio,'02','FEBRERO');
            ws_data.data[i].M03 = color_tbl_datos_planilla(ws_data.data[i].M03,anio,'03','MARZO');            
            ws_data.data[i].M04 = color_tbl_datos_planilla(ws_data.data[i].M04,anio,'04','ABRIL');
            ws_data.data[i].M05 = color_tbl_datos_planilla(ws_data.data[i].M05,anio,'05','MAYO');
            ws_data.data[i].M06 = color_tbl_datos_planilla(ws_data.data[i].M06,anio,'06','JUNIO');
            ws_data.data[i].M07 = color_tbl_datos_planilla(ws_data.data[i].M07,anio,'07','JULIO');
            ws_data.data[i].M08 = color_tbl_datos_planilla(ws_data.data[i].M08,anio,'08','AGOSTO');
            ws_data.data[i].M09 = color_tbl_datos_planilla(ws_data.data[i].M09,anio,'09','SETIEMBRE');
            ws_data.data[i].M10 = color_tbl_datos_planilla(ws_data.data[i].M10,anio,'10','OCTUBRE');
            ws_data.data[i].M11 = color_tbl_datos_planilla(ws_data.data[i].M11,anio,'11','NOVIEMBRE');
            ws_data.data[i].M12 = color_tbl_datos_planilla(ws_data.data[i].M12,anio,'12','DICIEMBRE');
        }        
        ws_datatable("tabla_data",ws_data.data , tbl_cab  , tbl_opc);
        
        $('#div_leyenda').show();
//    }
}

function color_tbl_datos_planilla(valor, p_anio, p_mes, p_nommes){
    var pnommes = "'" + p_nommes + "'";
    var p_mes = "'" + p_mes + "'";
    var color = '';
    /*        planilla / digitado
        1 =      si     /   si     /   verde
        2 =      si     /   no     /   azul
        3 =      no     /   si     /   naranja
        4 =      no     /   no     /   blanco 
    */
    if(valor == 1){
//        color = 'green';
        color = 'btn-success';
    }
    if(valor == 2){
//        color = 'blue';
        color = 'btn-info';
    }
    if(valor == 3){
//        color = 'orange';
        color = 'btn-warning';
    }
    if(valor == 4){
        color = 'btn-white';
//        color = 'btn-default';
    }
   
    if(valor != ''){
        if (valor == 1 || valor == 2){
//            return '<a class="btn-floating btn-sm '+color+'"></a><button type="button" class="btn  '+color+' btn-sm"><span class="glyphicon glyphicon-edit"></span></button>';
            return '<button type="button" class="btn  '+color+' btn-sm"><span class="glyphicon glyphicon-search" onclick="sisgem_visualiza_planilla_js('+p_anio+','+p_mes+','+pnommes+')"></span></button>';             
        }else if(valor == 3){
//            return '<a class="btn-floating btn-sm '+color+'"></a>';
            return '<button type="button" class="btn  '+color+' btn-sm"><span class="glyphicon glyphicon-list-alt"></span></button>';
        }else if(valor == 4){
//            return '<a class="btn-floating btn-sm '+color+'"></a>';
            return '<button type="button" class="btn  '+color+' btn-sm"><span class="glyphicon glyphicon-ban-circle"></span></button>';
        }
    }else{
        return '';
    }
}
//FIN BUSCAR DATA: ccmvd_dinum_c1 - sismethaweb
//
//INICIO VISUALIZAR PLANILLA
function sisgem_visualiza_planilla_js(p_anio, p_mes, pnommes){    
    var p_dz = $('#hd_dz').val();
    var p_codesta = $('#hd_esta').val();
    var p_codbp = $('#hd_tplanilla').val();
//    p_mes = ('00'+p_mes).slice(-'00'.length);
    
    var tpla = $('select[name=cb_tipo_planilla] option:selected').text();
    var dz = $('select[name=cb_dz] option:selected').text();    
    var esta = $('select[name=cb_estacion] option:selected').text();
    var extrae_esta = esta.indexOf('(');
    var nom_esta = esta.substring(0,extrae_esta);
    $('#lb_title_modal_tplanilla').text(tpla);
    $('#lb_title_modal_dz').text(dz);
    $('#lb_title_modal_esta').text(esta);
    
    if ($('#lb_dz').length){
        $('#lb_title_modal_dz').text($('#lb_dz').text());
        $('#lb_title_modal_esta').text($('#lb_esta').text());
        dz = $('#lb_dz').text();
        nom_esta = $('#lb_esta').text();
    }
    
    if ($('#lb_bplanilla').length){
        $('#lb_title_modal_tplanilla').text($('#lb_bplanilla').text());
        tpla = $('#lb_bplanilla').text();
    }
    
    //busca planillas (digitalizadas) registradas 
    var ws_data = ws('sismethaweb','pkg_gem.SP_OBT_RUTA', '["'+p_dz+'","'+p_codesta+'","'+p_codbp+'","'+p_mes+'","'+p_anio+'"]');
    var long_ws_data = ws_data.data.length;
    var archivo = "";
    var cara = "";
    var links = '<div id="lightgallery" class="col-md-10">';
    for(var i=0 ; i<long_ws_data ; i++){
        archivo = ws_data.data[i].RUTA;
        cara = archivo.charAt(11);
        var urlimg = "http://sgd.senamhi.gob.pe/files/sisgem/"+p_dz+"/"+p_anio+"/"+p_codesta+"/"+p_codbp+"/"+archivo;
        var extencion = archivo.substring(archivo.lastIndexOf("."));
        if (extencion === '.tif'){
            urlimg = encodeURI(path + "sisgem/mant_convtiff/?url="+urlimg);            
        }        
        links += '<a id="link'+i+'" href="'+urlimg+'" data-sub-html="'+dz+'<br>'+nom_esta+' - '+tpla+' - '+p_anio+' - '+pnommes+'<br>Cara '+cara+'">\n\
                            <div class="caption col-md-3 text-center">\n\
                                <img id="Myid'+i+'" src="'+urlimg+'" width="80">\n\
                                <p><small>Cara: '+cara+'</small></p>\n\
                            </div>\n\
                        </a>';
    }
    links += '</div>';
    $('#imagenes').html(links);
    $('#lightgallery').lightGallery({
        pager: true,
        thumbnail:true,
        subHtmlSelectorRelative: true
    });
    $('#modal_visualiza').modal();     
}
//FIN VISUALIZAR PLANILLA
//
//INICIO LISTA NOMENCLATURA ERRADA
function sisgem_lista_errada_js() {
    
    var p_codesta = '';
    var ws_lista_errada = ws('sismethaweb','pkg_gem.SP_OBT_LISTAERRADA', '["'+p_codesta+'"]');
    
    var tbl_cab = [
                        {'sTitle': 'ID' , "sClass" : "text-center"}
                       ,{'sTitle': 'NOMBRE ARCHIVO' , "sClass" : "text-center"}
                       ,{'sTitle': 'ERRORES'}
                       ,{'sTitle': 'EDITAR'}
                      ];
        
        var tbl_opc = {
                        responsive: false
                        , bFilter: false
                        , bLengthChange: false
                        , bInfo: true
                        , bPaginate: true
                        , aoColumnDefs : [{ "visible": false }]
                        , buttons: []
                    };
        
        for(var i= 0 ; i<data.length ; i++){
            data[i].btn1 = tbl_ext_btn('glyphicon-edit',"sisgem_detalles_js('"+ws_lista_errada.data[i].CODPLA+"','"+ws_lista_errada.data[i].RUTA+"','"+ws_lista_errada.data[i].OBSERVACION+"');") ;
        }    
        ws_datatable("tabla_prueba", ws_lista_errada.data, tbl_cab  , tbl_opc);        
}
//FIN LISTA NOMENCLATURA ERRADA
//
//INICIO DETALLES ARCHIVO
function sisgem_detalles_js(p_idpla, p_ruta, p_error){
    $('#hd_modal_manteniemiento_idpla').val(p_idpla);
    $('#hd_modal_manteniemiento_nomarchi').val(p_ruta);
    $('#txt_modal_archivo').val(p_ruta);
    $('#txt_modal_error').val(p_error);
    $('#modal_mantenimiento').modal();
}
//FIN DETALLES ARCHIVO
//
//INICIO VER ARCHIVO
function sisgem_ver_archivo(p_ruta){
    window.open("http://sgd.senamhi.gob.pe/files/sisgem/DirTem/"+p_ruta, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=200,width=800,height=500");
}
//FIN VER ARCHIVO
//
//INICIO VALIDAR NOMBRE
function sisgem_valida_nombre(p_idp, p_archi, p_errado, p_tinterf){
    
    var p_id = p_idp;
    var nom_archi = p_archi;
    var cod_plaimg = nom_archi.substring(0,nom_archi.lastIndexOf("."));
    var dz = nom_archi.substring(0,2);
    var estacion = nom_archi.substring(8,2);
    var tplanilla = nom_archi.substring(10,8);
    var periodo = nom_archi.substring(11,10);
    var cara = nom_archi.substring(12,11);
    var anio = nom_archi.substring(16,12);
    var mes = nom_archi.substring(18,16);
    var dia = nom_archi.substring(20,18);
    var fecha = anio +'/'+ mes +'/'+ dia;
    
    var ws_val = ws('sismethaweb','pkg_gem.SP_VALIDA_PLA', '["'+nom_archi+'"]');
    var val_rpta = ws_val.data[0].RPTA_VALIDAR; 
    var val_msj = ws_val.data[0].MSJ_VALIDAR;    
    
    if(val_rpta == 'OK'){
        var val_estado = 1;
        
        var ws_data = ws('sismethaweb','pkg_gem.sp_editar_plaimg', '["'+p_id+'","'+nom_archi+'","'+val_estado+'","'+dz+'","'+estacion+'","'+tplanilla+'","'+periodo+'","'+cara+'","'+fecha+'","'+val_msj+'"]');

        $.ajax({
                dataType: "html",
                type:     "GET",
                url:      path + "sisgem/mant_copyfile/", 
                data:     "nom_archi="+nom_archi+
                          "&p_errado="+p_errado+
                          "&p_tinterf="+p_tinterf
            });
        var msj = "Actualización exitosa. Planilla corregida.";    
        $.alert('<h6>'+ msj +'</h6>');  
//        $('#modal_mantenimiento').hide();
        $(function () {
            $('#modal_mantenimiento').modal('toggle');
        });
        sisgem_lista_errada_js();
    }else{
        var msj = "Error. Planilla mal nombrada.";
        $.alert('<h6>'+ msj +'</h6>');
    }
}
//FIN VALIDAR NOMBRE
//
//INICIO BUSCAR MAPA
function sisgem_mant_mapa_mostrar(){
    var cod_dz = $('#cb_dz').val();
    var cod_estacion = $('#cb_estacion').val();
    
    pintarmapa('div_map');
    var ws_data = ws('sisdad','PKG_KORI.SP_OBT_ESTACIONES', '["'+cod_estacion+'","'+cod_dz+'"]');//cambiar PROCEDURE al esquema SISMETHA
    var data = ws_data.data;
//    console.log(data);
   
    for(var i=0;i<data.length;i++){        
        aniadirmarker(data[i].COD_ESTA, data[i].NOM_ESTA, data[i].LON, data[i].LAT, data[i].NOM_CATE);
    }
    centrarmapa();    
}
//FIN BUSCAR MAPA
//
//INICIO OPCIONES MAPA
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
//FIN OPCIONES MAPA
//
//INICIO PINTAR MAPA
function pintarmapa(divmapa) {
    map = new google.maps.Map(document.getElementById(divmapa), optionsmaps);
};
//FIN PINTAR MAPA
//
//INICIO AÑADIR MARCADORES
function aniadirmarker(codestacion, estacion, lng, lat, tipo) {
//    if (tipo == 'METEOROLOGICA') {
        pathicon = path + 'static/img/localizacion_verde.png';
//    } else if (tipo == 'HIDROLOGICA') {
//        pathicon = path + 'static/img/localizacion_rojo.png';
//    } else {
//        pathicon = path + 'static/img/localizacion_amarillo.png';
//    }
   
    css = 'labelsgooglemaps';

    var marker = new MarkerWithLabel({
        position: new google.maps.LatLng(lat, lng),
        draggable: false,
        map: map,
        //raiseOnDrag: true,
        icon: pathicon,
        zoomControl: true,
        labelClass: css,
        labelStyle: {opacity: 0},
        labelInBackground: true
    });
    //añadiendo la ventana del evento hover
    createTooltip(marker, estacion, tipo);

    //copiar datos a campos
    google.maps.event.addListener(marker, 'click', function () {
//        estacion_datos(codestacion, estacion, sub_esta, tipo, dpto, prov, dtrito);
//        alert(codestacion, pathicon);
        $("#cb_estacion").val(codestacion);
        $('#cb_estacion').change();
        sisgem_buscar_data_js();
    });
    markersArray.push(marker);
}
//FIN AÑADIR MARCADORES
//
//INICIO CREAR TOOLTIP
function createTooltip(marker, estacion, tipo) {
    if (tipo == 'M'){
        tipo = 'Meteorológica';
    }else if (tipo == 'H'){
        tipo = 'Hidrológica';
    }        
    var tooltipOptions = {
        marker: marker,
        content: estacion +'<br>'+ tipo,
        cssClass: 'tooltip_googlemaps' // Nombre de la clase ha aplicar tooltip
    };
    var tooltip = new Tooltip(tooltipOptions);
}
//FIN CREAR TOOLTIP
//
//INICIO REMOVER MARCADORES
function removermarkers(markersArray) {
    for (var i = 0; i < markersArray.length; i++) {
        markersArray[i].setMap(null);
    }
    markersArray.length = 0;
}
//FIN REMOVER MARCADORES
//
//INICIO CENTRAR MAPA
function centrarmapa() {
    if (markersArray.length > 0) {
        //console.log('m1');
        var limits = new google.maps.LatLngBounds();
        for (var marker = 0; marker < markersArray.length; marker++) {
//             console.log(markersArray[marker]);
            limits.extend(markersArray[marker].getPosition());
        }
        map.fitBounds(limits);
    } else {
        map.setOptions({zoom: startZoom, center: new google.maps.LatLng(centerLatitude, centerLongitude)});
    }
}
//FIN CENTRAR MAPA
//
//INICIO DIRECCIÓN ZOONAL
function sisgem_cargar_dz(){
    var ws_data = ws('sismethaweb','pkg_gem.SP_OBT_DZ', '[""]');
    var data = ws_data.data;
    
    var cod_esta = "";
    var nom_esta = "";
    var fec_ini = "";
    var fec_fin = "";
    var nom_cate = "";
    var cad = "";
    var nomesta = "";
    var cod_dreg = "";
    
    cad += "<div class='panel-group'>\n\
                <div class='panel panel-default'>";
    for(var i=0; i<data.length; i++){    
        var cod_dz = data[i].CODDREG;
        var nom_dz = data[i].NOMDREG;
        var nomdz = "'" + nom_dz + "'";
        cad += '<div class="panel-heading" id='+nom_dz+'>\n\
                    <h4 class="panel-title">\n\
                        <a data-toggle="collapse" data-parent="#acordion" href="#collapse'+i+'">'+nom_dz+'</a>\n\
                    </h4>\n\
                </div>\n\
                <div id="collapse'+i+'" class="panel-collapse collapse">\n\
                    <ul class="list-group">\n\
                        <table class="table table-bordered">\n\
                            <thead>\n\
                                <tr>\n\
                                    <th style="width: 15%">Código</th>\n\
                                    <th style="width: 55%">Estación</th>\n\
                                    <th style="width: 15%">Fec.Ini.</th>\n\
                                    <th style="width: 15%">Fec.Fin</th>\n\
                                </tr>\n\
                            </thead>\n\
                            <tbody>';
                var ws_data_esta = ws('sismethaweb','pkg_gem.SP_ESTA_FECEXTR_PLUCLI', '["'+cod_dz+'"]');
                var data_esta = ws_data_esta.data;
                for(var j=0; j<data_esta.length; j++){
                    cod_esta = data_esta[j].COD_ANESTA;
                    nom_esta = data_esta[j].NOM_ESTA;
                    fec_ini = data_esta[j].V_MIN;
                    fec_fin = data_esta[j].V_MAX;
                    nom_cate = data_esta[j].NOM_CATE;
                    cod_esta = "'" + cod_esta + "'";
                    nomesta = "'" + nom_esta + "'";
                    cod_dreg = "'" + cod_dz + "'";
                    
                    cad += '<tr>\n\
                                <td style="width: 15%">'+cod_esta+'</td>\n\
                                <td style="width: 55%" onclick="sisgem_pasa_para_js('+cod_esta+','+cod_dreg+','+nomdz+','+nomesta+')"><a href="#">'+nom_esta+' ('+nom_cate+')</a></td>\n\
                                <td style="width: 15%">'+fec_ini+'</td>\n\
                                <td style="width: 15%">'+fec_fin+'</td>\n\
                            </tr>';
            }
        cad += '</tbody>\n\
                </table>\n\
                </ul>\n\
                </div>';
    }
    cad += '</div>\n\
            </div>';
    $('#accordion').html(cad);
}
//FIN DIRECCIÓN ZOONAL
//
//INICIO PASAR PARÁMETROS VISTA BUSQUEDA EN MAPA
function sisgem_pasa_para_js(cod_esta, cod_dz, nomdz, nomesta){
    var p_esta = cod_esta;
//    var p_esta = ('000000'+cod_esta).slice(-'000000'.length);
//    var p_dz = ('00'+cod_dz).slice(-'00'.length);
    $('#hd_esta').val(p_esta);
    $('#hd_dz').val(cod_dz);
    $('#lb_dz').text(nomdz);
    $('#lb_esta').text('ESTACIÓN: '+nomesta);
    sisgem_buscar_data_js();
}
//FIN PASAR PARÁMETROS VISTA BUSQUEDA EN MAPA
//
//INICIO EDITAR NOMBRES
function sisgem_edita_nombre(){
    var p_codbp = $('#cb_tipo_planilla').val();
    var p_dz = $('#hd_dz').val();
    var p_codesta = $('#hd_esta').val();
        
    var ws_lista_planilla = ws('sismethaweb','pkg_gem.SP_LISTA_PLANILLA', '["'+p_codbp+'","'+p_dz+'","'+p_codesta+'"]');
        
        var tbl_cab = [
                        {'sTitle': 'ID' , "sClass" : "text-center"}
                       ,{'sTitle': 'DIRECCIÓN ZONAL', "sClass" : "text-center"}
                       ,{'sTitle': 'ESTACIÓN', "sClass" : "text-center"}
                       ,{'sTitle': 'PLANILLA', "sClass" : "text-center"}
                       ,{'sTitle': 'COD.PERIODO', "sClass" : "text-center"}
                       ,{'sTitle': 'PERIODO', "sClass" : "text-center"}
                       ,{'sTitle': 'N° CARA', "sClass" : "text-center"}
                       ,{'sTitle': 'AÑO', "sClass" : "text-center"}
                       ,{'sTitle': 'COD.MES', "sClass" : "text-center"}
                       ,{'sTitle': 'MES', "sClass" : "text-center"}
                       ,{'sTitle': 'ARCHIVO', "sClass" : "text-center"}
                       ,{'sTitle': 'EDITAR'}
                      ];        
        var tbl_opc = {
                        responsive: false
                        , bFilter: false
                        , bLengthChange: false
                        , bInfo: true
                        , bPaginate: true
                        , aoColumnDefs : [{"aTargets": [0,4,8], "visible": false }]
                        , buttons: []
                    };
        
        for(var i= 0 ; i<ws_lista_planilla.data.length ; i++){
            //Editar planilla
            ws_lista_planilla.data[i].btn1 = tbl_ext_btn('glyphicon-edit',"sisgem_editacodigo_js('"+ws_lista_planilla.data[i].CODPLA+"','"+ws_lista_planilla.data[i].RUTA+"','"+ws_lista_planilla.data[i].ANIO+"','"+ws_lista_planilla.data[i].MES+"','"+ws_lista_planilla.data[i].NOMMES+"','"+ws_lista_planilla.data[i].NOMDREG+"','"+ws_lista_planilla.data[i].NOMESTA+"','"+ws_lista_planilla.data[i].BPLANILLA+"');") ;
        }    
        ws_datatable("div_lista_planilla", ws_lista_planilla.data, tbl_cab  , tbl_opc);
}
//FIN EDITAR NOMBRES
//
//INICIO EDITAR CODIGO
function sisgem_editacodigo_js(p_idpla, p_ruta, p_anio, p_mes, p_nommes, nomdreg, nomesta, bplanilla){    
    $('#hd_modal_manteniemiento_idpla').val(p_idpla);    
    $('#hd_modal_manteniemiento_anio').val(p_anio);
    $('#hd_modal_manteniemiento_mes').val(p_mes);
    $('#hd_modal_manteniemiento_nommes').val(p_nommes);
                
//    if($('#hd_dz').val() == ''){
        var dz = p_ruta.substring(0,2);
        $('#hd_dz').val(dz);
//    }
//    if($('#hd_esta').val() == ''){
        var estacion = p_ruta.substring(8,2);
        $('#hd_esta').val(estacion);
//    }
//    if($('#hd_tplanilla').val() == ''){
        var tplanilla = p_ruta.substring(10,8);
        $('#hd_tplanilla').val(tplanilla);
//    }
    $('#lb_dz').text(nomdreg);
    $('#lb_esta').text(estacion + ' - ' + nomesta);
    $('#lb_bplanilla').text(bplanilla);
    $('#lb_title_modal_tplanilla').text(bplanilla);
    
    $('#lbl_modal_archivo').addClass('lbl_active active');
    $('#txt_modal_archivo').val(p_ruta);
    $('#lbl_modal_archivocorrecto').addClass('lbl_active active');    
    $('#txt_modal_archivocorrecto').val(p_ruta);
    //nombres de los combos tipo_planilla y dz
    var idcbo_tplanilla = 'modal_cb_tipo_planilla';
    var idcbo_dz = 'modal_cb_dz';
    sisgem_cargar_cbos_js(idcbo_tplanilla, idcbo_dz);    
    sisgem_visualiza_planilla_js(p_anio, p_mes, p_nommes);
//    $('#tituloimg').hide();
//    $('#imagenes').hide();
    $('#modal_visualiza').modal();   
}
//FIN EDITAR CODIGO
//
//INICIO LLENAR COMBOS TIPO_PLANILLA Y DZ
function sisgem_cargar_cbos_js(idcbo_tplanilla, idcbo_dz) {
    var ws_tipo_planilla = ws('sismethaweb','pkg_gem.SP_OBT_TIPOPLANILLA', '');
    /*llenando un combo box*/    
    ws_contenido_combo(idcbo_tplanilla, ws_tipo_planilla.data, "");    
    //ws para DZ
    var ws_lista_dz = ws('sismethaweb','pkg_gem.SP_OBT_DZ', '[""]');    
    /*llenando un combo box*/
    ws_contenido_combo(idcbo_dz, ws_lista_dz.data, "");   
}
//FIN LLENAR COMBOS TIPO_PLANILLA Y DZ
//
//INICIO CARGAR ESTACIONES
function sisgem_cargar_cbestaciones_js(idcbo_dz, idcbo_estacion){
    var coddz = $('#'+ idcbo_dz).val();
    if(coddz != ''){
        var ws_data = ws('sismethaweb','pkg_gem.SP_OBT_ESTACION', '["'+coddz+'"]');
        var data = ws_data.data;
        var list = [];
        for(var i = 0 ; i<data.length ; i++){
            var cod = data[i].COD_ESTA;
            var val = data[i].COD_ESTA + " - " + data[i].NOM_ESTA + " ("+data[i].NOM_CATE+")";
            
            var item = [];
            item[0] = cod ;
            item[1] = val;
            list.push(item);            
        }
        ws_contenido_combo(idcbo_estacion, list, ""); 
    }else{
        ws_contenido_combo(idcbo_estacion, "", ""); 
    }
}
//FIN CARGAR ESTACIONES
//
//INICIO CODIGO PLANILLA
function sisgem_codigoplanilla_js(){
    var codigoarchi= $('#txt_modal_archivocorrecto').val();
    var dz = codigoarchi.substring(0,2);
    var estacion = codigoarchi.substring(8,2);
    var tplanilla = codigoarchi.substring(10,8);
    var periodo = codigoarchi.substring(11,10);
    var cara = codigoarchi.substring(12,11);
//        var fecha = nom_archi.substring(20,12);
    var anio = codigoarchi.substring(16,12);
    var mes = codigoarchi.substring(18,16);
    var dia = codigoarchi.substring(20,18);
    var extencion = codigoarchi.substring(codigoarchi.lastIndexOf("."));    
}
//FIN CODIGO PLANILLA
//
//INICIO CÓDIGO VALIDO TIPO PLANILLA
function sisgem_codtp_valido_js(tplanilla_n){
    var codigoarchi= $('#txt_modal_archivocorrecto').val();
    var cod1 = codigoarchi.substring(0,8);
    var cod2 = codigoarchi.substring(10,20);
    var extencion = codigoarchi.substring(codigoarchi.lastIndexOf("."));  
    var codigoarchi_n = cod1 + tplanilla_n + cod2 + extencion;
    $('#txt_modal_archivocorrecto').val(codigoarchi_n);
}
//FIN CÓDIGO VALIDO TIPO PLANILLA
//
//INICIO CÓDIGO VÁLIDO DZ
function sisgem_coddz_valido_js(dz_n){
    var codigoarchi= $('#txt_modal_archivocorrecto').val();
    var cod2 = codigoarchi.substring(2,20);
    var extencion = codigoarchi.substring(codigoarchi.lastIndexOf("."));
    var codigoarchi_n = dz_n + cod2 + extencion;
    $('#txt_modal_archivocorrecto').val(codigoarchi_n);
}
//FIN CÓDIGO VÁLIDO DZ
//
//INICIO CÓDIGO VÁLIDO ESTACION
function sisgem_codesta_valido_js(codesta_n){
    var codigoarchi= $('#txt_modal_archivocorrecto').val();
    var cod1 = codigoarchi.substring(0,2);
    var cod2 = codigoarchi.substring(8,20);
    var extencion = codigoarchi.substring(codigoarchi.lastIndexOf(".")); 
    var codigoarchi_n = cod1 + codesta_n + cod2 + extencion;
    $('#txt_modal_archivocorrecto').val(codigoarchi_n);
}
//FIN  CÓDIGO VÁLIDO ESTACION
//
//INICIO LISTA DE USUARIOS
function sisgem_lista_usuarios_js() {
        
    var ws_lista_usuario = ws('seguridad.fn_user_consulta', '[""]');
        
    $('#tabla_usuario').html('');
    for (var i = 0; i < ws_lista_usuario.data.length; i++) {
        ws_lista_usuario.data[i].btn1 = tbl_ext_btn('glyphicon-edit',"sisgem_editausuario_js('"+ws_lista_usuario.data[i].id_persona+"', '"+ws_lista_usuario.data[i].servidor+"', '"+ws_lista_usuario.data[i].des_user+"', '"+ws_lista_usuario.data[i].id_perfil+"', '"+ws_lista_usuario.data[i].des_uo+"');");
    }    
    var tbl_cab = [
                        {'sTitle': 'SERVIDOR'}
                       ,{'sTitle': 'USUARIO'}
                       ,{'sTitle': 'PERFIL'}
                       ,{'sTitle': 'UNIDAD ORG.'}
                       ,{'sTitle': 'COD_SERVIDOR'}
                       ,{'sTitle': 'COD_PERFIL'}
                       ,{'sTitle': 'COD_UO'}
                       ,{'sTitle': 'EDITAR', "sClass" : "text-center"}
                      ];  
    var tbl_opc = {
                        responsive: false
                        , bFilter: false
                        , bLengthChange: false
                        , bInfo: true
                        , bPaginate: true
                        , aoColumnDefs : [{"aTargets": [4, 5, 6], "visible": false }]
                        , buttons: []
                    };
    ws_datatable("tabla_usuario", ws_lista_usuario.data, tbl_cab, tbl_opc);       
}
//FIN LISTA NOMENCLATURA ERRADA
//
//INICIO EDITA USUARIO
function sisgem_editausuario_js(p_idpersona, p_nompersona, p_user, p_idperfil, p_id_uo){    
    $('#hd_modal_mant_idpersona').val(p_idpersona);    
    $('#txt_modal_nompersona').val(p_nompersona);
    $('#txt_modal_uo').val(p_id_uo);
    $('#txt_modal_usuario').val(p_user);
    $('#cb_modal_perfil').val(p_idperfil);
                
 
    $('#modal_mantenimiento').modal();   
}
//FIN EDITAR USUARIO