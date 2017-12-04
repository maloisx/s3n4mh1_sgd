//function ws(p_pkg , p_param){
function ws(p_1 , p_2 , p_3){    
    /*
     * si tiene 3 parametros validos es para oracle
     * param_1 = p_schema
     * param_2 = p_pkg
     * param_3 = p_param
     * 
     * si tiene 2 parametros validos es para postgres
     * param_1 = p_pkg
     * param_2 = p_param
     */
    var p_schema = "";
    var p_pkg = "";
    var p_param = "";
    var url_path_db = "";
    if(p_3 === undefined){
        p_schema = "";
        p_pkg = p_1;
        p_param = p_2;
        url_path_db = "/pg";
    }else{
        p_schema = p_1;
        p_pkg = p_2;
        p_param = p_3;
        url_path_db = "/ora";
    }
    
    
    var respuesta = {data : null , request :null};
    var settings = {
        'async': false,
        'crossDomain': true,
        'url': path_ws+url_path_db,
        'method': 'POST',
        'headers': {
            'content-type': 'application/x-www-form-urlencoded',
            'cache-control': 'no-cache',
            'token': localStorage.token
        },
        'data': {
            'p_schema': p_schema,
            'p_pkg': p_pkg,
            'p_param': p_param
        }
    }
    
    $.ajax(settings).done(function (response) {
        arrayobj = jQuery.parseJSON(response);
        data = arrayobj.data;
        request = arrayobj.request;
        token = arrayobj.token;
        
        respuesta.data = data;
        respuesta.request = request;
        
        localStorage.token = token;
        //console.log(localStorage.token); 
    });
    return respuesta;
}


function ws_contenido_combo(cb_id_html , data , id_seleccionado){
    /*llenado de combo dando por hecho q la data solo tiene dos columnas 1 = id , 2 = desc*/    
    //cb_id_html = "cb_prueba";
    //id_seleccionado = "";
    cont_combo = ""; 
    for(var i= 0 ; i<data.length ; i++){
        var item = data[i] ;
        ind = 0;
        id = null;
        val = null;
        $.each(item, function (index_item, value_item) {
            if(ind == 0){
                id = value_item;
                ind++; 
            }else if(ind == 1){
                val = value_item;
            }
        });
        //console.log(id + " // " + val);
        if(id_seleccionado == id){
           cont_combo += "<option value='"+id+"' selected='selected' >"+val+"</option>";
        }else{
            cont_combo += "<option value='"+id+"'>"+val+"</option>";
        }
    }
    $('#'+cb_id_html).html(cont_combo);
    $('#'+cb_id_html).selectpicker('refresh');
}

function ws_datatable(id_div_tbl , data , tbl_cab){
    /*tabla con datatables*/    
//    var id_div_tbl = "tabla_prueba";
//    var data = obj_rpta.data;
//    var tbl_cab = [{'sTitle': 'ID'} , {'sTitle': 'DESC'}];
        
    var tbl_data = [];
    for(var i= 0 ; i<data.length ; i++){
        var item = data[i] ;
         var tbl_row = [];
        $.each(item, function (index_item, value_item) {
            tbl_row.push(value_item);
        });
        tbl_data.push(tbl_row);
    }
        
    var tbl_n = parseInt(Math.random() * 99999+ 1);
    var html_tbl = "<table border='1' class='table table-striped table-bordered dt-responsive' id='tbl_dt_"+tbl_n+"'></table>";
//    var html_tbl = "<table border='1' class='table table-striped table-bordered' id='tbl_dt_"+tbl_n+"'></table>";
    $('#'+id_div_tbl).html(html_tbl);
    var tbl = $('#tbl_dt_'+tbl_n).dataTable({
        "bFilter": true,
        "bLengthChange": false,
        "bInfo": false,
        "bPaginate": false,
        "bPaginate": false,
        "bScrollCollapse": true, 
        //"sScrollY": '93%', 
        "aoColumns": tbl_cab,
        "aaData" : tbl_data,
        "fixedColumns": true,
//        "aaData": [["1", "11", "111"], ["2", "222", "222"]] ,
        
        "dom": "Bfrtip",
        "buttons": [{ extend:'excel',text:'Exportar a Excel',className:'btn btn-info btn-sm' }],
        "language": {'url': '/sis/static/datatables/Spanish.json'}
    });
    tbl.$('tr').hover(function () {
        $(this).addClass('highlighted');
    }, function () {
        tbl.$('tr.highlighted').removeClass('highlighted');
    });
}

function tbl_ext_btn(name_btn, func){
    
    var btn_n = parseInt(Math.random() * 99999+ 1);    
    if(func != undefined){
        html_btn = '<button id="btn_'+btn_n+'" onclick="'+func+'" type="button" class="btn btn-info" onclick=""><span class="glyphicon '+name_btn+'"></span></button>';
    }else{
        html_btn = '<button id="btn_'+btn_n+'" type="button" class="btn btn-info" onclick=""><span class="glyphicon '+name_btn+'"></span></button>';        
    } 
    return html_btn;
}