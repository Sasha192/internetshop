package mate.academy.ishop.dao.impl;

import java.util.NoSuchElementException;
import mate.academy.ishop.dao.BucketDao;
import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.Bucket;

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
