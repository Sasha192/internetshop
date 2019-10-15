package mate.academy.ishop.dao.hibernate;

import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.exceptions.AuthenticationException;
import mate.academy.ishop.model.User;
import mate.academy.ishop.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoHibernateImpl implements UserDao {
    private static Logger LOGGER = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public Optional<User> add(User user) {
        Transaction transaction = null;
        Session session = null;
        Long userId;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            userId = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't add user", e);
            return Optional.empty();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        user.setUserId(userId);
        return Optional.of(user);
    }

    @Override
    public Optional<User> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.of(session.get(User.class, id));
        } catch (Exception e) {
            LOGGER.error("Can't get user", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> update(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't update user", e);
            return Optional.empty();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.of(user);
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            User user = new User();
            user.setUserId(id);
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't delete user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> login(String login, String password) throws AuthenticationException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where login=:login");
            query.setParameter("login", login);
            User user = (User) query.uniqueResult();
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where token=:token");
            query.setParameter("token", token);
            User user = (User) query.uniqueResult();
            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User");
            users = query.list();
        } catch (Exception e) {
            LOGGER.error("Can't get users list", e);
            return new ArrayList<>();
        }
        return users;
    }
}
