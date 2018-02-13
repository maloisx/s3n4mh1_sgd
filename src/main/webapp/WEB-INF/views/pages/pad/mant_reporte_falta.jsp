<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-4">
                    <select name="cb_falta" id="cb_falta" class="form-control selectpicker" data-size="5">${requestScope['falta']}</select>
                    <label for="cb_falta" class="active">FALTA</label>
                </div>
                <div class="input-field col-sm-2">
                    <button id="btn_busca" onclick="pad_mant_reporte_falta_tbl()" class="btn btn-info btn-sm" >
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
                    <div class="col-sm-10" id="div_mant_reporte_falta_tbl" >
                    </div>
                    <div class="input-field col-sm-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

