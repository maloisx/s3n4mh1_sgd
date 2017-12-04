<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id']}" />
<input type="hidden" name="hd_iduo" id="hd_iduo" value="${requestScope['cod_unid_org']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-5">                      
                <select name="cb_tip_unidcons" id="cb_tip_unidcons" class="form-control selectpicker">${requestScope['cb_desc_tip_unidcons']}</select>
                <label for="cb_tip_unidcons" class="active">Seleccione el Tipo de Unid. Conservación:</label> 
            </div>
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-4">                      
                <select name="cb_almacen" id="cb_almacen" class="form-control selectpicker" onchange="sgd_mant_estante_cargar_cbo()" >${requestScope['cb_almacen']}</select>
                <label for="cb_almacen" class="active">Almacén:</label> 
            </div>
            <div class="input-field col-md-1">        
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
            <div class="input-field col-md-5">                
                <input type="text" name="txt_cod" id="txt_cod" value="${requestScope['cod']}" class="form-control text-uppercase"/>
                <label for="txt_cod" class="${requestScope['obj_active_form']}">Código:</label>
            </div>
            <div class="input-field col-md-1">        
            </div>            
            <div class="input-field col-md-4">                      
                <select name="cb_estante" id="cb_estante" class="form-control selectpicker" onchange="sgd_mant_cuerpo_cargar_cbo(); hd_estante.value=this.value"></select>
                <label for="cb_estante" class="active">Estante</label> 
                <input type="hidden" name="hd_estante" id="hd_estante" value="${requestScope['id_estante']}" />
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-5">                
                <input type="text" name="txt_des" id="txt_des" value="${requestScope['des']}" class="form-control text-uppercase"/>
                <label for="txt_des" class="${requestScope['obj_active_form']}">Descripción:</label>
            </div>
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-4">
                <select name="cb_cuerpo" id="cb_cuerpo" class="form-control selectpicker" data-size="3" onchange="sgd_mant_balda_cargar_cbo(); hd_cuerpo.value=this.value"></select>
                <label for="cb_cuerpo" class="active">Cuerpo:</label>
                <input type="hidden" name="hd_cuerpo" id="hd_cuerpo" value="${requestScope['id_cuerpo']}" />
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-5">                
                <input type="text" name="txt_obs" id="txt_obs" value="${requestScope['obs']}" class="form-control text-uppercase"/>
                <label for="txt_obs" class="${requestScope['obj_active_form']}">Observaciones:</label>
            </div>
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-4">
                <select name="cb_balda" id="cb_balda" class="form-control selectpicker" data-size="3" onchange="hd_balda.value=this.value"></select>
                <label for="cb_balda" class="active">Balda:</label>
                <input type="hidden" name="hd_balda" id="hd_balda" value="${requestScope['id_balda']}" />
            </div>
            <div class="input-field col-md-1">        
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
            <div class="input-field col-md-2">                
                <input type="date" name="dt_fec_ini" id="dt_fec_ini" value="${requestScope['fec_extini']}" class="form-control"/>
                <label for="dt_fec_ini" class="active">Fecha Extrema Inicial:</label>
            </div>
            <div class="input-field col-md-1">        
            </div>    
            <div class="input-field col-md-2">                
                <input type="date" name="dt_fec_fin" id="dt_fec_fin" value="${requestScope['fec_extfin']}" class="form-control"/>
                <label for="dt_fec_fin" class="active">Fecha Extrema Final:</label>
            </div>    
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>
<br>                
<div class="row">
    <div class="col-md-12 row-height">
        <div class="row">
            <div class="input-field col-md-1 row-height">        
            </div>
            <div class="input-field col-md-5 row-height">      
                <select class="form-control selectpicker" data-size="3" name="cb_cd_ini" id="cb_cd_ini" multiple  data-live-search="true" multiple data-actions-box="true" onchange="charge_list_boostrap_select('cb_cd_ini','cb_cd_fin'); hd_clasifdoc.value=this.value">${requestScope['cb_desc_cd']}</select>    
                <label for="cb_cd_ini" class="active">Seleccione Clasificación Documental:</label>
            </div>     
            <div class="input-field col-md-1">        
            </div>    
            <div class="input-field col-md-4">                     
                <select name="cb_estado" id="cb_estado" class="form-control selectpicker">${requestScope['cb_estado']}</select>
                <label for="cb_estado" class="active">Seleccione el estado:</label>  
            </div>
            <div class="input-field col-md-1 row-height">        
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
            <div class="input-field col-md-5 row-height"> 
                <select class="form-control selectpicker" data-size="3" name="cb_cd_fin" id="cb_cd_fin" multiple  data-live-search="true" multiple data-actions-box="true" onchange="delete_cascade_list_boostrap_select('cb_cd_fin','cb_cd_ini')"></select>    
                <label for="cb_cd_ini" class="active">Clasificación Documental Seleccionada:</label>
                <input type="hidden" name="hd_clasifdoc" id="hd_clasifdoc" value="" />
            </div> 
            
            <div class="input-field col-md-1">        
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
                <button onclick="sgd_mant_unidcons_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>

<script>
    charge_list_boostrap_select('cb_cd_ini','cb_cd_fin');
    
    $('#cb_tip_unidcons option:first-child').attr('selected', 'selected');
    $('#cb_estado option:first-child').attr('selected', 'selected');
    $('#cb_almacen option:first-child').attr('selected', 'selected');
//    $('#cb_cd_ini option:first-child').attr('selected', 'selected');
    
    $('#cb_almacen').load(sgd_mant_estante_cargar_cbo());    
    
    $('#cb_estante').change(sgd_mant_cuerpo_cargar_cbo());
//    $('#hd_cuerpo').load(alert($('#hd_cuerpo').val()));
    $('#hd_cuerpo').load(sgd_mant_cuerpo_cargar_cbo());
    $('#hd_cuerpo').change(sgd_mant_balda_cargar_cbo());
    
</script>