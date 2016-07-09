package student.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by Zheng on 16/7/8.
 */
@Repository
public class AdministratorRepositoryImpl implements AdministratorRepository {
    private JdbcOperations jdbcOperations;

    @Autowired
    public AdministratorRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Boolean checkPassword(String username, String password) {
        final String FETCH_PASSWORD = "SELECT password FROM student.administrator WHERE username = ?";
        String actualPassword = jdbcOperations.queryForObject(FETCH_PASSWORD,
                new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("password");
                    }
                }, username);
        return Objects.equals(actualPassword, password);
    }
}
