<%@ page import="model.*,java.util.List" %>

<%
    List<VenteProduit> ventes = (List<VenteProduit>) request.getAttribute("ventes");
%>

<h2>Liste des ventes</h2>
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
            for(int i=0; i<ventes.size(); i++){
        %>
                <tr>
                    <td><a href="/Boulangerie/venteServlet?id=<%= ventes.get(i).getId() %>"><%= ventes.get(i).getId() %></a></td>
                    <td><%= ventes.get(i).getDateVente() %></td>
                    <td><%= ventes.get(i).getDescription() %></td>
                </tr>
        <%
            }
        %>
    </tbody>
</table>