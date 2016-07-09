package student.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import student.model.Student;
import student.repository.StudentRepository;

import javax.servlet.http.HttpSession;

/**
 * Created by Zheng on 16/7/8.
 */
@Controller
@RequestMapping("/operate")
public class OperateController {
    private StudentRepository studentRepository;

    @Autowired
    public OperateController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String operate(HttpSession session, Model model) {
        if (session.getAttribute("loginUsername") == null) {
            return "redirect:/admin";
        }
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("studentId", null);
        return "operate";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchStudent(@RequestParam("studentId") String studentId,
                                HttpSession session, Model model) {
        if (session.getAttribute("loginUsername") == null) {
            return "redirect:/admin";
        }
        try {
            Student student = studentRepository.findStudentById(studentId);
            model.addAttribute("student", student);
        } catch (Exception ignored) {
        }
        return "operate";
    }
}
