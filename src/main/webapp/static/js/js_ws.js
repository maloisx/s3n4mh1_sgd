//function ws(p_pkg , p_param){
function ws(p_1, p_2, p_3) {
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
    if (p_3 === undefined) {
        p_schema = "";
        p_pkg = p_1;
        p_param = p_2;
        url_path_db = "/pg";
    } else {
        p_schema = p_1;
        p_pkg = p_2;
        p_param = p_3;
        url_path_db = "/ora";
    }


    var respuesta = {data: null, request: null};

    var settings = {
        'async': false,
        'crossDomain': true,
        'url': path_ws + url_path_db,
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


function ws_contenido_combo(cb_id_html, data, id_seleccionado) {
    /*llenado de combo dando por hecho q la data solo tiene dos columnas 1 = id , 2 = desc*/
    //cb_id_html = "cb_prueba";
    //id_seleccionado = "";
    cont_combo = "";
    for (var i = 0; i < data.length; i++) {
        var item = data[i];
        ind = 0;
        id = null;
        val = null;
        if(typeof item === 'object'){
            $.each(item, function (index_item, value_item) {
                if (ind == 0) {
                    id = value_item;
                    ind++;
                } else if (ind == 1) {
                    val = value_item;
                }
            });
        }else{
            id = item;
            val = item;
        }
        //console.log(id + " // " + val);
        if (id_seleccionado == id) {
            cont_combo += "<option value='" + id + "' selected='selected' >" + val + "</option>";
        } else {
            cont_combo += "<option value='" + id + "'>" + val + "</option>";
        }
    }
    $('#' + cb_id_html).html(cont_combo);
    $('#' + cb_id_html).selectpicker('refresh');
}

function ws_datatable(id_div_tbl, data, tbl_cab, opciones) {

    if(opciones == undefined) opciones = {};

    var opciones_default = {
        responsive: true
        , bFilter: true
        , bLengthChange: false
        , bInfo: false
        , bPaginate: false
        , dom: "Blfrtip"
        , buttons: [{extend: 'excel', text: 'Exportar a Excel', className: 'btn btn-info btn-sm'}]
    };

    var tbl_data = [];
    for (var i = 0; i < data.length; i++) {
        var item = data[i];
        var tbl_row = [];
        $.each(item, function (index_item, value_item) {
            tbl_row.push(value_item);
        });
        tbl_data.push(tbl_row);
    }

    var tbl_n = parseInt(Math.random() * 99999 + 1);
//    var html_tbl = "<table border='1' class='table table-striped table-bordered dt-responsive' id='tbl_dt_" + tbl_n + "'></table>";
    
    var tbl_responsive = "";
    var b_responsibe = true;
        if(opciones.responsive != undefined){
            b_responsibe = opciones.responsive;
        }else{
            b_responsibe = opciones_default.responsive;
        }
        
        if(b_responsibe == true){
            tbl_responsive = "dt-responsive";
        }
             

    var html_tbl = "<table border='1' class='table table-striped table-bordered "+ tbl_responsive + "' style='width:100%;' id='tbl_dt_" + id_div_tbl + "'></table>";

    $('#' + id_div_tbl).html(html_tbl);
        
       var tbl = $('#tbl_dt_' + id_div_tbl).dataTable({
        "bFilter": (opciones.bFilter != undefined)?opciones.bFilter : opciones_default.bFilter,
        "bLengthChange": (opciones.bLengthChange != undefined)?opciones.bLengthChange : opciones_default.bLengthChange,
        "bInfo": (opciones.bInfo != undefined)?opciones.bInfo : opciones_default.bInfo,
        "bPaginate": (opciones.bPaginate != undefined)?opciones.bPaginate : opciones_default.bPaginate,
        "bScrollCollapse": true,
        //"sScrollY": '93%', 
        "aoColumns": tbl_cab,
        "aaData": tbl_data,
        "fixedColumns": true,
        "dom": (opciones.dom != undefined)?opciones.dom : opciones_default.dom,
        "buttons": (opciones.buttons != undefined)?opciones.buttons : opciones_default.buttons,
        "language": {'url': '/sis/static/datatables/Spanish.json'}
    });
    
    tbl.$('tr').hover(function () {
        $(this).addClass('highlighted');
    }, function () {
        tbl.$('tr.highlighted').removeClass('highlighted');
    });

    return tbl;
}

function tbl_ext_btn(name_btn, func) {

    var btn_n = parseInt(Math.random() * 99999 + 1);
    if (func != undefined) {
        html_btn = '<button id="btn_' + btn_n + '" onclick="' + func + '" type="button" class="btn btn-info"><span class="glyphicon ' + name_btn + '"></span></button>';
    } else {
        html_btn = '<button id="btn_' + btn_n + '" type="button" class="btn btn-info" onclick=""><span class="glyphicon ' + name_btn + '"></span></button>';
    }
    return html_btn;
}