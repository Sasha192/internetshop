package internetshop.service.impl;

import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Inject
    private static UserDao userDao;

    @Override
    public Order add(Order order) {
        return orderDao.add(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List<Item> items, Long userId) {
        Order newOrder = new Order(new Bucket(userId,items),userId);
        userDao.get(userId).getCompletedOrders().add(newOrder);
        orderDao.add(newOrder);
        return newOrder;
    }
}
