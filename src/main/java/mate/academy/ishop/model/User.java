package mate.academy.ishop.model;

import mate.academy.ishop.generator.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Long userId;
    private final String login;
    private String password;
    private List<Order> completedOrders;
    private Bucket currentBucket;

    public User(final String login, final String password) {
        userId = IdGenerator.getUserId();
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

    @Override
    public String toString() {
        return "userId = " + userId
                + " login " + login;
    }
}
