package mate.academy.ishop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.ishop.model.Item;

public interface ItemService {
    Optional<Item> add(Item item);

    Optional<Item> get(Long id);

    Optional<Item> update(Item item);

    void delete(Long id);

    List<Item> getAll();
}
