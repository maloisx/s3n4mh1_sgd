<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-3">   
        <select name="cb_tipo_planilla" id="cb_tipo_planilla" class="form-control selectpicker" data-live-search="true"></select>
        <label for="cb_tipo_planilla" class="active">Tipo planilla</label>
    </div>
    <div class="input-field col-sm-3">   
        <select name="cb_dz" id="cb_dz" class="form-control selectpicker" onchange="sisgem_buscar_plantilla_cargar_cbestaciones(); dz(this.value);" data-live-search="true"></select>
        <label for="cb_dz" class="active">Dirección Zonal</label>
        <input type="hidden" id="hd_dz" value="">
    </div>
    <div class="input-field col-sm-3">   
        <select name="cb_estacion" id="cb_estacion" class="form-control selectpicker" onchange="esta(this.value);" data-live-search="true"></select>
        <label for="cb_estacion" class="active">Estación</label>
        <input type="hidden" id="hd_esta" value="">
    </div>    
    <div class="input-field col-sm-2"></div>
</div>

<div class="row col-sm-12">
    <br><br>
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-9 text-right">
        <button type="button" class="btn btn-primary" onclick="sisgem_buscar_data_js()">BUSCAR</button>
    </div>
    <div class="input-field col-sm-2"></div>
</div>

<br>

<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-10">   
        <div id="tabla_data">
        </div>
    </div>
</div>



<!---------------- Modal ---------------->
  <div class="modal fade" id="modal_visualiza" role="dialog">
    <div class="modal-dialog modal-sm">
    
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="lb_title_modal_visualiza">Visualizar planilla</h4>
              <button type="button" class="close" data-dismiss="modal" aria_label="close">&times;</button>          
            </div>
            <div class="modal-body">
                <div class="row">                    
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="input-field">												
                            <input type="text" id="txt_modal_estacion">
                            <label for="txt_modal_estacion" class="lbl_active">ARCHIVO</label>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>        
        </div>      
    </div> 
  </div>
<!---------------- Modal ---------------->

<script>
   sisgem_buscar_tipoplantilla_js();
   
   function dz(val){
        $("#hd_dz").val(val);
   }
   
   function esta(val){
        $("#hd_esta").val(val);
   }
  
</script>
   