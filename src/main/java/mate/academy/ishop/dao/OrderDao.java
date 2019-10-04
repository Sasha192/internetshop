package mate.academy.ishop.dao;

import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;

import java.util.List;

public interface OrderDao {
    public Order add(Order order);

    public Order get(Order order);

    public Order get(Long orderId);

    public Order update(Order order);

    public void delete(Order order);

    public void delete(Long orderId);

    public List<Order> getOrders(Long userId);

    public List<Item> getItems(Long orderId, Long userId);
}
