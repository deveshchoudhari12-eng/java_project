package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> findCoursesBySemester(Semester semester) {
        return courses.stream()
                .filter(course -> course.getSemester() == semester)
                .collect(Collectors.toList());
    }
}