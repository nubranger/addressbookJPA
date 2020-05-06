<%@page import="lt.bit.data.Database" %>
<%@page import="javax.persistence.EntityManager" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="lt.bit.data.Person" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Address book</title>
</head>
<body>

<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    EntityManager em = (EntityManager) request.getAttribute("em");
%>
<h2>Person list</h2>
<%
    for (Person p : Database.getPersons(em)) {
%>

ID: <%=p.getId()%>
Vardas: <%=p.getFirstName()%>
Pavarde: <%=p.getLastName()%>
Gimimo data: <%=(p.getBirthDate() != null) ? sdf.format(p.getBirthDate()) : ""%>
Alga: <%=p.getSalary()%>
<a href="edit.jsp?personID=<%=p.getId()%>">Edit</a>
<a href="remove?personID=<%=p.getId()%>">Remove</a> <br>
<a href="addresses.jsp?personID=<%=p.getId()%>">Addresses</a>
<a href="contacts.jsp?personID=<%=p.getId()%>">Contacts</a>
<hr>
<%
    }
%>
<a href="edit.jsp">New person</a>
</body>
</html>
