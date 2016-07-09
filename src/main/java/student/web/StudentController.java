package student.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import student.model.Message;
import student.model.Student;
import student.repository.StudentRepository;

import javax.servlet.http.HttpSession;

/**
 * Created by Zheng on 16/7/7.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {
    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    Message message(@RequestParam(name = "studentId") String studentId,
                    @RequestParam(name = "idCard") String idCard,
                    @RequestParam(name = "captcha") String captcha, HttpSession session) {
        Student student = null;
        Message message = new Message();
        if (!captcha.equals(session.getAttribute("token"))) {
            message.setError_code(2);
            return message;
        }
        try {
            student = studentRepository.findStudent(studentId, idCard);
            if (student == null) {
                message.setError_code(1);
            } else {
                message.setError_code(0);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            message.setError_code(1);
        }
        message.setData(student);
        return message;
    }
}
