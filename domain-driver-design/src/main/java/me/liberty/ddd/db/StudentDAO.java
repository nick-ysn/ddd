package me.liberty.ddd.db;

/**
 *
 *
 * @author shouna.ysn
 * @email shouna.ysn@alipay.com
 * @version $Id: me.liberty.ddd.db, v 0.1 16/08/2017
 *          Exp $
 */
public interface StudentDAO {

    void insert(StudentDO studentDO);

    StudentDO queryById(int id);
}
