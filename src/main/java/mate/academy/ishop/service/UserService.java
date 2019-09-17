package mate.academy.ishop.service;

import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;

import java.util.List;

public interface UserService {
    User add(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List getOrders(Long userId);

    List<Order> getOrders(User user);

    List<User> getAll();
}
