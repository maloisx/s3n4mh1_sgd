<div class="row col-sm-12">
    <div class="input-field col-sm-1"></div>     
    <div class="input-field col-sm-4">
        <form enctype="multipart/form-data" >
            <div style="border: 2px solid blue;">
            <!--<label>Seleccione los archivo(s)</label>-->
                <input id="file-sisgem" name="file-sisgem[]" type="file" multiple  data-show-preview="false" />
            <!--<label class="custom-file-label" for="file-sisgem" ></label>-->
            <!--<input id="file-sisgem" name="file-sisgem[]" type="file" multiple class="form-control" data-show-preview="false" />-->
            </div> 
        </form>
    </div>
    <div class="input-field col-sm-7"></div>    
</div>

<div class="row col-sm-12">
    <br><br>
    <div class="input-field col-sm-1"></div> 
    <div class="col-sm-4 text-right">
        <button type="button" class="btn btn-primary" onclick="sisgem_registro_planilla()">Guardar</button>
    </div>
    <div class="input-field col-sm-7"></div>
</div>

<div class="row col-sm-12">
    <div class="input-field col-sm-1"></div>
    <div class="input-field col-sm-4" id="div_msg" name="div_msg"></div>
    <div class="input-field col-sm-7"></div>
</div>
<div class="row col-sm-12">
    <div class="input-field col-sm-1"></div>
    <div class="input-field col-sm-4" id="div_msg" name="div_listatmp"></div>
    <div class="input-field col-sm-7"></div>
</div>
<br>

<script>
    $('#file-sisgem').fileinput({
        language: 'es',
        uploadUrl: '#',
        allowedFileExtensions: ['pdf','tif','jpg'],
        showUpload: false,
        //mainClass: "input-group-lg",
        rtl: true
    });    


</script>

