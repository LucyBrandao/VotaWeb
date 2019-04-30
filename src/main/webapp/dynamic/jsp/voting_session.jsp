<%-- 
    Document   : voting_session
    Created on : 30/04/2019, 04:59:45
    Author     : Fabian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sessão de Votação</h1>
        <input id="candidate" type="text" name="candidate" placeholder="nº do Candidato">
        <button id="confirm">Confirma</button>
        <button id="anule">Anula</button>
        <button id="blank">Branco</button><br>
        
        <div>
            <image src="static/img/generic_candidate.jpg" style="width: 30%"/>
        </div>
    </body>
</html>
