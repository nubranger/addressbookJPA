package lt.bit.servlet;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data.Database;
import lt.bit.data.Contact;

@WebServlet(name = "SaveContactServlet", urlPatterns = {"/saveContact"})
public class SaveContactServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idS = request.getParameter("personID");
        String idContactS = request.getParameter("contactID");
        String type = request.getParameter("type");
        String contact = request.getParameter("contact");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Contact c = null;
        Integer id = null;
        Integer idContact = null;

        try {
            id = new Integer(idS);
        } catch (Exception e) {

        }

        if (idContactS != null) {
            try {
                idContact = new Integer(idContactS);
                c = Database.getContact(em, idContact);

                if (c == null) {
                    response.sendRedirect("contacts.jsp?personID=" + id);
                }
            } catch (Exception e) {

            }
        } else {
            c = new Contact();
        }

        c.setPerson(Database.getPerson(em, id));
        c.setContactType(type);
        c.setContact(contact);

        if (idContactS == null) {
            Database.addContact(em, id, c);
        } else {
            Database.updateContact(em, c);
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
