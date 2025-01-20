<%@ page import="model.*,generator.*,java.util.*" %>

<h2>Client</h2>

<%
    Client client = new Client();
    out.println(Generator.generateForm(client, null));
%>