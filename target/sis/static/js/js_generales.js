function charge_list_boostrap_select(cb_ini , cb_fin){
     var id_cb_ini = $('#'+cb_ini).val();
//     console.log(id_cb_ini);
     var t_op = '';
     if(id_cb_ini){
     for(var i = 0 ; i<id_cb_ini.length ; i++){
         var t = $('#'+cb_ini+' option[value="'+id_cb_ini[i]+'"]').text();
         t_op += '<option value="'+id_cb_ini[i]+'" selected>'+t+'</option>'
//         console.log(id_cb_ini[i] +" -> " + t);
//         console.log(t_op);
         $('#'+cb_fin).html(t_op);
         $('#'+cb_fin).selectpicker('refresh');
     }
     }else{
         $('#'+cb_fin).html('');
         $('#'+cb_fin).selectpicker('refresh');
     }         
}

function delete_cascade_list_boostrap_select(cb_fin , cb_ini){
//     var id_cb_fin = $('#'+cb_fin).val();
//     console.log(id_cb_fin);
//     var t_op = '';
     var obj = $('#'+cb_fin).children();
     
     for(var i = 0 ; i<obj.length ; i++){
        var x_val = obj[i].value;
        var x_sel = obj[i].selected;
//        console.log(x_val + '->' + x_sel); 
        if(x_sel == false){
            $('#'+cb_fin+' option[value="'+x_val+'"]').remove();
            $('#'+cb_fin).selectpicker('refresh');
            $('#'+cb_ini+' option[value="'+x_val+'"]').attr('selected', false);
            $('#'+cb_ini).selectpicker('refresh');
        }
     }
     
 }