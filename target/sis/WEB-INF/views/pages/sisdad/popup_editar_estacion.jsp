<input type="hidden" id="hd_cod_esta" value="${requestScope['cod_esta']}"/>
<div  class="demo-section" style="width: 97%;" >
    COD. SENAMHI: ${requestScope['cod_esta']}
    <button onclick="popup_editar_estacion_sisdad2(${requestScope['cod_esta']})">abrir ventana 2</button>
    <button onclick="$.colorbox.close();">cerrar ventana 1</button>
</div> 
