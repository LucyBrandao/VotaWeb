<%-- 
    Document   : inspection
    Created on : 29/04/2019, 06:55:09
    Author     : Fabian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
        <script type="text/javascript">
            var auto_refresh = setInterval(
            function ()
            {
                $('#voting_requests').load('/Vota/voting_requests').fadeIn("slow");
            }, 10000); 
        </script>
    </head>
    <body>
        <h1>Hello World!</h1><br>
        
        <div id="voting_requests"></div>
        
    </body>
</html>
