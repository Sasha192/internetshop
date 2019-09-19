package mate.academy.ishop.dao.impl;

import java.util.NoSuchElementException;
import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item add(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return Storage.items.stream()
                .filter(element -> element.getIdItem().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id " + id));
    }

    @Override
    public Item update(Item newItem) {
        Item item = get(newItem.getIdItem());
        item.setName(newItem.getName());
        item.setPrice(newItem.getPrice());
        return item;
    }

    @Override
    public void delete(Long id) {
        Storage.items.removeIf(item -> item.getIdItem().equals(id));
    }
}
