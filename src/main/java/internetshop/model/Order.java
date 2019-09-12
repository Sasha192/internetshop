package internetshop.model;

public class Order {
    Bucket bucket;
    Long customerId;
    Double cost;
    Long orderId;

    public Order(final Bucket bucket, final Long customerId) {
        this.bucket = bucket;
        this.customerId = customerId;
        cost = bucket.getCost();
    }

    public void setCustomerId(final Long customer) {
        this.customerId = customer;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    public Bucket getBucket() {
        return this.bucket;
    }

    public void setBucket(final Bucket bucket) {
        this.bucket = bucket;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public Double getCost() {
        return this.cost;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }
}
