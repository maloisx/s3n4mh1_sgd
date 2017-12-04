function login(){
    var u = $('#txt_user').val();   
    var p = $('#txt_pass').val();
    
    if(u == '')
        $('#txt_user').addClass('invalid');  
    else
        $('#txt_user').removeClass('invalid');  
    
    if(p == '') 
        $('#txt_pass').addClass('invalid');  
    else
        $('#txt_pass').removeClass('invalid');
    
//    console.log(u+'->'+p);
    
            if(u!="" && p!=""){
                $.ajax( 
                {
                dataType: "html",
                type: "POST",
                url: path + "login/validarlogin/", 
                data: 	"user="+escape(u)+"&pass="+escape(p),	 	 
                success: function(requestData){  
                    if(requestData.trim() == ''){
                        $('#txt_user').addClass('invalid');
                        $('#txt_pass').addClass('invalid');
                    }else{
                        arrayobj = jQuery.parseJSON(requestData);
                        
                        request = arrayobj.request;  
                        console.log(request)
                        token = request[0].token
                        console.log(token)
                        if (typeof(Storage) !== "undefined") {    
                            localStorage.token = token;
                            window.open(path,'_self')
                        } else {
                            console.log("NAVEGADOR WEB NO SOPORTADO!");
                        }
                        
                    }
                },		
                error: function(requestData, strError, strTipoError){											
                        //all_inputs.addClass("input-error");
                }
                });
            }
    
}

function reset_pass(){
    var p1 = $('#txt_pass1').val();   
    var p2 = $('#txt_pass2').val();
    
    var u = $('#hd_u').val();
    var p = $('#hd_p').val();
    var cod = $('#hd_cod').val();
        
    if(p1 == '')
        $('#txt_pass1').addClass('invalid');  
    else
        $('#txt_pass1').removeClass('invalid');  
    
    if(p2 == '') 
        $('#txt_pass2').addClass('invalid');  
    else
        $('#txt_pass2').removeClass('invalid');
    
//    console.log(u+'->'+p);
    $('#div_msj').hide();
    if(p1 == p2){
        $.ajax( 
        {
        dataType: "html",
        type: "POST",
        url: path + "login/reset_pass_confirm/", 
        data: 	"np="+escape(p1)+"&cod="+cod+"&u="+u+"&p="+p,	 	 
        success: function(requestData){ 
            if(requestData == 1){
                
                $.confirm({
                    title: 'Operacion Exitosa!',
                    content: "<h4>Se guardo la contraseña con exito.</h4>",
                    type: 'blue',
                    typeAnimated: true,
                    buttons: {
                        btn_ok: {
                            text: 'Ir al Logeo',
                            btnClass: 'btn-blue',
                            action: function(){
                                window.open(path,'_self')
                            }
                        }
                        //,close: function () {}
                    }
                });
                
            }else{
                $('#txt_pass1').addClass('invalid');
                $('#txt_pass2').addClass('invalid');
                $('#div_msj').show();
                $('#div_msj').html('ERROR EN CAMBIO DE CLAVE');
//                console.log(requestData);
            }
        },		
        error: function(requestData, strError, strTipoError){											
                //all_inputs.addClass("input-error");
        }
        });
    }else{
        $('#div_msj').show();
         $('#div_msj').html('CLAVES NO COINCIDEN');   
    }
    
}