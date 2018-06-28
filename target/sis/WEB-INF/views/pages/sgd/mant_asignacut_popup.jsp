<div class="row input-field col-sm-12">
    <input type="hidden" name="hd_idsol" id="hd_idsol" value="${requestScope['id_sol']}" />
    <input type="hidden" name="hd_per_sol" id="hd_per_sol" value="${requestScope['per_sol']}" />
    <div class="input-field col-sm-5">
        <!--<input id ="txt_sol" type="text" value="${requestScope['id_sol']}" readonly style="text-align: center; color: red; font-size:150%; font-weight:bold;"/>n° cut-->
        <label class="active">Registre N° Expediente</label>
    </div>
</div>
<div class="row input-field col-sm-12">
    <div class="input-field col-sm-5">
        <input id ="txt_cut" type="text" value="${requestScope['cut_asig']}"/>
        <label for="txt_cut" class="active">N° CUT</label>
    </div>
    <div class="input-field col-sm-5">
        <select name="cb_periodo" id="cb_periodo" class="form-control selectpicker" >${requestScope['cb_periodo']}</select>
        <label for="cb_periodo" class="active">Periodo</label>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="input-field text-right col-md-12">
            <button onclick="sgd_mant_asignacut_guardar()" class="btn btn-info btn-sm" >Guardar</button>
        </div>
        <div class="input-field col-md-1">
        </div>
    </div>
</div>