
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10">   
        <form enctype="multipart/form-data">
                <label>Buscar Archivo</label>
                <input id="ptoobs_file" name="ptoobs_file[]" type="file" multiple class="form-control"  />
            </form>
    </div>
</div>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10 text-center">   
        <!--<button id="btn_procesar" onclick="sisdad_js_mant_ptoobs_subirfile_btn_leer()" class="btn btn-info btn-sm" >Leer Archivos</button>--> 
        <button id="btn_procesar" onclick="sisdad_js_mant_ptoobs_subirfile_btn()" class="btn btn-info btn-sm" >PROCESAR</button> 
    </div>
</div>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10 text-center">   
       <div class="progress">
           <div class="progress-bar progress-bar-striped active progress-bar-animated " id="pb_subirfile" style="width: 0%" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100" >
        </div>
      </div>
    </div>
</div>
<div class="row">
    <div class="input-field col-sm-1"></div> 
    <div class="input-field col-sm-10">   
        <label>LOG: ( Si encuentra error comunicarse al correo mpuza@senamhi.gob.pe o llamar al número 982 569 94. )</label><br><br>
        <div id="id_div_log" style="border: 1px solid"></div>        
    </div>
</div>
<input type="hidden" id="hd_datos"/>
<script>
    $('#ptoobs_file').fileinput({
        language: 'es',
        uploadUrl: '#',
        allowedFileExtensions: ['xls'],
        showUpload: false,
        //mainClass: "input-group-lg",
        rtl: true
    });
</script>