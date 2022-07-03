import org.junit.Test;

import static org.junit.Assert.*;

public class HibernateUtilTest {

    @Test
    public void getSessionFactory() {
        HibernateUtil hiberUtil = new HibernateUtil();
        boolean sessionFactoryCreated = false;

        if (hiberUtil.getSessionFactory().toString().contains("org.hibernate.internal.SessionFactoryImpl") == true) {
            sessionFactoryCreated = true;
        }
        assertEquals(true, sessionFactoryCreated);
    }
}