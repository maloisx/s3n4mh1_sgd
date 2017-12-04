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
                    <label for="txt_asun" class="active">ASUNTO</label>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="input-field col-sm-12">        
                <div class="input-field col-md-1">
                </div>
<!--                <div class="input-field col-sm-1">   
                    <select name="cb_unidorg" id="cb_unidorg" class="form-control selectpicker" data-size="3">${requestScope['cb_uo']}</select>
                    <label for="cb_unidorg" class="active">Unid. Orgánica</label>
                </div>-->
                <div class="input-field col-sm-2">   
                    <select name="cb_clsdoc" id="cb_clsdoc" class="form-control selectpicker" data-size="3">${requestScope['cb_clsdoc']}</select>
                    <label for="cb_clsdoc" class="active">Tipo documento</label>
                </div> 
                <div class="input-field col-md-1">
                    <input id="txt_nro" type="text" value=""/>
                    <label for="txt_nro" class="active">N° Documento</label>
                </div> 
                <div class="input-field col-sm-2">       
                    <button id="btn_busca" onclick="sgd_mant_buscar_creado_tbl(document.getElementById('txt_cut').value,document.getElementById('cb_periodo').value,document.getElementById('txt_asun').value,document.getElementById('cb_clsdoc').value,document.getElementById('txt_nro').value)" class="btn btn-info btn-sm" >
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
                    <div class="col-sm-10" id="div_buscar_creado_tbl" >                     
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