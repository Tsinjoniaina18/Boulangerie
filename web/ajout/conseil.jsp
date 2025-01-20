<%@ page import="model.*,generator.*,java.util.*" %>

<h2>Conseil</h2>

<%
    List<Produit> produitList = (List<Produit>) request.getAttribute("produits");
    ArrayList<Produit> produits = new ArrayList<>(produitList);

    ArrayList<Mois> mois = (ArrayList<Mois>) request.getAttribute("mois");

    HashMap<String, ArrayList> map = new HashMap<String, ArrayList>();
    map.put("produit", produits);
    map.put("mois", mois);

    Conseil conseil = new Conseil();
    out.println(Generator.generateForm(conseil, map));
%>