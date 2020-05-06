<%@page import="lt.bit.data.Person"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.data.Database"%>
<%@ page import="lt.bit.data.Contact" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contact list</title>
</head>
<body>

<%
    EntityManager em = (EntityManager) request.getAttribute("em");

    String ids = request.getParameter("personID");
    Integer id = null;
    try {
        id = new Integer(ids);
    } catch (Exception ex) {
        //ignoras
    }
    Person p = Database.getPerson(em, id);
%>
<h2> <%=p.getFirstName()%> <%=p.getLastName()%> contact list </h2>
<%

    for (Contact c : Database.getPersonsContacts(em, id)) {
%>

ID: <%=c.getId()%> Contact: <%=c.getContact()%> Contact type: <%=c.getContactType()%>
<a href="editContact.jsp?contactID=<%=c.getId()%>&personID=<%=id%>">Edit</a>
<a href="removeContact?contactID=<%=c.getId()%>&personID=<%=id%>">Remove</a>
<br>
<hr>


<%
    }
%>
<a href="editContact.jsp?personID=<%=id%>">Add new contact</a>
<a href = "index.jsp">Back</a>

</body>

</html>
