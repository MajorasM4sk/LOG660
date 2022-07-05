import POJOs.Film;
import POJOs.TClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;

import static java.lang.System.out;

public class TestHibernate {
    public static void main(String argv[]) {
        Session sessionHome = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        TClient client = null;
        String ptitre = "The";
        String panneemin = "1996";
        String panneemax = "1996";
        String ppays = "USA";
        String plangue = "English";
        String pgenre = "Romance";
        String prealisateur = "Jon Turteltaub";

        List list = null;
        try {
            transaction = sessionHome.beginTransaction();
            String SQL = "FROM Film AS F " +
                        "  where ('"+ ptitre +"' is null or titre like '%"+ ptitre +"%') "+
                    "and ("+ panneemin +" is null or annee >= "+ panneemin +") "+
                    "and ("+ panneemax +" is null or annee <= "+ panneemax +") "+
                    "and ('"+ plangue +"' is null or F.langue = '"+ plangue +"') "; /*
                        "and (Genre.noGenre = GenreFilm.noGenre)"+
                        "and (GenreFilm.codeFilm = F.codeFilm)"+
                        "and (Pays.codePays = PaysFilm.codePays)"+
                        "and (PaysFilm.codeFilm = F.codeFilm)"+
                        "and (F.codeFilm = RealisateurFilm.codeFilm) "+
                        "and (RealisateurFilm.idPersonne = Personne.idPersonne) " +
                        "and ('" + ppays + "' is null or Pays.nom = '"+ ppays +"') "+
                        "and ('"+ pgenre +"' is null or Genre.nom = '"+ pgenre +"') "+
                        "and ( '" + prealisateur + "' is null or '" + prealisateur + "' = Personne.nom )";*/


            list = (List<Film>) sessionHome.createQuery(SQL);
            transaction.commit();
            out.println(list.size());
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            sessionHome.close();
        }
    }
}
