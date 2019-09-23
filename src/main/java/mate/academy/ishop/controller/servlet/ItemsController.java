package mate.academy.ishop.controller.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.service.ItemService;

public class ItemsController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Item> list = itemService.getAll();
        request.setAttribute("items", list);
        request.setAttribute("user", request.getSession().getAttribute("user"));
        request.getRequestDispatcher("/WEB-INF/views/items.jsp").forward(request, response);
    }
}
