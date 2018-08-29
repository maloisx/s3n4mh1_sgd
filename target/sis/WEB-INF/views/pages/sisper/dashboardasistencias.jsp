

        
        <div class="row">
            <div class="input-field col-sm-1"></div>
            <div class="input-field col-sm-1">
                <input id="txt_fecha" type="date" value="" />
                <label for="txt_fecha" class="active">Fecha</label>
            </div>
            <div class="input-field col-sm-2">
                <select name="cb_lugar" id="cb_lugar" class="form-control selectpicker" data-live-search="true"></select>
                <label for="cb_lugar" class="active">Lugar</label>
            </div>
            <div class="input-field col-sm-3">
                <select name="cb_oficina" id="cb_oficina" class="form-control selectpicker" data-live-search="true"></select>
                <label for="cb_oficina" class="active">Oficina</label>
            </div>
            <div class="input-field col-sm-2">
                <button type="button" class="btn btn-primary" onclick="sisper_dashboardasistencias_fn_dashboard_show()" >Consultar</button>
            </div>
        </div>


<script>
    sisper_dashboardasistencias();
</script>