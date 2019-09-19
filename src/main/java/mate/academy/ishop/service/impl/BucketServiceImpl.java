package mate.academy.ishop.service.impl;

import java.util.List;
import mate.academy.ishop.dao.BucketDao;
import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.lib.Service;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static ItemDao itemDao;

    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket add(Bucket bucket) {
        return bucketDao.add(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) {
        bucketDao.delete(id);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.getItemsList().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        bucket.getItemsList().clear();
        return bucketDao.update(bucket);
    }

    @Override
    public List getAllItems(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        return bucket.getItemsList();
    }

    @Override
    public void removeItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.getItemsList().remove(item);
    }
}
