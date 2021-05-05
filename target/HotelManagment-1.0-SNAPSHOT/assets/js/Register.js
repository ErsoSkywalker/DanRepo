$(document).ready(() => {
    $('#Registrate').click(() => {
        $.post('Register', {
            user: $('#User').val(),
            pass: $('#contrasena').val(),
            name: $('#Nombre').val(),
            lastname: $('#Apellidos').val()
        }, function (responseText) {
            if(responseText > 0){
                window.location.href = 'Buscador.html';
            }else{
                Swal.fire(
                        'Error',
                        'Ya existe un usuario con ese username, escoge otro por favor',
                        'error'
                        );
            }
        });
    });
});


