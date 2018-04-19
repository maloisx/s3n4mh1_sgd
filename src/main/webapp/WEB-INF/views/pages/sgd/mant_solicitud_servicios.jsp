<br>
<br>
<input type="hidden" name="hd_id_sol" id="hd_id_sol" value=""/>
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
                    <label for="rb_per_natural" style="font-size: 12px;">Persona Natural</label>
                </div>
                <div class="md-radio md-radio-inline col-sm-3">
                    <input id="rb_per_juridica" type="radio" name="rb_persona"/>
                    <label for="rb_per_juridica" style="font-size: 12px;">Persona Jurídica</label>
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
                <div class="col-sm-12">
                    <div class="col-sm-12">
                        <div class="col-sm-6">
                            <div class="input-field col-sm-12">
                                <input id="txt_nombres" type="text" name="txt_nombres" style="text-transform: uppercase" />
                                <label id="lb_nombres" for="txt_nombres">Apellidos y Nombres</label>
                            </div>  
                        </div>   
                        <div class="col-sm-6">
                            <div class="input-field col-sm-12">
                                <input id="txt_direccion" type="text" name="txt_direccion" style="text-transform: uppercase"/>
                                <label id="lb_direccion" for="txt_direccion">Dirección</label>
                            </div>
                        </div>
                    </div>     
                </div>     
            </div>
            <div class="row">
                <div class=" col-sm-12">
                    <div class=" col-sm-12">
                        <div class="col-sm-6">
                            <div class="input-field col-sm-12">
                                <input id="txt_email" type="email" name="txt_email" style="text-transform: lowercase"/>
                                <label id="lb_email" for="txt_email">E-Mail</label>
                            </div>    
                        </div>
                        <div class="col-sm-6">
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
                <div class="col-sm-12">
                    <div class="col-sm-6">
                        <div class="input-field col-sm-12">
                            <input id="txt_rsocial" type="text" name="txt_rsocial" style="text-transform: uppercase" />
                            <label id="lb_rsocial" for="txt_rsocial">Razón social</label>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="input-field col-sm-12">
                            <input id="txt_direccion_ruc" type="text" name="txt_direccion_ruc" style="text-transform: uppercase"/>
                            <label id="lbl_direccion_ruc" for="txt_direccion_ruc">Dirección</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="col-sm-6">
                        <div class="input-field col-sm-7">
                            <select id="cb_sector" class="form-control selectpicker" name="cb_sector" data-size="5" >${requestScope['cb_sector']}</select>
                            <label for="cb_sector" class="active">Sector</label>
                        </div>   
                        <div class="input-field col-sm-5">
                            <input id="txt_telefono_ruc" type="text" name="txt_telefono_ruc">
                            <label id="lb_telefono_ruc" for="txt_telefono_ruc">Teléfono</label>
                        </div>      
                    </div>
                    <div class="col-sm-6">
                        <div class="input-field col-sm-12">
                            <input id="txt_email_ruc" type="text" name="txt_email_ruc" style="text-transform: lowercase"/>
                            <label id="lb_email_ruc" for="txt_email_ruc">E-Mail</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="col-sm-6">
                        <div class="input-field col-sm-12">
                            <input id="txt_representante" type="email" name="txt_representante" style="text-transform: uppercase"/>
                            <label id="lb_representante" for="txt_representante">Persona de contacto</label>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="input-field col-sm-3">
                            <input id="txt_telef_rep" type="text" name="txt_telef_rep">
                            <label id="lb_telef_rep" for="txt_telef_rep">Telef.Cont.</label>
                        </div>
                        <div class="input-field col-sm-6">
                            <input id="txt_email_rep" type="text" name="txt_email_rep">
                            <label id="lb_email_rep" for="txt_email_rep">E-Mail Cont.</label>
                        </div>
                        <div class="input-field col-sm-3 text-right" id="div_guarda_ciudadano_ruc">
                            <button id="btn_guarda_ciudadano_ruc" onclick="sgd_mant_ciudadano_ruc_guardar()" class="btn btn-info btn-sm" >
                                REGISTRAR
                            </button>
                        </div>
                    </div>
                </div>    
            </div>
        </div> 
        <br>
        <div class="row" id="div_cbo_servicio">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-12">
                        <select id="cb_servicio" class="form-control selectpicker" name="cb_servicio" data-size="5" data-live-search="true" onchange="sgd_mant_solicitud_mostrar()">${requestScope['cb_proc']}</select>
                        <label for="cb_servicio" class="active">Seleccione Servicio</label>
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
                            <div class="col-sm-8" id="div_ciudadano_mapa" style="width: 65%; height: 65%;">
                                <div class="row">
                                    <div class="input-field col-md-4">
                                        <select id="cb_dpto" name="cb_dpto" class="form-control selectpicker" data-size="5" data-live-search="true" onchange="sgd_mant_dpto_mostrar()">${requestScope['cb_dpto']}</select>
                                        <label for="cb_dpto" class="active">Departamento</label>
                                    </div>    
                                    <div class="input-field col-md-4" id="div_combos">        
                                        <select id="cb_estacion" class="form-control selectpicker" name="cb_estacion" data-size="5" data-live-search="true" onchange="sgd_mant_mapa_mostrar()">${requestScope['cb_estacion']}</select>
                                        <label for="cb_estacion" class="active">Estación</label>
                                    </div>    
                                    <div class="input-field col-md-4">
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
                                        <div class="input-field col-sm-12" id="div_variables" >                                            
                                        </div> 
                                    </div>                                
                                    <div class="row">
                                        <div class="col-md-4 text-right">
                                            <button onclick="sgd_mant_add_solicitud()" onfocus="lista_variables()" class="btn btn-info btn-sm">Agregar a Solicitud</button>            
                                        </div>
                                        <div class="input-field col-sm-8">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
        <br>
        <div class="row" id="div_solicitud_titulo">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field text-center col-sm-6">
                        <label  class="active" style="font-size:150%; font-weight:bold;">SOLICITUD</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="div_solicitud_info">
            <div class="col-sm-12" id="div_motivo">
                <div class="col-sm-12"><br>
                    <div class="input-field col-sm-6">
                        <textarea  id="txt_motivo" name="txt_motivo" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1"></textarea>
                        <label for="txt_motivo" class="active">Motivo del requerimiento</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="col-sm-12 table-responsive">
                        <div class="col-sm-12">
                            <div class="input-field" id="div_solicitud_detalle">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="div_solicitud_otros">
            <div class="col-sm-12">
                <div class="col-sm-12"><br>
                    <div class="input-field col-sm-6">
                        <textarea  id="txt_descripcion" name="txt_descripcion" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="1000" minlength="1"></textarea>
                        <label for="txt_descripcion" class="active">Descripción detallada del Servicio</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="div_solicitud_tupa">
            <div class="col-sm-12">
                <div class="col-sm-12"><br>
                    <div class="input-field col-sm-12">
                        <select id="cb_funcionario" class="form-control selectpicker" name="cb_funcionario" data-size="5" data-live-search="true">${requestScope['cb_func']}</select>
                        <label for="cb_funcionario" class="active">Funcionario responsable de entregar la información</label>
                    </div>
                </div>
            </div>
        </div><br>
        <div class="row" id="div_solicitud_tupa_detalle">
            <div class="col-sm-12">                
                <div class="col-sm-12">
                    <div class="input-field col-sm-12">
                        <label class="active">Recibir la información en:</label>
                    </div>                        
                </div>
                <div class="col-sm-12">
                    <div class="input-field col-sm-2">
                        <input id="cb_copias" type="checkbox" name="cb_copias" class="cb_entrega"/>
                        <label id="lb_copias" for="cb_copias" style="font-size: 11px;">Copia simple</label>
                    </div>
                    <div class="input-field col-sm-3">
                        <input id="cb_copiaa" type="checkbox" name="cb_copiaa" class="cb_entrega"/>
                        <label id="lb_copiaa" for="cb_copiaa" style="font-size: 11px;">Copia autenticada</label>
                    </div>
                    <div class="input-field col-sm-2">
                        <input id="cb_cd" type="checkbox" name="cb_cd" class="cb_entrega"/>
                        <label id="lb_cd" for="cb_cd" style="font-size: 11px;">CD</label>
                    </div>
                    <div class="input-field col-sm-3">
                        <input id="cb_email" type="checkbox" name="cb_email" class="cb_entrega"/>
                        <label id="lb_email" for="cb_email" style="font-size: 11px;">Correo Electrónico</label>
                    </div>
                    <div class="input-field col-sm-2">
                        <input id="cb_otro" type="checkbox" name="cb_otro" class="cb_entrega"/>
                        <label id="lb_otro" for="cb_otro" style="font-size: 11px;">Otro</label>
                    </div>
                </div>
            </div>
        </div><br>
        <div class="row" id="div_observacion">
            <div class="col-sm-12">
                <div class="col-sm-12"><br>
                    <div class="input-field col-sm-6">
                        <textarea  id="txt_observacion" name="txt_observacion" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="1000" minlength="1"></textarea>
                        <label for="txt_observacion" class="active">Observaciones</label>
                    </div>
                </div>
            </div>
        </div><br><br>
        <div class="row">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="col-md-3" id="div_enviar_sol_info">
                        <button onclick="sgd_mant_enviar_solicitud_info()" class="btn btn-info btn-sm">Enviar Solicitud</button>
                    </div>
                    <div class="col-md-3" id="div_enviar_sol_otros">
                        <button onclick="sgd_mant_enviar_solicitud_otros()" class="btn btn-info btn-sm">Enviar Solicitud</button>
                    </div>
                    <div class="col-md-3" id="div_enviar_sol_tupa">
                        <button onclick="sgd_mant_enviar_solicitud_tupa()" class="btn btn-info btn-sm">Enviar Solicitud</button>
                    </div>
                    <div class="input-field col-sm-3">
                    </div>     
                </div>                       
            </div>                
        </div>     
        <div class="row">
            <div class="col-sm-12" id="div_msg_registro_sol">                                     
            </div>                
        </div>     
    </div>
</div>
                                 
<script>
    $('#cb_anio option:first-child').attr('selected', 'selected');
    $('#div_cbo_servicio').hide();
    $('#div_solicitud_titulo').hide();
    $('#div_solicitud_otros').hide();
    $('#div_observacion').hide();
    $('#div_enviar_sol_info').hide();
    $('#div_enviar_sol_otros').hide();
    $('#div_msg_registro_sol').hide();
    $('#div_solicitud_tupa').hide();
    $('#div_solicitud_tupa_detalle').hide();
    $('#div_enviar_sol_tupa').hide();
    
    $('#div_guarda_ciudadano').toggle();
    $('#div_per_natural_buscar').toggle();
    $('#div_msg_registro').hide();
    $('#div_per_natural').hide();
    $('#div_solicitud_info').hide();
    $('#div_mapa').hide();
    $('#rb_per_natural').click(function() {        
        $('#hd_id').val('');
        $('#txt_dni').val('');
        $('#txt_dni').focus();
        $('#txt_nombres').val('');
        $('#txt_direccion').val('');
        $('#txt_telefono').val('');
        $('#div_per_natural').hide();
        $('#div_per_juridica').hide();
        $('#div_per_natural_buscar').show();
        $('#div_per_juridica_buscar').hide();
        $('#div_msg_re                                                                                                                                                                                                                                                        gistro').hide();
        $('#div_guarda_ciudadano').hide();
        $('#div_mapa').hide();
        $('#div_cbo_servicio').hide();
        $('#div_solicitud_titulo').hide();
        $('#div_motivo').hide();
        $('#div_solicitud_detalle').hide();
        $('#div_solicitud_otros').hide();
        $('#div_observacion').hide();
        $('#div_enviar_sol_info').hide();
        $('#div_enviar_sol_otros').hide();
        $('#div_msg_registro_sol').hide();
    });
    
    $('#div_per_juridica_buscar').toggle();
    $('#div_per_juridica').hide();
    $('#rb_per_juridica').click(function() {
        $('#hd_id').val('');
        $('#txt_ruc').val('');
        $('#txt_ruc').focus();
        $('#txt_rsocial').val('');
        $('#txt_direccion_ruc').val('');
        $('#txt_telefono_ruc').val('');
        $('#txt_email_ruc').val('');
        $('#txt_representante').val('');
        $('#txt_telef_rep').val('');
        $('#div_per_natural').hide();
        $('#div_per_juridica').hide();
        $('#div_per_juridica_buscar').show();
        $('#div_per_natural_buscar').hide();
        $('#div_msg_registro').hide();
        $('#div_guarda_ciudadano').hide();
        $('#div_mapa').hide();
        $('#div_cbo_servicio').hide();
        $('#div_solicitud_titulo').hide();
        $('#div_motivo').hide();
        $('#div_solicitud_detalle').hide();
        $('#div_solicitud_otros').hide();
        $('#div_observacion').hide();
        $('#div_enviar_sol_info').hide();
        $('#div_enviar_sol_otros').hide();
        $('#div_msg_registro_sol').hide();
    });
        
    

</script>

