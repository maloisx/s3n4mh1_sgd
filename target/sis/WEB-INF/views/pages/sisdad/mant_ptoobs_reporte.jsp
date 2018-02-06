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
        <input name="txt_fec_ini" id="txt_fec_ini" type="text" class="datepicker" value="01/07/2017" style="color: #000; " />
        <label for="txt_fec_ini" class="active">Fecha Inicio</label>
    </div>
    <div class="input-field col-sm-3">   
        <input name="txt_fec_fin" id="txt_fec_fin" type="text" class="datepicker" value="31/07/2017" style="color: #000; " />
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
  <ul class="nav nav-tabs tabs-2">
    <li class="active"><a data-toggle="tab" href="#ptoobs_reporte_data">DATOS</a></li>
    <li><a data-toggle="tab" href="#ptoobs_reporte_graf">GRAFICOS</a></li>
  </ul>

  <div class="tab-content card-panel blue-grey lighten-5">
    <div id="ptoobs_reporte_data" class="tab-pane fade in active">
        <div class="row">
            <div class="input-field col-sm-1"></div> 
            <div class="col-sm-10">   
                <div id="sisdad_js_mant_ptoobs_reporte_tbl" >

                </div>
            </div>
            <div class="input-field col-sm-1"></div> 
        </div>
    </div>
    <div id="ptoobs_reporte_graf" class="tab-pane fade">
        
        <div class="row">
            <div class="input-field col-sm-2"></div> 
            <div class="input-field col-sm-3">   
                <label>Puntos de Observacion:</label><br>
                <select id="cb_ptoobs_graf" class="selectpicker" data-live-search="true" data-width="100%">

                </select>
            </div>
            <div class="input-field col-sm-2"></div> 
            <div class="input-field col-sm-3">   
                <label>Variables:</label><br>
                <select id="cb_ptoobs_var" class="selectpicker" multiple data-width="100%" data-actions-box="true" >
                    <option value="TM">TEMPERATURA</option>
                    <option value="RH">HUMEDAD RELATIVA</option>
                    <option value="PR">PUNTO DE ROCIO</option>
                </select>
            </div>
            <div class="input-field col-sm-2"></div> 
        </div>
        <div class="row">
            <div class="input-field col-sm-1"></div> 
            <div class="input-field col-sm-10 text-center">   
                <button id="btn_procesar" onclick="sisdad_js_mant_ptoobs_reporte_btn_graf();" class="btn btn-info btn-sm" >GRAFICAR</button> 
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col-sm-1"></div> 
            <div class="col-sm-10">   
                <div id="sisdad_js_mant_ptoobs_reporte_graf" >

                </div>
            </div>
            <div class="input-field col-sm-1"></div> 
        </div>
        
    </div>   
  </div>
</div>

<input type="hidden" value="" id="sisdad_js_mant_ptoobs_data_hd"/>

<!--<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-10">   
        <div id="sisdad_js_mant_ptoobs_reporte_tbl" >
            
        </div>
    </div>
    <div class="input-field col-sm-1"></div> 
</div>-->

<script>
    sisdad_js_mant_ptoobs_reporte();
</script>


