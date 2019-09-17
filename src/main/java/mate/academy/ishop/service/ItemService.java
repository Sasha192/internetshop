package mate.academy.ishop.service;

import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.User;

import java.util.List;

public interface ItemService {
    Item add(Item item);

    Item get(Long id);

    Item update(Item item);

    void delete(Long id);

    List<Item> getAll();
}
