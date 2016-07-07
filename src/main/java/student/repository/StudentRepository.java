package student.repository;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Repository;
import student.model.Student;

import java.io.IOException;

/**
 * Created by Zheng on 16/7/7.
 */
@Repository
public interface StudentRepository {
    Student findStudent(String studentId, String idCard);

    void saveInformation(byte[] uploadExcel) throws IOException, InvalidFormatException;
}
