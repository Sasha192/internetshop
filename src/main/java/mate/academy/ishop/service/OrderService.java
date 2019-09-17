package mate.academy.ishop.service;

import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;

import java.util.List;

public interface OrderService {
    Order add(Order order);

    Order get(Long id);

    Order update(Order order);

    void delete(Long id);

    Order completeOrder(List<Item> items, Long userId);
}
