<%@ page import="model.*,generator.*,java.util.*" %>

<h2>Categorie</h2>

<%
    Categorie categorie = new Categorie();
    out.println(Generator.generateForm(categorie, null));
%>