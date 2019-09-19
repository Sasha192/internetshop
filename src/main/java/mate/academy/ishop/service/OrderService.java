package mate.academy.ishop.service;

import java.util.List;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;

public interface OrderService {
    Order add(Order order);

    Order get(Long id);

    Order update(Order order);

    void delete(Long id);

    Order completeOrder(List<Item> items, Long userId);
}
