<input type="hidden" id="hd_cod_esta" value="${requestScope['cod_esta']}"/>
<input type="hidden" id="hd_cod_goes" value="${requestScope['cod_goes']}"/>
<div  class="demo-section" style="width: 97%;" id="div_nom_esta" >
    ESTACION:     ${requestScope['nom_esta']} 
    <br>
    COD. SENAMHI: ${requestScope['cod_esta']}
    <br>
    COD. GOES:    ${requestScope['cod_goes']} 
</div> 
<br>
<div  class="demo-section" style="width: 97%;" >
    FECHA DE INICIO:  <input id="fecha_ini" value="${requestScope['fecha_ini']}" />
    
    FECHA DE FIN: <input id="fecha_fin" value="${requestScope['fecha_fin']}" />
    
    <button id="btn_show_tbldatos"  class="ui-button ui-widget ui-corner-all" id="btn_mostrar_tbl_datos" onclick="mostrar_tbl_datos()">Mostrar datos</button>
    
</div> 
<br>

<div  class="demo-section" style="width: 97%;" id="div_table_datos" >
</div> 

<script>
    $( "#fecha_ini" ).datepicker({
        //changeMonth: true,
        maxDate: '0',
        dateFormat : 'yy/mm/dd', //yy-mm-dd
        defaultDate: new Date(),
        onClose: function( selectedDate ) {
            $( "#fecha_fin" ).datepicker( "option", "minDate", selectedDate );
        }
    });
    $( "#fecha_fin" ).datepicker({
        //changeMonth: true,
        currentText: "Now",
        maxDate: '0',
        dateFormat : 'yy/mm/dd',
        defaultDate: new Date(),
        onClose: function( selectedDate ) {
            $( "#fecha_ini" ).datepicker( "option", "maxDate", selectedDate );
        }
    });
</script>