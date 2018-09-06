<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-3">   
        <select name="cb_tipo_planilla" id="cb_tipo_planilla" class="form-control selectpicker" data-live-search="true"></select>
        <label for="cb_tipo_planilla" class="active">Tipo planilla</label>
    </div>
    <div class="input-field col-sm-3">   
        <select name="cb_dz" id="cb_dz" class="form-control selectpicker" onchange="sisgem_buscar_plantilla_cargar_cbestaciones()" data-live-search="true"></select>
        <label for="cb_dz" class="active">Dirección Zonal</label>
    </div>
    <div class="input-field col-sm-3">   
        <select name="cb_estacion" id="cb_estacion" class="form-control selectpicker" data-live-search="true"></select>
        <label for="cb_estacion" class="active">Estación</label>
    </div>    
</div>

<br>

<!--<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-8">   
        <div id="tabla_prueba" >
        </div>
    </div>
</div>-->

<script>
   sisgem_buscar_plantilla_js();
</script>
   