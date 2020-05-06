<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.data.Contact"%>
<%@page import="lt.bit.data.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact list</title>
    </head>
    <body>

    <%
        String idS = request.getParameter("contactID");
        String personID = request.getParameter("personID");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Integer id = null;
        Contact c = null;

        try {
            id = new Integer(idS);
        } catch (Exception e) {

        }
        if (id != null) {
            c = Database.getContact(em,id);
        }
    %>
    <form method="get" action="saveContact">
        <%
            if (c != null) {
        %>
        <input type="hidden" value="<%=c.getId()%>" name="contactID">
        <%
            }
        %>
        <input type="hidden" value="<%=personID%>" name="personID">
        Contact: <input name="contact" value="<%=(c!=null)?c.getContact():""%>">
        Contact Type: <input name="type" value="<%=(c!=null)?c.getContactType():""%>">
        <input type="submit" value="Save">
    </form>

    <a href = "contacts.jsp?personID=<%=personID%>">Cancel</a>

    </body>
</html>
