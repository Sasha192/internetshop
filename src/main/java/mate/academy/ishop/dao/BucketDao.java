package mate.academy.ishop.dao;

import mate.academy.ishop.model.Bucket;

public interface BucketDao {
    public Bucket add(Bucket item);

    public Bucket get(Long id);

    public Bucket update(Bucket item);

    public void delete(Long id);
}
