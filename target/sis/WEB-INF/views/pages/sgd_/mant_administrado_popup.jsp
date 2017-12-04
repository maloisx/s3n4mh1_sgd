<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id']}" />
<br>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-md-5">
                <select name="cb_tipodoc" id="cb_tipodoc" class="form-control selectpicker " data-size="4">${requestScope['cb_tipodoc']}</select>                    
                <label for="cb_tipodoc" class="active">Tipo Documento:</label> 
            </div>
            <div class="input-field text-right col-md-5">
                <input type="text" name="txt_nrodoc" id="txt_nrodoc" value="${requestScope['docident']}" />
                <label for="txt_nrodoc" class="${requestScope['obj_active_form']}">N° Documento:</label>
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
                <input type="text" class="text-uppercase" name="txt_descripcion" id="txt_descripcion" value="${requestScope['descripcion']}" />
                <label for="txt_descripcion" class="${requestScope['obj_active_form']}">Descripción:</label>
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
                <input type="text" class="text-uppercase" name="txt_representante" id="txt_representante" value="${requestScope['representante']}" />
                <label for="txt_representante" class="${requestScope['obj_active_form']}">Representante:</label>
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
                <input type="text" name="txt_direccion" id="txt_direccion" value="${requestScope['direccion']}" />
                <label for="txt_direccion" class="${requestScope['obj_active_form']}">Dirección:</label>
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
                <input type="text" name="txt_email" id="txt_email" value="${requestScope['email']}" />
                <label for="txt_email" class="${requestScope['obj_active_form']}">E Mail:</label>
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
                <input type="text" name="txt_telefono" id="txt_telefono" value="${requestScope['telefono']}" />
                <label for="txt_telefono" class="${requestScope['obj_active_form']}">Dirección:</label>
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
                <button onclick="sgd_mant_administrado_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             

<script>
    $('#cb_tipodoc option:first-child').attr('selected', 'selected');
</script>            