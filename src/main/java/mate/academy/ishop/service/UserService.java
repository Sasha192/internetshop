package mate.academy.ishop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.ishop.exceptions.AuthenticationException;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;

public interface UserService {
    Optional<User> add(User user);

    Optional<User> get(Long id);

    Optional<User> update(User user);

    void delete(Long id);

    List getOrders(Long userId);

    List<Order> getOrders(User user);

    List<User> getAll();

    Optional<User> login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String value);
}
