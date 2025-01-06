<%@ page import="model.*,java.util.List" %>

<%
    List<Production> productions = (List<Production>) request.getAttribute("productions");
%>

<h2>Liste des productions</h2>
<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Date vente</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <%
            for(int i=0; i<productions.size(); i++){
        %>
                <tr>
                    <td><a href="/Boulangerie/productionServlet?id=<%= productions.get(i).getId() %>"><%= productions.get(i).getId() %></a></td>
                    <td><%= productions.get(i).getDateProduction() %></td>
                    <td><%= productions.get(i).getDescription() %></td>
                </tr>
        <%
            }
        %>
    </tbody>
</table>