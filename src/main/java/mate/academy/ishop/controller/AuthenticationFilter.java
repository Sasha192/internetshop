package mate.academy.ishop.controller;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter implements Filter {
        @Inject
        private static UserService userService;

        private static Logger logger = Logger.getLogger(AuthenticationFilter.class);

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                             FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("MATE")) {
                    Optional<User> user = userService.getByToken(cookie.getValue());
                    if (user.isPresent()) {
                        chain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
            logger.info("User was not authentificated");
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
