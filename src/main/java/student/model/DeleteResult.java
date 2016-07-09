package student.model;

/**
 * Created by Zheng on 16/7/9.
 */
public class DeleteResult {
    private Integer status;

    public DeleteResult(Integer status) {
        this.status = status;
    }

    public DeleteResult() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
