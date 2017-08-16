package me.liberty.ddd.repository;

import me.liberty.ddd.model.entity.Student;
import me.liberty.ddd.model.entity.StudentId;

/**
 *
 *
 * @author shouna.ysn
 * @email shouna.ysn@alipay.com
 * @version $Id: me.liberty.ddd.repository, v 0.1 16/08/2017
 *          Exp $
 */
public interface StudentRepository {
    StudentId save(Student student);

    Student studentOfId(StudentId studentId);
}
