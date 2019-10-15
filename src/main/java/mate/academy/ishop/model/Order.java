package mate.academy.ishop.model;

import java.util.List;
import mate.academy.ishop.generator.IdGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long orderId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_items",
            joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "idItem"))
    private List<Item> items;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Order() {
    }

    public Order(List<Item> items, User user) {
        orderId = IdGenerator.getOrderId();
        this.items = items;
        this.user = user;
    }

    public Order(Long orderId, User user, List<Item> items) {
        this.orderId = orderId;
        this.user = user;
        this.items = items;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(final User user) {
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
