<br>
<br>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-12">        
<!--                <div class="input-field col-sm-1">
                </div>-->
                <div class="input-field col-sm-2">
                    <input id="txt_expediente" type="text" style="text-align: center; color: red; font-size:130%; font-weight:bold;" />
                    <label for="txt_expediente" class="active">Expediente N°</label>
                </div>
                <div class="input-field col-sm-2">   
                    <select name="cb_anio" id="cb_anio" class="form-control selectpicker">${requestScope['cb_periodo']}</select>
                    <label for="cb_anio" class="active">Año</label>
                </div>                
                <div class="input-field col-sm-1">       
                    <button id="btn_busca" onclick="sgd_mant_atencion_ciudadano_tbl(),sgd_mant_atencion_ciudadano_doc()" class="btn btn-info btn-sm" >
                        BUSCAR       
                    </button>
                </div>                
            </div>
        </div>
        <div class="row">
            <div class="input-field col-sm-12">
<!--                <div class="input-field col-sm-1">
                </div>-->
                <div id="div_asunto" class="input-field col-sm-12">                     
                    <input name="txt_asunto" id="txt_asunto" type="text" readonly style="font-size:120%; font-weight:bold;" />
                    <label for="txt_asunto" id="lb_asunto">Documento</label>
                </div>     
            </div>            
        </div>            
        <br> 
        <!--<hr>-->        
        <div class="row">
            <div class="col-sm-12">
<!--                <div class="row">  
                   <div class="col-sm-12">     
                        <div class="input-field col-sm-1">        
                        </div>
                        <div class="row">-->
                            <div id="div_reporte" class="col-sm-12" style="background-color:#E9FFFF; border-color:#999; border-style:solid; border-radius: 10px;">
                                <div class="row">                                        
                                    <div class="col-sm-12">
                                        <br>                                     
                                        <div id="div_atencion_ciudadano_tbl">                     
                                        </div> 
                                        <br>                                            
                                    </div>                                        
                                </div>
                            </div>
                        <!--</div>-->
<!--                        <div class="input-field col-sm-1">        
                        </div>    
                    </div>                     
                </div>      -->
            </div>    
        </div>     
    </div>            
</div> 
                                 
<script>
    $('#cb_anio option:first-child').attr('selected', 'selected');
  
    $('#div_reporte').toggle();
    $('#div_asunto').toggle();

    $('#btn_busca').click(function() {
        $('#div_reporte').show();
        $('#div_asunto').show();
        $('#lb_asunto').addClass('active');
    });
</script>

