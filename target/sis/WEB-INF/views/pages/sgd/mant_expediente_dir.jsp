<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-12">        
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-1">   
                    <select name="cb_periodo" id="cb_periodo" class="form-control selectpicker" >${requestScope['cb_periodo']}</select>
                    <label for="cb_periodo" class="active">Periodo</label>
                </div>
                <div class="input-field col-sm-1">
                    <input id="txt_cut" type="text" value=""/>
                    <label for="txt_cut" class="active">CUT</label>
                </div>
                <div class="input-field col-sm-2">
                    <input id="txt_asun" type="text" value=""/>
                    <label for="txt_asun" class="active">Asunto</label>
                </div> 
                <div class="input-field col-sm-1">
                </div>
<!--                <div class="input-field col-sm-2">   
                    <select name="cb_unidfunc_envia" id="cb_unidfunc_envia" class="form-control selectpicker" data-size="5" onchange="sgd_mant_unid_func()">${requestScope['cb_unidfunc']}</select>
                    <label for="cb_unidfunc_envia" class="active">Unidad Funcional (Envía)</label>
                </div>-->
                <div class="input-field col-sm-3">   
                    <select name="cb_unidfunc_recibe" id="cb_unidfunc_recibe" class="form-control selectpicker" data-size="5" onchange="sgd_mant_unid_func_rec()">${requestScope['cb_unidfunc']}</select>
                    <label for="cb_unidfunc_recibe" class="active">Unidad Funcional (Recibe)</label>
                </div>
                <div class="input-field col-sm-2">   
                    <select name="cb_estado" id="cb_estado" class="form-control selectpicker" data-size="5">${requestScope['cb_estado']}</select>
                    <label for="cb_estado" class="active">Estado</label>
                </div>
                <div class="input-field col-sm-1"> 
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="input-field col-sm-12">        
                <div class="input-field col-md-1">
                </div>
                <div class="input-field col-sm-2">   
                    <select name="cb_clsdoc" id="cb_clsdoc" class="form-control selectpicker" data-size="5" data-live-search="true">${requestScope['cb_clsdoc']}</select>
                    <label for="cb_clsdoc" class="active">Tipo documento</label>
                </div> 
                <div class="input-field col-md-1">
                    <input id="txt_nro" type="text" value=""/>
                    <label for="txt_nro" class="active">N° Documento</label>
                </div> 
                <div class="input-field col-sm-2">
                </div>    
<!--                <div class="input-field col-sm-2">   
                    <select name="cb_envia" id="cb_envia" class="form-control selectpicker" data-size="5" data-live-search="true">${requestScope['cb_personal']}</select>
                    <label for="cb_envia" class="active">Personal (Envía)</label>
                </div>-->
                <div class="input-field col-sm-3">   
                    <select name="cb_recibe" id="cb_recibe" class="form-control selectpicker" data-size="5" data-live-search="true">${requestScope['cb_personal']}</select>
                    <label for="cb_recibe" class="active">Personal (Recibe)</label>
                </div>
                <div class="input-field col-sm-3">       
                    <!--<button id="btn_busca" onclick="sgd_mant_expediente_dir_tbl(document.getElementById('txt_cut').value,document.getElementById('cb_periodo').value,document.getElementById('txt_asun').value,document.getElementById('cb_clsdoc').value,document.getElementById('txt_nro').value,document.getElementById('cb_envia').value,document.getElementById('cb_recibe').value)" class="btn btn-info btn-sm" >-->
                    <button id="btn_busca" onclick="sgd_mant_expediente_dir_tbl()" class="btn btn-info btn-sm" >
                        BUSCAR       
                    </button>
                </div>    
            </div> 
        </div> 
        <br><br> 
        <hr>        
        <div class="row">
            <div class="col-sm-12">               
                <div class="row">  
                    <div class="input-field col-sm-1">        
                    </div>
                </div>             
                <div class="row">  
                    <div class="input-field col-sm-1">        
                    </div>
                    <div class="col-sm-10" id="div_expediente_dir_tbl" >                     
                    </div>
                    <div class="input-field col-sm-1">        
                    </div>
                </div>      
            </div>    
        </div>     
    </div>            
</div> 
             
<!--<div id="div_buscar_altdir_tbl"></div>-->

                    
<script>
//    sgd_mant_buscar_altdir_tbl();
  $('#cb_periodo option:first-child').attr('selected', 'selected');
</script>