<div class="row">
    <div class="col-sm-12">
        <div class="col-sm-1"></div>
        <div class="col-sm-3" align="left">
            <button onclick="sisproj_mant_expedientes_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-file"></span> Nuevo Expediente</button>
        </div>
        <div class="col-sm-8"></div>
    </div>
</div>
    
<div class="row">
    <div class="col-sm-12">
        <div class="col-sm-1"></div> 
        <div class="col-sm-10">   
            <div id="tabla_usuario" >            
            </div>
        </div>
        <div class="col-sm-1"></div>
    </div>
</div>

<!-- --------------   Modal --------------  -->
  <div class="modal fade" id="modal_mantenimiento" role="dialog">
    <div class="modal-dialog modal-md">
    
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="lb_title_modal_mantenimiento">Editar Perfiles</h4>
          <button type="button" id="close_modal" class="close" data-dismiss="modal">&times;</button>          
        </div>
        <div class="modal-body">            
            <input type="hidden" id="hd_modal_mant_idpersona" value="">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-7">
                    <div class="input-field">												
                        <input type="text" id="txt_modal_nompersona">
                        <label for="txt_modal_nompersona" class="lbl_active">Servidor</label>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="input-field">												
                        <input type="text" id="txt_modal_usuario">
                        <label for="txt_modal_usuario" class="lbl_active">Usuario</label>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>			
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-7">
                    <div class="input-field">												
                        <input type="text" id="txt_modal_uo">
                        <label for="txt_modal_uo" class="lbl_active">Unidad Organica</label>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="input-field">												
                        <select name="cb_modal_idperfil" id="cb_modal_idperfil" class="form-control selectpicker" >${requestScope['idperfil']}</select>
                        <label for="cb_modal_idperfil" class="active">Perfil</label>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>			
            <div class="row">
                <div class="col-md-2 text-center">                       
                </div>
                <div class="col-md-5 text-center">
                    <button type="button" class="btn btn-primary" onclick="sisgem_editaperfil_js()" ><span class='glyphicon glyphicon-floppy-disk'></button>
                </div>						
            </div>	
        </div>        
    </div>      
    </div> 
  </div>
<!-- --------------   Modal --------------  -->

<script>
   sisgem_lista_usuarios_js();
</script>
   