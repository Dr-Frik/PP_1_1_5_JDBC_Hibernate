package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "( id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(45) ," +
                    "lastname VARCHAR(45) ," +
                    "age TINYINT(10))");
            System.out.println("CREATE TABLE");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try ( Connection connection = Util.getConnection();
              Statement statement = connection.createStatement()) {

            statement.executeUpdate("drop table if exists users");
        } catch (SQLException e) {
            System.out.println("TABLE DELETE ERROR");
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users ( name, lastName, age) " +
                     "VALUES (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println( "User c именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("SAVE USER ERROR" + e);
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement("delete from users where id = ?")){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("remove Error " + e);
        }
    }

    public List<User> getAllUsers() {
        List <User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from users")) {
            ResultSet resultSet = ps.executeQuery();
            while ( resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }

        } catch (SQLException e) {
            System.out.println("LIST USERS ERROR " + e);
        }
        return list; // коментарий №2
    }

    public void cleanUsersTable() {
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            System.out.println("CLEAN TABLE ERROR " + e);;
        }
    }
}
