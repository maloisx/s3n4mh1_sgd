

function sisper_listadopersonal_js_component_ws() {
    console.log("token:" + localStorage.token);
    var obj_rpta = ws('SISPER', 'pkg_ws.sp_obt_listapersonal', '[""]');
    console.log(obj_rpta);
       var tbl_cab = [
                      {'sTitle': 'COD_EMP'}
                    , {'sTitle': 'APPAT'}
                    , {'sTitle': 'APMAT'}
                    , {'sTitle': 'NOMBRE'}
                    , {'sTitle': 'COND_LAB'}
                    , {'sTitle': 'NIVEL'}
                    , {'sTitle': 'DRE'}
                    , {'sTitle': 'DEPEN'}
                    , {'sTitle': 'UNID'}
                    , {'sTitle': 'TIP_DOC'}
                    , {'sTitle': 'DNI'}
                    , {'sTitle': 'EST_CIVIL'}
                    , {'sTitle': 'SEXO'}
                    , {'sTitle': 'VIVIENDA'}
                    , {'sTitle': 'FECHA_NAC'}
                    , {'sTitle': 'GRAD_ACADEMICO'}
                    , {'sTitle': 'GRAD_INSTRUCCION'}
                    , {'sTitle': 'PAIS_NAC'}
                    , {'sTitle': 'DEPA_NAC'}
                    , {'sTitle': 'PROV_NAC'}
                    , {'sTitle': 'DIST_NAC'}
                    , {'sTitle': 'PAIS_DOM'}
                    , {'sTitle': 'DEP_DOM'}
                    , {'sTitle': 'PROV_DOM'}
                    , {'sTitle': 'DIST_DOM'}
                    , {'sTitle': 'DIRECCION'}
                    , {'sTitle': 'INGRESO_AL_ESTADO'}
                    , {'sTitle': 'REG_LABORAL'}
                    , {'sTitle': 'AFP'}
                    , {'sTitle': 'FECHA_AFP'}
                    , {'sTitle': 'GRUPO_OCUPACIONAL'}
                    , {'sTitle': 'ENTIDAD_ESTUDIOS'}
                    , {'sTitle': 'ESPECIALIDAD_ESTUDIOS'}
                    , {'sTitle': 'COLEGIATURA'}
                    , {'sTitle': 'CARGO'}
                    , {'sTitle': 'CARGO_CAP'}
                    , {'sTitle': 'DRE_CAP'}
                    , {'sTitle': 'DEPEN_CAP'}
                    , {'sTitle': 'UNID_CAP'}
                    , {'sTitle': 'NIVEL_CAP'}
                    , {'sTitle': 'TIP_PLAZA'}
                    , {'sTitle': 'FECHA_ING_SENAMHI'}
                    , {'sTitle': 'LEY_PENSIONES'}
                    , {'sTitle': 'CSP'}
                    , {'sTitle': 'ESTADO_EMP'}
                    , {'sTitle': 'FECHA_ESTADO_EMP'}
                    , {'sTitle': 'COD_ESSALUD'}
                    , {'sTitle': 'GRUP_SANGRE'}
                    , {'sTitle': 'TELEFONO_EMP'}
                    , {'sTitle': 'ALERGIAS'}
                    , {'sTitle': 'POLICLINICO'}
                    , {'sTitle': 'CLINICA'}
                    , {'sTitle': 'NOMBRE_FAMILIAR'}
                    , {'sTitle': 'TELEFONO_FAMILIAR'}
                    , {'sTitle': 'CANT_HIJOS'}
                    , {'sTitle': 'HORA_ENTRADA'}
                    , {'sTitle': 'HORA_SALIDA'}
                    , {'sTitle': 'EMP_MARCACION'}
                    , {'sTitle': 'TARJETA_MARCACION'}
                    , {'sTitle': 'fecha_cambio_reg_lab'}
                    ];  
                    
     var opciones = {
                responsive: true
                //, bLengthChange: true
                , bInfo: true
                , bPaginate: true
                , buttons: [{extend: 'excel', text: 'Exportar a Excel', className: 'btn btn-info btn-sm'},{extend: 'csv', text: 'Exportar a CSV', className: 'btn btn-info btn-sm'}]
            };
       ws_datatable("tabla_listadopersonal", obj_rpta.data, tbl_cab , opciones);

    
//    ws_contenido_combo("cb_prueba", obj_rpta.data, "");
//    ws_contenido_combo("cb_prueba_1", obj_rpta.data, "2");
//
//    $('#txt_prueba_db').val(obj_rpta.data[0].des_colorbien);
//
//    $('#cb_prueba_1').change(function () {
//        var valor_seleccionado = $(this).val();
//        console.log(valor_seleccionado);
//        $('#txt_prueba_combo').val(valor_seleccionado);
//    });
//
//    $('#div_prueba').html('');
//    for (var item = 0; item < obj_rpta.data.length; item++) {
//        $('#div_prueba').append(obj_rpta.data[item].id_colorbien + '->' + obj_rpta.data[item].des_colorbien + '<br>');
//    }
//
//    var tbl_cab = [{'sTitle': 'ID'}, {'sTitle': 'DESC'}];
//    ws_datatable("tabla_prueba", obj_rpta.data, tbl_cab);

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
