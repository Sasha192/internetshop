package mate.academy.ishop.service;

import java.util.List;
import mate.academy.ishop.model.Item;

public interface ItemService {
    Item add(Item item);

    Item get(Long id);

    Item update(Item item);

    void delete(Long id);

    List<Item> getAll();
}
