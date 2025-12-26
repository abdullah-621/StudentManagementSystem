package com.example.student.util;

import com.example.student.model.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentDataManager {
    private static final String DATA_FILE = "students.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Save students to JSON file
    public static void saveStudents(List<Student> students) {
        try (Writer writer = new FileWriter(DATA_FILE)) {
            gson.toJson(students, writer);
            System.out.println("✅ Data saved successfully!");
        } catch (IOException e) {
            System.err.println("❌ Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Load students from JSON file
    public static List<Student> loadStudents() {
        File file = new File(DATA_FILE);
        
        // If file doesn't exist, return empty list
        if (!file.exists()) {
            System.out.println("ℹ️ No existing data file found. Starting fresh.");
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(DATA_FILE)) {
            Type studentListType = new TypeToken<ArrayList<Student>>(){}.getType();
            List<Student> students = gson.fromJson(reader, studentListType);
            System.out.println("✅ Data loaded successfully! Found " + students.size() + " students.");
            return students != null ? students : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("❌ Error loading data: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}