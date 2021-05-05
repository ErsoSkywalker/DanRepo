$(document).ready(function () {

    $('#entrar').on('click', () => {

        //alert($('#User').val());

        $.post('Login', {
            user: $('#User').val(),
            pass: $('#Contrasena').val()
        }, function (responseText) {
            if (responseText > 0) {
                window.location.href = 'Buscador.html';
            } else {
                Swal.fire(
                        'Error',
                        'Esas credenciales no son validas.',
                        'error'
                        );
                $('#User').val('');
                $('#Contrasena').val('');
            }
        });
    });

});


