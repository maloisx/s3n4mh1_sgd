<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-4">
                    <select name="cb_sancion" id="cb_sancion" class="form-control selectpicker" data-size="5">${requestScope['sancion']}</select>
                    <label for="cb_sancion" class="active">Sanción</label>
                </div>
                <div class="input-field col-sm-2">
                    <button id="btn_busca" onclick="pad_mant_reporte_sancion_tbl()" class="btn btn-info btn-sm" >
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
                    <div class="col-sm-10" id="div_mant_reporte_sancion_tbl" >
                    </div>
                    <div class="input-field col-sm-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

