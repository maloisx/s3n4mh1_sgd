<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_idinv" id="hd_idinv" value="${requestScope['idinv']}" />
<br>
<div class="row">
    <div class="col-sm-3" >
        <ul class="nav nav-tabs tabs-left">
            <li id="lista_doc" class="active" >
                <a id="a_lista_doc" href="#div_lista_doc" data-toggle="tab"><span class="glyphicon glyphicon-duplicate"></span>Investigados</a>
            </li>
            <li id="expediente">
                <a id="a_expediente" href="#doc" data-toggle="tab"><span class="glyphicon glyphicon-file"></span>Inf.Investigado</a>
            </li>
        </ul>
    </div>

    <div class="col-sm-9">
        <div class="tab-content" >
<!--PESTANA LISTA DE INVESTIGADO-->            
            <div class="tab-pane col-sm-12 active" id="div_lista_doc">
                
                <div class="row">
                    <div class="input-field col-sm-2">
                        <input type="text" name="txt_nroexp" id="txt_nroexp" value="${requestScope['nroexp']}" style="text-align: center; color: red; font-size:120%; font-weight:bold;" />                   
                        <label for="txt_nroexp" class="active">N° Exp:</label> 
                    </div>
                    <div class="tab-pane col-sm-11">                        
                    </div>    
                </div>
                    
                <div class="row">
                    <div class="tab-pane col-sm-12" id="div_mant_investigados_tbl">
                        <script>
                            pad_mant_investigados_tbl($('#txt_nroexp').val());
                        </script>
                    </div> 
<!--                    <div class="tab-pane col-sm-1">                        
                    </div>-->
                </div>    
            </div>                    
<!--PESTANA CREAR EXPEDIENTE Y DOCUMENTO-->
            <div class="tab-pane col-sm-12" id="doc">
                <div class="row">
                    <div class="input-field col-sm-2">
                        <input type="text" name="txt_nroexp1" id="txt_nroexp1" value="${requestScope['nroexp']}"  style="text-align: center; color: red; font-size:120%; font-weight:bold;" />                   
                        <label for="txt_nroexp1" class="active">N° Exp:</label> 
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="input-field col-sm-5">
                        <input name="txt_investigado" id="txt_investigado" type="text" />
                        <label for="txt_investigado" class="active">Investigado:</label>
                    </div>
                    <input type="hidden" name="cb_investigado" id="cb_investigado"/>
                    <div class="input-field col-sm-5">
<!--                        <input name="txt_cargo" id="txt_cargo" type="text" />
                        <label for="txt_cargo" class="active">Cargo:</label>-->
                        <select name="cb_cargo" id="cb_cargo" class="form-control selectpicker" data-size="5" onchange="">${requestScope['cargo']}</select>                    
                        <label for="cb_cargo" class="active">Cargo:</label> 
                    </div>
                    <input type="hidden" name="txt_idcargo" id="txt_idcargo"/>
                </div>
                <hr  style="width:90%"/>
                <br><br>  
<!--                <div class="row">
                    <div class="col-sm-12">
                        <div class="row">                            
                            <div class="input-field text-right col-sm-10">
                                <select name="cb_norma_jur" id="cb_norma_jur" class="form-control selectpicker" data-size="5" onchange="pad_mant_literal_norma_consulta();">${requestScope['norma']}</select>                    
                                <label for="cb_norma_jur" class="active">Norma Jurídica:</label> 
                            </div>                 
                            <div class="input-field col-sm-1">
                            </div>
                        </div>
                    </div>
                </div>    
                <br>       
                <br>    -->
                <div class="row">
                    <div class="input-field col-sm-10">
                        <select name="cb_falta" id="cb_falta" class="form-control selectpicker" data-size="5" multiple data-live-search="true" onchange="charge_list_boostrap_select('cb_falta','cb_faltasel')">${requestScope['falta']}</select>                    
                        <label for="cb_falta" class="active">Seleccione Falta:</label> 
                    </div>
                </div>
                <br><br>
                <div class="row">
                    <div class="input-field col-md-10">
                        <select name="cb_faltasel" id="cb_faltasel" class="form-control selectpicker" data-size="5" multiple data-live-search="true" onchange="delete_cascade_list_boostrap_select('cb_faltasel','cb_falta')"></select>    
                        <label for="cb_faltasel" class="active">Faltas seleccionadas:</label>
                    </div>
                </div>
                <br><br>
                <div class="row">
                    <div class="input-field col-sm-5">
                        <select name="cb_sancion" id="cb_sancion" class="form-control selectpicker" data-size="3">${requestScope['sancion']}</select>                    
                        <label for="cb_sancion" class="active">Sanción:</label> 
                    </div>
                    <div class="input-field col-sm-5">
                        <input name="txt_dias" id="txt_dias" type="text" />                 
                        <label for="txt_dias" class="active">Dias de sanción:</label> 
                    </div>
                </div>
                <br><br> 
                <div class="row">
                    <div class="input-field col-md-5">
                        <select name="cb_medida_caut" id="cb_medida_caut" class="form-control selectpicker" data-size="3">${requestScope['medcaut']}</select>    
                        <label for="cb_medida_caut" class="active">Medida Cautelar:</label>
                    </div>                    
                    <div class="input-field col-md-5">
                        <select name="cb_recurso" id="cb_recurso" class="form-control selectpicker" data-size="3">${requestScope['recurso']}</select>    
                        <label for="cb_recurso" class="active">Recurso:</label>
                    </div>
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="input-field col-sm-10" >
                        <textarea  id="txt_observacion" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1"></textarea>
                        <label for="txt_observacion" class="active">Observación</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field text-right col-sm-10" >                        
                        <div class="row col-sm-12" id="div_mant_adjunto_tbl">
                            <button onclick="pad_mant_investigado_modificar_guardar()" class="btn btn-info btn-sm">Guardar</button> 
                            <button id="volver" onclick="$('#lista_doc').attr('class','tab-pane active'); $('#div_lista_doc').attr('class','tab-pane active'); $('#div_lista_doc').attr('aria-expanded','true'); $('#expediente').attr('class','tab-pane'); $('#doc').attr('class','tab-pane');" class="btn btn-info btn-sm">Volver</button>  
                            <button onclick="$.colorbox.close()" class="btn btn-info btn-sm">Cerrar</button> 
                        </div>
                    </div>
                </div>        
                        
                <div class="row form-group text-right" >
                    <div class="col-sm-12" >                        
<!--                        <button id="btn_guardar" onclick="sgd_mant_expediente_guardar()" class="btn btn-info btn-sm" >
                            Guardar       
                        </button> -->
                    </div>                      
                </div>
            </div>                       
                              
        </div>             
    </div>                      
</div>


<script>
<!--//activa y desactiva tabs-->
    $( "#lista_doc" ).on( "click", function() {
        $('#div_lista_doc').attr('class','tab-pane active');
        $('#lista_doc').attr('class','tab-pane active');        
        
        $('#doc').attr('class','tab-pane');
        $('#expediente').attr('class',''); 
    });

charge_list_boostrap_select('cb_falta','cb_faltasel');
</script>


<!--charge_list_boostrap_select('cb_uo_ini','cb_uo_fin');-->

<!--<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-sm-1">
                <input type="text" name="txt_nroexp" id="txt_nroexp" value="${requestScope['nroexp']}" style="text-align: center; color: red; font-size:120%; font-weight:bold;" />                   
                <label for="txt_nroexp" class="active">N° Exp:</label> 
            </div>
            <div class="input-field text-right col-sm-4">
                <select name="cb_etapa" id="cb_etapa" class="form-control selectpicker " data-size="4">${requestScope['etapa']}</select>                    
                <label for="cb_etapa" class="active">Etapa:</label> 
            </div>    
            <div class="input-field col-md-1">
            </div>
        </div>
    </div>
</div>    
<br> 
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>
            <div class="col-sm-10" id="div_mant_expedientes_pad_docs_tbl">
                <script>
                    mant_expedientes_pad_docs_tbl($('#txt_nroexp').val());
                </script>  
            </div> 
            <div class="input-field col-sm-1">
            </div>
        </div>
    </div>
</div>    
<br> <br>-->
   