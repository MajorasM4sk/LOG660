import POJOs.Film;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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

        Session sessionHome = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Film> list = null;
        try {
            transaction = sessionHome.beginTransaction();
            String SQL = "FROM Film AS F" +
                    "where ("+ request.getParameter("titre") +" is null or F.titre like '%"+ request.getParameter("titre") +"%')"+
                    "and ("+ request.getParameter("anneemin") +" is null or F.annee >= "+ request.getParameter("anneemin") +")"+
                    "and ("+ request.getParameter("anneemax") +" is null or F.annee <= "+ request.getParameter("anneemax") +")"+
                    "and ("+ request.getParameter("langue") +" is null or F.langue = "+ request.getParameter("langue") +")"; /*+
            "and (Pays.codePays = PaysFilm.codePays)"+
            "and (PaysFilm.codeFilm = F.codeFilm)"; /*+
            /*"and (Genre.noGenre = GenreFilm.noGenre)"+
            "and (GenreFilm.codeFilm = F.codeFilm)"+
            "and (F.codeFilm = RealisateurFilm.codeFilm)"+
            "and (RealisateurFilm.idPersonne = Personne.idPersonne)"+*/
            ; /*"and ("+ request.getParameter("ppays") +" is null or Pays.nom = "+ request.getParameter("ppays") +")"+
                    "and ("+ request.getParameter("pgenre") +" is null or Genre.nom = "+ request.getParameter("pgenre") +")"+
                    "and ("+ request.getParameter("prealisateur") +" is null or "+ request.getParameter("prealisateur") +" = Personne.nom)"; */

            list = (List<Film>) sessionHome.createQuery(SQL);
            transaction.commit();

            // Utiliser out.println() pour retourner un résultat
            out.print("[");
            for(int i = 0; i<= list.size(); i++){
                System.out.print( "{code_film:" + list.get(i).getCodeFilm() + ", titre: "+ list.get(i).getTitre() + "}, ");
            }
            out.print("]");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            sessionHome.close();
        }

    }

    public void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {

    }
}
