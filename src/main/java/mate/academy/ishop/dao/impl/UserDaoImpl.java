package mate.academy.ishop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.exceptions.AuthenticationException;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id " + id));
    }

    @Override
    public User update(User newUser) {
        User user = get(newUser.getUserId());
        user.setCurrentBucket(newUser.getCurrentBucket());
        user.setCompletedOrders(newUser.getCompletedOrders());
        return user;
    }

    @Override
    public void delete(Long id) {
        Storage.users.removeIf(user -> user.getUserId().equals(id));
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException {
        Optional<User> userOptional = Storage.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
        if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(password)) {
            throw new AuthenticationException("Incorrect login or password");
        }
        return userOptional.get();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.users.stream()
                .filter(user -> user.getToken().equals(token))
                .findAny();
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
