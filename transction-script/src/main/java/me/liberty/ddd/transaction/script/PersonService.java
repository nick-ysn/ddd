package me.liberty.ddd.transaction.script;

import me.liberty.ddd.transaction.script.dao.UserDAO;
import me.liberty.ddd.transaction.script.dos.User;
import me.liberty.ddd.transaction.script.model.Person;
import me.liberty.ddd.transction.script.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script, v 0.1 2/26/16
 *          Exp $
 */
public class PersonService {

    private UserDAO userDAO;

    public PersonService() {
        this.userDAO = new UserDAO();
    }

    public void create(Person person) throws SQLException {
        final Connection connection = DBUtils.getConnection();
        final PreparedStatement preparedStatement = connection
                .prepareStatement("insert into users (name,age) values (?,?)");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.execute();
    }

    public void changeAgeOfNotDAO(Person person) throws SQLException {
        final Connection connection = DBUtils.getConnection();
        final PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM users WHERE name = ?");
        preparedStatement.setString(1, person.getName());
        final ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        final PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE users SET age = ? WHERE id = ?");
        updateStatement.setInt(1,person.getAge());
        updateStatement.setInt(2,id);
        updateStatement.execute();
    }

    public void changeAge(Person person) throws SQLException {
        final User user = userDAO.queryByName(person.getName());
        user.setAge(person.getAge());
        userDAO.update(user);
    }
}
