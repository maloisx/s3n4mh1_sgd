<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-12">        
                <div class="input-field col-sm-1">
                </div>
                <div class="input-field col-sm-1">
                    <input id="txt_expediente" type="text" value=""/>
                    <label for="txt_expediente" class="active">Expediente</label>
                </div>
                <div class="input-field col-sm-1">   
                    <select name="cb_anio" id="cb_anio" class="form-control selectpicker" >${requestScope['cb_periodo']}</select>
                    <label for="cb_anio" class="active">Año</label>
                </div>                
                <div class="input-field col-sm-2">       
                    <button id="btn_busca" onclick="sgd_mant_atencion_ciudadano_tbl()" class="btn btn-info btn-sm" >
                        BUSCAR       
                    </button>
                </div>    
            </div>
        </div>
        <br> 
        <hr>        
        <div class="row">
            <div class="col-sm-12">               
                <div class="row">  
                    <div class="input-field col-sm-1">        
                    </div>
                </div>             
                <div class="row">  
                    <div class="input-field col-sm-1">        
                    </div>
                    <div class="col-sm-10" id="div_atencion_ciudadano_tbl" >                     
                    </div>
                    <div class="input-field col-sm-1">        
                    </div>
                </div>      
            </div>    
        </div>     
    </div>            
</div> 
                                 
<script>
  $('#cb_anio option:first-child').attr('selected', 'selected');
</script>