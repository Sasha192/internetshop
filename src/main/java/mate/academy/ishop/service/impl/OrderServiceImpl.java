package mate.academy.ishop.service.impl;

import mate.academy.ishop.dao.OrderDao;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.lib.Service;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.service.OrderService;

import java.util.List;
import java.util.NoSuchElementException;

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
        Order newOrder = new Order(items, userDao.get(userId));
        orderDao.add(newOrder);
        userDao.get(userId)
                .getCompletedOrders().add(newOrder);
        userDao.get(userId)
                .getCurrentBucket().getItemsList().clear();
        return newOrder;
    }

    public Double getCost(Order order) {
        if(order.getItems() == null){
            throw new NoSuchElementException();
        }
        return order.getItems().stream()
                .map(obj -> obj.getPrice())
                .reduce(0.0, (price1, price2) -> price1 + price2);
    }
}
