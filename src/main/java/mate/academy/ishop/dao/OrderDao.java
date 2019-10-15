package mate.academy.ishop.dao;

import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    public Optional<Order> add(Order order);

    public Optional<Order> get(Order order);

    public Optional<Order> get(Long orderId);

    public Optional<Order> update(Order order);

    public void delete(Order order);

    public void delete(Long orderId);

    public List<Order> getOrders(Long userId);

    public List<Item> getItems(Long orderId, Long userId);
}
