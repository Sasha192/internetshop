package mate.academy.ishop.dao.hibernate;

import mate.academy.ishop.dao.BucketDao;
import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BucketDaoHibernateImpl implements BucketDao {
    private static Logger LOGGER = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Inject
    private static UserDao userDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket add(Bucket bucket) {
        Transaction transaction = null;
        Session session = null;
        Long bucketFromDbId = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            bucketFromDbId = (Long) session.save(bucket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't add bucket", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        bucket.setBucketId(bucketFromDbId);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Bucket.class, id);
        } catch (Exception e) {
            LOGGER.error("Can't get bucket", e);
            return null;
        }
    }

    @Override
    public Bucket update(Bucket bucket) {
        Transaction transaction = null;
        Session session = null;
        Bucket oldBucket = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(bucket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't add item into bucket", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        Bucket bucket = new Bucket(Long.valueOf(1));
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(bucket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't add item into bucket", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Item> getItems(Long bucketId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Bucket bucket = get(bucketId);
            return bucket.getItemsList();
        } catch (Exception e) {
            LOGGER.error("Can't get all items from bucket", e);
            return new ArrayList<>();
        }
    }
}
