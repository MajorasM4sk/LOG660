import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.management.Query;
import java.util.List;

public class TestHibernate {
    public static void main(String argv[]) {
        Session sessionHome = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Hibernate Test
            transaction = sessionHome.beginTransaction();
            Query query = (Query) sessionHome.createQuery("FROM FILM");
            List results = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            sessionHome.close();
        }
    }
}
