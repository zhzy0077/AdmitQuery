package student.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import student.model.DeleteResult;
import student.repository.StudentRepository;

import javax.servlet.http.HttpSession;

/**
 * Created by Zheng on 16/7/9.
 */
@Controller
@RequestMapping("/delete")
public class DeleteController {
    private StudentRepository studentRepository;

    @Autowired
    public DeleteController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    DeleteResult deleteStudent(@RequestParam("studentId") String studentId,
                               HttpSession session) {
        DeleteResult deleteResult = new DeleteResult();
        if (session.getAttribute("loginUsername") == null) {
            deleteResult.setStatus(1);
        } else {
            Boolean result = studentRepository.deleteStudent(studentId);
            if (result) {
                deleteResult.setStatus(0);
            } else {
                deleteResult.setStatus(1);
            }
        }
        return deleteResult;
    }

}
