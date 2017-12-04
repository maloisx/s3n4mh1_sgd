    <div style="background-image: url('../static/img/fondo.jpg'); background-size: 100% 100%; background-repeat: no-repeat;">
        <section id="site-content">


<!--<div style="display: table;height:90%;width: 90%;">
    <div style="display: table-cell;vertical-align: middle;" >     -->
<div style="display: table;
    height:100%;width: 100%;
     ">
    
    <div style="display: table-cell;
    vertical-align: middle;padding-left: 40%">
    <div class="row" align="center">
        <!--Second column-->
        <div class="col-md-4 mt-1">

            <!--Form with header-->
            <div class="card">
                <div class="card-block">

                    <!--Header-->
                    <div class="form-header blue-gradient">
                        <h3><i class="fa fa-user"></i>LOGIN</h3>
                    </div>

                    <!--Body-->
                    <div class="row">
                        <div class="input-field col-md-11">
                          <i class="fa fa-user prefix"></i>
                          <input id="txt_user" type="text" >
                          <label for="txt_user">Usuario</label>
                        </div>
                    </div>    
                    <div class="row">    
                        <div class="input-field col-md-11">
                          <i class="fa fa-lock prefix"></i>
                          <input id="txt_pass" type="password">
                          <label for="txt_pass">Clave</label>
                        </div>
                      </div>

                    <div class="text-center">
                        <button class="btn btn-primary waves-effect waves-light" id="btn_ingresar" onclick="login()">INGRESAR</button>
                    </div>
                    <hr>
                    <div style="text-align: right;padding-right: 10px;">
                        <a class="btn-flat waves-effect waves-teal" id="btn_reset_pass">¿Perdio su clave?</a>
                    </div>
                     <br>
                </div>
            </div>
            <!--/Form with header-->

        </div>
        <!--Second column-->
        </div>
    </div>
</div>



        </section>            
    </div> 


<script>
    $('#txt_user').on('keyup', function(e) {
        if (e.keyCode === 13) {
            login();
        }
    });
    $('#txt_pass').on('keyup', function(e) {
        if (e.keyCode === 13) {
            login();
        }
    });
    
    $('#btn_reset_pass').confirm({
    title: '¿Perdio su Clave?',
    content: '' +
    '<form action="" class="formName">' +
    '<div class="form-group">' +
    '<label>Ingresar su correo electronio institucional:</label>' +
    '<input type="text" placeholder="correo electronico" class="mail form-control" required />' +
    '</div>' +
    '</form>',
    buttons: {
        formSubmit: {
            text: 'Enviar',
            btnClass: 'btn-blue',
            action: function () {
                var mail = this.$content.find('.mail').val();
                if(!mail){
                    $.alert('<h4>Ingrese un correo electronico</h4>');
                    return false;
                }
                
                $.ajax({
                        dataType: "html",
                        type:     "POST",
                        url:      path + "login/solicitud_reset_pass/", 
                        data:     "mail="+mail ,	 	 
                        success: function(requestData){
                                if(requestData == 1){
                                   $.alert('<h4>le hemos enviado un e-mail a: ' + mail+'</h4>');                                      
                                }else{
                                        $.confirm({
                                            title: 'error!',
                                            content: requestData,
                                            type: 'red',
                                            typeAnimated: true,
                                            buttons: {
                                                tryAgain: {
                                                    text: 'regresar',
                                                    btnClass: 'btn-red',
                                                    action: function(){
                                                    }
                                                },
                                                close: function () {
                                                }
                                            }
                                        });
                                }
                        },		
                        error: function(requestData, strError, strTipoError){											
                                console.log("Error " + strTipoError +": " + strError);
                        }
                });
                
                
                
            }
        },
        cancel: function () {
            //close
        },
    }
    /*,
    onContentReady: function () {
        // bind to events
        var jc = this;
        this.$content.find('form').on('submit', function (e) {
            // if the user submits the form by pressing enter in the field.
            e.preventDefault();
            jc.$$formSubmit.trigger('click'); // reference the button and click it
        });
    }*/
});
    
</script>