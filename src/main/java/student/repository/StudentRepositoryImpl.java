package student.repository;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import student.model.Student;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zheng on 16/7/7.
 */
@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final String SELECT_STUDENT = "SELECT name, major, bell, ems FROM student.student_info WHERE studentId = ? AND idCard = ?";
    private final String SAVE_STUDENT = "INSERT INTO student.student_info (name, studentId, idCard, major, bell, ems) VALUES (?, ?, ?, ?, ?, ?)";
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

    private void saveStudent(Student student, String studentId, String idCard) {
        System.out.println("Saving " + student.getName() + studentId + idCard + student.getMajor() + student.getBell() + student.getEms());
        jdbcOperations.update(SAVE_STUDENT, student.getName(), studentId, idCard, student.getMajor(), student.getBell(), student.getEms());
    }

    @Override
    public void saveInformation(byte[] uploadExcel) throws IOException, InvalidFormatException {
        InputStream excel = new ByteArrayInputStream(uploadExcel);
        Workbook book = WorkbookFactory.create(excel);
        Sheet workSheet = book.getSheet("学生信息");
        for (int index = workSheet.getFirstRowNum() + 1; index <= workSheet.getLastRowNum(); index++) {
            try {
                Row row = workSheet.getRow(index);
                String name = row.getCell(0).getRichStringCellValue().getString();
                String idCard = row.getCell(1).getRichStringCellValue().getString();
                String studentId = row.getCell(2).getRichStringCellValue().getString();
                String major = row.getCell(3).getRichStringCellValue().getString();
                Integer bell = Integer.valueOf(row.getCell(4).getRichStringCellValue().getString());
                Integer ems = Integer.valueOf(row.getCell(5).getRichStringCellValue().getString());
                Student student = new Student(name, major, bell, ems);
                if (name == null || idCard == null || studentId == null || major == null || bell == null) {
                    throw new IOException("Error Occurs at line " + (index + 1) + " Stops");
                }
                saveStudent(student, studentId, idCard);
            } catch (Exception e) {
                throw new IOException("Error Occurs at line " + (index + 1) + " Stops");
            }
        }
    }
}
