package mate.academy.ishop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mate.academy.ishop.generator.IdGenerator;

public class User {
    private final Long userId;
    private final String login;
    private final String token;
    private String password;
    private List<Order> completedOrders;
    private Bucket currentBucket;

    public User(final String login) {
        userId = IdGenerator.getUserId();
        token = UUID.randomUUID().toString();
        this.login = login;
        password = new String();
        completedOrders = new ArrayList<Order>();
    }

    public User(final String login, final String password) {
        userId = IdGenerator.getUserId();
        token = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        completedOrders = new ArrayList<Order>();
    }

    public String getLogin() {
        return login;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(final List<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Bucket getCurrentBucket() {
        return currentBucket;
    }

    public void setCurrentBucket(final Bucket currentBucket) {
        this.currentBucket = currentBucket;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "userId = " + userId
                + " login " + login;
    }
}
