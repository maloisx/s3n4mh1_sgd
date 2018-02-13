<div class="row">
    <div class="col-sm-12">        
        <div class="row">
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-1">
                    <select name="cb_anio" id="cb_anio" class="form-control selectpicker" >${requestScope['anio']}</select>
                    <label for="cb_anio" class="active">Año</label>
                </div>
                <div class="input-field col-sm-2">
                    <select name="cb_procedimiento" id="cb_procedimiento" class="form-control selectpicker" data-size="3">${requestScope['tipo_proced']}</select>
                    <label for="cb_procedimiento" class="active">Procedimiento</label>
                </div>
                <div class="input-field col-sm-2">
                    <button id="btn_busca" onclick="pad_mant_reporte_procedimiento_tbl()" class="btn btn-info btn-sm" >
                        BUSCAR
                    </button>
                </div>
            </div>
        </div>    
        <br>
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
                    <div class="col-sm-10" id="div_mant_reporte_procedimiento_tbl" >
                    </div>
                    <div class="input-field col-sm-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

