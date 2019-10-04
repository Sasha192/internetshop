package mate.academy.ishop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.ishop.dao.BucketDao;
import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket add(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        return Storage.buckets.stream()
                .filter(bucket -> bucket.getBucketId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + id));
    }

    @Override
    public Bucket update(Bucket newBucket) {
        return null;
    }

    @Override
    public List<Item> getItems(Long bucketId) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Storage.buckets.removeIf(bucket -> bucket.getBucketId().equals(id));
    }
}
