<div class="row">
    <div class="col-md-12">
        <br><br>
        <div class="row">
            <div class="col-md-1"></div>            
            <div class="input-field col-md-1">                
                <input type="hidden" name="hd_alma" id="hd_alma" value="${requestScope['hd_alma']}" />
                <button onclick="sgd_mant_estante_popup()" class="btn btn-info btn-sm">Nuevo Registro</button>
            </div>
            <div class="input-field col-md-1">   
                <select name="cb_almacen" id="cb_almacen" class="form-control selectpicker" onchange="sgd_mant_estante_tbl(this.value);">${requestScope['cb_almacen']}</select>
                <label for="cb_almacen" class="active">Almacén</label>
            </div>
            <div class="col-md-9"></div>      
        </div>    
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div id="div_mant_estante_tbl"></div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
</div>

<script>
    sgd_mant_estante_tbl(cb_almacen.value);
</script>