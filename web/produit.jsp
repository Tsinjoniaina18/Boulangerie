<%@ page import="model.*,generator.*,java.util.*" %>

<h2>Produit</h2>

<%
    List<Categorie> categorieList = (List<Categorie>) request.getAttribute("categories");
    ArrayList<Categorie> categories = new ArrayList<>(categorieList);

    List<Ingredient> ingredients = (List<Ingredient>) request.getAttribute("ingredients");

    HashMap<String, ArrayList> map = new HashMap<String, ArrayList>();
    map.put("categories", categories);

    Produit produit = new Produit();
    out.println(Generator.generateFormHeader(produit));
    out.println(Generator.generateFormContent(produit, map));
%>
<div class="form-group">
    <label for="naturel">Naturel:</label>
    <select name="naturel" id="naturel">
        <option value="1">Naturel</option>
        <option value="0">composer</option>
    </select>
</div>
<h3>
    Recette
    <button id="addIngredient" type="button" class="btn-ing">+</button>
</h3>

<center><p><b>Ingredients</b></p></center>

<div class="form-ing">
    <div class="header-ing">
        <b>Nom</b>
        <b>Quantite</b>
    </div>
    <p>
        <div class="content-ing">
            <select name="ingredient-1">
                <%
                    for(int i=0; i<ingredients.size(); i++){
                %>
                    <option value="<%= ingredients.get(i).getId() %>"><%= ingredients.get(i).getNom() %></option>
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
    let ingredientCount = 1;

    var option = '';
    <%
        for(int i=0; i<ingredients.size(); i++){
    %>
            option += '<option value="<%= ingredients.get(i).getId() %>"><%= ingredients.get(i).getNom() %></option>';
    <%
        }
    %>
    console.log(option);

    document.getElementById('addIngredient').addEventListener('click', function() {
        ingredientCount++;

        const newIngredient = document.createElement('p');
        newIngredient.innerHTML = 
            '<select name="ingredient-' + ingredientCount + '">' +
                option +
            '</select> ' +
            '<input type="number" name="quantite-' + ingredientCount + '"> ';

        document.querySelector('.content-ing').appendChild(newIngredient);
    });

</script>

<style>
    .form-ing input, .form-ing select{
        width: 49%;
        padding: 6px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 12px;
    }
    .btn-ing{
        border: none;
        border-radius: 5px;
        color: white;
        background-color: #8b4513;
    }
    .header-ing{
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0px 20px;
    }
</style>