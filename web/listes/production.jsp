<%@ page import="model.*,java.util.List" %>

<%
    List<Production> productions = (List<Production>) request.getAttribute("productions");
    List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
    List<Ingredient> ingredients = (List<Ingredient>) request.getAttribute("ingredients");
%>   
<h2>
    Liste des productions
    <button id="searchButton" class="searchButton">
        <svg xmlns="http://www.w3.org/2000/svg" height="25" viewBox="0 96 960 960" width="25" fill="#d2691e"><path d="M796 935 533 672q-30 26-69.959 40.5T378 727q-108.162 0-183.081-75Q120 577 120 471t75-181q75-75 181.5-75t181 75Q632 365 632 471.15 632 514 618 554q-14 40-42 75l264 262-44 44ZM377 667q81.25 0 138.125-57.5T572 471q0-81-56.875-138.5T377 275q-82.083 0-139.542 57.5Q180 390 180 471t57.458 138.5Q294.917 667 377 667Z"/></svg>
    </button>
</h2>

<div id="searchPopup" class="popup">
    <h3 class="popup-header">Recherche Multi-Criteres</h3>
    <form action="/Boulangerie/rechercheProduction" method="post">
        <div class="form-grid">           
            <div class="form-grp">
                <label for="categorie" class="form-label">Categorie :</label>
                <select name="categorie" id="categorie" class="form-input">
                    <option value="">categorie</option>
                    <%
                        for(int i=0; i<categories.size(); i++){
                            %>
                                <option value="<%= categories.get(i).getId() %>"><%= categories.get(i).getNom() %></option>
                            <%
                        }
                    %>
                </select>
            </div>

            <div class="form-grp">
                <label for="categorie" class="form-label">Ingredient :</label>
                <select name="ingredient" id="ingredient" class="form-input">
                    <option value="">ingredient</option>
                    <%
                        for(int i=0; i<ingredients.size(); i++){
                            %>
                                <option value="<%= ingredients.get(i).getId() %>"><%= ingredients.get(i).getNom() %></option>
                            <%
                        }
                    %>
                </select>
            </div>
            
        </div>
        <div class="form-actions">
            <button type="submit" class="form-button btn-primary">Rechercher</button>
            <button type="button" id="closeButton" class="form-button btn-secondary">Annuler</button>
        </div>
    </form>
</div>

<div id="overlay" class="overlay"></div>

<!-- Overlay -->
<div id="overlay" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 999;"></div>    

    
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
            for(int i=0; i<productions.size(); i++){
        %>
                <tr>
                    <td><a href="/Boulangerie/productionServlet?id=<%= productions.get(i).getId() %>"><%= productions.get(i).getId() %></a></td>
                    <td><%= productions.get(i).getDateProduction() %></td>
                    <td><%= productions.get(i).getDescription() %></td>
                </tr>
        <%
            }
        %>
    </tbody>
</table>


<script>
    document.addEventListener('DOMContentLoaded', function () {
        const searchButton = document.getElementById('searchButton');
        const searchPopup = document.getElementById('searchPopup');
        const overlay = document.getElementById('overlay');
        const closeButton = document.getElementById('closeButton');

        searchButton.addEventListener('click', function () {
            searchPopup.style.display = 'block';
            overlay.style.display = 'block';
        });

        closeButton.addEventListener('click', function () {
            searchPopup.style.display = 'none';
            overlay.style.display = 'none';
        });

        overlay.addEventListener('click', function () {
            searchPopup.style.display = 'none';
            overlay.style.display = 'none';
        });
    });
</script>



<style>
    .popup {
        display: none;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: white;
        width: 90%;
        padding: 20px;
        border-radius: 12px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        z-index: 1000;
    }

    .overlay {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 999;
    }

    .popup-header {
        color: #d2691e;
        margin-bottom: 20px;
        text-align: center;
        font-size: 1.5rem;
    }

    .form-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 20px;
    }

    .form-grp {
        display: flex;
        flex-direction: column;
    }

    .form-label {
        margin-bottom: 8px;
        font-weight: bold;
        color: #5a463a;
    }

    .form-input {
        width: 98%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 1rem;
    }

    .form-actions {
        text-align: center;
        margin-top: 20px;
    }

    .form-button {
        padding: 10px 20px;
        font-size: 1rem;
        border: none;
        border-radius: 8px;
        cursor: pointer;
    }

    .btn-primary {
        background-color: #d2691e;
        color: white;
    }

    .btn-secondary {
        background-color: #6c757d;
        color: white;
    }

    .searchButton{
        border-color: #d2691e;
        border-radius: 10px; 
        background: none;
        cursor: pointer;
        margin-left: 70%;
    }
</style>