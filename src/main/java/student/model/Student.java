package student.model;

/**
 * Created by Zheng on 16/7/7.
 */
public class Student {
    private String name;
    private String major;
    private Integer bell;
    private Integer ems;

    public Student() {
    }

    public Student(String name, String major, Integer bell, Integer ems) {
        this.name = name;
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

    public Integer getEms() {
        return ems;
    }

    public void setEms(Integer ems) {
        this.ems = ems;
    }
}
