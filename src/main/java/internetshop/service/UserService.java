package internetshop.service;

import internetshop.model.Order;
import internetshop.model.User;

import java.util.List;

public interface UserService {
    User add(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List getOrders(Long userId);

    List<Order> getOrders(User user);
}
