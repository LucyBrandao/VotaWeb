<%-- 
    Document   : admin
    Created on : 04/06/2019, 09:16:36
    Author     : Lucy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Admin" %>

<%!
    public boolean hasPermission(String role) {
        String sessionId = request.getSession().getId();
        Admin admin = Admin.findBySession(sessionID);
        if (admin == null) return false;
        return admin.hasPermission(role);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if (hasPermission("Mesário")) { %>
            <h1>Hello World!</h1>
        <% } %>
    </body>
</html>
