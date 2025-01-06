<%@ page import="model.*,model.fiche.*,java.util.List" %>

<%
    FicheProduction fiche = (FicheProduction) request.getAttribute("fiche");

    Production production = fiche.getProduction();
    List<ProductionFille> productionFilles = fiche.getProductionFilles();
    List<String> categories = fiche.getCategories();
%>

<div class="product-details">
    <h2>Details Production</h2>
    <div class="detail">
        <strong>ID :</strong> <%= production.getId() %>
    </div>
    <div class="detail">
        <strong>Date d'achat :</strong> <%= production.getDateProduction() %>
    </div>
    <div class="detail">
        <strong>Description :</strong> <%= production.getDescription() %>
    </div>
    <div class="detail">
        <strong>Details :</strong>
        <p></p>
        <table>
            <thead>
                <tr>
                    <th>Produit</th>
                    <th>Categorie</th>
                    <th>Quantite</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (productionFilles.size()>0) {
                        for (int i=0; i<productionFilles.size(); i++) {
                %>
                            <tr>
                                <td><%= productionFilles.get(i).getIdProduit() %></td>
                                <td><%= categories.get(i) %></td>
                                <td><%= productionFilles.get(i).getQuantite() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <li>Aucune productions spécifié</li>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

<style>
    .product-details {
        background-color: #fff7e6;
        border: 1px solid #d2b48c;
        border-radius: 12px;
        padding: 20px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
    }

    .product-details h2 {
        color: #d2691e;
        margin-bottom: 20px;
        border-bottom: 2px solid #d2691e;
        padding-bottom: 5px;
    }

    .product-details .detail {
        margin-top: 10px;
        font-size: 1rem;
        margin-bottom: 15px;
        color: #5a463a;
    }

    .product-details .detail strong {
        font-weight: bold;
        color: #d2691e;
    }

    .product-details ul {
        margin: 0;
        padding-left: 20px;
        list-style-type: disc;
    }

    .product-details ul li {
        margin-bottom: 5px;
    }

</style>