package util.factory;

import dao.DAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Properties;

public abstract class UserDAOFactory {

    public abstract DAO createDAO();

    public static UserDAOFactory createFactoryByProperties() {
        Properties config = new Properties();
        try {
            config.load(Files.newInputStream(Paths.get("D:/MyProjects/abstractFactory/src/main/resources/config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (config.getProperty("daoType")) {
            case "jdbc":
                return UserDAOJDBCFactory.getInstance();
            case "hibernate":
                return UserDAOHibernateFactory.getInstance();
            default:
                throw new InvalidParameterException("invalid config.properties parameter");
        }
    }
}
