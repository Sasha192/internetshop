package mate.academy.ishop.dao.hibernate;

import mate.academy.ishop.dao.OrderDao;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoHibernateImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoHibernateImpl.class);
    @Inject
    private static UserDao userDao;

    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        Long orderId = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            orderId = (Long) session.save(order);
            transaction.commit();
        } catch (Exception e) {
            LOGGER.error("Can't add new order", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        order.setOrderId(orderId);
        return order;
    }

    @Override
    public Order get(Order order) {
        return get(order.getOrderId());
    }

    @Override
    public Order get(Long orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Order.class, orderId);
        } catch (Exception e) {
            LOGGER.error("Can't get order", e);
            return null;
        }
    }

    @Override
    public Order update(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't update order", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public void delete(Order order) {
        delete(order.getOrderId());
    }

    @Override
    public void delete(Long orderId) {
        Session session = null;
        Transaction transaction = null;
        try {
            Order order = get(orderId);
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't delete order", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrders(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Order WHERE user.id=:userId");
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Can't get orders by userId", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Item> getItems(Long orderId, Long userId) {
        try {
            Order order = get(orderId);
            return order.getItems();
        } catch (Exception e) {
            LOGGER.error("Can't get items from order", e);
            return new ArrayList<>();
        }
    }
}
