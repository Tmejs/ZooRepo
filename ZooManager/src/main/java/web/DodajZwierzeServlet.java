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

@WebServlet("/DodajZwierzeServlet")
public class DodajZwierzeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nazwa = (String) request.getParameter("nazwa");

        Integer tempoJedzenia = Integer.valueOf((String) request.getParameter("tempoJedzenia"));

        Zwierze newZwierze = new Zwierze(Integer.SIZE, nazwa, tempoJedzenia, 100, null, null, 1);

        try {
            
            RepositoryCatalogue rp = new RepositoryCatalogue();
            ZwierzetaRepository zwr = rp.zwierzetaRepository();
            zwr.add(newZwierze);
            rp.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("blad_dodawania_zwierzaka.html");
        } catch(Exception ex){
            ex.printStackTrace();
            response.sendRedirect("blad_dodawania_zwierzaka.html");
        }

        response.sendRedirect("index.html");
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
