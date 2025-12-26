package com.example.student.util;

import com.example.student.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private static List<Student> students = new ArrayList<>();
    
    // Static block to load data when class is first loaded
    static {
        loadData();
    }

    // Load data from file
    private static void loadData() {
        students = StudentDataManager.loadStudents();
    }

    // Save data to file
    private static void saveData() {
        StudentDataManager.saveStudents(students);
    }

    public static void addStudent(Student student) {
        students.add(student);
        saveData(); // Auto-save after adding
    }

    public static List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return copy to prevent external modification
    }

    public static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public static boolean deleteStudent(String id) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            saveData(); // Auto-save after deleting
            return true;
        }
        return false;
    }

    public static boolean updateStudent(String id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                students.set(i, updatedStudent);
                saveData(); // Auto-save after updating
                return true;
            }
        }
        return false;
    }
}