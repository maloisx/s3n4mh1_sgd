<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_id_flujo" id="hd_id_flujo" value="${requestScope['id_flujo']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>  
            <div class="input-field text-right col-md-3">                
                <input id="txt_cut" type="text" value="${requestScope['id_cut']}" ${requestScope['obj_readonly_form']} disabled="true" style="color:red;"/>
                <label for="txt_cut" class="active" style="color:red;">N° CUT:</label>
            </div>
            <div class="input-field text-right col-md-7">                
                <input id="txt_fecrechaza" type="text" value="${requestScope['fecha_rechaza']}" ${requestScope['obj_readonly_form']} disabled="true" style="color:red;"/>
                <label for="txt_fecrechaza" class="active" style="color:red;">Fecha en que rechaza:</label>
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
                <input id="txt_unid_org" type="text" value="${requestScope['unid_org']}" ${requestScope['obj_readonly_form']} disabled="true"  style="color:black;"/>
                <label for="txt_unid_org" class="active">Unidad Orgánica:</label>
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
                <input id="txt_persona" type="text" value="${requestScope['persona']}" ${requestScope['obj_readonly_form']} disabled="true"  style="color:black;"/>
                <label for="txt_persona" class="active">Remitente:</label>
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
                <textarea  id="txt_obs" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1">${requestScope['obs']}</textarea>
                <label for="txt_obs" class="${requestScope['obj_active_form']}">Observaciones</label>
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
                <button onclick="sgd_mant_rechaza_flujo_guardar(${requestScope['id_flujo']})" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div> 