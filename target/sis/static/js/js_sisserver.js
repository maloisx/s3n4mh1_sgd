function sisserver_listserver() {
//    console.log("token:" + localStorage.token);
    var obj_rpta = ws('OTI','pkg_ws.sp_obt_listaservers', '[""]');
    //console.log(obj_rpta);
    
    for (var item = 0; item < obj_rpta.data.length; item++) {
        
        var r = ws_server('172.25.0.125','ping -c1 '+obj_rpta.data[item].HOST);
        //console.log(r);
        var btn = '';
        if( r.indexOf('1 packets transmitted, 1 received') > -1){
            btn = '<img src="'+path+'static/img/ok.png" height="30px" />';
        }else{
            btn = '<img src="'+path+'static/img/minus.png" height="30px" />';
        }
        
        /*verificar servicios criticos*/
        var obj_rpta_critico = ws('OTI','pkg_ws.sp_obt_listcmd_servers', '["'+obj_rpta.data[item].COD_SERVER+'"]');
        var alert_critico = 0;
        var desc_critico = '';
         for (var item_crit = 0; item_crit < obj_rpta_critico.data.length; item_crit++) {
            var cmd = obj_rpta_critico.data[item_crit].CMD;
            var critico = obj_rpta_critico.data[item_crit].CRITICO;
            var output_default = obj_rpta_critico.data[item_crit].OUTPUT_DEFAULT;                        
                var desc_critico = '';
                if(critico == '1'){
                    var cmd_datos = ws_server(obj_rpta.data[item].HOST ,cmd);                                
                        if( cmd_datos.indexOf(output_default) == -1){
                            alert_critico++;
                        }           
                }            
        }
        if(alert_critico > 0){
            desc_critico = '<img src="'+path+'static/img/alerta_amarilla.png" height="30px" />';
        }
        
        $('#ul_listserver').append('<li class="list-group-item"  style="font-size: 1rem" onclick="window.open(\''+path+'sisserver/detalleservidor?cod='+obj_rpta.data[item].COD_SERVER+'&host='+obj_rpta.data[item].HOST+'&nomserver='+obj_rpta.data[item].NOM_SERVER+'\',\'_self\')">'+btn + ' '+obj_rpta.data[item].NOM_SERVER + '  ('+ obj_rpta.data[item].HOST + ') '+desc_critico+'</li>');
    }

}

function sisserver_detalleserver(){
    var cod = $('#hd_cod').val();
    var host = $('#hd_host').val();        
    console.log(cod);
    var obj_rpta = ws('OTI','pkg_ws.sp_obt_listcmd_servers', '["'+cod+'"]');
    console.log(obj_rpta);
    
    
        for (var item = 0; item < obj_rpta.data.length; item++) {
        
        var cmd = obj_rpta.data[item].CMD;
        var critico = obj_rpta.data[item].CRITICO;
        var output_default = obj_rpta.data[item].OUTPUT_DEFAULT;
        
        var cmd_datos = ws_server(host,cmd);
        var desc_critico = '';
        if(critico == '1'){
            var icon_critico = '';            
                if( cmd_datos.indexOf(output_default) > -1){
                    icon_critico = '<img src="'+path+'static/img/ok.png" height="30px" />';
                }else{
                    icon_critico = '<img src="'+path+'static/img/minus.png" height="30px" />';
                }            
            desc_critico = '<h6 class="card-subtitle mb-2 text-muted">Estado de Nivel crítico: '+icon_critico+'</h6>';
        }
        
        
//        var btn = '';
//        if( r.indexOf('1 packets transmitted, 1 received') > -1){
//            btn = '<img src="'+path+'static/img/ok.png" height="30px" />';
//        }else{
//            btn = '<img src="'+path+'static/img/minus.png" height="30px" />';
//        }
        
        $('#div_cmdserver').append('<div class="card"> '+
                                        '<div class="card-body"> '+
                                        '    <h2 class="card-title" style="color: black">'+ cmd +'</h2> '+
                                        desc_critico +
                                        '    <p class="card-text" style="font-size:1rem">'+cmd_datos+'</p> '+                
                                        '</div> '+
                                    '</div><br>');
    }
    
}

