<%-- 
    Document   : login
    Created on : 21/04/2019, 17:19:00
    Author     : Lucy
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="static/css/login.css">
    <!--link rel="stylesheet" type="text/css" href="static/css/errors.css"-->
    <!--script src="https://code.jquery.com/jquery-3.3.1.js"></script-->
    <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
</head>

<body>

    <!-- NAVBAR -->
    <div class="topnav" id="myTopnav">
        <a href="#home.html" style="letter-spacing: 2px; font-size: 1.3rem;">Vota Web</a>
    </div>
    <!-- /NAVBAR -->

    <!-- DARK PANEL -->
    <div class="dark-panel">
        <div class="eleitor-info-box">
            <h2 class="my-sub-title">Entrar como Eleitor</h2>
            <p>Entre como eleitor.</p>
            <label class="my-login" id="label-admin" for="log-admin-show">Login Eleitor</label>
            <input type="radio" name="active-log-panel" id="log-admin-show" checked="checked">
        </div>

        <div class="admin-info-box">
            <h2 class="my-sub-title">Entrar como Administrador</h2>
            <p>Entre como mesário ou chefe de seção.</p>
            <label class="my-login" id="label-eleitor" for="log-eleitor-show">Login Admin</label>
            <input type="radio" name="active-log-panel" id="log-eleitor-show">
        </div>

        <!-- LIGHT PANEL -->
        <div class="light-panel">
            <div class="eleitor-show">
                <h2>ELEITOR</h2>
                <input id="voter-username" type="text" placeholder="Username">
                <input id="voter-password" type="password" placeholder="Password">
                <input id="voter-submit" type="button" value="Entrar">
                <!--a class="createacc" href="create.html">Criar conta.</a-->
            </div>
            <div class="admin-show">
                <h2>ADMINISTRADOR</h2>
                <input id="admin-username" type="text" placeholder="Username">
                <input id="admin-password" type="password" placeholder="Password">
                <input id="admin-submit" type="button" value="Entrar">
                <!--a class="createacc" href="create.html">Criar conta.</a-->
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('.eleitor-info-box').fadeOut();
            $('.eleitor-show').addClass('show-log-panel');
        });
        $("#voter-submit").click(function() {
            var a = $("#voter-username").val();
            var b = $("#voter-password").val();
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
        
        $("#admin-submit").click(function() {
            var a = $("#admin-username").val();
            var b = $("#admin-password").val();
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


        $('.dark-panel input[type="radio"]').on('change', function () {
            if ($('#log-eleitor-show').is(':checked')) {
                $('.admin-info-box').fadeOut();
                $('.eleitor-info-box').fadeIn();

                $('.light-panel').addClass('right-log');
                $('.admin-show').addClass('show-log-panel');
                $('.eleitor-show').removeClass('show-log-panel');

            }
            else if ($('#log-admin-show').is(':checked')) {
                $('.admin-info-box').fadeIn();
                $('.eleitor-info-box').fadeOut();

                $('.light-panel').removeClass('right-log');

                $('.eleitor-show').addClass('show-log-panel');
                $('.admin-show').removeClass('show-log-panel');
            }
        });

    </script>
</body>

</html>

<!--%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <button id="register" onclick="location.href='/Vota/register'">Cadastrar</button>
        
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
</html-->
