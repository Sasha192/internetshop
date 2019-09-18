package mate.academy.ishop.model;

import mate.academy.ishop.generator.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private List<Item> itemsList;
    private User user;
    private Long bucketId;

    public Bucket(User user) {
        this.user = user;
        bucketId = IdGenerator.getBacketId();
        itemsList = new ArrayList<>();
    }

    public Bucket(User user, List<Item> itemsList) {
        this.itemsList = itemsList;
        this.user = user;
        bucketId = IdGenerator.getBacketId();
    }

    public User getUser() {
        return user;
    }

    public void setBucketId(final Long bucketId) {
        this.bucketId = bucketId;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(final List<Item> itemsList) {
        this.itemsList = itemsList;
    }
}