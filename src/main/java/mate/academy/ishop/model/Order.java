package mate.academy.ishop.model;

import mate.academy.ishop.generator.IdGenerator;

import java.util.List;

public class Order {
    private List<Item> items;
    private Long orderId;
    private User user;

    public Order(List<Item> items, User user) {
        orderId = IdGenerator.getOrderId();
        this.items = items;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }
}
