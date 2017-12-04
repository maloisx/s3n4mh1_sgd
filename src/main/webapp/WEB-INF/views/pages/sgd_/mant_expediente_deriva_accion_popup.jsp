<div id="div_mensaje_ajax1" class="text-success" style="font-size:130%; font-weight:bold;"></div>
<input type="hidden" name="hd_id_doc" id="hd_id_doc" value="${requestScope['id_doc']}" />
<input type="hidden" name="hd_cd_fin" id="hd_cd_fin" value="" />
<input type="hidden" name="hd_observa" id="hd_observa" value="${requestScope['observa']}" />

<!--<br>
<div class="row">
    <div class="input-field col-xs-12">
        <div class="col-xs-1" >                      
        </div>
        <div class="input-field col-xs-2">
            <input name="txt_cut" id ="txt_cut" type="text" value="${requestScope['cut']}" readonly/>
            <label for="txt_cut" class="active">N° CUT</label>
            
            
        </div>
    </div>    
</div> -->
        
<div class="row">
    <div class="input-field col-xs-12">
        <div class="col-xs-1" >                      
        </div>
        <div class="input-field col-xs-10">
            ${requestScope['tab_table']}
        </div>
    </div>    
</div>          
      
<div class="row">      
    <div class="form-control input-field col-md-12">        
        <div class="row">
            <div class="col-xs-1">        
            </div> 
            <div class="col-xs-10 text-right">     
                <!--<button onclick="sgd_mant_expediente_deriva();this.disabled=true;document.getElementById('volver').disabled=true;" class="btn btn-info btn-sm">Derivar</button>-->  
                <button id="deriva_exp" onclick="sgd_mant_expediente_deriva()" class="btn btn-info btn-sm">Derivar</button>  
                <button id="volver" onclick="$.colorbox2.close()" class="btn btn-info btn-sm">Volver</button>  
                <button onclick="$.colorbox2.close();$.colorbox.close()" class="btn btn-info btn-sm">Cerrar</button>                 
            </div>  
            <div class="col-xs-1">        
            </div>
        </div>  
    </div>
</div>        

<script>
    
//    function accion(){
//        var rpta_r = "";    
//        var rpta_c = "";    
//        $('.rd_accion_r:checked').each(function () {
//            rpta_r +=  $(this).attr("cod") + ",";  
//        });
//        
//         $('.rd_accion_c:checked').each(function () {
//            rpta_c +=  $(this).attr("cod") + ",";  
//        });
//        alert("r->"+rpta_r);
//        alert("c->"+rpta_c);
//    }
// 
</script>
            
        