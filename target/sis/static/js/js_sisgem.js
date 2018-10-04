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
        var ws_lista_estacion = ws('sismethaweb','pkg_gem.sp_obt_esta', '["'+coddz+'"]');
        ws_contenido_combo("cb_estacion", ws_lista_estacion.data, ""); 
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
//INICIO BUSCAR DATA: ccmvd_dinum_c1 - sismethaweb
function sisgem_buscar_data_js() {
//    var p_codesta = '000687' ;//$('#cb_estacion').val();
    var p_codbp = '56';//$('#cb_tipo_planilla').val();
    var p_dz = '04';//$('#cb_tipo_planilla').val();
    console.log('------'+p_codbp);
    console.log('---**-'+p_dz);
//    var dz = $('select[name=cb_dz] option:selected').text();
//    var estacion = $('select[name=cb_estacion] option:selected').text();
//    dz = dz.substr(dz.length-5, dz.length);
//    console.log('*-*-*-*-*--***'+dz);
//    estacion = dz.substr(estacion.length-5,estacion.length);
//    estacion = estacion.substr(estacion.indexOf('-')+2, estacion.length);
//    console.log('------'+estacion);
//    var p_dz = $('#cb_dz').val();    
    var p_codesta = $('#cb_estacion').val();
    
//    var p_codbp = $('#cb_tipo_planilla').val();  
    var ws_data = ws('sismethaweb','pkg_gem.SP_OBT_DATA', '["'+p_codesta+'","'+p_codbp+'"]');
        
    var tbl_cab = [
                        //{'sTitle': 'ESTACION' , "sClass" : "text-center"}
                        {'sTitle': 'AÑO' , "sClass" : "text-center"}
                       ,{'sTitle': 'ENERO', "sClass" : "text-center"}
                       ,{'sTitle': 'FEBRERO', "sClass" : "text-center"}
                       ,{'sTitle': 'MARZO', "sClass" : "text-center"}
                       ,{'sTitle': 'ABRIL', "sClass" : "text-center"}
                       ,{'sTitle': 'MAYO', "sClass" : "text-center"}
                       ,{'sTitle': 'JUNIO', "sClass" : "text-center"}
                       ,{'sTitle': 'JULIO', "sClass" : "text-center"}
                       ,{'sTitle': 'AGOSTO', "sClass" : "text-center"}
                       ,{'sTitle': 'SETIEMBRE', "sClass" : "text-center"}
                       ,{'sTitle': 'OCTUBRE', "sClass" : "text-center"}
                       ,{'sTitle': 'NOVIEMBRE', "sClass" : "text-center"}
                       ,{'sTitle': 'DICIEMBRE', "sClass" : "text-center"}
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
//        console.log(ws_data.data);      
        for(var i= 0 ; i<ws_data.data.length ; i++){
            var anio = ws_data.data[i].V_ANO;            
            ws_data.data[i].M01 = color_tbl_datos_planilla(data[i].M01,anio,'01');            
            ws_data.data[i].M02 = color_tbl_datos_planilla(data[i].M02,anio,'02');
            ws_data.data[i].M03 = color_tbl_datos_planilla(data[i].M03,anio,'03');            
            ws_data.data[i].M04 = color_tbl_datos_planilla(data[i].M04,anio,'04');
            ws_data.data[i].M05 = color_tbl_datos_planilla(data[i].M05,anio,'05');
            ws_data.data[i].M06 = color_tbl_datos_planilla(data[i].M06,anio,'06');
            ws_data.data[i].M07 = color_tbl_datos_planilla(data[i].M07,anio,'07');
            ws_data.data[i].M08 = color_tbl_datos_planilla(data[i].M08,anio,'08');
            ws_data.data[i].M09 = color_tbl_datos_planilla(data[i].M09,anio,'09');
            ws_data.data[i].M10 = color_tbl_datos_planilla(data[i].M10,anio,'10');
            ws_data.data[i].M11 = color_tbl_datos_planilla(data[i].M11,anio,'11');
            ws_data.data[i].M12 = color_tbl_datos_planilla(data[i].M12,anio,'12');
        }        
        ws_datatable("tabla_data",ws_data.data , tbl_cab  , tbl_opc);
}

function color_tbl_datos_planilla(valor, p_anio, p_mes){        
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
            return '<button type="button" class="btn  '+color+' btn-sm"><span class="glyphicon glyphicon-search" onclick="sisgem_visualiza_planilla_js('+p_anio+','+p_mes+')"></span></button>';             
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
function sisgem_visualiza_planilla_js(p_anio, p_mes){    
    var p_dz = $('#cb_dz').val();
    var p_codesta = $('#cb_estacion').val();
    var p_codbp = $('#cb_tipo_planilla').val();
    console.log('///////////////////////////////'+p_codesta);
    console.log('*******'+p_dz);
    console.log('-**-**-'+p_codbp);
    p_mes = ('00'+p_mes).slice(-'00'.length);
//    console.log('-**-**-'+res);
    //busca planillas (digitalizadas) registradas 
    var ws_data = ws('sismethaweb','pkg_gem.SP_OBT_RUTA', '["'+p_dz+'","'+p_codesta+'","'+p_codbp+'","'+p_mes+'","'+p_anio+'"]');
    console.log('/*/*/*/*/'+ws_data.data[0].RUTA);
    
//    $('#txt_modal_estacion').val(p_codesta);
//        
//    busca información de DZ
    var ws_dz = ws('sismethaweb','pkg_gem.SP_BUSCAR_DZ', '["'+p_dz+'"]');
    var dz = ws_dz.data[0].DZ;
    console.log('+-+-+-'+dz);
//    
//    //busca información de estación
//    var ws_esta = ws('sismethaweb','pkg_gem.SP_BUSCAR_ESTA', '["'+p_codesta+'"]');
//    var nom_esta = ws_esta.data[0].NOM_ESTA;
//    console.log('/-/-/-'+nom_esta);
    
    
//    $('#modal_visualiza').modal();
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
                       ,{'sTitle': 'VER'}
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
function sisgem_valida_nombre(p_id, p_archi, p_errado){
    
    var p_id = p_id;
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
                          "&p_errado="+p_errado
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