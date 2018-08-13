<!-- --------------   Modal --------------  -->
  <div class="modal fade" id="modal_mantenimiento" role="dialog">
    <div class="modal-dialog modal-sm">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="lb_title_modal_mantenimiento">Mantenimiento titulo</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>          
        </div>
        <div class="modal-body">
            
            <input type="hidden" id="hd_modal_manteniemiento_codesta" value="">  
            <input type="hidden" id="hd_modal_manteniemiento_codvar" value="" >           
                        <div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="input-field">												
					    <input type="text" id="txt_modal_fecha">
					    <label for="txt_modal_fecha" class="lbl_active">fecha</label>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>	
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="input-field">												
					    <input type="text" id="txt_modal_valor">
					    <label for="txt_modal_valor" class="lbl_active">VALOR</label>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>			
			<div class="row">
				<div class="col-md-12 text-center">
					<button type="button" class="btn btn-primary" onclick="fn_guardar_prueba()" >Guardar</button>
				</div>						
			</div>	
        </div>
<!--        <div class="modal-footer">-->
<!--          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
<!--        </div>-->
      </div>
      
    </div> 
  </div>
 

<!-- --------------   Modal --------------  -->



<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-5">   
        <input id="txt_nom_esta" type="text" value="" />
        <label for="txt_nom_esta" class="">txt_nom_esta</label>
    </div>
</div>

<br>

<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-5" id="div_lista">   
        
    </div>
</div>

<br>

<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-4">   
        <select name="cb_prueba" id="cb_prueba" class="form-control selectpicker"></select>
        <label for="cb_prueba" class="active">cb_prueba</label>
    </div>
</div>

<br>

<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-8">   
        <div id="tabla_prueba" >
            
        </div>
    </div>
</div>

<script>
   sisbien_prueba_js();
</script>
   