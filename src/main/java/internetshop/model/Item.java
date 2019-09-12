package internetshop.model;

import internetshop.generator.IdGenerator;

public class Item {
    private Long idItem;
    private String name;
    private Double price;

    public Item(final String name, final Double price) {
        this.idItem = IdGenerator.getItemId();
        this.name = name;
        this.price = price;
    }

    public Long getIdItem() {
        return this.idItem;
    }

    public void setIdItem(final Long idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
}
