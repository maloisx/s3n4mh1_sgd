//TRAMITE -----------------------------------------------------------------------------------------------------

function sgd_mant_tramite_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_tramite_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_tramite_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_tramite_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_tramite_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_tramite_popup(id){
//    console.log(id);
    var url = encodeURI(path + "sgd/mant_tramite_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 500 
    });
}

function sgd_mant_tramite_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var plazo = $('#txt_plazo').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_tramite_guardar/", 
            data:     "id="+id+"&des="+des+"&plazo="+plazo+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                
                arrayobj = jQuery.parseJSON(requestData);	
//                console.log(arrayobj);
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_tramite_tbl();
            },		
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//PROCEDIMIENTO -----------------------------------------------------------------------------------------------------
function sgd_mant_procedimiento_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_procedimiento_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_procedimiento').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_procedimiento').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_procedimiento').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_procedimiento_popup(id){
    var url = encodeURI(path + "sgd/mant_procedimiento_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 600
       ,"height" : 500 
    });
}

function sgd_mant_procedimiento_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var id_trami = $('#txt_id_trami').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_procedimiento_guardar/", 
            data:     "id="+id+"&des="+des+"&id_trami="+id_trami+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_procedimiento_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

function sgd_mant_procedimiento_cargar_cbo(){
    var id_tramite = $('#cb_tramite').val();
            
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_procedimiento_cargar_cbo/", 
            data:     "id_tramite="+id_tramite,	 	 
            beforeSend: function(data){ 	 	
                $('#cb_procedimiento').html("<option>CARGANDO...</option>");
                $('#cb_procedimiento').selectpicker('refresh');
            },
            success: function(requestData){
                $('#cb_procedimiento').html(requestData);
                $('#cb_procedimiento').selectpicker('refresh');
            },			
            error: function(requestData, strError, strTipoError){
                $('#cb_procedimiento').html("<option>Error " + strTipoError +": " + strError+"</option>");
                $('#cb_procedimiento').selectpicker('refresh');
            }
        });  
}

//ORIGEN -----------------------------------------------------------------------------------------------------
function sgd_mant_origen_tbl(){        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_origen_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_origen').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_origen').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_origen').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_origen_popup(id){
    var url = encodeURI(path + "sgd/mant_origen_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 400 
    });
}

function sgd_mant_origen_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_origen_guardar/", 
            data:     "id="+id+"&des="+des+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_origen_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//TEMA -----------------------------------------------------------------------------------------------------
function sgd_mant_tema_tbl(){        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_tema_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_tema').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_tema').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_tema').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_tema_popup(id){
    var url = encodeURI(path + "sgd/mant_tema_popup/?id="+id);    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 400 
    });
}

function sgd_mant_tema_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_tema_guardar/", 
            data:     "id="+id+"&des="+des+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_tema_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//ALCANCE -----------------------------------------------------------------------------------------------------
function sgd_mant_alcance_tbl(){        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_alcance_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_alcance').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_alcance').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_alcance').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_alcance_popup(id){
    var url = encodeURI(path + "sgd/mant_alcance_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 400 
    });
}

function sgd_mant_alcance_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_alcance_guardar/", 
            data:     "id="+id+"&des="+des+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_alcance_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//CONDICION -----------------------------------------------------------------------------------------------------
function sgd_mant_condicion_tbl(){        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_condicion_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_condicion').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_condicion').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_condicion').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_condicion_popup(id){
    var url = encodeURI(path + "sgd/mant_condicion_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 400 
    });
}

function sgd_mant_condicion_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_condicion_guardar/", 
            data:     "id="+id+"&des="+des+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_condicion_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//TIPO DE FLUJO -----------------------------------------------------------------------------------------------------
function sgd_mant_tipflujo_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_tipflujo_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_tipflujo_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_tipflujo_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_tipflujo_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_tipflujo_popup(id){
    var url = encodeURI(path + "sgd/mant_tipflujo_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 400 
    });
}

function sgd_mant_tipflujo_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_tipflujo_guardar/", 
            data:     "id="+id+"&des="+des+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_tipflujo_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//ACCION -----------------------------------------------------------------------------------------------------
function sgd_mant_accion_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_accion_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_accion_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_accion_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_accion_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_accion_popup(id){
    var url = encodeURI(path + "sgd/mant_accion_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 400 
    });
}

function sgd_mant_accion_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_accion_guardar/", 
            data:     "id="+id+"&des="+des+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_accion_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//TIPO DE ESTADO DEL FLUJO -----------------------------------------------------------------------------------------------------
function sgd_mant_tipestadoflujo_tbl(){        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_tipestadoflujo_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_tipestadoflujo_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_tipestadoflujo_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_tipestadoflujo_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_tipestadoflujo_popup(id){
    var url = encodeURI(path + "sgd/mant_tipestadoflujo_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 500 
    });
}

function sgd_mant_tipestadoflujo_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_tipestadoflujo_guardar/", 
            data:     "id="+id+"&des="+des+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_tipestadoflujo_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//PRIORIDAD -----------------------------------------------------------------------------------------------------
function sgd_mant_prioridad_tbl(){        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_prioridad_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_prioridad_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_prioridad_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_prioridad_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_prioridad_popup(id){
    var url = encodeURI(path + "sgd/mant_prioridad_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 500 
    });
}

function sgd_mant_prioridad_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_des').val();
    var plazo = $('#txt_plazo').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_prioridad_guardar/", 
            data:     "id="+id+"&des="+des+"&plazo="+plazo+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_prioridad_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

function sgd_mant_prioridad_cargar_txt(){
    var id_priori = $('#cb_priori').val();

    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_prioridad_cargar_txt/",
            data:     "id_priori="+id_priori,
            beforeSend: function(data){
                $('#txt_plazo').val("CARGANDO...");
            },
            success: function(requestData){
                $('#txt_plazo').val(parseInt(requestData));
            },
            error: function(requestData, strError, strTipoError){
                $('#txt_plazo').val("Error " + strTipoError +": " + strError+"");
            }
        });
}

//ESTANTE -----------------------------------------------------------------------------------------------------
function sgd_mant_estante_tbl(cod_almacen){    
    var select = $('#cb_almacen');
    select.val($('option:first', select).val());
    
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_estante_tbl/", 
        data:     "cod_almacen="+cod_almacen,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_estante_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_estante_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_estante_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_estante_popup(id){         
    var almacen = $('#hd_alma').val();
    var url = encodeURI(path + "sgd/mant_estante_popup/?id="+id+"&almacen="+almacen);
    
    $.colorbox({
        "href" : url
       ,"width" : 650
       ,"height" : 770 
    });
}

function sgd_mant_estante_guardar(){
    var id = $('#hd_id').val();
    var cod = $('#cb_estt').val();
    var des = $('#txt_des').val();
    var material = $('#txt_mat').val();
    var dimension = $('#txt_dim').val();
    var almacen = $('#hd_almacen').val();
    console.log('-------------------------------> '+almacen);
    var estado = $('#cb_estado').val();
    var cuerpo = $('#cb_cuerpo').val();
    var balda = $('#cb_balda').val();
    var lado = $('#cb_lado').val();
    
    var msj_error = "";
    if ($('#txt_des').val().length == 0){
        msj_error += " Ingrese la descripción.";
    }if ($('#txt_mat').val().length == 0){
        msj_error += " Ingrese el material.";
    }
    
    if(msj_error == ""){ 
        $.ajax({
                dataType: "html",
                type:     "GET",
                url:      path + "sgd/mant_estante_guardar/", 
                data:     "id="+id+"&cod="+cod+"&des="+des+"&material="+material+"&dimension="+dimension+"&almacen="+almacen+"&cb_estado="+estado+"&cb_cuerpo="+cuerpo+"&cb_balda="+balda+"&cb_lado="+lado,	 	 
                beforeSend: function(data){ 	 	
                    $('#div_mensaje_ajax').html("Cargando...");
                },
                success: function(requestData){
                    arrayobj = jQuery.parseJSON(requestData);	
                    var id  = arrayobj[0][0];
                    var msj = arrayobj[0][1];
                    $('#hd_id').val(id);
                    $('#div_mensaje_ajax').html('');                    
                    sgd_mant_estante_tbl(almacen);
                    $.alert('<h6>' + msj + '</h6>');
                },			
                error: function(requestData, strError, strTipoError){											
                    $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
                }
            });  
    }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }    
}

//CUERPO -----------------------------------------------------------------------------------------------------
function sgd_mant_cuerpo_tbl(){        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_cuerpo_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_cuerpo_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_cuerpo_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_cuerpo_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_cuerpo_popup(id){
    var url = encodeURI(path + "sgd/mant_cuerpo_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 530 
    });
}

function sgd_mant_cuerpo_guardar(){
    var id = $('#hd_id').val();
    var cod = $('#txt_cod').val();
    var des = $('#txt_des').val();
    var id_estt = $('#cb_estt').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_cuerpo_guardar/", 
            data:     "id="+id+"&cod="+cod+"&des="+des+"&id_estt="+id_estt+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_cuerpo_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//BALDA -----------------------------------------------------------------------------------------------------
function sgd_mant_balda_tbl(){        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_balda_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_balda_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_balda_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_balda_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_balda_popup(id){
    var url = encodeURI(path + "sgd/mant_balda_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 530
    });
}

function sgd_mant_balda_guardar(){
    var id = $('#hd_id').val();
    var cod = $('#txt_cod').val();
    var des = $('#txt_des').val();
    var id_cpo = $('#cb_cpo').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_balda_guardar/", 
            data:     "id="+id+"&cod="+cod+"&des="+des+"&id_cpo="+id_cpo+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_balda_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//TIPO DE UNIDAD DE CONSERVACIÓN -----------------------------------------------------------------------------------------------------
function sgd_mant_tipunidcons_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_tipunidcons_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_tipunidcons_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_tipunidcons_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_tipunidcons_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_tipunidcons_popup(id){
    var url = encodeURI(path + "sgd/mant_tipunidcons_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 500 
    });
}

function sgd_mant_tipunidcons_guardar(){
    var id = $('#hd_id').val();
    var cod = $('#txt_cod').val();
    var des = $('#txt_des').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_tipunidcons_guardar/", 
            data:     "id="+id+"&cod="+cod+"&des="+des+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_tipunidcons_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//UNIDAD DE CONSERVACIÓN -----------------------------------------------------------------------------------------------------
function sgd_mant_unidcons_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_unidcons_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_unidcons_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_unidcons_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_unidcons_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_unidcons_popup(id){
    var url = encodeURI(path + "sgd/mant_unidcons_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 900
       ,"height" : 770 
    });
}

//INICIO UNIDAD DE CONSERVACIÓN GUARDAR
function sgd_mant_unidcons_guardar(){
    var msj_error = "";
    if ($('#txt_cod').val().length == 0){
        msj_error += " Código.";
    }if ($('#txt_des').val().length == 0){
        msj_error += " Descripción.";
    }if ($('#dt_fec_ini').val() == ""){
        msj_error += " Fecha extrema inicial.";
    }if ($('#dt_fec_fin').val() == ""){
        msj_error += " Fecha extrema final.";
    }if ($('#cb_estante').val() == ""){
        msj_error += " Estante.";
    }if ($('#cb_cuerpo').val() == ""){
        msj_error += " Cuerpo.";
    }if ($('#cb_balda').val() == ""){
        msj_error += " Balda.";
    }if ($('#hd_clasifdoc').val().length == 0){
        msj_error += " Clasificación documental.";
    }
    
    if(msj_error == ""){           
    var id = $('#hd_id').val();
    var id_tip_unidcons = $('#cb_tip_unidcons').val();
    var cod = $('#txt_cod').val();
    var des = $('#txt_des').val();
    var obs = $('#txt_obs').val();
    var fec_ini = $('#dt_fec_ini').val();
    var fec_fin = $('#dt_fec_fin').val();
    var estado = $('#cb_estado').val();
    var cd = $('#cb_cd_fin').val().toString();
    var iduo = $('#hd_iduo').val();
    var idbalda = $('#hd_balda').val();          
        
    $.ajax({ 
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_unidcons_guardar/", 
            data:     "id="+id+"&id_tip_unidcons="+id_tip_unidcons+"&cod="+cod+"&des="+des+"&obs="+obs+"&fec_extini="+fec_ini+"&fec_extfin="+fec_fin+"&cb_estado="+estado+"&cd="+cd+"&iduo="+iduo+"&idbalda="+idbalda,
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html('');                
                sgd_mant_unidcons_tbl();                
                $.alert('<h6>' + msj + '</h6>');
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
    }else{
        $.alert('<h6>Ingrese: ' + msj_error + '</h6>');            
    }
}
//FIN UNIDAD DE CONSERVACIÓN GUARDAR
//
//INICIO SERIE DOCUMENTAL TABLA
function sgd_mant_seriedoc_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_seriedoc_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_seriedoc_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_seriedoc_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_seriedoc_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_seriedoc_popup(id){
    var url = encodeURI(path + "sgd/mant_seriedoc_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 800
       ,"height" : 650 
    });
}

function sgd_mant_seriedoc_guardar(){
    var id = $('#hd_id').val();
    var cod = $('#txt_cod').val();
    var des = $('#txt_des').val();
    var per = $('#txt_per').val();
    var pag = $('#txt_pag').val();
    var pap = $('#txt_pap').val();
    var poa = $('#txt_poa').val();
    var estado = $('#cb_estado').val();
    var uo = $('#cb_uo_fin').val().toString();
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_seriedoc_guardar/", 
            data:     "id="+id+"&cod="+cod+"&des="+des+"&per="+per+"&pag="+pag+"&pap="+pap+"&poa="+poa+"&cb_estado="+estado+"&uo="+uo,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_seriedoc_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//CLASIFICACIÓN DOCUMENTAL -----------------------------------------------------------------------------------------------------
function sgd_mant_clasifdoc_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_clasifdoc_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_clasifdoc_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_clasifdoc_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_clasifdoc_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

function sgd_mant_clasifdoc_popup(id){
    var url = encodeURI(path + "sgd/mant_clasifdoc_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 500
       ,"height" : 530 
    });
}

function sgd_mant_clasifdoc_guardar(){
    var id = $('#hd_id').val();
    var cod = $('#txt_cod').val();
    var des = $('#txt_des').val();
    var seriedoc = $('#txt_id_sd').val();
    var estado = $('#cb_estado').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_clasifdoc_guardar/", 
            data:     "id="+id+"&cod="+cod+"&des="+des+"&id_sd="+seriedoc+"&cb_estado="+estado,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_clasifdoc_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}

//UNIDAD ORGÁNICA -----------------------------------------------------------------------------------------------------
function sgd_mant_unidorg_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_unidorg_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_unidorg_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_unidorg_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_unidorg_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}

//BUSQUEDA BANDEJA -----------------------------------------------------------------------------------------------------
function sgd_busqueda_bandeja_tbl(){
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/busqueda_bandeja_tbl/",
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_busqueda_bandeja_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_busqueda_bandeja_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_busqueda_bandeja_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN BANDEJA -----------------------------------------------------------------------------------------------------

//INICIO función para capturar el perfil de la bandeja elegida
 function func_perfil(perfil,des_pfl){
    $('#hd_pfl').val(perfil);
    $('#hd_des_pfl').val(des_pfl);
}
//FIN función para capturar el perfil de la bandeja elegida


//EXPEDIENTE -----------------------------------------------------------------------------------------------------
function sgd_mant_expediente_popup(id, id_doc, id_flujo, doc_nuevo, doc_modif, uo_user){    
    var perfil = $('#hd_pfl').val().toString();
    
    var url = encodeURI(path + "sgd/mant_expediente_popup_copia/?id="+id+"&doc="+id_doc+"&flujo="+id_flujo+"&perfil="+perfil+"&doc_nuevo="+doc_nuevo+"&doc_modif="+doc_modif+"&uo_user="+uo_user);
  
    $.colorbox({
        "href" : url
       ,"width" : 1200
       ,"height" : 1000 
    });
}
//FIN EXPEDIENTE -----------------------------------------------------------------------------------------------------
//
//INICIO EXPEDIENTE AÑADIR DOCUMENTO POPUP
function sgd_mant_expediente_añadirdoc_popup(id_exp, codUser, id_pers, id_uo, nom_pers, id_vt){    
    var perfil = $('#hd_pfl').val().toString();
    var des_pfl = $('#hd_des_pfl').val();
    var url = encodeURI(path + "sgd/mant_expediente_añadirdoc_popup/?id_exp="+id_exp+"&codUser="+codUser+"&id_pers="+id_pers+"&id_uo="+id_uo+"&nom_pers="+nom_pers+"&perfil="+perfil+"&id_vt="+id_vt+"&des_pfl="+des_pfl);
  
    $.colorbox({
        "href" : url
       ,"width" : 1200
       ,"height" : 1000 
    });
}
//FIN EXPEDIENTE AÑADIR DOCUMENTO POPUP
//
//INICIO EXPEDIENTE CONSULTAR POPUP
function sgd_mant_expediente_consulta_popup(id_exp, id_doc, id_flujo, codUser, id_pers, id_uo, nom_pers, id_vt){    
    var perfil = $('#hd_pfl').val();
    var des_pfl = $('#hd_des_pfl').val();
    var user_agrupa = $('#hd_user_agrupa').val();
    
    var url = encodeURI(path + "sgd/mant_expediente_consulta_popup/?id_exp="+id_exp+"&doc="+id_doc+"&flujo="+id_flujo+"&codUser="+codUser+"&id_pers="+id_pers+"&id_uo="+id_uo+"&nom_pers="+nom_pers+"&perfil="+perfil+"&id_vt="+id_vt+"&user_agrupa="+user_agrupa+"&des_pfl="+des_pfl);
  
    $.colorbox({
        "href" : url
       ,"width" : 1200
       ,"height" : 1000 
    });
}
//FIN EXPEDIENTE AÑADIR DOCUMENTO POPUP
//
//INICIO NUEVO EXPEDIENTE POPUP
function sgd_mant_expediente_nuevo_popup(){    
    var perfil = $('#hd_pfl').val().toString();
    var des_pfl = $('#hd_des_pfl').val().toString();
    var codUser = $('#hd_codUser').val().toString();
    var nom_pers = $('#hd_nom_pers').val().toString();
    var id_pers = $('#hd_id_pers').val().toString();
    var id_uo = $('#hd_id_uo').val().toString();
    var id_vt = $('#hd_id_vt').val().toString();
         
    var url = encodeURI(path + "sgd/mant_expediente_nuevo_popup/?codUser="+codUser+"&id_pers="+id_pers+"&id_uo="+id_uo+"&nom_pers="+nom_pers+"&perfil="+perfil+"&id_vt="+id_vt+"&des_pfl="+des_pfl);
  
    $.colorbox({
        "href" : url
       ,"width" : 1200
       ,"height" : 1000 
    });
}
//FIN NUEVO EXPEDIENTE DOCUMENTO POPUP
//
//INICIO EXPEDIENTE MODIFICAR DOCUMENTO POPUP
function sgd_mant_expediente_modificadoc_popup(id_exp, id_doc, id_flujo, codUser, user_agrupa, id_uo){    
    var perfil = $('#hd_pfl').val();
    var des_pfl = $('#hd_des_pfl').val().toString();
    
    var url = encodeURI(path + "sgd/mant_expediente_modifica_popup/?id_exp="+id_exp+"&doc="+id_doc+"&flujo="+id_flujo+"&codUser="+codUser+"&user_agrupa="+user_agrupa+"&id_uo="+id_uo+"&perfil="+perfil+"&des_pfl="+des_pfl);
  
    $.colorbox({
        "href" : url
       ,"width" : 1200
       ,"height" : 1000 
    });
}
////FIN EXPEDIENTE MODIFICAR DOCUMENTO POPUP
//
//INICIO GUARDAR EXPEDIENTE POPUP
function sgd_mant_expediente_guardar(){
    var id = $('#hd_id').val();
    var per = $('#txt_per').val();
    var orig = $('#cb_origen').val();
    var tema = $('#cb_tema').val();
    var proc = $('#cb_procedimiento').val();
    var alcan = $('#cb_alcance').val();
    var userreg = $('#hd_userreg').val();
    var fecreg = $('#txt_fecreg').val();    
    
    var cond = $('#cb_condicion').val();
    var cutext = $('#txt_cutext').val();
    
    var priori = $('#cb_priori').val();
    var plazo = $('#txt_plazo').val();
    
    var id_doc = $('#hd_iddoc').val(); //id para consulta de documentos adjuntos
    
    var clsdoc = $('#cb_clsfdoc').val();
    var numd = $('#txt_nrodoc').val();
    var fecd = $('#txt_fec_doc').val();
    var fecreg_doc = $('#hd_fecreg_doc').val();
    var asu_doc = $('#txt_asunto').val();
    asu_doc = asu_doc.replace("–","");
    asu_doc = asu_doc.replace("\“","'");
    asu_doc = asu_doc.replace("\”","'");
    
    var folio = $('#txt_folio').val();    
    var uoreg_doc = $('#hd_uorem').val();
    var prflreg_doc = $('#hd_perfil').val();
    var userreg_doc = $('#hd_userreg').val();
    var id_vt = $('#hd_id_vtn').val();
    var obs = $('#txt_obs').val();
    obs = obs.replace("–","");
    obs = obs.replace("\“","'");
    obs = obs.replace("\”","'");
    
    var idrem = $('#hd_idrem').val();    
    var iddes = $('#hd_iddes').val();
    var input = document.querySelector('input[type="file"]');     
    var obsmodifica = $('#txt_obsmodifica').val();    
    
    if(obsmodifica == undefined){
        obsmodifica = '';
    }else{
        obsmodifica = obsmodifica.replace("–","");
        obsmodifica =obsmodifica.replace("\“","'");
        obsmodifica =obsmodifica.replace("\”","'");
    }  
    
    var msj_error = "";
    if ($('#txt_plazo').val().length == 0){
        msj_error += " Plazo en días.";
    }if ($('#txt_nrodoc').val().length == 0){
        msj_error += " N° de documento.";
    }if ($('#txt_asunto').val().length == 0){
        msj_error += " Asunto.";
    }if ($('#txt_folio').val().length == 0){
        msj_error += " N° de folios.";
    }if ($('#cb_procedimiento').val() == ''){
        msj_error += " Procedimiento.";
    }if ($('#cb_clsfdoc').val() == ''){
        msj_error += " Tipo de documento.";
    }if ($('#hd_iddes').val() == ""){
        msj_error += " Destinatario.";
    }if ($('#cb_priori').val() == ''){
        msj_error += " Prioridad.";
    }if ($('#cb_alcance').val() == ''){
        msj_error += " Alcance.";
    }if ($('#cb_tema').val() == ''){
        msj_error += " Tema.";
    }if ($('#cb_remite').val() == ''){
        msj_error += " Remitente.";
    }
//    if (modifica == '1'){
        if ($('#txt_obsmodifica').val() !== undefined){
            if ($('#txt_obsmodifica').val() == ''){            
                msj_error += " Razón de la modificación.";
            }     
        }
//    }
    
//    console.log('modificar doc txt--------------------------------------------->'+$('#txt_obsmodifica').val())
//    console.log('modificar doc txt--------------------------------------------->'+obsmodifica)
    
//    if(input.files.length == 0){
//        msj_error += "No hay documentos adjuntos.";
//    }        
    if(msj_error == ""){   
        $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_expediente_guardar/", 
            data:     "id="+id+"&per="+per+"&orig="+orig+"&tema="+tema+"&proc="+proc+"&alcan="+alcan+"&userreg="+userreg+"&fecreg="+fecreg+"&cond="+cond+"&cutext="+cutext+"&priori="+priori+"&plazo="+plazo+"&id_doc="+id_doc+"&clsdoc="+clsdoc+"&numd="+numd+"&fecd="+fecd+"&fecreg_doc="+fecreg_doc+"&asu_doc="+escape(asu_doc)+"&folio="+folio+"&uoreg_doc="+uoreg_doc+"&prflreg_doc="+prflreg_doc+"&userreg_doc="+userreg_doc+"&obs="+escape(obs)+"&idrem="+idrem+"&iddes="+iddes+"&id_vt="+id_vt+"&obsmodifica="+escape(obsmodifica),
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");                
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var id  = arrayobj[0][0];
                var id_doc = arrayobj[0][1];
                var msj = arrayobj[0][2];
                var n_exp = arrayobj[0][3];
                
                $('#hd_id').val(id); //id del expediente
                $('#hd_iddoc').val(id_doc); //id del documento
                $('#txt_cut').val(n_exp);//id del expediente
                
                /*inicio subir archivos */
                var fdata = new FormData();
                var file;
                fdata.append("anio",per);
                fdata.append("nrocut",id);
                fdata.append("id_doc",id_doc);
                for (var i = 0 ; i < input.files.length ; i++) {
                    file = input.files[i];
//                        console.log(file); 
//                        fdata.append(file.name, file);
                    fdata.append(per+"|"+id+"|"+id_doc+"|"+i, file);
                }    
                var xhr = new XMLHttpRequest();
                xhr.open("POST",path + "sgd/uploadfile/", true);
//                    xhr.open("POST",path + "sgd/2017/", true);
                xhr.addEventListener("load", function (e) {
//                        console.log(xhr.responseText);      
                    sgd_mant_adjuntos_cargar(id_doc);//Carga lista de adjuntos                      
                });
                xhr.send(fdata);
                /*fin subir archivos */ 
                sgd_mant_lista_docs_cargar(); 
                sgd_mant_lista_deriva_cargar();
                if (input.files.length > 0){
                    $('#div_mant_adjunto_tbl').show();
                    $('#file-sgd').fileinput('clear');
                }
                $('#div_mensaje_ajax').html(''); 
                //prflreg_doc ----> redirecciona a la bandeja donde se trabaja
                sgd_bandeja_perfil_tbl(prflreg_doc);                
                $.alert('<h6>' + msj + '</h6>');
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }                        
        });         
    }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }   
}   
//INICIO guardar expediente popup
//  
//INICIO Derivar Expediente completo
function sgd_mant_expediente_deriva_cut_popup(id, cut, per, perfil, id_pers, id_uo, id_flujo, nivel){
    var des_pfl = $('#hd_des_pfl').val().toString();
    var url = encodeURI(path + "sgd/mant_expediente_deriva_cut_popup/?id="+id+"&cut="+cut+"&per="+per+"&perfil="+perfil+"&id_pers="+id_pers+"&id_uo="+id_uo+"&id_flujo="+id_flujo+"&nivel="+nivel+"&des_pfl="+des_pfl);
   
    $.colorbox({
        "href" : url
       ,"width" : 900
       ,"height" : 800 
    });  
}   
//FIN Derivar Expediente completo
//  
//INICIO Derivar Expediente eligiendo documentos
function sgd_mant_expediente_deriva_popup(id, cut, per, perfil, id_pers, id_uo, id_flujo, nivel){    
    var url = encodeURI(path + "sgd/mant_expediente_deriva_popup/?id="+id+"&cut="+cut+"&per="+per+"&perfil="+perfil+"&id_pers="+id_pers+"&id_uo="+id_uo+"&id_flujo="+id_flujo+"&nivel="+nivel);
    
    $.colorbox({
        "href" : url
       ,"width" : 900
       ,"height" : 750 
    });    
}   
//FIN Derivar Expediente eligiendo documentos
//  
//INICIO LISTA DE DOCUMENTOS A DERIVAR
function lista_doc_deriva(){
    var id_doc = "";
        $('.cb_doc_exp:checked').each(function () {        
            id_doc += $(this).val() + ",";  
        }); 
        id_doc = id_doc.substring(0, id_doc.length - 1); 
        $('#hd_id_doc').val(id_doc);        
}       
//FIN LISTA DE DOCUMENTOS A DERIVAR
//
//INICIO DERIVACIÓN CON ACCION POPUP 
function sgd_mant_expediente_deriva_accion_popup(){        
    var id_doc = $('#hd_id_doc').val().toString();
    var cd_fin = $('#cb_cd_fin').val().toString();
    var cd_fin_pers = $('#cb_cd_fin').text().toString();
    var observa = $('#txt_obs').val(); 
    observa = observa.replace("–","");
    observa =observa.replace("\“","'");
    observa =observa.replace("\”","'");
//    console.log('observaciones d:  -------------------------->'+observa)
    
    var url = encodeURI(path + "sgd/mant_expediente_deriva_accion_popup/?id_doc="+id_doc+"&cd_fin="+cd_fin+"&observa="+observa);
    
    $.colorbox2({
        "href" : url
       ,"width" : 900
       ,"height" : 700 
    });
}   
//FIN DERIVACIÓN CON ACCION POPUP
//
//Guardar derivación
function sgd_mant_expediente_deriva(){    
//    SELECCIONAR CHECK de documentos
    if($('#hd_id_doc').val().toString() != ""){
       var id_doc = $('#hd_id_doc').val().toString(); 
    }else{  
        var id_doc = "";
        $('.cb_doc_exp:checked').each(function() {        
            id_doc += $(this).val() + ",";  
        }); 
        id_doc = id_doc.substring(0, id_doc.length - 1);        
    }
//selección del check de copia 
    var copia = "";
    if($('#chk_copia_exp').is(':checked') ) {
      copia = $('#chk_copia_exp').val().toString();
    }      
//checkbox fisico    
    var cb_fisico = "";//envio con físico    
    $('.cb_fisico:checked').each(function () {
        cb_fisico +=  $(this).val() + ",";  
    });    
//Inicio Acciones para derivación
    var rpta_r = "";//respuesta    
    var rpta_c = "";//conocimiento    
    var rpta_a = "";//conocimiento    
    $('.rd_accion_r:checked').each(function () {
        rpta_r +=  $(this).attr("cod") + ",";  
    });
    $('.rd_accion_c:checked').each(function () {
        rpta_c +=  $(this).attr("cod") + ",";  
    });       
    $('.rd_accion_a:checked').each(function () {
        rpta_a +=  $(this).attr("cod") + ",";  
    });       
//Fin Acciones para derivación    

    var id = $('#hd_id_flujo').val();//id del flujo
    var codUser = $('#hd_codUser').val();
    var perfil = $('#hd_perfil').val();
    var id_uo = $('#hd_id_uo').val();
    var cd_fin = $('#cb_cd_fin').val().toString(); 
    var id_flujo_ant = $('#hd_id_flujo_ant').val();
    var nivel = $('#hd_nivel').val();     
    var observa = $('#hd_observa').val();
//    console.log('observación ------------------------------------------------>'+observa);
    observa = observa.replace("–","");
    observa =observa.replace("\“","'");
    observa =observa.replace("\”","'");
//    console.log('------------------------------------------------>'+observa);
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_deriva_guardar/", 
            data:     "id="+id+"&codUser="+codUser+"&perfil="+perfil+"&id_uo="+id_uo+"&cd_fin="+cd_fin+"&id_doc="+id_doc+"&id_flujo_ant="+id_flujo_ant+"&copia="+copia+"&nivel="+nivel+"&cb_fisico="+cb_fisico+"&rpta_r="+rpta_r+"&rpta_c="+rpta_c+"&rpta_a="+rpta_a+"&observa="+observa,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax1').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var msj = arrayobj[0][0];
                $('#div_mensaje_ajax1').html(msj);
                $('#div_mensaje_ajax1').addClass('alert alert-success');                
                $('#deriva_exp').attr('disabled', true);
                $('#volver').attr('disabled', true);
                sgd_bandeja_perfil_tbl(perfil);
            },
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax1').html("Error " + strTipoError +": " + strError);
            }
        });
}

//DETALLE DE DOCUMENTOS
function sgd_mant_doc_detalle(id, codUser){
   $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_doc_detalle/", 
            data:     "id_doc="+id,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);                
                var clasif_doc  = arrayobj[0][1];
                var n_doc  = arrayobj[0][2];
                var fec_doc  = arrayobj[0][3];
                var folio_doc  = arrayobj[0][4];
                var asu_doc  = arrayobj[0][5];
                var obs_doc  = arrayobj[0][6];    
                var uo_remite = arrayobj[0][8];
                var pers_remite = arrayobj[0][9];
                var pers_destino = arrayobj[0][10];
                var uo_destino = arrayobj[0][11];
                var estado_doc = arrayobj[0][12];
                var c_destino = arrayobj[0][13];
                var obsmodifica = arrayobj[0][14];
                var user_crea = arrayobj[0][15];
                var msj = arrayobj[0][0];

                $('#hd_iddoc').val(id);
                $('#cb_clsfdoc').val(clasif_doc);
                $("#cb_clsfdoc").change();
                $('#txt_nrodoc').val(n_doc);
                $('#txt_fec_doc').val(fec_doc);
                $('#txt_nrodoc').val(n_doc);
                $('#txt_folio').val(folio_doc);
                $('#txt_asunto').val(asu_doc);
                $('#txt_obs').val(n_doc+' '+obs_doc);
                
                $('#txt_remite').val(pers_remite);  
                $('#lbl_txt_remite').attr('class','active');
                $('#cb_remite').val(uo_remite);                
                $("#cb_remite").change();
                
                $('#txt_destino').val(pers_destino);
                $('#lbl_txt_destino').attr('class','active');
                $('#cb_destino').val(uo_destino);                
//                $("#cb_destino").change();
                $("#hd_iddes").val(c_destino); 
                $("#txt_obsmodifica").val(obsmodifica); 
                
//              DESHABILITA TABS
                $('#lista_doc').attr('class','tab-pane');
                $('#div_lista_doc').attr('class','tab-pane');
                
                $('#expediente').attr('class','tab-pane active');
                $('#doc').attr('class','tab-pane active');                
                $('#doc').attr('aria-expanded','true');        
               
                $('#div_mensaje_ajax').hide();                
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_adjuntos_cargar(id);
                
                if (codUser == user_crea){                    
                    $('#cb_clsfdoc').prop('disabled', false).selectpicker('refresh');
                    $('#cb_destino').prop('disabled', false).selectpicker('refresh');
                    $('#btn_guardar').prop('disabled', false).selectpicker('refresh');
                    $('#txt_nrodoc').prop('readonly', false);
                    $('#txt_folio').prop('readonly', false);
                    $('#txt_fec_doc').prop('disabled', false);
                    $('#txt_asunto').prop('readonly', false);
                    $('#txt_obs').prop('readonly', false);
                    $('#txt_destino').prop('readonly', false);                   
                    $('#txt_obsmodifica').prop('readonly', false);                   
                    $('#file-sgd').attr('disabled', false);                        
                }else{
                    $('#cb_clsfdoc').attr('disabled', true).selectpicker('refresh');
                    $('#cb_destino').attr('disabled', true).selectpicker('refresh');
                    $('#btn_guardar').attr('disabled', true);
                    $('#txt_nrodoc').prop('readonly', true);
                    $('#txt_folio').prop('readonly', true);
                    $('#txt_fec_doc').prop('disabled', true);
                    $('#txt_asunto').prop('readonly', true);
                    $('#txt_obs').prop('readonly', true);
                    $('#txt_destino').prop('readonly', true);                 
                }
                $('#div_mant_adjunto_tbl').show();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN DETALLE DE DOCUMENTOS
//
//INICIO CARGAR TEXT DESDE COMBO REMITE / DESTINO - EXPEDIENTE
function sgd_mant_destino_cargar_txt(id_dest,tipo){
    var nom_dest = "";
    var perfil = "";
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_destino_cargar_txt/", 
            data:     "id_dest="+id_dest,	 	 
            beforeSend: function(data){ 	 	
//                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);  
                perfil = arrayobj[0][4];                
                if (perfil == "3" || perfil == "externo"){
                    nom_dest  = arrayobj[0][2];
                }else{
                    nom_dest  = arrayobj[0][1];
                }  
                if (tipo == "d"){
                    $('#txt_destino').val(nom_dest);
                }else{
                    $('#txt_remite').val(nom_dest);
                }
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN CARGAR TEXT DESDE COMBO DESTINO - EXPEDIENTE
//
//ELIMINAR DOCUMENTOS ADJUNTOS
function sgd_mant_adjuntos_elim(id, nom){
    var id_doc = id;
    var nom_doc = nom;
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_docadj_elim/", 
            data:     "id_doc="+id_doc+"&nom_doc="+nom_doc,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var msj = arrayobj[0][1];   
                $('#div_mensaje_ajax').html(msj);                
                sgd_mant_adjuntos_cargar(id);
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN ELIMINAR DOCUMENTOS ADJUNTOS
//
//LISTA ADJUNTOS
function sgd_mant_adjuntos_cargar(id_doc){
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_adjunto_cargar/",
            data:     "id_doc="+id_doc,
            beforeSend: function(data){
                $('#div_mant_adjunto_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_mant_adjunto_tbl').html(requestData);
                $('#div_mensaje_ajax').removeClass('alert alert-danger');
                $('#div_mensaje_ajax').removeClass('alert alert-success'); 
            },
            error: function(requestData, strError, strTipoError){
                $('#div_mant_adjunto_tbl').html("Error " + strTipoError +": " + strError);
                $('#div_mant_adjunto_tbl_1').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN LISTA ADJUNTOS DE UN EXPEDIENTE
//
//ELIMINAR DOCUMENTO 
function sgd_mant_documento_elim(id){
    var id_doc = id;    
    $.confirm({
            title: '',
            content: '¿DESEA ELIMINAR EL DOCUMENTO?',
            theme: 'supervan',
            buttons: {             
                buttonEliminar: {
                    text: 'ELIMINAR',
                    action: function () {
                        $.ajax({
                            dataType: "html",
                            type:     "GET",
                            url:      path + "sgd/mant_documento_elim/", 
                            data:     "id_doc="+id_doc,	 	 
                            beforeSend: function(data){ 	 	
                                $('#div_mensaje_ajax').html("Cargando...");
                            },
                            success: function(requestData){
                                arrayobj = jQuery.parseJSON(requestData);	
                //                var id  = arrayobj[0][0];
                                var msj = arrayobj[0][0];                
                //                $('#hd_id').val(id);
                //                $('#div_mensaje_ajax').html(msj);

                //                sgd_mant_adjuntos_cargar(id);
                                $.alert('<h6>' + msj + '</h6>');

                                sgd_mant_lista_docs_cargar();
                            },			
                            error: function(requestData, strError, strTipoError){											
                                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
                            }
                        }); 
                    }
                },
                buttonCancelar: {
                    text: 'CANCELAR'
                }
            }
        });    
}
//FIN ELIMINAR DOCUMENTO DE UN EXPEDIENTE
//
//INICIO LISTA DE DOCUMENTOS ADJUNTOS EN UN EXPEDIENTE
function sgd_mant_lista_docs_cargar(){
    var idexp = $('#hd_id').val();//id del expediente
    var codUser = $('#hd_userreg').val();
    var perfil = $('#hd_perfil').val();    
    var operacion = $('#hd_operacion').val();    
    
     $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_lista_docs_cargar/",
            data:     "idexp="+idexp+"&codUser="+codUser+"&perfil="+perfil+"&operacion="+operacion,
            beforeSend: function(data){
                $('#div_mant_lista_docs_cargar_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_mant_lista_docs_cargar_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_mant_lista_docs_cargar_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN LISTA DE DOCUMENTOS ADJUNTOS EN UN EXPEDIENTE
//
//INICIO LISTA DE DERIVACIONES EN UN EXPEDIENTE
function sgd_mant_lista_deriva_cargar(){
    var idexp = $('#hd_id').val();//id del expediente
    
     $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_lista_deriva_cargar/",
            data:     "idexp="+idexp,
            beforeSend: function(data){
                $('#div_mant_lista_deriva_cargar_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_mant_lista_deriva_cargar_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_mant_lista_deriva_cargar_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN LISTA DE DERIVACIONES EN UN EXPEDIENTE
//
//ACUSE FISICO
function sgd_mant_fisico_popup(id_doc, id_flujo){
    
    var url = encodeURI(path + "sgd/mant_fisico_popup/?id_doc="+id_doc+"&id_flujo="+id_flujo);
   
    $.colorbox({
        "href" : url
       ,"width" : 450
       ,"height" : 480 
    });
}
//FIN ACUSE FISICO
//
//GUARDAR FISICO
function sgd_mant_fisico_guardar(){
    var id_doc = $('#hd_id_doc').val();
    var id_flujo = $('#hd_id_flujo').val(); 
    var fecfisico = $('#txt_fecfisico').val();    
    var observacion = $('#txt_obs').val();    
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_fisico_guardar/", 
            data:     "id_doc="+id_doc+"&id_flujo="+id_flujo+"&fecfisico="+fecfisico+"&observacion="+escape(observacion),	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);	
                var msj = arrayobj[0][1];
                
                $('#div_mensaje_ajax').html(msj);
                $('#div_mensaje_ajax').addClass('alert alert-success');   
                sgd_bandeja_perfil_tbl();                
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }                 
        });  
}
//FIN GUARDAR FISICO
//
//ARCHIVAR DERIVACIÓN
function sgd_mant_archiva_flujo_popup(id_flujo, i_cut, i_per_exp, c_des_persona, c_des_uo){
    
    var url = encodeURI(path + "sgd/mant_archiva_flujo_popup/?id_flujo="+id_flujo+"&i_cut="+i_cut+"&i_per_exp="+i_per_exp+"&c_des_persona="+c_des_persona+"&c_des_uo="+c_des_uo);
   
    $.colorbox({
        "href" : url
       ,"width" : 700
       ,"height" : 630 
    });
}
//FIN ARCHIVAR DERIVACIÓN
//
//GUARDAR ARCHIVAR DERIVACIÓN
function sgd_mant_archiva_flujo_guardar(){
    var id_flujo = $('#hd_id_flujo').val(); 
    var fecarchiva = $('#txt_fecarchiva').val(); 
    var obs = $('#txt_obs').val(); 
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_archiva_flujo_guardar/", 
            data:     "id_flujo="+id_flujo+"&fecarchiva="+fecarchiva+"&obs="+escape(obs),	 	 
            beforeSend: function(data){ 	 	
//                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);
                var msj = arrayobj[0][1];                
//                $('#div_mensaje_ajax').html(msj);                
                $.alert('<h6>' + msj + '</h6>');
                sgd_bandeja_perfil_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            } 
        });  
}
//FIN ARCHIVAR DERIVACIÓN
//
//INICIO DESARCHIVAR DERIVACIÓN
function sgd_mant_desarchiva_flujo_popup(){
    
    var url = encodeURI(path + "sgd/mant_archiva_desarch_flujo_popup/");
   
    $.colorbox({
        "href" : url
       ,"width" : 1200
       ,"height" : 800 
    });
}
//FIN ARCHIVAR DERIVACIÓN
//
//INICIO BUSCAR DERIVACIÓN ARCHIVADA
//function sgd_mant_exp_archivados_consulta(id_cut, periodo, codUser){
function sgd_mant_exp_archivados_consulta(){
    var id_cut = $('#txt_cut').val(); 
    var periodo = $('#cb_periodo').val();     
    var codUser = $('#hd_codUser').val();     
   
   $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_exp_archivados_consulta/",
            data:     "id_cut="+id_cut+"&periodo="+periodo+"&codUser="+codUser,
            beforeSend: function(data){
                $('#tab_exp_archivados_tbl').html("Cargando...");                
            },
            success: function(requestData){
                $('#tab_exp_archivados_tbl').html(requestData);                
            },
            error: function(requestData, strError, strTipoError){
                $('#div_exp_archivados_tbl').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN BUSCAR DERIVACIÓN ARCHIVADA
//
//INICIO DESARCHIVAR DERIVACIÓN
function sgd_mant_desarchivar(id_flujo){    
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_exp_desarchiva_guardar/", 
            data:     "id_flujo="+id_flujo,	 	 
            beforeSend: function(data){ 	 	
//                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);                
                var msj = arrayobj[0][0];
//                $('#div_mensaje_ajax').html(msj);
                
                $.alert('<h6>' + msj + '</h6>');
                
                sgd_mant_exp_archivados_consulta();
                sgd_bandeja_perfil_tbl();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }                 
        });  
}
//FIN DESARCHIVAR DERIVACIÓN
//  
//RECHAZAR DERIVACIÓN
function sgd_mant_rechaza_flujo_popup(cut, flujo, persona, unid_org){
    
    var url = encodeURI(path + "sgd/mant_rechaza_flujo_popup/?id_cut="+cut+"&id_flujo="+flujo+"&persona="+persona+"&unid_org="+unid_org);
   
    $.colorbox({
        "href" : url
       ,"width" : 750
       ,"height" : 650 
    });
}
//FIN RECHAZAR DERIVACIÓN
//
//INICIO RECHAZA DERIVACIÓN GUARDAR
function sgd_mant_rechaza_flujo_guardar(id_flujo){    
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_exp_rechaza_guardar/", 
            data:     "id_flujo="+id_flujo,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);
                
                var msj = arrayobj[0][0];
                $('#div_mensaje_ajax').html(msj);
                
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }                 
        });  
}
//FIN RECHAZA DERIVACIÓN GUARDAR
//
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL  
function sgd_mant_archcentral_tbl(){
    
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_archcentral_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
            $('#div_mant_archcentral_tbl').html("Cargando...");            
        },
        success: function(requestData){
            $('#div_mant_archcentral_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
            $('#div_mant_archcentral_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL  
//        
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL POPUP  
function sgd_mant_archcentral_envia_popup(unid_org, coduser){    
//SELECCIONAR CHECK de expedientes
    var id_exp = "";
    $('.cb_exp:checked').each(function () {        
        id_exp += $(this).val() + ",";  
    }); 
    id_exp = id_exp.substring(0, id_exp.length - 1);
    
    var url = encodeURI(path + "sgd/mant_archcentral_envia_popup/?unid_org="+unid_org+"&id_exp="+id_exp+"&coduser="+coduser);
   
    $.colorbox({
        "href" : url
       ,"width" : 600
       ,"height" : 620 
    });
}
//FIN ENVIO DOCUMENTOS ARCHIVO CENTRAL POPUP
//
//INICIO ENVIO DOCUMENTOS ARCHIVO CENTRAL GUARDAR
function sgd_mant_archcentral_envia_guardar(idexp,coduser){    
    
    var id_sd = $('#cb_sd').val();
    var obs = $('#txt_obs').val();
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_archcentral_envia_guardar/", 
            data:     "idexp="+idexp+"&coduser="+coduser+"&id_sd="+id_sd+"&obs="+escape(obs),	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var msj = arrayobj[0][0];
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_archcentral_tbl();
            },
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });      
}
//FIN ENVIO DOCUMENTOS ARCHIVO CENTRAL GUARDAR
//
//INICIO UBICACIÓN TOPOGRÁFICA TABLA
function sgd_mant_ubicaciontopo_tbl(){
    
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_ubicaciontopo_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
            $('#div_mant_ubicaciontopo_tbl').html("Cargando...");            
        },
        success: function(requestData){
            $('#div_mant_ubicaciontopo_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
            $('#div_mant_ubicaciontopo_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//INICIO UBICACIÓN TOPOGRÁFICA TABLA          
//        
//INICIO UBICACIÓN TOPOGRÁFICA POPUP  
function sgd_mant_ubicaciontopo_popup(cod_unid_org, coduser){    
//SELECCIONAR CHECK de expedientes
    var id_exp = "";
    $('.cb_exp:checked').each(function () {        
        id_exp += $(this).val() + ",";  
    }); 
    id_exp = id_exp.substring(0, id_exp.length - 1);
    
    var url = encodeURI(path + "sgd/mant_ubicaciontopo_popup/?cod_unid_org="+cod_unid_org+"&id_exp="+id_exp+"&coduser="+coduser);
   
    $.colorbox({
        "href" : url
       ,"width" : 600
       ,"height" : 650
    });
}
//FIN UBICACIÓN TOPOGRÁFICA POPUP
//

//INICIO UBICACIÓN TOPOGRÁFICA TREEVIEW
function sgd_mant_ubicaciontopo_treeview_buscar_hijos(arraydatos,codpadre,nivelpadre){
        var array_obj =  new Array();
        for(var i = 0 ; i<arraydatos.length ; i++){
            var cod = arraydatos[i][0];
            var nom = arraydatos[i][1];
            var cod_pad = arraydatos[i][2];
            var nivel = arraydatos[i][3];
            var cant_hijos = arraydatos[i][4];
            var ruta = arraydatos[i][5];
            var url = arraydatos[i][6];
            var tipo = arraydatos[i][7];
//            console.log(nivel+'--'+(parseInt(nivelpadre) + 1));
            if(nivel == (parseInt(nivelpadre) + 1) && codpadre == cod_pad){
//                console.log('--')
               var array_hijos =  new Array();
               if(cant_hijos>0){
                   array_hijos = sgd_mant_ubicaciontopo_treeview_buscar_hijos(arraydatos , cod , nivel );
                   var obj = {cod:cod, text:nom,tipo: tipo, nodes:array_hijos, tags: [cant_hijos]};
               }else{
                   if(nivel == 7)
                        var obj = {
                                    cod:cod
                                   ,text:nom
                                   ,icon: 'glyphicon glyphicon-file'
                                   ,ruta: ruta
                                   ,url : url
                                   ,tipo: tipo
                                  };
                   else
                        var obj = {cod:cod, text:nom , tags: [cant_hijos]};
               }
               array_obj.push(obj);
            }
        } 
        return array_obj;
}

function sgd_mant_ubicaciontopo_treeview(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_ubicaciontopo_treeview_data/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 
        },
        success: function(requestData){
               
               var arrayobj = jQuery.parseJSON(requestData);
               var datos_treeview = new Array()
               for(var i = 0 ; i<arrayobj.length ; i++){
                   var cod = arrayobj[i][0];
                   var nom = arrayobj[i][1];
                   var cod_pad = arrayobj[i][2];
                   var nivel = arrayobj[i][3];
                   var cant_hijos = arrayobj[i][4];
                   var ruta = arrayobj[i][5];
                   var url = arrayobj[i][6];
                   var tipo = arrayobj[i][7];
                   if(nivel == 1){
                     var array_hijos =  new Array();
                      if(cant_hijos>0){
                          var array_hijos = sgd_mant_ubicaciontopo_treeview_buscar_hijos(arrayobj , cod , nivel );
                      } 
                      var obj_almacen = {cod:cod, text:nom, tags: [cant_hijos] ,nodes:array_hijos};
                      datos_treeview.push(obj_almacen);
                   }
               } 
               var $Tree = $('#treeview-searchable').treeview({
                    //showTags: true,
                    data: datos_treeview,
                            onNodeSelected: function(event, node) {
                            //$('#selectable-output').prepend('<p>' + node.text + ' was selected</p>');
                            var url = node.url;
                            var cod = node.cod;
                            if(url != undefined ){
                                $('#pdf_preview').html('<iframe style="position: absolute;top:0;left: 0; width: 100%;height: 100%;" src="'+url+'"></iframe>');
                            }
                            var tipo = node.tipo;
                            if(tipo == 'U'){
                                $('#hd_tipo').val(cod); 
                                var cod_unid_org = $('#hd_unidorg').val();
                                var codUser = $('#hd_codUser').val();                                
                                sgd_mant_mov_unidcons_popup(cod, codUser, cod_unid_org);
//                                sgd_submenu(cod);
                            }else{
                               $('#hd_tipo').val(''); 
                            }                            
                      }
                  });

//                  var search = function(e) {
//                    var pattern = $('#input-search').val();
//                    var options = {
//                      ignoreCase: true,
//                      exactMatch: false,
//                      revealResults: false
//                    };
//                    var results = $searchableTree.treeview('search', [ pattern, options ]);
//
//                    var output = '<p>' + results.length + ' coincidencias</p>';
//                    $.each(results, function (index, result) {
//                        var text = result.text;
//                        var ruta = result.ruta;
//                        if(ruta != undefined ){
//                            output += '<p>- ' + text + '(' + ruta +')'+ '</p>';
//                        }else{
//                            output += '<p>- ' + text + '</p>';
//                        }
//                    });
//                    $('#search-output').html(output);
//                  }
//
//                  $('#btn-search').on('click', search);
//                  $('#input-search').on('keyup', search);
//
//                  $('#btn-clear-search').on('click', function (e) {
//                    $searchableTree.treeview('clearSearch');
//                    $('#input-search').val('');
//                    $('#search-output').html('');
//                  });

                    var findExpandibleNodess = function() {
                    $Tree.treeview('collapseAll', { silent: true });    
                    return $Tree.treeview('search', [ $('#input-search').val(), { ignoreCase: true, exactMatch: false } ]);
                  };
                  var expandibleNodes = findExpandibleNodess();

                  // Expand/collapse/toggle nodes
                  $('#input-search').on('keyup', function (e) {
                    expandibleNodes = findExpandibleNodess();
                    $('.expand-node').prop('disabled', !(expandibleNodes.length >= 1));
                  });

//                  $('#btn-expand-node.expand-node').on('click', function (e) {
//                    var levels = $('#select-expand-node-levels').val();
//                    $Tree.treeview('expandNode', [ expandibleNodes, { levels: levels, silent: $('#chk-expand-silent').is(':checked') }]);
//                  });
//
//                  $('#btn-collapse-node.expand-node').on('click', function (e) {
//                    $Tree.treeview('collapseNode', [ expandibleNodes, { silent: $('#chk-expand-silent').is(':checked') }]);
//                  });
//
//                  $('#btn-toggle-expanded.expand-node').on('click', function (e) {
//                    $Tree.treeview('toggleNodeExpanded', [ expandibleNodes, { silent: $('#chk-expand-silent').is(':checked') }]);
//                  });//
                  // Expand/collapse all
                  $('#btn-expand-all').on('click', function (e) {
                    $Tree.treeview('expandAll');
                  });

                  $('#btn-collapse-all').on('click', function (e) {
                    $Tree.treeview('collapseAll');
                  });
        },		
        error: function(requestData, strError, strTipoError){											
//            console.log("Error " + strTipoError +": " + strError);
        }
    }); 
}
//FIN UBICACIÓN TOPOGRÁFICA TREEVIEW 
//
//INICIO CARGAR COMBO ESTANTE
function sgd_mant_estante_cargar_cbo(){
    var id_almacen = $('#cb_almacen').val();
    var id_estante = $('#hd_estante').val();
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_estante_cargar_cbo/", 
            data:     "id_almacen="+id_almacen+"&id_estante="+id_estante,	 	 
            beforeSend: function(data){ 	 	
                $('#cb_estante').html("<option>CARGANDO...</option>");
                $('#cb_estante').selectpicker('refresh');                
            },
            success: function(requestData){
                $('#cb_estante').html(requestData);
                $('#cb_estante').selectpicker('refresh');
            },			
            error: function(requestData, strError, strTipoError){
                $('#cb_estante').html("<option>Error " + strTipoError +": " + strError+"</option>");
                $('#cb_estante').selectpicker('refresh');
            }
        });  
}
//FIN CARGAR COMBO ESTANTE
//
//INICIO CARGAR COMBO CUERPO
function sgd_mant_cuerpo_cargar_cbo(){
    var id_estante = $('#hd_estante').val();
    var id_cuerpo = $('#hd_cuerpo').val();
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_cuerpo_cargar_cbo/", 
            data:     "id_estante="+id_estante+"&id_cuerpo="+id_cuerpo,	            
            beforeSend: function(data){ 	 	
                $('#cb_cuerpo').html("<option>CARGANDO...</option>");
                $('#cb_cuerpo').selectpicker('refresh');
            },
            success: function(requestData){
                $('#cb_cuerpo').html(requestData);
                $('#cb_cuerpo').selectpicker('refresh');
            },			
            error: function(requestData, strError, strTipoError){
                $('#cb_cuerpo').html("<option>Error " + strTipoError +": " + strError+"</option>");
                $('#cb_cuerpo').selectpicker('refresh');
            }
        });  
}
//FIN CARGAR COMBO CUERPO
//
//INICIO CARGAR COMBO BALDA
function sgd_mant_balda_cargar_cbo(){
    var id_cuerpo = $('#hd_cuerpo').val();
    var id_balda = $('#hd_balda').val();
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_balda_cargar_cbo/", 
            data:     "id_cuerpo="+id_cuerpo+"&id_balda="+id_balda,	 	 
            beforeSend: function(data){ 	 	
                $('#cb_balda').html("<option>CARGANDO...</option>");
                $('#cb_balda').selectpicker('refresh');
            },
            success: function(requestData){
                $('#cb_balda').html(requestData);
                $('#cb_balda').selectpicker('refresh');
            },			
            error: function(requestData, strError, strTipoError){
                $('#cb_balda').html("<option>Error " + strTipoError +": " + strError+"</option>");
                $('#cb_balda').selectpicker('refresh');
            }
        });  
}
//FIN CARGAR COMBO BALDA
//
//INICIO CARGAR COMBO UNIDAD DE CONSERVACIÓN
function sgd_mant_unidcons_cargar_cbo(){
    var id_balda = $('#cb_balda').val();
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_unidcons_cargar_cbo/", 
            data:     "id_balda="+id_balda,	 	 
            beforeSend: function(data){ 	 	
                $('#cb_unidc').html("<option>CARGANDO...</option>");
                $('#cb_unidc').selectpicker('refresh');
            },
            success: function(requestData){
                $('#cb_unidc').html(requestData);
                $('#cb_unidc').selectpicker('refresh');
            },			
            error: function(requestData, strError, strTipoError){
                $('#cb_unidc').html("<option>Error " + strTipoError +": " + strError+"</option>");
                $('#cb_unidc').selectpicker('refresh');
            }
        });  
}
//FIN CARGAR COMBO UNIDAD DE CONSERVACIÓN
//
//INICIO UBICACIÓN TOPOGRÁFICA GUARDAR
function sgd_mant_ubicaciontopo_guardar(){
    var idexp = $('#hd_idexp').val();
    var unidcons = $('#cb_unidc').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_ubicaciontopo_guardar/", 
            data:     "idexp="+idexp+"&unidcons="+unidcons,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var msj = arrayobj[0][0];
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_ubicaciontopo_tbl();
            },
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });      
}
//FIN ENVIO UBICACIÓN TOPOGRÁFICA GUARDAR
//
//INICIO MOVIMIENTO DE UNIDAD DE CONSERVACIÓN TABLA
function sgd_mant_mov_unidcons_tbl(){
    
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_mov_unidcons_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
            $('#div_mant_mov_unidcons_tbl').html("Cargando...");            
        },
        success: function(requestData){
            $('#div_mant_mov_unidcons_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
            $('#div_mant_mov_unidcons_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//INICIO MOVIMIENTO DE UNIDAD DE CONSERVACIÓN TABLA      
//
//INICIO MOVIMIENTO DE UNIDAD DE CONSERVACIÓN POPUP  
function sgd_mant_mov_unidcons_popup(cod, codUser, cod_unid_org){    
//SELECCIONAR CHECK de expedientes
//    var i_id = "";
//    $('.cb_unidcons:checked').each(function () {        
//        i_id += $(this).val() + ",";  
//    }); 
//    i_id = i_id.substring(0, i_id.length - 1);
    
    var i_id = cod; 
    var cod_unid_org = cod_unid_org;   
    var coduser = codUser;
//    var cod_unid_org = $('#hd_unidorg').val();   
//    var coduser = $('#hd_codUser').val();
    
    var url = encodeURI(path + "sgd/mant_mov_unidcons_popup/?cod_unid_org="+cod_unid_org+"&coduser="+coduser+"&id_unidcons="+i_id);
   
    $.colorbox({
        "href" : url
       ,"width" : 800
       ,"height" : 650 
    });
}
//FIN MOVIMIENTO DE UNIDAD DE CONSERVACIÓN POPUP
//
//INICIO LISTA UNIDADES DE CONSERVACIÓN A MOVER
function sgd_mant_unidcons_lista(){
    var id_unidcons = $('#hd_id_unidcons').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_unidcons_lista/",
            data:     "id_unidcons="+id_unidcons,
            beforeSend: function(data){
                $('#div_mant_unidcons_lista').html("Cargando...");
            },
            success: function(requestData){
                $('#div_mant_unidcons_lista').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_mant_unidcons_lista').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN LISTA UNIDADES DE CONSERVACIÓN A MOVER
//
//INICIO MOVIMIENTO DE UNIDAD DE CONSERVACIÓN GUARDAR
function sgd_mant_mov_unidcons_guardar(){
    var idunidcons = $('#hd_id_unidcons').val();
    var idbalda = $('#cb_balda').val();
    var obs = $('#txt_obs').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_mov_unidcons_guardar/", 
            data:     "idunidcons="+idunidcons+"&idbalda="+idbalda+"&obs="+escape(obs),	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var msj = arrayobj[0][1];
                $('#div_mensaje_ajax').html(msj);
                
                sgd_mant_ubicaciontopo_treeview();
                
                sgd_mant_ubicaciontopo_tbl();
            },
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });      
}
//FIN MOVIMIENTO DE UNIDAD DE CONSERVACIÓN GUARDAR
//
//INICIO AGRUPAR EXPEDIENTES POPUP

function sgd_lista_cut_agrupa_tmp(chbx){ 
    
    valor = $(chbx).val();
    ch    = $(chbx).is(':checked');
 
    if(ch){
        tmp = $('#hd_tmp_cut_agrupa').val();
        var n = tmp.indexOf(valor);
//        console.log(n);
        if(n > -1){
            $(chbx).removeAttr('checked');
            $.alert({title:'ALERTA!',content:"<h4>CUT YA FUE SELECCIONADO.</h4>",type: 'red'});
        }else{
            $('#hd_tmp_cut_agrupa').val( tmp  + valor + ",");
        }        
    }else{
      tmp = $('#hd_tmp_cut_agrupa').val();
      tmp = tmp.replace(valor+",","");
      $('#hd_tmp_cut_agrupa').val(tmp);
    }
    
//    console.log($('#hd_tmp_cut_agrupa').val());
    
}

function sgd_mant_agrupa_popup(){    
//    var id_expediente = ""; //class=\"cb_agrupa_exp\"  
//    $('.cb_agrupa_exp:checked').each(function () {        
//        id_expediente += $(this).val() + ",";  
//    }); 
    
    var id_expediente = $('#hd_tmp_cut_agrupa').val();
    if(id_expediente != '')
        id_expediente = id_expediente.substring(0, id_expediente.length - 1);

    var codUser = $('#hd_codUser').val();
    var url = encodeURI(path + "sgd/mant_agrupa_popup/?id_expediente="+id_expediente+"&codUser="+codUser);
   
    $.colorbox({
        "href" : url
       ,"width" : 600
       ,"height" : 500 
    });
}
//FIN AGRUPAR EXPEDIENTES POPUP
//
//INICIO AGRUPA GUARDAR
function sgd_mant_agrupa_guardar(){
    var cad_id_exp = $('#hd_cad_id_exp').val();
    var codUser = $('#hd_codUser').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_agrupa_guardar/", 
            data:     "cad_id_exp="+cad_id_exp+"&codUser="+codUser,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);                
                var msj = arrayobj[0][0];
                $('#div_mensaje_ajax').html(msj);
                $('#div_mensaje_ajax').addClass('alert alert-success');
                sgd_bandeja_perfil_tbl();                
            },
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });      
}
//FIN AGRUPA GUARDAR
//
//INICIO LISTA EXPEDIENTES AGRUPADOS
function sgd_mant_lista_agrupa_cargar(){
    var idexp = $('#hd_id').val();//id del expediente
    var exp_agr = '';
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_lista_agrupa_cargar/", 
        data:     "idexp="+idexp,	 	 
        beforeSend: function(data){ 
        },
        success: function(requestData){               
               var arrayobj = jQuery.parseJSON(requestData);
               var datos_treeview = new Array()
               for(var i = 0 ; i<arrayobj.length ; i++){
                   var cod = arrayobj[i][0];
                   var nom = arrayobj[i][1];
                   var cod_pad = arrayobj[i][2];
                   var nivel = arrayobj[i][3];
                   var cant_hijos = arrayobj[i][4];
                   var ruta = arrayobj[i][5];
                   var url = arrayobj[i][6];
                   var tipo = arrayobj[i][7];
                   if(nivel == 1){
                     var array_hijos =  new Array();
                      if(cant_hijos>0){
                          var array_hijos = sgd_mant_lista_agrupa_cargar_buscar_hijos(arrayobj , cod , nivel );
                      } 
                      var obj_almacen = {cod:cod, text:nom, tags: [cant_hijos] ,nodes:array_hijos, showCheckbox: true};
                      datos_treeview.push(obj_almacen);
                   }
               } 
               var $Tree = $('#treeview-searchable').treeview({
                    //showTags: true,
                    data: datos_treeview,
                            onNodeSelected: function(event, node) {
                            //$('#selectable-output').prepend('<p>' + node.text + ' was selected</p>');
                            var url = node.url;
                            var cod = node.cod;
                            if(url != undefined ){
                                $('#pdf_preview').html('<iframe style="position: absolute;top:0;left: 0; width: 100%;height: 100%;" src="'+url+'"></iframe>');
                            }
                            var tipo = node.tipo;
//                            if(tipo == 'U'){
//                                $('#hd_tipo').val(cod); 
//                                var cod_unid_org = $('#hd_unidorg').val();
//                                var codUser = $('#hd_codUser').val();                                
//                                sgd_mant_mov_unidcons_popup(cod, codUser, cod_unid_org);
////                                sgd_submenu(cod);
//                            }else{
//                               $('#hd_tipo').val(''); 
//                            }                            
                          },                          
                          onNodeChecked: function(event, node) {
//                            $('#tree_node_checked').append(node.cod+',');
//                         
                           exp_agr += node.cod+',';
                            $('#hd_idexp_desagr').val(exp_agr);
                          },
                          onNodeUnchecked: function (event, node) {
//                            var cc = $('#tree_node_checked').html();
//                            cc = cc.replace(node.cod+",","");
//                            $('#tree_node_checked').html(cc);
                            
                            var cca = exp_agr;
                            cca = cca.replace(node.cod+",","");
                            $('#hd_idexp_desagr').val(cca);
                            exp_agr ='';
                          }                      
                  });

                    var findExpandibleNodess = function() {
                    $Tree.treeview('collapseAll', { silent: true });    
                    return $Tree.treeview('search', [ $('#input-search').val(), { ignoreCase: true, exactMatch: false } ]);
                  };
                  var expandibleNodes = findExpandibleNodess();

                  // Expand/collapse/toggle nodes
                  $('#input-search').on('keyup', function (e) {
                    expandibleNodes = findExpandibleNodess();
                    $('.expand-node').prop('disabled', !(expandibleNodes.length >= 1));
                  });

//                  $('#btn-expand-node.expand-node').on('click', function (e) {
//                    var levels = $('#select-expand-node-levels').val();
//                    $Tree.treeview('expandNode', [ expandibleNodes, { levels: levels, silent: $('#chk-expand-silent').is(':checked') }]);
//                  });
//
//                  $('#btn-collapse-node.expand-node').on('click', function (e) {
//                    $Tree.treeview('collapseNode', [ expandibleNodes, { silent: $('#chk-expand-silent').is(':checked') }]);
//                  });
//
//                  $('#btn-toggle-expanded.expand-node').on('click', function (e) {
//                    $Tree.treeview('toggleNodeExpanded', [ expandibleNodes, { silent: $('#chk-expand-silent').is(':checked') }]);
//                  });//
                  // Expand/collapse all
                  $('#btn-expand-all').on('click', function (e) {
                    $Tree.treeview('expandAll');
                  });

                  $('#btn-collapse-all').on('click', function (e) {
                    $Tree.treeview('collapseAll');
                  });
        },		
        error: function(requestData, strError, strTipoError){
        }
    });     
}
//FIN LISTA EXPEDIENTES AGRUPADOS
//
//INICIO DOCUMENTOS DE EXPEDIENTES AGRUPADOS
function sgd_mant_lista_agrupa_cargar_buscar_hijos(arraydatos,codpadre,nivelpadre){
        var array_obj =  new Array();
        for(var i = 0 ; i<arraydatos.length ; i++){
            var cod = arraydatos[i][0];
            var nom = arraydatos[i][1];
            var cod_pad = arraydatos[i][2];
            var nivel = arraydatos[i][3];
            var cant_hijos = arraydatos[i][4];
            var ruta = arraydatos[i][5];
            var url = arraydatos[i][6];
            var tipo = arraydatos[i][7];
            if(nivel == (parseInt(nivelpadre) + 1) && codpadre == cod_pad){
               var array_hijos =  new Array();
               if(cant_hijos>0){
                   array_hijos = sgd_mant_lista_agrupa_cargar_buscar_hijos(arraydatos , cod , nivel );
                   var obj = {cod:cod, text:nom,tipo: tipo, nodes:array_hijos, tags: [cant_hijos]};
               }else{
                   if(nivel == 3)
                        var obj = {
                                    cod:cod
                                   ,text:nom
                                   ,icon: 'glyphicon glyphicon-file'
                                   ,ruta: ruta
                                   ,url : url
                                   ,tipo: tipo
                                  };
                   else
                        var obj = {cod:cod, text:nom , tags: [cant_hijos]};
               }
               array_obj.push(obj);
            }
        } 
        return array_obj;
}
//FIN DOCUMENTOS DE EXPEDIENTES AGRUPADOS
//
//INICIO DESAGRUPAR EXPEDIENTES
function sgd_desagrupa_guardar(){
    var idexp_desagr = $('#hd_idexp_desagr').val();
    var codUser = $('#hd_userreg').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_desagrupa_guardar/", 
            data:     "idexp_desagr="+idexp_desagr,
            beforeSend: function(data){
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);
                var msj = arrayobj[0][0];
                $('#div_mensaje_ajax').html('');
                sgd_mant_lista_agrupa_cargar();
                sgd_bandeja_perfil_tbl();
            },
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });      
}
//FIN DESAGRUPAR EXPEDIENTES
//
//INICIO BANDEJA ENTRADA PERFIL
function sgd_bandeja_perfil_tbl(cod_perfil){
    if(cod_perfil == undefined){
        cod_perfil = '';
    }    
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/bandeja_perfil_tbl/", 
        data:     "cod_perfil="+cod_perfil,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_bandeja_perfil_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_bandeja_perfil_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_bandeja_perfil_tbl').html("Error " + strTipoError +": " + strError);
        }
    });    
}
//FIN BANDEJA ENTRADA PERFIL
//
//INICIO BUSCAR EXPEDIENTE POPUP
//function sgd_mant_buscar_exp_popup(){       
//    var codUser = $('#hd_codUser').val();
//    
//    var url = encodeURI(path + "sgd/mant_buscar_exp_popup/?codUser="+codUser);
//   
//    $.colorbox({
//        "href" : url
//       ,"width" : 700
//       ,"height" : 600 
//    });
//    
//}
//FIN BUSCAR EXPEDIENTE POPUP
//
//
//INICIO BUSCAR EXPEDIENTE TABLA
function sgd_buscarexp_tbl(){
    var codUser = $('#hd_codUser').val();    
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscarexp_tbl/",
            data:     "codUser="+codUser,
            beforeSend: function(data){
                $('#div_buscarexp_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscarexp_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscarexp_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE TABLA
//
//INICIO BUSCAR EXPEDIENTE PROFESIONAL TABLA
function sgd_buscarexp_prof_tbl(){
    var codUser = $('#hd_codUser').val();    
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscarexp_prof_tbl/",
            data:     "codUser="+codUser,
            beforeSend: function(data){
                $('#div_buscarexp_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscarexp_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscarexp_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE PROFESIONAL TABLA
//
//INICIO MOSTRAR EXPEDIENTE TABLA
function sgd_expediente_buscar_popup(i_id, id_doc, codUser){
    
//   var url = encodeURI(path + "sgd/mant_expediente_buscar_popup/?codUser="+codUser+"&idexp="+idexp);   
    var url = encodeURI(path + "sgd/mant_expediente_buscar_popup/?id_exp="+i_id+"&id_doc="+id_doc+"&codUser="+codUser);
  
    $.colorbox({
        "href" : url
       ,"width" : 1200
       ,"height" : 1000 
    });
}
//FIN MOSTRAR EXPEDIENTE TABLA
//
//INICIO CONSULTA ALTA DIRECCIÓN TABLA
function sgd_mant_buscar_altdir_tbl(cut,anio,asunto,cd,nro,uo){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_altdir_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro+"&uo="+uo,
            beforeSend: function(data){
                $('#div_buscar_altdir_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_altdir_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_altdir_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN CONSULTA ALTA DIRECCIÓN TABLA
//
//INICIO ADMINISTRADO TABLA
function sgd_mant_administrado_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "sgd/mant_administrado_tbl/", 
        data:     "" ,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_administrado_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_administrado_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_administrado_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN ADMINISTRADO TABLA
//
//INICIO ADMINISTRADO POPUP
function sgd_mant_administrado_popup(id){
    
     var url = encodeURI(path + "sgd/mant_administrado_popup/?id="+id);
  
    $.colorbox({
        "href" : url
       ,"width" : 600
       ,"height" : 650 
    });    
}
//FIN ADMINISTRADO POPUP
//
//INICIO GESTION ADMINISTRADO GUARDAR
function sgd_mant_administrado_guardar(){
    var id = $('#hd_id').val();
    var des = $('#txt_descripcion').val();
    var repr = $('#txt_representante').val();
    var dir = $('#txt_direccion').val();
    var telef = $('#txt_telefono').val();
    var email = $('#txt_email').val();
    var tipodoc = $('#cb_tipodoc').val();
    var nrodoc = $('#txt_nrodoc').val();
    
    var msj_error = "";
    if ($('#cb_tipodoc').val().length == 0){
        msj_error += " Tipo documento.";
    }if ($('#txt_nrodoc').val().length == 0){
        msj_error += " N° documento.";
    }if ($('#txt_descripcion').val().length == 0){
        msj_error += " Descripción.";
    }if ($('#txt_representante').val().length == 0){
        msj_error += " Representante.";    
    }
    if(msj_error == ""){       
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_administrado_guardar/", 
            data:     "id="+id+"&des="+escape(des)+"&repr="+escape(repr)+"&dir="+dir+"&telef="+telef+"&email="+email+"&tipodoc="+tipodoc+"&nrodoc="+nrodoc,	 	 
//            data:     "id="+id+"&des="+des+"&repr="+repr+"&dir="+dir+"&telef="+telef+"&email="+email+"&tipodoc="+tipodoc+"&nrodoc="+nrodoc,	 	 
            beforeSend: function(data){ 
            },
            success: function(requestData){
                
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                
                $('#hd_id').val(id);
//                $('#div_mensaje_ajax').html(msj);                
                $.alert('<h6>' + msj + '</h6>');
                sgd_mant_administrado_tbl();
            },		
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
    }else{
         $.alert('<h6>Ingrese: ' + msj_error + '</h6>');
    }
}
//FIN GESTION ADMINISTRADO GUARDAR
//
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA
function sgd_mant_buscar_derivado_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_derivado_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#div_buscar_derivado_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_derivado_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_derivado_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA
//
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA
function sgd_mant_buscar_recibido_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_recibido_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#div_buscar_recibido_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_recibido_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_recibido_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA
//
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA POR DIRECCION
function sgd_mant_buscar_derivado_dir_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_derivado_dir_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#div_buscar_derivado_dir_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_derivado_dir_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_derivado_dir_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA POR DIRECCION
//
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA POR DIRECCION
function sgd_mant_buscar_recibido_dir_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_recibido_dir_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#div_buscar_recibido_dir_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_recibido_dir_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_recibido_dir_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA POR DIRECCION
//
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA POR PROFESIONAL
function sgd_mant_buscar_derivado_prof_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_derivado_prof_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#div_buscar_derivado_prof_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_derivado_prof_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_derivado_prof_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA POR PROFESIONAL
//
//INICIO BUSCAR EXPEDIENTE DERIVADO TABLA POR DIRECCION
function sgd_mant_buscar_recibido_prof_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_recibido_prof_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#div_buscar_recibido_prof_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_recibido_prof_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_recibido_prof_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE DERIVADO TABLA POR DIRECCION
//
//INICIO BUSCAR EXPEDIENTE CREADOS TABLA POR DIRECCIÓN    
function sgd_mant_buscar_creado_dir_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_creado_dir_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#div_buscar_creado_dir_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_creado_dir_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_creado_dir_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE CREADOS TABLA POR DIRECCIÓN    
//
//INICIO BUSCAR EXPEDIENTE CREADOS TABLA POR PROFESIONAL    
function sgd_mant_buscar_creado_prof_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_creado_prof_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#sgd_mant_buscar_creado_prof_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#sgd_mant_buscar_creado_prof_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#sgd_mant_buscar_creado_prof_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE CREADOS TABLA POR PROFESIONAL    
//
//INICIO BUSCAR EXPEDIENTE CREADOS TABLA POR ALTA DIRECCIÓN    
function sgd_mant_buscar_creado_tbl(cut,anio,asunto,cd,nro){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_creado_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro,
            beforeSend: function(data){
                $('#div_buscar_creado_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_creado_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_creado_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSCAR EXPEDIENTE CREADOS TABLA POR ALTA DIRECCIÓN     
//
//INICIO GUARDA FECHA ACUSE
function sgd_mant_acuse_guardar(id_flujo, fecha_acuse, cod_perfil){
        
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_acuse_guardar/", 
            data:     "id_flujo="+id_flujo+"&fecha_acuse="+fecha_acuse,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);                 
                sgd_bandeja_perfil_tbl(cod_perfil);                 
            },		
            error: function(requestData, strError, strTipoError){											
//                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });  
}
//FIN GUARDA FECHA ACUSE 
//
//INICIO REPORTE UO PENDIENTE TABLA 
function sgd_mant_rep_uo_pendiente_tbl(){
    var uo = $('#cb_unidorg').val();
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_rep_uo_pendiente_tbl/",
            data:     "uo="+uo,
            beforeSend: function(data){
                $('#div_rep_uo_pendiente_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_rep_uo_pendiente_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_rep_uo_pendiente_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN REPORTE UO PENDIENTE TABLA     
//
//INICIO REPORTE CUENTA PENDIENTES
function sgd_mant_cuenta_pendiente(){
    var uo = $('#cb_unidorg').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_cuenta_pendiente/",
            data:     "uo="+uo,
            beforeSend: function(data){
//                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var cuenta = arrayobj[0][0];
                $('#txt_pendiente').val(cuenta);
                $('#lb_pendiente').addClass('active');
                $("#div_cuenta_pendiente").show();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_cuenta_pendiente').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN REPORTE CUENTA PENDIENTES  
//
//INICIO REPORTE CUENTA CONSULTADOS
function sgd_mant_cuenta_consultado(){
    var uo = $('#cb_unidorg').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_cuenta_consultado/",
            data:     "uo="+uo,
            beforeSend: function(data){
//                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var cuenta = arrayobj[0][0];
                $('#txt_consultado').val(cuenta);
                $('#lb_consultado').addClass('active');
                $("#div_cuenta_consultado").show();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_cuenta_consultado').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN REPORTE CUENTA CONSULTADOS  
//
//INICIO REPORTE CUENTA NO CONSULTADOS
function sgd_mant_cuenta_noconsultado(){
    var uo = $('#cb_unidorg').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_cuenta_noconsultado/",
            data:     "uo="+uo,
            beforeSend: function(data){
//                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var cuenta = arrayobj[0][0];
                $('#txt_noconsultado').val(cuenta);
                $('#lb_noconsultado').addClass('active');
                $("#div_cuenta_noconsultado").show();
            },
            error: function(requestData, strError, strTipoError){
                $('#div_cuenta_noconsultado').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN REPORTE CUENTA NO CONSULTADOS
//
//INICIO REPORTE UO POR PERIODO GRÁFICO
function sgd_mant_rep_uo_periodo_grf(){
    var uo = $('#cb_unidorg').val();
    var f_ini = $('#dt_fec_ini').val();
    var f_fin = $('#dt_fec_fin').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_rep_uo_periodo_grf/",
            data:     "uo="+uo+"&f_ini="+f_ini+"&f_fin="+f_fin,
            beforeSend: function(data){
//                $('#div_rep_uo_periodo').html("Cargando...");
            },
            success: function(requestData){
                var arrayobj = jQuery.parseJSON(requestData);
                arrayobj.reverse();
                           
                var uo = "";
                var estado = "";
                var valor = "";
                var dat_cat =new Array();
                var dat_ser =new Array();
                 for(i=0; i<arrayobj.length; i++){
                    uo = arrayobj[i][0];
                    estado = arrayobj[i][1];
                    valor = arrayobj[i][2];
                    dat_cat.push(estado);
                    dat_ser.push(parseFloat(valor));
                 }
                
                Highcharts.chart('div_rep_uo_periodo_grf', {
                chart: {                    
                    type: 'bar'
                },
                title: {
                    text: "GRAFICO DEL "+ f_ini + ' AL '+ f_fin
                },
                xAxis: {
                    categories: dat_cat
                },
                legend: {
                    enabled: false
                },
                series: [{
                    type: 'bar',
                    name: 'Cantidad: ',
                    data: dat_ser
                }]
            });
                
            },
            error: function(requestData, strError, strTipoError){
                $('#div_rep_uo_periodo_grf').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN REPORTE UO POR PERIODO GRÁFICO 
//
//INICIO REPORTE UO PROFESIONAL GRÁFICO
function sgd_mant_rep_uo_prof_grf(){
    var uo = $('#cb_unidorg').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_rep_uo_prof_grf/",
            data:     "uo="+uo,
            beforeSend: function(data){
//                $('#div_rep_uo_periodo').html("Cargando...");
            },
            success: function(requestData){
                var arrayobj = jQuery.parseJSON(requestData);
                
                var fecha = new Date();
                var meses = new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
                var fec_actual = 'Al ' + fecha.getDate() + ' de ' + meses[fecha.getMonth()] + ' de ' + fecha.getFullYear();
                
                var prof = "";
                var estado = "";
                var valor = "";
                var dat_prof = new Array();
                var dat_val_pend = new Array();
                var dat_val_aten = new Array();
                var dat_val_arch = new Array();                
                var dat_profesionales = new Array();
                
                var j = 0;
                for(j=0; j<arrayobj.length; j++){
                    prof = arrayobj[j][0];
                    dat_prof.push(prof);
                    dat_profesionales = dat_prof.unique();
                }                
                
                var k = 0;
                for(k=0; k<dat_prof.length; k++){                         
                    estado = arrayobj[k][1];
                    valor = arrayobj[k][2];                                           
                    if (estado == 'ARCHIVADO'){
                        dat_val_arch.push(parseFloat(valor));
                    }else if (estado == 'ATENDIDO'){
                        dat_val_aten.push(parseFloat(valor));
                    }else 
                        if (estado == 'PENDIENTE'){
                        dat_val_pend.push(parseFloat(valor));
                    }   
                }                
                Highcharts.chart('div_rep_uo_prof_grf', {
                    chart: {
                        type: 'bar',
                        height: 700,
                        marginTop: 70
                    },
                    title: {
                        text: "REPORTE DE PROFESIONALES POR DIRECCIÓN"
                    },
                    subtitle: {
                        text: fec_actual
                    },
                    xAxis: {
                        categories: dat_profesionales
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Cantidad (unid)'
                        },
                         tickInterval: 30,
                         maxPadding: 0
                    },
                    plotOptions: {
                        bar: {
                            pointPadding: 0.1,
                            borderWidth: 0
                        }
                    },
                    legend: {
                        enabled: true
                    },
                    series: [{
                        name: 'Archivados',
                        data: dat_val_arch                        
                    },{
                        name: 'Atendidos',
                        data: dat_val_aten
                    },{
                       name: 'Pendientes',
                        data: dat_val_pend 
                    }]
                });                
            },
            error: function(requestData, strError, strTipoError){
                $('#div_rep_uo_prof_grf').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN REPORTE UO PROFESIONAL GRÁFICO
//
//INICIO BUSQUEDA DE EXPEDIENTES UTF TABLA 
function sgd_mant_buscar_exp_utf_tbl(cut,anio,asunto,cd,nro,uo){
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd/mant_buscar_exp_utf_tbl/",
            data:     "cut="+cut+"&anio="+anio+"&asunto="+asunto+"&cd="+cd+"&nro="+nro+"&uo="+uo,
            beforeSend: function(data){
                $('#div_buscar_exp_uft_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_buscar_exp_uft_tbl').html(requestData);
            },
            error: function(requestData, strError, strTipoError){
                $('#div_buscar_exp_uft_tbl').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN BUSQUEDA DE EXPEDIENTES UTF TABLA    
//
//INICIO CARGOS POPUP
function sgd_mant_cargo_popup(cut_exp,id_doc,documento){
    var url = encodeURI(path + "sgd/mant_cargo_popup/?cut_exp="+cut_exp+"&id_doc="+id_doc+"&documento="+documento);
    
    $.colorbox({
        "href" : url
       ,"width" : 800
       ,"height" : 700 
    });
}
//FIN CARGOS POPUP


