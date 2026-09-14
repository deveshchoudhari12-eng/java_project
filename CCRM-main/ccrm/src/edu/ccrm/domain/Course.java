package edu.ccrm.domain;

public class Course {
    private final String code;
    private final String title;
    private final int credits;
    private Instructor instructor;
    private Semester semester;

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }

    @Override
    public String toString() {
        String instructorName = (instructor != null) ? instructor.getFullName() : "TBD";
        return String.format("Course [%s: %s, Credits: %d, Instructor: %s, Semester: %s]",
                code, title, credits, instructorName, semester);
    }

    public static class Builder {
        private final String code;
        private final String title;
        private int credits = 3;
        private Instructor instructor;
        private Semester semester = Semester.FALL;

        public Builder(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}