<%@ page import="model.*,java.util.List" %>

<%

    List<Produit> produits = (List<Produit>)request.getAttribute("produits");

%>

<h2>Stock des Produits</h2>
<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Description</th>
            <th>Categorie</th>
            <th>Prix</th>
            <th>Stock</th>
        </tr>
    </thead>
    <tbody>
        <%
            for(int i = 0; i<produits.size(); i++){
                %>
                <tr>
                    <td><%= produits.get(i).getId() %></td>
                    <td><%= produits.get(i).getNom() %></td>
                    <td><%= produits.get(i).getDescription() %></td>
                    <td><%= produits.get(i).getIdCategorie() %></td>
                    <td><%= produits.get(i).getPrix() %></td>
                    <td><%= produits.get(i).getStock() %></td>
                </tr>
                <%
            }
        %>  
    </tbody>
</table>