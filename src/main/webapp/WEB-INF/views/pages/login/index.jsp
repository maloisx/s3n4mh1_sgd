<header>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xs-12 col-md-12 header-sistem">
                        <div class="row">
                            <div class="col-xs-3 col-sm-2 col-md-2 col-lg-1">
                                <img src="/sis/static/img/logo-minam.png" class="img-fluid" alt="MINAM" width="100%" style="padding: 12px 0;" />
                            </div>
                            <div class="col-xs-3 col-sm-2 col-md-2 col-lg-1">
                                <img src="/sis/static/img/logo-senamhi.png" class="img-fluid" alt="SENAMHI" width="100%" />
                            </div>
                            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-10 title-sistem">
                                Sistema de Gestión Digital
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
 
        <div class="container-fluid bg-login">
            <div class="text-center">
 
 
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col">
                            <img src="/sis/static/img/48-anos-renovando.png" alt="48 años de renovacion constante" class="img-fluid" style="padding: 20px;" />
  
                            <div class="container">
                                <div class="row justify-content-md-center">
                                    <div class="col col-lg-4 col-md-3 col-xs-0">
 
                                    </div>
                                    <div class="col col-lg-4 col-md-6 col-xs-12">
 
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
                                                    <a class="btn-flat waves-effect waves-teal" id="btn_reset_pass">SOLICITAR NUEVA CONTRASEÑA</a>
                                                </div>
                                                <br>
                                            </div>
                                        </div>
                                        <!--/Form with header-->
 
 
                                    </div>
                                    <div class="col col-lg-4 col-md-3 col-xs-0">
 
                                    </div>
                                </div>
                            </div>
 
                        </div>
                    </div>
                </div>
 
 
                <!-- ------------------------------------------ -->
 
 
                <!-- ------------------------------------------ -->
            </div>
        </div>
<style>
body{
    background: url("/sis/static/img/cielo.png");
    background-size: cover;
    background-position: center;
    height: 813px;
}
.header-sistem{
    background: #fff;
    border-bottom: 4px solid #C4D0DE;
    vertical-align: middle;
}
.header-sistem img{
    margin: 2px;
}
.title-sistem{
    color: #0063B7;
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 19px;
    font-style: oblique;
    font-weight: 800;
    text-align: right;
    padding: 10px 20px;
}    
</style>
<script>
    $('#txt_user').on('keyup', function (e) {
        if (e.keyCode === 13) {
            login();
        }
    });
    $('#txt_pass').on('keyup', function (e) {
        if (e.keyCode === 13) {
            login();
        }
    });

    $('#btn_reset_pass').confirm({
        title: 'SOLICITE UNA NUEVA CONTRASEÑA',
        content: '' +
                '<form action="" class="formName">' +
                '<div class="form-group">' +
                '<label>Ingresar su correo electronio institucional:</label>' +
                '<input type="text" placeholder="correo electronico" class="mail form-control" required />' +
                '</div>' +
                '</form>',
        boxWidth: '500px',
        useBootstrap: false,
        buttons: {
            formSubmit: {
                text: 'Enviar',
                btnClass: 'btn-blue',
                action: function () {
                    var mail = this.$content.find('.mail').val();
                    if (!mail) {
                        $.alert('<h4>Ingrese un correo electronico</h4>');
                        return false;
                    }

                    $.ajax({
                        dataType: "html",
                        type: "POST",
                        url: path + "login/solicitud_reset_pass/",
                        data: "mail=" + mail,
                        success: function (requestData) {
                            if (requestData == 1) {
                                $.alert('<h4>le hemos enviado un e-mail a: ' + mail + '</h4>');
                            } else {
                                $.confirm({
                                    title: 'error!',
                                    content: requestData,
                                    type: 'red',
                                    typeAnimated: true,
                                    buttons: {
                                        tryAgain: {
                                            text: 'regresar',
                                            btnClass: 'btn-red',
                                            action: function () {
                                            }
                                        },
                                        close: function () {
                                        }
                                    }
                                });
                            }
                        },
                        error: function (requestData, strError, strTipoError) {
                            console.log("Error " + strTipoError + ": " + strError);
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