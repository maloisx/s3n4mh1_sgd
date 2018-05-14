<input type="hidden" name="hd_tmp_exp" id="hd_tmp_exp" value="" />  
<div class="row">
    
    <div class="col-xs-11">
        <div class="col-xs-1"></div>
        <div class="col-xs-3" align="left">
            <button onclick="pad_mant_expedientes_pad_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-file"></span> Nuevo Expediente</button>
            <button onclick="pad_mant_asigna_abogado_popup($('#hd_tmp_exp').val())" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-folder-open" ></span> Asignar Abogado</button>
        </div>
        <div class="input-field text-right col-md-2">
            <select name="cb_abogado_lista" id="cb_abogado_lista" class="form-control selectpicker" onchange="pad_mant_expedientes_pad_tbl(this.value)">${requestScope['abogado']}</select>
            <label for="cb_abogado_lista" class="active">Filtro por abogado:</label>
<!--        </div>
            <div class="col-xs-6" align="right" id="div_alertaipad">
            <button onclick="pad_mant_alertaip_popup()" class="btn btn-info btn-sm" style="color: red; font-size:110%; font-weight:bold;"><span class="glyphicon glyphicon-time" style="color: red; font-size:200%;"></span> Alerta IPAD</button>
            <button onclick="pad_mant_alertapad_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-folder-open" ></span> Asignar Abogado</button>
        </div>-->
    </div>
</div>

<div class="row" id="pad">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div class="center-block" id="div_mant_expedientes_pad_tbl"></div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
</div>
                
<script>
    pad_mant_expedientes_pad_tbl();
    
        
    pad_mant_alerta_consulta();
    
</script>

<style>  
  #pad {height: 100%;}
  
  th.dt-center, td.dt-center { text-align: center; }
</style>