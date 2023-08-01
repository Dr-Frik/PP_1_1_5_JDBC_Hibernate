package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "( id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(45) , " +
                    "lastname VARCHAR(45) , " +
                    "age TINYINT(10))").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("CREATE USER TABLE ERROR " + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("DROP USER TABLE ERROR " + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user1 = new User(name, lastName, age);
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user1);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("SAVE USER ERROR " + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void removeUserById(long id) {

        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("DELETE USER ERROR " + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> list = new ArrayList<>();
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            list = session.createQuery("from User").list();
        } catch (Exception e ) {
            System.out.println("LIST USERS ERROR " + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE users");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e ) {
            System.out.println("CLEAN TABLE USERS ERROR " + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
