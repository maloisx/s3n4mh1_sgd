<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10">   
        <select name="cb_new_esta_goes" id="cb_new_esta_goes" class="form-control selectpicker" data-live-search="true" data-size="10"></select>
        <label for="cb_new_esta_goes" class="active">ESTACION</label>
    </div>
    <div class="input-field col-sm-1"></div> 
</div>
<br>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10">   
        <input id="txt_cod_goes" type="text" value="" />
        <label for="txt_cod_goes" class="">CODIGO GOES</label>
    </div>
</div>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10">   
        <select name="cb_tipogoes" id="cb_tipogoes" class="form-control selectpicker"></select>
        <label for="cb_tipogoes" class="active">TIPO</label>
    </div>
    <div class="input-field col-sm-1"></div> 
</div>
<br>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10">   
        <select name="cb_estado" id="cb_estado" class="form-control selectpicker"></select>
        <label for="cb_estado" class="active">ESTADO</label>
    </div>
    <div class="input-field col-sm-1"></div> 
</div>
<br>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10">   
        <button onclick="sisdad_js_mant_nueva_estacion_btn_guardar()" class="btn btn-info btn-sm">Guardar</button>   
    </div>
</div>
<script>
    sisdad_js_mant_nueva_estacion();    
</script>