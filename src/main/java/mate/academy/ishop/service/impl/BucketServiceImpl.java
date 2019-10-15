package mate.academy.ishop.service.impl;

import java.util.List;
import java.util.Optional;

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
    public Optional<Bucket> add(Bucket bucket) {
        return bucketDao.add(bucket);
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) {
        bucketDao.delete(id);
    }

    @Override
    public Optional<Bucket> addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId).get();
        Item item = itemDao.get(itemId).get();
        bucket.getItemsList().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public Optional<Bucket> clear(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId).get();
        bucket.getItemsList().clear();
        return bucketDao.update(bucket);
    }

    @Override
    public List getAllItems(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId).get();
        return bucket.getItemsList();
    }

    @Override
    public void removeItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId).get();
        Item item = itemDao.get(itemId).get();
        bucket.getItemsList().remove(item);
    }
}
