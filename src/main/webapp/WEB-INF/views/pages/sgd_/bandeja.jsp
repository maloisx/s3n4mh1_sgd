<!--<div id="div_bandeja_tbl"></div>
<script>
//    sgd_mant_expediente_tbl();
</script>-->
<!--Tabs--> 
 
<div id="div_bandejas"> 
    <div class="col-xs-2"></div>
        <div class="col-xs-10" align="left">
            <button onclick="${requestScope['btn_nuevo_reg']}" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-file"></span> Nuevo Expediente</button>
            <button onclick="${requestScope['btn_agrupa_reg']}" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-file" ></span> Agrupar Expediente</button>

            <button onclick="${requestScope['btn_desarchiva_reg']}" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-folder-close" ></span> Desarchivar</button>
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
            <script>  
                
            </script> 
        </div>
    </div>
    <div class="col-xs-1">        
    </div>    
        
    <input type="hidden" name="hd_pfl" id="hd_pfl" value="${requestScope['perfil']}" />       
</div>
        
    
<script>      
//función para responsive de tabs
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

    


</script>       


