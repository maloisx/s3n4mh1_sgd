<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id']}" />
<input type="hidden" name="hd_almacen" id="hd_almacen" value="${requestScope['almacen']}" />

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-3"> 
                <select name="cb_estt" id="cb_estt" class="form-control selectpicker id_estt" data-size="4" ${requestScope['obj_habilita_form']}>${requestScope['cb_codest']}</select>
                <label for="cb_estt" class="active">Código:</label>
                <input type="hidden" id="hd_cbo_estt" value="${requestScope['cod']}">
            </div>
            <div class="input-field col-md-3"> 
                <select name="cb_lado" id="cb_lado" class="form-control selectpicker id_lado" data-size="4" ${requestScope['obj_habilita_form']}>${requestScope['cb_lado']}</select>
                <label for="cb_lado" class="active">Lados:</label>
                <input type="hidden" id="hd_cb_lado" value="${requestScope['c_lado']}"> 
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
                <input type="text" name="txt_des" id="txt_des" class="text-uppercase" value="${requestScope['des']}"/>
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
                <input type="text" name="txt_mat" id="txt_mat" class="text-uppercase" value="${requestScope['material']}"/>
                <label for="txt_mat" class="${requestScope['obj_active_form']}">Material de fábrica:</label>
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
                <input type="text" name="txt_dim" id="txt_dim" class="text-uppercase" value="${requestScope['dimension']}"/>
                <label for="txt_dim" class="${requestScope['obj_active_form']}">Dimensiones:</label>
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
            <div class="input-field col-md-2">                      
                <select name="cb_cuerpo" id="cb_cuerpo" class="form-control selectpicker id_cuerpo" data-size="4" ${requestScope['obj_habilita_form']}>${requestScope['cb_cpo']}</select>                    
                <label for="cb_cuerpo" class="active">N° Cuerpos:</label> 
                <input type="hidden" id="hd_cb_cuerpo" value="${requestScope['c_cpos']}">
            </div>
            <div class="input-field col-md-3">                      
                <select name="cb_balda" id="cb_balda" class="form-control selectpicker id_balda" data-size="4" ${requestScope['obj_habilita_form']}>${requestScope['cb_balda']}</select>
                <label for="cb_balda" class="active">N° Baldas por cuerpo:</label> 
                <input type="hidden" id="hd_cb_balda" value="${requestScope['c_bld']}">
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
            <div class="input-field col-md-4">                      
                <select name="cb_estado" id="cb_estado" class="form-control selectpicker" data-size="4">${requestScope['cb_estado']}</select>
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
                <button onclick="sgd_mant_estante_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>
<br><br>                
<div id="div_mensaje_ajax" style="font-size: 12px;"></div>                    
                
<script> 
    $('#cb_lado option:first-child').attr('selected', 'selected');
    $('#cb_cuerpo option:first-child').attr('selected', 'selected');
    $('#cb_balda option:first-child').attr('selected', 'selected');
    $('#cb_alma option:first-child').attr('selected', 'selected');
    $('#cb_estado option:first-child').attr('selected', 'selected');
    
    var estt = $('#hd_cbo_estt').val();
    if (estt != ''){
       $('.id_estt option[value="'+estt+'"]').attr('selected','selected');
    }else{
        $('.id_estt option[value="A"]').attr('selected','selected');
    }
    
    var cpo = $('#hd_cb_cuerpo').val();
    $('.id_cuerpo option[value="'+cpo+'"]').attr('selected','selected');
    
    var balda = $('#hd_cb_balda').val();
    $('.id_balda option[value="'+balda+'"]').attr('selected','selected');
    
    var lado = $('#hd_cb_lado').val();
    $('.id_lado option[value="'+lado+'"]').attr('selected','selected');
    
    
</script>

