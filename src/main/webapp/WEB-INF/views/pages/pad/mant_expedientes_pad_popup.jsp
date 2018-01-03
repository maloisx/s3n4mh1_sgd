<!--<div id="div_mensaje_ajax" class="text-success"></div>-->    
<input type="hidden" name="hd_iddoc" id="hd_iddoc" value="${requestScope['doc']}" />
<br>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-sm-1">
                <input type="text" name="txt_nroexp" id="txt_nroexp" value="${requestScope['nroexp']}" style="text-align: center; color: red; font-size:120%; font-weight:bold;" />                   
                <label for="txt_nroexp" class="active">N° Exp:</label> 
            </div>
            <div class="input-field col-sm-1">
                <input name="txt_fecharecep" id="txt_fecharecep" type="text" class="datepicker" value="${requestScope['fecharecep']}" style="text-align: center; font-size:120%; font-weight:bold;" />
                <label for="txt_fecharecep" class="active">Fecha Recepción:</label>
            </div>
            <div class="input-field col-sm-1">
                <input name="txt_fecpresc_iniPAD" id="txt_fecpresc_iniPAD" type="text" value="" readonly style="text-align: center; color: red; font-size:120%; font-weight:bold;"/>
                <label for="txt_fecpresc_iniPAD" class="active">Fecha Prescr. Inicio PAD:</label>
            </div>
            <div class="input-field col-sm-3">
            </div>
            <div class="input-field text-right col-sm-4">
                <select name="cb_etapa" id="cb_etapa" class="form-control selectpicker " data-size="4" disabled>${requestScope['etapa']}</select>                    
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
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-sm-3">
                <select name="cb_denunciante" id="cb_denunciante" class="form-control selectpicker" data-live-search="true" data-size="5">${requestScope['persona']}</select>                    
                <label for="cb_denunciante" class="active">Denunciante:</label> 
            </div> 
            <div class="input-field text-right col-sm-2">
                <select name="cb_dependencia" id="cb_dependencia" class="form-control selectpicker" data-live-search="true" data-size="5">${requestScope['dependencia']}</select>                    
                <label for="cb_dependencia" class="active">Dependencia:</label> 
            </div> 
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-sm-4">
                <select name="cb_abogado" id="cb_abogado" class="form-control selectpicker " data-size="4">${requestScope['abogado']}</select>
                <label for="cb_abogado" class="active">Abogado:</label>
            </div>      
            <div class="input-field col-sm-1">
            </div>
        </div>
    </div>
</div>              
<br>
<hr  style="width:100%"/>                
<br>                
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-sm-3">
                <select name="cb_documento" id="cb_documento" class="form-control selectpicker" data-live-search="true" data-size="5">${requestScope['clsfdoc']}</select>                    
                <label for="cb_documento" class="active">Tipo Documento:</label> 
            </div>
            <div class="input-field text-right col-sm-2">
                <input name="txt_nrodoc" id="txt_nrodoc" type="number" min="0" value="${requestScope['nrodoc']}" />                   
                <label for="txt_nrodoc" class="active">N° Doc:</label> 
            </div>
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field col-sm-1">
                <input name="txt_fechadoc" id="txt_fechadoc" type="text" class="datepicker" value="${requestScope['fecdoc']}"  />
                <label for="txt_fechadoc" class="active">Fec.Doc</label>
            </div>
            <div class="input-field col-sm-1">
                <input name="txt_folio" id="txt_folio" type="number" min="1" value="${requestScope['folio']}" />                   
                <label for="txt_folio" class="active">Folios:</label> 
            </div>
            <div class="input-field col-sm-1">
                <input name="txt_plazo" id="txt_plazo" type="number" min="1" value="${requestScope['plazo']}" placeholder="Días"/>                   
                <label for="txt_plazo" class="active">Plazo Rpta:</label> 
            </div>     
            <div class="input-field col-sm-1">
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
            <div class="input-field text-right col-sm-5">
                <select name="cb_remite" id="cb_remite" class="form-control selectpicker" data-live-search="true" data-size="5">${requestScope['persona']}</select>                    
                <label for="cb_remite" class="active">Remite:</label> 
            </div>
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-sm-4">
                <select name="cb_destino" id="cb_destino" class="form-control selectpicker" data-live-search="true" data-size="5">${requestScope['persona']}</select>                    
                <label for="cb_destino" class="active">Destino:</label> 
            </div>
            <div class="input-field col-sm-1">
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
            <div class="input-field col-sm-5">
                <textarea  id="txt_asunto" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1">${requestScope['asunto']}</textarea>
                <label for="txt_asunto" class="active">Asunto</label>
            </div>     
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field col-sm-4">
                <textarea  id="txt_observacion" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1">${requestScope['observacion']}</textarea>
                <label for="txt_observacion" class="active">Observación</label>
            </div>            
            <div class="input-field col-sm-1">
            </div>
        </div>
    </div>
</div>    
<br> 
<br>             
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field col-sm-5" style=" width:43%;">                        
                <form enctype="multipart/form-data">
                    <label>Buscar Archivo</label>
                    <input id="file-pad" name="file-pad[]" type="file" multiple class="form-control"  />
                </form>
            </div>
            <div class="input-field col-sm-1">
            </div>            
            <div class="input-field text-right col-sm-4" >
                <div class="row col-sm-12" id="div_mant_adjunto_tbl">
                    <script>
                        pad_mant_adjuntos_cargar($('#hd_iddoc').val());  <!--CARGA ADJUNTOS-->                          
                    </script>
                </div>                        
                <div class="row col-sm-12" id="div_mant_adjunto_tbl">
                    <button onclick="pad_mant_expedientes_pad_guardar()" class="btn btn-info btn-sm">Guardar</button>
                </div>                        
            </div>             
            <div class="input-field col-sm-1">
            </div>
        </div>
    </div>
</div>    
<!--<br>--> 
<!--<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>        
            <div class="input-field text-right col-md-10">
                <button onclick="pad_mant_expedientes_pad_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             -->

<script>
    $('#file-pad').fileinput({
        language: 'es',
        uploadUrl: '#',
        allowedFileExtensions: ['pdf'],
        showUpload: false,
        //mainClass: "input-group-lg",
        rtl: true
    });    
    
    $('#cb_etapa option:first-child').attr('selected', 'selected');
    $('#div_mant_adjunto_tbl').toggle();
</script>            