<%@ page import="model.*,generator.*,java.util.*" %>
<h2>Rechercher Client</h2>

<form action="clientServlet" method="post">
<div class="form-group">
    <label for="date" class="form-label">date :</label>
    <input type="date" id="date" name="date" class="form-input">

</div>
<div class="form-actions">
    <button type="submit" class="btn">Rechercher</button>
</div>
</form>

<%

    if(request.getAttribute("clients")!=null){
        %>
            <br>
            <br>
            <br>
            <center><h3>Liste des clients</h3></center>
        <%

        ArrayList<String> clients = (ArrayList<String>) request.getAttribute("clients");
        String date = (String)request.getAttribute("date");

        %>
        <table>
            <thead>
                <tr>
                    <th>Client</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for(int i=0;i<clients.size();i++){ %>
                        <tr>
                            <td><%= clients.get(i) %></td>
                            <td><%= date %></td>
                        </tr>
                    <% }
                %>
            </tbody>
        </table>
        <%
    }
%>