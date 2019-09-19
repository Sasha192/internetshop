package mate.academy.ishop.dao.impl;

import java.util.NoSuchElementException;

import mate.academy.ishop.dao.OrderDao;
import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order add(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Order get(Order order) {
        return Storage.orders.stream()
                .filter(element -> element.equals(order))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find order with id " + order));
    }

    @Override
    public Order get(Long orderId) {
        return Storage.orders.stream()
                .filter(element -> element.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public Order update(Order newOrder) {
        Order order = get(newOrder);
        order.setItems(newOrder.getItems());
        order.setUser(newOrder.getUser());
        return order;
    }

    @Override
    public void delete(Order order) {
        Storage.orders.removeIf(item -> item.equals(order));
    }

    @Override
    public void delete(Long orderId) {
        Storage.orders.removeIf(item -> item.getOrderId().equals(orderId));
    }
}
