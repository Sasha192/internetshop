package mate.academy.ishop.dao;

import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;

import java.util.List;
import java.util.Optional;

public interface BucketDao {
    public Optional<Bucket> add(Bucket bucket);

    public Optional<Bucket> get(Long id);

    public Optional<Bucket> update(Bucket bucket);

    public void delete(Long id);

    public List<Item> getItems(Long bucketId);
}
