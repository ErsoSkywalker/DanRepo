
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return typeof sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
    return false;
};

function AgregarFavs() {
    
    $.post('AgregarFavs', {
        idHotel: getUrlParameter('idHotel')
    }, function (responseText) {
        Swal.fire(
                'Bien!',
                'Agregado a favoritos',
                'success'
                );
    });
}

function AgregarComentario(){
    
    $.post('AgregarComentario', {
        idHotel: getUrlParameter('idHotel'),
        Comentario: $('#ComentarioInput').val(),
        img: $('#imgInput').val()
    }, function (responseText) {
        $('#ComentariosContent').html(responseText);
        Swal.fire(
                'Bien!',
                'Agregado un comentario',
                'success'
                );
    });
    
}

function agregarReservacion(){
    alert($('#datePicker').val());
    $.post('AgregarReservacion', {
        idHotel: getUrlParameter('idHotel'),
        Fecha: $('#datePicker').val()
    }, function (responseText) {
        Swal.fire(
                'Bien!',
                'Agregada una fecha',
                'success'
                );
    });
}

$(document).ready(() => {

    $.post('InfoHotel', {
        idHotel: getUrlParameter('idHotel')
    }, function (responseText) {
        $('#contentOmg').html(responseText);
    });




    //getUrlParameter('idHotel');
});


