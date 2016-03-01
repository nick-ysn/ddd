package me.bliss.ddd.transaction.script;

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

    public void create(Person person) throws SQLException {
        final Connection connection = DBUtils.getConnection();
        final PreparedStatement preparedStatement = connection
                .prepareStatement("insert into users (name,age) values (?,?)");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.execute();
    }
}
