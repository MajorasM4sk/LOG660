import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ServletReservationFilm extends HttpServlet {
    // Initialisation du parent
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

    }

    /*
    POST/films/:code_film/reserver
    ajoute une location de film pour un utilisateur. retourne vrai si la location a eu lieu, faux sinon
    body : {
        client: email du client
    }
    response : 'true' | 'forfait' | 'false'
    */
    public void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        // Specifier le type et l’encodage des donnees
        reponse.setContentType("application/json");
        reponse.setCharacterEncoding("utf-8");

        // Creer un PrintWriter pour imprimer la page Web de la reponse
        OutputStreamWriter osw = new OutputStreamWriter(reponse.getOutputStream());
        PrintWriter out = new PrintWriter(osw);

        // Utiliser out.println() pour retourner un résultat
    }
}
