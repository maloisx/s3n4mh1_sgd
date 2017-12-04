${requestScope['response']}  

<div id="div_bandejas"> 
    <div class="col-xs-2"></div>
        <div class="col-xs-10" align="left">
            <button onclick="sgd_mant_expediente_nuevo_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-file"></span> Nuevo Expediente</button>
            <button onclick="sgd_mant_agrupa_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-folder-open" ></span> Agrupar Expediente</button>
            <button onclick="sgd_mant_desarchiva_flujo_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-paperclip" ></span> Desarchivar</button>
            <!--<button onclick="sgd_mant_buscar_exp_popup()" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-search" ></span> BUSCAR EXPEDIENTE</button>-->
        </div>
    <br>
    <hr>

    <div class="col-xs-2" id="myTopnav">
        <ul class="nav nav-tabs tabs-left " role="tab">
            ${requestScope['tab_bandeja_button']}
        </ul>
    </div>
        
    <div class="col-xs-9" style="overflow-x:unset;">        
        <div class="tab-content" id="tabla_bandeja" role="tabpanel">
            ${requestScope['tab_bandeja_content']}  
            
        </div>
    </div>
    <div class="col-xs-1">        
    </div>    
        
    <input type="hidden" name="hd_codUser" id="hd_codUser" value="${requestScope['codUser']}" />       
    <input type="hidden" name="hd_nom_pers" id="hd_nom_pers" value="${requestScope['nom_pers']}" />       
    <input type="hidden" name="hd_id_pers" id="hd_id_pers" value="${requestScope['id_pers']}" />       
    <input type="hidden" name="hd_id_uo" id="hd_id_uo" value="${requestScope['id_uo']}" />       
    <input type="hidden" name="hd_pfl" id="hd_pfl" value="${requestScope['perfil']}" />       
    <input type="hidden" name="hd_id_vt" id="hd_id_vt" value="${requestScope['id_vt']}" />       
    <input type="hidden" name="hd_user_agrupa" id="hd_user_agrupa" value="${requestScope['user_agrupa']}" />       
</div>

<script>
    
    $('a[data-toggle="tab"]').on('shown.bs.tab', function(e){
        $($.fn.dataTable.tables(true)).DataTable().columns.adjust()
    });
</script>
            