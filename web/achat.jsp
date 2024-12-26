<%@ page import="model.*,generator.*,java.util.List" %>

<h2>Achat d'ingredient</h2>

<%
    List<Ingredient> ingredients = (List<Ingredient>)request.getAttribute("ingredients");

    Achat achat = new Achat();

    out.println(Generator.generateFormHeader(achat));
    out.println(Generator.generateFormContent(achat, null));
%>

<h3>
    Ingredients
    <button id="addIngredient" type="button" class="btn-ing">+</button>
</h3>

<div class="form-ing">
    <div class="header-ing">
        <b>Nom</b>
        <b>Quantite</b>
        <b>Prix</b>
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
            <input type="number" name="prix-1">
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
            '<input type="number" name="quantite-' + ingredientCount + '"> ' +
            '<input type="number" name="prix-' + ingredientCount + '">';

        document.querySelector('.content-ing').appendChild(newIngredient);
    });

</script>

<style>
    .form-ing input, .form-ing select{
        width: 32%;
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