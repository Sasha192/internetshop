package mate.academy.ishop.dao.jdbc;

import mate.academy.ishop.dao.AbstractDao;
import mate.academy.ishop.dao.BucketDao;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket add(Bucket bucket) {
        String query = "INSERT INTO ishop.buckets VALUES ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(
                query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementBuckets.setLong(1, bucket.getUserId());
            statementBuckets.executeUpdate();
            ResultSet generatedKeys = statementBuckets.getGeneratedKeys();
            generatedKeys.next();
            Long bucketId = generatedKeys.getLong(1);
            bucket.setBucketId(bucketId);
        } catch (SQLException e) {
            logger.error("Can't create bucket", e);
        }
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        Bucket bucket = null;
        String query = "SELECT * FROM ishop.buckets WHERE bucketId = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(query)) {
            statementBuckets.setLong(1, bucketId);
            ResultSet resultSet = statementBuckets.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("userId");
                bucket = new Bucket(userId);
                bucket.setBucketId(bucketId);
            }
        } catch (SQLException e) {
            logger.error("Can't get bucket", e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        String query =
                "UPDATE ishop.buckets SET userId = ? WHERE bucketId = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(query)) {
            statementBuckets.setLong(1, bucket.getUserId());
            statementBuckets.setLong(2, bucket.getBucketId());
            statementBuckets.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update bucket", e);
        }
        return bucket;
    }

    @Override
    public void delete(Long bucketId) {
        Bucket bucket = get(bucketId);
        String query = "DELETE FROM ishop.buckets WHERE bucketId = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(query)) {
            statementBuckets.setLong(1, bucketId);
            statementBuckets.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete bucket", e);
        }
    }

    @Override
    public List<Item> getItems(Long bucketId) {
        List<Item> list = new ArrayList<>();
        String queryBucktes = "SELECT * FROM ishop.items INNER JOIN ishop.buckets_items "
                + "ON ishop.items.idItem = ishop.buckets_items.itemId WHERE bucketId = ?;";
        try (PreparedStatement statementBuckets = connection.prepareStatement(queryBucktes)) {
            statementBuckets.setLong(1, bucketId);
            ResultSet resultSet = statementBuckets.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(itemId, name, price);
                list.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't add item", e);
        }
        return list;
    }
}
