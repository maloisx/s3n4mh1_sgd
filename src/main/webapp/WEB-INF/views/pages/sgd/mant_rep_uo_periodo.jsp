<div class="row" id="reporte">
    <div class="col-sm-12">
        <div class="row">
            <!--<div class="input-field col-sm-12"  id="div_parametro">-->        
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field col-sm-4">   
                <select name="cb_unidorg" id="cb_unidorg" class="form-control selectpicker" data-size="4" data-live-search="true">${requestScope['cb_uo']}</select>
                <label for="cb_unidorg" class="active">Unid. Orgánica</label>
            </div>  
            <div class="input-field col-sm-1">   
                <input type="date" name="dt_fec_ini" id="dt_fec_ini" value=""/>
                <label for="dt_fec_ini" class="active">Fecha Inicial:</label>
            </div> 
            <div class="input-field col-sm-1">   
                <input type="date" name="dt_fec_fin" id="dt_fec_fin"  value=""/>
                <label for="dt_fec_fin" class="active">Fecha Final:</label>
            </div> 
            <div class="input-field col-sm-1">   
                <button id="btn_busca" onclick="sgd_mant_rep_uo_periodo_grf()" class="btn btn-info btn-sm" >
                    BUSCAR       
                </button>
            </div> 
            <div class="input-field col-sm-1">
            </div>
            <!--</div>-->
        </div> 
        <br><br>
        <hr>
        <br><br>     
        <div class="row">            
            <div class="input-field col-sm-1">        
            </div>
            <div class="col-sm-5" id="div_rep_uo_periodo_grf">                     
            </div>
            <div class="col-sm-2">
                
            </div>
        </div>     
    </div>            
</div> 
             
<!--<div id="div_buscar_altdir_tbl"></div>-->
                    
<script>
// $("#div_cuenta_consultado").hide();
// $("#div_cuenta_noconsultado").hide();
</script>

<style>  
  #reporte {height: 100%;}
</style>