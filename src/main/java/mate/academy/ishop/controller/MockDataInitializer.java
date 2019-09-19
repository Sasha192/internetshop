package mate.academy.ishop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.ItemService;
import mate.academy.ishop.service.OrderService;
import mate.academy.ishop.service.UserService;

public class MockDataInitializer implements ServletContextListener {
    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Item item;
        User user;
        Bucket bucket;
        Order order;
        user = new User("Anonymous");
        bucket = new Bucket(user);
        order = new Order(bucket.getItemsList(), user);
        for (int i = 0; i < 10; i++) {
            item = new Item(String.valueOf(i), Double.valueOf(i));
            user.setCurrentBucket(bucket);
            userService.add(user);
            bucketService.add(bucket);
            orderService.add(order);
            itemService.add(item);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
