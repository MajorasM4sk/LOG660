import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ServletDetailsFilm extends HttpServlet {
    // Initialisation du parent
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /*
    GET /films/:code_film
    infos complètes d'un film
    response : {
        code_film:1,
        titre:'film1',
        annee:1900,
        pays:['Canada', 'États-Unis'],
        langue:'C#',
        duree:123,
        genres:['Peur', 'Action'],
        realisateur:'Real Lee Sator',
        acteurs:{'Aku Thor':'Perce Honnage', 'Hak Theur':'Perseaux Nages'},
        resume_film:'ouaip',
        affiche:'https://via.placeholder.com/150',
        liens:['a', 'b', 'c'],
    }
    */
    public void doGet(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
        // Specifier le type et l’encodage des donnees
        reponse.setContentType("application/json");
        reponse.setCharacterEncoding("utf-8");

        // Creer un PrintWriter pour imprimer la page Web de la reponse
        OutputStreamWriter osw = new OutputStreamWriter(reponse.getOutputStream());
        PrintWriter out = new PrintWriter(osw);

        // Utiliser out.println() pour retourner un résultat
    }

    public void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {

    }
}
