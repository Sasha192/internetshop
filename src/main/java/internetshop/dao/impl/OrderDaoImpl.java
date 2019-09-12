package internetshop.dao.impl;

import internetshop.dao.OrderDao;
import internetshop.dao.Storage;
import internetshop.lib.Dao;
import internetshop.model.Order;

import java.util.NoSuchElementException;

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
    public Order update(Order newOrder) {
        Order order = get(newOrder);
        order.setBucket(newOrder.getBucket());
        return order;
    }

    @Override
    public void delete(Order order) {
        Storage.orders.removeIf(item -> item.equals(order));
    }
}
