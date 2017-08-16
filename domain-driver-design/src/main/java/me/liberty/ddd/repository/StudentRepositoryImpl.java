package me.liberty.ddd.repository;

import lombok.Setter;
import me.liberty.ddd.db.AddressDAO;
import me.liberty.ddd.db.StudentDAO;
import me.liberty.ddd.db.StudentDO;
import me.liberty.ddd.model.Address;
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
public class StudentRepositoryImpl implements StudentRepository {

    @Setter
    private StudentDAO studentDAO;

    @Setter
    private AddressDAO addressDAO;

    public StudentId save(Student student) {
        studentDAO.insert(new StudentDO(student.getName()));
        addressDAO.insert(student.getAddress());
        return student.getStudentId();
    }

    public Student studentOfId(StudentId studentId) {
        final StudentDO studentDO = studentDAO.queryById(studentId.getId());
        final Address address = addressDAO.queryById(studentDO.getAddressId());
        return Student.create(new StudentId(studentDO.getId()), studentDO.getName(), address);
    }
}
