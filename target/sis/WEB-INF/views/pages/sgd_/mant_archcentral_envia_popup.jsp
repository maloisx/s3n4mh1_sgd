<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_idexp" id="hd_idexp" value="${requestScope['id_exp']}" />
<br>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field text-right col-md-10">
                <input id="txt_uo" type="text" value="${requestScope['unid_org']}" readonly/>
                <label for="txt_uo" class="active">Unidad Orgánica remitente:</label>
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
            <div class="input-field col-md-5">
                <input id="txt_fecarchiva" type="text" value="${requestScope['fecha_archi']}" disabled="true" style="color:red;"/>
                <label for="txt_fecarchiva" class="active" style="color:red;">Fecha de envío:</label>
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
            <div class="input-field col-md-10">                
                <select name="cb_sd" id="cb_sd" class="form-control selectpicker" data-size="3">${requestScope['cb_sd']}</select>
                <label for="cb_sd" class="active">Serie Documental:</label>
            </div> 
            <div class="input-field col-md-1">
            </div>
        </div>
    </div>
</div>
<!--<br>                
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field col-md-5">                
                <select name="cb_anioini" id="cb_anioini" class="form-control selectpicker" data-size="3">${requestScope['cb_anioini']}</select>
                <label for="cb_anioini" class="active">Año inicial:</label>
            </div>
            <div class="input-field col-md-5">                
                <select name="cb_aniofin" id="cb_aniofin" class="form-control selectpicker" data-size="3">${requestScope['cb_aniofin']}</select>
                <label for="cb_aniofin" class="active">Año final:</label>
            </div>
            <div class="input-field col-md-1">
            </div>
        </div>
    </div>
</div>-->
<br>    
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field col-md-10">
                <textarea  id="txt_obs" class="materialize-textarea" type="text"></textarea>
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
                <button onclick="sgd_mant_archcentral_envia_guardar('${requestScope['id_exp']}','${requestScope['coduser']}')" class="btn btn-info btn-sm">ENVIAR</button>
            </div>
            <div class="input-field col-md-1">
            </div>
        </div>  
    </div>    
</div>    