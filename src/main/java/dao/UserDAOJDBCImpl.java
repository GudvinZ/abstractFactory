package dao;

import exeption.DBException;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBCImpl implements DAO {
    private static UserDAOJDBCImpl instance;
    private Connection connection;

    public static UserDAOJDBCImpl getInstance(Connection connection) {
        if (instance == null)
            instance = new UserDAOJDBCImpl(connection);
        return instance;
    }

    private UserDAOJDBCImpl(Connection connection) {
        this.connection = connection;
        createTable();
    }

    private int execUpdate(String update, Object... parameters) {
        try (PreparedStatement stmt = connection.prepareStatement(update)) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @FunctionalInterface
    private interface FunctionR<T, R> {
        R apply(T t) throws SQLException;
    }

    private <T> T execQuery(String query, FunctionR<ResultSet, T> f, Object... parameters) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
            return f.apply(stmt.executeQuery());
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public void addUser(User user) {
        execUpdate(
                "insert into users (login, password, name) values (?, ?, ?)",
                user.getLogin(),
                user.getPassword(),
                user.getName()
        );
    }

    @Override
    public void deleteUserById(Long id) {
        execUpdate("delete from users where id=?",
                id
        );
    }

    @Override
    public void deleteAllUsers() {
        execUpdate("delete from users");
    }

    @Override
    public void updateUser(User user) {
        execUpdate("update users set login=?, password=?, name=? where id=?",
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getId()
        );
    }

    @Override
    public List<User> getAllUsers() {
        return execQuery(
                "select * from users",
                x -> {
                    ArrayList<User> list = new ArrayList<>();
                    while (x.next()) {
                        list.add(
                                new User(
                                        x.getLong("id"),
                                        x.getString("login"),
                                        x.getString("password"),
                                        x.getString("name")
                                ));
                    }
                    return list;
                }
        );
    }

    @Override
    public boolean validateUser(String login, String password) {
        return execQuery(
                "select id from users where login=? and password=?",
                ResultSet::next,
                login,
                password
        );
    }

    public void createTable() {
        execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), name varchar(256), primary key (id))");
    }

    public void dropTable() {
        execUpdate("DROP TABLE IF EXISTS users");
    }
}
