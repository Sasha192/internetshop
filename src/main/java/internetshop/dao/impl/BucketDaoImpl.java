package internetshop.dao.impl;

import internetshop.dao.BucketDao;
import internetshop.dao.Storage;
import internetshop.lib.Dao;
import internetshop.model.Bucket;
import java.util.NoSuchElementException;

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
        Bucket bucket = get(newBucket.getBucketId());
        bucket.setItemsList(newBucket.getItemsList());
        bucket.setUser(newBucket.getUser());
        return bucket;
    }

    @Override
    public void delete(Long id) {
        Storage.buckets.removeIf(bucket -> bucket.getBucketId().equals(id));
    }
}
