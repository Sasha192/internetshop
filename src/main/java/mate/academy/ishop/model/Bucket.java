package mate.academy.ishop.model;

import java.util.ArrayList;
import java.util.List;

import mate.academy.ishop.generator.IdGenerator;

public class Bucket {
    private List<Item> itemsList;
    private Long userId;
    private Long bucketId;

    public Bucket(Long userId) {
        this.userId = userId;
        bucketId = IdGenerator.getBacketId();
        itemsList = new ArrayList<>();
    }

    public Bucket(Long userId, List<Item> itemsList) {
        this.itemsList = itemsList;
        this.userId = userId;
        bucketId = IdGenerator.getBacketId();
    }

    public Long getUserId() {
        return userId;
    }

    public void setBucketId(final Long bucketId) {
        this.bucketId = bucketId;
    }

    public void setUserId(final User user) {
        this.userId = userId;
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
