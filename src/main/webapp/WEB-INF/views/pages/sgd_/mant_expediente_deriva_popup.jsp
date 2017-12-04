<div id="div_mensaje_ajax" style="font-size:130%; font-weight:bold;"></div>
<div class="row">
    <div class="input-field col-md-12">
        <div class="col-xs-1" >                      
        </div>
        <div class="input-field col-md-2">
            <input name="txt_cut" id ="txt_cut" type="text" value="${requestScope['cut']}" readonly/>
            <label for="txt_cut" class="active">N° CUT</label>
            
            <input type="hidden" name="hd_id_flujo" id="hd_id_flujo" value="" />

            <input type="hidden" name="hd_codUser" id="hd_codUser" value="${requestScope['codUser']}" />
            <input type="hidden" name="hd_perfil" id="hd_perfil" value="${requestScope['perfil']}" />
            <input type="hidden" name="hd_id_pers" id="hd_id_pers" value="${requestScope['id_pers']}" />
            <input type="hidden" name="hd_id_uo" id="hd_id_uo" value="${requestScope['id_uo']}" />
            <input type="hidden" name="hd_uo_pdr" id="hd_uo_pdr" value="${requestScope['uo_pdr']}" />
            <input type="hidden" name="hd_uo_pdr_pdr" id="hd_uo_pdr_pdr" value="${requestScope['uo_pdr_pdr']}" />       
            <input type="hidden" name="hd_id_flujo_ant" id="hd_id_flujo_ant" value="${requestScope['id_flujo']}" />  
            <input type="hidden" name="hd_id_doc" id="hd_id_doc" value="${requestScope['id_doc']}" />
            <input type="hidden" name="hd_nivel" id="hd_nivel" value="${requestScope['nivel']}" />
        </div>
        <div class="input-field col-md-2">
            <input id ="txt_cut" type="text" value="${requestScope['per']}" readonly/>
            <label for="txt_cut" class="active">Periodo</label>
        </div>
        <div class="col-md-3">
            <input id="chk_copia_exp" name="chk_copia_exp" type="checkbox" class="validate" value="1"/>
            <label for="chk_copia_exp" class="active">Mantener copia</label> 
        </div>
        <div class="col-md-3">
<!--            <input id="chk_fisico_exp" name="chk_fisico_exp" type="checkbox" class="validate" value="1"/>
            <label for="chk_fisico_exp" class="active">Mantener copia</label> -->
        </div>
        <div class="col-xs-1" >                      
        </div>    
    </div>    
</div> 
            
<div class="row">
    <div class="input-field col-md-12">
        <div class="col-xs-1" >                      
        </div>
        <div class="col-xs-10" >                        
            <div class="tab-pane" id="div_lista_doc">        
                ${requestScope['tab_exp_content']}
            </div>
        </div> 
        <div class="col-xs-1" >                      
        </div>    
        <div class="col-xs-1" >
        </div>      
    </div>
</div>  
        
<div class="row">      
    <div class="form-control input-field col-md-12" style="height: 100px;">        
        <div class="row">
            <div class="col-xs-1">        
            </div> 
            <div class="col-xs-5">  
                <lable for="cb_cd_ini" class="active">Seleccione Unidad Orgánica:</lable> 
                <select class="form-control selectpicker " data-size="4" name="cb_cd_ini" id="cb_cd_ini" multiple data-live-search="true" multiple data-actions-box="true" onchange="charge_list_boostrap_select('cb_cd_ini','cb_cd_fin')">${requestScope['cb_uo']}</select>
            </div>
            <div class="col-xs-5">   
                <lable for="cb_cd_fin" class="active">Unidad Orgánica Seleccionada:</lable>
                <select class="form-control selectpicker" data-size="4" name="cb_cd_fin" id="cb_cd_fin" multiple  data-live-search="true" data-actions-box="true" onchange="delete_cascade_list_boostrap_select('cb_cd_fin','cb_cd_ini')"></select>                    
                <!--data-always-open="true"--> 
            </div>  
            <div class="col-xs-1">        
            </div>
        </div>  
    </div>
</div>            

<div class="row">
    <div class="col-xs-1">
    </div>
    <div class="input-field col-md-10">
        <textarea  id="txt_obs" class="materialize-textarea" type="text"></textarea>
        <label for="txt_obs" class="active">Observaciones</label>
    </div>
</div>
            
<div class="row">
    <div class="col-xs-1">        
    </div> 
    <div class="col-md-10 text-right">         
        <!--<button onclick="sgd_mant_expediente_deriva()" class="btn btn-info btn-sm">Derivar</button>-->  
        
        <button onclick="sgd_mant_expediente_deriva_accion_popup()" onfocus="lista_doc_deriva()" class="btn btn-info btn-sm">Siguiente</button> 
        <button onclick="$.colorbox.close()" class="btn btn-info btn-sm" >Cerrar</button>  
    </div>
    </div>    
</div>

