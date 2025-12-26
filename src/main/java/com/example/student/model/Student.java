package com.example.student.model;

public class Student {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String semester;
    private String cgpa;

    // Constructor
    public Student(String id, String name, String email, String phone, String semester, String cgpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.semester = semester;
        this.cgpa = cgpa;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSemester() {
        return semester;
    }

    public String getCgpa() {
        return cgpa;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", semester='" + semester + '\'' +
                ", cgpa='" + cgpa + '\'' +
                '}';
    }
}