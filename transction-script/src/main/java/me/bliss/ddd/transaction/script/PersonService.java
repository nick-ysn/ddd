package me.bliss.ddd.transaction.script;

import me.bliss.ddd.transaction.script.dao.UserDAO;
import me.bliss.ddd.transaction.script.dos.User;
import me.bliss.ddd.transaction.script.model.Person;
import me.bliss.ddd.transction.script.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public void changeAge(Person person) throws SQLException {
        final User user = userDAO.queryByName(person.getName());
        user.setAge(person.getAge());
        userDAO.update(user);
    }
}
