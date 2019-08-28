package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.factory.UserDAOHibernateFactory;

import java.util.List;

public class UserDAOHibernateImpl implements DAO {
    private Session session;

    public UserDAOHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addUser(User user) {
        Transaction trx = session.beginTransaction();
        session.save(user);
        trx.commit();
        session.close();
    }

    @Override
    public void deleteUserById(Long id) {
        Transaction trx = session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id=?1")
                .setParameter(1, id)
                .executeUpdate();
        trx.commit();
        session.close();
    }

    @Override
    public void deleteAllUsers(){
        Transaction trx = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        trx.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Transaction trx = session.beginTransaction();
        session.createQuery("UPDATE User SET login=?1, password=?2, name=?3 WHERE id=?4")
                .setParameter(1, user.getLogin())
                .setParameter(2, user.getPassword())
                .setParameter(3, user.getName())
                .setParameter(4, user.getId())
                .executeUpdate();
        trx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        return session.createQuery("FROM User").list();
    }

    @Override
    public boolean validateUser(String login, String password) {
        return session.createQuery("FROM User where login=?1 AND password=?2")
                .setParameter(1, login)
                .setParameter(2, password)
                .uniqueResult()!=null;
    }
}
