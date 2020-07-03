package me.liberty.ddd.transaction.script;

import me.liberty.ddd.domain.model.Phone;
import me.liberty.ddd.domain.model.Student;
import me.liberty.ddd.domain.repository.StudentRepository;
import me.liberty.ddd.transaction.script.dao.PhoneDAO;
import me.liberty.ddd.transaction.script.dao.StudentDAO;
import me.liberty.ddd.transaction.script.dos.PhoneDO;
import me.liberty.ddd.transaction.script.dos.StudentDO;
import org.apache.commons.lang.StringUtils;

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

    public void changePhoneNo(int studentId, String phoneNo) throws SQLException {

        StudentDO studentDO = studentDAO.queryById(studentId);
        if ("chinamobile".equals(studentDO.getUserType())) {
            final PhoneDO phoneDO = phoneDAO.queryByStudentIdAndPhoneNo(studentId, phoneNo);
            phoneDO.setNumber(phoneNo);
            phoneDAO.update(phoneDO);
        }
    }

    public void changePhoneNo1(int studentId, String phoneNo) throws SQLException {

        StudentDO studentDO = studentDAO.queryById(studentId);
        if ("chinamobile".equals(studentDO.getUserType()) && !"graduated".equals(studentDO.getStatus())) {
            final PhoneDO phoneDO = phoneDAO.queryByStudentIdAndPhoneNo(studentId, phoneNo);
            phoneDO.setNumber(phoneNo);
            phoneDAO.update(phoneDO);
        }
    }

    public void changePhoneNo2(int studentId, String phoneNo) throws SQLException {

        StudentDO studentDO = studentDAO.queryById(studentId);
        if (!"graduated".equals(studentDO.getStatus()) && studentDO.getAge() > 12) {
            if ("chinamobile".equals(studentDO.getUserType()) && isChinaMobile(phoneNo)) {
                final PhoneDO phoneDO = phoneDAO.queryByStudentIdAndPhoneNo(studentId, phoneNo);
                phoneDO.setNumber(phoneNo);
                phoneDAO.update(phoneDO);
            }
        }
    }

    public void sendMessage(int studentId, String phoneNo) throws SQLException {

        StudentDO studentDO = studentDAO.queryById(studentId);
        if ("graduated".equals(studentDO.getStatus()) && "chinamobile".equals(studentDO.getUserType())) {
            send("母校欢迎你");
            return;
        }
        if ("graduated".equals(studentDO.getStatus()) && "chinaunicom".equals(studentDO.getUserType())){
            send("欢迎回家");
            return;
        }
    }

    public void changePhoneNoOfDoaminModel(String studentName,Phone phone){
        final Student student = studentRepository.studentOfName(studentName);
        student.changePhone(phone);
        studentRepository.save(student);
    }

    private boolean isChinaMobile(String phone) {
        return true;
    }

    public void send(String message) {

    }
}
