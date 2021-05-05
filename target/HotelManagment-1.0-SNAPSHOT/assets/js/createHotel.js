$(document).ready(() => {
    $('#crear').click(() => {
        $.post('CreateHotel', {
            Nombre: $('#Nombre').val(),
            NumeroCuartos: $('#NumeroCuartos').val(),
            Precio: $('#Precio').val(),
            Descripcion : $('#Descripcion').val()
        }, function (responseText) {
            Swal.fire(
                'Bien!',
                'Agregado un hotel',
                'success'
                );
        });
    });

});


