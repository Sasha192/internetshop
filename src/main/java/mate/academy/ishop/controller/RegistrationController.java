package mate.academy.ishop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.UserService;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;

    private static Logger logger = Logger.getLogger(RegistrationController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User(req.getParameter("name"), req.getParameter("psw"));
        userService.add(user);
        Cookie cookie = new Cookie("MATE", user.getToken());
        resp.addCookie(cookie);
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        logger.info("User " + user.getLogin() + " registered");
        resp.sendRedirect(req.getContextPath() + "/servlet/items");
    }
}
