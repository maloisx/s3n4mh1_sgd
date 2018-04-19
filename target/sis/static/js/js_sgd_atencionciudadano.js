//ATENCIÓN AL CIUDADANO

//INICIO BUSCAR EXPEDIENTE ATENCIÓN AL CIUDADANO    
function sgd_mant_atencion_ciudadano_tbl(){
    var exp = $('#txt_expediente').val();
    var anio = $('#cb_anio').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_atencion_ciudadano_tbl/",
            data:     "exp="+exp+
                      "&anio="+anio,
            beforeSend: function(data){
                $('#div_atencion_ciudadano_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_atencion_ciudadano_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_atencion_ciudadano_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE ATENCIÓN AL CIUDADANO
//
//INICIO BUSCAR PRIMER DOCUMENTO
function sgd_mant_atencion_ciudadano_doc(){
    var exp = $('#txt_expediente').val();
    var anio = $('#cb_anio').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_atencion_ciudadano_doc/",
            data:     "exp="+exp+
                      "&anio="+anio,
            beforeSend: function(data){
//                $('#div_asunto').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);	
                var asunto  = arrayobj[0][0];
                $('#txt_asunto').val(asunto);                
            },
            error: function(requestData, strError, strTipoError){
//                $('#div_asunto').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR PRIMER DOCUMENTO
//
//INICIO BUSCAR DOCUMENTO
function sgd_mant_busca_ciudadano_dni(tipo_doc){
    var n_doc = $('#txt_dni').val();
    var t_doc = tipo_doc;
    var msj_error = "";
    if(n_doc == ''){
        msj_error = "N° de documento";
    }
    if(msj_error == ''){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_busca_ciudadano_dni/",
            data:     "n_doc="+n_doc+
                      "&t_doc="+t_doc,
            beforeSend: function(data){
//                $('#div_asunto').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                               
                var id  = "";
                var ndocumento  = "";
                var nombres  = "";
                var direccion  = "";
                var email  = "";
                var telefono  = "";
                $('#div_per_natural').show();
                if (arrayobj.length !== 0){
                    id  = arrayobj[0][0];
                    ndocumento  = arrayobj[0][1];
                    nombres  = arrayobj[0][2];
                    direccion  = arrayobj[0][4];
                    email  = arrayobj[0][5];
                    telefono  = arrayobj[0][6];

                    $('#hd_id').val(id);
                    $('#txt_nombres').val(nombres);
                    $('#lb_nombres').addClass('active');
                    $('#txt_direccion').val(direccion);
                    $('#lb_direccion').addClass('active');
                    $('#txt_email').val(email);
                    $('#lb_email').addClass('active');
                    $('#txt_telefono').val(telefono);
                    $('#lb_telefono').addClass('active');
                    $('#btn_guarda_ciudadano_dni').text('Actualizar');
                    $('#div_msg_registro').hide();
                    $('#div_cbo_servicio').show();
                    sgd_mant_mapa_mostrar();
                }else if (arrayobj.length == 0){
                    $('#div_msg_registro').text('Registre sus datos.');
                    $('#div_msg_registro').show();
                    $('#div_guarda_ciudadano').show();
                    $('#txt_nombres').val(nombres);
                    $('#txt_direccion').val(direccion);
                    $('#txt_email').val(email);
                    $('#txt_telefono').val(telefono);
                }
            },
            error: function(requestData, strError, strTipoError){
                $('#div_msg_registro').show();
//                $('#div_msg_registro').html("Error " + strTipoError +": " + strError);            
            }
        });
        }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }   
}
//FIN BUSCAR DOCUMENTO
//
//INICIO BUSCAR RUC
function sgd_mant_busca_ciudadano_ruc(tipo_doc){
    var n_doc = $('#txt_ruc').val();
    var t_doc = tipo_doc;
    var msj_error = "";
    if(n_doc == ''){
        msj_error = "N° de RUC";
    }
    if(msj_error == ''){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_busca_ciudadano_dni/",
            data:     "n_doc="+n_doc+
                      "&t_doc="+t_doc,
            beforeSend: function(data){
//                $('#div_asunto').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                               
                var id  = "";
                var ndocumento  = "";
                var rsocial = "";
                var direccion  = "";
                var email  = "";
                var telefono  = "";
                var sector  = "";
                var representante  = "";
                var telef_rep  = "";
                var email_rep  = "";
                $('#div_per_juridica').show();
                                
                if (arrayobj.length !== 0){
                    id  = arrayobj[0][0];
                    ndocumento  = arrayobj[0][1];
                    rsocial  = arrayobj[0][2];
                    direccion  = arrayobj[0][4];
                    email  = arrayobj[0][5];
                    telefono  = arrayobj[0][6];
                    sector  = arrayobj[0][9];
                    representante  = arrayobj[0][3];
                    telef_rep  = arrayobj[0][10];
                    email_rep  = arrayobj[0][11];

                    $('#hd_id').val(id);
                    $('#txt_rsocial').val(rsocial);
                    $('#lb_rsocial').addClass('active');
                    $('#cb_sector').val(sector);
                    $('#cb_sector').change();
                    $('#txt_telefono_ruc').val(telefono);
                    $('#lb_telefono_ruc').addClass('active');
                    $('#txt_email_ruc').val(email);
                    $('#lb_email_ruc').addClass('active');                    
                    $('#txt_direccion_ruc').val(direccion);
                    $('#lbl_direccion_ruc').addClass('active');
                    $('#txt_representante').val(representante);
                    $('#lb_representante').addClass('active');
                    $('#txt_telef_rep').val(telef_rep);
                    $('#lb_telef_rep').addClass('active');
                    $('#txt_email_rep').val(email_rep);
                    $('#lb_email_rep').addClass('active');
                    $('#btn_guarda_ciudadano_ruc').text('Actualizar');
                    $('#div_msg_registro').hide();
                    $('#div_cbo_servicio').show();
                }else if (arrayobj.length == 0){
                    $('#div_msg_registro').text('Registre sus datos.');
                    $('#div_msg_registro').show();
                    $('#div_guarda_ciudadano').show();
                    $('#txt_rsocial').val(rsocial);
                    $('#txt_direccion_ruc').val(direccion);
                    $('#cb_sector').val(sector);
                    $('#cb_sector').change();
                    $('#txt_telefono_ruc').val(email);
                    $('#txt_email_ruc').val(telefono);
                    $('#txt_representante').val(representante);
                    $('#txt_telef_rep').val(telef_rep);
                    $('#txt_email_rep').val(email_rep);
                }
            },
            error: function(requestData, strError, strTipoError){
                $('#div_msg_registro').show();
//                $('#div_msg_registro').html("Error " + strTipoError +": " + strError);            
            }
        });
        }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }   
}
//FIN BUSCAR RUC
//
//INICIO GUARDAR CIUDADANO DNI
function sgd_mant_ciudadano_dni_guardar(){
    var id = $('#hd_id').val();
    var dni = $('#txt_dni').val();
    var nombres = $('#txt_nombres').val();
    var direccion = $('#txt_direccion').val();
    var email = $('#txt_email').val();
    var telefono = $('#txt_telefono').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_ciudadano_dni_guardar/",
            data:     "id="+id
                      +"&dni="+dni
                      +"&nombres="+nombres
                      +"&direccion="+direccion
                      +"&email="+email
                      +"&telefono="+telefono,
            beforeSend: function(data){
                $('#div_msg_registro').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];//datos guardados
                
                $('#hd_id').val(id);
                $('#div_msg_registro').show();
                $('#div_msg_registro').html(msj);
            },
            error: function(requestData, strError, strTipoError){								
                $('#div_msg_registro').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN GUARDAR CIUDADANO DNI
//
//
//INICIO BUSCAR MAPA
function sgd_mant_mapa_mostrar(){
    var cod_estacion = $('#cb_estacion').val();
    var cod_dpto = $('#cb_dpto').val();
    //console.log(cod_estacion);
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_mapa_mostrar/",
            data:     "cod_estacion="+cod_estacion
                     +"&cod_dpto="+cod_dpto,
            beforeSend: function(data){
                $('#div_map').html("Cargando...");
            },
            success: function(requestData){
                $('#div_map').html(requestData);
            },
            error: function(requestData, strError, strTipoError){								
                $('#div_map').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR MAPA
//
//INICIO GUARDAR CIUDADANO RUC
function sgd_mant_ciudadano_ruc_guardar(){
    var id = $('#hd_id').val();
    var ruc = $('#txt_ruc').val();
    var rsocial = $('#txt_rsocial').val();
    var direccion_ruc = $('#txt_direccion_ruc').val();
    var sector = $('#cb_sector').val();
    var msj_error = "";
    if ($('#cb_sector').val().length == 0){
        msj_error += " Seleccione sector.";
    }
    var email = $('#txt_email_ruc').val();
    var telefono = $('#txt_telefono_ruc').val();
    var representante = $('#txt_representante').val();
    var dni_rep = $('#txt_dni_rep').val();
    if(msj_error == ''){
        $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_ciudadano_ruc_guardar/",
            data:     "id="+id
                      +"&ruc="+ruc
                      +"&rsocial="+rsocial
                      +"&direccion_ruc="+direccion_ruc
                      +"&sector="+sector
                      +"&email="+email
                      +"&telefono="+telefono
                      +"&representante="+representante
                      +"&dni_rep="+dni_rep,
            beforeSend: function(data){
                $('#div_msg_registro').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];//datos guardados
                
                $('#hd_id').val(id);
                $('#div_msg_registro').show();
                $('#div_msg_registro').html(msj);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_msg_registro').html("Error " + strTipoError +": " + strError);
            }
        });
    }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }
}
//FIN GUARDAR CIUDADANO RUC

///////////////////////////////////////
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


function aniadirmarker(codestacion, codgoes, estacion, txt, sub_esta, lng, lat, pathicon, css, tipo, dpto, prov, dtrito) {

    if (pathicon == 'F') {
        pathicon = path + 'static/img/localizacion_verde.png';
    } else {
        pathicon = path + 'static/img/localizacion_rojo.png';
    }

    if (css == '' || css == undefined) {
        css = 'labelsgooglemaps';
    }

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
    //añadiendo la ventana del evento hover
    createTooltip(marker, txt);

    //copiar datos a campos
    google.maps.event.addListener(marker, 'click', function () {
        estacion_datos(codestacion, estacion, sub_esta, tipo, dpto, prov, dtrito);
    });

    markersArray.push(marker);
}

function removermarkers(markersArray) {
    for (var i = 0; i < markersArray.length; i++) {
        markersArray[i].setMap(null);
    }
    markersArray.length = 0;
}
//
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
//INICIO DETALLE DE LA ESTACIÓN
function estacion_datos(codestacion, estacion, sub_esta, tipo, dpto, prov, dtrito){
    $('#txt_cod_estacion').val(codestacion);
    $('#lb_cod_estacion').addClass('active');
    $('#txt_estacion').val(estacion);
    $('#lb_estacion').addClass('active');
    $('#txt_dpto').val(dpto);
    $('#lb_dpto').addClass('active');
    $('#txt_provincia').val(prov+' / '+dtrito);
    $('#lb_provincia').addClass('active');
    $('#txt_tipo').val(sub_esta);
    $('#lb_tipo').addClass('active');
    $('#txt_categoria').val(tipo);
    $('#lb_categoria').addClass('active'); 
    
    sgd_estacion_variables();
}
//FIN DETALLE DE LA ESTACIÓN
//
//INICIO VARIABLES POR ESTACIÓN
function sgd_estacion_variables(){
    var cod_estacion = $('#txt_cod_estacion').val();
    var des_estacion = $('#txt_estacion').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_variables_mostrar/",
            data:     "cod_estacion="+cod_estacion+
                      "&des_estacion="+des_estacion,
            beforeSend: function(data){
                $('#div_variables').html("Cargando...");
            },
            success: function(requestData){
                $('#div_variables').html(requestData);
            },
            error: function(requestData, strError, strTipoError){								
                $('#div_variables').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN VARIABLES POR ESTACIÓN
//
//INICIO ADICIONAR PEDIDO A SOLICITUD
function sgd_mant_add_solicitud(){
    var cod_estacion = $('#txt_cod_estacion').val();
    var id_var = $('#hd_id_var').val();
    $('#div_solicitud_detalle').show(); 
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_solicitud_crear/",
            data:     "cod_estacion="+cod_estacion+
                      "&id_var="+id_var,
            beforeSend: function(data){
                $('#div_solicitud_detalle').html("Cargando...");
            },
            success: function(requestData){
//                console.log(requestData);
                $('#div_solicitud_detalle').html(requestData);
                $('#div_solicitud_titulo').show();
                $('#div_solicitud_info').show();
                $('#div_motivo').show();
                $('#div_observacion').show();
                $('#div_enviar_sol_info').show();
                                
            },
            error: function(requestData, strError, strTipoError){								
                $('#div_solicitud_detalle').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN ADICIONAR PEDIDO A SOLICITUD
//
//INICIO LISTA DE VARIABLES
function lista_variables(){
    var id_var = "";
    var valor_var = "";
     var tempo = "";
    var temp_var = $('#hd_id_var').val();   
    
    $('.cb_variable:checked').each(function () {
        valor_var += $(this).val() + ",";  
        tempo = $(this).val();
    });

        if (temp_var == ''){
            var id_var_tmp = valor_var;
            $('#hd_id_var').val(id_var_tmp); 
        }else{
            var array_cad_var = temp_var.split(',');
            for (var i=0; i<array_cad_var.length-1; i++){
                var cad = array_cad_var[i];
                if(cad !== tempo){
                    id_var += tempo + ",";
                    var id_var_tmp = temp_var + valor_var;
                    $('#hd_id_var').val(id_var_tmp);
                }
            }    
        }           
}
//FIN LISTA DE DE VARIABLES
//
//INICIO MOSTRAR ESTACIONES POR DEPARTAMENTO
function sgd_mant_dpto_mostrar(){
    var cod_dpto = $('#cb_dpto').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_dpto_mostrar/",
            data:     "cod_dpto="+cod_dpto,
            beforeSend: function(data){
                $('#cb_estacion').html("<option>CARGANDO...</option>");
                $('#cb_estacion').selectpicker('refresh');
            },
            success: function(requestData){
                $('#cb_estacion').html(requestData);
                $('#cb_estacion').selectpicker('refresh');
                sgd_mant_mapa_mostrar();
            },
            error: function(requestData, strError, strTipoError){
                $('#cb_estacion').html("<option>Error " + strTipoError +": " + strError+"</option>");
                $('#cb_estacion').selectpicker('refresh');
            }
        });
}
//FIN MOSTRAR ESTACIONES POR DEPARTAMENTO
//
//INICIO MOSTRAR SOLICITUD
function sgd_mant_solicitud_mostrar(){
    var cod_procedimiento = $('#cb_servicio').val();
    $('#div_solicitud_info').show();
    $('#div_motivo').show();
    
    if (cod_procedimiento == '4'){
        $('#div_mapa').show();
        $('#div_solicitud_titulo').hide();
        $('#div_solicitud_otros').hide();
        $('#div_observacion').hide();
        $('#div_enviar_sol_info').hide();
        $('#div_enviar_sol_otros').hide();
    }else if(cod_procedimiento == '2'){
        $('#div_solicitud_titulo').show();
        $('#div_solicitud_otros').show();
        $('#div_solicitud_tupa').show()
        $('#div_solicitud_tupa_detalle').show();
        $('#div_enviar_sol_tupa').show();
        $('#div_observacion').show();
        $('#div_solicitud_info').hide();
        $('#div_enviar_sol_otros').hide();
    }else{
        $('#div_solicitud_otros').show();
        $('#div_mapa').hide();
        $('#div_observacion').show();
        $('#div_enviar_sol_info').hide();
        $('#div_enviar_sol_otros').show();
        $('#div_solicitud_titulo').show();
//        $('#div_motivo').show();
        $('#div_solicitud_detalle').show();
        $('#div_solicitud_otros').show();
    }
        
}
//FIN MOSTRAR SOLICITUD
//
//INICIO GUARDAR SOLICITUD OTROS
function sgd_mant_enviar_solicitud_otros(){//DETERMINAR CAMPOS OBLIGATORIOS
    var id_sol = $('#hd_id_sol').val();
    var motivo = $('#txt_motivo').val();
    var proc = $('#cb_servicio').val();
    var descr = $('#txt_descripcion').val();
    var obs = $('#txt_observacion').val();
    var cod_adm = $('#hd_id').val();
    
    var msj_error = "";
    if (motivo.length == 0){
        msj_error += " Ingrese el motivo de la solicitud.";
    }
    
    if(msj_error == ''){
        $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_solicitud_otros_guardar/",
            data:     "id_sol="+id_sol
                      +"&motivo="+motivo
                      +"&proc="+proc
                      +"&descr="+descr
                      +"&obs="+obs
                      +"&cod_adm="+cod_adm,
            beforeSend: function(data){
                $('#div_msg_registro_sol').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];//datos guardados
                
                $('#hd_id_sol').val(id);
                $('#div_msg_registro_sol').show();
                $('#div_msg_registro_sol').html(msj);
            },
            error: function(requestData, strError, strTipoError){								
                $('#div_msg_registro_sol').html("Error " + strTipoError +": " + strError);
            }
        });
    }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }
}
//FIN GUARDAR SOLICITUD OTROS
//
//INICIO GUARDAR SOLICITUD TUSNE INFORMACIÓN
function sgd_mant_enviar_solicitud_info(){
    var id_sol = $('#hd_id_sol').val();
    var motivo = $('#txt_motivo').val();
    var proc = $('#cb_servicio').val();
    var obs = $('#txt_observacion').val();
    var id_var = $('#hd_id_var').val();
    var cod_adm = $('#hd_id').val();
    
    var fec_ini = "";
        $('.fec_ini').each(function () {
            fec_ini += $(this).val() + ",";
        }); 
        fec_ini = fec_ini.substring(0, fec_ini.length - 1);
    var fec_fin = "";
        $('.fec_fin').each(function () {
            fec_fin += $(this).val() + ",";
        }); 
        fec_fin = fec_fin.substring(0, fec_fin.length - 1);
    var cad_var = "";
        $('.cad_variable').each(function () {
            cad_var += $(this).val() + ",";
        }); 
        cad_var = cad_var.substring(0, cad_var.length - 1);
    var cad_esc = "";
        $('.cb_escala').each(function () {
            cad_esc += $(this).val() + ",";
        }); 
        cad_esc = cad_esc.substring(0, cad_esc.length - 1);
    var cad_des_est = "";
        $('.cad_estacion').each(function () {
            cad_des_est += $(this).val() + ",";
        }); 
        cad_des_est = cad_des_est.substring(0, cad_des_est.length - 1);
    var cad_des_var = "";
        $('.cad_desvariable').each(function () {
            cad_des_var += $(this).val() + ",";
        }); 
        cad_des_var = cad_des_var.substring(0, cad_des_var.length - 1);
    
    var msj_error = "";
    if (motivo.length == 0){
        msj_error += " Ingrese el motivo de la solicitud.";
    }
    
    if(msj_error == ''){
        $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_solicitud_info_guardar/",
            data:     "id_sol="+id_sol
                      +"&motivo="+motivo
                      +"&proc="+proc
                      +"&obs="+obs
                      +"&cod_adm="+cod_adm
                      +"&cad_var="+cad_var
                      +"&fec_ini="+fec_ini
                      +"&fec_fin="+fec_fin
                      +"&cad_esc="+cad_esc
                      +"&cad_des_est="+cad_des_est
                      +"&cad_des_var="+cad_des_var,
            beforeSend: function(data){
                $('#div_msg_registro_sol').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];//datos guardados
                
                $('#hd_id_sol').val(id);
                $('#div_msg_registro_sol').show();
                $('#div_msg_registro_sol').html(msj);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_msg_registro_sol').html("Error " + strTipoError +": " + strError);
            }
        });
    }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }
}
//FIN GUARDAR SOLICITUD TUSNE INFORMACIÓN
//
//INICIO GUARDAR SOLICITUD TUPA
function sgd_mant_enviar_solicitud_tupa(){
    var id_sol = $('#hd_id_sol').val();
    var proc = $('#cb_servicio').val();
    var descr = $('#txt_descripcion').val();
    var obs = $('#txt_observacion').val();
    var cod_adm = $('#hd_id').val();
    
    var msj_error = "";
    if (descr.length == 0){
        msj_error += " Ingrese el motivo de la solicitud.";
    }
    
    if(msj_error == ''){
        $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_solicitud_tupa_guardar/",
            data:     "id_sol="+id_sol
                      +"&proc="+proc
                      +"&descr="+descr
                      +"&obs="+obs
                      +"&cod_adm="+cod_adm,
            beforeSend: function(data){
                $('#div_msg_registro_sol').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];//datos guardados
                
                $('#hd_id_sol').val(id);
                $('#div_msg_registro_sol').show();
                $('#div_msg_registro_sol').html(msj);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_msg_registro_sol').html("Error " + strTipoError +": " + strError);
            }
        });
    }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }    
}
//
