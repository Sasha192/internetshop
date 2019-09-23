package mate.academy.ishop.service;

import java.util.List;

import mate.academy.ishop.exceptions.AuthenticationException;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List getOrders(Long userId);

    List<Order> getOrders(User user);

    List<User> getAll();

    User login(String login, String password) throws AuthenticationException;
}
