package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.factory.UserDAOHibernateFactory;

import java.util.List;

public class UserDAOHibernateImpl implements DAO {
    private static UserDAOHibernateImpl instance;
    private SessionFactory sessionFactory;

    public static UserDAOHibernateImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null)
            instance = new UserDAOHibernateImpl(sessionFactory);
        return instance;
    }

    private UserDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction trx = session.beginTransaction();
            session.save(user);
            trx.commit();
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction trx = session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id=?1")
                    .setParameter(1, id)
                    .executeUpdate();
            trx.commit();
        }
    }

    @Override
    public void deleteAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction trx = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            trx.commit();
        }
    }

    @Override
    public void updateUser(User user) {
        try(Session session = sessionFactory.openSession()) {
            Transaction trx = session.beginTransaction();
            session.createQuery("UPDATE User SET login=?1, password=?2, name=?3 WHERE id=?4")
                    .setParameter(1, user.getLogin())
                    .setParameter(2, user.getPassword())
                    .setParameter(3, user.getName())
                    .setParameter(4, user.getId())
                    .executeUpdate();
            trx.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User").list();
        }
    }

    @Override
    public boolean validateUser(String login, String password) {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User where login=?1 AND password=?2")
                    .setParameter(1, login)
                    .setParameter(2, password)
                    .uniqueResult() != null;
        }
    }
}
