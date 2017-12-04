/*!
 * FileInput Spanish Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";

    $.fn.fileinputLocales['es'] = {
        fileSingle: 'archivo',
        filePlural: 'archivos',
        browseLabel: 'Examinar &hellip;',
        removeLabel: 'Quitar',
        removeTitle: 'Quitar archivos seleccionados',
        cancelLabel: 'Cancelar',
        cancelTitle: 'Abortar la subida en curso',
        uploadLabel: 'Subir archivo',
        uploadTitle: 'Subir archivos seleccionados',
        msgNo: 'No',
        msgNoFilesSelected: 'No hay archivos seleccionados',
        msgCancelled: 'Cancelado',
        msgZoomModalHeading: 'Vista previa detallada',
        msgFileRequired: 'You must select a file to upload.',
        msgSizeTooSmall: 'El archivo "{name}" (<b>{size} KB</b>) es demasiado pequeño y debe ser mayor de <b>{minSize} KB</b>.',
        msgSizeTooLarge: 'El archivo "{name}" (<b>{size} KB</b>) excede el tamaño máximo permitido de <b>{maxSize} KB</b>.',
        msgFilesTooLess: 'Debe seleccionar al menos <b>{n}</b> {files} a cargar.',
        msgFilesTooMany: 'El nro de archivos seleccionados a cargar <b>({n})</b> excede el límite máximo permitido de <b>{m}</b>.',
        msgFileNotFound: 'Archivo "{name}" no encontrado.',
        msgFileSecured: 'No es posible acceder al archivo "{name}" porque estará siendo usado por otra aplicación o no tengamos permisos de lectura.',
        msgFileNotReadable: 'No es posible acceder al archivo "{name}".',
        msgFilePreviewAborted: 'Previsualización del archivo "{name}" cancelada.',
        msgFilePreviewError: 'Ocurrió un error mientras se leía el archivo "{name}".',
        msgInvalidFileName: 'Caracteres no válidos o no soportados en el nombre de archivo "{name}".',
        msgInvalidFileType: 'Tipo de archivo no válido para "{name}". Sólo se permiten archivos de tipo "{types}".',
        msgInvalidFileExtension: 'Extensi�n de archivo no v�lido para "{name}". S�lo se permiten archivos "{extensions}".',
        msgFileTypes: {
            'image': 'image',
            'html': 'HTML',
            'text': 'text',
            'video': 'video',
            'audio': 'audio',
            'flash': 'flash',
            'pdf': 'PDF',
            'object': 'object'
        },
        msgUploadAborted: 'La carga de archivos se ha cancelado',
        msgUploadThreshold: 'Procesando...',
        msgUploadBegin: 'Inicializando...',
        msgUploadEnd: 'Hecho',
        msgUploadEmpty: 'No existen datos válidos para el envío.',
        msgValidationError: 'Error de validación',
        msgLoading: 'Subiendo archivo {index} de {files} &hellip;',
        msgProgress: 'Subiendo archivo {index} de {files} - {name} - {percent}% completado.',
        msgSelected: '{n} {files} seleccionado(s)',
        msgFoldersNotAllowed: 'Arrastre y suelte únicamente archivos. Omitida(s) {n} carpeta(s).',
        msgImageWidthSmall: 'El ancho de la imagen "{name}" debe ser de al menos {size} px.',
        msgImageHeightSmall: 'La altura de la imagen "{name}" debe ser de al menos {size} px.',
        msgImageWidthLarge: 'El ancho de la imagen "{name}" no puede exceder de {size} px.',
        msgImageHeightLarge: 'La altura de la imagen "{name}" no puede exceder de {size} px.',
        msgImageResizeError: 'No se pudieron obtener las dimensiones de la imagen para cambiar el tamaño.',
        msgImageResizeException: 'Error al cambiar el tamaño de la imagen.<pre>{errors}</pre>',
        msgAjaxError: 'Algo ha ido mal con la operación {operation}. Por favor, inténtelo de nuevo mas tarde.',
        msgAjaxProgressError: 'La operación {operation} ha fallado',
        ajaxOperations: {
            deleteThumb: 'Archivo borrado',
            uploadThumb: 'Archivo subido',
            uploadBatch: 'Datos subidos en lote',
            uploadExtra: 'Datos do formulario subidos '
        },
        dropZoneTitle: 'Arrastre y suelte aqu� los archivos &hellip;',
        dropZoneClickTitle: '<br>(o haga clic para seleccionar {files})',
        fileActionSettings: {
            removeTitle: 'Eliminar archivo',
            uploadTitle: 'Subir archivo',
            zoomTitle: 'Ver detalles',
            dragTitle: 'Mover / Reordenar',
            indicatorNewTitle: 'No subido todavía',
            indicatorSuccessTitle: 'Subido',
            indicatorErrorTitle: 'Error al subir',
            indicatorLoadingTitle: 'Subiendo...'
        },
        previewZoomButtonTitles: {
            prev: 'Ver archivo anterior',
            next: 'Ver archivo siguiente',
            toggleheader: 'Mostrar encabezado',
            fullscreen: 'Mostrar a pantalla completa',
            borderless: 'Activar el modo sin bordes',
            close: 'Cerrar vista detallada'
        }
    };
})(window.jQuery);
