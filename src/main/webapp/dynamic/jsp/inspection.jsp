<%-- 
    Document   : inspection
    Created on : 29/04/2019, 06:55:09
    Author     : Lucy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="static/css/table.css">
        <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
        <script type="text/javascript">
            /*var auto_refresh = setInterval(
            function ()
            {
                $('#voting_requests').load('/Vota/voting_requests').fadeIn("slow");
            }, 1000);*/
            
            var auto_ref = setInterval(() => {
                $.get('/Vota/voting_requests', (result) => {
                    $('#voting_requests').html(result);
                }).fail(() => {
                    clearInterval(auto_ref);
                });
            }, 1000);
            
            function habilitate(tag) {
                console.log(tag.id);
                $.ajax({
                    type: 'POST',
                    url: '/Vota/enable_voting',
                    data: {voter_id: tag.id},
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json'
                }).done(function(response) {
                    console.log(response);
                }).fail(function() {
                    alert("fail");
                });
            }
            
            /*$(document).ready(function () {
                $(".habilitate").click(function() {
                    $.ajax({
                        type: 'POST',
                        url: '/Vota/new',
                        data: {username: a, password: b, name: c, cpf: d},
                        contentType: "application/json; charset=utf-8",
                        dataType: 'json'
                    }).done(function(response) {
                        console.log(response);
                        if (response.status) {
                            window.location.href = "/Vota/";
                        } else {
                            response.errors.forEach((error) => {
                                str = "#" + error.field.toLowerCase();
                                console.log(str);
                                $(str).addClass("error");
                            });
                        }

                    }).fail(function() {
                        alert("fail");
                    });
                });
            });*/
        </script>
    </head>
    <body>
        <h1>Hello World!</h1><br>
        
        <table id="voting_requests">
            <tr>
                <th>Nome</th>
                <th>Título de eleitor</th>
                <th>Habilitar</th>
            </tr>
        </table>
        
    </body>
</html>
