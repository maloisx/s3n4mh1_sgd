<div class="row col-sm-12">
    <div class="input-field col-sm-1"></div>
    <div class="input-field col-sm-3">        
        <select name="cb_tipo_planilla" id="cb_tipo_planilla" class="form-control selectpicker" data-live-search="true"></select>
        <label for="cb_tipo_planilla" class="active">Tipo planilla</label>
        <input type="hidden" id="hd_tplanilla" value="">
        
    </div>
    <div class="input-field col-sm-3">
        <select name="cb_dz" id="cb_dz" class="form-control selectpicker" onchange="sisgem_cargar_cbestaciones_js('cb_dz','cb_estacion'); cod_dz(this.options[this.selectedIndex].value);" data-live-search="true"></select>
        <label for="cb_dz" class="active">Dirección Zonal</label>
        <input type="hidden" id="hd_dz" value="">
    </div>
    <div class="input-field col-sm-3">
        <select name="cb_estacion" id="cb_estacion" class="form-control selectpicker" data-live-search="true" onchange="cod_esta(this.options[this.selectedIndex].value);"></select>
        <label for="cb_estacion" class="active">Estación</label>
        <input type="hidden" id="hd_esta" value="">
    </div>
    <div class="col-sm-1 text-center">
        <button type="button" class="btn btn-primary" onclick="sisgem_edita_nombre()" >BUSCAR</button>
    </div>
    <div class="input-field col-sm-1"></div>
</div>

<div class="row col-sm-12">
    <br><br>
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-10" id="div_lista_planilla">        
    </div>
    <div class="input-field col-sm-1"></div>
</div>

<!-- --------------   Modal --------------  -->
  <!--<div class="modal fade" id="modal_mantenimiento" role="dialog">-->
  <div class="modal fade" id="modal_visualiza" role="dialog">
    <div class="modal-dialog modal-lg">    
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" id="close_modal" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title" id="lb_title_modal_mantenimiento" style="font-weight: bold">Renombrar archivo</h4>          
            <!--<h4 class="modal-title" id="modal_visualiza">Renombrar archivo</h4>-->        
            <h6 class="modal-title" id="lb_title_modal_tplanilla"></h6>
            <div class="dz">
                <h6 class="modal-title" id="lb_title_modal_dz"></h6> 
            </div>                       
            <h6 class="modal-title" id="lb_title_modal_esta"></h6>
        </div>
        <div class="modal-body">            
            <input type="hidden" id="hd_modal_manteniemiento_idpla" value="">
            <input type="hidden" id="hd_modal_manteniemiento_anio" value="">
            <input type="hidden" id="hd_modal_manteniemiento_mes" value="">
            <input type="hidden" id="hd_modal_manteniemiento_nommes" value="">
            <input type="hidden" id="lb_bplanilla" value="">
            <input type="hidden" id="lb_dz" value="">
            <input type="hidden" id="lb_esta" value="">            
            <div class="row col-md-12"></div>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="input-field col-md-5">
                    <label for="txt_modal_archivo" id="lbl_modal_archivo">Código asignado</label>												
                    <input type="text" id="txt_modal_archivo" style="font-size: 14px;">
                </div>
                <div class="input-field col-md-5">
                    <label for="txt_modal_archivocorrecto" id="lbl_modal_archivocorrecto">Código nuevo</label>									
                    <input type="text" id="txt_modal_archivocorrecto" style="font-size: 14px; font-weight: bold;">
                </div>
                <div class="col-md-1"></div>
            </div>			
            <div class="row">
                <br>
                <br>
                <div class="col-md-1"></div>
                <div class="input-field col-sm-5">
                    <select name="modal_cb_tipo_planilla" id="modal_cb_tipo_planilla" class="form-control selectpicker" onchange="sisgem_codtp_valido_js(this.options[this.selectedIndex].value)" data-live-search="true"></select>
                    <label for="modal_cb_tipo_planilla" class="active">Tipo planilla</label>
                </div>
                <div class="input-field col-md-5">
                    <select name="modal_cb_dz" id="modal_cb_dz" class="form-control selectpicker" onchange="sisgem_cargar_cbestaciones_js('modal_cb_dz', 'modal_cb_estacion'); sisgem_coddz_valido_js(this.options[this.selectedIndex].value);" data-live-search="true"></select>
                    <label for="modal_cb_dz" class="active">Dirección Zonal</label>
                </div>
                <div class="col-md-1"></div>
            </div>			
            <div class="row">
                <br>
                <br>
                <div class="col-md-1"></div>
                <div class="input-field col-md-10">
                    <select name="modal_cb_estacion" id="modal_cb_estacion" class="form-control selectpicker" data-live-search="true" onchange="sisgem_codesta_valido_js(this.options[this.selectedIndex].value);"></select>
                    <label for="modal_cb_estacion" class="active">Estación</label>
                </div>
                <div class="col-md-1"></div>
            </div>			
            <div class="row">
                <br>
                <br>
                <div class="col-md-1 text-center">                       
                </div>						
                <div class="col-md-10 text-right">
                    <button type="button" class="btn btn-primary" onclick="sisgem_valida_nombre(hd_modal_manteniemiento_idpla.value, txt_modal_archivocorrecto.value, txt_modal_archivo.value, 'V')" >
                        <span class='glyphicon glyphicon-floppy-disk'></span>
                    </button>
                </div>
                <div class="col-md-1 text-center">
                </div>						
            </div>
            <div class="row col-md-12" id="tituloimg">
                <div class="col-md-1">                       
                </div>
                <div class="col-md-11">
                    <h4 style="font-weight: bold">Imágenes:</h4>
                    <br>
                </div>                  
            </div> 
            <div class="row text-center">                   
                <div class="col-md-1">                       
                </div>
                <div id="imagenes" class="input-field col-md-10">                         
                </div>
                <div class="col-md-1">                       
                </div>
            </div> 
            
        </div>        
    </div>      
    </div> 
  </div>
<!-- --------------   Modal --------------  -->

<script>
    sisgem_cargar_cbos_js('cb_tipo_planilla', 'cb_dz');
    
//    $('#tituloimg').hide();
//    $('#imagenes').hide();
   
    function cod_dz(cod_dz){
       $('#hd_dz').val(cod_dz);
    };
    function cod_esta(cod_esta){
       $('#hd_esta').val(cod_esta);
    };    
   
</script>

