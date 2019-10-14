package mate.academy.ishop.dao;

import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;

import java.util.List;

public interface BucketDao {
    public Bucket add(Bucket bucket);

    public Bucket get(Long id);

    public Bucket update(Bucket bucket);

    public void delete(Long id);

    public List<Item> getItems(Long bucketId);
}
