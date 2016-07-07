package student.model;

/**
 * Created by Zheng on 16/7/7.
 */
public class Message {
    private Integer error_code;
    private Student data;

    public Message() {
    }

    public Message(Integer error_code, Student data) {
        this.error_code = error_code;
        this.data = data;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }
}
