package mate.academy.ishop.dao.hibernate;

import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    private static Logger LOGGER = Logger.getLogger(ItemDaoHibernateImpl.class);

    @Override
    public Item add(Item item) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            LOGGER.error("Can't create item.", e);
            throw new RuntimeException();
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Item item = session.get(Item.class, id);
            return item;
        } catch (Exception e){
            LOGGER.error("Can't open Session.", e);
            throw new RuntimeException();
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            LOGGER.error("Can't update item.", e);
            throw new RuntimeException();
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        }
    }

    @Override
    public List<Item> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createCriteria(Item.class).list();
        }
        catch (Exception e){
            LOGGER.error("Can't create Criteria(Item.class)", e);
            throw new RuntimeException();
        }
    }
}
