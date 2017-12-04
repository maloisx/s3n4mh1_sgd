<div class="row" id="reporte">
    <div class="col-sm-12">
        <div class="row">                 
            <div class="input-field col-sm-1">
            </div>
            <div class="input-field col-sm-4">   
                <select name="cb_unidorg" id="cb_unidorg" class="form-control selectpicker" data-size="5" data-live-search="true" onchange="sgd_mant_rep_uo_prof_grf()">${requestScope['cb_uo']}</select>
                <label for="cb_unidorg" class="active">Unid. Orgánica</label>
            </div> 
            <div class="input-field col-sm-1">
            </div>           
        </div> 
        <br>
        <hr>
        <br>     
        <div class="row">            
            <div class="input-field col-sm-1">        
            </div>
            <div class="col-sm-10" id="div_rep_uo_prof_grf">                     
            </div>  
            <div class="input-field col-sm-1">        
            </div>
        </div>     
    </div>            
</div> 

<style>  
  #reporte {height: 100%;}
</style>