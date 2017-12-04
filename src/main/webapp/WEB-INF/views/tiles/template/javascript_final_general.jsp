<script>
        var boostrap_select = $('.selectpicker').selectpicker({
           style: 'btn-info',
           title: " "
        });
        $('select:not(.selectpicker)').material_select();
        $('.datepicker').pickadate({
            monthsFull: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciember'],
            monthsShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
            weekdaysFull: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
            weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
            weekdaysLetter: [ 'D', 'L', 'M', 'M', 'J', 'V', 'S' ],
            showMonthsShort: undefined,
            showWeekdaysFull: undefined,
            today: 'HOY',
            clear: 'LIMPIAR',
            close: 'OK',
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 15, // Creates a dropdown of 15 years to control year
            format: 'dd/mm/yyyy',
            formatSubmit: 'dd/mm/yyyy',
            closeOnSelect: true,
            closeOnClear: true
          });
//        for(var i = 0 ; i< boostrap_select.length ; i ++){
//            $(boostrap_select[i]).selectpicker('toggle');
//            $(boostrap_select[i]).selectpicker('refresh');
//        }
//        .on('hide.bs.select', function (e) {
//           $(e).selectpicker('show');
//        }).on('changed.bs.select', function (e) {
//           console.log(e);
//           $(e['target']).selectpicker('show');
//        })
        
</script>