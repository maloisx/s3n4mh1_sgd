<!--<div id="div_mensaje_ajax" class="text-success"></div>-->  
<br>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-sm-2">
                <input type="text" name="txt_nroexp" id="txt_nroexp" value="${requestScope['nroexp']}" style="text-align: center; color: red; font-size:120%; font-weight:bold;" />                   
                <label for="txt_nroexp" class="active">N° Exp:</label> 
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
            <div class="input-field text-right col-sm-5">
                <select name="cb_investigado" id="cb_investigado" class="form-control selectpicker" data-live-search="true" data-size="5">${requestScope['investigado']}</select>                    
                <label for="cb_investigado" class="active">Nombre:</label> 
            </div> 
            <div class="input-field text-right col-sm-5">
                <select name="cb_cargo" id="cb_cargo" class="form-control selectpicker" data-live-search="true" data-size="5">${requestScope['cargo']}</select>                    
                <label for="cb_cargo" class="active">Cargo:</label> 
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
            <div class="input-field text-right col-sm-10">
                <select name="cb_norma_jur" id="cb_norma_jur" class="form-control selectpicker" data-live-search="true" data-size="5" onchange="pad_mant_falta_cargar_cbo();">${requestScope['normajur']}</select>                    
                <label for="cb_norma_jur" class="active">Norma Jurídica:</label> 
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
                <select name="cb_falta" id="cb_falta" class="form-control selectpicker" data-size="5" multiple onchange="charge_list_boostrap_select('cb_falta','cb_faltas')">${requestScope['falta']}</select>                    
                <label for="cb_falta" class="active">Falta:</label> 
            </div>
            <div class="input-field text-right col-sm-5">
                <select name="cb_faltas" id="cb_faltas" class="form-control selectpicker" data-size="5" multiple onchange="delete_cascade_list_boostrap_select('cb_faltas','cb_falta')"></select>                    
                <label for="cb_faltas" class="active">Faltas seleccionadas:</label> 
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
                <select name="cb_med_caut" id="cb_med_caut" class="form-control selectpicker" data-size="4">${requestScope['medcaut']}</select>                    
                <label for="cb_med_caut" class="active">Medida Cautelar:</label> 
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
                <select name="cb_sancion" id="cb_sancion" class="form-control selectpicker" data-size="4">${requestScope['sancion']}</select>                    
                <label for="cb_sancion" class="active">Sanción:</label> 
            </div> 
            <div class="input-field col-sm-2">
                <input name="txt_fechasancion" id="txt_fechasancion" type="text" class="datepicker" value="${requestScope['fechasancion']}" style="text-align: center; font-size:120%; font-weight:bold;" />
                <label for="txt_fechasancion" class="active">Fecha Sanción:</label>
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
                <select name="cb_recurso" id="cb_recurso" class="form-control selectpicker" data-size="4">${requestScope['recurso']}</select>                    
                <label for="cb_recurso" class="active">Recurso:</label> 
            </div> 
            <div class="input-field col-sm-2">
                <input name="txt_fecharecurso" id="txt_fecharecurso" type="text" class="datepicker" value="${requestScope['fecharecurso']}" style="text-align: center; font-size:120%; font-weight:bold;" />
                <label for="txt_fecharecurso" class="active">Fecha Recurso:</label>
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
            <div class="input-field col-sm-10">
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
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>        
            <div class="input-field text-right col-md-10">
                <button onclick="pad_mant_investigado_modificar_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             

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