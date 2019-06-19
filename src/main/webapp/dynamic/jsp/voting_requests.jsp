<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="models.Voter" %>
<%@ page import="helpers.VotingRequestHelper" %>

<tr>
    <th>Nome</th>
    <th>Título de eleitor</th>
    <th>Habilitar</th>
</tr>
<% for (Voter voter : VotingRequestHelper.voters) { %>
    <tr>
        <td><%=voter.name%></td>
        <td><%=voter.username%></td>
        <td id="<%=voter.id%>" class="habilitate" onclick="habilitate(this)">
            <i class="fa fa-plus" aria-hidden="true"></i>
        </td>
    </tr>
<% } %>
