<%-- 
    Document   : register
    Created on : 27/04/2019, 13:50:22
    Author     : Lucy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
        <link rel="stylesheet" type="text/css" href="static/css/errors.css">
    </head>
    <body>
        <h1>Cadastrar</h1>
        <input id="username" type="text" name="username" placeholder="Título de Eleitor" class="username"><br>
        <input id="password" type="text" name="password" placeholder="Senha" class="password"><br>
        <input id="name" type="text" name="name" placeholder="Nome" class="name"><br>
        <input id="cpf" type="text" name="cpf" placeholder="CPF" class="cpf"><br>
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
        </script>
    </body>
</html>
