package util.factory;

import dao.DAO;
import dao.UserDAOJDBCImpl;
import util.DBHelper;

import java.sql.Connection;

public class UserDAOJDBCFactory extends UserDAOFactory {
    private static UserDAOJDBCFactory instance;
    private Connection connection;

    static UserDAOJDBCFactory getInstance() {
        if (instance == null) {
            instance = new UserDAOJDBCFactory();
        }
        return instance;
    }

    @Override
    public DAO createDAO() {
        return UserDAOJDBCImpl.getInstance(connection);
    }

    private UserDAOJDBCFactory() {
        this.connection = DBHelper.INSTANCE.getConnection();
    }
}
