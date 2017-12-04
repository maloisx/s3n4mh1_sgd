<input type="hidden" name="hd_codUser" id="hd_codUser" value="${requestScope['codUser']}" />

<!--<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>        
            <div class="input-field text-right col-md-1">
                <input id ="txt_cut" type="text" value=""/>n° cut
                <label for="txt_cut" class="active">N° CUT</label>
            </div>
            <div class="input-field text-right col-md-1">
                <select name="cb_periodo" id="cb_periodo" class="form-control selectpicker" data-size="3">${requestScope['periodo']}</select>
                <label for="cb_periodo" class="active">Periodo</label>
            </div>
            <div class="input-field text-left col-md-1">
                <button id="btn_guardar" onclick="sgd_buscarexp_tbl()" class="btn btn-info btn-sm">BUSCAR</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">        
            </div>        
            <div class="input-field text-right col-md-10">
                <hr>         
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div> -->
<div class="row">
    <div class="col-sm-12">               
        <div class="row">  
            <div class="input-field col-sm-1">        
            </div>
        </div>             
        <div class="row">  
            <div class="input-field col-sm-1">        
            </div>
            <div class="col-sm-10" id="div_buscarexp_tbl" >                     
            </div>
            <div class="input-field col-sm-1">        
            </div>
        </div>      
    </div>    
</div> 
<br>          
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">        
            </div>        
            
            <div class="input-field col-sm-1">        
            </div>  
        </div>  
    </div>    
</div>                
                
<script>
    sgd_buscarexp_tbl();    
</script>               
                
                
