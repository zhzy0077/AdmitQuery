package student.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import student.repository.StudentRepository;

/**
 * Created by Zheng on 16/7/7.
 */
@Controller
@RequestMapping("upload")
public class ExcelUploadController {
    private StudentRepository studentRepository;

    @Autowired
    public ExcelUploadController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String uploadFile(@RequestPart("file") byte[] file, Model model) {
        if (file != null && file.length != 0) {
            try {
                studentRepository.saveInformation(file);
                model.addAttribute("status", "上传成功");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("status", "上传失败, " + e.getMessage());
            }
        } else {
            model.addAttribute("status", "上传失败, 文件为空");
        }
        return "uploadResult";
    }
}
