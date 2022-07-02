import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServletRecherche extends HttpServlet {
    // Initialisation du parent
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /*
    GET /films
    retournes la liste des films selon la recherche spécifiée. à voir si aucun paramètre de recherche retourne rien
    query : {
        ?titre=&anneemin=&anneemax=&pays=&langue=&genre=&realisateur=&acteur=
    }
    response : [
        {code_film:1, titre:'film1'},
        {code_film:2, titre:'film2'},
        {code_film:3, titre:'film3'},
        ...
    ]
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
