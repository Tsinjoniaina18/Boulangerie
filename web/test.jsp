<%@ page import="model.*,generator.*,java.util.List" %>
<%
    List<Test> tests = (List<Test>) request.getAttribute("coco");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test</title>
</head>
<body>
    <h1>Test</h1>
    <form action="/PrepaS3S5/testServlet" method="post">
        <p>
            <b>Nom:</b>
            <input type="text" name="nom">
        </p>
        <select name="" id="">
            <% for(int i =0; i<tests.size(); i++){ %>
                <option value=""><%= tests.get(i).getNom() %></option>
            <% } %>
        </select>
        <p>
            <b>Prenom:</b>
            <input type="text" name="prenom">
        </p>
        <input type="submit" value="Valider">
    </form>
    <%
        Test test = new Test();
        out.println(Generator.generateForm(test, null));
    %>
</body>
</html>