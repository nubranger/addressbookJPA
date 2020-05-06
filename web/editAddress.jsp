
<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.data.Address"%>
<%@page import="lt.bit.data.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <%
        String idS = request.getParameter("addressID");
        String personID = request.getParameter("personID");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Integer id = null;
        Address a = null;

        try {
            id = new Integer(idS);
        } catch (Exception e) {

        }
        if (id != null) {
            a = Database.getAddress(em,id);
        }
    %>
    <form method="get" action="saveAddress">
        <%
            if (a != null) {
        %>
        <input type="hidden" value="<%=a.getId()%>" name="addressID">
        <%
            }
        %>
        <input type="hidden" value="<%=personID%>" name="personID">
        Address: <input name="address" value="<%=(a!=null)?a.getAddress():""%>">
        City: <input name="city" value="<%=(a!=null)?a.getCity():""%>">
        Postal code: <input name="postalCode" value="<%=(a!=null)?a.getPostalCode():""%>">
        <input type="submit" value="Save">
    </form>

    <a href = "addresses.jsp?personID=<%=personID%>">Cancel</a>

    </body>
</html>
