<div id="div_mensaje_ajax" class="text-success"></div>    
<input type="hidden" name="hd_id" id="hd_id" value="${requestScope['id_doc']}" />
<br>

<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="input-field col-md-1">
            </div>
            <div class="input-field col-sm-3">
                <input id="txt_nro_cut" type="text" value="${requestScope['cut_exp']}"/>
                <label for="txt_nro_cut" class="active">CUT</label>
            </div>
            <div class="input-field col-sm-3">
                <input id="txt_documento" type="text" value="${requestScope['documento']}"/>
                <label for="txt_documento" class="active">Documento</label>
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
            <div class="input-field col-sm-10">                        
                <form enctype="multipart/form-data">
                    <label>Buscar Archivo</label>
                    <input id="file-sgd" name="file-sgd[]" type="file" multiple class="form-control"  />
                </form>
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
                <button onclick="sgd_mant_accion_guardar()" class="btn btn-info btn-sm">Guardar</button>            
            </div>
            <div class="input-field col-md-1">        
            </div>  
        </div>  
    </div>    
</div>             

<script>
    $('#file-sgd').fileinput({
        language: 'es',
        uploadUrl: '#',
        allowedFileExtensions: ['pdf'],
        showUpload: false,
        //mainClass: "input-group-lg",
        rtl: true
    });
</script>