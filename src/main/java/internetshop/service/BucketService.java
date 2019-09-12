package internetshop.service;

import internetshop.model.Bucket;

import java.util.List;

public interface BucketService {
    Bucket add(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    void delete(Long id);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId); //remove all items from bucket

    List getAllItems(Long bucketId);
}
