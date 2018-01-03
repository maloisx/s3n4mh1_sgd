<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-1">
                    <select name="cb_anio" id="cb_anio" class="form-control selectpicker" >${requestScope['anio']}</select>
                    <label for="cb_anio" class="active">AÑO</label>
                </div>
                <div class="input-field col-sm-1">
                    <input id="txt_exp" type="text" value=""/>
                    <label for="txt_exp" class="active">N° EXP.</label>
                </div>
                <div class="input-field col-sm-2">
                </div>
                <div class="input-field col-sm-2">
                    <!--<input id="txt_fecinipad" type="text" value=""/>-->
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
<!--        <br>-->
        <div class="row">
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-2">
                    <select name="cb_clsdoc" id="cb_clsdoc" class="form-control selectpicker" data-size="3">${requestScope['cb_clsdoc']}</select>
                    <label for="cb_clsdoc" class="active">TIPO DOCUMENTO</label>
                </div>
                <div class="input-field col-sm-1">
                    <input id="txt_nro" type="text" value=""/>
                    <label for="txt_nro" class="active">N° Doc.</label>
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
                    <button id="btn_busca" onclick="pad_mant_buscar_tbl($('#txt_exp').val(),$('#cb_anio').val(),$('#cb_clsdoc').val(),$('#txt_nro').val(),$('#dt_fec_ini').val(),$('#dt_fec_fin').val(),$('#dt_fec_inipad').val(),$('#dt_fec_finpad').val())" class="btn btn-info btn-sm" >
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
                    <div class="col-sm-10" id="div_mant_buscar_tbl" >
                    </div>
                    <div class="input-field col-sm-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
  $('#cb_periodo option:first-child').attr('selected', 'selected');
</script>