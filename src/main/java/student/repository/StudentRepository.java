package student.repository;

import org.springframework.stereotype.Repository;
import student.model.Student;

/**
 * Created by Zheng on 16/7/7.
 */
@Repository
public interface StudentRepository {
    Student findStudent(String studentId, String idCard);
}
