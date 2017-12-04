<%-- 
    Document   : index
    Created on : 06/03/2017, 02:57:06 PM
    Author     : Usuario
--%>
<!--${requestScope['response']}-->
<br>
<div id="div_response"></div>
<script>
    var interval = 1000;
    setInterval(function(){ 
        
        $.ajax({
            dataType: "html",
            type:     "GET",
            url:      path + "sgd_monitor_ayax/", 
            data:     "" ,	 	 
            beforeSend: function(data){ 	 	
                    //$('#div_response').html("Cargando...");
            },
            success: function(requestData){
                    $('#div_response').html(requestData);
            },		
            error: function(requestData, strError, strTipoError){											
                    $('#div_response').html("Error " + strTipoError +": " + strError);
            }
        });
        
    }, (interval) );
</script>