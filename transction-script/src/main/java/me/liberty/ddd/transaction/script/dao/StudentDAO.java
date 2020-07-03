package me.liberty.ddd.transaction.script.dao;

import me.liberty.ddd.transaction.script.dos.StudentDO;
import me.liberty.ddd.transction.script.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.dao, v 0.1 3/10/16
 *          Exp $
 */
public class StudentDAO {

    public Connection connection;

    public StudentDAO(){
        connection = DBUtils.getConnection();
    }

    public StudentDO queryById(int studentId) throws SQLException {
        final PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM students WHERE id = ?");
        preparedStatement.setInt(1, studentId);
        final ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            return new StudentDO(rs.getInt("id"),rs.getString("name"),rs.getInt("age"), null);
        }
        return null;
    }

    public StudentDO queryByName(String name) throws SQLException {
        final PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM students WHERE name = ?");
        preparedStatement.setString(1, name);
        final ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            return new StudentDO(rs.getInt("id"),rs.getString("name"),rs.getInt("age"), null);
        }
        return null;
    }
}
