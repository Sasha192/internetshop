package mate.academy.ishop.dao.hibernate;

import mate.academy.ishop.dao.RoleDao;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Role;
import mate.academy.ishop.model.User;
import mate.academy.ishop.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;
import java.util.Set;

public class RoleDaoHibernateImpl implements RoleDao {
    @Inject
    private static UserDao userDao;
    private static Logger LOGGER = Logger.getLogger(RoleDaoHibernateImpl.class);

    @Override
    public Set<Role> getRoles(User user) {
        User userFromDb = userDao.get(user.getUserId()).get();
        return userFromDb.getRoles();
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        Session session = null;
        Role role = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Role where roleName=:role");
            Role.RoleName roleName = Role.of(name).getRoleName();
            query.setParameter("role", roleName);
            role = (Role) query.uniqueResult();
        } catch (Exception e) {
            LOGGER.error("Can't get role by name", e);
            return Optional.empty();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.of(role);
    }
}
