<div id="div_mensaje_ajax" class="text-success"></div>
<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id_sol']}" />
<input type="hidden" name="i_proc" id="hd_i_proc" value="${requestScope['i_proc']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-5">
                <input type="text" name="txt_fecha" id="txt_fecha" value="${requestScope['fecha']}"/>
                <label for="txt_fecha" class="${requestScope['obj_active_form']}">Fecha</label>           
            </div>
            <div class="input-field col-md-5">                
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
                <input type="text" name="txt_procedimiento" id="txt_procedimiento" value="${requestScope['procedimiento']}"/>
                <label for="txt_procedimiento" class="${requestScope['obj_active_form']}">Procedimiento</label>           
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
            <div class="input-field col-md-3"> 
                <input type="text" name="txt_doc_adm" id="txt_doc_adm" value="${requestScope['doc_adm']}"/>
                <label for="txt_doc_adm" class="${requestScope['obj_active_form']}">DNI/RUC</label>           
            </div>
            <div class="input-field col-md-7"> 
                <input type="text" name="txt_administrado" id="txt_administrado" value="${requestScope['administrado']}"/>
                <label for="txt_administrado" class="${requestScope['obj_active_form']}">Solicitante</label>           
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>
<!--<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>-->
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>
            <div class="input-field col-md-3"> 
                <input type="text" name="txt_telefono" id="txt_telefono" value="${requestScope['telefono']}"/>
                <label for="txt_telefono" class="${requestScope['obj_active_form']}">Teléfono</label>           
            </div>
            <div class="input-field col-md-7"> 
                <input type="text" name="txt_email" id="txt_email" value="${requestScope['email_adm']}"/>
                <label for="txt_email" class="${requestScope['obj_active_form']}">E-Mail</label>           
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
                <input type="text" name="txt_direccion" id="txt_direccion" value="${requestScope['direccion']}"/>
                <label for="txt_direccion" class="${requestScope['obj_active_form']}">Dirección</label>           
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
                <input type="text" name="txt_motivo" id="txt_motivo" value="${requestScope['motivo']}"/>
                <label for="txt_motivo" class="${requestScope['obj_active_form']}">Motivo de la solicitud</label>           
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
                <input type="text" name="txt_descripcion" id="txt_descripcion" value="${requestScope['descripcion']}"/>
                <label for="txt_descripcion" class="${requestScope['obj_active_form']}">Descripcion</label>           
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
                <input type="text" name="txt_observacion" id="txt_observacion" value="${requestScope['observacion']}"/>
                <label for="txt_observacion" class="${requestScope['obj_active_form']}">Observaciones</label>           
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
            <div class="input-field col-md-10" id="div_detalle">
                ${requestScope['response']}
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
            <div class="input-field text-right col-md-10">  
                <button onclick="sgd_mant_generar_pdf()" class="btn btn-info btn-sm" >Generar PDF</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>
        </div>
    </div>        
</div>        
        
<script>
    charge_list_boostrap_select('cb_uo_ini','cb_uo_fin');
</script>




