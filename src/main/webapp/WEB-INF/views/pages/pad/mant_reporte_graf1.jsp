<div class="row">
    <div class="col-sm-12">        
        <div class="row">
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-2">
                    <select name="cb_etapa" id="cb_etapa" class="form-control selectpicker" data-size="3">${requestScope['etapa']}</select>
                    <label for="cb_etapa" class="active">ETAPA</label>
                </div>
                
            </div>
        </div>        
        <div class="row">     
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-2">
                    <select name="cb_estado" id="cb_estado" class="form-control selectpicker" data-size="3">${requestScope['estado']}</select>
                    <label for="cb_estado" class="active">ESTADO</label>
                </div> 
                <div class="input-field col-sm-1">
                </div>    
                <div class="input-field col-sm-2">
                    <label class="bmd-label-floating" style="color: red; font-size:100%; font-weight:bold;">PRESCRIPCIÓN INICIO PAD</label>
                </div>  
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-2">
                    <!--<input id="txt_fecpad" type="text" value=""/>-->
                    <label class="bmd-label-floating" style="color: red; font-size:100%; font-weight:bold;">PRESCRIPCIÓN PAD</label>
                </div>
            </div>      
        </div>            
        <div class="row">     
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-2">
                    <select name="cb_abogado" id="cb_abogado" class="form-control selectpicker" data-size="3">${requestScope['abogado']}</select>
                    <label for="cb_abogado" class="active">ABOGADO</label>
                </div>  
                    
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-1">
                    <input type="date" name="dt_fec_ini" id="dt_fec_ini" value=""/>
                    <label for="dt_fec_ini" class="active">Fecha Inicial:</label>
                </div>
                <div class="input-field col-sm-1">
                    <input type="date" name="dt_fec_fin" id="dt_fec_fin" value=""/>
                    <label for="dt_fec_fin" class="active">Fecha Final:</label>
                </div>
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-1">
                    <input type="date" name="dt_fec_inipad" id="dt_fec_inipad" value=""/>
                    <label for="dt_fec_inipad" class="active">Fecha Inicial:</label>
                </div>
                <div class="input-field col-sm-1">
                    <input type="date" name="dt_fec_finpad" id="dt_fec_finpad" value=""/>
                    <label for="dt_fec_finpad" class="active">Fecha Final:</label>
                </div>
                <div class="input-field col-sm-2">
                    <button id="btn_busca" onclick="pad_mant_rep1_tbl($('#cb_etapa').val(),$('#cb_estado').val(),$('#cb_abogado').val(),$('#dt_fec_ini').val(),$('#dt_fec_fin').val(),$('#dt_fec_inipad').val(),$('#dt_fec_finpad').val())" class="btn btn-info btn-sm" >
                        BUSCAR
                    </button>
                </div>
                    
            </div>      
        </div>            
        <br><br>
        <hr>
        <div class="row">
            <div class="col-sm-12">
                <div class="row">
                    <div class="input-field col-sm-1">
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col-sm-1">
                    </div>
                    <div class="col-sm-10" id="div_mant_rep1_tbl" >
                    </div>
                    <div class="input-field col-sm-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

