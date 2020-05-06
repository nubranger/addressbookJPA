package lt.bit.servlet;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data.Address;
import lt.bit.data.Database;

@WebServlet(name = "SaveAddressServlet", urlPatterns = {"/saveAddress"})
public class SaveAddressServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idS = request.getParameter("personID");
        String idAddressS = request.getParameter("addressID");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Address a = null;
        Integer id = null;
        Integer idAddress = null;

        try {
            id = new Integer(idS);
        } catch (Exception e) {

        }
        if (idAddressS != null) {
            try {
                idAddress = new Integer(idAddressS);
                a = Database.getAddress(em, idAddress);
                if (a == null) {
                    response.sendRedirect("addresses.jsp?personID=" + id);
                }
            } catch (Exception e) {

            }
        } else {
            a = new Address();
        }
        a.setPerson(Database.getPerson(em, id));
        a.setAddress(address);
        a.setCity(city);
        a.setPostalCode(postalCode);

        if (idAddressS == null) {
            Database.addAddress(em, id, a);
        } else {
            Database.updateAddress(em, a);
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
