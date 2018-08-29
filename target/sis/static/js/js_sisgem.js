
function sisgem_buscar_plantilla_js() {
    var ws_tipo_planilla = ws('sismethaweb','pkg_gem.SP_OBT_TIPOPLANILLA', '');
    /*llenando un combo box*/
    ws_contenido_combo("cb_tipo_planilla", ws_tipo_planilla.data, "");
    
    //ws para DZ
    var ws_lista_dz = ws('sismethaweb','pkg_gem.SP_OBT_DZ', '');
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
    
    var fdata = new FormData();
    var file;
    for (var i = 0 ; i < input.files.length ; i++) {
        file = input.files[i];
        fdata.append(i, file);
        var nom_archi = file.name;
        var cod_plaimg = nom_archi.substring(0,nom_archi.lastIndexOf("."));
        var dz = nom_archi.substring(0,2);
        var estacion = nom_archi.substring(8,2);
        var tplanilla = nom_archi.substring(10,8);
        var periodo = nom_archi.substring(11,10);
        var cara = nom_archi.substring(12,11);
        var fecha = nom_archi.substring(20,12);
        
        alert(nom_archi + '-' + cod_plaimg + '-' + dz + '-' + estacion + '-' + tplanilla + '-' + periodo + '-' + cara + '-' + fecha);
        console.log(cod_plaimg); 
        //guardar información
        var ws_data = ws('sismethaweb','pkg_gem.sp_guardar_plaimg', '["'+cod_plaimg+'","'+dz+'","'+estacion+'","'+tplanilla+'","'+periodo+'","'+cara+'","'+fecha+'"]');
                
        
    }    
    var xhr = new XMLHttpRequest();
    xhr.open("POST",path + "sisgem/uploadfile/", true);
    xhr.addEventListener("load", function (e) {
//        pad_mant_adjuntos_cargar(id_doc);//Carga lista de adjuntos                      
    });
    xhr.send(fdata);
    if (input.files.length > 0){
        $('#file-sisgem').fileinput('clear');
    }
    /*fin subir archivos */ 
   
//   var p_codesta = '111042';
//   var p_codbp = '52';
//   var p_fechaplaimg = '2018/08/20';
//   var p_codperiodo = '0';
   
   //$.alert(p_codesta+' - '+ p_codvar +' - '+ p_fecha +' - '+ p_valor) ; 
   //p_codesta, p_codvar , p_fecha , p_valor
//   var ws_data = ws('SISDAD','PKG_KORI.SP_GUARDAR_PRUEBA', '["'+p_codesta+'","'+p_codvar+'","'+p_fecha+'","'+p_valor+'"]');
//   var ws_data = ws('sismethaweb','pkg_gem.sp_guardar_plaimg', '["'+p_codesta+'","'+p_codbp+'","'+p_fechaplaimg+'","'+p_codperiodo+'"]');
//   console.log(ws_data);
//   fn_listar_tabla(); //LISTAR ARCHIVOS CON NOMENCLATURA ERRADA
   //$('#modal_mantenimiento').modal('hide');
   
//****************************************************    
    
}
