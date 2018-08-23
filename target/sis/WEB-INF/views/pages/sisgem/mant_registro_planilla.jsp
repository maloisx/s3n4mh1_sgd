<div class="row">
    <div class="input-field col-sm-1"></div> 
    
    <div class="input-field col-sm-3">
        <form enctype="multipart/form-data">
            <!--<label>Seleccione los archivo(s)</label>-->
            <input id="file-sisgem" name="file-sisgem[]" type="file" multiple class="form-control" data-show-preview="false" />
        </form>
    </div>
    <div class="input-field col-sm-8"></div> 
    <div class="row col-sm-3">
        <div class="col-md-12 text-center">
            <button type="button" class="btn btn-primary" onclick="sisgem_registro_planilla()">Guardar</button>
        </div>						
    </div>
</div>

<br>

<script>
    $('#file-sisgem').fileinput({
        language: 'es',
        uploadUrl: '#',
        allowedFileExtensions: ['pdf','tif','jpg'],
        showUpload: false,
        //mainClass: "input-group-lg",
        rtl: true,       
        
    });    


</script>