//INICIO LISTA EXPEDIENTES
function sisproj_expediente_js() {
    
    var p_codesta = '';
    var ws_lista_errada = ws('sismethaweb','pkg_gem.SP_OBT_LISTAERRADA', '["'+p_codesta+'"]');
    
    var tbl_cab = [
                        {'sTitle': 'ID' , "sClass" : "text-center"}
                       ,{'sTitle': 'NOMBRE ' , "sClass" : "text-center"}
                       ,{'sTitle': 'ERRORES'}
                       ,{'sTitle': 'EDITAR'}
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
            data[i].btn1 = tbl_ext_btn('glyphicon-edit',"sisproj_detalle_js('"+ws_lista_errada.data[i].CODPLA+"','"+ws_lista_errada.data[i].RUTA+"','"+ws_lista_errada.data[i].OBSERVACION+"');") ;
        }    
        ws_datatable("tabla_prueba", ws_lista_errada.data, tbl_cab  , tbl_opc);        
}
//FIN LISTA EXPEDIENTES
