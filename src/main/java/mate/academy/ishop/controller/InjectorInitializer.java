package mate.academy.ishop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import mate.academy.ishop.lib.Injector;
import org.apache.log4j.Logger;

public class InjectorInitializer implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(InjectorInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            logger.debug("Inject Dependencies started ... ");
            Injector.injectDependencies();
        } catch (IllegalAccessException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
