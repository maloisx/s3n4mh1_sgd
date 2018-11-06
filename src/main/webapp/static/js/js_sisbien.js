
function sisbien_prueba_js() {
    var ws_temp_act = ws('SISDAD','PKG_KORI.SP_OBT_TEMP_ACT_ESTA', '["112181"]');
    var status = ws_temp_act.request.STATUS;
    
    if(status == 'OK'){
        var data = ws_temp_act.data;
        console.log(data);
        $('#txt_nom_esta').val(data[0].NOM_ESTA);
        
        for(var i = 0 ; i < data.length ; i++){
            var valor = data[i].VALOR;
            $('#div_lista').append(valor + "<br>");            
        }
        /*
        var array_cb = [];
        for(var i = 0 ; i < data.length ; i++){
            
            var array_item = [];
            var fecha = data[i].FECHA;
            var valor = data[i].VALOR;
            array_item.push(fecha);
            array_item.push(valor);            
            array_cb.push(array_item);                     
        }
        console.log(array_cb);
        ws_contenido_combo("cb_prueba", array_cb , "13/08/2018 16:00");
        */
        fn_listar_tabla();             
    }
}

function fn_listar_tabla(){
    var ws_lista_variables = ws('SISDAD','PKG_WS.SP_OBT_LISTA_VARIABLE', '');
        ws_contenido_combo("cb_prueba", ws_lista_variables.data , "");
        
        
        var tbl_cab = [
                        {'sTitle': 'C1' , "sClass" : "text-center"}
                       ,{'sTitle': 'C2' , "sClass" : "text-center"}
                       ,{'sTitle': 'C3'}
                       ,{'sTitle': 'C4'}
                       ,{'sTitle': 'C5'}
                       ,{'sTitle': 'C6'}
                       ,{'sTitle': 'C7'}
                       ,{'sTitle': 'btn'}
                      ];
                      
        var tbl_opc = {
                        responsive: false
                        , bFilter: false
                        , bLengthChange: false
                        , bInfo: true
                        , bPaginate: true
                        , aoColumnDefs : [{ "visible": false, "targets": [4] }]
                        , buttons: []
                    };
        for(var i= 0 ; i<data.length ; i++){
            //data[i].NOM_ESTA = "estacion "+ data[i].NOM_ESTA;
            data[i].btn1 = tbl_ext_btn('glyphicon-edit',"fn_edit_prueba('"+data[i].NOM_VAR+"','"+data[i].COD_VAR+"','"+data[i].FECHA+"','"+data[i].VALOR+"');") ;
            //data[i].btn2 = tbl_ext_btn('glyphicon-save',"fn_guardar_prueba('"+data[i].COD_ESTA+"','"+data[i].COD_VAR+"','"+data[i].FECHA+"','"+data[i].VALOR+"')");
            //data[i].btn3 = tbl_ext_btn('glyphicon-print');
        }                               
                    
        ws_datatable("tabla_prueba", data, tbl_cab  , tbl_opc);  
}

function fn_edit_prueba(p_codesta , p_codvar , p_fecha , p_valor){
    $('#hd_modal_manteniemiento_codesta').val(p_codesta);
    $('#hd_modal_manteniemiento_codvar').val(p_codvar);
    $('#txt_modal_fecha').val(p_fecha);
    $('#txt_modal_valor').val(p_valor);
    $('#modal_mantenimiento').modal();
}

function fn_guardar_prueba(){
    
   var p_codesta = $('#hd_modal_manteniemiento_codesta').val();
   var p_codvar =  $('#hd_modal_manteniemiento_codvar').val();
   var p_fecha =  $('#txt_modal_fecha').val();
   var p_valor =  $('#txt_modal_valor').val();
    
   //$.alert(p_codesta+' - '+ p_codvar +' - '+ p_fecha +' - '+ p_valor) ; 
   //p_codesta, p_codvar , p_fecha , p_valor
   var ws_data = ws('SISDAD','PKG_KORI.SP_GUARDAR_PRUEBA', '["'+p_codesta+'","'+p_codvar+'","'+p_fecha+'","'+p_valor+'"]');
   console.log('guardar');
   fn_listar_tabla();
   $('#modal_mantenimiento').modal('hide');
}

function sisbien_prueba_js_component_ws11() {
//    console.log("token:" + localStorage.token);
    var obj_rpta = ws('sisbien.fn_colorbien_obtener', '[""]');
    console.log(obj_rpta);


    /*llenando un combo box*/
    ws_contenido_combo("cb_prueba", obj_rpta.data, "");
    ws_contenido_combo("cb_prueba_1", obj_rpta.data, "2");
//
    $('#txt_prueba_db').val(obj_rpta.data[0].des_colorbien);

    $('#cb_prueba_1').change(function () {
        var valor_seleccionado = $(this).val();
        console.log(valor_seleccionado);
        $('#txt_prueba_combo').val(valor_seleccionado);
    });

    $('#div_prueba').html('');
    for (var item = 0; item < obj_rpta.data.length; item++) {
        $('#div_prueba').append(obj_rpta.data[item].id_colorbien + '->' + obj_rpta.data[item].des_colorbien + '<br>');
    }

    var tbl_cab = [{'sTitle': 'ID'}, {'sTitle': 'DESC'}];
    ws_datatable("tabla_prueba", obj_rpta.data, tbl_cab);

}


function sisbien_PreRegistro() {
    /*cargar cb con años de ordenes de compra*/
    var anio_act = new Date().getFullYear();
    var obj_anios = ws('SIGAMEF', 'pkg_senamhi.sp_ordencompra_anios', '');
    ws_contenido_combo("cb_AnioOrden", obj_anios.data, new Date().getFullYear());

    $("#btn_buscarOrdenCompra").on("click", function () {
        var anio_selected = $("#cb_AnioOrden").val();
        var Nro_OrdenCompra = $("#txt_NroOrden").val();
        //console.log(Nro_OrdenCompra);
        if (Nro_OrdenCompra == '') {
            $.alert('<h4>Ingrese Nro de orden de compra!</h4>');
        } else {
            var obj_items = ws('SIGAMEF', 'pkg_senamhi.sp_ordencompra_items', '["' + Nro_OrdenCompra + '","' + anio_selected + '"]');
            
            var tbl_cab = [
                {'sTitle': 'GRUPO', 'sClass':'text-center'}, 
                {'sTitle': 'CLASE', 'sClass':'text-center'},
                {'sTitle': 'FAMILIA', 'sClass':'text-center'},
                {'sTitle': 'ITEM'},
                {'sTitle': 'CANT', 'sClass':'text-center'},
                {'sTitle': 'MEDIDA', 'sClass':'text-center'},
                {'sTitle': 'PREC. UNIT.', 'sClass':'text-center'},
                {'sTitle': 'DET.', 'sClass':'text-center'} ,
                {'sTitle': 'DET.', 'sClass':'text-center'} ,
                {'sTitle': 'DET.', 'sClass':'text-center'} 
            ];       
            for(var i= 0 ; i<obj_items.data.length ; i++){
                obj_items.data[i].UNIDAD = obj_items.data[i].UNIDAD + 'xxxxx';
                obj_items.data[i].btn1 = tbl_ext_btn('glyphicon-edit',"$.alert('"+obj_items.data[i].NOMBRE_ITEM+"')") ;
                obj_items.data[i].btn2 = tbl_ext_btn('glyphicon-trash',"$.alert('"+obj_items.data[i].PREC_UNIT+"')");
                obj_items.data[i].btn3 = tbl_ext_btn('glyphicon-print');
            }
            //console.log(obj_items.data);
            ws_datatable("div_tbl_OrdenCompraItems", obj_items.data, tbl_cab);
        }

    });

}

function  sisbien_js_sigamef_bienes(){ 
    
    var d = new Date();
    var yyyy = d.getFullYear();
    
    var obj_data_anios_patri = ws('SIGAMEF','pkg_senamhi.sp_patrimonio_anios', '');
    var obj_data_sedes = ws('SIGAMEF','pkg_senamhi.sp_sedes', '');
    var obj_data_centro_costos = ws('SIGAMEF','pkg_senamhi.sp_centros_costos', '[""]');
    var obj_data_ubic_fisica = ws('SIGAMEF','pkg_senamhi.sp_ubic_fisica', '');
    var obj_data_personas = ws('SIGAMEF','pkg_senamhi.sp_personas', '');
    
    ws_contenido_combo("cb_anio", obj_data_anios_patri.data, yyyy);
    ws_contenido_combo("cb_sede", obj_data_sedes.data, "1_331");
    ws_contenido_combo("cb_centro_costo", obj_data_centro_costos.data, "");
    ws_contenido_combo("cb_ubic_fisica", obj_data_ubic_fisica.data, ""); 
    ws_contenido_combo("cb_usuario_final", obj_data_personas.data, "");
}

function sisbien_js_sigamef_bienes_listar(){
    var anio = $("#cb_anio").val();
    var sede = $("#cb_sede").val();
    var centro_costo = $("#cb_centro_costo").val();
    var ubic_fisica = $("#cb_ubic_fisica").val();
    var usuario_final = $("#cb_usuario_final").val();
    
     var rango_ini = $("#txt_rango_ini").val();
     var rango_fin = $("#txt_rango_fin").val();
    
    //console.log(anio + "\n" + sede + "\n" + centro_costo + "\n" + ubic_fisica + "\n" + usuario_final + "\n" + rango_ini + "\n" + rango_fin  );
    
    var obj_data = ws('SIGAMEF','pkg_senamhi.sp_obt_etiquetas', '["'+rango_ini+'","'+rango_fin+'","'+anio+'","'+sede+'","'+centro_costo+'","'+ubic_fisica+'","'+usuario_final+'"]');
    //console.log(obj_data);
    
    var opciones_tbl = {
            responsive: false
            , bLengthChange: false
            , bInfo: false
            , bFilter : true
            , bAutoWidth : true
            , bPaginate: true
            //, buttons: []
            , buttons: [{extend: 'excel', text: 'Exportar a Excel', className: 'btn btn-info btn-sm'}]
        };
    
    var tbl_cab = [
                {'sTitle': 'CODIGO', 'sClass':'text-center'}, 
                {'sTitle': 'DESCRIPCION', 'sClass':'text-center'},
                {'sTitle': 'FEC_COMPRA', 'sClass':'text-center'},
                {'sTitle': 'NRO_ORDEN'},
                {'sTitle': 'SERIE', 'sClass':'text-center'},
                {'sTitle': 'MARCA', 'sClass':'text-center'},
                {'sTitle': 'MODELO', 'sClass':'text-center'},
                {'sTitle': 'SEDE', 'sClass':'text-center'} ,
                {'sTitle': 'CENTRO COSTOS', 'sClass':'text-center'} ,
                {'sTitle': 'ABREV. CENTRO COSTOS', 'sClass':'text-center'} ,
                {'sTitle': 'UBIC. FISICA', 'sClass':'text-center'},                
                {'sTitle': 'AP. PAT. EMP.'},
                {'sTitle': 'AP. MAT. EMP.'},
                {'sTitle': 'NOMBRE EMP.'},
                {'sTitle': '-'}
                   
            ];       
            for(var i= 0 ; i<obj_data.data.length ; i++){
//                obj_items.data[i].UNIDAD = obj_items.data[i].UNIDAD + 'xxxxx';
                obj_data.data[i].btn1 = tbl_ext_btn('glyphicon-print',"window.open('"+path+"/sisbien/sigamef_bienes_etiquetas?r=sisbien_sigamef_bienes_etiquetas&p=p_cod_ini$"+obj_data.data[i].CODIGO+"|p_cod_fin$"+obj_data.data[i].CODIGO+"|p_anio$"+anio+"|p_cod_sede$|p_cod_centro_costo$|p_cod_ubic_fisica$|p_cod_emp_final$','_blank')") ;
//                obj_items.data[i].btn2 = tbl_ext_btn('glyphicon-trash',"$.alert('"+obj_items.data[i].PREC_UNIT+"')");
//                obj_items.data[i].btn3 = tbl_ext_btn('glyphicon-print');
            }
            ws_datatable("sisbien_js_sigamef_bienes_tbl", obj_data.data, tbl_cab);
    
}

function sisbien_js_sigamef_bienes_rpt_etiquetas(){
    var anio = $("#cb_anio").val();
    var sede = $("#cb_sede").val();
    var centro_costo = $("#cb_centro_costo").val();
    var ubic_fisica = $("#cb_ubic_fisica").val();
    var usuario_final = $("#cb_usuario_final").val();
    
     var rango_ini = $("#txt_rango_ini").val();
     var rango_fin = $("#txt_rango_fin").val();
     
     window.open(path+'/sisbien/sigamef_bienes_etiquetas?r=sisbien_sigamef_bienes_etiquetas&p=p_cod_ini$'+rango_ini+'|p_cod_fin$'+rango_fin+'|p_anio$'+anio+'|p_cod_sede$'+sede+'|p_cod_centro_costo$'+centro_costo+'|p_cod_ubic_fisica$'+ubic_fisica+'|p_cod_emp_final$'+usuario_final+'','_blank')
 
}
        