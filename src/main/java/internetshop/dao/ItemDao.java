package internetshop.dao;

import internetshop.model.Item;

public interface ItemDao {
    // CRUD

    public Item create(Item item);

    public Item get(Long id);

    public Item update(Item item);

    public Item delete(Long id);

    public Item deleteByItem(Item item);
}
