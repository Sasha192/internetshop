package mate.academy.ishop.dao;

import mate.academy.ishop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {
    public Optional<Item> add(Item item);

    public Optional<Item> get(Long id);

    public Optional<Item> update(Item item);

    public void delete(Long id);

    public List<Item> getAll();
}
