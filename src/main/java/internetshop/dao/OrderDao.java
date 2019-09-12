package internetshop.dao;

import internetshop.model.Order;

public interface OrderDao {
    // CRUD

    public Order add(Order item);

    public Order get(Long id);

    public Order update(Order item);

    public void delete(Long id);
}
