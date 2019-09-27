package mate.academy.ishop.service.impl;

import java.util.List;
import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.lib.Service;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item add(Item item) {
        return itemDao.add(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(id);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }
}
