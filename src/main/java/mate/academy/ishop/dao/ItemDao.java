package mate.academy.ishop.dao;

import mate.academy.ishop.model.Item;

import java.util.List;

public interface ItemDao {
    public Item add(Item item);

    public Item get(Long id);

    public Item update(Item item);

    public void delete(Long id);

    public List<Item> getAll();
}
