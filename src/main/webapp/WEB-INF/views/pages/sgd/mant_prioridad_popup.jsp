<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-10">                
                <input type="text" name="txt_des" id="txt_des" value="${requestScope['des']}" class="form-control"/>
                <label for="txt_des" class="${requestScope['obj_active_form']}">Digite la Descripción:</label>
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
                <input type="text" name="txt_plazo" id="txt_plazo" value="${requestScope['plazo']}" class="form-control"/>
                <label for="txt_plazo" class="${requestScope['obj_active_form']}">Indique el Plazo:</label>
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
                <select name="cb_estado" id="cb_estado" class="form-control selectpicker">${requestScope['cb_estado']}</select>
                <label>Seleccione el estado:</label>  
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
                <button onclick="sgd_mant_prioridad_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>