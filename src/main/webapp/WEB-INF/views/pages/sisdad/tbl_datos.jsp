<input type="hidden" id="json_datos" value='${requestScope['json_datos']}'/>
<div id="tabs_resumen_datos">
  <ul>
    <li><a href="#tabs_datos">DATOS</a></li>
    <li><a href="#tabs_graf">GRAFICOS</a></li>
  </ul>
  <div id="tabs_datos">
      ${requestScope['c_table_datos']}
  </div>
  <div id="tabs_graf">
      PARAMETRO: <select id="cb_param" onchange="graf_datos();" class="selectpicker"> ${requestScope['cb_param']} </select>
      <br>
      <input type="checkbox" id="chb_datos_horarios" onchange="graf_datos();" ><label for="chb_datos_horarios"></label>Mostrar solo datos Horarios
      <br><br>
      <input type="checkbox" id="chb_datos_observador" onchange="graf_datos();" checked><label for="chb_datos_observador"></label>Mostrar datos Observados
      <br><br>
      <div class="demo-section" style="width: 100% ;">
          <div id='chart_div'>              
          </div>
      </div>
      
      
  </div>
 </div>
  
  <script>
  $( function() {
    $( "#tabs_resumen_datos" ).tabs();  
  } );
  </script>

  