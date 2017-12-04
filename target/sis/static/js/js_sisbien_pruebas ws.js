function prueba_js() {

    /*forma 1*/
    /*
     var xhr = new XMLHttpRequest();
     xhr.open("POST", "http://localhost:8085/ws/rest/global/oracle", true);
     xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
     xhr.setRequestHeader("cache-control", "no-cache");
     xhr.addEventListener("load", function (e) {
     console.log(xhr.responseText);
     });
     xhr.send('p_schema=sismethaweb&p_pkg=pkg_movil.sp_user_estacion&p_param=["07061948"]');
     */

/*forma 2*/
//    var settings = {
//        'async': true,
//        'crossDomain': true,
//        'url': 'http://localhost:8085/ws/rest/global/oracle',
//        'method': 'POST',
//        'headers': {
//            'content-type': 'application/x-www-form-urlencoded',
//            'cache-control': 'no-cache'
//        },
//        'data': {
//            'p_schema': 'sismethaweb',
//            'p_pkg': 'pkg_movil.sp_user_estacion',
//            'p_param': '["07061948"]'
//        }
//    }
//
//    $.ajax(settings).done(function (response) {
//        console.log(response);
//    });
        
    console.log("token:" + localStorage.token);
    var obj_rpta = ws('sisbien.fn_colorbien_obtener' , '[""]');  
    console.log(obj_rpta);



}