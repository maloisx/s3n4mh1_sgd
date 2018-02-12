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
<br>   
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-md-10">                    
                <select name="cb_capitulo" id="cb_capitulo" class="form-control selectpicker" onchange="pad_mant_articulo_capitulo_consulta()">${requestScope['cb_capitulo']}</select>
                <label for="cb_capitulo" class="active">Seleccione el capítulo:</label>   
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
                <select name="cb_articulo" id="cb_articulo" class="form-control selectpicker">${requestScope['cb_articulo']}</select>
                <label for="cb_articulo" class="active">Seleccione el artículo:</label>   
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
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field text-right col-md-2">
                <input type="text" name="txt_literal" id="txt_literal" value="${requestScope['cod_literal']}" />
                <label for="txt_literal" class="active">Literal:</label>
            </div>        
            <div class="input-field text-right col-md-8">
                <input type="text" name="txt_des" id="txt_des" maxlength="500" minlength="1" value="${requestScope['descripcion']}" />
                <label for="txt_des" class="active">Descripción de la falta:</label>
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
                <button onclick="pad_mant_falta_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             

<script>    
//    $('#cb_estado option:first-child').attr('selected', 'selected');
</script>            