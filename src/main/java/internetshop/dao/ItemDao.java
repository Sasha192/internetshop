package internetshop.dao;

import internetshop.model.Item;

public interface ItemDao {
    public Item add(Item item);

    public Item get(Long id);

    public Item update(Item item);

    public void delete(Long id);
}
