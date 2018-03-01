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
//INICIO BUSCAR PRIMER DOCUMENTO
function sgd_mant_busca_ciudadano_dni(){
    var n_doc = $('#txt_dni').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_busca_ciudadano_dni/",
            data:     "n_doc="+n_doc,
            beforeSend: function(data){
//                $('#div_asunto').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                console.log(requestData);
                console.log('******************'+requestData.length);
                var id  = arrayobj[0][0];
                var ndocumento  = arrayobj[0][1];
                var nombres  = arrayobj[0][2];               
                var direccion  = arrayobj[0][4];               
                var email  = arrayobj[0][5];               
                var telefono  = arrayobj[0][6];               
                if (requestData.length > 0){
                    $('#div_per_natural').show();
                    $('#txt_nombres').val(nombres);                
                    $('#lb_nombres').addClass('active');                
                    $('#txt_direccion').val(direccion);             
                    $('#lb_direccion').addClass('active');                 
                    $('#txt_email').val(email);             
                    $('#lb_email').addClass('active');                 
                    $('#txt_telefono').val(telefono);             
                    $('#lb_telefono').addClass('active');      
                    
                }if (id.length == 0){
                    $('#div_msg_registro').show();              
                }
            },
            error: function(requestData, strError, strTipoError){             
                $('#div_msg_registro').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR PRIMER DOCUMENTO
//
