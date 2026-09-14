package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findStudentByRegNo(String regNo) {
        return students.stream()
                .filter(student -> student.getRegNo().equalsIgnoreCase(regNo))
                .findFirst();
    }
}