package internetshop.dao;

import internetshop.model.Bucket;

public interface BucketDao {
    // CRUD

    public Bucket create(Bucket item);

    public Bucket get(Long id);

    public Bucket update(Bucket item);

    public Bucket delete(Long id);

    public Bucket deleteByItem(Bucket item);
}
