<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-12">        
<!--                <div class="input-field col-sm-1">
                </div>-->
                <div class="input-field col-sm-1">   
                    <select name="cb_periodo" id="cb_periodo" class="form-control selectpicker" >${requestScope['cb_periodo']}</select>
                    <label for="cb_periodo" class="active">Periodo</label>
                </div>
<!--                <div class="input-field col-sm-1">
                </div>  -->
                <div class="input-field col-sm-3">   
                    <select name="cb_direccion" id="cb_direccion" class="form-control selectpicker" data-size="5" data-live-search="true" onchange="sgd_mant_subdireccion_cbo()">${requestScope['cb_unidfuncD']}</select>
                    <label for="cb_direccion" class="active">Dirección</label>
                </div>      
                <div class="input-field col-sm-3">   
                    <select name="cb_subdireccion" id="cb_subdireccion" class="form-control selectpicker" data-size="5" >${requestScope['cb_unidfunc']}</select>
                    <label for="cb_subdireccion" class="active">Unidad Funcional</label>
                </div>
                <div class="input-field col-sm-3">   
                    <select name="cb_recibe" id="cb_recibe" class="form-control selectpicker" data-size="5" data-live-search="true">${requestScope['cb_personal']}</select>
                    <label for="cb_recibe" class="active">Personal</label>
                </div>
                <div class="input-field col-sm-1"> 
                    <button id="btn_busca" onclick="sgd_mant_pendientes_tbl()" class="btn btn-info btn-sm" >
                        BUSCAR       
                    </button>
                </div>
            </div>
        </div>
        <br>
<!--        <div class="row">
            <div class="input-field col-sm-12"> 
                <div class="input-field col-md-5">
                </div>
                <div class="input-field col-sm-3">       
                    <button id="btn_busca" onclick="sgd_mant_expediente_dir_tbl(document.getElementById('txt_cut').value,document.getElementById('cb_periodo').value,document.getElementById('txt_asun').value,document.getElementById('cb_clsdoc').value,document.getElementById('txt_nro').value,document.getElementById('cb_envia').value,document.getElementById('cb_recibe').value)" class="btn btn-info btn-sm" >
                   
                </div>    
            </div> 
        </div> -->
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
                    <div class="col-sm-10" id="div_pendientes_tbl" >                     
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
 // $('#cb_periodo option:first-child').attr('selected', 'selected');
</script>