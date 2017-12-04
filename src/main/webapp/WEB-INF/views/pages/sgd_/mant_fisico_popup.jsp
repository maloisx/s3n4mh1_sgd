
<input type="hidden" name="hd_id_doc" id="hd_id_doc" value="${requestScope['id_doc']}" />
<input type="hidden" name="hd_id_flujo" id="hd_id_flujo" value="${requestScope['id_flujo']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>        
            <div class="input-field text-right col-md-5">                
                <input id="txt_fecfisico" type="text" value="${requestScope['fecha_acuse']}" readonly/>
                    <label for="txt_fecfisico" class="active">Recibido en fecha:</label>
            </div>
            <div class="input-field col-md-5"> 
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
                <textarea  id="txt_obs" class="materialize-textarea text-uppercase" type="text" class="validate" maxlength="500" minlength="1"></textarea>
                <label for="txt_obs" >Observaciones</label>
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
                <button onclick="sgd_mant_fisico_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             
<div id="div_mensaje_ajax" class="" style="font-size: 12px;"></div>    
                    
              