package internetshop.dao;

import internetshop.model.Order;

public interface OrderDao {
    // CRUD

    public Order add(Order order);

    public Order get(Order order);

    public Order update(Order order);

    public void delete(Order order);
}
