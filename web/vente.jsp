<%@ page import="model.*,generator.*,java.util.List" %>

<h2>Vente</h2>

<%
    List<Produit> produits = (List<Produit>) request.getAttribute("produits");

    VenteProduit vente = new VenteProduit();
    out.println(Generator.generateFormHeader(vente));
    out.println(Generator.generateFormContent(vente, null));
%>

<h3>
    Produits
    <button id="addProduit" type="button" class="btn-prod">+</button>
</h3>

<div class="form-vnt">
    <div class="header-vnt">
        <b>Nom</b>
        <b>Quantite</b>
    </div>
    <p>
        <div class="content-vnt">
            <select name="produit-1">
                <%
                    for(int i=0; i<produits.size(); i++){
                %>
                    <option value="<%= produits.get(i).getId() %>"><%= produits.get(i).getNom() %></option>
                <%        
                    }
                %>
            </select>
            <input type="number" name="quantite-1">
        </div>
    </p>
</div>

<%
    out.println(Generator.generateFormFooter());
%>

<script>
    let produitCount = 1;

    var option = '';
    <%
        for(int i=0; i<produits.size(); i++){
    %>
            option += '<option value="<%= produits.get(i).getId() %>"><%= produits.get(i).getNom() %></option>';
    <%
        }
    %>
    console.log(option);

    document.getElementById('addProduit').addEventListener('click', function() {
        produitCount++;

        const newProduit = document.createElement('p');
        newProduit.innerHTML = 
            '<select name="produit-' + produitCount + '">' +
                option +
            '</select> ' +
            '<input type="number" name="quantite-' + produitCount + '"> ';

        document.querySelector('.content-vnt').appendChild(newProduit);
    });

</script>

<style>
    .form-vnt input, .form-vnt select{
        width: 49%;
        padding: 6px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 12px;
    }
    .btn-prod{
        border: none;
        border-radius: 5px;
        color: white;
        background-color: #8b4513;
    }
    .header-vnt{
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0px 20px;
    }
</style>