<input type="hidden" id="hd_cod_esta" value="${requestScope['cod_esta']}"/>
<div  class="demo-section" style="width: 97%;" >

    <div class="row">   
        <div class="input-field col-sm-3">   
            <input name="txt_estacion" id="txt_estacion" type="text" value="" disabled  />
            <label for="txt_estacion" class="active">ESTACION:</label>        
        </div>                

        <div class="input-field col-sm-3">   
            <input name="txt_cod_goes" id="txt_cod_goes" type="text" value="" />
            <label for="txt_cod_goes" class="active">COD. GOES:</label>
        </div>

        <div class="input-field col-sm-3">               
            <label for="cb_tipogoes" class="active">TIPO GOES:</label>
            <select id="cb_tipogoes" class="selectpicker" data-width="100%"  ></select>
        </div>

        <div class="input-field col-sm-3">               
            <label for="cb_estado_esta" class="active">ESTADO</label>
            <select id="cb_estado_esta" class="selectpicker" data-width="100%"  ></select>
        </div>
    </div>


    <div class="row">
        <div class="input-field col-sm-1"></div> 
        <div class="col-sm-10">   
            <div id="sisdad_js_popup_editar_estacion_config_trama_tbl" style="width: 100%" >

            </div>
        </div>
        <div class="input-field col-sm-1"></div> 
    </div>


<!--    <button onclick="popup_editar_estacion_sisdad2(${requestScope['cod_esta']})">abrir ventana 2</button>
    <button onclick="$.colorbox.close();">cerrar ventana 1</button>-->
</div> 


<script>
    sisdad_js_popup_editar_estacion();
</script>
