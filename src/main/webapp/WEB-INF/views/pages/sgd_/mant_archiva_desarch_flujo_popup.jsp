<div id="div_mensaje_ajax" class="text-success"  style="font-size:130%; font-weight:bold;"></div>    
<input type="hidden" name="hd_codUser" id="hd_codUser" value="${requestScope['codUser']}" />
<br>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="input-field col-sm-1">        
            </div>        
            <div class="input-field col-sm-3">                
                <input id="txt_cut" type="text" value=""/>
                <label for="txt_cut" class="active" >CUT N°:</label>
            </div>
                        
            <div class="input-field col-sm-3">   
                <select name="cb_periodo" id="cb_periodo" class="form-control selectpicker">${requestScope['cb_periodo']}</select>
                <label for="cb_periodo" class="active">Periodo:</label>
            </div>
            <div class="input-field col-sm-4">
                <button onclick="sgd_mant_exp_archivados_consulta()" class="btn btn-info btn-sm">Buscar</button>            
            </div>    
            <div class="input-field col-sm-1"> 
            </div>  
        </div>  
    </div>
</div>  
<br>
<br>
<br>
<!--<div class="row">
    <div class="col-sm-12">
        <div class="row" id="div_expedientes">
            <div class="input-field col-sm-1">        
            </div>
            <div class="input-field col-sm-7 " id="tab_exp_archivados_tbl">    
                <script>
                    sgd_mant_exp_archivados_consulta(document.getElementById('txt_cut').value,'', ${requestScope['codUser']});
                </script>
            </div>            
            <div class="input-field col-sm-1">        
            </div>
        </div>
    </div>    
</div>            -->

                
<div class="row">
    <div class="col-sm-12">               
        <div class="row">  
            <div class="input-field col-sm-1">        
            </div>
        </div>             
        <div class="row">  
            <div class="input-field col-sm-1">        
            </div>
            <div class="col-sm-10" id="tab_exp_archivados_tbl" > 
                <script>
//                    sgd_mant_exp_archivados_consulta(document.getElementById('txt_cut').value,'', ${requestScope['codUser']});
                    sgd_mant_exp_archivados_consulta();
                </script>
            </div>
            <div class="input-field col-sm-1">        
            </div>
        </div>      
    </div>    
</div>                   