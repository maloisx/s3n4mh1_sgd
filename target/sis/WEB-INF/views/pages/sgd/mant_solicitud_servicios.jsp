<br>
<br>
<input type="hidden" name="hd_id_sol" id="hd_id_sol" value=""/>
<input type="hidden" name="hd_id" id="hd_id" value=""/><!--administrado-->
<input type="hidden" name="hd_tpersona" id="hd_tpersona" value=""/><!--administrado-->
  
<div class="row input-field col-sm-12">
    <div class="input-field col-sm-12">
        <label  class="active" style=" font-weight: bold;">I. FUNCIONARIO RESPONSABLE DE ENTREGAR LA INFORMACIÓN</label>
    </div>
</div>
<div class="row input-field col-sm-12">
    <div class="input-field col-sm-12">
        <label  class="active">Director de la Unidad de Atención al Ciudadano y Gestión Documental</label>
    </div>
</div>
<div class="row input-field col-sm-12">
    <br>
    <br>
    <div class="input-field col-sm-12">
        <label  class="active">II. DATOS DEL SOLICITANTE</label>
    </div>
</div>
<div class="row input-field col-sm-12">
    <div class="md-radio md-radio-inline col-sm-3">
        <input id="rb_per_natural" type="radio" name="rb_persona"/>
        <label for="rb_per_natural" style="font-size: 12px;">Persona Natural</label>
    </div>
    <div class="md-radio md-radio-inline col-sm-3">
        <input id="rb_per_juridica" type="radio" name="rb_persona"/>
        <label for="rb_per_juridica" style="font-size: 12px;">Persona Jurídica</label>
    </div>
</div>
<!--TIPO DE PERSONA-->
<div class="row input-field col-sm-12">                
    <div class="col-sm-11" id="div_per_natural_buscar">
        <div class="input-field md-radio md-radio-inline col-sm-2">
            <input id="txt_dni" type="text" name="txt_dni"/>
            <label for="txt_dni">D.N.I./C.E./OTRO</label>
        </div>
        <div class="col-sm-2">
            <button id="btn_busca_dni" onclick="sgd_mant_busca_ciudadano_dni(1)" class="btn btn-info btn-sm" >
                BUSCAR
            </button>
            <br>
            <br>
            <br>
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
            <br>
            <br>
            <br>
        </div>
    </div>
</div>

<!--PERSONA NATURAL-->
<div class="row" id="div_per_natural">  
    <div class="row">        
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-6">
                    <div class="input-field col-sm-12">                        
                        <input id="txt_nombres" type="text" name="txt_nombres" style="text-transform: uppercase" />
                        <label id="lb_nombres" for="txt_nombres">Apellidos y Nombres<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>  
                </div>   
                <div class="col-sm-2">
                    <div class="input-field col-sm-12">
                        <select id="cb_dpto_dir" class="form-control selectpicker" name="cb_dpto_dir" data-size="5" data-live-search="true" onchange="sgd_mant_provincia_mostrar(1)">${requestScope['cb_dpto_dir']}</select>
                        <label for="cb_dpto_dir" class="active">Departamento</label>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="input-field col-sm-12">
                        <select id="cb_prov_dir" class="form-control selectpicker" name="cb_prov_dir" data-size="5" data-live-search="true" onchange="sgd_mant_distrito_mostrar(1)">${requestScope['cb_prov_dir']}</select>
                        <label for="cb_prov_dir" class="active">Provincia</label>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="input-field col-sm-12">
                        <select id="cb_dist_dir" class="form-control selectpicker" name="cb_dist_dir" data-size="5" data-live-search="true">${requestScope['cb_dist_dir']}</select>
                        <label for="cb_dist_dir" class="active">Distrito</label>
                    </div>
                </div>
            </div>     
        </div>
    </div>
    <div class="row">
        <br>
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-6">
                    <div class="input-field col-sm-12">                        
                        <input id="txt_direccion" type="text" name="txt_direccion" style="text-transform: uppercase"/>
                        <label id="lb_direccion" for="txt_direccion">Dirección<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>  
                </div>   
                <div class="col-sm-6">
                    <div class="input-field col-sm-12">
                        <input id="txt_email" type="email" name="txt_email" style="text-transform: lowercase" required/>
                        <label id="lb_email" for="txt_email">E-Mail<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                </div>
            </div>     
        </div>     
    </div>
    <div class="row">
        <br>
        <div class=" col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-6">
                    <div class="input-field col-sm-12">
                        <input id="txt_telefono" type="text" name="txt_telefono" required/>
                        <label id="lb_telefono" for="txt_telefono">Teléfono<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="input-field col-sm-12 text-right" id="div_guarda_ciudadano_dni">
<!--                        <div class="input-field col-sm-6 text-right" id="div_guarda_ciudadano_dni">-->
                            <button id="btn_guarda_ciudadano_dni" onclick="sgd_mant_ciudadano_dni_guardar()" class="btn btn-info btn-sm" >
                                REGISTRAR
                            </button>
                        <!--</div>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--PERSONA JURIDICA-->
<div class="row" id="div_per_juridica">
    <div class="row">
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-6">
                    <div class="input-field col-sm-12">
                        <input id="txt_rsocial" type="text" name="txt_rsocial" style="text-transform: uppercase" required/>
                        <label id="lb_rsocial" for="txt_rsocial">Razón social<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="input-field col-sm-5">
                        <select id="cb_sector" class="form-control selectpicker" name="cb_sector" data-size="5" required>${requestScope['cb_sector']}</select>
                        <label for="cb_sector" class="active">Sector<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div> 
                    <div class="input-field col-sm-4">
                        <input id="txt_email_ruc" type="text" name="txt_email_ruc" style="text-transform: lowercase" required/>
                        <label id="lb_email_ruc" for="txt_email_ruc">E-Mail<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                    <div class="input-field col-sm-3">
                        <input id="txt_telefono_ruc" type="text" name="txt_telefono_ruc" required/>
                        <label id="lb_telefono_ruc" for="txt_telefono_ruc">Teléfono<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-6">
                    <br>
                    <br> 
                    <div class="input-field col-sm-4">
                        <select id="cb_dpto_dir_ruc" class="form-control selectpicker" name="cb_dpto_dir_ruc" data-size="5" data-live-search="true" onchange="sgd_mant_provincia_mostrar(2)">${requestScope['cb_dpto_dir']}</select>
                        <label for="cb_dpto_dir_ruc" class="active">Departamento</label>
                    </div>
                    <div class="input-field col-sm-4">
                        <select id="cb_prov_dir_ruc" class="form-control selectpicker" name="cb_prov_dir_ruc" data-size="5" data-live-search="true" onchange="sgd_mant_distrito_mostrar(2)">${requestScope['cb_prov_dir']}</select>
                        <label for="cb_prov_dir_ruc" class="active">Provincia</label>
                    </div>    
                    <div class="input-field col-sm-4">
                        <select id="cb_dist_dir_ruc" class="form-control selectpicker" name="cb_dist_dir_ruc" data-size="5" data-live-search="true"></select>
                        <label for="cb_dist_dir_ruc" class="active">Distrito</label>
                    </div>
                </div>
                <div class="col-sm-6">
                    <br>
                    <br>                    
                    <div class="input-field col-sm-12">
                        <input id="txt_direccion_ruc" type="text" name="txt_direccion_ruc" style="text-transform: uppercase" required/>
                        <label id="lbl_direccion_ruc" for="txt_direccion_ruc">Dirección<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>                    
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-6">
                    <br>
                    <br> 
                    <div class="input-field col-sm-7">
                        <input id="txt_representante" type="email" name="txt_representante" style="text-transform: uppercase" required/>
                        <label id="lb_representante" for="txt_representante">Persona de contacto<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                    <div class="input-field col-sm-5">
                        <input id="txt_dni_rep" type="text" name="txt_dni_rep" required/>
                        <label id="lb_dni_rep" for="txt_dni_rep">DNI Contacto<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>                    
                </div>
                <div class="col-sm-6"> 
                    <br>
                    <br> 
                    <div class="input-field col-sm-2">
                        <input id="txt_telef_rep" type="text" name="txt_telef_rep" required/>
                        <label id="lb_telef_rep" for="txt_telef_rep">Telef.Cont.<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                    <div class="input-field col-sm-6">
                        <input id="txt_email_rep" placeholder="e-mail@dominio.xyz" type="text" name="txt_email_rep" style="text-transform: lowercase" required />
                        <label id="lb_email_rep" for="txt_email_rep">E-Mail Contacto<span style='font-size: 11pt; color: red;'>(*)</span></label>
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
</div>
<!--MENSAJE CONFIRMACIÓN-->
<div class="input-field col-sm-11" id="div_msg_registro" style=" color: red; font-size:110%; font-weight:bold;">
    Registre sus datos.
</div> 
<!--SOLICITUD-->

<div class="row" id="div_infosol">
    <div class="row">
        <br>
        <br>
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-12">
                        <label class="active">III. INFORMACIÓN SOLICITADA:<span style='font-size: 11pt; color: red;'>(*)</span></label>
                        <textarea  id="txt_infosol" name="txt_infosol" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <div class="row">
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-12">
                        <label class="active">IV. DEPENDENCIA DE LA CUAL SE REQUIERE LA INFORMACIÓN:</label>           
                        <select id="cb_funcionario" class="form-control selectpicker" name="cb_funcionario" data-size="5" data-live-search="true">${requestScope['cb_func']}</select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <br>
        <br>
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-12">
                        <label class="active">V. FORMA DE ENTREGA DE LA INFORMACIÓN:<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="col-sm-12" id="div_tipo_entrega_chkb">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <br>
        <br>
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-12">            
                        <label class="active">VI. AUTORIZACIÓN PARA RECIBIR RESPUESTA DE LA SOLICITUD POR CORREO ELECTRÓNICO:<span style='font-size: 11pt; color: red;'>(*)</span></label>
                    </div>
                    <div class="col-sm-12" id="div_rpta_email_chkb">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <br>
        <br>
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-12">
                        <label class="active">VII. OBSERVACIONES:</label>
                        <textarea  id="txt_observacion" name="txt_observacion" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <br>
        <br>
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="input-field col-sm-6">
                        <label class="active"><span style='color: red;'>(*) Campos Obligatorios</span></label>
                    </div>
                    <div class="col-md-6 text-right" id="div_enviar_sol_tupa">
                        <button onclick="sgd_mant_enviar_solicitud_tupa()" class="btn btn-info btn-sm">Enviar Solicitud</button>
                    </div>
                </div>
            </div>
        </div>        
    </div>        
</div>
<div class="row" id="div_nota">
    <div class="col-sm-12">
        <div class="col-sm-12">
            <div class="col-sm-12">
                <div class="input-field col-sm-12">
                   <label>
                       <span style='font-size: 10pt; font-weight: bold; color: black;'>Nota: </span>
                       El plazo para el otorgamiento de respuesta conforme al artículo 11° del Texto Único Ordenado de la Ley N° 27806, 
                       Ley de Transparencia y Acceso a la Información Pública, aprobado mediante Decreto Supremo N° 043-2003-PCM y modificado 
                       por el Decreto Legislativo N° 1353, iniciará al día siguiente de haberse registrado la Solicitud de Acceso a la 
                       Información Pública en el Sistema de Gestión Documentaria - SGD con el correspondiente número de Registro.
                   </label>
                </div>
            </div>
        </div>
    </div>
</div>
                                 
<script>
    $('#div_per_natural_buscar').toggle();
    $('#div_per_juridica_buscar').toggle();
    $('#div_msg_registro').hide();
    $('#div_per_natural').hide();
    $('#div_per_juridica').hide();
    $('#div_infosol').hide();
    
    $('#rb_per_natural').click(function() {        
        $('#div_per_natural_buscar').show();
        $('#div_per_juridica_buscar').hide();
        $('#div_per_natural').hide();
        $('#div_per_juridica').hide();
        $('#hd_id').val('');
        $('#txt_dni').val('');
        $('#div_infosol').hide();
        $("#cb_dpto_dir_ruc").prop('selectedIndex', -1);
        $('#cb_dpto_dir_ruc').selectpicker('refresh');
        $('#cb_prov_dir_ruc').prop('selectedIndex', -1);
        $('#cb_prov_dir_ruc').selectpicker('refresh');
        $('#cb_dist_dir_ruc').prop('selectedIndex', -1);
        $('#cb_dist_dir_ruc').selectpicker('refresh');
    });    
    
    $('#rb_per_juridica').click(function() {        
        $('#div_per_natural_buscar').hide();        
        $('#div_per_juridica_buscar').show();
        $('#div_per_natural').hide();
        $('#div_per_juridica').hide();
        $('#hd_id').val('');
        $('#txt_ruc').val('');
        $('#div_infosol').hide();
        $("#cb_dpto_dir").prop('selectedIndex', -1);
        $('#cb_dpto_dir').selectpicker('refresh');
        $('#cb_prov_dir').prop('selectedIndex', -1);
        $('#cb_prov_dir').selectpicker('refresh');
        $('#cb_dist_dir').prop('selectedIndex', -1);
        $('#cb_dist_dir').selectpicker('refresh');
    });    
</script>