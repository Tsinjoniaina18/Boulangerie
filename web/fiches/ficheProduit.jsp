<%@ page import="model.*,model.fiche.*,java.util.List" %>

<%
    FicheProduit fiche = (FicheProduit) request.getAttribute("fiche");

    Produit produit = fiche.getProduit();
    Ingredient[] ingredients = fiche.getIngredients();
    int[] quantites = fiche.getQuantites();
%>

<div class="product-details">
    <h2>Details du Produit</h2>
    <div class="detail">
        <strong>ID :</strong> <%= produit.getId() %>
    </div>
    <div class="detail">
        <strong>Nom :</strong> <%= produit.getNom() %>
    </div>
    <div class="detail">
        <strong>Categorie :</strong> <%= produit.getIdCategorie() %>
    </div>
    <div class="detail">
        <strong>Description :</strong> <%= produit.getDescription() %>
    </div>
    <div class="detail">
        <strong>Prix :</strong> <%= produit.getPrix() %> Ar
    </div>
    <div class="detail">
        <strong>Ingredients :</strong>
        <p></p>
        <ul>
            <%
                if (ingredients.length>0) {
                    for (int i=0; i<ingredients.length; i++) {
            %>
                        <li><%= ingredients[i].getNom() %>: <%= quantites[i] %></li>
            <%
                    }
                } else {
            %>
                    <li>Aucun ingrédient spécifié</li>
            <%
                }
            %>
        </ul>
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