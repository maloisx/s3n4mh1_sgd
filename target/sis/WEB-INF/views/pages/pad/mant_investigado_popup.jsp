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
<!--<br>-->                
<!--<div class="row">
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
</div>    -->
<br>       
<br>       
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>            
            <div class="input-field text-right col-sm-5">
                <select name="cb_falta" id="cb_falta" class="form-control selectpicker" data-size="5" multiple onchange="charge_list_boostrap_select('cb_falta','cb_faltasel')">${requestScope['falta']}</select>                    
                <label for="cb_falta" class="active">Seleccione Falta:</label> 
            </div>
            <div class="input-field text-right col-sm-5">
                <select name="cb_faltasel" id="cb_faltasel" class="form-control selectpicker" data-size="5" multiple onchange="delete_cascade_list_boostrap_select('cb_faltasel','cb_falta')"></select>                    
                <label for="cb_faltasel" class="active">Faltas seleccionadas:</label> 
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
                <button onclick="pad_mant_investigado_guardar()" class="btn btn-info btn-sm">Guardar</button>            
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