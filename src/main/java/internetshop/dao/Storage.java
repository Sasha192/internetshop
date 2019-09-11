package internetshop.dao;

import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.model.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Bucket> buckets = new ArrayList<Bucket>();
    public static final List<User> users = new ArrayList<User>();
    public static final List<Order> orders = new ArrayList<Order>();
    public static final List<Item> items = new ArrayList<Item>();
}
