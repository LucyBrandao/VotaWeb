<%-- 
    Document   : newjsp
    Created on : 20/04/2019, 21:06:03
    Author     : Lucy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Voter" %>
<%@page import="models.Election" %>



<%! 
    public Voter voter;
%>

<% voter = Voter.class.cast(session.getAttribute("voter")); %>


<%!    
    public boolean everTrue() {
        return voter.username.equals("123456123456");
    }
%>

<!DOCTYPE html>
<html>

<head>
    <title>Vota Web</title>
    <!--link rel="icon" href="img/vw-logo.png"-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- CSS LINK -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/home.css">
    
    <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
</head>

<body>
    <!-- NAVBAR -->
    <div class="topnav" id="myTopnav">
        <a href="#home.html" style="letter-spacing: 2px; font-size: 1.3rem;">Vota Web</a>
    </div>
    <!-- /NAVBAR -->

    <!-- CARDS -->
    <div class="row" <!--onclick="window.location='ballot_box'"-->>
    <% if (voter.canVote() && Election.isElectionPeriod()) { %>
        <div class="column">
            <div class="card">
                <a class="card-home" href="ballot_box">
                    <h3>Vote!</h3>
                </a>
            </div>
        </div>
    <% } %>

    <% if (voter.canHabilitateVote()) { %>
        <div class="column">
            <div class="card">
                <a class="card-home" href="inspection">
                    <h3>Habilitações</h3>
                </a>
            </div>
        </div>
    <% } %>

    <% if (voter.canPrintReport()) { %>
        <div class="column">
            <div class="card">
                <a class="card-home" href="bulletin">
                    <h3>Relatório de votação</h3>
                </a>
            </div>
        </div>
    <% } %>
    
        <div class="column">
            <div class="card">
                <a class="card-home" href="result">
                    <h3>Votos</h3>
                </a>
            </div>
        </div>
    </div>

    <!--script>
        function myFunction() {
            var x = document.getElementById("myTopnav");
            if (x.className === "topnav") {
                x.className += " responsive";
            } else {
                x.className = "topnav";
            }
        }
    </script-->

</body>

</html>


<!--DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
    </head>
    <body>
        <h1>Orientações</h1>
        <input type="button" onclick="location.href='/Vota/voting_session';" value="Iniciar Sessão" /><br>
        <button id="start-section">Iniciar Sessão</button>
        <% if (!everTrue()) { %>
            <p>
                Bem vindo, caro eleitor, siga abaixo as orientações para a votação, 
                quando estiver pronto basta clicar em iniciar sessão e aguardar que 
                o mesário o libere para votação.
            </p>
        <% } %>
        <p>
            TODO Orientações
        </p>
    </body>
</html-->
        