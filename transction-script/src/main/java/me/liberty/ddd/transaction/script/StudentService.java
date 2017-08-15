package me.liberty.ddd.transaction.script;

import me.liberty.ddd.domain.model.Phone;
import me.liberty.ddd.domain.model.Student;
import me.liberty.ddd.domain.repository.StudentRepository;
import me.liberty.ddd.transaction.script.dao.PhoneDAO;
import me.liberty.ddd.transaction.script.dao.StudentDAO;
import me.liberty.ddd.transaction.script.dos.PhoneDO;
import me.liberty.ddd.transaction.script.dos.StudentDO;

import java.sql.SQLException;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script, v 0.1 3/10/16
 *          Exp $
 */
public class StudentService {

    private StudentDAO studentDAO;

    private PhoneDAO phoneDAO;

    private StudentRepository studentRepository;

    public StudentService(){
        this.studentDAO = new StudentDAO();
        this.phoneDAO = new PhoneDAO();
    }

    public void changePhoneNo(String studentName, String phoneNo) throws SQLException {
        final StudentDO studentDO = studentDAO.queryByName(studentName);
        final PhoneDO phoneDO = phoneDAO.queryByStudentIdAndPhoneNo(studentDO.getId(), phoneNo);
        phoneDO.setDesc("这是一个被修改的号码");
        phoneDAO.update(phoneDO);
    }

    public void changePhoneNoOfDoaminModel(String studentName,Phone phone){
        final Student student = studentRepository.studentOfName(studentName);
        student.changePhone(phone);
        studentRepository.save(student);
    }
}
