<%@ page import="model.*,generator.*,java.util.*" %>

<h2>Unite</h2>

<%
    Unite unite = new Unite();
    out.println(Generator.generateForm(unite, null));
%>