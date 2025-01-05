<%@ page import="model.*,java.util.List" %>

<%
    List<Achat> achats = (List<Achat>) request.getAttribute("achats");
%>

<h2>Liste des achats</h2>
<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Date achat</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <%
            for(int i=0; i<achats.size(); i++){
        %>
                <tr>
                    <td><a href="/Boulangerie/achatServlet?id=<%= achats.get(i).getId() %>"><%= achats.get(i).getId() %></a></td>
                    <td><%= achats.get(i).getDateAchat() %></td>
                    <td><%= achats.get(i).getDescription() %></td>
                </tr>
        <%
            }
        %>
    </tbody>
</table>