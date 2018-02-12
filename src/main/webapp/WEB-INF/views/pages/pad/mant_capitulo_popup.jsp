<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id']}" />  
<br>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-md-10">                    
                <select name="cb_norma" id="cb_norma" class="form-control selectpicker" onchange="pad_mant_titulo_norma_consulta()">${requestScope['cb_norma']}</select>
                <label for="cb_norma" class="active">Seleccione la norma:</label>   
            </div>                       
            <div class="input-field col-sm-1">
            </div>
        </div>
    </div>
</div>  
<br>  
<br>   
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-md-10">                    
                <select name="cb_titulo" id="cb_titulo" class="form-control selectpicker" onchange="pad_mant_capitulo_titulo_consulta()">${requestScope['cb_titulo']}</select>
                <label for="cb_titulo" class="active">Seleccione el título:</label>   
            </div>                       
            <div class="input-field col-sm-1">
            </div>
        </div>
    </div>
</div>              
<br>   
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-md-10">
                <input type="text" name="txt_n_capitulo" id="txt_n_capitulo" value="${requestScope['n_capitulo']}" />
                <label for="txt_n_capitulo" class="active">N° Capítulo:</label>
            </div>        
            <div class="input-field col-md-1">
            </div>
        </div>
    </div>
</div> 
<br>                
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-md-10">
                <input type="text" name="txt_des" id="txt_des" value="${requestScope['descripcion']}" />
                <label for="txt_des" class="active">Descripción:</label>
            </div>        
            <div class="input-field col-md-1">
            </div>
        </div>
    </div>
</div>    
<br>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-md-10">                    
                <select name="cb_estado" id="cb_estado" class="form-control selectpicker">${requestScope['cb_estado']}</select>
                <label for="cb_estado" class="active">Seleccione el estado:</label>   
            </div>                       
            <div class="input-field col-sm-1">
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
                <button onclick="pad_mant_capitulo_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             

<script>    
   
</script>            