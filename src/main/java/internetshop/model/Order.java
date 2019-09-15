package internetshop.model;

import internetshop.generator.IdGenerator;

import java.util.List;

public class Order {
    private List<Item> items;
    private Long orderId;
    private Double cost;
    private User user;

    public Order(List<Item> items, User user) {
        orderId = IdGenerator.getOrderId();
        this.items = items;
        this.user = user;
        cost = items.stream()
                .map(obj -> obj.getPrice())
                .reduce(0.0, (price1, price2) -> price1 + price2);
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Double getCost() {
        return this.cost;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }
}
