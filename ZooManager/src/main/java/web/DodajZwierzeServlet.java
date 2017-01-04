package web;

import dao.RepositoryCatalogue;
import dao.ZwierzetaRepository;
import dao.model.Zwierze;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DodajZwierzeServlet")
public class DodajZwierzeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        List<Zwierze> list = pobierzZwierzaki();
        session.setAttribute("ListaZwierzat", list);
        response.sendRedirect("zwierzaki.jsp");
        
    }

    
    
    public List<Zwierze> pobierzZwierzaki() {
        System.out.println("com.mycompany.zoomanager_projekt_pjwstk.Main.pobierzZwierzaka()");
        List<Zwierze> listaZierzat = new ArrayList<>();
        RepositoryCatalogue repos;
        try {
            repos = new RepositoryCatalogue();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        ZwierzetaRepository zr = repos.zwierzetaRepository();
        repos.save();
        listaZierzat = zr.getAll();

        repos.close();

        return listaZierzat;
    }

}
