<input type="hidden" name="hd_tmp_exp" id="hd_tmp_exp" value="" />  
<div class="row">    
    <div class="col-xs-11">
        <div class="col-xs-1"></div>
        <div class="col-xs-3" align="left">
            <button onclick="sisproj_mant_expediente_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-file"></span> Nuevo Expediente</button>
        </div>
        <div class="input-field text-right col-md-2">
        </div>
    </div>
</div>

<div class="row" id="proj">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div class="center-block" id="div_mant_expediente_tbl"></div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
</div>
                
<script>
    sisproj_expediente_js();
    
           
//    pad_mant_alerta_consulta();
    
</script>

<style>  
  #pad {height: 100%;}  
  th.dt-center, td.dt-center { text-align: center; }
</style>