<div class="row" id="div_cont">
    <div class="input-field col-sm-1"></div>
    <div class="input-field col-sm-2">
        <input id="txt_NroOrden" type="number" value="" />
        <label for="txt_NroOrden" class="">Nro de Orden</label>
    </div>    
    <div class="input-field col-sm-1">   
        <select name="cb_AnioOrden" id="cb_AnioOrden" class="form-control selectpicker"></select>
        <label for="cb_AnioOrden" class=""></label>
    </div>
    <div class="input-field col-md-1"> 
        <button id="btn_buscarOrdenCompra" class="btn btn-info btn-sm">Buscar</button>            
    </div>
</div>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-10">   
        <div id="div_tbl_OrdenCompraItems" >
            
        </div>
    </div>
    <div class="input-field col-sm-1"></div>
</div>

<script>
   sisbien_PreRegistro();    
</script>