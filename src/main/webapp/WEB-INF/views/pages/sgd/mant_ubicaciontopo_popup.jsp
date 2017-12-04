<div id="div_agrupa_popup" class="${requestScope['danger']}" style="font-size: 12px;">${requestScope['msj']}</div>
<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_idexp" id="hd_idexp" value="${requestScope['id_exp']}" />
<input type="hidden" name="hd_coduser" id="hd_coduser" value="${requestScope['coduser']}" />
<input type="hidden" name="hd_id_uo" id="hd_id_uo" value="${requestScope['id_uo']}" />
<br>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field col-md-10">
                <select name="cb_almacen" id="cb_almacen" class="form-control selectpicker" data-size="3" onchange="sgd_mant_estante_cargar_cbo();" ${requestScope['obj_disable_form']}>${requestScope['cb_almacen']}</select>
                <label for="cb_almacen" class="active">Almacén:</label>
            </div>     
            <div class="input-field col-md-1">
            </div>
        </div>
    </div>
</div>
<br><br>                 
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field col-md-6 text-center">
                <select name="cb_estante" id="cb_estante" class="form-control selectpicker" data-size="3" onchange="sgd_mant_cuerpo_cargar_cbo();" ${requestScope['obj_disable_form']}></select>
                <label for="cb_estante" class="active">Estante:</label>
            </div>
            <div class="input-field col-md-6">
            </div>
        </div>
    </div>
</div>
<br>                
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field col-md-6">
                <select name="cb_cuerpo" id="cb_cuerpo" class="form-control selectpicker" data-size="3" onchange="sgd_mant_balda_cargar_cbo();" ${requestScope['obj_disable_form']}></select>
                <label for="cb_cuerpo" class="active">Cuerpo:</label>
            </div>
            <div class="input-field col-md-6">
            </div>
        </div>
    </div>
</div>
<br>                
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field col-md-6">
                <select name="cb_balda" id="cb_balda" class="form-control selectpicker" data-size="3" onchange="sgd_mant_unidcons_cargar_cbo();" ${requestScope['obj_disable_form']}></select>
                <label for="cb_balda" class="active">Balda:</label>
            </div>
            <div class="input-field col-md-6">
            </div>
        </div>
    </div>
</div>
<br>                
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field col-md-6">
                <select name="cb_unidc" id="cb_unidc" class="form-control selectpicker" data-size="3" ${requestScope['obj_disable_form']}></select>
                <label for="cb_unidc" class="active">Unidad de Conservación</label>
            </div>
            <div class="input-field col-md-6">
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-md-10">
                <button onclick="sgd_mant_ubicaciontopo_guardar()" class="btn btn-info btn-sm" ${requestScope['obj_disable_form']}>UBICAR</button>
            </div>
            <div class="input-field col-md-1">
            </div>
        </div>  
    </div>    
</div>             

<script>
    $('#cb_almacen option:first-child').attr('selected', 'selected');
    
    $('#cb_almacen').load(sgd_mant_estante_cargar_cbo());   
</script>                    
              