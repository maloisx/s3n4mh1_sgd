<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-10">
                <input type="text" name="txt_cod" id="txt_cod" value="${requestScope['cod']}"/>
                <label for="txt_cod" class="${requestScope['obj_active_form']}">Código:</label>
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
            <div class="input-field col-md-10"> 
                <input type="text"  data-style="btn-info" name="txt_des" id="txt_des" value="${requestScope['des']}"/>
                <label for="txt_des" class="${requestScope['obj_active_form']}">Descripción:</label>
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
            <div class="input-field col-md-10"> 
                <select name="txt_sd" id="txt_sd" class="form-control selectpicker" onchange="javascript:txt_id_sd.value=this.value;">${requestScope['cb_desc_sd']}</select>
                <label for="txt_sd" class="active">Serie Documental:</label> 
                <input type="hidden" name="txt_id_sd" id="txt_id_sd" value="${requestScope['id_sd']}" class="form-control"/>    
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
            <div class="input-field col-md-10">
                <select name="cb_estado" id="cb_estado" class="form-control selectpicker">${requestScope['cb_estado']}</select>
                <label for="cb_estado" class="active">Seleccione el estado:</label>   
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
                <button onclick="sgd_mant_clasifdoc_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>