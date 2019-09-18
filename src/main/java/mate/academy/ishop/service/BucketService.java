package mate.academy.ishop.service;

import mate.academy.ishop.model.Bucket;

import java.util.List;

public interface BucketService {
    Bucket add(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    void delete(Long id);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List getAllItems(Long bucketId);

    void removeItem(Long bucketId, Long itemId);
}