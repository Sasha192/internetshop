package internetshop.service;

import internetshop.model.Item;
import internetshop.model.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    void delete(Long id);

    Order completeOrder(List<Item> items, Long userId);
}
