<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %><%--
  Created by IntelliJ IDEA.
  User: Tsinjoniaina
  Date: 1/28/2025
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Produit> produits = (List<Produit>) request.getAttribute("produits");
%>

<h2>Changement de Prix</h2>

<form action="changementPrix" method="post">
    <div class="form-group">
        <label for="produit">Produit</label>
        <select name="produit" id="produit">
            <%
                for (int i = 0; i < produits.size(); i++) {
                    %>
                        <option value="<%= produits.get(i).getId() %>"><%= produits.get(i).getNom() %></option>
                    <%
                }
            %>
        </select>
    </div>
    <div class="form-group">
        <label for="date">Date</label>
        <input type="date" name="date">
    </div>
    <div class="form-group">
        <label for="prix">Prix</label>
        <input type="number" name="prix">
    </div>
    <div class="form-group">
        <button type="submit">Valider</button>
    </div>
</form>
