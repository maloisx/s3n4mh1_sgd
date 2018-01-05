<div class="row" id="reporte">
    <div class="col-sm-12">
        <div class="row">     
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-2">
                    <label class="bmd-label-floating" style="color: red; font-size:100%; font-weight:bold;">SELECCIONE PERIODO</label>
                </div>
            </div>      
        </div>  
        <br>
        <div class="row">     
            <div class="input-field col-sm-10">
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-1">
                    <input name="dt_fec_ini" id="dt_fec_ini" type="text" class="datepicker" value="${requestScope['fec_mes']}" style="text-align: center; font-size:120%; font-weight:bold;" />
                    <label for="dt_fec_ini" class="active bmd-label-floating">Fecha Inicial:</label>
                </div>
                <div class="input-field col-sm-1">
                    <input name="dt_fec_fin" id="dt_fec_fin" type="text" class="datepicker" value="${requestScope['hoy']}" style="text-align: center; font-size:120%; font-weight:bold;" />
                    <label for="dt_fec_fin" class="active bmd-label-floating">Fecha Final:</label>
                </div>
                <div class="input-field col-sm-2">
                    <button id="btn_busca" onclick="pad_mant_rep_grf1($('#dt_fec_ini').val(),$('#dt_fec_fin').val())" class="btn btn-info btn-sm" >
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
                    <div class="col-sm-10" id="div_rep_grf" >
                    </div>
                    <div class="input-field col-sm-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style>  
    #reporte {height: 50%;}
</style>

<script>
    
</script>
