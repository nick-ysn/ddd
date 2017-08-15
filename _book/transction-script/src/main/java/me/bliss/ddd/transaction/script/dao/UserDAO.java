package me.bliss.ddd.transaction.script.dao;

import me.bliss.ddd.transaction.script.dos.User;
import me.bliss.ddd.transction.script.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.dao, v 0.1 3/9/16
 *          Exp $
 */
public class UserDAO {

    public Connection connection;

    public UserDAO() {
        connection = DBUtils.getConnection();
    }

    public User queryByName(String name) throws SQLException {
        final PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM users WHERE name = ?");
        preparedStatement.setString(1, name);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt(
                    "age"));
        }
        return null;
    }

    public void create(User user) throws SQLException {
        final PreparedStatement preparedStatement = connection
                .prepareStatement("insert into users (name,age) values (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setInt(2, user.getAge());
        preparedStatement.execute();
    }

    public void update(User user) throws SQLException {
        final PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE users SET name = ?,age = ? WHERE id = ?");
        preparedStatement.setString(1,user.getName());
        preparedStatement.setInt(2,user.getAge());
        preparedStatement.setInt(3,user.getId());
        preparedStatement.execute();
    }
}
