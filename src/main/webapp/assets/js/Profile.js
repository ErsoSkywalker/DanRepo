$(document).ready(()=>{
    $.post('InfoProfile', {
        data: ''
    }, function (responseText) {
        $('#ProfileContent').html(responseText);
    });
});


function eliminarReservacion(numero){
    $.post('EliminarReservacion', {
        reservacion: numero
    }, function (responseText) {
        $('#ReservacionesDiv').html(responseText);
    });
}
