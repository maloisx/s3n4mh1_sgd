<input type="hidden" name="hd_tmp_exp" id="hd_tmp_exp" value="" />  
<div class="row">
    <div class="col-xs-1"></div>
    <div class="col-xs-11" align="left">
        <button onclick="pad_mant_expedientes_pad_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-file"></span> Nuevo Expediente</button>
        <button onclick="pad_mant_asigna_abogado_popup($('#hd_tmp_exp').val())" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-folder-open" ></span> Asignar Abogado</button>
    </div>
</div>

<div class="row" id="pad">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10" >
                <div id="div_mant_expedientes_pad_tbl"></div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
</div>
                
<script>
    pad_mant_expedientes_pad_tbl();
</script>

<style>  
  #pad {height: 100%;}
</style>