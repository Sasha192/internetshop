package mate.academy.ishop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.ishop.model.Bucket;

public interface BucketService {
    Optional<Bucket> add(Bucket bucket);

    Optional<Bucket> get(Long id);

    Optional<Bucket> update(Bucket bucket);

    void delete(Long id);

    Optional<Bucket> addItem(Long bucketId, Long itemId);

    Optional<Bucket> clear(Long bucketId);

    List getAllItems(Long bucketId);

    void removeItem(Long bucketId, Long itemId);
}
