<br>
<br>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-2">
                    <label  class="active">DATOS DEL SOLICITANTE</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="input-field col-sm-12">
                <div class="md-radio md-radio-inline col-sm-2">
                    <input id="rb_per_natural" type="radio" name="rb_persona">
                    <label for="rb_per_natural">Persona Natural</label>
                </div>
                <div class="md-radio md-radio-inline col-sm-2">
                    <input id="rb_per_juridica" type="radio" name="rb_persona">
                    <label for="rb_per_juridica">Persona Jurídica</label>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="input-field col-sm-12">                
                <div class="col-sm-11" id="div_per_natural_buscar">
                    <div class="input-field md-radio md-radio-inline col-sm-1">
                        <input id="txt_dni" type="text" name="txt_dni">
                        <label for="txt_dni">DNI</label>
                    </div>
                    <div class="col-sm-1">
                        <button id="btn_busca_dni" onclick="sgd_mant_busca_ciudadano_dni()" class="btn btn-info btn-sm" >
                            BUSCAR
                        </button>
                    </div>
                </div>                
                <div class="col-sm-11" id="div_per_juridica_buscar">
                    <div class="input-field col-sm-1">
                        <input id="txt_ruc" type="text" name="txt_ruc">
                        <label for="txt_ruc">RUC</label>
                    </div>
                     <div class="col-sm-1">
                        <button id="btn_busca_ruc" onclick="sgd_mant_busca_ciudadano_ruc()" class="btn btn-info btn-sm" >
                            BUSCAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="input-field col-sm-12" id="div_msg_registro">
            Registre sus datos
        </div>
        <div class="row" id="div_per_natural">
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-4">
                    <input id="txt_nombres" type="text" name="txt_nombres" style="text-transform: uppercase" >
                    <label id="lb_nombres" for="txt_nombres">Apellidos y Nombres</label>
                </div>
            </div>
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-4">
                    <input id="txt_direccion" type="text" name="txt_direccion" style="text-transform: uppercase">
                    <label id="lb_direccion" for="txt_direccion">Dirección</label>
                </div>
            </div>
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-2">
                    <input id="txt_email" type="email" name="txt_email" style="text-transform: lowercase">
                    <label id="lb_email" for="txt_email">E-Mail</label>
                </div>
                <div class="input-field col-sm-2">
                    <input id="txt_telefono" type="text" name="txt_telefono">
                    <label id="lb_telefono" for="txt_telefono">Teléfono</label>
                </div>
            </div>
        </div>
        <div class="row" id="div_per_juridica">
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-4">
                    <input id="txt_nombres" type="text" name="txt_nombres" style="text-transform: uppercase" >
                    <label for="txt_nombres">Razón social</label>
                </div>
            </div>
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-1">
                    <select id="cb_sector" class="form-control selectpicker" name="cb_sector" data-size="5" >${requestScope['cb_sector']}</select>
                    <label for="cb_sector" class="active">Sector</label>
                </div>
                <div class="input-field col-sm-1">
                    <input id="txt_app" type="text" name="txt_app">
                    <label for="txt_app">Teléfono</label>
                </div>
                <div class="input-field col-sm-2">
                    <input id="txt_apm" type="text" name="txt_apm" style="text-transform: lowercase">
                    <label for="txt_apm">E-Mail</label>
                </div>
            </div>
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-4">
                    <input id="txt_direccion" type="text" name="txt_direccion" style="text-transform: uppercase">
                    <label for="txt_direccion">Dirección</label>
                </div>
            </div>
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-3">
                    <input id="txt_email" type="email" name="txt_email" style="text-transform: uppercase">
                    <label for="txt_email">Representante</label>
                </div>
                <div class="input-field col-sm-1">
                    <input id="txt_telefono" type="text" name="txt_telefono">
                    <label for="txt_telefono">DNI Representante</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-1" id="div_guarda_ciudadano">
                    <button id="btn_busca" onclick="sgd_mant_guarda_ciudadano()" class="btn btn-info btn-sm" >
                        REGISTRAR
                    </button>
                </div>
            </div>
        </div>
        <br>
        <!--<hr>-->        
        <div class="row">
            <div class="col-sm-12">
<!--                <div class="row">  
                   <div class="col-sm-12">     
                        <div class="input-field col-sm-1">        
                        </div>
                        <div class="row">-->
                            <div id="div_reporte" class="col-sm-12" style="background-color:#E9FFFF; border-color:#999; border-style:solid; border-radius: 10px;">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <br>
                                        <div id="div_ciudadano">
                                        </div>
                                        <br>
                                    </div>
                                </div>
                            </div>
                        <!--</div>-->
<!--                        <div class="input-field col-sm-1">        
                        </div>    
                    </div>                     
                </div>      -->
            </div>
        </div>
    </div>
</div>
                                 
<script>
    $('#cb_anio option:first-child').attr('selected', 'selected');
  
    $('#div_guarda_ciudadano').toggle();
    $('#div_per_natural_buscar').toggle();
    $('#div_msg_registro').toggle();
    $('#div_per_natural').toggle();
    $('#rb_per_natural').click(function() {
//        $('#div_per_natural').show();
        $('#div_per_juridica').hide();
        $('#div_per_natural_buscar').show();
        $('#div_per_juridica_buscar').hide();
        $('#div_guarda_ciudadano').show();
    });
    
    $('#div_per_juridica_buscar').toggle();
    $('#div_per_juridica').toggle();
    $('#rb_per_juridica').click(function() {
        $('#div_per_juridica').show();
        $('#div_per_natural').hide();
        $('#div_per_juridica_buscar').show();
        $('#div_per_natural_buscar').hide();
        $('#div_guarda_ciudadano').show();
    });
    
    
    $('#div_reporte').toggle();
    $('#div_asunto').toggle();

//    $('#btn_busca').click(function() {
//        $('#div_reporte').show();
//        $('#div_asunto').show();
//        $('#lb_asunto').addClass('active');
//    });
</script>

