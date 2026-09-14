package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final ImportExportService importExportService = new ImportExportService();
    private final BackupService backupService = new BackupService();

    public void display() {
        AppConfig.getInstance().loadConfigurations();
        loadInitialData();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Campus Course & Records Manager ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. File Operations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageStudents();
                case 2 -> manageCourses();
                case 3 -> manageFiles();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Thank you for using CCRM. Goodbye!");
    }

    private void manageStudents() {
        System.out.println("\n-- Student Management --");
        System.out.println("1. Add New Student");
        System.out.println("2. List All Students");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Registration Number: ");
            String regNo = scanner.nextLine();
            studentService.addStudent(new Student(id, name, email, regNo));
            System.out.println("Student added successfully.");
        } else if (choice == 2) {
            studentService.getAllStudents().forEach(s -> System.out.println(s.getProfileDetails()));
        }
    }

    private void manageCourses() {
        System.out.println("\n-- Course Management --");
        System.out.println("1. List All Courses");
        courseService.getAllCourses().forEach(System.out::println);
    }

    private void manageFiles() {
        System.out.println("\n-- File Operations --");
        System.out.println("1. Export All Students to CSV");
        System.out.println("2. Create a Backup");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            if (choice == 1) {
                importExportService.exportStudents(studentService.getAllStudents());
            } else if (choice == 2) {
                backupService.createBackup(Paths.get("data"));
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    private void loadInitialData() {
        studentService.addStudent(new Student(1, "Alice Johnson", "alice@example.com", "S001"));
        studentService.addStudent(new Student(2, "Bob Williams", "bob@example.com", "S002"));
        courseService.addCourse(new Course.Builder("CS101", "Intro to CS").credits(4).semester(Semester.FALL).build());
        courseService.addCourse(new Course.Builder("MA202", "Calculus II").credits(3).semester(Semester.SPRING).build());
    }
}