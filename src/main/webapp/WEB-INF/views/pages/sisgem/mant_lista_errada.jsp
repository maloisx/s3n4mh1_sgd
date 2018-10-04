<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-8">   
        <div id="tabla_prueba" >            
        </div>
    </div>
</div>

<!-- --------------   Modal --------------  -->
  <div class="modal fade" id="modal_mantenimiento" role="dialog">
    <div class="modal-dialog modal-sm">
    
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="lb_title_modal_mantenimiento">Renombrar archivo</h4>
          <button type="button" id="close_modal" class="close" data-dismiss="modal">&times;</button>          
        </div>
        <div class="modal-body">            
            <input type="hidden" id="hd_modal_manteniemiento_idpla" value="">
            <input type="hidden" id="hd_modal_manteniemiento_nomarchi" value="">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class="input-field">												
                        <input type="text" id="txt_modal_archivo">
                        <label for="txt_modal_archivo" class="lbl_active">ARCHIVO</label>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>			
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class="input-field">												
                        <input type="text" id="txt_modal_error">
                        <label for="txt_modal_error" class="lbl_active">ERRORES</label>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>			
            <div class="row">						
                <div class="col-md-5 text-center">
                        <button type="button" class="btn btn-primary" onclick="sisgem_ver_archivo(txt_modal_archivo.value)" ><span class='glyphicon glyphicon-search'></span></button>
                </div>
                <div class="col-md-2 text-center">                       
                </div>
                <div class="col-md-5 text-center">
                    <button type="button" class="btn btn-primary" onclick="sisgem_valida_nombre(hd_modal_manteniemiento_idpla.value,txt_modal_archivo.value, hd_modal_manteniemiento_nomarchi.value)" ><span class='glyphicon glyphicon-floppy-disk'></button>
                </div>						
            </div>	
        </div>        
    </div>      
    </div> 
  </div>
<!-- --------------   Modal --------------  -->

<script>
   sisgem_lista_errada_js();
</script>
   