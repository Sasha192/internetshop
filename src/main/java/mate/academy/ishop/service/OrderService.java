package mate.academy.ishop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;

public interface OrderService {
    Optional<Order> add(Order order);

    Optional<Order> get(Long id);

    Optional<Order> update(Order order);

    void delete(Long id);

    Optional<Order> completeOrder(List<Item> items, Long userId);
}
