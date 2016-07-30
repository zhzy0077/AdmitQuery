package student.model;

/**
 * Created by Zheng on 16/7/7.
 */
public class Student {
    private String name;
    private String studentId;
    private String idCard;
    private String major;
    private Integer bell;
    private String ems;

    public Student() {
    }

    public Student(String name, String studentId, String idCard, String major, Integer bell, String ems) {
        this.name = name;
        this.studentId = studentId;
        this.idCard = idCard;
        this.major = major;
        this.bell = bell;
        this.ems = ems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getBell() {
        return bell;
    }

    public void setBell(Integer bell) {
        this.bell = bell;
    }

    public String getEms() {
        return ems;
    }

    public void setEms(String ems) {
        this.ems = ems;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
