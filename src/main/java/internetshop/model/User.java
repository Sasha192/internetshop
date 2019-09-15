package internetshop.model;

import internetshop.generator.IdGenerator;
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
    }

    public String getLogin() {
        return this.login;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public List<Order> getCompletedOrders() {
        return this.completedOrders;
    }

    public void setCompletedOrders(final List<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Bucket getCurrentBucket() {
        return this.currentBucket;
    }

    public void setCurrentBucket(final Bucket currentBucket) {
        this.currentBucket = currentBucket;
    }
}
