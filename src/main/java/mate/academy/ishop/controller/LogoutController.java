package mate.academy.ishop.controller;

import mate.academy.ishop.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends HttpServlet {
    private static Logger logger = Logger.getLogger(LogoutController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("MATE", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().invalidate();
        logger.info("GG WP, " + user.getLogin());
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
