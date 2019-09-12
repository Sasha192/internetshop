package internetshop.service.impl;

import internetshop.lib.Service;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order add(Order order) {
        return null;
    }

    @Override
    public Order get(Long id) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Order completeOrder(List<Item> items, Long userId) {
        return null;
    }
}
