package mate.academy.ishop.dao;

import java.util.ArrayList;
import java.util.List;

import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;

public class Storage {
    public static final List<Bucket> buckets = new ArrayList<Bucket>();
    public static final List<User> users = new ArrayList<User>();
    public static final List<Order> orders = new ArrayList<Order>();
    public static final List<Item> items = new ArrayList<Item>();
}
