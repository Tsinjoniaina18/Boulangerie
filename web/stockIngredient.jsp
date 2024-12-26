<%@ page import="model.*,java.util.List" %>

<%

    List<Ingredient> ingredients = (List<Ingredient>)request.getAttribute("ingredients");

%>

<h2>Stock des Ingredients</h2>
<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Description</th>
            <th>Prix</th>
            <th>Stock</th>
        </tr>
    </thead>
    <tbody>
        <%
            for(int i = 0; i<ingredients.size(); i++){
                %>
                <tr>
                    <td><%= ingredients.get(i).getId() %></td>
                    <td><%= ingredients.get(i).getNom() %></td>
                    <td><%= ingredients.get(i).getDescription() %></td>
                    <td><%= ingredients.get(i).getPrix() %></td>
                    <td><%= ingredients.get(i).getStock() %></td>
                </tr>
                <%
            }
        %>  
    </tbody>
</table>