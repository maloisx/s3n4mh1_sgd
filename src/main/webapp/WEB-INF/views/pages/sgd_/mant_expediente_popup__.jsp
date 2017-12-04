<div id="div_mensaje_ajax"></div>

<div class="col-xs-2">
        <ul class="nav nav-tabs tabs-left">
          <li align="center" class="active"><a href="#doc" data-toggle="tab"><span class="glyphicon glyphicon-file"></span> Datos del<br>Documento</a></li>
          <li align="center"><a href="#lista_doc" data-toggle="tab"><span class="glyphicon glyphicon-duplicate"></span> Documentos del<br>Expediente</a></li>
          <li align="center"><a href="#deriva" data-toggle="tab"><span class="glyphicon glyphicon-send"></span> Derivaciones del<br>Expediente</a></li>
        </ul>
    </div>

    <div class="col-xs-10">
        <div class="tab-content">
            <div class="tab-pane active" id="doc">
                <div class="col-xs-1">
                </div>
                <div class="col-xs-4">
                    <div class="form-group">
                        <lable>Prioridad:</lable>       
                        <select name="cb_priori" id="cb_priori" class="form-control">${requestScope['cb_priori']}</select>
                    </div>
                    <div class="form-group">
                        <lable>N° de CUT:</lable>
                        <input type="text" name="txt_cut" id="txt_cut" value="${requestScope['cut']}" class="" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        <lable>Tema:</lable> 
                        <select name="cb_tema" id="cb_tema" class="form-control selectpicker">${requestScope['cb_tema']}</select>
                    </div>
                    <div class="form-group">
                        <lable>Seleccione el origen:</lable>
                        <select name="cb_origen" id="cb_origen" class="form-control selectpicker">${requestScope['cb_origen']}</select>
                    </div>
                    <div class="form-group">
                        <lable>Documento externo:</lable>
                        <input type="text" name="txt_docext" id="txt_docext" value="${requestScope['docext']}" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <lable>Estado del expediente:</lable>
                        <input type="text" name="txt_estado" id="txt_estado" value="${requestScope['estado']}" class="form-control"/>
                    </div>
                    
                    <div class="form-group">
                        <lable>Adjunto físico:</lable>
                        <input type="checkbox" name="chk_fisico" id="chk_fisico"/>
                    </div>
                    <div class="form-group">
                        <lable>Tipo de Documento:</lable>
                        <select name="cb_documento" id="cb_documento" class="form-control selectpicker">${requestScope['cb_documento']}</select>                 
                    </div>
                    <div class="form-group">
                        <lable>Fecha de Documento:</lable>  
                        <input type="date" name="dt_fecdoc" id="dt_reg" value="${requestScope['fec_doc']}" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <lable>Remite:</lable>
                        <br>
                        <lable>Institución/Unidad Orgánica:</lable>
                        <input type="text" name="txt_emi" id="txt_emi" value="${requestScope['emisor']}" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <lable>Sr./Sra.:</lable>
                        <input type="text" name="txt_persona" id="txt_persona" value="${requestScope['persona']}" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <!-- <lable>Asunto:</lable> -->
                        <textarea  name="txt_asunto" id="txt_asunto" value="${requestScope['asunto']}" class="form-control" placeholder="Inserte el asunto"></textarea>
                    </div> 
                </div>
                <div class="col-xs-2">
                </div>
                <div class="col-xs-4">
                    <div class="form-group">
                        <lable>Plazo (días):</lable>
                        <input type="txt_plazo" name="txt_plazo" id="txt_cut" value="${requestScope['plazo']}" class="form-control" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        <lable>Fecha de registro:</lable>
                        <input type="date" name="dt_fecreg" id="dt_fecreg" value="${requestScope['fec_registro']}" class="form-control" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        <lable>Tipo de acceso:</lable>
                        <select name="cb_acceso" id="cb_acceso" class="form-control selectpicker">${requestScope['cb_acceso']}</select>
                    </div>
                    <div class="form-group">
                        <lable>Tipo de trámite:</lable>
                        <select name="cb_tramite" id="cb_tramite" class="form-control selectpicker">${requestScope['cb_tramite']}</select>
                    </div>
                    <div class="form-group">
                        <lable>Tipo de procedimiento:</lable>
                        <select name="cb_procedi" id="cb_procedi" class="form-control selectpicker">${requestScope['cb_procedi']}</select>
                    </div>
                    <div class="form-group">
                        <lable>Plazo de respuesta:</lable>
                        <input type="txt_plazoresp" name="txt_plazoresp" id="txt_cut" value="${requestScope['plazoresp']}" class="form-control" readonly="readonly"/>
                    </div>
                    
                    <br>
                    <br>
                    <div class="form-group">
                        <lable>N° de documento:</lable>
                        <input type="txt_nrodoc" name="txt_nrodoc" id="txt_cut" value="${requestScope['nrodoc']}" class="form-control" readonly="readonly"/>
                    </div>
                    
                    
                    
                </div>
                <div class="col-xs-1">
                </div>
            
                <button id="guardar" onclick="sgd_mant_expediente_guardar()" class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" data-hover="dropdown">
                    Guardar       
                </button>        
            </div>
            <div class="tab-pane" id="lista_doc">Documentos del Expediente</div>
            <div class="tab-pane" id="deriva">Derivaciones del Expediente</div>    
        </div>  
    
                
    </div>    
    


