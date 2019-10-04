package mate.academy.ishop.dao;

import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;

import java.util.List;

public interface BucketDao {
    public Bucket add(Bucket item);

    public Bucket get(Long id);

    public Bucket update(Bucket item);

    public void delete(Long id);

    public List<Item> getItems(Long bucketId);
}
