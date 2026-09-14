package edu.ccrm.domain;

import java.time.LocalDate;

public class Student extends Person {
    private String regNo;
    private LocalDate registrationDate;

    public Student(int id, String fullName, String email, String regNo) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.registrationDate = LocalDate.now();
    }

    public String getRegNo() {
        return regNo;
    }

    @Override
    public String getProfileDetails() {
        return String.format("Student Profile | Name: %s | Reg No: %s | Email: %s",
                getFullName(), this.regNo, getEmail());
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s", getId(), getFullName(), getEmail(), getRegNo());
    }
}