package lt.bit.servlet;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data.Database;


@WebServlet(name = "RemoveContactServlet", urlPatterns = {"/removeContact"})
public class RemoveContactServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idS = request.getParameter("personID");
        String idContactS = request.getParameter("contactID");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Integer id = null;
        Integer idContact;

        try {
            id = new Integer(idS);
            idContact = new Integer(idContactS);
            Database.removeContact(em, idContact);
        } catch (Exception e) {

        }

        response.sendRedirect("contacts.jsp?personID=" + id);
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
