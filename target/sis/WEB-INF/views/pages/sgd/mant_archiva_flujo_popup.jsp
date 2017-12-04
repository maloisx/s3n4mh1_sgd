<div id="div_mensaje_ajax" class="text-success" style="font-size:130%; font-weight:bold;"></div>    
<input type="hidden" name="hd_id_flujo" id="hd_id_flujo" value="${requestScope['id_flujo']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-md-3">
                <input id="txt_cut" type="text" value="${requestScope['i_cut']}" readonly/>
                <label for="txt_cut" class="active">N° de CUT:</label>
            </div>
            <div class="input-field text-right col-md-3">                
                <input id="txt_periodo" type="text" value="${requestScope['i_per_exp']}" readonly/>
                <label for="txt_periodo" class="active">Periodo:</label>
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
            <div class="input-field text-right col-md-10">
                <input id="txt_uo" type="text" value="${requestScope['c_des_uo']}" readonly/>
                <label for="txt_uo" class="active">Unidad Orgánica remitente:</label>
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
                <input id="txt_persona" type="text" value="${requestScope['c_des_persona']}" readonly/>
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
            <div class="input-field text-right col-md-5">
                <input id="txt_fecarchiva" type="text" value="${requestScope['fecha_archi']}" disabled="true" style="color:red;"/>
                <label for="txt_fecarchiva" class="active" style="color:red;">Fecha en que archiva:</label>
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
                <button onclick="sgd_mant_archiva_flujo_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             

                    
              