package internetshop.service;

import internetshop.model.Item;

public interface ItemService {
    Item add(Item item);

    Item get(Long id);

    Item update(Item item);

    void delete(Long id);

    void delete(Item item);
}
