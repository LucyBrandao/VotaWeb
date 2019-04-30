<%-- 
    Document   : login
    Created on : 21/04/2019, 17:19:00
    Author     : Fabian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
    </head>
    <body>
        <input id="username" type="text" name="username" placeholder="Título de Eleitor"/><br>
        <input id="password" type="password" name="password" placeholder="Senha"/><br>
        <input id="submit" type="submit" value="Autenticar"/><br>
        <button id="register" onclick="location.href='/Vota/register';">Cadastrar</button>
        
        <script>
            $("#submit").click(function() {
                var a = $("#username").val();
                var b = $("#password").val();
                console.log(a);
                console.log(b);
                $.ajax({
                    type: 'POST',
                    url: '/Vota/authenticate',
                    data: {username: a, password: b},
                    contentType: "application/json",
                    dataType: 'json'
                }).done(function(response) {
                    console.log(response);
                    window.location.href = response.url;
                }).fail(function() {
                    alert("fail");
                });
            });
        </script>
    </body>
</html>
