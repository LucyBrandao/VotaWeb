<%-- 
    Document   : voting_requests
    Created on : 29/04/2019, 08:42:39
    Author     : Fabian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="models.Voter" %>

<table>
    <% for (Voter voter : Voter.voters) { %>
        <tr>
            <td><%=voter.getName()%></td>
            <td><%=voter.getUsername()%></td>
            <td><%=voter.getCpf()%></td>
        </tr>
    <% } %>
</table>