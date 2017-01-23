package web;

import dao.RepositoryCatalogue;
import dao.TypWybieguRepository;
import dao.WybiegRepository;
import dao.model.TypWybiegu;
import dao.model.Wybieg;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DodajWybiegServlet")
public class DodajWybiegServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String typWybieguString = (String) request.getParameter("typ_wybiegu");

        try {
            RepositoryCatalogue rpc = new RepositoryCatalogue();

            WybiegRepository twr = rpc.wybiegRepository();

            twr.add(new Wybieg(0, TypWybiegu.RodzajWybiegu.valueOf(typWybieguString).id, Wybieg.STAN_WYBIEGU.brudny));

            rpc.close();

        } catch (SQLException e) {

            response.sendRedirect("blad_dodawania_wybiegu.html");
            e.printStackTrace();

        } catch (Exception ex) {

            response.sendRedirect("blad_dodawania_wybiegu.html");
            
        }
        response.sendRedirect("index.html");

    }

}
