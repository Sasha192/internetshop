package mate.academy.ishop.dao.hibernate;

import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    private static Logger LOGGER = Logger.getLogger(ItemDaoHibernateImpl.class);

    @Override
    public Item add(Item item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't create item.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Item item = session.get(Item.class, id);
            return item;
        } catch (Exception e){
            LOGGER.error("Can't open Session.", e);
            throw new RuntimeException();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            LOGGER.error("Can't update item.", e);
            throw new RuntimeException();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Item item = new Item();
            item.setIdItem(id);
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't delete item", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Item> getAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createCriteria(Item.class).list();
        } catch (Exception e){
            LOGGER.error("Can't create Criteria(Item.class)", e);
            throw new RuntimeException();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
