package internetshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Bucket {
    private List<Item> itemsList;
    private Double cost;
    private final Long BucketOwnerId;
    private final Long bucketId;

    public Long getBucketOwnerId() {
        return this.BucketOwnerId;
    }

    public Long getBucketId() {
        return this.bucketId;
    }

    Bucket(Long BucketOwnerId, Long bucketId){
        this.BucketOwnerId = BucketOwnerId;
        this.bucketId = bucketId;
        itemsList = new ArrayList<>();
        cost = Double.valueOf(0);
    }

    public void addItem(Item item){
        itemsList.add(item);
        cost = cost + item.getPrice();
    }

    public Item removeItem(Long id){
        Optional<Item> itemToRemove = itemsList.stream()
                .filter(item -> item.getIdItem() != id).findAny();
        itemsList.remove(itemToRemove);
        return itemToRemove.orElseThrow(()-> new NoSuchElementException());
    }

    public Item removeItem(Item item){
        itemsList.remove(item);
        return item;
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
