<div id="div_mensaje_ajax"></div>
<div class="row">
<div class="col-md-3">
        <ul class="nav nav-tabs tabs-left">
          <li align="center" class="active"><a href="#doc" data-toggle="tab"><span class="glyphicon glyphicon-file"></span> Datos del Documento</a></li>
          <li align="center"><a href="#lista_doc" data-toggle="tab"><span class="glyphicon glyphicon-duplicate"></span> Documentos del Expediente</a></li>
          <li align="center"><a href="#deriva" data-toggle="tab"><span class="glyphicon glyphicon-send"></span> Derivaciones del Expediente</a></li>
        </ul>
    </div>

    <div class="col-md-9">
        <div class="tab-content">
            <div class="tab-pane active" id="doc">
             
                <div class="row">
                    <div class="input-field col-md-3">
                        <select name="cb_priori" id="cb_priori">
                            <option value="1">Normal</option>
                            <option value="2">Media</option>
                            <option value="3">Alta</option>
                        </select>
                        <label for="cb_priori">Prioridad</label>
                    </div>
                    <!--<div class="col-md-1"></div>-->
                     <div class="input-field col-md-2">
                         <input id="txt_plazo" type="number" class="validate">
                        <label for="txt_plazo">Plazo (días):</label>
                      </div>
                    <div class="col-md-1" ></div>
                    <div class="input-field col-md-3">   
                        <input id ="lb_origen" type="text" value="INTERNO" readonly>
                        <label for="lb_origen" class="active">Origent</label>
                    </div>
                    <div class="input-field col-md-3">   
                        <input id ="lb_estado" type="text" value="PENDIENTE" readonly>
                        <label for="lb_estado" class="active">Estado</label>
                    </div>
                </div>
                <!--<hr>-->
                <div class="row">
                     <div class="input-field col-md-3">
                        <input id ="txt_cut" type="text" >
                        <label for="txt_cut">Nro. de Cut</label>
                      </div>
                    <!--<div class="col-md-1"></div>-->
                     <div class="input-field col-md-3">
                        <input id="txt_fec_reg" type="date" class="datepicker">
                        <label for="txt_fec_reg">Fecha de Registro</label>
                      </div>
<!--                </div>
                <div class="row">-->
                     <div class="input-field col-md-3">
                        <select name="cb_tema" id="cb_tema">
                             <option value="1">Normal</option>
                             <option value="2">Media</option>
                             <option value="3">Alta</option>
                         </select>
                        <label for="cb_tema">Tema</label>
                      </div>
                    <!--<div class="col-md-1"></div>-->
                      <div class="input-field col-md-3">
                        <input id="txt_tip_acceso" type="text" class="">
                        <label for="txt_tip_acceso">Tipo de Acceso</label>
                      </div>
                </div>
                
                <div class="row">
                     <div class="input-field col-md-3">
                        <select name="cb_tip_tramite" id="cb_tip_tramite">
                             <option value="1">Normal</option>
                             <option value="2">Media</option>
                             <option value="3">Alta</option>
                        </select>
                        <label for="cb_tip_tramite">Tipo de Tramite</label>
                    </div>
                    <!--<div class="col-md-1"></div>-->
                    <div class="input-field col-md-6">
                        <select name="cb_procedimiento" id="cb_procedimiento">
                             <option value="1">You forgot the closing div on the form-group element that wraps this textareas (99 dias)</option>
                             <option value="2">Media (99 dias)</option>
                             <option value="3">The whitespace in the text area tags is adding that same whitespace to the textarea input when you put your curser in there. Textareas are whitespace sensitive, so this particular textarea should look like (99 dias)</option>
                         </select>
                        <label for="cb_procedimiento">Procedimiento</label>
                    </div>
                    <!--<div class="col-md-1"> </div>-->
                      <div class="input-field col-md-3">
                          <input id="txt_nro_doc_externo" type="text" class=""> 
                        <label for="txt_nro_doc_externo" class="">Nro Doc Externo:</label>
                      </div>
                </div>
                <hr>
                
                <div class="row">
                     <div class="input-field col-md-3">
                         <select name="cb_tipodoc" id="cb_priori">
                             <option value="1">Oficio</option>
                             <option value="2">Informe</option>
                             <option value="3">Memorandum</option>
                         </select>
                        <label for="cb_tipodoc">Tipo de documento</label>
                      </div>
                    <!--<div class="col-md-1"></div>-->
                     <div class="input-field col-md-2">
                        <input id="lb_nrodoc" type="number" class="validate">
                        <label for="lb_nrodoc">N° Doc.</label>
                    </div>
                    <div class="col-md-1" ></div>
                      <div class="input-field col-md-3">
                            <input id="lb_fecdoc" type="date" class="datepicker">
                            <label for="lb_fecdoc" class="">Fecha del documento</label>
                      </div>
                      <div class="input-field col-md-3">   
                          <input id ="lb_folios" type="text" value="">
                          <label for="lb_folios" class="">N° Folios</label>
                      </div>
                </div>
                <div class="row">
                     <div class="input-field col-md-6">
                        <input id="lb_destino" type="text" class="validate">
                        <label for="lb_destino" class="active">Institución/Unid.Org.</label>
                    </div>
                    <div class="input-field col-md-6">
                        <input id="lb_remite" type="text" class="validate">
                        <label for="lb_remite" class="active">Sr./Sra.</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col-md-6">
                        <select class="form-control selectpicker" data-size="5" name="cb_destino" id="cb_destino"  data-live-search="true">
                             <option value="1">OTI </option>
                             <option value="2">SECRETARIA GENERAL</option>
                             <option value="3">PRESIDENCIA EJECUTIVA</option>
                        </select>    
                        <label for="cb_destino" class="active">Destinatario</label>
                    </div>
                    <div class="input-field col-md-6">
                        <input id="lb_receptor" type="text" class="validate">
                        <label for="lb_receptor" class="active">Sr./Sra.</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 input-field ">                        
                        <textarea  id="txta_asunto" class="materialize-textarea" type="text"></textarea>
                        <label for="txta_asunto">Asunto</label>
                    </div>
                    <div class="col-md-6 input-field ">
                        <textarea  id="txta_asunto" class="materialize-textarea" type="text"></textarea>
                        <label for="txta_obs">Observaciones</label>
                    </div>
                </div>
                    <form enctype="multipart/form-data">
                   <div class="input-field" style="height:320px">
                        <label>Buscar Archivo</label>
                        <input id="file-es" name="file-es[]" type="file" multiple>
                    </div>
                    </form>
                
                
                <br><br><br><br><br><br><br><br>
                <button id="guardar" onclick="sgd_mant_expediente_guardar()" class="btn btn-info" >
                    Guardar       
                </button>        
            </div>
            <div class="tab-pane" id="lista_doc">Documentos del Expediente</div>
            <div class="tab-pane" id="deriva">Derivaciones del Expediente</div>    
        </div>  
    
                
    </div>    
</div>

<script>
    $('#file-es').fileinput({
        language: 'es',
        uploadUrl: '#',
        allowedFileExtensions: ['pdf']
    });
</script>


