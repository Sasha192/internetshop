package mate.academy.ishop.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static Logger LOGGER = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e){
            LOGGER.error("Can't create SessionFactory ", e);
            throw new RuntimeException();
        }
    }

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
