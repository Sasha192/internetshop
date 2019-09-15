package internetshop.model;

import internetshop.generator.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private List<Item> itemsList;
    private Double cost;
    private User user;
    private Long bucketId;

    public Bucket(User user) {
        this.user = user;
        this.bucketId = IdGenerator.getBacketId();
        itemsList = new ArrayList<>();
        cost = Double.valueOf(0);
    }

    public Bucket(User user, List<Item> itemsList) {
        this.itemsList = itemsList;
        this.user = user;
        this.bucketId = IdGenerator.getBacketId();
        itemsList = new ArrayList<>();
        cost = Double.valueOf(0);
    }

    public User getUser() {
        return this.user;
    }

    public void setBucketId(final Long bucketId) {
        this.bucketId = bucketId;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Long getBucketId() {
        return this.bucketId;
    }

    public List<Item> getItemsList() {
        return this.itemsList;
    }

    public Double getCost() {
        return this.cost;
    }

    public void setItemsList(final List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }
}
