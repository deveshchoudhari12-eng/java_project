package edu.ccrm.domain;

public class Instructor extends Person {
    private String department;
    private String title;

    public Instructor(int id, String fullName, String email, String department, String title) {
        super(id, fullName, email);
        this.department = department;
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getProfileDetails() {
        return String.format("Instructor Profile | Name: %s | Title: %s | Dept: %s",
                getFullName(), this.title, this.department);
    }
}