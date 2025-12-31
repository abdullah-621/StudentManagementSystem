package com.example.student.util;

import com.example.student.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private static List<Student> students = new ArrayList<>();

    static {
        students = StudentDataManager.loadStudents();
    }

    // 🔹 Get all students
    public static List<Student> getAllStudents() {
        return students;
    }

    // 🔹 Find student by ID
    public static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    // 🔹 Add student
    public static void addStudent(Student student) {
        students.add(student);
        StudentDataManager.saveStudents(students);
    }

    // 🔹 Update student by ID
    public static boolean updateStudent(String id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, updatedStudent);
                StudentDataManager.saveStudents(students);
                return true;
            }
        }
        return false;
    }

    // 🔹 Delete student by ID
    public static boolean deleteStudent(String id) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            StudentDataManager.saveStudents(students);
            return true;
        }
        return false;
    }
}
