package util;

import model.User;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBHelper {
    INSTANCE;

    private static Connection connection;
    private static Configuration configuration;

    public Connection getConnection() {
        if (connection == null) {
            try {
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

                connection = DriverManager.getConnection(
                        "jdbc:mysql://" +
                                "localhost:3306/" +
                                "db_example?useSSL=false&" +
                                "user=root&" +
                                "password=root"
                );
            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public Configuration getConfiguration() {
        if (connection == null)
            configuration = new Configuration()
                    .configure()
                    .addAnnotatedClass(User.class);
        return configuration;
    }
}
