package student.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import student.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zheng on 16/7/7.
 */
@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final String SELECT_STUDENT = "SELECT name, major, bell, ems FROM student.student_info WHERE studentId = ? AND idCard = ?";
    private JdbcOperations jdbcOperations;

    @Autowired
    public StudentRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Student findStudent(String studentId, String idCard) {
        Student student = jdbcOperations.queryForObject(SELECT_STUDENT, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                String name = rs.getString("name");
                String major = rs.getString("major");
                Integer bell = rs.getInt("bell");
                Integer ems = rs.getInt("ems");
                return new Student(name, major, bell, ems);
            }
        }, studentId, idCard);
        return student;
    }
}
