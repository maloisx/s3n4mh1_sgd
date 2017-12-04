
<!--<input type="hidden" name="hd_id_doc" id="hd_id_doc" value="${requestScope['id_doc']}" />-->
<input type="hidden" name="hd_id_unidcons" id="hd_id_unidcons" value="${requestScope['id_unidcons']}" />
<br>

<div class="row">
    <div class="col-md-12">               
        <div class="row">  
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-10"> 
                <label class="active">ORIGEN</label>                
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>             
        <div class="row">  
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-10" id="div_mant_unidcons_lista"> 
                <!--${requestScope['tbl_lista_unidcons']}-->
                <script>
                    sgd_mant_unidcons_lista();
                </script> 
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
            <div class="input-field col-md-10"> 
                <label class="active">DESTINO</label>
                <hr  style="width:96%"/>  
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div> 
        <div class="row">  
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-7"> 
                <select name="cb_almacen" id="cb_almacen" class="form-control selectpicker" data-size="3" onchange="sgd_mant_estante_cargar_cbo();">${requestScope['cb_almacen']}</select>
                <label for="cb_almacen" class="active">Almacén:</label>
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div> 
        <br>
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-3">                
                <select name="cb_estante" id="cb_estante" class="form-control selectpicker" data-size="3" onchange="sgd_mant_cuerpo_cargar_cbo();"></select>
                <label for="cb_estante" class="active">Cod. de Estante:</label>
            </div>
            <div class="input-field col-md-4">                
                <select name="cb_cuerpo" id="cb_cuerpo" class="form-control selectpicker" data-size="3" onchange="sgd_mant_balda_cargar_cbo();"></select>
                <label for="cb_cuerpo" class="active">N° de Cuerpo:</label>
            </div>
            <div class="input-field col-md-3">                
                <select name="cb_balda" id="cb_balda" class="form-control selectpicker" data-size="3" onchange="sgd_mant_unidcons_cargar_cbo();"></select>
                <label for="cb_balda" class="active">N° de Balda:</label>
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
                <button onclick="sgd_mant_mov_unidcons_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             
<div id="div_mensaje_ajax" class="" style="font-size: 12px;"></div>    
                    
<script>   
                
            
          
</script>

