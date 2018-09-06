
function sisgem_buscar_plantilla_js() {
    var ws_tipo_planilla = ws('sismethaweb','pkg_gem.SP_OBT_TIPOPLANILLA', '');
    /*llenando un combo box*/
    ws_contenido_combo("cb_tipo_planilla", ws_tipo_planilla.data, "");
    
    //var coddz = '13';
    //ws para DZ
    var ws_lista_dz = ws('sismethaweb','pkg_gem.SP_OBT_DZ', '[""]');
    //console(ws_lista_dz);
    /*llenando un combo box*/
    ws_contenido_combo("cb_dz", ws_lista_dz.data, "");
    
    //var ws_lista_estacion = ws('sismethaweb','pkg_gem.sp_obt_esta', '');
//    console.log(ws_lista_estacion);
     /*llenando un combo box*/    
    //ws_contenido_combo("cb_estacion", ws_lista_estacion.data, "");    
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
    console.log(input);
    
    
    var file;
    var temporal = '';
    var cta_ok = 0;
    var cta_tmp = 0;
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
                
        var ws_dz_val = ws('sismethaweb','pkg_gem.SP_OBT_DZ', '["'+dz+'"]');        
        var ws_esta_val = ws('sismethaweb','pkg_gem.SP_OBT_ESTA_VAL', '["'+estacion+'"]');        
        var ws_tplan_val = ws('sismethaweb','pkg_gem.SP_OBT_TIPOPLANILLA_VAL', '["'+tplanilla+'"]');        
        var ws_periodo_val = ws('sismethaweb','pkg_gem.SP_OBT_PERIODO_VAL', '["'+periodo+'"]');
               
        var length_dz = ws_dz_val.data.length;
        var length_esta = ws_esta_val.data.length;
        var length_tplan = ws_tplan_val.data.length;
        var length_per = ws_periodo_val.data.length;
        
        if(length_dz > 0 && length_esta > 0 && length_tplan > 0 && length_per > 0){
            var fdata = new FormData();    
            fdata.append('file', file);
            /*Inicio subir archivos */
            var xhr = new XMLHttpRequest();
            
            xhr.open("POST",path + "sisgem/uploadfile/", true);
            xhr.addEventListener("load", function (e) {
//                console.log(xhr.responseText); 
            //        pad_mant_adjuntos_cargar(id_doc);//Carga lista de adjuntos                      
            });
            xhr.send(fdata);
            /*fin subir archivos */
            //guardar información
            var ws_data = ws('sismethaweb','pkg_gem.sp_guardar_plaimg', '["'+cod_plaimg+'","'+dz+'","'+estacion+'","'+tplanilla+'","'+periodo+'","'+cara+'","'+fecha+'"]');            
            cta_ok +=1;
        }else{
            temporal +=  nom_archi + ',';  
            var fdata = new FormData();
            fdata.append(0, file);
            /*Inicio subir archivos con nomenclatura errada */                        
            var xhr = new XMLHttpRequest();
            xhr.open("POST",path + "sisgem/uploadfiletmp/", true);
            xhr.addEventListener("load", function (e) {
            //        pad_mant_adjuntos_cargar(id_doc);//Carga lista de adjuntos                      
            });
            xhr.send(fdata);            
            /*fin subir archivos con nomenclatura errada */
            //guardar información
            var ws_datatmp = ws('sismethaweb','pkg_gem.sp_guardar_plaimgtmp', '["'+cod_plaimg+'"]');
            cta_tmp +=1;
        }
    }
    if (input.files.length > 0){
        $('#file-sisgem').fileinput('clear');
        $('#div_msg').html("Registro exitoso: <br>" + cta_ok + " Planilla(s) guardada(s).<br>" + cta_tmp + " Planilla(s) mal nombrada(s).");
    }
    
}
