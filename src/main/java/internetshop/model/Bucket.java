package internetshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Bucket {
    private List<Item> itemsList;
    private Double cost;
    private User BucketOwner;

    Bucket(User BucketOwner){
        this.BucketOwner = BucketOwner;
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

    public User getBucketOwner() {
        return this.BucketOwner;
    }
}
