<div id="div_ubica_popup" class="${requestScope['danger']}" style="font-size: 12px;">${requestScope['msj']}</div>
<div id="div_mensaje_ajax" class="" style="font-size: 12px;"></div>
<input type="hidden" name="hd_cad_id_exp" id="hd_cad_id_exp" value="${requestScope['cad_id_exp']}" />
<input type="hidden" name="hd_codUser" id="hd_codUser" value="${requestScope['codUser']}" />

<div class="row">
    <div class="col-md-12">               
        <div class="row">  
            <div class="input-field col-md-1">        
            </div>
        </div>             
        <div class="row">  
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-10" id="div_mant_agrupa_lista" > 
                ${requestScope['tbl_agrupa']}               
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
            <div class="input-field text-right col-md-10">
                <button id="btn_guardar" onclick="sgd_mant_agrupa_guardar()" class="btn btn-info btn-sm" ${requestScope['obj_disable_form']}>Guardar</button>            
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
                <hr>         
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div> 
<div class="row">
    <div class="col-md-12" id="div_leyenda" >
        <div class="row">
            <div class="input-field col-md-1">        
            </div>        
            <div class="input-field text-left col-md-10">
                <p style="font-size: 10px;">Leyenda:</p>
                <button class="btn btn-info" id="matriz"> 
                    <span class="glyphicon glyphicon-star">
                    </span>                     
                </button>Expediente matriz.     
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>   
<div id="div_mensaje_ajax" class="" style="font-size: 12px;"></div>

<scrip>
    
</scrip>    