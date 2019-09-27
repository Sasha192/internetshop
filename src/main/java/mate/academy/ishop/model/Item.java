package mate.academy.ishop.model;

import mate.academy.ishop.generator.IdGenerator;

public class Item {
    private Long idItem;
    private String name;
    private Double price;

    public Item(final String name, final Double price) {
        idItem = IdGenerator.getItemId();
        this.name = name;
        this.price = price;
    }

    public Item(Long idItem, String name, Double price) {
        this.idItem = idItem;
        this.name = name;
        this.price = price;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getIdItem() {
        return idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
}
