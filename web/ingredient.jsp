<%@ page import="model.*,generator.*,java.util.*" %>

<h2>Ingredient</h2>

<%
    List<Unite> uniteList = (List<Unite>) request.getAttribute("unites");
    ArrayList<Unite> unites = new ArrayList<>(uniteList);
    
    HashMap<String, ArrayList> map = new HashMap<String, ArrayList>();
    map.put("unites", unites);

    Ingredient ingredient = new Ingredient();
    out.println(Generator.generateForm(ingredient, map));
%>