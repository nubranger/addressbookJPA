
<%@page import="lt.bit.data.Person"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.data.Address"%>
<%@page import="lt.bit.data.Database"%>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address list</title>
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
<h2> <%=p.getFirstName()%> <%=p.getLastName()%> address list </h2>
    <%

        for (Address a : Database.getPersonsAddresses(em, id)) {
    %>

    ID: <%=a.getId()%> Adresas: <%=a.getAddress()%> Miestas: <%=a.getCity()%> Pasto kodas: <%=a.getPostalCode()%>
    <a href="editAddress.jsp?addressID=<%=a.getId()%>&personID=<%=id%>">Edit</a>
    <a href="removeAddress?addressID=<%=a.getId()%>&personID=<%=id%>">Remove</a>
    <br>
    <hr>


    <%
        }
    %>
    <a href="editAddress.jsp?personID=<%=id%>">Add new address</a>
    <a href = "index.jsp">Back</a>

    </body>
</html>
