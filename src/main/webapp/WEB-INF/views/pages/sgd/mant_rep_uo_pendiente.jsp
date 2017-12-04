<div class="row" id="reporte">
    <div class="col-sm-12">
        <div class="row">     
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field col-sm-4">   
                <!--<select name="cb_unidorg" id="cb_unidorg" class="form-control selectpicker" data-size="3">${requestScope['cb_uo']}</select>-->
                <select name="cb_unidorg" id="cb_unidorg" class="form-control selectpicker" data-size="4" data-live-search="true" onchange="sgd_mant_rep_uo_pendiente_tbl();sgd_mant_cuenta_pendiente();sgd_mant_cuenta_consultado();sgd_mant_cuenta_noconsultado()">${requestScope['cb_uo']}</select>
                <label for="cb_unidorg" class="active">Unid. Orgánica</label>
            </div>
            <div class="input-field col-sm-1">
            </div>
        </div>
        <br><br>
        <hr>
        <br><br>
        <div class="row active">
            <div class="input-field col-sm-1">
            </div>
            <div class="col-sm-5" id="div_rep_uo_pendiente_tbl">
            </div>
            <div class="col-sm-2">
                <div class="row">
                    <div class="input-field col-sm-5" id="div_cuenta_pendiente">
                        <input id ="txt_pendiente" readonly type="text" style="color: black; font-size:130%; font-weight:bold;"/>
                        <label for="txt_pendiente" id="lb_pendiente" class="active">Pendientes</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col-sm-5" id="div_cuenta_consultado">
                        <input id ="txt_consultado" readonly type="text" style="color: blue; font-size:130%; font-weight:bold;"/>
                        <label for="txt_consultado" id="lb_consultado" class="active">Consultados</label>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="input-field col-sm-5" id="div_cuenta_noconsultado">
                        <input id ="txt_noconsultado" readonly type="text" style="color: red; font-size:130%; font-weight:bold;"/>
                        <label for="txt_noconsultado" id="lb_noconsultado" class="active">No Consultados</label>
                    </div>
                </div>
            </div>
        </div>     
    </div>
</div>
                           
<script>
 $("#div_cuenta_pendiente").hide();
 $("#div_cuenta_consultado").hide();
 $("#div_cuenta_noconsultado").hide();
</script>

<style>
    #reporte {height: 100%;}
</style>