package mate.academy.ishop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.lib.Injector;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.ItemService;

import mate.academy.ishop.service.OrderService;
import mate.academy.ishop.service.UserService;
import org.apache.log4j.Logger;

public class InjectorInitializer implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(InjectorInitializer.class);

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
        try {
            logger.debug("Inject Dependencies started ... ");
            Injector.injectDependencies();
        } catch (IllegalAccessException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        Item item;
        User user;
        Bucket bucket;
        Order order;
        for (int i = 0; i < 10; i++) {
            user = new User(String.valueOf(i));
            bucket = new Bucket(user);
            order = new Order(bucket.getItemsList(), user);
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
