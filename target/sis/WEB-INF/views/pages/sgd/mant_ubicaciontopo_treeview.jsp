<div class="row">
    <div class="col-md-12">               
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-4" STYLE="border: 1px solid">
                    
                <div class="form-group">
                  <label for="input-search" class="sr-only">Buscador:</label>
                  <input type="input" class="form-control" id="input-search" placeholder="Buscar..." value="">
                </div>
                
                <div class="form-group row">
                    <div class="col-md-6">
                      <button type="button" class="btn btn-success" style="width:100%" id="btn-expand-all">EXPANDE TODO</button>
                    </div>
                    <div class="col-md-6">
                      <button type="button" class="btn btn-danger" style="width:100%" id="btn-collapse-all">COLAPSA TODO</button>
                    </div>
                </div>
                
                <!--<div id="search-output" style="border: 1px solid"></div>-->
                <div id="treeview-searchable" align="left" class=""></div>
                
            </div>            
            <div class="col-md-5" STYLE="border:1px solid; height:600px" id="pdf_preview">                
                Previsualiza el documento
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
    <input type="hidden" id="hd_tipo">
    <input type="hidden" name="hd_unidorg" id="hd_unidorg" value="${requestScope['cod_unid_org']}" />
    <input type="hidden" name="hd_codUser" id="hd_codUser" value="${requestScope['codUser']}" />
</div>

<script type="text/javascript">
    sgd_mant_ubicaciontopo_treeview();
    
</script>

  