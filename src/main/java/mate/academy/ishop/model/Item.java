package mate.academy.ishop.model;

import mate.academy.ishop.generator.IdGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private Long idItem;
    @Column(name = "name")
    private String name;
    @Column(name = "price", columnDefinition = "DECIMAL")
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

    public Item() {

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
