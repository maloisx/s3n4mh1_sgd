
//INICIO LISTA EXPEDIENTES PAD TABLA
function pad_mant_expedientes_pad_tbl(abogado){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "pad/mant_expedientes_pad_tbl/", 
        data:     "abogado="+abogado,	 	 
        beforeSend: function(data){ 	 	
                $('#div_mant_expedientes_pad_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_expedientes_pad_tbl').html(requestData);
        },		
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_expedientes_pad_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN LISTA EXPEDIENTES PAD TABLA
//
//INICIO NUEVO EXPEDIENTE PAD POPUP
function pad_mant_expedientes_pad_popup(id){
    var url = encodeURI(path + "pad/mant_expedientes_pad_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 1200
       ,"height" : 900 
    });
}
//FIN NUEVO EXPEDIENTE PAD POPUP
//
//INICIO MODIFICAR EXPEDIENTE PAD POPUP
function pad_mant_expedientes_pad_modifica_popup(id){
    
    var url = encodeURI(path + "pad/mant_expedientes_pad_modifica_popup/?id="+id);
    
    $.colorbox2({
        "href" : url
       ,"width" : 1200
       ,"height" : 900 
    });
}
//FIN MODIFICAR EXPEDIENTE PAD POPUP
//
//INICIO MODIFICAR EXPEDIENTE PAD POPUP
function pad_mant_expedientes_pad_consulta_popup(id){
    
    var url = encodeURI(path + "pad/mant_expedientes_pad_consulta_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 1400
       ,"height" : 900 
    });
}
//FIN MODIFICAR EXPEDIENTE PAD POPUP
//
//INICIO EXPEDIENTE PAD GUARDAR
function pad_mant_expedientes_pad_guardar(){
    var id = $('#txt_nroexp').val();
    var fecharecep = $('#txt_fecharecep').val();
    var fecpresc_iniPAD = $('#txt_fecpresc_iniPAD').val();
    var etapa = $('#cb_etapa').val();
    var denunciante = $('#cb_denunciante').val();
    var dependencia = $('#cb_dependencia').val();
    var abogado = $('#cb_abogado').val();
    var tipo_proced = $('#cb_tipo_proced').val();
    var documento = $('#cb_documento').val();
    var nrodoc = $('#txt_nrodoc').val();    
    var fechadoc = $('#txt_fechadoc').val();    
    var folio = $('#txt_folio').val();    
    var plazo = $('#txt_plazo').val();    
    var remite = $('#cb_remite').val(); 
    var uo_remite = remite.split('_')[3];  
    remite = remite.split('_')[0];
    var destino = $('#cb_destino').val();    
    destino = destino.split('_')[0];    
    var asunto = $('#txt_asunto').val(); 
    asunto = asunto.replace("–","");
    asunto = asunto.replace("\“","'");
    asunto = asunto.replace("\”","'"); 
    var observacion = $('#txt_observacion').val();  
    observacion = observacion.replace("–","");
    observacion = observacion.replace("\“","'");
    observacion = observacion.replace("\”","'"); 
    var input = document.querySelector('input[type="file"]');
    var iddoc = $('#hd_iddoc').val(); 
    var estado = $('#hd_estado').val(); 
    var instructor = $('#cb_instructor').val();  
    var uo_instructor = '';
    if(instructor == undefined || instructor == ''){
        instructor = '';
        uo_instructor = '';
    }else{ 
        uo_instructor = instructor.split('_')[1];
        instructor = instructor.split('_')[0];        
    }   
    var sancionador = $('#cb_sancionador').val();   
    var uo_sancionador = '';
    if(sancionador == undefined || sancionador == ''){
        sancionador = '';
        uo_sancionador = '';       
    }else{
        uo_sancionador = sancionador.split('_')[1];
        sancionador = sancionador.split('_')[0];
    }
    console.log('****************************************'+sancionador); //vacio
    
    
    var fecnotif_iniPAD = $('#txt_fecnotif_iniPAD').val(); 
    if(fecnotif_iniPAD == undefined){
        fecnotif_iniPAD = '';
    }
    
    var fecpres_PAD = $('#txt_fecpres_PAD').val(); 
    if(fecpres_PAD == undefined){
        fecpres_PAD = '';
    }
   
//    Mensaje ingreso información 
     var msj_error = "";
     
    if ($('#cb_denunciante').val() == ''){
        msj_error += " Denunciante.";
    }if ($('#cb_dependencia').val() == ''){
        msj_error += " Dependencia.";
    }if ($('#cb_abogado').val() == ''){
        msj_error += " Abogado.";
    }if ($('#cb_documento').val() == ''){
        msj_error += " Tipo de documento.";
    }if ($('#txt_folio').val().length == 0){
        msj_error += " N° de folios.";
    }if ($('#cb_remite').val() == ''){
        msj_error += " Remitente.";
    }if (uo_remite == undefined && $('#txt_nrodoc').val() == '' && $('#cb_documento').val() != '110'){//** AGREGAR TIPO DE DOCUMENTO DENUNCIA
        msj_error += " N° documento.";
        uo_remite = "";
    }if ($('#cb_destino').val() == ''){
        msj_error += " Destinatario.";
    }if ($('#txt_asunto').val().length == 0){
        msj_error += " Asunto.";
    }
    
    if(msj_error == ""){   
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_expedientes_pad_guardar/", 
            data:     "id="+id+
                      "&abogado="+abogado+
                      "&fecharecep="+fecharecep+
                      "&fecpresc_iniPAD="+fecpresc_iniPAD+
                      "&etapa="+etapa+"&denunciante="+denunciante+
                      "&dependencia="+dependencia+
                      "&documento="+documento+
                      "&nrodoc="+nrodoc+
                      "&fechadoc="+fechadoc+
                      "&folio="+folio+ 
                      "&plazo="+plazo+ 
                      "&remite="+remite+ 
                      "&destino="+destino+
                      "&asunto="+asunto+
                      "&observacion="+observacion+
                      "&iddoc="+iddoc+
                      "&instructor="+instructor+ 
                      "&sancionador="+sancionador+
                      "&fecnotif_iniPAD="+fecnotif_iniPAD+ 
                      "&fecpres_PAD="+fecpres_PAD+
                      "&tipo_proced="+tipo_proced+
                      "&estado="+estado+
                      "&uo_instructor="+uo_instructor+
                      "&uo_sancionador="+uo_sancionador+ 
                      "&uo_remite="+uo_remite, 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){ 
                arrayobj = jQuery.parseJSON(requestData);
                
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                var id_doc = arrayobj[0][2];
                var ndoc = arrayobj[0][3];
                var fec_presc_ipad = arrayobj[0][4];
                
                $('#txt_nroexp').val(id);
                $('#div_mensaje_ajax').html(msj);
                $('#hd_iddoc').val(id_doc);
                $('#txt_fecpresc_iniPAD').val(fec_presc_ipad);
                $('#txt_nrodoc').val(ndoc);
                
                /*inicio subir archivos */
                var exp = id.split('-')[0];
                var per = id.split('-')[1];
//                
                var fdata = new FormData();
                var file;
                fdata.append("anio",per);
                fdata.append("exp",exp);
                fdata.append("id_doc",id_doc);
                for (var i = 0 ; i < input.files.length ; i++) {
                    file = input.files[i];
                    fdata.append(per+"|"+exp+"|"+id_doc+"|"+i, file);
                }    
                var xhr = new XMLHttpRequest();
                xhr.open("POST",path + "pad/uploadfile/", true);
                xhr.addEventListener("load", function (e) {
                    pad_mant_adjuntos_cargar(id_doc);//Carga lista de adjuntos                      
                });
                xhr.send(fdata);
                if (input.files.length > 0){
                    $('#div_mant_adjunto_tbl').show();
                    $('#file-pad').fileinput('clear');
                }
                /*fin subir archivos */ 
               
               pad_mant_expedientes_pad_tbl();
               mant_expedientes_pad_docs_tbl(id);
               $.alert('<h6>' + msj + '</h6>');
            },			
            error: function(requestData, strError, strTipoError){											
//                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
                $.alert('<h6>' + strTipoError +": " + strError + '</h6>');
            }
        }); 
    }else{
         $.alert('<h6>Ingrese: '+ msj_error + '</h6>');
    } 
}
//FIN EXPEDIENTE PAD GUARDAR
//
//INICIO EXPEDIENTE PAD NUEVO GUARDAR
function pad_mant_expedientes_pad_nuevo_guardar(){
    var id = $('#txt_nroexp').val();
    var fecharecep = $('#txt_fecharecep').val();
    var fecpresc_iniPAD = $('#txt_fecpresc_iniPAD').val();
    var etapa = $('#cb_etapa').val();
    var denunciante = $('#cb_denunciante').val();
    var dependencia = $('#cb_dependencia').val();
    var abogado = $('#cb_abogado').val();
    var documento = $('#cb_documento').val();
    var nrodoc = $('#txt_nrodoc').val();    
    var fechadoc = $('#txt_fechadoc').val();    
    var folio = $('#txt_folio').val();    
    var plazo = $('#txt_plazo').val();    
    var remite = $('#cb_remite').val();    
    var destino = $('#cb_destino').val();    
    var asunto = $('#txt_asunto').val(); 
    asunto = asunto.replace("–","");
    asunto = asunto.replace("\“","'");
    asunto = asunto.replace("\”","'"); 
    var observacion = $('#txt_observacion').val();  
    observacion = observacion.replace("–","");
    observacion = observacion.replace("\“","'");
    observacion = observacion.replace("\”","'"); 
    var input = document.querySelector('input[type="file"]');
    var iddoc = $('#hd_iddoc').val(); 
    var anio = $('#cb_anio').val(); 
    var tiempo = $('#cb_tiempo').val(); 
    var instructor = $('#cb_instructor').val();   
    var investigado = $('#cb_investigado').val();  
    var cargo = $('#cb_cargo').val(); 
    if(instructor == undefined){
        instructor = '';
    }
    
    var sancionador = $('#cb_sancionador').val();   
    if(sancionador == undefined){
        sancionador = '';
    }
    
    var fecnotif_iniPAD = $('#txt_fecnotif_iniPAD').val(); 
    if(fecnotif_iniPAD == undefined){
        fecnotif_iniPAD = '';
    }
    
    var fecpres_PAD = $('#txt_fecpres_PAD').val(); 
    if(fecpres_PAD == undefined){
        fecpres_PAD = '';
    }
   
//    Mensaje ingreso información 
     var msj_error = "";
     
    if ($('#cb_denunciante').val() == ''){
        msj_error += " Denunciante.";
    }if ($('#cb_dependencia').val() == ''){
        msj_error += " Dependencia.";
    }if ($('#cb_abogado').val() == ''){
        msj_error += " Abogado.";
    }if ($('#cb_documento').val() == ''){
        msj_error += " Tipo de documento.";
    }    
    
//  se valida y solicita N° documento si es un reporte
    if ($('#cb_documento').val() == '118'){
        if ($('#txt_nrodoc').val().length == 0){
            msj_error += " N° de documento.";
        }
    }    
    if ($('#txt_folio').val().length == 0){
        msj_error += " N° de folios.";
    }if ($('#cb_remite').val() == ''){
        msj_error += " Remitente.";
    }if ($('#cb_destino').val() == ''){
        msj_error += " Destinatario.";
    }
    if ($('#txt_asunto').val().length == 0){
        msj_error += " Asunto.";
    }
    
    if(msj_error == ""){   
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_expedientes_pad_nuevo_guardar/", 
            data:     "id="+id+
                      "&abogado="+abogado+
                      "&fecharecep="+fecharecep+
                      "&fecpresc_iniPAD="+fecpresc_iniPAD+
                      "&etapa="+etapa+"&denunciante="+denunciante+
                      "&dependencia="+dependencia+
                      "&documento="+documento+
                      "&nrodoc="+nrodoc+
                      "&fechadoc="+fechadoc+
                      "&folio="+folio+ 
                      "&plazo="+plazo+ 
                      "&remite="+remite+ 
                      "&destino="+destino+
                      "&asunto="+asunto+
                      "&observacion="+observacion+
                      "&iddoc="+iddoc+
                      "&instructor="+instructor+ 
                      "&sancionador="+sancionador+
                      "&fecnotif_iniPAD="+fecnotif_iniPAD+ 
                      "&fecpres_PAD="+fecpres_PAD+
                      "&anio="+anio+
                      "&tiempo="+tiempo+ 
                      "&investigado="+investigado+
                      "&cargo="+cargo, 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){ 
                arrayobj = jQuery.parseJSON(requestData);
                
                var id  = arrayobj[0][0];
                var msj = arrayobj[0][1];
                var id_doc = arrayobj[0][2];
                var ndoc = arrayobj[0][3];
                var fec_presc_ipad = arrayobj[0][4];
                
                $('#txt_nroexp').val(id);
                $('#div_mensaje_ajax').html(msj);
                $('#hd_iddoc').val(id_doc);
                $('#txt_fecpresc_iniPAD').val(fec_presc_ipad);
                $('#txt_nrodoc').val(ndoc);
                
                /*inicio subir archivos */
                var exp = id.split('-')[0];
                var per = id.split('-')[1];
//                
                var fdata = new FormData();
                var file;
                fdata.append("anio",per);
                fdata.append("exp",exp);
                fdata.append("id_doc",id_doc);
                for (var i = 0 ; i < input.files.length ; i++) {
                    file = input.files[i];
                    fdata.append(per+"|"+exp+"|"+id_doc+"|"+i, file);
                }    
                var xhr = new XMLHttpRequest();
                xhr.open("POST",path + "pad/uploadfile/", true);
                xhr.addEventListener("load", function (e) {
                    pad_mant_adjuntos_cargar(id_doc);//Carga lista de adjuntos                      
                });
                xhr.send(fdata);
                if (input.files.length > 0){
                    $('#div_mant_adjunto_tbl').show();
                    $('#file-pad').fileinput('clear');
                }
                /*fin subir archivos */ 
               
               pad_mant_expedientes_pad_tbl();
               mant_expedientes_pad_docs_tbl(id);
               $.alert('<h6>' + msj + '</h6>');
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        }); 
    }else{
         $.alert('<h6>Ingrese: '+ msj_error + '</h6>');
    } 
}
//FIN EXPEDIENTE PAD NUEVO GUARDAR
//
//INICIO LISTA ADJUNTOS
function pad_mant_adjuntos_cargar(id_doc){ 
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_adjunto_cargar/",
            data:     "id_doc="+id_doc,
            beforeSend: function(data){
                $('#div_mant_adjunto_tbl').html("Cargando...");
            },
            success: function(requestData){
                $('#div_mant_adjunto_tbl').html(requestData);
//                $('#div_mensaje_ajax').removeClass('alert alert-danger');
//                $('#div_mensaje_ajax').removeClass('alert alert-success'); 
            },
            error: function(requestData, strError, strTipoError){
                $('#div_mant_adjunto_tbl').html("Error " + strTipoError +": " + strError);
//                $('#div_mant_adjunto_tbl_1').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN LISTA ADJUNTOS DE UN EXPEDIENTE
//
//INICIO LISTA DOCUMENTOS POR EXPEDIENTE TABLA   
function mant_expedientes_pad_docs_tbl(nroexp){   
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "pad/mant_expedientes_pad_docs_tbl/", 
        data:     "nroexp="+nroexp ,
        beforeSend: function(data){
                $('#div_mant_expedientes_pad_docs_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_expedientes_pad_docs_tbl').html(requestData);
        },
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_expedientes_pad_docs_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN LISTA DOCUMENTOS POR EXPEDIENTE TABLA   
//
//INICIO DETALLE DE DOCUMENTOS
function pad_mant_doc_detalle(id){
   $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_doc_detalle/", 
            data:     "id_doc="+id,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);                
                var id_doc  = arrayobj[0][0];
                var exp  = arrayobj[0][1];
                var fec_recep  = arrayobj[0][12];
                var fecpresc_iniPAD  = arrayobj[0][13];
                var fecnotif_iniPAD  = arrayobj[0][14];
                var fecpres_PAD  = arrayobj[0][15];
                var etapa  = arrayobj[0][16];
                var denunciante  = arrayobj[0][17];
                var dependencia  = arrayobj[0][18];
                var abogado  = arrayobj[0][19];
                var instructor  = arrayobj[0][20];
                var sancionador  = arrayobj[0][21];
                var documento  = arrayobj[0][2];
                var n_doc  = arrayobj[0][3];
                var fec_doc  = arrayobj[0][5];
                var folio_doc  = arrayobj[0][4];
                var plazo  = arrayobj[0][6];
                var remite  = arrayobj[0][7];
                var destino  = arrayobj[0][8];
                var asu_doc  = arrayobj[0][9];
                var obs_doc  = arrayobj[0][10];    
                var investigado  = arrayobj[0][22];
                var tipo_procedimiento  = arrayobj[0][23];
                var uo_organo_instr  = arrayobj[0][24];
                var uo_organo_sanc  = arrayobj[0][25];
                
                var msj = arrayobj[0][0];

                $('#hd_iddoc').val(id);
                $('#txt_nroexp').val(exp);
                $('#txt_fecharecep').val(fec_recep);
                $('#txt_fecpresc_iniPAD').val(fecpresc_iniPAD);
                $('#txt_fecnotif_iniPAD').val(fecnotif_iniPAD);
                $('#txt_fecpres_PAD').val(fecpres_PAD);
                $('#cb_etapa').val(etapa);
                $("#cb_etapa").change();
                $('#cb_denunciante').val(denunciante);
                $("#cb_denunciante").change();
                $('#cb_dependencia').val(dependencia);
                $("#cb_dependencia").change();
                $('#cb_abogado').val(abogado);
                $("#cb_abogado").change();
                $('#cb_instructor').val(instructor+'_'+uo_organo_instr);
                $("#cb_instructor").change();
                $('#cb_sancionador').val(sancionador+'_'+uo_organo_sanc);
                $("#cb_sancionador").change();
                $('#cb_documento').val(documento);
                $("#cb_documento").change();
                $('#txt_nrodoc').val(n_doc);
                $('#txt_fechadoc').val(fec_doc);
                $('#txt_folio').val(folio_doc);
                $('#txt_plazo').val(plazo);
                $('#cb_remite').val(remite);
                $("#cb_remite").change();
                $('#cb_destino').val(destino);
                $("#cb_destino").change();
                $('#txt_asunto').val(asu_doc);
                $('#txt_observacion').val(obs_doc); 
                if (investigado == '') {
                    investigado = 'No determinado(s)';
                }
                $('#txt_investigados').val(investigado); 
                $('#cb_tipo_proced').val(tipo_procedimiento); 
                $('#cb_tipo_proced').change(); 
                
//              DESHABILITA TABS
                $('#lista_doc').attr('class','tab-pane');
                $('#div_lista_doc').attr('class','tab-pane');
                
                $('#expediente').attr('class','tab-pane active');
                $('#doc').attr('class','tab-pane active');                
                $('#doc').attr('aria-expanded','true');        
               
                $('#div_mensaje_ajax').hide();                
                $('#div_mensaje_ajax').html(msj);
                
                pad_mant_adjuntos_cargar(id_doc);   
                
                $('#div_mant_adjunto_tbl').show();
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN DETALLE DE DOCUMENTOS
//
//INICIO NUEVO INVESTIGADO POPUP
function pad_mant_investigado_popup(id){
    
    var url = encodeURI(path + "pad/mant_investigado_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 800
       ,"height" : 750 
    });
}
//FIN INVESTIGADO POPUP
//
//INICIO NUEVO CARGAR COMBO FALTAS
function pad_mant_falta_cargar_cbo(){
    var id_normajur = $('#cb_norma_jur').val();
            
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_falta_cargar_cbo/", 
            data:     "id_normajur="+id_normajur,	 	 
            beforeSend: function(data){ 	 	
                $('#cb_falta').html("<option>CARGANDO...</option>");
                $('#cb_falta').selectpicker('refresh');
            },
            success: function(requestData){
                $('#cb_falta').html(requestData);
                $('#cb_falta').selectpicker('refresh');
            },			
            error: function(requestData, strError, strTipoError){
                $('#cb_falta').html("<option>Error " + strTipoError +": " + strError+"</option>");
                $('#cb_falta').selectpicker('refresh');
            }
        });  
}
//FIN CARGAR COMBO FALTAS
//
//INICIO GUARDAR INVESTIGADO
function pad_mant_investigado_guardar(){
    var idexp = $('#txt_nroexp').val();
    var investigado = $('#cb_investigado').val();
    var cargo = $('#cb_cargo').val();
    var observacion = $('#txt_observacion').val();    
    var faltas = $('#cb_faltasel').val();
    
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_investigado_guardar/", 
            data:     "idexp="+idexp+
                      "&investigado="+investigado+
                      "&cargo="+cargo+
                      "&observacion="+observacion+
                      "&faltas="+faltas,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){                
                arrayobj = jQuery.parseJSON(requestData);
                var msj  = arrayobj[0][0];
                
                pad_mant_expedientes_pad_tbl();
                $.alert('<h6>' + msj + '</h6>');
            },		
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN GUARDAR NUEVO INVESTIGADO
//
//INICIO MODIFICAR INVESTIGADO
function pad_mant_investigado_modificar_guardar(){
    var idexp = $('#txt_nroexp').val();
    var investigado = $('#cb_investigado').val();
    var cargo = $('#cb_cargo').val();
    var observacion = $('#txt_observacion').val();
    var faltas = $('#cb_faltasel').val();
    var sancion = $('#cb_sancion').val();
    var dias = $('#txt_dias').val();
    var medcautelar = $('#cb_medida_caut').val();
    var recurso = $('#cb_recurso').val();
    
    var msj_error = "";
    if ($('#cb_sancion').val() == '2' && $('#txt_dias').val() == ''){
        msj_error = 'Ingrese días.';
    }
    if(msj_error == ""){  
        $.ajax({
                dataType: "html",
                type:     "GET",
                url:      path + "pad/mant_investigado_modificar_guardar/",
                data:     "idexp="+idexp+
                          "&investigado="+investigado+
                          "&cargo="+cargo+
                          "&observacion="+observacion+
                          "&faltas="+faltas+	 	 
                          "&sancion="+sancion+	 	 
                          "&dias="+dias+	 
                          "&medcautelar="+medcautelar+	 	 
                          "&recurso="+recurso,	 	 
                beforeSend: function(data){
                    $('#div_mensaje_ajax').html("Cargando...");
                },
                success: function(requestData){
                    arrayobj = jQuery.parseJSON(requestData);
                    var msj  = arrayobj[0][0];

                    $.alert('<h6>' + msj + '</h6>');
                },
                error: function(requestData, strError, strTipoError){
                    $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
                }
            });
    }else{
         $.alert('<h6>Ingrese: '+ msj_error + '</h6>');
    } 
}
//FIN MODIFICAR INVESTIGADO
//
//INICIO CONSULTA INVESTIGADO POPUP
function pad_mant_investigado_consulta_popup(id){
    
    var url = encodeURI(path + "pad/mant_investigado_consulta_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 1100
       ,"height" : 850 
    });
}
//FIN CONSULTA INVESTIGADO POPUP
//
//INICIO LISTA INVESTIGADOS POR EXPEDIENTE TABLA   
function pad_mant_investigados_tbl(nroexp){   
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "pad/mant_investigados_tbl/", 
        data:     "nroexp="+nroexp ,
        beforeSend: function(data){
                $('#div_mant_investigados_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_investigados_tbl').html(requestData);
        },
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_investigados_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN LISTA INVESTIGADOS POR EXPEDIENTE TABLA  
// 
//INICIO DETALLE DE INVESTIGADO
function pad_mant_investigado_detalle(id){
   $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_investigado_detalle/", 
            data:     "investigado="+id,	 	 
            beforeSend: function(data){ 	 	
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);                
                var id_inv  = arrayobj[0][0];
                var investigado  = arrayobj[0][1];
                var cargo  = arrayobj[0][2];
                var idcargo  = arrayobj[0][3];
                var observacion  = arrayobj[0][4];
                var falta  = arrayobj[0][5];
                var nroexp  = arrayobj[0][6];
                var sancion  = arrayobj[0][7];
                var medidacaut  = arrayobj[0][8];
                var recurso  = arrayobj[0][9];
                
                var msj  = arrayobj[0][0];    
                $('#hd_idinv').val(id);
                $('#txt_investigado').val(investigado);
                $('#txt_cargo').val(cargo);
                $('#txt_observacion').val(observacion);                    
                $('#cb_falta').selectpicker('val', falta.split(","));
                $('#cb_falta').selectpicker('render');
                jQuery('#cb_falta').trigger("change");
                $('#cb_sancion').val(sancion);
                $("#cb_sancion").change();
                $('#cb_medida_caut').val(medidacaut);
                $("#cb_medida_caut").change();
                $('#cb_recurso').val(recurso);
                $("#cb_recurso").change(); 
                
                 $('#cb_investigado').val(id);
                 $('#cb_cargo').val(idcargo);
                
//              DESHABILITA TABS
                $('#lista_doc').attr('class','tab-pane');
                $('#div_lista_doc').attr('class','tab-pane');
                
                $('#expediente').attr('class','tab-pane active');
                $('#doc').attr('class','tab-pane active');                
                $('#doc').attr('aria-expanded','true');        
               
                $('#div_mensaje_ajax').hide();                
                $('#div_mensaje_ajax').html(msj);
                
//                pad_mant_investigados_tbl(nroexp);   
                
               
            },			
            error: function(requestData, strError, strTipoError){											
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN DETALLE DE INVESTIGADO
//
//INICIO ASIGNAR ABOGADO POPUP
function pad_mant_asigna_abogado_popup(id){
    
    var url = encodeURI(path + "pad/mant_asigna_abogado_popup/?array_expediente="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 600
       ,"height" : 600 
    });
}
//FIN INVESTIGADO POPUP
//
//INICIO LISTA EXPEDIENTES PARA ASIGNAR ABOGADO POPUP
function pad_lista_exp_asigna_abogado_tmp(chbx){
    var valor = $(chbx).val();
    var ch    = $(chbx).is(':checked');
 
    if(ch){
        var tmp = $('#hd_tmp_exp').val();
        var n = tmp.indexOf(valor);
        $('#hd_tmp_exp').val( tmp  + valor + ",");
    }else{
      tmp = $('#hd_tmp_exp').val();
      tmp = tmp.replace(valor+",","");
      $('#hd_tmp_exp').val(tmp);
    }
    
}
//LISTA EXPEDIENTES PARA ASIGNAR ABOGADO POPUP
//
//INICIO MODIFICAR ASIGNAR ABOGADO GUARDAR
function pad_mant_asigna_abogado_modificar_guardar(){
    var cad_id_exp = $('#hd_cad_id_exp').val();
    var abogado = $('#cb_abogado').val();
       
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_asigna_abogado_modificar_guardar/",
            data:     "cadidexp="+cad_id_exp+
                      "&abogado="+abogado,	 	 
            beforeSend: function(data){
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var msj  = arrayobj[0][0];
                
                pad_mant_expedientes_pad_tbl();
                $.alert('<h6>' + msj + '</h6>');                
            },
            error: function(requestData, strError, strTipoError){
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN MODIFICAR ASIGNAR ABOGADO GUARDAR
//
//INICIO BUSCAR EXPEDIENTE TABLA   
function pad_mant_buscar_tbl(nroexp, anio, fecini, fecfin, fecinipad, fecfinpad){   
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "pad/mant_buscar_tbl/", 
        data:     "nroexp="+nroexp+
                  "&anio="+anio+
                  "&fecini="+fecini+
                  "&fecfin="+fecfin+
                  "&fecinipad="+fecinipad+
                  "&fecfinpad="+fecfinpad,
        beforeSend: function(data){
                $('#div_mant_buscar_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_buscar_tbl').html(requestData);
        },
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_buscar_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN BUSCAR EXPEDIENTE TABLA  
//
//INICIO REPORTE EXPEDIENTE TABLA   
function pad_mant_rep1_tbl(){   
    var etapa = $('#cb_etapa').val();
    var estado = $('#cb_estado').val();
    var abogado = $('#cb_abogado').val();
    
    var fecini = $('#dt_fec_ini').val();
    var fecini_formato = fecini.split('/');
    fecini_formato = fecini_formato[2]+'-'+fecini_formato[1]+'-'+fecini_formato[0];
    
    var fecfin = $('#dt_fec_fin').val();
    var fecfin_formato = fecfin.split('/');
    fecfin_formato = fecfin_formato[2]+'-'+fecfin_formato[1]+'-'+fecfin_formato[0];
          
    var fec_inipad = $('#dt_fec_inipad').val();
    var fec_inipad_formato = fec_inipad.split('/');
    fec_inipad_formato = fec_inipad_formato[2]+'-'+fec_inipad_formato[1]+'-'+fec_inipad_formato[0];
    
    var fec_finpad = $('#dt_fec_finpad').val();
    var fec_finpad_formato = fec_finpad.split('/');
    fec_finpad_formato = fec_finpad_formato[2]+'-'+fec_finpad_formato[1]+'-'+fec_finpad_formato[0]
    
    if (fecini_formato == 'undefined-undefined-'){
        fecini_formato = '';
    }
    if (fecfin_formato == 'undefined-undefined-'){
        fecfin_formato = '';
    }
    if (fec_inipad_formato == 'undefined-undefined-'){
        fec_inipad_formato = '';
    }
    if (fec_finpad_formato == 'undefined-undefined-'){
        fec_finpad_formato = '';
    }
    
    
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "pad/mant_rep1_tbl/", 
        data:     "etapa="+etapa+
                  "&estado="+estado+
                  "&abogado="+abogado+
                  "&fecini="+fecini_formato+
                  "&fecfin="+fecfin_formato+
                  "&fecinipad="+fec_inipad_formato+
                  "&fecfinpad="+fec_finpad_formato,
        beforeSend: function(data){
                $('#div_mant_rep1_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_rep1_tbl').html(requestData);
        },
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_rep1_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN REPORTE EXPEDIENTE TABLA  
//
//INICIO REPORTE GRÁFICO POR ETAPA
function pad_mant_rep_grf1(){
    var f_ini = $('#dt_fec_ini').val();
    var f_ini_formato = f_ini.split('/');
    f_ini_formato = f_ini_formato[2]+'-'+f_ini_formato[1]+'-'+f_ini_formato[0];
    var f_fin = $('#dt_fec_fin').val();
    var f_fin_formato = f_fin.split('/');
    f_fin_formato = f_fin_formato[2]+'-'+f_fin_formato[1]+'-'+f_fin_formato[0];
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_reporte_grafico1/",
            data:     "f_ini="+f_ini_formato+"&f_fin="+f_fin_formato,
            beforeSend: function(data){
//                $('#div_rep_uo_periodo').html("Cargando...");
            },
            success: function(requestData){
                var arrayobj = jQuery.parseJSON(requestData);
                arrayobj.reverse();
                           
                var id_etapa_exp = "";
                var etapa_exp = "";
                var valor = "";
                var dat_cat =new Array();
                var dat_ser =new Array();
                 for(i=0; i<arrayobj.length; i++){
                    id_etapa_exp = arrayobj[i][0];
                    etapa_exp = arrayobj[i][1];
                    valor = arrayobj[i][2];
                    dat_cat.push(etapa_exp);
                    dat_ser.push(parseFloat(valor));
                 }
                
                Highcharts.chart('div_rep_grf', {
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
                $('#div_rep_grf').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN REPORTE GRÁFICO POR ETAPA
//
//INICIO REPORTE GRÁFICO POR ABOGADO
function pad_mant_rep_abogado_grf(){
    var f_ini = $('#dt_fec_ini').val();
    var f_ini_formato = f_ini.split('/');
    f_ini_formato = f_ini_formato[2]+'-'+f_ini_formato[1]+'-'+f_ini_formato[0];
    var f_fin = $('#dt_fec_fin').val();
    var f_fin_formato = f_fin.split('/');
    f_fin_formato = f_fin_formato[2]+'-'+f_fin_formato[1]+'-'+f_fin_formato[0];
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_reporte_abogado_grafico/",
            data:     "f_ini="+f_ini_formato+"&f_fin="+f_fin_formato,
            beforeSend: function(data){
//                $('#div_rep_uo_periodo').html("Cargando...");
            },
            success: function(requestData){
                var arrayobj = jQuery.parseJSON(requestData);
                arrayobj.reverse();
                           
                var id_etapa_exp = "";
                var etapa_exp = "";
                var valor = "";
                var dat_cat =new Array();
                var dat_ser =new Array();
                 for(i=0; i<arrayobj.length; i++){
                    id_etapa_exp = arrayobj[i][0];
                    etapa_exp = arrayobj[i][1];
                    valor = arrayobj[i][2];
                    dat_cat.push(etapa_exp);
                    dat_ser.push(parseFloat(valor));
                 }
                
                Highcharts.chart('div_rep_abogado_grf', {
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
                $('#div_rep_abogado_grf').html("Error " + strTipoError +": " + strError);
            }
        }); 
}
//FIN REPORTE GRÁFICO POR ABOGADO
//
//INICIO LISTA MEDIDA CAUTELAR TABLA
function pad_mant_medida_caut_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "pad/mant_medida_caut_tbl/", 
        data:     "" ,
        beforeSend: function(data){
                $('#div_mant_medida_caut_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_medida_caut_tbl').html(requestData);
        },
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_medida_caut_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN LISTA MEDIDA CAUTELAR TABLA
//
//INICIO MEDIDA CAUTELAR POPUP
function pad_mant_medida_caut_popup(id){
    var url = encodeURI(path + "pad/mant_medida_caut_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 600
       ,"height" : 400 
    });
}
//FIN MEDIDA CAUTELAR POPUP
//
//INICIO MEDIDA CAUTELAR GUARDAR
function pad_mant_medida_caut_guardar(){
    var id = $('#hd_id').val();
    var descripcion = $('#txt_des').val();
    var estado = $('#cb_estado').val();
       
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_medida_caut_guardar/",
            data:     "id="+id+
                      "&descripcion="+descripcion+	 	 
                      "&estado="+estado,	 	 
            beforeSend: function(data){
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj  = arrayobj[0][1];
                
                $('#hd_id').val(id);
                pad_mant_medida_caut_tbl();
                $.alert('<h6>' + msj + '</h6>');                
            },
            error: function(requestData, strError, strTipoError){
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN MEDIDA CAUTELAR GUARDAR//
//
//INICIO LISTA FALTA TABLA
function pad_mant_falta_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "pad/mant_falta_tbl/", 
        data:     "" ,
        beforeSend: function(data){
                $('#div_mant_falta_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_falta_tbl').html(requestData);
        },
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_falta_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN LISTA FALTA TABLA
//
//INICIO FALTA POPUP
function pad_mant_falta_popup(id){
    
    var url = encodeURI(path + "pad/mant_falta_popup/?id="+id);
    
    $.colorbox({
        "href" : url
       ,"width" : 600
       ,"height" : 400 
    });
}
//FIN FALTA POPUP
//
//INICIO FALTA GUARDAR
function pad_mant_falta_guardar(){
    var id = $('#hd_id').val();
    var descripcion = $('#txt_des').val();
    var estado = $('#cb_estado').val();
       
    $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "pad/mant_falta_guardar/",
            data:     "id="+id+
                      "&descripcion="+descripcion+	 	 
                      "&estado="+estado,	 	 
            beforeSend: function(data){
                $('#div_mensaje_ajax').html("Cargando...");
            },
            success: function(requestData){
                arrayobj = jQuery.parseJSON(requestData);
                var id  = arrayobj[0][0];
                var msj  = arrayobj[0][1];
                
                $('#hd_id').val(id);
                pad_mant_falta_tbl();
                $.alert('<h6>' + msj + '</h6>');                
            },
            error: function(requestData, strError, strTipoError){
                $('#div_mensaje_ajax').html("Error " + strTipoError +": " + strError);
            }
        });
}
//FIN FALTA GUARDAR
//
//INICIO LISTA LITERAL TABLA
function pad_mant_literal_tbl(){
        
    $.ajax({
        dataType: "html",
        type:     "GET",
        url:      path + "pad/mant_literal_tbl/", 
        data:     "" ,
        beforeSend: function(data){
                $('#div_mant_literal_tbl').html("Cargando...");
        },
        success: function(requestData){
                $('#div_mant_literal_tbl').html(requestData);
        },
        error: function(requestData, strError, strTipoError){											
                $('#div_mant_literal_tbl').html("Error " + strTipoError +": " + strError);
        }
    });
}
//FIN LISTA LITERAL TABLA
//
//