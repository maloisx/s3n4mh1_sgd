<input type="hidden" id="hd_datos"/>
<div class="row">
    <div class="input-field col-sm-3"></div> 
    <div class="input-field col-sm-6">   
        <label>Seleccionar puntos de observacion:</label><br>
        <select id="cb_ptoobs" class="selectpicker" multiple data-live-search="true" data-width="100%" multiple data-actions-box="true" >
            
        </select>
    </div>
    <div class="input-field col-sm-3"></div> 
</div>
<br>
<div class="row">
    <div class="input-field col-sm-3"></div> 
    <div class="input-field col-sm-3">   
        <input name="txt_fec_ini" id="txt_fec_ini" type="text" class="datepicker" value="" style="color: #000; " />
        <label for="txt_fec_ini" class="active">Fecha Inicio</label>
    </div>
    <div class="input-field col-sm-3">   
        <input name="txt_fec_fin" id="txt_fec_fin" type="text" class="datepicker" value="" style="color: #000; " />
        <label for="txt_fec_fin" class="active">Fecha Fin</label>
    </div>
    <div class="input-field col-sm-3"></div> 
</div>

<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10 text-center">   
        <button id="btn_procesar" onclick="sisdad_js_mant_ptoobs_reporte_procesar();" class="btn btn-info btn-sm" >PROCESAR</button> 
    </div>
</div>
<br>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-10">   
        <div id="sisdad_js_mant_ptoobs_reporte_tbl" >
            
        </div>
    </div>
    <div class="input-field col-sm-1"></div> 
</div>

<script>
    sisdad_js_mant_ptoobs_reporte();
</script>


