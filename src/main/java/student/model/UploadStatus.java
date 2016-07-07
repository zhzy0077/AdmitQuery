package student.model;

/**
 * Created by Zheng on 16/7/7.
 */
public class UploadStatus {
    private Boolean result;
    private String errorMessage;

    public UploadStatus() {
    }

    public UploadStatus(Boolean result, String errorMessage) {
        this.result = result;
        this.errorMessage = errorMessage;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
