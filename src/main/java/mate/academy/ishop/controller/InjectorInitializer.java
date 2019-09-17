package mate.academy.ishop.controller;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.lib.Injector;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.service.ItemService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

public class InjectorInitializer implements ServletContextListener {
    @Inject
    private static ItemService itemService;

    private static final Logger logger = Logger.getLogger(InjectorInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Injector.injectDependencies();
        } catch (IllegalAccessException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        Item item;
        for(int i = 0; i < 10; i++){
            item = new Item(String.valueOf(i), Double.valueOf(i));
            itemService.add(item);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
