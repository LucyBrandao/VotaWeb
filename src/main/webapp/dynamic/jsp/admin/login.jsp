<%-- 
    Document   : admin-login
    Created on : 04/06/2019, 09:27:10
    Author     : Lucy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Página de Administrador</h1>
        <input id="username" type="text" name="username" placeholder="Título de Eleitor"/><br>
        <input id="password" type="password" name="password" placeholder="Senha"/><br>
        <input id="submit" type="submit" value="Autenticar"/><br>
        <button id="register" onclick="location.href='/Vota/admin/register'">Cadastrar</button>
        
        <script>
            $("#submit").click(function() {
                var a = $("#username").val();
                var b = $("#password").val();
                console.log(a);
                console.log(b);
                $.ajax({
                    type: 'POST',
                    url: '/Vota/admin/authenticate',
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
