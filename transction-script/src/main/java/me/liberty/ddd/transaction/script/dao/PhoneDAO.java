package me.liberty.ddd.transaction.script.dao;

import me.liberty.ddd.transaction.script.dos.PhoneDO;
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
public class PhoneDAO {

    private Connection connection;

    public PhoneDAO() {
        connection = DBUtils.getConnection();
    }

    public PhoneDO queryByStudentIdAndPhoneNo(int studentId, String number) throws SQLException {
        final PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM phone WHERE student_id = ? AND phone_no = ?");
        preparedStatement.setInt(1, studentId);
        preparedStatement.setString(2, number);
        final ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            return new PhoneDO(rs.getInt("id"), rs.getString("phone_no"),
                    rs.getString("description"));
        }
        return null;
    }

    public void update(PhoneDO phoneDO) throws SQLException {
        final PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE phone SET phone_no = ?,description = ? WHERE id = ?");
        preparedStatement.setString(1, phoneDO.getNumber());
        preparedStatement.setString(2, phoneDO.getDesc());
        preparedStatement.setInt(3, phoneDO.getId());
        preparedStatement.execute();
    }
}
