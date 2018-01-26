//INICIO BUSCAR EXPEDIENTE ATENCIÓN AL CIUDADANO    
function sgd_mant_atencion_ciudadano_tbl(){
    var exp = $('#txt_expediente').val();
    var anio = $('#cb_anio').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "mant_atencion_ciudadano_tbl/",
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
            url:      path + "mant_atencion_ciudadano_doc/",
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
