package mate.academy.ishop.model;

import java.util.List;
import mate.academy.ishop.generator.IdGenerator;

public class Order {
    private List<Item> items;
    private Long orderId;
    private Long userId;

    public Order(List<Item> items, Long userId) {
        orderId = IdGenerator.getOrderId();
        this.items = items;
        this.userId = userId;
    }

    public Order(Long orderId, Long userId, List<Item> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
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
