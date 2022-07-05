import POJOs.Film;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
import java.util.List;

public class ServletDetailsFilm extends HttpServlet {
    // Initialisation du parent
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /*
    GET /film
    infos complètes d'un film
    query : {
        ?code_film=
    }
    response : {

    }
    */
    public void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        // Specifier le type et l’encodage des donnees
        reponse.setContentType("application/json");
        reponse.setCharacterEncoding("utf-8");

        // Creer un PrintWriter pour imprimer la page Web de la reponse
        OutputStreamWriter osw = new OutputStreamWriter(reponse.getOutputStream());
        PrintWriter out = new PrintWriter(osw);

        Session sessionHome = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        String codeFilm = "";
        Film film = null;
        try {
            codeFilm = requete.getParameter("code_film");
            transaction = sessionHome.beginTransaction();
            String SQL = "FROM Film where codeFilm = " + codeFilm ;

           // film = (Film) sessionHome.createQuery(SQL).getSingleResult();
            transaction.commit();

            // Utiliser out.println() pour retourner un résultat
           /* out.print("code_film:" + film.getCodeFilm(),
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
                    liens:['a', 'b', 'c'],);*/

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            sessionHome.close();
        }


        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Recuperer le parametre provenant de la page HTML d’entree
            codeFilm = requete.getParameter("code_film");
            if (codeFilm != null) {
                // Ouvrir une connexion
                String url = "jdbc:oracle:thin:EQUIPE112/C66VmkzD@log660ora12c.logti.etsmtl.ca:1521:LOG660";
                conn = DriverManager.getConnection(url);

                // Creer une requete au serveur BD
                ps = conn.prepareStatement("select ...");
                /*
                select code_film, titre, annee, langue, duree, resume_film, affiche from film where code_film = ?;
                select nom from personne, realisateur_film where personne.id_personne = realisateur_film.id_personne and code_film = ?;
                select nom from personne, role_film where personne.id_personne = role_film.id_personne and code_film = ?;
                select nom from pays, pays_film where pays.code_pays = pays_film.code_pays and code_film = ?;
                select nom from genre, genre_film where genre.no_genre = genre_film.no_genre and code_film = ?;
                select lien_bande_annonce from bande_annonce_film where code_film = ?;
                */
                ps.setString(1, codeFilm);

                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.next())
                    out.println(true);
                else
                    out.println(false);
            }
        } catch (Exception e) {
            // Debug: afficher la trace d’erreur directement dans la page
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            out.println(sw.toString());
        } finally {
            try {
                // Liberer les connections et resources
                out.close();
                ps.close();
                conn.close();
            } catch (Exception lException) {
                lException.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {

    }
}
