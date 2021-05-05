$(document).ready(() => {

    $.post('Buscador', {
        data: 'GetHotels'
    }, function (responseText) {
        $('#content').html(responseText);
    });

    $('#Buscar').click(() => {
        $.post('Buscador', {
            data: 'getQuery',
            query: $('#query').val()
        }, function (responseText) {
            $('#content').html(responseText);
        });
    });

});


