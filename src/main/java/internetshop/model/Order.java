package internetshop.model;

public class Order {
    Bucket bucket;
    User customer;
    Double cost;

    public Order(final Bucket bucket, final User customer) {
        this.bucket = bucket;
        this.customer = customer;
        cost = bucket.getCost();
    }

    public Bucket getBucket() {
        return this.bucket;
    }

    public void setBucket(final Bucket bucket) {
        this.bucket = bucket;
    }

    public User getCustomer() {
        return this.customer;
    }

    public Double getCost() {
        return this.cost;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }
}
