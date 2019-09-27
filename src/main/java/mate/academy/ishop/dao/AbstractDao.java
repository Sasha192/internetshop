package mate.academy.ishop.dao;

import java.sql.Connection;

public class AbstractDao<T> {
    protected final static String DB_NAME = "ishop";
    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}
