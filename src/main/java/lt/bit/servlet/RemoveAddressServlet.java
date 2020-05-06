package lt.bit.servlet;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data.Database;


@WebServlet(name = "RemoveAddressServlet", urlPatterns = {"/removeAddress"})
public class RemoveAddressServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idS = request.getParameter("personID");
        String idAddressS = request.getParameter("addressID");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Integer id = null;
        Integer idAddress;

        try {
            id = new Integer(idS);
            idAddress = new Integer(idAddressS);
            Database.removeAddress(em, idAddress);
        } catch (Exception e) {

        }

        response.sendRedirect("addresses.jsp?personID=" + id);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
