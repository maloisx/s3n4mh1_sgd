<div class="row">    
    <div class="input-field col-sm-12">
        <div class="input-field col-sm-1"></div> 
        <div class="input-field col-sm-3">   
            <select name="cb_tipo_planilla" id="cb_tipo_planilla" class="form-control selectpicker" data-live-search="true" onchange="cod_tplanilla(this.options[this.selectedIndex].value); sisgem_cargar_dz()"></select>
            <label for="cb_tipo_planilla" class="active">Tipo planilla</label>
            <input type="hidden" id="hd_tplanilla" value="">
            <input type="hidden" id="hd_dz" value="">
            <input type="hidden" id="hd_esta" value="">
            <input type="hidden" id="hd_nom_dz" value="">
        </div>    
        <div class="input-field col-sm-3">
            <h6 id="lb_dz" style="font-weight: bold">                
            </h6> 
        </div>
        <div class="input-field col-sm-4"> 
            <h6 id="lb_esta" style="font-weight: bold">                
            </h6>
        </div>
        <div class="input-field col-sm-1"></div>
    </div> 
</div>

<div class="row">
    <br>
</div>

<div class="row">
    <div class="col-sm-12">
        <div class="col-sm-1"></div> 
        <div class="container col-sm-3" id="accordion">
        </div>
        <div class="container col-sm-6" id="tabla_data">
        </div>
        <div class="col-sm-2 text-left">
            <div id="div_leyenda">
                <p>Leyenda:</p>
                <div class="row">
                    <button type="button" class="btn btn-success" style=" background-color: #40a742"><span class="glyphicon glyphicon-search"></span></button>
                    Planilla digitalizada y digitada
                </div>
                <div class="row">
                    <button type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span></button>
                    Planilla digitalizada
                </div>
                <div class="row">
                    <button type="button" class="btn btn-warning"><span class="glyphicon glyphicon-list-alt"></span></button>
                    Planilla digitada
                </div>
                <div class="row">
                    <button type="button" class="btn btn-white"><span class="glyphicon glyphicon-ban-circle"></span></button>
                    Sin información
                </div>
            </div>
        </div>
    </div>
</div>

<!---------------- Modal ---------------->
  <div class="modal fade" id="modal_visualiza" role="dialog">
    <div class="modal-dialog modal-md">    
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <!--<h4 class="modal-title" id="lb_title_modal_visualiza"></h4>-->
                <h6 class="modal-title" id="lb_title_modal_tplanilla"></h6>
                <div class="dz">
                    <h6 class="modal-title" id="lb_title_modal_dz"></h6> 
                </div>                       
                <h6 class="modal-title" id="lb_title_modal_esta"></h6> 
            </div>
            <div class="modal-body" style="padding: 40px 50px;">
                <div id="imagenes" class="row">                            
                </div>
            </div>        
        </div>      
    </div> 
  </div>
<!---------------- Modal ---------------->

<script>
   sisgem_buscar_tipoplantilla_js();
//   sisgem_cargar_dz();
   
    function cod_tplanilla(cod_tplanilla){
       $('#hd_tplanilla').val(cod_tplanilla);
    };
    function cod_dz(cod_dz){
       $('#hd_dz').val(cod_dz);
    };
    function cod_esta(cod_esta){
       $('#hd_esta').val(cod_esta);
    };
   
    $('#div_leyenda').hide();
</script>
   