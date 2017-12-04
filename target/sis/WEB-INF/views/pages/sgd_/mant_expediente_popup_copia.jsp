<div class="row">
    <div class="col-md-3" >
        <ul class="nav nav-tabs tabs-left">
            <li class="active" id="expediente">
                <a id="a_expediente" href="#doc" data-toggle="tab"><span class="glyphicon glyphicon-file"></span> Datos del Documentos</a>
            </li>
            <li id="lista_doc">
                <a id="a_lista_doc" href="#div_lista_doc" data-toggle="tab"><span class="glyphicon glyphicon-duplicate"></span> Documentos del Expediente</a>
            </li>
            <li id="lista_deriva">
                <a id="a_lista_der" href="#div_lista_deriva" data-toggle="tab"><span class="glyphicon glyphicon-send"></span> Derivaciones del Expediente</a>
            </li>
        </ul>
    </div>

    <div class="col-md-9">
        <div class="tab-content" >
<!--PESTANA CREAR EXPEDIENTE Y DOCUMENTO-->            
            <div class="tab-pane active" id="doc">
                <div class="row">
                   
                    <div class="col-md-6">
                        <div class="row">
                            <div class="input-field col-md-2">
                                <input id ="txt_cut" type="text" value="${requestScope['cut']}" readonly/>
                                <label for="txt_cut" class="active">N° CUT</label>
                                <input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id']}" />
                                <input type="hidden" name="hd_userreg" id="hd_userreg" value="${requestScope['codUser']}" />
                                <input type="hidden" name="hd_perfil" id="hd_perfil" value="${requestScope['perfil']}" />
                                <input type="hidden" name="hd_iddoc" id="hd_iddoc" value="${requestScope['doc']}"  />
                                <input type="hidden" name="hd_doc_modif" id="hd_doc_modif" value="${requestScope['doc_modif']}" />
                                <input type="hidden" name="hd_doc_nuevo" id="hd_doc_nuevo" value="${requestScope['doc_nuevo']}" />
                            </div>
                            <div class="input-field col-md-2">
                                <input id ="txt_per" type="text" value="${requestScope['per']}" ${requestScope['obj_readonly_form']}/>
                                <label for="txt_per" class="active">Periodo</label>
                            </div>
                            <div class="input-field col-md-4">
                                <input id="txt_fecreg" type="text" value="${requestScope['fecreg']}" ${requestScope['obj_readonly_form']}/>
                                <label for="txt_fecreg" class="active">Fecha Reg.</label>
                            </div>
                            <div class="input-field col-md-4">   
                                <select name="cb_condicion" id="cb_condicion" class="form-control selectpicker " disabled>${requestScope['cb_condicion']}</select>
                                <label for="cb_condicion" class="active">Condición</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col-md-4">
                                <select name="cb_priori" id="cb_priori" class="form-control selectpicker" onchange="sgd_mant_prioridad_cargar_txt()" data-size="3" ${requestScope['obj_disabled_form']} >${requestScope['cb_priori']}</select>
                                <label for="cb_priori" class="active">Prioridad</label>
                            </div>
                            <div class="input-field col-md-4">
                                <input id="txt_plazo" type="number" min="1" value="${requestScope['plazo']}" ${requestScope['obj_readonly_form']}/>
                                <label for="txt_plazo" class="active">Plazo (días):</label>
                            </div>
                            <div class="input-field col-md-4">
                                <select name="cb_alcance" id="cb_alcance" class="form-control selectpicker" ${requestScope['obj_disabled_form']}>${requestScope['cb_alcance']}</select>
                                <label for="cb_alcance" class="active">Alcance</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col-md-8">
                                <select name="cb_tema" id="cb_tema" class="form-control selectpicker" data-size="3" ${requestScope['obj_disabled_form']}>${requestScope['cb_tema']}</select>
                                <label for="cb_tema" class="active">Tema</label>
                            </div>
                        </div> 
                    </div>
                             
                    <div class="col-md-1"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="input-field col-md-4">
                                <select name="cb_tramite" id="cb_tramite" class="form-control selectpicker" onchange="sgd_mant_procedimiento_cargar_cbo();" data-size="3" ${requestScope['obj_disabled_form']}>${requestScope['cb_tramite']}</select>
                                <label for="cb_tramite" class="active">Tipo de Tramite</label>
                            </div>
                            <div class="input-field col-md-4">
                                <select name="cb_origen" id="cb_origen" class="form-control selectpicker" disabled>${requestScope['cb_origen']}</select>
                                <label for="cb_origen" class="active">Origen</label>
                            </div>
                        </div>
                        <div class="row" style="padding-top: 13px">
                            <div class="input-field col-md-11">
                                <select name="cb_procedimiento" id="cb_procedimiento" class="form-control selectpicker" data-size="3" ${requestScope['obj_disabled_form']}>${requestScope['cb_procedimiento']}</select>
                                <label for="cb_procedimiento" class="active">Procedimiento</label>
                            </div>                            
                        </div>
                        <div class="row" style="padding-top: 13px">
                            <div class="input-field col-md-7">
                                <input id="txt_cutext" type="text" class="text-uppercase" value="${requestScope['cutext']}" ${requestScope['obj_readonly_form']}> 
                                <label for="txt_cutext" class="${requestScope['obj_active_form']}">N° CUT Externo</label>
                            </div>
                        </div>
                    </div>  
                </div>
                <hr  style="width:96%"/>
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="input-field col-md-5">
                                <input type="hidden" name="txt_doc" id="txt_doc" value="${requestScope['doc']}" />
                                <input type="hidden" name="txt_flujo" id="txt_flujo" value="${requestScope['flujo']}" />
                                <select name="cb_clsfdoc" id="cb_clsfdoc" class="form-control selectpicker" data-size="3" ${requestScope['obj_disabled_form']}>${requestScope['cb_clsfdoc']}</select>
                                <label for="cb_clsfdoc" class="active">Tipo de documento</label>
                            </div>
                            <div class="input-field col-md-2">
                                 <input name="txt_nrodoc" id="txt_nrodoc" type="number" min="1" value="${requestScope['nrodoc']}" required>
                                <label for="txt_nrodoc" class="${requestScope['obj_active_form']}">N°Doc.</label>
                            </div>
                            <div class="input-field col-md-3">
                                <input id="txt_fec_doc" type="text" class="datepicker" value="${requestScope['fec_doc']}">
                                <label for="txt_fec_doc" class="active">Fecha Doc.</label>
                                <!--fecha de registro del documento-->
                                <input type="hidden" name="hd_fecreg_doc" id="hd_fecreg_doc" value="${requestScope['fecreg']}" />
                            </div>
                            <div class="input-field col-md-2">   
                                <input name="txt_folio" id ="txt_folio" type="number" min="1" value="${requestScope['folio']}">
                                <label for="txt_folio" class="${requestScope['obj_active_form']}">Folios</label>
                            </div>                            
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="input-field col-md-12">
                                <select name="cb_remite" id="cb_remite" class="form-control selectpicker" data-live-search="true" data-size="3" onchange="sgd_mant_destino_cargar_txt(this.value,'r'); hd_idrem.value=this.value;" ${requestScope['obj_disabled_form_rmte']}>${requestScope['cb_rmte']}</select>                                       
                                <label for="cb_remite" class="active" >Remitente: Institución/Unid.Org. </label>
                            </div>
                        </div>
                        <div class="row" style="padding-top:25px">
                            <div class="input-field col-md-12">
                                <input required name="txt_remite" id="txt_remite" type="text" class="validate" value="${requestScope['txt_rmte']}" ${requestScope['obj_readonly_form_rmte']}/>
                                <label id="lbl_txt_remite" for="txt_remite" class="active">Remitente: Sr./Sra.</label>
                                <input type="hidden" name="hd_idrem" id="hd_idrem" value="${requestScope['hd_id_rem']}" />
                            </div> 
                        </div>
                    </div>  
                    <div class="col-sm-1"></div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="input-field col-md-11">                                
                                <!--<select name="cb_destino" id="cb_destino" class="form-control selectpicker" data-live-search="true" data-size="3" onchange="txt_destino.value=this.options[this.selectedIndex].innerHTML; hd_iddes.value=this.value;">${requestScope['cb_destino']}</select>-->
                                <select name="cb_destino" id="cb_destino" class="form-control selectpicker" data-live-search="true" data-size="3" onchange="sgd_mant_destino_cargar_txt(this.value,'d'); hd_iddes.value=this.value;" ${requestScope['obj_disabled_form']}>${requestScope['cb_destino']}</select>
                                <label for="cb_destino" class="active">Destinatario: Institución/Unid.Org.</label>
                            </div>
                        </div>
                        <div class="row" style="padding-top:25px">
                            <div class="input-field col-md-11">
                                <input required name="txt_destino" id="txt_destino" type="text" class="validate" value="${requestScope['txt_destino']}" ${requestScope['obj_readonly_form']}/>
                                <label id="lbl_txt_destino" for="txt_destino" class="active">Destinatario Sr./Sra.</label>
                                <input type="hidden" name="hd_iddes" id="hd_iddes" value="${requestScope['hd_id_des']}" />
                            </div>    
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col-md-6">
                        <div class="row">
                            <div class="input-field col-md-12">
                                <textarea  id="txt_asunto" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1" ${requestScope['obj_readonly_form']}>${requestScope['asunto']}</textarea>
                                <label for="txt_asunto" class="${requestScope['obj_active_form']}">Asunto</label>
                            </div>
                        </div>  
                    </div> 
                    <div class="input-field col-md-1"></div>
                    <div class="input-field col-md-5">
                        <div class="row">
                            <div class="input-field col-md-11">
                                <textarea  id="txt_obs" class="materialize-textarea text-uppercase" type="text" ${requestScope['obj_readonly_form']}>${requestScope['observacion']}</textarea>
                                <label for="txt_obs" class="${requestScope['obj_active_form']}">Observaciones</label>
                            </div>
                        </div>
                    </div>
                </div>
                <hr  style="width:96%"/>            
                <div class="row input-field col-md-5" id="div_mant_adjunto_tbl">
                    <script>
                        sgd_mant_adjuntos_cargar($('#hd_iddoc').val());                            
                    </script>                                                                
                </div>
                <div class="row form-group" >
                    <div class="col-md-12" style=" width:90%;">
                        <form enctype="multipart/form-data">
                            <label>Buscar Archivo</label>
                            <input id="file-sgd" name="file-sgd[]" type="file" multiple class="form-control" />
                        </form>
                    </div>
                </div> 
                
                <div class="row form-group text-right" >
                    <div class="col-md-12" >
                        <button id="guardar" onclick="sgd_mant_expediente_guardar()" class="btn btn-info btn-sm" >
                            Guardar       
                        </button> 
                    </div>    
                </div>
            </div>
<!--PESTANA LISTA DE DOCUMENTOS-->            
            <div class="tab-pane col-md-11" id="div_lista_doc">
                <div class="tab-pane col-md-11" id="div_mant_lista_docs_cargar_tbl">
                    <script>
                        sgd_mant_lista_docs_cargar();
                    </script>  
                </div>    
            </div>  
<!--PESTANA LISTA DE DERIVACION-->                                
            <div class="tab-pane col-md-11" id="div_lista_deriva">
                <div class="tab-pane col-md-11" id="div_mant_lista_deriva_cargar_tbl">
                    <script>
                        sgd_mant_lista_deriva_cargar();
                    </script> 
                </div>      
            </div>                     
        </div>             
    </div>                      
</div>
<div id="div_mensaje_ajax" class="text-success "></div>

<script>
    $('#file-sgd').fileinput({
        language: 'es',
        uploadUrl: '#',
        allowedFileExtensions: ['pdf'],
        showUpload: false,
        //mainClass: "input-group-lg",
        rtl: true
    });

//activa y desactiva tabs
    $( "#lista_doc" ).on( "click", function() {
        $('#div_lista_doc').attr('class','tab-pane active');
        $('#lista_doc').attr('class','tab-pane active');        
        
        $('#doc').attr('class','tab-pane');
        $('#expediente').attr('class','');
        
        $('#div_lista_deriva').attr('class','tab-pane');
        $('#lista_deriva').attr('class','');         
    });
    
//carga el txt_plazo    
    $('#cb_priori').load(sgd_mant_prioridad_cargar_txt());
    
//carga el combo procedimiento
if ($('#txt_cut').val() == ''){
    $('#cb_tramite').load(sgd_mant_procedimiento_cargar_cbo());
}     

if ($('#hd_doc_nuevo').val() == 0){
    $('#div_mant_adjunto_tbl').toggle();
}

$('#cb_clsfdoc option:first-child').attr('selected', 'selected');
   
</script>


