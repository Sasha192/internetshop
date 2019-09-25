package mate.academy.ishop.controller;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Role;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static mate.academy.ishop.model.Role.RoleName.ADMIN;
import static mate.academy.ishop.model.Role.RoleName.USER;

public class AuthorizationFilter implements Filter {
    private static final String EMPTY_STRING = "";
    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();
    private static Logger logger = Logger.getLogger(AuthorizationFilter.class);

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/users", ADMIN);
        protectedUrls.put("/servlet/items", USER);
        protectedUrls.put("/servlet/bucket", USER);
        protectedUrls.put("/servlet/user/complete", USER);
        protectedUrls.put("/servlet/user/showOrders", USER);
        protectedUrls.put("/servlet/showAllOrders", USER);
        protectedUrls.put("/servlet/user/deleteorder", USER);
        protectedUrls.put("/servlet/user/delete", USER);
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User visitor = (User) req.getAttribute("visitor");
        req.removeAttribute("visitor");
        String requestUri = req.getRequestURI().replace(req.getContextPath(), EMPTY_STRING);
        Role.RoleName requiredRoleName = protectedUrls.get(requestUri);
        if (requiredRoleName != null
                && !checkRole(visitor, requiredRoleName)) {
            logger.info("User's role doesn't match required role");
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    private static boolean checkRole(User user, Role.RoleName roleName) {
        return user.getRoles().stream().anyMatch(r -> r.getRoleName().equals(roleName));
    }

    @Override
    public void destroy() {

    }
}
