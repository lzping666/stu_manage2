package com.liao.pojo;

/**
 * @author lzp
 * @version 1.0
 * lzpnb!
 */
import java.util.Date;

public class Student {
    private int id;
    private String name;
    private String gender;
    private Date birthDate;
    private String studentId;
    private String qqNumber;

    public Student(int id, String name, String gender, Date birthDate, String studentId, String qqNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.studentId = studentId;
        this.qqNumber = qqNumber;
    }

    public Student() {
    }

    public Student(String name, String gender, Date birthDate, String studentId, String qqNumber) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.studentId = studentId;
        this.qqNumber = qqNumber;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }
}


