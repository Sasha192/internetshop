package internetshop.dao;

import internetshop.model.Bucket;

public interface BucketDao {
    public Bucket add(Bucket item);

    public Bucket get(Long id);

    public Bucket update(Bucket item);

    public void delete(Long id);
}
