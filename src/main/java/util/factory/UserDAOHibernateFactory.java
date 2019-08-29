package util.factory;

import dao.DAO;
import dao.UserDAOHibernateImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import util.DBHelper;

public class UserDAOHibernateFactory extends UserDAOFactory {
    private static UserDAOHibernateFactory instance;
    private SessionFactory sessionFactory;

    static UserDAOHibernateFactory getInstance() {
        if (instance == null) {
            instance = new UserDAOHibernateFactory();
        }
        return instance;
    }

    @Override
    public DAO createDAO() {
        return UserDAOHibernateImpl.getInstance(sessionFactory);
    }

    private UserDAOHibernateFactory() {
        this.sessionFactory = DBHelper.INSTANCE.getConfiguration()
                .buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(DBHelper.INSTANCE.getConfiguration().getProperties())
                                .build()
                );
    }
}
