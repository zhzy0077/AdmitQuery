package student.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import student.model.UploadStatus;
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
    public
    @ResponseBody
    UploadStatus uploadFile(@RequestPart("file") byte[] file) {
        UploadStatus uploadStatus = new UploadStatus();
        if (file != null && file.length != 0) {
            try {
                studentRepository.saveInformation(file);
                uploadStatus.setResult(true);
            } catch (Exception e) {
                uploadStatus.setResult(false);
                uploadStatus.setErrorMessage(e.getMessage());
                e.printStackTrace();
            }
        } else {
            uploadStatus.setResult(false);
            uploadStatus.setErrorMessage("file does not exist");
        }
        return uploadStatus;
    }
}
