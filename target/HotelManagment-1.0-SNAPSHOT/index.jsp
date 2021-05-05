

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chat</title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css"
              <!-- Latest compiled and minified JavaScript -->

        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


    </head>
    <body>

        <div class="row navbar-fixed-bottom">
            <div class="col-md-4 chat-box footer pull-right">
                <div class="panel panel-primary">
                    <div class="panel-heading">

                        <span class="glyphicon glyphicon-comment"></span> Chat
                        <div class="btn-group pull-right">
                            <button type="button" class="btn btn-default btn-xs dropdown toggle" data-toggle="dropdown">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </button>
                            <ul class="dropdown-menu slidedown">
                                <li><a href=""><span class="glyphicon glyphicon-off"></span>Salir</a></li>

                            </ul>
                        </div>

                    </div>

                    <div class="panel-body chat-widget">
                        <ul class="chat chat-messages">                




                        </ul>
                    </div>

                    <div class="panel-footer">
                        <div class="form-group">
                            <input type="text" class="form-control input-sm chat-name" placeholder="Escriba su nombre">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control input-sm chat-entry" placeholder="Escriba un mensaje y presione enter para enviar">
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <script src="https://js.pusher.com/3.2/pusher.min.js"></script>
        <script src="js/chat.js"></script>

        <script>

            Pusher.logToConsole = true;

            var pusher = new Pusher('cdd987e6993216811b25', {
                cluster: 'us2'
            });

            var channel = pusher.subscribe('my-channel');
            channel.bind('my-event', function (data) {
                alert(JSON.stringify(data));
            });
        </script>


    </body>
</html>
