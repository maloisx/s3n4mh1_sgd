<input type="hidden" id="hd_datos"/>
<div class="row">
    <div class="input-field col-sm-3"></div> 
    <div class="input-field col-sm-6">   
        <label>AÑO:</label><br>
        <select id="cb_anio" class="selectpicker" data-live-search="false" data-width="100px" multiple data-actions-box="false" >            
        </select>
    </div>
    <div class="input-field col-sm-3"></div> 
</div>
<div class="row">
    <div class="input-field col-sm-3"></div> 
    <div class="input-field col-sm-3">   
        <label>SEDE</label><br>
        <select id="cb_sede" class="selectpicker" data-live-search="true" data-width="100%" multiple data-actions-box="false" >            
        </select>
    </div>
    <div class="input-field col-sm-3">   
        <label>CENTRO DE COSTO</label><br>
        <select id="cb_centro_costo" class="selectpicker" data-live-search="true" data-width="100%" multiple data-actions-box="false" >            
        </select>
    </div>
    <div class="input-field col-sm-3"></div> 
</div>
<div class="row">
    <div class="input-field col-sm-3"></div> 
    <div class="input-field col-sm-3">   
        <label>UBICACION FISICA</label><br>
        <select id="cb_ubic_fisica" class="selectpicker" data-live-search="true" data-width="100%" multiple data-actions-box="false" >            
        </select>
    </div>
    <div class="input-field col-sm-3">   
        <label>USUARIO FINAL</label><br>
        <select id="cb_usuario_final" class="selectpicker" data-live-search="true" data-width="100%" multiple data-actions-box="false" >            
        </select>
    </div>
    <div class="input-field col-sm-3"></div> 
</div>
<br>
<div class="row">
    <div class="input-field col-sm-3"></div> 
    <div class="input-field col-sm-3">   
        <label for="txt_rango_ini" class="active">RANGO INICIO</label>
        <input name="txt_rango_ini" id="txt_fec_ini" type="text" value="" style="color: #000; " />        
    </div>
    <div class="input-field col-sm-3">   
        <label for="txt_rango_fin" class="active">RANGO FIN</label>
        <input name="txt_rango_fin" id="txt_fec_fin" type="text" value="" style="color: #000; " />        
    </div>
    <div class="input-field col-sm-3"></div> 
</div>

<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10 text-center">   
        <button id="btn_procesar" onclick="sisdad_js_mant_ptoobs_reporte_resum_procesar();" class="btn btn-info btn-sm" >PROCESAR</button> 
    </div>
</div>
<br>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-10">   
        <div id="sisbien_js_sigamef_bienes_tbl" >
            
        </div>
    </div>
    <div class="input-field col-sm-1"></div> 
</div>

<script>
    sisbien_js_sigamef_bienes();
</script>


