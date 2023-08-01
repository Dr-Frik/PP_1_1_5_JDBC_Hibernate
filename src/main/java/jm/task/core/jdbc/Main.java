package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserService us1 = new UserServiceImpl();


        us1.createUsersTable();
        us1.saveUser("Masha","Las", (byte) 31);
        us1.saveUser("Ivan", "Null", (byte) 45);
        us1.saveUser("Sasha", "O", (byte) 25);
        us1.saveUser("Geka", "Z", (byte) 18);
        us1.saveUser("Frik", "N", (byte) 34);
        us1.removeUserById( 2);
        System.out.println(us1.getAllUsers());

        us1.cleanUsersTable();
        us1.dropUsersTable();


        // реализуйте алгоритм здесь
    }
}
