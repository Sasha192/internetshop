package internetshop.dao;

import internetshop.model.Order;

public interface OrderDao {
    public Order add(Order order);

    public Order get(Order order);

    public Order get(Long orderId);

    public Order update(Order order);

    public void delete(Order order);

    public void delete(Long orderId);
}
