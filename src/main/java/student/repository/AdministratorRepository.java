package student.repository;

/**
 * Created by Zheng on 16/7/8.
 */
public interface AdministratorRepository {
    Boolean checkPassword(String username, String password);
}
