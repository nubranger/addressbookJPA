package lt.bit.servlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data.Database;
import lt.bit.data.Person;

@WebServlet(name = "SaveServlet", urlPatterns = {"/save"})
public class SaveServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String idS = request.getParameter("personID");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String salaryS = request.getParameter("salary");
        String birthDateS = request.getParameter("bdate");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Person p = null;
        Integer id;
        Date birthDate = null;
        BigDecimal salary = null;

        if (idS != null) {
            try {
                id = new Integer(idS);
                p = Database.getPerson(em, id);

                if (p == null) {
                    response.sendRedirect("index.jsp");
                }
            } catch (Exception e) {

            }
        } else {
            p = new Person();
        }
        p.setFirstName(firstName);
        p.setLastName(lastName);

        try {
            if (birthDateS != null) {
                birthDate = sdf.parse(birthDateS);
                p.setBirthDate(birthDate);
            }
            if (salaryS != null) {
                salary = new BigDecimal(salaryS);
                p.setSalary(salary);
            }

        } catch (Exception e) {

        }

        if (idS == null) {
            Database.addPerson(em, p);
        } else {
            Database.updatePerson(em, p);
        }

        response.sendRedirect("index.jsp");

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
