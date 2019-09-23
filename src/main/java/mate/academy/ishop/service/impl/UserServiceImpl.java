package mate.academy.ishop.service.impl;

import java.util.List;
import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.exceptions.AuthenticationException;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.lib.Service;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public List getOrders(Long userId) {
        return userDao.get(userId).getCompletedOrders();
    }

    @Override
    public List<Order> getOrders(User user) {
        return userDao.get(user.getUserId()).getCompletedOrders();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException {
        return userDao.login(login, password);
    }
}
