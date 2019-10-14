package mate.academy.ishop.model;

import java.util.ArrayList;
import java.util.List;

import mate.academy.ishop.generator.IdGenerator;

import javax.persistence.*;

@Entity
@Table(name = "buckets")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucketId")
    private Long bucketId;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "buckets_items",
            joinColumns = @JoinColumn(name = "bucketId", referencedColumnName = "bucketId"),
            inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "idItem"))
    private List<Item> itemsList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    public Bucket(){

    }

    public Bucket(Long userId) {
        user = new User();
        user.setUserId(userId);
        bucketId = IdGenerator.getBacketId();
        itemsList = new ArrayList<>();
    }

    public Bucket(Long userId, List<Item> itemsList) {
        this.itemsList = itemsList;
        this.user = new User();
        user.setUserId(userId);
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
