package internetshop.dao;

import internetshop.model.Order;

public interface OrderDao {
    // CRUD

    public Order create(Order item);

    public Order get(Long id);

    public Order update(Order item);

    public Order delete(Long id);

    public Order deleteByItem(Order item);
}
