<%-- 
    Document   : register
    Created on : 27/04/2019, 13:50:22
    Author     : Fabian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
    </head>
    <body>
        <h1>Cadastrar</h1>
        <input id="username" type="text" name="username" placeholder="Título de Eleitor"><br>
        <input id="password" type="text" name="password" placeholder="Senha"><br>
        <input id="name" type="text" name="name" placeholder="Nome"><br>
        <input id="cpf" type="text" name="cpf" placeholder="CPF"><br>
        <input id="register" type="submit" name="Cadastrar">
        
        <script>
            $("#register").click(function() {
                var a = $("#username").val();
                var b = $("#password").val();
                var c = $("#name").val();
                var d = $("#cpf").val();
                $.ajax({
                    type: 'POST',
                    url: '/Vota/new',
                    data: {username: a, password: b, name: c, cpf: d},
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json'
                }).done(function(response) {
                    console.log(response);
                    window.location.href = "/Vota/";
                    
                }).fail(function() {
                    alert("fail");
                });
            });
        </script>
    </body>
</html>
