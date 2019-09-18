package mate.academy.ishop.controller;


import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddItemsController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user =(User)request.getSession().getAttribute("user");
        Item item = itemService.get(Long.valueOf(request.getParameter("item")));
        Bucket bucket = user.getCurrentBucket();
        if(bucket == null){
            bucket = new Bucket(user);
            user.setCurrentBucket(bucket);
            bucketService.add(bucket);
        }
        bucketService.addItem(bucket.getBucketId(), item.getIdItem());
        response.sendRedirect(request.getContextPath() + "/items");
    }
}
