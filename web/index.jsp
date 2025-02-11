<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String content = request.getParameter("content");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Boulangerie Artisanale</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fdf3e7;
            margin: 0;
            padding: 0;
            color: #5a463a;
        }

        header {
            background-color: #d2691e;
            color: white;
            padding: 20px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            font-size: 2rem;
            margin: 0;
        }

        .sidebar {
            width: 220px;
            background-color: #d2691e;
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            padding-top: 20px;
            color: white;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }

        .menu {
            cursor: pointer;
            padding: 15px 20px;
            font-size: 1rem;
            color: white;
        }

        .menu::before {
            content: "• ";
            color: white;
        }

        .menu:hover {
            background-color: #a0522d;
        }

        .submenu {
            display: none;
            padding-left: 20px;
            font-size: 0.9rem;
        }

        .submenu a {
            display: block;
            padding: 10px 20px;
            color: white;
            text-decoration: none;
        }

        .submenu a:hover {
            background-color: #a0522d;
        }

        .container {
            margin: 20px auto;
            margin-left: 270px;
            max-width: 1000px;
            padding: 20px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #d2691e;
            border-bottom: 2px solid #d2691e;
            padding-bottom: 5px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .form-group input, .form-group select, .form-group textarea {
            width: 95%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 16px;
        }

        .form-group textarea {
            resize: none;
            height: 100px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 1rem;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        table th {
            background-color: #d2691e;
            color: white;
            font-weight: bold;
        }

        table tr:nth-child(even) {
            background-color: #fdf3e7;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 1rem;
            color: white;
            background-color: #8b4513;
            border: none;
            border-radius: 8px;
            text-decoration: none;
            text-align: center;
        }

        .btn:hover {
            background-color: #5a2d1c;
        }

        .welcome {
            margin-bottom: 20px;
            padding: 20px;
            background-color: #fff7e6;
            border: 1px solid #d2b48c;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        footer {
            text-align: center;
            margin-top: 20px;
            padding: 10px;
            background-color: #d2691e;
            color: white;
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const menus = document.querySelectorAll('.menu');

            menus.forEach(menu => {
                menu.addEventListener('click', function () {
                    const submenu = menu.nextElementSibling;
                    if (submenu.style.display === 'block') {
                        submenu.style.display = 'none';
                    } else {
                        submenu.style.display = 'block';
                    }
                });
            });
        });
    </script>
</head>
<body>

<header>
    <h1>Boulangerie Artisanale</h1>
</header>

<div class="sidebar">
    <div class="menu">Ajouts</div>
    <div class="submenu">
        <a href="/Boulangerie/prepaProduit">Produit</a>
        <a href="/Boulangerie/prepaIngredient">Ingredient</a>
        <a href="/Boulangerie/views/?content=ajout/unite.jsp">Unite</a>
        <a href="/Boulangerie/views/?content=ajout/categorie.jsp">Categorie</a>
        <a href="/Boulangerie/views/?content=ajout/client.jsp">Client</a>
        <a href="/Boulangerie/prepaConseil">Conseil</a>
        <a href="/Boulangerie/prepaChangementPrix">Changement Prix</a>
    </div>
    <div class="menu">Boulangeries</div>
    <div class="submenu">
        <a href="/Boulangerie/prepaAchat">Achat</a>
        <a href="/Boulangerie/prepaProduction">Production</a>
        <a href="/Boulangerie/prepaVente">Vente</a>
    </div>
    <div class="menu">Stocks</div>
    <div class="submenu">
        <a href="/Boulangerie/produitServlet">Produit</a>
        <a href="/Boulangerie/ingredientServlet">Ingredient</a>
    </div>
    <div class="menu">Listes</div>
    <div class="submenu">
        <a href="/Boulangerie/achatServlet">Achat</a>
        <a href="/Boulangerie/productionServlet">Production</a>
        <a href="/Boulangerie/venteServlet">Vente</a>
        <a href="/Boulangerie/conseilServlet">Conseil</a>
        <a href="/Boulangerie/clientServlet">Client</a>
        <a href="/Boulangerie/changementPrix">Changement Prix</a>
    </div>
</div>

<%
    if(request.getAttribute("error")!=null){
        %>
        <center>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        </center>
        <%
    }
%>

<div class="container">
<%
    if(content==null){
%>
        <div class="welcome">
            <h2>Bienvenue à la Boulangerie Artisanale</h2>
            <p>Découvrez nos délicieux pains, viennoiseries, et gâteaux faits maison. Commandez dès aujourd'hui pour savourer des produits frais et savoureux !</p>
        </div>
<%   
    } else {
        try {
%>
                
    <jsp:include page="<%= content%>" />

    <% } catch(Exception e) { %>
    <p>Erreur : <%= e.getMessage() %></p>
<%  
        } 
    }
%>
</div>

<!-- <footer>
    &copy; 2024 Boulangerie Artisanale. Tous droits réservés.
</footer> -->

</body>
</html>