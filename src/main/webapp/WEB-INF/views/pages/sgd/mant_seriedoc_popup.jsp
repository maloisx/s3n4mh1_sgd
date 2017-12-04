<div id="div_mensaje_ajax" class="text-success"></div>
<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-5">
                <input type="text" name="txt_cod" id="txt_cod" value="${requestScope['cod']}"/>
                <label for="txt_cod" class="${requestScope['obj_active_form']}" >Código</label>           
            </div>
            <div class="input-field col-md-5">
                <input type="text" name="txt_des" id="txt_des" value="${requestScope['des']}"/>   
                <label for="txt_des" class="${requestScope['obj_active_form']}" >Descripción</label>
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
                <input type="text" name="txt_per" id="txt_per" value="${requestScope['per']}"/>
                <label for="txt_per" class="${requestScope['obj_active_form']}" >Periodo Retención (AÑOS)</label>           
            </div>
            <div class="input-field col-md-5">
                <input type="text" name="txt_pag" id="txt_pag" value="${requestScope['pag']}"/>  
                <label for="txt_pag" class="${requestScope['obj_active_form']}" >Periodo Archivo General (AÑOS)</label>
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
                <input type="text" name="txt_pap" id="txt_pap" value="${requestScope['pap']}"/>
                <label for="txt_pap" class="${requestScope['obj_active_form']}" >Periodo Archivo Periférico (AÑOS)</label>           
            </div>
            <div class="input-field col-md-5">
                <input type="text" name="txt_poa" id="txt_poa" value="${requestScope['poa']}"/>
                <label for="txt_poa" class="${requestScope['obj_active_form']}" >Periodo Organo Adm. Archivos (AÑOS)</label>
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
                <select class="form-control selectpicker" data-size="3" name="cb_uo_ini" id="cb_uo_ini" multiple data-live-search="true" data-actions-box="true" onchange="charge_list_boostrap_select('cb_uo_ini','cb_uo_fin')">${requestScope['cb_desc_uo']}</select>                
                <label for="cb_uo_ini" class="active">Seleccione Unidad Orgánica:</label> 
            </div>
            <div class="input-field col-md-5">
                <select class="form-control selectpicker" data-size="3" name="cb_uo_fin" id="cb_uo_fin" multiple  data-live-search="true" data-actions-box="true" onchange="delete_cascade_list_boostrap_select('cb_uo_fin','cb_uo_ini')"></select>    
                <label for="cb_cd_fin" class="active">Unidad Orgánica Seleccionada:</label>
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
            <div class="input-field col-md-5">  
                <select name="cb_estado" id="cb_estado" class="form-control selectpicker">${requestScope['cb_estado']}</select>
                <label for="cb_estado" class="active" class="${requestScope['obj_active_form']}" >Seleccione el estado</label>
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
                <button onclick="sgd_mant_seriedoc_guardar()" class="btn btn-info btn-sm" >Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>        
        
<script>
    charge_list_boostrap_select('cb_uo_ini','cb_uo_fin');
</script>




