<br>
<br>
<input type="hidden" name="hd_id" id="hd_id" value=""/>
<input type="hidden" name="hd_id_var" id="hd_id_var" value=""/>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-12">
                    <label  class="active" style="font-size:150%; font-weight:bold;">DATOS DEL SOLICITANTE</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="input-field col-sm-12">
                <div class="md-radio md-radio-inline col-sm-3">
                    <input id="rb_per_natural" type="radio" name="rb_persona"/>
                    <label for="rb_per_natural">Persona Natural</label>
                </div>
                <div class="md-radio md-radio-inline col-sm-3">
                    <input id="rb_per_juridica" type="radio" name="rb_persona"/>
                    <label for="rb_per_juridica">Persona Jurídica</label>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="input-field col-sm-12">                
                <div class="col-sm-11" id="div_per_natural_buscar">
                    <div class="input-field md-radio md-radio-inline col-sm-2">
                        <input id="txt_dni" type="text" name="txt_dni"/>
                        <label for="txt_dni">DNI</label>
                    </div>
                    <div class="col-sm-2">
                        <button id="btn_busca_dni" onclick="sgd_mant_busca_ciudadano_dni(1)" class="btn btn-info btn-sm" >
                            BUSCAR
                        </button>
                    </div>
                </div>                
                <div class="col-sm-11" id="div_per_juridica_buscar">
                    <div class="input-field col-sm-2">
                        <input id="txt_ruc" type="text" name="txt_ruc"/>
                        <label for="txt_ruc">RUC</label>
                    </div>
                     <div class="col-sm-2">
                        <button id="btn_busca_ruc" onclick="sgd_mant_busca_ciudadano_ruc(2)" class="btn btn-info btn-sm" >
                            BUSCAR
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-11" id="div_msg_registro" style=" color: red; font-size:150%; font-weight:bold;">
                        Registre sus datos.
                    </div>    
                </div>
            </div>
        </div>        
        <div class="row" id="div_per_natural">
            <div class="row">
                <div class="input-field col-sm-12">
                    <div class="input-field col-sm-12">
                        <div class="input-field col-sm-6">
                            <div class="input-field col-sm-12">
                                <input id="txt_nombres" type="text" name="txt_nombres" style="text-transform: uppercase" />
                                <label id="lb_nombres" for="txt_nombres">Apellidos y Nombres</label>
                            </div>  
                        </div>   
                        <div class="input-field col-sm-6">
                            <div class="input-field col-sm-12">
                                <input id="txt_direccion" type="text" name="txt_direccion" style="text-transform: uppercase"/>
                                <label id="lb_direccion" for="txt_direccion">Dirección</label>
                            </div>
                        </div>
                    </div>     
                </div>     
            </div>
            <div class="row">
                <div class="input-field col-sm-12">
                    <div class="input-field col-sm-12">
                        <div class="input-field col-sm-6">
                            <div class="input-field col-sm-12">
                                <input id="txt_email" type="email" name="txt_email" style="text-transform: lowercase"/>
                                <label id="lb_email" for="txt_email">E-Mail</label>
                            </div>    
                        </div>
                        <div class="input-field col-sm-6">
                            <div class="input-field col-sm-5">
                                <input id="txt_telefono" type="text" name="txt_telefono">
                                <label id="lb_telefono" for="txt_telefono">Teléfono</label>
                            </div>    

                            <div class="input-field col-sm-7 text-right" id="div_guarda_ciudadano_dni">
                                <button id="btn_guarda_ciudadano_dni" onclick="sgd_mant_ciudadano_dni_guardar()" class="btn btn-info btn-sm" >
                                    REGISTRAR
                                </button>
                            </div>
                        </div>                        
                    </div>    
                </div>    
            </div>
        </div>
        <div class="row" id="div_per_juridica">
            <div class="row">
                <div class="input-field col-sm-12">
                    <div class="input-field col-sm-6">
                        <div class="input-field col-sm-12">
                            <input id="txt_rsocial" type="text" name="txt_rsocial" style="text-transform: uppercase" />
                            <label id="lb_rsocial" for="txt_rsocial">Razón social</label>
                        </div>
                    </div>
                    <div class="input-field col-sm-6">
                        <div class="input-field col-sm-12">
                            <input id="txt_direccion_ruc" type="text" name="txt_direccion_ruc" style="text-transform: uppercase"/>
                            <label id="lbl_direccion_ruc" for="txt_direccion_ruc">Dirección</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="input-field col-sm-12">
                    <div class="input-field col-sm-6">
                        <div class="input-field col-sm-7">
                            <select id="cb_sector" class="form-control selectpicker" name="cb_sector" data-size="5" >${requestScope['cb_sector']}</select>
                            <label for="cb_sector" class="active">Sector</label>
                        </div>   
                        <div class="input-field col-sm-5">
                            <input id="txt_telefono_ruc" type="text" name="txt_telefono_ruc">
                            <label id="lb_telefono_ruc" for="txt_telefono_ruc">Teléfono</label>
                        </div>      
                    </div>
                    <div class="input-field col-sm-6">
                        <div class="input-field col-sm-12">
                            <input id="txt_email_ruc" type="text" name="txt_email_ruc" style="text-transform: lowercase"/>
                            <label id="lb_email_ruc" for="txt_email_ruc">E-Mail</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="input-field col-sm-12">
                    <div class="input-field col-sm-6">
                        <div class="input-field col-sm-12">
                            <input id="txt_representante" type="email" name="txt_representante" style="text-transform: uppercase"/>
                            <label id="lb_representante" for="txt_representante">Representante</label>
                        </div>
                    </div>
                    <div class="input-field col-sm-6">
                        <div class="input-field col-sm-5">
                            <input id="txt_dni_rep" type="text" name="txt_dni_rep">
                            <label id="lb_dni_rep" for="txt_dni_rep">DNI Representante</label>
                        </div> 
                        <div class="input-field col-sm-7 text-right" id="div_guarda_ciudadano_ruc">
                            <button id="btn_guarda_ciudadano_ruc" onclick="sgd_mant_ciudadano_ruc_guardar()" class="btn btn-info btn-sm" >
                                REGISTRAR
                            </button>
                        </div>
                    </div>
                </div>    
            </div>
        </div>
        <br>
<!--        <div class="row">
            <div class="input-field col-sm-12">
                <div class="input-field col-sm-12">
                    <label  class="active" style="font-size:150%; font-weight:bold;">SOLICITUD</label>
                </div>
            </div>
        </div>-->
        <br>
        <div class="row" id="div_inf_ref">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-6">
                        <label  class="active" style="font-size:150%; font-weight:bold;">SOLICITUD</label>
                    </div>    
                </div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-6" id="div_msg_registro" style=" color: red; font-size:150%; font-weight:bold;">
                        <textarea  id="txt_inf_ref" name="txt_inf_ref" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1"></textarea>
                        <label for="txt_inf_ref" class="active">Motivo del requerimiento</label>
                    </div>    
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12">
                <div id="div_mapa" class="col-sm-12" style="background-color:#E9FFFF; border-color:#999; border-style:solid; border-radius: 10px; ">
                    <div class="row">
                        <div class="col-sm-12">
                            <br>
                            <div class="col-sm-8" id="div_ciudadano_mapa" style="width: 65%; height: 600px;">
                                <div class="row">
                                    <div class="col-md-8">        
                                    </div>    
                                    <div class="input-field col-md-4" id="div_combos">        
                                        <select id="cb_estacion" class="form-control selectpicker" name="cb_estacion" data-size="5" data-live-search="true" onchange="sgd_mant_mapa_mostrar()">${requestScope['cb_estacion']}</select>
                                        <label for="cb_estacion" class="active">Estación</label>
                                    </div>    
                                </div>                                  
                                <div class="col-md-12 demo-section" style="height: calc(100%);">        
                                    <div id="div_map" style="width: 100% ;height: 100%"></div>
                                </div>  
                            </div>
                            <div class="col-sm-4" id="div_detalle" style="width: 35%; height: 700px;">
                                <div class="col-sm-12" id="div_detalle">
                                    <div class="row">
                                        <div class="input-field text-right col-sm-4">
                                            <input id="txt_cod_estacion" type="text" name="txt_cod_estacion"/>
                                            <label id="lb_cod_estacion" for="txt_cod_estacion">Código</label>
                                        </div>
                                        <div class="input-field text-right col-sm-8">
                                            <input id="txt_estacion" type="text" name="txt_estacion"/>
                                            <label id="lb_estacion" for="txt_estacion">Estación</label>
                                        </div>                                        
                                    </div>
                                    <div class="row">
                                        <div class="input-field text-right col-sm-4">
                                            <input id="txt_dpto" type="text" name="txt_dpto"/>
                                            <label id="lb_dpto" for="txt_dpto">Departamento</label>
                                        </div>
                                        <div class="input-field text-right col-sm-8">
                                            <input id="txt_provincia" type="text" name="txt_provincia"/>
                                            <label id="lb_provincia" for="txt_provincia">Provincia / Distrito</label>
                                        </div> 
                                    </div>
                                    <div class="row">
                                        <div class="input-field text-right col-sm-4">
                                            <input id="txt_tipo" type="text" name="txt_tipo"/>
                                            <label id="lb_tipo" for="txt_tipo">Tipo Estación</label>
                                        </div>
                                        <div class="input-field text-right col-sm-8">
                                            <input id="txt_categoria" type="text" name="txt_categoria"/>
                                            <label id="lb_categoria" for="txt_categoria">Categoría</label>
                                        </div> 
                                    </div>
                                    <br>
                                    <div class="row">                                       
                                        <div class="input-field text-right col-sm-12" id="div_variables">                                            
                                        </div> 
                                    </div>                                
                                    <div class="row">
                                        <div class="col-md-4">
                                            <button onclick="sgd_mant_add_solicitud()" onfocus="lista_variables()" class="btn btn-info btn-sm">ADICIONAR</button>            
                                        </div>
                                        <div class="input-field col-sm-8">        
                                        </div>  
                                    </div>  
                                </div>                                
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col-sm-12" id="div_solicitud">
                        </div>
                    </div>            
                </div>
            </div>
        </div>
    </div>
</div>
                                 
<script>
    $('#cb_anio option:first-child').attr('selected', 'selected');
  
    $('#div_guarda_ciudadano').toggle();
    $('#div_per_natural_buscar').toggle();
    $('#div_msg_registro').hide();
    $('#div_per_natural').toggle();
    $('#div_inf_ref').toggle();
    $('#div_mapa').toggle();
    $('#rb_per_natural').click(function() {        
        $('#hd_id').val('');
        $('#txt_dni').val('');
        $('#txt_nombres').val('');
        $('#txt_direccion').val('');
        $('#txt_telefono').val('');
        $('#div_per_juridica').hide();
        $('#div_per_natural_buscar').show();
        $('#div_per_juridica_buscar').hide();
        $('#div_msg_registro').hide();
        $('#div_guarda_ciudadano').hide();
    });
    
    $('#div_per_juridica_buscar').toggle();
    $('#div_per_juridica').toggle();
    $('#rb_per_juridica').click(function() {
        $('#hd_id').val('');
        $('#txt_ruc').val('');
        $('#txt_rsocial').val('');
        $('#txt_direccion_ruc').val('');
        $('#txt_telefono_ruc').val('');
        $('#txt_email_ruc').val('');
        $('#txt_representante').val('');
        $('#txt_dni_rep').val('');
        $('#div_per_natural').hide();
        $('#div_per_juridica_buscar').show();
        $('#div_per_natural_buscar').hide();
        $('#div_msg_registro').hide();
        $('#div_guarda_ciudadano').hide();
    });
        
    $('#div_reporte').toggle();
    $('#div_asunto').toggle();

//    $('#btn_busca').click(function() {
//        $('#div_reporte').show();
//        $('#div_asunto').show();
//        $('#lb_asunto').addClass('active');
//    });
</script>

